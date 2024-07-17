package com.google.android.systemui.elmyra.actions;

import android.net.Uri;
import android.provider.Settings;
import java.util.function.Consumer;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class LaunchOpa$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ LaunchOpa f$0;

    public /* synthetic */ LaunchOpa$$ExternalSyntheticLambda0(LaunchOpa launchOpa) {
        this.f$0 = launchOpa;
    }

    public final void accept(Object obj) {
        LaunchOpa launchOpa = this.f$0;
        Uri uri = (Uri) obj;
        boolean z = true;
        if (Settings.Secure.getIntForUser(launchOpa.mContext.getContentResolver(), "assist_gesture_enabled", 1, -2) == 0) {
            z = false;
        }
        if (launchOpa.mIsGestureEnabled != z) {
            launchOpa.mIsGestureEnabled = z;
            launchOpa.notifyListener();
        }
    }
}
