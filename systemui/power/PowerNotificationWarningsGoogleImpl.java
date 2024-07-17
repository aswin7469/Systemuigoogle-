package com.google.android.systemui.power;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.os.ServiceSpecificException;
import android.os.UserHandle;
import android.provider.Settings;
import android.service.dreams.IDreamManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import com.android.internal.logging.UiEventLogger;
import com.android.keyguard.ActiveUnlockConfig$$ExternalSyntheticOutline0;
import com.android.keyguard.KeyguardClockSwitch$$ExternalSyntheticOutline0;
import com.android.systemui.animation.DialogCuj;
import com.android.systemui.animation.DialogLaunchAnimator;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.broadcast.BroadcastSender;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.power.EnhancedEstimates;
import com.android.systemui.power.PowerNotificationWarnings;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.BatteryControllerImpl;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.util.settings.GlobalSettings;
import com.android.systemui.util.time.SystemClockImpl;
import com.google.android.systemui.googlebattery.GoogleBatteryManager;
import dagger.Lazy;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.internal.ContextScope;
import vendor.google.google_battery.IGoogleBattery;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class PowerNotificationWarningsGoogleImpl extends PowerNotificationWarnings {
    public final ActivityStarter mActivityStarter;
    public AdaptiveChargingNotification mAdaptiveChargingNotification;
    public final Lazy mBatteryControllerLazy;
    public BatteryDefenderNotificationHandler mBatteryDefenderNotificationHandler;
    public final BatteryEventClient mBatteryEventClient;
    public BatteryInfoBroadcast mBatteryInfoBroadcast;
    public BatterySaverConfirmationDialog mBatterySaverConfirmationDialog;
    public final BroadcastDispatcher mBroadcastDispatcher;
    final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        public final void onReceive(Context context, Intent intent) {
            String action;
            String action2;
            View view;
            if (intent != null && intent.getAction() != null) {
                Log.d("PowerNotificationWarningsGoogleImpl", "onReceive: " + intent.getAction());
                int i = 0;
                if ("PNW.startSaverConfirmation".equals(intent.getAction())) {
                    PowerNotificationWarningsGoogleImpl.this.mLowPowerWarningsController.cancelNotification();
                    PowerNotificationWarningsGoogleImpl powerNotificationWarningsGoogleImpl = PowerNotificationWarningsGoogleImpl.this;
                    powerNotificationWarningsGoogleImpl.mBatterySaverConfirmationDialog = new BatterySaverConfirmationDialog(context, powerNotificationWarningsGoogleImpl.mActivityStarter, powerNotificationWarningsGoogleImpl.mUiEventLogger, powerNotificationWarningsGoogleImpl.mDialogLaunchAnimator);
                    WeakReference weakReference = (WeakReference) ((BatteryControllerImpl) ((BatteryController) powerNotificationWarningsGoogleImpl.mBatteryControllerLazy.get())).mPowerSaverStartView.get();
                    BatterySaverConfirmationDialog batterySaverConfirmationDialog = PowerNotificationWarningsGoogleImpl.this.mBatterySaverConfirmationDialog;
                    if (weakReference != null) {
                        view = (View) weakReference.get();
                    } else {
                        view = null;
                    }
                    SystemUIDialog systemUIDialog = batterySaverConfirmationDialog.mConfirmationDialog;
                    if (systemUIDialog == null || !systemUIDialog.isShowing()) {
                        SystemUIDialog systemUIDialog2 = batterySaverConfirmationDialog.mConfirmationDialog;
                        if (systemUIDialog2 != null) {
                            systemUIDialog2.show();
                            return;
                        }
                        Context context2 = batterySaverConfirmationDialog.mContext;
                        View inflate = LayoutInflater.from(context2).inflate(2131558485, (ViewGroup) null);
                        RadioButton radioButton = (RadioButton) inflate.findViewById(2131363664);
                        RadioButton radioButton2 = (RadioButton) inflate.findViewById(2131362550);
                        batterySaverConfirmationDialog.mIsStandardMode = true;
                        inflate.findViewById(2131363665).setOnClickListener(new BatterySaverConfirmationDialog$$ExternalSyntheticLambda2(batterySaverConfirmationDialog, radioButton, radioButton2, 0));
                        inflate.findViewById(2131362551).setOnClickListener(new BatterySaverConfirmationDialog$$ExternalSyntheticLambda2(batterySaverConfirmationDialog, radioButton, radioButton2, 1));
                        ((Button) inflate.findViewById(2131363578)).setOnClickListener(new BatterySaverConfirmationDialog$$ExternalSyntheticLambda3(batterySaverConfirmationDialog));
                        SystemUIDialog systemUIDialog3 = new SystemUIDialog(context2);
                        batterySaverConfirmationDialog.mConfirmationDialog = systemUIDialog3;
                        systemUIDialog3.setTitle(2131953680);
                        batterySaverConfirmationDialog.mConfirmationDialog.setMessage(2131953676);
                        batterySaverConfirmationDialog.mConfirmationDialog.setView(inflate);
                        SystemUIDialog systemUIDialog4 = batterySaverConfirmationDialog.mConfirmationDialog;
                        systemUIDialog4.getClass();
                        SystemUIDialog.setShowForAllUsers(systemUIDialog4);
                        batterySaverConfirmationDialog.mConfirmationDialog.setCanceledOnTouchOutside(true);
                        batterySaverConfirmationDialog.mConfirmationDialog.setPositiveButton(2131951967, new BatterySaverConfirmationDialog$$ExternalSyntheticLambda0(batterySaverConfirmationDialog, 0));
                        batterySaverConfirmationDialog.mConfirmationDialog.setNeutralButton(2131953675, new BatterySaverConfirmationDialog$$ExternalSyntheticLambda0(batterySaverConfirmationDialog, 1), true);
                        if (view == null || !view.isAggregatedVisible()) {
                            batterySaverConfirmationDialog.mConfirmationDialog.show();
                        } else {
                            SystemUIDialog systemUIDialog5 = batterySaverConfirmationDialog.mConfirmationDialog;
                            DialogLaunchAnimator dialogLaunchAnimator = batterySaverConfirmationDialog.mDialogLaunchAnimator;
                            dialogLaunchAnimator.getClass();
                            DialogLaunchAnimator.showFromView$default(dialogLaunchAnimator, systemUIDialog5, view, (DialogCuj) null, false, 12);
                        }
                        batterySaverConfirmationDialog.log(BatteryMetricEvent.SAVER_CONFIRMATION_DIALOG);
                        return;
                    }
                    return;
                }
                BatteryInfoBroadcast batteryInfoBroadcast = PowerNotificationWarningsGoogleImpl.this.mBatteryInfoBroadcast;
                batteryInfoBroadcast.getClass();
                batteryInfoBroadcast.mExecutor.execute(new BatteryInfoBroadcast$$ExternalSyntheticLambda0(batteryInfoBroadcast, intent));
                LowPowerWarningsController lowPowerWarningsController = PowerNotificationWarningsGoogleImpl.this.mLowPowerWarningsController;
                lowPowerWarningsController.getClass();
                lowPowerWarningsController.executor.execute(new LowPowerWarningsController$dispatchIntent$1(lowPowerWarningsController, intent));
                BatteryDefenderNotificationHandler batteryDefenderNotificationHandler = PowerNotificationWarningsGoogleImpl.this.mBatteryDefenderNotificationHandler;
                if (!(batteryDefenderNotificationHandler == null || (action2 = intent.getAction()) == null)) {
                    int hashCode = action2.hashCode();
                    ContextScope contextScope = batteryDefenderNotificationHandler.backgroundCoroutineScope;
                    UiEventLogger uiEventLogger = batteryDefenderNotificationHandler.uiEventLogger;
                    switch (hashCode) {
                        case -1838162442:
                            if (action2.equals("PNW.defenderResumeCharging")) {
                                Log.d("BatteryDefenderNotification", "onActionResumeChargingIntent");
                                if (uiEventLogger != null) {
                                    uiEventLogger.logWithPosition(BatteryMetricEvent.BATTERY_DEFENDER_BYPASS_LIMIT, 0, (String) null, batteryDefenderNotificationHandler.batteryLevel);
                                }
                                batteryDefenderNotificationHandler.getDwellTempDefenderNotification().cancelNotification();
                                BuildersKt.launch$default(contextScope, (CoroutineContext) null, (CoroutineStart) null, new BatteryDefenderNotificationHandler$onActionResumeChargingIntent$1(batteryDefenderNotificationHandler, (Continuation) null), 3);
                                break;
                            }
                            break;
                        case -1769274126:
                            if (action2.equals("systemui.power.action.dismissBatteryDefenderWarning")) {
                                DwellTempDefenderNotification dwellTempDefenderNotification = batteryDefenderNotificationHandler.getDwellTempDefenderNotification();
                                Log.d("DwellTempDefenderNotification", "swipeNotificationByUser, notificationVisible:" + dwellTempDefenderNotification.notificationVisible + "->false");
                                dwellTempDefenderNotification.notificationVisible = false;
                                UiEventLogger uiEventLogger2 = dwellTempDefenderNotification.uiEventLogger;
                                if (uiEventLogger2 != null) {
                                    uiEventLogger2.log(BatteryMetricEvent.DELETE_BATTERY_DEFENDER_NOTIFICATION);
                                    break;
                                }
                            }
                            break;
                        case -646306533:
                            if (action2.equals("PNW.defenderResumeCharging.settings")) {
                                Log.d("BatteryDefenderNotification", "onActionResumeChargingSettings");
                                if (uiEventLogger != null) {
                                    uiEventLogger.logWithPosition(BatteryMetricEvent.BATTERY_DEFENDER_BYPASS_LIMIT_FOR_TIPS, 0, (String) null, batteryDefenderNotificationHandler.batteryLevel);
                                }
                                batteryDefenderNotificationHandler.getDwellTempDefenderNotification().cancelNotification();
                                if (intent.getBooleanExtra("is_dock_defender", false)) {
                                    i = 4;
                                }
                                BuildersKt.launch$default(contextScope, (CoroutineContext) null, (CoroutineStart) null, new BatteryDefenderNotificationHandler$onActionResumeChargingSettings$1(batteryDefenderNotificationHandler, i, (Continuation) null), 3);
                                break;
                            }
                            break;
                        case 798292259:
                            if (action2.equals("android.intent.action.BOOT_COMPLETED") && batteryDefenderNotificationHandler.dockDefendEnabled) {
                                BuildersKt.launch$default(contextScope, (CoroutineContext) null, (CoroutineStart) null, new BatteryDefenderNotificationHandler$onActionBootCompleted$1(batteryDefenderNotificationHandler, (Continuation) null), 3);
                                break;
                            }
                    }
                }
                AdaptiveChargingNotification adaptiveChargingNotification = PowerNotificationWarningsGoogleImpl.this.mAdaptiveChargingNotification;
                if (!(adaptiveChargingNotification == null || (action = intent.getAction()) == null)) {
                    if ("android.intent.action.BATTERY_CHANGED".equals(action)) {
                        adaptiveChargingNotification.resolveBatteryChangedIntent(intent);
                    } else if ("com.google.android.systemui.adaptivecharging.ADAPTIVE_CHARGING_DEADLINE_SET".equals(action)) {
                        adaptiveChargingNotification.checkAdaptiveChargingStatus(true);
                    } else {
                        boolean equals = "PNW.acChargeNormally".equals(action);
                        UiEventLogger uiEventLogger3 = adaptiveChargingNotification.mUiEventLogger;
                        if (equals) {
                            uiEventLogger3.log(BatteryMetricEvent.ADAPTIVE_CHARGING_NOTIFICATION_BYPASS);
                            adaptiveChargingNotification.mAdaptiveChargingManager.getClass();
                            IGoogleBattery initHalInterface = GoogleBatteryManager.initHalInterface((IBinder.DeathRecipient) null);
                            if (initHalInterface != null) {
                                try {
                                    ((IGoogleBattery.Stub.Proxy) initHalInterface).setChargingDeadline();
                                } catch (RemoteException | ServiceSpecificException | IllegalArgumentException e) {
                                    Log.e("AdaptiveChargingManager", "setChargingDeadline failed: ", e);
                                }
                                GoogleBatteryManager.destroyHalInterface(initHalInterface, (IBinder.DeathRecipient) null);
                            }
                            adaptiveChargingNotification.cancelNotification();
                            Intent intent2 = new Intent("com.google.android.systemui.adaptivecharging.ADAPTIVE_CHARGING_DEADLINE_SET");
                            Context context3 = adaptiveChargingNotification.mContext;
                            context3.sendBroadcastAsUser(intent2.setPackage(context3.getPackageName()).setFlags(1342177280), UserHandle.ALL);
                        } else if ("systemui.power.action.dismissAdaptiveChargingWarning".equals(action) && uiEventLogger3 != null) {
                            uiEventLogger3.log(BatteryMetricEvent.DELETE_ADAPTIVE_CHARGING_NOTIFICATION);
                        }
                    }
                }
                IncompatibleChargerNotification incompatibleChargerNotification = PowerNotificationWarningsGoogleImpl.this.mIncompatibleChargerNotification;
                if (incompatibleChargerNotification != null) {
                    String action3 = intent.getAction();
                    Log.d("IncompatibleChargerNotification", "dispatchIntent: " + action3);
                    if ("systemui.power.action.dismissIncompatibleChargerWarning".equals(action3)) {
                        BatteryMetricEvent batteryMetricEvent = BatteryMetricEvent.DELETE_INCOMPATIBLE_CHARGING_NOTIFICATION;
                        UiEventLogger uiEventLogger4 = incompatibleChargerNotification.mUiEventLogger;
                        if (uiEventLogger4 != null) {
                            uiEventLogger4.log(batteryMetricEvent);
                        }
                    }
                }
            }
        }
    };
    public final BroadcastSender mBroadcastSender;
    public final Context mContext;
    public final DialogLaunchAnimator mDialogLaunchAnimator;
    public final EnhancedEstimates mEnhancedEstimates;
    public final Executor mExecutor = Executors.newFixedThreadPool(2);
    public final GlobalSettings mGlobalSettings;
    public final Handler mHandler;
    public final IDreamManager mIDreamManager;
    public IncompatibleChargerNotification mIncompatibleChargerNotification;
    public final KeyguardStateController mKeyguardStateController;
    LowPowerWarningsController mLowPowerWarningsController;
    public final UiEventLogger mUiEventLogger;
    public final UserTracker mUserTracker;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PowerNotificationWarningsGoogleImpl(Context context, ActivityStarter activityStarter, BroadcastDispatcher broadcastDispatcher, BroadcastSender broadcastSender, UiEventLogger uiEventLogger, GlobalSettings globalSettings, Lazy lazy, DialogLaunchAnimator dialogLaunchAnimator, EnhancedEstimates enhancedEstimates, KeyguardStateController keyguardStateController, IDreamManager iDreamManager, UserTracker userTracker, BatteryEventClient batteryEventClient) {
        super(context, activityStarter, broadcastSender, lazy, dialogLaunchAnimator, uiEventLogger, userTracker);
        UiEventLogger uiEventLogger2 = uiEventLogger;
        Handler handler = new Handler(Looper.getMainLooper());
        this.mHandler = handler;
        this.mContext = context;
        this.mBroadcastDispatcher = broadcastDispatcher;
        this.mBroadcastSender = broadcastSender;
        this.mUiEventLogger = uiEventLogger2;
        this.mGlobalSettings = globalSettings;
        this.mEnhancedEstimates = enhancedEstimates;
        this.mKeyguardStateController = keyguardStateController;
        this.mIDreamManager = iDreamManager;
        this.mActivityStarter = activityStarter;
        this.mUserTracker = userTracker;
        this.mBatteryEventClient = batteryEventClient;
        this.mDialogLaunchAnimator = dialogLaunchAnimator;
        this.mBatteryControllerLazy = lazy;
        handler.post(new PowerNotificationWarningsGoogleImpl$$ExternalSyntheticLambda0(this, context, uiEventLogger2));
    }

    public final void dismissLowBatteryWarning() {
        LowPowerWarningsController lowPowerWarningsController = this.mLowPowerWarningsController;
        if (lowPowerWarningsController != null) {
            lowPowerWarningsController.cancelNotification();
        }
    }

    public final void dump(PrintWriter printWriter) {
        boolean z;
        boolean z2;
        super.dump(printWriter);
        BatteryInfoBroadcast batteryInfoBroadcast = this.mBatteryInfoBroadcast;
        if (batteryInfoBroadcast != null) {
            printWriter.println("\tdump BatteryInfoBroadcast states:");
            batteryInfoBroadcast.writeString(printWriter, "LastConnectedTime: ", "last_phone_connected_time");
            batteryInfoBroadcast.writeString(printWriter, "LastDisconnectedTime: ", "last_phone_disconnected_time");
            batteryInfoBroadcast.writeString(printWriter, "LastDataResetTime: ", "last_data_reset_time");
        }
        IncompatibleChargerNotification incompatibleChargerNotification = this.mIncompatibleChargerNotification;
        if (incompatibleChargerNotification != null) {
            StringBuilder m = KeyguardClockSwitch$$ExternalSyntheticOutline0.m(printWriter, "\tdump IncompatibleCharger states:", "\t\tLastCompatibleChargerTime: ");
            Context context = incompatibleChargerNotification.mContext;
            m.append(DumpUtils.toReadableDateTime(IncompatibleChargerNotification.getSharedPreferences(context).getLong(IncompatibleChargerNotification.KEY_COMPATIBLE_CHARGER_TIME, 0)));
            StringBuilder m2 = KeyguardClockSwitch$$ExternalSyntheticOutline0.m(printWriter, m.toString(), "\t\tLastIncompatibleChargerTime: ");
            m2.append(DumpUtils.toReadableDateTime(IncompatibleChargerNotification.getSharedPreferences(context).getLong(IncompatibleChargerNotification.KEY_INCOMPATIBLE_CHARGER_TIME, 0)));
            printWriter.println(m2.toString());
        }
        LowPowerWarningsController lowPowerWarningsController = this.mLowPowerWarningsController;
        boolean z3 = false;
        if (lowPowerWarningsController != null) {
            printWriter.println("\tdump LowPowerWarningsController states");
            printWriter.println("\t\tprevBatteryLevel: " + lowPowerWarningsController.prevBatteryLevel);
            printWriter.println("\t\tprevBatteryEventType: " + lowPowerWarningsController.prevBatteryEventTypes);
            if (lowPowerWarningsController.globalSettings.getInt(1, "low_power_mode_reminder_enabled") == 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            printWriter.println("\t\tisBatterySaverReminderDisabled: " + z2);
            ActiveUnlockConfig$$ExternalSyntheticOutline0.m("\t\tisScheduledByPercentage: ", lowPowerWarningsController.isScheduledByPercentage(), printWriter);
            ActiveUnlockConfig$$ExternalSyntheticOutline0.m("\t\tlowBatteryNotificationCancelled: ", lowPowerWarningsController.lowBatteryNotificationCancelled, printWriter);
        }
        BatteryDefenderNotificationHandler batteryDefenderNotificationHandler = this.mBatteryDefenderNotificationHandler;
        if (batteryDefenderNotificationHandler != null) {
            printWriter.println("\tdump BatteryDefenderNotificationHandler states:");
            Context context2 = batteryDefenderNotificationHandler.context;
            if (Settings.Global.getInt(context2.getContentResolver(), "dock_defender_first_run", -1) == -1) {
                z = true;
            } else {
                z = false;
            }
            printWriter.println("\t\tdockDefendFirstRun: " + z);
            if (Settings.Global.getInt(context2.getContentResolver(), "dock_defender_bypass", 0) == 1) {
                z3 = true;
            }
            ActiveUnlockConfig$$ExternalSyntheticOutline0.m("\t\tdockDefendBypassed: ", z3, printWriter);
            ActiveUnlockConfig$$ExternalSyntheticOutline0.m("\t\tdockDefendEnabled: ", batteryDefenderNotificationHandler.dockDefendEnabled, printWriter);
            ActiveUnlockConfig$$ExternalSyntheticOutline0.m("\t\tinBatteryDefenderSection: ", batteryDefenderNotificationHandler.inDefenderSection, printWriter);
            printWriter.println("\t\tbatteryLevel: " + batteryDefenderNotificationHandler.batteryLevel);
            printWriter.println("\t\tstartTimestamp: " + ((SharedPreferences) batteryDefenderNotificationHandler.sharedPreferences$delegate.getValue()).getLong("trigger_time", -1));
            ((SystemClockImpl) batteryDefenderNotificationHandler.systemClock).getClass();
            printWriter.println("\t\tcurrentTimestamp: " + System.currentTimeMillis());
        }
        this.mExecutor.execute(new PowerNotificationWarningsGoogleImpl$$ExternalSyntheticLambda1(this));
    }

    public final void userSwitched() {
        Integer num;
        LowPowerWarningsController lowPowerWarningsController = this.mLowPowerWarningsController;
        if (lowPowerWarningsController != null && (num = lowPowerWarningsController.prevBatteryLevel) != null) {
            lowPowerWarningsController.onBatteryEventUpdate(num.intValue(), lowPowerWarningsController.prevBatteryEventTypes);
        }
    }
}
