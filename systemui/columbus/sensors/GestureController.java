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
import kotlinx.coroutines.flow.EmptyFlow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.ReadonlySharedFlow;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class GestureController implements Dumpable {
    public final ReadonlySharedFlow gestureEvents;
    public final NoOpGestureSensor gestureSensor;
    public final SparseLongArray lastTimestampMap = new SparseLongArray();
    public final SystemClock systemClock;

    /* JADX WARNING: type inference failed for: r5v3, types: [kotlin.coroutines.jvm.internal.SuspendLambda, kotlin.jvm.functions.Function3] */
    public GestureController(CoroutineScope coroutineScope, NoOpGestureSensor noOpGestureSensor, Set set, GateFetcher gateFetcher, UiEventLogger uiEventLogger, SystemClock systemClock2) {
        this.gestureSensor = noOpGestureSensor;
        this.systemClock = systemClock2;
        FlowKt.shareIn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(new GestureController$gestureEvents$5(uiEventLogger, (Continuation) null), new GestureController$special$$inlined$map$1(new GestureController$special$$inlined$filter$1(com.android.systemui.util.kotlin.FlowKt.sample(new GestureController$special$$inlined$filter$1(EmptyFlow.INSTANCE, this, 0), (StateFlow) gateFetcher.blockingGateMap.computeIfAbsent(new GateFetcher.GateCollectionKey(set), new GateFetcher$getBlockingGateFlow$1(gateFetcher)), new SuspendLambda(3, (Continuation) null)), this, 1))), coroutineScope, new SharingStartedWhileSubscribedThrottled(), 0);
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
