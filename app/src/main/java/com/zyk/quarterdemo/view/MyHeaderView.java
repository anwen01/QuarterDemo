package com.zyk.quarterdemo.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zyk.quarterdemo.R;

/**
 * 作者：张玉轲
 * 时间：2017/11/7
 */

public class MyHeaderView extends LinearLayout {
    private TextView tvContent;
    private ImageView ivHeader;
    private ImageView ivWrite;

    public MyHeaderView(Context context) {
        super(context);
    }

    public MyHeaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //加载视图布局
        LayoutInflater.from(context).inflate(R.layout.main_header,this,true);
        tvContent=findViewById(R.id.tv_content);
        ivHeader=findViewById(R.id.iv_header);
        ivWrite=findViewById(R.id.iv_write);
        ivHeader.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                headerClickListener.setOnHeaderClickListener();
            }
        });

    }

    public TextView getTvContent() {
        return tvContent;
    }



    public MyHeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public OnHeaderClickListener headerClickListener;
    public interface OnHeaderClickListener{
        void setOnHeaderClickListener();
    }
    public void getIvHeader(OnHeaderClickListener headerClickListener) {
       this.headerClickListener=headerClickListener;
    }

}
