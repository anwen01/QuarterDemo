package com.zyk.quarterdemo.presenter;

import com.zyk.quarterdemo.beans.TalkBean;
import com.zyk.quarterdemo.model.TalkModel;
import com.zyk.quarterdemo.view.ITalkView;

/**
 * 作者：张玉轲
 * 时间：2017/11/28
 */

public class TalkPresenter {
    private ITalkView iTalkView;
    private TalkModel talkModel;

    public TalkPresenter(ITalkView iTalkView) {
        this.iTalkView = iTalkView;
        this.talkModel=new TalkModel();
    }
    public void getData(String page){
        talkModel.getData(page,new TalkModel.TalkModelListener() {
            @Override
            public void talkBackSuccess(TalkBean bean) {
                iTalkView.talkBackSuccess(bean);
            }

            @Override
            public void talkBackFailure(String str) {
                iTalkView.talkBackFailure(str);
            }
        });
    }


}
