package com.google.android.systemui.dagger;

import android.app.NotificationManager;
import android.app.admin.DevicePolicyManager;
import com.android.systemui.screenshot.ScreenshotNotificationsController;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$29 {
    public final /* synthetic */ DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider this$0;

    public DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$29(DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider) {
        this.this$0 = switchingProvider;
    }

    public final ScreenshotNotificationsController create(int i) {
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = this.this$0;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = switchingProvider.sysUIGoogleGlobalRootComponentImpl;
        return new ScreenshotNotificationsController(i, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (NotificationManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideNotificationManagerProvider.get(), (DevicePolicyManager) switchingProvider.sysUIGoogleGlobalRootComponentImpl.provideDevicePolicyManagerProvider.get());
    }
}
