package com.abtingramian.abtinblr.feature.start;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.Fade;
import android.transition.Transition;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abtingramian.abtinblr.R;
import com.abtingramian.abtinblr.base.BaseFragment;
import com.abtingramian.abtinblr.cache.UserCache;
import com.abtingramian.abtinblr.feature.home.HomeActivity;
import com.abtingramian.abtinblr.util.SnackbarUtil;

import butterknife.OnClick;
import de.devland.esperandro.Esperandro;

public class StartFragment extends BaseFragment {

    UserCache userCache;

    public static StartFragment newInstance() {
        Bundle args = new Bundle();
        StartFragment fragment = new StartFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userCache = Esperandro.getPreferences(UserCache.class, getContext().getApplicationContext());
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
        userCache.loggedIn(true);
        startActivity(HomeActivity.newIntent(getActivity()));
    }

    @OnClick(R.id.get_started)
    void getStarted() {
        SnackbarUtil.showSnackbar(getContext().getApplicationContext(), getView(), R.string.coming_soon);
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
