package com.example.qiaop.xiangmu_firstnavigation.contact;

import com.example.qiaop.xiangmu_firstnavigation.Identifying.RefreshtheloadApi;
import com.example.qiaop.xiangmu_firstnavigation.base.http.HttpFinishCallBack;
import com.example.qiaop.xiangmu_firstnavigation.base.view.BaseView;
import com.example.qiaop.xiangmu_firstnavigation.entity.DownListBean;

import java.util.Map;

public interface RefreshtheloadFace {
    interface ShowView extends BaseView {
        void showList(DownListBean downListBean, RefreshtheloadApi refreshtheloadApi);
        void showError(String error);
    }
    interface ShowModel extends HttpFinishCallBack {
        void setList(DownListBean downListBean,RefreshtheloadApi refreshtheloadApi);
    }
    interface ShowPresenter{
        void getList(Map<String,Object> map,RefreshtheloadApi refreshtheloadApi);
    }
}
