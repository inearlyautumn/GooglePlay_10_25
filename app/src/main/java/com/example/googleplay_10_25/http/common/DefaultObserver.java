package com.example.googleplay_10_25.http.common;

import com.example.googleplay_10_25.R;
import com.example.googleplay_10_25.http.exception.NoDataExceptionException;
import com.example.googleplay_10_25.http.exception.ServerResponseException;
import com.example.googleplay_10_25.utils.LogUtil;
import com.example.googleplay_10_25.utils.ToastUtil;
import com.google.gson.JsonParseException;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class DefaultObserver<T> implements Observer<T> {
    private static final String TAG = DefaultObserver.class.getSimpleName();

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T response) {
        onSuccess(response);
        onFinish();
    }

    @Override
    public void onError(Throwable e) {
        LogUtil.e(TAG, "Retrofit = "+e.getMessage());
        if (e instanceof HttpException) {     //   HTTP错误
            onException(ExceptionReason.BAD_NETWORK);
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {   //   连接错误
            onException(ExceptionReason.CONNECT_ERROR);
        } else if (e instanceof InterruptedIOException) {   //  连接超时
            onException(ExceptionReason.CONNECT_TIMEOUT);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {   //  解析错误
            onException(ExceptionReason.PARSE_ERROR);
        } else if (e instanceof ServerResponseException) {
            onFail(e.getMessage());
        } else if (e instanceof NoDataExceptionException) {
            onSuccess(null);
        } else {
            onException(ExceptionReason.UNKNOWN_ERROR);
        }
        onFinish();
    }

    @Override
    public void onComplete() {

    }

    /**
     * 服务器返回数据，但响应码不为200
     * @param message
     */
    protected void onFail(String message) {
        ToastUtil.showShortToast(message);
    }

    protected void onException(ExceptionReason reason) {
        switch (reason) {
            case CONNECT_ERROR:
                ToastUtil.showShortToast(R.string.connect_error);
                break;
            case CONNECT_TIMEOUT:
                ToastUtil.showShortToast(R.string.connect_timeout);
                break;
            case BAD_NETWORK:
                ToastUtil.showShortToast(R.string.bad_network);
                break;
            case PARSE_ERROR:
                ToastUtil.showShortToast(R.string.parse_error);
                break;
            case UNKNOWN_ERROR:
            default:
                ToastUtil.showShortToast(R.string.unknown_error);
                break;
        }
    }

    protected void onFinish() {}

    protected abstract void onSuccess(T response);

    /**
     * 请求网络失败原因
     */
    public enum ExceptionReason {
        /**
         * 解析数据失败
         */
        PARSE_ERROR,
        /**
         * 网络问题
         */
        BAD_NETWORK,
        /**
         * 连接错误
         */
        CONNECT_ERROR,
        /**
         * 连接超时
         */
        CONNECT_TIMEOUT,
        /**
         * 未知错误
         */
        UNKNOWN_ERROR,
    }
}
