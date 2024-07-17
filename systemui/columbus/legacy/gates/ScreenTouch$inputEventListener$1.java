package com.google.android.systemui.columbus.legacy.gates;

import android.frameworks.stats.VendorAtomValue$1$$ExternalSyntheticOutline0;
import android.view.InputEvent;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import com.android.systemui.shared.system.InputChannelCompat$InputEventListener;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ScreenTouch$inputEventListener$1 implements InputChannelCompat$InputEventListener {
    public final /* synthetic */ ScreenTouch this$0;

    public ScreenTouch$inputEventListener$1(ScreenTouch screenTouch) {
        this.this$0 = screenTouch;
    }

    public final void onInputEvent(InputEvent inputEvent) {
        MotionEvent motionEvent;
        long j;
        if (inputEvent instanceof MotionEvent) {
            motionEvent = (MotionEvent) inputEvent;
        } else {
            motionEvent = null;
        }
        if (motionEvent != null) {
            ScreenTouch screenTouch = this.this$0;
            screenTouch.getClass();
            int action = motionEvent.getAction() & 255;
            if (action == 0 || action == 1 || action == 5 || action == 6) {
                int action2 = motionEvent.getAction() & 255;
                if (action2 != 0) {
                    if (action2 != 1) {
                        if (action2 != 5) {
                            if (action2 != 6) {
                                throw new IllegalStateException(VendorAtomValue$1$$ExternalSyntheticOutline0.m("No valid delay for MotionEvent action: ", motionEvent.getAction()));
                            }
                        }
                    }
                    j = 250;
                    screenTouch.blockForMillis(j);
                }
                j = ((long) ViewConfiguration.getLongPressTimeout()) + 500;
                screenTouch.blockForMillis(j);
            }
        }
    }
}
