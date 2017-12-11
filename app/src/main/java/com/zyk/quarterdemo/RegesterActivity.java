package com.zyk.quarterdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zyk.quarterdemo.base.BaseActivity;
import com.zyk.quarterdemo.beans.RegesterBean;
import com.zyk.quarterdemo.presenter.RegestPresenter;
import com.zyk.quarterdemo.view.IRegesterView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：张玉轲
 * 时间：2017/11/28
 */

public class RegesterActivity extends BaseActivity<IRegesterView,RegestPresenter> implements IRegesterView{
    @BindView(R.id.regest_back)
    ImageView regestBack;
    @BindView(R.id.regest_log)
    TextView regestLog;
    @BindView(R.id.regest_phone)
    EditText regestPhone;
    @BindView(R.id.regest_pwd)
    EditText regestPwd;
    @BindView(R.id.regest_regest)
    Button regestRegest;
    @BindView(R.id.regest_yklog)
    TextView regestYklog;
    private RegestPresenter regestPresenter;

    @Override
    public int setView() {
        return R.layout.regest;
    }

    @Override
    public RegestPresenter createPresenter() {
        regestPresenter = new RegestPresenter(this);
        return regestPresenter;
    }

    @OnClick({R.id.regest_back, R.id.regest_log, R.id.regest_regest, R.id.regest_yklog})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.regest_back:
                finish();
                break;
            case R.id.regest_log:
                finish();
                break;
            case R.id.regest_regest:
                regestPresenter.getData("0",regestPhone.getText().toString(),regestPwd.getText().toString());

                break;
            case R.id.regest_yklog:
                startActivity(new Intent(RegesterActivity.this,MainActivity.class));
                break;
        }
    }

    @Override
    public void regesterBackSuccess(RegesterBean bean) {
        Toast.makeText(this, bean.getMsg(), Toast.LENGTH_SHORT).show();
        if (bean.getMsg().equals("注册成功")){
            startActivity(new Intent(RegesterActivity.this,LoginActivity.class));
        }
    }

    @Override
    public void regesterBackFailure(String code) {
        Toast.makeText(this, code, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (regestPresenter.isAttachView()){
            regestPresenter.detach();
        }
    }
}
