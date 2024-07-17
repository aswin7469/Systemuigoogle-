package com.google.android.systemui.power;

import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class BatteryEventClient$emptyCallback$1 extends Lambda implements Function3 {
    final /* synthetic */ BatteryEventClient this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BatteryEventClient$emptyCallback$1(BatteryEventClient batteryEventClient) {
        super(3);
        this.this$0 = batteryEventClient;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        List list = (List) obj;
        ((Number) obj2).intValue();
        ((Number) obj3).intValue();
        this.this$0.logWithCaller.d("No callback for battery event update");
        return Unit.INSTANCE;
    }
}
