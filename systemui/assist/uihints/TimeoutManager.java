package com.google.android.systemui.assist.uihints;

import android.os.Handler;
import android.os.Looper;
import com.google.android.systemui.assist.uihints.NgaMessageHandler;
import dagger.Lazy;
import java.util.concurrent.TimeUnit;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class TimeoutManager implements NgaMessageHandler.KeepAliveListener {
    public static final long SESSION_TIMEOUT_MS = TimeUnit.SECONDS.toMillis(10);
    public final Handler mHandler = new Handler(Looper.getMainLooper());
    public final TimeoutManager$$ExternalSyntheticLambda0 mOnTimeout;
    public NgaUiController$$ExternalSyntheticLambda2 mTimeoutCallback;

    public TimeoutManager(Lazy lazy) {
        this.mOnTimeout = new TimeoutManager$$ExternalSyntheticLambda0(this, lazy);
    }
}
