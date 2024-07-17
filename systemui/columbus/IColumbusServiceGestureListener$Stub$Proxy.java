package com.google.android.systemui.columbus;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class IColumbusServiceGestureListener$Stub$Proxy implements IInterface {
    public IBinder mRemote;

    public final IBinder asBinder() {
        return this.mRemote;
    }

    public final void onTrigger() {
        Parcel obtain = Parcel.obtain(this.mRemote);
        try {
            obtain.writeInterfaceToken("com.google.android.systemui.columbus.IColumbusServiceGestureListener");
            this.mRemote.transact(1, obtain, (Parcel) null, 1);
        } finally {
            obtain.recycle();
        }
    }
}
