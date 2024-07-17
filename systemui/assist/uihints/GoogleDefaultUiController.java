package com.google.android.systemui.assist.uihints;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.WindowManager;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.assist.ui.DefaultUiController;
import com.android.systemui.navigationbar.NavigationBar;
import com.android.systemui.navigationbar.NavigationBarControllerImpl;
import com.android.systemui.navigationbar.NavigationBarTransitions;
import com.google.android.systemui.assist.GoogleAssistLogger;
import dagger.Lazy;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class GoogleDefaultUiController extends DefaultUiController {
    public GoogleDefaultUiController(Context context, GoogleAssistLogger googleAssistLogger, WindowManager windowManager, MetricsLogger metricsLogger, Lazy lazy, NavigationBarControllerImpl navigationBarControllerImpl) {
        super(context, googleAssistLogger, windowManager, metricsLogger, lazy, navigationBarControllerImpl);
        NavigationBarControllerImpl navigationBarControllerImpl2;
        NavigationBar defaultNavigationBar;
        context.getResources();
        AssistantInvocationLightsView assistantInvocationLightsView = this.mInvocationLightsView;
        AssistantInvocationLightsView assistantInvocationLightsView2 = assistantInvocationLightsView;
        assistantInvocationLightsView.mUseNavBarColor = true;
        assistantInvocationLightsView.mPaint.setStrokeCap(Paint.Cap.BUTT);
        if (!(assistantInvocationLightsView.mRegistered || (navigationBarControllerImpl2 = assistantInvocationLightsView.mNavigationBarController) == null || (defaultNavigationBar = navigationBarControllerImpl2.getDefaultNavigationBar()) == null)) {
            NavigationBarTransitions navigationBarTransitions = defaultNavigationBar.mNavigationBarTransitions;
            navigationBarTransitions.mDarkIntensityListeners.add(assistantInvocationLightsView);
            assistantInvocationLightsView.updateDarkness(navigationBarTransitions.mLightTransitionsController.mDarkIntensity);
            assistantInvocationLightsView.mRegistered = true;
        }
        AssistantInvocationLightsView assistantInvocationLightsView3 = (AssistantInvocationLightsView) LayoutInflater.from(context).inflate(2131558635, this.mRoot, false);
        this.mInvocationLightsView = assistantInvocationLightsView3;
        this.mRoot.addView(assistantInvocationLightsView3);
    }
}
