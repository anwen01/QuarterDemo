package com.zyk.quarterdemo.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zyk.quarterdemo.R;

/**
 * 作者：张玉轲
 * 时间：2017/11/29
 */

public class ZhuanMainView extends LinearLayout{
    private ImageView zhuan_iv_open;
    private ImageView zhuan_iv_packup;

    public ZhuanMainView(Context context) {
        super(context);
        init(context);
    }

    public ZhuanMainView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ZhuanMainView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    public void init(Context context) {
        View view = View.inflate(context, R.layout.zhuan_main, this);
        zhuan_iv_open = view.findViewById(R.id.zhuan_iv_open);
        zhuan_iv_packup = view.findViewById(R.id.zhuan_iv_packup);
        zhuan_iv_open.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击打开时出现动画
                zhuan_iv_open.setVisibility(GONE);
                zhuan_iv_packup.setVisibility(VISIBLE);
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(zhuan_iv_packup, "rotation", 180f, 0f);
                //旋转动画
                objectAnimator.setDuration(800);
                objectAnimator.start();
                if (onZhuanMainListener != null) {
                    onZhuanMainListener.onListener(true);
                }
            }
        });
        //点击关闭时
        zhuan_iv_packup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                zhuan_iv_packup.setVisibility(GONE);
                zhuan_iv_open.setVisibility(VISIBLE);
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(zhuan_iv_open, "rotation", 0f, 180f);
                //旋转动画
                objectAnimator.setDuration(800);
                objectAnimator.start();
                if (onZhuanMainListener != null) {
                    onZhuanMainListener.onListener(false);
                }
            }
        });
    }

    //定义接口
    public OnZhuanMainListener onZhuanMainListener;

    public interface OnZhuanMainListener {
        public void onListener(boolean flag);
    }

    public void setonZhuanMainListener(OnZhuanMainListener onZhuanMainListener) {
        this.onZhuanMainListener = onZhuanMainListener;
    }


}
