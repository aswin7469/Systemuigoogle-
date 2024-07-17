package com.google.android.systemui.columbus.legacy;

import com.android.systemui.shared.system.InputMonitorCompat;
import javax.inject.Provider;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class ColumbusModule_ProvideInputMonitorFactory implements Provider {
    public static InputMonitorCompat provideInputMonitor() {
        return new InputMonitorCompat(0, "Quick Tap");
    }
}
