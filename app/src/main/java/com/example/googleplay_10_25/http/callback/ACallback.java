package com.example.googleplay_10_25.http.callback;

/**
 * @Description: 请求接口回调
 * @author: Vincent7
 * @date: 2018/11/9
 */
public abstract class ACallback<T> {
    public abstract void onSuccess(T data);

    public abstract void onFail(int errCode, String errMsg);
}
