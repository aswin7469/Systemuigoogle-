package com.google.android.systemui.columbus.legacy.sensors;

import androidx.compose.animation.AndroidFlingSpline$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class Highpass1C {
    public float mLastX = 0.0f;
    public float mLastY = 0.0f;
    public float mPara = 1.0f;

    public final float update(float f) {
        float f2 = this.mPara;
        if (f2 == 1.0f) {
            return f;
        }
        float m = AndroidFlingSpline$$ExternalSyntheticOutline0.m(f, this.mLastX, f2, this.mLastY * f2);
        this.mLastY = m;
        this.mLastX = f;
        return m;
    }
}
