package com.google.android.systemui.power;

import android.net.Uri;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class DumpUtils {
    public static final Uri PROVIDER_URI = new Uri.Builder().scheme("content").authority("com.google.android.apps.turbo.battery_health_provider").build();

    public static String toReadableDateTime(long j) {
        return new SimpleDateFormat("MMM dd,yyyy HH:mm:ss", Locale.getDefault()).format(new Date(j));
    }
}
