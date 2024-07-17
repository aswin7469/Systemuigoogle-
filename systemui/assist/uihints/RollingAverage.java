package com.google.android.systemui.assist.uihints;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class RollingAverage {
    public int mIndex;
    public final float[] mSamples;
    public float mTotal = 0.0f;

    public RollingAverage() {
        this.mIndex = 0;
        this.mSamples = new float[3];
        for (int i = 0; i < 3; i++) {
            this.mSamples[i] = 0.0f;
        }
    }

    public final void add(float f) {
        float f2 = this.mTotal;
        int i = this.mIndex;
        float[] fArr = this.mSamples;
        fArr[i] = f;
        this.mTotal = (f2 - fArr[i]) + f;
        int i2 = i + 1;
        this.mIndex = i2;
        if (i2 == 3) {
            this.mIndex = 0;
        }
    }
}
