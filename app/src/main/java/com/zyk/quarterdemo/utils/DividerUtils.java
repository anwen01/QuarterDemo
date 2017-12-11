package com.zyk.quarterdemo.utils;

import java.math.BigDecimal;

/**
 * 作者：张玉轲
 * 时间：2017/12/6
 */

public class DividerUtils {
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
