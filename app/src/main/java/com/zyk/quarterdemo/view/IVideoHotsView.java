package com.zyk.quarterdemo.view;

import com.zyk.quarterdemo.beans.AdviceBean;
import com.zyk.quarterdemo.beans.HotListBean;

/**
 * 作者：张玉轲
 * 时间：2017/11/28
 */

public interface IVideoHotsView {
    void videoHotsBackSuccess(HotListBean bean);
    void videoHotsBackFailure(String code);
}
