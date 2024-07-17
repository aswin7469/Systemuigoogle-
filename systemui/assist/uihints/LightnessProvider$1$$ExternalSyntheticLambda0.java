package com.google.android.systemui.assist.uihints;

import com.google.android.systemui.assist.uihints.LightnessProvider;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class LightnessProvider$1$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ LightnessProvider.AnonymousClass1 f$0;
    public final /* synthetic */ float f$1;

    public /* synthetic */ LightnessProvider$1$$ExternalSyntheticLambda0(LightnessProvider.AnonymousClass1 r1, float f) {
        this.f$0 = r1;
        this.f$1 = f;
    }

    public final void run() {
        LightnessProvider.AnonymousClass1 r0 = this.f$0;
        float f = this.f$1;
        LightnessProvider lightnessProvider = LightnessProvider.this;
        NgaUiController$$ExternalSyntheticLambda2 ngaUiController$$ExternalSyntheticLambda2 = lightnessProvider.mListener;
        if (ngaUiController$$ExternalSyntheticLambda2 != null && !lightnessProvider.mMuted) {
            if (!lightnessProvider.mCardVisible || lightnessProvider.mColorMode == 0) {
                ngaUiController$$ExternalSyntheticLambda2.onLightnessUpdate(f);
            }
        }
    }
}
