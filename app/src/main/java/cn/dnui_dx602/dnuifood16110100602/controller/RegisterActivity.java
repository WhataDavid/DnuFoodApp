package cn.dnui_dx602.dnuifood16110100602.controller;

import android.os.AsyncTask;
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

public class RegisterActivity extends BaseActivity {
    private EditText username, password,phone,add,comment;
    private String usernameString, passwordString,phoneString,addString,commentString;
    private Button button;
    RegistBean registBean;
    Gson gson;

    @Override
    void initViews() {
        setContentView(R.layout.activity_regist2);
        button=findViewById(R.id.button);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        phone = findViewById(R.id.mobiletext);
        add = findViewById(R.id.add);
        comment = findViewById(R.id.comment);
    }

    @Override
    void initEvents() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usernameString = username.getText().toString();
                passwordString = password.getText().toString();
                phoneString = phone.getText().toString();
                addString = add.getText().toString();
                commentString = comment.getText().toString();
//                Toast.makeText(RegisterActivity.this, usernameString+passwordString+phoneString+addString+commentString, Toast.LENGTH_LONG).show();
//                System.out.println(usernameString+passwordString+phoneString+addString+commentString);

            //    http://172.24.10.175:8080/foodService/userRegister.do?username=lnn&userpass=11&mobilenum=13476543211&addr ess=大连&comment=老师
                String string=new String();
                string="http://172.24.10.175:8080/foodService/userRegister.do?username="+usernameString+"&userpass="+passwordString+"&mobilenum="+
                        phoneString+"&address="+addString+"&comment="+commentString;
                new AsyncTask<String, Void, Void>() {


                    @Override
                    protected void onPostExecute(Void aVoid) {
                        if (registBean.getSuccess().equals("0"))
                        {
                            Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                            System.out.println("注册失败");
                        }
                        else
                        {
                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                            System.out.println("注册成功");
                        }
                        super.onPostExecute(aVoid);
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
