package com.google.android.systemui.power.batteryevent.domain;

import android.content.IntentFilter;
import android.os.UserHandle;
import com.android.settingslib.fuelgauge.BatteryStatus;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.google.android.systemui.power.batteryevent.common.BatteryEventSubscriber;
import com.google.android.systemui.power.batteryevent.common.BatteryEvents;
import com.google.android.systemui.power.batteryevent.common.data.SystemEventData;
import com.google.android.systemui.power.batteryevent.common.module.BaseBatteryEventModule;
import com.google.android.systemui.power.batteryevent.common.module.BatteryEventModuleProvider;
import com.google.android.systemui.power.batteryevent.repository.EventSourceMonitor;
import com.google.android.systemui.power.batteryevent.repository.SystemEventDataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class BatteryEventStateController {
    public final BatteryEventModuleProvider batteryEventModuleProvider;
    public final StateFlowImpl mutableBatteryEventsFlow = StateFlowKt.MutableStateFlow((Object) null);
    public final EventSourceMonitor systemEventDataSource;

    public BatteryEventStateController(BatteryEventModuleProvider batteryEventModuleProvider2, EventSourceMonitor eventSourceMonitor) {
        this.batteryEventModuleProvider = batteryEventModuleProvider2;
        this.systemEventDataSource = eventSourceMonitor;
        List<BaseBatteryEventModule> list = batteryEventModuleProvider2.eventModuleList;
        ArrayList<BatteryEventSubscriber> arrayList = new ArrayList<>(CollectionsKt__IterablesKt.collectionSizeOrDefault(list));
        for (BaseBatteryEventModule baseBatteryEventModule : list) {
            arrayList.add(new BatteryEventSubscriber(baseBatteryEventModule.getModuleType(), baseBatteryEventModule.getIntentActions(), baseBatteryEventModule.getEventDataTypes()));
        }
        EventSourceMonitor eventSourceMonitor2 = this.systemEventDataSource;
        AnonymousClass1 r0 = new Function2() {
            public final Object invoke(Object obj, Object obj2) {
                SystemEventData systemEventData = (SystemEventData) obj;
                List list = (List) obj2;
                int batteryLevel = BatteryStatus.getBatteryLevel(((Number) systemEventData.batteryLevel.value).intValue(), ((Number) systemEventData.batteryScale.value).intValue());
                List list2 = BatteryEventStateController.this.batteryEventModuleProvider.eventModuleList;
                ArrayList<BaseBatteryEventModule> arrayList = new ArrayList<>();
                for (Object next : list2) {
                    BaseBatteryEventModule baseBatteryEventModule = (BaseBatteryEventModule) next;
                    if (list.contains(baseBatteryEventModule.getModuleType()) && baseBatteryEventModule.validate(systemEventData)) {
                        arrayList.add(next);
                    }
                }
                StateFlowImpl stateFlowImpl = BatteryEventStateController.this.mutableBatteryEventsFlow;
                ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList));
                for (BaseBatteryEventModule moduleType : arrayList) {
                    arrayList2.add(moduleType.getModuleType());
                }
                stateFlowImpl.setValue(new BatteryEvents(CollectionsKt.toSet(arrayList2), batteryLevel, ((Number) systemEventData.plugged.value).intValue()));
                return Unit.INSTANCE;
            }
        };
        SystemEventDataSource systemEventDataSource2 = (SystemEventDataSource) eventSourceMonitor2;
        systemEventDataSource2.subscribers = arrayList;
        systemEventDataSource2.onEventSourceUpdate = r0;
        IntentFilter intentFilter = new IntentFilter();
        for (BatteryEventSubscriber batteryEventSubscriber : arrayList) {
            for (String addAction : batteryEventSubscriber.actions) {
                intentFilter.addAction(addAction);
            }
        }
        BroadcastDispatcher.registerReceiver$default(systemEventDataSource2.broadcastDispatcher, systemEventDataSource2, intentFilter, (Executor) null, (UserHandle) null, 0, (String) null, 60);
    }
}
