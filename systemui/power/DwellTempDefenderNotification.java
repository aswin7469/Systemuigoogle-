package com.google.android.systemui.power;

import android.app.NotificationManager;
import android.content.Context;
import android.os.UserHandle;
import android.util.Log;
import androidx.core.app.NotificationCompat$Builder;
import com.android.internal.logging.UiEventLogger;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntriesKt;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class DwellTempDefenderNotification {
    public final Context context;
    public boolean lastPlugged;
    public final NotificationManager notificationManager;
    public boolean notificationVisible;
    public boolean postNotificationVisible;
    public final UiEventLogger uiEventLogger;

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public enum BatteryDefendType {
        ;

        /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Enum, com.google.android.systemui.power.DwellTempDefenderNotification$BatteryDefendType] */
        /* JADX WARNING: type inference failed for: r1v1, types: [java.lang.Enum, com.google.android.systemui.power.DwellTempDefenderNotification$BatteryDefendType] */
        static {
            BatteryDefendType[] batteryDefendTypeArr;
            EnumEntriesKt.enumEntries(batteryDefendTypeArr);
        }
    }

    public DwellTempDefenderNotification(Context context2, NotificationManager notificationManager2, UiEventLogger uiEventLogger2) {
        this.context = context2;
        this.notificationManager = notificationManager2;
        this.uiEventLogger = uiEventLogger2;
    }

    public final void cancelNotification() {
        boolean z = this.notificationVisible;
        Log.d("DwellTempDefenderNotification", "cancelNotification, notificationVisible:" + z + "->false");
        this.notificationVisible = false;
        NotificationManager notificationManager2 = this.notificationManager;
        if (notificationManager2 != null) {
            notificationManager2.cancelAsUser("battery_defender", 2131952430, UserHandle.ALL);
        }
    }

    public final void cancelPostNotification() {
        boolean z = this.postNotificationVisible;
        Log.d("DwellTempDefenderNotification", "swipeNotificationByUser, postNotificationVisible:" + z + "->false");
        this.postNotificationVisible = false;
        NotificationManager notificationManager2 = this.notificationManager;
        if (notificationManager2 != null) {
            notificationManager2.cancelAsUser("battery_defender", 2131952432, UserHandle.ALL);
        }
    }

    public final void sendDefenderNotification(boolean z, BatteryDefendType batteryDefendType) {
        int i;
        int i2;
        Log.d("DwellTempDefenderNotification", "sendDefenderNotification, plugged:" + z + ", type: " + batteryDefendType);
        int ordinal = batteryDefendType.ordinal();
        if (ordinal == 0) {
            i = 2131953988;
        } else if (ordinal == 1) {
            i = 2131952501;
        } else {
            throw new NoWhenBranchMatchedException();
        }
        Context context2 = this.context;
        String string = context2.getString(i);
        int ordinal2 = batteryDefendType.ordinal();
        if (ordinal2 == 0) {
            i2 = 2131953987;
        } else if (ordinal2 == 1) {
            i2 = 2131952500;
        } else {
            throw new NoWhenBranchMatchedException();
        }
        String string2 = context2.getString(i2);
        NotificationCompat$Builder notificationCompat$Builder = new NotificationCompat$Builder(context2);
        notificationCompat$Builder.mNotification.icon = 2131232477;
        notificationCompat$Builder.mContentTitle = NotificationCompat$Builder.limitCharSequenceLength(string);
        notificationCompat$Builder.mContentText = NotificationCompat$Builder.limitCharSequenceLength(string2);
        notificationCompat$Builder.mContentIntent = PowerUtils.createBatterySettingsPendingIntent(context2);
        notificationCompat$Builder.mSilent = true;
        notificationCompat$Builder.mNotification.deleteIntent = PowerUtils.createPendingIntent(context2, "systemui.power.action.dismissBatteryDefenderWarning");
        notificationCompat$Builder.addAction(context2.getString(2131951960), PowerUtils.createHelpArticlePendingIntent(2131952428, context2));
        notificationCompat$Builder.mLocalOnly = true;
        if (z) {
            notificationCompat$Builder.addAction(context2.getString(2131952429), PowerUtils.createPendingIntent(context2, "PNW.defenderResumeCharging"));
        }
        PowerUtils.overrideNotificationAppName(context2, notificationCompat$Builder);
        NotificationManager notificationManager2 = this.notificationManager;
        if (notificationManager2 != null) {
            notificationManager2.notifyAsUser("battery_defender", 2131952430, notificationCompat$Builder.build(), UserHandle.ALL);
        }
    }
}
