package com.google.android.systemui.assist.uihints;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class RollingAverage {
    public final float[] mSamples = new float[3];

    public RollingAverage() {
        for (int i = 0; i < 3; i++) {
            this.mSamples[i] = 0.0f;
        }
    }
}
