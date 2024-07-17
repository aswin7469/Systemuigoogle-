package com.google.android.systemui.keyguard;

import com.google.android.systemui.keyguard.domain.interactor.RefreshRateInteractor;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class RefreshRateRequesterBinder$start$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ RefreshRateRequesterBinder this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RefreshRateRequesterBinder$start$1(RefreshRateRequesterBinder refreshRateRequesterBinder, Continuation continuation) {
        super(2, continuation);
        this.this$0 = refreshRateRequesterBinder;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new RefreshRateRequesterBinder$start$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((RefreshRateRequesterBinder$start$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ChannelFlowTransformLatest channelFlowTransformLatest = ((RefreshRateInteractor) this.this$0.interactor.get()).requestOverridingMaxRefreshRate;
            final RefreshRateRequesterBinder refreshRateRequesterBinder = this.this$0;
            AnonymousClass1 r1 = new FlowCollector() {
                public final Object emit(Object obj, Continuation continuation) {
                    ((RefreshRateInteractor) RefreshRateRequesterBinder.this.interactor.get()).authController.requestMaxRefreshRate(((Boolean) obj).booleanValue());
                    return Unit.INSTANCE;
                }
            };
            this.label = 1;
            if (channelFlowTransformLatest.collect(r1, this) == coroutineSingletons) {
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
