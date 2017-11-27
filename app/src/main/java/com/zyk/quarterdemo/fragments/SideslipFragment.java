package com.zyk.quarterdemo.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zyk.quarterdemo.R;
import com.zyk.quarterdemo.databinding.SideslipItemBinding;

/**
 * 作者：张玉轲
 * 时间：2017/11/8
 */

public class SideslipFragment extends Fragment {
    SideslipItemBinding mSideslipItemBinding;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mSideslipItemBinding= DataBindingUtil.inflate(inflater, R.layout.sideslip_item,container,false);
        return mSideslipItemBinding.getRoot();
    }
}
