package com.google.android.systemui.statusbar;

import android.app.AlarmManager;
import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.DeviceConfig;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import com.android.internal.app.IBatteryStats;
import com.android.internal.widget.LockPatternUtils;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.keyguard.logging.KeyguardLogger;
import com.android.settingslib.fuelgauge.BatteryStatus;
import com.android.systemui.biometrics.AuthController;
import com.android.systemui.biometrics.FaceHelpMessageDeferral;
import com.android.systemui.bouncer.domain.interactor.AlternateBouncerInteractor;
import com.android.systemui.bouncer.domain.interactor.BouncerMessageInteractor;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.dock.DockManager;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.keyguard.KeyguardIndication;
import com.android.systemui.keyguard.KeyguardIndicationRotateTextViewController;
import com.android.systemui.keyguard.ScreenLifecycle;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.keyguard.util.IndicationHelper;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.statusbar.KeyguardIndicationController;
import com.android.systemui.statusbar.KeyguardIndicationController$$ExternalSyntheticLambda7;
import com.android.systemui.statusbar.phone.KeyguardBypassController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.tuner.TunerService;
import com.android.systemui.util.DeviceConfigProxy;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.settings.GlobalSettings;
import com.android.systemui.util.wakelock.WakeLock;
import com.google.android.systemui.dreamliner.DockObserver;
import com.google.android.systemui.googlebattery.AdaptiveChargingManager;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class KeyguardIndicationControllerGoogle extends KeyguardIndicationController {
    public boolean mAdaptiveChargingActive;
    public boolean mAdaptiveChargingEnabledInSettings;
    protected AdaptiveChargingManager mAdaptiveChargingManager;
    protected AdaptiveChargingManager.AdaptiveChargingStatusReceiver mAdaptiveChargingStatusReceiver = new AdaptiveChargingManager.AdaptiveChargingStatusReceiver() {
        public final void onReceiveStatus(int i, String str) {
            boolean z;
            KeyguardIndicationControllerGoogle keyguardIndicationControllerGoogle = KeyguardIndicationControllerGoogle.this;
            boolean z2 = keyguardIndicationControllerGoogle.mAdaptiveChargingActive;
            boolean z3 = AdaptiveChargingManager.DEBUG;
            if (("Active".equals(str) || "Enabled".equals(str)) && i > 0) {
                z = true;
            } else {
                z = false;
            }
            keyguardIndicationControllerGoogle.mAdaptiveChargingActive = z;
            long j = keyguardIndicationControllerGoogle.mEstimatedChargeCompletion;
            long currentTimeMillis = System.currentTimeMillis();
            TimeUnit timeUnit = TimeUnit.SECONDS;
            long millis = timeUnit.toMillis((long) (i + 29)) + currentTimeMillis;
            keyguardIndicationControllerGoogle.mEstimatedChargeCompletion = millis;
            long abs = Math.abs(millis - j);
            boolean z4 = keyguardIndicationControllerGoogle.mAdaptiveChargingActive;
            if (z2 != z4 || (z4 && abs > timeUnit.toMillis(30))) {
                keyguardIndicationControllerGoogle.updateDeviceEntryIndication(true);
            }
        }
    };
    public int mBatteryLevel;
    public final BroadcastDispatcher mBroadcastDispatcher;
    public final AnonymousClass1 mBroadcastReceiver = new BroadcastReceiver() {
        public final void onReceive(Context context, Intent intent) {
            if ("com.google.android.systemui.adaptivecharging.ADAPTIVE_CHARGING_DEADLINE_SET".equals(intent.getAction())) {
                KeyguardIndicationControllerGoogle.this.triggerAdaptiveChargingStatusUpdate();
            }
        }
    };
    public final Context mContext;
    public final DeviceConfigProxy mDeviceConfig;
    public long mEstimatedChargeCompletion;
    public final GlobalSettings mGlobalSettings;
    public boolean mInited;
    public final TunerService mTunerService;
    public KeyguardUpdateMonitorCallback mUpdateMonitorCallback;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public class GoogleKeyguardCallback extends KeyguardIndicationController.BaseKeyguardCallback {
        public GoogleKeyguardCallback() {
            super();
        }

        public final void onRefreshBatteryInfo(BatteryStatus batteryStatus) {
            boolean z;
            boolean z2;
            boolean z3;
            boolean z4;
            boolean z5;
            boolean z6;
            boolean z7;
            long j;
            int i = batteryStatus.level;
            KeyguardIndicationControllerGoogle keyguardIndicationControllerGoogle = KeyguardIndicationControllerGoogle.this;
            keyguardIndicationControllerGoogle.mBatteryLevel = i;
            boolean z8 = true;
            int i2 = batteryStatus.status;
            if (i2 == 2 || BatteryStatus.isCharged(i2, i)) {
                z = true;
            } else {
                z = false;
            }
            KeyguardIndicationController keyguardIndicationController = KeyguardIndicationController.this;
            boolean z9 = keyguardIndicationController.mPowerPluggedIn;
            KeyguardLogger keyguardLogger = keyguardIndicationController.mKeyguardLogger;
            int i3 = batteryStatus.plugged;
            if ((i3 == 1 || i3 == 2) && z) {
                z2 = true;
            } else {
                z2 = false;
            }
            keyguardIndicationController.mPowerPluggedInWired = z2;
            if (i3 != 4 || !z) {
                z3 = false;
            } else {
                z3 = true;
            }
            keyguardIndicationController.mPowerPluggedInWireless = z3;
            if (i3 != 8 || !z) {
                z4 = false;
            } else {
                z4 = true;
            }
            keyguardIndicationController.mPowerPluggedInDock = z4;
            if (!BatteryStatus.isPluggedIn(i3) || !z) {
                z5 = false;
            } else {
                z5 = true;
            }
            keyguardIndicationController.mPowerPluggedIn = z5;
            int i4 = batteryStatus.level;
            keyguardIndicationController.mPowerCharged = BatteryStatus.isCharged(i2, i4);
            keyguardIndicationController.mChargingWattage = batteryStatus.maxChargingWattage;
            keyguardIndicationController.mChargingSpeed = batteryStatus.getChargingSpeed(keyguardIndicationController.mContext);
            keyguardIndicationController.mBatteryLevel = i4;
            keyguardIndicationController.mBatteryPresent = batteryStatus.present;
            if (batteryStatus.chargingStatus == 4) {
                z6 = true;
            } else {
                z6 = false;
            }
            keyguardIndicationController.mBatteryDefender = z6;
            if (!z6 || !BatteryStatus.isPluggedIn(i3)) {
                z7 = false;
            } else {
                z7 = true;
            }
            keyguardIndicationController.mEnableBatteryDefender = z7;
            keyguardIndicationController.mIncompatibleCharger = ((Boolean) batteryStatus.incompatibleCharger.orElse(Boolean.FALSE)).booleanValue();
            try {
                if (keyguardIndicationController.mPowerPluggedIn) {
                    j = keyguardIndicationController.mBatteryInfo.computeChargeTimeRemaining();
                } else {
                    j = -1;
                }
                keyguardIndicationController.mChargingTimeRemaining = j;
            } catch (RemoteException e) {
                keyguardLogger.buffer.log("KeyguardIndication", LogLevel.ERROR, "Error calling IBatteryStats", e);
                keyguardIndicationController.mChargingTimeRemaining = -1;
            }
            keyguardLogger.logRefreshBatteryInfo(keyguardIndicationController.mBatteryLevel, z, keyguardIndicationController.mPowerPluggedIn, keyguardIndicationController.mBatteryDefender);
            if (z9 || !keyguardIndicationController.mPowerPluggedInWired) {
                z8 = false;
            }
            keyguardIndicationController.updateDeviceEntryIndication(z8);
            if (keyguardIndicationControllerGoogle.mPowerPluggedIn) {
                keyguardIndicationControllerGoogle.triggerAdaptiveChargingStatusUpdate();
            } else {
                keyguardIndicationControllerGoogle.mAdaptiveChargingActive = false;
            }
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public KeyguardIndicationControllerGoogle(Context context, Looper looper, WakeLock.Builder builder, KeyguardStateController keyguardStateController, StatusBarStateController statusBarStateController, KeyguardUpdateMonitor keyguardUpdateMonitor, DockManager dockManager, BroadcastDispatcher broadcastDispatcher, DevicePolicyManager devicePolicyManager, IBatteryStats iBatteryStats, UserManager userManager, TunerService tunerService, DeviceConfigProxy deviceConfigProxy, DelayableExecutor delayableExecutor, DelayableExecutor delayableExecutor2, FalsingManager falsingManager, AuthController authController, LockPatternUtils lockPatternUtils, ScreenLifecycle screenLifecycle, KeyguardBypassController keyguardBypassController, AccessibilityManager accessibilityManager, FaceHelpMessageDeferral faceHelpMessageDeferral, KeyguardLogger keyguardLogger, GlobalSettings globalSettings, AlternateBouncerInteractor alternateBouncerInteractor, AlarmManager alarmManager, UserTracker userTracker, BouncerMessageInteractor bouncerMessageInteractor, FeatureFlags featureFlags, IndicationHelper indicationHelper, KeyguardInteractor keyguardInteractor) {
        super(context, looper, builder, keyguardStateController, statusBarStateController, keyguardUpdateMonitor, dockManager, broadcastDispatcher, devicePolicyManager, iBatteryStats, userManager, delayableExecutor, delayableExecutor2, falsingManager, authController, lockPatternUtils, screenLifecycle, keyguardBypassController, accessibilityManager, faceHelpMessageDeferral, keyguardLogger, alternateBouncerInteractor, alarmManager, userTracker, bouncerMessageInteractor, featureFlags, indicationHelper, keyguardInteractor);
        Context context2 = context;
        Context context3 = context;
        this.mContext = context3;
        this.mBroadcastDispatcher = broadcastDispatcher;
        this.mTunerService = tunerService;
        this.mDeviceConfig = deviceConfigProxy;
        this.mAdaptiveChargingManager = new AdaptiveChargingManager(context3);
        this.mGlobalSettings = globalSettings;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00aa, code lost:
        if (r0 != false) goto L_0x00ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00df, code lost:
        if (r0 != false) goto L_0x00ac;
     */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00f1  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0104  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String computePowerIndication() {
        /*
            r8 = this;
            boolean r0 = r8.mPowerPluggedIn
            r1 = 1120403456(0x42c80000, float:100.0)
            if (r0 == 0) goto L_0x0035
            boolean r2 = r8.mAdaptiveChargingEnabledInSettings
            if (r2 == 0) goto L_0x0035
            boolean r2 = r8.mAdaptiveChargingActive
            if (r2 == 0) goto L_0x0035
            com.google.android.systemui.googlebattery.AdaptiveChargingManager r0 = r8.mAdaptiveChargingManager
            long r2 = r8.mEstimatedChargeCompletion
            java.lang.String r0 = r0.formatTimeToFull(r2)
            java.text.NumberFormat r2 = java.text.NumberFormat.getPercentInstance()
            int r3 = r8.mBatteryLevel
            float r3 = (float) r3
            float r3 = r3 / r1
            double r3 = (double) r3
            java.lang.String r1 = r2.format(r3)
            android.content.Context r8 = r8.mContext
            android.content.res.Resources r8 = r8.getResources()
            r2 = 2131951871(0x7f1300ff, float:1.9540169E38)
            java.lang.Object[] r0 = new java.lang.Object[]{r1, r0}
            java.lang.String r8 = r8.getString(r2, r0)
            return r8
        L_0x0035:
            boolean r2 = r8.mBatteryDefender
            android.content.Context r3 = r8.mContext
            if (r2 == 0) goto L_0x0059
            java.text.NumberFormat r0 = java.text.NumberFormat.getPercentInstance()
            int r8 = r8.mBatteryLevel
            float r8 = (float) r8
            float r8 = r8 / r1
            double r1 = (double) r8
            java.lang.String r8 = r0.format(r1)
            android.content.res.Resources r0 = r3.getResources()
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            r1 = 2131952810(0x7f1304aa, float:1.9542073E38)
            java.lang.String r8 = r0.getString(r1, r8)
            goto L_0x0110
        L_0x0059:
            if (r0 == 0) goto L_0x007d
            boolean r0 = r8.mIncompatibleCharger
            if (r0 == 0) goto L_0x007d
            java.text.NumberFormat r0 = java.text.NumberFormat.getPercentInstance()
            int r8 = r8.mBatteryLevel
            float r8 = (float) r8
            float r8 = r8 / r1
            double r1 = (double) r8
            java.lang.String r8 = r0.format(r1)
            android.content.res.Resources r0 = r3.getResources()
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            r1 = 2131952813(0x7f1304ad, float:1.954208E38)
            java.lang.String r8 = r0.getString(r1, r8)
            goto L_0x0110
        L_0x007d:
            boolean r0 = r8.mPowerCharged
            if (r0 == 0) goto L_0x008e
            android.content.res.Resources r8 = r3.getResources()
            r0 = 2131952780(0x7f13048c, float:1.9542012E38)
            java.lang.String r8 = r8.getString(r0)
            goto L_0x0110
        L_0x008e:
            long r4 = r8.mChargingTimeRemaining
            r6 = 0
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 <= 0) goto L_0x0098
            r0 = 1
            goto L_0x0099
        L_0x0098:
            r0 = 0
        L_0x0099:
            boolean r2 = r8.mPowerPluggedInWired
            r4 = 2131952808(0x7f1304a8, float:1.954207E38)
            r5 = 2131952798(0x7f13049e, float:1.9542049E38)
            if (r2 == 0) goto L_0x00c3
            int r2 = r8.mChargingSpeed
            if (r2 == 0) goto L_0x00b9
            r6 = 2
            if (r2 == r6) goto L_0x00ae
            if (r0 == 0) goto L_0x00e2
        L_0x00ac:
            r4 = r5
            goto L_0x00e2
        L_0x00ae:
            if (r0 == 0) goto L_0x00b5
            r2 = 2131952800(0x7f1304a0, float:1.9542053E38)
        L_0x00b3:
            r4 = r2
            goto L_0x00e2
        L_0x00b5:
            r2 = 2131952809(0x7f1304a9, float:1.9542071E38)
            goto L_0x00b3
        L_0x00b9:
            if (r0 == 0) goto L_0x00bf
            r2 = 2131952801(0x7f1304a1, float:1.9542055E38)
            goto L_0x00b3
        L_0x00bf:
            r2 = 2131952811(0x7f1304ab, float:1.9542075E38)
            goto L_0x00b3
        L_0x00c3:
            boolean r2 = r8.mPowerPluggedInWireless
            if (r2 == 0) goto L_0x00d1
            if (r0 == 0) goto L_0x00cd
            r2 = 2131952802(0x7f1304a2, float:1.9542057E38)
            goto L_0x00b3
        L_0x00cd:
            r2 = 2131952814(0x7f1304ae, float:1.9542081E38)
            goto L_0x00b3
        L_0x00d1:
            boolean r2 = r8.mPowerPluggedInDock
            if (r2 == 0) goto L_0x00df
            if (r0 == 0) goto L_0x00db
            r2 = 2131952799(0x7f13049f, float:1.954205E38)
            goto L_0x00b3
        L_0x00db:
            r2 = 2131952812(0x7f1304ac, float:1.9542077E38)
            goto L_0x00b3
        L_0x00df:
            if (r0 == 0) goto L_0x00e2
            goto L_0x00ac
        L_0x00e2:
            java.text.NumberFormat r2 = java.text.NumberFormat.getPercentInstance()
            int r5 = r8.mBatteryLevel
            float r5 = (float) r5
            float r5 = r5 / r1
            double r5 = (double) r5
            java.lang.String r1 = r2.format(r5)
            if (r0 == 0) goto L_0x0104
            long r5 = r8.mChargingTimeRemaining
            java.lang.String r8 = android.text.format.Formatter.formatShortElapsedTimeRoundingUpToMinutes(r3, r5)
            android.content.res.Resources r0 = r3.getResources()
            java.lang.Object[] r8 = new java.lang.Object[]{r8, r1}
            java.lang.String r8 = r0.getString(r4, r8)
            goto L_0x0110
        L_0x0104:
            android.content.res.Resources r8 = r3.getResources()
            java.lang.Object[] r0 = new java.lang.Object[]{r1}
            java.lang.String r8 = r8.getString(r4, r0)
        L_0x0110:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.statusbar.KeyguardIndicationControllerGoogle.computePowerIndication():java.lang.String");
    }

    public final void init() {
        if (!this.mInited) {
            this.mInited = true;
            KeyguardIndicationController$$ExternalSyntheticLambda7 keyguardIndicationController$$ExternalSyntheticLambda7 = new KeyguardIndicationController$$ExternalSyntheticLambda7(this);
            DockObserver dockObserver = (DockObserver) this.mDockManager;
            dockObserver.getClass();
            Log.d("DLObserver", "add alignment listener: " + keyguardIndicationController$$ExternalSyntheticLambda7);
            List list = dockObserver.mAlignmentStateListeners;
            if (!list.contains(keyguardIndicationController$$ExternalSyntheticLambda7)) {
                list.add(keyguardIndicationController$$ExternalSyntheticLambda7);
            }
            if (this.mUpdateMonitorCallback == null) {
                this.mUpdateMonitorCallback = new GoogleKeyguardCallback();
            }
            this.mKeyguardUpdateMonitor.registerCallback(this.mUpdateMonitorCallback);
            StatusBarStateController statusBarStateController = this.mStatusBarStateController;
            KeyguardIndicationController.AnonymousClass4 r2 = this.mStatusBarStateListener;
            statusBarStateController.addCallback(r2);
            ((KeyguardStateControllerImpl) this.mKeyguardStateController).addCallback(this.mKeyguardStateCallback);
            r2.onDozingChanged(statusBarStateController.isDozing());
        }
        if (!this.mInited) {
            this.mInited = true;
            this.mTunerService.addTunable(new KeyguardIndicationControllerGoogle$$ExternalSyntheticLambda0(this), "adaptive_charging_enabled");
            KeyguardIndicationControllerGoogle$$ExternalSyntheticLambda1 keyguardIndicationControllerGoogle$$ExternalSyntheticLambda1 = new KeyguardIndicationControllerGoogle$$ExternalSyntheticLambda1(this);
            this.mDeviceConfig.getClass();
            DeviceConfig.addOnPropertiesChangedListener("adaptive_charging", this.mExecutor, keyguardIndicationControllerGoogle$$ExternalSyntheticLambda1);
            triggerAdaptiveChargingStatusUpdate();
            this.mBroadcastDispatcher.registerReceiver(this.mBroadcastReceiver, new IntentFilter("com.google.android.systemui.adaptivecharging.ADAPTIVE_CHARGING_DEADLINE_SET"), (Executor) null, UserHandle.ALL);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0019, code lost:
        if (r3.mAdaptiveChargingManager.isEnabled() != false) goto L_0x001d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void refreshAdaptiveChargingEnabled() {
        /*
            r3 = this;
            com.google.android.systemui.googlebattery.AdaptiveChargingManager r0 = r3.mAdaptiveChargingManager
            boolean r0 = r0.hasAdaptiveChargingFeature()
            if (r0 == 0) goto L_0x001c
            java.lang.String r0 = "adaptive_charging"
            java.lang.String r1 = "adaptive_charging_enabled"
            r2 = 1
            boolean r0 = android.provider.DeviceConfig.getBoolean(r0, r1, r2)
            if (r0 == 0) goto L_0x001c
            com.google.android.systemui.googlebattery.AdaptiveChargingManager r0 = r3.mAdaptiveChargingManager
            boolean r0 = r0.isEnabled()
            if (r0 == 0) goto L_0x001c
            goto L_0x001d
        L_0x001c:
            r2 = 0
        L_0x001d:
            r3.mAdaptiveChargingEnabledInSettings = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.statusbar.KeyguardIndicationControllerGoogle.refreshAdaptiveChargingEnabled():void");
    }

    public final void setReverseChargingMessage(CharSequence charSequence) {
        if (!this.mStatusBarStateController.isDozing()) {
            if (TextUtils.isEmpty(charSequence)) {
                this.mRotateTextViewController.hideIndication(10);
                return;
            }
            KeyguardIndicationRotateTextViewController keyguardIndicationRotateTextViewController = this.mRotateTextViewController;
            Drawable drawable = this.mContext.getDrawable(2130772561);
            ColorStateList colorStateList = this.mInitialTextColorState;
            if (TextUtils.isEmpty(charSequence) && drawable == null) {
                throw new IllegalStateException("message or icon must be set");
            } else if (colorStateList != null) {
                keyguardIndicationRotateTextViewController.updateIndication(10, new KeyguardIndication(charSequence, colorStateList, drawable, (View.OnClickListener) null, (Drawable) null, (Long) null), false);
            } else {
                throw new IllegalStateException("text color must be set");
            }
        }
    }

    public final void showTrustGrantedMessage(String str, boolean z) {
        if (str == null || (this.mGlobalSettings.getInt(0, "chip_all_watch_unlocks") == 0 && !z)) {
            this.mTrustGrantedIndication = str;
            updateDeviceEntryIndication(false);
            return;
        }
        this.mTrustGrantedIndication = "";
        updateDeviceEntryIndication(false);
    }

    public final void triggerAdaptiveChargingStatusUpdate() {
        refreshAdaptiveChargingEnabled();
        if (this.mAdaptiveChargingEnabledInSettings) {
            AdaptiveChargingManager adaptiveChargingManager = this.mAdaptiveChargingManager;
            AdaptiveChargingManager.AdaptiveChargingStatusReceiver adaptiveChargingStatusReceiver = this.mAdaptiveChargingStatusReceiver;
            adaptiveChargingManager.getClass();
            AdaptiveChargingManager.queryStatus(adaptiveChargingStatusReceiver);
            return;
        }
        this.mAdaptiveChargingActive = false;
    }
}
