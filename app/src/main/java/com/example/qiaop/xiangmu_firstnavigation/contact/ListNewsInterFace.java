package com.example.qiaop.xiangmu_firstnavigation.contact;

import com.example.qiaop.xiangmu_firstnavigation.base.http.HttpFinishCallBack;
import com.example.qiaop.xiangmu_firstnavigation.base.view.BaseView;
import com.example.qiaop.xiangmu_firstnavigation.entity.ListDataBean;

import java.io.File;

public interface ListNewsInterFace {
    interface ListNewsView extends BaseView {
        void showListNewsTab(ListDataBean listDataBean);
        void showError(String error);
    }
    public interface ListNewsModel extends HttpFinishCallBack {
        void setListNewsTab(ListDataBean listNewsTab);
    }
    interface ListNewsPresenter{
        void getListNewsTab(String json);
    }
}
