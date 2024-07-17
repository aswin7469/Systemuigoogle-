package com.google.android.systemui.columbus.legacy.gates;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class KeyguardProximity extends Gate {
    public boolean isListening;
    public final KeyguardVisibility keyguardGate;
    public final KeyguardProximity$keyguardListener$1 keyguardListener = new KeyguardProximity$keyguardListener$1(this, 0);
    public final Proximity proximity;
    public final KeyguardProximity$keyguardListener$1 proximityListener = new KeyguardProximity$keyguardListener$1(this, 1);

    public KeyguardProximity(KeyguardVisibility keyguardVisibility, Proximity proximity2) {
        this.keyguardGate = keyguardVisibility;
        this.proximity = proximity2;
    }

    public final void onActivate() {
        this.keyguardGate.registerListener(this.keyguardListener);
        BuildersKt.launch$default(this.coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new KeyguardProximity$updateProximityListener$1(this, (Continuation) null), 3);
    }

    public final void onDeactivate() {
        this.keyguardGate.unregisterListener(this.keyguardListener);
        BuildersKt.launch$default(this.coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new KeyguardProximity$updateProximityListener$1(this, (Continuation) null), 3);
    }

    public final String toString() {
        String gate = super.toString();
        Object runBlocking = BuildersKt.runBlocking(this.mainDispatcher, new KeyguardProximity$toString$1(this, (Continuation) null));
        return gate + runBlocking;
    }
}
