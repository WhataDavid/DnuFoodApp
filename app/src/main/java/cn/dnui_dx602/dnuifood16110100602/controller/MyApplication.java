package cn.dnui_dx602.dnuifood16110100602.controller;

import android.app.Application;

import com.tandong.swichlayout.SwitchLayout;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SwitchLayout.animDuration=900;
    }
}
