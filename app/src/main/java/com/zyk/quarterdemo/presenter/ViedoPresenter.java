package com.zyk.quarterdemo.presenter;

import com.zyk.quarterdemo.base.BasePresenter;
import com.zyk.quarterdemo.beans.RegesterBean;
import com.zyk.quarterdemo.model.RegesterModel;
import com.zyk.quarterdemo.model.VideoModel;
import com.zyk.quarterdemo.view.IRegesterView;
import com.zyk.quarterdemo.view.IVideoView;

import java.io.File;

/**
 * 作者：张玉轲
 * 时间：2017/11/28
 */

public class ViedoPresenter extends BasePresenter<IVideoView>{
    private IVideoView iVideoView;
    private VideoModel videoModel;

    public ViedoPresenter( IVideoView iVideoView) {
        this.iVideoView = iVideoView;
        this.videoModel=new VideoModel();
        attachView(iVideoView);
    }
    public void getData(String uid, File videoFile, File videoDesc, String log, String lat){
        videoModel.getData(uid, videoFile, videoDesc, log, lat, new VideoModel.VideoListener() {
            @Override
            public void videoBackSuccess(RegesterBean bean) {
                iVideoView.videoBackSuccess(bean);
            }

            @Override
            public void videoBackFailure(String str) {
                iVideoView.videoBackFailure(str);
            }
        });
    }


}
