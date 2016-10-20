package com.abtingramian.abtinblr.feature.start;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.transition.Fade;
import android.transition.Transition;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abtingramian.abtinblr.R;
import com.abtingramian.abtinblr.base.BaseFragment;
import com.abtingramian.abtinblr.feature.home.HomeActivity;

import butterknife.OnClick;

public class StartFragment extends BaseFragment {

    public static StartFragment newInstance() {
        Bundle args = new Bundle();
        StartFragment fragment = new StartFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_start, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @OnClick(R.id.sign_in)
    void signIn() {
        startActivity(HomeActivity.newIntent(getActivity()));
    }

    @OnClick(R.id.get_started)
    void getStarted() {
        Snackbar snackbar = Snackbar.make(getView(), R.string.coming_soon, Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(getContext(), R.color.button_color_transparent));
        TextView textView = (TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(getContext(), R.color.button_text_color));
        snackbar.show();
    }

    @Override
    public Transition getEnterTransition() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Fade fade = new Fade(Fade.IN);
            return fade;
        }
        return null;
    }

}
