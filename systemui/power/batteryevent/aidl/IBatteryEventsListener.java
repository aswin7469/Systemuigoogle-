package com.google.android.systemui.power.batteryevent.aidl;

import android.os.IInterface;
import java.util.List;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public interface IBatteryEventsListener extends IInterface {
    void onBatteryEventChanged(int i, int i2, List list);
}
