package com.google.android.systemui.columbus.sensors;

import com.android.systemui.util.time.SystemClock;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.internal.SubscriptionCountStateFlow;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class SharingStartedWhileSubscribedThrottled implements SharingStarted {
    public final SystemClock clock = new Object();

    /* JADX WARNING: type inference failed for: r0v0, types: [kotlin.coroutines.jvm.internal.SuspendLambda, kotlin.jvm.functions.Function2] */
    /* JADX WARNING: type inference failed for: r0v1, types: [kotlin.coroutines.jvm.internal.SuspendLambda, kotlin.jvm.functions.Function2] */
    public final Flow command(SubscriptionCountStateFlow subscriptionCountStateFlow) {
        return FlowKt.distinctUntilChanged(com.android.systemui.util.kotlin.FlowKt.throttle(new FlowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1(new SuspendLambda(2, (Continuation) null), FlowKt.distinctUntilChanged(FlowKt.mapLatest(new SuspendLambda(2, (Continuation) null), subscriptionCountStateFlow))), 1000, this.clock));
    }
}
