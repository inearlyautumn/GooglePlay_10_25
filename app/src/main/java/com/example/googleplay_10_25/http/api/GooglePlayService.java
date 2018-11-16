package com.example.googleplay_10_25.http.api;

import com.example.googleplay_10_25.bean.AppInfo;
import com.example.googleplay_10_25.bean.Category;
import com.example.googleplay_10_25.bean.Home;
import com.example.googleplay_10_25.bean.Subject;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GooglePlayService {

    @GET("home")
    Observable<Home> getHomeData(@Query("index") String index);

    @GET("app")
    Observable<List<AppInfo>> getAppData(@Query("index") String index);

    @GET("game")
    Observable<ResponseBody> getGameData(@Query("index") String index);

    @GET("subject")
    Observable<List<Subject>> getSubjectData(@Query("index") String index);

    @GET("recommend")
    Observable<List<String>> getRecommendData(@Query("index") String index);

    @GET("category")
    Observable<List<Category>> getCategoryData(@Query("index") String index);

    @GET("hot")
    Observable<List<String>> getHotData(@Query("index") String index);
}
