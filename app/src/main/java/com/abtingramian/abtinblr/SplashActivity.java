package com.abtingramian.abtinblr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.abtingramian.abtinblr.feature.home.HomeActivity;
import com.abtingramian.abtinblr.feature.start.StartActivity;

public class SplashActivity extends AppCompatActivity {

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

}
