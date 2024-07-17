package com.google.android.systemui.theme;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import com.android.systemui.flags.SystemPropertiesHelper;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.theme.ThemeOverlayController;
import java.io.PrintWriter;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ThemeOverlayControllerGoogle extends ThemeOverlayController {
    public final Context context;
    public final Resources resources;
    public final SystemPropertiesHelper systemProperties;
    public final UserTracker userTracker;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ThemeOverlayControllerGoogle(android.content.Context r20, com.android.systemui.broadcast.BroadcastDispatcher r21, android.os.Handler r22, java.util.concurrent.Executor r23, java.util.concurrent.Executor r24, com.android.systemui.theme.ThemeOverlayApplier r25, com.android.systemui.util.settings.SecureSettings r26, com.android.systemui.flags.SystemPropertiesHelper r27, android.content.res.Resources r28, android.app.WallpaperManager r29, android.os.UserManager r30, com.android.systemui.dump.DumpManager r31, com.android.systemui.statusbar.policy.DeviceProvisionedController r32, com.android.systemui.settings.UserTracker r33, com.android.systemui.flags.FeatureFlags r34, com.android.systemui.keyguard.WakefulnessLifecycle r35, com.android.systemui.util.kotlin.JavaAdapter r36, com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor r37, android.app.UiModeManager r38, com.android.systemui.statusbar.policy.ConfigurationController r39) {
        /*
            r19 = this;
            r15 = r19
            r0 = r19
            r1 = r20
            r2 = r21
            r3 = r22
            r4 = r23
            r5 = r24
            r6 = r25
            r7 = r26
            r14 = r28
            r8 = r29
            r9 = r30
            r12 = r31
            r10 = r32
            r11 = r33
            r13 = r34
            r15 = r35
            r16 = r36
            r17 = r37
            r18 = r38
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18)
            r0.context = r1
            r1 = r27
            r0.systemProperties = r1
            r1 = r28
            r0.resources = r1
            r1 = r33
            r0.userTracker = r1
            com.google.android.systemui.theme.ThemeOverlayControllerGoogle$configurationChangedListener$1 r1 = new com.google.android.systemui.theme.ThemeOverlayControllerGoogle$configurationChangedListener$1
            r1.<init>(r0)
            r2 = r39
            com.android.systemui.statusbar.phone.ConfigurationControllerImpl r2 = (com.android.systemui.statusbar.phone.ConfigurationControllerImpl) r2
            r2.addCallback(r1)
            int[] r0 = r19.getBootColors()
            int r1 = r0.length
            r2 = 0
        L_0x004b:
            if (r2 >= r1) goto L_0x005b
            r3 = r0[r2]
            int r2 = r2 + 1
            java.lang.String r4 = "Boot animation colors "
            java.lang.String r5 = ": "
            java.lang.String r6 = "ThemeOverlayController"
            androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0.m(r4, r2, r5, r3, r6)
            goto L_0x004b
        L_0x005b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.theme.ThemeOverlayControllerGoogle.<init>(android.content.Context, com.android.systemui.broadcast.BroadcastDispatcher, android.os.Handler, java.util.concurrent.Executor, java.util.concurrent.Executor, com.android.systemui.theme.ThemeOverlayApplier, com.android.systemui.util.settings.SecureSettings, com.android.systemui.flags.SystemPropertiesHelper, android.content.res.Resources, android.app.WallpaperManager, android.os.UserManager, com.android.systemui.dump.DumpManager, com.android.systemui.statusbar.policy.DeviceProvisionedController, com.android.systemui.settings.UserTracker, com.android.systemui.flags.FeatureFlags, com.android.systemui.keyguard.WakefulnessLifecycle, com.android.systemui.util.kotlin.JavaAdapter, com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor, android.app.UiModeManager, com.android.systemui.statusbar.policy.ConfigurationController):void");
    }

    public final void dump(PrintWriter printWriter, String[] strArr) {
        super.dump(printWriter, strArr);
        printWriter.println("ThemeOverlayControllerGoogle: yes");
    }

    public final int[] getBootColors() {
        Context context2 = this.context;
        int color = context2.getColor(17170494);
        int red = Color.red(color);
        int green = Color.green(color);
        Resources resources2 = this.resources;
        if (red == green && Color.green(color) == Color.blue(color)) {
            return new int[]{resources2.getColor(2131100825, context2.getTheme()), resources2.getColor(2131100831, context2.getTheme()), resources2.getColor(2131100827, context2.getTheme()), resources2.getColor(2131100829, context2.getTheme())};
        }
        return new int[]{resources2.getColor(2131100824, context2.getTheme()), resources2.getColor(2131100830, context2.getTheme()), resources2.getColor(2131100826, context2.getTheme()), resources2.getColor(2131100828, context2.getTheme())};
    }
}
