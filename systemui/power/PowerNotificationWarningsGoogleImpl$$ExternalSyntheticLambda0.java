package com.google.android.systemui.power;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Handler;
import android.os.UserHandle;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.google.android.systemui.googlebattery.AdaptiveChargingManager;
import com.google.android.systemui.power.batteryevent.aidl.BatteryEventType;
import com.google.android.systemui.power.batteryevent.aidl.SurfaceType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Executor;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class PowerNotificationWarningsGoogleImpl$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ PowerNotificationWarningsGoogleImpl f$0;
    public final /* synthetic */ Context f$1;
    public final /* synthetic */ UiEventLogger f$2;

    public /* synthetic */ PowerNotificationWarningsGoogleImpl$$ExternalSyntheticLambda0(PowerNotificationWarningsGoogleImpl powerNotificationWarningsGoogleImpl, Context context, UiEventLogger uiEventLogger) {
        this.f$0 = powerNotificationWarningsGoogleImpl;
        this.f$1 = context;
        this.f$2 = uiEventLogger;
    }

    public final void run() {
        PowerNotificationWarningsGoogleImpl powerNotificationWarningsGoogleImpl = this.f$0;
        Context context = this.f$1;
        UiEventLogger uiEventLogger = this.f$2;
        Executor executor = powerNotificationWarningsGoogleImpl.mExecutor;
        Context context2 = context;
        powerNotificationWarningsGoogleImpl.mBatteryInfoBroadcast = new BatteryInfoBroadcast(context2, powerNotificationWarningsGoogleImpl.mBroadcastSender, powerNotificationWarningsGoogleImpl.mEnhancedEstimates, executor, powerNotificationWarningsGoogleImpl.mUserTracker);
        powerNotificationWarningsGoogleImpl.mLowPowerWarningsController = new LowPowerWarningsController(context2, (SevereLowBatteryNotification) powerNotificationWarningsGoogleImpl.mSevereLowBatteryNotificationProvider.get(), powerNotificationWarningsGoogleImpl.mGlobalSettings, uiEventLogger, executor);
        SurfaceType surfaceType = SurfaceType.NOTIFICATION;
        ArrayList arrayList = new ArrayList(Arrays.asList(new BatteryEventType[]{BatteryEventType.LOW_BATTERY, BatteryEventType.SEVERE_LOW_BATTERY, BatteryEventType.EXTREME_LOW_BATTERY, BatteryEventType.WIRED_INCOMPATIBLE_CHARGING, BatteryEventType.TEMP_DEFEND_BATTERY, BatteryEventType.DWELL_DEFEND_BATTERY, BatteryEventType.DOCK_DEFEND_BATTERY}));
        PowerNotificationWarningsGoogleImpl$$ExternalSyntheticLambda2 powerNotificationWarningsGoogleImpl$$ExternalSyntheticLambda2 = new PowerNotificationWarningsGoogleImpl$$ExternalSyntheticLambda2(powerNotificationWarningsGoogleImpl);
        BatteryEventClient batteryEventClient = powerNotificationWarningsGoogleImpl.mBatteryEventClient;
        if (batteryEventClient.service == null) {
            batteryEventClient.surfaceType = surfaceType;
            batteryEventClient.callerTag = "PowerNotificationWarningsGoogleImpl";
            batteryEventClient.subscribedBatteryEvents.addAll(arrayList);
            batteryEventClient.onBatteryEventUpdate = powerNotificationWarningsGoogleImpl$$ExternalSyntheticLambda2;
            BuildersKt.launch$default(batteryEventClient.coroutineScope, batteryEventClient.backgroundDispatcher, (CoroutineStart) null, new BatteryEventClient$registerBatteryEventCallback$1(batteryEventClient, (Continuation) null), 2);
        } else {
            batteryEventClient.logWithCaller.w("already registered for NOTIFICATION, need to unregister before register again");
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.BATTERY_CHANGED");
        intentFilter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        intentFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        intentFilter.addAction("android.intent.action.BOOT_COMPLETED");
        intentFilter.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
        intentFilter.addAction("PNW.startSaverConfirmation");
        intentFilter.addAction("com.android.settingslib.fuelgauge.ACTION_SAVER_STATE_MANUAL_UPDATE");
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.device.action.BATTERY_LEVEL_CHANGED");
        intentFilter.addAction("android.bluetooth.device.action.ALIAS_CHANGED");
        intentFilter.addAction("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.hearingaid.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("systemui.power.action.START_FLIPENDO");
        Resources resources = context.getResources();
        if (resources.getBoolean(2131034119)) {
            powerNotificationWarningsGoogleImpl.mBatteryDefenderNotificationHandler = new BatteryDefenderNotificationHandler(context, uiEventLogger, executor);
            intentFilter.addAction("PNW.defenderResumeCharging");
            intentFilter.addAction("PNW.defenderResumeCharging.settings");
            intentFilter.addAction("systemui.power.action.dismissBatteryDefenderWarning");
        }
        if (resources.getBoolean(2131034116)) {
            powerNotificationWarningsGoogleImpl.mAdaptiveChargingNotification = new AdaptiveChargingNotification(context, new AdaptiveChargingManager(context), uiEventLogger);
            intentFilter.addAction("com.google.android.systemui.adaptivecharging.ADAPTIVE_CHARGING_DEADLINE_SET");
            intentFilter.addAction("PNW.acChargeNormally");
            intentFilter.addAction("systemui.power.action.dismissAdaptiveChargingWarning");
        }
        if (resources.getBoolean(2131034150)) {
            powerNotificationWarningsGoogleImpl.mIncompatibleChargerNotification = new IncompatibleChargerNotification(context, uiEventLogger);
            intentFilter.addAction("systemui.power.action.dismissIncompatibleChargerWarning");
        }
        if (resources.getBoolean(2131034202)) {
            powerNotificationWarningsGoogleImpl.mWirelessChargingNotification = new WirelessChargingNotification(context, uiEventLogger);
            intentFilter.addAction("systemui.power.action.dismissWirelessChargingNotification");
            intentFilter.addAction("systemui.power.action.disableWirelessChargingNotification");
        }
        BroadcastReceiver broadcastReceiver = powerNotificationWarningsGoogleImpl.mBroadcastReceiver;
        Handler handler = powerNotificationWarningsGoogleImpl.mHandler;
        UserHandle userHandle = UserHandle.ALL;
        BroadcastDispatcher broadcastDispatcher = powerNotificationWarningsGoogleImpl.mBroadcastDispatcher;
        broadcastDispatcher.getClass();
        BroadcastDispatcher.registerReceiverWithHandler$default(broadcastDispatcher, broadcastReceiver, intentFilter, handler, userHandle, 2, 32);
        Intent registerReceiver = context.registerReceiver((BroadcastReceiver) null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (registerReceiver != null) {
            powerNotificationWarningsGoogleImpl.mBroadcastReceiver.onReceive(context, registerReceiver);
        }
    }
}
