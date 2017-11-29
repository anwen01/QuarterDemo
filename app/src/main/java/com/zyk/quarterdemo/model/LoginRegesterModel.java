package com.zyk.quarterdemo.model;

import com.zyk.quarterdemo.beans.LoginBean;
import com.zyk.quarterdemo.beans.TalkBean;

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
import com.zyk.quarterdemo.utils.RetrofitUtils;

/**
 * 作者：张玉轲
 * 时间：2017/11/28
 */

public class LoginRegesterModel {

    public void getData(String mobile, String password,final LoginRegesterlListener listener){
        Map<String,String> map=new HashMap<>();
        map.put("mobile",mobile);
        map.put("password",password);
        Observable<LoginBean> data = new RetrofitUtils.Builder()
                .addConverterFactorys(GsonConverterFactory.create())
                .addCallAdapterFactorys(RxJava2CallAdapterFactory.create())
                .build().service().getLogin(map);
        data.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull LoginBean talkBean) {
                        listener.logReBackSuccess(talkBean);
                        System.out.println("==="+talkBean.getMsg());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        listener.logReBackFailure(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface LoginRegesterlListener{
        void logReBackSuccess(LoginBean bean);
        void logReBackFailure(String str);
    }
}
