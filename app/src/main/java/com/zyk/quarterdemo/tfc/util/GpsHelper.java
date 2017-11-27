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

package com.zyk.quarterdemo.tfc.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;

/**
 * GpsHelper class file
 * GPS辅助类
 * 需要包：Android Support Library V4
 * 需要权限：通过GPS定位或者支持位置更新监测器
 * <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
 * 需要权限：只是通过基站或者Wifi获取位置信息
 * <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
 * @version $Id: GpsHelper.java 1 2015-10-16 15:01:06Z huan.song $
 * @since 1.0
 */
public class GpsHelper {
    /**
     * 用于对获取单例的线程加锁
     */
    private static final Object sInstanceLock = new Object();

    private static GpsHelper sInstance;

    /**
     * 上下文环境
     */
    private final Context mAppContext;

    /**
     * 定位系统服务类
     */
    private LocationManager mLocationManager;

    /**
     * 构造方法：初始化上下文环境、定位系统服务类
     */
    private GpsHelper(Context c) {
        mAppContext = c.getApplicationContext();
        mLocationManager = (LocationManager) getAppContext().getSystemService(Context.LOCATION_SERVICE);
    }

    /**
     * 获取已存在的实例，该实例是共享的，如果实例不存在，则创建新实例
     */
    public static GpsHelper getInstance(Context c) {
        synchronized (sInstanceLock) {
            if (sInstance == null) {
                sInstance = new GpsHelper(c);
            }

            return sInstance;
        }
    }

    /**
     * 打开位置信息设置页面
     *
     * @param context a Activity
     */
    public static void toSetting(Activity context) {
        Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        context.startActivity(i);
    }

    /**
     * 检查ACCESS_FINE_LOCATION权限：通过GPS定位或者支持位置更新监测器
     * 添加ACCESS_FINE_LOCATION权限：<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
     *
     * @return Returns True, or False
     */
    public boolean isAccessFineLocationGranted() {
        return (ActivityCompat.checkSelfPermission(getAppContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED);
    }

    /**
     * 检查ACCESS_COARSE_LOCATION权限：只是通过基站或者Wifi获取位置信息
     * 添加ACCESS_COARSE_LOCATION权限：<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
     *
     * @return Returns True, or False
     */
    public boolean isAccessCoarseLocationGranted() {
        return (ActivityCompat.checkSelfPermission(getAppContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED);
    }

    /**
     * 检查是否开启GPS
     * 需要权限：<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
     *
     * @return Returns True, or False
     */
    public boolean isGpsEnabled() {
        return getLocationManager().isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    /**
     * 检查是否支持位置更新监测器
     * 需要权限：<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
     *
     * @return Returns True, or False
     */
    public boolean isPassiveEnabled() {
        return getLocationManager().isProviderEnabled(LocationManager.PASSIVE_PROVIDER);
    }

    /**
     * 检查是否支持通过基站或者Wifi获取位置信息
     * 需要权限：<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />或<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
     *
     * @return Returns True, or False
     */
    public boolean isNetworkEnabled() {
        return getLocationManager().isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    /**
     * 获取定位系统服务类
     *
     * @return a LocationManager
     */
    public LocationManager getLocationManager() {
        return mLocationManager;
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
