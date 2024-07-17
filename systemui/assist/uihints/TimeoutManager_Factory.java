package com.google.android.systemui.assist.uihints;

import android.os.Handler;
import android.os.Looper;
import javax.inject.Provider;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class TimeoutManager_Factory implements Provider {
    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Object, com.google.android.systemui.assist.uihints.TimeoutManager] */
    public static TimeoutManager newInstance() {
        ? obj = new Object();
        new Handler(Looper.getMainLooper());
        return obj;
    }
}
