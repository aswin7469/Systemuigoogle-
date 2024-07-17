package com.google.android.systemui.columbus.legacy.actions;

import android.content.ComponentName;
import com.google.android.systemui.columbus.legacy.ColumbusSettings;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class LaunchApp$settingsListener$1 implements ColumbusSettings.ColumbusSettingsChangeListener {
    public final /* synthetic */ LaunchApp this$0;

    public LaunchApp$settingsListener$1(LaunchApp launchApp) {
        this.this$0 = launchApp;
    }

    public final void onSelectedAppChange(String str) {
        ComponentName unflattenFromString = ComponentName.unflattenFromString(str);
        LaunchApp launchApp = this.this$0;
        launchApp.currentApp = unflattenFromString;
        launchApp.updateAvailable$4();
    }

    public final void onSelectedAppShortcutChange(String str) {
        LaunchApp launchApp = this.this$0;
        launchApp.currentShortcut = str;
        launchApp.updateAvailable$4();
    }
}
