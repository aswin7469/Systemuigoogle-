package com.google.android.systemui.assist.uihints;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.WindowManager;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.assist.ui.DefaultUiController;
import com.android.systemui.navigationbar.NavigationBar;
import com.android.systemui.navigationbar.NavigationBarController;
import com.android.systemui.navigationbar.NavigationBarControllerImpl;
import com.android.systemui.navigationbar.NavigationBarTransitions;
import com.google.android.systemui.assist.GoogleAssistLogger;
import dagger.Lazy;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class GoogleDefaultUiController extends DefaultUiController {
    public GoogleDefaultUiController(Context context, GoogleAssistLogger googleAssistLogger, WindowManager windowManager, MetricsLogger metricsLogger, Lazy lazy, NavigationBarController navigationBarController) {
        super(context, googleAssistLogger, windowManager, metricsLogger, lazy, navigationBarController);
        NavigationBarController navigationBarController2;
        NavigationBar defaultNavigationBar;
        context.getResources();
        AssistantInvocationLightsView assistantInvocationLightsView = (AssistantInvocationLightsView) this.mInvocationLightsView;
        assistantInvocationLightsView.mUseNavBarColor = true;
        assistantInvocationLightsView.mPaint.setStrokeCap(Paint.Cap.BUTT);
        if (!(assistantInvocationLightsView.mRegistered || (navigationBarController2 = assistantInvocationLightsView.mNavigationBarController) == null || (defaultNavigationBar = ((NavigationBarControllerImpl) navigationBarController2).getDefaultNavigationBar()) == null)) {
            NavigationBarTransitions navigationBarTransitions = defaultNavigationBar.mNavigationBarTransitions;
            navigationBarTransitions.mDarkIntensityListeners.add(assistantInvocationLightsView);
            assistantInvocationLightsView.updateDarkness(navigationBarTransitions.mLightTransitionsController.mDarkIntensity);
            assistantInvocationLightsView.mRegistered = true;
        }
        AssistantInvocationLightsView assistantInvocationLightsView2 = (AssistantInvocationLightsView) LayoutInflater.from(context).inflate(2131558629, this.mRoot, false);
        this.mInvocationLightsView = assistantInvocationLightsView2;
        this.mRoot.addView(assistantInvocationLightsView2);
    }
}
