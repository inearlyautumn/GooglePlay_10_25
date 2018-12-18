package com.example.googleplay_10_25.holder;

import android.view.View;
import android.widget.RelativeLayout;

import com.example.googleplay_10_25.R;
import com.example.googleplay_10_25.adapter.DefaultAdapter;
import com.example.googleplay_10_25.utils.UiUtils;

/**
 * @Description:
 * @author: Vincent7
 * @date: 2018/11/15
 */
public class MoreHolder extends BaseHolder<Integer> {
    public static final int HAS_NO_MORE = 0; //没有额外数据了
    public static final int LOAD_ERROR = 1; //加载失败
    public static final int HAS_MORE = 2; //有额外的数据

    private boolean hasmore;
    private RelativeLayout rl_more_loading, rl_more_error;

    private DefaultAdapter adapter;

    /* 当Holder显示的时候 显示什么样子 */
    @Override
    protected View initView() {
        View view = UiUtils.inflate(R.layout.load_more);
        rl_more_loading = view.findViewById(R.id.rl_more_loading);
        rl_more_error = view.findViewById(R.id.rl_more_error);
        return view;
    }

    public MoreHolder(DefaultAdapter adapter, boolean hasmore) {
        super();
        this.hasmore = hasmore;
        this.adapter = adapter;
        if (!hasmore) {
            setData(0);
        }
    }

    @Override
    public View getContentView() {
        if (hasmore) {
            loadMore();
        }
        return super.getContentView();
    }

    private void loadMore() {
        // 请求服务器 加载下一批数据
        // 交给adapter 让Adapter 加载更多数据
        adapter.loadMore();
    }

    /**
     * 根据数据做界面的修改
     * @param data
     */
    @Override
    protected void refreshView(Integer data) {
        rl_more_error.setVisibility(data == LOAD_ERROR ? View.VISIBLE : View.GONE);
        rl_more_loading.setVisibility(data == HAS_MORE ? View.VISIBLE : View.GONE);
    }
}
