package com.example.googleplay_10_25;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.LinkedList;
import java.util.List;

/**
 * @Description: 抽取BaseActivity
 * @author: Vincent7
 * @date: 2018/11/15
 */
public class BaseActivity extends AppCompatActivity {
    //管理运行的所有的activity
    public final static List<BaseActivity> mActivities = new LinkedList<>();

    public static BaseActivity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        synchronized (mActivities) {
            mActivities.add(this);
        }

        init();
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        activity = this;
    }

    @Override
    protected void onPause() {
        super.onPause();
        activity = null;
    }

    public void killAll() {
        //  复制了一份 mActivities 集合
        List<BaseActivity> copy;
        synchronized (mActivities) {
            copy = new LinkedList<>(mActivities);
        }
        for (BaseActivity activity : copy) {
            activity.finish();
        }
        //杀死当前的进程
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    protected void init() {
    }
    protected void initView() {
    }
}
