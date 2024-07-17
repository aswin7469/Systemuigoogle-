package com.google.android.systemui.power.batteryevent.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.systemui.power.BatteryEventClient$listener$1;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public interface IBatteryEventsListener extends IInterface {
    void onBatteryEventChanged(int i, int i2, List list);

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public abstract class Stub extends Binder implements IBatteryEventsListener {

        /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
        public final class Proxy implements IBatteryEventsListener {
            public IBinder mRemote;

            public final IBinder asBinder() {
                return this.mRemote;
            }

            public final void onBatteryEventChanged(int i, int i2, List list) {
                Parcel obtain = Parcel.obtain(this.mRemote);
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener");
                    obtain.writeTypedList(list, 0);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface("com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener");
            }
            if (i == 1598968902) {
                parcel2.writeString("com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener");
                return true;
            } else if (i != 1) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                ArrayList createTypedArrayList = parcel.createTypedArrayList(BatteryEventType.CREATOR);
                int readInt = parcel.readInt();
                int readInt2 = parcel.readInt();
                parcel.enforceNoDataAvail();
                ((BatteryEventClient$listener$1) this).onBatteryEventChanged(readInt, readInt2, createTypedArrayList);
                parcel2.writeNoException();
                return true;
            }
        }

        public final IBinder asBinder() {
            return this;
        }
    }
}
