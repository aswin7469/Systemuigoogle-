package com.google.android.systemui.columbus.fetchers;

import android.content.IntentFilter;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class BroadcastFetcher$createDirectFlow$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ IntentFilter $intentFilter;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ BroadcastFetcher this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BroadcastFetcher$createDirectFlow$1(BroadcastFetcher broadcastFetcher, IntentFilter intentFilter, Continuation continuation) {
        super(2, continuation);
        this.this$0 = broadcastFetcher;
        this.$intentFilter = intentFilter;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        BroadcastFetcher$createDirectFlow$1 broadcastFetcher$createDirectFlow$1 = new BroadcastFetcher$createDirectFlow$1(this.this$0, this.$intentFilter, continuation);
        broadcastFetcher$createDirectFlow$1.L$0 = obj;
        return broadcastFetcher$createDirectFlow$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((BroadcastFetcher$createDirectFlow$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0064 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r6.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0024
            if (r1 == r3) goto L_0x0018
            if (r1 != r2) goto L_0x0010
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0065
        L_0x0010:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0018:
            java.lang.Object r1 = r6.L$1
            com.google.android.systemui.columbus.fetchers.BroadcastFetcher$createDirectFlow$1$broadcastReceiver$1 r1 = (com.google.android.systemui.columbus.fetchers.BroadcastFetcher$createDirectFlow$1$broadcastReceiver$1) r1
            java.lang.Object r3 = r6.L$0
            kotlinx.coroutines.channels.ProducerScope r3 = (kotlinx.coroutines.channels.ProducerScope) r3
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x004f
        L_0x0024:
            kotlin.ResultKt.throwOnFailure(r7)
            java.lang.Object r7 = r6.L$0
            kotlinx.coroutines.channels.ProducerScope r7 = (kotlinx.coroutines.channels.ProducerScope) r7
            com.google.android.systemui.columbus.fetchers.BroadcastFetcher$createDirectFlow$1$broadcastReceiver$1 r1 = new com.google.android.systemui.columbus.fetchers.BroadcastFetcher$createDirectFlow$1$broadcastReceiver$1
            r1.<init>(r7)
            com.google.android.systemui.columbus.fetchers.BroadcastFetcher r4 = r6.this$0
            android.content.Context r4 = r4.context
            android.content.IntentFilter r5 = r6.$intentFilter
            android.content.Intent r4 = r4.registerReceiver(r1, r5)
            if (r4 == 0) goto L_0x0050
            r6.L$0 = r7
            r6.L$1 = r1
            r6.label = r3
            r3 = r7
            kotlinx.coroutines.channels.ProducerCoroutine r3 = (kotlinx.coroutines.channels.ProducerCoroutine) r3
            kotlinx.coroutines.channels.Channel r3 = r3._channel
            java.lang.Object r3 = r3.send(r4, r6)
            if (r3 != r0) goto L_0x004e
            return r0
        L_0x004e:
            r3 = r7
        L_0x004f:
            r7 = r3
        L_0x0050:
            com.google.android.systemui.columbus.fetchers.BroadcastFetcher$createDirectFlow$1$1 r3 = new com.google.android.systemui.columbus.fetchers.BroadcastFetcher$createDirectFlow$1$1
            com.google.android.systemui.columbus.fetchers.BroadcastFetcher r4 = r6.this$0
            r3.<init>(r1)
            r1 = 0
            r6.L$0 = r1
            r6.L$1 = r1
            r6.label = r2
            java.lang.Object r6 = kotlinx.coroutines.channels.ProduceKt.awaitClose(r7, r3, r6)
            if (r6 != r0) goto L_0x0065
            return r0
        L_0x0065:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.columbus.fetchers.BroadcastFetcher$createDirectFlow$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
