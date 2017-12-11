package com.zyk.quarterdemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zyk.quarterdemo.R;

/**
 * 作者：张玉轲
 * 时间：2017/11/29
 */

public class ZhuantuView extends LinearLayout {
    private TextView zhuantu_item_text;
    private ImageView zhuantu1_tu;

    public ZhuantuView(Context context) {
        this(context, null);
    }

    public ZhuantuView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        View view = View.inflate(context, R.layout.zhuantu_item, this);
        zhuantu1_tu = view.findViewById(R.id.zhuantu1_tu);
        zhuantu_item_text = view.findViewById(R.id.zhuantu_item_text);

        //得到类型数据
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ZhuanMainView);
        int id = typedArray.getResourceId(R.styleable.ZhuanMainView_imageResoce, R.mipmap.ic_launcher_round);
        zhuantu1_tu.setImageResource(id);
        String string = typedArray.getString(R.styleable.ZhuanMainView_text);
        zhuantu_item_text.setText(string);

    }

    public ZhuantuView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
