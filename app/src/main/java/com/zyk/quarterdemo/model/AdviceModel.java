package com.zyk.quarterdemo.model;

import com.zyk.quarterdemo.beans.AdviceBean;
import com.zyk.quarterdemo.beans.RemmTuijianBean;
import com.zyk.quarterdemo.beans.TalkBean;
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

public class AdviceModel {

    public void getData( final AdviceModelListener listener){

        Observable<AdviceBean> data = new RetrofitUtils.Builder()
                .addConverterFactorys(GsonConverterFactory.create())
                .addCallAdapterFactorys(RxJava2CallAdapterFactory.create())
                .build().service().getAdvice();
        data.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<AdviceBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull AdviceBean talkBean) {
                        listener.adviceBackSuccess(talkBean);
                        System.out.println("轮播图"+talkBean.getMsg());
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        listener.adviceBackFailure(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getData(String type, String page, final TuijianListener listener){
        MultipartBody.Builder builder = new MultipartBody.Builder()
                             .addFormDataPart("type", type)
                             .addFormDataPart("page", page);
        List<MultipartBody.Part> parts = builder.build().parts();
        Observable<RemmTuijianBean> data = new RetrofitUtils.Builder()
                .addConverterFactorys(GsonConverterFactory.create())
                .addCallAdapterFactorys(RxJava2CallAdapterFactory.create())
                .build().service().getTuijian(parts);
        data.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<RemmTuijianBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RemmTuijianBean bean) {
                        listener.tuijanBackSuccess(bean);
                        System.out.println("推荐"+bean.getMsg());
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        listener.tuijianBackFailure(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface TuijianListener{
        void tuijanBackSuccess(RemmTuijianBean bean);
        void tuijianBackFailure(String str);
    }

    public interface AdviceModelListener{
        void adviceBackSuccess(AdviceBean bean);
        void adviceBackFailure(String str);
    }
}
