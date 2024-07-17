package com.google.android.systemui.columbus;

import androidx.appcompat.app.WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ColumbusManager$special$$inlined$flatMapLatest$2 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ ColumbusManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ColumbusManager$special$$inlined$flatMapLatest$2(ColumbusManager columbusManager, Continuation continuation) {
        super(3, continuation);
        this.this$0 = columbusManager;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        ColumbusManager$special$$inlined$flatMapLatest$2 columbusManager$special$$inlined$flatMapLatest$2 = new ColumbusManager$special$$inlined$flatMapLatest$2(this.this$0, (Continuation) obj3);
        columbusManager$special$$inlined$flatMapLatest$2.L$0 = (FlowCollector) obj;
        columbusManager$special$$inlined$flatMapLatest$2.L$1 = obj2;
        return columbusManager$special$$inlined$flatMapLatest$2.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Pair pair = (Pair) this.L$1;
            WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(pair.component1());
            WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(pair.component2());
            this.label = 1;
            FlowKt.ensureActive((FlowCollector) this.L$0);
            CoroutineSingletons coroutineSingletons2 = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (unit == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return unit;
    }
}
