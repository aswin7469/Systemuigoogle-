package com.google.android.systemui.elmyra.gates;

import android.os.RemoteException;
import android.service.vr.IVrManager;
import android.service.vr.IVrStateCallbacks;
import android.util.Log;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class VrMode extends Gate {
    public boolean mInVrMode;
    public final IVrManager mVrManager;
    public final AnonymousClass1 mVrStateCallbacks = new IVrStateCallbacks.Stub() {
        public final void onVrStateChanged(boolean z) {
            VrMode vrMode = VrMode.this;
            if (z != vrMode.mInVrMode) {
                vrMode.mInVrMode = z;
                vrMode.notifyListener();
            }
        }
    };

    public VrMode(Executor executor, IVrManager iVrManager) {
        super(executor);
        this.mVrManager = iVrManager;
    }

    public final boolean isBlocked() {
        return this.mInVrMode;
    }

    public final void onActivate() {
        IVrManager iVrManager = this.mVrManager;
        if (iVrManager != null) {
            try {
                this.mInVrMode = iVrManager.getVrModeState();
                iVrManager.registerListener(this.mVrStateCallbacks);
            } catch (RemoteException e) {
                Log.e("Elmyra/VrMode", "Could not register IVrManager listener", e);
                this.mInVrMode = false;
            }
        }
    }

    public final void onDeactivate() {
        IVrManager iVrManager = this.mVrManager;
        if (iVrManager != null) {
            try {
                iVrManager.unregisterListener(this.mVrStateCallbacks);
            } catch (RemoteException e) {
                Log.e("Elmyra/VrMode", "Could not unregister IVrManager listener", e);
                this.mInVrMode = false;
            }
        }
    }
}
