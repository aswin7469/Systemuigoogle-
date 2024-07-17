package com.google.android.systemui.columbus.sensors;

import android.util.SparseLongArray;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.Dumpable;
import com.android.systemui.util.time.SystemClock;
import com.google.android.systemui.columbus.fetchers.GateFetcher;
import com.google.android.systemui.columbus.fetchers.GateFetcher$getBlockingGateFlow$1;
import java.io.PrintWriter;
import java.util.Set;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.ReadonlySharedFlow;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class GestureController implements Dumpable {
    public final ReadonlySharedFlow gestureEvents;
    public final NoOpGestureSensor gestureSensor;
    public final SparseLongArray lastTimestampMap;
    public final SystemClock systemClock;

    /* JADX WARNING: type inference failed for: r5v1, types: [kotlin.coroutines.jvm.internal.SuspendLambda, kotlin.jvm.functions.Function3] */
    public GestureController(CoroutineScope coroutineScope, NoOpGestureSensor noOpGestureSensor, Set set, GateFetcher gateFetcher, UiEventLogger uiEventLogger, SystemClock systemClock2) {
        this.gestureSensor = noOpGestureSensor;
        FlowKt.shareIn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(new GestureController$special$$inlined$map$1(0, new GestureController$special$$inlined$filter$2(com.android.systemui.util.kotlin.FlowKt.sample(new GestureController$special$$inlined$map$1(1, this), (StateFlow) gateFetcher.blockingGateMap.computeIfAbsent(new GateFetcher.GateCollectionKey(set), new GateFetcher$getBlockingGateFlow$1(gateFetcher)), new SuspendLambda(3, (Continuation) null)), this)), new GestureController$gestureEvents$5(uiEventLogger, (Continuation) null), 0), coroutineScope, new SharingStartedWhileSubscribedThrottled(), 0);
        new SparseLongArray();
    }

    public final void dump(PrintWriter printWriter, String[] strArr) {
        Dumpable dumpable;
        printWriter.println("  Repeat Gesture Blocks: 0");
        printWriter.println("  Soft Blocks: 0");
        StringBuilder sb = new StringBuilder("  Gesture Sensor: ");
        NoOpGestureSensor noOpGestureSensor = this.gestureSensor;
        sb.append(noOpGestureSensor);
        printWriter.println(sb.toString());
        if (noOpGestureSensor instanceof Dumpable) {
            dumpable = (Dumpable) noOpGestureSensor;
        } else {
            dumpable = null;
        }
        if (dumpable != null) {
            dumpable.dump(printWriter, strArr);
        }
    }
}
