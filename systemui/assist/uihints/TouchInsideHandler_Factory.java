package com.google.android.systemui.assist.uihints;

import com.android.systemui.assist.AssistLogger;
import com.android.systemui.navigationbar.NavigationModeController;
import dagger.Lazy;
import javax.inject.Provider;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class TouchInsideHandler_Factory implements Provider {
    public static TouchInsideHandler newInstance(Lazy lazy, NavigationModeController navigationModeController, AssistLogger assistLogger) {
        return new TouchInsideHandler(lazy, navigationModeController, assistLogger);
    }
}
