package com.example.googleplay_10_25.global;

public class Constant {
    //服务器的主机地址
    public static final String SERVER_HOST = "http://127.0.0.1:8090/";
    //图片的url前缀
    public static final String IMAGE_PREFIX = SERVER_HOST +"image?name=";

    //Home页的url地址
    public static final String Home = SERVER_HOST + "home?index=";
    //App页的url地址
    public static final String App = SERVER_HOST + "app?index=";
    //Game页的url地址
    public static final String Game = SERVER_HOST + "game?index=";
    //Subject页的url地址
    public static final String Subject = SERVER_HOST + "subject?index=";
    //Recommend页的url地址
    public static final String Recommend = SERVER_HOST + "recommend?index=0";
    //Category页的url地址
    public static final String Category = SERVER_HOST + "category?index=0";
    //Hot页的url地址
    public static final String Hot = SERVER_HOST + "hot?index=0";
    //Detail页的url地址
    public static final String App_Detail = SERVER_HOST + "detail?packageName=%s";
    //下载apk的url地址
    public static final String Download = SERVER_HOST + "download?name=%s";
    //断点下载apk的url地址
    public static final String Break_Download = SERVER_HOST + "download?name=%s&range=%d";
}
