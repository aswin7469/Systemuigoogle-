package com.google.android.systemui.columbus.sensors;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class GestureController$special$$inlined$filter$1 implements Flow {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Flow $this_unsafeTransform$inlined;
    public final /* synthetic */ GestureController this$0;

    public /* synthetic */ GestureController$special$$inlined$filter$1(Flow flow, GestureController gestureController, int i) {
        this.$r8$classId = i;
        this.$this_unsafeTransform$inlined = flow;
        this.this$0 = gestureController;
    }

    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
        Unit unit = Unit.INSTANCE;
        int i = this.$r8$classId;
        Flow flow = this.$this_unsafeTransform$inlined;
        GestureController gestureController = this.this$0;
        switch (i) {
            case 0:
                Object collect = flow.collect(new FlowCollector(flowCollector, gestureController) {
                    public final /* synthetic */ GestureController this$0;

                    {
                        this.this$0 = r2;
                    }

                    /* JADX WARNING: Removed duplicated region for block: B:14:0x0031  */
                    /* JADX WARNING: Removed duplicated region for block: B:8:0x0020  */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public final java.lang.Object emit(java.lang.Object r5, kotlin.coroutines.Continuation r6) {
                        /*
                            r4 = this;
                            boolean r0 = r6 instanceof com.google.android.systemui.columbus.sensors.GestureController$special$$inlined$filter$1.AnonymousClass2.AnonymousClass1
                            if (r0 == 0) goto L_0x0013
                            r0 = r6
                            com.google.android.systemui.columbus.sensors.GestureController$special$$inlined$filter$1$2$1 r0 = (com.google.android.systemui.columbus.sensors.GestureController$special$$inlined$filter$1.AnonymousClass2.AnonymousClass1) r0
                            int r1 = r0.label
                            r2 = -2147483648(0xffffffff80000000, float:-0.0)
                            r3 = r1 & r2
                            if (r3 == 0) goto L_0x0013
                            int r1 = r1 - r2
                            r0.label = r1
                            goto L_0x0018
                        L_0x0013:
                            com.google.android.systemui.columbus.sensors.GestureController$special$$inlined$filter$1$2$1 r0 = new com.google.android.systemui.columbus.sensors.GestureController$special$$inlined$filter$1$2$1
                            r0.<init>(r6)
                        L_0x0018:
                            java.lang.Object r6 = r0.result
                            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                            int r0 = r0.label
                            if (r0 == 0) goto L_0x0031
                            r4 = 1
                            if (r0 != r4) goto L_0x0029
                            kotlin.ResultKt.throwOnFailure(r6)
                            kotlin.Unit r4 = kotlin.Unit.INSTANCE
                            return r4
                        L_0x0029:
                            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                            r4.<init>(r5)
                            throw r4
                        L_0x0031:
                            kotlin.ResultKt.throwOnFailure(r6)
                            androidx.appcompat.app.WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(r5)
                            com.google.android.systemui.columbus.sensors.GestureController r4 = r4.this$0
                            com.android.systemui.util.time.SystemClock r5 = r4.systemClock
                            com.android.systemui.util.time.SystemClockImpl r5 = (com.android.systemui.util.time.SystemClockImpl) r5
                            r5.getClass()
                            android.os.SystemClock.elapsedRealtime()
                            android.util.SparseLongArray r4 = r4.lastTimestampMap
                            r4 = 0
                            throw r4
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.columbus.sensors.GestureController$special$$inlined$filter$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }, continuation);
                if (collect == CoroutineSingletons.COROUTINE_SUSPENDED) {
                    return collect;
                }
                return unit;
            default:
                Object collect2 = flow.collect(new GestureController$special$$inlined$filter$2$2(flowCollector, gestureController), continuation);
                if (collect2 == CoroutineSingletons.COROUTINE_SUSPENDED) {
                    return collect2;
                }
                return unit;
        }
    }
}
