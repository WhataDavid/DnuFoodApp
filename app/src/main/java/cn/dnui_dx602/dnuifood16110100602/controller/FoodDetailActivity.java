package cn.dnui_dx602.dnuifood16110100602.controller;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import cn.dnui_dx602.dnuifood16110100602.R;
import cn.dnui_dx602.dnuifood16110100602.adapter.MyPagerAdapter;

public class FoodDetailActivity extends BaseActivity {
TextView textView;
    private ViewPager viewpager_one;
    private ArrayList<View> aList;
    private MyPagerAdapter mAdapter;

    @Override
    void initViews() {
        setContentView(R.layout.activity_food_detail);
        textView=findViewById(R.id.duxu);
        textView.setText(getIntent().getStringExtra("foodid"));
//        viewpager
        viewpager_one = (ViewPager) findViewById(R.id.viewpager);
        aList = new ArrayList<View>();
        LayoutInflater li = getLayoutInflater();
        aList.add(li.inflate(R.layout.view_one,null,false));
        aList.add(li.inflate(R.layout.view_two,null,false));
        aList.add(li.inflate(R.layout.view_three,null,false));
        mAdapter = new MyPagerAdapter(aList);
        viewpager_one.setAdapter(mAdapter);
    }

    @Override
    void initEvents() {

    }

    @Override
    void initDatas() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        initDatas();
        initDatas();
    }
}
