package com.google.android.systemui.power;

import android.app.NotificationManager;
import android.content.Context;
import com.android.internal.logging.UiEventLogger;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
