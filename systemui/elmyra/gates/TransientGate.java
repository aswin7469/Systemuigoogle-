package com.google.android.systemui.elmyra.gates;

import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class TransientGate extends Gate {
    public final long mBlockDuration;
    public ExecutorImpl.ExecutionToken mCancelReset;
    public final DelayableExecutor mExecutor;
    public boolean mIsBlocking;
    public final AnonymousClass1 mResetGate = new Runnable() {
        public final void run() {
            TransientGate transientGate = TransientGate.this;
            transientGate.mIsBlocking = false;
            transientGate.notifyListener();
        }
    };

    public TransientGate(DelayableExecutor delayableExecutor, long j) {
        super(delayableExecutor);
        this.mBlockDuration = j;
        this.mExecutor = delayableExecutor;
    }

    public final void block() {
        this.mIsBlocking = true;
        notifyListener();
        ExecutorImpl.ExecutionToken executionToken = this.mCancelReset;
        if (executionToken != null) {
            executionToken.run();
            this.mCancelReset = null;
        }
        this.mCancelReset = this.mExecutor.executeDelayed(this.mBlockDuration, this.mResetGate);
    }

    public final boolean isBlocked() {
        return this.mIsBlocking;
    }
}
