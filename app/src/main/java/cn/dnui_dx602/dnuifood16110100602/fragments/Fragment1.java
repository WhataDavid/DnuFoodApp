package cn.dnui_dx602.dnuifood16110100602.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.dnui_dx602.dnuifood16110100602.R;


public class Fragment1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment1, container, false);
    }

    @Override
    public void onStart() {
        TextView textView = getView().findViewById(R.id.textOne);
        textView.setText("我是第一个视图哦");
        super.onStart();
    }
}
