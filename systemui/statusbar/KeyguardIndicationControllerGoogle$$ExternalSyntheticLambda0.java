package com.google.android.systemui.statusbar;

import com.android.systemui.tuner.TunerService;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class KeyguardIndicationControllerGoogle$$ExternalSyntheticLambda0 implements TunerService.Tunable {
    public final /* synthetic */ KeyguardIndicationControllerGoogle f$0;

    public /* synthetic */ KeyguardIndicationControllerGoogle$$ExternalSyntheticLambda0(KeyguardIndicationControllerGoogle keyguardIndicationControllerGoogle) {
        this.f$0 = keyguardIndicationControllerGoogle;
    }

    public final void onTuningChanged(String str, String str2) {
        this.f$0.refreshAdaptiveChargingEnabled();
    }
}
