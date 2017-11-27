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

package com.zyk.quarterdemo.tfc.file;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Sandbox class file
 * 应用沙盒目录中文件读写类
 * 沙盒目录：/data/data/<package name>/files
 * 沙盒目录可阻止其他应用的访问，甚至是其他用户的私自窥探（如果设备被root了，则用户可以随意获取任何数据）
 * @version $Id: Sandbox.java 1 2015-10-16 15:01:06Z huan.song $
 * @since 1.0
 */
public final class Sandbox extends AbstractAsyncIO {

    public static final String TAG = "Sandbox";

    /**
     * 用于对获取单例的线程加锁
     */
    private static final Object sInstanceLock = new Object();

    private static Sandbox sInstance;

    /**
     * 上下文环境
     */
    private final Context mAppContext;

    /**
     * 构造方法：初始化上下文环境
     */
    private Sandbox(Context c) {
        mAppContext = c.getApplicationContext();
    }

    /**
     * 获取已存在的实例，该实例是共享的，如果实例不存在，则创建新实例
     */
    public static Sandbox getInstance(Context c) {
        synchronized (sInstanceLock) {
            if (sInstance == null) {
                sInstance = new Sandbox(c);
            }

            return sInstance;
        }
    }

    @Override
    public String read(String fileName) throws IOException {
        // fileName：仅文件名，不需要路径
        StringBuilder data = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(getAppContext().openFileInput(fileName)));
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line);
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        return data.toString();
    }

    @Override
    public void write(String fileName, String data, boolean isAppend) throws IOException {
        // fileName：仅文件名，不需要路径
        BufferedWriter writer = null;
        try {
            FileOutputStream out = getAppContext().openFileOutput(fileName, (isAppend ? Context.MODE_APPEND : Context.MODE_PRIVATE));
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(data);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     * 获取应用沙盒目录绝对路径
     *
     * @return 返回沙盒目录文件对象
     */
    public File getDirectory() {
        return getAppContext().getFilesDir();
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
