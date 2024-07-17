package com.google.android.systemui.columbus;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.systemui.columbus.legacy.actions.ServiceAction;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public interface IColumbusServiceListener extends IInterface {
    void setListener(IBinder iBinder, IBinder iBinder2);

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public abstract class Stub extends Binder implements IColumbusServiceListener {

        /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
        public final class Proxy implements IColumbusServiceListener {
            public IBinder mRemote;

            public final IBinder asBinder() {
                return this.mRemote;
            }

            public final void setListener(IBinder iBinder, IBinder iBinder2) {
                Parcel obtain = Parcel.obtain(this.mRemote);
                try {
                    obtain.writeInterfaceToken("com.google.android.systemui.columbus.IColumbusServiceListener");
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeStrongBinder(iBinder2);
                    this.mRemote.transact(1, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }
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
                ((ServiceAction.ColumbusServiceListener) this).setListener(readStrongBinder, readStrongBinder2);
                return true;
            }
        }

        public final IBinder asBinder() {
            return this;
        }
    }
}
