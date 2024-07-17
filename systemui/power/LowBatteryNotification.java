package com.google.android.systemui.power;

import android.app.NotificationManager;
import android.content.Context;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class LowBatteryNotification {
    public final Context mContext;
    NotificationManager mNotificationManager;

    public LowBatteryNotification(Context context) {
        this.mContext = context;
        this.mNotificationManager = (NotificationManager) context.getSystemService(NotificationManager.class);
    }
}
