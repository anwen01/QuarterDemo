package com.zyk.quarterdemo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 作者：张玉轲
 * 时间：2017/12/1
 */

public class RandomUtil {
    public static String getRandomFileName(){
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyyMMdd");

        Date date = new Date();

        String str = simpleDateFormat.format(date);

        Random random = new Random();
        int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数
        return str+rannum ;// 当前时间
    }

}
