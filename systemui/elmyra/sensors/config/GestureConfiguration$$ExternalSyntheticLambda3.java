package com.google.android.systemui.elmyra.sensors.config;

import android.provider.Settings;
import java.util.function.Supplier;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class GestureConfiguration$$ExternalSyntheticLambda3 implements Supplier {
    public final /* synthetic */ GestureConfiguration f$0;

    public /* synthetic */ GestureConfiguration$$ExternalSyntheticLambda3(GestureConfiguration gestureConfiguration) {
        this.f$0 = gestureConfiguration;
    }

    public final Object get() {
        return Float.valueOf(Settings.Secure.getFloatForUser(this.f$0.mContext.getContentResolver(), "assist_gesture_sensitivity", 0.5f, -2));
    }
}
