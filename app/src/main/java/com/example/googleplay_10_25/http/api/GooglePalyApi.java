package com.example.googleplay_10_25.http.api;

import com.example.googleplay_10_25.bean.AppInfo;
import com.example.googleplay_10_25.bean.Category;
import com.example.googleplay_10_25.bean.Home;
import com.example.googleplay_10_25.bean.Subject;
import com.example.googleplay_10_25.global.Constant;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GooglePalyApi {
    public static GooglePalyApi instance;

    private GooglePlayService service;

    public GooglePalyApi() {
//        new LoggingInter

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.SERVER_HOST)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
//                .client(okHttpClient)
                .build();
        service = retrofit.create(GooglePlayService.class);
    }

    public static GooglePalyApi getInstance() {
        if (instance == null) {
            instance = new GooglePalyApi();
        }
        return instance;
    }

    public Observable<Home> getHomeData(int index) {
        return service.getHomeData(""+index);
    }

    public Observable<List<AppInfo>> getAppData(int index) {
        return service.getAppData(""+index);
    }

    public Observable<ResponseBody> getGameData(int index) {
        return service.getGameData(""+index);
    }

    public Observable<List<Subject>> getSubjectData(String index) {
        return service.getSubjectData(index);
    }

    public Observable<List<String>> getRecommendData(String index) {
        return service.getRecommendData(index);
    }

    public Observable<List<Category>> getCategoryData(String index) {
        return service.getCategoryData(index);
    }

    public Observable<List<String>> getHotData(String index) {
        return service.getHotData(index);
    }
}
