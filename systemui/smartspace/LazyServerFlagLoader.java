package com.google.android.systemui.smartspace;

import android.provider.DeviceConfig;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
