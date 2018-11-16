package com.example.googleplay_10_25.holder;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;

import com.example.googleplay_10_25.R;
import com.example.googleplay_10_25.global.Constant;
import com.example.googleplay_10_25.tools.UiUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * @Description:
 * @author: Vincent7
 * @date: 2018/11/14
 */
public class HomePictureHolder extends BaseHolder<List<String>> {
    /* 当 new HomePictureHolder()就会调用该方法 */
    private ViewPager viewPager;
    private List<String> datas;

    private boolean flag;
    private AutoRunTask runTask;

    public class AutoRunTask implements Runnable {

        @Override
        public void run() {
            if (flag) {
                UiUtils.cancel(this); // 取消之前
                int currentItem = viewPager.getCurrentItem();
                currentItem++;
                viewPager.setCurrentItem(currentItem);
                //延迟执行当前的任务
                UiUtils.postDelayed(this, 2000); // 递归调用
            }
        }

        public void start() {
            if (!flag) {
                UiUtils.cancel(this); // 取消之前
                flag = true;
                UiUtils.postDelayed(this, 2000); // 递归调用
            }
        }

        public void stop() {
            if (flag) {
                flag = false;
                UiUtils.cancel(this);
            }
        }
    }

    @Override
    protected View initView() {
        viewPager = new ViewPager(UiUtils.getContext());
        viewPager.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                UiUtils.getDimens(R.dimen.home_picture_height)));

        return viewPager;
    }

    /* 当 holder.setData 才会调用 */
    @Override
    protected void refreshView(List<String> data) {
        this.datas = data;
        viewPager.setAdapter(new HomeAdapter());
        viewPager.setCurrentItem(2000 * datas.size());// 设置起始的位置 Integer.Max_Value/2
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        runTask.stop();
                        break;
                    case MotionEvent.ACTION_CANCEL: //事件的取消
                    case MotionEvent.ACTION_UP:
                        runTask.start();
                        break;
                }
                return false; // viewPager 触摸事件，返回值要是false
            }
        });
        runTask = new AutoRunTask();
        runTask.start();
    }

    class HomeAdapter extends PagerAdapter {
        // 当前viewPager里面有多少个条目
        LinkedList<ImageView> convertView = new LinkedList<>();

        // ArrayList
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        /* 判断返回的对象和 加载view对象的关系 */
        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            ImageView view = (ImageView) object;
            convertView.add(view); // 把移除的对象 添加到缓存集合中
            container.removeView(view);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            int index = position % datas.size();
            ImageView view;
            if (convertView.size() > 0) {
                view = convertView.remove(0);
            } else {
                view = new ImageView(UiUtils.getContext());
            }
            bitmapUtils.display(view, Constant.IMAGE_PREFIX+datas.get(index));
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            container.addView(view); // 加载的view对象
            return view; // 返回的对象
        }
    }
}
