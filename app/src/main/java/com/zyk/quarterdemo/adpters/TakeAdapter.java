package com.zyk.quarterdemo.adpters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zyk.quarterdemo.R;
import com.zyk.quarterdemo.beans.TalkBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：张玉轲
 * 时间：2017/11/28
 */

public class TakeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private TalkBean talkBean;

    public TakeAdapter(Context context, TalkBean talkBean) {
        this.context = context;
        this.talkBean = talkBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.talk_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder){
            String icon = talkBean.getData().get(position).getUser().getIcon();
            ((ViewHolder) holder).myImageView.setImageURI(Uri.parse(icon));
            ((ViewHolder) holder).duanziUserName.setText(talkBean.getData().get(position).getUser().getNickname());
            ((ViewHolder) holder).duanziUserTime.setText(talkBean.getData().get(position).getCreateTime());
            ((ViewHolder) holder).duanziUserContent.setText(talkBean.getData().get(position).getContent());
        }
    }

    @Override
    public int getItemCount() {
        return talkBean.getData().size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.my_image_view)
        SimpleDraweeView myImageView;
        @BindView(R.id.duanzi_user_name)
        TextView duanziUserName;
        @BindView(R.id.duanzi_user_time)
        TextView duanziUserTime;
        @BindView(R.id.duanzi_user_content)
        TextView duanziUserContent;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
