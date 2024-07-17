package com.google.android.systemui.assist;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.os.Handler;
import android.provider.Settings;
import android.view.IWindowManager;
import com.android.internal.app.AssistUtils;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.systemui.assist.AssistLogger;
import com.android.systemui.assist.AssistManager;
import com.android.systemui.assist.PhoneStateMonitor;
import com.android.systemui.navigationbar.NavigationModeController;
import com.android.systemui.recents.OverviewProxyService;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.systemui.util.settings.SecureSettings;
import com.google.android.systemui.assist.uihints.AssistantPresenceHandler;
import com.google.android.systemui.assist.uihints.GoogleDefaultUiController;
import com.google.android.systemui.assist.uihints.NgaMessageHandler;
import com.google.android.systemui.assist.uihints.NgaUiController;
import dagger.Lazy;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class AssistManagerGoogle extends AssistManager {
    public final AssistantPresenceHandler mAssistantPresenceHandler;
    public boolean mCheckAssistantStatus = true;
    public final GoogleDefaultUiController mDefaultUiController;
    public boolean mGoogleIsAssistant;
    public int mNavigationMode;
    public boolean mNgaIsAssistant;
    public final NgaMessageHandler mNgaMessageHandler;
    public final NgaUiController mNgaUiController;
    public final AssistManagerGoogle$$ExternalSyntheticLambda2 mOnProcessBundle;
    public final OpaEnabledReceiver mOpaEnabledReceiver;
    public boolean mSqueezeSetUp;
    public AssistManager.UiController mUiController;
    public final Handler mUiHandler;
    public final IWindowManager mWindowManagerService;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AssistManagerGoogle(DeviceProvisionedController deviceProvisionedController, Context context, AssistUtils assistUtils, NgaUiController ngaUiController, CommandQueue commandQueue, OpaEnabledReceiver opaEnabledReceiver, PhoneStateMonitor phoneStateMonitor, OverviewProxyService overviewProxyService, OpaEnabledDispatcher opaEnabledDispatcher, KeyguardUpdateMonitor keyguardUpdateMonitor, NavigationModeController navigationModeController, AssistantPresenceHandler assistantPresenceHandler, NgaMessageHandler ngaMessageHandler, Lazy lazy, Handler handler, GoogleDefaultUiController googleDefaultUiController, IWindowManager iWindowManager, AssistLogger assistLogger, UserTracker userTracker, SecureSettings secureSettings, SelectedUserInteractor selectedUserInteractor, ActivityManager activityManager) {
        super(deviceProvisionedController, context, assistUtils, commandQueue, phoneStateMonitor, overviewProxyService, lazy, assistLogger, handler, userTracker, secureSettings, selectedUserInteractor, activityManager);
        AssistantPresenceHandler assistantPresenceHandler2 = assistantPresenceHandler;
        GoogleDefaultUiController googleDefaultUiController2 = googleDefaultUiController;
        this.mUiHandler = handler;
        this.mOpaEnabledReceiver = opaEnabledReceiver;
        addOpaEnabledListener(opaEnabledDispatcher);
        keyguardUpdateMonitor.registerCallback(new KeyguardUpdateMonitorCallback() {
            public final void onUserSwitching(int i) {
                OpaEnabledReceiver opaEnabledReceiver = AssistManagerGoogle.this.mOpaEnabledReceiver;
                opaEnabledReceiver.updateOpaEnabledState(true, (BroadcastReceiver.PendingResult) null);
                opaEnabledReceiver.mContentResolver.unregisterContentObserver(opaEnabledReceiver.mContentObserver);
                opaEnabledReceiver.registerContentObserver();
                opaEnabledReceiver.mContext.unregisterReceiver(opaEnabledReceiver.mBroadcastReceiver);
                opaEnabledReceiver.registerEnabledReceiver(i);
            }
        });
        this.mNgaUiController = ngaUiController;
        this.mDefaultUiController = googleDefaultUiController2;
        this.mUiController = googleDefaultUiController2;
        this.mNavigationMode = navigationModeController.addListener(new AssistManagerGoogle$$ExternalSyntheticLambda0(this));
        AssistantPresenceHandler assistantPresenceHandler3 = assistantPresenceHandler;
        this.mAssistantPresenceHandler = assistantPresenceHandler3;
        assistantPresenceHandler3.mAssistantPresenceChangeListeners.add(new AssistManagerGoogle$$ExternalSyntheticLambda1(this));
        this.mNgaMessageHandler = ngaMessageHandler;
        this.mOnProcessBundle = new AssistManagerGoogle$$ExternalSyntheticLambda2(2, this);
        this.mWindowManagerService = iWindowManager;
    }

    public final void addOpaEnabledListener(OpaEnabledListener opaEnabledListener) {
        OpaEnabledReceiver opaEnabledReceiver = this.mOpaEnabledReceiver;
        opaEnabledReceiver.mListeners.add(opaEnabledListener);
        OpaEnabledListener opaEnabledListener2 = opaEnabledListener;
        opaEnabledListener2.onOpaEnabledReceived(opaEnabledReceiver.mContext, opaEnabledReceiver.mIsOpaEligible, opaEnabledReceiver.mIsAGSAAssistant, opaEnabledReceiver.mIsOpaEnabled, opaEnabledReceiver.mIsLongPressHomeEnabled);
    }

    public final void onInvocationProgress(int i, float f) {
        boolean z;
        boolean z2 = true;
        if (f == 0.0f || f == 1.0f) {
            this.mCheckAssistantStatus = true;
            if (i == 2) {
                if (Settings.Secure.getInt(this.mContext.getContentResolver(), "assist_gesture_setup_complete", 0) == 1) {
                    z = true;
                } else {
                    z = false;
                }
                this.mSqueezeSetUp = z;
            }
        }
        if (this.mCheckAssistantStatus) {
            AssistantPresenceHandler assistantPresenceHandler = this.mAssistantPresenceHandler;
            ComponentName assistComponentForUser = assistantPresenceHandler.mAssistUtils.getAssistComponentForUser(-2);
            if (assistComponentForUser == null || !"com.google.android.googlequicksearchbox/com.google.android.voiceinteraction.GsaVoiceInteractionService".equals(assistComponentForUser.flattenToString())) {
                z2 = false;
            }
            assistantPresenceHandler.updateAssistantPresence(z2, assistantPresenceHandler.mNgaIsAssistant, assistantPresenceHandler.mSysUiIsNgaUi);
            this.mCheckAssistantStatus = false;
        }
        if (i != 2 || this.mSqueezeSetUp) {
            this.mUiController.onInvocationProgress(i, f);
        }
    }
}
