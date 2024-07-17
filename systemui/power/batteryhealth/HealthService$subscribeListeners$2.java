package com.google.android.systemui.power.batteryhealth;

import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class HealthService$subscribeListeners$2 implements FlowCollector {
    public final /* synthetic */ HealthService this$0;

    public HealthService$subscribeListeners$2(HealthService healthService) {
        this.this$0 = healthService;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object emit(java.lang.Object r4, kotlin.coroutines.Continuation r5) {
        /*
            r3 = this;
            kotlin.Pair r4 = (kotlin.Pair) r4
            java.lang.Object r0 = r4.getSecond()
            java.lang.String r0 = (java.lang.String) r0
            if (r0 == 0) goto L_0x00a1
            java.lang.Object r1 = r4.getFirst()
            android.content.SharedPreferences r1 = (android.content.SharedPreferences) r1
            boolean r1 = r1.contains(r0)
            java.lang.String r2 = "HealthService"
            if (r1 == 0) goto L_0x008b
            java.lang.String r1 = "Notify data update for key: "
            java.lang.String r1 = r1.concat(r0)
            android.util.Log.i(r2, r1)
            int r1 = r0.hashCode()
            com.google.android.systemui.power.batteryhealth.HealthService r3 = r3.this$0
            switch(r1) {
                case -2076922611: goto L_0x006f;
                case 851466543: goto L_0x0059;
                case 917416757: goto L_0x0042;
                case 1564109820: goto L_0x002b;
                default: goto L_0x002a;
            }
        L_0x002a:
            goto L_0x0077
        L_0x002b:
            java.lang.String r1 = "perf_index"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0034
            goto L_0x0077
        L_0x0034:
            com.google.android.systemui.power.batteryhealth.HealthService$subscribeListeners$2$1$2 r0 = new com.google.android.systemui.power.batteryhealth.HealthService$subscribeListeners$2$1$2
            r0.<init>(r4)
            java.lang.Object r3 = com.google.android.systemui.power.batteryhealth.HealthService.access$notifyListeners(r3, r0, r5)
            kotlin.coroutines.intrinsics.CoroutineSingletons r4 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            if (r3 != r4) goto L_0x00a1
            goto L_0x00a3
        L_0x0042:
            java.lang.String r1 = "health_status"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x004b
            goto L_0x0077
        L_0x004b:
            com.google.android.systemui.power.batteryhealth.HealthService$subscribeListeners$2$1$4 r0 = new com.google.android.systemui.power.batteryhealth.HealthService$subscribeListeners$2$1$4
            r0.<init>(r4)
            java.lang.Object r3 = com.google.android.systemui.power.batteryhealth.HealthService.access$notifyListeners(r3, r0, r5)
            kotlin.coroutines.intrinsics.CoroutineSingletons r4 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            if (r3 != r4) goto L_0x00a1
            goto L_0x00a3
        L_0x0059:
            java.lang.String r1 = "health_index"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0077
            com.google.android.systemui.power.batteryhealth.HealthService$subscribeListeners$2$1$1 r0 = new com.google.android.systemui.power.batteryhealth.HealthService$subscribeListeners$2$1$1
            r0.<init>(r4)
            java.lang.Object r3 = com.google.android.systemui.power.batteryhealth.HealthService.access$notifyListeners(r3, r0, r5)
            kotlin.coroutines.intrinsics.CoroutineSingletons r4 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            if (r3 != r4) goto L_0x00a1
            goto L_0x00a3
        L_0x006f:
            java.lang.String r1 = "capacity_index"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x007d
        L_0x0077:
            java.lang.String r3 = "Unknown prefs key"
            android.util.Log.i(r2, r3)
            goto L_0x00a1
        L_0x007d:
            com.google.android.systemui.power.batteryhealth.HealthService$subscribeListeners$2$1$3 r0 = new com.google.android.systemui.power.batteryhealth.HealthService$subscribeListeners$2$1$3
            r0.<init>(r4)
            java.lang.Object r3 = com.google.android.systemui.power.batteryhealth.HealthService.access$notifyListeners(r3, r0, r5)
            kotlin.coroutines.intrinsics.CoroutineSingletons r4 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            if (r3 != r4) goto L_0x00a1
            goto L_0x00a3
        L_0x008b:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Key: "
            r3.<init>(r4)
            r3.append(r0)
            java.lang.String r4 = ", not from prefs"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            android.util.Log.i(r2, r3)
        L_0x00a1:
            kotlin.Unit r3 = kotlin.Unit.INSTANCE
        L_0x00a3:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.power.batteryhealth.HealthService$subscribeListeners$2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
