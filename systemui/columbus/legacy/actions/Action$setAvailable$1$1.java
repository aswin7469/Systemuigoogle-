package com.google.android.systemui.columbus.legacy.actions;

import com.google.android.systemui.columbus.legacy.actions.Action;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class Action$setAvailable$1$1 implements Runnable {
    public final /* synthetic */ Action.Listener $it;
    public final /* synthetic */ Action this$0;

    public Action$setAvailable$1$1(Action.Listener listener, Action action) {
        this.$it = listener;
        this.this$0 = action;
    }

    public final void run() {
        this.$it.onActionAvailabilityChanged(this.this$0);
    }
}
