package com.zyk.quarterdemo.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zyk.quarterdemo.R;
import com.zyk.quarterdemo.adpters.TakeAdapter;
import com.zyk.quarterdemo.base.BaseFragment;
import com.zyk.quarterdemo.beans.TalkBean;
import com.zyk.quarterdemo.presenter.TalkPresenter;
import com.zyk.quarterdemo.utils.DividerItemDecoration;
import com.zyk.quarterdemo.view.ITalkView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者：张玉轲
 * 时间：2017/11/7
 */

public class TalkFragment extends BaseFragment<ITalkView,TalkPresenter> implements ITalkView{
    @BindView(R.id.recycler)
    XRecyclerView recycler;
    private TalkPresenter talkPresenter;
    private int page=1;
    private TakeAdapter takeAdapter;
    private List<TalkBean.DataBean> bigList;
    private String token;

    @Override
    protected int setLayout() {
        return R.layout.talk_fragment;
    }

    @Override
    protected void createpresenter() {
        //引用Presenter层
        talkPresenter = new TalkPresenter(this);


    }

    @Override
    protected void processLogic() {
        bigList = new ArrayList<>();
        //逻辑代码
        talkPresenter.getData(page +"");

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);
    }


    @Override
    public void talkBackSuccess(final TalkBean bean) {
        if (bean.getData()!=null) {
            if (takeAdapter == null) {
                bigList.addAll(bean.getData());
                takeAdapter = new TakeAdapter(getActivity(), bigList);
                recycler.setAdapter(takeAdapter);
            } else {
                takeAdapter.notifyDataSetChanged();
            }
        }
        recycler.setPullRefreshEnabled(true);
        recycler.setLoadingMoreEnabled(true);
        recycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                bigList.clear();
                talkPresenter.getData("1");
                takeAdapter.isno(true,bean.getData());
                recycler.refreshComplete();
            }

            @Override
            public void onLoadMore() {

                talkPresenter.getData(page++ +"");
                takeAdapter.isno(false,bean.getData());
                recycler.loadMoreComplete();
            }
        });

        //万能下划线
        recycler.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL, Color.BLACK,1));

    }

    @Override
    public void talkBackFailure(String code) {
        System.out.println(code);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (talkPresenter.isAttachView()){
            talkPresenter.detach();
        }
    }
}
