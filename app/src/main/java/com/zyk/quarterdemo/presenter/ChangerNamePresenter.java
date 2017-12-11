package com.zyk.quarterdemo.presenter;

import com.zyk.quarterdemo.base.BasePresenter;
import com.zyk.quarterdemo.beans.RegesterBean;
import com.zyk.quarterdemo.model.ChangerNameModel;
import com.zyk.quarterdemo.model.FbTalkModel;
import com.zyk.quarterdemo.view.ChangerNameView;
import com.zyk.quarterdemo.view.FbTalkView;

/**
 * 作者：张玉轲
 * 时间：2017/11/28
 */

public class ChangerNamePresenter extends BasePresenter<ChangerNameView> {
    private ChangerNameView changerNameView;
    private ChangerNameModel changerNameModel;

    public ChangerNamePresenter(ChangerNameView changerNameView) {
        this.changerNameView = changerNameView;
        this.changerNameModel=new ChangerNameModel();
        attachView(changerNameView);
    }
    public void getData(String uid,String nickname){
        changerNameModel.getData(uid, nickname, new ChangerNameModel.ChangerNameListener() {
            @Override
            public void changerNameBackSuccess(RegesterBean bean) {
                changerNameView.changerNameBackSuccess(bean);
            }

            @Override
            public void changerNameBackFailure(String str) {
                changerNameView.changerNameBackFailure(str);
            }
        });
    }


}
