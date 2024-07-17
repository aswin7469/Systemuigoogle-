package com.google.android.systemui.columbus.legacy.gates;

import com.android.systemui.keyguard.WakefulnessLifecycle;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class PowerState$wakefulnessLifecycleObserver$1 implements WakefulnessLifecycle.Observer {
    public final /* synthetic */ PowerState this$0;

    public PowerState$wakefulnessLifecycleObserver$1(PowerState powerState) {
        this.this$0 = powerState;
    }

    public final void onFinishedGoingToSleep$1() {
        this.this$0.updateBlocking$2();
    }

    public final void onStartedWakingUp() {
        this.this$0.updateBlocking$2();
    }
}
