package com.google.android.systemui.power;

import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import com.android.internal.logging.UiEventLogger;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class IncompatibleChargerNotification {
    public static final String KEY_COMPATIBLE_CHARGER_TIME = "last_compatible_charger_time";
    public static final String KEY_INCOMPATIBLE_CHARGER_TIME = "last_incompatible_charger_time";
    public boolean mContainsIncompatibleChargers = false;
    public final Context mContext;
    public final Handler mMainHandler = new Handler(Looper.getMainLooper());
    public boolean mNotificationActive = false;
    public final NotificationManager mNotificationManager;
    public final UiEventLogger mUiEventLogger;

    public IncompatibleChargerNotification(Context context, UiEventLogger uiEventLogger) {
        this.mContext = context;
        this.mUiEventLogger = uiEventLogger;
        this.mNotificationManager = (NotificationManager) context.getSystemService(NotificationManager.class);
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getApplicationContext().getSharedPreferences("incompatible_charger_shared_prefs", 0);
    }
}
