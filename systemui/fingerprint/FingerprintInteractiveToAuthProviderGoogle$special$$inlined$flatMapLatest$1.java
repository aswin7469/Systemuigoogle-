package com.google.android.systemui.fingerprint;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class FingerprintInteractiveToAuthProviderGoogle$special$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ FingerprintInteractiveToAuthProviderGoogle this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FingerprintInteractiveToAuthProviderGoogle$special$$inlined$flatMapLatest$1(Continuation continuation, FingerprintInteractiveToAuthProviderGoogle fingerprintInteractiveToAuthProviderGoogle) {
        super(3, continuation);
        this.this$0 = fingerprintInteractiveToAuthProviderGoogle;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        FingerprintInteractiveToAuthProviderGoogle$special$$inlined$flatMapLatest$1 fingerprintInteractiveToAuthProviderGoogle$special$$inlined$flatMapLatest$1 = new FingerprintInteractiveToAuthProviderGoogle$special$$inlined$flatMapLatest$1((Continuation) obj3, this.this$0);
        fingerprintInteractiveToAuthProviderGoogle$special$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        fingerprintInteractiveToAuthProviderGoogle$special$$inlined$flatMapLatest$1.L$1 = obj2;
        return fingerprintInteractiveToAuthProviderGoogle$special$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            int intValue = ((Number) this.L$1).intValue();
            FingerprintInteractiveToAuthProviderGoogle fingerprintInteractiveToAuthProviderGoogle = this.this$0;
            Flow buffer$default = FlowKt.buffer$default(FlowKt.callbackFlow(new FingerprintInteractiveToAuthProviderGoogle$enabledForCurrentUser$1$1(fingerprintInteractiveToAuthProviderGoogle, new FingerprintInteractiveToAuthProviderGoogle$enabledForCurrentUser$1$getCurrentSettingValue$1(fingerprintInteractiveToAuthProviderGoogle, intValue), (Continuation) null)), -1);
            this.label = 1;
            if (FlowKt.emitAll(this, buffer$default, (FlowCollector) this.L$0) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
