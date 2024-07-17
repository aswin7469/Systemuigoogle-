package com.google.android.systemui.assist.uihints.input;

import android.graphics.Region;
import android.hardware.input.InputManagerGlobal;
import android.os.Looper;
import android.util.Log;
import android.view.InputChannel;
import android.view.InputEvent;
import android.view.InputEventReceiver;
import android.view.InputMonitor;
import android.view.MotionEvent;
import com.google.android.systemui.assist.uihints.NgaMessageHandler;
import com.google.android.systemui.assist.uihints.TouchInsideHandler;
import java.util.Set;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class NgaInputHandler implements NgaMessageHandler.EdgeLightsInfoListener {
    public NgaInputEventReceiver mInputEventReceiver;
    public InputMonitor mInputMonitor;
    public final Set mTouchActionRegions;
    public final TouchInsideHandler mTouchInsideHandler;
    public final Set mTouchInsideRegions;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class NgaInputEventReceiver extends InputEventReceiver {
        public NgaInputEventReceiver(InputChannel inputChannel) {
            super(inputChannel, Looper.getMainLooper());
        }

        public final void onInputEvent(InputEvent inputEvent) {
            if (inputEvent instanceof MotionEvent) {
                NgaInputHandler ngaInputHandler = NgaInputHandler.this;
                MotionEvent motionEvent = (MotionEvent) inputEvent;
                ngaInputHandler.getClass();
                int rawX = (int) motionEvent.getRawX();
                int rawY = (int) motionEvent.getRawY();
                Region region = new Region();
                for (TouchInsideRegion touchInsideRegion : ngaInputHandler.mTouchInsideRegions) {
                    touchInsideRegion.getTouchInsideRegion().ifPresent(new NgaInputHandler$$ExternalSyntheticLambda0(0, region));
                }
                for (TouchActionRegion touchActionRegion : ngaInputHandler.mTouchActionRegions) {
                    touchActionRegion.getTouchActionRegion().ifPresent(new NgaInputHandler$$ExternalSyntheticLambda0(1, region));
                }
                if (region.contains(rawX, rawY)) {
                    ngaInputHandler.mTouchInsideHandler.onTouchInside();
                }
            }
            finishInputEvent(inputEvent, false);
        }
    }

    public NgaInputHandler(TouchInsideHandler touchInsideHandler, Set set, Set set2) {
        this.mTouchInsideHandler = touchInsideHandler;
        this.mTouchActionRegions = set;
        this.mTouchInsideRegions = set2;
    }

    public final void onEdgeLightsInfo(String str, boolean z) {
        if (!"HALF_LISTENING".equals(str)) {
            NgaInputEventReceiver ngaInputEventReceiver = this.mInputEventReceiver;
            if (ngaInputEventReceiver != null) {
                ngaInputEventReceiver.dispose();
                this.mInputEventReceiver = null;
            }
            InputMonitor inputMonitor = this.mInputMonitor;
            if (inputMonitor != null) {
                inputMonitor.dispose();
                this.mInputMonitor = null;
            }
        } else if (this.mInputEventReceiver == null && this.mInputMonitor == null) {
            this.mInputMonitor = InputManagerGlobal.getInstance().monitorGestureInput("NgaInputHandler", 0);
            this.mInputEventReceiver = new NgaInputEventReceiver(this.mInputMonitor.getInputChannel());
        } else {
            Log.w("NgaInputHandler", "Already monitoring");
        }
    }
}
