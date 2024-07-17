package com.google.android.systemui.elmyra.gates;

import android.net.Uri;
import java.util.function.Consumer;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
