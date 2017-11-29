package com.zyk.quarterdemo.view;

import com.zyk.quarterdemo.beans.LoginBean;
import com.zyk.quarterdemo.beans.TalkBean;

/**
 * 作者：张玉轲
 * 时间：2017/11/28
 */

public interface ILoginRegesterView {
    void logReBackSuccess(LoginBean bean);
    void logReBackFailure(String code);
}
