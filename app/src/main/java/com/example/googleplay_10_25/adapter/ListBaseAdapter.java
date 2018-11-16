package com.example.googleplay_10_25.adapter;

import android.content.Intent;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.googleplay_10_25.DetailActivity;
import com.example.googleplay_10_25.bean.AppInfo;
import com.example.googleplay_10_25.holder.BaseHolder;
import com.example.googleplay_10_25.holder.ListBaseHolder;
import com.example.googleplay_10_25.tools.ToastUtil;
import com.example.googleplay_10_25.tools.UiUtils;

import java.util.List;

/**
 * @Description:
 * @author: Vincent7
 * @date: 2018/11/15
 */
public abstract class ListBaseAdapter extends DefaultAdapter<AppInfo> {
    public ListBaseAdapter(List datas, ListView lv) {
        super(datas, lv);
    }

    @Override
    protected BaseHolder getHolder() {
        return new ListBaseHolder();
    }

    @Override
    public void onInnerItemClick(int position) {
        super.onInnerItemClick(position);
        ToastUtil.showShortToast("position : "+position);
        AppInfo appInfo = datas.get(position);
        Intent intent = new Intent(UiUtils.getContext(), DetailActivity.class);
        intent.putExtra("packageName", appInfo.getPackageName());
        UiUtils.startActivity(intent);
    }
}
