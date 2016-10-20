package com.abtingramian.abtinblr.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.graphics.drawable.DrawableCompat;

public class DrawableUtil {

    public static Drawable tint(Context context, int drawableResId, int colorResId) {
        return tint(context.getResources().getDrawable(drawableResId), context.getResources().getColor(colorResId));
    }

    public static Drawable tint(Context context, final Drawable drawable, int colorResId) {
        return tint(drawable, context.getResources().getColor(colorResId));
    }

    public static Drawable tint(final Drawable drawable, int colorResId) {
        if (drawable != null) {
            final Drawable wrapped = DrawableCompat.wrap(drawable);
            drawable.mutate();
            DrawableCompat.setTint(wrapped, colorResId);
        }
        return drawable;
    }

    public static VectorDrawableCompat createVectorDrawable(@NonNull Context context, int drawableResId) {
        return createVectorDrawable(context, drawableResId, null);
    }

    public static VectorDrawableCompat createVectorDrawable(@NonNull Context context, int drawableResId, Integer colorResId) {
        VectorDrawableCompat vectorDrawableCompat = VectorDrawableCompat.create(context.getResources(), drawableResId, null);
        if (colorResId != null) {
            vectorDrawableCompat.setTint(context.getResources().getColor(colorResId));
        }
        return vectorDrawableCompat;
    }

}
