package com.google.android.systemui.columbus.legacy.sensors;

import java.io.Closeable;
import java.util.Random;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class GestureSensor implements Closeable {
    public GestureController$gestureSensorListener$1 listener;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class DetectionProperties {
        public final long actionId = new Random().nextLong();
        public final boolean isHapticConsumed;

        public DetectionProperties(boolean z) {
            this.isHapticConsumed = z;
        }
    }

    public abstract boolean isListening();

    public void setGestureListener(GestureController$gestureSensorListener$1 gestureController$gestureSensorListener$1) {
        this.listener = gestureController$gestureSensorListener$1;
    }

    public abstract void startListening();

    public abstract void stopListening();

    public void close() {
    }
}
