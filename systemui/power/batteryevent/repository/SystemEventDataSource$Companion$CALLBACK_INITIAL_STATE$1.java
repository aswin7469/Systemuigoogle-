package com.google.android.systemui.power.batteryevent.repository;

import com.google.android.systemui.power.batteryevent.common.data.SystemEventData;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class SystemEventDataSource$Companion$CALLBACK_INITIAL_STATE$1 extends Lambda implements Function2 {
    public static final SystemEventDataSource$Companion$CALLBACK_INITIAL_STATE$1 INSTANCE = new SystemEventDataSource$Companion$CALLBACK_INITIAL_STATE$1();

    public SystemEventDataSource$Companion$CALLBACK_INITIAL_STATE$1() {
        super(2);
    }

    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        SystemEventData systemEventData = (SystemEventData) obj;
        List list = (List) obj2;
        return Unit.INSTANCE;
    }
}
