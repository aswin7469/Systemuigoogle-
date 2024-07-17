package com.google.android.systemui.power.batteryhealth;

import android.content.SharedPreferences;
import android.os.Parcel;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class HealthService$subscribeListeners$2$1$4 extends Lambda implements Function1 {
    final /* synthetic */ Pair $pair;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HealthService$subscribeListeners$2$1$4(Pair pair) {
        super(1);
        this.$pair = pair;
    }

    /* JADX INFO: finally extract failed */
    public final Object invoke(Object obj) {
        int i = ((SharedPreferences) this.$pair.getFirst()).getInt("health_status", -1);
        IHealthListener$Stub$Proxy iHealthListener$Stub$Proxy = (IHealthListener$Stub$Proxy) ((IHealthListener) obj);
        Parcel obtain = Parcel.obtain(iHealthListener$Stub$Proxy.mRemote);
        try {
            obtain.writeInterfaceToken("com.google.android.systemui.power.batteryhealth.IHealthListener");
            obtain.writeInt(i);
            iHealthListener$Stub$Proxy.mRemote.transact(4, obtain, (Parcel) null, 1);
            obtain.recycle();
            return Unit.INSTANCE;
        } catch (Throwable th) {
            obtain.recycle();
            throw th;
        }
    }
}
