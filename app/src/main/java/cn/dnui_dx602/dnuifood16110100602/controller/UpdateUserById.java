package cn.dnui_dx602.dnuifood16110100602.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.dnui_dx602.dnuifood16110100602.R;

public class UpdateUserById extends BaseActivity {

    @Override
    void initViews() {
        setLayout(R.layout.activity_update_user_by_id);
        Intent intent = new Intent();
        intent.getExtras("username");
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
        initEvents();

    }
}
