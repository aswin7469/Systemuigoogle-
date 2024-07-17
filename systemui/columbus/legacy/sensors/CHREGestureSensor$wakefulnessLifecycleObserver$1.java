package com.google.android.systemui.columbus.legacy.sensors;

import com.android.systemui.keyguard.WakefulnessLifecycle;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class CHREGestureSensor$wakefulnessLifecycleObserver$1 implements WakefulnessLifecycle.Observer {
    public final /* synthetic */ CHREGestureSensor this$0;

    public CHREGestureSensor$wakefulnessLifecycleObserver$1(CHREGestureSensor cHREGestureSensor) {
        this.this$0 = cHREGestureSensor;
    }

    public final void onFinishedGoingToSleep() {
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
