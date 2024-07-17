package com.google.android.systemui.power.batteryevent.repository;

import android.content.Context;
import android.os.PowerManager;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
