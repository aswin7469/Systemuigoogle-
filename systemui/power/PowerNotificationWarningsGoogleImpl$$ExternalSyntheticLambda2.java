package com.google.android.systemui.power;

import android.app.NotificationManager;
import android.content.Context;
import android.os.UserHandle;
import android.provider.Settings;
import android.util.Log;
import androidx.core.app.NotificationCompat$Builder;
import com.android.internal.logging.UiEventLogger;
import com.android.settingslib.fuelgauge.BatteryStatus;
import com.android.settingslib.mobile.MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0;
import com.google.android.systemui.power.batteryevent.aidl.BatteryEventType;
import java.util.List;
import kotlin.Lazy;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class PowerNotificationWarningsGoogleImpl$$ExternalSyntheticLambda2 implements Function3 {
    public final /* synthetic */ PowerNotificationWarningsGoogleImpl f$0;

    public /* synthetic */ PowerNotificationWarningsGoogleImpl$$ExternalSyntheticLambda2(PowerNotificationWarningsGoogleImpl powerNotificationWarningsGoogleImpl) {
        this.f$0 = powerNotificationWarningsGoogleImpl;
    }

    /* JADX WARNING: type inference failed for: r2v12, types: [java.lang.Object, androidx.core.app.NotificationCompat$BigTextStyle] */
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
            MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0.m("[refreshUsbState] isIncompatibleCharger: ", contains, "IncompatibleChargerNotification");
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
                MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0.m("onDockDefenderEvent: dockDefend1RunStatus: ", z, "BatteryDefenderNotification");
                if (z) {
                    Settings.Global.putInt(context.getContentResolver(), "dock_defender_first_run", 1);
                    DockDefenderNotification dockDefenderNotification = (DockDefenderNotification) lazy.getValue();
                    dockDefenderNotification.getClass();
                    Log.d("DockDefenderNotification", "showNotification");
                    Context context2 = dockDefenderNotification.context;
                    String string = context2.getString(2131952445);
                    NotificationCompat$Builder notificationCompat$Builder = new NotificationCompat$Builder(context2);
                    notificationCompat$Builder.mNotification.icon = 17303721;
                    notificationCompat$Builder.mContentTitle = NotificationCompat$Builder.limitCharSequenceLength(context2.getString(2131952447));
                    notificationCompat$Builder.mContentText = NotificationCompat$Builder.limitCharSequenceLength(string);
                    notificationCompat$Builder.mContentIntent = PowerUtils.createBatterySettingsPendingIntent(context2);
                    ? obj4 = new Object();
                    obj4.mBigText = NotificationCompat$Builder.limitCharSequenceLength(string);
                    notificationCompat$Builder.setStyle(obj4);
                    notificationCompat$Builder.mSilent = true;
                    notificationCompat$Builder.setFlag(16, true);
                    notificationCompat$Builder.addAction(context2.getString(2131952446), PowerUtils.createBatterySettingsPendingIntent(context2));
                    notificationCompat$Builder.addAction(context2.getString(2131951951), PowerUtils.createHelpArticlePendingIntent(2131952448, context2));
                    NotificationManager notificationManager = dockDefenderNotification.notificationManager;
                    if (notificationManager != null) {
                        notificationManager.notifyAsUser("battery_defender", 2131952447, notificationCompat$Builder.build(), UserHandle.ALL);
                    }
                }
            } else {
                boolean contains3 = list.contains(BatteryEventType.TEMP_DEFEND_BATTERY);
                ContextScope contextScope = batteryDefenderNotificationHandler.backgroundCoroutineScope;
                if (contains3 || list.contains(BatteryEventType.DWELL_DEFEND_BATTERY)) {
                    boolean isPluggedIn = BatteryStatus.isPluggedIn(intValue2);
                    Log.d("BatteryDefenderNotification", "onBatteryDefenderEvent, pluggedIn:" + isPluggedIn + ", inDefenderSection:" + batteryDefenderNotificationHandler.inDefenderSection);
                    if (batteryDefenderNotificationHandler.inDefenderSection) {
                        DwellTempDefenderNotification dwellTempDefenderNotification = batteryDefenderNotificationHandler.getDwellTempDefenderNotification();
                        MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0.m("updateNotificationIfNeeded, notificationVisible:", dwellTempDefenderNotification.notificationVisible, "DwellTempDefenderNotification");
                        if (dwellTempDefenderNotification.notificationVisible && isPluggedIn != dwellTempDefenderNotification.lastPlugged) {
                            dwellTempDefenderNotification.lastPlugged = isPluggedIn;
                            dwellTempDefenderNotification.sendDefenderNotification(isPluggedIn);
                        }
                    } else {
                        batteryDefenderNotificationHandler.inDefenderSection = true;
                        DwellTempDefenderNotification dwellTempDefenderNotification2 = batteryDefenderNotificationHandler.getDwellTempDefenderNotification();
                        Log.d("DwellTempDefenderNotification", "showNotification, postNotificationVisible:" + dwellTempDefenderNotification2.postNotificationVisible + "->true");
                        if (dwellTempDefenderNotification2.postNotificationVisible) {
                            dwellTempDefenderNotification2.cancelPostNotification();
                        }
                        dwellTempDefenderNotification2.sendDefenderNotification(true);
                        dwellTempDefenderNotification2.notificationVisible = true;
                        dwellTempDefenderNotification2.lastPlugged = isPluggedIn;
                        UiEventLogger uiEventLogger = batteryDefenderNotificationHandler.uiEventLogger;
                        if (uiEventLogger != null) {
                            uiEventLogger.log(BatteryMetricEvent.BATTERY_DEFENDER_NOTIFICATION);
                        }
                        BuildersKt.launch$default(contextScope, (CoroutineContext) null, (CoroutineStart) null, new BatteryDefenderNotificationHandler$onBatteryDefenderEvent$1(batteryDefenderNotificationHandler, (Continuation) null), 3);
                    }
                } else {
                    boolean isCharged = BatteryStatus.isCharged(1, batteryDefenderNotificationHandler.batteryLevel);
                    MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0.m("onNonDefenderEvent: charged: ", isCharged, "BatteryDefenderNotification");
                    boolean z2 = batteryDefenderNotificationHandler.dockDefendEnabled;
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
                            notificationManager2.cancelAsUser("battery_defender", 2131952447, UserHandle.ALL);
                        }
                        BuildersKt.launch$default(contextScope, (CoroutineContext) null, (CoroutineStart) null, new BatteryDefenderNotificationHandler$exitDockDefenderIfNeeded$1(batteryDefenderNotificationHandler, (Continuation) null), 3);
                    }
                }
            }
        }
        return null;
    }
}
