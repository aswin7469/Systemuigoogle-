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
import com.google.android.systemui.power.batteryevent.repository.SettingsDataSource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import vendor.google.google_battery.IGoogleBattery;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
        List list;
        Integer num;
        Iterator it;
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
            HalDataSource$deathRecipient$1 halDataSource$deathRecipient$1 = HalDataSource$deathRecipient$1.INSTANCE;
            halDataSource.googleBatteryManager.getClass();
            IGoogleBattery initHalInterface = GoogleBatteryManager.initHalInterface(halDataSource$deathRecipient$1);
            for (HalDataType ordinal : arrayList) {
                int ordinal2 = ordinal.ordinal();
                if (ordinal2 == 0) {
                    if (intExtra == 8) {
                        if (initHalInterface == null) {
                            Log.w("GoogleBatteryDataSource", "getDockDefendStatus failed. googleBattery is null");
                        } else {
                            try {
                                i = ((IGoogleBattery.Stub.Proxy) initHalInterface).getDockDefendStatus();
                                Log.i("GoogleBatteryDataSource", "fetchDockDefendStatus: dockDefendStatus:" + i);
                            } catch (Exception e) {
                                Log.w("GoogleBatteryDataSource", "fetchDockDefendStatus failed.", e);
                            }
                            halDataSource.lastGoogleBatteryDockDefendStatus = i;
                        }
                    }
                    i = -3;
                    halDataSource.lastGoogleBatteryDockDefendStatus = i;
                } else if (ordinal2 == 1) {
                    String fetchFeatureStatus = halDataSource.fetchFeatureStatus(initHalInterface, 1, true);
                    Log.d("GoogleBatteryDataSource", "fetchTempDefendStatus: ".concat(fetchFeatureStatus));
                    halDataSource.lastTempDefendStatus = StringsKt.contains$default(fetchFeatureStatus, " t=1");
                } else if (ordinal2 == 2) {
                    String fetchFeatureStatus2 = halDataSource.fetchFeatureStatus(initHalInterface, 3, true);
                    Log.d("GoogleBatteryDataSource", "fetchDwellDefendStatus: ".concat(fetchFeatureStatus2));
                    halDataSource.lastDwellDefendStatus = Intrinsics.areEqual(fetchFeatureStatus2, "ACTIVE");
                }
            }
            halDataSource.destroyGoogleBattery(initHalInterface);
            int i2 = halDataSource.lastGoogleBatteryDockDefendStatus;
            boolean z2 = halDataSource.lastTempDefendStatus;
            boolean z3 = halDataSource.lastDwellDefendStatus;
            Integer valueOf = Integer.valueOf(i2);
            Boolean valueOf2 = Boolean.valueOf(z2);
            Boolean valueOf3 = Boolean.valueOf(z3);
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
            for (SettingsDataType ordinal3 : arrayList2) {
                if (SettingsDataSource.WhenMappings.$EnumSwitchMapping$0[ordinal3.ordinal()] == 1) {
                    settingsDataSource.lastDockDefenderByPass = Settings.Global.getInt(settingsDataSource.context.getContentResolver(), "dock_defender_bypass", 0);
                }
            }
            Integer valueOf4 = Integer.valueOf(settingsDataSource.lastDockDefenderByPass);
            SystemEventDataSource systemEventDataSource3 = this.this$0;
            FrameworkDataSource frameworkDataSource = systemEventDataSource3.frameworkDataSource;
            List access$getAllEventDataType3 = SystemEventDataSource.access$getAllEventDataType(systemEventDataSource3, this.$intentAction);
            ArrayList arrayList3 = new ArrayList();
            Iterator it2 = access$getAllEventDataType3.iterator();
            while (it2.hasNext()) {
                Object next3 = it2.next();
                Iterator it3 = it2;
                if (next3 instanceof FrameworkApiDataType) {
                    arrayList3.add(next3);
                }
                it2 = it3;
            }
            frameworkDataSource.getClass();
            Iterator it4 = arrayList3.iterator();
            while (it4.hasNext()) {
                int ordinal4 = ((FrameworkApiDataType) it4.next()).ordinal();
                PowerManager powerManager = frameworkDataSource.powerManager;
                if (ordinal4 != 0) {
                    it = it4;
                    if (ordinal4 != 1) {
                        num = valueOf4;
                    } else {
                        if (powerManager.isPowerSaveMode()) {
                            num = valueOf4;
                            bundle = frameworkDataSource.context.getContentResolver().call("com.google.android.flipendo.api", "get_flipendo_state", (String) null, (Bundle) null);
                        } else {
                            num = valueOf4;
                            bundle = null;
                        }
                        if (bundle == null || !bundle.getBoolean("flipendo_state")) {
                            z = false;
                        } else {
                            z = true;
                        }
                        frameworkDataSource.lastExtremeBatterySaverState = z;
                    }
                } else {
                    num = valueOf4;
                    it = it4;
                    frameworkDataSource.lastBatterySaverState = powerManager.isPowerSaveMode();
                }
                it4 = it;
                valueOf4 = num;
            }
            Integer num2 = valueOf4;
            boolean z4 = frameworkDataSource.lastBatterySaverState;
            boolean z5 = frameworkDataSource.lastExtremeBatterySaverState;
            Boolean valueOf5 = Boolean.valueOf(z4);
            Boolean valueOf6 = Boolean.valueOf(z5);
            String str = this.$intentAction;
            Integer valueOf7 = Integer.valueOf(intExtra);
            Integer valueOf8 = Integer.valueOf(intExtra2);
            Integer valueOf9 = Integer.valueOf(intExtra3);
            Integer valueOf10 = Integer.valueOf(intExtra4);
            Integer valueOf11 = Integer.valueOf(intExtra5);
            Integer valueOf12 = Integer.valueOf(intExtra6);
            Integer valueOf13 = Integer.valueOf(intExtra7);
            SystemEventData systemEventData = this.this$0.lastSystemEventData;
            EventData eventData = systemEventData.plugged;
            String str2 = str;
            if (Intrinsics.areEqual(eventData.value, valueOf7)) {
                eventData.isChanged = false;
            } else {
                eventData = new EventData(valueOf7);
                eventData.isChanged = true;
            }
            EventData eventData2 = eventData;
            EventData eventData3 = systemEventData.batteryScale;
            if (Intrinsics.areEqual(eventData3.value, valueOf8)) {
                eventData3.isChanged = false;
            } else {
                eventData3 = new EventData(valueOf8);
                eventData3.isChanged = true;
            }
            EventData eventData4 = eventData3;
            EventData eventData5 = systemEventData.batteryLevel;
            if (Intrinsics.areEqual(eventData5.value, valueOf9)) {
                eventData5.isChanged = false;
            } else {
                eventData5 = new EventData(valueOf9);
                eventData5.isChanged = true;
            }
            EventData eventData6 = eventData5;
            EventData eventData7 = systemEventData.chargingStatus;
            if (Intrinsics.areEqual(eventData7.value, valueOf10)) {
                eventData7.isChanged = false;
            } else {
                eventData7 = new EventData(valueOf10);
                eventData7.isChanged = true;
            }
            EventData eventData8 = eventData7;
            EventData eventData9 = systemEventData.maxChargingCurrent;
            if (Intrinsics.areEqual(eventData9.value, valueOf11)) {
                eventData9.isChanged = false;
            } else {
                eventData9 = new EventData(valueOf11);
                eventData9.isChanged = true;
            }
            EventData eventData10 = eventData9;
            EventData eventData11 = systemEventData.maxChargingVoltage;
            if (Intrinsics.areEqual(eventData11.value, valueOf12)) {
                eventData11.isChanged = false;
            } else {
                eventData11 = new EventData(valueOf12);
                eventData11.isChanged = true;
            }
            EventData eventData12 = eventData11;
            EventData eventData13 = systemEventData.batteryStatus;
            if (Intrinsics.areEqual(eventData13.value, valueOf13)) {
                eventData13.isChanged = false;
            } else {
                eventData13 = new EventData(valueOf13);
                eventData13.isChanged = true;
            }
            EventData eventData14 = eventData13;
            HalEventData halEventData = systemEventData.halEventData;
            EventData eventData15 = halEventData.dockDefendStatus;
            if (Intrinsics.areEqual(eventData15.value, valueOf)) {
                eventData15.isChanged = false;
            } else {
                eventData15 = new EventData(valueOf);
                eventData15.isChanged = true;
            }
            EventData eventData16 = halEventData.tempDefendEventData;
            if (Intrinsics.areEqual(eventData16.value, valueOf2)) {
                eventData16.isChanged = false;
            } else {
                eventData16 = new EventData(valueOf2);
                eventData16.isChanged = true;
            }
            EventData eventData17 = halEventData.dwellDefendEventData;
            if (Intrinsics.areEqual(eventData17.value, valueOf3)) {
                eventData17.isChanged = false;
            } else {
                eventData17 = new EventData(valueOf3);
                eventData17.isChanged = true;
            }
            HalEventData halEventData2 = new HalEventData(eventData15, eventData16, eventData17);
            EventData eventData18 = systemEventData.settingsEventData.dockDefenderBypass;
            Integer num3 = num2;
            if (Intrinsics.areEqual(eventData18.value, num3)) {
                eventData18.isChanged = false;
            } else {
                eventData18 = new EventData(num3);
                eventData18.isChanged = true;
            }
            SettingsEventData settingsEventData = new SettingsEventData(eventData18);
            FrameworkApiEventData frameworkApiEventData = systemEventData.frameworkApiEventData;
            EventData eventData19 = frameworkApiEventData.batterySaverState;
            if (Intrinsics.areEqual(eventData19.value, valueOf5)) {
                eventData19.isChanged = false;
            } else {
                eventData19 = new EventData(valueOf5);
                eventData19.isChanged = true;
            }
            EventData eventData20 = frameworkApiEventData.extremeBatterySaverState;
            if (Intrinsics.areEqual(eventData20.value, valueOf6)) {
                eventData20.isChanged = false;
            } else {
                eventData20 = new EventData(valueOf6);
                eventData20.isChanged = true;
            }
            SystemEventData systemEventData2 = new SystemEventData(str2, eventData2, eventData4, eventData6, eventData8, eventData10, eventData12, eventData14, halEventData2, settingsEventData, new FrameworkApiEventData(eventData19, eventData20));
            Log.d("SystemEventDataSource", "updatedEventData: " + systemEventData2);
            SystemEventDataSource systemEventDataSource4 = this.this$0;
            String str3 = this.$intentAction;
            systemEventDataSource4.getClass();
            boolean z6 = !Intrinsics.areEqual(str3, "android.hardware.usb.action.USB_PORT_COMPLIANCE_CHANGED");
            Unit unit = Unit.INSTANCE;
            if (!z6 || !Intrinsics.areEqual(systemEventData2, this.this$0.lastSystemEventData)) {
                SystemEventDataSource systemEventDataSource5 = this.this$0;
                String str4 = this.$intentAction;
                if (!(!systemEventDataSource5.actionToBatteryEventTypeCache.isEmpty()) || !systemEventDataSource5.actionToBatteryEventTypeCache.keySet().contains(str4)) {
                    ArrayList arrayList4 = new ArrayList();
                    for (BatteryEventSubscriber batteryEventSubscriber : systemEventDataSource5.subscribers) {
                        if (batteryEventSubscriber.actions.contains(str4)) {
                            arrayList4.add(batteryEventSubscriber.batteryEventType);
                        }
                    }
                    List distinct = CollectionsKt.distinct(arrayList4);
                    systemEventDataSource5.actionToBatteryEventTypeCache.put(str4, distinct);
                    list = distinct;
                } else {
                    Object obj2 = systemEventDataSource5.actionToBatteryEventTypeCache.get(str4);
                    Intrinsics.checkNotNull(obj2);
                    list = (List) obj2;
                }
                ExifInterface$$ExternalSyntheticOutline0.m("notifyModules count: ", "SystemEventDataSource", list.size());
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
