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

import android.util.Log;

/**
 * Logger final class file
 * 日志处理类
 * @version $Id: Logger.java 1 2015-01-09 10:00:06Z huan.song $
 * @since 1.0
 */
public final class Logger {
    /**
     * 日志级别
     */
    public static final int VERBOSE = Log.VERBOSE; // use Logger.v
    public static final int DEBUG = Log.DEBUG;     // use Logger.d
    public static final int INFO = Log.INFO;       // use Logger.i
    public static final int WARN = Log.WARN;       // use Logger.w
    public static final int ERROR = Log.ERROR;     // use Logger.e

    /**
     * 报告日志接口
     */
    private static OnReportListener mReportListener = null;

    private Logger() {
    }

    /**
     * 打印Verbose日志
     *
     * @param tag 日志标识
     * @param msg 日志内容
     * @return The number of bytes written.
     */
    public static int v(String tag, String msg) {
        report(VERBOSE, tag, msg);
        return Log.v(tag, msg);
    }

    /**
     * 打印Debug日志
     *
     * @param tag 日志标识
     * @param msg 日志内容
     * @return The number of bytes written.
     */
    public static int d(String tag, String msg) {
        report(DEBUG, tag, msg);
        return Log.d(tag, msg);
    }

    /**
     * 打印Info日志
     *
     * @param tag 日志标识
     * @param msg 日志内容
     * @return The number of bytes written.
     */
    public static int i(String tag, String msg) {
        report(INFO, tag, msg);
        return Log.i(tag, msg);
    }

    /**
     * 打印Warn日志
     *
     * @param tag 日志标识
     * @param msg 日志内容
     * @return The number of bytes written.
     */
    public static int w(String tag, String msg) {
        report(WARN, tag, msg);
        return Log.w(tag, msg);
    }

    /**
     * 打印Warn日志
     *
     * @param tag 日志标识
     * @param tr  a Throwable to log
     * @return The number of bytes written.
     */
    public static int w(String tag, Throwable tr) {
        return w(tag, Log.getStackTraceString(tr));
    }

    /**
     * 打印Warn日志
     *
     * @param tag 日志标识
     * @param msg 日志内容
     * @param tr  a Throwable to log
     * @return The number of bytes written.
     */
    public static int w(String tag, String msg, Throwable tr) {
        return w(tag, msg + '\n' + getStackTraceString(tr));
    }

    /**
     * 打印Error日志
     *
     * @param tag 日志标识
     * @param msg 日志内容
     * @return The number of bytes written.
     */
    public static int e(String tag, String msg) {
        report(ERROR, tag, msg);
        return Log.e(tag, msg);
    }

    /**
     * 打印Error日志
     *
     * @param tag 日志标识
     * @param tr  a Throwable to log
     * @return The number of bytes written.
     */
    public static int e(String tag, Throwable tr) {
        return e(tag, getStackTraceString(tr));
    }

    /**
     * 打印Error日志
     *
     * @param tag 日志标识
     * @param msg 日志内容
     * @param tr  a Throwable to log
     * @return The number of bytes written.
     */
    public static int e(String tag, String msg, Throwable tr) {
        return e(tag, msg + '\n' + getStackTraceString(tr));
    }

    /**
     * 通过Throwable获取日志内容
     *
     * @param tr a Throwable to log
     * @return 日志内容
     */
    public static String getStackTraceString(Throwable tr) {
        return Log.getStackTraceString(tr);
    }

    /**
     * 报告日志
     *
     * @param priority 日志级别
     * @param tag      日志标识
     * @param msg      日志内容
     */
    public static void report(int priority, String tag, String msg) {
        if (mReportListener == null) {
            return;
        }

        mReportListener.onReport(priority, tag, msg);
    }

    /**
     * 设置报告日志接口
     *
     * @param listener a OnReportListener to report log
     */
    public static void setOnReportListener(OnReportListener listener) {
        mReportListener = listener;
    }

    /**
     * OnReportListener interface
     * 报告日志接口
     *
     * @since 1.0
     */
    public interface OnReportListener {
        /**
         * 报告日志的回调方法
         *
         * @param priority 日志级别
         * @param tag      日志标识
         * @param msg      日志内容
         */
        void onReport(int priority, String tag, String msg);
    }

}
