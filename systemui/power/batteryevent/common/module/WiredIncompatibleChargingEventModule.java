package com.google.android.systemui.power.batteryevent.common.module;

import android.content.Context;
import com.android.settingslib.Utils;
import com.google.android.systemui.power.batteryevent.aidl.BatteryEventType;
import com.google.android.systemui.power.batteryevent.common.data.SystemEventData;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class WiredIncompatibleChargingEventModule extends BaseBatteryEventModule {
    public final Context context;
    public final WiredIncompatibleChargingUtilImpl utils;

    public WiredIncompatibleChargingEventModule(Context context2, WiredIncompatibleChargingUtilImpl wiredIncompatibleChargingUtilImpl) {
        this.context = context2;
        this.utils = wiredIncompatibleChargingUtilImpl;
    }

    public final List getEventDataTypes() {
        return EmptyList.INSTANCE;
    }

    public final List getIntentActions() {
        return CollectionsKt__CollectionsKt.listOf("android.hardware.usb.action.USB_PORT_COMPLIANCE_CHANGED", "android.intent.action.BATTERY_CHANGED");
    }

    public final BatteryEventType getModuleType() {
        return BatteryEventType.WIRED_INCOMPATIBLE_CHARGING;
    }

    public final boolean validate(SystemEventData systemEventData) {
        if (Intrinsics.areEqual(systemEventData.intentAction, "android.hardware.usb.action.USB_PORT_COMPLIANCE_CHANGED")) {
            this.utils.getClass();
            this.lastValidation = Utils.containsIncompatibleChargers(this.context, "WiredIncompatibleEvent");
        }
        return this.lastValidation;
    }
}
