package com.google.android.systemui.columbus.legacy.gates;

import com.android.systemui.shared.system.InputChannelCompat$InputEventReceiver;
import com.android.systemui.shared.system.InputMonitorCompat;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class ScreenTouch$stopListeningForTouch$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ ScreenTouch this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ScreenTouch$stopListeningForTouch$1(ScreenTouch screenTouch, Continuation continuation) {
        super(2, continuation);
        this.this$0 = screenTouch;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new ScreenTouch$stopListeningForTouch$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        Unit unit = Unit.INSTANCE;
        ((ScreenTouch$stopListeningForTouch$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(unit);
        return unit;
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            InputChannelCompat$InputEventReceiver inputChannelCompat$InputEventReceiver = this.this$0.inputEventReceiver;
            if (inputChannelCompat$InputEventReceiver != null) {
                inputChannelCompat$InputEventReceiver.dispose();
            }
            ScreenTouch screenTouch = this.this$0;
            screenTouch.inputEventReceiver = null;
            InputMonitorCompat inputMonitorCompat = screenTouch.inputMonitor;
            if (inputMonitorCompat != null) {
                inputMonitorCompat.dispose();
            }
            this.this$0.inputMonitor = null;
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}