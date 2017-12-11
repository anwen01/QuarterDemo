package com.zyk.quarterdemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.zyk.quarterdemo.base.BaseActivity;
import com.zyk.quarterdemo.beans.RegesterBean;
import com.zyk.quarterdemo.presenter.ChangerNamePresenter;
import com.zyk.quarterdemo.view.ChangerNameView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：张玉轲
 * 时间：2017/11/30
 */

public class ChangerNameActivity extends BaseActivity<ChangerNameView, ChangerNamePresenter> implements ChangerNameView {
    @BindView(R.id.change_tv_back)
    TextView changeTvBack;
    @BindView(R.id.change_tv_change)
    TextView changeTvChange;
    @BindView(R.id.change_ed_changename)
    EditText changeEdChangename;
    private ChangerNamePresenter changerNamePresenter;

    //成功
    @Override
    public void changerNameBackSuccess(RegesterBean bean) {
        Toast.makeText(this, bean.getMsg(), Toast.LENGTH_SHORT).show();
        if (bean.getMsg().equals("昵称修改成功")){
            SharedPreferences sharedPreferences=getSharedPreferences("config", Context.MODE_PRIVATE);
            sharedPreferences.edit().putString("nicheng",changeEdChangename.getText().toString()).commit();
            finish();
        }

    }

    //失败
    @Override
    public void changerNameBackFailure(String code) {
        Logger.i("===",code);
    }

    @Override
    public int setView() {
        return R.layout.changename_item;
    }

    @Override
    public ChangerNamePresenter createPresenter() {
        changerNamePresenter = new ChangerNamePresenter(this);
        return changerNamePresenter;
    }


    @OnClick({R.id.change_tv_back, R.id.change_tv_change})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.change_tv_back:
                finish();
                break;
            case R.id.change_tv_change:
                SharedPreferences sharedPreferences=getSharedPreferences("config", Context.MODE_PRIVATE);
                String uid = sharedPreferences.getString("uid", "");
                changerNamePresenter.getData(uid,changeEdChangename.getText().toString().trim());
                break;
        }
    }
}
