package com.google.android.systemui.power;

import android.app.NotificationManager;
import android.content.Context;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class LowBatteryNotification {
    public final Context mContext;
    NotificationManager mNotificationManager;

    public LowBatteryNotification(Context context) {
        this.mContext = context;
        this.mNotificationManager = (NotificationManager) context.getSystemService(NotificationManager.class);
    }
}
