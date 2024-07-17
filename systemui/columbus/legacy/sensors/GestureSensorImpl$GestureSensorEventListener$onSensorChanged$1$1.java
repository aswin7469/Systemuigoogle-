package com.google.android.systemui.columbus.legacy.sensors;

import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class GestureSensorImpl$GestureSensorEventListener$onSensorChanged$1$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ GestureSensorImpl this$0;

    public /* synthetic */ GestureSensorImpl$GestureSensorEventListener$onSensorChanged$1$1(GestureSensorImpl gestureSensorImpl, int i) {
        this.$r8$classId = i;
        this.this$0 = gestureSensorImpl;
    }

    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                GestureSensorImpl gestureSensorImpl = this.this$0;
                GestureSensor.DetectionProperties detectionProperties = new GestureSensor.DetectionProperties(true);
                GestureController$gestureSensorListener$1 gestureController$gestureSensorListener$1 = gestureSensorImpl.listener;
                if (gestureController$gestureSensorListener$1 != null) {
                    gestureController$gestureSensorListener$1.onGestureDetected(2, detectionProperties);
                    return;
                }
                return;
            default:
                GestureSensorImpl gestureSensorImpl2 = this.this$0;
                GestureSensor.DetectionProperties detectionProperties2 = new GestureSensor.DetectionProperties(false);
                GestureController$gestureSensorListener$1 gestureController$gestureSensorListener$12 = gestureSensorImpl2.listener;
                if (gestureController$gestureSensorListener$12 != null) {
                    gestureController$gestureSensorListener$12.onGestureDetected(1, detectionProperties2);
                    return;
                }
                return;
        }
    }
}
