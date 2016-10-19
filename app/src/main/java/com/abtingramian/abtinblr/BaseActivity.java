package com.abtingramian.abtinblr;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;
import android.view.Window;

public class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setAllowEnterTransitionOverlap(true);
            getWindow().setAllowReturnTransitionOverlap(true);
            // use transitions if available
            if (getEnterTransition() != null) {
                getWindow().setEnterTransition(getEnterTransition());
            }
            if (getExitTransition() != null) {
                getWindow().setExitTransition(getExitTransition());
            }
            if (getReenterTransition() != null) {
                getWindow().setReenterTransition(getEnterTransition());
            }
            if (getReturnTransition() != null) {
                getWindow().setReturnTransition(getExitTransition());
            }
        }
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
            startActivity(intent, bundle);
        } else {
            startActivity(intent);
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
        return null;
    }

}
