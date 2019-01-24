package com.example.qiaop.xiangmu_firstnavigation.presenter;

import com.example.qiaop.xiangmu_firstnavigation.base.presenter.BasePresenter;
import com.example.qiaop.xiangmu_firstnavigation.contact.ListHeaderImageInterFace;
import com.example.qiaop.xiangmu_firstnavigation.entity.ListHeaderImageChannel;
import com.example.qiaop.xiangmu_firstnavigation.model.IListHeaderImageModel;
import com.example.qiaop.xiangmu_firstnavigation.model.IModel;

import java.io.File;

public class IHeaderImagePresenter<V extends ListHeaderImageInterFace.ShowView> extends BasePresenter<V> implements ListHeaderImageInterFace.ShowPresenter, ListHeaderImageInterFace.ShowModel {
    private IModel iModel = new IModel();
    @Override
    public void getList(String userId, File file) {
        if (view!=null){
            view.ShowLoadingAnimtion();
            iModel.getListHeaderImageChanne(userId,file,this);
        }
    }

    @Override
    public void setList(ListHeaderImageChannel listHeaderImageChannel) {
        if (view!=null){
            view.HideLoadingAnimtion();
            view.showList(listHeaderImageChannel);
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
