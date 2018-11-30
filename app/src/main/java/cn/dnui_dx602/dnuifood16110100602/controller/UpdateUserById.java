package cn.dnui_dx602.dnuifood16110100602.controller;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import cn.dnui_dx602.dnuifood16110100602.R;
import cn.dnui_dx602.dnuifood16110100602.bean.RegistBean;

public class UpdateUserById extends BaseActivity {

    String usernameString, passwordString, mobileString, addressString;
    EditText username, password, phone, address,originalPass;
    Button button;
    Gson gson;
    RegistBean registBean;
    Boolean success=false;

    @Override
    void initViews() {
        setLayout(R.layout.activity_update_user_by_id);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        phone = findViewById(R.id.mobiletext);
        address = findViewById(R.id.add);
        button= findViewById(R.id.button);
        originalPass=findViewById(R.id.originalPass);

        Intent intent = getIntent();
        usernameString = intent.getStringExtra("username");
        passwordString = intent.getStringExtra("password");
        mobileString = intent.getStringExtra("mobile");
        addressString = intent.getStringExtra("address");
        username.setText(usernameString);
//        password.setText(passwordString);
        phone.setText(mobileString);
        address.setText(addressString);
        System.out.println(usernameString + "zzzz");


    }

    @Override
    void initEvents() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UpdateUserById.this,LoginActivity.user_pass+"///"+originalPass.getText() , Toast.LENGTH_SHORT).show();
                if (LoginActivity.user_pass.equals(originalPass.getText().toString()))
                {
                    usernameString=username.getText().toString();
//                passwordString=password.getText().toString();
                    mobileString=phone.getText().toString();
                    addressString=address.getText().toString();
                    LoginActivity.user_pass=passwordString;
                    String string2 = "http://172.24.10.175:8080/foodService/updateUserById.do?user_id="+LoginActivity.user_id+"&username=" + usernameString + "&userpass=" + passwordString + "&mobilenum=" +
                            mobileString + "&address=" + addressString;
                    new AsyncTask<String, Void, Void>() {
                        @Override
                        protected void onPostExecute(Void aVoid) {
                            super.onPostExecute(aVoid);
                            if (success==true)
                            {
                                Toast.makeText(UpdateUserById.this, "修改成功", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(UpdateUserById.this, "修改失败", Toast.LENGTH_SHORT).show();
                            }

                        }

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
                                    registBean = gson.fromJson(line, RegistBean.class);

                                    System.out.println(registBean.getSuccess());
                                    if (registBean.getSuccess().equals("0")) {
                                        System.out.println("修改失败");
                                        success=false;
                                    } else {
                                        System.out.println("修改成功");
                                        success=true;
                                    }


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
                    }.execute(string2);
                }
                else {
                    Toast.makeText(UpdateUserById.this, "原始密码错误", Toast.LENGTH_SHORT).show();
                }
                



            }
        });
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
//        while (true)
//        {
//            if (success==true)
//            {
//                Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
//                break;
//            }
//        }

    }
}
