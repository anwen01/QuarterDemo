package com.zyk.quarterdemo.presenter;

import android.util.Log;

import com.zyk.quarterdemo.beans.LoginBean;
import com.zyk.quarterdemo.beans.RegesterBean;
import com.zyk.quarterdemo.model.LoginRegesterModel;
import com.zyk.quarterdemo.model.RegesterModel;
import com.zyk.quarterdemo.view.ILoginRegesterView;
import com.zyk.quarterdemo.view.IRegesterView;

/**
 * 作者：张玉轲
 * 时间：2017/11/28
 */

public class RegestPresenter {
    private IRegesterView iRegesterView;
    private RegesterModel regesterModel;

    public RegestPresenter(IRegesterView iRegesterView) {
        this.iRegesterView = iRegesterView;
        this.regesterModel=new RegesterModel();
    }
    public void getData(String regType,String mobile, String password){
        regesterModel.getData(regType, mobile, password, new RegesterModel.RegesterlListener() {
            @Override
            public void regesterBackSuccess(RegesterBean bean) {
                iRegesterView.regesterBackSuccess(bean);
            }

            @Override
            public void regesterBackFailure(String str) {
                iRegesterView.regesterBackFailure(str);
            }
        });
    }


}
