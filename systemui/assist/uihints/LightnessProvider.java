package com.google.android.systemui.assist.uihints;

import android.os.Handler;
import android.os.Looper;
import android.view.CompositionSamplingListener;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class LightnessProvider {
    public final AnonymousClass1 mColorMonitor = new CompositionSamplingListener(new Object()) {
        public final void onSampleCollected(float f) {
            LightnessProvider.this.mUiHandler.post(new LightnessProvider$1$$ExternalSyntheticLambda0(this, f));
        }
    };
    public boolean mIsMonitoringColor = false;
    public NgaUiController$$ExternalSyntheticLambda6 mListener;
    public boolean mMuted = false;
    public final Handler mUiHandler = new Handler(Looper.getMainLooper());
}
