package com.zyk.quarterdemo.utils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：张玉轲
 * 时间：2017/11/28
 */

public class RetrofitUtils {
    public static RetrofitUtils responseUtils;
    //接口
    public InterfaceService service;

    //构造方法
    public RetrofitUtils(InterfaceService service) {
        this.service = service;
    }

    public InterfaceService service() {
        return service;
    }

    //内部类
    public static class Builder {
        private List<Converter.Factory> converterFactories = new ArrayList<>();
        private List<CallAdapter.Factory> calladapterFactories = new ArrayList<>();
        private Retrofit.Builder builder;


        //转化为gson格式
        public Builder addConverterFactorys(Converter.Factory factory) {
            //添加集合
            converterFactories.add(factory);
            return this;
        }

        //支持rxjava
        public Builder addCallAdapterFactorys(CallAdapter.Factory factory) {
            calladapterFactories.add(factory);
            return this;
        }

        public RetrofitUtils build() {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    //拦截器
                    .addInterceptor(new MyInterceptor())
                    .build();
            //添加OK
            builder = new Retrofit.Builder()
                    .baseUrl("https://www.zhaoapi.cn/")
                    //添加OK
                    .client(okHttpClient);
            if (converterFactories.size() == 0) {
                converterFactories.add(GsonConverterFactory.create());
            } else {
                for (Converter.Factory con : converterFactories) {
                    builder.addConverterFactory(con);
                }
            }
            if (calladapterFactories.size() == 0) {
                calladapterFactories.add(RxJava2CallAdapterFactory.create());
            } else {
                for (CallAdapter.Factory call : calladapterFactories) {
                    builder.addCallAdapterFactory(call);
                }
            }
            InterfaceService t = builder.build().create(InterfaceService.class);
            responseUtils = new RetrofitUtils(t);
            return responseUtils;

        }
    }
}
