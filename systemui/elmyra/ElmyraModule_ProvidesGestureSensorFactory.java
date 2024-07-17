package com.google.android.systemui.elmyra;

import android.content.Context;
import com.google.android.systemui.elmyra.sensors.GestureSensor;
import com.google.android.systemui.elmyra.sensors.JNIGestureSensor;
import dagger.Lazy;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
