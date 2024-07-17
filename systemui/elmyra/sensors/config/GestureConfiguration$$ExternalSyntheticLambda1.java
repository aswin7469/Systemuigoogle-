package com.google.android.systemui.elmyra.sensors.config;

import android.provider.Settings;
import java.util.function.Supplier;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class GestureConfiguration$$ExternalSyntheticLambda1 implements Supplier {
    public final /* synthetic */ GestureConfiguration f$0;

    public /* synthetic */ GestureConfiguration$$ExternalSyntheticLambda1(GestureConfiguration gestureConfiguration) {
        this.f$0 = gestureConfiguration;
    }

    public final Object get() {
        return Float.valueOf(Settings.Secure.getFloatForUser(this.f$0.mContext.getContentResolver(), "assist_gesture_sensitivity", 0.5f, -2));
    }
}
