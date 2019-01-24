package com.example.qiaop.xiangmu_firstnavigation.presenter;

import com.example.qiaop.xiangmu_firstnavigation.base.presenter.BasePresenter;
import com.example.qiaop.xiangmu_firstnavigation.contact.ListNewsInterFace;
import com.example.qiaop.xiangmu_firstnavigation.entity.ListDataBean;
import com.example.qiaop.xiangmu_firstnavigation.model.IListNewsModel;
import com.example.qiaop.xiangmu_firstnavigation.model.IModel;

public class IListNewsPresenter<V extends ListNewsInterFace.ListNewsView> extends BasePresenter<V> implements ListNewsInterFace.ListNewsPresenter,ListNewsInterFace.ListNewsModel{
    private IModel iModel = new IModel();
    @Override
    public void getListNewsTab(String json) {
        if (view!=null){
            view.ShowLoadingAnimtion();
            iModel.getListNewsChanne(json,this);
        }
    }

    @Override
    public void setListNewsTab(ListDataBean listNewsTab) {
        if (view!=null){
            view.HideLoadingAnimtion();
            view.showListNewsTab(listNewsTab);
        }
    }

    @Override
    public void ShowError(String error) {
        if (view!=null){
            view.HideLoadingAnimtion();
            view.showError(error);
        }
    }
}
