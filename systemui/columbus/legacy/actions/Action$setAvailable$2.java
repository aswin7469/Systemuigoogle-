package com.google.android.systemui.columbus.legacy.actions;

import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class Action$setAvailable$2 implements Runnable {
    public final /* synthetic */ Action this$0;

    public Action$setAvailable$2(Action action) {
        this.this$0 = action;
    }

    public final void run() {
        this.this$0.updateFeedbackEffects(0, (GestureSensor.DetectionProperties) null);
    }
}
