package cn.dnui_dx602.dnuifood16110100602.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import cn.dnui_dx602.dnuifood16110100602.R;
import cn.dnui_dx602.dnuifood16110100602.adapter.MyPagerAdapter;
import cn.dnui_dx602.dnuifood16110100602.bean.CollectBean;
import cn.dnui_dx602.dnuifood16110100602.bean.Success;

public class FoodDetailActivity extends BaseActivity {
EditText dialog_commemt;
Switch aSwitch;
Button dialog_button;
SharedPreferences sharedPreferences;
    private ViewPager viewpager_one;
    private ArrayList<View> aList;
    private MyPagerAdapter mAdapter;
    String foodidString;
    String urlString;
    Gson gson;
    CollectBean collectBean;
    Success success ;
    String Like=new String();
    Menu menu;
    MenuItem menuItem;
    LayoutInflater li;
    @Override
    void initViews() {
        setContentView(R.layout.activity_food_detail);
        viewpager_one = (ViewPager) findViewById(R.id.viewpager);
        aSwitch=findViewById(R.id.view_one_switch);
        aList = new ArrayList<View>();
        li = getLayoutInflater();
        aList.add(li.inflate(R.layout.view_one,null,false));
        aList.add(li.inflate(R.layout.view_two,null,false));
        mAdapter = new MyPagerAdapter(aList);
        viewpager_one.setAdapter(mAdapter);
    }

    @Override
    void initEvents() {
            aSwitch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    new AsyncTask<String, Void, Void>() {
                        @Override
                        protected void onPostExecute(Void aVoid) {
                            super.onPostExecute(aVoid);
                            if (aSwitch.isChecked()&&success.getSuccess().equals("1"))
                            {
                                aSwitch.setChecked(true);
                                aSwitch.setText("已收藏");
                            }
                            else if (!aSwitch.isChecked()&&success.getSuccess().equals("1")){
                                aSwitch.setText("收藏");
                                aSwitch.setChecked(false);
                            }


                        }
                        String line=new String();

                        @Override
                        protected Void doInBackground(String... strings) {
                            try {
                                URL url = new URL(strings[0]);
                                URLConnection connection = url.openConnection();
                                InputStream inputStream = connection.getInputStream();
                                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
                                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                                while ((line = bufferedReader.readLine()) != null) {
                                    System.out.println("收藏返回信息"+line );
                                    gson = new Gson();
                                    success = gson.fromJson(line, Success.class);
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
                    }.execute("http://172.24.10.175:8080/foodService/userCollectFood.do?user_id="+LoginActivity.user_id+"&food_id="+sharedPreferences.getString("foodid",""));

                }
            });
    }

    @Override
    void initDatas() {
        initLike();
    }

    private void initLike() {

        new AsyncTask<String, Void, Void>() {
            @Override
            protected void onPostExecute(Void aVoid) {

                super.onPostExecute(aVoid);
                System.out.println("啧啧啧啧啧啧做"+Like);

                System.out.println("COLLECTED  BEAN  ::222:::"+Like);
                if(Like.equals("1"))
                {
                    aSwitch.setChecked(true);
                    aSwitch.setText("已收藏");
                }
                else
                {
                    aSwitch.setChecked(false);

                    aSwitch.setText("收藏");
                }
                initEvents();
            }
            String line=new String();

            @Override
            protected Void doInBackground(String... strings) {
                try {
                    URL url = new URL(strings[0]);
                    URLConnection connection = url.openConnection();
                    InputStream inputStream = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    while ((line = bufferedReader.readLine()) != null) {
                        System.out.println("---------------------Like-------------------"+sharedPreferences.getString("foodid",""));
                        System.out.println(sharedPreferences.getString("shopid",""));
                        System.out.println("收藏状态返回信息"+line );
                        gson = new Gson();
                        collectBean = gson.fromJson(line, CollectBean.class);
                        System.out.println("COLLECTED  BEAN  :::::"+collectBean.getCollected());
                        Like=collectBean.getCollected();
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
        }.execute(urlString);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences=getSharedPreferences("food", Context.MODE_PRIVATE);
        foodidString=sharedPreferences.getString("foodid","");
        urlString="http://172.24.10.175:8080/foodService/isCollected.do?user_id="+LoginActivity.user_id+"&shop_food_id="+foodidString+"&flag=1";
        initViews();
        initDatas();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        this.menu = menu;
        menuItem = menu.findItem(R.id.menu_collect);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(FoodDetailActivity.this);
        final AlertDialog dialog = builder.create();
        View dialogView = View.inflate(FoodDetailActivity.this, R.layout.dialog_addcomment, null);
        //设置对话框布局
        dialog.setView(dialogView);
        dialog_commemt = dialogView.findViewById(R.id.dialog_commentEditText);
        dialog_button = dialogView.findViewById(R.id.dialog_confirm);
        dialog.show();
        dialog_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AsyncTask<String, Void, Void>() {
                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                if (success.getSuccess().equals("1"))
                {
                    Toast.makeText(FoodDetailActivity.this, "成功", Toast.LENGTH_SHORT).show();
                    viewpager_one.setAdapter(mAdapter);
                    viewpager_one.setCurrentItem(1,true);

                }
                else
                    Toast.makeText(FoodDetailActivity.this, "失败", Toast.LENGTH_SHORT).show();
                    }
                    String line = new String();
                    @Override
                    protected Void doInBackground(String... strings) {
                        try {
                            URL url = new URL(strings[0]);
                            URLConnection connection = url.openConnection();
                            InputStream inputStream = connection.getInputStream();
                            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
                            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                            while ((line = bufferedReader.readLine()) != null) {
                                System.out.println("添加评论返回信息" + line);
                                gson = new Gson();
                                success = gson.fromJson(line, Success.class);
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
                }.execute("http://172.24.10.175:8080/foodService/insertComment.do?item_id="+sharedPreferences.getString("itemid","")+"&content="+dialog_commemt.getText());
            }
        });



        return super.onOptionsItemSelected(item);
    }
}
