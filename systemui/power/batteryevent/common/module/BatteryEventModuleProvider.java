package com.google.android.systemui.power.batteryevent.common.module;

import android.content.Context;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class BatteryEventModuleProvider {
    public final List eventModuleList;

    /* JADX WARNING: type inference failed for: r12v0, types: [java.lang.Object, com.google.android.systemui.power.batteryevent.common.module.WiredIncompatibleChargingUtilImpl] */
    public BatteryEventModuleProvider(Context context) {
        this.eventModuleList = CollectionsKt__CollectionsKt.listOf(new BaseBatteryEventModule(), new BaseBatteryEventModule(), new BaseBatteryEventModule(), new BaseBatteryEventModule(), new BaseBatteryEventModule(), new BaseBatteryEventModule(), new BaseBatteryEventModule(), new BaseBatteryEventModule(), new RegularChargingEventModule(context), new SlowChargingEventModule(context), new FastChargingEventModule(context), new WiredIncompatibleChargingEventModule(context, new Object()));
    }
}
