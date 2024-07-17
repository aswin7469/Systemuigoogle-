package com.google.android.systemui.qs.launcher;

import android.os.IBinder;
import android.os.Parcel;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ILauncherTileListener$Stub$Proxy implements ILauncherTileListener {
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
