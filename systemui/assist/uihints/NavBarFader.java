package com.google.android.systemui.assist.uihints;

import android.animation.ObjectAnimator;
import com.android.systemui.navigationbar.NavigationBarControllerImpl;
import com.android.systemui.navigationbar.NavigationBarView;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class NavBarFader {
    public ObjectAnimator animator = new ObjectAnimator();
    public final NavigationBarControllerImpl navigationBarController;
    public float targetAlpha;

    public NavBarFader(NavigationBarControllerImpl navigationBarControllerImpl) {
        float f;
        this.navigationBarController = navigationBarControllerImpl;
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
