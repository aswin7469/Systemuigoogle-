package com.google.android.systemui.screenprotector;

import android.content.Context;
import android.content.SharedPreferences;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class ScreenProtectorSharedPreferenceUtils {
    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences("screen_protector_shared_perf", 0);
    }
}
