package com.google.android.systemui.assist.uihints;

import android.app.PendingIntent;
import android.metrics.LogMaker;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.assist.AssistLogger;
import com.android.systemui.assist.AssistantSessionEvent;
import com.android.systemui.navigationbar.NavigationModeController;
import com.android.systemui.shared.system.QuickStepContract;
import com.google.android.systemui.assist.uihints.NgaMessageHandler;
import dagger.Lazy;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class TouchInsideHandler implements NgaMessageHandler.ConfigInfoListener, View.OnClickListener, View.OnTouchListener {
    public final AssistLogger mAssistLogger;
    public Runnable mFallback;
    public boolean mGuardLocked;
    public boolean mGuarded;
    public final Handler mHandler = new Handler(Looper.getMainLooper());
    public boolean mInGesturalMode;
    public PendingIntent mTouchInside;

    public TouchInsideHandler(Lazy lazy, NavigationModeController navigationModeController, AssistLogger assistLogger) {
        this.mAssistLogger = assistLogger;
        this.mFallback = new TouchInsideHandler$$ExternalSyntheticLambda0(0, lazy);
        boolean isGesturalMode = QuickStepContract.isGesturalMode(navigationModeController.addListener(new TouchInsideHandler$$ExternalSyntheticLambda1(this)));
        this.mInGesturalMode = isGesturalMode;
        if (isGesturalMode) {
            this.mGuardLocked = false;
            this.mGuarded = false;
        }
    }

    public final void onClick(View view) {
        onTouchInside();
    }

    public final void onConfigInfo(NgaMessageHandler.ConfigInfo configInfo) {
        this.mTouchInside = configInfo.onTouchInside;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.mInGesturalMode) {
            if (motionEvent.getAction() == 0) {
                onTouchInside();
            }
        } else if (this.mGuarded && !this.mGuardLocked && motionEvent.getAction() == 0) {
            this.mGuarded = false;
        } else if (!this.mGuarded && motionEvent.getAction() == 1) {
            onTouchInside();
        }
        return true;
    }

    public final void onTouchInside() {
        PendingIntent pendingIntent = this.mTouchInside;
        if (pendingIntent != null) {
            try {
                pendingIntent.send();
            } catch (PendingIntent.CanceledException unused) {
                Log.w("TouchInsideHandler", "Touch outside PendingIntent canceled");
                this.mFallback.run();
            }
        } else {
            this.mFallback.run();
        }
        this.mAssistLogger.reportAssistantSessionEvent(AssistantSessionEvent.ASSISTANT_SESSION_USER_DISMISS);
        MetricsLogger.action(new LogMaker(1716).setType(5).setSubtype(2));
    }
}
