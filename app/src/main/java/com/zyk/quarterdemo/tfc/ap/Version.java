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
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Version class file
 * 版本信息类
 * 获取AndroidManifest.xml中android:versionCode、android:versionName
 * @version $Id: Version.java 1 2015-01-09 10:00:06Z huan.song $
 * @since 1.0
 */
public final class Version {

    private static final String TAG = "Version";

    /**
     * 用于对获取单例的线程加锁
     */
    private static final Object sInstanceLock = new Object();

    private static Version sInstance;

    /**
     * 上下文环境
     */
    private final Context mAppContext;

    /**
     * 版本号，从1开始
     */
    private int mCode = 0;

    /**
     * 版本名
     */
    private String mName = "";

    /**
     * 构造方法：初始化上下文环境、获取版本号和版本名
     */
    private Version(Context c) {
        mAppContext = c.getApplicationContext();

        PackageManager packageManager = getAppContext().getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getAppContext().getPackageName(), 0);
            if ((mCode = packageInfo.versionCode) < 0) {
                mCode = 0;
            }

            if ((mName = packageInfo.versionName) == null) {
                mName = "";
            }
        } catch (PackageManager.NameNotFoundException e) {
            Logger.e(TAG, "Version constructor", e);
        }
    }

    /**
     * 获取已存在的实例，该实例是共享的，如果实例不存在，则创建新实例
     */
    public static Version getInstance(Context c) {
        synchronized (sInstanceLock) {
            if (sInstance == null) {
                sInstance = new Version(c);
            }

            return sInstance;
        }
    }

    /**
     * 获取版本号
     *
     * @return 如果获取成功返回 > 0，否则返回0
     */
    public int getCode() {
        return mCode;
    }

    /**
     * 获取版本名
     *
     * @return 如果获取成功返回非空字符串，否则返回空字符串
     */
    public String getName() {
        return mName;
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
