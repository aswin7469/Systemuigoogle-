package com.google.android.material.color;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import com.google.android.material.resources.MaterialAttributes;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class MaterialColors {
    public static int compositeARGBWithAlpha(int i, int i2) {
        return ColorUtils.setAlphaComponent(i, (Color.alpha(i) * i2) / 255);
    }

    public static int getColor(View view, int i) {
        Context context = view.getContext();
        TypedValue resolveTypedValueOrThrow = MaterialAttributes.resolveTypedValueOrThrow(view.getContext(), view.getClass().getCanonicalName(), i);
        int i2 = resolveTypedValueOrThrow.resourceId;
        if (i2 == 0) {
            return resolveTypedValueOrThrow.data;
        }
        Object obj = ContextCompat.sLock;
        return context.getColor(i2);
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

    public static int getColor(Context context, int i, int i2) {
        TypedValue resolve = MaterialAttributes.resolve(i, context);
        if (resolve == null) {
            return i2;
        }
        int i3 = resolve.resourceId;
        if (i3 == 0) {
            return resolve.data;
        }
        Object obj = ContextCompat.sLock;
        return context.getColor(i3);
    }
}
