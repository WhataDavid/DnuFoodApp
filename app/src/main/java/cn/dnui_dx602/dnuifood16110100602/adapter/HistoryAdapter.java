package cn.dnui_dx602.dnuifood16110100602.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.LinkedList;

import cn.dnui_dx602.dnuifood16110100602.R;
import cn.dnui_dx602.dnuifood16110100602.bean.HistoryBean;

public class HistoryAdapter extends BaseAdapter {

    private LinkedList<HistoryBean> mData;
    private Context mContext;

    public HistoryAdapter(LinkedList<HistoryBean> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }
    public void setList(LinkedList<HistoryBean> mData){
        this.mData = mData;
    }
    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.history_item,parent,false);
        TextView txt_aName = (TextView) convertView.findViewById(R.id.historyname);
        txt_aName.setText(mData.get(position).getName());
        return convertView;
    }
}