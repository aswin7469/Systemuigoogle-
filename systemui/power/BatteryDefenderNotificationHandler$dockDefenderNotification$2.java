package com.google.android.systemui.power;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class BatteryDefenderNotificationHandler$dockDefenderNotification$2 extends Lambda implements Function0 {
    final /* synthetic */ BatteryDefenderNotificationHandler this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BatteryDefenderNotificationHandler$dockDefenderNotification$2(BatteryDefenderNotificationHandler batteryDefenderNotificationHandler) {
        super(0);
        this.this$0 = batteryDefenderNotificationHandler;
    }

    public final Object invoke() {
        BatteryDefenderNotificationHandler batteryDefenderNotificationHandler = this.this$0;
        return new DockDefenderNotification(batteryDefenderNotificationHandler.context, batteryDefenderNotificationHandler.notificationManager);
    }
}
