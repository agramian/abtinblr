package com.abtingramian.abtinblr;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.AnimRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;
import android.view.Window;

public class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setupTransitions();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupTransitions();
    }

    @Override
    public void startActivity(Intent intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
            super.startActivity(intent, bundle);
        } else {
            super.startActivity(intent);
            overridePendingTransition(getEnterAnimationRes(), getExitAnimationRes());
        }
    }
    public Transition getEnterTransition() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide slide = new Slide(Gravity.END);
            return slide;
        }
        return null;
    }

    public Transition getExitTransition() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide slide = new Slide(Gravity.START);
            return slide;
        }
        return null;
    }

    public Transition getReenterTransition() {
        return null;
    }

    public Transition getReturnTransition() {
        return getEnterTransition();
    }

    public @AnimRes int getEnterAnimationRes() {
        return R.anim.right_in;
    }

    public @AnimRes int getExitAnimationRes() {
        return R.anim.left_out;
    }

    public void setupTransitions() {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setAllowEnterTransitionOverlap(true);
            getWindow().setAllowReturnTransitionOverlap(true);
            getWindow().setTransitionBackgroundFadeDuration(1000);
            if (getEnterTransition() != null) {
                getWindow().setEnterTransition(getEnterTransition());
            }
            if (getExitTransition() != null) {
                getWindow().setExitTransition(getExitTransition());
            }
            if (getReenterTransition() != null) {
                getWindow().setReenterTransition(getReenterTransition());
            }
            if (getReturnTransition() != null) {
                getWindow().setReturnTransition(getReturnTransition());
            }
        }
    }

}
