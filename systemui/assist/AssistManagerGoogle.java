package com.google.android.systemui.assist;

import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.app.SearchManager;
import android.app.StatusBarManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.metrics.LogMaker;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.Log;
import android.view.IWindowManager;
import com.android.internal.app.AssistUtils;
import com.android.internal.app.IVoiceInteractionSessionListener;
import com.android.internal.app.IVoiceInteractionSessionShowCallback;
import com.android.internal.logging.MetricsLogger;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.systemui.assist.AssistDisclosure;
import com.android.systemui.assist.AssistLogger;
import com.android.systemui.assist.AssistManager$$ExternalSyntheticLambda0;
import com.android.systemui.assist.AssistManager$$ExternalSyntheticLambda1;
import com.android.systemui.assist.AssistManager$1;
import com.android.systemui.assist.AssistManager$2;
import com.android.systemui.assist.AssistManager$4;
import com.android.systemui.assist.AssistManager$5;
import com.android.systemui.assist.AssistManager$UiController;
import com.android.systemui.assist.AssistantSessionEvent;
import com.android.systemui.assist.PhoneStateMonitor;
import com.android.systemui.assist.domain.interactor.AssistInteractor;
import com.android.systemui.model.SysUiState;
import com.android.systemui.navigationbar.NavigationModeController;
import com.android.systemui.recents.OverviewProxyService;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.shared.recents.IOverviewProxy;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.DeviceProvisionedControllerImpl;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.systemui.util.settings.SecureSettings;
import com.google.android.systemui.assist.uihints.AssistantPresenceHandler;
import com.google.android.systemui.assist.uihints.GoogleDefaultUiController;
import com.google.android.systemui.assist.uihints.NgaMessageHandler;
import com.google.android.systemui.assist.uihints.NgaUiController;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class AssistManagerGoogle {
    public final ActivityManager mActivityManager;
    public final AssistDisclosure mAssistDisclosure;
    public final AssistLogger mAssistLogger;
    public int[] mAssistOverrideInvocationTypes;
    public final AssistUtils mAssistUtils;
    public final AssistantPresenceHandler mAssistantPresenceHandler;
    public boolean mCheckAssistantStatus;
    public final CommandQueue mCommandQueue;
    public final Context mContext;
    public final GoogleDefaultUiController mDefaultUiController;
    public final DeviceProvisionedController mDeviceProvisionedController;
    public boolean mGoogleIsAssistant;
    public final AssistInteractor mInteractor;
    public int mNavigationMode;
    public boolean mNgaIsAssistant;
    public final NgaMessageHandler mNgaMessageHandler;
    public final NgaUiController mNgaUiController;
    public final AssistManagerGoogle$$ExternalSyntheticLambda2 mOnProcessBundle;
    public final OpaEnabledReceiver mOpaEnabledReceiver;
    public final OverviewProxyService mOverviewProxyService;
    public final PhoneStateMonitor mPhoneStateMonitor;
    public final SecureSettings mSecureSettings;
    public final SelectedUserInteractor mSelectedUserInteractor;
    public boolean mSqueezeSetUp;
    public final Lazy mSysUiState;
    public AssistManager$UiController mUiController;
    public final Handler mUiHandler;
    public final UserTracker mUserTracker;
    public final List mVisualQueryAttentionListeners = new ArrayList();
    public final AssistManager$1 mVisualQueryDetectionAttentionListener = new AssistManager$1(this);
    public final IWindowManager mWindowManagerService;

    /* renamed from: -$$Nest$mhandleVisualAttentionChanged  reason: not valid java name */
    public static void m916$$Nest$mhandleVisualAttentionChanged(AssistManagerGoogle assistManagerGoogle, boolean z) {
        AssistManager$$ExternalSyntheticLambda1 assistManager$$ExternalSyntheticLambda1;
        StatusBarManager statusBarManager = (StatusBarManager) assistManagerGoogle.mContext.getSystemService(StatusBarManager.class);
        if (statusBarManager != null) {
            statusBarManager.setIconVisibility("assist_attention", z);
        }
        List list = assistManagerGoogle.mVisualQueryAttentionListeners;
        if (z) {
            assistManager$$ExternalSyntheticLambda1 = new AssistManager$$ExternalSyntheticLambda1(0);
        } else {
            assistManager$$ExternalSyntheticLambda1 = new AssistManager$$ExternalSyntheticLambda1(1);
        }
        ((ArrayList) list).forEach(assistManager$$ExternalSyntheticLambda1);
    }

    public AssistManagerGoogle(DeviceProvisionedController deviceProvisionedController, Context context, AssistUtils assistUtils, NgaUiController ngaUiController, CommandQueue commandQueue, OpaEnabledReceiver opaEnabledReceiver, PhoneStateMonitor phoneStateMonitor, OverviewProxyService overviewProxyService, OpaEnabledDispatcher opaEnabledDispatcher, KeyguardUpdateMonitor keyguardUpdateMonitor, NavigationModeController navigationModeController, AssistantPresenceHandler assistantPresenceHandler, NgaMessageHandler ngaMessageHandler, Lazy lazy, Handler handler, GoogleDefaultUiController googleDefaultUiController, IWindowManager iWindowManager, AssistLogger assistLogger, UserTracker userTracker, SecureSettings secureSettings, SelectedUserInteractor selectedUserInteractor, ActivityManager activityManager, AssistInteractor assistInteractor) {
        OverviewProxyService overviewProxyService2 = overviewProxyService;
        AssistantPresenceHandler assistantPresenceHandler2 = assistantPresenceHandler;
        Handler handler2 = handler;
        GoogleDefaultUiController googleDefaultUiController2 = googleDefaultUiController;
        this.mContext = context;
        this.mDeviceProvisionedController = deviceProvisionedController;
        this.mCommandQueue = commandQueue;
        this.mAssistUtils = assistUtils;
        this.mAssistDisclosure = new AssistDisclosure(context, handler2);
        this.mOverviewProxyService = overviewProxyService2;
        this.mPhoneStateMonitor = phoneStateMonitor;
        this.mAssistLogger = assistLogger;
        this.mUserTracker = userTracker;
        this.mSecureSettings = secureSettings;
        this.mSelectedUserInteractor = selectedUserInteractor;
        this.mActivityManager = activityManager;
        this.mInteractor = assistInteractor;
        assistUtils.registerVoiceInteractionSessionListener(new IVoiceInteractionSessionListener.Stub() {
            public final void onSetUiHints(Bundle bundle) {
                String string = bundle.getString("action");
                if ("set_assist_gesture_constrained".equals(string)) {
                    SysUiState sysUiState = (SysUiState) AssistManagerGoogle.this.mSysUiState.get();
                    sysUiState.setFlag(8192, bundle.getBoolean("should_constrain", false));
                    sysUiState.commitUpdate(0);
                } else if ("show_global_actions".equals(string)) {
                    try {
                        AssistManagerGoogle.this.mWindowManagerService.showGlobalActions();
                    } catch (RemoteException e) {
                        Log.e("AssistManagerGoogle", "showGlobalActions failed", e);
                    }
                } else {
                    AssistManagerGoogle assistManagerGoogle = AssistManagerGoogle.this;
                    assistManagerGoogle.mNgaMessageHandler.processBundle(bundle, assistManagerGoogle.mOnProcessBundle);
                }
            }

            public final void onVoiceSessionHidden() {
                AssistManagerGoogle.this.mAssistLogger.reportAssistantSessionEvent(AssistantSessionEvent.ASSISTANT_SESSION_CLOSE);
            }

            public final void onVoiceSessionShown() {
                AssistManagerGoogle.this.mAssistLogger.reportAssistantSessionEvent(AssistantSessionEvent.ASSISTANT_SESSION_UPDATE);
            }

            public final void onVoiceSessionWindowVisibilityChanged(boolean z) {
            }
        });
        if (context.getResources().getBoolean(2131034145)) {
            assistUtils.subscribeVisualQueryRecognitionStatus(new AssistManager$5(this));
        }
        this.mSysUiState = lazy;
        overviewProxyService2.addCallback((OverviewProxyService.OverviewProxyListener) new AssistManager$2(this));
        this.mCheckAssistantStatus = true;
        this.mUiHandler = handler2;
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
        this.mAssistantPresenceHandler = assistantPresenceHandler2;
        assistantPresenceHandler2.mAssistantPresenceChangeListeners.add(new AssistManagerGoogle$$ExternalSyntheticLambda1(this));
        this.mNgaMessageHandler = ngaMessageHandler;
        this.mOnProcessBundle = new AssistManagerGoogle$$ExternalSyntheticLambda2(0, this);
        this.mWindowManagerService = iWindowManager;
    }

    public final void addOpaEnabledListener(OpaEnabledListener opaEnabledListener) {
        OpaEnabledReceiver opaEnabledReceiver = this.mOpaEnabledReceiver;
        opaEnabledReceiver.mListeners.add(opaEnabledListener);
        OpaEnabledListener opaEnabledListener2 = opaEnabledListener;
        opaEnabledListener2.onOpaEnabledReceived(opaEnabledReceiver.mContext, opaEnabledReceiver.mIsOpaEligible, opaEnabledReceiver.mIsAGSAAssistant, opaEnabledReceiver.mIsOpaEnabled, opaEnabledReceiver.mIsLongPressHomeEnabled);
    }

    public final void hideAssist() {
        this.mAssistUtils.hideCurrentSession();
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
            assistantPresenceHandler.updateAssistantPresence(z2, assistantPresenceHandler.mNgaIsAssistant);
            this.mCheckAssistantStatus = false;
        }
        if (i != 2 || this.mSqueezeSetUp) {
            this.mUiController.onInvocationProgress(i, f);
        }
    }

    public final boolean shouldOverrideAssist(int i) {
        int[] iArr = this.mAssistOverrideInvocationTypes;
        if (iArr == null || !Arrays.stream(iArr).anyMatch(new AssistManager$$ExternalSyntheticLambda0(i))) {
            return false;
        }
        return true;
    }

    public final void startAssist(Bundle bundle) {
        Intent assistIntent;
        Parcel obtain;
        boolean z = true;
        if (this.mActivityManager.getLockTaskModeState() != 1) {
            if (bundle == null || !bundle.containsKey("invocation_type") || !shouldOverrideAssist(bundle.getInt("invocation_type"))) {
                int selectedUserId = this.mSelectedUserInteractor.getSelectedUserId();
                AssistUtils assistUtils = this.mAssistUtils;
                ComponentName assistComponentForUser = assistUtils.getAssistComponentForUser(selectedUserId);
                if (assistComponentForUser != null) {
                    boolean equals = assistComponentForUser.equals(assistUtils.getActiveServiceComponentName());
                    if (bundle == null) {
                        bundle = new Bundle();
                    }
                    Bundle bundle2 = bundle;
                    int i = bundle2.getInt("invocation_type", 0);
                    int phoneState = this.mPhoneStateMonitor.getPhoneState();
                    bundle2.putInt("invocation_phone_state", phoneState);
                    bundle2.putLong("invocation_time_ms", SystemClock.elapsedRealtime());
                    this.mAssistLogger.reportAssistantInvocationEventFromLegacy(i, true, assistComponentForUser, Integer.valueOf(phoneState));
                    MetricsLogger.action(new LogMaker(1716).setType(1).setSubtype((i << 1) | (phoneState << 4) | ((this.mAssistantPresenceHandler.mNgaIsAssistant ? 1 : 0) << true)));
                    this.mInteractor.getClass();
                    Context context = this.mContext;
                    if (equals) {
                        this.mAssistUtils.showSessionForActiveService(bundle2, 4, context.getAttributionTag(), (IVoiceInteractionSessionShowCallback) null, (IBinder) null);
                    } else if (((DeviceProvisionedControllerImpl) this.mDeviceProvisionedController).deviceProvisioned.get()) {
                        this.mCommandQueue.animateCollapsePanels(3, false);
                        if (this.mSecureSettings.getIntForUser("assist_structure_enabled", 1, -2) == 0) {
                            z = false;
                        }
                        SearchManager searchManager = (SearchManager) context.getSystemService("search");
                        if (searchManager != null && (assistIntent = searchManager.getAssistIntent(z)) != null) {
                            assistIntent.setComponent(assistComponentForUser);
                            assistIntent.putExtras(bundle2);
                            if (z && AssistUtils.isDisclosureEnabled(context)) {
                                AssistDisclosure assistDisclosure = this.mAssistDisclosure;
                                AssistDisclosure.AnonymousClass1 r3 = assistDisclosure.mShowRunnable;
                                Handler handler = assistDisclosure.mHandler;
                                handler.removeCallbacks(r3);
                                handler.post(r3);
                            }
                            try {
                                ActivityOptions makeCustomAnimation = ActivityOptions.makeCustomAnimation(context, 2130772562, 2130772563);
                                assistIntent.addFlags(268435456);
                                AsyncTask.execute(new AssistManager$4(this, assistIntent, makeCustomAnimation));
                            } catch (ActivityNotFoundException unused) {
                                Log.w("AssistManager", "Activity not found for " + assistIntent.getAction());
                            }
                        }
                    }
                }
            } else {
                try {
                    IOverviewProxy iOverviewProxy = this.mOverviewProxyService.mOverviewProxy;
                    if (iOverviewProxy == null) {
                        Log.w("AssistManager", "No OverviewProxyService to invoke assistant override");
                        return;
                    }
                    int i2 = bundle.getInt("invocation_type");
                    IOverviewProxy.Stub.Proxy proxy = (IOverviewProxy.Stub.Proxy) iOverviewProxy;
                    obtain = Parcel.obtain(proxy.mRemote);
                    obtain.writeInterfaceToken("com.android.systemui.shared.recents.IOverviewProxy");
                    obtain.writeInt(i2);
                    proxy.mRemote.transact(29, obtain, (Parcel) null, 1);
                    obtain.recycle();
                } catch (RemoteException e) {
                    Log.w("AssistManager", "Unable to invoke assistant via OverviewProxyService override", e);
                } catch (Throwable th) {
                    obtain.recycle();
                    throw th;
                }
            }
        }
    }
}
