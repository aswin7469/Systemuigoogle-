package com.google.android.systemui.elmyra.gates;

import android.content.res.Resources;
import android.view.KeyEvent;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.util.concurrency.DelayableExecutor;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class SystemKeyPress extends TransientGate {
    public final int[] mBlockingKeys;
    public final CommandQueue mCommandQueue;
    public final AnonymousClass1 mCommandQueueCallbacks = new CommandQueue.Callbacks() {
        public final void handleSystemKey(KeyEvent keyEvent) {
            int i = 0;
            while (true) {
                SystemKeyPress systemKeyPress = SystemKeyPress.this;
                int[] iArr = systemKeyPress.mBlockingKeys;
                if (i >= iArr.length) {
                    return;
                }
                if (iArr[i] == keyEvent.getKeyCode()) {
                    systemKeyPress.block();
                    return;
                }
                i++;
            }
        }
    };

    public SystemKeyPress(Resources resources, DelayableExecutor delayableExecutor, int i, CommandQueue commandQueue) {
        super(delayableExecutor, (long) i);
        this.mBlockingKeys = resources.getIntArray(2130903125);
        this.mCommandQueue = commandQueue;
    }

    public final void onActivate() {
        this.mCommandQueue.addCallback((CommandQueue.Callbacks) this.mCommandQueueCallbacks);
    }

    public final void onDeactivate() {
        this.mCommandQueue.removeCallback((CommandQueue.Callbacks) this.mCommandQueueCallbacks);
    }
}
