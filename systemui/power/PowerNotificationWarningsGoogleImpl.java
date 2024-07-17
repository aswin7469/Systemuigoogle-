package com.google.android.systemui.power;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import com.android.internal.logging.UiEventLogger;
import com.android.keyguard.ActiveUnlockConfig$$ExternalSyntheticOutline0;
import com.android.keyguard.KeyguardClockSwitch$$ExternalSyntheticOutline0;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.broadcast.BroadcastSender;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.power.EnhancedEstimates;
import com.android.systemui.power.PowerNotificationWarnings;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.util.settings.GlobalSettings;
import com.android.systemui.util.time.SystemClockImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import dagger.Lazy;
import java.io.PrintWriter;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.inject.Provider;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class PowerNotificationWarningsGoogleImpl extends PowerNotificationWarnings {
    public AdaptiveChargingNotification mAdaptiveChargingNotification;
    public final Lazy mBatteryControllerLazy;
    public BatteryDefenderNotificationHandler mBatteryDefenderNotificationHandler;
    public final BatteryEventClient mBatteryEventClient;
    public BatteryInfoBroadcast mBatteryInfoBroadcast;
    public BatterySaverConfirmationDialog mBatterySaverConfirmationDialog;
    public final Provider mBatterySaverConfirmationDialogProvider;
    public final BroadcastDispatcher mBroadcastDispatcher;
    final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        /* JADX WARNING: Removed duplicated region for block: B:114:0x02e0  */
        /* JADX WARNING: Removed duplicated region for block: B:16:0x004e  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void onReceive(android.content.Context r11, android.content.Intent r12) {
            /*
                r10 = this;
                if (r12 == 0) goto L_0x03e2
                java.lang.String r11 = r12.getAction()
                if (r11 != 0) goto L_0x000a
                goto L_0x03e2
            L_0x000a:
                java.lang.StringBuilder r11 = new java.lang.StringBuilder
                java.lang.String r0 = "onReceive: "
                r11.<init>(r0)
                java.lang.String r0 = r12.getAction()
                r11.append(r0)
                java.lang.String r11 = r11.toString()
                java.lang.String r0 = "PowerNotificationWarningsGoogleImpl"
                android.util.Log.d(r0, r11)
                java.lang.String r11 = r12.getAction()
                int r0 = r11.hashCode()
                r1 = -1841120158(0xffffffff9242bc62, float:-6.1447806E-28)
                r2 = 0
                r3 = 1
                if (r0 == r1) goto L_0x0040
                r1 = 1207904925(0x47ff2a9d, float:130645.23)
                if (r0 == r1) goto L_0x0036
                goto L_0x004a
            L_0x0036:
                java.lang.String r0 = "PNW.startSaverConfirmation"
                boolean r11 = r11.equals(r0)
                if (r11 == 0) goto L_0x004a
                r11 = r2
                goto L_0x004b
            L_0x0040:
                java.lang.String r0 = "systemui.power.action.START_FLIPENDO"
                boolean r11 = r11.equals(r0)
                if (r11 == 0) goto L_0x004a
                r11 = r3
                goto L_0x004b
            L_0x004a:
                r11 = -1
            L_0x004b:
                r0 = 0
                if (r11 == 0) goto L_0x02e0
                if (r11 == r3) goto L_0x02d2
                com.google.android.systemui.power.PowerNotificationWarningsGoogleImpl r11 = com.google.android.systemui.power.PowerNotificationWarningsGoogleImpl.this
                com.google.android.systemui.power.BatteryInfoBroadcast r11 = r11.mBatteryInfoBroadcast
                r11.getClass()
                com.google.android.systemui.power.BatteryInfoBroadcast$$ExternalSyntheticLambda0 r1 = new com.google.android.systemui.power.BatteryInfoBroadcast$$ExternalSyntheticLambda0
                r1.<init>(r11, r12)
                java.util.concurrent.Executor r11 = r11.mExecutor
                r11.execute(r1)
                com.google.android.systemui.power.PowerNotificationWarningsGoogleImpl r11 = com.google.android.systemui.power.PowerNotificationWarningsGoogleImpl.this
                com.google.android.systemui.power.LowPowerWarningsController r11 = r11.mLowPowerWarningsController
                r11.getClass()
                com.google.android.systemui.power.LowPowerWarningsController$dispatchIntent$1 r1 = new com.google.android.systemui.power.LowPowerWarningsController$dispatchIntent$1
                r1.<init>(r11, r12)
                java.util.concurrent.Executor r11 = r11.executor
                r11.execute(r1)
                com.google.android.systemui.power.PowerNotificationWarningsGoogleImpl r11 = com.google.android.systemui.power.PowerNotificationWarningsGoogleImpl.this
                com.google.android.systemui.power.BatteryDefenderNotificationHandler r11 = r11.mBatteryDefenderNotificationHandler
                r1 = 4
                if (r11 == 0) goto L_0x0133
                java.lang.String r4 = r12.getAction()
                if (r4 == 0) goto L_0x0133
                int r5 = r4.hashCode()
                r6 = 3
                kotlinx.coroutines.internal.ContextScope r7 = r11.backgroundCoroutineScope
                com.android.internal.logging.UiEventLogger r8 = r11.uiEventLogger
                java.lang.String r9 = "BatteryDefenderNotification"
                switch(r5) {
                    case -1838162442: goto L_0x010d;
                    case -1769274126: goto L_0x00da;
                    case -646306533: goto L_0x00a7;
                    case 798292259: goto L_0x008f;
                    default: goto L_0x008d;
                }
            L_0x008d:
                goto L_0x0133
            L_0x008f:
                java.lang.String r5 = "android.intent.action.BOOT_COMPLETED"
                boolean r4 = r4.equals(r5)
                if (r4 != 0) goto L_0x0099
                goto L_0x0133
            L_0x0099:
                boolean r4 = r11.dockDefendEnabled
                if (r4 == 0) goto L_0x0133
                com.google.android.systemui.power.BatteryDefenderNotificationHandler$onActionBootCompleted$1 r4 = new com.google.android.systemui.power.BatteryDefenderNotificationHandler$onActionBootCompleted$1
                r4.<init>(r11, r0)
                kotlinx.coroutines.BuildersKt.launch$default(r7, r0, r0, r4, r6)
                goto L_0x0133
            L_0x00a7:
                java.lang.String r5 = "PNW.defenderResumeCharging.settings"
                boolean r4 = r4.equals(r5)
                if (r4 != 0) goto L_0x00b1
                goto L_0x0133
            L_0x00b1:
                java.lang.String r4 = "onActionResumeChargingSettings"
                android.util.Log.d(r9, r4)
                if (r8 == 0) goto L_0x00bf
                com.google.android.systemui.power.BatteryMetricEvent r4 = com.google.android.systemui.power.BatteryMetricEvent.BATTERY_DEFENDER_BYPASS_LIMIT_FOR_TIPS
                int r5 = r11.batteryLevel
                r8.logWithPosition(r4, r2, r0, r5)
            L_0x00bf:
                com.google.android.systemui.power.DwellTempDefenderNotification r4 = r11.getDwellTempDefenderNotification()
                r4.cancelNotification()
                java.lang.String r4 = "is_dock_defender"
                boolean r4 = r12.getBooleanExtra(r4, r2)
                if (r4 == 0) goto L_0x00d0
                r4 = r1
                goto L_0x00d1
            L_0x00d0:
                r4 = r2
            L_0x00d1:
                com.google.android.systemui.power.BatteryDefenderNotificationHandler$onActionResumeChargingSettings$1 r5 = new com.google.android.systemui.power.BatteryDefenderNotificationHandler$onActionResumeChargingSettings$1
                r5.<init>(r11, r4, r0)
                kotlinx.coroutines.BuildersKt.launch$default(r7, r0, r0, r5, r6)
                goto L_0x0133
            L_0x00da:
                java.lang.String r5 = "systemui.power.action.dismissBatteryDefenderWarning"
                boolean r4 = r4.equals(r5)
                if (r4 != 0) goto L_0x00e3
                goto L_0x0133
            L_0x00e3:
                com.google.android.systemui.power.DwellTempDefenderNotification r11 = r11.getDwellTempDefenderNotification()
                boolean r4 = r11.notificationVisible
                java.lang.StringBuilder r5 = new java.lang.StringBuilder
                java.lang.String r6 = "swipeNotificationByUser, notificationVisible:"
                r5.<init>(r6)
                r5.append(r4)
                java.lang.String r4 = "->false"
                r5.append(r4)
                java.lang.String r4 = r5.toString()
                java.lang.String r5 = "DwellTempDefenderNotification"
                android.util.Log.d(r5, r4)
                r11.notificationVisible = r2
                com.android.internal.logging.UiEventLogger r11 = r11.uiEventLogger
                if (r11 == 0) goto L_0x0133
                com.google.android.systemui.power.BatteryMetricEvent r4 = com.google.android.systemui.power.BatteryMetricEvent.DELETE_BATTERY_DEFENDER_NOTIFICATION
                r11.log(r4)
                goto L_0x0133
            L_0x010d:
                java.lang.String r5 = "PNW.defenderResumeCharging"
                boolean r4 = r4.equals(r5)
                if (r4 != 0) goto L_0x0116
                goto L_0x0133
            L_0x0116:
                java.lang.String r4 = "onActionResumeChargingIntent"
                android.util.Log.d(r9, r4)
                if (r8 == 0) goto L_0x0124
                com.google.android.systemui.power.BatteryMetricEvent r4 = com.google.android.systemui.power.BatteryMetricEvent.BATTERY_DEFENDER_BYPASS_LIMIT
                int r5 = r11.batteryLevel
                r8.logWithPosition(r4, r2, r0, r5)
            L_0x0124:
                com.google.android.systemui.power.DwellTempDefenderNotification r4 = r11.getDwellTempDefenderNotification()
                r4.cancelNotification()
                com.google.android.systemui.power.BatteryDefenderNotificationHandler$onActionResumeChargingIntent$1 r4 = new com.google.android.systemui.power.BatteryDefenderNotificationHandler$onActionResumeChargingIntent$1
                r4.<init>(r11, r0)
                kotlinx.coroutines.BuildersKt.launch$default(r7, r0, r0, r4, r6)
            L_0x0133:
                com.google.android.systemui.power.PowerNotificationWarningsGoogleImpl r11 = com.google.android.systemui.power.PowerNotificationWarningsGoogleImpl.this
                com.google.android.systemui.power.AdaptiveChargingNotification r11 = r11.mAdaptiveChargingNotification
                java.lang.String r4 = "android.intent.action.BATTERY_CHANGED"
                if (r11 == 0) goto L_0x01b3
                java.lang.String r5 = r12.getAction()
                if (r5 != 0) goto L_0x0143
                goto L_0x01b3
            L_0x0143:
                boolean r6 = r4.equals(r5)
                if (r6 == 0) goto L_0x014d
                r11.resolveBatteryChangedIntent(r12)
                goto L_0x01b3
            L_0x014d:
                java.lang.String r6 = "com.google.android.systemui.adaptivecharging.ADAPTIVE_CHARGING_DEADLINE_SET"
                boolean r7 = r6.equals(r5)
                if (r7 == 0) goto L_0x0159
                r11.checkAdaptiveChargingStatus(r3)
                goto L_0x01b3
            L_0x0159:
                java.lang.String r7 = "PNW.acChargeNormally"
                boolean r7 = r7.equals(r5)
                com.android.internal.logging.UiEventLogger r8 = r11.mUiEventLogger
                if (r7 == 0) goto L_0x01a4
                com.google.android.systemui.power.BatteryMetricEvent r5 = com.google.android.systemui.power.BatteryMetricEvent.ADAPTIVE_CHARGING_NOTIFICATION_BYPASS
                r8.log(r5)
                com.google.android.systemui.googlebattery.AdaptiveChargingManager r5 = r11.mAdaptiveChargingManager
                r5.getClass()
                vendor.google.google_battery.IGoogleBattery r5 = com.google.android.systemui.googlebattery.GoogleBatteryManager.initHalInterface(r0)
                if (r5 != 0) goto L_0x0174
                goto L_0x0186
            L_0x0174:
                r7 = r5
                vendor.google.google_battery.IGoogleBattery$Stub$Proxy r7 = (vendor.google.google_battery.IGoogleBattery.Stub.Proxy) r7     // Catch:{ RemoteException | ServiceSpecificException | IllegalArgumentException -> 0x017b }
                r7.setChargingDeadline()     // Catch:{ RemoteException | ServiceSpecificException | IllegalArgumentException -> 0x017b }
                goto L_0x0183
            L_0x017b:
                r7 = move-exception
                java.lang.String r8 = "AdaptiveChargingManager"
                java.lang.String r9 = "setChargingDeadline failed: "
                android.util.Log.e(r8, r9, r7)
            L_0x0183:
                com.google.android.systemui.googlebattery.GoogleBatteryManager.destroyHalInterface(r5, r0)
            L_0x0186:
                r11.cancelNotification()
                android.content.Intent r0 = new android.content.Intent
                r0.<init>(r6)
                android.content.Context r11 = r11.mContext
                java.lang.String r5 = r11.getPackageName()
                android.content.Intent r0 = r0.setPackage(r5)
                r5 = 1342177280(0x50000000, float:8.5899346E9)
                android.content.Intent r0 = r0.setFlags(r5)
                android.os.UserHandle r5 = android.os.UserHandle.ALL
                r11.sendBroadcastAsUser(r0, r5)
                goto L_0x01b3
            L_0x01a4:
                java.lang.String r11 = "systemui.power.action.dismissAdaptiveChargingWarning"
                boolean r11 = r11.equals(r5)
                if (r11 == 0) goto L_0x01b3
                if (r8 == 0) goto L_0x01b3
                com.google.android.systemui.power.BatteryMetricEvent r11 = com.google.android.systemui.power.BatteryMetricEvent.DELETE_ADAPTIVE_CHARGING_NOTIFICATION
                r8.log(r11)
            L_0x01b3:
                com.google.android.systemui.power.PowerNotificationWarningsGoogleImpl r11 = com.google.android.systemui.power.PowerNotificationWarningsGoogleImpl.this
                com.google.android.systemui.power.IncompatibleChargerNotification r11 = r11.mIncompatibleChargerNotification
                if (r11 == 0) goto L_0x01e1
                java.lang.String r0 = r12.getAction()
                java.lang.StringBuilder r5 = new java.lang.StringBuilder
                java.lang.String r6 = "dispatchIntent: "
                r5.<init>(r6)
                r5.append(r0)
                java.lang.String r5 = r5.toString()
                java.lang.String r6 = "IncompatibleChargerNotification"
                android.util.Log.d(r6, r5)
                java.lang.String r5 = "systemui.power.action.dismissIncompatibleChargerWarning"
                boolean r0 = r5.equals(r0)
                if (r0 == 0) goto L_0x01e1
                com.google.android.systemui.power.BatteryMetricEvent r0 = com.google.android.systemui.power.BatteryMetricEvent.DELETE_INCOMPATIBLE_CHARGING_NOTIFICATION
                com.android.internal.logging.UiEventLogger r11 = r11.mUiEventLogger
                if (r11 == 0) goto L_0x01e1
                r11.log(r0)
            L_0x01e1:
                com.google.android.systemui.power.PowerNotificationWarningsGoogleImpl r10 = com.google.android.systemui.power.PowerNotificationWarningsGoogleImpl.this
                com.google.android.systemui.power.WirelessChargingNotification r10 = r10.mWirelessChargingNotification
                if (r10 == 0) goto L_0x02d1
                java.lang.String r11 = r12.getAction()
                boolean r0 = r4.equals(r11)
                com.android.internal.logging.UiEventLogger r4 = r10.mUiEventLogger
                java.lang.String r5 = "systemui.power.action.disableWirelessChargingNotification"
                java.lang.String r6 = "systemui.power.action.dismissWirelessChargingNotification"
                android.content.Context r7 = r10.mContext
                if (r0 == 0) goto L_0x02ae
                java.lang.String r11 = "plugged"
                int r11 = r12.getIntExtra(r11, r2)
                boolean r12 = r10.mIsWirelessCharging
                if (r11 != r1) goto L_0x0204
                r2 = r3
            L_0x0204:
                r10.mIsWirelessCharging = r2
                if (r12 != r2) goto L_0x020a
                goto L_0x02d1
            L_0x020a:
                java.lang.StringBuilder r11 = new java.lang.StringBuilder
                java.lang.String r0 = "mWirelessCharging:"
                r11.<init>(r0)
                boolean r0 = r10.mIsWirelessCharging
                java.lang.String r1 = "WirelessChargingNotification"
                com.android.settingslib.bluetooth.CachedBluetoothDevice$$ExternalSyntheticOutline0.m(r11, r0, r1)
                boolean r11 = r10.mIsWirelessCharging
                if (r11 != 0) goto L_0x0223
                if (r12 == 0) goto L_0x0223
                r10.cancelNotification()
                goto L_0x02d1
            L_0x0223:
                boolean r11 = com.android.settingslib.Utils.shouldShowWirelessChargingNotificationInternal(r7)     // Catch:{ Exception -> 0x02a7 }
                if (r11 == 0) goto L_0x02d1
                java.lang.StringBuilder r11 = new java.lang.StringBuilder
                java.lang.String r12 = "[sendNotification] isNotificationActive: "
                r11.<init>(r12)
                boolean r12 = r10.mIsNotificationActive
                r11.append(r12)
                java.lang.String r11 = r11.toString()
                android.util.Log.d(r1, r11)
                r11 = 2131954371(0x7f130ac3, float:1.954524E38)
                java.lang.String r12 = r7.getString(r11)
                r0 = 2131954370(0x7f130ac2, float:1.9545237E38)
                java.lang.String r0 = r7.getString(r0)
                androidx.core.app.NotificationCompat$Builder r1 = new androidx.core.app.NotificationCompat$Builder
                r1.<init>(r7)
                android.app.Notification r2 = r1.mNotification
                r8 = 2131232472(0x7f0806d8, float:1.8081054E38)
                r2.icon = r8
                java.lang.CharSequence r0 = androidx.core.app.NotificationCompat$Builder.limitCharSequenceLength(r0)
                r1.mContentText = r0
                java.lang.CharSequence r12 = androidx.core.app.NotificationCompat$Builder.limitCharSequenceLength(r12)
                r1.mContentTitle = r12
                android.app.PendingIntent r12 = com.google.android.systemui.power.PowerUtils.createPendingIntent(r7, r6)
                android.app.Notification r0 = r1.mNotification
                r0.deleteIntent = r12
                r1.mVisibility = r3
                r12 = 2131951960(0x7f130158, float:1.954035E38)
                java.lang.String r12 = r7.getString(r12)
                r0 = 2131952469(0x7f130355, float:1.9541382E38)
                android.app.PendingIntent r0 = com.google.android.systemui.power.PowerUtils.createHelpArticlePendingIntent(r0, r7)
                r1.addAction(r12, r0)
                r12 = 2131954369(0x7f130ac1, float:1.9545235E38)
                java.lang.String r12 = r7.getString(r12)
                android.app.PendingIntent r0 = com.google.android.systemui.power.PowerUtils.createPendingIntent(r7, r5)
                r1.addAction(r12, r0)
                r1.mLocalOnly = r3
                com.google.android.systemui.power.PowerUtils.overrideNotificationAppName(r7, r1)
                android.app.NotificationManager r12 = r10.mNotificationManager
                android.app.Notification r0 = r1.build()
                android.os.UserHandle r1 = android.os.UserHandle.ALL
                java.lang.String r2 = "wireless_charging"
                r12.notifyAsUser(r2, r11, r0, r1)
                r10.mIsNotificationActive = r3
                com.google.android.systemui.power.BatteryMetricEvent r10 = com.google.android.systemui.power.BatteryMetricEvent.SEND_WIRELESS_CHARGING_NOTIFICATION
                if (r4 == 0) goto L_0x02d1
                r4.log(r10)
                goto L_0x02d1
            L_0x02a7:
                r10 = move-exception
                java.lang.String r11 = "shouldShowWirelessChargingNotification()"
                android.util.Log.e(r1, r11, r10)
                goto L_0x02d1
            L_0x02ae:
                boolean r12 = r5.equals(r11)
                if (r12 == 0) goto L_0x02c4
                r10.cancelNotification()
                r10 = -9223372036854775808
                com.android.settingslib.Utils.updateWirelessChargingNotificationTimestamp(r7, r10)
                com.google.android.systemui.power.BatteryMetricEvent r10 = com.google.android.systemui.power.BatteryMetricEvent.DISABLE_WIRELESS_CHARGING_NOTIFICATION
                if (r4 == 0) goto L_0x02d1
                r4.log(r10)
                goto L_0x02d1
            L_0x02c4:
                boolean r10 = r6.equals(r11)
                if (r10 == 0) goto L_0x02d1
                com.google.android.systemui.power.BatteryMetricEvent r10 = com.google.android.systemui.power.BatteryMetricEvent.DISMISS_WIRELESS_CHARGING_NOTIFICATION
                if (r4 == 0) goto L_0x02d1
                r4.log(r10)
            L_0x02d1:
                return
            L_0x02d2:
                com.google.android.systemui.power.PowerNotificationWarningsGoogleImpl r11 = com.google.android.systemui.power.PowerNotificationWarningsGoogleImpl.this
                java.util.concurrent.Executor r11 = r11.mExecutor
                com.google.android.systemui.power.PowerNotificationWarningsGoogleImpl$$ExternalSyntheticLambda1 r12 = new com.google.android.systemui.power.PowerNotificationWarningsGoogleImpl$$ExternalSyntheticLambda1
                r0 = 1
                r12.<init>(r0, r10)
                r11.execute(r12)
                return
            L_0x02e0:
                com.google.android.systemui.power.PowerNotificationWarningsGoogleImpl r11 = com.google.android.systemui.power.PowerNotificationWarningsGoogleImpl.this
                com.google.android.systemui.power.LowPowerWarningsController r11 = r11.mLowPowerWarningsController
                r11.cancelNotification()
                com.google.android.systemui.power.PowerNotificationWarningsGoogleImpl r11 = com.google.android.systemui.power.PowerNotificationWarningsGoogleImpl.this
                javax.inject.Provider r12 = r11.mBatterySaverConfirmationDialogProvider
                java.lang.Object r12 = r12.get()
                com.google.android.systemui.power.BatterySaverConfirmationDialog r12 = (com.google.android.systemui.power.BatterySaverConfirmationDialog) r12
                r11.mBatterySaverConfirmationDialog = r12
                com.google.android.systemui.power.PowerNotificationWarningsGoogleImpl r11 = com.google.android.systemui.power.PowerNotificationWarningsGoogleImpl.this
                dagger.Lazy r11 = r11.mBatteryControllerLazy
                java.lang.Object r11 = r11.get()
                com.android.systemui.statusbar.policy.BatteryController r11 = (com.android.systemui.statusbar.policy.BatteryController) r11
                com.android.systemui.statusbar.policy.BatteryControllerImpl r11 = (com.android.systemui.statusbar.policy.BatteryControllerImpl) r11
                java.util.concurrent.atomic.AtomicReference r11 = r11.mPowerSaverStartView
                java.lang.Object r11 = r11.get()
                java.lang.ref.WeakReference r11 = (java.lang.ref.WeakReference) r11
                com.google.android.systemui.power.PowerNotificationWarningsGoogleImpl r10 = com.google.android.systemui.power.PowerNotificationWarningsGoogleImpl.this
                com.google.android.systemui.power.BatterySaverConfirmationDialog r10 = r10.mBatterySaverConfirmationDialog
                if (r11 == 0) goto L_0x0315
                java.lang.Object r11 = r11.get()
                android.view.View r11 = (android.view.View) r11
                r6 = r11
                goto L_0x0316
            L_0x0315:
                r6 = r0
            L_0x0316:
                com.android.systemui.statusbar.phone.SystemUIDialog r11 = r10.mConfirmationDialog
                if (r11 == 0) goto L_0x0322
                boolean r11 = r11.isShowing()
                if (r11 == 0) goto L_0x0322
                goto L_0x03e2
            L_0x0322:
                com.android.systemui.statusbar.phone.SystemUIDialog r11 = r10.mConfirmationDialog
                if (r11 == 0) goto L_0x032b
                r11.show()
                goto L_0x03e2
            L_0x032b:
                android.content.Context r11 = r10.mContext
                android.view.LayoutInflater r11 = android.view.LayoutInflater.from(r11)
                r12 = 2131558486(0x7f0d0056, float:1.874229E38)
                android.view.View r11 = r11.inflate(r12, r0)
                r12 = 2131363697(0x7f0a0771, float:1.834721E38)
                android.view.View r12 = r11.findViewById(r12)
                android.widget.RadioButton r12 = (android.widget.RadioButton) r12
                r0 = 2131362570(0x7f0a030a, float:1.8344924E38)
                android.view.View r0 = r11.findViewById(r0)
                android.widget.RadioButton r0 = (android.widget.RadioButton) r0
                r10.mIsStandardMode = r3
                r1 = 2131363698(0x7f0a0772, float:1.8347212E38)
                android.view.View r1 = r11.findViewById(r1)
                com.google.android.systemui.power.BatterySaverConfirmationDialog$$ExternalSyntheticLambda3 r2 = new com.google.android.systemui.power.BatterySaverConfirmationDialog$$ExternalSyntheticLambda3
                r4 = 0
                r2.<init>(r10, r12, r0, r4)
                r1.setOnClickListener(r2)
                r1 = 2131362571(0x7f0a030b, float:1.8344926E38)
                android.view.View r1 = r11.findViewById(r1)
                com.google.android.systemui.power.BatterySaverConfirmationDialog$$ExternalSyntheticLambda3 r2 = new com.google.android.systemui.power.BatterySaverConfirmationDialog$$ExternalSyntheticLambda3
                r4 = 1
                r2.<init>(r10, r12, r0, r4)
                r1.setOnClickListener(r2)
                r12 = 2131363607(0x7f0a0717, float:1.8347028E38)
                android.view.View r12 = r11.findViewById(r12)
                android.widget.Button r12 = (android.widget.Button) r12
                com.google.android.systemui.power.BatterySaverConfirmationDialog$$ExternalSyntheticLambda5 r0 = new com.google.android.systemui.power.BatterySaverConfirmationDialog$$ExternalSyntheticLambda5
                r0.<init>(r10)
                r12.setOnClickListener(r0)
                com.android.systemui.statusbar.phone.SystemUIDialog$Factory r12 = r10.mSystemUIDialogFactory
                com.android.systemui.statusbar.phone.SystemUIDialog r12 = r12.create()
                r10.mConfirmationDialog = r12
                r0 = 2131953722(0x7f13083a, float:1.9543923E38)
                r12.setTitle(r0)
                com.android.systemui.statusbar.phone.SystemUIDialog r12 = r10.mConfirmationDialog
                r0 = 2131953718(0x7f130836, float:1.9543915E38)
                r12.setMessage(r0)
                com.android.systemui.statusbar.phone.SystemUIDialog r12 = r10.mConfirmationDialog
                r12.setView(r11)
                com.android.systemui.statusbar.phone.SystemUIDialog r11 = r10.mConfirmationDialog
                r11.getClass()
                com.android.systemui.statusbar.phone.SystemUIDialog.setShowForAllUsers(r11)
                com.android.systemui.statusbar.phone.SystemUIDialog r11 = r10.mConfirmationDialog
                r11.setCanceledOnTouchOutside(r3)
                com.android.systemui.statusbar.phone.SystemUIDialog r11 = r10.mConfirmationDialog
                com.google.android.systemui.power.BatterySaverConfirmationDialog$$ExternalSyntheticLambda0 r12 = new com.google.android.systemui.power.BatterySaverConfirmationDialog$$ExternalSyntheticLambda0
                r0 = 0
                r12.<init>(r10, r0)
                r0 = 2131951977(0x7f130169, float:1.9540384E38)
                r11.setPositiveButton(r0, r12)
                com.android.systemui.statusbar.phone.SystemUIDialog r11 = r10.mConfirmationDialog
                com.google.android.systemui.power.BatterySaverConfirmationDialog$$ExternalSyntheticLambda0 r12 = new com.google.android.systemui.power.BatterySaverConfirmationDialog$$ExternalSyntheticLambda0
                r0 = 1
                r12.<init>(r10, r0)
                r0 = 2131953717(0x7f130835, float:1.9543913E38)
                r11.setNeutralButton(r0, r12, r3)
                if (r6 == 0) goto L_0x03d8
                boolean r11 = r6.isAggregatedVisible()
                if (r11 == 0) goto L_0x03d8
                com.android.systemui.statusbar.phone.SystemUIDialog r5 = r10.mConfirmationDialog
                com.android.systemui.animation.DialogTransitionAnimator r4 = r10.mDialogTransitionAnimator
                r4.getClass()
                r8 = 0
                r9 = 12
                r7 = 0
                com.android.systemui.animation.DialogTransitionAnimator.showFromView$default(r4, r5, r6, r7, r8, r9)
                goto L_0x03dd
            L_0x03d8:
                com.android.systemui.statusbar.phone.SystemUIDialog r11 = r10.mConfirmationDialog
                r11.show()
            L_0x03dd:
                com.google.android.systemui.power.BatteryMetricEvent r11 = com.google.android.systemui.power.BatteryMetricEvent.SAVER_CONFIRMATION_DIALOG
                r10.log(r11)
            L_0x03e2:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.power.PowerNotificationWarningsGoogleImpl.AnonymousClass1.onReceive(android.content.Context, android.content.Intent):void");
        }
    };
    public final BroadcastSender mBroadcastSender;
    public final Context mContext;
    public final EnhancedEstimates mEnhancedEstimates;
    public final Executor mExecutor = Executors.newFixedThreadPool(2);
    public final GlobalSettings mGlobalSettings;
    public final Handler mHandler;
    public IncompatibleChargerNotification mIncompatibleChargerNotification;
    LowPowerWarningsController mLowPowerWarningsController;
    public final Provider mSevereLowBatteryNotificationProvider;
    public final UserTracker mUserTracker;
    public WirelessChargingNotification mWirelessChargingNotification;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PowerNotificationWarningsGoogleImpl(Context context, ActivityStarter activityStarter, BroadcastDispatcher broadcastDispatcher, BroadcastSender broadcastSender, UiEventLogger uiEventLogger, GlobalSettings globalSettings, Lazy lazy, DialogTransitionAnimator dialogTransitionAnimator, EnhancedEstimates enhancedEstimates, UserTracker userTracker, BatteryEventClient batteryEventClient, SystemUIDialog.Factory factory, DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider, DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider2) {
        super(context, activityStarter, broadcastSender, lazy, dialogTransitionAnimator, uiEventLogger, userTracker, factory);
        Handler handler = new Handler(Looper.getMainLooper());
        this.mHandler = handler;
        this.mContext = context;
        this.mBroadcastDispatcher = broadcastDispatcher;
        this.mBroadcastSender = broadcastSender;
        this.mGlobalSettings = globalSettings;
        this.mEnhancedEstimates = enhancedEstimates;
        this.mUserTracker = userTracker;
        this.mBatteryEventClient = batteryEventClient;
        this.mBatteryControllerLazy = lazy;
        this.mBatterySaverConfirmationDialogProvider = switchingProvider;
        this.mSevereLowBatteryNotificationProvider = switchingProvider2;
        handler.post(new PowerNotificationWarningsGoogleImpl$$ExternalSyntheticLambda0(this, context, uiEventLogger));
    }

    public final void dismissLowBatteryWarning() {
        LowPowerWarningsController lowPowerWarningsController = this.mLowPowerWarningsController;
        if (lowPowerWarningsController != null) {
            lowPowerWarningsController.cancelNotification();
        }
    }

    public final void dump(PrintWriter printWriter) {
        boolean z;
        boolean z2;
        super.dump(printWriter);
        BatteryInfoBroadcast batteryInfoBroadcast = this.mBatteryInfoBroadcast;
        if (batteryInfoBroadcast != null) {
            printWriter.println("\tdump BatteryInfoBroadcast states:");
            batteryInfoBroadcast.writeString(printWriter, "LastConnectedTime: ", "last_phone_connected_time");
            batteryInfoBroadcast.writeString(printWriter, "LastDisconnectedTime: ", "last_phone_disconnected_time");
            batteryInfoBroadcast.writeString(printWriter, "LastDataResetTime: ", "last_data_reset_time");
        }
        IncompatibleChargerNotification incompatibleChargerNotification = this.mIncompatibleChargerNotification;
        if (incompatibleChargerNotification != null) {
            StringBuilder m = KeyguardClockSwitch$$ExternalSyntheticOutline0.m(printWriter, "\tdump IncompatibleCharger states:", "\t\tLastCompatibleChargerTime: ");
            Context context = incompatibleChargerNotification.mContext;
            m.append(DumpUtils.toReadableDateTime(IncompatibleChargerNotification.getSharedPreferences(context).getLong(IncompatibleChargerNotification.KEY_COMPATIBLE_CHARGER_TIME, 0)));
            StringBuilder m2 = KeyguardClockSwitch$$ExternalSyntheticOutline0.m(printWriter, m.toString(), "\t\tLastIncompatibleChargerTime: ");
            m2.append(DumpUtils.toReadableDateTime(IncompatibleChargerNotification.getSharedPreferences(context).getLong(IncompatibleChargerNotification.KEY_INCOMPATIBLE_CHARGER_TIME, 0)));
            printWriter.println(m2.toString());
        }
        LowPowerWarningsController lowPowerWarningsController = this.mLowPowerWarningsController;
        boolean z3 = false;
        if (lowPowerWarningsController != null) {
            printWriter.println("\tdump LowPowerWarningsController states");
            printWriter.println("\t\tprevBatteryLevel: " + lowPowerWarningsController.prevBatteryLevel);
            printWriter.println("\t\tprevBatteryEventType: " + lowPowerWarningsController.prevBatteryEventTypes);
            if (lowPowerWarningsController.globalSettings.getInt(1, "low_power_mode_reminder_enabled") == 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            printWriter.println("\t\tisBatterySaverReminderDisabled: " + z2);
            ActiveUnlockConfig$$ExternalSyntheticOutline0.m("\t\tisScheduledByPercentage: ", lowPowerWarningsController.isScheduledByPercentage(), printWriter);
            ActiveUnlockConfig$$ExternalSyntheticOutline0.m("\t\tlowBatteryNotificationCancelled: ", lowPowerWarningsController.lowBatteryNotificationCancelled, printWriter);
            ActiveUnlockConfig$$ExternalSyntheticOutline0.m("\t\tsevereLowBatteryNotificationCancelled: ", lowPowerWarningsController.severeLowBatteryNotificationCancelled, printWriter);
        }
        BatteryDefenderNotificationHandler batteryDefenderNotificationHandler = this.mBatteryDefenderNotificationHandler;
        if (batteryDefenderNotificationHandler != null) {
            printWriter.println("\tdump BatteryDefenderNotificationHandler states:");
            Context context2 = batteryDefenderNotificationHandler.context;
            if (Settings.Global.getInt(context2.getContentResolver(), "dock_defender_first_run", -1) == -1) {
                z = true;
            } else {
                z = false;
            }
            printWriter.println("\t\tdockDefendFirstRun: " + z);
            if (Settings.Global.getInt(context2.getContentResolver(), "dock_defender_bypass", 0) == 1) {
                z3 = true;
            }
            ActiveUnlockConfig$$ExternalSyntheticOutline0.m("\t\tdockDefendBypassed: ", z3, printWriter);
            ActiveUnlockConfig$$ExternalSyntheticOutline0.m("\t\tdockDefendEnabled: ", batteryDefenderNotificationHandler.dockDefendEnabled, printWriter);
            ActiveUnlockConfig$$ExternalSyntheticOutline0.m("\t\tinBatteryDefenderSection: ", batteryDefenderNotificationHandler.inDefenderSection, printWriter);
            printWriter.println("\t\tbatteryLevel: " + batteryDefenderNotificationHandler.batteryLevel);
            printWriter.println("\t\tstartTimestamp: " + ((SharedPreferences) batteryDefenderNotificationHandler.sharedPreferences$delegate.getValue()).getLong("trigger_time", -1));
            ((SystemClockImpl) batteryDefenderNotificationHandler.systemClock).getClass();
            printWriter.println("\t\tcurrentTimestamp: " + System.currentTimeMillis());
        }
        this.mExecutor.execute(new PowerNotificationWarningsGoogleImpl$$ExternalSyntheticLambda1(0, this));
    }

    public final void userSwitched() {
        Integer num;
        LowPowerWarningsController lowPowerWarningsController = this.mLowPowerWarningsController;
        if (lowPowerWarningsController != null && (num = lowPowerWarningsController.prevBatteryLevel) != null) {
            lowPowerWarningsController.onBatteryEventUpdate(num.intValue(), lowPowerWarningsController.prevBatteryEventTypes);
        }
    }
}
