package com.google.android.material.resources;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.content.ContextCompat;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class MaterialResources {
    public static ColorStateList getColorStateList(Context context, TypedArray typedArray, int i) {
        int resourceId;
        ColorStateList colorStateList;
        if (!typedArray.hasValue(i) || (resourceId = typedArray.getResourceId(i, 0)) == 0 || (colorStateList = ContextCompat.getColorStateList(resourceId, context)) == null) {
            return typedArray.getColorStateList(i);
        }
        return colorStateList;
    }

    public static Drawable getDrawable(Context context, TypedArray typedArray, int i) {
        int resourceId;
        Drawable drawable;
        if (!typedArray.hasValue(i) || (resourceId = typedArray.getResourceId(i, 0)) == 0 || (drawable = AppCompatResources.getDrawable(resourceId, context)) == null) {
            return typedArray.getDrawable(i);
        }
        return drawable;
    }

    public static boolean isFontScaleAtLeast1_3(Context context) {
        if (context.getResources().getConfiguration().fontScale >= 1.3f) {
            return true;
        }
        return false;
    }

    public static ColorStateList getColorStateList(Context context, TintTypedArray tintTypedArray, int i) {
        int resourceId;
        ColorStateList colorStateList;
        TypedArray typedArray = tintTypedArray.mWrapped;
        if (!typedArray.hasValue(i) || (resourceId = typedArray.getResourceId(i, 0)) == 0 || (colorStateList = ContextCompat.getColorStateList(resourceId, context)) == null) {
            return tintTypedArray.getColorStateList(i);
        }
        return colorStateList;
    }
}
