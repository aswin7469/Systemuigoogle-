package com.google.android.systemui.power;

import android.app.NotificationManager;
import android.content.Context;
import com.android.internal.logging.UiEventLogger;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ExtremeLowBatteryNotification {
    public final Context mContext;
    NotificationManager mNotificationManager;
    public final UiEventLogger mUiEventLogger;

    public ExtremeLowBatteryNotification(Context context, UiEventLogger uiEventLogger) {
        this.mContext = context;
        this.mUiEventLogger = uiEventLogger;
        this.mNotificationManager = (NotificationManager) context.getSystemService(NotificationManager.class);
    }
}
