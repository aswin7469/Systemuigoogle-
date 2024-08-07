package com.google.android.systemui.power;

import android.content.SharedPreferences;
import android.util.Log;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class BatteryDefenderNotificationHandler$loadDefenderStartTimestamp$2 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ BatteryDefenderNotificationHandler this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BatteryDefenderNotificationHandler$loadDefenderStartTimestamp$2(BatteryDefenderNotificationHandler batteryDefenderNotificationHandler, Continuation continuation) {
        super(2, continuation);
        this.this$0 = batteryDefenderNotificationHandler;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new BatteryDefenderNotificationHandler$loadDefenderStartTimestamp$2(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((BatteryDefenderNotificationHandler$loadDefenderStartTimestamp$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            BatteryDefenderNotificationHandler batteryDefenderNotificationHandler = this.this$0;
            int i = BatteryDefenderNotificationHandler.$r8$clinit;
            long j = ((SharedPreferences) batteryDefenderNotificationHandler.sharedPreferences$delegate.getValue()).getLong("trigger_time", -1);
            Log.d("BatteryDefenderNotification", "loadDefenderStartTimestamp: " + j);
            return new Long(j);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
