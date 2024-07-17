package com.google.android.systemui.columbus.legacy.actions;

import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class Action$setAvailable$2 implements Runnable {
    public final /* synthetic */ Action this$0;

    public Action$setAvailable$2(Action action) {
        this.this$0 = action;
    }

    public final void run() {
        this.this$0.updateFeedbackEffects(0, (GestureSensor.DetectionProperties) null);
    }
}
