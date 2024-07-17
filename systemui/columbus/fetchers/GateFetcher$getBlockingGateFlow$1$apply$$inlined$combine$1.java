package com.google.android.systemui.columbus.fetchers;

import androidx.appcompat.app.WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.internal.CombineKt;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class GateFetcher$getBlockingGateFlow$1$apply$$inlined$combine$1 implements Flow {
    public final /* synthetic */ Flow[] $flowArray$inlined;

    /* renamed from: com.google.android.systemui.columbus.fetchers.GateFetcher$getBlockingGateFlow$1$apply$$inlined$combine$1$3  reason: invalid class name */
    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class AnonymousClass3 extends SuspendLambda implements Function3 {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        /* JADX WARNING: type inference failed for: r1v1, types: [com.google.android.systemui.columbus.fetchers.GateFetcher$getBlockingGateFlow$1$apply$$inlined$combine$1$3, kotlin.coroutines.jvm.internal.SuspendLambda] */
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            ? suspendLambda = new SuspendLambda(3, (Continuation) obj3);
            suspendLambda.L$0 = (FlowCollector) obj;
            suspendLambda.L$1 = (Object[]) obj2;
            return suspendLambda.invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Pair pair;
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FlowCollector flowCollector = (FlowCollector) this.L$0;
                Pair[] pairArr = (Pair[]) ((Object[]) this.L$1);
                int length = pairArr.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        pair = null;
                        break;
                    }
                    pair = pairArr[i2];
                    if (((Boolean) pair.getSecond()).booleanValue()) {
                        break;
                    }
                    i2++;
                }
                if (pair != null) {
                    WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(pair.getFirst());
                }
                this.label = 1;
                if (flowCollector.emit((Object) null, this) == coroutineSingletons) {
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

    public GateFetcher$getBlockingGateFlow$1$apply$$inlined$combine$1(Flow[] flowArr) {
        this.$flowArray$inlined = flowArr;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [kotlin.coroutines.jvm.internal.SuspendLambda, kotlin.jvm.functions.Function3] */
    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
        final Flow[] flowArr = this.$flowArray$inlined;
        Object combineInternal = CombineKt.combineInternal(continuation, new Function0() {
            public final Object invoke() {
                return new Pair[flowArr.length];
            }
        }, new SuspendLambda(3, (Continuation) null), flowCollector, flowArr);
        if (combineInternal == CoroutineSingletons.COROUTINE_SUSPENDED) {
            return combineInternal;
        }
        return Unit.INSTANCE;
    }
}
