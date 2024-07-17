package com.google.android.systemui.elmyra;

import android.os.IBinder;
import android.os.Parcel;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class IElmyraServiceListener$Stub$Proxy implements IElmyraServiceListener {
    public IBinder mRemote;

    public final IBinder asBinder() {
        return this.mRemote;
    }

    public final void setListener(IBinder iBinder, IBinder iBinder2) {
        Parcel obtain = Parcel.obtain(this.mRemote);
        try {
            obtain.writeInterfaceToken("com.google.android.systemui.elmyra.IElmyraServiceListener");
            obtain.writeStrongBinder(iBinder);
            obtain.writeStrongBinder(iBinder2);
            this.mRemote.transact(1, obtain, (Parcel) null, 1);
        } finally {
            obtain.recycle();
        }
    }

    public final void triggerAction() {
        Parcel obtain = Parcel.obtain(this.mRemote);
        try {
            obtain.writeInterfaceToken("com.google.android.systemui.elmyra.IElmyraServiceListener");
            this.mRemote.transact(2, obtain, (Parcel) null, 1);
        } finally {
            obtain.recycle();
        }
    }
}
