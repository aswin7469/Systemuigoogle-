package com.google.android.systemui.columbus.legacy.gates;

import android.os.RemoteException;
import android.service.vr.IVrManager;
import android.util.Log;
import dagger.Lazy;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class VrMode extends Gate {
    public final CoroutineDispatcher bgDispatcher;
    public boolean inVrMode;
    public final Lazy vrManager;
    public final VrMode$vrStateCallbacks$1 vrStateCallbacks = new VrMode$vrStateCallbacks$1(this);

    public VrMode(Lazy lazy, CoroutineDispatcher coroutineDispatcher) {
        this.vrManager = lazy;
        this.bgDispatcher = coroutineDispatcher;
    }

    public final void onActivate() {
        VrMode$onActivate$1 vrMode$onActivate$1 = new VrMode$onActivate$1(this, (Continuation) null);
        BuildersKt.launch$default(this.coroutineScope, this.mainDispatcher, (CoroutineStart) null, vrMode$onActivate$1, 2);
    }

    public final void onDeactivate() {
        try {
            IVrManager iVrManager = (IVrManager) this.vrManager.get();
            if (iVrManager != null) {
                iVrManager.unregisterListener(this.vrStateCallbacks);
            }
        } catch (RemoteException e) {
            Log.e("Columbus/VrMode", "Could not unregister IVrManager listener", e);
        }
    }
}
