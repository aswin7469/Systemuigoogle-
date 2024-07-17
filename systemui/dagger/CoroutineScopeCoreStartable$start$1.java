package com.google.android.systemui.dagger;

import androidx.appcompat.app.WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
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
final class CoroutineScopeCoreStartable$start$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ CoroutineScopeCoreStartable this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CoroutineScopeCoreStartable$start$1(CoroutineScopeCoreStartable coroutineScopeCoreStartable, Continuation continuation) {
        super(2, continuation);
        this.this$0 = coroutineScopeCoreStartable;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        CoroutineScopeCoreStartable$start$1 coroutineScopeCoreStartable$start$1 = new CoroutineScopeCoreStartable$start$1(this.this$0, continuation);
        coroutineScopeCoreStartable$start$1.L$0 = obj;
        return coroutineScopeCoreStartable$start$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        Unit unit = Unit.INSTANCE;
        ((CoroutineScopeCoreStartable$start$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(unit);
        return unit;
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [kotlin.coroutines.jvm.internal.SuspendLambda, kotlin.jvm.functions.Function2] */
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            for (Object m : this.this$0.coroutineInitializers) {
                WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(m);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new SuspendLambda(2, (Continuation) null), 3);
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
