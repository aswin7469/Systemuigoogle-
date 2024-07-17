package com.google.android.systemui.columbus.dagger;

import com.google.android.systemui.columbus.sensors.NoOpGestureSensor;
import dagger.Lazy;
import javax.inject.Provider;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class ColumbusModule_ProvideGestureSensorFactory implements Provider {
    public static NoOpGestureSensor provideGestureSensor(Lazy lazy) {
        return (NoOpGestureSensor) lazy.get();
    }
}
