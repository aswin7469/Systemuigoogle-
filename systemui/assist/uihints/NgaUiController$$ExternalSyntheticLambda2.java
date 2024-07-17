package com.google.android.systemui.assist.uihints;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.SystemClock;
import android.util.Log;
import android.widget.ImageView;
import java.util.Iterator;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class NgaUiController$$ExternalSyntheticLambda2 {
    public final /* synthetic */ Object f$0;

    public /* synthetic */ NgaUiController$$ExternalSyntheticLambda2(Object obj) {
        this.f$0 = obj;
    }

    public final void onLightnessUpdate(float f) {
        PorterDuff.Mode mode;
        NgaUiController ngaUiController = (NgaUiController) this.f$0;
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
        iconController.maybeUpdateIconVisibility(iconController.mKeyboardIcon, iconController.mKeyboardIconRequested);
        iconController.maybeUpdateIconVisibility(iconController.mZeroStateIcon, iconController.mZerostateIconRequested);
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
        scrimController.refresh$1();
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
            ColorChangeHandler colorChangeHandler = ngaUiController.mColorChangeHandler;
            colorChangeHandler.mIsDark = z;
            colorChangeHandler.sendColor();
            ngaUiController.dispatchHasDarkBackground();
        }
        ngaUiController.refresh$2();
    }
}
