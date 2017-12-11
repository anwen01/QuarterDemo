package com.zyk.quarterdemo.view;

import com.zyk.quarterdemo.beans.RemmTuijianBean;
import com.zyk.quarterdemo.beans.TalkBean;

/**
 * 作者：张玉轲
 * 时间：2017/11/28
 */

public interface IRemmTuijianView {
     void tuijianBackSuccess(RemmTuijianBean bean);
     void tuijianBackFailure(String code);
}
