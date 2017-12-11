package com.zyk.quarterdemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 作者：张玉轲
 * 时间：2017/12/2
 */

public class SharePreUtils {
    private final static String NAME="config";

    public static SharedPreferences getPreferences(){
        return  MyApplication.context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    /**
     * 添加
     * @param key
     * @param value
     */
    public static void putShareprefer(String key,String value){
        SharedPreferences.Editor edit = getPreferences().edit();
        edit.putString(key,value).commit();
    }
    /**
     * 添加数字
     * @param key
     * @param value
     */
    public static void putSharepreferNum(String key,int value){
        SharedPreferences.Editor edit = getPreferences().edit();
        edit.putInt(key,value).commit();
    }

    public static void putSharepreferBoolean(String key,Boolean b){
        SharedPreferences.Editor edit = getPreferences().edit();
        edit.putBoolean(key,b).commit();
    }

    /**
     * 查询
     */
    public static String getShareprefervalue(String key){

        return getPreferences().getString(key,"");

    }
    /**
     * 查询数字
     */
    public static int getSharepreferNumvalue(String key){

        return getPreferences().getInt(key,0);

    }
    public static boolean getSharepreferBooleanvalue(String key){

        return getPreferences().getBoolean(key,false);

    }

    /**
     * 清除
     */

    public static void removeShareprefer(String key){
        SharedPreferences.Editor edit = getPreferences().edit();
        edit.remove(key).commit();
    }
}
