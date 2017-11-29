package com.zyk.quarterdemo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zyk.quarterdemo.R;
import com.zyk.quarterdemo.adpters.TakeAdapter;
import com.zyk.quarterdemo.beans.TalkBean;
import com.zyk.quarterdemo.presenter.TalkPresenter;
import com.zyk.quarterdemo.view.ITalkView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：张玉轲
 * 时间：2017/11/7
 */

public class TalkFragment extends Fragment implements ITalkView{
    @BindView(R.id.recycler)
    RecyclerView recycler;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.talk_fragment, null);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        TalkPresenter talkPresenter = new TalkPresenter(this);
        talkPresenter.getData("3");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void talkBackSuccess(TalkBean bean) {
        recycler.setAdapter(new TakeAdapter(getActivity(),bean));
    }

    @Override
    public void talkBackFailure(String code) {

    }
}
