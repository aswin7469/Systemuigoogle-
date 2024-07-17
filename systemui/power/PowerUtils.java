package com.google.android.systemui.power;

import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.LocaleList;
import android.os.UserHandle;
import android.util.Log;
import androidx.core.app.NotificationCompat$Builder;
import java.util.Locale;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class PowerUtils {
    public static PendingIntent createBatterySettingsPendingIntent(Context context) {
        return PendingIntent.getActivity(context, 0, new Intent("android.intent.action.POWER_USAGE_SUMMARY"), 67108864);
    }

    public static PendingIntent createHelpArticlePendingIntent(int i, Context context) {
        return PendingIntent.getActivity(context, 0, new Intent("android.intent.action.VIEW", Uri.parse(context.getString(i))), 67108864);
    }

    public static PendingIntent createPendingIntent(Context context, String str) {
        return PendingIntent.getBroadcastAsUser(context, 0, new Intent(str).setPackage(context.getPackageName()).setFlags(1342177280), 67108864, UserHandle.CURRENT);
    }

    public static Locale getLocale(Context context) {
        LocaleList locales = context.getResources().getConfiguration().getLocales();
        if (locales == null || locales.isEmpty()) {
            return Locale.getDefault();
        }
        return locales.get(0);
    }

    public static boolean isFlipendoEnabled(ContentResolver contentResolver) {
        try {
            Bundle call = contentResolver.call("com.google.android.flipendo.api", "get_flipendo_state", (String) null, new Bundle());
            if (call == null || !call.getBoolean("flipendo_state", false)) {
                return false;
            }
            return true;
        } catch (Exception e) {
            Log.e("PowerUtils", "isFlipendoEnabled() failed", e);
            return false;
        }
    }

    public static void overrideNotificationAppName(Context context, NotificationCompat$Builder notificationCompat$Builder) {
        Bundle bundle = new Bundle();
        bundle.putString("android.substName", context.getString(17039674));
        Bundle bundle2 = notificationCompat$Builder.mExtras;
        if (bundle2 == null) {
            notificationCompat$Builder.mExtras = new Bundle(bundle);
        } else {
            bundle2.putAll(bundle);
        }
    }
}
