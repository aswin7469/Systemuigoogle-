package com.google.android.systemui.columbus.legacy.sensors;

import com.android.systemui.keyguard.WakefulnessLifecycle;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class CHREGestureSensor$wakefulnessLifecycleObserver$1 implements WakefulnessLifecycle.Observer {
    public final /* synthetic */ CHREGestureSensor this$0;

    public CHREGestureSensor$wakefulnessLifecycleObserver$1(CHREGestureSensor cHREGestureSensor) {
        this.this$0 = cHREGestureSensor;
    }

    public final void onFinishedGoingToSleep$1() {
        CHREGestureSensor cHREGestureSensor = this.this$0;
        if (cHREGestureSensor.isAwake) {
            cHREGestureSensor.isAwake = false;
            cHREGestureSensor.updateScreenState();
        }
    }

    public final void onFinishedWakingUp() {
        CHREGestureSensor cHREGestureSensor = this.this$0;
        if (!cHREGestureSensor.isAwake) {
            cHREGestureSensor.isAwake = true;
            cHREGestureSensor.updateScreenState();
        }
    }

    public final void onStartedGoingToSleep() {
        CHREGestureSensor cHREGestureSensor = this.this$0;
        if (cHREGestureSensor.isAwake) {
            cHREGestureSensor.isAwake = false;
            cHREGestureSensor.updateScreenState();
        }
    }

    public final void onStartedWakingUp() {
        CHREGestureSensor cHREGestureSensor = this.this$0;
        if (cHREGestureSensor.isAwake) {
            cHREGestureSensor.isAwake = false;
            cHREGestureSensor.updateScreenState();
        }
    }
}
