package com.google.android.systemui.power;

import android.net.Uri;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class DumpUtils {
    public static final Uri PROVIDER_URI = new Uri.Builder().scheme("content").authority("com.google.android.apps.turbo.battery_health_provider").build();

    public static String toReadableDateTime(long j) {
        return new SimpleDateFormat("MMM dd,yyyy HH:mm:ss", Locale.getDefault()).format(new Date(j));
    }
}
