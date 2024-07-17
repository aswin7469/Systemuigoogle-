package com.google.android.material.ripple;

import android.content.res.ColorStateList;
import android.graphics.Color;
import androidx.core.graphics.ColorUtils;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class RippleUtils {
    public static final int[] FOCUSED_STATE_SET = {16842908};
    static final String LOG_TAG = "RippleUtils";
    public static final int[] PRESSED_STATE_SET = {16842919};
    public static final int[] SELECTED_PRESSED_STATE_SET = {16842913, 16842919};
    public static final int[] SELECTED_STATE_SET = {16842913};
    static final String TRANSPARENT_DEFAULT_COLOR_WARNING = "Use a non-transparent color for the default color as it will be used to finish ripple animations.";

    public static int getColorForState(ColorStateList colorStateList, int[] iArr) {
        int i;
        if (colorStateList != null) {
            i = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
        } else {
            i = 0;
        }
        return ColorUtils.setAlphaComponent(i, Math.min(Color.alpha(i) * 2, 255));
    }

    public static boolean shouldDrawRippleCompat(int[] iArr) {
        boolean z = false;
        boolean z2 = false;
        for (int i : iArr) {
            if (i == 16842910) {
                z = true;
            } else if (i == 16842908 || i == 16842919 || i == 16843623) {
                z2 = true;
            }
        }
        if (!z || !z2) {
            return false;
        }
        return true;
    }
}
