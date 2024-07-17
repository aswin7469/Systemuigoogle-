package com.google.android.systemui.assist.uihints;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Paint;
import android.os.Handler;
import android.provider.Settings;
import com.android.internal.app.AssistUtils;
import com.android.systemui.assist.AssistManager$UiController;
import com.android.systemui.assist.ui.EdgeLight;
import com.android.systemui.navigationbar.NavigationBar;
import com.android.systemui.navigationbar.NavigationBarControllerImpl;
import com.android.systemui.navigationbar.NavigationBarTransitions;
import com.google.android.systemui.assist.AssistManagerGoogle;
import com.google.android.systemui.assist.AssistManagerGoogle$$ExternalSyntheticLambda1;
import com.google.android.systemui.assist.AssistManagerGoogle$$ExternalSyntheticLambda2;
import com.google.android.systemui.assist.uihints.NgaMessageHandler;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class AssistantPresenceHandler implements NgaMessageHandler.ConfigInfoListener {
    public final AssistUtils mAssistUtils;
    public final Set mAssistantPresenceChangeListeners = new HashSet();
    public final ContentResolver mContentResolver;
    public boolean mGoogleIsAssistant;
    public boolean mNgaIsAssistant;

    public AssistantPresenceHandler(Context context, AssistUtils assistUtils) {
        ContentResolver contentResolver = context.getContentResolver();
        this.mContentResolver = contentResolver;
        this.mAssistUtils = assistUtils;
        this.mNgaIsAssistant = Settings.Secure.getInt(contentResolver, "com.google.android.systemui.assist.uihints.NGA_IS_ASSISTANT", 0) != 0;
    }

    public final void onConfigInfo(NgaMessageHandler.ConfigInfo configInfo) {
        boolean z;
        ComponentName assistComponentForUser = this.mAssistUtils.getAssistComponentForUser(-2);
        if (assistComponentForUser == null || !"com.google.android.googlequicksearchbox/com.google.android.voiceinteraction.GsaVoiceInteractionService".equals(assistComponentForUser.flattenToString())) {
            z = false;
        } else {
            z = true;
        }
        updateAssistantPresence(z, configInfo.ngaIsAssistant);
    }

    public final void updateAssistantPresence(boolean z, boolean z2) {
        boolean z3;
        NavigationBarControllerImpl navigationBarControllerImpl;
        NavigationBar defaultNavigationBar;
        NavigationBarControllerImpl navigationBarControllerImpl2;
        NavigationBar defaultNavigationBar2;
        if (!z || !z2) {
            z3 = false;
        } else {
            z3 = true;
        }
        if (this.mGoogleIsAssistant != z || this.mNgaIsAssistant != z3) {
            this.mGoogleIsAssistant = z;
            this.mNgaIsAssistant = z3;
            Settings.Secure.putInt(this.mContentResolver, "com.google.android.systemui.assist.uihints.NGA_IS_ASSISTANT", z3 ? 1 : 0);
            Iterator it = ((HashSet) this.mAssistantPresenceChangeListeners).iterator();
            while (it.hasNext()) {
                boolean z4 = this.mGoogleIsAssistant;
                boolean z5 = this.mNgaIsAssistant;
                AssistManagerGoogle assistManagerGoogle = ((AssistManagerGoogle$$ExternalSyntheticLambda1) it.next()).f$0;
                if (assistManagerGoogle.mGoogleIsAssistant != z4 || assistManagerGoogle.mNgaIsAssistant != z5) {
                    Handler handler = assistManagerGoogle.mUiHandler;
                    if (z5) {
                        AssistManager$UiController assistManager$UiController = assistManagerGoogle.mUiController;
                        NgaUiController ngaUiController = assistManagerGoogle.mNgaUiController;
                        if (!assistManager$UiController.equals(ngaUiController)) {
                            AssistManager$UiController assistManager$UiController2 = assistManagerGoogle.mUiController;
                            assistManagerGoogle.mUiController = ngaUiController;
                            Objects.requireNonNull(assistManager$UiController2);
                            handler.post(new AssistManagerGoogle$$ExternalSyntheticLambda2(1, assistManager$UiController2));
                        }
                    } else {
                        AssistManager$UiController assistManager$UiController3 = assistManagerGoogle.mUiController;
                        GoogleDefaultUiController googleDefaultUiController = assistManagerGoogle.mDefaultUiController;
                        if (!assistManager$UiController3.equals(googleDefaultUiController)) {
                            AssistManager$UiController assistManager$UiController4 = assistManagerGoogle.mUiController;
                            assistManagerGoogle.mUiController = googleDefaultUiController;
                            Objects.requireNonNull(assistManager$UiController4);
                            handler.post(new AssistManagerGoogle$$ExternalSyntheticLambda2(1, assistManager$UiController4));
                        }
                        AssistantInvocationLightsView assistantInvocationLightsView = googleDefaultUiController.mInvocationLightsView;
                        AssistantInvocationLightsView assistantInvocationLightsView2 = assistantInvocationLightsView;
                        if (z4) {
                            int i = assistantInvocationLightsView.mColorBlue;
                            int i2 = assistantInvocationLightsView.mColorRed;
                            int i3 = assistantInvocationLightsView.mColorYellow;
                            int i4 = assistantInvocationLightsView.mColorGreen;
                            assistantInvocationLightsView.mUseNavBarColor = false;
                            if (!(!assistantInvocationLightsView.mRegistered || (navigationBarControllerImpl2 = assistantInvocationLightsView.mNavigationBarController) == null || (defaultNavigationBar2 = navigationBarControllerImpl2.getDefaultNavigationBar()) == null)) {
                                defaultNavigationBar2.mNavigationBarTransitions.mDarkIntensityListeners.remove(assistantInvocationLightsView);
                                assistantInvocationLightsView.mRegistered = false;
                            }
                            ((EdgeLight) assistantInvocationLightsView.mAssistInvocationLights.get(0)).setColor(i);
                            ((EdgeLight) assistantInvocationLightsView.mAssistInvocationLights.get(1)).setColor(i2);
                            ((EdgeLight) assistantInvocationLightsView.mAssistInvocationLights.get(2)).setColor(i3);
                            ((EdgeLight) assistantInvocationLightsView.mAssistInvocationLights.get(3)).setColor(i4);
                        } else {
                            assistantInvocationLightsView.mUseNavBarColor = true;
                            assistantInvocationLightsView.mPaint.setStrokeCap(Paint.Cap.BUTT);
                            if (!(assistantInvocationLightsView.mRegistered || (navigationBarControllerImpl = assistantInvocationLightsView.mNavigationBarController) == null || (defaultNavigationBar = navigationBarControllerImpl.getDefaultNavigationBar()) == null)) {
                                NavigationBarTransitions navigationBarTransitions = defaultNavigationBar.mNavigationBarTransitions;
                                navigationBarTransitions.mDarkIntensityListeners.add(assistantInvocationLightsView);
                                assistantInvocationLightsView.updateDarkness(navigationBarTransitions.mLightTransitionsController.mDarkIntensity);
                                assistantInvocationLightsView.mRegistered = true;
                            }
                        }
                    }
                    assistManagerGoogle.mGoogleIsAssistant = z4;
                    assistManagerGoogle.mNgaIsAssistant = z5;
                }
                assistManagerGoogle.mCheckAssistantStatus = false;
            }
        }
    }
}
