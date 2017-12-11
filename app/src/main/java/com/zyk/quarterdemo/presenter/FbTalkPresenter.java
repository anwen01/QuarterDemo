package com.zyk.quarterdemo.presenter;

import android.util.Log;

import com.zyk.quarterdemo.base.BasePresenter;
import com.zyk.quarterdemo.beans.LoginBean;
import com.zyk.quarterdemo.beans.RegesterBean;
import com.zyk.quarterdemo.model.FbTalkModel;
import com.zyk.quarterdemo.model.LoginRegesterModel;
import com.zyk.quarterdemo.view.FbTalkView;
import com.zyk.quarterdemo.view.ILoginRegesterView;

import java.io.File;
import java.util.List;

/**
 * 作者：张玉轲
 * 时间：2017/11/28
 */

public class FbTalkPresenter extends BasePresenter<FbTalkView> {
    private FbTalkView fbTalkView;
    private FbTalkModel fbTalkModel;

    public FbTalkPresenter(FbTalkView fbTalkView) {
        this.fbTalkView = fbTalkView;
        this.fbTalkModel=new FbTalkModel();
        attachView(fbTalkView);
    }
    //文字
    public void getData(String uid,String content){
        fbTalkModel.getData(uid, content, new FbTalkModel.FbTalklListener() {
            @Override
            public void fbTalkBackSuccess(RegesterBean bean) {
                fbTalkView.fbTalkBackSuccess(bean);
            }

            @Override
            public void fbTalkBackFailure(String str) {
                fbTalkView.fbTalkBackFailure(str);
            }
        });
    }

    //文字和图片
    public void getDataPicture(String uid, String content, List<File> jokeFile){

        fbTalkModel.getDataPicture(uid, content, jokeFile, new FbTalkModel.FbTalklListener() {
            @Override
            public void fbTalkBackSuccess(RegesterBean bean) {
                fbTalkView.fbTalkBackSuccess(bean);
            }

            @Override
            public void fbTalkBackFailure(String str) {
                fbTalkView.fbTalkBackFailure(str);
            }
        });
    }


}
