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

import android.os.AsyncTask;
import android.support.annotation.NonNull;

/**
 * AsyncRegistry class file
 * 异步全局数据寄存类
 * @version $Id: AsyncRegistry.java 1 2015-10-16 15:01:06Z huan.song $
 * @since 1.0
 */
public class AsyncRegistry {

    public static final String TAG = "AsyncRegistry";

    /**
     * Registry类
     */
    private final Registry mRegistry;

    /**
     * 构造方法：初始化Registry类
     *
     * @param registry Registry类
     */
    public AsyncRegistry(@NonNull Registry registry) {
        mRegistry = registry;
    }

    /**
     * 通过名称获取整数值
     *
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @param listener     同步回执线程的处理接口
     */
    public void getInt(String key, int defaultValue, IntReaderCallback listener) {
        (new IntAsyncReader()).execute(key, defaultValue, listener);
    }

    /**
     * 设置名称和整数值
     *
     * @param key      The name of the preference to modify.
     * @param value    The new value for the preference.
     * @param listener 同步回执线程的处理接口
     */
    public void putInt(String key, int value, WriterCallback listener) {
        (new IntAsyncWriter()).execute(key, value, listener);
    }

    /**
     * 通过名称获取长整数值
     *
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @param listener     同步回执线程的处理接口
     */
    public void getLong(String key, long defaultValue, LongReaderCallback listener) {
        (new LongAsyncReader()).execute(key, defaultValue, listener);
    }

    /**
     * 设置名称和长整数值
     *
     * @param key      The name of the preference to modify.
     * @param value    The new value for the preference.
     * @param listener 同步回执线程的处理接口
     */
    public void putLong(String key, long value, WriterCallback listener) {
        (new LongAsyncWriter()).execute(key, value, listener);
    }

    /**
     * 通过名称获取浮点值
     *
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @param listener     同步回执线程的处理接口
     */
    public void getFloat(String key, float defaultValue, FloatReaderCallback listener) {
        (new FloatAsyncReader()).execute(key, defaultValue, listener);
    }

    /**
     * 设置名称和浮点值
     *
     * @param key      The name of the preference to modify.
     * @param value    The new value for the preference.
     * @param listener 同步回执线程的处理接口
     */
    public void putFloat(String key, float value, WriterCallback listener) {
        (new FloatAsyncWriter()).execute(key, value, listener);
    }

    /**
     * 通过名称获取布尔值
     *
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @param listener     同步回执线程的处理接口
     */
    public void getBoolean(String key, boolean defaultValue, BooleanReaderCallback listener) {
        (new BooleanAsyncReader()).execute(key, defaultValue, listener);
    }

    /**
     * 设置名称和布尔值
     *
     * @param key      The name of the preference to modify.
     * @param value    The new value for the preference.
     * @param listener 同步回执线程的处理接口
     */
    public void putBoolean(String key, boolean value, WriterCallback listener) {
        (new BooleanAsyncWriter()).execute(key, value, listener);
    }

    /**
     * 通过名称获取字符串值
     *
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @param listener     同步回执线程的处理接口
     */
    public void getString(String key, String defaultValue, StringReaderCallback listener) {
        (new StringAsyncReader()).execute(key, defaultValue, listener);
    }

    /**
     * 设置名称和字符串值
     *
     * @param key      The name of the preference to modify.
     * @param value    The new value for the preference.
     * @param listener 同步回执线程的处理接口
     */
    public void putString(String key, String value, WriterCallback listener) {
        (new StringAsyncWriter()).execute(key, value, listener);
    }

    /**
     * 通过名称删除数据
     *
     * @param key      The name of the preference to remove.
     * @param listener 同步回执线程的处理接口
     */
    public void remove(String key, RemoverCallback listener) {
        (new AsyncRemover()).execute(key, listener);
    }

    /**
     * IntReaderCallback interface
     * 异步读取文件，回执线程的处理接口
     *
     * @since 1.0
     */
    public interface IntReaderCallback {
        /**
         * 请求成功后回调方法
         *
         * @param value 整数值
         */
        void onComplete(int value);
    }

    /**
     * LongReaderCallback interface
     * 异步读取文件，回执线程的处理接口
     *
     * @since 1.0
     */
    public interface LongReaderCallback {
        /**
         * 请求成功后回调方法
         *
         * @param value 长整数值
         */
        void onComplete(long value);
    }

    /**
     * FloatReaderCallback interface
     * 异步读取文件，回执线程的处理接口
     *
     * @since 1.0
     */
    public interface FloatReaderCallback {
        /**
         * 请求成功后回调方法
         *
         * @param value 浮点值
         */
        void onComplete(float value);
    }

    /**
     * BooleanReaderCallback interface
     * 异步读取文件，回执线程的处理接口
     *
     * @since 1.0
     */
    public interface BooleanReaderCallback {
        /**
         * 请求成功后回调方法
         *
         * @param value 布尔值
         */
        void onComplete(boolean value);
    }

    /**
     * StringReaderCallback interface
     * 异步读取文件，回执线程的处理接口
     *
     * @since 1.0
     */
    public interface StringReaderCallback {
        /**
         * 请求成功后回调方法
         *
         * @param value 字符串值
         */
        void onComplete(String value);
    }

    /**
     * WriterCallback interface
     * 异步写入文件，回执线程的处理接口
     *
     * @since 1.0
     */
    public interface WriterCallback {
        /**
         * 请求完成后回调方法
         *
         * @param result 结果
         */
        void onComplete(boolean result);
    }

    /**
     * RemoverCallback interface
     * 异步删除数据，回执线程的处理接口
     *
     * @since 1.0
     */
    public interface RemoverCallback {
        /**
         * 请求完成后回调方法
         *
         * @param result 结果
         */
        void onComplete(boolean result);
    }

    /**
     * 异步文件读取器
     */
    private class IntAsyncReader extends AsyncTask<Object, Void, IntReaderCallback> {
        /**
         * 值
         */
        private int mValue;

        @Override
        protected IntReaderCallback doInBackground(Object... params) {
            mValue = mRegistry.getInt((String) params[0], (int) params[1]);
            return (IntReaderCallback) params[2];
        }

        @Override
        protected void onPostExecute(IntReaderCallback listener) {
            listener.onComplete(mValue);
        }
    }

    /**
     * 异步文件写入器
     */
    private class IntAsyncWriter extends AsyncTask<Object, Void, WriterCallback> {
        /**
         * 结果
         */
        private boolean mResult;

        @Override
        protected WriterCallback doInBackground(Object... params) {
            mResult = mRegistry.putInt((String) params[0], (int) params[1]);
            return (WriterCallback) params[2];
        }

        @Override
        protected void onPostExecute(WriterCallback listener) {
            listener.onComplete(mResult);
        }
    }

    /**
     * 异步文件读取器
     */
    private class LongAsyncReader extends AsyncTask<Object, Void, LongReaderCallback> {
        /**
         * 值
         */
        private long mValue;

        @Override
        protected LongReaderCallback doInBackground(Object... params) {
            mValue = mRegistry.getLong((String) params[0], (long) params[1]);
            return (LongReaderCallback) params[2];
        }

        @Override
        protected void onPostExecute(LongReaderCallback listener) {
            listener.onComplete(mValue);
        }
    }

    /**
     * 异步文件写入器
     */
    private class LongAsyncWriter extends AsyncTask<Object, Void, WriterCallback> {
        /**
         * 结果
         */
        private boolean mResult;

        @Override
        protected WriterCallback doInBackground(Object... params) {
            mResult = mRegistry.putLong((String) params[0], (long) params[1]);
            return (WriterCallback) params[2];
        }

        @Override
        protected void onPostExecute(WriterCallback listener) {
            listener.onComplete(mResult);
        }
    }

    /**
     * 异步文件读取器
     */
    private class FloatAsyncReader extends AsyncTask<Object, Void, FloatReaderCallback> {
        /**
         * 值
         */
        private float mValue;

        @Override
        protected FloatReaderCallback doInBackground(Object... params) {
            mValue = mRegistry.getFloat((String) params[0], (float) params[1]);
            return (FloatReaderCallback) params[2];
        }

        @Override
        protected void onPostExecute(FloatReaderCallback listener) {
            listener.onComplete(mValue);
        }
    }

    /**
     * 异步文件写入器
     */
    private class FloatAsyncWriter extends AsyncTask<Object, Void, WriterCallback> {
        /**
         * 结果
         */
        private boolean mResult;

        @Override
        protected WriterCallback doInBackground(Object... params) {
            mResult = mRegistry.putFloat((String) params[0], (float) params[1]);
            return (WriterCallback) params[2];
        }

        @Override
        protected void onPostExecute(WriterCallback listener) {
            listener.onComplete(mResult);
        }
    }

    /**
     * 异步文件读取器
     */
    private class BooleanAsyncReader extends AsyncTask<Object, Void, BooleanReaderCallback> {
        /**
         * 值
         */
        private boolean mValue;

        @Override
        protected BooleanReaderCallback doInBackground(Object... params) {
            mValue = mRegistry.getBoolean((String) params[0], (boolean) params[1]);
            return (BooleanReaderCallback) params[2];
        }

        @Override
        protected void onPostExecute(BooleanReaderCallback listener) {
            listener.onComplete(mValue);
        }
    }

    /**
     * 异步文件写入器
     */
    private class BooleanAsyncWriter extends AsyncTask<Object, Void, WriterCallback> {
        /**
         * 结果
         */
        private boolean mResult;

        @Override
        protected WriterCallback doInBackground(Object... params) {
            mResult = mRegistry.putBoolean((String) params[0], (boolean) params[1]);
            return (WriterCallback) params[2];
        }

        @Override
        protected void onPostExecute(WriterCallback listener) {
            listener.onComplete(mResult);
        }
    }

    /**
     * 异步文件读取器
     */
    private class StringAsyncReader extends AsyncTask<Object, Void, StringReaderCallback> {
        /**
         * 值
         */
        private String mValue;

        @Override
        protected StringReaderCallback doInBackground(Object... params) {
            mValue = mRegistry.getString((String) params[0], (String) params[1]);
            return (StringReaderCallback) params[2];
        }

        @Override
        protected void onPostExecute(StringReaderCallback listener) {
            listener.onComplete(mValue);
        }
    }

    /**
     * 异步文件写入器
     */
    private class StringAsyncWriter extends AsyncTask<Object, Void, WriterCallback> {
        /**
         * 结果
         */
        private boolean mResult;

        @Override
        protected WriterCallback doInBackground(Object... params) {
            mResult = mRegistry.putString((String) params[0], (String) params[1]);
            return (WriterCallback) params[2];
        }

        @Override
        protected void onPostExecute(WriterCallback listener) {
            listener.onComplete(mResult);
        }
    }

    /**
     * 异步文件写入器
     */
    private class AsyncRemover extends AsyncTask<Object, Void, RemoverCallback> {
        /**
         * 结果
         */
        private boolean mResult;

        @Override
        protected RemoverCallback doInBackground(Object... params) {
            mResult = mRegistry.remove((String) params[0]);
            return (RemoverCallback) params[1];
        }

        @Override
        protected void onPostExecute(RemoverCallback listener) {
            listener.onComplete(mResult);
        }
    }

}
