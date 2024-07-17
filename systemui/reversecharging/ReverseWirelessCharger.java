package com.google.android.systemui.reversecharging;

import android.os.IBinder;
import android.os.IInterface;
import android.os.ServiceManager;
import android.os.ServiceSpecificException;
import android.util.Log;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import java.util.ArrayList;
import vendor.google.wireless_charger.IWirelessCharger;
import vendor.google.wireless_charger.IWirelessChargerRtxStatusCallback;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ReverseWirelessCharger extends IWirelessChargerRtxStatusCallback.Stub implements IBinder.DeathRecipient {
    public static final boolean DEBUG = Log.isLoggable("ReverseWirelessCharger", 3);
    public final String mCallbackIdentifier = AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("default_callback_", Integer.toHexString(hashCode()));
    public final ArrayList mIsDockPresentCallbacks = new ArrayList();
    public final Object mLock = new Object();
    public final ArrayList mRtxInformationCallbacks = new ArrayList();
    public final ArrayList mRtxStatusCallbacks = new ArrayList();
    public IWirelessCharger mWirelessCharger;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public interface ReverseChargingChangeListener {
    }

    public ReverseWirelessCharger() {
        markVintfStability();
        attachInterface(this, IWirelessChargerRtxStatusCallback.DESCRIPTOR);
    }

    public final void binderDied() {
        Log.i("ReverseWirelessCharger", "serviceDied");
        this.mWirelessCharger = null;
    }

    /* JADX WARNING: type inference failed for: r4v4, types: [java.lang.Object, vendor.google.wireless_charger.IWirelessCharger$Stub$Proxy] */
    public final void initHALInterface() {
        IWirelessCharger iWirelessCharger;
        if (this.mWirelessCharger == null) {
            try {
                IBinder service = ServiceManager.getService("vendor.google.wireless_charger.IWirelessCharger/default");
                if (service != null) {
                    IInterface queryLocalInterface = service.queryLocalInterface(IWirelessCharger.DESCRIPTOR);
                    if (queryLocalInterface == null || !(queryLocalInterface instanceof IWirelessCharger)) {
                        ? obj = new Object();
                        obj.mRemote = service;
                        iWirelessCharger = obj;
                    } else {
                        iWirelessCharger = (IWirelessCharger) queryLocalInterface;
                    }
                    this.mWirelessCharger = iWirelessCharger;
                    service.linkToDeath(this, 0);
                    Log.i("ReverseWirelessCharger", "mWirelessCharger service connected!!!!");
                }
                service.linkToDeath(this, 0);
                Log.d("ReverseWirelessCharger", "Register RtxCallback as " + this.mCallbackIdentifier);
                ((IWirelessCharger.Stub.Proxy) this.mWirelessCharger).unregisterRtxCallback(this);
                ((IWirelessCharger.Stub.Proxy) this.mWirelessCharger).registerRtxCallback(this);
            } catch (ServiceSpecificException e) {
                if (e.errorCode == 5) {
                    Log.d("ReverseWirelessCharger", "RtxCallback is already registered...");
                    return;
                }
                throw e;
            } catch (Exception e2) {
                Log.i("ReverseWirelessCharger", "no wireless charger hal found: " + e2.getMessage(), e2);
                this.mWirelessCharger = null;
            }
        }
    }
}
