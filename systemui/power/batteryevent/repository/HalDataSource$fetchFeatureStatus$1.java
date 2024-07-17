package com.google.android.systemui.power.batteryevent.repository;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import vendor.google.google_battery.IGoogleBattery;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final class HalDataSource$fetchFeatureStatus$1 extends Lambda implements Function1 {
    final /* synthetic */ int $feature;
    final /* synthetic */ HalDataSource this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HalDataSource$fetchFeatureStatus$1(HalDataSource halDataSource, int i) {
        super(1);
        this.this$0 = halDataSource;
        this.$feature = i;
    }

    public final Object invoke(Object obj) {
        return this.this$0.fetchFeatureStatus((IGoogleBattery) obj, this.$feature, false);
    }
}
