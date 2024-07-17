package com.google.android.systemui.columbus.dagger;

import android.content.Context;
import com.android.systemui.util.wakelock.WakeLock;
import com.android.systemui.util.wakelock.WakeLockLogger;
import javax.inject.Provider;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class ColumbusModule_ProvideQuickTapWakeLockFactory implements Provider {
    public static WakeLock provideQuickTapWakeLock(Context context, WakeLockLogger wakeLockLogger) {
        return WakeLock.createPartial(context, wakeLockLogger, "Columbus", 2000);
    }
}
