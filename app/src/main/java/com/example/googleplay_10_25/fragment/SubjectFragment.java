package com.example.googleplay_10_25.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.googleplay_10_25.R;
import com.example.googleplay_10_25.adapter.DefaultAdapter;
import com.example.googleplay_10_25.bean.Subject;
import com.example.googleplay_10_25.holder.BaseHolder;
import com.example.googleplay_10_25.http.api.GooglePalyApi;
import com.example.googleplay_10_25.utils.UiUtils;
import com.example.googleplay_10_25.view.BaseListView;

import java.util.List;

import io.reactivex.Observable;

public class SubjectFragment extends BaseFragment {
    private List<Subject> datas;

    @Override
    protected View getSuccessView() {
        BaseListView listView = new BaseListView(UiUtils.getContext());
//        listView.setAdapter(new SubjectAdapter);
        return null;
    }

    @Override
    protected void requestData() {
        Observable<List<Subject>> observable = GooglePalyApi.getInstance().getSubjectData("0");

    }

    private class SubjectAdapter extends DefaultAdapter<Subject> {

        public SubjectAdapter(List<Subject> datas, ListView lv) {
            super(datas, lv);
        }

        @Override
        protected void onLoad() {

        }

        @Override
        protected BaseHolder<Subject> getHolder() {
            return new SubjectHolder();
        }
    }

    class SubjectHolder extends BaseHolder<Subject> {

        ImageView item_icon;
        TextView item_txt;

        @Override
        protected View initView() {
            View contentView = UiUtils.inflate(R.layout.item_subject);
            return null;
        }

        @Override
        protected void refreshView(Subject data) {

        }
    }

}
