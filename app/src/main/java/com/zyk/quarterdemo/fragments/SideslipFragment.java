package com.zyk.quarterdemo.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zyk.quarterdemo.ChangerNameActivity;
import com.zyk.quarterdemo.LoginRegestActivity;
import com.zyk.quarterdemo.R;
import com.zyk.quarterdemo.UserInfoActivity;
import com.zyk.quarterdemo.adpters.TakeAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 作者：张玉轲
 * 时间：2017/11/8
 */

public class SideslipFragment extends Fragment {


    @BindView(R.id.side_header)
    SimpleDraweeView sideHeader;
    @BindView(R.id.tv_left_nicheng)
    TextView tvLeftNicheng;
    @BindView(R.id.linearLayout)
    RelativeLayout linearLayout;
    @BindView(R.id.myfollow)
    RelativeLayout myfollow;
    @BindView(R.id.myhide)
    RelativeLayout myhide;
    @BindView(R.id.searchfriend)
    RelativeLayout searchfriend;
    @BindView(R.id.mynews)
    RelativeLayout mynews;
    @BindView(R.id.linearLayout2)
    LinearLayout linearLayout2;
    @BindView(R.id.iv_day_night)
    ImageView ivDayNight;
    @BindView(R.id.tv_day_night)
    TextView tvDayNight;
    @BindView(R.id.iv_left_select)
    ImageView ivLeftSelect;
    @BindView(R.id.iv_left_zuopin)
    ImageView ivLeftZuopin;
    @BindView(R.id.iv_left_shezhi)
    ImageView ivLeftShezhi;
    @BindView(R.id.mywork)
    TextView mywork;
    @BindView(R.id.tv_setting)
    TextView tvSetting;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sideslip_item, null);
        unbinder = ButterKnife.bind(this, view);
        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
        tvLeftNicheng.setText(sharedPreferences.getString("nicheng","昵称"));
        //头像设置
        String icon = sharedPreferences.getString("icon","");
        if (icon != null) {
            sideHeader.setImageURI(Uri.parse(icon));
        } else {
            sideHeader.setImageResource(R.mipmap.raw_header);
        }
        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.side_header, R.id.tv_left_nicheng})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.side_header:
                Intent intent = new Intent(getActivity(), LoginRegestActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_left_nicheng:
                Intent intent1 = new Intent(getActivity(), UserInfoActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
