package com.google.android.systemui.columbus.legacy.actions;

import com.google.android.systemui.columbus.legacy.gates.Gate;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class LaunchApp$gateListener$1 implements Gate.Listener {
    public final /* synthetic */ LaunchApp this$0;

    public LaunchApp$gateListener$1(LaunchApp launchApp) {
        this.this$0 = launchApp;
    }

    public final void onGateChanged(Gate gate) {
        if (!gate.isBlocking()) {
            this.this$0.updateAvailableAppsAndShortcutsAsync();
        }
    }
}