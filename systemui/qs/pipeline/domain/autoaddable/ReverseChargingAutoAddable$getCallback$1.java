package com.google.android.systemui.qs.pipeline.domain.autoaddable;

import com.android.systemui.statusbar.policy.BatteryController;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ReverseChargingAutoAddable$getCallback$1 implements BatteryController.BatteryStateChangeCallback {
    public final /* synthetic */ ProducerScope $this_getCallback;
    public final /* synthetic */ ReverseChargingAutoAddable this$0;

    public ReverseChargingAutoAddable$getCallback$1(ReverseChargingAutoAddable reverseChargingAutoAddable, ProducerScope producerScope) {
        this.this$0 = reverseChargingAutoAddable;
        this.$this_getCallback = producerScope;
    }

    public final void onReverseChanged(int i, String str, boolean z) {
        if (z) {
            this.this$0.sendAdd(this.$this_getCallback);
        }
    }
}
