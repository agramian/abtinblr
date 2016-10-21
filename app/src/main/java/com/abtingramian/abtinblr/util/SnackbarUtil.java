package com.abtingramian.abtinblr.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.abtingramian.abtinblr.R;

public class SnackbarUtil {

    public static void showSnackbarShort(@NonNull Context context, @NonNull View view, @StringRes int messageResId) {
        showSnackbar(context, view, messageResId, Snackbar.LENGTH_SHORT);
    }

    public static void showSnackbarLong(@NonNull Context context, @NonNull View view, @StringRes int messageResId) {
        showSnackbar(context, view, messageResId, Snackbar.LENGTH_LONG);
    }

    public static void showSnackbarIndefinite(@NonNull Context context, @NonNull View view, @StringRes int messageResId) {
        showSnackbar(context, view, messageResId, Snackbar.LENGTH_INDEFINITE);
    }

    public static void showSnackbar(@NonNull Context context, @NonNull View view, @StringRes int messageResId, int length) {
        Snackbar snackbar = Snackbar.make(view, messageResId, length);
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(context, R.color.button_color_transparent));
        TextView textView = (TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(context, R.color.button_text_color));
        snackbar.show();
    }

}
