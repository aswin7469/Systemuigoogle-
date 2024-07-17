package com.google.android.systemui.columbus.dagger;

import android.content.Context;
import com.android.systemui.util.wakelock.WakeLock;
import com.android.systemui.util.wakelock.WakeLockLogger;
import javax.inject.Provider;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class ColumbusModule_ProvideQuickTapWakeLockFactory implements Provider {
    public static WakeLock provideQuickTapWakeLock(Context context, WakeLockLogger wakeLockLogger) {
        return WakeLock.wrap(WakeLock.createWakeLockInner(context, "Columbus", 1), wakeLockLogger, 2000);
    }
}
