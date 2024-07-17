package com.google.android.systemui.columbus.fetchers;

import android.content.IntentFilter;
import android.os.UserHandle;
import com.android.systemui.settings.UserTracker;
import com.google.android.systemui.columbus.fetchers.BroadcastFetcher;
import java.util.concurrent.Executor;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlySharedFlow;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class UserFetcher {
    public final ReadonlySharedFlow currentUserChange;
    public final ReadonlyStateFlow currentUserHandle;

    /* JADX WARNING: type inference failed for: r2v1, types: [kotlin.coroutines.jvm.internal.SuspendLambda, kotlin.jvm.functions.Function2] */
    public UserFetcher(CoroutineScope coroutineScope, Executor executor, UserTracker userTracker, BroadcastFetcher broadcastFetcher) {
        boolean z = false;
        this.currentUserHandle = FlowKt.stateIn(new UserFetcher$special$$inlined$map$1(new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new SuspendLambda(2, (Continuation) null), FlowKt.shareIn(FlowKt.buffer$default(FlowKt.callbackFlow(new UserFetcher$currentUserChange$1(userTracker, executor, (Continuation) null)), -1), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), 0)), userTracker), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), UserHandle.of(-10000));
        IntentFilter intentFilter = new IntentFilter("android.intent.action.USER_UNLOCKED");
        z = intentFilter.countDataSchemes() == 0 ? true : z;
        Flow flow = (Flow) broadcastFetcher.cachedFlows.computeIfAbsent(new BroadcastFetcher.BroadcastKey(intentFilter, z), new BroadcastFetcher$getBroadcastFlow$1(z, broadcastFetcher, intentFilter));
    }
}
