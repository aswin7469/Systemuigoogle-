package com.google.android.systemui.dreamliner;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.os.UserManager;
import android.service.dreams.IDreamManager;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0;
import com.android.settingslib.Utils$$ExternalSyntheticOutline0;
import com.android.settingslib.bluetooth.LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0;
import com.android.systemui.dock.DockManager;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.notification.interruption.NotificationInterruptStateProviderWrapper;
import com.android.systemui.statusbar.notification.interruption.NotificationInterruptSuppressor;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionCondition;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProvider;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionType;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.google.android.systemui.dreamliner.WirelessCharger;
import dagger.Lazy;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class DockObserver implements DockManager {
    static final String ACTION_ALIGN_STATE_CHANGE = "com.google.android.systemui.dreamliner.ALIGNMENT_CHANGE";
    static final String ACTION_DOCK_UI_ACTIVE = "com.google.android.systemui.dreamliner.ACTION_DOCK_UI_ACTIVE";
    static final String ACTION_DOCK_UI_IDLE = "com.google.android.systemui.dreamliner.ACTION_DOCK_UI_IDLE";
    static final String ACTION_REBIND_DOCK_SERVICE = "com.google.android.systemui.dreamliner.ACTION_REBIND_DOCK_SERVICE";
    static final String ACTION_START_DREAMLINER_CONTROL_SERVICE = "com.google.android.apps.dreamliner.START";
    static final String COMPONENTNAME_DREAMLINER_CONTROL_SERVICE = "com.google.android.apps.dreamliner/.DreamlinerControlService";
    static final String EXTRA_ALIGN_STATE = "align_state";
    static final String KEY_SHOWING = "showing";
    static final String PERMISSION_WIRELESS_CHARGER_STATUS = "com.google.android.systemui.permission.WIRELESS_CHARGER_STATUS";
    static final int RESULT_NOT_FOUND = -1;
    static final int RESULT_OK = 0;
    public static boolean sIsDockingUiShowing = false;
    public final List mAlignmentStateListeners;
    public final List mClients;
    public final ConfigurationController mConfigurationController;
    public final Context mContext;
    public final DockAlignmentController mDockAlignmentController;
    DockGestureController mDockGestureController;
    public final DockObserverBroadcastReceiver mDockObserverBroadcastReceiver;
    int mDockState;
    public ImageView mDreamlinerGear;
    final DreamlinerBroadcastReceiver mDreamlinerReceiver = new DreamlinerBroadcastReceiver();
    DreamlinerServiceConn mDreamlinerServiceConn;
    public DockIndicationController mIndicationController;
    public final AnonymousClass2 mInterruptSuppressor;
    public boolean mIsWirelessCharging = false;
    public final KeyguardStateController mKeyguardStateController;
    int mLastAlignState;
    public final DelayableExecutor mMainExecutor;
    public DockObserver$$ExternalSyntheticLambda1 mPhotoAction;
    public FrameLayout mPhotoPreview;
    ProtectedBroadcastSender mProtectedBroadcastSender;
    public final StatusBarStateController mStatusBarStateController;
    public final UserTracker.Callback mUserChangedCallback;
    public final UserTracker mUserTracker;
    public final AnonymousClass3 mVisualInterruptionCondition;
    public final Lazy mVisualInterruptionDecisionProviderLazy;
    public final Optional mWirelessCharger;
    public final WirelessChargerCommander mWirelessChargerCommander;

    /* renamed from: com.google.android.systemui.dreamliner.DockObserver$2  reason: invalid class name */
    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class AnonymousClass2 implements NotificationInterruptSuppressor {
        public final String getName() {
            return "DLObserver";
        }

        public final boolean suppressInterruptions() {
            return DockObserver.sIsDockingUiShowing;
        }
    }

    /* renamed from: com.google.android.systemui.dreamliner.DockObserver$3  reason: invalid class name */
    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class AnonymousClass3 extends VisualInterruptionCondition {
    }

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class DockObserverBroadcastReceiver extends BroadcastReceiver {
        public DockObserverBroadcastReceiver() {
        }

        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void onReceive(android.content.Context r8, android.content.Intent r9) {
            /*
                r7 = this;
                if (r9 != 0) goto L_0x0003
                return
            L_0x0003:
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                java.lang.String r1 = "onReceive(); "
                r0.<init>(r1)
                java.lang.String r1 = r9.getAction()
                r0.append(r1)
                java.lang.String r0 = r0.toString()
                java.lang.String r1 = "DLObserver"
                android.util.Log.d(r1, r0)
                java.lang.String r0 = r9.getAction()
                int r2 = r0.hashCode()
                r3 = 1
                r4 = 0
                r5 = 3
                r6 = 2
                switch(r2) {
                    case -1886648615: goto L_0x0048;
                    case -1538406691: goto L_0x003e;
                    case 798292259: goto L_0x0034;
                    case 1318602046: goto L_0x002a;
                    default: goto L_0x0029;
                }
            L_0x0029:
                goto L_0x0052
            L_0x002a:
                java.lang.String r2 = "com.google.android.systemui.dreamliner.ACTION_REBIND_DOCK_SERVICE"
                boolean r0 = r0.equals(r2)
                if (r0 == 0) goto L_0x0052
                r0 = r6
                goto L_0x0053
            L_0x0034:
                java.lang.String r2 = "android.intent.action.BOOT_COMPLETED"
                boolean r0 = r0.equals(r2)
                if (r0 == 0) goto L_0x0052
                r0 = r5
                goto L_0x0053
            L_0x003e:
                java.lang.String r2 = "android.intent.action.BATTERY_CHANGED"
                boolean r0 = r0.equals(r2)
                if (r0 == 0) goto L_0x0052
                r0 = r4
                goto L_0x0053
            L_0x0048:
                java.lang.String r2 = "android.intent.action.ACTION_POWER_DISCONNECTED"
                boolean r0 = r0.equals(r2)
                if (r0 == 0) goto L_0x0052
                r0 = r3
                goto L_0x0053
            L_0x0052:
                r0 = -1
            L_0x0053:
                if (r0 == 0) goto L_0x006a
                if (r0 == r3) goto L_0x0062
                if (r0 == r6) goto L_0x005c
                if (r0 == r5) goto L_0x005c
                goto L_0x008f
            L_0x005c:
                com.google.android.systemui.dreamliner.DockObserver r7 = com.google.android.systemui.dreamliner.DockObserver.this
                r7.updateCurrentDockingStatus(r8)
                goto L_0x008f
            L_0x0062:
                com.google.android.systemui.dreamliner.DockObserver r7 = com.google.android.systemui.dreamliner.DockObserver.this
                com.google.android.systemui.dreamliner.DockObserver.m1748$$Nest$mstopDreamlinerService(r7, r8)
                com.google.android.systemui.dreamliner.DockObserver.sIsDockingUiShowing = r4
                goto L_0x008f
            L_0x006a:
                com.google.android.systemui.dreamliner.DockObserver r0 = com.google.android.systemui.dreamliner.DockObserver.this
                boolean r2 = r0.mIsWirelessCharging
                boolean r9 = com.google.android.systemui.dreamliner.DockObserver.isWirelessCharging(r8, r9)
                r0.mIsWirelessCharging = r9
                com.google.android.systemui.dreamliner.DockObserver r9 = com.google.android.systemui.dreamliner.DockObserver.this
                boolean r9 = r9.mIsWirelessCharging
                if (r2 == r9) goto L_0x008f
                java.lang.StringBuilder r9 = new java.lang.StringBuilder
                java.lang.String r0 = "mWirelessCharging:"
                r9.<init>(r0)
                com.google.android.systemui.dreamliner.DockObserver r0 = com.google.android.systemui.dreamliner.DockObserver.this
                boolean r0 = r0.mIsWirelessCharging
                com.android.settingslib.bluetooth.CachedBluetoothDevice$$ExternalSyntheticOutline0.m(r9, r0, r1)
                com.google.android.systemui.dreamliner.DockObserver r7 = com.google.android.systemui.dreamliner.DockObserver.this
                boolean r9 = r7.mIsWirelessCharging
                r7.tryToStartDreamlinerServiceIfNeeded(r8, r9)
            L_0x008f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.dreamliner.DockObserver.DockObserverBroadcastReceiver.onReceive(android.content.Context, android.content.Intent):void");
        }
    }

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    class DreamlinerBroadcastReceiver extends BroadcastReceiver {
        public boolean mListening;

        public DreamlinerBroadcastReceiver() {
        }

        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void onReceive(android.content.Context r8, android.content.Intent r9) {
            /*
                r7 = this;
                if (r9 != 0) goto L_0x0003
                return
            L_0x0003:
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                java.lang.String r1 = "Dock Receiver.onReceive(): "
                r0.<init>(r1)
                java.lang.String r1 = r9.getAction()
                r0.append(r1)
                java.lang.String r0 = r0.toString()
                java.lang.String r1 = "DLObserver"
                android.util.Log.d(r1, r0)
                java.lang.String r0 = r9.getAction()
                int r2 = r0.hashCode()
                r3 = 4
                r4 = 2
                r5 = 1
                r6 = 0
                switch(r2) {
                    case -1584152500: goto L_0x0087;
                    case -1579804275: goto L_0x007d;
                    case -545730616: goto L_0x0073;
                    case -484477188: goto L_0x0069;
                    case -390730981: goto L_0x005f;
                    case 664552276: goto L_0x0055;
                    case 675144007: goto L_0x004b;
                    case 675346819: goto L_0x0040;
                    case 717413661: goto L_0x0036;
                    case 1996802687: goto L_0x002b;
                    default: goto L_0x0029;
                }
            L_0x0029:
                goto L_0x0092
            L_0x002b:
                java.lang.String r2 = "com.google.android.systemui.dreamliner.ACTION_DOCK_UI_ACTIVE"
                boolean r0 = r0.equals(r2)
                if (r0 == 0) goto L_0x0092
                r0 = r5
                goto L_0x0093
            L_0x0036:
                java.lang.String r2 = "com.google.android.systemui.dreamliner.assistant_poodle"
                boolean r0 = r0.equals(r2)
                if (r0 == 0) goto L_0x0092
                r0 = 7
                goto L_0x0093
            L_0x0040:
                java.lang.String r2 = "com.google.android.systemui.dreamliner.photo"
                boolean r0 = r0.equals(r2)
                if (r0 == 0) goto L_0x0092
                r0 = 8
                goto L_0x0093
            L_0x004b:
                java.lang.String r2 = "com.google.android.systemui.dreamliner.pause"
                boolean r0 = r0.equals(r2)
                if (r0 == 0) goto L_0x0092
                r0 = 5
                goto L_0x0093
            L_0x0055:
                java.lang.String r2 = "com.google.android.systemui.dreamliner.dream"
                boolean r0 = r0.equals(r2)
                if (r0 == 0) goto L_0x0092
                r0 = r4
                goto L_0x0093
            L_0x005f:
                java.lang.String r2 = "com.google.android.systemui.dreamliner.undock"
                boolean r0 = r0.equals(r2)
                if (r0 == 0) goto L_0x0092
                r0 = 6
                goto L_0x0093
            L_0x0069:
                java.lang.String r2 = "com.google.android.systemui.dreamliner.resume"
                boolean r0 = r0.equals(r2)
                if (r0 == 0) goto L_0x0092
                r0 = r3
                goto L_0x0093
            L_0x0073:
                java.lang.String r2 = "com.google.android.systemui.dreamliner.paired"
                boolean r0 = r0.equals(r2)
                if (r0 == 0) goto L_0x0092
                r0 = 3
                goto L_0x0093
            L_0x007d:
                java.lang.String r2 = "com.google.android.systemui.dreamliner.ACTION_DOCK_UI_IDLE"
                boolean r0 = r0.equals(r2)
                if (r0 == 0) goto L_0x0092
                r0 = r6
                goto L_0x0093
            L_0x0087:
                java.lang.String r2 = "com.google.android.systemui.dreamliner.photo_error"
                boolean r0 = r0.equals(r2)
                if (r0 == 0) goto L_0x0092
                r0 = 9
                goto L_0x0093
            L_0x0092:
                r0 = -1
            L_0x0093:
                r2 = 1073741824(0x40000000, float:2.0)
                switch(r0) {
                    case 0: goto L_0x01ad;
                    case 1: goto L_0x0190;
                    case 2: goto L_0x0175;
                    case 3: goto L_0x0129;
                    case 4: goto L_0x0143;
                    case 5: goto L_0x010f;
                    case 6: goto L_0x00f5;
                    case 7: goto L_0x00e2;
                    case 8: goto L_0x00ad;
                    case 9: goto L_0x009a;
                    default: goto L_0x0098;
                }
            L_0x0098:
                goto L_0x01c9
            L_0x009a:
                com.google.android.systemui.dreamliner.DockObserver r7 = com.google.android.systemui.dreamliner.DockObserver.this
                r7.getClass()
                java.lang.String r8 = "Fail to launch photo"
                android.util.Log.w(r1, r8)
                com.google.android.systemui.dreamliner.DockGestureController r7 = r7.mDockGestureController
                if (r7 == 0) goto L_0x01c9
                r7.hidePhotoPreview(r6)
                goto L_0x01c9
            L_0x00ad:
                com.google.android.systemui.dreamliner.DockObserver r8 = com.google.android.systemui.dreamliner.DockObserver.this
                r8.getClass()
                java.lang.String r0 = "android.intent.extra.RESULT_RECEIVER"
                android.os.Parcelable r0 = r9.getParcelableExtra(r0)
                android.os.ResultReceiver r0 = (android.os.ResultReceiver) r0
                java.lang.String r2 = "enabled"
                boolean r9 = r9.getBooleanExtra(r2, r6)
                java.lang.String r2 = "configPhotoAction, enabled="
                com.android.settingslib.Utils$$ExternalSyntheticOutline0.m(r2, r1, r9)
                com.google.android.systemui.dreamliner.DockGestureController r1 = r8.mDockGestureController
                if (r1 == 0) goto L_0x00cb
                r1.mPhotoEnabled = r9
            L_0x00cb:
                if (r0 == 0) goto L_0x00d8
                com.google.android.systemui.dreamliner.DockIndicationController r9 = r8.mIndicationController
                if (r9 == 0) goto L_0x00d8
                com.google.android.systemui.dreamliner.DockObserver$$ExternalSyntheticLambda1 r9 = new com.google.android.systemui.dreamliner.DockObserver$$ExternalSyntheticLambda1
                r1 = 2
                r9.<init>(r8, r0, r1)
                goto L_0x00d9
            L_0x00d8:
                r9 = 0
            L_0x00d9:
                r8.mPhotoAction = r9
                com.google.android.systemui.dreamliner.DockObserver r7 = com.google.android.systemui.dreamliner.DockObserver.this
                r7.runPhotoAction()
                goto L_0x01c9
            L_0x00e2:
                com.google.android.systemui.dreamliner.DockObserver r7 = com.google.android.systemui.dreamliner.DockObserver.this
                com.google.android.systemui.dreamliner.DockIndicationController r7 = r7.mIndicationController
                if (r7 == 0) goto L_0x01c9
                java.lang.String r8 = "showing"
                boolean r8 = r9.getBooleanExtra(r8, r6)
                r7.mTopIconShowing = r8
                r7.updateVisibility$8()
                goto L_0x01c9
            L_0x00f5:
                com.google.android.systemui.dreamliner.DockObserver r8 = com.google.android.systemui.dreamliner.DockObserver.this
                r8.onDockStateChanged(r6)
                com.google.android.systemui.dreamliner.DockObserver r8 = com.google.android.systemui.dreamliner.DockObserver.this
                com.google.android.systemui.dreamliner.DockGestureController r8 = r8.mDockGestureController
                java.lang.Class<com.google.android.systemui.dreamliner.DockGestureController> r9 = com.google.android.systemui.dreamliner.DockGestureController.class
                boolean r8 = com.google.android.systemui.dreamliner.DockObserver.assertNotNull(r8)
                if (r8 == 0) goto L_0x01c9
                com.google.android.systemui.dreamliner.DockObserver r7 = com.google.android.systemui.dreamliner.DockObserver.this
                com.google.android.systemui.dreamliner.DockGestureController r7 = r7.mDockGestureController
                r7.stopMonitoring()
                goto L_0x01c9
            L_0x010f:
                com.google.android.systemui.dreamliner.DockObserver r8 = com.google.android.systemui.dreamliner.DockObserver.this
                r8.onDockStateChanged(r4)
                com.google.android.systemui.dreamliner.DockObserver r8 = com.google.android.systemui.dreamliner.DockObserver.this
                com.google.android.systemui.dreamliner.DockGestureController r8 = r8.mDockGestureController
                java.lang.Class<com.google.android.systemui.dreamliner.DockGestureController> r9 = com.google.android.systemui.dreamliner.DockGestureController.class
                boolean r8 = com.google.android.systemui.dreamliner.DockObserver.assertNotNull(r8)
                if (r8 == 0) goto L_0x01c9
                com.google.android.systemui.dreamliner.DockObserver r7 = com.google.android.systemui.dreamliner.DockObserver.this
                com.google.android.systemui.dreamliner.DockGestureController r7 = r7.mDockGestureController
                r7.stopMonitoring()
                goto L_0x01c9
            L_0x0129:
                com.google.android.systemui.dreamliner.DockObserver r8 = com.google.android.systemui.dreamliner.DockObserver.this
                com.google.android.systemui.dreamliner.DockGestureController r8 = r8.mDockGestureController
                java.lang.Class<com.google.android.systemui.dreamliner.DockGestureController> r0 = com.google.android.systemui.dreamliner.DockGestureController.class
                boolean r8 = com.google.android.systemui.dreamliner.DockObserver.assertNotNull(r8)
                if (r8 == 0) goto L_0x0143
                com.google.android.systemui.dreamliner.DockObserver r8 = com.google.android.systemui.dreamliner.DockObserver.this
                com.google.android.systemui.dreamliner.DockGestureController r8 = r8.mDockGestureController
                java.lang.String r0 = "single_tap_action"
                android.os.Parcelable r9 = r9.getParcelableExtra(r0)
                android.app.PendingIntent r9 = (android.app.PendingIntent) r9
                r8.mTapAction = r9
            L_0x0143:
                com.google.android.systemui.dreamliner.DockObserver r8 = com.google.android.systemui.dreamliner.DockObserver.this
                r8.onDockStateChanged(r5)
                com.google.android.systemui.dreamliner.DockObserver r8 = com.google.android.systemui.dreamliner.DockObserver.this
                com.google.android.systemui.dreamliner.DockGestureController r8 = r8.mDockGestureController
                java.lang.Class<com.google.android.systemui.dreamliner.DockGestureController> r9 = com.google.android.systemui.dreamliner.DockGestureController.class
                boolean r8 = com.google.android.systemui.dreamliner.DockObserver.assertNotNull(r8)
                if (r8 == 0) goto L_0x01c9
                com.google.android.systemui.dreamliner.DockObserver r7 = com.google.android.systemui.dreamliner.DockObserver.this
                com.google.android.systemui.dreamliner.DockGestureController r7 = r7.mDockGestureController
                android.widget.ImageView r8 = r7.mSettingsGear
                r8.setVisibility(r3)
                com.android.systemui.plugins.statusbar.StatusBarStateController r8 = r7.mStatusBarStateController
                boolean r8 = r8.isDozing()
                r7.onDozingChanged(r8)
                com.android.systemui.plugins.statusbar.StatusBarStateController r8 = r7.mStatusBarStateController
                r8.addCallback(r7)
                com.android.systemui.statusbar.policy.KeyguardStateController r8 = r7.mKeyguardStateController
                com.google.android.systemui.dreamliner.DockGestureController$1 r7 = r7.mKeyguardMonitorCallback
                com.android.systemui.statusbar.policy.KeyguardStateControllerImpl r8 = (com.android.systemui.statusbar.policy.KeyguardStateControllerImpl) r8
                r8.addCallback(r7)
                goto L_0x01c9
            L_0x0175:
                com.google.android.systemui.dreamliner.DockObserver r7 = com.google.android.systemui.dreamliner.DockObserver.this
                r7.getClass()
                java.lang.Class<android.os.PowerManager> r7 = android.os.PowerManager.class
                java.lang.Object r7 = r8.getSystemService(r7)
                android.os.PowerManager r7 = (android.os.PowerManager) r7
                boolean r8 = r7.isScreenOn()
                if (r8 == 0) goto L_0x01c9
                long r8 = android.os.SystemClock.uptimeMillis()
                r7.goToSleep(r8)
                goto L_0x01c9
            L_0x0190:
                com.google.android.systemui.dreamliner.DockObserver r7 = com.google.android.systemui.dreamliner.DockObserver.this
                r7.getClass()
                java.lang.String r8 = "sendDockActiveIntent()"
                android.util.Log.d(r1, r8)
                com.google.android.systemui.dreamliner.DockObserver$ProtectedBroadcastSender r7 = r7.mProtectedBroadcastSender
                android.content.Intent r8 = new android.content.Intent
                java.lang.String r9 = "android.intent.action.DOCK_ACTIVE"
                r8.<init>(r9)
                android.content.Intent r8 = r8.addFlags(r2)
                r7.sendBroadcast(r8)
                com.google.android.systemui.dreamliner.DockObserver.sIsDockingUiShowing = r6
                goto L_0x01c9
            L_0x01ad:
                com.google.android.systemui.dreamliner.DockObserver r7 = com.google.android.systemui.dreamliner.DockObserver.this
                r7.getClass()
                java.lang.String r8 = "sendDockIdleIntent()"
                android.util.Log.d(r1, r8)
                com.google.android.systemui.dreamliner.DockObserver$ProtectedBroadcastSender r7 = r7.mProtectedBroadcastSender
                android.content.Intent r8 = new android.content.Intent
                java.lang.String r9 = "android.intent.action.DOCK_IDLE"
                r8.<init>(r9)
                android.content.Intent r8 = r8.addFlags(r2)
                r7.sendBroadcast(r8)
                com.google.android.systemui.dreamliner.DockObserver.sIsDockingUiShowing = r5
            L_0x01c9:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.dreamliner.DockObserver.DreamlinerBroadcastReceiver.onReceive(android.content.Context, android.content.Intent):void");
        }

        public final void registerReceiver(Context context) {
            if (!this.mListening) {
                UserHandle userHandle = UserHandle.ALL;
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(DockObserver.ACTION_DOCK_UI_IDLE);
                intentFilter.addAction(DockObserver.ACTION_DOCK_UI_ACTIVE);
                intentFilter.addAction("com.google.android.systemui.dreamliner.dream");
                intentFilter.addAction("com.google.android.systemui.dreamliner.paired");
                intentFilter.addAction("com.google.android.systemui.dreamliner.pause");
                intentFilter.addAction("com.google.android.systemui.dreamliner.resume");
                intentFilter.addAction("com.google.android.systemui.dreamliner.undock");
                intentFilter.addAction("com.google.android.systemui.dreamliner.assistant_poodle");
                intentFilter.addAction("com.google.android.systemui.dreamliner.photo");
                intentFilter.addAction("com.google.android.systemui.dreamliner.photo_error");
                context.registerReceiverAsUser(this, userHandle, intentFilter, DockObserver.PERMISSION_WIRELESS_CHARGER_STATUS, (Handler) null, 2);
                this.mListening = true;
            }
        }
    }

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    final class IsDockPresentCallback implements WirelessCharger.IsDockPresentCallback {
        public final Context mContext;

        public IsDockPresentCallback(Context context) {
            this.mContext = context;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void onCallback(boolean r5, byte r6, byte r7, boolean r8, int r9) {
            /*
                r4 = this;
                if (r5 == 0) goto L_0x00a4
                com.google.android.systemui.dreamliner.DockObserver r5 = com.google.android.systemui.dreamliner.DockObserver.this
                android.content.Context r4 = r4.mContext
                java.lang.String r0 = "Unable to bind Dreamliner service: "
                monitor-enter(r5)
                com.google.android.systemui.dreamliner.DockObserver$DreamlinerServiceConn r1 = r5.mDreamlinerServiceConn     // Catch:{ all -> 0x0080 }
                if (r1 == 0) goto L_0x0010
                monitor-exit(r5)
                goto L_0x00a4
            L_0x0010:
                r5.addInterruptionSuppressor()     // Catch:{ all -> 0x0080 }
                r1 = 1
                com.google.android.systemui.dreamliner.DockObserver.notifyForceEnabledAmbientDisplay(r1)     // Catch:{ all -> 0x0080 }
                com.google.android.systemui.dreamliner.DockObserver$DreamlinerBroadcastReceiver r2 = r5.mDreamlinerReceiver     // Catch:{ all -> 0x0080 }
                r2.registerReceiver(r4)     // Catch:{ all -> 0x0080 }
                android.content.Intent r2 = new android.content.Intent     // Catch:{ all -> 0x0080 }
                java.lang.String r3 = "com.google.android.apps.dreamliner.START"
                r2.<init>(r3)     // Catch:{ all -> 0x0080 }
                java.lang.String r3 = "com.google.android.apps.dreamliner/.DreamlinerControlService"
                android.content.ComponentName r3 = android.content.ComponentName.unflattenFromString(r3)     // Catch:{ all -> 0x0080 }
                android.content.Intent r2 = r2.setComponent(r3)     // Catch:{ all -> 0x0080 }
                java.lang.String r3 = "type"
                android.content.Intent r6 = r2.putExtra(r3, r6)     // Catch:{ all -> 0x0080 }
                java.lang.String r2 = "orientation"
                android.content.Intent r6 = r6.putExtra(r2, r7)     // Catch:{ all -> 0x0080 }
                java.lang.String r7 = "is_get_info_supported"
                android.content.Intent r6 = r6.putExtra(r7, r8)     // Catch:{ all -> 0x0080 }
                java.lang.String r7 = "id"
                android.content.Intent r6 = r6.putExtra(r7, r9)     // Catch:{ all -> 0x0080 }
                java.lang.String r7 = "manufacturer_code"
                r8 = 0
                android.content.Intent r6 = r6.putExtra(r7, r8)     // Catch:{ all -> 0x0080 }
                java.lang.String r7 = "occluded"
                com.android.systemui.statusbar.policy.KeyguardStateController r8 = r5.mKeyguardStateController     // Catch:{ all -> 0x0080 }
                com.android.systemui.statusbar.policy.KeyguardStateControllerImpl r8 = (com.android.systemui.statusbar.policy.KeyguardStateControllerImpl) r8     // Catch:{ all -> 0x0080 }
                boolean r8 = r8.mOccluded     // Catch:{ all -> 0x0080 }
                android.content.Intent r6 = r6.putExtra(r7, r8)     // Catch:{ all -> 0x0080 }
                com.google.android.systemui.dreamliner.DockObserver$DreamlinerServiceConn r7 = new com.google.android.systemui.dreamliner.DockObserver$DreamlinerServiceConn     // Catch:{ all -> 0x0080 }
                r7.<init>(r4)     // Catch:{ all -> 0x0080 }
                r5.mDreamlinerServiceConn = r7     // Catch:{ all -> 0x0080 }
                android.os.UserHandle r8 = new android.os.UserHandle     // Catch:{ SecurityException -> 0x0082 }
                com.android.systemui.settings.UserTracker r9 = r5.mUserTracker     // Catch:{ SecurityException -> 0x0082 }
                com.android.systemui.settings.UserTrackerImpl r9 = (com.android.systemui.settings.UserTrackerImpl) r9     // Catch:{ SecurityException -> 0x0082 }
                int r9 = r9.getUserId()     // Catch:{ SecurityException -> 0x0082 }
                r8.<init>(r9)     // Catch:{ SecurityException -> 0x0082 }
                boolean r7 = r4.bindServiceAsUser(r6, r7, r1, r8)     // Catch:{ SecurityException -> 0x0082 }
                if (r7 == 0) goto L_0x008c
                com.google.android.systemui.dreamliner.DockObserver$$ExternalSyntheticLambda1 r6 = new com.google.android.systemui.dreamliner.DockObserver$$ExternalSyntheticLambda1     // Catch:{ all -> 0x0080 }
                r7 = 1
                r6.<init>(r5, r4, r7)     // Catch:{ all -> 0x0080 }
                com.android.systemui.util.concurrency.DelayableExecutor r4 = r5.mMainExecutor     // Catch:{ all -> 0x0080 }
                com.android.systemui.util.concurrency.ExecutorImpl r4 = (com.android.systemui.util.concurrency.ExecutorImpl) r4     // Catch:{ all -> 0x0080 }
                r4.execute(r6)     // Catch:{ all -> 0x0080 }
                goto L_0x00a0
            L_0x0080:
                r4 = move-exception
                goto L_0x00a2
            L_0x0082:
                r4 = move-exception
                java.lang.String r7 = "DLObserver"
                java.lang.String r8 = r4.getMessage()     // Catch:{ all -> 0x0080 }
                android.util.Log.e(r7, r8, r4)     // Catch:{ all -> 0x0080 }
            L_0x008c:
                r4 = 0
                r5.mDreamlinerServiceConn = r4     // Catch:{ all -> 0x0080 }
                java.lang.String r4 = "DLObserver"
                java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x0080 }
                r7.<init>(r0)     // Catch:{ all -> 0x0080 }
                r7.append(r6)     // Catch:{ all -> 0x0080 }
                java.lang.String r6 = r7.toString()     // Catch:{ all -> 0x0080 }
                android.util.Log.w(r4, r6)     // Catch:{ all -> 0x0080 }
            L_0x00a0:
                monitor-exit(r5)
                goto L_0x00a4
            L_0x00a2:
                monitor-exit(r5)
                throw r4
            L_0x00a4:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.dreamliner.DockObserver.IsDockPresentCallback.onCallback(boolean, byte, byte, boolean, int):void");
        }
    }

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class ProtectedBroadcastSender {
        public final Context mContext;

        public ProtectedBroadcastSender(Context context) {
            this.mContext = context.getApplicationContext();
        }

        public final void sendBroadcast(Intent intent) {
            Context context = this.mContext;
            if (context.getUser().isSystem()) {
                try {
                    context.sendBroadcast(intent);
                } catch (SecurityException e) {
                    Log.w("DLObserver", "Send protected broadcast failed. intent= " + intent, e);
                }
            }
        }

        public final void sendBroadcastAsUser(Intent intent, UserHandle userHandle) {
            Context context = this.mContext;
            if (context.getUser().isSystem()) {
                try {
                    context.sendBroadcastAsUser(intent, userHandle);
                } catch (SecurityException e) {
                    Log.w("DLObserver", "Send protected broadcast failed. intent= " + intent, e);
                }
            }
        }
    }

    /* renamed from: -$$Nest$mstopDreamlinerService  reason: not valid java name */
    public static void m1748$$Nest$mstopDreamlinerService(DockObserver dockObserver, Context context) {
        dockObserver.getClass();
        notifyForceEnabledAmbientDisplay(false);
        dockObserver.onDockStateChanged(0);
        dockObserver.removeInterruptionSuppressor();
        try {
            if (dockObserver.mDreamlinerServiceConn != null) {
                Class<DockGestureController> cls = DockGestureController.class;
                if (assertNotNull(dockObserver.mDockGestureController)) {
                    ((ConfigurationControllerImpl) dockObserver.mConfigurationController).removeCallback(dockObserver.mDockGestureController);
                    dockObserver.mDockGestureController.stopMonitoring();
                    dockObserver.mDockGestureController = null;
                }
                ((UserTrackerImpl) dockObserver.mUserTracker).removeCallback(dockObserver.mUserChangedCallback);
                DreamlinerBroadcastReceiver dreamlinerBroadcastReceiver = dockObserver.mDreamlinerReceiver;
                if (dreamlinerBroadcastReceiver.mListening) {
                    context.unregisterReceiver(dreamlinerBroadcastReceiver);
                    dreamlinerBroadcastReceiver.mListening = false;
                }
                context.unbindService(dockObserver.mDreamlinerServiceConn);
                dockObserver.mDreamlinerServiceConn = null;
            }
        } catch (IllegalArgumentException e) {
            Log.e("DLObserver", e.getMessage(), e);
        }
    }

    /* JADX WARNING: type inference failed for: r2v3, types: [java.lang.Object, com.google.android.systemui.dreamliner.DockObserver$2] */
    public DockObserver(Context context, Optional optional, WirelessChargerCommander wirelessChargerCommander, StatusBarStateController statusBarStateController, Lazy lazy, ConfigurationController configurationController, DelayableExecutor delayableExecutor, KeyguardStateController keyguardStateController, DockAlignmentController dockAlignmentController, UserTracker userTracker) {
        WirelessChargerCommander wirelessChargerCommander2 = wirelessChargerCommander;
        DockObserverBroadcastReceiver dockObserverBroadcastReceiver = new DockObserverBroadcastReceiver();
        this.mDockObserverBroadcastReceiver = dockObserverBroadcastReceiver;
        this.mDockState = 0;
        this.mLastAlignState = -1;
        this.mUserChangedCallback = new UserTracker.Callback() {
            public final void onUserChanged(int i, Context context) {
                DockObserver dockObserver = DockObserver.this;
                DockObserver.m1748$$Nest$mstopDreamlinerService(dockObserver, dockObserver.mContext);
                dockObserver.updateCurrentDockingStatus(dockObserver.mContext);
            }
        };
        this.mInterruptSuppressor = new Object();
        new VisualInterruptionCondition("docking UI showing", Set.of(VisualInterruptionType.PEEK, VisualInterruptionType.PULSE, VisualInterruptionType.BUBBLE));
        this.mVisualInterruptionDecisionProviderLazy = lazy;
        this.mMainExecutor = delayableExecutor;
        this.mContext = context;
        this.mKeyguardStateController = keyguardStateController;
        this.mClients = new ArrayList();
        this.mAlignmentStateListeners = new ArrayList();
        this.mUserTracker = userTracker;
        this.mProtectedBroadcastSender = new ProtectedBroadcastSender(context);
        this.mWirelessCharger = optional;
        if (optional.isEmpty()) {
            Log.i("DLObserver", "wireless charger is not present, check dock component.");
        }
        this.mWirelessChargerCommander = wirelessChargerCommander2;
        UserManager userManager = (UserManager) context.getSystemService(UserManager.class);
        if (userManager != null && userManager.isSystemUser()) {
            wirelessChargerCommander2.wirelessChargerFanLevelChangedCallback.add(new DockObserver$$ExternalSyntheticLambda2(this));
            WirelessChargerCommander.asyncIfPresent(wirelessChargerCommander2.wirelessCharger, new WirelessChargerCommander$registerFanLevelChangedCallback$1(wirelessChargerCommander));
            UserHandle userHandle = UserHandle.ALL;
            IntentFilter intentFilter = wirelessChargerCommander2.commandIntents;
            context.registerReceiverAsUser(wirelessChargerCommander2.commandReceiver, userHandle, intentFilter, PERMISSION_WIRELESS_CHARGER_STATUS, (Handler) null, 2);
        }
        this.mStatusBarStateController = statusBarStateController;
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("android.intent.action.BATTERY_CHANGED");
        intentFilter2.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        intentFilter2.addAction("android.intent.action.BOOT_COMPLETED");
        intentFilter2.addAction(ACTION_REBIND_DOCK_SERVICE);
        intentFilter2.setPriority(1000);
        context.registerReceiver(dockObserverBroadcastReceiver, intentFilter2, PERMISSION_WIRELESS_CHARGER_STATUS, (Handler) null, 2);
        this.mDockAlignmentController = dockAlignmentController;
        this.mConfigurationController = configurationController;
    }

    public static boolean assertNotNull(DockGestureController dockGestureController) {
        if (dockGestureController != null) {
            return true;
        }
        Log.w("DLObserver", "DockGestureController is null");
        return false;
    }

    public static boolean isWirelessCharging(Context context, Intent intent) {
        if (intent == null) {
            intent = context.registerReceiver((BroadcastReceiver) null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        }
        if (intent == null || !"android.intent.action.BATTERY_CHANGED".equals(intent.getAction())) {
            Log.i("DLObserver", "invalid battery intent when checking plugged status. batteryIntent=" + intent);
            return false;
        }
        int intExtra = intent.getIntExtra("plugged", -1);
        ExifInterface$$ExternalSyntheticOutline0.m("plugged=", "DLObserver", intExtra);
        if (intExtra == 4) {
            return true;
        }
        return false;
    }

    public static void notifyForceEnabledAmbientDisplay(boolean z) {
        IDreamManager asInterface = IDreamManager.Stub.asInterface(ServiceManager.checkService("dreams"));
        if (asInterface != null) {
            try {
                asInterface.forceAmbientDisplayEnabled(z);
            } catch (RemoteException unused) {
            }
        } else {
            Log.e("DLObserver", "DreamManager not found");
        }
    }

    public void addInterruptionSuppressor() {
        ((NotificationInterruptStateProviderWrapper) ((VisualInterruptionDecisionProvider) this.mVisualInterruptionDecisionProviderLazy.get())).addLegacySuppressor(this.mInterruptSuppressor);
    }

    public final void addListener(DockManager.DockEventListener dockEventListener) {
        Log.d("DLObserver", "add listener: " + dockEventListener);
        List list = this.mClients;
        if (!list.contains(dockEventListener)) {
            list.add(dockEventListener);
        }
        ((ExecutorImpl) this.mMainExecutor).execute(new DockObserver$$ExternalSyntheticLambda1(this, dockEventListener, 0));
    }

    public BroadcastReceiver getDockObserverBroadcastReceiver() {
        return this.mDockObserverBroadcastReceiver;
    }

    public final boolean isDocked() {
        int i = this.mDockState;
        if (i == 1 || i == 2) {
            return true;
        }
        return false;
    }

    public final boolean isHidden() {
        if (this.mDockState == 2) {
            return true;
        }
        return false;
    }

    public final void notifyDreamlinerAlignStateChanged(int i) {
        Log.d("DLObserver", "sendBroadcastAsUser for alignment changed, alignState: " + i);
        if (isDocked()) {
            this.mProtectedBroadcastSender.sendBroadcastAsUser(new Intent(ACTION_ALIGN_STATE_CHANGE).putExtra(EXTRA_ALIGN_STATE, i).addFlags(1073741824), UserHandle.ALL);
        }
    }

    public final void onDockStateChanged(int i) {
        if (this.mDockState != i) {
            Log.d("DLObserver", "dock state changed from " + this.mDockState + " to " + i);
            int i2 = this.mDockState;
            this.mDockState = i;
            int i3 = 0;
            while (true) {
                ArrayList arrayList = (ArrayList) this.mClients;
                if (i3 >= arrayList.size()) {
                    break;
                }
                LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0.m(new StringBuilder("onDockEvent mDockState = "), this.mDockState, "DLObserver");
                ((DockManager.DockEventListener) arrayList.get(i3)).onEvent(this.mDockState);
                i3++;
            }
            DockIndicationController dockIndicationController = this.mIndicationController;
            if (dockIndicationController != null) {
                boolean isDocked = isDocked();
                dockIndicationController.mDocking = isDocked;
                if (!isDocked) {
                    dockIndicationController.mTopIconShowing = false;
                    dockIndicationController.mShowPromo = false;
                }
                dockIndicationController.updateVisibility$8();
                dockIndicationController.updateLiveRegionIfNeeded();
            }
            if (i2 == 0 && i == 1) {
                notifyDreamlinerAlignStateChanged(this.mLastAlignState);
            }
        }
    }

    public void removeInterruptionSuppressor() {
        ((NotificationInterruptStateProviderWrapper) ((VisualInterruptionDecisionProvider) this.mVisualInterruptionDecisionProviderLazy.get())).removeLegacySuppressor(this.mInterruptSuppressor);
    }

    public final void removeListener(DockManager.DockEventListener dockEventListener) {
        Log.d("DLObserver", "remove listener: " + dockEventListener);
        this.mClients.remove(dockEventListener);
    }

    public final void runPhotoAction() {
        if (this.mLastAlignState == 0 && this.mPhotoAction != null && this.mIndicationController.mDockPromo.getVisibility() != 0) {
            DockObserver$$ExternalSyntheticLambda1 dockObserver$$ExternalSyntheticLambda1 = this.mPhotoAction;
            this.mMainExecutor.executeDelayed(Duration.ofSeconds(3).toMillis(), dockObserver$$ExternalSyntheticLambda1);
        }
    }

    public final void tryToStartDreamlinerServiceIfNeeded(Context context, boolean z) {
        UserManager userManager = (UserManager) context.getSystemService(UserManager.class);
        if (userManager.isManagedProfile() && !userManager.isSystemUser()) {
            Log.i("DLObserver", "do not bind Dreamliner service for work profile");
        } else if (z) {
            IsDockPresentCallback isDockPresentCallback = new IsDockPresentCallback(context);
            WirelessChargerCommander wirelessChargerCommander = this.mWirelessChargerCommander;
            wirelessChargerCommander.getClass();
            WirelessChargerCommander.asyncIfPresent(wirelessChargerCommander.wirelessCharger, new WirelessChargerCommander$getDockPresent$1(isDockPresentCallback));
        }
    }

    public final void updateCurrentDockingStatus(Context context) {
        notifyForceEnabledAmbientDisplay(false);
        tryToStartDreamlinerServiceIfNeeded(context, isWirelessCharging(context, (Intent) null));
    }

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    final class DreamlinerServiceConn implements ServiceConnection {
        public final Context mContext;

        public DreamlinerServiceConn(Context context) {
            this.mContext = context;
        }

        public final void onBindingDied(ComponentName componentName) {
            DockObserver.m1748$$Nest$mstopDreamlinerService(DockObserver.this, this.mContext);
            DockObserver.sIsDockingUiShowing = false;
            boolean isWirelessCharging = DockObserver.isWirelessCharging(this.mContext, (Intent) null);
            Utils$$ExternalSyntheticOutline0.m("onBindingDied, isWirelessCharging:", "DLObserver", isWirelessCharging);
            if (isWirelessCharging) {
                DockObserver.this.updateCurrentDockingStatus(this.mContext);
            }
        }

        public final void onServiceDisconnected(ComponentName componentName) {
            DockObserver dockObserver = DockObserver.this;
            dockObserver.getClass();
            Log.d("DLObserver", "sendDockActiveIntent()");
            dockObserver.mProtectedBroadcastSender.sendBroadcast(new Intent("android.intent.action.DOCK_ACTIVE").addFlags(1073741824));
        }

        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        }
    }
}
