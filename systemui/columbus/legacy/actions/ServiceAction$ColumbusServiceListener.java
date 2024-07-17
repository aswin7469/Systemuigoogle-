package com.google.android.systemui.columbus.legacy.actions;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.systemui.columbus.IColumbusServiceGestureListener$Stub$Proxy;
import com.google.android.systemui.columbus.IColumbusServiceListener;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.collections.ArraysKt;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ServiceAction$ColumbusServiceListener extends Binder implements IColumbusServiceListener {
    public final /* synthetic */ SettingsAction this$0;

    public ServiceAction$ColumbusServiceListener(SettingsAction settingsAction) {
        this.this$0 = settingsAction;
        attachInterface(this, "com.google.android.systemui.columbus.IColumbusServiceListener");
    }

    public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i >= 1 && i <= 16777215) {
            parcel.enforceInterface("com.google.android.systemui.columbus.IColumbusServiceListener");
        }
        if (i == 1598968902) {
            parcel2.writeString("com.google.android.systemui.columbus.IColumbusServiceListener");
            return true;
        } else if (i != 1) {
            return super.onTransact(i, parcel, parcel2, i2);
        } else {
            IBinder readStrongBinder = parcel.readStrongBinder();
            IBinder readStrongBinder2 = parcel.readStrongBinder();
            parcel.enforceNoDataAvail();
            setListener(readStrongBinder, readStrongBinder2);
            return true;
        }
    }

    /* JADX WARNING: type inference failed for: r4v2, types: [com.google.android.systemui.columbus.IColumbusServiceGestureListener$Stub$Proxy, java.lang.Object] */
    public final void setListener(IBinder iBinder, IBinder iBinder2) {
        IColumbusServiceGestureListener$Stub$Proxy iColumbusServiceGestureListener$Stub$Proxy;
        Object obj;
        SettingsAction settingsAction = this.this$0;
        String[] packagesForUid = settingsAction.context.getPackageManager().getPackagesForUid(Binder.getCallingUid());
        if (packagesForUid != null) {
            Iterator it = settingsAction.supportedCallerPackages.iterator();
            while (true) {
                iColumbusServiceGestureListener$Stub$Proxy = null;
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (ArraysKt.contains((Object[]) packagesForUid, (Object) (String) obj)) {
                    break;
                }
            }
            if (obj == null) {
                return;
            }
            if (iBinder2 != null || this.this$0.columbusServiceGestureListener != null) {
                SettingsAction settingsAction2 = this.this$0;
                if (iBinder2 != null) {
                    Object queryLocalInterface = iBinder2.queryLocalInterface("com.google.android.systemui.columbus.IColumbusServiceGestureListener");
                    if (queryLocalInterface == null || !(queryLocalInterface instanceof IColumbusServiceGestureListener$Stub$Proxy)) {
                        ? obj2 = new Object();
                        obj2.mRemote = iBinder2;
                        iColumbusServiceGestureListener$Stub$Proxy = obj2;
                    } else {
                        iColumbusServiceGestureListener$Stub$Proxy = (IColumbusServiceGestureListener$Stub$Proxy) queryLocalInterface;
                    }
                }
                settingsAction2.columbusServiceGestureListener = iColumbusServiceGestureListener$Stub$Proxy;
                this.this$0.updateAvailable$1();
                if (iBinder != null) {
                    try {
                        SettingsAction settingsAction3 = this.this$0;
                        if (iBinder2 == null) {
                            iBinder.unlinkToDeath(settingsAction3, 0);
                        } else {
                            iBinder.linkToDeath(settingsAction3, 0);
                        }
                    } catch (RemoteException e) {
                        Log.e("Columbus/ServiceAction", "RemoteException during linkToDeath", e);
                    } catch (NoSuchElementException e2) {
                        Log.e("Columbus/ServiceAction", "NoSuchElementException during linkToDeath", e2);
                    }
                }
            }
        }
    }

    public final IBinder asBinder() {
        return this;
    }
}
