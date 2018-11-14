package cn.dnui_dx602.dnuifood16110100602.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import cn.dnui_dx602.dnuifood16110100602.bean.ShopBean;
import cn.dnui_dx602.dnuifood16110100602.adapter.ShopAdapter;


public class Fragment1 extends Fragment {
    RecyclerView recyclerView;
    // Inflate the layout for this fragment

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment1, container, false);
    }

    @Override
    public void onStart() {

        initData();

        super.onStart();
    }
    public void initData() {

        List<ShopBean> mDataList;
        recyclerView=getActivity().findViewById(R.id.shop_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
       getData();


    }

    private void getData() {

        new AsyncTask<String, Void, Void>() {
            List<ShopBean> shopsList;
            @Override
            protected void onPostExecute(Void aVoid) {
                recyclerView.setAdapter(new ShopAdapter(getContext(),shopsList));
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
                        System.out.println("店铺返回信息"+line );
                        shopsList = new Gson().fromJson(line, new TypeToken<List<ShopBean>>(){}.getType());

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
        }.execute("http://172.24.10.175:8080/foodService/getAllShops.do");
    }
}
