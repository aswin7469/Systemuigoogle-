package com.google.android.systemui.power.batteryevent.common.module;

import com.google.android.systemui.power.batteryevent.aidl.BatteryEventType;
import com.google.android.systemui.power.batteryevent.common.data.SystemEventData;
import java.util.List;
import kotlin.collections.EmptyList;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class BaseBatteryEventModule {
    public boolean lastValidation;

    public BaseBatteryEventModule() {
        EmptyList emptyList = EmptyList.INSTANCE;
    }

    public abstract List getEventDataTypes();

    public abstract List getIntentActions();

    public abstract BatteryEventType getModuleType();

    public abstract boolean validate(SystemEventData systemEventData);
}
