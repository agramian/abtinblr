package com.abtingramian.abtinblr.feature.start;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Transition;

import com.abtingramian.abtinblr.BaseActivity;
import com.abtingramian.abtinblr.R;
import com.abtingramian.abtinblr.feature.home.HomeActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartActivity extends BaseActivity {

    public static Intent newIntent(Activity activity) {
        return new Intent(activity, StartActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.sign_in)
    void signIn() {
        startActivity(new Intent(this, HomeActivity.class));
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
