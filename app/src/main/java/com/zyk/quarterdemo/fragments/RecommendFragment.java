package com.zyk.quarterdemo.fragments;

import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zyk.quarterdemo.R;
import com.zyk.quarterdemo.adpters.MyViewPageFragAdapter;
import com.zyk.quarterdemo.databinding.RecommendFragmentBinding;
import com.zyk.quarterdemo.utils.TabLengthUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：张玉轲
 * 时间：2017/11/7
 */

public class RecommendFragment extends Fragment {
    RecommendFragmentBinding mRecommendBinding;
    private String[] titlearr;
    private TabLayout recommentTab;
    private ViewPager recommentVp;
    private HotspotFragment hotspotFragment;
    private AtentionFragment atentionFragment;
    private List<Fragment> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRecommendBinding = DataBindingUtil.inflate(inflater, R.layout.recommend_fragment, container, false);
        return mRecommendBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        titlearr = new String[]{"热门", "关注"};
        recommentTab = mRecommendBinding.recommentTab;
        recommentVp = mRecommendBinding.recommentVp;
        hotspotFragment = new HotspotFragment();
        atentionFragment = new AtentionFragment();
        list = new ArrayList<>();
        list.add(hotspotFragment);
        list.add(atentionFragment);

        initVP();
        recommentTab.setupWithViewPager(recommentVp);
        //设置下划线的长短
        recommentTab.post(new Runnable() {
            @Override
            public void run() {
                TabLengthUtils.setIndicator(recommentTab, 60, 60);
            }
        });


    }


    private void initVP() {
        //适配器
        recommentVp.setAdapter(new MyViewPageFragAdapter(getFragmentManager(),titlearr,list));
    }



}
