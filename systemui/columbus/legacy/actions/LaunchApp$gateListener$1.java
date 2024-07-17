package com.google.android.systemui.columbus.legacy.actions;

import com.google.android.systemui.columbus.legacy.gates.Gate;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
