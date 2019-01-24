package com.example.qiaop.xiangmu_firstnavigation.contact;

import com.example.qiaop.xiangmu_firstnavigation.base.http.HttpFinishCallBack;
import com.example.qiaop.xiangmu_firstnavigation.base.view.BaseView;
import com.example.qiaop.xiangmu_firstnavigation.entity.ListHeaderImageChannel;

import java.io.File;
import java.util.Map;

public interface ListHeaderImageInterFace {
    interface ShowView extends BaseView{
        void showList(ListHeaderImageChannel listHeaderImageChannel);
        void showError(String error);
    }
    interface ShowModel extends HttpFinishCallBack {
        void setList(ListHeaderImageChannel listHeaderImageChannel);
    }
    interface ShowPresenter<T>{
        void getList(String userId, File file);
    }
}
