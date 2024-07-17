package com.google.android.systemui.columbus.legacy.actions;

import android.content.ComponentName;
import com.google.android.systemui.columbus.legacy.ColumbusSettings;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
