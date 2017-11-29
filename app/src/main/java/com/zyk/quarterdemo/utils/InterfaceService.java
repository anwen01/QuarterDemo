package com.zyk.quarterdemo.utils;

import com.zyk.quarterdemo.beans.LoginBean;
import com.zyk.quarterdemo.beans.RegesterBean;
import com.zyk.quarterdemo.beans.TalkBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by lenovo on 2017/11/12.
 */

public interface InterfaceService {
    @POST("user/login")
    @FormUrlEncoded
    Observable<LoginBean> getLogin(@FieldMap Map<String, String> map);

    @POST("quarter/register")
    @FormUrlEncoded
    Observable<RegesterBean> getRegest(@FieldMap Map<String, String> map);

    @POST("quarter/getJokes")
    @FormUrlEncoded
    Observable<TalkBean> getData(@FieldMap Map<String, String> map);

}
