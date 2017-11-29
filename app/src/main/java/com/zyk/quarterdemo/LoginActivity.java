package com.zyk.quarterdemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.zyk.quarterdemo.beans.LoginBean;
import com.zyk.quarterdemo.presenter.LoginRegestPresenter;
import com.zyk.quarterdemo.view.ILoginRegesterView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：张玉轲
 * 时间：2017/11/28
 */

public class LoginActivity extends AppCompatActivity implements ILoginRegesterView {
    @BindView(R.id.log_loginback)
    Button logLoginback;
    @BindView(R.id.log_zhuce)
    TextView logZhuce;
    @BindView(R.id.log_ednum)
    EditText logEdnum;
    @BindView(R.id.log_psw)
    EditText logPsw;
    @BindView(R.id.log_login)
    Button logLogin;
    @BindView(R.id.log_wpass)
    TextView logWpass;
    @BindView(R.id.log_youlogin)
    TextView logYoulogin;
    private LoginRegestPresenter loginRegestPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_item);
        ButterKnife.bind(this);
        loginRegestPresenter = new LoginRegestPresenter(this);


    }

    @Override
    public void logReBackSuccess(LoginBean bean) {
        Toast.makeText(this, bean.getMsg(), Toast.LENGTH_SHORT).show();
        if (bean.getMsg().equals("登录成功")){
            SharedPreferences sharedPreferences=getSharedPreferences("config", Context.MODE_PRIVATE);
            sharedPreferences.edit().putString("token",bean.getData().getToken()).commit();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void logReBackFailure(String code) {
        Logger.i("", "logReBackFailure: "+code);
    }

    @OnClick({R.id.log_loginback, R.id.log_zhuce, R.id.log_login, R.id.log_wpass, R.id.log_youlogin})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.log_loginback:
                finish();
                break;
            case R.id.log_zhuce:
                Intent zintent = new Intent(LoginActivity.this, RegesterActivity.class);
                startActivity(zintent);
                break;
            case R.id.log_login:
                loginRegestPresenter.getData(logEdnum.getText().toString(),logPsw.getText().toString());
                break;
            case R.id.log_wpass:
                break;
            case R.id.log_youlogin:
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                break;
        }


    }
}
