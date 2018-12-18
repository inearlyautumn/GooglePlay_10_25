package com.example.googleplay_10_25.utils;

import android.widget.Toast;

import com.example.googleplay_10_25.R;


/**
 * Created by Y3870 on 2018/6/30.
 */

public class ToastUtil {
    private static Toast toast;

    private static boolean isDebug = true;

    public static void showSingleShortToast(final String text) {
        if (isDebug) {
            UiUtils.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (toast == null) {
                        toast = Toast.makeText(UiUtils.getContext(), text, Toast.LENGTH_SHORT);
                    }
                    toast.setText(text);
                    toast.show();
                }
            });
        }
    }

    public static void showSingleLongToast(final String text) {
        if (isDebug) {
            UiUtils.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (toast == null) {
                        toast = Toast.makeText(UiUtils.getContext(), text, Toast.LENGTH_LONG);
                    }
                    toast.setText(text);
                    toast.show();
                }
            });
        }
    }

    public static void showShortToast(final String text) {
        if (isDebug) {
            UiUtils.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(UiUtils.getContext(), text, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public static void showShortToast(final int resId) {
        if (isDebug) {
            UiUtils.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String text = UiUtils.getResource().getString(resId);
                    Toast.makeText(UiUtils.getContext(), text, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public static void showLongToast(final String text) {
        if (isDebug) {
            UiUtils.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(UiUtils.getContext(), text, Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
