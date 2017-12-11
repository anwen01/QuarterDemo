package com.zyk.quarterdemo.adpters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 作者：张玉轲
 * 时间：2017/12/5
 */

public class MyViewPageFragAdapter extends FragmentPagerAdapter {
    private String[] titlearr;
    private List<Fragment> list;

    public MyViewPageFragAdapter(FragmentManager fm, String[] titlearr, List<Fragment> list) {
        super(fm);
        this.titlearr = titlearr;
        this.list = list;
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
