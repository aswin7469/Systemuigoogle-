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
public final class ColumbusManager$special$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ ColumbusManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ColumbusManager$special$$inlined$flatMapLatest$1(ColumbusManager columbusManager, Continuation continuation) {
        super(3, continuation);
        this.this$0 = columbusManager;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        ColumbusManager$special$$inlined$flatMapLatest$1 columbusManager$special$$inlined$flatMapLatest$1 = new ColumbusManager$special$$inlined$flatMapLatest$1(this.this$0, (Continuation) obj3);
        columbusManager$special$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        columbusManager$special$$inlined$flatMapLatest$1.L$1 = obj2;
        return columbusManager$special$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object obj2 = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Object obj3 = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(this.L$1);
            Pair pair = new Pair((Object) null, (Object) null);
            this.label = 1;
            FlowKt.ensureActive(flowCollector);
            Object emit = flowCollector.emit(pair, this);
            if (emit != CoroutineSingletons.COROUTINE_SUSPENDED) {
                emit = obj3;
            }
            if (emit != CoroutineSingletons.COROUTINE_SUSPENDED) {
                emit = obj3;
            }
            if (emit == obj2) {
                return obj2;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return obj3;
    }
}
