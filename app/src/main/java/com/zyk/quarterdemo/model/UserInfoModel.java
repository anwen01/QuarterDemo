package com.zyk.quarterdemo.model;

import android.widget.Toast;

import com.zyk.quarterdemo.UserInfoActivity;
import com.zyk.quarterdemo.beans.RegesterBean;
import com.zyk.quarterdemo.beans.UserBean;
import com.zyk.quarterdemo.utils.RetrofitUtils;

import java.io.File;
import java.util.HashMap;
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

public class UserInfoModel {

    public void getData(String uid,final UserInfolListener listener){
        Map<String,String> map=new HashMap<>();
        map.put("uid",uid);
        Observable<UserBean> data = new RetrofitUtils.Builder()
                .addConverterFactorys(GsonConverterFactory.create())
                .addCallAdapterFactorys(RxJava2CallAdapterFactory.create())
                .build().service().getUserInfo(map);
        data.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<UserBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull UserBean userBean) {
                        listener.userInfoBackSuccess(userBean);
                        System.out.println("==="+userBean.getMsg());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        listener.userInfoBackFailure(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface UserInfolListener{
        void userInfoBackSuccess(UserBean bean);
        void userInfoBackFailure(String str);
    }



}
