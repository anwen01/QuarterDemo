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

/**
 * TypeCast class file
 * 类型转换类

 * @version $Id: TypeCast.java 1 2017-03-03 10:00:06Z huan.song $
 * @since 1.0
 */
public class TypeCast {

    public static final String TAG = "TypeCast";

    /**
     * Convert Object to Integer
     * 注：
     * TypeCast.toInt(null, -1)); // Return：-1，Not：0
     * TypeCast.toInt("", -1)); // Return：-1，Not：0
     *
     * @param value        a Object
     * @param defaultValue Value to return if value is null、empty or convert failure.
     * @return Returns the converted value if it exists, or defaultValue
     */
    public static int toInt(Object value, int defaultValue) {
        if (value == null) {
            return defaultValue;
        }

        try {
            return Integer.valueOf(value.toString());
        } catch (NumberFormatException e1) {
            Logger.e(TAG, "toInt() Convert String to Integer, defaultValue: " + defaultValue, e1);
            try {
                return Double.valueOf(value.toString()).intValue();
            } catch (NumberFormatException e2) {
                Logger.e(TAG, "toInt() Convert String to Double, defaultValue: " + defaultValue, e2);
                return defaultValue;
            }
        }
    }

    /**
     * Convert Object to Long
     * 注：
     * TypeCast.toLong(null, -1)); // Return：-1，Not：0
     * TypeCast.toLong("", -1)); // Return：-1，Not：0
     *
     * @param value        a Object
     * @param defaultValue Value to return if value is null、empty or convert failure.
     * @return Returns the converted value if it exists, or defaultValue
     */
    public static long toLong(Object value, long defaultValue) {
        if (value == null) {
            return defaultValue;
        }

        try {
            return Long.valueOf(value.toString());
        } catch (NumberFormatException e1) {
            Logger.e(TAG, "toLong() Convert String to Long, defaultValue: " + defaultValue, e1);
            try {
                return Double.valueOf(value.toString()).longValue();
            } catch (NumberFormatException e2) {
                Logger.e(TAG, "toLong() Convert String to Double, defaultValue: " + defaultValue, e2);
                return defaultValue;
            }
        }
    }

    /**
     * Convert Object to Float
     * 注：
     * TypeCast.toFloat(null, -1.0f)); // Return：-1.0f，Not：0
     * TypeCast.toFloat("", -1.0f)); // Return：-1.0f，Not：0
     *
     * @param value        a Object
     * @param defaultValue Value to return if value is null、empty or convert failure.
     * @return Returns the converted value if it exists, or defaultValue
     */
    public static float toFloat(Object value, float defaultValue) {
        if (value == null) {
            return defaultValue;
        }

        try {
            return Float.valueOf(value.toString());
        } catch (NumberFormatException e) {
            Logger.e(TAG, "toFloat() Convert String to Float, defaultValue: " + defaultValue, e);
            return defaultValue;
        }
    }

    /**
     * Convert Object to Double
     * 注：
     * TypeCast.toDouble(null, -1.0)); // Return：-1.0，Not：0
     * TypeCast.toDouble("", -1.0)); // Return：-1.0，Not：0
     *
     * @param value        a Object
     * @param defaultValue Value to return if value is null、empty or convert failure.
     * @return Returns the converted value if it exists, or defaultValue
     */
    public static double toDouble(Object value, double defaultValue) {
        if (value == null) {
            return defaultValue;
        }

        try {
            return Double.valueOf(value.toString());
        } catch (NumberFormatException e) {
            Logger.e(TAG, "toDouble() Convert String to Double, defaultValue: " + defaultValue, e);
            return defaultValue;
        }
    }

}
