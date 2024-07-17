package com.google.android.systemui.columbus;

import android.content.Context;
import android.content.pm.PackageManager;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ColumbusContext {
    public final PackageManager packageManager;

    public ColumbusContext(Context context) {
        this.packageManager = context.getPackageManager();
    }
}
