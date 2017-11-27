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

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Contacts class file
 * 通讯录管理类
 * 需要权限：
 * <uses-permission android:name="android.permission.READ_CONTACTS" />
 * @version $Id: Contacts.java 1 2015-10-16 15:01:06Z huan.song $
 * @since 1.0
 */
public class Contacts {
    /**
     * 用于对获取单例的线程加锁
     */
    private static final Object sInstanceLock = new Object();

    private static Contacts sInstance;

    /**
     * 上下文环境
     */
    private final Context mAppContext;

    /**
     * 进程数据读取类
     */
    private ContentResolver mContentResolver;

    /**
     * 构造方法：初始化上下文环境、进程数据读取类
     */
    private Contacts(Context c) {
        mAppContext = c.getApplicationContext();
        mContentResolver = getAppContext().getContentResolver();
    }

    /**
     * 获取已存在的实例，该实例是共享的，如果实例不存在，则创建新实例
     */
    public static Contacts getInstance(Context c) {
        synchronized (sInstanceLock) {
            if (sInstance == null) {
                sInstance = new Contacts(c);
            }

            return sInstance;
        }
    }

    /**
     * 获取所有通讯录数据，包括：Id 和 Display Name
     *
     * @return a Sparse Array Key => CONTACT_ID, Value => DISPLAY_NAME
     */
    public SparseArray<String> getDisplayNames() {
        SparseArray<String> rows = new SparseArray<>();

        Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        if (cursor == null) {
            return rows;
        }

        while (cursor.moveToNext()) {
            int contactId = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            if (contactId <= 0) {
                continue;
            }

            String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            rows.put(contactId, displayName);
        }

        cursor.close();
        return rows;
    }

    /**
     * 通过Id获取手机号码
     *
     * @param contactId 联系人Id
     * @return 手机号码列表
     */
    public List<String> getPhoneNumbers(int contactId) {
        List<String> rows = new ArrayList<>();

        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId, null, null);
        if (cursor == null) {
            return rows;
        }

        while (cursor.moveToNext()) {
            String phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            if (TextUtils.isEmpty(phoneNumber)) {
                continue;
            }

            rows.add(phoneNumber);
        }

        cursor.close();
        return rows;
    }

    /**
     * 通过Id获取邮箱
     *
     * @param contactId 联系人Id
     * @return 邮箱列表
     */
    public List<String> getEmails(int contactId) {
        List<String> rows = new ArrayList<>();

        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + "=" + contactId, null, null);
        if (cursor == null) {
            return rows;
        }

        while (cursor.moveToNext()) {
            String emailData = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
            if (TextUtils.isEmpty(emailData)) {
                continue;
            }

            rows.add(emailData);
        }

        cursor.close();
        return rows;
    }

    /**
     * 获取所有通讯录数据，包括：Id、Display Name、Phone Numbers、Emails
     *
     * @return 所有通讯录数据：Id、名字、号码列表、邮箱列表
     */
    public List<Item> getData() {
        List<Item> data = new ArrayList<>();

        SparseArray<String> rows = getDisplayNames();

        for (int i = 0; i < rows.size(); ++i) {
            int id = rows.keyAt(i);
            data.add(new Item(id, rows.valueAt(i), getPhoneNumbers(id), getEmails(id)));
        }

        return data;
    }

    /**
     * 获取进程数据读取类
     *
     * @return a ContentResolver
     */
    public ContentResolver getContentResolver() {
        return mContentResolver;
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
     * Item class
     * 通讯录单条记录类
     *
     * @since 1.0
     */
    public static class Item {
        /**
         * 联系人Id
         */
        private int mId;

        /**
         * Display Name
         */
        private String mDisplayName;

        /**
         * 手机号码列表
         */
        private List<String> mPhoneNumbers;

        /**
         * 邮箱列表
         */
        private List<String> mEmails;

        /**
         * 构造方法：初始化联系人Id、Display Name、手机号码列表、邮箱列表
         *
         * @param id           联系人Id
         * @param displayName  Display Name
         * @param phoneNumbers 手机号码列表
         * @param emails       邮箱列表
         */
        public Item(int id, String displayName, List<String> phoneNumbers, List<String> emails) {
            setId(id);
            setDisplayName(displayName);
            setPhoneNumbers(phoneNumbers);
            setEmails(emails);
        }

        /**
         * 获取联系人Id
         *
         * @return 联系人Id
         */
        public int getId() {
            return mId;
        }

        /**
         * 设置联系人Id
         *
         * @param id 联系人Id
         */
        public void setId(int id) {
            mId = id;
        }

        /**
         * 获取Display Name
         *
         * @return Display Name
         */
        public String getDisplayName() {
            return mDisplayName;
        }

        /**
         * 设置Display Name
         *
         * @param displayName Display Name
         */
        public void setDisplayName(String displayName) {
            mDisplayName = displayName;
        }

        /**
         * 获取手机号码列表
         *
         * @return 手机号码列表
         */
        public List<String> getPhoneNumbers() {
            return mPhoneNumbers;
        }

        /**
         * 设置手机号码列表
         *
         * @param phoneNumbers 手机号码列表
         */
        public void setPhoneNumbers(List<String> phoneNumbers) {
            mPhoneNumbers = phoneNumbers;
        }

        /**
         * 获取邮箱列表
         *
         * @return 邮箱列表
         */
        public List<String> getEmails() {
            return mEmails;
        }

        /**
         * 设置邮箱列表
         *
         * @param emails 邮箱列表
         */
        public void setEmails(List<String> emails) {
            mEmails = emails;
        }

    }

}
