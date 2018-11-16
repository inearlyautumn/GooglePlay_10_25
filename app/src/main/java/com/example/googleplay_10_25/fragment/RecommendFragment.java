package com.example.googleplay_10_25.fragment;

import android.view.LayoutInflater;
import android.view.View;

import com.example.googleplay_10_25.R;

public class RecommendFragment extends BaseFragment {
//    private StellarMap stellarMap;

    @Override
    protected View getSuccessView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_item,
                null, false);
        return view;
    }

    @Override
    protected void requestData() {

    }
}
