package com.google.android.systemui.columbus.legacy.sensors;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class Lowpass3C extends Lowpass1C {
    public final Lowpass1C mLowpassX = new Lowpass1C();
    public final Lowpass1C mLowpassY = new Lowpass1C();
    public final Lowpass1C mLowpassZ = new Lowpass1C();

    public final void setPara() {
        this.mLowpassX.mPara = 1.0f;
        this.mLowpassY.mPara = 1.0f;
        this.mLowpassZ.mPara = 1.0f;
    }
}
