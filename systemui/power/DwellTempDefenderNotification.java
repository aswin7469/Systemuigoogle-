package com.google.android.systemui.power;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.UserHandle;
import android.util.Log;
import androidx.core.app.NotificationCompat$Builder;
import com.android.internal.logging.UiEventLogger;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class DwellTempDefenderNotification {
    public final Context context;
    public boolean lastPlugged;
    public final NotificationManager notificationManager;
    public boolean notificationVisible;
    public boolean postNotificationVisible;
    public final UiEventLogger uiEventLogger;

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
            notificationManager2.cancelAsUser("battery_defender", 2131952410, UserHandle.ALL);
        }
    }

    public final void cancelPostNotification() {
        boolean z = this.postNotificationVisible;
        Log.d("DwellTempDefenderNotification", "swipeNotificationByUser, postNotificationVisible:" + z + "->false");
        this.postNotificationVisible = false;
        NotificationManager notificationManager2 = this.notificationManager;
        if (notificationManager2 != null) {
            notificationManager2.cancelAsUser("battery_defender", 2131952412, UserHandle.ALL);
        }
    }

    public final void sendDefenderNotification(boolean z) {
        Log.d("DwellTempDefenderNotification", "sendDefenderNotification, plugged:" + z);
        Context context2 = this.context;
        NotificationCompat$Builder notificationCompat$Builder = new NotificationCompat$Builder(context2);
        Notification notification = notificationCompat$Builder.mNotification;
        notification.icon = 2131232461;
        notificationCompat$Builder.mContentTitle = NotificationCompat$Builder.limitCharSequenceLength(context2.getString(2131952410));
        notificationCompat$Builder.mContentText = NotificationCompat$Builder.limitCharSequenceLength(context2.getString(2131952407));
        notificationCompat$Builder.mContentIntent = PowerUtils.createBatterySettingsPendingIntent(context2);
        notificationCompat$Builder.mSilent = true;
        notification.deleteIntent = PowerUtils.createPendingIntent(context2, "systemui.power.action.dismissBatteryDefenderWarning");
        notificationCompat$Builder.addAction(context2.getString(2131951951), PowerUtils.createHelpArticlePendingIntent(2131952408, context2));
        if (z) {
            notificationCompat$Builder.addAction(context2.getString(2131952409), PowerUtils.createPendingIntent(context2, "PNW.defenderResumeCharging"));
        }
        PowerUtils.overrideNotificationAppName(context2, notificationCompat$Builder, 17040952);
        NotificationManager notificationManager2 = this.notificationManager;
        if (notificationManager2 != null) {
            notificationManager2.notifyAsUser("battery_defender", 2131952410, notificationCompat$Builder.build(), UserHandle.ALL);
        }
    }
}
