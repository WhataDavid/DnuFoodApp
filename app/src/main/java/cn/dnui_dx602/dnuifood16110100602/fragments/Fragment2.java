package cn.dnui_dx602.dnuifood16110100602.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabItem;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.design.widget.TabLayout;

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
import cn.dnui_dx602.dnuifood16110100602.adapter.ShopAdapter;
import cn.dnui_dx602.dnuifood16110100602.bean.CollectionsBean;
import cn.dnui_dx602.dnuifood16110100602.bean.ShopBean;


public class Fragment2 extends Fragment {
    RecyclerView recyclerView;
    TabItem tabItem1, tabItem2;
    TabLayout tabLayout;

    //    TabLayout.Tab tab = tablayout.getTabAt(0);
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment2, container, false);
    }

    @Override
    public void onStart() {

        initData();

        super.onStart();
    }

    public void initData() {

        recyclerView = getActivity().findViewById(R.id.collect_list);

        tabLayout = getActivity().findViewById(R.id.tabs);
        tabItem1 = tabLayout.findViewById(R.id.tab1);
        tabItem2 = tabLayout.findViewById(R.id.tab2);
//        tableLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
//        tabItem1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//        getData("http://172.24.10.175:8080/foodService/getAllUserCollection.do?user_id=15&flag=0", 1);
//            }
//        });
//        tabItem2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
        getData("http://172.24.10.175:8080/foodService/getAllUserCollection.do?user_id=15&flag=1", 0);
//            }
//        });


    }

    private void getData(String url, final int flag) {

        new AsyncTask<String, Void, Void>() {
            List<CollectionsBean> collectionsBeanList;

            @Override
            protected void onPostExecute(Void aVoid) {
                if (flag == 1)
                    recyclerView.setAdapter(new CollectShopAdapter(getContext(), collectionsBeanList));
                else
                    recyclerView.setAdapter(new CollectFoodAdapter(getContext(), collectionsBeanList));
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
                        System.out.println("收藏列表返回信息" + line);
                        collectionsBeanList = new Gson().fromJson(line, new TypeToken<List<CollectionsBean>>() {
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
