package com.abtingramian.abtinblr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (false) {
            // TODO if logged in start different activity
        } else {
            startActivity(MainActivity.newIntent(this));
        }
        finish();
    }

}
