package com.google.android.systemui.elmyra.sensors;

import java.util.Random;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public interface GestureSensor {

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class DetectionProperties {
        public final long mActionId = new Random().nextLong();
        public final boolean mHapticConsumed;
        public final boolean mHostSuspended;

        public DetectionProperties(boolean z, boolean z2) {
            this.mHapticConsumed = z;
            this.mHostSuspended = z2;
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public interface Listener {
        void onGestureDetected(DetectionProperties detectionProperties);

        void onGestureProgress(float f, int i);
    }

    boolean isListening();

    void setGestureListener(Listener listener);

    void startListening();

    void stopListening();
}
