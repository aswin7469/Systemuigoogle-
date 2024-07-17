package com.google.android.systemui.power.batteryhealth;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class HealthManager$healthDebugReceiver$1 extends BroadcastReceiver {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ HealthManager this$0;

    public /* synthetic */ HealthManager$healthDebugReceiver$1(HealthManager healthManager, int i) {
        this.$r8$classId = i;
        this.this$0 = healthManager;
    }

    public final void onReceive(Context context, Intent intent) {
        switch (this.$r8$classId) {
            case 0:
                Log.d("HealthManager", "onReceive: " + intent);
                if (HealthManager.healthDebugEnabled && "com.google.android.systemui.BATTERY_HEALTH_DEBUG".equals(intent.getAction())) {
                    HealthManager healthManager = this.this$0;
                    BuildersKt.launch$default(healthManager.mainScope, (CoroutineContext) null, (CoroutineStart) null, new HealthManager$healthDebugReceiver$1$onReceive$1(healthManager, (Continuation) null), 3);
                    return;
                }
                return;
            default:
                Log.d("HealthManager", "onReceive: " + intent);
                if (StringsKt__StringsJVMKt.equals(intent.getAction(), "android.intent.action.BOOT_COMPLETED", false)) {
                    this.this$0.broadcastDispatcher.unregisterReceiver(this);
                    HealthManager healthManager2 = this.this$0;
                    BuildersKt.launch$default(healthManager2.mainScope, (CoroutineContext) null, (CoroutineStart) null, new HealthManager$bootCompletedReceiver$1$onReceive$1(healthManager2, (Continuation) null), 3);
                    return;
                }
                return;
        }
    }
}
