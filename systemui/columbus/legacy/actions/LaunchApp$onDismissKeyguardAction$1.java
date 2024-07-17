package com.google.android.systemui.columbus.legacy.actions;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.pm.ShortcutInfo;
import android.graphics.Rect;
import android.os.Bundle;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.plugins.ActivityStarter;
import com.google.android.systemui.columbus.ColumbusEvent;
import java.util.Map;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class LaunchApp$onDismissKeyguardAction$1 implements ActivityStarter.OnDismissAction {
    public final /* synthetic */ LaunchApp this$0;

    public LaunchApp$onDismissKeyguardAction$1(LaunchApp launchApp) {
        this.this$0 = launchApp;
    }

    public final boolean onDismiss() {
        String str;
        ShortcutInfo shortcutInfo;
        String str2;
        LaunchApp launchApp = this.this$0;
        boolean usingShortcut = launchApp.usingShortcut();
        UiEventLogger uiEventLogger = launchApp.uiEventLogger;
        String str3 = null;
        if (usingShortcut) {
            Map map = launchApp.availableShortcuts;
            ComponentName componentName = launchApp.currentApp;
            if (componentName != null) {
                str = componentName.getPackageName();
            } else {
                str = null;
            }
            Map map2 = (Map) map.get(str);
            if (!(map2 == null || (shortcutInfo = (ShortcutInfo) map2.get(launchApp.currentShortcut)) == null)) {
                ColumbusEvent columbusEvent = ColumbusEvent.COLUMBUS_INVOKED_LAUNCH_SHORTCUT;
                ComponentName componentName2 = launchApp.currentApp;
                if (componentName2 != null) {
                    str2 = componentName2.getPackageName();
                } else {
                    str2 = null;
                }
                uiEventLogger.log(columbusEvent, 0, str2);
                launchApp.launcherApps.startShortcut(shortcutInfo, (Rect) null, (Bundle) null);
            }
        } else {
            PendingIntent pendingIntent = (PendingIntent) launchApp.availableApps.get(launchApp.currentApp);
            if (pendingIntent != null) {
                ColumbusEvent columbusEvent2 = ColumbusEvent.COLUMBUS_INVOKED_LAUNCH_APP;
                ComponentName componentName3 = launchApp.currentApp;
                if (componentName3 != null) {
                    str3 = componentName3.getPackageName();
                }
                uiEventLogger.log(columbusEvent2, 0, str3);
                pendingIntent.send();
            }
        }
        return false;
    }
}
