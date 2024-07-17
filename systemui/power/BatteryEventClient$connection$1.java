package com.google.android.systemui.power;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.systemui.power.batteryevent.aidl.IBatteryEventService;
import com.google.android.systemui.power.batteryevent.aidl.SurfaceType;
import com.google.android.systemui.power.batteryevent.domain.BatteryEventService$binder$1;
import kotlin.Unit;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class BatteryEventClient$connection$1 implements ServiceConnection {
    public final /* synthetic */ BatteryEventClient this$0;

    public BatteryEventClient$connection$1(BatteryEventClient batteryEventClient) {
        this.this$0 = batteryEventClient;
    }

    /* JADX WARNING: type inference failed for: r2v4, types: [com.google.android.systemui.power.batteryevent.aidl.IBatteryEventService$Stub$Proxy, java.lang.Object] */
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        IBatteryEventService iBatteryEventService;
        Unit unit;
        BatteryEventClient batteryEventClient = this.this$0;
        int i = BatteryEventService$binder$1.$r8$clinit;
        String str = null;
        if (iBinder == null) {
            iBatteryEventService = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.systemui.power.batteryevent.aidl.IBatteryEventService");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IBatteryEventService)) {
                ? obj = new Object();
                obj.mRemote = iBinder;
                iBatteryEventService = obj;
            } else {
                iBatteryEventService = (IBatteryEventService) queryLocalInterface;
            }
        }
        batteryEventClient.service = iBatteryEventService;
        try {
            BatteryEventClient batteryEventClient2 = this.this$0;
            IBatteryEventService iBatteryEventService2 = batteryEventClient2.service;
            if (iBatteryEventService2 != null) {
                iBatteryEventService2.registerBatteryEventsCallback(batteryEventClient2.listener, batteryEventClient2.subscribedBatteryEvents, batteryEventClient2.surfaceType);
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            if (unit == null) {
                BatteryEventClient batteryEventClient3 = this.this$0;
                BatteryEventClient$logWithCaller$1 batteryEventClient$logWithCaller$1 = batteryEventClient3.logWithCaller;
                SurfaceType surfaceType = batteryEventClient3.surfaceType;
                if (surfaceType != null) {
                    str = surfaceType.name();
                }
                batteryEventClient$logWithCaller$1.w("bound service for " + str + " failed");
            }
        } catch (RemoteException e) {
            Log.e("BatteryEventClient", "[" + this.this$0.logWithCaller.this$0.callerTag + "] unexpected exception for registerBatteryEventCallback", e);
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        this.this$0.logWithCaller.d("onServiceDisconnected");
        BatteryEventClient batteryEventClient = this.this$0;
        batteryEventClient.callerTag = "--";
        batteryEventClient.onBatteryEventUpdate = batteryEventClient.emptyCallback;
        batteryEventClient.subscribedBatteryEvents.clear();
        BatteryEventClient batteryEventClient2 = this.this$0;
        batteryEventClient2.surfaceType = null;
        batteryEventClient2.service = null;
    }
}
