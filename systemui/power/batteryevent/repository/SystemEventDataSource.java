package com.google.android.systemui.power.batteryevent.repository;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.google.android.systemui.power.batteryevent.common.BatteryEventSubscriber;
import com.google.android.systemui.power.batteryevent.common.data.FrameworkApiEventData;
import com.google.android.systemui.power.batteryevent.common.data.HalEventData;
import com.google.android.systemui.power.batteryevent.common.data.SettingsEventData;
import com.google.android.systemui.power.batteryevent.common.data.SystemEventData;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class SystemEventDataSource extends BroadcastReceiver implements EventSourceMonitor {
    public static final Function2 CALLBACK_INITIAL_STATE = SystemEventDataSource$Companion$CALLBACK_INITIAL_STATE$1.INSTANCE;
    public final Map actionToBatteryEventTypeCache = new LinkedHashMap();
    public final Map actionToEventDataTypeCache = new LinkedHashMap();
    public final CoroutineScope applicationScope;
    public final CoroutineDispatcher backgroundDispatcher;
    public final BroadcastDispatcher broadcastDispatcher;
    public final FrameworkDataSource frameworkDataSource;
    public final HalDataSource halDataSource;
    public SystemEventData lastSystemEventData = new SystemEventData(new HalEventData(0), new SettingsEventData(0), new FrameworkApiEventData(false, false));
    public Function2 onEventSourceUpdate = CALLBACK_INITIAL_STATE;
    public final SettingsDataSource settingsDataSource;
    public List subscribers = EmptyList.INSTANCE;

    public SystemEventDataSource(HalDataSource halDataSource2, SettingsDataSource settingsDataSource2, FrameworkDataSource frameworkDataSource2, BroadcastDispatcher broadcastDispatcher2, CoroutineDispatcher coroutineDispatcher, CoroutineScope coroutineScope) {
        this.halDataSource = halDataSource2;
        this.settingsDataSource = settingsDataSource2;
        this.frameworkDataSource = frameworkDataSource2;
        this.broadcastDispatcher = broadcastDispatcher2;
        this.backgroundDispatcher = coroutineDispatcher;
        this.applicationScope = coroutineScope;
    }

    public static final List access$getAllEventDataType(SystemEventDataSource systemEventDataSource, String str) {
        if (!(!systemEventDataSource.actionToEventDataTypeCache.isEmpty()) || !systemEventDataSource.actionToEventDataTypeCache.keySet().contains(str)) {
            ArrayList arrayList = new ArrayList();
            for (BatteryEventSubscriber batteryEventSubscriber : systemEventDataSource.subscribers) {
                if (batteryEventSubscriber.actions.contains(str)) {
                    arrayList.addAll(batteryEventSubscriber.eventDataType);
                }
            }
            List distinct = CollectionsKt___CollectionsKt.distinct(arrayList);
            systemEventDataSource.actionToEventDataTypeCache.put(str, distinct);
            return distinct;
        }
        Object obj = systemEventDataSource.actionToEventDataTypeCache.get(str);
        Intrinsics.checkNotNull(obj);
        return (List) obj;
    }

    public final void onReceive(Context context, Intent intent) {
        String action;
        if (intent == null || intent.getAction() == null || ((action = intent.getAction()) != null && action.length() == 0)) {
            Log.w("SystemEventDataSource", "onReceive, unexpected intent " + intent);
            return;
        }
        String action2 = intent.getAction();
        Intrinsics.checkNotNull(action2);
        Log.d("SystemEventDataSource", "onReceive: intentAction");
        BuildersKt.launch$default(this.applicationScope, (CoroutineContext) null, (CoroutineStart) null, new SystemEventDataSource$onReceive$1(context, intent, this, action2, (Continuation) null), 3);
    }
}
