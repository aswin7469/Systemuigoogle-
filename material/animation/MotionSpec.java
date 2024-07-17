package com.google.android.material.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import androidx.collection.SimpleArrayMap;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class MotionSpec {
    public final SimpleArrayMap propertyValues = new SimpleArrayMap();
    public final SimpleArrayMap timings = new SimpleArrayMap();

    public static MotionSpec createFromResource(int i, Context context) {
        try {
            Animator loadAnimator = AnimatorInflater.loadAnimator(context, i);
            if (loadAnimator instanceof AnimatorSet) {
                return createSpecFromAnimators(((AnimatorSet) loadAnimator).getChildAnimations());
            }
            if (loadAnimator == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(loadAnimator);
            return createSpecFromAnimators(arrayList);
        } catch (Exception e) {
            Log.w("MotionSpec", "Can't load animation resource ID #0x" + Integer.toHexString(i), e);
            return null;
        }
    }

    /* JADX WARNING: type inference failed for: r6v1, types: [java.lang.Object, com.google.android.material.animation.MotionTiming] */
    public static MotionSpec createSpecFromAnimators(List list) {
        MotionSpec motionSpec = new MotionSpec();
        int size = list.size();
        int i = 0;
        while (i < size) {
            Animator animator = (Animator) list.get(i);
            if (animator instanceof ObjectAnimator) {
                ObjectAnimator objectAnimator = (ObjectAnimator) animator;
                motionSpec.propertyValues.put(objectAnimator.getPropertyName(), objectAnimator.getValues());
                String propertyName = objectAnimator.getPropertyName();
                long startDelay = objectAnimator.getStartDelay();
                long duration = objectAnimator.getDuration();
                TimeInterpolator interpolator = objectAnimator.getInterpolator();
                if ((interpolator instanceof AccelerateDecelerateInterpolator) || interpolator == null) {
                    interpolator = AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR;
                } else if (interpolator instanceof AccelerateInterpolator) {
                    interpolator = AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR;
                } else if (interpolator instanceof DecelerateInterpolator) {
                    interpolator = AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR;
                }
                ? obj = new Object();
                obj.repeatCount = 0;
                obj.repeatMode = 1;
                obj.delay = startDelay;
                obj.duration = duration;
                obj.interpolator = interpolator;
                obj.repeatCount = objectAnimator.getRepeatCount();
                obj.repeatMode = objectAnimator.getRepeatMode();
                motionSpec.timings.put(propertyName, obj);
                i++;
            } else {
                throw new IllegalArgumentException("Animator must be an ObjectAnimator: " + animator);
            }
        }
        return motionSpec;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MotionSpec)) {
            return false;
        }
        return this.timings.equals(((MotionSpec) obj).timings);
    }

    public final int hashCode() {
        return this.timings.hashCode();
    }

    public final String toString() {
        return "\n" + MotionSpec.class.getName() + '{' + Integer.toHexString(System.identityHashCode(this)) + " timings: " + this.timings + "}\n";
    }
}
