package cn.dnui_dx602.dnuifood16110100602.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import cn.dnui_dx602.dnuifood16110100602.R;

public class GetFoodByShopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Intent intent=getIntent();
        Toast.makeText(this,getIntent().getStringExtra("id") , Toast.LENGTH_SHORT).show();
        System.out.println(getIntent().getStringExtra("id"));
        setContentView(R.layout.activity_get_food_by_shop);
    }
}
