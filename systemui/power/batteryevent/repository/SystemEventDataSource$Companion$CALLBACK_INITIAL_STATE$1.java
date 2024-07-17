package com.google.android.systemui.power.batteryevent.repository;

import com.google.android.systemui.power.batteryevent.common.data.SystemEventData;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
