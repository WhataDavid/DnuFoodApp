package cn.dnui_dx602.dnuifood16110100602.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import cn.dnui_dx602.dnuifood16110100602.R;

public class MyPagerAdapter extends PagerAdapter {
    private ArrayList<View> viewLists;

    public MyPagerAdapter(ArrayList<View> viewLists) {
        super();
        this.viewLists = viewLists;
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
        if (position==0)
        {
            SharedPreferences sharedPreferences=container.getContext().getSharedPreferences("food",Context.MODE_PRIVATE);

            Init_View_One(container,sharedPreferences);
        }
        if (position==1)
        {

        }

        return viewLists.get(position);
    }

    private void Init_View_One(ViewGroup container,SharedPreferences sharedPreferences) {

//        shopnameString = sharedPreferences.getString("shopname","");
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

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewLists.get(position));
    }
}
