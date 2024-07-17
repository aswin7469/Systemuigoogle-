package com.google.android.systemui.columbus.legacy.actions;

import android.net.Uri;
import android.provider.Settings;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class LaunchOpa$settingsObserver$1 extends Lambda implements Function1 {
    final /* synthetic */ LaunchOpa this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LaunchOpa$settingsObserver$1(LaunchOpa launchOpa) {
        super(1);
        this.this$0 = launchOpa;
    }

    public final Object invoke(Object obj) {
        Uri uri = (Uri) obj;
        LaunchOpa launchOpa = this.this$0;
        boolean z = true;
        if (Settings.Secure.getIntForUser(launchOpa.context.getContentResolver(), "assist_gesture_enabled", 1, -2) == 0) {
            z = false;
        }
        launchOpa.isGestureEnabled = z;
        launchOpa.updateAvailable$5();
        return Unit.INSTANCE;
    }
}
