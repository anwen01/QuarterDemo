package com.zyk.quarterdemo.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * 作者：张玉轲
 * 时间：2017/11/29
 */

public class BasePresenter<V>  {
    public V mView;
    private Reference<V> mReference;
    //关联
    public void attachView(V mView){
        mReference=new WeakReference<V>(mView);
    }
    //解绑
    public void detach(){
        if (isAttachView()){
            mReference.clear();
            this.mView=null;
        }
    }
    //判断是否关联
    public boolean isAttachView(){
        return mReference!=null&&mReference.get()!=null?true:false;
    }
    //获取view的方法
    public V getmView(){
        return isAttachView()?mReference.get():null;
    }
}
