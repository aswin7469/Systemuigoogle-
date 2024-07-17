package com.google.android.systemui.power.batteryevent.aidl;

import android.os.IBinder;
import android.os.Parcel;
import java.util.List;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class IBatteryEventService$Stub$Proxy implements IBatteryEventService {
    public IBinder mRemote;

    public final IBinder asBinder() {
        return this.mRemote;
    }

    public final void registerBatteryEventsCallback(IBatteryEventsListener iBatteryEventsListener, List list, SurfaceType surfaceType) {
        Parcel obtain = Parcel.obtain(this.mRemote);
        try {
            obtain.writeInterfaceToken("com.google.android.systemui.power.batteryevent.aidl.IBatteryEventService");
            obtain.writeStrongInterface(iBatteryEventsListener);
            obtain.writeTypedList(list, 0);
            obtain.writeTypedObject(surfaceType, 0);
            this.mRemote.transact(1, obtain, (Parcel) null, 1);
        } finally {
            obtain.recycle();
        }
    }
}
