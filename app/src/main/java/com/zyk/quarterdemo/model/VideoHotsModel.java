package com.zyk.quarterdemo.model;

import com.zyk.quarterdemo.beans.HotListBean;
import com.zyk.quarterdemo.beans.UserBean;
import com.zyk.quarterdemo.utils.RetrofitUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：张玉轲
 * 时间：2017/11/28
 */

public class VideoHotsModel {

    public void getData(String page,final VideoHotListener listener){
        Map<String,String> map=new HashMap<>();
        map.put("page",page);

        Observable<HotListBean> data=new RetrofitUtils.Builder()
                .addCallAdapterFactorys(RxJava2CallAdapterFactory.create())
                .addConverterFactorys(GsonConverterFactory.create()).build()
                .service().getVideoHots(map);
        data.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<HotListBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HotListBean bean) {
                        listener.videoHotBackSuccess(bean);
                        System.out.println("==="+bean.getMsg());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        listener.videoHotBackFailure(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface VideoHotListener{
        void videoHotBackSuccess(HotListBean bean);
        void videoHotBackFailure(String str);
    }



}
