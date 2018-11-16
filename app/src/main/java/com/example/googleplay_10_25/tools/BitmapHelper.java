package com.example.googleplay_10_25.tools;

import com.lidroid.xutils.BitmapUtils;

/**
 * @Description:
 * @author: Vincent7
 * @date: 2018/11/14
 */
public class BitmapHelper {
    public BitmapHelper() {
    }

    private static BitmapUtils bitmapUtils;

    public static BitmapUtils getBitmapUtils() {
        if (bitmapUtils == null) {
            // 第二个参数 缓存图片的路径
            // 加载图片 最多消耗多少比例的内存 0.05 - 0.8f
            bitmapUtils = new BitmapUtils(UiUtils.getContext(), FileUtils.getIconDir().getAbsolutePath(),
                    0.3f);
        }
        return bitmapUtils;
    }
}
