package com.abtingramian.abtinblr.feature.placeholder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abtingramian.abtinblr.R;
import com.abtingramian.abtinblr.base.BaseFragment;

public class PlaceholderFragment extends BaseFragment {

    public static PlaceholderFragment newInstance() {
        Bundle args = new Bundle();
        PlaceholderFragment fragment = new PlaceholderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_placeholder, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
