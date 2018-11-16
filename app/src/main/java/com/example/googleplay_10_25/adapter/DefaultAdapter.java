package com.example.googleplay_10_25.adapter;

import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.googleplay_10_25.holder.BaseHolder;
import com.example.googleplay_10_25.holder.MoreHolder;
import com.example.googleplay_10_25.manager.ThreadManager;
import com.example.googleplay_10_25.tools.UiUtils;

import java.util.List;

/**
 * @Description:
 * @author: Vincent7
 * @date: 2018/11/15
 */
public abstract class DefaultAdapter<T> extends BaseAdapter implements AdapterView.OnItemClickListener {

    public static final int DEFAULT_ITEM = 0;
    public static final int MORE_ITEM = 1;

    private ListView listView;
    protected List<T> datas;
    private MoreHolder holder;

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public DefaultAdapter(List<T> datas, ListView lv) {
        this.datas = datas;
        //给ListView设置条目的点击事件
        lv.setOnItemClickListener(this);
        listView = lv;
    }

    /**
     * ListView 条目点击事件回调的方法
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        position = position - listView.getHeaderViewsCount(); // 获取到顶部条目的数量位置去掉顶部view的数量
        onInnerItemClick(position);
    }

    /**
     * 在该方法去处理条目的点击事件
     *
     * @param position
     */
    public void onInnerItemClick(int position) {

    }

    @Override
    public int getCount() {
        return datas.size() + 1; // 最后的一个条目，就是加载更多的条目
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    /* 根据位置  判断当前条目是什么类型 */
    @Override
    public int getItemViewType(int position) {
        if (position == datas.size()) { // 当前是最后一个条目
            return MORE_ITEM;
        }
        return super.getItemViewType(position); // 如果不是最后一个条目， 返回默认类型
    }

    protected int getInnerItemViewType(int position) {
        return DEFAULT_ITEM;
    }

    /* 当前ListView 有几种不同的条目类型 */
    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount() + 1; // 2 有两种不同的类型
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseHolder holder = null;
        switch (getItemViewType(position)) {
            case MORE_ITEM:
                if (convertView == null) {
                    holder = getMoreHolder();
                } else {
                    holder = (BaseHolder) convertView.getTag();
                }
                break;
            default:
                if (convertView == null) {
                    holder = getHolder();
                } else {
                    System.out.println("aaa");
                    holder = (BaseHolder) convertView.getTag();
                }
                if (datas.size() > position) {
                    holder.setData(datas.get(position));
                }
                break;
        }
        return holder.getContentView(); // 如果当前Holder 恰好是MoreHolder 证明MoreHolder已经显示
    }

    private BaseHolder getMoreHolder() {
        if (holder != null) {
            return holder;
        } else {
            holder = new MoreHolder(this, hasMore());
            return holder;
        }
    }

    private boolean hasMore() {
        return true;
    }

    /* 当加载更多条目显示的时候 调用该方法 */
    public void loadMore() {
        ThreadManager.getInstance().createLongPool().execute(new Runnable() {
            @Override
            public void run() {
                //在子线程中加载更多
                SystemClock.sleep(2000);
                onLoad();
            }
        });
    }

    public void onLoadData(final List<T> newData) {
        UiUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (newData == null) {
                    holder.setData(MoreHolder.LOAD_ERROR);
                } else if (newData.size() == 0) {
                    holder.setData(MoreHolder.HAS_NO_MORE);
                } else {
                    // 成功了
                    holder.setData(MoreHolder.HAS_MORE);
                    datas.addAll(newData);  // 给listView之前的集合添加一个新的集合
                    notifyDataSetChanged(); // 刷新界面
                }
            }
        });
    }

    protected abstract void onLoad();

    protected abstract BaseHolder<T> getHolder();
}
