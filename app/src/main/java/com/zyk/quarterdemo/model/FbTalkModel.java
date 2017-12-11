package com.zyk.quarterdemo.model;

import com.zyk.quarterdemo.beans.RegesterBean;
import com.zyk.quarterdemo.utils.RetrofitUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class FbTalkModel {
    //文字
    public void getData(String uid, String content, final FbTalklListener listener){

        Map<String,String> map=new HashMap<>();
        map.put("uid",uid);
        map.put("content",content);
        Observable<RegesterBean> data = new RetrofitUtils.Builder()
                .addConverterFactorys(GsonConverterFactory.create())
                .addCallAdapterFactorys(RxJava2CallAdapterFactory.create())
                .build().service().getFbTalk(map);
        data.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<RegesterBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RegesterBean regesterBean) {
                        listener.fbTalkBackSuccess(regesterBean);
                        System.out.println("==="+regesterBean.getMsg());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        listener.fbTalkBackFailure(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    //图片和文字
    public void getDataPicture(String uid, String content,List<File> files, final FbTalklListener listener){
        MultipartBody.Builder builder=new MultipartBody.Builder()
                .addFormDataPart("uid",uid)
                .addFormDataPart("content",content);
             for (File file : files) {
             RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                 builder.addFormDataPart("jokeFiles",file.getName(),requestBody);
         }

        List<MultipartBody.Part> parts = builder.build().parts();

        Observable<RegesterBean> data = new RetrofitUtils.Builder()
                .addConverterFactorys(GsonConverterFactory.create())
                .addCallAdapterFactorys(RxJava2CallAdapterFactory.create())
                .build().service().getFbTalkPicture(parts);
        data.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<RegesterBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RegesterBean regesterBean) {
                        listener.fbTalkBackSuccess(regesterBean);
                        System.out.println("==="+regesterBean.getMsg());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        listener.fbTalkBackFailure(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface FbTalklListener{
        void fbTalkBackSuccess(RegesterBean bean);
        void fbTalkBackFailure(String str);
    }
}
