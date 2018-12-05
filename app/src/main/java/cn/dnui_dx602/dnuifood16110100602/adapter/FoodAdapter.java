package cn.dnui_dx602.dnuifood16110100602.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import cn.dnui_dx602.dnuifood16110100602.R;
import cn.dnui_dx602.dnuifood16110100602.bean.FoodBean;
import cn.dnui_dx602.dnuifood16110100602.bean.ShopBean;
import cn.dnui_dx602.dnuifood16110100602.controller.FoodDetailActivity;
import cn.dnui_dx602.dnuifood16110100602.controller.GetFoodByShopActivity;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {
    private List<FoodBean> mDataList;
    public static int Foodid;

    public FoodAdapter(){
//        this.mDataList=mDataList;
    }
    public void setList(List<FoodBean> list){
        mDataList = list;
    }
    @NonNull
    @Override
    public FoodAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(View.inflate(viewGroup.getContext(),R.layout.food_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.ViewHolder  holder, int i) {
        final FoodBean entity=(FoodBean)mDataList.get(i);
        if(null==entity)
            return;
        ViewHolder viewHolder=(ViewHolder) holder;
        viewHolder.foodname.setText(entity.getFoodname());
        System.out.println("-------------------------Food------------------------"+"\n"+"FoodName====="+entity.getFoodname());
        System.out.println("Intro====="+entity.getIntro());
        System.out.println("Price====="+entity.getPrice());
        viewHolder.intro.setText(entity.getIntro());
        viewHolder.price.setText(String.valueOf(entity.getPrice()));

        String url = "http://172.24.10.175:8080/foodService/" + entity.getPic();
        Picasso.get().load(url).into(viewHolder.image);

        viewHolder.itemView
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //显示菜谱详情
                        Foodid=Integer.parseInt(entity.getFood_id());
                        Intent intent = new Intent(v.getContext(), FoodDetailActivity.class);
                        SharedPreferences sharedPreferences;
                        sharedPreferences=v.getContext().getSharedPreferences("food", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString("foodname",entity.getFoodname());
                        editor.putString("intro",entity.getIntro());
                        editor.putString("pic",entity.getPic());
                        editor.putString("price",entity.getPrice());
                        editor.putString("level",entity.getRecommand());
                        editor.putString("foodid",entity.getFood_id());
                        editor.putString("shopid",entity.getShop_id());

                        editor.commit();

                        v.getContext().startActivity(intent);

                    }
                });
    }

    @Override
    public int getItemCount() {
        if (mDataList==null)
            return 0;
        else
        return mDataList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{

        TextView foodname,intro,price;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodname= itemView.findViewById(R.id.foodname);
            intro= itemView.findViewById(R.id.intro);
            image=itemView.findViewById(R.id.res_image);
            price= itemView.findViewById(R.id.price);
        }
    }
}
