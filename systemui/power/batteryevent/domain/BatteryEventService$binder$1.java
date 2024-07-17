package com.google.android.systemui.power.batteryevent.domain;

import android.os.Binder;
import android.os.Parcel;
import android.util.Log;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import com.google.android.systemui.power.batteryevent.aidl.IBatteryEventService;
import com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener;
import com.google.android.systemui.power.batteryevent.aidl.SurfaceType;
import com.google.android.systemui.power.batteryevent.domain.BatteryEventService;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.EmptySet;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class BatteryEventService$binder$1 extends IBatteryEventService.Stub {
    public final /* synthetic */ BatteryEventService this$0;

    public BatteryEventService$binder$1(BatteryEventService batteryEventService) {
        this.this$0 = batteryEventService;
        attachInterface(this, "com.google.android.systemui.power.batteryevent.aidl.IBatteryEventService");
    }

    public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        BatteryEventService batteryEventService = this.this$0;
        Set set = BatteryEventService.supportedCallers;
        batteryEventService.getClass();
        int callingUid = Binder.getCallingUid();
        Log.d("BatteryEventService", "ensureSupportedCallers: uid=" + callingUid);
        String[] packagesForUid = batteryEventService.getApplicationContext().getPackageManager().getPackagesForUid(callingUid);
        if (packagesForUid != null) {
            int length = packagesForUid.length;
            int i3 = 0;
            while (i3 < length) {
                if (!BatteryEventService.supportedCallers.contains(packagesForUid[i3])) {
                    i3++;
                }
            }
            throw new SecurityException(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("ensureSupportedCallers: ", Arrays.toString(packagesForUid)));
        }
        return super.onTransact(i, parcel, parcel2, i2);
    }

    public final void registerBatteryEventsCallback(IBatteryEventsListener iBatteryEventsListener, List list, SurfaceType surfaceType) {
        Set set;
        if (list == null || (set = CollectionsKt___CollectionsKt.toSet(list)) == null) {
            set = EmptySet.INSTANCE;
        }
        this.this$0.aidlBatteryEventsCallbackListener.register(iBatteryEventsListener, new BatteryEventService.BatteryEventsCallbackData(set, surfaceType));
    }
}
