package com.google.android.systemui.columbus.legacy.gates;

import java.util.concurrent.CancellationException;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
