package com.google.android.systemui.columbus.legacy.gates;

import com.android.systemui.statusbar.CommandQueue;
import dagger.Lazy;
import java.util.Set;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
