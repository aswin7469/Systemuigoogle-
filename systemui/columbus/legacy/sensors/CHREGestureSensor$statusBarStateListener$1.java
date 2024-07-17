package com.google.android.systemui.columbus.legacy.sensors;

import com.android.systemui.plugins.statusbar.StatusBarStateController;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
