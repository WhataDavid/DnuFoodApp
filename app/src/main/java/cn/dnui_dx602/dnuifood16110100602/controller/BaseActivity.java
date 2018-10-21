package cn.dnui_dx602.dnuifood16110100602.controller;

import android.os.Bundle;

import cn.dnui_dx602.dnuifood16110100602.R;


public abstract class BaseActivity extends AppCompatActivity {
    protected int layout_file = R.layout.activity_main;
    abstract void initViews();
    abstract void initEvents();
    abstract void initDatas();
    void setLayout(int layout_file)
    {
        setContentView(layout_file);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setLayout(layout_file);
        initViews();
        initEvents();
        initDatas();
    }
}
