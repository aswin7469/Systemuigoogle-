package com.google.android.systemui.columbus.legacy.actions;

import com.android.systemui.keyguard.WakefulnessLifecycle;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class UserSelectedAction$wakefulnessLifecycleObserver$1 implements WakefulnessLifecycle.Observer {
    public final /* synthetic */ UserSelectedAction this$0;

    public UserSelectedAction$wakefulnessLifecycleObserver$1(UserSelectedAction userSelectedAction) {
        this.this$0 = userSelectedAction;
    }

    public final void onFinishedGoingToSleep() {
        this.this$0.updateAvailable$7();
    }

    public final void onStartedWakingUp() {
        this.this$0.updateAvailable$7();
    }
}
