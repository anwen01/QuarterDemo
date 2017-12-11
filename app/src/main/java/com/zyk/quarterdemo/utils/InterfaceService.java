package com.zyk.quarterdemo.utils;

import com.zyk.quarterdemo.beans.AdviceBean;
import com.zyk.quarterdemo.beans.HotListBean;
import com.zyk.quarterdemo.beans.LoginBean;
import com.zyk.quarterdemo.beans.RegesterBean;
import com.zyk.quarterdemo.beans.RemmTuijianBean;
import com.zyk.quarterdemo.beans.TalkBean;
import com.zyk.quarterdemo.beans.UserBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by lenovo on 2017/11/12.
 */

public interface InterfaceService {
    //登录
    @POST("user/login")
    @FormUrlEncoded
    Observable<LoginBean> getLogin(@FieldMap Map<String, String> map);
    //注册
    @POST("quarter/register")
    @FormUrlEncoded
    Observable<RegesterBean> getRegest(@FieldMap Map<String, String> map);
    //段子
    @POST("quarter/getJokes")
    @FormUrlEncoded
    Observable<TalkBean> getData(@FieldMap Map<String, String> map);

    //段子Get
    @GET("quarter/getJokes")
    Observable<TalkBean> getData1(@QueryMap Map<String, String> map);

    //发布段子
    @POST("quarter/publishJoke")
    @FormUrlEncoded
    Observable<RegesterBean> getFbTalk(@FieldMap Map<String, String> map);

    //发布段子的文字和图片
    @POST("quarter/publishJoke")
    @Multipart
    Observable<RegesterBean> getFbTalkPicture(@Part List<MultipartBody.Part> parts);

    //修改昵称
    @POST("user/updateNickName")
    @FormUrlEncoded
    Observable<RegesterBean> getChangerName(@FieldMap Map<String, String> map);
    //获取用户信息
    @POST("user/getUserInfo")
    @FormUrlEncoded
    Observable<UserBean> getUserInfo(@FieldMap Map<String, String> map);
    //修改头像
    @Multipart
    @POST("file/upload")
    Observable<RegesterBean> getUserHeader(@Query("uid") String uid, @Part MultipartBody.Part img);
    //广告
    @POST("quarter/getAd")
    Observable<AdviceBean> getAdvice();

    //视频热门
    @POST("quarter/getHotVideos")
    @FormUrlEncoded
    Observable<HotListBean> getVideoHots(@FieldMap Map<String, String> map);

    //发布视频
    @Multipart
    @POST("quarter/publishVideo")
    Observable<RegesterBean> getviedo(@Part List<MultipartBody.Part> parts);

    //推荐页面
    @Multipart
    @POST("quarter/getVideos")
    Observable<RemmTuijianBean> getTuijian(@Part List<MultipartBody.Part> parts);

}
