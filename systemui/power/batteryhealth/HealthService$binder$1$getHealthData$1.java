package com.google.android.systemui.power.batteryhealth;

import android.util.Log;
import java.util.Arrays;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class HealthService$binder$1$getHealthData$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ String[] $callerPackage;
    int label;
    final /* synthetic */ HealthService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HealthService$binder$1$getHealthData$1(String[] strArr, HealthService healthService, Continuation continuation) {
        super(2, continuation);
        this.$callerPackage = strArr;
        this.this$0 = healthService;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new HealthService$binder$1$getHealthData$1(this.$callerPackage, this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((HealthService$binder$1$getHealthData$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            String arrays = Arrays.toString(this.$callerPackage);
            Log.i("HealthService", "getHealthData: " + arrays);
            String[] strArr = this.$callerPackage;
            if (strArr == null || !ArraysKt___ArraysKt.contains((Object[]) strArr, (Object) "com.google.android.apps.diagnosticstool")) {
                HealthManager healthManager = this.this$0.healthManager;
                this.label = 2;
                healthManager.getClass();
                obj = BuildersKt.withContext(healthManager.bgDispatcher, new HealthManager$getOnBackground$2((Continuation) null, new HealthManager$getHealthData$2(healthManager, (Continuation) null)), this);
                if (obj == coroutineSingletons) {
                    return coroutineSingletons;
                }
                return (HealthData) obj;
            }
            HealthManager healthManager2 = this.this$0.healthManager;
            this.label = 1;
            healthManager2.getClass();
            obj = BuildersKt.withContext(healthManager2.bgDispatcher, new HealthManager$getOnBackground$2((Continuation) null, new HealthManager$getHealthData$4(healthManager2, 8, (Continuation) null)), this);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else if (i == 2) {
            ResultKt.throwOnFailure(obj);
            return (HealthData) obj;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return (HealthData) obj;
    }
}
