package com.zyk.quarterdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dou361.ijkplayer.widget.PlayerView;
import com.zyk.quarterdemo.model.FbTalkModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：张玉轲
 * 时间：2017/11/29
 */

public class WriteActivity extends AppCompatActivity {
    @BindView(R.id.back)
    TextView back;
    @BindView(R.id.iv_shipin)
    ImageView ivShipin;
    @BindView(R.id.iv_duanzi)
    ImageView ivDuanzi;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writeitem);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.back, R.id.iv_shipin, R.id.iv_duanzi})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.iv_shipin:
                startActivity(new Intent(WriteActivity.this,FbVideoActivity.class));
                break;
            case R.id.iv_duanzi:
                startActivity(new Intent(WriteActivity.this,FbTalkActivity.class));
                break;
        }
    }
}
