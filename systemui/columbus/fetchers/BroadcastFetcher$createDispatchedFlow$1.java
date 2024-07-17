package com.google.android.systemui.columbus.fetchers;

import android.content.IntentFilter;
import android.os.UserHandle;
import com.android.systemui.broadcast.BroadcastDispatcher;
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

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class BroadcastFetcher$createDispatchedFlow$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ IntentFilter $intentFilter;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ BroadcastFetcher this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BroadcastFetcher$createDispatchedFlow$1(BroadcastFetcher broadcastFetcher, IntentFilter intentFilter, Continuation continuation) {
        super(2, continuation);
        this.this$0 = broadcastFetcher;
        this.$intentFilter = intentFilter;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        BroadcastFetcher$createDispatchedFlow$1 broadcastFetcher$createDispatchedFlow$1 = new BroadcastFetcher$createDispatchedFlow$1(this.this$0, this.$intentFilter, continuation);
        broadcastFetcher$createDispatchedFlow$1.L$0 = obj;
        return broadcastFetcher$createDispatchedFlow$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((BroadcastFetcher$createDispatchedFlow$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ProducerScope producerScope = (ProducerScope) this.L$0;
            final BroadcastFetcher$createDispatchedFlow$1$broadcastReceiver$1 broadcastFetcher$createDispatchedFlow$1$broadcastReceiver$1 = new BroadcastFetcher$createDispatchedFlow$1$broadcastReceiver$1(producerScope);
            BroadcastDispatcher.registerReceiver$default(this.this$0.broadcastDispatcher, broadcastFetcher$createDispatchedFlow$1$broadcastReceiver$1, this.$intentFilter, (Executor) null, (UserHandle) null, 0, (String) null, 60);
            final BroadcastFetcher broadcastFetcher = this.this$0;
            AnonymousClass1 r3 = new Function0() {
                public final Object invoke() {
                    BroadcastFetcher.this.broadcastDispatcher.unregisterReceiver(broadcastFetcher$createDispatchedFlow$1$broadcastReceiver$1);
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
