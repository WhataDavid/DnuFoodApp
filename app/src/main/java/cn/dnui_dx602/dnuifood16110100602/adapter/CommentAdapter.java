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
import cn.dnui_dx602.dnuifood16110100602.bean.CommentBean;
import cn.dnui_dx602.dnuifood16110100602.bean.ShopBean;
import cn.dnui_dx602.dnuifood16110100602.controller.GetFoodByShopActivity;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private List<CommentBean> mDataList;

    public CommentAdapter(List mDataList){
        this.mDataList=mDataList;
    }
    @NonNull
    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(View.inflate(viewGroup.getContext(),R.layout.comment_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ViewHolder  holder, int i) {
        final CommentBean entity=(CommentBean) mDataList.get(i);
        if(null==entity)
            return;
        ViewHolder viewHolder=(ViewHolder) holder;
        System.out.println(entity.getComment_time());
        System.out.println(entity.getUsername());
        System.out.println(entity.getUser_id());
        viewHolder.comment_foodname.setText(entity.getFoodname());
        viewHolder.comment_username.setText("用户:"+Integer.toString(entity.getUser_id()));
        viewHolder.comment_time.setText(entity.getComment_time());
        viewHolder.comment_intro.setText(entity.getContent());

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{

        TextView comment_username,comment_foodname,comment_time,comment_intro;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            comment_foodname= itemView.findViewById(R.id.comment_foodname);
            comment_username= itemView.findViewById(R.id.comment_username);
            comment_time=itemView.findViewById(R.id.comment_time);
            comment_intro= itemView.findViewById(R.id.commment_intro);
        }
    }
}
