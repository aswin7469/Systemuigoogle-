package com.google.android.systemui.elmyra.gates;

import android.net.Uri;
import java.util.function.Consumer;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class WakeMode$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ WakeMode f$0;

    public /* synthetic */ WakeMode$$ExternalSyntheticLambda0(WakeMode wakeMode) {
        this.f$0 = wakeMode;
    }

    public final void accept(Object obj) {
        WakeMode wakeMode = this.f$0;
        Uri uri = (Uri) obj;
        boolean z = true;
        if (wakeMode.mSettings.getIntForUser("assist_gesture_wake_enabled", 1, -2) == 0) {
            z = false;
        }
        if (z != wakeMode.mWakeSettingEnabled) {
            wakeMode.mWakeSettingEnabled = z;
            wakeMode.notifyListener();
        }
    }
}
