package com.example.googleplay_10_25.fragment;

import android.view.View;

import com.example.googleplay_10_25.adapter.ListBaseAdapter;
import com.example.googleplay_10_25.bean.AppInfo;
import com.example.googleplay_10_25.http.api.GooglePalyApi;
import com.example.googleplay_10_25.http.callback.ACallback;
import com.example.googleplay_10_25.utils.UiUtils;
import com.example.googleplay_10_25.view.BaseListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class GameFragment extends BaseFragment {
    public static final String TAG = GameFragment.class.getSimpleName();

    private List<AppInfo> datas;

    @Override
    protected View getSuccessView() {
        BaseListView listView = new BaseListView(UiUtils.getContext());
        listView.setAdapter(new ListBaseAdapter(datas, listView) {
            @Override
            protected void onLoad() {
                Observable<ResponseBody> observable = GooglePalyApi.getInstance().getGameData(datas.size());
/*                observable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new ApiCallbackSubscriber<ResponseBody>(new ACallback<ResponseBody>() {
                            @Override
                            public void onSuccess(ResponseBody data) {
                                try {
                                    String string = data.string();
                                    Gson gson = new Gson();
                                    Type type = new TypeToken<List<AppInfo>>() {
                                    }.getType();
                                    ArrayList<AppInfo> appInfos = gson.fromJson(string, type);
                                    datas.addAll(appInfos);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }finally {
                                    dealData(datas);
                                }
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {
                                dealData(datas);
                            }
                        }));*/
            }
        });
        return listView;
    }

    @Override
    protected void requestData() {
        Observable<ResponseBody> observable = GooglePalyApi.getInstance().getGameData(0);
/*        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ApiCallbackSubscriber<ResponseBody>(new ACallback<ResponseBody>() {
                    @Override
                    public void onSuccess(ResponseBody data) {
                        try {
                            String string = data.string();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<AppInfo>>() {
                            }.getType();
                            ArrayList<AppInfo> appInfos = gson.fromJson(string, type);
                            datas = appInfos;
                            dealData(datas);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        dealData(datas);
                    }
                }));*/
    }
}
