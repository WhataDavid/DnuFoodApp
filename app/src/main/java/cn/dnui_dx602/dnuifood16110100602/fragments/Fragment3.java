package cn.dnui_dx602.dnuifood16110100602.fragments;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tandong.swichlayout.SwitchLayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.List;

import cn.dnui_dx602.dnuifood16110100602.R;
import cn.dnui_dx602.dnuifood16110100602.adapter.FoodAdapter;
import cn.dnui_dx602.dnuifood16110100602.adapter.HistoryAdapter;
import cn.dnui_dx602.dnuifood16110100602.bean.FoodBean;
import cn.dnui_dx602.dnuifood16110100602.bean.HistoryBean;
import cn.dnui_dx602.dnuifood16110100602.database.Db;


public class Fragment3 extends Fragment {
    RecyclerView recyclerView;
    SearchView searchView;
    FoodAdapter foodAdapter;
    List<FoodBean> foodBeanList;
    private List<HistoryBean> mData = null;
    private HistoryAdapter mAdapter = null;
    private ListView list_history;
    ContentValues cv;
    SQLiteDatabase dbWrite;
    Db db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment3, container, false);
    }

    @Override
    public void onStart() {
        db = new Db(getContext(), "history", null, 1);
        dbWrite = db.getWritableDatabase();
        dbWrite.close();


        initData();
        initEvent();

        super.onStart();
    }

    private void initEvent() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                getData("http://172.24.10.175:8080/foodService/getFoodBySearch.do?search=" + s);
                    recyclerView.getAdapter().notifyDataSetChanged();
                dbWrite = db.getWritableDatabase();



                cv.put("name", s);
                dbWrite.insert("history", null, cv);
                dbWrite.close();
//                mAdapter = new HistoryAdapter((LinkedList<HistoryBean>) mData, getContext());
//                list_history.setAdapter(mAdapter);
//                initdb();
//                mAdapter.setList(new LinkedList<HistoryBean>);
                mData = new LinkedList<HistoryBean>();
                initdb();
//        mData.add(new HistoryBean("123456"));
//        mData.add(new HistoryBean("7890"));

//                mAdapter = new HistoryAdapter((LinkedList<HistoryBean>) mData, getContext());
//                list_history.setAdapter(mAdapter);

                mAdapter.setList((LinkedList<HistoryBean>) mData);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s != null) {
                    getData("http://172.24.10.175:8080/foodService/getFoodBySearch.do?search=" + s);
                } else {
                    for (int i = 0; i < recyclerView.getChildCount(); i++)
                        recyclerView.getAdapter().notifyItemRemoved(i);
                }
                recyclerView.getAdapter().notifyDataSetChanged();
                return false;
            }


        });
    }

    public void initData() {
        recyclerView = getActivity().findViewById(R.id.search_list);
        SwitchLayout.getFadingIn(recyclerView);
        searchView = getActivity().findViewById(R.id.searchView);
        SwitchLayout.ScaleToBigVerticalIn(searchView, false, null);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        foodAdapter = new FoodAdapter();
        recyclerView.setAdapter(foodAdapter);

        list_history=(ListView) getActivity().findViewById(R.id.history_list);
        mData = new LinkedList<HistoryBean>();
        initdb();
//        mData.add(new HistoryBean("123456"));
//        mData.add(new HistoryBean("7890"));
        mAdapter = new HistoryAdapter((LinkedList<HistoryBean>) mData, getContext());
        list_history.setAdapter(mAdapter);

    }

    public void initdb() {



        cv = new ContentValues();
//        dbWrite.execSQL("delete from history");

//        cv.put("name", "test");
//        dbWrite.insert("history", null, cv);
//        cv.put("name", "test2");
//        dbWrite.insert("history", null, cv);
//        cv.put("name", "test3");
//        dbWrite.insert("history", null, cv);
//        cv.put("name", "test4");
//        dbWrite.insert("history", null, cv);

        SQLiteDatabase dbRead = db.getReadableDatabase();
        Cursor c = dbRead.query(true,"history",new String[]{"name"}, null, null, null, null, null, null);
        while (c.moveToNext()) {
            String name = c.getString(c.getColumnIndex("name"));
            mData.add(new HistoryBean(name));
            System.out.println("！！！！！！！！！！！！！！！！！！！！！" + name);
        }


    }


    private void getData(String url) {

        new AsyncTask<String, Void, Void>() {

            @Override
            protected void onPostExecute(Void aVoid) {
                foodAdapter.setList(foodBeanList);
                foodAdapter.notifyDataSetChanged();
                super.onPostExecute(aVoid);
            }

            @Override
            protected Void doInBackground(String... strings) {
                try {
                    URL url = new URL(strings[0]);
                    URLConnection connection = url.openConnection();
                    InputStream inputStream = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        System.out.println("搜索列表返回信息" + line);
                        foodBeanList = new Gson().fromJson(line, new TypeToken<List<FoodBean>>() {
                        }.getType());

                        bufferedReader.close();
                        inputStreamReader.close();
                        inputStream.close();
                    }

                } catch (MalformedURLException e) {

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute(url);
    }
}
