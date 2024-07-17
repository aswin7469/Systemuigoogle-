package com.google.android.systemui.power.batteryevent.repository;

import android.content.Context;
import android.os.PowerManager;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class FrameworkDataSource {
    public final Context context;
    public boolean lastBatterySaverState;
    public boolean lastExtremeBatterySaverState;
    public final PowerManager powerManager;

    public FrameworkDataSource(Context context2, PowerManager powerManager2) {
        this.context = context2;
        this.powerManager = powerManager2;
    }
}
