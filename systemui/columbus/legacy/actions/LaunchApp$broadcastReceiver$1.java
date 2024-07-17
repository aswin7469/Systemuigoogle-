package com.google.android.systemui.columbus.legacy.actions;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class LaunchApp$broadcastReceiver$1 extends BroadcastReceiver {
    public final /* synthetic */ LaunchApp this$0;

    public LaunchApp$broadcastReceiver$1(LaunchApp launchApp) {
        this.this$0 = launchApp;
    }

    public final void onReceive(Context context, Intent intent) {
        this.this$0.updateAvailableAppsAndShortcutsAsync();
    }
}
