package com.google.android.systemui.statusbar;

import android.provider.DeviceConfig;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class KeyguardIndicationControllerGoogle$$ExternalSyntheticLambda1 implements DeviceConfig.OnPropertiesChangedListener {
    public final /* synthetic */ KeyguardIndicationControllerGoogle f$0;

    public /* synthetic */ KeyguardIndicationControllerGoogle$$ExternalSyntheticLambda1(KeyguardIndicationControllerGoogle keyguardIndicationControllerGoogle) {
        this.f$0 = keyguardIndicationControllerGoogle;
    }

    public final void onPropertiesChanged(DeviceConfig.Properties properties) {
        KeyguardIndicationControllerGoogle keyguardIndicationControllerGoogle = this.f$0;
        keyguardIndicationControllerGoogle.getClass();
        if (properties.getKeyset().contains("adaptive_charging_enabled")) {
            keyguardIndicationControllerGoogle.triggerAdaptiveChargingStatusUpdate();
        }
    }
}
