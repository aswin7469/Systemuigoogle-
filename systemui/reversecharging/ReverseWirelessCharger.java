package com.google.android.systemui.reversecharging;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Process;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.ServiceSpecificException;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import vendor.google.wireless_charger.IWirelessCharger;
import vendor.google.wireless_charger.IWirelessChargerRtxStatusCallback;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ReverseWirelessCharger extends IWirelessChargerRtxStatusCallback.Stub implements IBinder.DeathRecipient {
    public static final boolean DEBUG = Log.isLoggable("ReverseWirelessCharger", 3);
    public final Context mContext;
    public final ArrayList mIsDockPresentCallbacks = new ArrayList();
    public final Object mLock = new Object();
    public final ArrayList mRtxInformationCallbacks = new ArrayList();
    public final ArrayList mRtxStatusCallbacks = new ArrayList();
    public IWirelessCharger mWirelessCharger;

    public ReverseWirelessCharger(Context context) {
        markVintfStability();
        attachInterface(this, IWirelessChargerRtxStatusCallback.DESCRIPTOR);
        this.mContext = context;
    }

    public final void binderDied() {
        Log.i("ReverseWirelessCharger", "serviceDied");
        this.mWirelessCharger = null;
    }

    public final void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.printf("rtx callback in [%d]%s\n", new Object[]{Integer.valueOf(Process.myPid()), this.mContext.getPackageName()});
    }

    /* JADX WARNING: type inference failed for: r4v6, types: [java.lang.Object, vendor.google.wireless_charger.IWirelessCharger$Stub$Proxy] */
    public final boolean initHALInterface() {
        IWirelessCharger iWirelessCharger;
        if (this.mWirelessCharger != null) {
            return true;
        }
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
            try {
                service.linkToDeath(this, 0);
                Log.i("ReverseWirelessCharger", "mWirelessCharger service connected!!!!");
            } catch (RemoteException unused) {
                Log.w("ReverseWirelessCharger", "Can't link death recipient to HAL");
                this.mWirelessCharger = null;
            }
        }
        IWirelessCharger iWirelessCharger2 = this.mWirelessCharger;
        if (iWirelessCharger2 == null) {
            return false;
        }
        try {
            ((IWirelessCharger.Stub.Proxy) iWirelessCharger2).registerRtxCallback(this);
        } catch (ServiceSpecificException e) {
            if (e.errorCode == 5) {
                Log.d("ReverseWirelessCharger", "RtxCallback is already registered...");
            } else {
                Log.w("ReverseWirelessCharger", "RtxCallback registration error: " + e.errorCode);
            }
        } catch (Exception e2) {
            Log.w("ReverseWirelessCharger", "registerRtxCallback fail: ", e2);
        }
        if (this.mWirelessCharger != null) {
            return true;
        }
        return false;
    }
}
