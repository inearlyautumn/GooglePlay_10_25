package com.example.googleplay_10_25.holder;

import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.googleplay_10_25.R;
import com.example.googleplay_10_25.bean.AppInfo;
import com.example.googleplay_10_25.global.Constant;
import com.example.googleplay_10_25.utils.UiUtils;

/**
 * @Description:
 * @author: Vincent7
 * @date: 2018/11/15
 */
public class ListBaseHolder extends BaseHolder<AppInfo> {
    ImageView item_icon;
    TextView item_title, item_size, item_bottom;
    RatingBar item_rating;

    @Override
    protected void refreshView(AppInfo data) {
        item_title.setText(data.getName());
        String size = Formatter.formatFileSize(UiUtils.getContext(), data.getSize());
        item_size.setText(size);
        item_bottom.setText(data.getDes());
        float stars = data.getStars();
        item_rating.setRating(stars);
        String iconUrl = data.getIconUrl(); //http://127.0.0.1:8090/image?name=app/com.youyuan.yyhl/icon.jpg

        bitmapUtils.display(this.item_icon, Constant.IMAGE_PREFIX + iconUrl);
    }

    @Override
    protected View initView() {
        View contentView=View.inflate(UiUtils.getContext(), R.layout.item_app, null);
        this.item_icon=(ImageView) contentView.findViewById(R.id.item_icon);
        this.item_title=(TextView) contentView.findViewById(R.id.item_title);
        this.item_size=(TextView) contentView.findViewById(R.id.item_size);
        this.item_bottom=(TextView) contentView.findViewById(R.id.item_bottom);
        this.item_rating=(RatingBar) contentView.findViewById(R.id.item_rating);
        return contentView;
    }

}
