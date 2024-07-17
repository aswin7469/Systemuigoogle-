package com.google.android.systemui.columbus.legacy.gates;

import android.telephony.TelephonyManager;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class TelephonyActivity$onActivate$1 extends SuspendLambda implements Function2 {
    Object L$0;
    int label;
    final /* synthetic */ TelephonyActivity this$0;

    /* renamed from: com.google.android.systemui.columbus.legacy.gates.TelephonyActivity$onActivate$1$1  reason: invalid class name */
    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            boolean z;
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (new Integer(((TelephonyManager) TelephonyActivity.this.telephonyManager.get()).getCallState()).intValue() == 2) {
                    z = true;
                } else {
                    z = false;
                }
                return Boolean.valueOf(z);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TelephonyActivity$onActivate$1(TelephonyActivity telephonyActivity, Continuation continuation) {
        super(2, continuation);
        this.this$0 = telephonyActivity;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new TelephonyActivity$onActivate$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((TelephonyActivity$onActivate$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        TelephonyActivity telephonyActivity;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final TelephonyActivity telephonyActivity2 = this.this$0;
            CoroutineDispatcher coroutineDispatcher = telephonyActivity2.bgDispatcher;
            AnonymousClass1 r4 = new AnonymousClass1((Continuation) null);
            this.L$0 = telephonyActivity2;
            this.label = 1;
            Object withContext = BuildersKt.withContext(coroutineDispatcher, r4, this);
            if (withContext == coroutineSingletons) {
                return coroutineSingletons;
            }
            telephonyActivity = telephonyActivity2;
            obj = withContext;
        } else if (i == 1) {
            telephonyActivity = (TelephonyActivity) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        telephonyActivity.isCallBlocked = ((Boolean) obj).booleanValue();
        TelephonyActivity telephonyActivity3 = this.this$0;
        BuildersKt.launch$default(telephonyActivity3.coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new TelephonyActivity$updateBlocking$1(telephonyActivity3, (Continuation) null), 3);
        return Unit.INSTANCE;
    }
}
