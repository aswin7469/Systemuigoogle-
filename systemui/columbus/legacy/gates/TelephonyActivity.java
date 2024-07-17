package com.google.android.systemui.columbus.legacy.gates;

import com.android.systemui.telephony.TelephonyListenerManager;
import dagger.Lazy;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class TelephonyActivity extends Gate {
    public final CoroutineDispatcher bgDispatcher;
    public boolean isCallBlocked;
    public final TelephonyActivity$phoneStateListener$1 phoneStateListener = new TelephonyActivity$phoneStateListener$1(this);
    public final Lazy telephonyListenerManager;
    public final Lazy telephonyManager;

    public TelephonyActivity(Lazy lazy, Lazy lazy2, CoroutineDispatcher coroutineDispatcher) {
        this.telephonyManager = lazy;
        this.telephonyListenerManager = lazy2;
        this.bgDispatcher = coroutineDispatcher;
    }

    public final void onActivate() {
        ((TelephonyListenerManager) this.telephonyListenerManager.get()).addCallStateListener(this.phoneStateListener);
        TelephonyActivity$onActivate$1 telephonyActivity$onActivate$1 = new TelephonyActivity$onActivate$1(this, (Continuation) null);
        BuildersKt.launch$default(this.coroutineScope, this.mainDispatcher, (CoroutineStart) null, telephonyActivity$onActivate$1, 2);
    }

    public final void onDeactivate() {
        ((TelephonyListenerManager) this.telephonyListenerManager.get()).removeCallStateListener(this.phoneStateListener);
    }
}
