package com.google.android.systemui.assist.uihints;

import com.android.systemui.assist.AssistLogger;
import com.android.systemui.navigationbar.NavigationModeController;
import dagger.Lazy;
import javax.inject.Provider;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class TouchInsideHandler_Factory implements Provider {
    public static TouchInsideHandler newInstance(Lazy lazy, NavigationModeController navigationModeController, AssistLogger assistLogger) {
        return new TouchInsideHandler(lazy, navigationModeController, assistLogger);
    }
}
