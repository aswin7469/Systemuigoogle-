package com.google.android.systemui.columbus.fetchers;

import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import java.util.concurrent.Executor;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class UserFetcher$currentUserChange$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Executor $mainExecutor;
    final /* synthetic */ UserTracker $userTracker;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public UserFetcher$currentUserChange$1(UserTracker userTracker, Executor executor, Continuation continuation) {
        super(2, continuation);
        this.$userTracker = userTracker;
        this.$mainExecutor = executor;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        UserFetcher$currentUserChange$1 userFetcher$currentUserChange$1 = new UserFetcher$currentUserChange$1(this.$userTracker, this.$mainExecutor, continuation);
        userFetcher$currentUserChange$1.L$0 = obj;
        return userFetcher$currentUserChange$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((UserFetcher$currentUserChange$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ProducerScope producerScope = (ProducerScope) this.L$0;
            final UserFetcher$currentUserChange$1$userTrackerCallback$1 userFetcher$currentUserChange$1$userTrackerCallback$1 = new UserFetcher$currentUserChange$1$userTrackerCallback$1(producerScope);
            ((UserTrackerImpl) this.$userTracker).addCallback(userFetcher$currentUserChange$1$userTrackerCallback$1, this.$mainExecutor);
            final UserTracker userTracker = this.$userTracker;
            AnonymousClass1 r3 = new Function0() {
                public final Object invoke() {
                    ((UserTrackerImpl) UserTracker.this).removeCallback(userFetcher$currentUserChange$1$userTrackerCallback$1);
                    return Unit.INSTANCE;
                }
            };
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, r3, this) == coroutineSingletons) {
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
