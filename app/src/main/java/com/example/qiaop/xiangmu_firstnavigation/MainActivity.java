package com.example.qiaop.xiangmu_firstnavigation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;

import com.example.qiaop.xiangmu_firstnavigation.base.activity.BaseActivity;
import com.example.qiaop.xiangmu_firstnavigation.contact.ListNewsInterFace;
import com.example.qiaop.xiangmu_firstnavigation.entity.ListDataBean;
import com.example.qiaop.xiangmu_firstnavigation.presenter.IListNewsPresenter;

public class MainActivity extends BaseActivity<ListNewsInterFace.ListNewsView,IListNewsPresenter<ListNewsInterFace.ListNewsView>> implements ListNewsInterFace.ListNewsView{

    @Override
    protected void initEventAndData() {
        mPresenter.getListNewsTab("");
    }

    @Override
    protected IListNewsPresenter<ListNewsInterFace.ListNewsView> createPresenter() {
        return new IListNewsPresenter<>();
    }
    @Override
    protected int createLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void ShowLoadingAnimtion() {
       // ShowLoadingAnimtion();
        dialog.show();
    }

    @Override
    public void HideLoadingAnimtion() {
       // HideLoadingAnimtion();
        dialog.hide();
    }

    @Override
    public void showListNewsTab(ListDataBean listDataBean) {
        Log.e("MainActivity", "listDataBean:" + listDataBean.toString());
    }

    @Override
    public void showError(String error) {

    }
}
