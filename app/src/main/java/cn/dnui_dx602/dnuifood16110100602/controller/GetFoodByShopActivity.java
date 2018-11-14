package cn.dnui_dx602.dnuifood16110100602.controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import cn.dnui_dx602.dnuifood16110100602.R;
import cn.dnui_dx602.dnuifood16110100602.adapter.FoodAdapter;
import cn.dnui_dx602.dnuifood16110100602.adapter.ShopAdapter;
import cn.dnui_dx602.dnuifood16110100602.bean.FoodBean;
import cn.dnui_dx602.dnuifood16110100602.bean.ShopBean;

public class GetFoodByShopActivity extends BaseActivity {
    SharedPreferences sharedPreferences;
    private TextView shopname,phonenum,intro;
    ImageView imageView;
    RecyclerView recyclerView;
    String shopnameString,phonenumString,picString,introString,shopidString;
    @Override
    void initViews() {
        setLayout(R.layout.activity_get_food_by_shop);
        shopname=findViewById(R.id.shopname);
        phonenum=findViewById(R.id.phonenum);
        imageView=findViewById(R.id.image);
//        intro=findViewById(R.id.intro);
        recyclerView=findViewById(R.id.food_list);
    }

    @Override
    void initEvents() {

    }

    @Override
    void initDatas() {
        sharedPreferences=getSharedPreferences("INFOR", Context.MODE_PRIVATE);
         shopnameString = sharedPreferences.getString("shopname","");
         phonenumString = sharedPreferences.getString("phonenum","");
//        String introString = sharedPreferences.getString("intro","");
         picString = sharedPreferences.getString("pic","");
         shopidString = sharedPreferences.getString("shopid","");

        shopname.setText(shopnameString);
        phonenum.setText("订餐电话："+phonenumString);
//        intro.setText(introString);
        String url = "http://172.24.10.175:8080/foodService/" + picString;
        Picasso.get().load(url).into(imageView);

        List<FoodBean> mDataList;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
        getData();
    }

    private void getData() {
        String string="http://172.24.10.175:8080/foodService/getFoodByShop.do?shop_id="+shopidString;
        new AsyncTask<String, Void, Void>() {
            List<FoodBean> foodsList;
            @Override
            protected void onPostExecute(Void aVoid) {
                recyclerView.setAdapter(new FoodAdapter(foodsList));
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
                        System.out.println("菜单返回信息"+line );
                        foodsList = new Gson().fromJson(line, new TypeToken<List<FoodBean>>(){}.getType());
                        System.out.println(foodsList.get(0).toString());

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
        }.execute(string);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initViews();
        initEvents();
        initDatas();
        super.onCreate(savedInstanceState);
    }


}
