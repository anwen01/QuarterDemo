package com.zyk.quarterdemo.fragments;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.zyk.quarterdemo.LoginRegestActivity;
import com.zyk.quarterdemo.R;
import com.zyk.quarterdemo.databinding.SideslipItemBinding;

/**
 * 作者：张玉轲
 * 时间：2017/11/8
 */

public class SideslipFragment extends Fragment {

    private ImageView sideHeader;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sideslip_item, null);
        sideHeader = view.findViewById(R.id.side_header);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sideHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginRegestActivity.class);
                startActivity(intent);
            }
        });
    }
}
