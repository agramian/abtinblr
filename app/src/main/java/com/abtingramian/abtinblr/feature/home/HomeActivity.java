package com.abtingramian.abtinblr.feature.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.abtingramian.abtinblr.BaseActivity;
import com.abtingramian.abtinblr.R;

public class HomeActivity extends BaseActivity {

    private static final int MENU_SETTINGS = Menu.FIRST;
    Toolbar mToolbar;

    public static Intent newIntent(Activity activity) {
        return new Intent(activity, HomeActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, MENU_SETTINGS, 0, R.string.action_settings).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_SETTINGS:
                Snackbar.make(findViewById(android.R.id.content), R.string.action_settings, Snackbar.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

}
