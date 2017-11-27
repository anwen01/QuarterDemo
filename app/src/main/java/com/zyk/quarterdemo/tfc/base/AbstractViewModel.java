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

package com.zyk.quarterdemo.tfc.base;

import android.os.Bundle;

import com.zyk.quarterdemo.tfc.ap.Logger;

/**
 * AbstractViewModel interface file
 * ViewModel基类，需要子类继承后使用
 * @version $Id: AbstractViewModel.java 1 2015-01-09 10:00:06Z huan.song $
 * @since 1.0
 */
public abstract class AbstractViewModel {

    public static final String TAG = "AbstractViewModel";

    /**
     * 当View执行onViewCreated()后调用该方法
     *
     * @param args The construction arguments for this fragment.
     */
    public void onCreate(Bundle args) {
        Logger.d(TAG, getClass().getName() + "::onCreate()");
    }

    /**
     * 当View执行onStart()后调用该方法
     */
    public void onStart() {
        Logger.d(TAG, getClass().getName() + "::onStart()");
    }

    /**
     * 当View执行onStop()前调用该方法
     */
    public void onStop() {
        Logger.d(TAG, getClass().getName() + "::onStop()");
    }

    /**
     * 当View执行onDestroyView()前调用该方法
     * 销毁ViewModel前释放资源（cancel HTTP requests, close database connection...）
     */
    public void onDestroyed() {
        Logger.d(TAG, getClass().getName() + "::onDestroyed()");
    }

}
