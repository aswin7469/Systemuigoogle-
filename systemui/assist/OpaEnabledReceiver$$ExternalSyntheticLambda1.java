package com.google.android.systemui.assist;

import android.content.BroadcastReceiver;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class OpaEnabledReceiver$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ OpaEnabledReceiver$$ExternalSyntheticLambda1(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                OpaEnabledReceiver opaEnabledReceiver = (OpaEnabledReceiver) obj;
                opaEnabledReceiver.dispatchOpaEnabledState(opaEnabledReceiver.mContext);
                return;
            default:
                ((BroadcastReceiver.PendingResult) obj).finish();
                return;
        }
    }
}
