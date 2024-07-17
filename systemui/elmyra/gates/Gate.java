package com.google.android.systemui.elmyra.gates;

import java.util.concurrent.Executor;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class Gate {
    public boolean mActive = false;
    public Listener mListener;
    public final Executor mNotifyExecutor;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public interface Listener {
        void onGateChanged(Gate gate);
    }

    public Gate(Executor executor) {
        this.mNotifyExecutor = executor;
    }

    public final void activate() {
        if (!this.mActive) {
            this.mActive = true;
            onActivate();
        }
    }

    public final void deactivate() {
        if (this.mActive) {
            this.mActive = false;
            onDeactivate();
        }
    }

    public abstract boolean isBlocked();

    public final boolean isBlocking() {
        if (!this.mActive || !isBlocked()) {
            return false;
        }
        return true;
    }

    public final void notifyListener() {
        if (this.mActive && this.mListener != null) {
            this.mNotifyExecutor.execute(new Gate$$ExternalSyntheticLambda0(this));
        }
    }

    public abstract void onActivate();

    public abstract void onDeactivate();

    public String toString() {
        return getClass().getSimpleName();
    }
}
