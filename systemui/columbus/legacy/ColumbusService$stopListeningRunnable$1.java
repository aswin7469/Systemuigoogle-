package com.google.android.systemui.columbus.legacy;

import android.os.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import com.google.android.systemui.columbus.legacy.actions.Action;
import com.google.android.systemui.columbus.legacy.feedback.FeedbackEffect;
import com.google.android.systemui.columbus.legacy.gates.Gate;
import com.google.android.systemui.columbus.legacy.sensors.GestureController;
import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ColumbusService$stopListeningRunnable$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ColumbusService this$0;

    public /* synthetic */ ColumbusService$stopListeningRunnable$1(ColumbusService columbusService, int i) {
        this.$r8$classId = i;
        this.this$0 = columbusService;
    }

    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                ColumbusService columbusService = this.this$0;
                GestureController gestureController = columbusService.gestureController;
                GestureSensor gestureSensor = gestureController.gestureSensor;
                if (gestureSensor.isListening()) {
                    gestureSensor.stopListening();
                    for (Gate unregisterListener : gestureController.softGates) {
                        unregisterListener.unregisterListener(gestureController.softGateListener);
                    }
                    ((SystemClockImpl) columbusService.systemClock).getClass();
                    columbusService.lastStateChangeTimestamp = SystemClock.elapsedRealtime();
                    for (FeedbackEffect onGestureDetected : columbusService.effects) {
                        onGestureDetected.onGestureDetected(0, (GestureSensor.DetectionProperties) null);
                    }
                    Action updateActiveAction = columbusService.updateActiveAction();
                    if (updateActiveAction != null) {
                        updateActiveAction.onGestureDetected(0, (GestureSensor.DetectionProperties) null);
                        return;
                    }
                    return;
                }
                return;
            default:
                ColumbusService columbusService2 = this.this$0;
                GestureController gestureController2 = columbusService2.gestureController;
                GestureSensor gestureSensor2 = gestureController2.gestureSensor;
                if (!gestureSensor2.isListening()) {
                    for (Gate registerListener : gestureController2.softGates) {
                        registerListener.registerListener(gestureController2.softGateListener);
                    }
                    gestureSensor2.startListening();
                    ((SystemClockImpl) columbusService2.systemClock).getClass();
                    columbusService2.lastStateChangeTimestamp = SystemClock.elapsedRealtime();
                    return;
                }
                return;
        }
    }
}
