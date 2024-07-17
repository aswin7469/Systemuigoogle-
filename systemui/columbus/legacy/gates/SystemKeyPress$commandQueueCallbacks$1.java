package com.google.android.systemui.columbus.legacy.gates;

import android.view.KeyEvent;
import com.android.systemui.statusbar.CommandQueue;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
