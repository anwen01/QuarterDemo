package com.zyk.quarterdemo.view;

import com.zyk.quarterdemo.beans.TalkBean;

/**
 * 作者：张玉轲
 * 时间：2017/11/28
 */

public interface ITalkView {
    public void talkBackSuccess(TalkBean bean);
    public void talkBackFailure(String code);
}
