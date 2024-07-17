package com.google.android.systemui.assist.uihints;

import android.animation.ObjectAnimator;
import android.os.Handler;
import android.view.View;
import com.android.systemui.navigationbar.NavigationBarControllerImpl;
import com.android.systemui.navigationbar.NavigationBarView;
import com.google.android.systemui.assist.uihints.NgaMessageHandler;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class NavBarFadeController implements NgaMessageHandler.NavBarVisibilityListener {
    public final Handler handler;
    public final NavBarFader navBarFader;
    public boolean ngaVisible = true;
    public final NavBarFadeController$onTimeout$1 onTimeout = new NavBarFadeController$onTimeout$1(this);
    public boolean systemVisible = true;

    public NavBarFadeController(NavBarFader navBarFader2, Handler handler2) {
        this.navBarFader = navBarFader2;
        this.handler = handler2;
    }

    public final void update() {
        float f;
        boolean z = this.systemVisible & this.ngaVisible;
        Handler handler2 = this.handler;
        NavBarFadeController$onTimeout$1 navBarFadeController$onTimeout$1 = this.onTimeout;
        handler2.removeCallbacks(navBarFadeController$onTimeout$1);
        if (!z) {
            handler2.postDelayed(navBarFadeController$onTimeout$1, 10000);
        }
        NavBarFader navBarFader2 = this.navBarFader;
        NavigationBarControllerImpl navigationBarControllerImpl = (NavigationBarControllerImpl) navBarFader2.navigationBarController;
        navigationBarControllerImpl.mDisplayTracker.getClass();
        NavigationBarView navigationBarView = navigationBarControllerImpl.getNavigationBarView(0);
        if (navigationBarView != null) {
            if (z) {
                f = 1.0f;
            } else {
                f = 0.0f;
            }
            if (f != navBarFader2.targetAlpha) {
                navBarFader2.animator.cancel();
                float alpha = navigationBarView.getAlpha();
                navBarFader2.targetAlpha = f;
                ObjectAnimator duration = ObjectAnimator.ofFloat(navigationBarView, View.ALPHA, new float[]{alpha, f}).setDuration((long) (Math.abs(f - alpha) * ((float) 80)));
                navBarFader2.animator = duration;
                if (z) {
                    duration.setStartDelay(80);
                }
                navBarFader2.animator.start();
            }
        }
    }
}
