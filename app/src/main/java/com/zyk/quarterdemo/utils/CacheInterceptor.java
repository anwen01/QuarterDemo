package com.zyk.quarterdemo.utils;

import android.os.Looper;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者：张玉轲
 * 时间：2017/12/6
 */

public class CacheInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        // 有网络时 设置缓存超时时间1个小时
        int maxAge = 60 * 60;
        // 无网络时，设置超时为1天
        int maxStale = 60 * 60 * 24;
        Request request = chain.request();
        if (NetWorkUtils.isNetWorkAvailable(MyApplication.getInstance())) {
            //有网络时只从网络获取
            request = request.newBuilder().cacheControl(CacheControl.FORCE_NETWORK).build();
        } else {
            //无网络时只从缓存中读取
            request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
               Looper.prepare();
                Toast.makeText(MyApplication.getInstance(), "走拦截器缓存", Toast.LENGTH_SHORT).show();
                Looper.loop();
        }
        Response response = chain.proceed(request);
        if (NetWorkUtils.isNetWorkAvailable(MyApplication.getInstance())) {
            response = response.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .build();
        } else {
            response = response.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
        }
        return response;
    }
}
