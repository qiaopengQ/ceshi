package com.example.qiaop.xiangmu_firstnavigation.base.presenter;

import java.lang.ref.WeakReference;

public class BasePresenter<V> {


    private WeakReference<V> weakReference;
    public V view;

    public void attachView(V v){
        weakReference = new WeakReference<>(v);
        if (weakReference!=null){
            view = weakReference.get();
        }
    }
    public void destoryView(){
        if (weakReference!=null){
            weakReference.clear();
        }
    }

}
