package com.zyk.quarterdemo.view;

import com.zyk.quarterdemo.beans.RegesterBean;

/**
 * 作者：张玉轲
 * 时间：2017/11/28
 */

public interface IUpHeaderView {
    void upHeaderBackSuccess(RegesterBean bean);
    void upHeaderBackFailure(String code);
}
