package cn.dnui_dx602.dnuifood16110100602.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import cn.dnui_dx602.dnuifood16110100602.R;
import cn.dnui_dx602.dnuifood16110100602.bean.CommentBean;

public class MyPagerAdapter extends PagerAdapter {
    private ArrayList<View> viewLists;
    RecyclerView recyclerView;
    CommentAdapter commentAdapter;
    public MyPagerAdapter(ArrayList<View> viewLists) {
        super();
        this.viewLists = viewLists;
        commentAdapter=new CommentAdapter();

    }

    @Override
    public int getCount() {
        return viewLists.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewLists.get(position));
        SharedPreferences sharedPreferences=container.getContext().getSharedPreferences("food",Context.MODE_PRIVATE);
        if (position==0)
        {
            Init_View_One(container,sharedPreferences);
        }
        if (position==1)
        {
            Init_View_Two(container,sharedPreferences);
        }

        return viewLists.get(position);
    }


    private void Init_View_One(ViewGroup container,SharedPreferences sharedPreferences) {

        TextView foodname=container.findViewById(R.id.view_one_foodname);
        TextView intro=container.findViewById(R.id.view_one_intro);
        TextView price=container.getRootView().findViewById(R.id.view_one_price);
        RatingBar ratingBar=container.findViewById(R.id.view_one_ratingBar);
        TextView title=container.findViewById(R.id.duxu);
        ImageView imageView=container.findViewById(R.id.view_one_pic);

        title.setText("菜品详情");
        foodname.setText(sharedPreferences.getString("foodname",""));
        intro.setText(sharedPreferences.getString("intro",""));
        price.setText(sharedPreferences.getString("price","")+"￥");
        ratingBar.setNumStars(Integer.parseInt(sharedPreferences.getString("level","")));
        Picasso.get().load("http://172.24.10.175:8080/foodService/"+sharedPreferences.getString("pic","")).into(imageView);
    }

    private void Init_View_Two(ViewGroup container,SharedPreferences sharedPreferences) {
        recyclerView=container.findViewById(R.id.view_two_recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(container.getContext(),2));
        recyclerView.setAdapter(commentAdapter);
        getData(container,sharedPreferences);
    }

    private void getData(ViewGroup container, final SharedPreferences sharedPreferences) {
        new AsyncTask<String, Void, Void>() {
            List<CommentBean> commentsList;
            @Override
            protected void onPostExecute(Void aVoid) {
//                recyclerView.setAdapter(new CommentAdapter());
                commentAdapter.setList(commentsList);
                commentAdapter.notifyDataSetChanged();
                super.onPostExecute(aVoid);

                if (!commentsList.isEmpty())
                {
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("itemid",Integer.toString(commentsList.get(0).getItem_id()));
                    editor.commit();
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
                        System.out.println("评论返回信息"+line );
                        commentsList = new Gson().fromJson(line, new TypeToken<List<CommentBean>>(){}.getType());
//                        System.out.println();
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
        }.execute("http://172.24.10.175:8080/foodService/getAllCommentsByFood.do?food_id="+sharedPreferences.getString("foodid",""));
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewLists.get(position));
    }
}
