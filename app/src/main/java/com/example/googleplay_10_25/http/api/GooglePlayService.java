package com.example.googleplay_10_25.http.api;

import com.example.googleplay_10_25.bean.Home;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GooglePlayService {

    @GET("home")
    Observable<Home> getHomeData(@Query("index") String index);
}
