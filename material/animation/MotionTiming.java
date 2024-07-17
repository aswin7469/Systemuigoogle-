package com.google.android.material.animation;

import android.animation.TimeInterpolator;
import androidx.compose.runtime.Anchor$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class MotionTiming {
    public long delay;
    public long duration;
    public TimeInterpolator interpolator;
    public int repeatCount;
    public int repeatMode;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MotionTiming)) {
            return false;
        }
        MotionTiming motionTiming = (MotionTiming) obj;
        if (this.delay == motionTiming.delay && this.duration == motionTiming.duration && this.repeatCount == motionTiming.repeatCount && this.repeatMode == motionTiming.repeatMode) {
            return getInterpolator().getClass().equals(motionTiming.getInterpolator().getClass());
        }
        return false;
    }

    public final TimeInterpolator getInterpolator() {
        TimeInterpolator timeInterpolator = this.interpolator;
        if (timeInterpolator != null) {
            return timeInterpolator;
        }
        return AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR;
    }

    public final int hashCode() {
        long j = this.delay;
        long j2 = this.duration;
        return ((((getInterpolator().getClass().hashCode() + (((((int) (j ^ (j >>> 32))) * 31) + ((int) ((j2 >>> 32) ^ j2))) * 31)) * 31) + this.repeatCount) * 31) + this.repeatMode;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("\n");
        sb.append(MotionTiming.class.getName());
        sb.append('{');
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" delay: ");
        sb.append(this.delay);
        sb.append(" duration: ");
        sb.append(this.duration);
        sb.append(" interpolator: ");
        sb.append(getInterpolator().getClass());
        sb.append(" repeatCount: ");
        sb.append(this.repeatCount);
        sb.append(" repeatMode: ");
        return Anchor$$ExternalSyntheticOutline0.m(sb, this.repeatMode, "}\n");
    }
}
