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
import cn.dnui_dx602.dnuifood16110100602.bean.CollectBean;
import cn.dnui_dx602.dnuifood16110100602.bean.CollectionsBean;
import cn.dnui_dx602.dnuifood16110100602.bean.ShopBean;
import cn.dnui_dx602.dnuifood16110100602.controller.GetFoodByShopActivity;

public class CollectShopAdapter extends RecyclerView.Adapter<CollectShopAdapter.ViewHolder> {
    private List<CollectionsBean> mDataList;

    public CollectShopAdapter(Context mContext){

//        this.mDataList=mDataList;
    }
    public void setList(List<CollectionsBean> list){
        mDataList = list;
    }
    @NonNull
    @Override
    public CollectShopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(View.inflate(viewGroup.getContext(),R.layout.collect_shop_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull CollectShopAdapter.ViewHolder  holder, int i) {
        final CollectionsBean entity=(CollectionsBean) mDataList.get(i);
        if(null==entity)
            return;
        ViewHolder viewHolder=(ViewHolder) holder;
        viewHolder.res_name.setText(entity.getShopname());
        viewHolder.res_address.setText(entity.getAddress());
        String url = "http://172.24.10.175:8080/foodService/" + entity.getPic();
        Picasso.get().load(url).into(viewHolder.image);
    }

    @Override
    public int getItemCount() {
//        return mDataList.size();
        if(mDataList==null) return 0;
        else
            return mDataList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{

        TextView res_name,res_address;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            res_name= itemView.findViewById(R.id.comment_foodname);
            image=itemView.findViewById(R.id.res_image);
            res_address= itemView.findViewById(R.id.res_address);
        }
    }
}
