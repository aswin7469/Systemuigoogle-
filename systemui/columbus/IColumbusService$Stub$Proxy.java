package com.google.android.systemui.columbus;

import android.os.IBinder;
import android.os.Parcel;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
