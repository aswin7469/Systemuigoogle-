package com.google.android.systemui.columbus.legacy.sensors;

import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;
import android.util.SparseLongArray;
import com.google.android.systemui.columbus.ColumbusEvent;
import com.google.android.systemui.columbus.legacy.ColumbusService;
import com.google.android.systemui.columbus.legacy.ColumbusService$gestureListener$1;
import com.google.android.systemui.columbus.legacy.actions.Action;
import com.google.android.systemui.columbus.legacy.feedback.FeedbackEffect;
import com.google.android.systemui.columbus.legacy.gates.Gate;
import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;
import java.util.Iterator;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class GestureController$gestureSensorListener$1 {
    public final /* synthetic */ GestureController this$0;

    public GestureController$gestureSensorListener$1(GestureController gestureController) {
        this.this$0 = gestureController;
    }

    public final void onGestureDetected(int i, GestureSensor.DetectionProperties detectionProperties) {
        Object obj;
        PowerManager.WakeLock wakeLock;
        GestureController gestureController = this.this$0;
        gestureController.getClass();
        long uptimeMillis = SystemClock.uptimeMillis();
        SparseLongArray sparseLongArray = gestureController.lastTimestampMap;
        long j = sparseLongArray.get(i);
        sparseLongArray.put(i, uptimeMillis);
        if (uptimeMillis - j <= 500) {
            Log.w("Columbus/GestureControl", "Gesture " + i + " throttled");
            return;
        }
        Iterator it = gestureController.softGates.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (((Gate) obj).isBlocking()) {
                break;
            }
        }
        Gate gate = (Gate) obj;
        if (gate != null) {
            gestureController.softGateBlockCount++;
            Log.i("Columbus/GestureControl", "Gesture blocked by " + gate);
            return;
        }
        if (i == 1) {
            gestureController.uiEventLogger.log(ColumbusEvent.COLUMBUS_DOUBLE_TAP_DETECTED);
        }
        ColumbusService$gestureListener$1 columbusService$gestureListener$1 = gestureController.gestureListener;
        if (columbusService$gestureListener$1 != null) {
            ColumbusService columbusService = columbusService$gestureListener$1.this$0;
            if (!(i == 0 || (wakeLock = columbusService.wakeLock.wakeLock) == null)) {
                wakeLock.acquire(2000);
            }
            Action updateActiveAction = columbusService.updateActiveAction();
            if (updateActiveAction != null) {
                updateActiveAction.onGestureDetected(i, detectionProperties);
                for (FeedbackEffect onGestureDetected : columbusService.effects) {
                    onGestureDetected.onGestureDetected(i, detectionProperties);
                }
            }
        }
    }
}
