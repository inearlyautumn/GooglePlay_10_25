package com.example.googleplay_10_25.global;

import android.graphics.Bitmap;

import com.example.googleplay_10_25.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

public interface ImageLoaderOptions {
    // 不带任何动画的options
    DisplayImageOptions default_options = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.drawable.ic_default)// 在加载过程中显示哪张图片
            .showImageForEmptyUri(R.drawable.ic_default)// url为空显示什么图片
            .showImageOnFail(R.drawable.ic_default)// 加载失败显示什么图片
            .cacheInMemory(false)// 是否在内存缓存
            .cacheOnDisk(true)// 是否在硬盘缓存
            .imageScaleType(ImageScaleType.EXACTLY)// 会对图片进一步的缩放
            .bitmapConfig(Bitmap.Config.RGB_565)// 配置了图片的减小内存的颜色模式
            .displayer(new SimpleBitmapDisplayer()).build();//
    // .displayer(new RoundedBitmapDisplayer(40)).build();// 圆角加载;
    // 带有渐渐显示的动画options
    DisplayImageOptions fadein_options = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.drawable.ic_default)// 在加载过程中显示哪张图片
            .showImageForEmptyUri(R.drawable.ic_default)// url为空显示什么图片
            .showImageOnFail(R.drawable.ic_default)// 加载失败显示什么图片
            .cacheInMemory(false)// 是否在内存缓存
            .cacheOnDisk(true)// 是否在硬盘缓存
            .imageScaleType(ImageScaleType.EXACTLY)// 会对图片进一步的缩放
            .bitmapConfig(Bitmap.Config.RGB_565)// 配置了图片的减小内存的颜色模式
            .displayer(new FadeInBitmapDisplayer(800)).build();// 渐渐显示的加载动画
    // .displayer(new RoundedBitmapDisplayer(40)).build();// 圆角加载;


    DisplayImageOptions circle_options = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.drawable.ic_default)// 在加载过程中显示哪张图片
            .showImageForEmptyUri(R.drawable.ic_default)// url为空显示什么图片
            .showImageOnFail(R.drawable.ic_default)// 加载失败显示什么图片
            .cacheInMemory(false)// 是否在内存缓存
            .cacheOnDisk(true)// 是否在硬盘缓存
            .imageScaleType(ImageScaleType.EXACTLY)// 会对图片进一步的缩放
            .bitmapConfig(Bitmap.Config.RGB_565)// 配置了图片的减小内存的颜色模式
            // .displayer(new FadeInBitmapDisplayer(800)).build();// 渐渐显示的加载动画
            .displayer(new RoundedBitmapDisplayer(40)).build();// 圆角加载;
}
