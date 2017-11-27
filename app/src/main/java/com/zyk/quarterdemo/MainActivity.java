package com.zyk.quarterdemo;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.zyk.quarterdemo.databinding.ActivityMainBinding;
import com.zyk.quarterdemo.view.MyHeaderView;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{
    ActivityMainBinding mMainBinding;
    private MainActivityViewModel mainActivityViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        mMainBinding.mainRg.setOnCheckedChangeListener(this);
        mainActivityViewModel = new MainActivityViewModel(this);
        RadioButton r= (RadioButton) mMainBinding.mainRg.getChildAt(0);
        r.setChecked(true);
        mMainBinding.myview.getTvContent().setText(r.getText().toString());
        mMainBinding.myview.getIvHeader(new MyHeaderView.OnHeaderClickListener() {
            @Override
            public void setOnHeaderClickListener() {
                Toast.makeText(MainActivity.this, "点击了", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        RadioButton r= (RadioButton) radioGroup.getChildAt(i-1);
        mMainBinding.myview.getTvContent().setText(r.getText().toString());
        mainActivityViewModel.show(i);
    }
}
