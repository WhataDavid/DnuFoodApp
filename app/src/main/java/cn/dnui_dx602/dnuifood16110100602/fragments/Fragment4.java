package cn.dnui_dx602.dnuifood16110100602.fragments;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import cn.dnui_dx602.dnuifood16110100602.R;
import cn.dnui_dx602.dnuifood16110100602.bean.GetUserBean;
import cn.dnui_dx602.dnuifood16110100602.controller.LoginActivity;
import cn.dnui_dx602.dnuifood16110100602.controller.UpdateUserById;

public class Fragment4 extends Fragment {
    TextView userName,userid,mobileNum,address;
    Gson gson;
    GetUserBean getUserBean;
    Boolean success=false;
    Button update;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment4, container, false);

    }
    @Override
    public void onStart() {
        initViews();
//        while (true)
//        {
//            if (success==true)
//            {
//                initDatas();
//                break;
//            }
//        }
        initEvents();

        super.onStart();
    }

    private void initViews() {
        String string="http://172.24.10.175:8080/foodService/getUserById.do?user_id="+ LoginActivity.user_id;
        AsyncTask<String, Void, Void> execute = new AsyncTask<String, Void, Void>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                update = getActivity().findViewById(R.id.update);
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                initDatas();
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
                        getUserBean = gson.fromJson(line, GetUserBean.class);
                        System.out.println(getUserBean.getUsername() + "\n"+getUserBean.getUser_id()+"\n"+getUserBean.getMobilenum()+"\n"+getUserBean.getAddress());
                        success=true;
//                        initDatas();
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

    private void initEvents() {
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),UpdateUserById.class);
                intent.putExtra("username",getUserBean.getUsername());
                intent.putExtra("userid",getUserBean.getUser_id());
                intent.putExtra("mobile",getUserBean.getMobilenum());
                intent.putExtra("address",getUserBean.getAddress());
                intent.putExtra("password",LoginActivity.user_pass);

                startActivity(intent);
            }
        });
    }

    private void initDatas() {
        userName = getView().findViewById(R.id.username);
        mobileNum = getActivity().findViewById(R.id.mobilenum);
        address = getActivity().findViewById(R.id.dialog_address);
        userid = getActivity().findViewById(R.id.userid);
//        update = getActivity().findViewById(R.id.update);

        System.out.println(getUserBean.getUser_id()+"8888");
        userid.setText("id:   "+getUserBean.getUser_id()+" ");
        userName.setText("用户名:   "+getUserBean.getUsername());
        mobileNum.setText(getUserBean.getMobilenum());
        address.setText(getUserBean.getAddress());



    }




}
