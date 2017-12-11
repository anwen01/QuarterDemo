package com.zyk.quarterdemo.view;

import com.zyk.quarterdemo.beans.UserBean;

/**
 * 作者：张玉轲
 * 时间：2017/11/28
 */

public interface IUserInfoView {
    void userInfoBackSuccess(UserBean bean);
    void userinfoBackFailure(String code);
}
