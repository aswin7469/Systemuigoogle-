package com.google.android.systemui.assist.uihints;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.SystemClock;
import android.util.Log;
import android.widget.ImageView;
import com.google.android.systemui.assist.uihints.LightnessProvider;
import java.util.Iterator;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class LightnessProvider$1$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ LightnessProvider.AnonymousClass1 f$0;
    public final /* synthetic */ float f$1;

    public /* synthetic */ LightnessProvider$1$$ExternalSyntheticLambda0(LightnessProvider.AnonymousClass1 r1, float f) {
        this.f$0 = r1;
        this.f$1 = f;
    }

    public final void run() {
        PorterDuff.Mode mode;
        LightnessProvider.AnonymousClass1 r0 = this.f$0;
        float f = this.f$1;
        LightnessProvider lightnessProvider = LightnessProvider.this;
        NgaUiController$$ExternalSyntheticLambda6 ngaUiController$$ExternalSyntheticLambda6 = lightnessProvider.mListener;
        if (ngaUiController$$ExternalSyntheticLambda6 != null && !lightnessProvider.mMuted) {
            NgaUiController ngaUiController = ngaUiController$$ExternalSyntheticLambda6.f$0;
            if (ngaUiController.mColorMonitoringStart > 0) {
                long elapsedRealtime = SystemClock.elapsedRealtime() - ngaUiController.mColorMonitoringStart;
                if (NgaUiController.VERBOSE) {
                    Log.d("NgaUiController", "Got lightness update (" + f + ") after " + elapsedRealtime + " ms");
                }
                ngaUiController.mColorMonitoringStart = 0;
            }
            IconController iconController = ngaUiController.mIconController;
            boolean z = true;
            iconController.mHasAccurateLuma = true;
            iconController.maybeUpdateIconVisibility(iconController.mKeyboardIcon, false);
            iconController.maybeUpdateIconVisibility(iconController.mZeroStateIcon, false);
            GlowController glowController = ngaUiController.mGlowController;
            glowController.getClass();
            int i = (f > 0.4f ? 1 : (f == 0.4f ? 0 : -1));
            if (i <= 0) {
                mode = PorterDuff.Mode.LIGHTEN;
            } else {
                mode = PorterDuff.Mode.SRC_OVER;
            }
            GlowView glowView = glowController.mGlowView;
            glowView.mPaint.setXfermode(new PorterDuffXfermode(mode));
            Iterator it = glowView.mGlowImageViews.iterator();
            while (it.hasNext()) {
                ((ImageView) it.next()).setLayerPaint(glowView.mPaint);
            }
            glowController.mMedianLightness = f;
            ScrimController scrimController = ngaUiController.mScrimController;
            scrimController.mHaveAccurateLightness = true;
            scrimController.mMedianLightness = f;
            scrimController.refresh();
            TranscriptionController transcriptionController = ngaUiController.mTranscriptionController;
            if (!transcriptionController.mHasAccurateBackground) {
                transcriptionController.mHasAccurateBackground = true;
                transcriptionController.maybeSetState();
            }
            if (i > 0) {
                z = false;
            }
            if (ngaUiController.mHasDarkBackground != z) {
                ngaUiController.mHasDarkBackground = z;
                ngaUiController.dispatchHasDarkBackground();
            }
            ngaUiController.refresh$1();
        }
    }
}
