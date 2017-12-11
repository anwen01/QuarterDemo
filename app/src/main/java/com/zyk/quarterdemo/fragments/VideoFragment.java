package com.zyk.quarterdemo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zyk.quarterdemo.R;
import com.zyk.quarterdemo.adpters.MyViewPageFragAdapter;
import com.zyk.quarterdemo.utils.TabLengthUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：张玉轲
 * 时间：2017/11/7
 */

public class VideoFragment extends Fragment {
    @BindView(R.id.video_tab)
    TabLayout videoTab;
    @BindView(R.id.video_vp)
    ViewPager videoVp;
    Unbinder unbinder;
    private String[] titlearr;
    private List<Fragment> list;
    private VideoHotspotFragment videoHotspotFragment;
    private VideoNearFragment videoNearFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.video_fragment, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        titlearr = new String[]{"热门", "附近"};
        videoHotspotFragment = new VideoHotspotFragment();
        videoNearFragment = new VideoNearFragment();
        list = new ArrayList<>();
        list.add(videoHotspotFragment);
        list.add(videoNearFragment);
        initVP();
        videoTab.setupWithViewPager(videoVp);
        //设置下划线的长短
        videoTab.post(new Runnable() {
            @Override
            public void run() {
                TabLengthUtils.setIndicator(videoTab, 60, 60);
            }
        });

    }
    private void initVP() {
        //适配器
        videoVp.setAdapter(new MyViewPageFragAdapter(getFragmentManager(),titlearr,list));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
