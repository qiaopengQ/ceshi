package com.example.qiaop.xiangmu_firstnavigation.presenter;

import com.example.qiaop.xiangmu_firstnavigation.Identifying.RefreshtheloadApi;
import com.example.qiaop.xiangmu_firstnavigation.base.presenter.BasePresenter;
import com.example.qiaop.xiangmu_firstnavigation.contact.RefreshtheloadFace;
import com.example.qiaop.xiangmu_firstnavigation.entity.DownListBean;
import com.example.qiaop.xiangmu_firstnavigation.model.IModel;

import java.util.Map;

public class RefreshtheloadListPresenter<V extends RefreshtheloadFace.ShowView> extends BasePresenter<V> implements RefreshtheloadFace.ShowPresenter,RefreshtheloadFace.ShowModel{
    private IModel iModel = new IModel();
    @Override
    public void setList(DownListBean downListBean, RefreshtheloadApi refreshtheloadApi) {
        if (view!=null){
            view.HideLoadingAnimtion();
            view.showList(downListBean,refreshtheloadApi);
        }
    }

    @Override
    public void ShowError(String error) {
        if (view!=null){
            view.HideLoadingAnimtion();
            view.showError(error);
        }
    }

    @Override
    public void getList(Map<String, Object> map, RefreshtheloadApi refreshtheloadApi) {
        if (view!=null){
            view.ShowLoadingAnimtion();
            iModel.getRefreshtheloadList(map,refreshtheloadApi,this);
        }
    }
}
