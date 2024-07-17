package com.google.android.systemui.elmyra.sensors.config;

import android.net.Uri;
import com.android.systemui.DejankUtils;
import com.google.android.systemui.elmyra.sensors.config.GestureConfiguration;
import java.util.function.Consumer;
import java.util.function.Supplier;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class GestureConfiguration$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ GestureConfiguration f$0;

    public /* synthetic */ GestureConfiguration$$ExternalSyntheticLambda0(GestureConfiguration gestureConfiguration, int i) {
        this.$r8$classId = i;
        this.f$0 = gestureConfiguration;
    }

    public final void accept(Object obj) {
        int i = this.$r8$classId;
        float f = 0.5f;
        GestureConfiguration gestureConfiguration = this.f$0;
        switch (i) {
            case 0:
                ScreenStateAdjustment screenStateAdjustment = (ScreenStateAdjustment) obj;
                gestureConfiguration.getClass();
                Float f2 = (Float) DejankUtils.whitelistIpcs((Supplier) new GestureConfiguration$$ExternalSyntheticLambda1(gestureConfiguration));
                float floatValue = f2.floatValue();
                if (GestureConfiguration.SENSITIVITY_RANGE.contains(f2)) {
                    f = floatValue;
                }
                gestureConfiguration.mSensitivity = f;
                GestureConfiguration.Listener listener = gestureConfiguration.mListener;
                if (listener != null) {
                    listener.onGestureConfigurationChanged(gestureConfiguration);
                    return;
                }
                return;
            case 1:
                ((ScreenStateAdjustment) obj).mCallback = gestureConfiguration.mAdjustmentCallback;
                return;
            default:
                Uri uri = (Uri) obj;
                gestureConfiguration.getClass();
                Float f3 = (Float) DejankUtils.whitelistIpcs((Supplier) new GestureConfiguration$$ExternalSyntheticLambda1(gestureConfiguration));
                float floatValue2 = f3.floatValue();
                if (GestureConfiguration.SENSITIVITY_RANGE.contains(f3)) {
                    f = floatValue2;
                }
                gestureConfiguration.mSensitivity = f;
                GestureConfiguration.Listener listener2 = gestureConfiguration.mListener;
                if (listener2 != null) {
                    listener2.onGestureConfigurationChanged(gestureConfiguration);
                    return;
                }
                return;
        }
    }
}
