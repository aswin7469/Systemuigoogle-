package com.google.android.systemui.smartspace;

import android.provider.DeviceConfig;
import java.util.Set;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class LazyServerFlagLoader$$ExternalSyntheticLambda0 implements DeviceConfig.OnPropertiesChangedListener {
    public final /* synthetic */ LazyServerFlagLoader f$0;

    public /* synthetic */ LazyServerFlagLoader$$ExternalSyntheticLambda0(LazyServerFlagLoader lazyServerFlagLoader) {
        this.f$0 = lazyServerFlagLoader;
    }

    public final void onPropertiesChanged(DeviceConfig.Properties properties) {
        LazyServerFlagLoader lazyServerFlagLoader = this.f$0;
        lazyServerFlagLoader.getClass();
        Set keyset = properties.getKeyset();
        String str = lazyServerFlagLoader.mPropertyKey;
        if (keyset.contains(str)) {
            lazyServerFlagLoader.mValue = Boolean.valueOf(properties.getBoolean(str, true));
        }
    }
}
