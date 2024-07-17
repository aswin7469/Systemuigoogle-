package com.google.android.systemui.columbus.legacy.actions;

import android.app.SynchronousUserSwitchObserver;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class LaunchApp$userSwitchCallback$1 extends SynchronousUserSwitchObserver {
    public final /* synthetic */ LaunchApp this$0;

    public LaunchApp$userSwitchCallback$1(LaunchApp launchApp) {
        this.this$0 = launchApp;
    }

    public final void onUserSwitching(int i) {
        this.this$0.updateAvailableAppsAndShortcutsAsync();
    }
}
