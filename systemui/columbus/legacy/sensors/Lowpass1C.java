package com.google.android.systemui.columbus.legacy.sensors;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class Lowpass1C {
    public float mLastX = 0.0f;
    public float mPara = 1.0f;

    public final float update(float f) {
        float f2 = this.mPara;
        if (f2 == 1.0f) {
            return f;
        }
        float f3 = (f2 * f) + ((1.0f - f2) * this.mLastX);
        this.mLastX = f3;
        return f3;
    }
}
