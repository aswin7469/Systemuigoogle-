package com.google.android.systemui.power;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import com.google.android.systemui.power.batteryevent.aidl.BatteryEventType;
import com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class BatteryEventClient$listener$1 extends Binder implements IBatteryEventsListener {
    public final /* synthetic */ BatteryEventClient this$0;

    public BatteryEventClient$listener$1(BatteryEventClient batteryEventClient) {
        this.this$0 = batteryEventClient;
        attachInterface(this, "com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener");
    }

    public final void onBatteryEventChanged(int i, int i2, List list) {
        BatteryEventClient$logWithCaller$1 batteryEventClient$logWithCaller$1 = this.this$0.logWithCaller;
        batteryEventClient$logWithCaller$1.d("onBatteryEventChanged: " + list + ", batteryLevel: " + i);
        Function3 function3 = this.this$0.onBatteryEventUpdate;
        if (list == null) {
            list = EmptyList.INSTANCE;
        }
        function3.invoke(list, Integer.valueOf(i), Integer.valueOf(i2));
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
            onBatteryEventChanged(readInt, readInt2, createTypedArrayList);
            parcel2.writeNoException();
            return true;
        }
    }

    public final IBinder asBinder() {
        return this;
    }
}
