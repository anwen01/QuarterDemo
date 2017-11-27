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

import android.app.Activity;
import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * PhoneState class file
 * 手机信息类
 * 需要权限：
 * <uses-permission android:name="android.permission.READ_PHONE_STATE" />
 *
 * @version $Id: PhoneState.java 1 2015-01-09 10:00:06Z huan.song $
 * @since 1.0
 */
public class PhoneState {

    private static final String TAG = "PhoneState";

    /**
     * 用于对获取单例的线程加锁
     */
    private static final Object sInstanceLock = new Object();

    private static PhoneState sInstance;

    /**
     * 上下文环境
     */
    private final Context mAppContext;

    /**
     * 手机信息管理器
     */
    private final TelephonyManager mTelephonyManager;

    /**
     * 构造方法：初始化上下文环境、手机信息管理器
     */
    private PhoneState(Context c) {
        mAppContext = c.getApplicationContext();
        mTelephonyManager = (TelephonyManager) getAppContext().getSystemService(Activity.TELEPHONY_SERVICE);
    }

    /**
     * 获取已存在的实例，该实例是共享的，如果实例不存在，则创建新实例
     */
    public static PhoneState getInstance(Context c) {
        synchronized (sInstanceLock) {
            if (sInstance == null) {
                sInstance = new PhoneState(c);
            }

            return sInstance;
        }
    }

    /**
     * 获取设备识别码，GSM：IMEI、CDMA：MEID
     *
     * @return 设备识别码
     */
    public String getDeviceId() {
        String deviceId = getTelephonyManager().getDeviceId();
        return (deviceId == null) ? "" : deviceId;
    }

    /**
     * 获取SIM卡序列号
     *
     * @return SIM卡序列号
     */
    public String getSimSerialNumber() {
        String serialNumber = getTelephonyManager().getSimSerialNumber();
        return (serialNumber == null) ? "" : serialNumber;
    }

    /**
     * 获取手机号码
     * 注：如果运营商没有将手机号写入SIM卡，则无法获取；有些双卡手机可能无法获取。
     *
     * @return 手机号码
     */
    public String getPhoneNumber() {
        String phoneNumber = getTelephonyManager().getLine1Number();
        return (phoneNumber == null) ? "" : phoneNumber;
    }

    /**
     * 获取电话状态
     * 响铃：{@link TelephonyManager#CALL_STATE_RINGING}
     * 摘机：{@link TelephonyManager#CALL_STATE_OFFHOOK}
     * 无活动：{@link TelephonyManager#CALL_STATE_IDLE}
     *
     * @return 电话状态
     */
    public int getCallState() {
        return getTelephonyManager().getCallState();
    }

    /**
     * 获取手机信息管理器
     *
     * @return a TelephonyManager
     */
    public TelephonyManager getTelephonyManager() {
        return mTelephonyManager;
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
