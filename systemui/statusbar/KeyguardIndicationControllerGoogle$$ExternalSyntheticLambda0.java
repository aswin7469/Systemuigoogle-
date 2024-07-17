package com.google.android.systemui.statusbar;

import com.android.systemui.tuner.TunerService;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class KeyguardIndicationControllerGoogle$$ExternalSyntheticLambda0 implements TunerService.Tunable {
    public final /* synthetic */ KeyguardIndicationControllerGoogle f$0;

    public /* synthetic */ KeyguardIndicationControllerGoogle$$ExternalSyntheticLambda0(KeyguardIndicationControllerGoogle keyguardIndicationControllerGoogle) {
        this.f$0 = keyguardIndicationControllerGoogle;
    }

    public final void onTuningChanged(String str, String str2) {
        this.f$0.refreshAdaptiveChargingEnabled();
    }
}
