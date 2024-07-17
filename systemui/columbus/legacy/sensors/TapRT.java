package com.google.android.systemui.columbus.legacy.sensors;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class TapRT {
    public Deque mAccXs;
    public Deque mAccYs;
    public Deque mAccZs;
    public TfClassifier mClassifier;
    public ArrayList mFeatureVector;
    public boolean mGotAcc;
    public boolean mGotGyro;
    public Deque mGyroXs;
    public Deque mGyroYs;
    public Deque mGyroZs;
    public Highpass3C mHighpassAcc;
    public Highpass3C mHighpassGyro;
    public Lowpass3C mLowpassAcc;
    public Lowpass3C mLowpassGyro;
    public int mNumberFeature;
    public PeakDetector mPeakDetector;
    public Resample3C mResampleAcc;
    public Resample3C mResampleGyro;
    public int mResult;
    public int mSizeFeatureWindow;
    public long mSizeWindowNs;
    public Slope3C mSlopeAcc;
    public Slope3C mSlopeGyro;
    public long mSyncTime;
    public Deque mTimestampsBackTap;
    public PeakDetector mValleyDetector;
    public boolean mWasPeakApproaching;

    public final void addToFeatureVector(Deque deque, int i, int i2) {
        Iterator it = ((ArrayDeque) deque).iterator();
        int i3 = 0;
        while (it.hasNext()) {
            if (i3 < i) {
                it.next();
            } else if (i3 < this.mSizeFeatureWindow + i) {
                this.mFeatureVector.set(i2, (Float) it.next());
                i2++;
            } else {
                return;
            }
            i3++;
        }
    }

    public final void reset$com$google$android$systemui$columbus$legacy$sensors$EventIMURT() {
        this.mAccXs.clear();
        this.mAccYs.clear();
        this.mAccZs.clear();
        this.mGyroXs.clear();
        this.mGyroYs.clear();
        this.mGyroZs.clear();
        this.mGotAcc = false;
        this.mGotGyro = false;
        this.mSyncTime = 0;
    }
}
