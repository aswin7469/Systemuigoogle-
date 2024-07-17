package com.google.android.systemui.assist.uihints;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Paint;
import android.os.Handler;
import android.provider.Settings;
import com.android.internal.app.AssistUtils;
import com.android.systemui.assist.AssistManager;
import com.android.systemui.assist.ui.EdgeLight;
import com.android.systemui.navigationbar.NavigationBar;
import com.android.systemui.navigationbar.NavigationBarController;
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

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class AssistantPresenceHandler implements NgaMessageHandler.ConfigInfoListener {
    public final AssistUtils mAssistUtils;
    public final Set mAssistantPresenceChangeListeners = new HashSet();
    public final ContentResolver mContentResolver;
    public boolean mGoogleIsAssistant;
    public boolean mNgaIsAssistant;
    public boolean mSysUiIsNgaUi;
    public final Set mSysUiIsNgaUiChangeListeners = new HashSet();

    public AssistantPresenceHandler(Context context, AssistUtils assistUtils) {
        boolean z;
        ContentResolver contentResolver = context.getContentResolver();
        this.mContentResolver = contentResolver;
        this.mAssistUtils = assistUtils;
        boolean z2 = false;
        if (Settings.Secure.getInt(contentResolver, "com.google.android.systemui.assist.uihints.NGA_IS_ASSISTANT", 0) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.mNgaIsAssistant = z;
        this.mSysUiIsNgaUi = Settings.Secure.getInt(contentResolver, "com.google.android.systemui.assist.uihints.SYS_UI_IS_NGA_UI", 0) != 0 ? true : z2;
    }

    public final void onConfigInfo(NgaMessageHandler.ConfigInfo configInfo) {
        boolean z;
        ComponentName assistComponentForUser = this.mAssistUtils.getAssistComponentForUser(-2);
        if (assistComponentForUser == null || !"com.google.android.googlequicksearchbox/com.google.android.voiceinteraction.GsaVoiceInteractionService".equals(assistComponentForUser.flattenToString())) {
            z = false;
        } else {
            z = true;
        }
        updateAssistantPresence(z, configInfo.ngaIsAssistant, configInfo.sysUiIsNgaUi);
    }

    public final void updateAssistantPresence(boolean z, boolean z2, boolean z3) {
        boolean z4;
        boolean z5;
        NavigationBarController navigationBarController;
        NavigationBar defaultNavigationBar;
        NavigationBarController navigationBarController2;
        NavigationBar defaultNavigationBar2;
        if (!z || !z2) {
            z4 = false;
        } else {
            z4 = true;
        }
        if (!z4 || !z3) {
            z5 = false;
        } else {
            z5 = true;
        }
        boolean z6 = this.mGoogleIsAssistant;
        ContentResolver contentResolver = this.mContentResolver;
        if (!(z6 == z && this.mNgaIsAssistant == z4)) {
            this.mGoogleIsAssistant = z;
            this.mNgaIsAssistant = z4;
            Settings.Secure.putInt(contentResolver, "com.google.android.systemui.assist.uihints.NGA_IS_ASSISTANT", z4 ? 1 : 0);
            Iterator it = ((HashSet) this.mAssistantPresenceChangeListeners).iterator();
            while (it.hasNext()) {
                boolean z7 = this.mGoogleIsAssistant;
                boolean z8 = this.mNgaIsAssistant;
                AssistManagerGoogle assistManagerGoogle = ((AssistManagerGoogle$$ExternalSyntheticLambda1) it.next()).f$0;
                if (assistManagerGoogle.mGoogleIsAssistant != z7 || assistManagerGoogle.mNgaIsAssistant != z8) {
                    Handler handler = assistManagerGoogle.mUiHandler;
                    if (z8) {
                        AssistManager.UiController uiController = assistManagerGoogle.mUiController;
                        NgaUiController ngaUiController = assistManagerGoogle.mNgaUiController;
                        if (!uiController.equals(ngaUiController)) {
                            AssistManager.UiController uiController2 = assistManagerGoogle.mUiController;
                            assistManagerGoogle.mUiController = ngaUiController;
                            Objects.requireNonNull(uiController2);
                            handler.post(new AssistManagerGoogle$$ExternalSyntheticLambda2(0, uiController2));
                        }
                    } else {
                        AssistManager.UiController uiController3 = assistManagerGoogle.mUiController;
                        GoogleDefaultUiController googleDefaultUiController = assistManagerGoogle.mDefaultUiController;
                        if (!uiController3.equals(googleDefaultUiController)) {
                            AssistManager.UiController uiController4 = assistManagerGoogle.mUiController;
                            assistManagerGoogle.mUiController = googleDefaultUiController;
                            Objects.requireNonNull(uiController4);
                            handler.post(new AssistManagerGoogle$$ExternalSyntheticLambda2(1, uiController4));
                        }
                        AssistantInvocationLightsView assistantInvocationLightsView = (AssistantInvocationLightsView) googleDefaultUiController.mInvocationLightsView;
                        if (z7) {
                            int i = assistantInvocationLightsView.mColorBlue;
                            int i2 = assistantInvocationLightsView.mColorRed;
                            int i3 = assistantInvocationLightsView.mColorYellow;
                            int i4 = assistantInvocationLightsView.mColorGreen;
                            assistantInvocationLightsView.mUseNavBarColor = false;
                            if (!(!assistantInvocationLightsView.mRegistered || (navigationBarController2 = assistantInvocationLightsView.mNavigationBarController) == null || (defaultNavigationBar2 = ((NavigationBarControllerImpl) navigationBarController2).getDefaultNavigationBar()) == null)) {
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
                            if (!(assistantInvocationLightsView.mRegistered || (navigationBarController = assistantInvocationLightsView.mNavigationBarController) == null || (defaultNavigationBar = ((NavigationBarControllerImpl) navigationBarController).getDefaultNavigationBar()) == null)) {
                                NavigationBarTransitions navigationBarTransitions = defaultNavigationBar.mNavigationBarTransitions;
                                navigationBarTransitions.mDarkIntensityListeners.add(assistantInvocationLightsView);
                                assistantInvocationLightsView.updateDarkness(navigationBarTransitions.mLightTransitionsController.mDarkIntensity);
                                assistantInvocationLightsView.mRegistered = true;
                            }
                        }
                    }
                    assistManagerGoogle.mGoogleIsAssistant = z7;
                    assistManagerGoogle.mNgaIsAssistant = z8;
                }
                assistManagerGoogle.mCheckAssistantStatus = false;
            }
        }
        if (this.mSysUiIsNgaUi != z5) {
            this.mSysUiIsNgaUi = z5;
            Settings.Secure.putInt(contentResolver, "com.google.android.systemui.assist.uihints.SYS_UI_IS_NGA_UI", z5 ? 1 : 0);
            Iterator it2 = ((HashSet) this.mSysUiIsNgaUiChangeListeners).iterator();
            while (it2.hasNext()) {
                boolean z9 = this.mSysUiIsNgaUi;
                NgaUiController ngaUiController2 = ((NgaUiController$$ExternalSyntheticLambda3) it2.next()).f$0;
                if (!z9) {
                    ngaUiController2.hide();
                } else {
                    ngaUiController2.getClass();
                }
            }
        }
    }
}
