package com.google.android.systemui.power;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.UserHandle;
import android.provider.Settings;
import android.util.Log;
import androidx.core.app.NotificationCompat$Builder;
import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0;
import com.android.internal.logging.UiEventLogger;
import com.android.keyguard.logging.BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0;
import com.android.systemui.power.BatteryWarningEvents$LowBatteryWarningEvent;
import com.android.systemui.util.settings.GlobalSettings;
import com.google.android.systemui.power.batteryevent.aidl.BatteryEventType;
import java.text.NumberFormat;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.collections.EmptyList;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
    public final SevereLowBatteryNotification severeLowBatteryNotification;
    public boolean severeLowBatteryNotificationCancelled;
    public boolean severeLowBatterySectionEntered;
    public final UiEventLogger uiEventLogger;

    public LowPowerWarningsController(Context context2, SevereLowBatteryNotification severeLowBatteryNotification2, GlobalSettings globalSettings2, UiEventLogger uiEventLogger2, Executor executor2) {
        LowBatteryNotification lowBatteryNotification2 = new LowBatteryNotification(context2);
        ExtremeLowBatteryNotification extremeLowBatteryNotification = new ExtremeLowBatteryNotification(context2, uiEventLogger2);
        this.context = context2;
        this.severeLowBatteryNotification = severeLowBatteryNotification2;
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
            SevereLowBatteryNotification severeLowBatteryNotification2 = this.severeLowBatteryNotification;
            severeLowBatteryNotification2.getClass();
            Log.d("SevereLowBatteryNotification", "cancel()");
            ((NotificationManager) severeLowBatteryNotification2.notificationManager$delegate.getValue()).cancelAsUser("low_battery", 3, UserHandle.ALL);
            this.severeLowBatteryNotificationCancelled = true;
        }
        if (this.extremeLowBatterySectionEntered) {
            Log.d("LowPowerWarningsController", "cancelNotification->extremeLowBatterySection");
            this.extremeLowNotification.mNotificationManager.cancelAsUser("low_battery", 2131952541, UserHandle.ALL);
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
    /* JADX WARNING: type inference failed for: r1v21, types: [java.lang.Object, androidx.core.app.NotificationCompat$BigTextStyle] */
    public final void onBatteryEventUpdate(int i, List list) {
        boolean z;
        boolean z2;
        int i2;
        int i3;
        boolean z3;
        int i4;
        int i5 = i;
        List list2 = list;
        this.prevBatteryLevel = Integer.valueOf(i);
        this.prevBatteryEventTypes = list2;
        boolean z4 = this.lowBatterySectionEntered;
        boolean z5 = false;
        if ((z4 || this.lowBatteryNotificationCancelled || this.severeLowBatterySectionEntered || this.severeLowBatteryNotificationCancelled) && i5 >= 30) {
            boolean z6 = this.lowBatteryNotificationCancelled;
            boolean z7 = this.severeLowBatterySectionEntered;
            boolean z8 = this.severeLowBatteryNotificationCancelled;
            StringBuilder sb = new StringBuilder("reset section guard for low/severe low. batteryLevel:");
            sb.append(i5);
            sb.append(" | lowBatterySectionEntered:");
            sb.append(z4);
            sb.append(" -> false, lowBatteryNotificationCancelled:");
            BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0.m(sb, z6, " -> false, severeLowBatterySectionEntered:", z7, " -> falsesevereLowBatteryNotificationCancelled:");
            sb.append(z8);
            sb.append(" -> false");
            Log.d("LowPowerWarningsController", sb.toString());
            this.lowBatterySectionEntered = false;
            this.lowBatteryNotificationCancelled = false;
            this.severeLowBatterySectionEntered = false;
            this.severeLowBatteryNotificationCancelled = false;
        }
        boolean z9 = this.extremeLowBatterySectionEntered;
        ExtremeLowBatteryNotification extremeLowBatteryNotification = this.extremeLowNotification;
        if (z9 && i5 >= 4) {
            ExifInterface$$ExternalSyntheticOutline0.m("reset section guard for extreme low. batteryLevel:", "LowPowerWarningsController", i5);
            this.extremeLowBatterySectionEntered = false;
            extremeLowBatteryNotification.mNotificationManager.cancelAsUser("low_battery", 2131952541, UserHandle.ALL);
        }
        if (!list.isEmpty()) {
            boolean contains = list2.contains(BatteryEventType.LOW_BATTERY);
            GlobalSettings globalSettings2 = this.globalSettings;
            LowBatteryNotification lowBatteryNotification2 = this.lowBatteryNotification;
            Context context2 = this.context;
            PowerManager powerManager2 = this.powerManager;
            if (!contains) {
                boolean contains2 = list2.contains(BatteryEventType.SEVERE_LOW_BATTERY);
                SevereLowBatteryNotification severeLowBatteryNotification2 = this.severeLowBatteryNotification;
                if (contains2) {
                    if (this.severeLowBatteryNotificationCancelled) {
                        Log.d("LowPowerWarningsController", "notification has been canceled, skip showing notification");
                    } else if (globalSettings2.getInt(1, "low_power_mode_reminder_enabled") == 0) {
                        Log.d("LowPowerWarningsController", "battery saver reminder has been disabled, skip showing notification");
                    } else if (PowerUtils.isFlipendoEnabled(context2.getContentResolver())) {
                        Log.d("LowPowerWarningsController", "EBS has been enabled, skip showing notification");
                    } else {
                        if (!this.severeLowBatterySectionEntered) {
                            this.severeLowBatterySectionEntered = true;
                            lowBatteryNotification2.mNotificationManager.cancelAsUser("low_battery", 3, UserHandle.ALL);
                            this.lowBatteryNotificationCancelled = true;
                            z = false;
                        } else {
                            z = true;
                        }
                        if (isScheduledByPercentage() || (powerManager2 != null && powerManager2.isPowerSaveMode())) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        severeLowBatteryNotification2.getClass();
                        Log.d("SevereLowBatteryNotification", "show() batteryLevel:" + i5 + ", scheduled:" + z2 + ", subsequenceEvent:" + z);
                        Context context3 = severeLowBatteryNotification2.context;
                        String string = context3.getString(2131953867, new Object[]{NumberFormat.getPercentInstance().format(((double) i5) * 0.01d)});
                        if (z2) {
                            i2 = 2131953865;
                        } else {
                            i2 = 2131953866;
                        }
                        String string2 = context3.getString(i2);
                        if (z2) {
                            i3 = 2131953868;
                        } else {
                            i3 = 2131951981;
                        }
                        PendingIntent createPendingIntent = PowerUtils.createPendingIntent(context3, "systemui.power.action.START_FLIPENDO");
                        NotificationCompat$Builder notificationCompat$Builder = new NotificationCompat$Builder(context3);
                        notificationCompat$Builder.mNotification.icon = 2131233010;
                        notificationCompat$Builder.mContentTitle = NotificationCompat$Builder.limitCharSequenceLength(string);
                        notificationCompat$Builder.mContentText = NotificationCompat$Builder.limitCharSequenceLength(string2);
                        ? obj = new Object();
                        obj.mBigText = NotificationCompat$Builder.limitCharSequenceLength(string2);
                        notificationCompat$Builder.setStyle(obj);
                        notificationCompat$Builder.mNotification.deleteIntent = PowerUtils.createPendingIntent(context3, "PNW.dismissedWarning");
                        notificationCompat$Builder.mVisibility = 1;
                        notificationCompat$Builder.addAction(context3.getString(i3), createPendingIntent);
                        notificationCompat$Builder.mLocalOnly = true;
                        if (z) {
                            notificationCompat$Builder.setFlag(8, true);
                        }
                        PowerUtils.overrideNotificationAppName(context3, notificationCompat$Builder);
                        ((NotificationManager) severeLowBatteryNotification2.notificationManager$delegate.getValue()).notifyAsUser("low_battery", 3, notificationCompat$Builder.build(), UserHandle.ALL);
                        severeLowBatteryNotification2.uiEventLogger.log(BatteryMetricEvent.SEVERE_BATTERY_DIALOG);
                    }
                } else if (list2.contains(BatteryEventType.EXTREME_LOW_BATTERY) && !this.extremeLowBatterySectionEntered) {
                    this.extremeLowBatterySectionEntered = true;
                    NotificationManager notificationManager = lowBatteryNotification2.mNotificationManager;
                    UserHandle userHandle = UserHandle.ALL;
                    notificationManager.cancelAsUser("low_battery", 3, userHandle);
                    this.lowBatteryNotificationCancelled = true;
                    severeLowBatteryNotification2.getClass();
                    Log.d("SevereLowBatteryNotification", "cancel()");
                    ((NotificationManager) severeLowBatteryNotification2.notificationManager$delegate.getValue()).cancelAsUser("low_battery", 3, userHandle);
                    this.severeLowBatteryNotificationCancelled = true;
                    Context context4 = extremeLowBatteryNotification.mContext;
                    String string3 = context4.getString(2131952541);
                    String string4 = context4.getString(2131952540);
                    NotificationCompat$Builder notificationCompat$Builder2 = new NotificationCompat$Builder(context4);
                    notificationCompat$Builder2.mNotification.icon = 2131232473;
                    ? obj2 = new Object();
                    obj2.mBigText = NotificationCompat$Builder.limitCharSequenceLength(string4);
                    notificationCompat$Builder2.setStyle(obj2);
                    notificationCompat$Builder2.mContentText = NotificationCompat$Builder.limitCharSequenceLength(string4);
                    notificationCompat$Builder2.mContentTitle = NotificationCompat$Builder.limitCharSequenceLength(string3);
                    notificationCompat$Builder2.mVisibility = 1;
                    PowerUtils.overrideNotificationAppName(context4, notificationCompat$Builder2);
                    extremeLowBatteryNotification.mNotificationManager.notifyAsUser("low_battery", 2131952541, notificationCompat$Builder2.build(), userHandle);
                    UiEventLogger uiEventLogger2 = extremeLowBatteryNotification.mUiEventLogger;
                    if (uiEventLogger2 != null) {
                        uiEventLogger2.log(BatteryMetricEvent.EXTREME_LOW_BATTERY_NOTIFICATION);
                    }
                }
            } else if (this.lowBatteryNotificationCancelled) {
                Log.d("LowPowerWarningsController", "not showing notification -> notificationCanceled: true");
            } else if (globalSettings2.getInt(1, "low_power_mode_reminder_enabled") == 0) {
                Log.d("LowPowerWarningsController", "not showing notification -> isBatterySaverReminderDisabled: true");
            } else if (isScheduledByPercentage()) {
                Log.d("LowPowerWarningsController", "not showing notification -> isScheduledByPercentage: true");
            } else if (powerManager2 == null || !powerManager2.isPowerSaveMode()) {
                if (!this.lowBatterySectionEntered) {
                    this.lowBatterySectionEntered = true;
                    this.uiEventLogger.log(BatteryWarningEvents$LowBatteryWarningEvent.LOW_BATTERY_NOTIFICATION);
                    z3 = true;
                } else {
                    z3 = false;
                }
                Bundle call = context2.getApplicationContext().getContentResolver().call("com.google.android.flipendo.api", "get_flipendo_state", (String) null, (Bundle) null);
                if (call == null) {
                    Log.w("LowPowerWarningsController", "contentResolver call Flipendo FLIPENDO_STATE_METHOD failed");
                } else {
                    z5 = call.getBoolean("is_flipendo_aggressive", false);
                }
                lowBatteryNotification2.getClass();
                Context context5 = lowBatteryNotification2.mContext;
                String string5 = context5.getString(2131953028, new Object[]{NumberFormat.getPercentInstance().format(((double) i5) * 0.01d)});
                if (z5) {
                    i4 = 2131953027;
                } else {
                    i4 = 2131953026;
                }
                String string6 = context5.getString(i4);
                NotificationCompat$Builder notificationCompat$Builder3 = new NotificationCompat$Builder(context5);
                notificationCompat$Builder3.mNotification.icon = 2131233010;
                notificationCompat$Builder3.mContentText = NotificationCompat$Builder.limitCharSequenceLength(string6);
                notificationCompat$Builder3.mContentTitle = NotificationCompat$Builder.limitCharSequenceLength(string5);
                notificationCompat$Builder3.setFlag(8, !z3);
                notificationCompat$Builder3.mNotification.deleteIntent = PowerUtils.createPendingIntent(context5, "PNW.dismissedWarning");
                notificationCompat$Builder3.mVisibility = 1;
                notificationCompat$Builder3.addAction(context5.getString(2131951981), PowerUtils.createPendingIntent(context5, "PNW.startSaver"));
                notificationCompat$Builder3.mLocalOnly = true;
                PowerUtils.overrideNotificationAppName(context5, notificationCompat$Builder3);
                lowBatteryNotification2.mNotificationManager.notifyAsUser("low_battery", 3, notificationCompat$Builder3.build(), UserHandle.ALL);
            } else {
                Log.d("LowPowerWarningsController", "not showing notification -> isPowerSaveMode: true");
            }
        }
    }
}
