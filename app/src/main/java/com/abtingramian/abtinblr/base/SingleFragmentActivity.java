package com.abtingramian.abtinblr.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.abtingramian.abtinblr.R;

import butterknife.ButterKnife;

public class SingleFragmentActivity extends BaseActivity {

    protected Fragment currentFragment;

    public void pushFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(fragment.getClass().getSimpleName())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        ButterKnife.bind(this);
    }

    protected @LayoutRes int getLayoutResId() {
        return R.layout.activity_single_fragment;
    }

    protected void updateCurrentFragment() {
        final Fragment fragment = getCurrentFragment();
        if (fragment != null) {
            currentFragment = fragment;
        }
    }

    private Fragment getCurrentFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0 ) {
            return getSupportFragmentManager().findFragmentByTag(
                    getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 1).getName());
        }
        return null;
    }

    private void clearFragmentBackStack() {
        FragmentManager fm = getSupportFragmentManager();
        for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
    }

    public interface ITitle {
        @StringRes
        int titleRes();
    }

    public interface IFab {
        void onFabClicked();
    }

}
