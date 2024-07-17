package com.google.android.systemui.theme;

import android.os.SystemProperties;
import android.util.Log;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.policy.ConfigurationController;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ThemeOverlayControllerGoogle$configurationChangedListener$1 implements ConfigurationController.ConfigurationListener {
    public final /* synthetic */ ThemeOverlayControllerGoogle this$0;

    public ThemeOverlayControllerGoogle$configurationChangedListener$1(ThemeOverlayControllerGoogle themeOverlayControllerGoogle) {
        this.this$0 = themeOverlayControllerGoogle;
    }

    public final void onThemeChanged() {
        ThemeOverlayControllerGoogle themeOverlayControllerGoogle = this.this$0;
        if (((UserTrackerImpl) themeOverlayControllerGoogle.userTracker).getUserId() == 0) {
            try {
                int[] bootColors = themeOverlayControllerGoogle.getBootColors();
                int length = bootColors.length;
                int i = 0;
                while (i < length) {
                    int i2 = bootColors[i];
                    i++;
                    themeOverlayControllerGoogle.systemProperties.getClass();
                    SystemProperties.set("persist.bootanim.color" + i, String.valueOf(i2));
                    Log.d("ThemeOverlayController", "Writing boot animation colors " + i + ": " + Integer.toHexString(i2));
                }
            } catch (RuntimeException unused) {
                Log.w("ThemeOverlayController", "Cannot set sysprop. Look for 'init' and 'dmesg' logs for more info.");
            }
        }
    }
}
