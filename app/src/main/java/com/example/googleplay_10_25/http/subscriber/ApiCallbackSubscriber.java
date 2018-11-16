package com.example.googleplay_10_25.http.subscriber;

import com.example.googleplay_10_25.http.callback.ACallback;
import com.example.googleplay_10_25.http.exception.ApiException;

public class ApiCallbackSubscriber<T> extends ApiSubscriber<T> {
    ACallback<T> callBack;
    T data;

    public ApiCallbackSubscriber(ACallback<T> callBack) {
        if (callBack == null) {
            throw new NullPointerException("this callback is null！");
        }
        this.callBack = callBack;
    }

    @Override
    protected void onError(ApiException e) {
        if (e == null) {
            callBack.onFail(-1, "This ApiException is NUll.");
            return;
        }
        callBack.onFail(e.getCode(), e.getMessage());
    }

    @Override
    public void onNext(T t) {
        this.data = t;
        callBack.onSuccess(t);
    }

    @Override
    public void onComplete() {

    }

    public T getData() {
        return data;
    }
}
