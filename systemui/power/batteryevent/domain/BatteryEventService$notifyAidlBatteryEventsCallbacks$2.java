package com.google.android.systemui.power.batteryevent.domain;

import com.google.android.systemui.power.batteryevent.common.BatteryEvents;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class BatteryEventService$notifyAidlBatteryEventsCallbacks$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ BatteryEvents $events;
    int I$0;
    int I$1;
    long J$0;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ BatteryEventService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BatteryEventService$notifyAidlBatteryEventsCallbacks$2(BatteryEvents batteryEvents, BatteryEventService batteryEventService, Continuation continuation) {
        super(2, continuation);
        this.this$0 = batteryEventService;
        this.$events = batteryEvents;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new BatteryEventService$notifyAidlBatteryEventsCallbacks$2(this.$events, this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((BatteryEventService$notifyAidlBatteryEventsCallbacks$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0051 A[SYNTHETIC, Splitter:B:12:0x0051] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0075 A[Catch:{ all -> 0x0023 }] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0078 A[Catch:{ all -> 0x0023 }] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x007b A[Catch:{ all -> 0x0023 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r19) {
        /*
            r18 = this;
            r0 = r18
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            java.lang.String r3 = " ms"
            java.lang.String r4 = "BatteryEventService"
            r5 = 1
            if (r2 == 0) goto L_0x002e
            if (r2 != r5) goto L_0x0026
            int r2 = r0.I$1
            int r6 = r0.I$0
            long r7 = r0.J$0
            java.lang.Object r9 = r0.L$1
            com.google.android.systemui.power.batteryevent.common.BatteryEvents r9 = (com.google.android.systemui.power.batteryevent.common.BatteryEvents) r9
            java.lang.Object r10 = r0.L$0
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService r10 = (com.google.android.systemui.power.batteryevent.domain.BatteryEventService) r10
            kotlin.ResultKt.throwOnFailure(r19)     // Catch:{ all -> 0x0023 }
            r11 = r19
            goto L_0x0064
        L_0x0023:
            r0 = move-exception
            goto L_0x00a1
        L_0x0026:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x002e:
            kotlin.ResultKt.throwOnFailure(r19)
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService r2 = r0.this$0
            com.google.android.systemui.power.batteryevent.common.BatteryEvents r6 = r0.$events
            long r7 = java.lang.System.currentTimeMillis()
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService$aidlBatteryEventsCallbackListener$1 r9 = r2.aidlBatteryEventsCallbackListener
            int r9 = r9.beginBroadcast()
            java.lang.String r10 = "AIDL callback listeners count: "
            androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0.m(r10, r4, r9)
            r10 = 0
            r16 = r10
            r10 = r2
            r2 = r16
            r17 = r9
            r9 = r6
            r6 = r17
        L_0x004f:
            if (r2 >= r6) goto L_0x00a7
            r0.L$0 = r10     // Catch:{ all -> 0x0023 }
            r0.L$1 = r9     // Catch:{ all -> 0x0023 }
            r0.J$0 = r7     // Catch:{ all -> 0x0023 }
            r0.I$0 = r6     // Catch:{ all -> 0x0023 }
            r0.I$1 = r2     // Catch:{ all -> 0x0023 }
            r0.label = r5     // Catch:{ all -> 0x0023 }
            java.lang.Object r11 = com.google.android.systemui.power.batteryevent.domain.BatteryEventService.access$notifyAidlListenerBatteryEventUpdate(r10, r2, r9, r0)     // Catch:{ all -> 0x0023 }
            if (r11 != r1) goto L_0x0064
            return r1
        L_0x0064:
            java.lang.Number r11 = (java.lang.Number) r11     // Catch:{ all -> 0x0023 }
            long r11 = r11.longValue()     // Catch:{ all -> 0x0023 }
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService$aidlBatteryEventsCallbackListener$1 r13 = r10.aidlBatteryEventsCallbackListener     // Catch:{ all -> 0x0023 }
            java.lang.Object r13 = r13.getBroadcastCookie(r2)     // Catch:{ all -> 0x0023 }
            boolean r14 = r13 instanceof com.google.android.systemui.power.batteryevent.aidl.SurfaceType     // Catch:{ all -> 0x0023 }
            r15 = 0
            if (r14 == 0) goto L_0x0078
            com.google.android.systemui.power.batteryevent.aidl.SurfaceType r13 = (com.google.android.systemui.power.batteryevent.aidl.SurfaceType) r13     // Catch:{ all -> 0x0023 }
            goto L_0x0079
        L_0x0078:
            r13 = r15
        L_0x0079:
            if (r13 == 0) goto L_0x007f
            java.lang.String r15 = r13.name()     // Catch:{ all -> 0x0023 }
        L_0x007f:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x0023 }
            r13.<init>()     // Catch:{ all -> 0x0023 }
            java.lang.String r14 = "notify AIDL callback to "
            r13.append(r14)     // Catch:{ all -> 0x0023 }
            r13.append(r15)     // Catch:{ all -> 0x0023 }
            java.lang.String r14 = ", cost: "
            r13.append(r14)     // Catch:{ all -> 0x0023 }
            r13.append(r11)     // Catch:{ all -> 0x0023 }
            r13.append(r3)     // Catch:{ all -> 0x0023 }
            java.lang.String r11 = r13.toString()     // Catch:{ all -> 0x0023 }
            android.util.Log.d(r4, r11)     // Catch:{ all -> 0x0023 }
            int r2 = r2 + 1
            goto L_0x004f
        L_0x00a1:
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService$aidlBatteryEventsCallbackListener$1 r1 = r10.aidlBatteryEventsCallbackListener
            r1.finishBroadcast()
            throw r0
        L_0x00a7:
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService$aidlBatteryEventsCallbackListener$1 r0 = r10.aidlBatteryEventsCallbackListener
            r0.finishBroadcast()
            long r0 = java.lang.System.currentTimeMillis()
            long r0 = r0 - r7
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r5 = "notify all AIDL callbacks, cost: "
            r2.<init>(r5)
            r2.append(r0)
            r2.append(r3)
            java.lang.String r0 = r2.toString()
            int r0 = android.util.Log.i(r4, r0)
            java.lang.Integer r1 = new java.lang.Integer
            r1.<init>(r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.power.batteryevent.domain.BatteryEventService$notifyAidlBatteryEventsCallbacks$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
