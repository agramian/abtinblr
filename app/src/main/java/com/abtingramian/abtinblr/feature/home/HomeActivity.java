package com.abtingramian.abtinblr.feature.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.abtingramian.abtinblr.R;
import com.abtingramian.abtinblr.base.SingleFragmentActivity;
import com.abtingramian.abtinblr.util.ConnectionStateUtil;
import com.abtingramian.abtinblr.util.DrawableUtil;
import com.abtingramian.abtinblr.util.SnackbarUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeActivity extends SingleFragmentActivity {

    @BindView(R.id.main_content)
    View mainContent;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.fab_button)
    FloatingActionButton fab;
    HomePagerAdapter homePagerAdapter;

    public static Intent newIntent(Activity activity) {
        return new Intent(activity, HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homePagerAdapter = new HomePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(homePagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        setTabIcons();
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout) {
            @Override
            public void onPageSelected(int position) {
                updateFabVisibility();
                setTabIcons();
                checkConnection();
            }
        });
        // fab button
        fab.setImageDrawable(DrawableUtil.createVectorDrawable(this, R.drawable.ic_create_black_24dp, R.color.button_text_color));
        updateFabVisibility();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkConnection();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_home;
    }

    private void setTabIcons() {
        tabLayout.getTabAt(HomePagerAdapter.PAGE_DASHBOARD).setIcon(getTintedTabIcon(R.drawable.ic_account_balance_black_24dp, HomePagerAdapter.PAGE_DASHBOARD));
        tabLayout.getTabAt(HomePagerAdapter.PAGE_SEARCH).setIcon(getTintedTabIcon(R.drawable.ic_search_black_24dp, HomePagerAdapter.PAGE_SEARCH));
        tabLayout.getTabAt(HomePagerAdapter.PAGE_PROFILE).setIcon(getTintedTabIcon(R.drawable.ic_message_black_24dp, HomePagerAdapter.PAGE_PROFILE));
        tabLayout.getTabAt(HomePagerAdapter.PAGE_ACCOUNT).setIcon(getTintedTabIcon(R.drawable.ic_person_black_24dp, HomePagerAdapter.PAGE_ACCOUNT));
    }

    private VectorDrawableCompat getTintedTabIcon(int tabIconResId, int iconTabPosition) {
        return DrawableUtil.createVectorDrawable(getApplicationContext(), tabIconResId, (tabLayout.getSelectedTabPosition() == iconTabPosition) ? R.color.tab_active : R.color.tab_inactive);
    }

    private void updateFabVisibility() {
        fab.setVisibility(homePagerAdapter.getCurrentFragment() instanceof IFab ? View.VISIBLE : View.GONE);
    }

    public void checkConnection() {
        if (!ConnectionStateUtil.isConnected(getApplicationContext())) {
            SnackbarUtil.showSnackbarIndefinite(getApplicationContext(), mainContent, R.string.offline_mode);
        }
    }

    @OnClick(R.id.fab_button)
    void fabClicked() {
        if (homePagerAdapter.getCurrentFragment() instanceof IFab) {
            ((IFab) homePagerAdapter.getCurrentFragment()).onFabClicked();
        }
    }

}
