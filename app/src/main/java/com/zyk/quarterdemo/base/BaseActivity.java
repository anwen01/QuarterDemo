package com.zyk.quarterdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * 作者：张玉轲
 * 时间：2017/11/29
 */

public abstract class BaseActivity<V,P extends BasePresenter<V>> extends AppCompatActivity {
    private P mPresenter;
    public abstract int setView();
    public abstract P createPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setView());
        ButterKnife.bind(this);
        mPresenter=createPresenter();
        mPresenter.attachView((V) this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.detach();
        }
    }
}
