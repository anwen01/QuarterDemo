package com.zyk.quarterdemo.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.xbanner.XBanner;
import com.zyk.quarterdemo.R;
import com.zyk.quarterdemo.adpters.TuijianAdapter;
import com.zyk.quarterdemo.base.BaseFragment;
import com.zyk.quarterdemo.beans.AdviceBean;
import com.zyk.quarterdemo.beans.RemmTuijianBean;
import com.zyk.quarterdemo.presenter.AdvicePresenter;
import com.zyk.quarterdemo.view.IAdviceView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * 作者：张玉轲
 * 时间：2017/11/7
 */

public class HotspotFragment extends BaseFragment<IAdviceView, AdvicePresenter> implements IAdviceView {

    @BindView(R.id.hots_xrv)
    XRecyclerView hotsXrv;
    Unbinder unbinder;
    private AdvicePresenter advicePresenter;
    private XBanner xBanner;
    private List<String> list_xbanner;

    @Override
    protected int setLayout() {
        return R.layout.hotspot_fragment;
    }

    //创建Presenter
    @Override
    protected void createpresenter() {
        advicePresenter = new AdvicePresenter(this);
    }

    //主要代码
    @Override
    protected void processLogic() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        hotsXrv.setLayoutManager(layoutManager);
        //轮播图
        View view = View.inflate(getActivity(), R.layout.remm_hots_item, null);
        xBanner = view.findViewById(R.id.remm_hots_banner);
        hotsXrv.addHeaderView(view);
        initXBanner();
        //调用方法
        advicePresenter.getData("1","1");

    }
    @Override
    public void adviceBackSuccess(AdviceBean bean) {
    }

    private void initXBanner() {

        list_xbanner = new ArrayList<>();
        list_xbanner.add("https://www.zhaoapi.cn/images/quarter/ad1.png");
        list_xbanner.add("https://www.zhaoapi.cn/images/quarter/ad2.png");
        list_xbanner.add("https://www.zhaoapi.cn/images/quarter/ad3.png");
        list_xbanner.add("https://www.zhaoapi.cn/images/quarter/ad4.png");
        xBanner.setData(list_xbanner,null);
        xBanner.setPoinstPosition(XBanner.RIGHT);
        xBanner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, View view, int position)
            {
                Glide.with(getActivity()).load(list_xbanner.get(position)).into((ImageView) view);
            }
        });
    }

    @Override
    public void adviceBackFailure(String code) {
    }
    @Override
    public void tuijianBackSuccess(RemmTuijianBean bean) {
        List<RemmTuijianBean.DataBean> data = bean.getData();
        hotsXrv.setAdapter(new TuijianAdapter(getActivity(),bean.getData()));

    }
    @Override
    public void tuijianBackFailure(String code) {
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (advicePresenter.isAttachView()){
            advicePresenter.detach();
        }
    }
}
