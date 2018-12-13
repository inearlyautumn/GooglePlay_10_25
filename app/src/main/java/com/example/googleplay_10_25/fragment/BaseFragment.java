package com.example.googleplay_10_25.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.googleplay_10_25.tools.BitmapHelper;
import com.example.googleplay_10_25.tools.CommonUtil;
import com.example.googleplay_10_25.tools.UiUtils;
import com.example.googleplay_10_25.view.LoadingPage;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;

public abstract class BaseFragment extends Fragment {
    protected LoadingPage loadingPage;
    protected BitmapUtils bitmapUtils;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        bitmapUtils = BitmapHelper.getBitmapUtils();
        if (loadingPage == null) {
            loadingPage = new LoadingPage(getActivity()) {
                @Override
                public View createSuccessView() {
                    return getSuccessView();
                }

                @Override
                protected void load() {
                    requestData();
                }
            };
        } else {
            //1. 第一次创建Fragment的时候，将fragment加入到ViewPager中的时候，FragmentManager会在loadingPage外面
            //包裹一层
            //NoSaveStateFragmeLayout,由于我们第二次进入Fragment的时候是使用了已经创建了的loadingPage,此时
            //FragmentManager，又会试图在它外面包裹帧布局，但是由于loadingPage已经有父View了，所以就报错了；
            //2. 解决方案：拿帧布局将loadingPage移除即可
            CommonUtil.removeSelfFromParent(loadingPage);
        }
        return loadingPage;
    }

    @Override
    public void onResume() {
        super.onResume();
//        loadingPage.initLoadingPage();
    }

    public void show() {
        if (loadingPage != null) {
            loadingPage.show();
        }
    }

    public LoadingPage.LoadResult checkData(List datas) {
        if (datas == null) {
            return LoadingPage.LoadResult.error; // 请求服务器失败
        } else {
            if (datas.size() == 0) {
                return LoadingPage.LoadResult.empty;
            } else {
                return LoadingPage.LoadResult.success;
            }
        }
    }

    protected void dealData(List datas) {
        loadingPage.dealData(checkData(datas));
    }

    /**
     * 获取succcessView，由每个子类去实现
     *
     * @return
     */
    protected abstract View getSuccessView();

    /**
     * 请求数据，同样也是由每个子类去实现
     *
     * @return
     */
    protected abstract void requestData();
}
