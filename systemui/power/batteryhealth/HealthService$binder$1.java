package com.google.android.systemui.power.batteryhealth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class HealthService$binder$1 extends Binder implements IInterface {
    public final /* synthetic */ HealthService this$0;

    public HealthService$binder$1(HealthService healthService) {
        this.this$0 = healthService;
        attachInterface(this, "com.google.android.systemui.power.batteryhealth.IHealthService");
    }

    /* JADX WARNING: type inference failed for: r8v3, types: [com.google.android.systemui.power.batteryhealth.IHealthListener$Stub$Proxy, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r8v8, types: [com.google.android.systemui.power.batteryhealth.IHealthListener$Stub$Proxy, java.lang.Object] */
    /* renamed from: onTransact$com$google$android$systemui$power$batteryhealth$IHealthService$Stub */
    public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        IHealthListener iHealthListener;
        IHealthListener iHealthListener2;
        if (i >= 1 && i <= 16777215) {
            parcel.enforceInterface("com.google.android.systemui.power.batteryhealth.IHealthService");
        }
        if (i == 1598968902) {
            parcel2.writeString("com.google.android.systemui.power.batteryhealth.IHealthService");
            return true;
        }
        if (i == 1) {
            HealthService$binder$1$getHealthData$1 healthService$binder$1$getHealthData$1 = new HealthService$binder$1$getHealthData$1(HealthService.access$ensureSupportedCallers(this.this$0), this.this$0, (Continuation) null);
            parcel2.writeNoException();
            parcel2.writeTypedObject((HealthData) BuildersKt.runBlocking(EmptyCoroutineContext.INSTANCE, healthService$binder$1$getHealthData$1), 1);
        } else if (i == 2) {
            IBinder readStrongBinder = parcel.readStrongBinder();
            if (readStrongBinder == null) {
                iHealthListener = null;
            } else {
                IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.systemui.power.batteryhealth.IHealthListener");
                if (queryLocalInterface == null || !(queryLocalInterface instanceof IHealthListener)) {
                    ? obj = new Object();
                    obj.mRemote = readStrongBinder;
                    iHealthListener = obj;
                } else {
                    iHealthListener = (IHealthListener) queryLocalInterface;
                }
            }
            parcel.enforceNoDataAvail();
            String[] access$ensureSupportedCallers = HealthService.access$ensureSupportedCallers(this.this$0);
            HealthService healthService = this.this$0;
            BuildersKt.launch$default(healthService.mainScope, (CoroutineContext) null, (CoroutineStart) null, new HealthService$binder$1$registerHealthListener$1(access$ensureSupportedCallers, healthService, iHealthListener, (Continuation) null), 3);
        } else if (i == 3) {
            IBinder readStrongBinder2 = parcel.readStrongBinder();
            if (readStrongBinder2 == null) {
                iHealthListener2 = null;
            } else {
                IInterface queryLocalInterface2 = readStrongBinder2.queryLocalInterface("com.google.android.systemui.power.batteryhealth.IHealthListener");
                if (queryLocalInterface2 == null || !(queryLocalInterface2 instanceof IHealthListener)) {
                    ? obj2 = new Object();
                    obj2.mRemote = readStrongBinder2;
                    iHealthListener2 = obj2;
                } else {
                    iHealthListener2 = (IHealthListener) queryLocalInterface2;
                }
            }
            parcel.enforceNoDataAvail();
            String[] access$ensureSupportedCallers2 = HealthService.access$ensureSupportedCallers(this.this$0);
            HealthService healthService2 = this.this$0;
            BuildersKt.launch$default(healthService2.mainScope, (CoroutineContext) null, (CoroutineStart) null, new HealthService$binder$1$unregisterHealthListener$1(access$ensureSupportedCallers2, healthService2, iHealthListener2, (Continuation) null), 3);
        } else if (i == 4) {
            HealthService$binder$1$getIncompatibleChargerData$1 healthService$binder$1$getIncompatibleChargerData$1 = new HealthService$binder$1$getIncompatibleChargerData$1(HealthService.access$ensureSupportedCallers(this.this$0), this.this$0, (Continuation) null);
            parcel2.writeNoException();
            parcel2.writeTypedObject((IncompatibleChargerData) BuildersKt.runBlocking(EmptyCoroutineContext.INSTANCE, healthService$binder$1$getIncompatibleChargerData$1), 1);
        } else if (i != 5) {
            return super.onTransact(i, parcel, parcel2, i2);
        } else {
            int readInt = parcel.readInt();
            parcel.enforceNoDataAvail();
            HealthService$binder$1$getHealthDataWithAlgo$1 healthService$binder$1$getHealthDataWithAlgo$1 = new HealthService$binder$1$getHealthDataWithAlgo$1(readInt, HealthService.access$ensureSupportedCallers(this.this$0), this.this$0, (Continuation) null);
            parcel2.writeNoException();
            parcel2.writeTypedObject((HealthData) BuildersKt.runBlocking(EmptyCoroutineContext.INSTANCE, healthService$binder$1$getHealthDataWithAlgo$1), 1);
        }
        return true;
    }

    public final IBinder asBinder() {
        return this;
    }
}
