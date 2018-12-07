package cn.dnui_dx602.dnuifood16110100602.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tandong.swichlayout.BaseEffects;
import com.tandong.swichlayout.SwitchLayout;

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
import cn.dnui_dx602.dnuifood16110100602.controller.LoginActivity;


public class Fragment2 extends Fragment {
    RecyclerView recyclerView;
    TabLayout tabLayout;
    TabLayout.Tab item1,item2;
    CollectShopAdapter shopadapter;
    CollectFoodAdapter foodadapter;
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
        initEvent();

        super.onStart();
    }

    private void initEvent() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.equals(item1))
                {
                    getData("http://172.24.10.175:8080/foodService/getAllUserCollection.do?user_id="+ LoginActivity.user_id+"&flag=0", 1);
                }
                else
                    getData("http://172.24.10.175:8080/foodService/getAllUserCollection.do?user_id="+LoginActivity.user_id+"&flag=1", 0);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void initData() {

        recyclerView = getActivity().findViewById(R.id.collect_list);
        SwitchLayout.ScaleToBigHorizontalIn(recyclerView,false, BaseEffects.getMoreQuickEffect());

        tabLayout = getActivity().findViewById(R.id.tabs);
         item1=tabLayout.getTabAt(0);
         item2=tabLayout.getTabAt(1);
        item1.setText("店铺");
        item2.setText("菜品");

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        getData("http://172.24.10.175:8080/foodService/getAllUserCollection.do?user_id=15&flag=0", 1);
        shopadapter = new CollectShopAdapter(getContext());
        foodadapter = new CollectFoodAdapter(getContext());
        recyclerView.setAdapter(shopadapter);

    }

    private void getData(String url, final int flag) {

        new AsyncTask<String, Void, Void>() {
            List<CollectionsBean> collectionsBeanList;

            @Override
            protected void onPostExecute(Void aVoid) {
                if (flag == 1)
                {
                    recyclerView.setAdapter(shopadapter);
                    shopadapter.setList(collectionsBeanList);
                    shopadapter.notifyDataSetChanged();
                }

//                    recyclerView.setAdapter(new CollectShopAdapter(getContext(), collectionsBeanList));
                else
                {
                    recyclerView.setAdapter(foodadapter);
                    foodadapter.setList(collectionsBeanList);
                    foodadapter.notifyDataSetChanged();

                }
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
