package com.zyk.quarterdemo.presenter;

import com.zyk.quarterdemo.base.BasePresenter;
import com.zyk.quarterdemo.beans.AdviceBean;
import com.zyk.quarterdemo.beans.RemmTuijianBean;
import com.zyk.quarterdemo.model.AdviceModel;
import com.zyk.quarterdemo.view.IAdviceView;

/**
 * 作者：张玉轲
 * 时间：2017/11/28
 */

public class AdvicePresenter extends BasePresenter<IAdviceView> {
    private IAdviceView iAdviceView;
    private AdviceModel adviceModel;

    public AdvicePresenter(IAdviceView iAdviceView) {
        this.iAdviceView = iAdviceView;
        this.adviceModel=new AdviceModel();
        attachView(iAdviceView);
    }

    public void getData(){
        adviceModel.getData(new AdviceModel.AdviceModelListener() {
            @Override
            public void adviceBackSuccess(AdviceBean bean) {
                iAdviceView.adviceBackSuccess(bean);
            }

            @Override
            public void adviceBackFailure(String str) {
                iAdviceView.adviceBackFailure(str);
            }
        });
    }

    public void getData(String type, String page){
        adviceModel.getData(type, page, new AdviceModel.TuijianListener() {
            @Override
            public void tuijanBackSuccess(RemmTuijianBean bean) {
                iAdviceView.tuijianBackSuccess(bean);
            }

            @Override
            public void tuijianBackFailure(String str) {
                iAdviceView.tuijianBackFailure(str);
            }
        });
    }

}
