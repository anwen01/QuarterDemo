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

package com.zyk.quarterdemo.tfc.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zyk.quarterdemo.tfc.ap.TypeCast;

/**
 * Registry class file
 * 全局数据寄存类
 * 使用DB寄存，用于寄存全局数据的DB名：trotri_db_registry_setting，table名：setting
 * <pre>
 * 表概要：
 * id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL
 * setting_key VARCHAR(50) UNIQUE NOT NULL
 * setting_value TEXT NOT NULL
 * </pre>
 * @version $Id: Registry.java 1 2015-10-16 15:01:06Z huan.song $
 * @since 1.0
 */
public class Registry {

    public static final String TAG = "Registry";

    /**
     * 默认用于寄存全局数据的DB名称
     */
    private static final String DEFAULT_DB_NAME = "tfc_db_registry_setting";

    /**
     * 版本
     */
    private static final int VERSION = 1;

    /**
     * 用于寄存全局数据的表名称
     */
    private static final String TABLE_NAME = "setting";

    /**
     * 寄存主键的键名
     */
    private static final String SETTING_PK = "id";

    /**
     * 寄存Key的键名
     */
    private static final String SETTING_KEY = "setting_key";

    /**
     * 寄存Value的键名
     */
    private static final String SETTING_VALUE = "setting_value";

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
     * 用于寄存全局数据的DB对象
     */
    private Db mDb;

    /**
     * 构造方法：初始化上下文环境、DB对象
     */
    protected Registry(Context c) {
        mAppContext = c.getApplicationContext();
        mDb = new Db(getAppContext(), getDbName(), VERSION, TABLE_NAME, getCommand());
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
     * 通过名称获取整数值
     *
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defaultValue
     */
    public int getInt(String key, int defaultValue) {
        String value = getString(key, null);
        return TypeCast.toInt(value, defaultValue);
    }

    /**
     * 设置名称和整数值
     *
     * @param key   The name of the preference to modify.
     * @param value The new value for the preference.
     * @return Returns True, or False
     */
    public boolean putInt(String key, int value) {
        return putString(key, String.valueOf(value));
    }

    /**
     * 通过名称获取长整数值
     *
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defaultValue
     */
    public long getLong(String key, long defaultValue) {
        String value = getString(key, null);
        return TypeCast.toLong(value, defaultValue);
    }

    /**
     * 设置名称和长整数值
     *
     * @param key   The name of the preference to modify.
     * @param value The new value for the preference.
     * @return Returns True, or False
     */
    public boolean putLong(String key, long value) {
        return putString(key, String.valueOf(value));
    }

    /**
     * 通过名称获取浮点值
     *
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defaultValue
     */
    public float getFloat(String key, float defaultValue) {
        String value = getString(key, null);
        return TypeCast.toFloat(value, defaultValue);
    }

    /**
     * 设置名称和浮点值
     *
     * @param key   The name of the preference to modify.
     * @param value The new value for the preference.
     * @return Returns True, or False
     */
    public boolean putFloat(String key, float value) {
        return putString(key, String.valueOf(value));
    }

    /**
     * 通过名称获取布尔值
     *
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defaultValue
     */
    public boolean getBoolean(String key, boolean defaultValue) {
        return (getInt(key, (defaultValue ? 1 : 0)) == 1);
    }

    /**
     * 设置名称和布尔值
     *
     * @param key   The name of the preference to modify.
     * @param value The new value for the preference.
     * @return Returns True, or False
     */
    public boolean putBoolean(String key, boolean value) {
        return putInt(key, (value ? 1 : 0));
    }

    /**
     * 通过名称获取字符串值
     *
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defaultValue
     */
    public String getString(String key, String defaultValue) {
        String value = defaultValue;

        if (key == null || "".equals(key)) {
            return value;
        }

        SQLiteDatabase dbReadable = mDb.getReadableDatabase();
        Cursor cursor = dbReadable.query(TABLE_NAME, new String[]{SETTING_VALUE}, SETTING_KEY + " = ?", new String[]{key}, null, null, null);
        if (cursor.moveToNext()) {
            value = cursor.getString(cursor.getColumnIndex(SETTING_VALUE));
        }

        cursor.close();
        dbReadable.close();
        return value;
    }

    /**
     * 设置名称和字符串值
     *
     * @param key   The name of the preference to modify.
     * @param value The new value for the preference.
     * @return Returns True, or False
     */
    public boolean putString(String key, String value) {
        if (key == null || "".equals(key) || value == null) {
            return false;
        }

        ContentValues values = new ContentValues();
        values.put(SETTING_KEY, key);
        values.put(SETTING_VALUE, value);

        boolean isNewRecord = (getString(key, null) == null);

        if (isNewRecord) {
            long lastInsertId = mDb.insert(null, values);
            if (lastInsertId > 0) {
                return true;
            }
        } else {
            int rowCount = mDb.update(values, SETTING_KEY + " = ?", new String[]{key});
            if (rowCount > 0) {
                return true;
            }
        }

        return false;
    }

    /**
     * 通过名称删除数据
     *
     * @param key The name of the preference to remove.
     * @return Returns True, or False
     */
    public boolean remove(String key) {
        if (key == null || "".equals(key)) {
            return false;
        }

        int rowCount = mDb.delete(SETTING_KEY + " = ?", new String[]{key});
        return (rowCount > 0);
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
     * 获取创建表命令
     *
     * @return 创建表命令
     */
    public String getCommand() {
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("CREATE TABLE IF NOT EXISTS ").append(TABLE_NAME).append(" ")
                .append("(")
                .append(SETTING_PK).append(" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ")
                .append(SETTING_KEY).append(" VARCHAR(50) UNIQUE NOT NULL, ")
                .append(SETTING_VALUE).append(" TEXT NOT NULL")
                .append(");");
        return sBuilder.toString();
    }

    /**
     * 获取用于寄存全局数据的DB名称
     *
     * @return 表名称
     */
    public String getDbName() {
        return DEFAULT_DB_NAME;
    }

}
