package com.google.android.systemui.columbus.legacy.gates;

import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class KeyguardProximity$updateProximityListener$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ KeyguardProximity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public KeyguardProximity$updateProximityListener$1(KeyguardProximity keyguardProximity, Continuation continuation) {
        super(2, continuation);
        this.this$0 = keyguardProximity;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new KeyguardProximity$updateProximityListener$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        Unit unit = Unit.INSTANCE;
        ((KeyguardProximity$updateProximityListener$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(unit);
        return unit;
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (!this.this$0.isActive() || !((KeyguardStateControllerImpl) ((KeyguardStateController) this.this$0.keyguardGate.keyguardStateController.get())).mShowing || ((KeyguardStateControllerImpl) ((KeyguardStateController) this.this$0.keyguardGate.keyguardStateController.get())).mOccluded) {
                KeyguardProximity keyguardProximity = this.this$0;
                if (keyguardProximity.isListening) {
                    keyguardProximity.proximity.unregisterListener(keyguardProximity.proximityListener);
                    this.this$0.isListening = false;
                }
            } else {
                KeyguardProximity keyguardProximity2 = this.this$0;
                if (!keyguardProximity2.isListening) {
                    keyguardProximity2.proximity.registerListener(keyguardProximity2.proximityListener);
                    this.this$0.isListening = true;
                }
            }
            KeyguardProximity keyguardProximity3 = this.this$0;
            BuildersKt.launch$default(keyguardProximity3.coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new KeyguardProximity$updateBlocking$1(keyguardProximity3, (Continuation) null), 3);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
