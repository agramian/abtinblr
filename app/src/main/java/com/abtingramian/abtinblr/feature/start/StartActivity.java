package com.abtingramian.abtinblr.feature.start;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.abtingramian.abtinblr.R;
import com.abtingramian.abtinblr.base.SingleFragmentActivity;

public class StartActivity extends SingleFragmentActivity {

    public static Intent newIntent(Activity activity) {
        return new Intent(activity, StartActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, StartFragment.newInstance())
                    .commit();
        }
    }

}
