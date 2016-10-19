package com.abtingramian.abtinblr;

import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Transition;

import com.abtingramian.abtinblr.feature.home.HomeActivity;
import com.abtingramian.abtinblr.feature.start.StartActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (false) {
            startActivity(HomeActivity.newIntent(this));
        } else {
            startActivity(StartActivity.newIntent(this));
        }
        finish();
    }

    @Override
    public Transition getEnterTransition() {
        return null;
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public Transition getExitTransition() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Fade fade = new Fade(Fade.OUT);
            fade.setDuration(1000);
            fade.setStartDelay(500);
            return fade;
        }
        return null;
    }

}
