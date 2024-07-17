package com.google.android.systemui.power.batteryevent.common.module;

import com.google.android.systemui.power.batteryevent.aidl.BatteryEventType;
import com.google.android.systemui.power.batteryevent.common.data.SystemEventData;
import java.util.List;
import kotlin.collections.EmptyList;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
