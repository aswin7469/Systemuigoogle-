package com.google.android.systemui.elmyra;

import android.os.IBinder;
import android.os.Parcel;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class IElmyraServiceGestureListener$Stub$Proxy implements IElmyraServiceGestureListener {
    public IBinder mRemote;

    public final IBinder asBinder() {
        return this.mRemote;
    }

    public final void onGestureDetected() {
        Parcel obtain = Parcel.obtain(this.mRemote);
        try {
            obtain.writeInterfaceToken("com.google.android.systemui.elmyra.IElmyraServiceGestureListener");
            this.mRemote.transact(2, obtain, (Parcel) null, 1);
        } finally {
            obtain.recycle();
        }
    }

    public final void onGestureProgress(int i, float f) {
        Parcel obtain = Parcel.obtain(this.mRemote);
        try {
            obtain.writeInterfaceToken("com.google.android.systemui.elmyra.IElmyraServiceGestureListener");
            obtain.writeFloat(f);
            obtain.writeInt(i);
            this.mRemote.transact(1, obtain, (Parcel) null, 1);
        } finally {
            obtain.recycle();
        }
    }
}
