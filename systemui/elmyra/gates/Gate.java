package com.google.android.systemui.elmyra.gates;

import java.util.concurrent.Executor;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class Gate {
    public boolean mActive = false;
    public Listener mListener;
    public final Executor mNotifyExecutor;

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
