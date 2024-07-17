package com.google.android.systemui.columbus.legacy.gates;

import com.android.systemui.keyguard.WakefulnessLifecycle;
import dagger.Lazy;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class PowerState extends Gate {
    public final CoroutineDispatcher bgDispatcher;
    public final Lazy powerManager;
    public final Lazy wakefulnessLifecycle;
    public final PowerState$wakefulnessLifecycleObserver$1 wakefulnessLifecycleObserver = new PowerState$wakefulnessLifecycleObserver$1(this);

    public PowerState(Lazy lazy, Lazy lazy2, CoroutineDispatcher coroutineDispatcher) {
        this.powerManager = lazy;
        this.wakefulnessLifecycle = lazy2;
        this.bgDispatcher = coroutineDispatcher;
    }

    public final void onActivate() {
        ((WakefulnessLifecycle) this.wakefulnessLifecycle.get()).addObserver(this.wakefulnessLifecycleObserver);
        updateBlocking$2();
    }

    public final void onDeactivate() {
        ((WakefulnessLifecycle) this.wakefulnessLifecycle.get()).removeObserver(this.wakefulnessLifecycleObserver);
    }

    public final void updateBlocking$2() {
        PowerState$updateBlocking$1 powerState$updateBlocking$1 = new PowerState$updateBlocking$1(this, (Continuation) null);
        BuildersKt.launch$default(this.coroutineScope, this.mainDispatcher, (CoroutineStart) null, powerState$updateBlocking$1, 2);
    }
}