package com.google.android.systemui.statusbar.policy;

import com.android.systemui.statusbar.policy.BatteryController;
import java.util.function.Consumer;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class BatteryControllerImplGoogle$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ BatteryControllerImplGoogle f$0;

    public /* synthetic */ BatteryControllerImplGoogle$$ExternalSyntheticLambda0(BatteryControllerImplGoogle batteryControllerImplGoogle, int i) {
        this.$r8$classId = i;
        this.f$0 = batteryControllerImplGoogle;
    }

    public final void accept(Object obj) {
        int i = this.$r8$classId;
        BatteryControllerImplGoogle batteryControllerImplGoogle = this.f$0;
        BatteryController.BatteryStateChangeCallback batteryStateChangeCallback = (BatteryController.BatteryStateChangeCallback) obj;
        switch (i) {
            case 0:
                batteryStateChangeCallback.onReverseChanged(batteryControllerImplGoogle.mRtxLevel, batteryControllerImplGoogle.mName, batteryControllerImplGoogle.mReverse);
                return;
            default:
                batteryStateChangeCallback.onExtremeBatterySaverChanged(batteryControllerImplGoogle.mExtremeSaver);
                return;
        }
    }
}
