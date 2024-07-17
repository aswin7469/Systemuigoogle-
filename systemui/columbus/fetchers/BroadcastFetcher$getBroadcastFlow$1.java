package com.google.android.systemui.columbus.fetchers;

import android.content.IntentFilter;
import com.google.android.systemui.columbus.fetchers.BroadcastFetcher;
import java.util.function.Function;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class BroadcastFetcher$getBroadcastFlow$1 implements Function {
    public final /* synthetic */ IntentFilter $intentFilter;
    public final /* synthetic */ boolean $usingDispatcher;
    public final /* synthetic */ BroadcastFetcher this$0;

    public BroadcastFetcher$getBroadcastFlow$1(boolean z, BroadcastFetcher broadcastFetcher, IntentFilter intentFilter) {
        this.$usingDispatcher = z;
        this.this$0 = broadcastFetcher;
        this.$intentFilter = intentFilter;
    }

    public final Object apply(Object obj) {
        BroadcastFetcher.BroadcastKey broadcastKey = (BroadcastFetcher.BroadcastKey) obj;
        if (this.$usingDispatcher) {
            BroadcastFetcher broadcastFetcher = this.this$0;
            IntentFilter intentFilter = this.$intentFilter;
            broadcastFetcher.getClass();
            return FlowKt.shareIn(new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1(FlowKt.buffer$default(FlowKt.callbackFlow(new BroadcastFetcher$createDispatchedFlow$1(broadcastFetcher, intentFilter, (Continuation) null)), -1)), broadcastFetcher.coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), 0);
        }
        BroadcastFetcher broadcastFetcher2 = this.this$0;
        IntentFilter intentFilter2 = this.$intentFilter;
        broadcastFetcher2.getClass();
        return FlowKt.shareIn(new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1(FlowKt.buffer$default(FlowKt.callbackFlow(new BroadcastFetcher$createDirectFlow$1(broadcastFetcher2, intentFilter2, (Continuation) null)), -1)), broadcastFetcher2.coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), 0);
    }
}
