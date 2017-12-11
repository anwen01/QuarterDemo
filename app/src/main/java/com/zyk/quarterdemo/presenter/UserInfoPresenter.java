package com.zyk.quarterdemo.presenter;

import com.zyk.quarterdemo.base.BasePresenter;
import com.zyk.quarterdemo.beans.RegesterBean;
import com.zyk.quarterdemo.beans.UserBean;
import com.zyk.quarterdemo.model.FbTalkModel;
import com.zyk.quarterdemo.model.UserInfoModel;
import com.zyk.quarterdemo.view.FbTalkView;
import com.zyk.quarterdemo.view.IUserInfoView;

/**
 * 作者：张玉轲
 * 时间：2017/11/28
 */

public class UserInfoPresenter extends BasePresenter<IUserInfoView> {
    private IUserInfoView iUserInfoView;
    private UserInfoModel userInfoModel;

    public UserInfoPresenter(IUserInfoView iUserInfoView) {
        this.iUserInfoView = iUserInfoView;
        this.userInfoModel=new UserInfoModel();
        attachView(iUserInfoView);
    }
    public void getData(String uid){
        userInfoModel.getData(uid, new UserInfoModel.UserInfolListener() {
            @Override
            public void userInfoBackSuccess(UserBean bean) {
                iUserInfoView.userInfoBackSuccess(bean);
            }

            @Override
            public void userInfoBackFailure(String str) {
                iUserInfoView.userinfoBackFailure(str);
            }
        });
    }


}
