package com.google.android.systemui.columbus.fetchers;

import android.os.UserHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class ContentFetcher$getSettingChangesForCurrentUser$1$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ UserHandle $it;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ContentFetcher$getSettingChangesForCurrentUser$1$1(UserHandle userHandle, Continuation continuation) {
        super(2, continuation);
        this.$it = userHandle;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        ContentFetcher$getSettingChangesForCurrentUser$1$1 contentFetcher$getSettingChangesForCurrentUser$1$1 = new ContentFetcher$getSettingChangesForCurrentUser$1$1(this.$it, continuation);
        contentFetcher$getSettingChangesForCurrentUser$1$1.L$0 = obj;
        return contentFetcher$getSettingChangesForCurrentUser$1$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ContentFetcher$getSettingChangesForCurrentUser$1$1) create((FlowCollector) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Integer num = new Integer(this.$it.getIdentifier());
            this.label = 1;
            if (((FlowCollector) this.L$0).emit(num, this) == coroutineSingletons) {
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
