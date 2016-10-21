package com.abtingramian.abtinblr.feature.placeholder;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abtingramian.abtinblr.R;
import com.abtingramian.abtinblr.base.BaseFragment;
import com.abtingramian.abtinblr.cache.UserCache;
import com.abtingramian.abtinblr.util.DrawableUtil;

import butterknife.BindView;
import butterknife.OnClick;
import de.devland.esperandro.Esperandro;

public class AccountFragment extends BaseFragment {

    @BindView(R.id.sign_out)
    TextView signOut;
    UserCache userCache;

    public static AccountFragment newInstance() {
        Bundle args = new Bundle();
        AccountFragment fragment = new AccountFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userCache = Esperandro.getPreferences(UserCache.class, getContext().getApplicationContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        signOut.setCompoundDrawablesWithIntrinsicBounds(DrawableUtil.createVectorDrawable(getContext(),
                    R.drawable.ic_power_settings_new_black_24dp,
                    R.color.button_text_color),
                null,
                null,
                null);
    }

    @OnClick(R.id.sign_out)
    void signOut() {
        new AlertDialog.Builder(getActivity(), R.style.AlertDialog)
                .setMessage(R.string.sign_out_confirmation)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        userCache.loggedIn(false);
                        getActivity().supportFinishAfterTransition();
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .show();
    }

}
