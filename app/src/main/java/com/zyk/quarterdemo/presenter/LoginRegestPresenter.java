package com.zyk.quarterdemo.presenter;

import android.util.Log;

import com.zyk.quarterdemo.beans.LoginBean;
import com.zyk.quarterdemo.beans.TalkBean;
import com.zyk.quarterdemo.model.LoginRegesterModel;
import com.zyk.quarterdemo.model.TalkModel;
import com.zyk.quarterdemo.view.ILoginRegesterView;
import com.zyk.quarterdemo.view.ITalkView;

/**
 * 作者：张玉轲
 * 时间：2017/11/28
 */

public class LoginRegestPresenter {
    private ILoginRegesterView iLoginRegesterView;
    private LoginRegesterModel loginRegesterModel;

    public LoginRegestPresenter(ILoginRegesterView iLoginRegesterView) {
        this.iLoginRegesterView = iLoginRegesterView;
        this.loginRegesterModel=new LoginRegesterModel();
    }
    public void getData(String mobile, String password){
        loginRegesterModel.getData(mobile, password, new LoginRegesterModel.LoginRegesterlListener() {
            @Override
            public void logReBackSuccess(LoginBean bean) {
                iLoginRegesterView.logReBackSuccess(bean);
                Log.i("", "logReBackSuccess: "+bean.getMsg());
            }

            @Override
            public void logReBackFailure(String str) {
                iLoginRegesterView.logReBackFailure(str);
            }
        });
    }


}
