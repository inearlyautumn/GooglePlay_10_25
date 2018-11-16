package com.example.googleplay_10_25.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.googleplay_10_25.BaseApplication;
import com.example.googleplay_10_25.global.Constant;
import com.example.googleplay_10_25.global.ImageLoaderOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

public class HomeHeaderAdapter extends BasePagerAdapter<String> {

    public HomeHeaderAdapter(ArrayList<String> urlList) {
        super(urlList);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(BaseApplication.getApplication());
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        //显示图片
        ImageLoader.getInstance().displayImage(Constant.IMAGE_PREFIX+list.get(position),
                imageView,ImageLoaderOptions.fadein_options);

        //将imageView添加到container
        container.addView(imageView);
        return imageView;
    }
}
