package com.google.android.systemui.lowlightclock;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.RemoteException;
import android.service.dreams.DreamService;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextClock;
import android.widget.TextView;
import com.android.app.animation.Interpolators;
import com.android.dream.lowlight.LowLightTransitionCoordinator;
import com.android.internal.util.Preconditions;
import com.android.systemui.statusbar.CrossFadeHelper;
import com.google.android.systemui.lowlightclock.ChargingStatusProvider;
import com.google.hardware.pixel.display.IDisplay;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public class LowLightClockDreamService extends DreamService {
    public Animator mAnimationIn;
    public Animator mAnimationOut;
    public final LowLightClockAnimationProvider mAnimationProvider;
    public final ChargingStatusProvider mChargingStatusProvider;
    public TextView mChargingStatusTextView;
    public final IDisplay mDisplayHal;
    public boolean mIsDimBrightnessSupported = false;
    public final LowLightTransitionCoordinator mLowLightTransitionCoordinator;
    public TextClock mTextClock;

    public LowLightClockDreamService(ChargingStatusProvider chargingStatusProvider, LowLightClockAnimationProvider lowLightClockAnimationProvider, LowLightTransitionCoordinator lowLightTransitionCoordinator, IDisplay iDisplay) {
        this.mAnimationProvider = lowLightClockAnimationProvider;
        this.mDisplayHal = iDisplay;
        this.mChargingStatusProvider = chargingStatusProvider;
        this.mLowLightTransitionCoordinator = lowLightTransitionCoordinator;
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        boolean z = false;
        setInteractive(false);
        setFullscreen(true);
        setContentView(LayoutInflater.from(getApplicationContext()).inflate(2131558744, (ViewGroup) null));
        this.mTextClock = (TextClock) findViewById(2131362943);
        this.mChargingStatusTextView = (TextView) findViewById(2131362249);
        ChargingStatusProvider chargingStatusProvider = this.mChargingStatusProvider;
        LowLightClockDreamService$$ExternalSyntheticLambda0 lowLightClockDreamService$$ExternalSyntheticLambda0 = new LowLightClockDreamService$$ExternalSyntheticLambda0(this);
        if (chargingStatusProvider.mCallback == null) {
            z = true;
        }
        Preconditions.checkState(z, "ChargingStatusProvider already started!");
        chargingStatusProvider.mCallback = lowLightClockDreamService$$ExternalSyntheticLambda0;
        ChargingStatusProvider.ChargingStatusCallback chargingStatusCallback = new ChargingStatusProvider.ChargingStatusCallback();
        chargingStatusProvider.mChargingStatusCallback = chargingStatusCallback;
        chargingStatusProvider.mKeyguardUpdateMonitor.registerCallback(chargingStatusCallback);
        chargingStatusProvider.reportStatusToCallback();
        this.mLowLightTransitionCoordinator.getClass();
    }

    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Animator animator = this.mAnimationOut;
        if (animator != null) {
            animator.cancel();
        }
        ChargingStatusProvider chargingStatusProvider = this.mChargingStatusProvider;
        chargingStatusProvider.mCallback = null;
        ChargingStatusProvider.ChargingStatusCallback chargingStatusCallback = chargingStatusProvider.mChargingStatusCallback;
        if (chargingStatusCallback != null) {
            chargingStatusProvider.mKeyguardUpdateMonitor.removeCallback(chargingStatusCallback);
            chargingStatusProvider.mChargingStatusCallback = null;
        }
        this.mLowLightTransitionCoordinator.getClass();
    }

    public final void onDreamingStarted() {
        LowLightClockAnimationProvider lowLightClockAnimationProvider = this.mAnimationProvider;
        View[] viewArr = {this.mTextClock, this.mChargingStatusTextView};
        lowLightClockAnimationProvider.getClass();
        AnimatorSet animatorSet = new AnimatorSet();
        for (int i = 0; i < 2; i++) {
            View view = viewArr[i];
            CrossFadeHelper.fadeOut(view, 0.0f, false);
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{1.0f});
            ofFloat.setStartDelay(lowLightClockAnimationProvider.mAlphaAnimationInStartDelayMillis);
            ofFloat.setDuration(lowLightClockAnimationProvider.mAlphaAnimationDurationMillis);
            ofFloat.setInterpolator(Interpolators.LINEAR);
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{(float) lowLightClockAnimationProvider.mYTranslationAnimationInStartOffset, 0.0f});
            ofFloat2.setDuration(lowLightClockAnimationProvider.mYTranslationAnimationInDurationMillis);
            ofFloat2.setInterpolator(Interpolators.EMPHASIZED);
            animatorSet.playTogether(new Animator[]{ofFloat2, ofFloat});
        }
        this.mAnimationIn = animatorSet;
        animatorSet.start();
        IDisplay iDisplay = this.mDisplayHal;
        if (iDisplay != null) {
            try {
                boolean isDbmSupported = ((IDisplay.Stub.Proxy) iDisplay).isDbmSupported();
                this.mIsDimBrightnessSupported = isDbmSupported;
                if (isDbmSupported) {
                    ((IDisplay.Stub.Proxy) this.mDisplayHal).setDbmState(true);
                }
            } catch (RemoteException e) {
                Log.e("LowLightClockDreamService", "RemoteException", e);
            }
        }
    }

    public final void onDreamingStopped() {
        if (this.mIsDimBrightnessSupported) {
            try {
                ((IDisplay.Stub.Proxy) this.mDisplayHal).setDbmState(false);
            } catch (RemoteException e) {
                Log.e("LowLightClockDreamService", "RemoteException", e);
            }
        }
    }

    public final void onWakeUp() {
        Animator animator = this.mAnimationIn;
        if (animator != null) {
            animator.cancel();
        }
        LowLightClockAnimationProvider lowLightClockAnimationProvider = this.mAnimationProvider;
        View[] viewArr = {this.mTextClock, this.mChargingStatusTextView};
        lowLightClockAnimationProvider.getClass();
        AnimatorSet animatorSet = new AnimatorSet();
        for (int i = 0; i < 2; i++) {
            View view = viewArr[i];
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{0.0f});
            long j = lowLightClockAnimationProvider.mAlphaAnimationDurationMillis;
            ofFloat.setDuration(j);
            ofFloat.setInterpolator(Interpolators.LINEAR);
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{(float) lowLightClockAnimationProvider.mYTranslationAnimationInStartOffset});
            ofFloat2.setDuration(j);
            ofFloat2.setInterpolator(lowLightClockAnimationProvider.mTranslationOutInterpolator);
            animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
        }
        this.mAnimationOut = animatorSet;
        animatorSet.addListener(new AnimatorListenerAdapter() {
            public final void onAnimationEnd(Animator animator) {
                LowLightClockDreamService.super.onWakeUp();
            }
        });
        this.mAnimationOut.start();
    }
}
