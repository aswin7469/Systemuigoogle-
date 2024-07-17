package com.google.android.systemui.qs.launcher;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ILauncherTileListener$Stub$Proxy implements IInterface {
    public IBinder mRemote;

    public final IBinder asBinder() {
        return this.mRemote;
    }

    public final void onTileUpdated(TileState tileState) {
        Parcel obtain = Parcel.obtain(this.mRemote);
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.systemui.qs.launcher.ILauncherTileListener");
            obtain.writeTypedObject(tileState, 0);
            this.mRemote.transact(1, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
