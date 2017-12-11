package com.zyk.quarterdemo;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;

import butterknife.ButterKnife;

/**
 * 作者：张玉轲
 * 时间：2017/12/6
 */

public class VideoHotsDetailsActivity extends AppCompatActivity {
    PlayerView play;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.video_hot_details);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String path = intent.getStringExtra("path");
        System.out.println("======path:"+path);
        play = new PlayerView(this)
                .setTitle("视频")
                .setScaleType(PlayStateParams.fitparent)
                .hideMenu(true)
                .forbidTouch(false)
                .setPlaySource(path);
        play.startPlay();

    }
    //当你离开播放界面的时候视频停止播放
    @Override
    protected void onStop() {
        super.onStop();
        play.stopPlay();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }else{
                finish();
            }
        }
        return false;
    }


}

