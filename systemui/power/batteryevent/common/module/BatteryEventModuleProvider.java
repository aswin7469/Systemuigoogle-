package com.google.android.systemui.power.batteryevent.common.module;

import android.content.Context;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class BatteryEventModuleProvider {
    public final List eventModuleList;

    /* JADX WARNING: type inference failed for: r11v0, types: [java.lang.Object, com.google.android.systemui.power.batteryevent.common.module.WiredIncompatibleChargingUtilImpl] */
    public BatteryEventModuleProvider(Context context) {
        this.eventModuleList = CollectionsKt__CollectionsKt.listOf(new BaseBatteryEventModule(), new BaseBatteryEventModule(), new BaseBatteryEventModule(), new BaseBatteryEventModule(), new BaseBatteryEventModule(), new BaseBatteryEventModule(), new BaseBatteryEventModule(), new RegularChargingEventModule(context), new SlowChargingEventModule(context), new FastChargingEventModule(context), new WiredIncompatibleChargingEventModule(context, new Object()));
    }
}
