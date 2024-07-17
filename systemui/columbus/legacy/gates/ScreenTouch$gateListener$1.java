package com.google.android.systemui.columbus.legacy.gates;

import com.google.android.systemui.columbus.legacy.gates.Gate;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ScreenTouch$gateListener$1 implements Gate.Listener {
    public final /* synthetic */ ScreenTouch this$0;

    public ScreenTouch$gateListener$1(ScreenTouch screenTouch) {
        this.this$0 = screenTouch;
    }

    public final void onGateChanged(Gate gate) {
        ScreenTouch screenTouch = this.this$0;
        boolean isBlocking = screenTouch.powerState.isBlocking();
        ContextScope contextScope = screenTouch.coroutineScope;
        if (isBlocking) {
            BuildersKt.launch$default(contextScope, (CoroutineContext) null, (CoroutineStart) null, new ScreenTouch$stopListeningForTouch$1(screenTouch, (Continuation) null), 3);
        } else {
            BuildersKt.launch$default(contextScope, (CoroutineContext) null, (CoroutineStart) null, new ScreenTouch$startListeningForTouch$1(screenTouch, (Continuation) null), 3);
        }
    }
}
