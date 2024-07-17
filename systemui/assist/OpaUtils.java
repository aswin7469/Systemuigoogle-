package com.google.android.systemui.assist;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.util.ArraySet;
import android.view.RenderNodeAnimator;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class OpaUtils {
    public static final Interpolator INTERPOLATOR_40_40 = new PathInterpolator(0.4f, 0.0f, 0.6f, 1.0f);
    public static final Interpolator INTERPOLATOR_40_OUT = new PathInterpolator(0.4f, 0.0f, 1.0f, 1.0f);

    public static ObjectAnimator getAlphaObjectAnimator(int i, View view, Interpolator interpolator) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{1.0f});
        ofFloat.setInterpolator(interpolator);
        ofFloat.setDuration((long) 50);
        ofFloat.setStartDelay((long) i);
        return ofFloat;
    }

    public static Animator getLongestAnim(ArraySet arraySet) {
        long j = Long.MIN_VALUE;
        Animator animator = null;
        for (int size = arraySet.size() - 1; size >= 0; size--) {
            Animator animator2 = (Animator) arraySet.valueAt(size);
            if (animator2.getTotalDuration() > j) {
                j = animator2.getTotalDuration();
                animator = animator2;
            }
        }
        return animator;
    }

    public static ObjectAnimator getScaleObjectAnimator(View view, float f, int i, Interpolator interpolator) {
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat(View.SCALE_X, new float[]{f}), PropertyValuesHolder.ofFloat(View.SCALE_Y, new float[]{f})});
        ofPropertyValuesHolder.setDuration((long) i);
        ofPropertyValuesHolder.setInterpolator(interpolator);
        return ofPropertyValuesHolder;
    }

    public static Animator getTranslationAnimatorX(int i, View view, Interpolator interpolator) {
        RenderNodeAnimator renderNodeAnimator = new RenderNodeAnimator(0, 0.0f);
        renderNodeAnimator.setTarget(view);
        renderNodeAnimator.setInterpolator(interpolator);
        renderNodeAnimator.setDuration((long) i);
        return renderNodeAnimator;
    }

    public static ObjectAnimator getTranslationObjectAnimatorX(View view, Interpolator interpolator, float f, float f2, int i) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.X, new float[]{f2, f + f2});
        ofFloat.setInterpolator(interpolator);
        ofFloat.setDuration((long) i);
        return ofFloat;
    }

    public static ObjectAnimator getTranslationObjectAnimatorY(View view, Interpolator interpolator, float f, float f2) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.Y, new float[]{f2, f + f2});
        ofFloat.setInterpolator(interpolator);
        ofFloat.setDuration((long) 350);
        return ofFloat;
    }
}
