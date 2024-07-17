package com.google.android.systemui.smartspace;

import android.os.SystemClock;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
