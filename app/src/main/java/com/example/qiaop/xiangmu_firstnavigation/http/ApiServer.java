package com.example.qiaop.xiangmu_firstnavigation.http;

import com.example.qiaop.xiangmu_firstnavigation.entity.HttpResponse;
import com.example.qiaop.xiangmu_firstnavigation.entity.ListDataBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ApiServer {
    //新闻标题
    @POST("news/listNewsChannel")
    @Headers("Content-Type:application/x-www-form-urlencoded")
    Observable<HttpResponse<ListDataBean>> getListNewsBean(@Body RequestBody requestBody);
}
