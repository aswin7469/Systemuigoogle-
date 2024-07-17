package com.google.android.systemui.theme;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import com.android.systemui.flags.SystemPropertiesHelper;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.theme.ThemeOverlayController;
import java.io.PrintWriter;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ThemeOverlayControllerGoogle extends ThemeOverlayController {
    public final Context context;
    public final Resources resources;
    public final SystemPropertiesHelper systemProperties;
    public final UserTracker userTracker;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ThemeOverlayControllerGoogle(android.content.Context r21, com.android.systemui.broadcast.BroadcastDispatcher r22, android.os.Handler r23, java.util.concurrent.Executor r24, java.util.concurrent.Executor r25, com.android.systemui.theme.ThemeOverlayApplier r26, com.android.systemui.util.settings.SecureSettings r27, com.android.systemui.flags.SystemPropertiesHelper r28, android.content.res.Resources r29, android.app.WallpaperManager r30, android.os.UserManager r31, com.android.systemui.dump.DumpManager r32, com.android.systemui.statusbar.policy.DeviceProvisionedController r33, com.android.systemui.settings.UserTracker r34, com.android.systemui.flags.FeatureFlags r35, com.android.systemui.keyguard.WakefulnessLifecycle r36, com.android.systemui.util.kotlin.JavaAdapter r37, com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor r38, android.app.UiModeManager r39, android.app.ActivityManager r40, com.android.systemui.statusbar.policy.ConfigurationController r41) {
        /*
            r20 = this;
            r15 = r20
            r0 = r20
            r1 = r21
            r2 = r22
            r3 = r23
            r4 = r24
            r5 = r25
            r6 = r26
            r7 = r27
            r14 = r29
            r8 = r30
            r9 = r31
            r12 = r32
            r10 = r33
            r11 = r34
            r13 = r35
            r15 = r36
            r16 = r37
            r17 = r38
            r18 = r39
            r19 = r40
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19)
            r0.context = r1
            r1 = r28
            r0.systemProperties = r1
            r1 = r29
            r0.resources = r1
            r1 = r34
            r0.userTracker = r1
            com.google.android.systemui.theme.ThemeOverlayControllerGoogle$configurationChangedListener$1 r1 = new com.google.android.systemui.theme.ThemeOverlayControllerGoogle$configurationChangedListener$1
            r1.<init>(r0)
            r2 = r41
            com.android.systemui.statusbar.phone.ConfigurationControllerImpl r2 = (com.android.systemui.statusbar.phone.ConfigurationControllerImpl) r2
            r2.addCallback(r1)
            int[] r0 = r20.getBootColors()
            int r1 = r0.length
            r2 = 0
        L_0x004d:
            if (r2 >= r1) goto L_0x005d
            r3 = r0[r2]
            int r2 = r2 + 1
            java.lang.String r4 = "Boot animation colors "
            java.lang.String r5 = ": "
            java.lang.String r6 = "ThemeOverlayController"
            androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0.m(r4, r2, r5, r3, r6)
            goto L_0x004d
        L_0x005d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.theme.ThemeOverlayControllerGoogle.<init>(android.content.Context, com.android.systemui.broadcast.BroadcastDispatcher, android.os.Handler, java.util.concurrent.Executor, java.util.concurrent.Executor, com.android.systemui.theme.ThemeOverlayApplier, com.android.systemui.util.settings.SecureSettings, com.android.systemui.flags.SystemPropertiesHelper, android.content.res.Resources, android.app.WallpaperManager, android.os.UserManager, com.android.systemui.dump.DumpManager, com.android.systemui.statusbar.policy.DeviceProvisionedController, com.android.systemui.settings.UserTracker, com.android.systemui.flags.FeatureFlags, com.android.systemui.keyguard.WakefulnessLifecycle, com.android.systemui.util.kotlin.JavaAdapter, com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor, android.app.UiModeManager, android.app.ActivityManager, com.android.systemui.statusbar.policy.ConfigurationController):void");
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
            return new int[]{resources2.getColor(2131100843, context2.getTheme()), resources2.getColor(2131100849, context2.getTheme()), resources2.getColor(2131100845, context2.getTheme()), resources2.getColor(2131100847, context2.getTheme())};
        }
        return new int[]{resources2.getColor(2131100842, context2.getTheme()), resources2.getColor(2131100848, context2.getTheme()), resources2.getColor(2131100844, context2.getTheme()), resources2.getColor(2131100846, context2.getTheme())};
    }
}
