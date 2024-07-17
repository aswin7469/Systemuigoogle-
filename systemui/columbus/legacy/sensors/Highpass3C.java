package com.google.android.systemui.columbus.legacy.sensors;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
