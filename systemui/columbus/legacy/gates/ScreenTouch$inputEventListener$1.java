package com.google.android.systemui.columbus.legacy.gates;

import android.frameworks.stats.VendorAtomValue$1$$ExternalSyntheticOutline0;
import android.view.InputEvent;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import com.android.systemui.shared.system.InputChannelCompat$InputEventListener;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
                                throw new IllegalStateException(VendorAtomValue$1$$ExternalSyntheticOutline0.m(motionEvent.getAction(), "No valid delay for MotionEvent action: "));
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
