package com.google.android.systemui.power;

import android.content.Context;
import com.google.android.systemui.power.batteryevent.aidl.IBatteryEventService;
import com.google.android.systemui.power.batteryevent.aidl.SurfaceType;
import java.util.ArrayList;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class BatteryEventClient {
    public final CoroutineDispatcher backgroundDispatcher;
    public String callerTag = "--";
    public final BatteryEventClient$connection$1 connection = new BatteryEventClient$connection$1(this);
    public final Context context;
    public final CoroutineScope coroutineScope;
    public final Function3 emptyCallback;
    public final BatteryEventClient$listener$1 listener = new BatteryEventClient$listener$1(this);
    public final BatteryEventClient$logWithCaller$1 logWithCaller = new BatteryEventClient$logWithCaller$1(this);
    public Function3 onBatteryEventUpdate;
    public IBatteryEventService service;
    public final ArrayList subscribedBatteryEvents = new ArrayList();
    public SurfaceType surfaceType;

    public BatteryEventClient(Context context2, CoroutineDispatcher coroutineDispatcher, CoroutineScope coroutineScope2) {
        this.context = context2;
        this.coroutineScope = coroutineScope2;
        this.backgroundDispatcher = coroutineDispatcher;
        BatteryEventClient$emptyCallback$1 batteryEventClient$emptyCallback$1 = new BatteryEventClient$emptyCallback$1(this);
        this.emptyCallback = batteryEventClient$emptyCallback$1;
        this.onBatteryEventUpdate = batteryEventClient$emptyCallback$1;
    }
}
