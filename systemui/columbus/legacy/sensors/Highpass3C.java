package com.google.android.systemui.columbus.legacy.sensors;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class Highpass3C {
    public final Highpass1C mHighpassX = new Highpass1C();
    public final Highpass1C mHighpassY = new Highpass1C();
    public final Highpass1C mHighpassZ = new Highpass1C();

    public final void setPara() {
        this.mHighpassX.mPara = 0.05f;
        this.mHighpassY.mPara = 0.05f;
        this.mHighpassZ.mPara = 0.05f;
    }
}
