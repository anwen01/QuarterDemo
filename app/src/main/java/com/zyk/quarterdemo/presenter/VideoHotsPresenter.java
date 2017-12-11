package com.zyk.quarterdemo.presenter;

import android.view.View;

import com.zyk.quarterdemo.base.BasePresenter;
import com.zyk.quarterdemo.beans.AdviceBean;
import com.zyk.quarterdemo.beans.HotListBean;
import com.zyk.quarterdemo.model.AdviceModel;
import com.zyk.quarterdemo.model.VideoHotsModel;
import com.zyk.quarterdemo.view.IAdviceView;
import com.zyk.quarterdemo.view.IVideoHotsView;

/**
 * 作者：张玉轲
 * 时间：2017/11/28
 */

public class VideoHotsPresenter extends BasePresenter<IVideoHotsView> {
    private IVideoHotsView iVideoHotsView;
    private VideoHotsModel videoHotsModel;

    public VideoHotsPresenter(IVideoHotsView iVideoHotsView) {
        this.iVideoHotsView = iVideoHotsView;
        this.videoHotsModel=new VideoHotsModel();
        attachView(iVideoHotsView);
    }

    public void getData(String page){
        videoHotsModel.getData(page, new VideoHotsModel.VideoHotListener() {
            @Override
            public void videoHotBackSuccess(HotListBean bean) {
                iVideoHotsView.videoHotsBackSuccess(bean);
            }

            @Override
            public void videoHotBackFailure(String str) {
                iVideoHotsView.videoHotsBackFailure(str);
            }
        });
    }

}
