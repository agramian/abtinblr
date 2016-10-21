package com.abtingramian.abtinblr.feature.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.abtingramian.abtinblr.feature.dashboard.DashboardFragment;
import com.abtingramian.abtinblr.feature.placeholder.AccountFragment;
import com.abtingramian.abtinblr.feature.placeholder.PlaceholderFragment;

public class HomePagerAdapter extends FragmentPagerAdapter {

    public static final int PAGE_DASHBOARD = 0;
    public static final int PAGE_SEARCH = 1;
    public static final int PAGE_PROFILE = 2;
    public static final int PAGE_ACCOUNT = 3;
    static final int NUM_PAGES = 4;

    Fragment currentFragment;

    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case PAGE_DASHBOARD:
                return DashboardFragment.newInstance();
            case PAGE_ACCOUNT:
                return AccountFragment.newInstance();
            default:
                return PlaceholderFragment.newInstance();
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object object = super.instantiateItem(container, position);
        if (object instanceof Fragment) {
            currentFragment = (Fragment) object;
        }
        return object;
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }

    public Fragment getCurrentFragment() {
        return currentFragment;
    }

}
