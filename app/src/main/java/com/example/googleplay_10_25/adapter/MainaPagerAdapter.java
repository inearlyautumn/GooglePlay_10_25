package com.example.googleplay_10_25.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.googleplay_10_25.R;
import com.example.googleplay_10_25.fragment.FragmentFactory;
import com.example.googleplay_10_25.utils.UiUtils;

public class MainaPagerAdapter extends FragmentPagerAdapter {
    private String[] tabs;

    public MainaPagerAdapter(FragmentManager fm) {
        super(fm);
        tabs = UiUtils.getStringArray(R.array.tab_names);
    }

    @Override
    public Fragment getItem(int i) {
        return FragmentFactory.createFragment(i);
    }

    @Override
    public int getCount() {
        return tabs.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }
}
