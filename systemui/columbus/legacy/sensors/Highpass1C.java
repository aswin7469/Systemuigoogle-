package com.google.android.systemui.columbus.legacy.sensors;

import androidx.compose.animation.AndroidFlingSpline$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
