package com.google.android.systemui.elmyra;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.systemui.elmyra.actions.ServiceAction;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public interface IElmyraServiceListener extends IInterface {
    void setListener(IBinder iBinder, IBinder iBinder2);

    void triggerAction();

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public abstract class Stub extends Binder implements IElmyraServiceListener {

        /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
        public final class Proxy implements IElmyraServiceListener {
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

        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface("com.google.android.systemui.elmyra.IElmyraServiceListener");
            }
            if (i == 1598968902) {
                parcel2.writeString("com.google.android.systemui.elmyra.IElmyraServiceListener");
                return true;
            }
            if (i == 1) {
                IBinder readStrongBinder = parcel.readStrongBinder();
                IBinder readStrongBinder2 = parcel.readStrongBinder();
                parcel.enforceNoDataAvail();
                ((ServiceAction.ElmyraServiceListener) this).setListener(readStrongBinder, readStrongBinder2);
            } else if (i != 2) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                ((ServiceAction.ElmyraServiceListener) this).triggerAction();
            }
            return true;
        }

        public final IBinder asBinder() {
            return this;
        }
    }
}
