package com.google.android.systemui.assist.uihints;

import dagger.Lazy;
import javax.inject.Provider;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class TimeoutManager_Factory implements Provider {
    public static TimeoutManager newInstance(Lazy lazy) {
        return new TimeoutManager(lazy);
    }
}
