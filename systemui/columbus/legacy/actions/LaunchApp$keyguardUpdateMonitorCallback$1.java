package com.google.android.systemui.columbus.legacy.actions;

import android.content.Context;
import com.android.keyguard.KeyguardUpdateMonitorCallback;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
            launchApp.mainHandler.post(new LaunchApp$keyguardUpdateMonitorCallback$1$onKeyguardBouncerFullyShowingChanged$1(launchApp, this.$context));
        }
    }
}
