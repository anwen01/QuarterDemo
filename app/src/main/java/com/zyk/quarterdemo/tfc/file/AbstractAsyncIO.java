package com.zyk.quarterdemo.tfc.file;

import android.os.AsyncTask;

import com.zyk.quarterdemo.tfc.ap.Logger;

import java.io.IOException;

/**
 * AbstractAsyncIO abstract class file
 * 异步文件读写基类，需要子类继承后使用
 * @version $Id: AbstractAsyncIO.java 1 2015-10-16 15:01:06Z huan.song $
 * @since 1.0
 */
public abstract class AbstractAsyncIO {

    public static final String TAG = "AbstractAsyncIO";

    /**
     * 异步读取文件
     *
     * @param fileName 文件路径
     * @param listener 同步回执线程的处理接口
     */
    public void read(String fileName, AsyncReaderCallback listener) {
        (new AsyncReader()).execute(fileName, listener);
    }

    /**
     * 异步写入文件
     *
     * @param fileName 文件路径
     * @param data     需要写入或追加的数据
     * @param isAppend 操作模式：如果文件已存在，是覆盖原文件内容，还是往文件中追加内容
     * @param listener 同步回执线程的处理接口
     */
    public void write(String fileName, String data, boolean isAppend, AsyncWriterCallback listener) {
        (new AsyncWriter()).execute(fileName, data, isAppend, listener);
    }

    /**
     * 同步读取文件
     *
     * @param fileName 文件路径
     * @return 文件内容
     * @throws IOException 文件不存在、读取流被关闭、或安卓发生I/O错误
     */
    public abstract String read(String fileName) throws IOException;

    /**
     * 同步写入文件
     *
     * @param fileName 文件路径
     * @param data     需要写入或追加的数据
     * @param isAppend 操作模式：如果文件已存在，是覆盖原文件内容，还是往文件中追加内容
     * @throws IOException 打开文件失败、写入流被关闭、或安卓发生I/O错误
     */
    public abstract void write(String fileName, String data, boolean isAppend) throws IOException;

    /**
     * AsyncReaderCallback interface
     * 异步读取文件，回执线程的处理接口
     *
     * @since 1.0
     */
    public interface AsyncReaderCallback {
        /**
         * 请求成功后回调方法
         *
         * @param data 文件内容
         */
        void onSuccess(String data);

        /**
         * 请求失败后回调方法
         *
         * @param message 错误消息
         */
        void onFailure(String message);
    }

    /**
     * AsyncWriterCallback interface
     * 异步写入文件，回执线程的处理接口
     *
     * @since 1.0
     */
    public interface AsyncWriterCallback {
        /**
         * 请求成功后回调方法
         */
        void onSuccess();

        /**
         * 请求失败后回调方法
         *
         * @param message 错误消息
         */
        void onFailure(String message);
    }

    /**
     * 异步文件读取器
     */
    private class AsyncReader extends AsyncTask<Object, Void, Object> {
        /**
         * 文件名
         */
        private String mFileName;

        /**
         * 回执线程的处理接口
         */
        private AsyncReaderCallback mListener;

        @Override
        protected Object doInBackground(Object... params) {
            mFileName = (String) params[0];

            if (params[1] == null) {
                return new IllegalArgumentException("listener is null");
            }

            mListener = (AsyncReaderCallback) params[1];

            try {
                return read(mFileName);
            } catch (IOException e) {
                return e;
            }
        }

        @Override
        protected void onPostExecute(Object o) {
            if (o instanceof IllegalArgumentException) {
                Logger.e(TAG, "async read failure, fileName: " + mFileName + ", " + o.toString());
                return;
            }

            if (o instanceof IOException) {
                mListener.onFailure("fileName: " + mFileName + ", " + ((IOException) o).getMessage());
                return;
            }

            mListener.onSuccess((String) o);
        }
    }

    /**
     * 异步文件写入器
     */
    private class AsyncWriter extends AsyncTask<Object, Void, Object> {
        /**
         * 文件名
         */
        private String mFileName;

        /**
         * 回执线程的处理接口
         */
        private AsyncWriterCallback mListener;

        @Override
        protected Object doInBackground(Object... params) {
            mFileName = (String) params[0];

            if (params[3] == null) {
                return new IllegalArgumentException("listener is null");
            }

            String data = (String) params[1];
            boolean isAppend = (boolean) params[2];
            mListener = (AsyncWriterCallback) params[3];

            try {
                write(mFileName, data, isAppend);
                return data;
            } catch (IOException e) {
                return e;
            }
        }

        @Override
        protected void onPostExecute(Object o) {
            if (o instanceof IllegalArgumentException) {
                Logger.e(TAG, "async write failure, fileName: " + mFileName + ", " + o.toString());
                return;
            }

            if (o instanceof IOException) {
                mListener.onFailure("fileName: " + mFileName + ", " + ((IOException) o).getMessage());
                return;
            }

            mListener.onSuccess();
        }
    }

}
