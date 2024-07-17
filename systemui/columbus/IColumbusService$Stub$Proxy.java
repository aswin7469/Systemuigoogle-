package com.google.android.systemui.columbus;

import android.os.IBinder;
import android.os.Parcel;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class IColumbusService$Stub$Proxy implements IColumbusService {
    public IBinder mRemote;

    public final IBinder asBinder() {
        return this.mRemote;
    }

    public final void registerServiceListener(IBinder iBinder, IBinder iBinder2) {
        Parcel obtain = Parcel.obtain(this.mRemote);
        try {
            obtain.writeInterfaceToken("com.google.android.systemui.columbus.IColumbusService");
            obtain.writeStrongBinder(iBinder);
            obtain.writeStrongBinder(iBinder2);
            this.mRemote.transact(2, obtain, (Parcel) null, 1);
        } finally {
            obtain.recycle();
        }
    }
}
