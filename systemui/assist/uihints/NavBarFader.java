package com.google.android.systemui.assist.uihints;

import android.animation.ObjectAnimator;
import com.android.systemui.navigationbar.NavigationBarController;
import com.android.systemui.navigationbar.NavigationBarControllerImpl;
import com.android.systemui.navigationbar.NavigationBarView;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class NavBarFader {
    public ObjectAnimator animator = new ObjectAnimator();
    public final NavigationBarController navigationBarController;
    public float targetAlpha;

    public NavBarFader(NavigationBarController navigationBarController2) {
        float f;
        this.navigationBarController = navigationBarController2;
        NavigationBarControllerImpl navigationBarControllerImpl = (NavigationBarControllerImpl) navigationBarController2;
        navigationBarControllerImpl.mDisplayTracker.getClass();
        NavigationBarView navigationBarView = navigationBarControllerImpl.getNavigationBarView(0);
        if (navigationBarView != null) {
            f = navigationBarView.getAlpha();
        } else {
            f = 1.0f;
        }
        this.targetAlpha = f;
    }
}
