package com.google.android.systemui.columbus.legacy.sensors;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
