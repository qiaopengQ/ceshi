package com.example.qiaop.xiangmu_firstnavigation.model;

import com.example.qiaop.xiangmu_firstnavigation.Identifying.RefreshtheloadApi;
import com.example.qiaop.xiangmu_firstnavigation.base.http.BaseObserver;
import com.example.qiaop.xiangmu_firstnavigation.contact.ListHeaderImageInterFace;
import com.example.qiaop.xiangmu_firstnavigation.contact.ListNewsInterFace;
import com.example.qiaop.xiangmu_firstnavigation.contact.RefreshtheloadFace;
import com.example.qiaop.xiangmu_firstnavigation.entity.DownListBean;
import com.example.qiaop.xiangmu_firstnavigation.entity.HttpResponse;
import com.example.qiaop.xiangmu_firstnavigation.entity.ListDataBean;
import com.example.qiaop.xiangmu_firstnavigation.entity.ListHeaderImageChannel;
import com.example.qiaop.xiangmu_firstnavigation.http.HttpManager;
import com.example.qiaop.xiangmu_firstnavigation.utils.HttpUtils;
import com.example.qiaop.xiangmu_firstnavigation.utils.RxUtils;

import java.io.File;
import java.util.Map;

public class IModel {

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
    public void getListHeaderImageChanne(String userId, File file, final ListHeaderImageInterFace.ShowModel showModel){
        HttpManager.getInstance().getServer().getImageFile(HttpUtils.getInstance().getFormData(file,userId))
                .compose(RxUtils.<HttpResponse<ListHeaderImageChannel>>rxScheduleThread())
                .compose(RxUtils.<ListHeaderImageChannel>handeResult())
                .subscribe(new BaseObserver<ListHeaderImageChannel>(showModel) {
                    @Override
                    public void onNext(ListHeaderImageChannel value) {
                        showModel.setList(value);
                    }
                });

    }
    public void getRefreshtheloadList(Map<String,Object> map, final RefreshtheloadApi refreshtheloadApi, final RefreshtheloadFace.ShowModel showModel){
        String  url = (String) map.get("url");
        String json = (String) map.get("json");
        String header = (String) map.get("header");
        HttpManager.getInstance().getServer().getRefreshtheload(url,HttpUtils.getInstance().getJsonBody(json),header)
                .compose(RxUtils.<HttpResponse<DownListBean>>rxScheduleThread())
                .compose(RxUtils.<DownListBean>handeResult())
                .subscribe(new BaseObserver<DownListBean>(showModel) {
                    @Override
                    public void onNext(DownListBean value) {
                        showModel.setList(value,refreshtheloadApi);
                    }
                });
    }
}
