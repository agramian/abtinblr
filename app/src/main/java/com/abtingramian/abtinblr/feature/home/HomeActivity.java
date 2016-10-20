package com.abtingramian.abtinblr.feature.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.abtingramian.abtinblr.R;
import com.abtingramian.abtinblr.base.SingleFragmentActivity;

import butterknife.BindView;

public class HomeActivity extends SingleFragmentActivity {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.fab_button)
    FloatingActionButton fab;

    public static Intent newIntent(Activity activity) {
        return new Intent(activity, HomeActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void updateCurrentFragment() {
        super.updateCurrentFragment();
        fab.setVisibility(currentFragment instanceof IFab ? View.VISIBLE : View.GONE);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_home;
    }
}
