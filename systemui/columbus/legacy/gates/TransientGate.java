package com.google.android.systemui.columbus.legacy.gates;

import java.util.concurrent.CancellationException;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class TransientGate extends Gate {
    public Job currentJob;

    public final void blockForMillis(long j) {
        Job job = this.currentJob;
        if (job != null) {
            job.cancel((CancellationException) null);
        }
        this.currentJob = BuildersKt.launch$default(this.coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new TransientGate$blockForMillis$1(this, j, (Continuation) null), 3);
    }
}
