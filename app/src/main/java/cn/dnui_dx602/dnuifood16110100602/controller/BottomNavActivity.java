package cn.dnui_dx602.dnuifood16110100602.controller;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import cn.dnui_dx602.dnuifood16110100602.R;
import cn.dnui_dx602.dnuifood16110100602.fragments.Fragment1;
import cn.dnui_dx602.dnuifood16110100602.fragments.Fragment2;
import cn.dnui_dx602.dnuifood16110100602.fragments.Fragment3;
import cn.dnui_dx602.dnuifood16110100602.fragments.Fragment4;

public class BottomNavActivity extends BaseActivity {
    private TextView mTextMessage;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener;

    @Override
    void initViews() {
        layout_file = R.layout.activity_bottom_nav;
        setLayout(layout_file);
        setDefaultFragment();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    void initEvents() {
        mOnNavigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("ResourceType")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                fragmentManager = getSupportFragmentManager();
                transaction = fragmentManager.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                        transaction.replace(R.id.content,new Fragment1());
                        transaction.commit();
                        return true;
                    case R.id.navigation_dashboard:
                        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                        transaction.replace(R.id.content,new Fragment2());
                        transaction.commit();
                        return true;
                    case R.id.navigation_notifications:
                        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        transaction.replace(R.id.content,new Fragment3());
                        transaction.commit();
                        return true;
                    case R.id.navigation_four:
                        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        transaction.replace(R.id.content,new Fragment4());
                        transaction.commit();
                        return true;
                }


                return false;
            }
        };

    }

    @Override
    void initDatas() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        initEvents();
    }

    private FragmentTransaction transaction;
    private FragmentManager fragmentManager;

    // 设置默认进来是tab 显示的页面
    private void setDefaultFragment(){
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content,new Fragment1());
        transaction.commit();
    }

}
