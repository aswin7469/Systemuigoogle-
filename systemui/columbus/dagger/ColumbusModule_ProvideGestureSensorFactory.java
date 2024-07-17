package com.google.android.systemui.columbus.dagger;

import com.google.android.systemui.columbus.sensors.NoOpGestureSensor;
import dagger.Lazy;
import javax.inject.Provider;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class ColumbusModule_ProvideGestureSensorFactory implements Provider {
    public static NoOpGestureSensor provideGestureSensor(Lazy lazy) {
        return (NoOpGestureSensor) lazy.get();
    }
}
