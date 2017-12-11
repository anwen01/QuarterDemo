package com.zyk.quarterdemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.zyk.quarterdemo.databinding.ActivityMainBinding;
import com.zyk.quarterdemo.fragments.RecommendFragment;
import com.zyk.quarterdemo.fragments.TalkFragment;
import com.zyk.quarterdemo.fragments.VideoFragment;
import com.zyk.quarterdemo.view.MyHeaderView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.myview)
    MyHeaderView myview;
    @BindView(R.id.fragment)
    FrameLayout fragment;
    @BindView(R.id.main_rb_recommend)
    RadioButton mainRbRecommend;
    @BindView(R.id.main_rb_talk)
    RadioButton mainRbTalk;
    @BindView(R.id.main_rb_video)
    RadioButton mainRbVideo;
    @BindView(R.id.mainrg)
    RadioGroup mainrg;
    private SlidingMenu slidingMenu;

    private FragmentManager fm;
    private List<Fragment> list;
    private List<RadioButton> radioButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            //透明状态栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
        inits();
        mainRbRecommend.setOnClickListener(this);
        mainRbTalk.setOnClickListener(this);
        mainRbVideo.setOnClickListener(this);
        //侧滑
        slidingMenu = new SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.LEFT);
        slidingMenu.setBehindOffset(100);
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
        slidingMenu.setMenu(R.layout.sidefragment);

        SharedPreferences sharedPreferences=getSharedPreferences("config", Context.MODE_PRIVATE);
        String icon = sharedPreferences.getString("icon","");
        if (icon != null) {
            myview.getIvHeader().setImageURI(Uri.parse(icon));
        } else {
            myview.getIvHeader().setImageResource(R.mipmap.raw_header);
        }
        //头像
        myview.getIvHeader(new MyHeaderView.OnHeaderClickListener() {
            @Override
            public void setOnHeaderClickListener() {

                slidingMenu.toggle();
            }
        });
        //编写
        myview.getIvWrite(new MyHeaderView.OnWriteClickListener() {
            @Override
            public void setOnHeaderClickListener() {
                Intent intent = new Intent(MainActivity.this, WriteActivity.class);
                startActivity(intent);
            }
        });
    }

    public void inits(){
        RecommendFragment tuiJianFragment = new RecommendFragment();
        TalkFragment duanZiFragment = new TalkFragment();
        VideoFragment videoFragment = new VideoFragment();
        list = new ArrayList<>();
        list.add(tuiJianFragment);
        list.add(duanZiFragment);
        list.add(videoFragment);
        fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragment, tuiJianFragment);
        ft.add(R.id.fragment, duanZiFragment);
        ft.add(R.id.fragment, videoFragment);
        ft.commit();
        isnoShow(0);
        myview.getTvContent().setText(mainRbRecommend.getText().toString());
        radioButtons = new ArrayList<>();
        radioButtons.add(mainRbRecommend);
        radioButtons.add(mainRbTalk);
        radioButtons.add(mainRbVideo);
        mainRbRecommend.setChecked(true);

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_rb_recommend:
                radioButton(0);
                isnoShow(0);
                myview.getTvContent().setText(mainRbRecommend.getText().toString());
                break;
            case R.id.main_rb_talk:
                radioButton(1);
                isnoShow(1);
                myview.getTvContent().setText(mainRbTalk.getText().toString());
                break;
            case R.id.main_rb_video:
                radioButton(2);
                isnoShow(2);
                myview.getTvContent().setText(mainRbVideo.getText().toString());
                break;
        }
    }

    public void radioButton(int index) {
        for (int i = 0; i < radioButtons.size(); i++) {
            RadioButton radioButton = radioButtons.get(i);
            if (index == i) {
                radioButton.setChecked(true);
            } else {
                radioButton.setChecked(false);
            }
        }
    }
    public void isnoShow(int index) {
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        for (int i = 0; i < list.size(); i++) {
            Fragment fragment = list.get(i);
            if (index == i) {
                fragmentTransaction.show(fragment);
            } else {
                fragmentTransaction.hide(fragment);
            }
        }
        fragmentTransaction.commit();
    }




}
