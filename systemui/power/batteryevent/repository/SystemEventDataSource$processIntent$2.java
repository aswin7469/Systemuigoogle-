package com.google.android.systemui.power.batteryevent.repository;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0;
import com.google.android.systemui.googlebattery.GoogleBatteryManager;
import com.google.android.systemui.power.batteryevent.common.BatteryEventSubscriber;
import com.google.android.systemui.power.batteryevent.common.FrameworkApiDataType;
import com.google.android.systemui.power.batteryevent.common.HalDataType;
import com.google.android.systemui.power.batteryevent.common.SettingsDataType;
import com.google.android.systemui.power.batteryevent.common.data.EventData;
import com.google.android.systemui.power.batteryevent.common.data.FrameworkApiEventData;
import com.google.android.systemui.power.batteryevent.common.data.HalEventData;
import com.google.android.systemui.power.batteryevent.common.data.SettingsEventData;
import com.google.android.systemui.power.batteryevent.common.data.SystemEventData;
import com.google.android.systemui.power.batteryevent.repository.HalDataSource;
import com.google.android.systemui.power.batteryevent.repository.SettingsDataSource;
import java.util.ArrayList;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import vendor.google.google_battery.IGoogleBattery;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class SystemEventDataSource$processIntent$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Context $context;
    final /* synthetic */ String $intentAction;
    final /* synthetic */ Intent $receivedIntent;
    int label;
    final /* synthetic */ SystemEventDataSource this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SystemEventDataSource$processIntent$2(Context context, Intent intent, SystemEventDataSource systemEventDataSource, String str, Continuation continuation) {
        super(2, continuation);
        this.$intentAction = str;
        this.$receivedIntent = intent;
        this.$context = context;
        this.this$0 = systemEventDataSource;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        String str = this.$intentAction;
        return new SystemEventDataSource$processIntent$2(this.$context, this.$receivedIntent, this.this$0, str, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((SystemEventDataSource$processIntent$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Intent intent;
        EventData eventData;
        List list;
        Bundle bundle;
        boolean z;
        int i;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (Intrinsics.areEqual(this.$intentAction, "android.intent.action.BATTERY_CHANGED")) {
                intent = this.$receivedIntent;
            } else {
                intent = this.$context.registerReceiver((BroadcastReceiver) null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            }
            int intExtra = intent.getIntExtra("plugged", 0);
            int intExtra2 = intent.getIntExtra("scale", 100);
            int intExtra3 = intent.getIntExtra("level", 0);
            int i2 = 1;
            int intExtra4 = intent.getIntExtra("android.os.extra.CHARGING_STATUS", 1);
            int intExtra5 = intent.getIntExtra("max_charging_current", 0);
            int intExtra6 = intent.getIntExtra("max_charging_voltage", 0);
            int intExtra7 = intent.getIntExtra("status", 1);
            SystemEventDataSource systemEventDataSource = this.this$0;
            HalDataSource halDataSource = systemEventDataSource.halDataSource;
            List access$getAllEventDataType = SystemEventDataSource.access$getAllEventDataType(systemEventDataSource, this.$intentAction);
            ArrayList<HalDataType> arrayList = new ArrayList<>();
            for (Object next : access$getAllEventDataType) {
                if (next instanceof HalDataType) {
                    arrayList.add(next);
                }
            }
            halDataSource.getClass();
            for (HalDataType ordinal : arrayList) {
                if (HalDataSource.WhenMappings.$EnumSwitchMapping$0[ordinal.ordinal()] == i2) {
                    if (intExtra == 8) {
                        HalDataSource$fetchDockDefendStatus$deathRecipient$1 halDataSource$fetchDockDefendStatus$deathRecipient$1 = HalDataSource$fetchDockDefendStatus$deathRecipient$1.INSTANCE;
                        halDataSource.googleBatteryManager.getClass();
                        IGoogleBattery initHalInterface = GoogleBatteryManager.initHalInterface(halDataSource$fetchDockDefendStatus$deathRecipient$1);
                        if (initHalInterface == null) {
                            Log.w("GoogleBatteryDataSource", "getDockDefendStatus failed. googleBattery is null");
                        } else {
                            try {
                                int dockDefendStatus = ((IGoogleBattery.Stub.Proxy) initHalInterface).getDockDefendStatus();
                                try {
                                    GoogleBatteryManager.destroyHalInterface(initHalInterface, halDataSource$fetchDockDefendStatus$deathRecipient$1);
                                } catch (Exception e) {
                                    Log.w("GoogleBatteryDataSource", "destroyHalInterface failed: ", e);
                                }
                                i = dockDefendStatus;
                            } catch (Exception e2) {
                                Log.w("GoogleBatteryDataSource", "getDockDefendStatus: ", e2);
                                try {
                                    GoogleBatteryManager.destroyHalInterface(initHalInterface, halDataSource$fetchDockDefendStatus$deathRecipient$1);
                                } catch (Exception e3) {
                                    Log.w("GoogleBatteryDataSource", "destroyHalInterface failed: ", e3);
                                }
                            } catch (Throwable th) {
                                Throwable th2 = th;
                                try {
                                    GoogleBatteryManager.destroyHalInterface(initHalInterface, halDataSource$fetchDockDefendStatus$deathRecipient$1);
                                } catch (Exception e4) {
                                    Log.w("GoogleBatteryDataSource", "destroyHalInterface failed: ", e4);
                                }
                                throw th2;
                            }
                            halDataSource.lastGoogleBatteryDockDefendStatus = i;
                            i2 = 1;
                        }
                    }
                    i = -3;
                    halDataSource.lastGoogleBatteryDockDefendStatus = i;
                    i2 = 1;
                }
            }
            Integer valueOf = Integer.valueOf(halDataSource.lastGoogleBatteryDockDefendStatus);
            SystemEventDataSource systemEventDataSource2 = this.this$0;
            SettingsDataSource settingsDataSource = systemEventDataSource2.settingsDataSource;
            List access$getAllEventDataType2 = SystemEventDataSource.access$getAllEventDataType(systemEventDataSource2, this.$intentAction);
            ArrayList<SettingsDataType> arrayList2 = new ArrayList<>();
            for (Object next2 : access$getAllEventDataType2) {
                if (next2 instanceof SettingsDataType) {
                    arrayList2.add(next2);
                }
            }
            settingsDataSource.getClass();
            for (SettingsDataType ordinal2 : arrayList2) {
                if (SettingsDataSource.WhenMappings.$EnumSwitchMapping$0[ordinal2.ordinal()] == 1) {
                    settingsDataSource.lastDockDefenderByPass = Settings.Global.getInt(settingsDataSource.context.getContentResolver(), "dock_defender_bypass", 0);
                }
            }
            Integer valueOf2 = Integer.valueOf(settingsDataSource.lastDockDefenderByPass);
            SystemEventDataSource systemEventDataSource3 = this.this$0;
            FrameworkDataSource frameworkDataSource = systemEventDataSource3.frameworkDataSource;
            List access$getAllEventDataType3 = SystemEventDataSource.access$getAllEventDataType(systemEventDataSource3, this.$intentAction);
            ArrayList<FrameworkApiDataType> arrayList3 = new ArrayList<>();
            for (Object next3 : access$getAllEventDataType3) {
                if (next3 instanceof FrameworkApiDataType) {
                    arrayList3.add(next3);
                }
            }
            frameworkDataSource.getClass();
            for (FrameworkApiDataType ordinal3 : arrayList3) {
                int ordinal4 = ordinal3.ordinal();
                PowerManager powerManager = frameworkDataSource.powerManager;
                if (ordinal4 == 0) {
                    frameworkDataSource.lastBatterySaverState = powerManager.isPowerSaveMode();
                } else if (ordinal4 == 1) {
                    if (powerManager.isPowerSaveMode()) {
                        bundle = frameworkDataSource.context.getContentResolver().call("com.google.android.flipendo.api", "get_flipendo_state", (String) null, (Bundle) null);
                    } else {
                        bundle = null;
                    }
                    if (bundle == null || !bundle.getBoolean("flipendo_state")) {
                        z = false;
                    } else {
                        z = true;
                    }
                    frameworkDataSource.lastExtremeBatterySaverState = z;
                }
            }
            boolean z2 = frameworkDataSource.lastBatterySaverState;
            boolean z3 = frameworkDataSource.lastExtremeBatterySaverState;
            Boolean valueOf3 = Boolean.valueOf(z2);
            Boolean valueOf4 = Boolean.valueOf(z3);
            String str = this.$intentAction;
            Integer valueOf5 = Integer.valueOf(intExtra);
            Integer valueOf6 = Integer.valueOf(intExtra2);
            Integer valueOf7 = Integer.valueOf(intExtra3);
            Integer valueOf8 = Integer.valueOf(intExtra4);
            Integer valueOf9 = Integer.valueOf(intExtra5);
            Integer valueOf10 = Integer.valueOf(intExtra6);
            Integer valueOf11 = Integer.valueOf(intExtra7);
            SystemEventData systemEventData = this.this$0.lastSystemEventData;
            EventData eventData2 = systemEventData.plugged;
            if (Intrinsics.areEqual(eventData2.value, valueOf5)) {
                eventData2.isChanged = false;
                eventData = eventData2;
            } else {
                EventData eventData3 = new EventData(valueOf5);
                eventData3.isChanged = true;
                eventData = eventData3;
            }
            EventData eventData4 = systemEventData.batteryScale;
            if (Intrinsics.areEqual(eventData4.value, valueOf6)) {
                eventData4.isChanged = false;
            } else {
                eventData4 = new EventData(valueOf6);
                eventData4.isChanged = true;
            }
            EventData eventData5 = eventData4;
            EventData eventData6 = systemEventData.batteryLevel;
            if (Intrinsics.areEqual(eventData6.value, valueOf7)) {
                eventData6.isChanged = false;
            } else {
                eventData6 = new EventData(valueOf7);
                eventData6.isChanged = true;
            }
            EventData eventData7 = eventData6;
            EventData eventData8 = systemEventData.chargingStatus;
            if (Intrinsics.areEqual(eventData8.value, valueOf8)) {
                eventData8.isChanged = false;
            } else {
                eventData8 = new EventData(valueOf8);
                eventData8.isChanged = true;
            }
            EventData eventData9 = eventData8;
            EventData eventData10 = systemEventData.maxChargingCurrent;
            if (Intrinsics.areEqual(eventData10.value, valueOf9)) {
                eventData10.isChanged = false;
            } else {
                eventData10 = new EventData(valueOf9);
                eventData10.isChanged = true;
            }
            EventData eventData11 = eventData10;
            EventData eventData12 = systemEventData.maxChargingVoltage;
            if (Intrinsics.areEqual(eventData12.value, valueOf10)) {
                eventData12.isChanged = false;
            } else {
                eventData12 = new EventData(valueOf10);
                eventData12.isChanged = true;
            }
            EventData eventData13 = eventData12;
            EventData eventData14 = systemEventData.batteryStatus;
            if (Intrinsics.areEqual(eventData14.value, valueOf11)) {
                eventData14.isChanged = false;
            } else {
                eventData14 = new EventData(valueOf11);
                eventData14.isChanged = true;
            }
            EventData eventData15 = eventData14;
            EventData eventData16 = systemEventData.halEventData.dockDefendStatus;
            if (Intrinsics.areEqual(eventData16.value, valueOf)) {
                eventData16.isChanged = false;
            } else {
                eventData16 = new EventData(valueOf);
                eventData16.isChanged = true;
            }
            HalEventData halEventData = new HalEventData(eventData16);
            EventData eventData17 = systemEventData.settingsEventData.dockDefenderBypass;
            if (Intrinsics.areEqual(eventData17.value, valueOf2)) {
                eventData17.isChanged = false;
            } else {
                eventData17 = new EventData(valueOf2);
                eventData17.isChanged = true;
            }
            SettingsEventData settingsEventData = new SettingsEventData(eventData17);
            FrameworkApiEventData frameworkApiEventData = systemEventData.frameworkApiEventData;
            EventData eventData18 = frameworkApiEventData.batterySaverState;
            if (Intrinsics.areEqual(eventData18.value, valueOf3)) {
                eventData18.isChanged = false;
            } else {
                eventData18 = new EventData(valueOf3);
                eventData18.isChanged = true;
            }
            EventData eventData19 = frameworkApiEventData.extremeBatterySaverState;
            if (Intrinsics.areEqual(eventData19.value, valueOf4)) {
                eventData19.isChanged = false;
            } else {
                eventData19 = new EventData(valueOf4);
                eventData19.isChanged = true;
            }
            SystemEventData systemEventData2 = new SystemEventData(str, eventData, eventData5, eventData7, eventData9, eventData11, eventData13, eventData15, halEventData, settingsEventData, new FrameworkApiEventData(eventData18, eventData19));
            Log.d("SystemEventDataSource", "updatedEventData: " + systemEventData2);
            SystemEventDataSource systemEventDataSource4 = this.this$0;
            String str2 = this.$intentAction;
            systemEventDataSource4.getClass();
            boolean z4 = !Intrinsics.areEqual(str2, "android.hardware.usb.action.USB_PORT_COMPLIANCE_CHANGED");
            Unit unit = Unit.INSTANCE;
            if (!z4 || !Intrinsics.areEqual(systemEventData2, this.this$0.lastSystemEventData)) {
                SystemEventDataSource systemEventDataSource5 = this.this$0;
                String str3 = this.$intentAction;
                if (!(!systemEventDataSource5.actionToBatteryEventTypeCache.isEmpty()) || !systemEventDataSource5.actionToBatteryEventTypeCache.keySet().contains(str3)) {
                    ArrayList arrayList4 = new ArrayList();
                    for (BatteryEventSubscriber batteryEventSubscriber : systemEventDataSource5.subscribers) {
                        if (batteryEventSubscriber.actions.contains(str3)) {
                            arrayList4.add(batteryEventSubscriber.batteryEventType);
                        }
                    }
                    List distinct = CollectionsKt___CollectionsKt.distinct(arrayList4);
                    systemEventDataSource5.actionToBatteryEventTypeCache.put(str3, distinct);
                    list = distinct;
                } else {
                    Object obj2 = systemEventDataSource5.actionToBatteryEventTypeCache.get(str3);
                    Intrinsics.checkNotNull(obj2);
                    list = (List) obj2;
                }
                ExifInterface$$ExternalSyntheticOutline0.m("notifyModules count: ", list.size(), "SystemEventDataSource");
                SystemEventDataSource systemEventDataSource6 = this.this$0;
                systemEventDataSource6.lastSystemEventData = systemEventData2;
                systemEventDataSource6.onEventSourceUpdate.invoke(systemEventData2, list);
                return unit;
            }
            Log.d("SystemEventDataSource", "extra doesn't changed, no need to onEventSourceUpdate");
            return unit;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
