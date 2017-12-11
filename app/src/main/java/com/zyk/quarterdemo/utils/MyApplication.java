package com.zyk.quarterdemo.utils;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import imageloader.bwie.com.imageloaderlibrary.UtilImage;

/**
 * 作者：张玉轲
 * 时间：2017/11/28
 */

public class MyApplication extends Application {
    public static Context context;
    public static MyApplication mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        context = getApplicationContext();

        ImageLoaderConfiguration configuration= UtilImage.getConfiguration(context);
        ImageLoader.getInstance().init(configuration);

        mInstance = this;
    }



    public static MyApplication getInstance() {
        return mInstance;
    }


}
