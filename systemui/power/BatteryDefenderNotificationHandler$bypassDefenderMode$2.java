package com.google.android.systemui.power;

import android.os.Parcel;
import android.os.RemoteException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import vendor.google.google_battery.IGoogleBattery;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class BatteryDefenderNotificationHandler$bypassDefenderMode$2 extends Lambda implements Function1 {
    final /* synthetic */ int $defenderMode;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BatteryDefenderNotificationHandler$bypassDefenderMode$2(int i) {
        super(1);
        this.$defenderMode = i;
    }

    /* JADX INFO: finally extract failed */
    public final Object invoke(Object obj) {
        int i = this.$defenderMode;
        IGoogleBattery.Stub.Proxy proxy = (IGoogleBattery.Stub.Proxy) ((IGoogleBattery) obj);
        Parcel obtain = Parcel.obtain(proxy.mRemote);
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(IGoogleBattery.DESCRIPTOR);
            obtain.writeInt(i);
            if (proxy.mRemote.transact(22, obtain, obtain2, 0)) {
                obtain2.readException();
                obtain2.recycle();
                obtain.recycle();
                return Unit.INSTANCE;
            }
            throw new RemoteException("Method clearBatteryDefenders is unimplemented.");
        } catch (Throwable th) {
            obtain2.recycle();
            obtain.recycle();
            throw th;
        }
    }
}