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
import com.zyk.quarterdemo.databinding.RecommendFragmentBinding;

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
                setIndicator(recommentTab, 60, 60);
            }
        });


    }


    private void initVP() {
        //适配器
        recommentVp.setAdapter(new MyViewPageFragAdapter(getFragmentManager()));
    }

    class MyViewPageFragAdapter extends FragmentPagerAdapter {

        public MyViewPageFragAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titlearr[position];
        }
    }

    //TabLayout下划线长短的方法
    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }


    }
}
