package com.zyk.quarterdemo.base;

/**
 * 作者：张玉轲
 * 时间：2017/11/29
 */

public interface BaseView {
    void showLoading();
    void hideLoading();
    void showFailure(String msg);
}
