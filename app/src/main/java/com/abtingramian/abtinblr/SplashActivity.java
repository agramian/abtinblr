package com.abtingramian.abtinblr;

import android.os.Bundle;

import com.abtingramian.abtinblr.base.BaseActivity;
import com.abtingramian.abtinblr.cache.UserCache;
import com.abtingramian.abtinblr.feature.home.HomeActivity;
import com.abtingramian.abtinblr.feature.start.StartActivity;

import de.devland.esperandro.Esperandro;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserCache userCache = Esperandro.getPreferences(UserCache.class, this);
        if (userCache.loggedIn()) {
            startActivity(HomeActivity.newIntent(this), null);
        } else {
            startActivity(StartActivity.newIntent(this), null);
        }
        finish();
        overridePendingTransition(getEnterAnimationRes(), getExitAnimationRes());
    }

    @Override
    public int getEnterAnimationRes() {
        return 0;
    }

    @Override
    public int getExitAnimationRes() {
        return R.anim.fade_out;
    }

}
