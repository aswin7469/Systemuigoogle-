package com.google.android.systemui.columbus.legacy.gates;

import android.service.vr.IVrStateCallbacks;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class VrMode$vrStateCallbacks$1 extends IVrStateCallbacks.Stub {
    public final /* synthetic */ VrMode this$0;

    public VrMode$vrStateCallbacks$1(VrMode vrMode) {
        this.this$0 = vrMode;
    }

    public final void onVrStateChanged(boolean z) {
        VrMode vrMode = this.this$0;
        BuildersKt.launch$default(vrMode.coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new VrMode$vrStateCallbacks$1$onVrStateChanged$1(vrMode, z, (Continuation) null), 3);
    }
}
