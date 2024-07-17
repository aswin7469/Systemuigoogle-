package com.google.android.systemui.columbus.legacy.gates;

import com.android.systemui.statusbar.CommandQueue;
import dagger.Lazy;
import java.util.Set;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class SystemKeyPress extends TransientGate {
    public final Set blockingKeys;
    public final Lazy commandQueue;
    public final SystemKeyPress$commandQueueCallbacks$1 commandQueueCallbacks;
    public final long gateDuration = 500;

    public SystemKeyPress(Lazy lazy, Set set) {
        this.commandQueue = lazy;
        this.blockingKeys = set;
        this.commandQueueCallbacks = new SystemKeyPress$commandQueueCallbacks$1(this);
    }

    public final void onActivate() {
        ((CommandQueue) this.commandQueue.get()).addCallback((CommandQueue.Callbacks) this.commandQueueCallbacks);
    }

    public final void onDeactivate() {
        ((CommandQueue) this.commandQueue.get()).removeCallback((CommandQueue.Callbacks) this.commandQueueCallbacks);
    }
}
