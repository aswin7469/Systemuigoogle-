package com.google.android.systemui.power.batteryevent.domain;

import com.google.android.systemui.power.batteryevent.common.BatteryEvents;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class BatteryEventService$notifyBroadcastBatteryEventsUpdate$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ BatteryEvents $events;
    long J$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ BatteryEventService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BatteryEventService$notifyBroadcastBatteryEventsUpdate$2(BatteryEvents batteryEvents, BatteryEventService batteryEventService, Continuation continuation) {
        super(2, continuation);
        this.this$0 = batteryEventService;
        this.$events = batteryEvents;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new BatteryEventService$notifyBroadcastBatteryEventsUpdate$2(this.$events, this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((BatteryEventService$notifyBroadcastBatteryEventsUpdate$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v10, resolved type: com.google.android.systemui.power.batteryevent.domain.BatteryEventService$BatteryEventsBroadcastData} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0078  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x004d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) {
        /*
            r12 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r12.label
            java.lang.String r2 = "BatteryEventService"
            r3 = 1
            if (r1 == 0) goto L_0x0029
            if (r1 != r3) goto L_0x0021
            long r4 = r12.J$0
            java.lang.Object r1 = r12.L$3
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService$BatteryEventsBroadcastData r1 = (com.google.android.systemui.power.batteryevent.domain.BatteryEventService.BatteryEventsBroadcastData) r1
            java.lang.Object r6 = r12.L$2
            java.util.Iterator r6 = (java.util.Iterator) r6
            java.lang.Object r7 = r12.L$1
            com.google.android.systemui.power.batteryevent.common.BatteryEvents r7 = (com.google.android.systemui.power.batteryevent.common.BatteryEvents) r7
            java.lang.Object r8 = r12.L$0
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService r8 = (com.google.android.systemui.power.batteryevent.domain.BatteryEventService) r8
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x0073
        L_0x0021:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L_0x0029:
            kotlin.ResultKt.throwOnFailure(r13)
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService r13 = r12.this$0
            com.google.android.systemui.power.batteryevent.common.BatteryEvents r1 = r12.$events
            long r4 = java.lang.System.currentTimeMillis()
            java.util.concurrent.CopyOnWriteArraySet r6 = r13.broadcastIntentBatteryEventsListener
            int r6 = r6.size()
            java.lang.String r7 = "BatteryEventsListener (broadcast) count: "
            androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0.m(r7, r2, r6)
            java.util.concurrent.CopyOnWriteArraySet r6 = r13.broadcastIntentBatteryEventsListener
            java.util.Iterator r6 = r6.iterator()
            r8 = r13
            r7 = r1
        L_0x0047:
            boolean r13 = r6.hasNext()
            if (r13 == 0) goto L_0x00c7
            java.lang.Object r13 = r6.next()
            r1 = r13
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService$BatteryEventsBroadcastData r1 = (com.google.android.systemui.power.batteryevent.domain.BatteryEventService.BatteryEventsBroadcastData) r1
            java.util.Set r13 = r1.subscribedEvents
            java.lang.Iterable r13 = (java.lang.Iterable) r13
            java.util.Set r9 = r7.eventTypes
            java.lang.Iterable r9 = (java.lang.Iterable) r9
            java.util.Set r13 = kotlin.collections.CollectionsKt.intersect(r13, r9)
            r12.L$0 = r8
            r12.L$1 = r7
            r12.L$2 = r6
            r12.L$3 = r1
            r12.J$0 = r4
            r12.label = r3
            java.lang.Object r13 = com.google.android.systemui.power.batteryevent.domain.BatteryEventService.access$updateBatteryEventsBroadcastCache(r8, r1, r7, r13, r12)
            if (r13 != r0) goto L_0x0073
            return r0
        L_0x0073:
            com.google.android.systemui.power.batteryevent.domain.BatteryEventService$CachedBatteryEvents r13 = (com.google.android.systemui.power.batteryevent.domain.BatteryEventService.CachedBatteryEvents) r13
            if (r13 != 0) goto L_0x0078
            goto L_0x0047
        L_0x0078:
            android.content.Intent r9 = new android.content.Intent
            java.lang.String r10 = "com.google.android.battery_event.BATTERY_EVENTS_UPDATE"
            r9.<init>(r10)
            android.content.ComponentName r1 = r1.componentName
            r9.setComponent(r1)
            java.util.Set r1 = r13.needNotifiedEvents
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.ArrayList r10 = new java.util.ArrayList
            int r11 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r1)
            r10.<init>(r11)
            java.util.Iterator r1 = r1.iterator()
        L_0x0095:
            boolean r11 = r1.hasNext()
            if (r11 == 0) goto L_0x00a9
            java.lang.Object r11 = r1.next()
            com.google.android.systemui.power.batteryevent.aidl.BatteryEventType r11 = (com.google.android.systemui.power.batteryevent.aidl.BatteryEventType) r11
            java.lang.String r11 = r11.getTypeName()
            r10.add(r11)
            goto L_0x0095
        L_0x00a9:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>(r10)
            java.lang.String r10 = "battery_event.event_name_list"
            r9.putStringArrayListExtra(r10, r1)
            java.lang.String r1 = "battery_event.battery_level"
            int r10 = r13.batteryLevel
            r9.putExtra(r1, r10)
            java.lang.String r1 = "battery_event.battery_plugged"
            int r13 = r13.pluggedType
            r9.putExtra(r1, r13)
            com.android.systemui.broadcast.BroadcastSender r13 = r8.broadcastSender
            r13.sendBroadcast(r9)
            goto L_0x0047
        L_0x00c7:
            long r12 = java.lang.System.currentTimeMillis()
            long r12 = r12 - r4
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "notify all broadcast intent, cost: "
            r0.<init>(r1)
            r0.append(r12)
            java.lang.String r12 = " ms"
            r0.append(r12)
            java.lang.String r12 = r0.toString()
            android.util.Log.i(r2, r12)
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.power.batteryevent.domain.BatteryEventService$notifyBroadcastBatteryEventsUpdate$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
