package com.example.googleplay_10_25.http.exception;

public class NoDataExceptionException extends RuntimeException {
    public NoDataExceptionException() {
        super("服务器没有返回对应的Data数据", new Throwable("Server error"));
    }
}
