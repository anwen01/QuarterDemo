package com.zyk.quarterdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：张玉轲
 * 时间：2017/11/29
 */

public abstract class BaseFragment<V,P extends BasePresenter<V>> extends Fragment {
    private View view;
    protected P mPresenter;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //判断布局是否为空
        if (view == null) {
            view = View.inflate(getActivity(), setLayout(), null);
        }
        ViewGroup vg = (ViewGroup) view.getParent();
        if (vg != null) {
            vg.removeView(view);
        }
        createpresenter();
        if (mPresenter != null) {
            mPresenter.attachView((V) this);
        }
        //    initView(view);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        processLogic();
    }
    //得到布局
    protected abstract int setLayout();

    //获取控件
    //  protected abstract void initView(View view);

    //创建presenter
    protected abstract void createpresenter();

    //处理逻辑
    protected abstract void processLogic();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detach();
        }
        unbinder.unbind();
    }

}
