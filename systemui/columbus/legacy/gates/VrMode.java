package com.google.android.systemui.columbus.legacy.gates;

import android.os.RemoteException;
import android.service.vr.IVrManager;
import android.util.Log;
import dagger.Lazy;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
