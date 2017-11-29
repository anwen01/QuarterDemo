package com.zyk.quarterdemo.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;


public class MyInterceptor implements Interceptor {
    private int versionCode;
    public static String token;
    private Context context;
    private Request request;
    private PackageInfo info;

    @Override
    public Response intercept(Chain chain) throws IOException {

        try {
            //获取request
            request = chain.request();
            context= MyApplication.context;
            PackageManager manager=context.getPackageManager();
            info = manager.getPackageInfo(context.getPackageName(),0);
            versionCode=info.versionCode;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        SharedPreferences mytoken=context.getSharedPreferences("config",Context.MODE_PRIVATE);
        token=mytoken.getString("token","");

        //判断当前的请求
        if (request.method().equals("POST"))
        {
            //判断当前的请求Body
            if(request.body() instanceof FormBody)
            {
                //创建一个新的FromBody
                FormBody.Builder bodyBuilder=new FormBody.Builder();
                //获取原来的body
                FormBody body= (FormBody) request.body();
                //遍历body
                for (int i = 0; i <body.size() ; i++) {
                    //取出原来body的数据   存入新的body
                    bodyBuilder.add(body.encodedName(i),body.encodedValue(i));
                }
                //添加制定的公共参数到新的body里  把原先的body替换掉
                body=bodyBuilder.add("source","android")
                        .add("appVersion",versionCode+"")
                        .add("token",token)
                        .build();

                //获取新的request   取代原先的request
                request=request.newBuilder().post(body).build();
            }
        }
        //进行返回
        Response proceed = chain.proceed(request);
        return proceed;
    }
}
