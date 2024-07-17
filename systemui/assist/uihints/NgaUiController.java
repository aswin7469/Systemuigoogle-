package com.google.android.systemui.assist.uihints;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.Region;
import android.metrics.LogMaker;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;
import android.util.MathUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.PathInterpolator;
import android.widget.ImageView;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.DejankUtils;
import com.android.systemui.assist.AssistLogger;
import com.android.systemui.assist.AssistManager$UiController;
import com.android.systemui.assist.AssistantSessionEvent;
import com.android.systemui.assist.ui.EdgeLight;
import com.android.systemui.navigationbar.NavigationBar;
import com.android.systemui.navigationbar.NavigationBarControllerImpl;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.google.android.systemui.assist.AssistManagerGoogle;
import com.google.android.systemui.assist.uihints.NgaMessageHandler;
import com.google.android.systemui.assist.uihints.TranscriptionController;
import com.google.android.systemui.assist.uihints.edgelights.EdgeLightsController;
import com.google.android.systemui.assist.uihints.edgelights.EdgeLightsView;
import com.google.android.systemui.assist.uihints.edgelights.mode.Gone;
import dagger.Lazy;
import java.util.Locale;
import java.util.Optional;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class NgaUiController implements AssistManager$UiController, ViewTreeObserver.OnComputeInternalInsetsListener, StatusBarStateController.StateListener {
    public static final boolean VERBOSE;
    public static final PathInterpolator mProgressInterpolator = new PathInterpolator(0.83f, 0.0f, 0.84f, 1.0f);
    public final AssistLogger mAssistLogger;
    public final Lazy mAssistManager;
    public final AssistantWarmer mAssistantWarmer;
    public long mColorMonitoringStart;
    public final Context mContext;
    public final EdgeLightsController mEdgeLightsController;
    public final FlingVelocityWrapper mFlingVelocity;
    public final GlowController mGlowController;
    public boolean mHasDarkBackground;
    public final IconController mIconController;
    public boolean mInvocationInProgress;
    public final AssistantInvocationLightsView mInvocationLightsView;
    public boolean mIsMonitoringColor;
    public long mLastInvocationStartTime;
    public final LightnessProvider mLightnessProvider;
    public final NavBarFadeController mNavBarFadeController;
    public final PromptView mPromptView;
    public final ScrimController mScrimController;
    public boolean mShouldKeepWakeLock;
    public boolean mShowingAssistUi;
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

    public NgaUiController(Context context, TimeoutManager timeoutManager, TouchInsideHandler touchInsideHandler, OverlayUiHost overlayUiHost, EdgeLightsController edgeLightsController, GlowController glowController, ScrimController scrimController, TranscriptionController transcriptionController, IconController iconController, LightnessProvider lightnessProvider, StatusBarStateController statusBarStateController, Lazy lazy, FlingVelocityWrapper flingVelocityWrapper, AssistantWarmer assistantWarmer, NavBarFadeController navBarFadeController, AssistLogger assistLogger) {
        NavigationBarControllerImpl navigationBarControllerImpl;
        NavigationBar defaultNavigationBar;
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
        this.mColorMonitoringStart = 0;
        this.mContext = context;
        this.mAssistLogger = assistLogger;
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
        lightnessProvider2.mListener = new NgaUiController$$ExternalSyntheticLambda6(this);
        touchInsideHandler.mFallback = new NgaUiController$$ExternalSyntheticLambda1(this);
        edgeLightsController.getClass();
        this.mWakeLock = ((PowerManager) context.getSystemService("power")).newWakeLock(805306378, "Assist (NGA)");
        NgaUiController$$ExternalSyntheticLambda6 ngaUiController$$ExternalSyntheticLambda6 = new NgaUiController$$ExternalSyntheticLambda6(this);
        glowController2.mVisibilityListener = ngaUiController$$ExternalSyntheticLambda6;
        scrimController2.mVisibilityListener = ngaUiController$$ExternalSyntheticLambda6;
        AssistUIView assistUIView = overlayUiHost2.mRoot;
        AssistantInvocationLightsView assistantInvocationLightsView = (AssistantInvocationLightsView) assistUIView.findViewById(2131362764);
        this.mInvocationLightsView = assistantInvocationLightsView;
        int i = assistantInvocationLightsView.mColorBlue;
        int i2 = assistantInvocationLightsView.mColorRed;
        int i3 = assistantInvocationLightsView.mColorYellow;
        int i4 = assistantInvocationLightsView.mColorGreen;
        assistantInvocationLightsView.mUseNavBarColor = false;
        if (!(!assistantInvocationLightsView.mRegistered || (navigationBarControllerImpl = assistantInvocationLightsView.mNavigationBarController) == null || (defaultNavigationBar = navigationBarControllerImpl.getDefaultNavigationBar()) == null)) {
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
            scrimController.refresh();
        }
        this.mPromptView = (PromptView) assistUIView.findViewById(2131363368);
        dispatchHasDarkBackground();
        statusBarStateController.addCallback(this);
        refresh$1();
        timeoutManager.getClass();
    }

    public final void closeNgaUi() {
        ((AssistManagerGoogle) this.mAssistManager.get()).hideAssist();
        hide();
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

    /* JADX WARNING: type inference failed for: r3v0, types: [java.lang.Object, com.google.android.systemui.assist.uihints.edgelights.mode.Gone] */
    public final void hide() {
        this.mInvocationInProgress = false;
        TranscriptionController transcriptionController = this.mTranscriptionController;
        transcriptionController.mQueuedState = TranscriptionController.State.NONE;
        transcriptionController.mQueuedStateAnimates = false;
        transcriptionController.mQueuedCompletion = null;
        transcriptionController.maybeSetState();
        EdgeLightsView edgeLightsView = this.mEdgeLightsController.mEdgeLightsView;
        Gone gone = edgeLightsView.mMode;
        ? obj = new Object();
        gone.getClass();
        edgeLightsView.setVisibility(0);
        edgeLightsView.commitModeTransition(obj);
        PromptView promptView = this.mPromptView;
        promptView.mEnabled = false;
        promptView.setVisibility(8);
        IconController iconController = this.mIconController;
        iconController.getClass();
        iconController.maybeUpdateIconVisibility(iconController.mKeyboardIcon, false);
        IconController iconController2 = this.mIconController;
        iconController2.getClass();
        iconController2.maybeUpdateIconVisibility(iconController2.mZeroStateIcon, false);
        refresh$1();
    }

    public final void logInvocationProgressMetrics(int i, float f, boolean z) {
        if (!z && f > 0.0f) {
            this.mAssistLogger.reportAssistantInvocationEventFromLegacy(i, false, (ComponentName) null, (Integer) null);
            MetricsLogger.action(new LogMaker(1716).setType(4).setSubtype((i << 1) | (((AssistManagerGoogle) this.mAssistManager.get()).mPhoneStateMonitor.getPhoneState() << 4)));
        }
        if (z && f == 0.0f) {
            this.mAssistLogger.reportAssistantSessionEvent(AssistantSessionEvent.ASSISTANT_SESSION_INVOCATION_CANCELLED);
            MetricsLogger.action(new LogMaker(1716).setType(5).setSubtype(1));
        }
    }

    public final void onComputeInternalInsets(ViewTreeObserver.InternalInsetsInfo internalInsetsInfo) {
        Optional optional;
        Optional optional2;
        Optional optional3;
        Optional optional4;
        Optional optional5;
        internalInsetsInfo.setTouchableInsets(3);
        Region region = new Region();
        IconController iconController = this.mIconController;
        iconController.getClass();
        Region region2 = new Region();
        KeyboardIconView keyboardIconView = iconController.mKeyboardIcon;
        if (keyboardIconView.getVisibility() == 0) {
            Rect rect = new Rect();
            keyboardIconView.getHitRect(rect);
            region2.union(rect);
        }
        ZeroStateIconView zeroStateIconView = iconController.mZeroStateIcon;
        if (zeroStateIconView.getVisibility() == 0) {
            Rect rect2 = new Rect();
            zeroStateIconView.getHitRect(rect2);
            region2.union(rect2);
        }
        if (region2.isEmpty()) {
            optional = Optional.empty();
        } else {
            optional = Optional.of(region2);
        }
        optional.ifPresent(new NgaUiController$$ExternalSyntheticLambda2(0, region));
        Region region3 = new Region();
        Gone gone = this.mEdgeLightsController.mEdgeLightsView.mMode;
        GlowController glowController = this.mGlowController;
        GlowView glowView = glowController.mGlowView;
        if (glowView.getVisibility() == 0) {
            Rect rect3 = new Rect();
            glowView.getBoundsOnScreen(rect3);
            rect3.top = rect3.bottom - glowController.getMaxTranslationY();
            optional2 = Optional.of(new Region(rect3));
        } else {
            optional2 = Optional.empty();
        }
        optional2.ifPresent(new NgaUiController$$ExternalSyntheticLambda2(1, region3));
        ScrimController scrimController = this.mScrimController;
        if (scrimController.mScrimView.getVisibility() == 0) {
            Rect rect4 = new Rect();
            View view = scrimController.mScrimView;
            view.getHitRect(rect4);
            rect4.top = rect4.bottom - view.getResources().getDimensionPixelSize(2131167440);
            optional3 = Optional.of(new Region(rect4));
        } else {
            optional3 = Optional.empty();
        }
        optional3.ifPresent(new NgaUiController$$ExternalSyntheticLambda2(2, region3));
        NgaUiController$$ExternalSyntheticLambda2 ngaUiController$$ExternalSyntheticLambda2 = new NgaUiController$$ExternalSyntheticLambda2(3, region3);
        TranscriptionController transcriptionController = this.mTranscriptionController;
        int ordinal = transcriptionController.mCurrentState.ordinal();
        if (ordinal == 0 || ordinal == 1 || ordinal != 2) {
            optional4 = transcriptionController.getTouchRegion();
        } else {
            optional4 = Optional.empty();
        }
        optional4.ifPresent(ngaUiController$$ExternalSyntheticLambda2);
        TranscriptionController transcriptionController2 = this.mTranscriptionController;
        int ordinal2 = transcriptionController2.mCurrentState.ordinal();
        if (ordinal2 == 0 || ordinal2 == 1 || ordinal2 != 2) {
            optional5 = Optional.empty();
        } else {
            optional5 = transcriptionController2.getTouchRegion();
        }
        optional5.ifPresent(ngaUiController$$ExternalSyntheticLambda2);
        region.op(region3, Region.Op.UNION);
        internalInsetsInfo.touchableRegion.set(region);
    }

    public final void onDozingChanged(boolean z) {
        if (Looper.myLooper() != this.mUiHandler.getLooper()) {
            this.mUiHandler.post(new NgaUiController$$ExternalSyntheticLambda0(this, z));
            return;
        }
        ScrimController scrimController = this.mScrimController;
        scrimController.mIsDozing = z;
        scrimController.refresh();
        if (z && this.mShowingAssistUi) {
            DejankUtils.whitelistIpcs((Runnable) new NgaUiController$$ExternalSyntheticLambda1(this));
        }
    }

    public final void onGestureCompletion(float f) {
        this.mEdgeLightsController.mEdgeLightsView.mMode.getClass();
        this.mFlingVelocity.getClass();
        setProgress(1, 0.0f);
        this.mInvocationInProgress = false;
        this.mInvocationLightsView.hide();
        ScrimController scrimController = this.mScrimController;
        scrimController.getClass();
        float constrain = MathUtils.constrain(0.0f, 0.0f, 1.0f);
        if (scrimController.mInvocationProgress != constrain) {
            scrimController.mInvocationProgress = constrain;
            scrimController.refresh();
        }
        refresh$1();
        logInvocationProgressMetrics(1, 1.0f, this.mInvocationInProgress);
    }

    public final void onInvocationProgress(int i, float f) {
        float f2;
        boolean z;
        float f3;
        this.mEdgeLightsController.mEdgeLightsView.mMode.getClass();
        boolean z2 = this.mInvocationInProgress;
        int i2 = (f > 1.0f ? 1 : (f == 1.0f ? 0 : -1));
        if (i2 < 0) {
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
            this.mFlingVelocity.getClass();
            setProgress(i, 0.0f);
            this.mInvocationInProgress = false;
            this.mInvocationLightsView.hide();
            ScrimController scrimController = this.mScrimController;
            scrimController.getClass();
            float constrain = MathUtils.constrain(0.0f, 0.0f, 1.0f);
            if (scrimController.mInvocationProgress != constrain) {
                scrimController.mInvocationProgress = constrain;
                scrimController.refresh();
            }
            refresh$1();
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
        logInvocationProgressMetrics(i, f, z2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0070, code lost:
        if (r6 == null) goto L_0x012d;
     */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0131  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x01aa  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void refresh$1() {
        /*
            r17 = this;
            r0 = r17
            com.google.android.systemui.assist.uihints.edgelights.EdgeLightsController r1 = r0.mEdgeLightsController
            com.google.android.systemui.assist.uihints.edgelights.EdgeLightsView r1 = r1.mEdgeLightsView
            com.google.android.systemui.assist.uihints.edgelights.mode.Gone r1 = r1.mMode
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
            if (r1 != 0) goto L_0x0048
            com.google.android.systemui.assist.uihints.IconController r4 = r0.mIconController
            com.google.android.systemui.assist.uihints.KeyboardIconView r5 = r4.mKeyboardIcon
            int r5 = r5.getVisibility()
            if (r5 == 0) goto L_0x0048
            com.google.android.systemui.assist.uihints.ZeroStateIconView r4 = r4.mZeroStateIcon
            int r4 = r4.getVisibility()
            if (r4 != 0) goto L_0x0041
            goto L_0x0048
        L_0x0041:
            com.google.android.systemui.assist.uihints.IconController r4 = r0.mIconController
            r4.getClass()
            r4 = r2
            goto L_0x0049
        L_0x0048:
            r4 = r3
        L_0x0049:
            boolean r5 = r0.mIsMonitoringColor
            if (r5 != r4) goto L_0x004f
            goto L_0x012d
        L_0x004f:
            r5 = 0
            if (r4 == 0) goto L_0x0074
            com.google.android.systemui.assist.uihints.ScrimController r6 = r0.mScrimController
            android.view.View r6 = r6.mScrimView
            int r6 = r6.getVisibility()
            if (r6 != 0) goto L_0x0074
            com.google.android.systemui.assist.uihints.ScrimController r6 = r0.mScrimController
            android.view.View r6 = r6.mScrimView
            android.view.ViewRootImpl r7 = r6.getViewRootImpl()
            if (r7 != 0) goto L_0x0068
            r6 = r5
            goto L_0x0070
        L_0x0068:
            android.view.ViewRootImpl r6 = r6.getViewRootImpl()
            android.view.SurfaceControl r6 = r6.getSurfaceControl()
        L_0x0070:
            if (r6 != 0) goto L_0x0074
            goto L_0x012d
        L_0x0074:
            r0.mIsMonitoringColor = r4
            if (r4 == 0) goto L_0x0102
            android.content.Context r6 = r0.mContext
            android.view.Display r6 = com.google.android.systemui.assist.uihints.DisplayUtils.getDefaultDisplay(r6)
            android.graphics.Point r7 = new android.graphics.Point
            r7.<init>()
            r6.getRealSize(r7)
            int r6 = r7.y
            android.content.Context r7 = r0.mContext
            android.content.res.Resources r7 = r7.getResources()
            r8 = 2131167790(0x7f070a2e, float:1.7949864E38)
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
            if (r9 != 0) goto L_0x00ed
            goto L_0x00f5
        L_0x00ed:
            android.view.ViewRootImpl r5 = r7.getViewRootImpl()
            android.view.SurfaceControl r5 = r5.getSurfaceControl()
        L_0x00f5:
            boolean r7 = r6.mIsMonitoringColor
            if (r7 != r3) goto L_0x00fa
            goto L_0x012d
        L_0x00fa:
            r6.mIsMonitoringColor = r3
            com.google.android.systemui.assist.uihints.LightnessProvider$1 r6 = r6.mColorMonitor
            android.view.CompositionSamplingListener.register(r6, r2, r5, r8)
            goto L_0x012d
        L_0x0102:
            com.google.android.systemui.assist.uihints.LightnessProvider r5 = r0.mLightnessProvider
            boolean r6 = r5.mIsMonitoringColor
            if (r6 != 0) goto L_0x0109
            goto L_0x0110
        L_0x0109:
            r5.mIsMonitoringColor = r2
            com.google.android.systemui.assist.uihints.LightnessProvider$1 r5 = r5.mColorMonitor
            android.view.CompositionSamplingListener.unregister(r5)
        L_0x0110:
            com.google.android.systemui.assist.uihints.IconController r5 = r0.mIconController
            r5.mHasAccurateLuma = r2
            com.google.android.systemui.assist.uihints.KeyboardIconView r6 = r5.mKeyboardIcon
            r5.maybeUpdateIconVisibility(r6, r2)
            com.google.android.systemui.assist.uihints.ZeroStateIconView r6 = r5.mZeroStateIcon
            r5.maybeUpdateIconVisibility(r6, r2)
            com.google.android.systemui.assist.uihints.ScrimController r5 = r0.mScrimController
            r5.mHaveAccurateLightness = r2
            r5.refresh()
            com.google.android.systemui.assist.uihints.TranscriptionController r5 = r0.mTranscriptionController
            boolean r6 = r5.mHasAccurateBackground
            if (r6 == 0) goto L_0x012d
            r5.mHasAccurateBackground = r2
        L_0x012d:
            boolean r5 = r0.mShowingAssistUi
            if (r5 == r4) goto L_0x01a6
            r0.mShowingAssistUi = r4
            com.google.android.systemui.assist.uihints.OverlayUiHost r5 = r0.mUiHost
            com.google.android.systemui.assist.uihints.edgelights.EdgeLightsController r6 = r0.mEdgeLightsController
            com.google.android.systemui.assist.uihints.edgelights.EdgeLightsView r6 = r6.mEdgeLightsView
            com.google.android.systemui.assist.uihints.edgelights.mode.Gone r6 = r6.mMode
            com.google.android.systemui.assist.uihints.AssistUIView r6 = r5.mRoot
            android.view.WindowManager r7 = r5.mWindowManager
            if (r4 == 0) goto L_0x0174
            boolean r8 = r5.mAttached
            if (r8 != 0) goto L_0x0174
            android.view.WindowManager$LayoutParams r8 = new android.view.WindowManager$LayoutParams
            r13 = 0
            r14 = 2024(0x7e8, float:2.836E-42)
            r10 = -1
            r11 = -1
            r12 = 0
            r15 = 262952(0x40328, float:3.68474E-40)
            r16 = -3
            r9 = r8
            r9.<init>(r10, r11, r12, r13, r14, r15, r16)
            r5.mLayoutParams = r8
            r5.mFocusable = r2
            r9 = 80
            r8.gravity = r9
            r9 = 64
            r8.privateFlags = r9
            r8.setFitInsetsTypes(r2)
            android.view.WindowManager$LayoutParams r8 = r5.mLayoutParams
            java.lang.String r9 = "Assist"
            r8.setTitle(r9)
            android.view.WindowManager$LayoutParams r8 = r5.mLayoutParams
            r7.addView(r6, r8)
            r5.mAttached = r3
            goto L_0x018d
        L_0x0174:
            if (r4 != 0) goto L_0x0180
            boolean r8 = r5.mAttached
            if (r8 == 0) goto L_0x0180
            r7.removeViewImmediate(r6)
            r5.mAttached = r2
            goto L_0x018d
        L_0x0180:
            if (r4 == 0) goto L_0x018d
            boolean r8 = r5.mFocusable
            if (r8 == 0) goto L_0x018d
            android.view.WindowManager$LayoutParams r8 = r5.mLayoutParams
            r7.updateViewLayout(r6, r8)
            r5.mFocusable = r2
        L_0x018d:
            if (r4 == 0) goto L_0x019b
            com.google.android.systemui.assist.uihints.OverlayUiHost r4 = r0.mUiHost
            com.google.android.systemui.assist.uihints.AssistUIView r4 = r4.mRoot
            android.view.ViewTreeObserver r4 = r4.getViewTreeObserver()
            r4.addOnComputeInternalInsetsListener(r0)
            goto L_0x01a6
        L_0x019b:
            com.google.android.systemui.assist.uihints.OverlayUiHost r4 = r0.mUiHost
            com.google.android.systemui.assist.uihints.AssistUIView r4 = r4.mRoot
            android.view.ViewTreeObserver r4 = r4.getViewTreeObserver()
            r4.removeOnComputeInternalInsetsListener(r0)
        L_0x01a6:
            boolean r4 = r0.mShouldKeepWakeLock
            if (r4 == r1) goto L_0x01b9
            r0.mShouldKeepWakeLock = r1
            if (r1 == 0) goto L_0x01b4
            android.os.PowerManager$WakeLock r1 = r0.mWakeLock
            r1.acquire()
            goto L_0x01b9
        L_0x01b4:
            android.os.PowerManager$WakeLock r1 = r0.mWakeLock
            r1.release()
        L_0x01b9:
            com.google.android.systemui.assist.uihints.edgelights.EdgeLightsController r1 = r0.mEdgeLightsController
            com.google.android.systemui.assist.uihints.edgelights.EdgeLightsView r1 = r1.mEdgeLightsView
            com.google.android.systemui.assist.uihints.edgelights.mode.Gone r1 = r1.mMode
            boolean r4 = r0.mInvocationInProgress
            if (r4 != 0) goto L_0x01c9
            boolean r1 = r1 instanceof com.google.android.systemui.assist.uihints.edgelights.mode.Gone
            if (r1 != 0) goto L_0x01c8
            goto L_0x01c9
        L_0x01c8:
            r2 = r3
        L_0x01c9:
            com.google.android.systemui.assist.uihints.NavBarFadeController r0 = r0.mNavBarFadeController
            r0.systemVisible = r2
            r0.update()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.assist.uihints.NgaUiController.refresh$1():void");
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
            glowController.setVisibility(i2);
            Context context = glowController.mContext;
            int lerp = (int) MathUtils.lerp(glowController.getBlurRadius(), context.getResources().getDimensionPixelSize(2131165980), Math.min(1.0f, 5.0f * f));
            GlowView glowView = glowController.mGlowView;
            if (glowView.mBlurRadius != lerp) {
                glowView.setBlurredImageOnViews(lerp);
            }
            int min = (int) MathUtils.min((int) MathUtils.lerp(glowController.getMinTranslationY(), context.getResources().getDimensionPixelSize(2131165982), f), context.getResources().getDimensionPixelSize(2131165976));
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
            scrimController.refresh();
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
        refresh$1();
    }
}
