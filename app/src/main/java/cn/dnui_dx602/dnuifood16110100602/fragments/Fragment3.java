package cn.dnui_dx602.dnuifood16110100602.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import cn.dnui_dx602.dnuifood16110100602.R;
import cn.dnui_dx602.dnuifood16110100602.adapter.CollectFoodAdapter;
import cn.dnui_dx602.dnuifood16110100602.adapter.CollectShopAdapter;
import cn.dnui_dx602.dnuifood16110100602.adapter.FoodAdapter;
import cn.dnui_dx602.dnuifood16110100602.adapter.ShopAdapter;
import cn.dnui_dx602.dnuifood16110100602.bean.CollectionsBean;
import cn.dnui_dx602.dnuifood16110100602.bean.FoodBean;
import cn.dnui_dx602.dnuifood16110100602.bean.ShopBean;
import cn.dnui_dx602.dnuifood16110100602.controller.LoginActivity;


public class Fragment3 extends Fragment {
RecyclerView recyclerView;
SearchView searchView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment3, container, false);
    }

    @Override
    public void onStart() {

        initData();
        initEvent();

        super.onStart();
    }

    private void initEvent() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                getData("http://172.24.10.175:8080/foodService/getFoodBySearch.do?search="+s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s!=null)
                {
                    getData("http://172.24.10.175:8080/foodService/getFoodBySearch.do?search="+s);
                }
                else
                {
                    for (int i=0;i<recyclerView.getChildCount();i++)
                        recyclerView.getAdapter().notifyItemRemoved(i);
                }
                return false;
            }
        });
    }

    public void initData() {

        recyclerView = getActivity().findViewById(R.id.search_list);
        searchView=getActivity().findViewById(R.id.searchView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));


    }

    private void getData(String url) {

        new AsyncTask<String, Void, Void>() {
            List<FoodBean> foodBeanList;

            @Override
            protected void onPostExecute(Void aVoid) {
                    recyclerView.setAdapter(new FoodAdapter( foodBeanList));
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
