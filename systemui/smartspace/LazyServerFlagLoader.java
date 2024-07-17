package com.google.android.systemui.smartspace;

import android.provider.DeviceConfig;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class LazyServerFlagLoader {
    public final String mPropertyKey;
    public Boolean mValue = null;

    public LazyServerFlagLoader(String str) {
        this.mPropertyKey = str;
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [java.util.concurrent.Executor, java.lang.Object] */
    public final boolean get() {
        if (this.mValue == null) {
            this.mValue = Boolean.valueOf(DeviceConfig.getBoolean("launcher", this.mPropertyKey, true));
            DeviceConfig.addOnPropertiesChangedListener("launcher", new Object(), new LazyServerFlagLoader$$ExternalSyntheticLambda0(this));
        }
        return this.mValue.booleanValue();
    }
}
