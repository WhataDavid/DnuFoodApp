package cn.dnui_dx602.dnuifood16110100602.controller;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import cn.dnui_dx602.dnuifood16110100602.R;
import cn.dnui_dx602.dnuifood16110100602.bean.UserBean;
import cn.dnui_dx602.dnuifood16110100602.listener.onRetrofitListener;
import cn.dnui_dx602.dnuifood16110100602.model.UserModel;

public class LoginActivity extends BaseActivity
 implements onRetrofitListener<UserBean>,
        View.OnClickListener{
    private UserModel model;
    private Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        initDatas();
        initEvents();
    }



    @Override
    void initViews() {
        layout_file= R.layout.activity_login;
        setLayout(layout_file);

        btn_login = findViewById(R.id.btn_login);

    }

    @Override
    void initEvents() {
        btn_login.setOnClickListener(this);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AsyncTask<String,Void,Void>()
                {

                    @Override
                    protected Void doInBackground(String... strings) {
                        try {
                            URL url=new URL(strings [0]);
                            URLConnection connection=url.openConnection();
                            InputStream inputStream=connection.getInputStream();
                            InputStreamReader inputStreamReader=new InputStreamReader(inputStream,"utf-8");
                            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                            String line;
                            while ((line=bufferedReader.readLine())!=null)
                            {
                                System.out.println(line);
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
                }.execute("http://172.24.10.175:8080/foodService/userLogin.do?username=16110100601&userpass=11");
                Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    void initDatas() {

    }
    @Override
    public void onSuccess(UserBean object) {
        Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFalure(String msg) {
        Toast.makeText(LoginActivity.this, "Falut", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onClick(View view) {
        model = new UserModel();
        model.login("1","1",this);
    }
}
