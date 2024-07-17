package com.google.android.systemui.columbus.legacy.sensors;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
