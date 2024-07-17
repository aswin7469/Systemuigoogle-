package com.google.android.systemui.power;

import android.app.Notification;
import android.content.Context;
import android.os.UserHandle;
import androidx.core.app.NotificationCompat$Builder;
import com.google.android.systemui.googlebattery.AdaptiveChargingManager;
import com.google.android.systemui.power.AdaptiveChargingNotification;
import java.util.concurrent.TimeUnit;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class AdaptiveChargingNotification$1$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ AdaptiveChargingNotification.AnonymousClass1 f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ boolean f$3;

    public /* synthetic */ AdaptiveChargingNotification$1$$ExternalSyntheticLambda0(AdaptiveChargingNotification.AnonymousClass1 r1, String str, int i, boolean z) {
        this.f$0 = r1;
        this.f$1 = str;
        this.f$2 = i;
        this.f$3 = z;
    }

    public final void run() {
        AdaptiveChargingNotification.AnonymousClass1 r0 = this.f$0;
        String str = this.f$1;
        int i = this.f$2;
        boolean z = this.f$3;
        AdaptiveChargingNotification adaptiveChargingNotification = AdaptiveChargingNotification.this;
        adaptiveChargingNotification.getClass();
        boolean z2 = AdaptiveChargingManager.DEBUG;
        if ((!"Active".equals(str) && !"Enabled".equals(str)) || i <= 0) {
            adaptiveChargingNotification.cancelNotification();
        } else if (!adaptiveChargingNotification.mWasActive || z) {
            long currentTimeMillis = System.currentTimeMillis();
            String formatTimeToFull = adaptiveChargingNotification.mAdaptiveChargingManager.formatTimeToFull(TimeUnit.SECONDS.toMillis((long) (i + 29)) + currentTimeMillis);
            Context context = adaptiveChargingNotification.mContext;
            NotificationCompat$Builder notificationCompat$Builder = new NotificationCompat$Builder(context);
            notificationCompat$Builder.mShowWhen = false;
            notificationCompat$Builder.mSilent = true;
            Notification notification = notificationCompat$Builder.mNotification;
            notification.icon = 2131232458;
            notificationCompat$Builder.mContentTitle = NotificationCompat$Builder.limitCharSequenceLength(context.getString(2131951869));
            notificationCompat$Builder.mContentText = NotificationCompat$Builder.limitCharSequenceLength(context.getString(2131951867, new Object[]{formatTimeToFull}));
            notificationCompat$Builder.addAction(context.getString(2131951870), PowerUtils.createPendingIntent(context, "PNW.acChargeNormally"));
            notification.deleteIntent = PowerUtils.createPendingIntent(context, "systemui.power.action.dismissAdaptiveChargingWarning");
            PowerUtils.overrideNotificationAppName(context, notificationCompat$Builder, 17039674);
            adaptiveChargingNotification.mNotificationManager.notifyAsUser("adaptive_charging", 2131951869, notificationCompat$Builder.build(), UserHandle.ALL);
            adaptiveChargingNotification.mUiEventLogger.log(BatteryMetricEvent.ADAPTIVE_CHARGING_NOTIFICATION);
            adaptiveChargingNotification.mWasActive = true;
        }
    }
}
