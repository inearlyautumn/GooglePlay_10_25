package com.example.googleplay_10_25.fragment;

import android.view.View;
import com.example.googleplay_10_25.adapter.ListBaseAdapter;
import com.example.googleplay_10_25.bean.AppInfo;
import com.example.googleplay_10_25.http.api.GooglePalyApi;
import com.example.googleplay_10_25.http.callback.ACallback;
import com.example.googleplay_10_25.http.subscriber.ApiCallbackSubscriber;
import com.example.googleplay_10_25.tools.LogUtil;
import com.example.googleplay_10_25.tools.ToastUtil;
import com.example.googleplay_10_25.tools.UiUtils;
import com.example.googleplay_10_25.view.BaseListView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AppFragment extends BaseFragment {
    public static final String TAG = AppFragment.class.getSimpleName();

    private List<AppInfo> datas;

    /**
     * 当加载成功的时候，显示的界面
     * @return
     */
    @Override
    protected View getSuccessView() {
        BaseListView listView = new BaseListView(UiUtils.getContext());
        listView.setAdapter(new ListBaseAdapter(datas, listView) {
            @Override
            protected void onLoad() {
                Observable<List<AppInfo>> observable = GooglePalyApi.getInstance().getAppData(datas.size());
                observable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new ApiCallbackSubscriber<List<AppInfo>>(new ACallback<List<AppInfo>>() {
                            @Override
                            public void onSuccess(List<AppInfo> data) {
                                datas.addAll(data);
                                onLoadData(datas);
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {
                                dealData(datas);
                            }
                        }));
            }
        });
        return listView;
    }

    @Override
    protected void requestData() {
        Observable<List<AppInfo>> observable = GooglePalyApi.getInstance().getAppData(0);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ApiCallbackSubscriber<List<AppInfo>>(new ACallback<List<AppInfo>>() {
                    @Override
                    public void onSuccess(List<AppInfo> data) {
                        datas = data;
                        dealData(datas);
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        dealData(datas);
                    }
                }));

    }
}
