package com.google.android.systemui.power;

import android.app.NotificationManager;
import android.content.Context;
import android.os.UserHandle;
import android.provider.Settings;
import android.util.Log;
import androidx.core.app.NotificationCompat$Builder;
import com.android.settingslib.Utils$$ExternalSyntheticOutline0;
import com.android.settingslib.fuelgauge.BatteryStatus;
import com.google.android.systemui.power.DwellTempDefenderNotification;
import com.google.android.systemui.power.batteryevent.aidl.BatteryEventType;
import java.util.List;
import kotlin.Lazy;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class PowerNotificationWarningsGoogleImpl$$ExternalSyntheticLambda2 implements Function3 {
    public final /* synthetic */ PowerNotificationWarningsGoogleImpl f$0;

    public /* synthetic */ PowerNotificationWarningsGoogleImpl$$ExternalSyntheticLambda2(PowerNotificationWarningsGoogleImpl powerNotificationWarningsGoogleImpl) {
        this.f$0 = powerNotificationWarningsGoogleImpl;
    }

    /* JADX WARNING: type inference failed for: r1v9, types: [java.lang.Object, androidx.core.app.NotificationCompat$BigTextStyle] */
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        boolean contains;
        String str;
        List list = (List) obj;
        int intValue = ((Integer) obj2).intValue();
        int intValue2 = ((Integer) obj3).intValue();
        PowerNotificationWarningsGoogleImpl powerNotificationWarningsGoogleImpl = this.f$0;
        powerNotificationWarningsGoogleImpl.getClass();
        Log.d("PowerNotificationWarningsGoogleImpl", "[onBatteryEventUpdate] " + list);
        IncompatibleChargerNotification incompatibleChargerNotification = powerNotificationWarningsGoogleImpl.mIncompatibleChargerNotification;
        if (!(incompatibleChargerNotification == null || (contains = list.contains(BatteryEventType.WIRED_INCOMPATIBLE_CHARGING)) == incompatibleChargerNotification.mContainsIncompatibleChargers)) {
            Utils$$ExternalSyntheticOutline0.m("[refreshUsbState] isIncompatibleCharger: ", "IncompatibleChargerNotification", contains);
            incompatibleChargerNotification.mMainHandler.post(new IncompatibleChargerNotification$$ExternalSyntheticLambda0(incompatibleChargerNotification, contains));
            if (contains) {
                str = IncompatibleChargerNotification.KEY_INCOMPATIBLE_CHARGER_TIME;
            } else {
                str = IncompatibleChargerNotification.KEY_COMPATIBLE_CHARGER_TIME;
            }
            IncompatibleChargerNotification.getSharedPreferences(incompatibleChargerNotification.mContext).edit().putLong(str, System.currentTimeMillis()).apply();
            incompatibleChargerNotification.mContainsIncompatibleChargers = contains;
        }
        LowPowerWarningsController lowPowerWarningsController = powerNotificationWarningsGoogleImpl.mLowPowerWarningsController;
        if (lowPowerWarningsController != null) {
            lowPowerWarningsController.onBatteryEventUpdate(intValue, list);
        }
        BatteryDefenderNotificationHandler batteryDefenderNotificationHandler = powerNotificationWarningsGoogleImpl.mBatteryDefenderNotificationHandler;
        if (batteryDefenderNotificationHandler != null) {
            batteryDefenderNotificationHandler.batteryLevel = intValue;
            boolean contains2 = list.contains(BatteryEventType.DOCK_DEFEND_BATTERY);
            boolean z = false;
            Lazy lazy = batteryDefenderNotificationHandler.dockDefenderNotification$delegate;
            if (contains2) {
                Context context = batteryDefenderNotificationHandler.context;
                if (Settings.Global.getInt(context.getContentResolver(), "dock_defender_bypass", 0) != 1 && Settings.Global.getInt(context.getContentResolver(), "dock_defender_first_run", -1) == -1) {
                    z = true;
                }
                Utils$$ExternalSyntheticOutline0.m("onDockDefenderEvent: dockDefend1RunStatus: ", "BatteryDefenderNotification", z);
                if (z) {
                    Settings.Global.putInt(context.getContentResolver(), "dock_defender_first_run", 1);
                    DockDefenderNotification dockDefenderNotification = (DockDefenderNotification) lazy.getValue();
                    dockDefenderNotification.getClass();
                    Log.d("DockDefenderNotification", "showNotification");
                    Context context2 = dockDefenderNotification.context;
                    String string = context2.getString(2131952466);
                    NotificationCompat$Builder notificationCompat$Builder = new NotificationCompat$Builder(context2);
                    notificationCompat$Builder.mNotification.icon = 2131232477;
                    notificationCompat$Builder.mContentTitle = NotificationCompat$Builder.limitCharSequenceLength(context2.getString(2131952468));
                    notificationCompat$Builder.mContentText = NotificationCompat$Builder.limitCharSequenceLength(string);
                    notificationCompat$Builder.mContentIntent = PowerUtils.createBatterySettingsPendingIntent(context2);
                    ? obj4 = new Object();
                    obj4.mBigText = NotificationCompat$Builder.limitCharSequenceLength(string);
                    notificationCompat$Builder.setStyle(obj4);
                    notificationCompat$Builder.mSilent = true;
                    notificationCompat$Builder.setFlag(16, true);
                    notificationCompat$Builder.addAction(context2.getString(2131951960), PowerUtils.createHelpArticlePendingIntent(2131952469, context2));
                    notificationCompat$Builder.addAction(context2.getString(2131952467), PowerUtils.createBatterySettingsPendingIntent(context2));
                    notificationCompat$Builder.mLocalOnly = true;
                    PowerUtils.overrideNotificationAppName(context2, notificationCompat$Builder);
                    NotificationManager notificationManager = dockDefenderNotification.notificationManager;
                    if (notificationManager != null) {
                        notificationManager.notifyAsUser("battery_defender", 2131952468, notificationCompat$Builder.build(), UserHandle.ALL);
                    }
                }
            } else if (list.contains(BatteryEventType.TEMP_DEFEND_BATTERY)) {
                batteryDefenderNotificationHandler.onBatteryDefenderEvent(intValue2, DwellTempDefenderNotification.BatteryDefendType.TEMP_DEFEND);
            } else if (list.contains(BatteryEventType.DWELL_DEFEND_BATTERY)) {
                batteryDefenderNotificationHandler.onBatteryDefenderEvent(intValue2, DwellTempDefenderNotification.BatteryDefendType.DWELL_DEFEND);
            } else {
                boolean isCharged = BatteryStatus.isCharged(1, batteryDefenderNotificationHandler.batteryLevel);
                Utils$$ExternalSyntheticOutline0.m("onNonDefenderEvent: charged: ", "BatteryDefenderNotification", isCharged);
                boolean z2 = batteryDefenderNotificationHandler.dockDefendEnabled;
                ContextScope contextScope = batteryDefenderNotificationHandler.backgroundCoroutineScope;
                if (z2 && batteryDefenderNotificationHandler.inDefenderSection) {
                    Log.d("BatteryDefenderNotification", "dockDefendEnabled:true, no post notification");
                    batteryDefenderNotificationHandler.inDefenderSection = false;
                    batteryDefenderNotificationHandler.getDwellTempDefenderNotification().cancelNotification();
                    BuildersKt.launch$default(contextScope, (CoroutineContext) null, (CoroutineStart) null, new BatteryDefenderNotificationHandler$exitTempOrDwellDefendOnTablet$1(batteryDefenderNotificationHandler, (Continuation) null), 3);
                } else if (!z2) {
                    if (isCharged) {
                        batteryDefenderNotificationHandler.getDwellTempDefenderNotification().cancelPostNotification();
                    }
                    if (batteryDefenderNotificationHandler.inDefenderSection) {
                        batteryDefenderNotificationHandler.inDefenderSection = false;
                        batteryDefenderNotificationHandler.getDwellTempDefenderNotification().cancelNotification();
                        BuildersKt.launch$default(contextScope, (CoroutineContext) null, (CoroutineStart) null, new BatteryDefenderNotificationHandler$exitTempOrDwellDefenderIfNeeded$1(batteryDefenderNotificationHandler, (Continuation) null), 3);
                    }
                } else if (intValue2 != 8) {
                    DockDefenderNotification dockDefenderNotification2 = (DockDefenderNotification) lazy.getValue();
                    dockDefenderNotification2.getClass();
                    Log.d("DockDefenderNotification", "cancelNotification");
                    NotificationManager notificationManager2 = dockDefenderNotification2.notificationManager;
                    if (notificationManager2 != null) {
                        notificationManager2.cancelAsUser("battery_defender", 2131952468, UserHandle.ALL);
                    }
                    BuildersKt.launch$default(contextScope, (CoroutineContext) null, (CoroutineStart) null, new BatteryDefenderNotificationHandler$exitDockDefenderIfNeeded$1(batteryDefenderNotificationHandler, (Continuation) null), 3);
                }
            }
        }
        return null;
    }
}
