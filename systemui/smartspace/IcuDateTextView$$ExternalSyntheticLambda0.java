package com.google.android.systemui.smartspace;

import android.os.SystemClock;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class IcuDateTextView$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ IcuDateTextView f$0;

    public /* synthetic */ IcuDateTextView$$ExternalSyntheticLambda0(IcuDateTextView icuDateTextView) {
        this.f$0 = icuDateTextView;
    }

    public final void run() {
        IcuDateTextView icuDateTextView = this.f$0;
        int i = IcuDateTextView.$r8$clinit;
        icuDateTextView.onTimeChanged(false);
        if (icuDateTextView.mHandler != null) {
            long uptimeMillis = SystemClock.uptimeMillis();
            icuDateTextView.mHandler.postAtTime(icuDateTextView.mTicker, (1000 - (uptimeMillis % 1000)) + uptimeMillis);
        }
    }
}
