package com.google.android.systemui.columbus.legacy;

import com.android.systemui.shared.system.InputMonitorCompat;
import javax.inject.Provider;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class ColumbusModule_ProvideInputMonitorFactory implements Provider {
    public static InputMonitorCompat provideInputMonitor() {
        return new InputMonitorCompat(0, "Quick Tap");
    }
}
