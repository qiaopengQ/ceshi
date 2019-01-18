package com.example.qiaop.xiangmu_firstnavigation.model;

import com.example.qiaop.xiangmu_firstnavigation.base.http.BaseObserver;
import com.example.qiaop.xiangmu_firstnavigation.contact.ListNewsInterFace;
import com.example.qiaop.xiangmu_firstnavigation.entity.HttpResponse;
import com.example.qiaop.xiangmu_firstnavigation.entity.ListDataBean;
import com.example.qiaop.xiangmu_firstnavigation.http.HttpManager;
import com.example.qiaop.xiangmu_firstnavigation.utils.HttpUtils;
import com.example.qiaop.xiangmu_firstnavigation.utils.RxUtils;

public class IListNewsModel {

    public void getListNewsChanne(String json, final ListNewsInterFace.ListNewsModel listNewsModel){
        HttpManager.getInstance().getServer().getListNewsBean(HttpUtils.getInstance().getJsonBody(json))
                    .compose(RxUtils.<HttpResponse<ListDataBean>>rxScheduleThread())
                    .compose(RxUtils.<ListDataBean>handeResult())
                    .subscribe(new BaseObserver<ListDataBean>(listNewsModel) {
                        @Override
                        public void onNext(ListDataBean value) {
                            listNewsModel.setListNewsTab(value);
                        }
                    });
    }
}
