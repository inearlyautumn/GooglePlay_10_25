package com.example.googleplay_10_25.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.googleplay_10_25.R;

public class SubjectFragment extends BaseFragment {

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
