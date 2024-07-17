package com.google.android.systemui.dreamliner;

import android.content.Intent;
import android.os.UserHandle;
import android.util.Log;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class DockObserver$$ExternalSyntheticLambda2 {
    public final /* synthetic */ DockObserver f$0;

    public /* synthetic */ DockObserver$$ExternalSyntheticLambda2(DockObserver dockObserver) {
        this.f$0 = dockObserver;
    }

    public final void onFanLevelChanged(int i) {
        DockObserver dockObserver = this.f$0;
        dockObserver.getClass();
        Log.d("DLObserver", "notify l=" + i + ", isDocked=" + dockObserver.isDocked());
        if (dockObserver.isDocked()) {
            dockObserver.mProtectedBroadcastSender.sendBroadcastAsUser(new Intent("com.google.android.systemui.dreamliner.ACTION_UPDATE_FAN_LEVEL").putExtra("fan_level", i).addFlags(1073741824), UserHandle.ALL);
        }
    }
}
