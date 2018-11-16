package com.example.googleplay_10_25.fragment;

import android.support.v4.app.Fragment;

import java.util.HashMap;
import java.util.Map;

public class FragmentFactory {

    private static Map<Integer, BaseFragment> mFragments = new HashMap<Integer, BaseFragment>();

    public static BaseFragment createFragment(int position) {
        BaseFragment fragment = null;
        fragment = mFragments.get(position);// 在集合中取出来Fragment
        if (fragment == null) {
            switch (position) {
                case 0:
                    fragment = new HomeFragment();
                    break;
                case 1:
                    fragment = new AppFragment();
                    break;
                case 2:
                    fragment = new GameFragment();
                    break;
                case 3:
                    fragment = new SubjectFragment();
                    break;
                case 4:
                    fragment = new RecommendFragment();
                    break;
                case 5:
                    fragment = new CategoryFragment();
                    break;
                case 6:
                    fragment = new HotFragment();
                    break;
                default:
                    break;
            }
            if (fragment != null) {
                mFragments.put(position, fragment); // 把创建好的Fragment存放到集合中缓存起来
            }
        }
        return fragment;
    }
}
