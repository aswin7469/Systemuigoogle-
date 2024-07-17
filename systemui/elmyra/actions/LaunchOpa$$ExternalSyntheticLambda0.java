package com.google.android.systemui.elmyra.actions;

import android.net.Uri;
import android.provider.Settings;
import java.util.function.Consumer;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
