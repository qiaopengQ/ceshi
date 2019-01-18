package com.example.qiaop.xiangmu_firstnavigation.base.http;

import android.util.Log;

import com.example.qiaop.xiangmu_firstnavigation.http.ApiException;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

public abstract class BaseObserver<T> implements Observer<T> {
    private HttpFinishCallBack httpFinishCallBack;

    public BaseObserver(HttpFinishCallBack httpFinishCallBack) {
        this.httpFinishCallBack = httpFinishCallBack;
    }

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Override
    public void onSubscribe(Disposable d) {
        compositeDisposable.add(d);
    }

    @Override
    public void onError(Throwable e) {
        Log.e("Qiaop",e.getMessage());
        String error=null;
        if (e instanceof ApiException){
            ApiException apiException = (ApiException) e;
            switch (apiException.getCode()){
                case 0:
                    error = "成功";
                    break;
                case 1001:
                    error = "服务器内部错误";
                    break;
                case 2001:
                    error = "验证码错误";
                    break;
                case 2002:
                    error = "数据不存在";
                    break;
                case 2003:
                    error = "非法手机号";
                    break;
                case 2004:
                    error = "验证码一分钟内不能重复发送";
                    break;
                case 2005:
                    error = "短信功能业务限流";
                    break;
                case 2006:
                    error = "验证码发送失败";
                    break;
                case 2100:
                    error = "上传文件为空";
                    break;
                case 2101:
                    error = "文件大小超出限制";
                    break;
                case 2102:
                    error = "非法文件格式";
                    break;
                case 2103:
                    error = "上传文件失败";
                    break;
            }
        }else if (e instanceof HttpException){
            error = "网络请求";
        }

        if (httpFinishCallBack!=null){
            httpFinishCallBack.ShowError(error);
        }
    }

    @Override
    public void onComplete() {
        if (compositeDisposable!=null){
            compositeDisposable.clear();
        }
    }
}
