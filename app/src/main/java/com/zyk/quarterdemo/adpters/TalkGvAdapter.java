package com.zyk.quarterdemo.adpters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zyk.quarterdemo.R;

/**
 * 作者：张玉轲
 * 时间：2017/12/4
 */

public class TalkGvAdapter extends BaseAdapter {
    String[] urls;
    Context context;

    public TalkGvAdapter(Context context, String[] urls) {
        this.urls = urls;
        this.context = context;
    }

    @Override
    public int getCount() {
        return urls.length;
    }

    @Override
    public Object getItem(int position) {
        return urls[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder;
        if (null == convertView) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.talk_gv_item, null); //mContext指的是调用的Activtty
            holder.talk_gv_icon = (ImageView) convertView.findViewById(R.id.talk_gv_icon);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        Glide.with(context).load(urls[position]).into(holder.talk_gv_icon);
        //holder.talk_gv_icon.setImageURI(urls[position]);
        return convertView;
    }

    class Holder {
        ImageView talk_gv_icon;
    }
}
