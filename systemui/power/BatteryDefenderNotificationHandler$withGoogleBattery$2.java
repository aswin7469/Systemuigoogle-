package com.google.android.systemui.power;

import android.util.Log;
import com.google.android.systemui.googlebattery.GoogleBatteryManager;
import com.google.android.systemui.power.batteryevent.repository.GoogleBatteryManagerWrapperImpl;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import vendor.google.google_battery.IGoogleBattery;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class BatteryDefenderNotificationHandler$withGoogleBattery$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Function1 $action;
    int label;
    final /* synthetic */ BatteryDefenderNotificationHandler this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BatteryDefenderNotificationHandler$withGoogleBattery$2(BatteryDefenderNotificationHandler batteryDefenderNotificationHandler, Function1 function1, Continuation continuation) {
        super(2, continuation);
        this.this$0 = batteryDefenderNotificationHandler;
        this.$action = function1;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new BatteryDefenderNotificationHandler$withGoogleBattery$2(this.this$0, this.$action, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((BatteryDefenderNotificationHandler$withGoogleBattery$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        GoogleBatteryManagerWrapperImpl googleBatteryManagerWrapperImpl;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            BatteryDefenderNotificationHandler$withGoogleBattery$2$deathRecipient$1 batteryDefenderNotificationHandler$withGoogleBattery$2$deathRecipient$1 = BatteryDefenderNotificationHandler$withGoogleBattery$2$deathRecipient$1.INSTANCE;
            this.this$0.googleBatteryManagerWrapper.getClass();
            IGoogleBattery initHalInterface = GoogleBatteryManager.initHalInterface(batteryDefenderNotificationHandler$withGoogleBattery$2$deathRecipient$1);
            Unit unit = Unit.INSTANCE;
            if (initHalInterface == null) {
                Log.w("BatteryDefenderNotification", "operation failed. cannot init hal interface");
                return unit;
            }
            try {
                this.$action.invoke(initHalInterface);
                googleBatteryManagerWrapperImpl = this.this$0.googleBatteryManagerWrapper;
            } catch (Exception e) {
                Log.e("BatteryDefenderNotification", "operation error: ", e);
                googleBatteryManagerWrapperImpl = this.this$0.googleBatteryManagerWrapper;
            } catch (Throwable th) {
                this.this$0.googleBatteryManagerWrapper.getClass();
                GoogleBatteryManager.destroyHalInterface(initHalInterface, batteryDefenderNotificationHandler$withGoogleBattery$2$deathRecipient$1);
                throw th;
            }
            googleBatteryManagerWrapperImpl.getClass();
            GoogleBatteryManager.destroyHalInterface(initHalInterface, batteryDefenderNotificationHandler$withGoogleBattery$2$deathRecipient$1);
            return unit;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
