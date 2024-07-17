package com.google.android.systemui.power.batteryevent.aidl;

import android.os.IBinder;
import android.os.Parcel;
import java.util.List;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class IBatteryEventsListener$Stub$Proxy implements IBatteryEventsListener {
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
