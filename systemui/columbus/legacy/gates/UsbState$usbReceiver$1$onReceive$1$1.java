package com.google.android.systemui.columbus.legacy.gates;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class UsbState$usbReceiver$1$onReceive$1$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ boolean $newUsbConnected;
    int label;
    final /* synthetic */ UsbState this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public UsbState$usbReceiver$1$onReceive$1$1(boolean z, UsbState usbState, Continuation continuation) {
        super(2, continuation);
        this.$newUsbConnected = z;
        this.this$0 = usbState;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new UsbState$usbReceiver$1$onReceive$1$1(this.$newUsbConnected, this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        Unit unit = Unit.INSTANCE;
        ((UsbState$usbReceiver$1$onReceive$1$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(unit);
        return unit;
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            boolean z = this.$newUsbConnected;
            UsbState usbState = this.this$0;
            if (z != usbState.usbConnected) {
                usbState.usbConnected = z;
                usbState.blockForMillis(usbState.gateDuration);
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
