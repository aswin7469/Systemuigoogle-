package com.google.android.systemui.dagger;

import com.android.systemui.unfold.FullscreenLightRevealAnimation;
import com.android.systemui.unfold.UnfoldTransitionProgressProvider;
import com.android.systemui.unfold.util.NaturalRotationUnfoldProgressProvider;
import com.android.systemui.unfold.util.ScopedUnfoldTransitionProgressProvider;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl;
import dagger.internal.DoubleCheck;
import dagger.internal.InstanceFactory;
import dagger.internal.Preconditions;
import dagger.internal.SingleCheck;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl {
    public final Provider bindsFoldLightRevealOverlayAnimationProvider;
    public final Provider factoryProvider;
    public final Provider foldAodAnimationControllerProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider foldLightRevealOverlayAnimationProvider;
    public final Provider keyguardUnfoldTransitionProvider;
    public final Provider notificationPanelUnfoldAnimationControllerProvider;
    public final InstanceFactory p1Provider;
    public final NaturalRotationUnfoldProgressProvider p2;
    public final ScopedUnfoldTransitionProgressProvider p3;
    public final InstanceFactory p4Provider;
    public final Provider unfoldHapticsPlayerProvider;
    public final Provider unfoldLatencyTrackerProvider;
    public final Provider unfoldLightRevealOverlayAnimationProvider;
    public final Provider unfoldTransitionWallpaperControllerProvider;

    public DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, UnfoldTransitionProgressProvider unfoldTransitionProgressProvider, NaturalRotationUnfoldProgressProvider naturalRotationUnfoldProgressProvider, ScopedUnfoldTransitionProgressProvider scopedUnfoldTransitionProgressProvider, UnfoldTransitionProgressProvider unfoldTransitionProgressProvider2) {
        this.p2 = naturalRotationUnfoldProgressProvider;
        this.p3 = scopedUnfoldTransitionProgressProvider;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
        this.keyguardUnfoldTransitionProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 0, 10));
        DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 1, 10));
        this.notificationPanelUnfoldAnimationControllerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 2, 10));
        this.foldAodAnimationControllerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 3, 10));
        this.p4Provider = new InstanceFactory(unfoldTransitionProgressProvider2);
        this.p1Provider = new InstanceFactory(unfoldTransitionProgressProvider);
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
        this.factoryProvider = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, this, 5, 10));
        this.unfoldLightRevealOverlayAnimationProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, this, 4, 10));
        this.bindsFoldLightRevealOverlayAnimationProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, this, 6, 10));
        this.unfoldTransitionWallpaperControllerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, this, 7, 10));
        this.unfoldHapticsPlayerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, this, 8, 10));
        this.unfoldLatencyTrackerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, this, 9, 10));
    }

    public final Set getFullScreenLightRevealAnimations() {
        ArrayList arrayList = new ArrayList(2);
        FullscreenLightRevealAnimation fullscreenLightRevealAnimation = (FullscreenLightRevealAnimation) this.unfoldLightRevealOverlayAnimationProvider.get();
        Preconditions.checkNotNull(fullscreenLightRevealAnimation, "Set contributions cannot be null");
        arrayList.add(fullscreenLightRevealAnimation);
        FullscreenLightRevealAnimation fullscreenLightRevealAnimation2 = (FullscreenLightRevealAnimation) this.bindsFoldLightRevealOverlayAnimationProvider.get();
        Preconditions.checkNotNull(fullscreenLightRevealAnimation2, "Set contributions cannot be null");
        arrayList.add(fullscreenLightRevealAnimation2);
        if (arrayList.isEmpty()) {
            return Collections.emptySet();
        }
        if (arrayList.size() == 1) {
            return Collections.singleton(arrayList.get(0));
        }
        return Collections.unmodifiableSet(new HashSet(arrayList));
    }
}
