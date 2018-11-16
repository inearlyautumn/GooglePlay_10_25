package com.example.googleplay_10_25.tools;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

public class CommonUtil {

    /**
     *
     * @param child
     */
    public static void removeSelfFromParent(View child) {
        if (child != null) {
            ViewParent parent = child.getParent();
            if (parent != null && parent instanceof ViewGroup) {
                ViewGroup group = (ViewGroup) parent;
                group.removeView(child); //将child从父View中移除
            }
        }
    }
}
