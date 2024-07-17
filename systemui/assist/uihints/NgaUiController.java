package com.google.android.systemui.assist.uihints;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Region;
import android.metrics.LogMaker;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;
import android.util.MathUtils;
import android.view.ViewTreeObserver;
import android.view.animation.OvershootInterpolator;
import android.view.animation.PathInterpolator;
import android.widget.ImageView;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.DejankUtils;
import com.android.systemui.assist.AssistLogger;
import com.android.systemui.assist.AssistManager;
import com.android.systemui.assist.AssistantSessionEvent;
import com.android.systemui.assist.ui.EdgeLight;
import com.android.systemui.navigationbar.NavigationBar;
import com.android.systemui.navigationbar.NavigationBarController;
import com.android.systemui.navigationbar.NavigationBarControllerImpl;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.google.android.systemui.assist.uihints.NgaMessageHandler;
import com.google.android.systemui.assist.uihints.TranscriptionController;
import com.google.android.systemui.assist.uihints.edgelights.EdgeLightsController;
import com.google.android.systemui.assist.uihints.edgelights.EdgeLightsView;
import com.google.android.systemui.assist.uihints.edgelights.mode.FullListening;
import com.google.android.systemui.assist.uihints.edgelights.mode.Gone;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class NgaUiController implements AssistManager.UiController, ViewTreeObserver.OnComputeInternalInsetsListener, StatusBarStateController.StateListener {
    public static final boolean VERBOSE;
    public static final PathInterpolator mProgressInterpolator = new PathInterpolator(0.83f, 0.0f, 0.84f, 1.0f);
    public final AssistLogger mAssistLogger;
    public final Lazy mAssistManager;
    public final AssistantPresenceHandler mAssistantPresenceHandler;
    public final AssistantWarmer mAssistantWarmer;
    public final ColorChangeHandler mColorChangeHandler;
    public long mColorMonitoringStart;
    public final Context mContext;
    public final EdgeLightsController mEdgeLightsController;
    public final FlingVelocityWrapper mFlingVelocity;
    public final GlowController mGlowController;
    public boolean mHasDarkBackground;
    public final IconController mIconController;
    public ValueAnimator mInvocationAnimator;
    public boolean mInvocationInProgress;
    public final AssistantInvocationLightsView mInvocationLightsView;
    public boolean mIsMonitoringColor;
    public float mLastInvocationProgress;
    public long mLastInvocationStartTime;
    public final LightnessProvider mLightnessProvider;
    public final NavBarFadeController mNavBarFadeController;
    public Runnable mPendingEdgeLightsModeChange;
    public final PromptView mPromptView;
    public final ScrimController mScrimController;
    public boolean mShouldKeepWakeLock;
    public boolean mShowingAssistUi;
    public final TimeoutManager mTimeoutManager;
    public final TouchInsideHandler mTouchInsideHandler;
    public final TranscriptionController mTranscriptionController;
    public final Handler mUiHandler = new Handler(Looper.getMainLooper());
    public final OverlayUiHost mUiHost;
    public final PowerManager.WakeLock mWakeLock;

    static {
        boolean z;
        Class<NgaUiController> cls = NgaUiController.class;
        String str = Build.TYPE;
        Locale locale = Locale.ROOT;
        if (str.toLowerCase(locale).contains("debug") || str.toLowerCase(locale).equals("eng")) {
            z = true;
        } else {
            z = false;
        }
        VERBOSE = z;
    }

    public NgaUiController(Context context, TimeoutManager timeoutManager, AssistantPresenceHandler assistantPresenceHandler, TouchInsideHandler touchInsideHandler, ColorChangeHandler colorChangeHandler, OverlayUiHost overlayUiHost, EdgeLightsController edgeLightsController, GlowController glowController, ScrimController scrimController, TranscriptionController transcriptionController, IconController iconController, LightnessProvider lightnessProvider, StatusBarStateController statusBarStateController, Lazy lazy, FlingVelocityWrapper flingVelocityWrapper, AssistantWarmer assistantWarmer, NavBarFadeController navBarFadeController, AssistLogger assistLogger) {
        NavigationBarController navigationBarController;
        NavigationBar defaultNavigationBar;
        Context context2 = context;
        TimeoutManager timeoutManager2 = timeoutManager;
        AssistantPresenceHandler assistantPresenceHandler2 = assistantPresenceHandler;
        TouchInsideHandler touchInsideHandler2 = touchInsideHandler;
        ColorChangeHandler colorChangeHandler2 = colorChangeHandler;
        OverlayUiHost overlayUiHost2 = overlayUiHost;
        EdgeLightsController edgeLightsController2 = edgeLightsController;
        GlowController glowController2 = glowController;
        ScrimController scrimController2 = scrimController;
        TranscriptionController transcriptionController2 = transcriptionController;
        LightnessProvider lightnessProvider2 = lightnessProvider;
        boolean z = false;
        this.mHasDarkBackground = false;
        this.mIsMonitoringColor = false;
        this.mInvocationInProgress = false;
        this.mShowingAssistUi = false;
        this.mShouldKeepWakeLock = false;
        this.mLastInvocationStartTime = 0;
        this.mLastInvocationProgress = 0.0f;
        this.mColorMonitoringStart = 0;
        this.mContext = context2;
        this.mAssistLogger = assistLogger;
        this.mColorChangeHandler = colorChangeHandler2;
        colorChangeHandler2.mIsDark = false;
        colorChangeHandler.sendColor();
        this.mTimeoutManager = timeoutManager2;
        this.mAssistantPresenceHandler = assistantPresenceHandler2;
        this.mTouchInsideHandler = touchInsideHandler2;
        this.mUiHost = overlayUiHost2;
        this.mEdgeLightsController = edgeLightsController2;
        this.mGlowController = glowController2;
        this.mScrimController = scrimController2;
        this.mTranscriptionController = transcriptionController2;
        this.mIconController = iconController;
        this.mLightnessProvider = lightnessProvider2;
        this.mAssistManager = lazy;
        this.mFlingVelocity = flingVelocityWrapper;
        this.mAssistantWarmer = assistantWarmer;
        this.mNavBarFadeController = navBarFadeController;
        lightnessProvider2.mListener = new NgaUiController$$ExternalSyntheticLambda2(this);
        assistantPresenceHandler2.mSysUiIsNgaUiChangeListeners.add(new NgaUiController$$ExternalSyntheticLambda3(this));
        touchInsideHandler2.mFallback = new NgaUiController$$ExternalSyntheticLambda1(1, this);
        edgeLightsController2.mThrottler = new NgaUiController$$ExternalSyntheticLambda2(this);
        this.mWakeLock = ((PowerManager) context2.getSystemService("power")).newWakeLock(805306378, "Assist (NGA)");
        NgaUiController$$ExternalSyntheticLambda2 ngaUiController$$ExternalSyntheticLambda2 = new NgaUiController$$ExternalSyntheticLambda2(this);
        glowController2.mVisibilityListener = ngaUiController$$ExternalSyntheticLambda2;
        scrimController2.mVisibilityListener = ngaUiController$$ExternalSyntheticLambda2;
        AssistUIView assistUIView = overlayUiHost2.mRoot;
        AssistantInvocationLightsView assistantInvocationLightsView = (AssistantInvocationLightsView) assistUIView.findViewById(2131362743);
        this.mInvocationLightsView = assistantInvocationLightsView;
        int i = assistantInvocationLightsView.mColorBlue;
        int i2 = assistantInvocationLightsView.mColorRed;
        int i3 = assistantInvocationLightsView.mColorYellow;
        int i4 = assistantInvocationLightsView.mColorGreen;
        assistantInvocationLightsView.mUseNavBarColor = false;
        if (!(!assistantInvocationLightsView.mRegistered || (navigationBarController = assistantInvocationLightsView.mNavigationBarController) == null || (defaultNavigationBar = ((NavigationBarControllerImpl) navigationBarController).getDefaultNavigationBar()) == null)) {
            defaultNavigationBar.mNavigationBarTransitions.mDarkIntensityListeners.remove(assistantInvocationLightsView);
            assistantInvocationLightsView.mRegistered = false;
        }
        ((EdgeLight) assistantInvocationLightsView.mAssistInvocationLights.get(0)).setColor(i);
        ((EdgeLight) assistantInvocationLightsView.mAssistInvocationLights.get(1)).setColor(i2);
        ((EdgeLight) assistantInvocationLightsView.mAssistInvocationLights.get(2)).setColor(i3);
        ((EdgeLight) assistantInvocationLightsView.mAssistInvocationLights.get(3)).setColor(i4);
        EdgeLightsView edgeLightsView = edgeLightsController2.mEdgeLightsView;
        edgeLightsView.mListeners.add(glowController2);
        edgeLightsView.mListeners.add(scrimController2);
        transcriptionController2.mListener = scrimController2;
        z = transcriptionController2.mCurrentState != TranscriptionController.State.NONE ? true : z;
        if (scrimController2.mTranscriptionVisible != z) {
            scrimController2.mTranscriptionVisible = z;
            scrimController.refresh$1();
        }
        this.mPromptView = (PromptView) assistUIView.findViewById(2131363341);
        dispatchHasDarkBackground();
        statusBarStateController.addCallback(this);
        refresh$2();
        timeoutManager2.mTimeoutCallback = new NgaUiController$$ExternalSyntheticLambda2(new NgaUiController$$ExternalSyntheticLambda1(2, this));
    }

    public final void closeNgaUi() {
        ((AssistManager) this.mAssistManager.get()).hideAssist();
        hide();
    }

    public final void completeInvocation(int i) {
        float f;
        if (!this.mAssistantPresenceHandler.mSysUiIsNgaUi) {
            setProgress(i, 0.0f);
            this.mInvocationInProgress = false;
            this.mInvocationLightsView.hide();
            this.mLastInvocationProgress = 0.0f;
            ScrimController scrimController = this.mScrimController;
            scrimController.getClass();
            float constrain = MathUtils.constrain(0.0f, 0.0f, 1.0f);
            if (scrimController.mInvocationProgress != constrain) {
                scrimController.mInvocationProgress = constrain;
                scrimController.refresh$1();
            }
            refresh$2();
            return;
        }
        TouchInsideHandler touchInsideHandler = this.mTouchInsideHandler;
        if (!touchInsideHandler.mInGesturalMode) {
            touchInsideHandler.mGuardLocked = true;
            touchInsideHandler.mGuarded = true;
            touchInsideHandler.mHandler.postDelayed(new TouchInsideHandler$$ExternalSyntheticLambda0(1, touchInsideHandler), 500);
        }
        TimeoutManager timeoutManager = this.mTimeoutManager;
        Handler handler = timeoutManager.mHandler;
        TimeoutManager$$ExternalSyntheticLambda0 timeoutManager$$ExternalSyntheticLambda0 = timeoutManager.mOnTimeout;
        handler.removeCallbacks(timeoutManager$$ExternalSyntheticLambda0);
        handler.postDelayed(timeoutManager$$ExternalSyntheticLambda0, TimeoutManager.SESSION_TIMEOUT_MS);
        PromptView promptView = this.mPromptView;
        promptView.mEnabled = false;
        promptView.setVisibility(8);
        ValueAnimator valueAnimator = this.mInvocationAnimator;
        if (valueAnimator != null && valueAnimator.isStarted()) {
            this.mInvocationAnimator.cancel();
        }
        float f2 = this.mFlingVelocity.mVelocity;
        float f3 = 3.0f;
        if (f2 != 0.0f) {
            f3 = MathUtils.constrain((-f2) / 1.45f, 3.0f, 12.0f);
        }
        OvershootInterpolator overshootInterpolator = new OvershootInterpolator(f3);
        float f4 = this.mLastInvocationProgress;
        if (i == 2) {
            f = f4 * 0.95f;
        } else {
            f = mProgressInterpolator.getInterpolation(f4 * 0.8f);
        }
        Float valueOf = Float.valueOf(f);
        ArrayList arrayList = new ArrayList((int) 200.0f);
        for (float f5 = 0.0f; f5 < 1.0f; f5 += 0.005f) {
            arrayList.add(Float.valueOf(Math.min(1.0f, overshootInterpolator.getInterpolation(f5))));
        }
        int binarySearch = Collections.binarySearch(arrayList, valueOf);
        if (binarySearch < 0) {
            binarySearch = (binarySearch + 1) * -1;
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{((float) binarySearch) * 0.005f, 1.0f});
        ofFloat.setDuration(600);
        ofFloat.setStartDelay(1);
        ofFloat.addUpdateListener(new NgaUiController$$ExternalSyntheticLambda4(this, i, overshootInterpolator));
        ofFloat.addListener(new AnimatorListenerAdapter() {
            public boolean mCancelled = false;

            public final void onAnimationCancel(Animator animator) {
                super.onAnimationCancel(animator);
                this.mCancelled = true;
            }

            public final void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                if (!this.mCancelled) {
                    NgaUiController ngaUiController = NgaUiController.this;
                    Runnable runnable = ngaUiController.mPendingEdgeLightsModeChange;
                    if (runnable == null) {
                        EdgeLightsController edgeLightsController = ngaUiController.mEdgeLightsController;
                        EdgeLightsView edgeLightsView = edgeLightsController.mEdgeLightsView;
                        edgeLightsView.mMode.onNewModeRequest(edgeLightsView, new FullListening(edgeLightsController.mContext, false));
                    } else {
                        runnable.run();
                        NgaUiController.this.mPendingEdgeLightsModeChange = null;
                    }
                }
                NgaUiController.this.mUiHandler.post(new NgaUiController$$ExternalSyntheticLambda1(3, this));
            }
        });
        this.mInvocationAnimator = ofFloat;
        ofFloat.start();
    }

    public final void dispatchHasDarkBackground() {
        int i;
        int i2;
        int i3;
        TranscriptionController transcriptionController = this.mTranscriptionController;
        boolean z = this.mHasDarkBackground;
        for (TranscriptionController.TranscriptionSpaceView hasDarkBackground : transcriptionController.mViewMap.values()) {
            hasDarkBackground.setHasDarkBackground(z);
        }
        IconController iconController = this.mIconController;
        boolean z2 = this.mHasDarkBackground;
        KeyboardIconView keyboardIconView = iconController.mKeyboardIcon;
        ImageView imageView = keyboardIconView.mKeyboardIcon;
        if (z2) {
            i = keyboardIconView.COLOR_DARK_BACKGROUND;
        } else {
            i = keyboardIconView.COLOR_LIGHT_BACKGROUND;
        }
        imageView.setImageTintList(ColorStateList.valueOf(i));
        ZeroStateIconView zeroStateIconView = iconController.mZeroStateIcon;
        ImageView imageView2 = zeroStateIconView.mZeroStateIcon;
        if (z2) {
            i2 = zeroStateIconView.COLOR_DARK_BACKGROUND;
        } else {
            i2 = zeroStateIconView.COLOR_LIGHT_BACKGROUND;
        }
        imageView2.setImageTintList(ColorStateList.valueOf(i2));
        PromptView promptView = this.mPromptView;
        boolean z3 = this.mHasDarkBackground;
        if (z3 != promptView.mHasDarkBackground) {
            if (z3) {
                i3 = promptView.mTextColorDark;
            } else {
                i3 = promptView.mTextColorLight;
            }
            promptView.setTextColor(i3);
            promptView.mHasDarkBackground = z3;
        }
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [com.google.android.systemui.assist.uihints.edgelights.EdgeLightsView$Mode, java.lang.Object] */
    public final void hide() {
        ValueAnimator valueAnimator = this.mInvocationAnimator;
        if (valueAnimator != null && valueAnimator.isStarted()) {
            this.mInvocationAnimator.cancel();
        }
        this.mInvocationInProgress = false;
        TranscriptionController transcriptionController = this.mTranscriptionController;
        transcriptionController.setQueuedState(TranscriptionController.State.NONE, false, (Runnable) null);
        transcriptionController.maybeSetState();
        EdgeLightsView edgeLightsView = this.mEdgeLightsController.mEdgeLightsView;
        edgeLightsView.mMode.onNewModeRequest(edgeLightsView, new Object());
        this.mPendingEdgeLightsModeChange = null;
        PromptView promptView = this.mPromptView;
        promptView.mEnabled = false;
        promptView.setVisibility(8);
        IconController iconController = this.mIconController;
        iconController.mKeyboardIconRequested = false;
        iconController.mOnKeyboardIconTap = null;
        iconController.maybeUpdateIconVisibility(iconController.mKeyboardIcon, false);
        IconController iconController2 = this.mIconController;
        iconController2.mZerostateIconRequested = false;
        iconController2.mOnZerostateIconTap = null;
        iconController2.maybeUpdateIconVisibility(iconController2.mZeroStateIcon, false);
        refresh$2();
    }

    public final void logInvocationProgressMetrics(float f, int i, boolean z) {
        if (!z && f > 0.0f) {
            this.mAssistLogger.reportAssistantInvocationEventFromLegacy(i, false, (ComponentName) null, (Integer) null);
            MetricsLogger.action(new LogMaker(1716).setType(4).setSubtype((i << 1) | (((AssistManager) this.mAssistManager.get()).mPhoneStateMonitor.getPhoneState() << 4)));
        }
        ValueAnimator valueAnimator = this.mInvocationAnimator;
        if ((valueAnimator == null || !valueAnimator.isRunning()) && z && f == 0.0f) {
            this.mAssistLogger.reportAssistantSessionEvent(AssistantSessionEvent.ASSISTANT_SESSION_INVOCATION_CANCELLED);
            MetricsLogger.action(new LogMaker(1716).setType(5).setSubtype(1));
        }
    }

    public final void onComputeInternalInsets(ViewTreeObserver.InternalInsetsInfo internalInsetsInfo) {
        internalInsetsInfo.setTouchableInsets(3);
        Region region = new Region();
        this.mIconController.getTouchActionRegion().ifPresent(new NgaUiController$$ExternalSyntheticLambda5(0, region));
        Region region2 = new Region();
        EdgeLightsView.Mode mode = this.mEdgeLightsController.mEdgeLightsView.mMode;
        if (!(mode instanceof FullListening) || !((FullListening) mode).mFakeForHalfListening) {
            this.mGlowController.getTouchInsideRegion().ifPresent(new NgaUiController$$ExternalSyntheticLambda5(1, region2));
        }
        this.mScrimController.getTouchInsideRegion().ifPresent(new NgaUiController$$ExternalSyntheticLambda5(2, region2));
        NgaUiController$$ExternalSyntheticLambda5 ngaUiController$$ExternalSyntheticLambda5 = new NgaUiController$$ExternalSyntheticLambda5(3, region2);
        this.mTranscriptionController.getTouchInsideRegion().ifPresent(ngaUiController$$ExternalSyntheticLambda5);
        this.mTranscriptionController.getTouchActionRegion().ifPresent(ngaUiController$$ExternalSyntheticLambda5);
        region.op(region2, Region.Op.UNION);
        internalInsetsInfo.touchableRegion.set(region);
    }

    public final void onDozingChanged(boolean z) {
        if (Looper.myLooper() != this.mUiHandler.getLooper()) {
            this.mUiHandler.post(new NgaUiController$$ExternalSyntheticLambda0(this, z));
            return;
        }
        ScrimController scrimController = this.mScrimController;
        scrimController.mIsDozing = z;
        scrimController.refresh$1();
        if (z && this.mShowingAssistUi) {
            DejankUtils.whitelistIpcs((Runnable) new NgaUiController$$ExternalSyntheticLambda1(0, this));
        }
    }

    public final void onGestureCompletion(float f) {
        if (!this.mEdgeLightsController.mEdgeLightsView.mMode.preventsInvocations()) {
            FlingVelocityWrapper flingVelocityWrapper = this.mFlingVelocity;
            flingVelocityWrapper.mVelocity = f;
            flingVelocityWrapper.mGuarded = false;
            completeInvocation(1);
            logInvocationProgressMetrics(1.0f, 1, this.mInvocationInProgress);
        } else if (VERBOSE) {
            this.mEdgeLightsController.mEdgeLightsView.mMode.getClass();
        }
    }

    public final void onInvocationProgress(int i, float f) {
        float f2;
        boolean z;
        float f3;
        ValueAnimator valueAnimator = this.mInvocationAnimator;
        if (valueAnimator != null && valueAnimator.isStarted()) {
            Log.w("NgaUiController", "Already animating; ignoring invocation progress");
        } else if (!this.mEdgeLightsController.mEdgeLightsView.mMode.preventsInvocations()) {
            boolean z2 = this.mInvocationInProgress;
            int i2 = (f > 1.0f ? 1 : (f == 1.0f ? 0 : -1));
            if (i2 < 0) {
                this.mLastInvocationProgress = f;
                if (!z2 && f > 0.0f) {
                    this.mLastInvocationStartTime = SystemClock.uptimeMillis();
                }
                if (f <= 0.0f || i2 >= 0) {
                    z = false;
                } else {
                    z = true;
                }
                this.mInvocationInProgress = z;
                if (!z) {
                    PromptView promptView = this.mPromptView;
                    promptView.mEnabled = false;
                    promptView.setVisibility(8);
                } else if (f < 0.9f && SystemClock.uptimeMillis() - this.mLastInvocationStartTime > 200) {
                    this.mPromptView.mEnabled = true;
                }
                if (i == 2) {
                    f3 = 0.95f * f;
                } else {
                    f3 = mProgressInterpolator.getInterpolation(0.8f * f);
                }
                setProgress(i, f3);
            } else {
                ValueAnimator valueAnimator2 = this.mInvocationAnimator;
                if (valueAnimator2 == null || !valueAnimator2.isStarted()) {
                    FlingVelocityWrapper flingVelocityWrapper = this.mFlingVelocity;
                    flingVelocityWrapper.mVelocity = 0.0f;
                    flingVelocityWrapper.mGuarded = false;
                    completeInvocation(i);
                }
            }
            AssistantWarmer assistantWarmer = this.mAssistantWarmer;
            if (f >= 1.0f) {
                assistantWarmer.primed = false;
            } else {
                if (f > 0.0f || !assistantWarmer.primed) {
                    NgaMessageHandler.WarmingRequest warmingRequest = assistantWarmer.request;
                    if (warmingRequest != null) {
                        f2 = warmingRequest.threshold;
                    } else {
                        f2 = 0.1f;
                    }
                    if (f > f2 && !assistantWarmer.primed) {
                        assistantWarmer.primed = true;
                    }
                } else {
                    assistantWarmer.primed = false;
                }
                NgaMessageHandler.WarmingRequest warmingRequest2 = assistantWarmer.request;
                if (warmingRequest2 != null) {
                    Context context = assistantWarmer.context;
                    boolean z3 = assistantWarmer.primed;
                    PendingIntent pendingIntent = warmingRequest2.onWarm;
                    if (pendingIntent != null) {
                        try {
                            pendingIntent.send(context, 0, new Intent().putExtra("primed", z3));
                        } catch (PendingIntent.CanceledException e) {
                            Log.e("NgaMessageHandler", "Unable to warm assistant, PendingIntent cancelled", e);
                        }
                    }
                }
            }
            logInvocationProgressMetrics(f, i, z2);
        } else if (VERBOSE) {
            this.mEdgeLightsController.mEdgeLightsView.mMode.getClass();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0076, code lost:
        if (r6 == null) goto L_0x0137;
     */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0055  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x013b  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x01c5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void refresh$2() {
        /*
            r18 = this;
            r0 = r18
            com.google.android.systemui.assist.uihints.edgelights.EdgeLightsController r1 = r0.mEdgeLightsController
            com.google.android.systemui.assist.uihints.edgelights.EdgeLightsView r1 = r1.mEdgeLightsView
            com.google.android.systemui.assist.uihints.edgelights.EdgeLightsView$Mode r1 = r1.mMode
            boolean r1 = r1 instanceof com.google.android.systemui.assist.uihints.edgelights.mode.Gone
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L_0x002b
            com.google.android.systemui.assist.uihints.GlowController r1 = r0.mGlowController
            com.google.android.systemui.assist.uihints.GlowView r1 = r1.mGlowView
            int r1 = r1.getVisibility()
            if (r1 != 0) goto L_0x0019
            goto L_0x002b
        L_0x0019:
            com.google.android.systemui.assist.uihints.ScrimController r1 = r0.mScrimController
            android.view.View r1 = r1.mScrimView
            int r1 = r1.getVisibility()
            if (r1 != 0) goto L_0x0024
            goto L_0x002b
        L_0x0024:
            boolean r1 = r0.mInvocationInProgress
            if (r1 == 0) goto L_0x0029
            goto L_0x002b
        L_0x0029:
            r1 = r2
            goto L_0x002c
        L_0x002b:
            r1 = r3
        L_0x002c:
            if (r1 != 0) goto L_0x004e
            com.google.android.systemui.assist.uihints.IconController r4 = r0.mIconController
            com.google.android.systemui.assist.uihints.KeyboardIconView r5 = r4.mKeyboardIcon
            int r5 = r5.getVisibility()
            if (r5 == 0) goto L_0x004e
            com.google.android.systemui.assist.uihints.ZeroStateIconView r4 = r4.mZeroStateIcon
            int r4 = r4.getVisibility()
            if (r4 != 0) goto L_0x0041
            goto L_0x004e
        L_0x0041:
            com.google.android.systemui.assist.uihints.IconController r4 = r0.mIconController
            boolean r5 = r4.mKeyboardIconRequested
            if (r5 != 0) goto L_0x004e
            boolean r4 = r4.mZerostateIconRequested
            if (r4 == 0) goto L_0x004c
            goto L_0x004e
        L_0x004c:
            r4 = r2
            goto L_0x004f
        L_0x004e:
            r4 = r3
        L_0x004f:
            boolean r5 = r0.mIsMonitoringColor
            if (r5 != r4) goto L_0x0055
            goto L_0x0137
        L_0x0055:
            r5 = 0
            if (r4 == 0) goto L_0x007a
            com.google.android.systemui.assist.uihints.ScrimController r6 = r0.mScrimController
            android.view.View r6 = r6.mScrimView
            int r6 = r6.getVisibility()
            if (r6 != 0) goto L_0x007a
            com.google.android.systemui.assist.uihints.ScrimController r6 = r0.mScrimController
            android.view.View r6 = r6.mScrimView
            android.view.ViewRootImpl r7 = r6.getViewRootImpl()
            if (r7 != 0) goto L_0x006e
            r6 = r5
            goto L_0x0076
        L_0x006e:
            android.view.ViewRootImpl r6 = r6.getViewRootImpl()
            android.view.SurfaceControl r6 = r6.getSurfaceControl()
        L_0x0076:
            if (r6 != 0) goto L_0x007a
            goto L_0x0137
        L_0x007a:
            r0.mIsMonitoringColor = r4
            if (r4 == 0) goto L_0x0108
            android.content.Context r6 = r0.mContext
            android.view.Display r6 = com.google.android.systemui.assist.uihints.DisplayUtils.getDefaultDisplay(r6)
            android.graphics.Point r7 = new android.graphics.Point
            r7.<init>()
            r6.getRealSize(r7)
            int r6 = r7.y
            android.content.Context r7 = r0.mContext
            android.content.res.Resources r7 = r7.getResources()
            r8 = 2131167745(0x7f070a01, float:1.7949772E38)
            float r7 = r7.getDimension(r8)
            int r7 = (int) r7
            int r6 = r6 - r7
            android.content.Context r7 = r0.mContext
            android.content.res.Resources r7 = r7.getResources()
            android.util.DisplayMetrics r7 = r7.getDisplayMetrics()
            r8 = 2
            r9 = 1101004800(0x41a00000, float:20.0)
            float r7 = android.util.TypedValue.applyDimension(r8, r9, r7)
            int r7 = (int) r7
            int r6 = r6 - r7
            android.content.Context r7 = r0.mContext
            android.view.Display r7 = com.google.android.systemui.assist.uihints.DisplayUtils.getDefaultDisplay(r7)
            android.util.DisplayMetrics r8 = new android.util.DisplayMetrics
            r8.<init>()
            r7.getRealMetrics(r8)
            float r7 = r8.density
            r8 = 1126170624(0x43200000, float:160.0)
            float r8 = r8 * r7
            double r7 = (double) r8
            double r7 = java.lang.Math.ceil(r7)
            int r7 = (int) r7
            int r7 = r6 - r7
            android.graphics.Rect r8 = new android.graphics.Rect
            android.content.Context r9 = r0.mContext
            android.view.Display r9 = com.google.android.systemui.assist.uihints.DisplayUtils.getDefaultDisplay(r9)
            android.graphics.Point r10 = new android.graphics.Point
            r10.<init>()
            r9.getRealSize(r10)
            int r9 = r10.x
            r8.<init>(r2, r7, r9, r6)
            long r6 = android.os.SystemClock.elapsedRealtime()
            r0.mColorMonitoringStart = r6
            com.google.android.systemui.assist.uihints.LightnessProvider r6 = r0.mLightnessProvider
            com.google.android.systemui.assist.uihints.ScrimController r7 = r0.mScrimController
            android.view.View r7 = r7.mScrimView
            android.view.ViewRootImpl r9 = r7.getViewRootImpl()
            if (r9 != 0) goto L_0x00f3
            goto L_0x00fb
        L_0x00f3:
            android.view.ViewRootImpl r5 = r7.getViewRootImpl()
            android.view.SurfaceControl r5 = r5.getSurfaceControl()
        L_0x00fb:
            boolean r7 = r6.mIsMonitoringColor
            if (r7 != r3) goto L_0x0100
            goto L_0x0137
        L_0x0100:
            r6.mIsMonitoringColor = r3
            com.google.android.systemui.assist.uihints.LightnessProvider$1 r6 = r6.mColorMonitor
            android.view.CompositionSamplingListener.register(r6, r2, r5, r8)
            goto L_0x0137
        L_0x0108:
            com.google.android.systemui.assist.uihints.LightnessProvider r5 = r0.mLightnessProvider
            boolean r6 = r5.mIsMonitoringColor
            if (r6 != 0) goto L_0x010f
            goto L_0x0116
        L_0x010f:
            r5.mIsMonitoringColor = r2
            com.google.android.systemui.assist.uihints.LightnessProvider$1 r5 = r5.mColorMonitor
            android.view.CompositionSamplingListener.unregister(r5)
        L_0x0116:
            com.google.android.systemui.assist.uihints.IconController r5 = r0.mIconController
            r5.mHasAccurateLuma = r2
            com.google.android.systemui.assist.uihints.KeyboardIconView r6 = r5.mKeyboardIcon
            boolean r7 = r5.mKeyboardIconRequested
            r5.maybeUpdateIconVisibility(r6, r7)
            com.google.android.systemui.assist.uihints.ZeroStateIconView r6 = r5.mZeroStateIcon
            boolean r7 = r5.mZerostateIconRequested
            r5.maybeUpdateIconVisibility(r6, r7)
            com.google.android.systemui.assist.uihints.ScrimController r5 = r0.mScrimController
            r5.mHaveAccurateLightness = r2
            r5.refresh$1()
            com.google.android.systemui.assist.uihints.TranscriptionController r5 = r0.mTranscriptionController
            boolean r6 = r5.mHasAccurateBackground
            if (r6 == 0) goto L_0x0137
            r5.mHasAccurateBackground = r2
        L_0x0137:
            boolean r5 = r0.mShowingAssistUi
            if (r5 == r4) goto L_0x01c1
            r0.mShowingAssistUi = r4
            com.google.android.systemui.assist.uihints.OverlayUiHost r5 = r0.mUiHost
            com.google.android.systemui.assist.uihints.edgelights.EdgeLightsController r6 = r0.mEdgeLightsController
            com.google.android.systemui.assist.uihints.edgelights.EdgeLightsView r6 = r6.mEdgeLightsView
            com.google.android.systemui.assist.uihints.edgelights.EdgeLightsView$Mode r6 = r6.mMode
            boolean r6 = r6 instanceof com.google.android.systemui.assist.uihints.edgelights.mode.FullListening
            com.google.android.systemui.assist.uihints.AssistUIView r7 = r5.mRoot
            android.view.WindowManager r8 = r5.mWindowManager
            if (r4 == 0) goto L_0x0180
            boolean r9 = r5.mAttached
            if (r9 != 0) goto L_0x0180
            android.view.WindowManager$LayoutParams r9 = new android.view.WindowManager$LayoutParams
            r14 = 0
            r15 = 2024(0x7e8, float:2.836E-42)
            r11 = -1
            r12 = -1
            r13 = 0
            r16 = 262952(0x40328, float:3.68474E-40)
            r17 = -3
            r10 = r9
            r10.<init>(r11, r12, r13, r14, r15, r16, r17)
            r5.mLayoutParams = r9
            r5.mFocusable = r6
            r6 = 80
            r9.gravity = r6
            r6 = 64
            r9.privateFlags = r6
            r9.setFitInsetsTypes(r2)
            android.view.WindowManager$LayoutParams r6 = r5.mLayoutParams
            java.lang.String r9 = "Assist"
            r6.setTitle(r9)
            android.view.WindowManager$LayoutParams r6 = r5.mLayoutParams
            r8.addView(r7, r6)
            r5.mAttached = r3
            goto L_0x0199
        L_0x0180:
            if (r4 != 0) goto L_0x018c
            boolean r9 = r5.mAttached
            if (r9 == 0) goto L_0x018c
            r8.removeViewImmediate(r7)
            r5.mAttached = r2
            goto L_0x0199
        L_0x018c:
            if (r4 == 0) goto L_0x0199
            boolean r9 = r5.mFocusable
            if (r9 == r6) goto L_0x0199
            android.view.WindowManager$LayoutParams r9 = r5.mLayoutParams
            r8.updateViewLayout(r7, r9)
            r5.mFocusable = r6
        L_0x0199:
            if (r4 == 0) goto L_0x01a7
            com.google.android.systemui.assist.uihints.OverlayUiHost r4 = r0.mUiHost
            com.google.android.systemui.assist.uihints.AssistUIView r4 = r4.mRoot
            android.view.ViewTreeObserver r4 = r4.getViewTreeObserver()
            r4.addOnComputeInternalInsetsListener(r0)
            goto L_0x01c1
        L_0x01a7:
            com.google.android.systemui.assist.uihints.OverlayUiHost r4 = r0.mUiHost
            com.google.android.systemui.assist.uihints.AssistUIView r4 = r4.mRoot
            android.view.ViewTreeObserver r4 = r4.getViewTreeObserver()
            r4.removeOnComputeInternalInsetsListener(r0)
            android.animation.ValueAnimator r4 = r0.mInvocationAnimator
            if (r4 == 0) goto L_0x01c1
            boolean r4 = r4.isStarted()
            if (r4 == 0) goto L_0x01c1
            android.animation.ValueAnimator r4 = r0.mInvocationAnimator
            r4.cancel()
        L_0x01c1:
            boolean r4 = r0.mShouldKeepWakeLock
            if (r4 == r1) goto L_0x01d4
            r0.mShouldKeepWakeLock = r1
            if (r1 == 0) goto L_0x01cf
            android.os.PowerManager$WakeLock r1 = r0.mWakeLock
            r1.acquire()
            goto L_0x01d4
        L_0x01cf:
            android.os.PowerManager$WakeLock r1 = r0.mWakeLock
            r1.release()
        L_0x01d4:
            com.google.android.systemui.assist.uihints.edgelights.EdgeLightsController r1 = r0.mEdgeLightsController
            com.google.android.systemui.assist.uihints.edgelights.EdgeLightsView r1 = r1.mEdgeLightsView
            com.google.android.systemui.assist.uihints.edgelights.EdgeLightsView$Mode r1 = r1.mMode
            boolean r4 = r0.mInvocationInProgress
            if (r4 != 0) goto L_0x01e4
            boolean r1 = r1 instanceof com.google.android.systemui.assist.uihints.edgelights.mode.Gone
            if (r1 != 0) goto L_0x01e3
            goto L_0x01e4
        L_0x01e3:
            r2 = r3
        L_0x01e4:
            com.google.android.systemui.assist.uihints.NavBarFadeController r0 = r0.mNavBarFadeController
            r0.systemVisible = r2
            r0.update()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.assist.uihints.NgaUiController.refresh$2():void");
    }

    public final void setProgress(int i, float f) {
        int i2;
        this.mInvocationLightsView.onInvocationProgress(f);
        GlowController glowController = this.mGlowController;
        if (glowController.mEdgeLightsMode instanceof Gone) {
            if (f > 0.0f) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            glowController.setVisibility$1(i2);
            Context context = glowController.mContext;
            int lerp = (int) MathUtils.lerp(glowController.getBlurRadius(), context.getResources().getDimensionPixelSize(2131165947), Math.min(1.0f, 5.0f * f));
            GlowView glowView = glowController.mGlowView;
            if (glowView.mBlurRadius != lerp) {
                glowView.setBlurredImageOnViews(lerp);
            }
            int min = (int) MathUtils.min((int) MathUtils.lerp(glowController.getMinTranslationY(), context.getResources().getDimensionPixelSize(2131165949), f), context.getResources().getDimensionPixelSize(2131165943));
            glowController.mGlowsY = min;
            glowController.mGlowsYDestination = min;
            glowView.setGlowsY(min, min, (EdgeLight[]) null);
            glowView.distributeEvenly();
        }
        ScrimController scrimController = this.mScrimController;
        scrimController.getClass();
        float constrain = MathUtils.constrain(f, 0.0f, 1.0f);
        if (scrimController.mInvocationProgress != constrain) {
            scrimController.mInvocationProgress = constrain;
            scrimController.refresh$1();
        }
        PromptView promptView = this.mPromptView;
        if (f > 1.0f) {
            promptView.getClass();
        } else if (f == 0.0f) {
            promptView.setVisibility(8);
            promptView.setAlpha(0.0f);
            promptView.setTranslationY(0.0f);
            promptView.mLastInvocationType = 0;
        } else if (promptView.mEnabled) {
            if (i != 1) {
                if (i != 2) {
                    promptView.mLastInvocationType = 0;
                    promptView.setText("");
                } else if (promptView.mLastInvocationType != i) {
                    promptView.mLastInvocationType = i;
                    promptView.setText(promptView.mSqueezeString);
                    promptView.announceForAccessibility(promptView.mSqueezeString);
                }
            } else if (promptView.mLastInvocationType != i) {
                promptView.mLastInvocationType = i;
                promptView.setText(promptView.mHandleString);
                promptView.announceForAccessibility(promptView.mHandleString);
            }
            promptView.setVisibility(0);
            promptView.setTranslationY((-promptView.mRiseDistance) * f);
            if (i != 2 && f > 0.8f) {
                promptView.setAlpha(0.0f);
            } else if (f > 0.32000002f) {
                promptView.setAlpha(1.0f);
            } else {
                promptView.setAlpha(promptView.mDecelerateInterpolator.getInterpolation(f / 0.32000002f));
            }
        }
        refresh$2();
    }
}
