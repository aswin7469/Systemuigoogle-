package com.google.android.systemui.dagger;

import com.android.systemui.statusbar.phone.PhoneStatusBarView;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class DaggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl {
    public final Provider endSideContentProvider;
    public final Provider getStatusBarLocationProvider;
    public final Provider headsUpAppearanceControllerProvider;
    public final Provider legacyLightsOutNotifControllerProvider;
    public final PhoneStatusBarView phoneStatusBarView;
    public final Provider provideBatteryMeterViewProvider;
    public final Provider provideClockProvider;
    public final Provider provideLightsOutNotifViewProvider;
    public final Provider provideOperatorFrameNameViewProvider;
    public final Provider provideOperatorNameViewProvider;
    public final Provider providePhoneStatusBarTransitionsProvider;
    public final Provider providePhoneStatusBarViewControllerProvider;
    public final Provider providesHeasdUpStatusBarViewProvider;
    public final Provider startSideContentProvider;
    public final Provider statusBarBoundsProvider;
    public final Provider statusBarDemoModeProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl sysUIGoogleGlobalRootComponentImpl;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl sysUIGoogleSysUIComponentImpl;

    public DaggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, PhoneStatusBarView phoneStatusBarView2) {
        this.sysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
        this.sysUIGoogleSysUIComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
        this.phoneStatusBarView = phoneStatusBarView2;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
        this.provideBatteryMeterViewProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 0, 9));
        this.getStatusBarLocationProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 1, 9));
        this.providePhoneStatusBarViewControllerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 2, 9));
        this.providePhoneStatusBarTransitionsProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 4, 9));
        this.providesHeasdUpStatusBarViewProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 5, 9));
        this.provideClockProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 6, 9));
        this.provideOperatorFrameNameViewProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 7, 9));
        this.headsUpAppearanceControllerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 3, 9));
        this.provideLightsOutNotifViewProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 9, 9));
        this.legacyLightsOutNotifControllerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 8, 9));
        this.provideOperatorNameViewProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 11, 9));
        this.statusBarDemoModeProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 10, 9));
        this.startSideContentProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 13, 9));
        this.endSideContentProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 14, 9));
        this.statusBarBoundsProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, this, 12, 9));
    }
}