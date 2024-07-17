package com.google.android.systemui.columbus.legacy.actions;

import android.app.SynchronousUserSwitchObserver;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class LaunchApp$userSwitchCallback$1 extends SynchronousUserSwitchObserver {
    public final /* synthetic */ LaunchApp this$0;

    public LaunchApp$userSwitchCallback$1(LaunchApp launchApp) {
        this.this$0 = launchApp;
    }

    public final void onUserSwitching(int i) {
        this.this$0.updateAvailableAppsAndShortcutsAsync();
    }
}
