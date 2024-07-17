package com.google.android.systemui.columbus.legacy.gates;

import com.android.systemui.keyguard.WakefulnessLifecycle;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class PowerState$wakefulnessLifecycleObserver$1 implements WakefulnessLifecycle.Observer {
    public final /* synthetic */ PowerState this$0;

    public PowerState$wakefulnessLifecycleObserver$1(PowerState powerState) {
        this.this$0 = powerState;
    }

    public final void onFinishedGoingToSleep() {
        this.this$0.updateBlocking$2();
    }

    public final void onStartedWakingUp() {
        this.this$0.updateBlocking$2();
    }
}
