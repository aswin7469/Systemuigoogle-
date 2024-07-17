package com.google.android.systemui.assist.uihints;

import android.metrics.LogMaker;
import android.util.Log;
import android.util.MathUtils;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.assist.AssistantSessionEvent;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class NgaUiController$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ NgaUiController$$ExternalSyntheticLambda1(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                ((NgaUiController) obj).closeNgaUi();
                return;
            case 1:
                ((NgaUiController) obj).closeNgaUi();
                return;
            case 2:
                NgaUiController ngaUiController = (NgaUiController) obj;
                if (ngaUiController.mShowingAssistUi) {
                    Log.e("NgaUiController", "Timed out");
                    ngaUiController.mAssistLogger.reportAssistantSessionEvent(AssistantSessionEvent.ASSISTANT_SESSION_TIMEOUT_DISMISS);
                    ngaUiController.closeNgaUi();
                    MetricsLogger.action(new LogMaker(1716).setType(5).setSubtype(4));
                    return;
                }
                return;
            default:
                NgaUiController ngaUiController2 = NgaUiController.this;
                ngaUiController2.mInvocationInProgress = false;
                ngaUiController2.mInvocationLightsView.hide();
                ngaUiController2.mLastInvocationProgress = 0.0f;
                ScrimController scrimController = ngaUiController2.mScrimController;
                scrimController.getClass();
                float constrain = MathUtils.constrain(0.0f, 0.0f, 1.0f);
                if (scrimController.mInvocationProgress != constrain) {
                    scrimController.mInvocationProgress = constrain;
                    scrimController.refresh$1();
                }
                ngaUiController2.refresh$2();
                return;
        }
    }
}
