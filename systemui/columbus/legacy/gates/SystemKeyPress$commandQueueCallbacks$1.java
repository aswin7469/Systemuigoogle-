package com.google.android.systemui.columbus.legacy.gates;

import android.view.KeyEvent;
import com.android.systemui.statusbar.CommandQueue;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class SystemKeyPress$commandQueueCallbacks$1 implements CommandQueue.Callbacks {
    public final /* synthetic */ SystemKeyPress this$0;

    public SystemKeyPress$commandQueueCallbacks$1(SystemKeyPress systemKeyPress) {
        this.this$0 = systemKeyPress;
    }

    public final void handleSystemKey(KeyEvent keyEvent) {
        SystemKeyPress systemKeyPress = this.this$0;
        if (systemKeyPress.blockingKeys.contains(Integer.valueOf(keyEvent.getKeyCode()))) {
            systemKeyPress.blockForMillis(systemKeyPress.gateDuration);
        }
    }
}
