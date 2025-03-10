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
import cn.dnui_dx602.dnuifood16110100602.bean.ShopBean;
import cn.dnui_dx602.dnuifood16110100602.controller.GetFoodByShopActivity;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {
    private List<ShopBean> mDataList;
    public static int Shopid;

    public ShopAdapter(Context mContext){
        //this.mDataList=mDataList;
    }

    public void setList(List<ShopBean> list){
        mDataList = list;
    }
    @NonNull
    @Override
    public ShopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(View.inflate(viewGroup.getContext(),R.layout.shop_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull ShopAdapter.ViewHolder  holder, int i) {
        final ShopBean entity=(ShopBean)mDataList.get(i);
        if(null==entity)
            return;
        ViewHolder viewHolder=(ViewHolder) holder;
        viewHolder.res_name.setText(entity.getShopname());
        viewHolder.res_address.setText(entity.getAddress());
        viewHolder.res_bar.setRating(entity.getLevel());
        viewHolder.res_intro.setText(entity.getIntro());
        String url = "http://172.24.10.175:8080/foodService/" + entity.getPic();
        Picasso.get().load(url).into(viewHolder.image);
//        http://172.24.10.175:8080/foodService/images/shop/chuaicai.jpg
        viewHolder.itemView
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences sharedPreferences;
                        sharedPreferences=v.getContext().getSharedPreferences("INFOR", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString("shopname",entity.getShopname());
                        editor.putString("phonenum",entity.getPhonenum());
                        editor.putString("intro",entity.getIntro());
                        editor.putString("pic",entity.getPic());
                        editor.putString("address",entity.getAddress());
                        editor.putString("level", String.valueOf(entity.getLevel()));
                        editor.putString("shopid", String.valueOf(entity.getShop_id()));

                        editor.commit();
                        //显示店铺详情
                        Shopid=entity.getShop_id();
                        Intent intent = new Intent(v.getContext(), GetFoodByShopActivity.class);
                        System.out.println("shopid="+entity.getShopname());
                        v.getContext().startActivity(intent);

                    }
                });
    }

    @Override
    public int getItemCount() {
        if(mDataList==null) return 0;
        else
        return mDataList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{

        RatingBar res_bar;
        TextView res_name,res_address,res_intro;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            res_name= itemView.findViewById(R.id.historyname);
            res_bar= itemView.findViewById(R.id.ratingBar1);
            image=itemView.findViewById(R.id.res_image);
            res_address= itemView.findViewById(R.id.res_address);
            res_intro=itemView.findViewById(R.id.intro);
        }
    }
    //  删除数据
    public void removeData(int position) {
        mDataList.remove(position);
        //删除动画
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

}
