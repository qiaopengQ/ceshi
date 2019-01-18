package com.example.qiaop.xiangmu_firstnavigation.utils;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class HttpUtils {
    private static HttpUtils httpUtils;
    private HttpUtils(){

    }
    public static HttpUtils getInstance(){
        if (httpUtils ==null){
            synchronized (HttpUtils.class){
                if (httpUtils ==null){
                    httpUtils = new HttpUtils();
                }
            }
        }
        return httpUtils;
    }
    public RequestBody getJsonBody(String json){
        RequestBody requestBody = null;
        if (json!=null){
            requestBody = RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        }
        return requestBody;
    }
    public RequestBody getFormBody(Map<String,Object> map){
        RequestBody requestBody = null;
        if (map!=null){
            for (String s:map.keySet()) {

            }
            /*requestBody=new FormBody.Builder()
                    .add("","");*/

        }
        return requestBody;
    }
}
