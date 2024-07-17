package com.google.android.systemui.power;

import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import vendor.google.google_battery.IGoogleBattery;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class BatteryDefenderNotificationHandler$enableDockDefenderFeature$2 extends Lambda implements Function1 {
    public static final BatteryDefenderNotificationHandler$enableDockDefenderFeature$2 INSTANCE = new BatteryDefenderNotificationHandler$enableDockDefenderFeature$2();

    public BatteryDefenderNotificationHandler$enableDockDefenderFeature$2() {
        super(1);
    }

    /* JADX INFO: finally extract failed */
    public final Object invoke(Object obj) {
        Log.d("BatteryDefenderNotification", "enable dock defend");
        IGoogleBattery.Stub.Proxy proxy = (IGoogleBattery.Stub.Proxy) ((IGoogleBattery) obj);
        Parcel obtain = Parcel.obtain(proxy.mRemote);
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(IGoogleBattery.DESCRIPTOR);
            obtain.writeInt(7);
            obtain.writeBoolean(true);
            if (proxy.mRemote.transact(1, obtain, obtain2, 0)) {
                obtain2.readException();
                obtain2.recycle();
                obtain.recycle();
                return Unit.INSTANCE;
            }
            throw new RemoteException("Method setEnable is unimplemented.");
        } catch (Throwable th) {
            obtain2.recycle();
            obtain.recycle();
            throw th;
        }
    }
}
