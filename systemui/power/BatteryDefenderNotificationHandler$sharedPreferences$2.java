package com.google.android.systemui.power;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class BatteryDefenderNotificationHandler$sharedPreferences$2 extends Lambda implements Function0 {
    final /* synthetic */ BatteryDefenderNotificationHandler this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BatteryDefenderNotificationHandler$sharedPreferences$2(BatteryDefenderNotificationHandler batteryDefenderNotificationHandler) {
        super(0);
        this.this$0 = batteryDefenderNotificationHandler;
    }

    public final Object invoke() {
        return this.this$0.context.getApplicationContext().getSharedPreferences("defender_shared_prefs", 0);
    }
}
