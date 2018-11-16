package com.example.googleplay_10_25.holder;

import android.view.View;

import com.example.googleplay_10_25.tools.BitmapHelper;
import com.lidroid.xutils.BitmapUtils;

public abstract class BaseHolder<T> {
    protected View contentView;//相当于convertView
    private T data;
    protected BitmapUtils bitmapUtils;

    public BaseHolder() {
        bitmapUtils = BitmapHelper.getBitmapUtils();
        contentView = initView();
        contentView.setTag(this);
    }

    public View getContentView() {
        return contentView;
    }

    public void setData(T data) {
        this.data = data;
        refreshView(data);
    }

    /**
     * 创建界面
     */
    protected abstract View initView();
    /* 根据数据刷新界面 */
    protected abstract void refreshView(T data);
}
