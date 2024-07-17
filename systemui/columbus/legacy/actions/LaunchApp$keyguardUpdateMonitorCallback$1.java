package com.google.android.systemui.columbus.legacy.actions;

import android.content.Context;
import com.android.keyguard.KeyguardUpdateMonitorCallback;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class LaunchApp$keyguardUpdateMonitorCallback$1 extends KeyguardUpdateMonitorCallback {
    public final /* synthetic */ Context $context;
    public final /* synthetic */ LaunchApp this$0;

    public LaunchApp$keyguardUpdateMonitorCallback$1(LaunchApp launchApp, Context context) {
        this.this$0 = launchApp;
        this.$context = context;
    }

    public final void onKeyguardBouncerFullyShowingChanged(boolean z) {
        if (z) {
            LaunchApp launchApp = this.this$0;
            launchApp.keyguardUpdateMonitor.removeCallback(this);
            launchApp.mainHandler.post(new LaunchApp$updateAvailableAppsAndShortcutsAsync$$inlined$traceRunnable$1(launchApp, this.$context));
        }
    }
}
