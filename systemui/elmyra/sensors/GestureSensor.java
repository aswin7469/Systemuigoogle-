package com.google.android.systemui.elmyra.sensors;

import java.util.Random;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public interface GestureSensor {

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class DetectionProperties {
        public final long mActionId = new Random().nextLong();
        public final boolean mHapticConsumed;
        public final boolean mHostSuspended;

        public DetectionProperties(boolean z, boolean z2) {
            this.mHapticConsumed = z;
            this.mHostSuspended = z2;
        }
    }

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public interface Listener {
        void onGestureDetected(DetectionProperties detectionProperties);

        void onGestureProgress(int i, float f);
    }

    boolean isListening();

    void setGestureListener(Listener listener);

    void startListening();

    void stopListening();
}
