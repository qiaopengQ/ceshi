package com.example.qiaop.xiangmu_firstnavigation.http;

import com.example.qiaop.xiangmu_firstnavigation.base.CodeListBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface CodeServer {
    String URL="http://yun918.cn/study/public/index.php/";
    @GET("verify")
    Observable<CodeListBean>getCode();
}
