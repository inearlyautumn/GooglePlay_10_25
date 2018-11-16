package com.example.googleplay_10_25.http.subscriber;

import com.example.googleplay_10_25.http.exception.ApiException;
import com.example.googleplay_10_25.http.mode.ApiCode;

import io.reactivex.observers.DisposableObserver;

abstract class ApiSubscriber<T> extends DisposableObserver<T> {
    public ApiSubscriber() {
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof ApiException) {
            onError((ApiException) e);
        } else {
            onError(new ApiException(e, ApiCode.Request.UNKNOWN));
        }
    }

    protected abstract void onError(ApiException e);
}
