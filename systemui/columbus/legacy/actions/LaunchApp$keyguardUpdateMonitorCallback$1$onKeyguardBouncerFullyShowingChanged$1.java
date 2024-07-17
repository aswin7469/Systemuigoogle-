package com.google.android.systemui.columbus.legacy.actions;

import android.content.Context;
import android.content.res.ColorStateList;
import android.hardware.biometrics.BiometricSourceType;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class LaunchApp$keyguardUpdateMonitorCallback$1$onKeyguardBouncerFullyShowingChanged$1 implements Runnable {
    public final /* synthetic */ Context $context;
    public final /* synthetic */ LaunchApp this$0;

    public LaunchApp$keyguardUpdateMonitorCallback$1$onKeyguardBouncerFullyShowingChanged$1(LaunchApp launchApp, Context context) {
        this.this$0 = launchApp;
        this.$context = context;
    }

    public final void run() {
        this.this$0.statusBarKeyguardViewManager.setKeyguardMessage(this.$context.getString(2131952237), ColorStateList.valueOf(-1), (BiometricSourceType) null);
    }
}
