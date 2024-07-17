package com.google.android.systemui.assist.uihints;

import android.os.Handler;
import android.os.Looper;
import android.view.CompositionSamplingListener;
import com.google.android.systemui.assist.uihints.NgaMessageHandler;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class LightnessProvider implements NgaMessageHandler.CardInfoListener {
    public boolean mCardVisible = false;
    public int mColorMode = 0;
    public final AnonymousClass1 mColorMonitor = new CompositionSamplingListener(new Object()) {
        public final void onSampleCollected(float f) {
            LightnessProvider.this.mUiHandler.post(new LightnessProvider$1$$ExternalSyntheticLambda0(this, f));
        }
    };
    public boolean mIsMonitoringColor = false;
    public NgaUiController$$ExternalSyntheticLambda2 mListener;
    public boolean mMuted = false;
    public final Handler mUiHandler = new Handler(Looper.getMainLooper());

    public final void onCardInfo(int i, boolean z, boolean z2, boolean z3) {
        this.mCardVisible = z;
        this.mColorMode = i;
        NgaUiController$$ExternalSyntheticLambda2 ngaUiController$$ExternalSyntheticLambda2 = this.mListener;
        if (ngaUiController$$ExternalSyntheticLambda2 != null && z) {
            if (i == 1) {
                ngaUiController$$ExternalSyntheticLambda2.onLightnessUpdate(0.0f);
            } else if (i == 2) {
                ngaUiController$$ExternalSyntheticLambda2.onLightnessUpdate(1.0f);
            }
        }
    }
}
