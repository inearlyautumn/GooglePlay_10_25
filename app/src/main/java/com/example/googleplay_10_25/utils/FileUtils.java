package com.example.googleplay_10_25.utils;

import android.os.Environment;

import java.io.File;

/**
 * @Description:
 * @author: Vincent7
 * @date: 2018/11/14
 */
public class FileUtils {
    public static final String CACHE = "cache";
    public static final String ICON = "icon";
    public static final String ROOT = "GooglePlay";

    public static File getIconDir() {
        return getDir(ICON);
    }

    public static File getCacheDir() {
        return getDir(CACHE);
    }

    private static File getDir(String cache) {
        StringBuilder path = new StringBuilder();
        if (isSDAvailable()) {
            path.append(Environment.getExternalStorageDirectory().getAbsolutePath());
            path.append(File.separator); // '/'
            path.append(ROOT); // /mnt/sdcard/GooglePlay
            path.append(File.separator);
            path.append(cache);// /mnt/sdcard/GooglePaly/cache
        } else {
            File fileDir = UiUtils.getContext().getCacheDir();
            path.append(fileDir.getAbsolutePath());
            path.append(File.separator);
            path.append(cache);
        }
        File file = new File(path.toString());
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs(); // 创建文件夹
        }
        return file;
    }

    private static boolean isSDAvailable() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }
}
