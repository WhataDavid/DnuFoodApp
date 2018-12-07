package cn.dnui_dx602.dnuifood16110100602.controller;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tandong.swichlayout.SwitchLayout;

import cn.dnui_dx602.dnuifood16110100602.R;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SwitchLayout.animDuration=900;
    }
}
