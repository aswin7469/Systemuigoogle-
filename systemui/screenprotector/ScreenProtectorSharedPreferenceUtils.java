package com.google.android.systemui.screenprotector;

import android.content.Context;
import android.content.SharedPreferences;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class ScreenProtectorSharedPreferenceUtils {
    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences("screen_protector_shared_perf", 0);
    }
}
