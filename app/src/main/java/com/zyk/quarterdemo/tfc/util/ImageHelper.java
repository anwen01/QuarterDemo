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

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * ImageHelper class file
 * 图片辅助类
 * @version $Id: ImageHelper.java 1 2017-02-24 10:00:06Z huan.song $
 * @since 1.0
 */
public class ImageHelper {

    public static final String TAG = "ImageHelper";

    /**
     * Options class
     */
    public static class Options {
        /**
         * The width of the bitmap.
         * <p>Width will be set to -1 if there is an error trying to decode.</p>
         */
        private int mWidth = -1;

        /**
         * The height of the bitmap.
         * <p>Height will be set to -1 if there is an error trying to decode.</p>
         */
        private int mHeight = -1;

        /**
         * The mimetype of the decoded image.
         */
        private String mMimeType = "";

        /**
         * 构造方法：初始化图片宽度、宽度和类型
         *
         * @param width    bitmap宽度
         * @param height   bitmap高度
         * @param mimeType 图片类型
         */
        public Options(int width, int height, String mimeType) {
            setWidth(width);
            setHeight(height);
            setMimeType(mimeType);
        }

        /**
         * 获取bitmap宽度
         *
         * @return the width of the bitmap.
         */
        public int getWidth() {
            return mWidth;
        }

        /**
         * 设置bitmap宽度
         *
         * @param width the width of the bitmap.
         */
        public void setWidth(int width) {
            mWidth = (width < 0) ? -1 : width;
        }

        /**
         * 获取bitmap高度
         *
         * @return the height of the bitmap.
         */
        public int getHeight() {
            return mHeight;
        }

        /**
         * 设置bitmap高度
         *
         * @param height the height of the bitmap.
         */
        public void setHeight(int height) {
            mHeight = (height < 0) ? -1 : height;
        }

        /**
         * 获取图片类型
         *
         * @return the mimetype of the decoded image.
         */
        public String getMimeType() {
            return mMimeType;
        }

        /**
         * 设置图片类型
         *
         * @param mimeType the mimetype of the decoded image.
         */
        public void setMimeType(String mimeType) {
            mMimeType = (mimeType == null) ? "" : mimeType;
        }

        @Override
        public String toString() {
            return (new StringBuilder()).append("[ ")
                    .append("width: ").append(getWidth())
                    .append(", height: ").append(getHeight())
                    .append(", mimeType: ").append(getMimeType())
                    .append(" ]").toString();
        }

    }

    /**
     * 通过Drawable获取Bitmap
     *
     * @param dw a Drawable
     * @return a Bitmap
     */
    public static Bitmap drawableToBitmap(Drawable dw) {
        if (dw == null) {
            return null;
        }

        if (dw instanceof BitmapDrawable) {
            return ((BitmapDrawable) dw).getBitmap();
        }

        int width = dw.getIntrinsicWidth();
        int height = dw.getIntrinsicHeight();
        if (width <= 0 || height <= 0) {
            return null;
        }

        Bitmap bm = Bitmap.createBitmap(Math.max(width, 2), Math.max(height, 2), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bm);
        dw.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        dw.draw(canvas);

        return bm;
    }

    /**
     * 通过资源Id，获取图片的宽度、高度和类型
     *
     * @param c          上下文环境
     * @param resourceId 资源Id
     * @return 图片宽度、高度和类型
     */
    public static Options getOptions(Context c, int resourceId) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(c.getResources(), resourceId, opts);
        return new Options(opts.outWidth, opts.outHeight, opts.outMimeType);
    }

    /**
     * 通过图片路径，获取图片的宽度、高度和类型
     *
     * @param fileName 图片路径
     * @return 图片宽度、高度和类型
     */
    public static Options getOptions(String fileName) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(fileName, opts);
        return new Options(opts.outWidth, opts.outHeight, opts.outMimeType);
    }

}
