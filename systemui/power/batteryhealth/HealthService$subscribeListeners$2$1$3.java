package com.google.android.systemui.power.batteryhealth;

import android.content.SharedPreferences;
import android.os.Parcel;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class HealthService$subscribeListeners$2$1$3 extends Lambda implements Function1 {
    final /* synthetic */ Pair $pair;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HealthService$subscribeListeners$2$1$3(Pair pair) {
        super(1);
        this.$pair = pair;
    }

    /* JADX INFO: finally extract failed */
    public final Object invoke(Object obj) {
        int i = ((SharedPreferences) this.$pair.getFirst()).getInt("capacity_index", -1);
        IHealthListener$Stub$Proxy iHealthListener$Stub$Proxy = (IHealthListener$Stub$Proxy) ((IHealthListener) obj);
        Parcel obtain = Parcel.obtain(iHealthListener$Stub$Proxy.mRemote);
        try {
            obtain.writeInterfaceToken("com.google.android.systemui.power.batteryhealth.IHealthListener");
            obtain.writeInt(i);
            iHealthListener$Stub$Proxy.mRemote.transact(3, obtain, (Parcel) null, 1);
            obtain.recycle();
            return Unit.INSTANCE;
        } catch (Throwable th) {
            obtain.recycle();
            throw th;
        }
    }
}
