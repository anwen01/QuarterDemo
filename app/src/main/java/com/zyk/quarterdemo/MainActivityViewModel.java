package com.zyk.quarterdemo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.zyk.quarterdemo.fragments.RecommendFragment;
import com.zyk.quarterdemo.fragments.TalkFragment;
import com.zyk.quarterdemo.fragments.VideoFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 时间： 2017/11/7 19:22
 * 功能：
 */

public class MainActivityViewModel {

    private RecommendFragment tuiJianFragment = new RecommendFragment();
    private TalkFragment duanZiFragment = new TalkFragment();
    private VideoFragment videoFragment = new VideoFragment();
    private List<Fragment> list = new ArrayList<>();
    private final FragmentManager fm;

    public MainActivityViewModel(AppCompatActivity activity) {
        list.add(tuiJianFragment);
        list.add(duanZiFragment);
        list.add(videoFragment);
        fm = activity.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragment,tuiJianFragment);
        ft.add(R.id.fragment,duanZiFragment);
        ft.add(R.id.fragment,videoFragment);
        show(0);
        ft.commit();
    }

    public void show(int id){
        FragmentTransaction ft = fm.beginTransaction();
        for (int i = 0; i < list.size(); i++) {
            if (i+1 == id){
                ft.show(list.get(i));
            }else {
                ft.hide(list.get(i));
            }
        }
        ft.commit();
    }

}
