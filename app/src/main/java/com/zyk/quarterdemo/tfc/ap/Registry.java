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

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

/**
 * Registry class file
 * 全局数据寄存类
 * 使用SharedPreferences寄存，用于寄存全局数据的SharedPreferences名：trotri_ap_registry_setting、模式：PRIVATE
 * 文件存放目录：/data/data/<package name>/shared_prefs
 *
 * @version $Id: Registry.java 1 2015-01-09 10:00:06Z huan.song $
 * @since 1.0
 */
public class Registry {

    public static final String TAG = "Registry";

    /**
     * 默认用于寄存全局数据的SharedPreferences对象名
     */
    private static final String DEFAULT_SETTING_NAME = "tfc_ap_registry_setting";

    /**
     * 用于对获取单例的线程加锁
     */
    private static final Object sInstanceLock = new Object();

    private static Registry sInstance;

    /**
     * 上下文环境
     */
    private final Context mAppContext;

    /**
     * SharedPreferences对象
     */
    private SharedPreferences mSetting;

    /**
     * 构造方法：初始化上下文环境、SharedPreferences对象
     */
    protected Registry(Context c) {
        mAppContext = c.getApplicationContext();
        mSetting = getAppContext().getSharedPreferences(getSettingName(), Context.MODE_PRIVATE);
    }

    /**
     * 获取已存在的实例，该实例是共享的，如果实例不存在，则创建新实例
     */
    public static Registry getInstance(Context c) {
        synchronized (sInstanceLock) {
            if (sInstance == null) {
                sInstance = new Registry(c);
            }

            return sInstance;
        }
    }

    /**
     * 通过名称获取字符串值
     *
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defaultValue
     */
    public String getString(String key, String defaultValue) {
        return getSetting().getString(key, defaultValue);
    }

    /**
     * 设置名称和字符串值，设置完后直接提交
     * 如果设置多个值，可先获取Editor类，设置完后再统一提交
     *
     * @param key   The name of the preference to modify.
     * @param value The new value for the preference.
     * @return Returns a reference to the same Registry object, so you can chain put calls together.
     */
    public Registry putString(String key, String value) {
        SharedPreferences.Editor editor = getEditor().putString(key, value);
        commit(editor);
        return this;
    }

    /**
     * 通过名称获取整数值
     *
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defaultValue
     */
    public int getInt(String key, int defaultValue) {
        return getSetting().getInt(key, defaultValue);
    }

    /**
     * 设置名称和整数值，设置完后直接提交
     * 如果设置多个值，可先获取Editor类，设置完后再统一提交
     *
     * @param key   The name of the preference to modify.
     * @param value The new value for the preference.
     * @return Returns a reference to the same Registry object, so you can chain put calls together.
     */
    public Registry putInt(String key, int value) {
        SharedPreferences.Editor editor = getEditor().putInt(key, value);
        commit(editor);
        return this;
    }

    /**
     * 通过名称获取长整数值
     *
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defaultValue
     */
    public long getLong(String key, long defaultValue) {
        return getSetting().getLong(key, defaultValue);
    }

    /**
     * 设置名称和长整数值，设置完后直接提交
     * 如果设置多个值，可先获取Editor类，设置完后再统一提交
     *
     * @param key   The name of the preference to modify.
     * @param value The new value for the preference.
     * @return Returns a reference to the same Registry object, so you can chain put calls together.
     */
    public Registry putLong(String key, long value) {
        SharedPreferences.Editor editor = getEditor().putLong(key, value);
        commit(editor);
        return this;
    }

    /**
     * 通过名称获取浮点值
     *
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defaultValue
     */
    public float getFloat(String key, float defaultValue) {
        return getSetting().getFloat(key, defaultValue);
    }

    /**
     * 设置名称和浮点值，设置完后直接提交
     * 如果设置多个值，可先获取Editor类，设置完后再统一提交
     *
     * @param key   The name of the preference to modify.
     * @param value The new value for the preference.
     * @return Returns a reference to the same Registry object, so you can chain put calls together.
     */
    public Registry putFloat(String key, float value) {
        SharedPreferences.Editor editor = getEditor().putFloat(key, value);
        commit(editor);
        return this;
    }

    /**
     * 通过名称获取布尔值
     *
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defaultValue
     */
    public boolean getBoolean(String key, boolean defaultValue) {
        return getSetting().getBoolean(key, defaultValue);
    }

    /**
     * 设置名称和布尔值，设置完后直接提交
     * 如果设置多个值，可先获取Editor类，设置完后再统一提交
     *
     * @param key   The name of the preference to modify.
     * @param value The new value for the preference.
     * @return Returns a reference to the same Registry object, so you can chain put calls together.
     */
    public Registry putBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = getEditor().putBoolean(key, value);
        commit(editor);
        return this;
    }

    /**
     * 通过名称删除数据，删除完后直接提交
     * 如果删除多个数据，可先获取Editor类，删除完后再统一提交
     *
     * @param key The name of the preference to remove.
     * @return Returns a reference to the same Registry object, so you can chain put calls together.
     */
    public Registry remove(String key) {
        SharedPreferences.Editor editor = getEditor().remove(key);
        commit(editor);
        return this;
    }

    /**
     * 如果Editor类存在apply方法，则apply提交，否则commit提交
     *
     * @param editor SharedPreferences的内部Editor类
     * @return boolean 大于等于GINGERBREAD时都返回true，否则返回commit结果
     */
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public boolean commit(SharedPreferences.Editor editor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            editor.apply();
            return true;
        }

        return editor.commit();
    }

    /**
     * 获取SharedPreferences的内部Editor类
     *
     * @return a SharedPreferences.Editor
     */
    public SharedPreferences.Editor getEditor() {
        return getSetting().edit();
    }

    /**
     * 获取SharedPreferences类
     *
     * @return a SharedPreferences
     */
    public SharedPreferences getSetting() {
        return mSetting;
    }

    /**
     * 获取上下文环境
     *
     * @return a Context
     */
    public Context getAppContext() {
        return mAppContext;
    }

    /**
     * 获取用于寄存全局数据的SharedPreferences对象名
     *
     * @return SharedPreferences对象名
     */
    public String getSettingName() {
        return DEFAULT_SETTING_NAME;
    }

}
