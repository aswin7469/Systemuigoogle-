package com.google.android.systemui.assist.uihints;

import android.util.Log;
import com.android.systemui.assist.AssistManager;
import dagger.Lazy;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class TimeoutManager$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ TimeoutManager f$0;
    public final /* synthetic */ Lazy f$1;

    public /* synthetic */ TimeoutManager$$ExternalSyntheticLambda0(TimeoutManager timeoutManager, Lazy lazy) {
        this.f$0 = timeoutManager;
        this.f$1 = lazy;
    }

    public final void run() {
        TimeoutManager timeoutManager = this.f$0;
        Lazy lazy = this.f$1;
        NgaUiController$$ExternalSyntheticLambda2 ngaUiController$$ExternalSyntheticLambda2 = timeoutManager.mTimeoutCallback;
        if (ngaUiController$$ExternalSyntheticLambda2 != null) {
            ((Runnable) ngaUiController$$ExternalSyntheticLambda2.f$0).run();
            return;
        }
        Log.e("TimeoutManager", "Timeout occurred, but there was no callback provided");
        ((AssistManager) lazy.get()).hideAssist();
    }
}
