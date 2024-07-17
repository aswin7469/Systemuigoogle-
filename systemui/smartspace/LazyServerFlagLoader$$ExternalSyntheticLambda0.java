package com.google.android.systemui.smartspace;

import android.provider.DeviceConfig;
import java.util.Set;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
