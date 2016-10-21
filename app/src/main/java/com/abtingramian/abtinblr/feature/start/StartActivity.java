package com.abtingramian.abtinblr.feature.start;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.abtingramian.abtinblr.R;
import com.abtingramian.abtinblr.base.SingleFragmentActivity;
import com.abtingramian.abtinblr.cache.UserCache;
import com.abtingramian.abtinblr.feature.home.HomeActivity;

import de.devland.esperandro.Esperandro;

public class StartActivity extends SingleFragmentActivity {

    public static Intent newIntent(Activity activity) {
        return new Intent(activity, StartActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isUserLoggedIn() && savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, StartFragment.newInstance())
                    .commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        isUserLoggedIn();
    }

    private boolean isUserLoggedIn() {
        UserCache userCache = Esperandro.getPreferences(UserCache.class, this);
        if (userCache.loggedIn()) {
            startActivity(HomeActivity.newIntent(this), null);
            supportFinishAfterTransition();
            return true;
        }
        return false;
    }

}
