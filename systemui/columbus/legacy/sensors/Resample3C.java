package com.google.android.systemui.columbus.legacy.sensors;

import androidx.compose.animation.AndroidFlingSpline$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class Resample3C {
    public long mInterval = 0;
    public long mRawLastT;
    public float mRawLastX;
    public float mRawLastY;
    public float mRawLastZ;
    public long mResampledLastT;
    public float mResampledThisX = 0.0f;
    public float mResampledThisY;
    public float mResampledThisZ;

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.android.systemui.columbus.legacy.sensors.Sample3C, java.lang.Object] */
    public final Sample3C getResults() {
        float f = this.mResampledThisX;
        float f2 = this.mResampledThisY;
        float f3 = this.mResampledThisZ;
        long j = this.mResampledLastT;
        ? obj = new Object();
        Point3f point3f = new Point3f(0.0f, 0.0f, 0.0f);
        obj.mPoint = point3f;
        point3f.x = f;
        point3f.y = f2;
        point3f.z = f3;
        obj.mT = j;
        return obj;
    }

    public final boolean update(float f, float f2, float f3, long j) {
        long j2 = this.mRawLastT;
        if (j == j2) {
            return false;
        }
        long j3 = this.mInterval;
        if (j3 <= 0) {
            j3 = j - j2;
        }
        long j4 = this.mResampledLastT + j3;
        if (j < j4) {
            this.mRawLastT = j;
            this.mRawLastX = f;
            this.mRawLastY = f2;
            this.mRawLastZ = f3;
            return false;
        }
        float f4 = ((float) (j4 - j2)) / ((float) (j - j2));
        float f5 = this.mRawLastX;
        this.mResampledThisX = AndroidFlingSpline$$ExternalSyntheticOutline0.m(f, f5, f4, f5);
        float f6 = this.mRawLastY;
        this.mResampledThisY = AndroidFlingSpline$$ExternalSyntheticOutline0.m(f2, f6, f4, f6);
        float f7 = this.mRawLastZ;
        this.mResampledThisZ = AndroidFlingSpline$$ExternalSyntheticOutline0.m(f3, f7, f4, f7);
        this.mResampledLastT = j4;
        if (j2 >= j4) {
            return true;
        }
        this.mRawLastT = j;
        this.mRawLastX = f;
        this.mRawLastY = f2;
        this.mRawLastZ = f3;
        return true;
    }
}
