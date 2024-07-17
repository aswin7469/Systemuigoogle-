package com.google.android.material.color;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import com.google.android.material.resources.MaterialAttributes;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class MaterialColors {
    public static int getColor(View view, int i) {
        Context context = view.getContext();
        TypedValue resolveTypedValueOrThrow = MaterialAttributes.resolveTypedValueOrThrow(view.getContext(), view.getClass().getCanonicalName(), i);
        int i2 = resolveTypedValueOrThrow.resourceId;
        if (i2 != 0) {
            return context.getColor(i2);
        }
        return resolveTypedValueOrThrow.data;
    }

    public static boolean isColorLight(int i) {
        if (i == 0 || ColorUtils.calculateLuminance(i) <= 0.5d) {
            return false;
        }
        return true;
    }

    public static int layer(int i, float f, int i2) {
        return ColorUtils.compositeColors(ColorUtils.setAlphaComponent(i2, Math.round(((float) Color.alpha(i2)) * f)), i);
    }
}
