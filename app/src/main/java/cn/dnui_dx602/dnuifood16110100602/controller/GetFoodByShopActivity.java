package cn.dnui_dx602.dnuifood16110100602.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

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
import cn.dnui_dx602.dnuifood16110100602.bean.FoodBean;

public class GetFoodByShopActivity extends BaseActivity {
    SharedPreferences sharedPreferences;
    private TextView shopname,phonenum,intro,detail,dialog_shopname,dialog_phonenum,dialog_address,dialog_intro;
    RatingBar dialog_ratingBar;
    ImageView imageView,dialog_imageView;
    RecyclerView recyclerView;
    String shopnameString,phonenumString,picString,introString,shopidString,levelString,addressString;

    @Override
    void initViews() {
        setLayout(R.layout.activity_get_food_by_shop);
        shopname=findViewById(R.id.shopname);
        phonenum=findViewById(R.id.phonenum);
        imageView=findViewById(R.id.image);
//        intro=findViewById(R.id.intro);
        recyclerView=findViewById(R.id.food_list);
        detail=findViewById(R.id.detail);





    }

    @Override
    void initEvents() {
        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(GetFoodByShopActivity.this);
                final AlertDialog dialog = builder.create();
                View dialogView = View.inflate(GetFoodByShopActivity.this, R.layout.dialog_detail, null);



                //设置对话框布局
                dialog.setView(dialogView);

                dialog_shopname=dialogView.findViewById(R.id.dialog_shopnameView2);
                dialog_address=dialogView.findViewById(R.id.dialog_addressView);
                dialog_imageView=dialogView.findViewById(R.id.dialog_imageView);
                dialog_intro=dialogView.findViewById(R.id.dialog_introView);
                dialog_phonenum=dialogView.findViewById(R.id.dialog_phonenumView);
                dialog_ratingBar=dialogView.findViewById(R.id.dialog_ratingBar);

                dialog_shopname.setText(shopnameString);
                dialog_address.setText(addressString);
                dialog_intro.setText(introString);
                dialog_phonenum.setText(phonenumString);
                dialog_ratingBar.setNumStars(Integer.parseInt(levelString));
                String url = "http://172.24.10.175:8080/foodService/" + picString;
                Picasso.get().load(url).into(dialog_imageView);
                dialog.show();


            }
        });
    }

    @Override
    void initDatas() {
        sharedPreferences=getSharedPreferences("INFOR", Context.MODE_PRIVATE);
         shopnameString = sharedPreferences.getString("shopname","");
         phonenumString = sharedPreferences.getString("phonenum","");
         introString = sharedPreferences.getString("intro","");
         picString = sharedPreferences.getString("pic","");
         shopidString = sharedPreferences.getString("shopid","");
         levelString = sharedPreferences.getString("level","");
        addressString=sharedPreferences.getString("address","");
        System.out.println("Address:--------------------------"+addressString);
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
