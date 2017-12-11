package com.zyk.quarterdemo.adpters;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zyk.quarterdemo.R;
import com.zyk.quarterdemo.beans.HotListBean;
import com.zyk.quarterdemo.beans.TalkBean;
import com.zyk.quarterdemo.utils.ScreenUtils;
import com.zyk.quarterdemo.view.ZhuanMainView;
import com.zyk.quarterdemo.view.ZhuantuView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：张玉轲
 * 时间：2017/11/28
 */

public class TakeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<TalkBean.DataBean> list;

    public TakeAdapter(Context context, List<TalkBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    public void isno(boolean flag,List<TalkBean.DataBean>  bean){
        if(flag){
            for (TalkBean.DataBean datas : bean){
                list.add(0,datas);
            }
        }else {
            list.addAll(bean);
        }
        System.out.println(" 集合的长度是"+list.size());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.talk_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            String icon = list.get(position).getUser().getIcon();
            Glide.with(context).load(icon).into(((ViewHolder) holder).myImageView);
//            if (icon != null) {
//                ((ViewHolder) holder).myImageView.setImageURI(Uri.parse(icon));
//            } else {
//                ((ViewHolder) holder).myImageView.setImageURI("");
//            }
            if (list.get(position).getImgUrls()!=null){
                String[] uri=list.get(position).getImgUrls().split("\\|");
                ((ViewHolder) holder).talkGv.setAdapter(new TalkGvAdapter(context,uri));
            }else{
                ((ViewHolder) holder).talkGv.setVisibility(View.GONE);
            }

            ((ViewHolder) holder).duanziUserName.setText(list.get(position).getUser().getNickname());
            ((ViewHolder) holder).duanziUserTime.setText(list.get(position).getCreateTime());
            ((ViewHolder) holder).duanziUserContent.setText(list.get(position).getContent());
            //ViewHolder停止复用
            holder.setIsRecyclable(false);
            final int width= ScreenUtils.getScreenWidth(context);

            //自定义
            ((ViewHolder) holder).zhuanMain.setonZhuanMainListener(new ZhuanMainView.OnZhuanMainListener() {
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
                            ((ViewHolder) holder).talkZhuanItem.setVisibility(View.VISIBLE);
                            ((ViewHolder) holder).talkZhuanItem2.setVisibility(View.VISIBLE);
                            ((ViewHolder) holder).talkZhuanItem3.setVisibility(View.VISIBLE);

                            ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(((ViewHolder) holder).talkZhuanItem, "translationX", -(width*0.1f));
                            ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(((ViewHolder) holder).talkZhuanItem, "alpha", 0f, 1f);
                            AnimatorSet set = new AnimatorSet();
                            set.play(objectAnimator1).with(objectAnimator2);
                            set.setDuration(1000);
                            set.start();


                            ObjectAnimator tran = ObjectAnimator.ofFloat(((ViewHolder) holder).talkZhuanItem2, "translationX", -(width*0.2f));
                            ObjectAnimator alpha = ObjectAnimator.ofFloat(((ViewHolder) holder).talkZhuanItem2, "alpha", 0f, 1f);
                            AnimatorSet set2 = new AnimatorSet();
                            set2.play(tran).with(alpha);
                            set2.setDuration(1000);
                            set2.start();

                            ObjectAnimator tran3 = ObjectAnimator.ofFloat(((ViewHolder) holder).talkZhuanItem3, "translationX",-(width*0.3f));
                            ObjectAnimator alpha3 = ObjectAnimator.ofFloat(((ViewHolder) holder).talkZhuanItem3, "alpha", 0f, 1f);
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
                        ObjectAnimator pahcktran = ObjectAnimator.ofFloat(((ViewHolder) holder).talkZhuanItem, "translationX", 0f);
                        ObjectAnimator pachalpha = ObjectAnimator.ofFloat(((ViewHolder) holder).talkZhuanItem, "alpha", 1f, 0f);
                        AnimatorSet set = new AnimatorSet();
                        set.play(pahcktran).with(pachalpha);
                        set.setDuration(1000);
                        set.start();


                        ObjectAnimator tran = ObjectAnimator.ofFloat(((ViewHolder) holder).talkZhuanItem2, "translationX", 0f);
                        ObjectAnimator alpha = ObjectAnimator.ofFloat(((ViewHolder) holder).talkZhuanItem2, "alpha", 1f, 0f);
                        AnimatorSet set2 = new AnimatorSet();
                        set2.play(tran).with(alpha);
                        set2.setDuration(1000);
                        set2.start();

                        ObjectAnimator tran3 = ObjectAnimator.ofFloat(((ViewHolder) holder).talkZhuanItem3, "translationX", 0f);
                        ObjectAnimator alpha3 = ObjectAnimator.ofFloat(((ViewHolder) holder).talkZhuanItem3, "alpha", 1f, 0f);
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
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.my_image_view)
        ImageView myImageView;
        @BindView(R.id.duanzi_user_name)
        TextView duanziUserName;
        @BindView(R.id.duanzi_user_time)
        TextView duanziUserTime;
        @BindView(R.id.talk_zhuan_item)
        ZhuantuView talkZhuanItem;
        @BindView(R.id.talk_zhuan_item2)
        ZhuantuView talkZhuanItem2;
        @BindView(R.id.talk_zhuan_item3)
        ZhuantuView talkZhuanItem3;
        @BindView(R.id.zhuan_main)
        ZhuanMainView zhuanMain;
        @BindView(R.id.duanzi_user_content)
        TextView duanziUserContent;
        @BindView(R.id.talk_gv)
        GridView talkGv;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
