package com.google.android.material.animation;

import android.animation.TimeInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import androidx.compose.animation.AndroidFlingSpline$$ExternalSyntheticOutline0;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class AnimationUtils {
    public static final TimeInterpolator DECELERATE_INTERPOLATOR = new DecelerateInterpolator();
    public static final FastOutLinearInInterpolator FAST_OUT_LINEAR_IN_INTERPOLATOR = new Object();
    public static final FastOutSlowInInterpolator FAST_OUT_SLOW_IN_INTERPOLATOR = new Object();
    public static final TimeInterpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
    public static final LinearOutSlowInInterpolator LINEAR_OUT_SLOW_IN_INTERPOLATOR = new Object();

    public static int lerp(int i, float f, int i2) {
        return Math.round(f * ((float) (i2 - i))) + i;
    }

    public static float lerp(float f, float f2, float f3, float f4, float f5) {
        if (f5 < f3) {
            return f;
        }
        return f5 > f4 ? f2 : AndroidFlingSpline$$ExternalSyntheticOutline0.m(f2, f, (f5 - f3) / (f4 - f3), f);
    }
}
