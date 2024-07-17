package com.google.android.systemui.power;

import android.content.Context;
import android.os.PowerManager;
import android.os.UserHandle;
import android.provider.Settings;
import android.util.Log;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.util.settings.GlobalSettings;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.collections.EmptyList;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class LowPowerWarningsController {
    public final Context context;
    public final Executor executor;
    public boolean extremeLowBatterySectionEntered;
    public final ExtremeLowBatteryNotification extremeLowNotification;
    public final GlobalSettings globalSettings;
    public final LowBatteryNotification lowBatteryNotification;
    public boolean lowBatteryNotificationCancelled;
    public boolean lowBatterySectionEntered;
    public final PowerManager powerManager;
    public List prevBatteryEventTypes = EmptyList.INSTANCE;
    public Integer prevBatteryLevel;
    public Boolean prevPowerSaveEnabledAsync;
    public final SevereLowBatteryDialog severeLowBatteryDialog;
    public boolean severeLowBatterySectionEntered;
    public final UiEventLogger uiEventLogger;

    public LowPowerWarningsController(Context context2, SevereLowBatteryDialog severeLowBatteryDialog2, GlobalSettings globalSettings2, UiEventLogger uiEventLogger2, Executor executor2) {
        LowBatteryNotification lowBatteryNotification2 = new LowBatteryNotification(context2);
        ExtremeLowBatteryNotification extremeLowBatteryNotification = new ExtremeLowBatteryNotification(context2, uiEventLogger2);
        this.context = context2;
        this.severeLowBatteryDialog = severeLowBatteryDialog2;
        this.globalSettings = globalSettings2;
        this.uiEventLogger = uiEventLogger2;
        this.executor = executor2;
        this.lowBatteryNotification = lowBatteryNotification2;
        this.extremeLowNotification = extremeLowBatteryNotification;
        this.powerManager = (PowerManager) context2.getSystemService(PowerManager.class);
        Settings.Secure.putInt(context2.getContentResolver(), "suppress_auto_battery_saver_suggestion", 1);
        Settings.Secure.putInt(context2.getContentResolver(), "low_power_warning_acknowledged", 1);
    }

    public final void cancelNotification() {
        if (this.lowBatterySectionEntered) {
            Log.d("LowPowerWarningsController", "cancelNotification->lowBatterySection");
            this.lowBatteryNotification.mNotificationManager.cancelAsUser("low_battery", 3, UserHandle.ALL);
            this.lowBatteryNotificationCancelled = true;
        }
        if (this.severeLowBatterySectionEntered) {
            Log.d("LowPowerWarningsController", "cancelNotification->severeLowBatterySection");
            SevereLowBatteryDialog severeLowBatteryDialog2 = this.severeLowBatteryDialog;
            SystemUIDialog systemUIDialog = severeLowBatteryDialog2.mSevereBatteryDialog;
            if (systemUIDialog != null && systemUIDialog.isShowing()) {
                severeLowBatteryDialog2.mSevereBatteryDialog.dismiss();
            }
        }
        if (this.extremeLowBatterySectionEntered) {
            Log.d("LowPowerWarningsController", "cancelNotification->extremeLowBatterySection");
            this.extremeLowNotification.mNotificationManager.cancelAsUser("low_battery", 2131952516, UserHandle.ALL);
        }
    }

    public final boolean isScheduledByPercentage() {
        GlobalSettings globalSettings2 = this.globalSettings;
        if (globalSettings2.getInt(0, "automatic_power_save_mode") != 0 || globalSettings2.getInt(0, "low_power_trigger_level") <= 0) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: type inference failed for: r5v2, types: [java.lang.Object, androidx.core.app.NotificationCompat$BigTextStyle] */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x01d7, code lost:
        if (r4.mIDreamManager.isDreaming() != false) goto L_0x01d9;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onBatteryEventUpdate(int r17, java.util.List r18) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = r18
            java.lang.Integer r3 = java.lang.Integer.valueOf(r17)
            r0.prevBatteryLevel = r3
            r0.prevBatteryEventTypes = r2
            boolean r3 = r0.lowBatteryNotificationCancelled
            r4 = 0
            java.lang.String r5 = "LowPowerWarningsController"
            if (r3 != 0) goto L_0x001d
            boolean r6 = r0.lowBatterySectionEntered
            if (r6 != 0) goto L_0x001d
            boolean r6 = r0.severeLowBatterySectionEntered
            if (r6 == 0) goto L_0x0059
        L_0x001d:
            r6 = 30
            if (r1 < r6) goto L_0x0059
            boolean r6 = r0.lowBatterySectionEntered
            boolean r7 = r0.severeLowBatterySectionEntered
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = "reset section guard for low/severe low. batteryLevel:"
            r8.<init>(r9)
            r8.append(r1)
            java.lang.String r9 = " | mLowBatteryNotificationCancelled:"
            r8.append(r9)
            r8.append(r3)
            java.lang.String r3 = " -> false, mLowBatterySectionEntered:"
            r8.append(r3)
            r8.append(r6)
            java.lang.String r3 = " -> false, mSevereLowBatterySectionEntered:"
            r8.append(r3)
            r8.append(r7)
            java.lang.String r3 = " -> false"
            r8.append(r3)
            java.lang.String r3 = r8.toString()
            android.util.Log.d(r5, r3)
            r0.lowBatteryNotificationCancelled = r4
            r0.lowBatterySectionEntered = r4
            r0.severeLowBatterySectionEntered = r4
        L_0x0059:
            boolean r3 = r0.extremeLowBatterySectionEntered
            r6 = 2131952516(0x7f130384, float:1.9541477E38)
            java.lang.String r7 = "low_battery"
            com.google.android.systemui.power.ExtremeLowBatteryNotification r8 = r0.extremeLowNotification
            if (r3 == 0) goto L_0x0075
            r3 = 4
            if (r1 < r3) goto L_0x0075
            java.lang.String r3 = "reset section guard for extreme low. batteryLevel:"
            androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0.m(r3, r1, r5)
            r0.extremeLowBatterySectionEntered = r4
            android.app.NotificationManager r3 = r8.mNotificationManager
            android.os.UserHandle r9 = android.os.UserHandle.ALL
            r3.cancelAsUser(r7, r6, r9)
        L_0x0075:
            boolean r3 = r18.isEmpty()
            if (r3 == 0) goto L_0x007c
            return
        L_0x007c:
            com.google.android.systemui.power.batteryevent.aidl.BatteryEventType r3 = com.google.android.systemui.power.batteryevent.aidl.BatteryEventType.LOW_BATTERY
            boolean r3 = r2.contains(r3)
            java.lang.String r9 = "low_power_mode_reminder_enabled"
            com.android.systemui.util.settings.GlobalSettings r10 = r0.globalSettings
            r11 = 3
            com.google.android.systemui.power.LowBatteryNotification r12 = r0.lowBatteryNotification
            r13 = 1
            r14 = 17039674(0x104013a, float:2.424545E-38)
            android.os.PowerManager r15 = r0.powerManager
            if (r3 == 0) goto L_0x016b
            boolean r2 = r0.lowBatteryNotificationCancelled
            if (r2 == 0) goto L_0x009c
            java.lang.String r0 = "not showing notification -> notificationCanceled: true"
            android.util.Log.d(r5, r0)
            goto L_0x0272
        L_0x009c:
            int r2 = r10.getInt(r13, r9)
            if (r2 != 0) goto L_0x00a9
            java.lang.String r0 = "not showing notification -> isBatterySaverReminderDisabled: true"
            android.util.Log.d(r5, r0)
            goto L_0x0272
        L_0x00a9:
            boolean r2 = r16.isScheduledByPercentage()
            if (r2 == 0) goto L_0x00b6
            java.lang.String r0 = "not showing notification -> isScheduledByPercentage: true"
            android.util.Log.d(r5, r0)
            goto L_0x0272
        L_0x00b6:
            if (r15 == 0) goto L_0x00c5
            boolean r2 = r15.isPowerSaveMode()
            if (r2 != r13) goto L_0x00c5
            java.lang.String r0 = "not showing notification -> isPowerSaveMode: true"
            android.util.Log.d(r5, r0)
            goto L_0x0272
        L_0x00c5:
            boolean r2 = r0.lowBatterySectionEntered
            if (r2 != 0) goto L_0x00d4
            r0.lowBatterySectionEntered = r13
            com.android.systemui.power.BatteryWarningEvents$LowBatteryWarningEvent r2 = com.android.systemui.power.BatteryWarningEvents$LowBatteryWarningEvent.LOW_BATTERY_NOTIFICATION
            com.android.internal.logging.UiEventLogger r3 = r0.uiEventLogger
            r3.log(r2)
            r2 = r13
            goto L_0x00d5
        L_0x00d4:
            r2 = r4
        L_0x00d5:
            android.content.Context r0 = r0.context
            android.content.Context r0 = r0.getApplicationContext()
            android.content.ContentResolver r0 = r0.getContentResolver()
            java.lang.String r3 = "get_flipendo_state"
            r6 = 0
            java.lang.String r8 = "com.google.android.flipendo.api"
            android.os.Bundle r0 = r0.call(r8, r3, r6, r6)
            if (r0 != 0) goto L_0x00f0
            java.lang.String r0 = "contentResolver call Flipendo FLIPENDO_STATE_METHOD failed"
            android.util.Log.w(r5, r0)
            goto L_0x00f6
        L_0x00f0:
            java.lang.String r3 = "is_flipendo_aggressive"
            boolean r4 = r0.getBoolean(r3, r4)
        L_0x00f6:
            r12.getClass()
            java.text.NumberFormat r0 = java.text.NumberFormat.getPercentInstance()
            double r5 = (double) r1
            r8 = 4576918229304087675(0x3f847ae147ae147b, double:0.01)
            double r5 = r5 * r8
            java.lang.String r0 = r0.format(r5)
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            android.content.Context r1 = r12.mContext
            r3 = 2131952992(0x7f130560, float:1.9542442E38)
            java.lang.String r0 = r1.getString(r3, r0)
            if (r4 == 0) goto L_0x011b
            r3 = 2131952991(0x7f13055f, float:1.954244E38)
            goto L_0x011e
        L_0x011b:
            r3 = 2131952990(0x7f13055e, float:1.9542438E38)
        L_0x011e:
            java.lang.String r3 = r1.getString(r3)
            androidx.core.app.NotificationCompat$Builder r4 = new androidx.core.app.NotificationCompat$Builder
            r4.<init>(r1)
            android.app.Notification r5 = r4.mNotification
            r6 = 2131232967(0x7f0808c7, float:1.8082058E38)
            r5.icon = r6
            java.lang.CharSequence r3 = androidx.core.app.NotificationCompat$Builder.limitCharSequenceLength(r3)
            r4.mContentText = r3
            java.lang.CharSequence r0 = androidx.core.app.NotificationCompat$Builder.limitCharSequenceLength(r0)
            r4.mContentTitle = r0
            r0 = r2 ^ 1
            r2 = 8
            r4.setFlag(r2, r0)
            java.lang.String r0 = "PNW.dismissedWarning"
            android.app.PendingIntent r0 = com.google.android.systemui.power.PowerUtils.createPendingIntent(r1, r0)
            r5.deleteIntent = r0
            r4.mVisibility = r13
            r0 = 2131951971(0x7f130163, float:1.9540372E38)
            java.lang.String r0 = r1.getString(r0)
            java.lang.String r2 = "PNW.startSaver"
            android.app.PendingIntent r2 = com.google.android.systemui.power.PowerUtils.createPendingIntent(r1, r2)
            r4.addAction(r0, r2)
            com.google.android.systemui.power.PowerUtils.overrideNotificationAppName(r1, r4, r14)
            android.app.NotificationManager r0 = r12.mNotificationManager
            android.app.Notification r1 = r4.build()
            android.os.UserHandle r2 = android.os.UserHandle.ALL
            r0.notifyAsUser(r7, r11, r1, r2)
            goto L_0x0272
        L_0x016b:
            com.google.android.systemui.power.batteryevent.aidl.BatteryEventType r3 = com.google.android.systemui.power.batteryevent.aidl.BatteryEventType.SEVERE_LOW_BATTERY
            boolean r3 = r2.contains(r3)
            com.google.android.systemui.power.SevereLowBatteryDialog r4 = r0.severeLowBatteryDialog
            if (r3 == 0) goto L_0x0202
            int r2 = r10.getInt(r13, r9)
            if (r2 != 0) goto L_0x0182
            java.lang.String r0 = "isBatterySaverReminderDisabled: true"
            android.util.Log.d(r5, r0)
            goto L_0x0272
        L_0x0182:
            boolean r2 = r0.severeLowBatterySectionEntered
            if (r2 != 0) goto L_0x0272
            r0.severeLowBatterySectionEntered = r13
            boolean r2 = r16.isScheduledByPercentage()
            if (r2 != 0) goto L_0x0199
            if (r15 == 0) goto L_0x0197
            boolean r2 = r15.isPowerSaveMode()
            if (r2 == 0) goto L_0x0197
            goto L_0x0199
        L_0x0197:
            r2 = 0
            goto L_0x019a
        L_0x0199:
            r2 = r13
        L_0x019a:
            android.app.NotificationManager r3 = r12.mNotificationManager
            android.os.UserHandle r5 = android.os.UserHandle.ALL
            r3.cancelAsUser(r7, r11, r5)
            r0.lowBatteryNotificationCancelled = r13
            com.android.systemui.statusbar.phone.SystemUIDialog r0 = r4.mSevereBatteryDialog
            java.lang.String r3 = "SevereLowBatteryDialog"
            if (r0 == 0) goto L_0x01b6
            boolean r0 = r0.isShowing()
            if (r0 == 0) goto L_0x01b6
            java.lang.String r0 = "showSevereBatteryDialog: already showing"
            android.util.Log.d(r3, r0)
            goto L_0x0272
        L_0x01b6:
            com.android.systemui.statusbar.policy.KeyguardStateController r0 = r4.mKeyguardStateController
            if (r0 == 0) goto L_0x01c0
            com.android.systemui.statusbar.policy.KeyguardStateControllerImpl r0 = (com.android.systemui.statusbar.policy.KeyguardStateControllerImpl) r0
            boolean r0 = r0.mShowing
            if (r0 != 0) goto L_0x01d9
        L_0x01c0:
            java.lang.Class<android.os.PowerManager> r0 = android.os.PowerManager.class
            android.content.Context r5 = r4.mContext
            java.lang.Object r0 = r5.getSystemService(r0)
            android.os.PowerManager r0 = (android.os.PowerManager) r0
            boolean r0 = r0.isInteractive()
            if (r0 != 0) goto L_0x01d1
            goto L_0x01d9
        L_0x01d1:
            android.service.dreams.IDreamManager r0 = r4.mIDreamManager     // Catch:{ RemoteException -> 0x01e0 }
            boolean r0 = r0.isDreaming()     // Catch:{ RemoteException -> 0x01e0 }
            if (r0 == 0) goto L_0x01e6
        L_0x01d9:
            java.lang.String r0 = "showSevereBatteryDialog: device is not active"
            android.util.Log.d(r3, r0)
            goto L_0x0272
        L_0x01e0:
            r0 = move-exception
            java.lang.String r6 = "mIDreamManager.isDreaming()"
            android.util.Log.e(r3, r6, r0)
        L_0x01e6:
            android.content.ContentResolver r0 = r5.getContentResolver()
            boolean r0 = com.google.android.systemui.power.PowerUtils.isFlipendoEnabled(r0)
            if (r0 == 0) goto L_0x01f7
            java.lang.String r0 = "showSevereBatteryDialog: EBS is enabled"
            android.util.Log.d(r3, r0)
            goto L_0x0272
        L_0x01f7:
            android.os.Handler r0 = r4.mHandler
            com.google.android.systemui.power.SevereLowBatteryDialog$$ExternalSyntheticLambda0 r3 = new com.google.android.systemui.power.SevereLowBatteryDialog$$ExternalSyntheticLambda0
            r3.<init>(r4, r1, r2)
            r0.post(r3)
            goto L_0x0272
        L_0x0202:
            com.google.android.systemui.power.batteryevent.aidl.BatteryEventType r1 = com.google.android.systemui.power.batteryevent.aidl.BatteryEventType.EXTREME_LOW_BATTERY
            boolean r1 = r2.contains(r1)
            if (r1 == 0) goto L_0x0272
            boolean r1 = r0.extremeLowBatterySectionEntered
            if (r1 != 0) goto L_0x0272
            r0.extremeLowBatterySectionEntered = r13
            android.app.NotificationManager r1 = r12.mNotificationManager
            android.os.UserHandle r2 = android.os.UserHandle.ALL
            r1.cancelAsUser(r7, r11, r2)
            r0.lowBatteryNotificationCancelled = r13
            com.android.systemui.statusbar.phone.SystemUIDialog r0 = r4.mSevereBatteryDialog
            if (r0 == 0) goto L_0x0228
            boolean r0 = r0.isShowing()
            if (r0 == 0) goto L_0x0228
            com.android.systemui.statusbar.phone.SystemUIDialog r0 = r4.mSevereBatteryDialog
            r0.dismiss()
        L_0x0228:
            android.content.Context r0 = r8.mContext
            java.lang.String r1 = r0.getString(r6)
            r3 = 2131952515(0x7f130383, float:1.9541475E38)
            java.lang.String r3 = r0.getString(r3)
            androidx.core.app.NotificationCompat$Builder r4 = new androidx.core.app.NotificationCompat$Builder
            r4.<init>(r0)
            android.app.Notification r5 = r4.mNotification
            r9 = 2131232459(0x7f0806cb, float:1.8081028E38)
            r5.icon = r9
            androidx.core.app.NotificationCompat$BigTextStyle r5 = new androidx.core.app.NotificationCompat$BigTextStyle
            r5.<init>()
            java.lang.CharSequence r9 = androidx.core.app.NotificationCompat$Builder.limitCharSequenceLength(r3)
            r5.mBigText = r9
            r4.setStyle(r5)
            java.lang.CharSequence r3 = androidx.core.app.NotificationCompat$Builder.limitCharSequenceLength(r3)
            r4.mContentText = r3
            java.lang.CharSequence r1 = androidx.core.app.NotificationCompat$Builder.limitCharSequenceLength(r1)
            r4.mContentTitle = r1
            r4.mVisibility = r13
            com.google.android.systemui.power.PowerUtils.overrideNotificationAppName(r0, r4, r14)
            android.app.NotificationManager r0 = r8.mNotificationManager
            android.app.Notification r1 = r4.build()
            r0.notifyAsUser(r7, r6, r1, r2)
            com.android.internal.logging.UiEventLogger r0 = r8.mUiEventLogger
            if (r0 == 0) goto L_0x0272
            com.google.android.systemui.power.BatteryMetricEvent r1 = com.google.android.systemui.power.BatteryMetricEvent.EXTREME_LOW_BATTERY_NOTIFICATION
            r0.log(r1)
        L_0x0272:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.power.LowPowerWarningsController.onBatteryEventUpdate(int, java.util.List):void");
    }
}
