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

import com.zyk.quarterdemo.tfc.ap.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * FileSearch class file
 * 文件查找类
 * @version $Id: FileSearch.java 1 2015-10-16 15:01:06Z huan.song $
 * @since 1.0
 */
public class FileSearch {

    private static final String TAG = "FileSearch";

    /**
     * 在目录中查找文件，通过多个文件名查找多个文件
     *
     * @param directory   目录File对象
     * @param fileNames   文件名数组
     * @param isRecursive 是否递归查询子目录
     * @return 查找到的文件对象集合
     */
    public static List<File> findByName(File directory, String[] fileNames, boolean isRecursive) {
        List<File> fileList = new ArrayList<>();

        if (directory == null || !directory.isDirectory()) {
            return fileList;
        }

        File files[] = directory.listFiles();
        if (files == null) {
            return fileList;
        }

        for (File file : files) {
            if (file == null) {
                continue;
            }

            if (file.isDirectory() && isRecursive) {
                fileList.addAll(findByName(file, fileNames, true));
                continue;
            }

            if (!file.isFile()) {
                continue;
            }

            for (String name : fileNames) {
                if (name.equalsIgnoreCase(file.getName())) {
                    fileList.add(file);
                }
            }
        }

        return fileList;
    }

    /**
     * 在目录中查找文件，通过多个文件后缀名查找多个文件
     *
     * @param directory    目录File对象
     * @param fileExtNames 文件后缀名数组
     * @param isRecursive  是否递归查询子目录
     * @return 查找到的文件对象集合
     */
    public static List<File> findByExtName(File directory, String[] fileExtNames, boolean isRecursive) {
        List<File> fileList = new ArrayList<>();

        if (directory == null || !directory.isDirectory()) {
            return fileList;
        }

        File files[] = directory.listFiles();
        if (files == null) {
            return fileList;
        }

        for (int i = 0; i < fileExtNames.length; i++) {
            fileExtNames[i] = fileExtNames[i].toLowerCase();
        }

        for (File file : files) {
            if (file == null) {
                continue;
            }

            if (file.isDirectory() && isRecursive) {
                fileList.addAll(findByExtName(file, fileExtNames, true));
                continue;
            }

            if (!file.isFile()) {
                continue;
            }

            for (String name : fileExtNames) {
                if (file.getName().endsWith(name)) {
                    fileList.add(file);
                }
            }
        }

        return fileList;
    }

    /**
     * 输出目录文件结构，树型结构输出到Info日志中
     *
     * @param directory   目录File对象
     * @param isRecursive 是否递归查询子目录
     * @param leftPad     树型结构左边字符，如：|-
     */
    public static void sendDirectoryTreeToInfoLog(File directory, boolean isRecursive, String leftPad) {
        if (directory == null || !directory.isDirectory()) {
            return;
        }

        File files[] = directory.listFiles();
        if (files == null) {
            return;
        }

        for (File file : files) {
            if (file == null) {
                continue;
            }

            Logger.i(TAG, "Tree - " + (file.isDirectory() ? "d" : "-") + " : " + leftPad + file.getAbsolutePath());

            if (file.isDirectory() && isRecursive) {
                sendDirectoryTreeToInfoLog(file, true, leftPad + leftPad);
            }
        }
    }

}
