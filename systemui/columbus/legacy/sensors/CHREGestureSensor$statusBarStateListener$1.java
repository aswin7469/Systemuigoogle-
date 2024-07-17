package com.google.android.systemui.columbus.legacy.sensors;

import com.android.systemui.plugins.statusbar.StatusBarStateController;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class CHREGestureSensor$statusBarStateListener$1 implements StatusBarStateController.StateListener {
    public final /* synthetic */ CHREGestureSensor this$0;

    public CHREGestureSensor$statusBarStateListener$1(CHREGestureSensor cHREGestureSensor) {
        this.this$0 = cHREGestureSensor;
    }

    public final void onDozingChanged(boolean z) {
        CHREGestureSensor cHREGestureSensor = this.this$0;
        if (cHREGestureSensor.isDozing != z) {
            cHREGestureSensor.isDozing = z;
            cHREGestureSensor.updateScreenState();
        }
    }
}
