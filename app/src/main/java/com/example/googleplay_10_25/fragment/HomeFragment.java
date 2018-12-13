package com.example.googleplay_10_25.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.googleplay_10_25.R;
import com.example.googleplay_10_25.adapter.ListBaseAdapter;
import com.example.googleplay_10_25.bean.AppInfo;
import com.example.googleplay_10_25.bean.Home;
import com.example.googleplay_10_25.holder.HomePictureHolder;
import com.example.googleplay_10_25.http.api.GooglePalyApi;
import com.example.googleplay_10_25.http.callback.ACallback;
import com.example.googleplay_10_25.http.subscriber.ApiCallbackSubscriber;
import com.example.googleplay_10_25.tools.LogUtil;
import com.example.googleplay_10_25.tools.ToastUtil;
import com.example.googleplay_10_25.tools.UiUtils;
import com.example.googleplay_10_25.view.BaseListView;
import com.example.googleplay_10_25.view.LoadingPage;
import com.lidroid.xutils.bitmap.PauseOnScrollListener;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeFragment extends BaseFragment {
    public static final String TAG = HomeFragment.class.getSimpleName();

    private List<AppInfo> datas;
    private List<String> pictures; // 顶部ViewPager 显示界面的数据

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    @Override
    protected View getSuccessView() {
        BaseListView listView = new BaseListView(UiUtils.getContext());
        HomePictureHolder holder = new HomePictureHolder();
        holder.setData(pictures);
        View contentView = holder.getContentView(); // 得到holder里面管理的view对象
        listView.addHeaderView(contentView); // 把holder里的view对象 添加到listView的上面

        listView.setAdapter(new ListBaseAdapter(datas, listView) {
            @Override
            protected void onLoad() {
                //这里返回网络请求的数据
                Observable<Home> observable = GooglePalyApi.getInstance().getHomeData(datas.size());
                observable
                        .subscribeOn(Schedulers.io()) // 请求在新的线程执行
                        .observeOn(AndroidSchedulers.mainThread()) // 请求完成后在子线程中执行
                        .subscribe(new ApiCallbackSubscriber<Home>(new ACallback<Home>() {
                            @Override
                            public void onSuccess(Home data) {
                                datas.addAll(data.getList());
                                onLoadData(datas);
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {
                                dealData(datas);
                            }
                        }));
            }
        });

        // 第二个参数 慢慢滑动的时候是否加载图片 false 加载 true不加载
        // 第三个参数 飞速滑动的时候是否加载图片 true 不加载
        listView.setOnScrollListener(new PauseOnScrollListener(bitmapUtils, false, true));
        //设置如果图片加载中显示的图片
        bitmapUtils.configDefaultLoadingImage(R.drawable.ic_default);
        //加载失败显示的图片
        bitmapUtils.configDefaultLoadFailedImage(R.drawable.ic_default);
        return listView;
    }

    @Override
    protected void requestData() {
        LogUtil.i(TAG,"requestData");
        //这里请求网络数据
        Observable<Home> observable = GooglePalyApi.getInstance().getHomeData(0);
        observable
                .subscribeOn(Schedulers.io()) // 请求在新的线程执行
                .observeOn(AndroidSchedulers.mainThread()) // 请求完成后在子线程中执行
                .subscribe(new ApiCallbackSubscriber<Home>(new ACallback<Home>() {
                    @Override
                    public void onSuccess(Home data) {
                        datas = data.getList();
                        pictures = data.getPicture();
                        dealData(datas);
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        dealData(datas);
                    }
                }));
    }
}