package com.google.android.systemui.columbus.legacy.sensors;

import java.io.Closeable;
import java.util.Random;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class GestureSensor implements Closeable {
    public GestureController$gestureSensorListener$1 listener;

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
