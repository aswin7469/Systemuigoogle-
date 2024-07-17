package com.google.android.systemui.elmyra.sensors.config;

import android.net.Uri;
import com.android.systemui.DejankUtils;
import com.google.android.systemui.elmyra.sensors.config.GestureConfiguration;
import java.util.function.Consumer;
import java.util.function.Supplier;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class GestureConfiguration$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ GestureConfiguration f$0;

    public /* synthetic */ GestureConfiguration$$ExternalSyntheticLambda0(GestureConfiguration gestureConfiguration, int i) {
        this.$r8$classId = i;
        this.f$0 = gestureConfiguration;
    }

    public final void accept(Object obj) {
        int i = this.$r8$classId;
        GestureConfiguration gestureConfiguration = this.f$0;
        switch (i) {
            case 0:
                ScreenStateAdjustment screenStateAdjustment = (ScreenStateAdjustment) obj;
                gestureConfiguration.getClass();
                Float f = (Float) DejankUtils.whitelistIpcs((Supplier) new GestureConfiguration$$ExternalSyntheticLambda3(gestureConfiguration));
                float floatValue = f.floatValue();
                if (!GestureConfiguration.SENSITIVITY_RANGE.contains(f)) {
                    floatValue = 0.5f;
                }
                gestureConfiguration.mSensitivity = floatValue;
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
                Float f2 = (Float) DejankUtils.whitelistIpcs((Supplier) new GestureConfiguration$$ExternalSyntheticLambda3(gestureConfiguration));
                float floatValue2 = f2.floatValue();
                if (!GestureConfiguration.SENSITIVITY_RANGE.contains(f2)) {
                    floatValue2 = 0.5f;
                }
                gestureConfiguration.mSensitivity = floatValue2;
                GestureConfiguration.Listener listener2 = gestureConfiguration.mListener;
                if (listener2 != null) {
                    listener2.onGestureConfigurationChanged(gestureConfiguration);
                    return;
                }
                return;
        }
    }
}
