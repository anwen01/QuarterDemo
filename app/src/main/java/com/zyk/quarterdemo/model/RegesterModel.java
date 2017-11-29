package com.zyk.quarterdemo.model;

import com.zyk.quarterdemo.beans.LoginBean;
import com.zyk.quarterdemo.beans.RegesterBean;
import com.zyk.quarterdemo.utils.RetrofitUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：张玉轲
 * 时间：2017/11/28
 */

public class RegesterModel {

    public void getData(String regType,String mobile, String password,final RegesterlListener listener){
        Map<String,String> map=new HashMap<>();
        map.put("regType",regType);
        map.put("mobile",mobile);
        map.put("password",password);
        Observable<RegesterBean> data = new RetrofitUtils.Builder()
                .addConverterFactorys(GsonConverterFactory.create())
                .addCallAdapterFactorys(RxJava2CallAdapterFactory.create())
                .build().service().getRegest(map);
        data.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<RegesterBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RegesterBean regesterBean) {
                        listener.regesterBackSuccess(regesterBean);
                        System.out.println("==="+regesterBean.getMsg());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        listener.regesterBackFailure(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface RegesterlListener{
        void regesterBackSuccess(RegesterBean bean);
        void regesterBackFailure(String str);
    }
}
