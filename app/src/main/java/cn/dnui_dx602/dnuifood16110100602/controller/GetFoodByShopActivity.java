package cn.dnui_dx602.dnuifood16110100602.controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import cn.dnui_dx602.dnuifood16110100602.R;
import cn.dnui_dx602.dnuifood16110100602.adapter.ShopAdapter;

public class GetFoodByShopActivity extends BaseActivity {
    SharedPreferences sharedPreferences;
    private TextView shopname,phonenum,intro;
    ImageView imageView;
    @Override
    void initViews() {
        setLayout(R.layout.activity_get_food_by_shop);
        shopname=findViewById(R.id.shopname);
        phonenum=findViewById(R.id.phonenum);
        imageView=findViewById(R.id.image);
//        intro=findViewById(R.id.intro);
    }

    @Override
    void initEvents() {

    }

    @Override
    void initDatas() {
        sharedPreferences=getSharedPreferences("INFOR", Context.MODE_PRIVATE);
        String shopnameString = sharedPreferences.getString("shopname","");
        String phonenumString = sharedPreferences.getString("phonenum","");
//        String introString = sharedPreferences.getString("intro","");
        String picString = sharedPreferences.getString("pic","");
        System.out.println("shopname:"+shopnameString);
        shopname.setText(shopnameString);
        phonenum.setText("订餐电话："+phonenumString);
//        intro.setText(introString);
        String url = "http://172.24.10.175:8080/foodService/" + picString;
        Picasso.get().load(url).into(imageView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initViews();
        initEvents();
        super.onCreate(savedInstanceState);
    }


}
