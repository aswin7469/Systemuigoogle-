package com.google.android.systemui.columbus.legacy.sensors.config;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class GestureConfiguration$adjustmentCallback$1 extends Lambda implements Function1 {
    final /* synthetic */ GestureConfiguration this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GestureConfiguration$adjustmentCallback$1(GestureConfiguration gestureConfiguration) {
        super(1);
        this.this$0 = gestureConfiguration;
    }

    public final Object invoke(Object obj) {
        LowSensitivitySettingAdjustment lowSensitivitySettingAdjustment = (LowSensitivitySettingAdjustment) obj;
        this.this$0.updateSensitivity();
        return Unit.INSTANCE;
    }
}
