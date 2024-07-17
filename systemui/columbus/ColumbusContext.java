package com.google.android.systemui.columbus;

import android.content.Context;
import android.content.pm.PackageManager;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ColumbusContext {
    public final PackageManager packageManager;

    public ColumbusContext(Context context) {
        this.packageManager = context.getPackageManager();
    }
}
