package com.google.android.systemui.elmyra;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.systemui.elmyra.proto.nano.SnapshotProtos$SnapshotHeader;
import com.google.android.systemui.elmyra.sensors.CHREGestureSensor;
import com.google.android.systemui.elmyra.sensors.CHREGestureSensor$$ExternalSyntheticLambda0;
import com.google.android.systemui.elmyra.sensors.GestureSensor;
import com.google.protobuf.nano.MessageNano;
import java.util.Random;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class SnapshotController implements GestureSensor.Listener {
    public final AnonymousClass1 mHandler = new Handler(Looper.getMainLooper()) {
        public final void handleMessage(Message message) {
            if (message.what == 1) {
                SnapshotController snapshotController = SnapshotController.this;
                SnapshotProtos$SnapshotHeader snapshotProtos$SnapshotHeader = (SnapshotProtos$SnapshotHeader) message.obj;
                CHREGestureSensor$$ExternalSyntheticLambda0 cHREGestureSensor$$ExternalSyntheticLambda0 = snapshotController.mSnapshotListener;
                if (cHREGestureSensor$$ExternalSyntheticLambda0 != null) {
                    CHREGestureSensor cHREGestureSensor = cHREGestureSensor$$ExternalSyntheticLambda0.f$0;
                    cHREGestureSensor.getClass();
                    cHREGestureSensor.sendMessageToNanoApp(203, MessageNano.toByteArray(snapshotProtos$SnapshotHeader));
                }
            }
        }
    };
    public int mLastGestureStage = 0;
    public final int mSnapshotDelayAfterGesture;
    public CHREGestureSensor$$ExternalSyntheticLambda0 mSnapshotListener;

    public SnapshotController(SnapshotConfiguration snapshotConfiguration) {
        this.mSnapshotDelayAfterGesture = snapshotConfiguration.mSnapshotDelayAfterGesture;
    }

    public final void onGestureDetected(GestureSensor.DetectionProperties detectionProperties) {
        long j;
        SnapshotProtos$SnapshotHeader snapshotProtos$SnapshotHeader = new SnapshotProtos$SnapshotHeader();
        snapshotProtos$SnapshotHeader.gestureType = 1;
        if (detectionProperties != null) {
            j = detectionProperties.mActionId;
        } else {
            j = 0;
        }
        snapshotProtos$SnapshotHeader.identifier = j;
        this.mLastGestureStage = 0;
        AnonymousClass1 r5 = this.mHandler;
        r5.sendMessageDelayed(r5.obtainMessage(1, snapshotProtos$SnapshotHeader), (long) this.mSnapshotDelayAfterGesture);
    }

    public final void onGestureProgress(float f, int i) {
        if (this.mLastGestureStage == 2 && i != 2) {
            SnapshotProtos$SnapshotHeader snapshotProtos$SnapshotHeader = new SnapshotProtos$SnapshotHeader();
            snapshotProtos$SnapshotHeader.identifier = new Random().nextLong();
            snapshotProtos$SnapshotHeader.gestureType = 2;
            CHREGestureSensor$$ExternalSyntheticLambda0 cHREGestureSensor$$ExternalSyntheticLambda0 = this.mSnapshotListener;
            if (cHREGestureSensor$$ExternalSyntheticLambda0 != null) {
                CHREGestureSensor cHREGestureSensor = cHREGestureSensor$$ExternalSyntheticLambda0.f$0;
                cHREGestureSensor.getClass();
                cHREGestureSensor.sendMessageToNanoApp(203, MessageNano.toByteArray(snapshotProtos$SnapshotHeader));
            }
        }
        this.mLastGestureStage = i;
    }
}
