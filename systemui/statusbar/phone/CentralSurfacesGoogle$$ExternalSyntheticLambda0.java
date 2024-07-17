package com.google.android.systemui.statusbar.phone;

import android.content.IntentFilter;
import android.os.UserHandle;
import android.util.Log;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.google.android.systemui.power.batteryhealth.HealthManager;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class CentralSurfacesGoogle$$ExternalSyntheticLambda0 implements Consumer {
    public final void accept(Object obj) {
        HealthManager healthManager = (HealthManager) obj;
        if (healthManager.periodicUpdateEnabled) {
            Log.i("HealthManager", "Enable BHI");
            BroadcastDispatcher.registerReceiver$default(healthManager.broadcastDispatcher, healthManager.bootCompletedReceiver, new IntentFilter("android.intent.action.BOOT_COMPLETED"), (Executor) null, (UserHandle) null, 0, (String) null, 60);
        }
    }
}
