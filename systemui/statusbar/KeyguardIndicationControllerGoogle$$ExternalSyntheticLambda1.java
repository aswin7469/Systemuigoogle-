package com.google.android.systemui.statusbar;

import android.provider.DeviceConfig;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
