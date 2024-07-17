package com.google.android.systemui.columbus.legacy.actions;

import android.provider.DeviceConfig;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class LaunchApp$deviceConfigPropertiesChangedListener$1 implements DeviceConfig.OnPropertiesChangedListener {
    public final /* synthetic */ LaunchApp this$0;

    public LaunchApp$deviceConfigPropertiesChangedListener$1(LaunchApp launchApp) {
        this.this$0 = launchApp;
    }

    public final void onPropertiesChanged(DeviceConfig.Properties properties) {
        if (properties.getKeyset().contains("systemui_google_columbus_secure_deny_list")) {
            this.this$0.updateDenyList(properties.getString("systemui_google_columbus_secure_deny_list", (String) null));
        }
    }
}
