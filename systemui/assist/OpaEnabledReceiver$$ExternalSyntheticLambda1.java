package com.google.android.systemui.assist;

import android.content.BroadcastReceiver;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
