package com.zyk.quarterdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：张玉轲
 * 时间：2017/11/27
 */

public class LoginRegestActivity extends AppCompatActivity {
    @BindView(R.id.log_loginback)
    Button logLoginback;
    @BindView(R.id.log_weixin)
    ImageView logWeixin;
    @BindView(R.id.log_qq)
    ImageView logQq;
    @BindView(R.id.log_qt)
    TextView logQt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginregest);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.log_loginback, R.id.log_weixin, R.id.log_qq, R.id.log_qt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.log_loginback:
                finish();
                //返回操作
                break;
            case R.id.log_weixin:
                //微信登录
                break;
            case R.id.log_qq:
                //qq登录
                break;
            case R.id.log_qt:
                //其他登录方式
                Intent intent = new Intent(LoginRegestActivity.this,LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}
