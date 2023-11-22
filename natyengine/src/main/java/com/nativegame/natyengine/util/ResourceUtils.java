package com.nativegame.natyengine.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;

import androidx.core.content.res.ResourcesCompat;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class ResourceUtils {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    private ResourceUtils() {
    }
    //========================================================

    //--------------------------------------------------------
    // Static methods
    //--------------------------------------------------------
    public static Bitmap getBitmap(Context context, int drawableId) {
        return ((BitmapDrawable) context.getResources().getDrawable(drawableId)).getBitmap();
    }

    public static int getColor(Context context, int colorId) {
        return context.getResources().getColor(colorId);
    }

    public static Typeface getFont(Context context, int fontId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return context.getResources().getFont(fontId);
        } else {
            return ResourcesCompat.getFont(context, fontId);
        }
    }

    public static float getDimension(Context context, int dimensionId) {
        return context.getResources().getDimension(dimensionId);
    }

    public static String getString(Context context, int stringId) {
        return context.getResources().getString(stringId);
    }

    public static String getString(Context context, int stringId, Object... args) {
        return context.getResources().getString(stringId, args);
    }

    public static float getFloat(Context context, int floatId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            return context.getResources().getFloat(floatId);
        } else {
            return ResourcesCompat.getFloat(context.getResources(), floatId);
        }
    }

    public static int getInteger(Context context, int integerId) {
        return context.getResources().getInteger(integerId);
    }
    //========================================================

}
