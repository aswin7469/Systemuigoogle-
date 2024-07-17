package com.google.android.systemui.power;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class BatteryDefenderNotificationHandler$exitTempOrDwellDefenderIfNeeded$1 extends SuspendLambda implements Function2 {
    long J$0;
    int label;
    final /* synthetic */ BatteryDefenderNotificationHandler this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BatteryDefenderNotificationHandler$exitTempOrDwellDefenderIfNeeded$1(BatteryDefenderNotificationHandler batteryDefenderNotificationHandler, Continuation continuation) {
        super(2, continuation);
        this.this$0 = batteryDefenderNotificationHandler;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new BatteryDefenderNotificationHandler$exitTempOrDwellDefenderIfNeeded$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((BatteryDefenderNotificationHandler$exitTempOrDwellDefenderIfNeeded$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x0100  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0103 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0104 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r18) {
        /*
            r17 = this;
            r0 = r17
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            kotlin.Unit r3 = kotlin.Unit.INSTANCE
            r4 = 0
            r5 = 3
            r6 = 2
            r7 = 1
            if (r2 == 0) goto L_0x002e
            if (r2 == r7) goto L_0x0026
            if (r2 == r6) goto L_0x0021
            if (r2 != r5) goto L_0x0019
            kotlin.ResultKt.throwOnFailure(r18)
            goto L_0x0104
        L_0x0019:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0021:
            kotlin.ResultKt.throwOnFailure(r18)
            goto L_0x00e9
        L_0x0026:
            long r7 = r0.J$0
            kotlin.ResultKt.throwOnFailure(r18)
            r2 = r18
            goto L_0x0056
        L_0x002e:
            kotlin.ResultKt.throwOnFailure(r18)
            com.google.android.systemui.power.BatteryDefenderNotificationHandler r2 = r0.this$0
            com.android.systemui.util.time.SystemClock r2 = r2.systemClock
            com.android.systemui.util.time.SystemClockImpl r2 = (com.android.systemui.util.time.SystemClockImpl) r2
            r2.getClass()
            long r8 = java.lang.System.currentTimeMillis()
            com.google.android.systemui.power.BatteryDefenderNotificationHandler r2 = r0.this$0
            r0.J$0 = r8
            r0.label = r7
            r2.getClass()
            com.google.android.systemui.power.BatteryDefenderNotificationHandler$loadDefenderStartTimestamp$2 r7 = new com.google.android.systemui.power.BatteryDefenderNotificationHandler$loadDefenderStartTimestamp$2
            r7.<init>(r2, r4)
            kotlinx.coroutines.CoroutineDispatcher r2 = r2.backgroundDispatcher
            java.lang.Object r2 = kotlinx.coroutines.BuildersKt.withContext(r2, r7, r0)
            if (r2 != r1) goto L_0x0055
            return r1
        L_0x0055:
            r7 = r8
        L_0x0056:
            java.lang.Number r2 = (java.lang.Number) r2
            long r9 = r2.longValue()
            r11 = -1
            int r2 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            java.lang.String r11 = "BatteryDefenderNotification"
            if (r2 == 0) goto L_0x006b
            int r2 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r2 <= 0) goto L_0x006b
            long r12 = r7 - r9
            goto L_0x0072
        L_0x006b:
            java.lang.String r2 = "no defender start timestamp"
            android.util.Log.w(r11, r2)
            r12 = 0
        L_0x0072:
            com.google.android.systemui.power.BatteryDefenderNotificationHandler r2 = r0.this$0
            android.content.Context r2 = r2.context
            java.util.Locale r14 = com.google.android.systemui.power.PowerUtils.getLocale(r2)
            boolean r2 = android.text.format.DateFormat.is24HourFormat(r2)
            java.lang.String r15 = "h:m"
            java.lang.String r16 = "HH:mm"
            if (r2 == 0) goto L_0x0087
            r2 = r16
            goto L_0x0088
        L_0x0087:
            r2 = r15
        L_0x0088:
            java.lang.String r2 = android.text.format.DateFormat.getBestDateTimePattern(r14, r2)
            java.lang.CharSequence r2 = android.text.format.DateFormat.format(r2, r9)
            java.lang.String r2 = r2.toString()
            java.lang.String r2 = r2.toUpperCase(r14)
            com.google.android.systemui.power.BatteryDefenderNotificationHandler r9 = r0.this$0
            android.content.Context r9 = r9.context
            java.util.Locale r10 = com.google.android.systemui.power.PowerUtils.getLocale(r9)
            boolean r9 = android.text.format.DateFormat.is24HourFormat(r9)
            if (r9 == 0) goto L_0x00a8
            r15 = r16
        L_0x00a8:
            java.lang.String r9 = android.text.format.DateFormat.getBestDateTimePattern(r10, r15)
            java.lang.CharSequence r7 = android.text.format.DateFormat.format(r9, r7)
            java.lang.String r7 = r7.toString()
            java.lang.String r7 = r7.toUpperCase(r10)
            java.lang.String r8 = "battery defender, startTime: "
            java.lang.String r9 = ", endTime:"
            java.lang.String r10 = ", duration:"
            java.lang.StringBuilder r8 = com.android.compose.PlatformSliderColors$$ExternalSyntheticOutline0.m(r8, r2, r9, r7, r10)
            r8.append(r12)
            java.lang.String r9 = " ms"
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            android.util.Log.d(r11, r8)
            long r8 = com.google.android.systemui.power.BatteryDefenderNotificationHandler.POST_NOTIFICATION_THRESHOLD_MILLIS
            int r8 = (r12 > r8 ? 1 : (r12 == r8 ? 0 : -1))
            if (r8 <= 0) goto L_0x00e9
            com.google.android.systemui.power.BatteryDefenderNotificationHandler r8 = r0.this$0
            kotlinx.coroutines.CoroutineDispatcher r9 = r8.mainDispatcher
            com.google.android.systemui.power.BatteryDefenderNotificationHandler$exitTempOrDwellDefenderIfNeeded$1$1 r10 = new com.google.android.systemui.power.BatteryDefenderNotificationHandler$exitTempOrDwellDefenderIfNeeded$1$1
            r10.<init>(r2, r7, r4)
            r0.label = r6
            java.lang.Object r2 = kotlinx.coroutines.BuildersKt.withContext(r9, r10, r0)
            if (r2 != r1) goto L_0x00e9
            return r1
        L_0x00e9:
            com.google.android.systemui.power.BatteryDefenderNotificationHandler r2 = r0.this$0
            r0.label = r5
            int r5 = com.google.android.systemui.power.BatteryDefenderNotificationHandler.$r8$clinit
            r2.getClass()
            com.google.android.systemui.power.BatteryDefenderNotificationHandler$clearDefenderStartTimestamp$2 r5 = new com.google.android.systemui.power.BatteryDefenderNotificationHandler$clearDefenderStartTimestamp$2
            r5.<init>(r2, r4)
            kotlinx.coroutines.CoroutineDispatcher r2 = r2.backgroundDispatcher
            java.lang.Object r0 = kotlinx.coroutines.BuildersKt.withContext(r2, r5, r0)
            if (r0 != r1) goto L_0x0100
            goto L_0x0101
        L_0x0100:
            r0 = r3
        L_0x0101:
            if (r0 != r1) goto L_0x0104
            return r1
        L_0x0104:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.power.BatteryDefenderNotificationHandler$exitTempOrDwellDefenderIfNeeded$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
