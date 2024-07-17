package com.google.android.systemui.elmyra;

import android.content.Context;
import com.google.android.systemui.elmyra.sensors.GestureSensor;
import com.google.android.systemui.elmyra.sensors.JNIGestureSensor;
import dagger.Lazy;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class ElmyraModule_ProvidesGestureSensorFactory implements Provider {
    public static GestureSensor providesGestureSensor(Context context, Lazy lazy, Lazy lazy2) {
        GestureSensor gestureSensor;
        if (JNIGestureSensor.isAvailable(context)) {
            gestureSensor = (GestureSensor) lazy.get();
        } else {
            gestureSensor = (GestureSensor) lazy2.get();
        }
        Preconditions.checkNotNullFromProvides(gestureSensor);
        return gestureSensor;
    }
}
