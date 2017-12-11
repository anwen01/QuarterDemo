package com.zyk.quarterdemo.adpters;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zyk.quarterdemo.R;
import com.zyk.quarterdemo.beans.RemmTuijianBean;
import com.zyk.quarterdemo.utils.ScreenUtils;
import com.zyk.quarterdemo.view.ZhuanMainView;
import com.zyk.quarterdemo.view.ZhuantuView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：张玉轲
 * 时间：2017/12/8
 */

public class TuijianAdapter extends XRecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<RemmTuijianBean.DataBean> list;

    public TuijianAdapter(Context context, List<RemmTuijianBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View view = View.inflate(context, R.layout.remm_hots_item1, null);
        final ViewHolder viewholder=new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tuijianListener.getlistener(viewholder.getAdapterPosition());
            }
        });
        return viewholder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder){
            String icon = list.get(position).getUser().getIcon();
            if (icon != null) {
                ((TuijianAdapter.ViewHolder) holder).f1ItemHead.setImageURI(Uri.parse(icon));
            } else {
                ((TuijianAdapter.ViewHolder) holder).f1ItemHead.setImageURI("");
            }

            ((ViewHolder) holder).f1ItemName.setText(list.get(position).getUser().getNickname());
            ((ViewHolder) holder).f1ItemTime.setText(list.get(position).getCommentNum()+"");
            ((ViewHolder) holder).f1TvContent.setText(list.get(position).getWorkDesc()!=null?list.get(position).getWorkDesc():"");
            Glide.with(context).load(list.get(position).getCover()).into(((ViewHolder) holder).remm_hots_iv);
            //设置宽
            final int width= ScreenUtils.getScreenWidth(context);

            //自定义
            ((TuijianAdapter.ViewHolder) holder).remmMain.setonZhuanMainListener(new ZhuanMainView.OnZhuanMainListener() {
                @Override
                public void onListener(boolean flag) {
                    if (flag) {
                        //open
                        list.get(position).setFlag(flag);

                        for (int i = 0; i < list.size(); i++) {
                            if (i != position) {
                                list.get(i).setFlag(false);
                            }
                        }
                        for (int i = 0; i < list.size(); i++) {
                            System.out.println("======javabean.size" + list.get(position).isFlag());
                        }
                        if (list.get(position).isFlag() == true) {
                            ((ViewHolder) holder).remmZhuanItem.setVisibility(View.VISIBLE);
                            ((TuijianAdapter.ViewHolder) holder).remmZhuanItem2.setVisibility(View.VISIBLE);
                            ((TuijianAdapter.ViewHolder) holder).remmZhuanItem3.setVisibility(View.VISIBLE);

                            ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(((ViewHolder) holder).remmZhuanItem, "translationX", -(width*0.1f));
                            ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(((ViewHolder) holder).remmZhuanItem, "alpha", 0f, 1f);
                            AnimatorSet set = new AnimatorSet();
                            set.play(objectAnimator1).with(objectAnimator2);
                            set.setDuration(1000);
                            set.start();


                            ObjectAnimator tran = ObjectAnimator.ofFloat(((TuijianAdapter.ViewHolder) holder).remmZhuanItem2, "translationX", -(width*0.2f));
                            ObjectAnimator alpha = ObjectAnimator.ofFloat(((TuijianAdapter.ViewHolder) holder).remmZhuanItem2, "alpha", 0f, 1f);
                            AnimatorSet set2 = new AnimatorSet();
                            set2.play(tran).with(alpha);
                            set2.setDuration(1000);
                            set2.start();

                            ObjectAnimator tran3 = ObjectAnimator.ofFloat(((TuijianAdapter.ViewHolder) holder).remmZhuanItem3, "translationX",-(width*0.3f));
                            ObjectAnimator alpha3 = ObjectAnimator.ofFloat(((TuijianAdapter.ViewHolder) holder).remmZhuanItem3, "alpha", 0f, 1f);
                            AnimatorSet set3 = new AnimatorSet();
                            set3.play(tran3).with(alpha3);
                            set3.setDuration(1000);
                            set3.start();

                        }

                    } else {
                        //关闭
                        list.get(position).setFlag(flag);

                        for (int i = 0; i < list.size(); i++) {
                            if (i != position) {
                                list.get(i).setFlag(false);
                            }
                        }
                        for (int i = 0; i < list.size(); i++) {
                            System.out.println("=====关闭===javabean.size" + list.get(position).isFlag());
                        }
                        ObjectAnimator pahcktran = ObjectAnimator.ofFloat(((TuijianAdapter.ViewHolder) holder).remmZhuanItem, "translationX", 0f);
                        ObjectAnimator pachalpha = ObjectAnimator.ofFloat(((TuijianAdapter.ViewHolder) holder).remmZhuanItem, "alpha", 1f, 0f);
                        AnimatorSet set = new AnimatorSet();
                        set.play(pahcktran).with(pachalpha);
                        set.setDuration(1000);
                        set.start();


                        ObjectAnimator tran = ObjectAnimator.ofFloat(((TuijianAdapter.ViewHolder) holder).remmZhuanItem2, "translationX", 0f);
                        ObjectAnimator alpha = ObjectAnimator.ofFloat(((TuijianAdapter.ViewHolder) holder).remmZhuanItem2, "alpha", 1f, 0f);
                        AnimatorSet set2 = new AnimatorSet();
                        set2.play(tran).with(alpha);
                        set2.setDuration(1000);
                        set2.start();

                        ObjectAnimator tran3 = ObjectAnimator.ofFloat(((TuijianAdapter.ViewHolder) holder).remmZhuanItem3, "translationX", 0f);
                        ObjectAnimator alpha3 = ObjectAnimator.ofFloat(((TuijianAdapter.ViewHolder) holder).remmZhuanItem3, "alpha", 1f, 0f);
                        AnimatorSet set3 = new AnimatorSet();
                        set3.play(tran3).with(alpha3);
                        set3.setDuration(1000);
                        set3.start();
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public TuijianListener tuijianListener;
    public void tideoHotsListener(TuijianListener tuijianListener){
        this.tuijianListener=tuijianListener;
    }
    public interface TuijianListener{
        void getlistener(int po);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.f1_item_head)
        SimpleDraweeView f1ItemHead;
        @BindView(R.id.f1_item_name)
        TextView f1ItemName;
        @BindView(R.id.f1_item_time)
        TextView f1ItemTime;
        @BindView(R.id.remm_hots_iv)
        ImageView remm_hots_iv;
        @BindView(R.id.remm_zhuan_item)
        ZhuantuView remmZhuanItem;
        @BindView(R.id.remm_zhuan_item2)
        ZhuantuView remmZhuanItem2;
        @BindView(R.id.remm_zhuan_item3)
        ZhuantuView remmZhuanItem3;
        @BindView(R.id.remm_main)
        ZhuanMainView remmMain;
        @BindView(R.id.rl_frag1_item)
        LinearLayout rlFrag1Item;
        @BindView(R.id.f1_tv_content)
        TextView f1TvContent;
        @BindView(R.id.iv_img1)
        ImageView ivImg1;
        @BindView(R.id.tv_like1)
        TextView tvLike1;
        @BindView(R.id.iv_img2)
        ImageView ivImg2;
        @BindView(R.id.tv_like2)
        TextView tvLike2;
        @BindView(R.id.iv_img3)
        ImageView ivImg3;
        @BindView(R.id.tv_like3)
        TextView tvLike3;
        @BindView(R.id.iv_img4)
        ImageView ivImg4;
        @BindView(R.id.tv_like4)
        TextView tvLike4;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
