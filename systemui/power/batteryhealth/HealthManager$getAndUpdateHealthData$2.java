package com.google.android.systemui.power.batteryhealth;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class HealthManager$getAndUpdateHealthData$2 extends SuspendLambda implements Function2 {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    final /* synthetic */ HealthManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HealthManager$getAndUpdateHealthData$2(HealthManager healthManager, Continuation continuation) {
        super(2, continuation);
        this.this$0 = healthManager;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new HealthManager$getAndUpdateHealthData$2(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((HealthManager$getAndUpdateHealthData$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x00f2 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00f3  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0118 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0119  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0140 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r19) {
        /*
            r18 = this;
            r0 = r18
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 5
            r4 = 4
            r5 = 3
            r6 = 2
            r7 = 1
            r8 = 0
            if (r2 == 0) goto L_0x0084
            if (r2 == r7) goto L_0x0075
            if (r2 == r6) goto L_0x0061
            if (r2 == r5) goto L_0x0047
            if (r2 == r4) goto L_0x0027
            if (r2 != r3) goto L_0x001f
            kotlin.ResultKt.throwOnFailure(r19)
            r0 = r19
            goto L_0x0141
        L_0x001f:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0027:
            java.lang.Object r2 = r0.L$4
            java.lang.Integer r2 = (java.lang.Integer) r2
            java.lang.Object r4 = r0.L$3
            java.lang.Integer r4 = (java.lang.Integer) r4
            java.lang.Object r5 = r0.L$2
            java.lang.Integer r5 = (java.lang.Integer) r5
            java.lang.Object r6 = r0.L$1
            android.content.SharedPreferences r6 = (android.content.SharedPreferences) r6
            java.lang.Object r7 = r0.L$0
            com.google.android.systemui.power.batteryhealth.HealthManager r7 = (com.google.android.systemui.power.batteryhealth.HealthManager) r7
            kotlin.ResultKt.throwOnFailure(r19)
            r12 = r2
            r11 = r4
            r10 = r5
            r14 = r6
            r15 = r7
            r4 = r19
            goto L_0x011e
        L_0x0047:
            java.lang.Object r2 = r0.L$3
            java.lang.Integer r2 = (java.lang.Integer) r2
            java.lang.Object r5 = r0.L$2
            java.lang.Integer r5 = (java.lang.Integer) r5
            java.lang.Object r6 = r0.L$1
            android.content.SharedPreferences r6 = (android.content.SharedPreferences) r6
            java.lang.Object r7 = r0.L$0
            com.google.android.systemui.power.batteryhealth.HealthManager r7 = (com.google.android.systemui.power.batteryhealth.HealthManager) r7
            kotlin.ResultKt.throwOnFailure(r19)
            r9 = r7
            r7 = r6
            r6 = r5
            r5 = r19
            goto L_0x00f8
        L_0x0061:
            java.lang.Object r2 = r0.L$2
            java.lang.Integer r2 = (java.lang.Integer) r2
            java.lang.Object r6 = r0.L$1
            android.content.SharedPreferences r6 = (android.content.SharedPreferences) r6
            java.lang.Object r7 = r0.L$0
            com.google.android.systemui.power.batteryhealth.HealthManager r7 = (com.google.android.systemui.power.batteryhealth.HealthManager) r7
            kotlin.ResultKt.throwOnFailure(r19)
            r9 = r7
            r7 = r6
            r6 = r19
            goto L_0x00d4
        L_0x0075:
            java.lang.Object r2 = r0.L$1
            android.content.SharedPreferences r2 = (android.content.SharedPreferences) r2
            java.lang.Object r7 = r0.L$0
            com.google.android.systemui.power.batteryhealth.HealthManager r7 = (com.google.android.systemui.power.batteryhealth.HealthManager) r7
            kotlin.ResultKt.throwOnFailure(r19)
            r9 = r7
            r7 = r19
            goto L_0x00b2
        L_0x0084:
            kotlin.ResultKt.throwOnFailure(r19)
            com.google.android.systemui.power.batteryhealth.HealthManager r2 = r0.this$0
            android.content.Context r9 = r2.context
            android.content.Context r9 = r9.getApplicationContext()
            java.lang.String r10 = "health_prefs"
            r11 = 0
            android.content.SharedPreferences r9 = r9.getSharedPreferences(r10, r11)
            com.google.android.systemui.power.batteryhealth.HealthManager r10 = r0.this$0
            r0.L$0 = r2
            r0.L$1 = r9
            r0.label = r7
            r10.getClass()
            com.google.android.systemui.power.batteryhealth.HealthManager$getHealthIndex$2 r7 = new com.google.android.systemui.power.batteryhealth.HealthManager$getHealthIndex$2
            r7.<init>(r10, r8)
            java.lang.Object r7 = kotlinx.coroutines.CoroutineScopeKt.coroutineScope(r0, r7)
            if (r7 != r1) goto L_0x00ad
            return r1
        L_0x00ad:
            r17 = r9
            r9 = r2
            r2 = r17
        L_0x00b2:
            java.lang.Integer r7 = (java.lang.Integer) r7
            com.google.android.systemui.power.batteryhealth.HealthManager r10 = r0.this$0
            r0.L$0 = r9
            r0.L$1 = r2
            r0.L$2 = r7
            r0.label = r6
            boolean r6 = com.google.android.systemui.power.batteryhealth.HealthManager.healthDebugEnabled
            r10.getClass()
            com.google.android.systemui.power.batteryhealth.HealthManager$getHealthImpedanceIndex$2 r6 = new com.google.android.systemui.power.batteryhealth.HealthManager$getHealthImpedanceIndex$2
            r6.<init>(r10, r8)
            java.lang.Object r6 = kotlinx.coroutines.CoroutineScopeKt.coroutineScope(r0, r6)
            if (r6 != r1) goto L_0x00cf
            return r1
        L_0x00cf:
            r17 = r7
            r7 = r2
            r2 = r17
        L_0x00d4:
            java.lang.Integer r6 = (java.lang.Integer) r6
            com.google.android.systemui.power.batteryhealth.HealthManager r10 = r0.this$0
            r0.L$0 = r9
            r0.L$1 = r7
            r0.L$2 = r2
            r0.L$3 = r6
            r0.label = r5
            boolean r5 = com.google.android.systemui.power.batteryhealth.HealthManager.healthDebugEnabled
            r10.getClass()
            com.google.android.systemui.power.batteryhealth.HealthManager$getHealthCapacityIndex$2 r5 = new com.google.android.systemui.power.batteryhealth.HealthManager$getHealthCapacityIndex$2
            r5.<init>(r10, r8)
            java.lang.Object r5 = kotlinx.coroutines.CoroutineScopeKt.coroutineScope(r0, r5)
            if (r5 != r1) goto L_0x00f3
            return r1
        L_0x00f3:
            r17 = r6
            r6 = r2
            r2 = r17
        L_0x00f8:
            java.lang.Integer r5 = (java.lang.Integer) r5
            com.google.android.systemui.power.batteryhealth.HealthManager r10 = r0.this$0
            r0.L$0 = r9
            r0.L$1 = r7
            r0.L$2 = r6
            r0.L$3 = r2
            r0.L$4 = r5
            r0.label = r4
            boolean r4 = com.google.android.systemui.power.batteryhealth.HealthManager.healthDebugEnabled
            r10.getClass()
            com.google.android.systemui.power.batteryhealth.HealthManager$getHealthStatus$2 r4 = new com.google.android.systemui.power.batteryhealth.HealthManager$getHealthStatus$2
            r4.<init>(r10, r8)
            java.lang.Object r4 = kotlinx.coroutines.CoroutineScopeKt.coroutineScope(r0, r4)
            if (r4 != r1) goto L_0x0119
            return r1
        L_0x0119:
            r11 = r2
            r12 = r5
            r10 = r6
            r14 = r7
            r15 = r9
        L_0x011e:
            r13 = r4
            java.lang.Integer r13 = (java.lang.Integer) r13
            r0.L$0 = r8
            r0.L$1 = r8
            r0.L$2 = r8
            r0.L$3 = r8
            r0.L$4 = r8
            r0.label = r3
            boolean r2 = com.google.android.systemui.power.batteryhealth.HealthManager.healthDebugEnabled
            r15.getClass()
            com.google.android.systemui.power.batteryhealth.HealthManager$saveAsHealthData$2 r2 = new com.google.android.systemui.power.batteryhealth.HealthManager$saveAsHealthData$2
            r16 = 0
            r9 = r2
            r9.<init>(r10, r11, r12, r13, r14, r15, r16)
            java.lang.Object r0 = kotlinx.coroutines.CoroutineScopeKt.coroutineScope(r0, r2)
            if (r0 != r1) goto L_0x0141
            return r1
        L_0x0141:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.power.batteryhealth.HealthManager$getAndUpdateHealthData$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
