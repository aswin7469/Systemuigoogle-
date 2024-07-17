package com.google.android.systemui.power.batteryhealth;

import android.util.Log;
import java.util.Arrays;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class HealthService$binder$1$registerHealthListener$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ String[] $callerPackage;
    final /* synthetic */ IHealthListener $listener;
    int label;
    final /* synthetic */ HealthService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HealthService$binder$1$registerHealthListener$1(String[] strArr, HealthService healthService, IHealthListener iHealthListener, Continuation continuation) {
        super(2, continuation);
        this.$callerPackage = strArr;
        this.this$0 = healthService;
        this.$listener = iHealthListener;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new HealthService$binder$1$registerHealthListener$1(this.$callerPackage, this.this$0, this.$listener, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        Unit unit = Unit.INSTANCE;
        ((HealthService$binder$1$registerHealthListener$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(unit);
        return unit;
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            String arrays = Arrays.toString(this.$callerPackage);
            Log.i("HealthService", "registerHealthListener: " + arrays);
            this.this$0.healthListeners.register(this.$listener);
            HealthService healthService = this.this$0;
            int registeredCallbackCount = healthService.healthListeners.getRegisteredCallbackCount();
            healthService.registeredListenerNum$delegate.setValue(healthService, Integer.valueOf(registeredCallbackCount), HealthService.$$delegatedProperties[0]);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
