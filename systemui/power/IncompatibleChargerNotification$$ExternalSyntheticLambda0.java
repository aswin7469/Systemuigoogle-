package com.google.android.systemui.power;

import android.app.NotificationManager;
import android.content.Context;
import android.os.UserHandle;
import android.util.Log;
import androidx.core.app.NotificationCompat$Builder;
import com.android.internal.logging.UiEventLogger;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class IncompatibleChargerNotification$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ IncompatibleChargerNotification f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ IncompatibleChargerNotification$$ExternalSyntheticLambda0(IncompatibleChargerNotification incompatibleChargerNotification, boolean z) {
        this.f$0 = incompatibleChargerNotification;
        this.f$1 = z;
    }

    /* JADX WARNING: type inference failed for: r6v9, types: [java.lang.Object, androidx.core.app.NotificationCompat$BigTextStyle] */
    public final void run() {
        IncompatibleChargerNotification incompatibleChargerNotification = this.f$0;
        boolean z = this.f$1;
        incompatibleChargerNotification.getClass();
        Log.d("IncompatibleChargerNotification", "[updateNotification] showNotification: " + z);
        NotificationManager notificationManager = incompatibleChargerNotification.mNotificationManager;
        if (z) {
            Log.d("IncompatibleChargerNotification", "[sendNotification] isNotificationActive: " + incompatibleChargerNotification.mNotificationActive + " -> true");
            Context context = incompatibleChargerNotification.mContext;
            NotificationCompat$Builder notificationCompat$Builder = new NotificationCompat$Builder(context);
            notificationCompat$Builder.mNotification.icon = 2131232474;
            notificationCompat$Builder.mShowWhen = false;
            notificationCompat$Builder.mContentTitle = NotificationCompat$Builder.limitCharSequenceLength(context.getString(2131952703));
            notificationCompat$Builder.mContentText = NotificationCompat$Builder.limitCharSequenceLength(context.getString(2131952702));
            ? obj = new Object();
            obj.mBigText = NotificationCompat$Builder.limitCharSequenceLength(context.getString(2131952702));
            notificationCompat$Builder.setStyle(obj);
            notificationCompat$Builder.mNotification.deleteIntent = PowerUtils.createPendingIntent(context, "systemui.power.action.dismissIncompatibleChargerWarning");
            notificationCompat$Builder.mContentIntent = PowerUtils.createBatterySettingsPendingIntent(context);
            notificationCompat$Builder.addAction(context.getString(2131952701), PowerUtils.createHelpArticlePendingIntent(2131952700, context));
            notificationCompat$Builder.mLocalOnly = true;
            PowerUtils.overrideNotificationAppName(context, notificationCompat$Builder);
            notificationManager.notifyAsUser("incompatible_charging", 2131952703, notificationCompat$Builder.build(), UserHandle.ALL);
            incompatibleChargerNotification.mNotificationActive = true;
            BatteryMetricEvent batteryMetricEvent = BatteryMetricEvent.SEND_INCOMPATIBLE_CHARGING_NOTIFICATION;
            UiEventLogger uiEventLogger = incompatibleChargerNotification.mUiEventLogger;
            if (uiEventLogger != null) {
                uiEventLogger.log(batteryMetricEvent);
                return;
            }
            return;
        }
        Log.d("IncompatibleChargerNotification", "[cancelNotification] isNotificationActive: " + incompatibleChargerNotification.mNotificationActive + " -> false");
        if (incompatibleChargerNotification.mNotificationActive) {
            notificationManager.cancelAsUser("incompatible_charging", 2131952703, UserHandle.ALL);
            incompatibleChargerNotification.mNotificationActive = false;
        }
    }
}
