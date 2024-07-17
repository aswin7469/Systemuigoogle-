package com.google.android.systemui.elmyra.sensors;

import com.google.android.systemui.elmyra.sensors.config.GestureConfiguration;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class JNIGestureSensor$$ExternalSyntheticLambda0 implements GestureConfiguration.Listener {
    public final /* synthetic */ JNIGestureSensor f$0;

    public /* synthetic */ JNIGestureSensor$$ExternalSyntheticLambda0(JNIGestureSensor jNIGestureSensor) {
        this.f$0 = jNIGestureSensor;
    }

    public final void onGestureConfigurationChanged(GestureConfiguration gestureConfiguration) {
        this.f$0.lambda$new$0(gestureConfiguration);
    }
}
