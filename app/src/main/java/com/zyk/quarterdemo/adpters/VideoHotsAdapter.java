package com.zyk.quarterdemo.adpters;

import android.content.Context;
import android.databinding.adapters.AbsListViewBindingAdapter;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.luck.picture.lib.tools.ScreenUtils;
import com.zyk.quarterdemo.R;
import com.zyk.quarterdemo.beans.HotListBean;
import com.zyk.quarterdemo.beans.TalkBean;
import com.zyk.quarterdemo.utils.DividerUtils;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_DRAGGING;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_SETTLING;
import static java.security.AccessController.getContext;

/**
 * 作者：张玉轲
 * 时间：2017/12/6
 */

public class VideoHotsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<HotListBean.DataBean> list;

    public VideoHotsAdapter(Context context, List<HotListBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }
    public void isno(boolean flag,List<HotListBean.DataBean> bean){
        if(flag){
            for (HotListBean.DataBean datas : bean){
                list.add(0,datas);
            }
        }else {
            list.addAll(bean);
        }
        System.out.println(" 集合的长度是"+list.size());
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View view = View.inflate(context, R.layout.video_hots_item, null);
        final ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoHotsListener.getlistener(viewHolder.getAdapterPosition());
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder){
            Glide.with(context).asBitmap().load(list.get(position).getCover()).into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
                @Override
                public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                    int imgwidth = resource.getWidth();
                    int imgheight = resource.getHeight();
                    int width = ScreenUtils.getScreenWidth(context)/2;
                    double div = DividerUtils.div(width, imgwidth, 2);
                    int height = (int) (imgheight*(div));
                    ViewGroup.LayoutParams para = ((ViewHolder) holder).videoHotsIcon.getLayoutParams();
                    para.width = width;
                    para.height = height;
                    ((ViewHolder) holder).videoHotsIcon.setImageBitmap(resource);
                }
            });

        }
    }



//
//    //监听滚动来对图片加载进行判断处理
//    public class ImageAutoLoadScrollListener extends RecyclerView.OnScrollListener {
//
//        @Override
//        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//            super.onScrolled(recyclerView, dx, dy);
//        }
//
//        @Override
//        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//            super.onScrollStateChanged(recyclerView, newState);
//            switch (newState) {
//                case SCROLL_STATE_IDLE: // The RecyclerView is not currently scrolling.
//                    //当屏幕停止滚动，加载图片
//                    try {
//                        if (getContext() != null) Glide.with(context).resumeRequests();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    break;
//                case SCROLL_STATE_DRAGGING: // The RecyclerView is currently being dragged by outside input such as user touch input.
//                    //当屏幕滚动且用户使用的触碰或手指还在屏幕上，停止加载图片
//                    try {
//                        if (getContext() != null) Glide.with(context).pauseRequests();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    break;
//                case SCROLL_STATE_SETTLING: // The RecyclerView is currently animating to a final position while not under outside control.
//                    //由于用户的操作，屏幕产生惯性滑动，停止加载图片
//                    try {
//                        if (getContext() != null) Glide.with(context).pauseRequests();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    break;
//            }
//        }
//    }

        public VideoHotsListener videoHotsListener;
    public void VideoHotsListener(VideoHotsListener videoHotsListener){
        this.videoHotsListener=videoHotsListener;
    }
    public interface VideoHotsListener{
        void getlistener(int po);
    }

    @Override
    public int getItemCount() {
        return list.size()==0?0:list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.video_hots_icon)
        ImageView videoHotsIcon;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
