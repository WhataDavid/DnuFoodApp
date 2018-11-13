package cn.dnui_dx602.dnuifood16110100602.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import cn.dnui_dx602.dnuifood16110100602.R;
import cn.dnui_dx602.dnuifood16110100602.bean.ShopBean;
import cn.dnui_dx602.dnuifood16110100602.controller.GetFoodByShopActivity;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {
    private List<ShopBean> mDataList;
    public ShopAdapter(Context mContext,List mDataList){
        this.mDataList=mDataList;
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

        String url = "http://172.24.10.175:8080/foodService/" + entity.getPic();
        Picasso.get().load(url).into(viewHolder.image);

        viewHolder.itemView
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //显示文章详情
                        Intent intent = new Intent(v.getContext(), GetFoodByShopActivity.class).putExtra("id",entity.getShop_id());
//                        Toast.makeText(v.getContext(), entity.getShop_id(), Toast.LENGTH_SHORT).show();
                        System.out.println("shopid="+entity.getShop_id());
                        v.getContext().startActivity(intent);

                    }
                });
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{

        RatingBar res_bar;
        TextView res_name,res_address;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            res_name= itemView.findViewById(R.id.res_name);
            res_bar= itemView.findViewById(R.id.ratingBar1);
            image=itemView.findViewById(R.id.res_image);
            res_address= itemView.findViewById(R.id.res_address);
        }
    }
}
