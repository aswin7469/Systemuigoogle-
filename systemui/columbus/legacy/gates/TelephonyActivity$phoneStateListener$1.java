package com.google.android.systemui.columbus.legacy.gates;

import android.telephony.TelephonyCallback;
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
public final class TelephonyActivity$phoneStateListener$1 implements TelephonyCallback.CallStateListener {
    public final /* synthetic */ TelephonyActivity this$0;

    /* renamed from: com.google.android.systemui.columbus.legacy.gates.TelephonyActivity$phoneStateListener$1$1  reason: invalid class name */
    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(i, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            Unit unit = Unit.INSTANCE;
            ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(unit);
            return unit;
        }

        public final Object invokeSuspend(Object obj) {
            boolean z;
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                TelephonyActivity telephonyActivity = TelephonyActivity.this;
                Integer num = new Integer(i);
                telephonyActivity.getClass();
                if (num.intValue() == 2) {
                    z = true;
                } else {
                    z = false;
                }
                telephonyActivity.isCallBlocked = z;
                TelephonyActivity telephonyActivity2 = TelephonyActivity.this;
                BuildersKt.launch$default(telephonyActivity2.coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new TelephonyActivity$updateBlocking$1(telephonyActivity2, (Continuation) null), 3);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public TelephonyActivity$phoneStateListener$1(TelephonyActivity telephonyActivity) {
        this.this$0 = telephonyActivity;
    }

    public final void onCallStateChanged(final int i) {
        final TelephonyActivity telephonyActivity = this.this$0;
        BuildersKt.launch$default(telephonyActivity.coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1((Continuation) null), 3);
    }
}
