package com.google.android.systemui.power;

import android.provider.Settings;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class BatteryDefenderNotificationHandler$onActionBootCompleted$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ BatteryDefenderNotificationHandler this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BatteryDefenderNotificationHandler$onActionBootCompleted$1(BatteryDefenderNotificationHandler batteryDefenderNotificationHandler, Continuation continuation) {
        super(2, continuation);
        this.this$0 = batteryDefenderNotificationHandler;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new BatteryDefenderNotificationHandler$onActionBootCompleted$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((BatteryDefenderNotificationHandler$onActionBootCompleted$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object obj2 = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Object obj3 = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            BatteryDefenderNotificationHandler batteryDefenderNotificationHandler = this.this$0;
            this.label = 1;
            int i2 = BatteryDefenderNotificationHandler.$r8$clinit;
            BatteryDefenderNotificationHandler$enableDockDefenderFeature$2 batteryDefenderNotificationHandler$enableDockDefenderFeature$2 = BatteryDefenderNotificationHandler$enableDockDefenderFeature$2.INSTANCE;
            batteryDefenderNotificationHandler.getClass();
            Object withContext = BuildersKt.withContext(batteryDefenderNotificationHandler.backgroundDispatcher, new BatteryDefenderNotificationHandler$withGoogleBattery$2(batteryDefenderNotificationHandler, batteryDefenderNotificationHandler$enableDockDefenderFeature$2, (Continuation) null), this);
            if (withContext != obj2) {
                withContext = obj3;
            }
            if (withContext != obj2) {
                withContext = obj3;
            }
            if (withContext == obj2) {
                return obj2;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Settings.Global.putInt(this.this$0.context.getContentResolver(), "dock_defender_bypass", 0);
        return obj3;
    }
}
