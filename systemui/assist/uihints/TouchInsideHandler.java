package com.google.android.systemui.assist.uihints;

import android.metrics.LogMaker;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.assist.AssistLogger;
import com.android.systemui.assist.AssistantSessionEvent;
import com.android.systemui.navigationbar.NavigationModeController;
import com.android.systemui.shared.system.QuickStepContract;
import dagger.Lazy;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class TouchInsideHandler implements View.OnClickListener, View.OnTouchListener {
    public final AssistLogger mAssistLogger;
    public Runnable mFallback;
    public boolean mInGesturalMode;

    public TouchInsideHandler(Lazy lazy, NavigationModeController navigationModeController, AssistLogger assistLogger) {
        new Handler(Looper.getMainLooper());
        this.mAssistLogger = assistLogger;
        this.mFallback = new TouchInsideHandler$$ExternalSyntheticLambda0(lazy);
        this.mInGesturalMode = QuickStepContract.isGesturalMode(navigationModeController.addListener(new TouchInsideHandler$$ExternalSyntheticLambda1(this)));
    }

    public final void onClick(View view) {
        onTouchInside();
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.mInGesturalMode) {
            if (motionEvent.getAction() == 0) {
                onTouchInside();
            }
        } else if (motionEvent.getAction() == 1) {
            onTouchInside();
        }
        return true;
    }

    public final void onTouchInside() {
        this.mFallback.run();
        this.mAssistLogger.reportAssistantSessionEvent(AssistantSessionEvent.ASSISTANT_SESSION_USER_DISMISS);
        MetricsLogger.action(new LogMaker(1716).setType(5).setSubtype(2));
    }
}
