package cn.dnui_dx602.dnuifood16110100602.controller;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tandong.swichlayout.BaseEffects;
import com.tandong.swichlayout.SwitchLayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import cn.dnui_dx602.dnuifood16110100602.R;
import cn.dnui_dx602.dnuifood16110100602.bean.UserBean;
import cn.dnui_dx602.dnuifood16110100602.model.UserModel;

public class  LoginActivity extends BaseActivity {
    private UserModel model;
    private Button btn_login,btn_regist;
    public EditText usernameEditText, userpassEditText;
     UserBean userBean=new UserBean();
    Gson gson;
    String usernameString, userpassString;
    public static String user_id,user_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        initDatas();
        initEvents();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    void initViews() {
        layout_file = R.layout.activity_login;
        setLayout(layout_file);
        SwitchLayout.RotateCenterIn(this,false,BaseEffects.getMoreQuickEffect());
        btn_regist=findViewById(R.id.btn_regist);
        btn_login = findViewById(R.id.btn_login);
        usernameEditText = findViewById(R.id.username);
        userpassEditText = findViewById(R.id.password);

    }

    @Override
    void initEvents() {
//        btn_login.setOnClickListener(this);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                BaseAnimViewS.animDuration = 2000;
                SwitchLayout.get3DRotateFromLeft(btn_login,false,null);
                usernameString = usernameEditText.getText().toString();
                userpassString = userpassEditText.getText().toString();
                user_pass=usernameString;
                String string = "http://172.24.10.175:8080/foodService/userLogin.do?username=" + usernameString + "&userpass=" + userpassString;
                new AsyncTask<String, Void, Void>() {


                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                        if (userBean.getUserid().equals("0"))
                        {
                            System.out.println("登录失败");
                            Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            System.out.println("登录成功");
                            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            System.out.println(user_pass);
                            Intent intent = new Intent(LoginActivity.this, BottomNavActivity.class);
                            startActivity(intent);
//                            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
//                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }

                    }

                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
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
                                System.out.println(line + "123");
                                gson = new Gson();
                                userBean = gson.fromJson(line, UserBean.class);
                                System.out.println(userBean.getUserid());
                                user_id=userBean.getUserid();
                                user_pass=userpassString;


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
//                    "http://172.24.10.175:8080/foodService/userLogin.do?username=16110100602&userpass=11"
                }.execute(string);

            }
        });

        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                SwitchLayout.get3DRotateFromLeft(btn_regist,false,null);
//                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
//                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }

    @Override
    void initDatas() {

    }

}
