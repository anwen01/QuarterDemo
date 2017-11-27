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

import android.os.Environment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * SdCard class file
 * Secure Digital Memory Card 读写类
 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
 * @version $Id: SdCard.java 1 2015-10-16 15:01:06Z huan.song $
 * @since 1.0
 */
public final class SdCard extends AbstractAsyncIO {

    public static final String TAG = "SdCard";

    private static SdCard sInstance = new SdCard();

    private SdCard() {
    }

    /**
     * 获取已存在的实例，该实例是共享的
     */
    public static SdCard getInstance() {
        return sInstance;
    }

    @Override
    public String read(String fileName) throws IOException {
        // fileName：文件路径，不需要SD卡根路径
        StringBuilder data = new StringBuilder();
        BufferedReader reader = null;
        try {
            fileName = getDirectory(true).getAbsolutePath() + File.separator + fileName;
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName))));
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
        // fileName：文件路径，不需要SD卡根路径
        BufferedWriter writer = null;
        try {
            fileName = getDirectory(true).getAbsolutePath() + File.separator + fileName;
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(fileName), isAppend)));
            writer.write(data);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     * 获取SD卡根目录绝对路径
     *
     * @return 返回SD卡根目录文件对象，如果SD卡不存在，返回null
     */
    public File getDirectory() {
        return isExists() ? Environment.getExternalStorageDirectory() : null;
    }

    /**
     * 获取SD卡根目录绝对路径
     *
     * @param throwException 当SD卡不存在时，True：抛出异常、False：返回null
     * @return 返回SD卡根目录文件对象
     * @throws FileNotFoundException 如果SD卡不存在，返回null，或抛出异常
     */
    public File getDirectory(boolean throwException) throws FileNotFoundException {
        File file = getDirectory();
        if (file == null && throwException) {
            throw new FileNotFoundException("Secure Digital Memory Card Not Exists or Cannot Readable");
        }

        return file;
    }

    /**
     * 检查SD卡是否存在
     *
     * @return Returns True, or False
     */
    public boolean isExists() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

}
