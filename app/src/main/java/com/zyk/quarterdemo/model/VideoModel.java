package com.zyk.quarterdemo.model;

import com.zyk.quarterdemo.beans.RegesterBean;
import com.zyk.quarterdemo.utils.RetrofitUtils;

import java.io.File;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：张玉轲
 * 时间：2017/11/28
 */

public class VideoModel {

    public void getData(String uid, File videoFile, File coverFile, String log, String lat, final VideoListener listener){
        RequestBody requestBody1 = RequestBody.create(MediaType.parse("multipart/form-data"), videoFile);
        RequestBody requestBody2 = RequestBody.create(MediaType.parse("multipart/form-data"), coverFile);

        MultipartBody.Builder builder = new MultipartBody.Builder()
                .addFormDataPart("uid", uid)
                .addFormDataPart("longitude", log)
                .addFormDataPart("latitude", lat)
                .addFormDataPart("videoFile", videoFile.getName(), requestBody1)
                .addFormDataPart("coverFile", coverFile.getName(), requestBody2);
        List<MultipartBody.Part> parts = builder.build().parts();
        Observable<RegesterBean> data = new RetrofitUtils.Builder()
                .addConverterFactorys(GsonConverterFactory.create())
                .addCallAdapterFactorys(RxJava2CallAdapterFactory.create())
                .build().service().getviedo(parts);
        data.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<RegesterBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RegesterBean regesterBean) {
                        listener.videoBackSuccess(regesterBean);
                        System.out.println("==="+regesterBean.getMsg());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        listener.videoBackFailure(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface VideoListener{
        void videoBackSuccess(RegesterBean bean);
        void videoBackFailure(String str);
    }
}
