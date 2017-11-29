package com.zyk.quarterdemo.model;

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

public class TalkModel {

    public void getData(String page, final TalkModelListener listener){
        Map<String,String> map=new HashMap<>();
        map.put("page",page);
        Observable<TalkBean> data = new RetrofitUtils.Builder()
                .addConverterFactorys(GsonConverterFactory.create())
                .addCallAdapterFactorys(RxJava2CallAdapterFactory.create())
                .build().service().getData(map);
        data.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<TalkBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull TalkBean talkBean) {
                        listener.talkBackSuccess(talkBean);
                        System.out.println("==="+talkBean.getMsg());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        listener.talkBackFailure(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface TalkModelListener{
        void talkBackSuccess(TalkBean bean);
        void talkBackFailure(String str);
    }
}
