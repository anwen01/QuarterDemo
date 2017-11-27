/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zyk.quarterdemo.tfc.ap;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * DisplayPixels class file
 * 屏幕信息类，宽、高、密度等，单位：像素
 *
 * @version $Id: DisplayPixels.java 1 2015-01-09 10:00:06Z huan.song $
 * @since 1.0
 */
public final class DisplayPixels {

    private static final String TAG = "DisplayPixels";

    /**
     * 用于对获取单例的线程加锁
     */
    private static final Object sInstanceLock = new Object();

    private static DisplayPixels sInstance;

    /**
     * 上下文环境
     */
    private final Context mAppContext;

    /**
     * 屏幕信息描述器
     */
    private DisplayMetrics mDisplayMetrics;

    /**
     * 构造方法：初始化上下文环境、屏幕信息描述器
     */
    private DisplayPixels(Context c) {
        mAppContext = c.getApplicationContext();
        mDisplayMetrics = getAppContext().getResources().getDisplayMetrics();
    }

    /**
     * 获取已存在的实例，该实例是共享的，如果实例不存在，则创建新实例
     */
    public static DisplayPixels getInstance(Context c) {
        synchronized (sInstanceLock) {
            if (sInstance == null) {
                sInstance = new DisplayPixels(c);
            }

            return sInstance;
        }
    }

    /**
     * 获取屏幕X轴像素点个数
     *
     * @return 返回屏幕宽度，单位：像素
     */
    public int getWidth() {
        return getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕Y轴像素点个数
     *
     * @return 返回屏幕高度，单位：像素
     */
    public int getHeight() {
        return getDisplayMetrics().heightPixels;
    }

    /**
     * 获取屏幕逻辑密度
     * 160 dpi 屏幕（如：240x320, 1.5"x2"），逻辑密度 = 1；120 dpi 屏幕，逻辑密度 = 0.75;
     *
     * @return 返回逻辑密度
     */
    public float getDensity() {
        return getDisplayMetrics().density;
    }

    /**
     * 获取屏幕每英寸物理像素点的个数
     *
     * @return The screen density expressed as dots-per-inch
     */
    public float getDPI() {
        return getDisplayMetrics().densityDpi;
    }

    /**
     * 获取屏幕X轴每英寸物理像素点的个数
     *
     * @return The exact physical pixels per inch of the screen in the X dimension.
     */
    public float getXDPI() {
        return getDisplayMetrics().xdpi;
    }

    /**
     * 获取屏幕Y轴每英寸物理像素点的个数
     *
     * @return The exact physical pixels per inch of the screen in the Y dimension.
     */
    public float getYDPI() {
        return getDisplayMetrics().ydpi;
    }

    /**
     * 获取状态栏高度
     *
     * @return 返回状态栏高度，单位：像素
     */
    public float getStatusBarHeight() {
        int identifier = 0;
        try {
            identifier = getAppContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        } catch (Exception e) {
            // Ignore
        }

        if (identifier <= 0) {
            Logger.e(TAG, "identifier <= 0");
            return 0;
        }

        float result = 0;
        try {
            result = getAppContext().getResources().getDimensionPixelSize(identifier);
        } catch (Resources.NotFoundException e) {
            // Ignore
        }

        if (result <= 0) {
            Logger.e(TAG, "result <= 0");
            return 0;
        }

        return result;
    }

    /**
     * 获取屏幕信息描述器
     *
     * @return a DisplayMetrics
     */
    public DisplayMetrics getDisplayMetrics() {
        return mDisplayMetrics;
    }

    /**
     * 获取上下文环境
     *
     * @return a Context
     */
    public Context getAppContext() {
        return mAppContext;
    }

}
