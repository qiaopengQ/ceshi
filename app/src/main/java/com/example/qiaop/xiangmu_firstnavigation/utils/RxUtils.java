package com.example.qiaop.xiangmu_firstnavigation.utils;

import com.example.qiaop.xiangmu_firstnavigation.entity.HttpResponse;
import com.example.qiaop.xiangmu_firstnavigation.http.ApiException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxUtils {
    public static <T>ObservableTransformer<T,T>rxScheduleThread(){
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
    public static <T>ObservableTransformer<HttpResponse<T>,T>handeResult(){
        return new ObservableTransformer<HttpResponse<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<HttpResponse<T>> upstream) {
                return upstream.flatMap(new Function<HttpResponse<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(HttpResponse<T> tHttpResponse) throws Exception {
                        if (tHttpResponse.getCode()==0){
                            return createData(tHttpResponse.getData());
                        }else {
                            return Observable.error(new ApiException(tHttpResponse.getCode(),tHttpResponse.getMessage()));
                        }
                    }
                });
            }
        };
    }
    public static <T>Observable<T>createData(final T t){
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> e) throws Exception {
                try {
                    e.onNext(t);
                    e.onComplete();
                }catch (Exception error){
                    e.onError(error);
                }
            }
        });
    }
}
