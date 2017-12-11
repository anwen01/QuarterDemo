package com.zyk.quarterdemo.fragments;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zyk.quarterdemo.R;
import com.zyk.quarterdemo.VideoHotsDetailsActivity;
import com.zyk.quarterdemo.adpters.VideoHotsAdapter;
import com.zyk.quarterdemo.base.BaseFragment;
import com.zyk.quarterdemo.beans.HotListBean;
import com.zyk.quarterdemo.presenter.VideoHotsPresenter;
import com.zyk.quarterdemo.utils.SpacesItemDecoration;
import com.zyk.quarterdemo.view.IVideoHotsView;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.Unbinder;

/**
 * 作者：张玉轲
 * 时间：2017/11/7
 */

public class VideoHotspotFragment extends BaseFragment<IVideoHotsView, VideoHotsPresenter> implements IVideoHotsView {
    @BindView(R.id.video_hots_xrv)
    XRecyclerView videoHotsXrv;
    private VideoHotsPresenter videoHotsPresenter;
    private int page=1;
    private VideoHotsAdapter videoHotsAdapter;
    private List<HotListBean.DataBean> bigList = new ArrayList<>();
    private boolean falg=true;

    @Override
    protected int setLayout() {
        return R.layout.video_hotspot_fragment;
    }

    @Override
    protected void createpresenter() {
        videoHotsPresenter = new VideoHotsPresenter(this);
    }

    @Override
    protected void processLogic() {
        videoHotsPresenter.getData(page+"");
        final StaggeredGridLayoutManager manager= new StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL);
        //防止图片动
        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        videoHotsXrv.setLayoutManager(manager);
    }

    @Override
    public void videoHotsBackSuccess(final HotListBean bean) {
         List<HotListBean.DataBean> data = bean.getData();
         bigList.addAll(data);
        if (videoHotsAdapter==null){
            videoHotsAdapter = new VideoHotsAdapter(getActivity(),bigList);
            videoHotsXrv.setAdapter(videoHotsAdapter);
        }else{
            videoHotsAdapter.notifyDataSetChanged();
        }

        if (falg){
            //设置瀑布流的间距
            int spacingInPixels = 8;
            videoHotsXrv.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
            falg=false;
        }

        videoHotsXrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                bigList.clear();
                videoHotsPresenter.getData("1");
                videoHotsAdapter.isno(true,bean.getData());
                videoHotsXrv.refreshComplete();
            }

            @Override
            public void onLoadMore() {

                videoHotsPresenter.getData(page++ +"");
                videoHotsAdapter.isno(false,bean.getData());
                videoHotsXrv.loadMoreComplete();
            }
        });

        videoHotsAdapter.VideoHotsListener(new VideoHotsAdapter.VideoHotsListener() {
            @Override
            public void getlistener(int po) {
                Intent intent = new Intent(getActivity(), VideoHotsDetailsActivity.class);
                String videoUrl = bigList.get(po-1).getVideoUrl().replace("https://www.zhaoapi.cn","http://120.27.23.105");
                System.out.println("=========po:"+po);
                intent.putExtra("path",videoUrl);
                intent.putExtra("cover",bigList.get(po).getCover());
                startActivity(intent);
            }
        });

    }

    @Override
    public void videoHotsBackFailure(String code) {
        System.out.println(code);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (videoHotsPresenter.isAttachView()){
            videoHotsPresenter.detach();
        }
    }
}
