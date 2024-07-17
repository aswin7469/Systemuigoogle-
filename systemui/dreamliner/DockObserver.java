package com.google.android.systemui.dreamliner;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.os.UserManager;
import android.service.dreams.IDreamManager;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0;
import com.android.settingslib.bluetooth.LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0;
import com.android.settingslib.mobile.MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import vendor.google.wireless_charger.IWirelessCharger;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class DockObserver implements DockManager {
    static final String ACTION_ALIGN_STATE_CHANGE = "com.google.android.systemui.dreamliner.ALIGNMENT_CHANGE";
    static final String ACTION_CHALLENGE = "com.google.android.systemui.dreamliner.ACTION_CHALLENGE";
    static final String ACTION_DOCK_UI_ACTIVE = "com.google.android.systemui.dreamliner.ACTION_DOCK_UI_ACTIVE";
    static final String ACTION_DOCK_UI_IDLE = "com.google.android.systemui.dreamliner.ACTION_DOCK_UI_IDLE";
    static final String ACTION_GET_DOCK_INFO = "com.google.android.systemui.dreamliner.ACTION_GET_DOCK_INFO";
    static final String ACTION_KEY_EXCHANGE = "com.google.android.systemui.dreamliner.ACTION_KEY_EXCHANGE";
    static final String ACTION_REBIND_DOCK_SERVICE = "com.google.android.systemui.dreamliner.ACTION_REBIND_DOCK_SERVICE";
    static final String ACTION_START_DREAMLINER_CONTROL_SERVICE = "com.google.android.apps.dreamliner.START";
    static final String COMPONENTNAME_DREAMLINER_CONTROL_SERVICE = "com.google.android.apps.dreamliner/.DreamlinerControlService";
    static final String EXTRA_ALIGN_STATE = "align_state";
    static final String EXTRA_CHALLENGE_DATA = "challenge_data";
    static final String EXTRA_CHALLENGE_DOCK_ID = "challenge_dock_id";
    static final String EXTRA_PUBLIC_KEY = "public_key";
    static final String KEY_SHOWING = "showing";
    static final String PERMISSION_WIRELESS_CHARGER_STATUS = "com.google.android.systemui.permission.WIRELESS_CHARGER_STATUS";
    static final int RESULT_NOT_FOUND = 1;
    static final int RESULT_OK = 0;
    public static boolean sIsDockingUiShowing = false;
    static volatile ExecutorService sSingleThreadExecutor;
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
    public int mFanLevel;
    public DockIndicationController mIndicationController;
    public final AnonymousClass2 mInterruptSuppressor;
    public boolean mIsWirelessCharging = false;
    public final KeyguardStateController mKeyguardStateController;
    int mLastAlignState;
    public final DelayableExecutor mMainExecutor;
    public DockObserver$$ExternalSyntheticLambda0 mPhotoAction;
    public FrameLayout mPhotoPreview;
    ProtectedBroadcastSender mProtectedBroadcastSender;
    public final StatusBarStateController mStatusBarStateController;
    public final UserTracker.Callback mUserChangedCallback;
    public final UserTracker mUserTracker;
    public final AnonymousClass3 mVisualInterruptionCondition;
    public final Lazy mVisualInterruptionDecisionProviderLazy;
    public final Optional mWirelessCharger;

    /* renamed from: com.google.android.systemui.dreamliner.DockObserver$2  reason: invalid class name */
    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class AnonymousClass2 implements NotificationInterruptSuppressor {
        public final String getName() {
            return "DLObserver";
        }

        public final boolean suppressInterruptions() {
            return DockObserver.sIsDockingUiShowing;
        }
    }

    /* renamed from: com.google.android.systemui.dreamliner.DockObserver$3  reason: invalid class name */
    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class AnonymousClass3 extends VisualInterruptionCondition {
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    final class ChallengeCallback implements WirelessCharger.ChallengeCallback {
        public final ResultReceiver mResultReceiver;

        public ChallengeCallback(ResultReceiver resultReceiver) {
            this.mResultReceiver = resultReceiver;
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class ChallengeWithDock implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public final byte[] challengeData;
        public final byte dockId;
        public final ResultReceiver resultReceiver;
        public final /* synthetic */ DockObserver this$0;

        public /* synthetic */ ChallengeWithDock(DockObserver dockObserver, ResultReceiver resultReceiver2, byte b, byte[] bArr, int i) {
            this.$r8$classId = i;
            this.this$0 = dockObserver;
            this.dockId = b;
            this.challengeData = bArr;
            this.resultReceiver = resultReceiver2;
        }

        /* JADX WARNING: Removed duplicated region for block: B:31:0x0083  */
        /* JADX WARNING: Removed duplicated region for block: B:32:0x00d6  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void run() {
            /*
                r11 = this;
                int r0 = r11.$r8$classId
                switch(r0) {
                    case 0: goto L_0x00db;
                    default: goto L_0x0005;
                }
            L_0x0005:
                com.google.android.systemui.dreamliner.DockObserver r0 = r11.this$0
                java.util.Optional r0 = r0.mWirelessCharger
                boolean r0 = r0.isPresent()
                if (r0 == 0) goto L_0x00da
                com.google.android.systemui.dreamliner.DockObserver r0 = r11.this$0
                java.util.Optional r0 = r0.mWirelessCharger
                java.lang.Object r0 = r0.get()
                com.google.android.systemui.dreamliner.WirelessCharger r0 = (com.google.android.systemui.dreamliner.WirelessCharger) r0
                byte r1 = r11.dockId
                byte[] r2 = r11.challengeData
                com.google.android.systemui.dreamliner.DockObserver$GetWpcAuthChallengeResponseCallback r3 = new com.google.android.systemui.dreamliner.DockObserver$GetWpcAuthChallengeResponseCallback
                com.google.android.systemui.dreamliner.DockObserver r4 = r11.this$0
                android.os.ResultReceiver r11 = r11.resultReceiver
                r3.<init>(r11)
                com.google.android.systemui.dreamliner.WirelessChargerImpl r0 = (com.google.android.systemui.dreamliner.WirelessChargerImpl) r0
                r0.initHALInterface()
                vendor.google.wireless_charger.IWirelessCharger r11 = r0.mWirelessCharger
                if (r11 == 0) goto L_0x00da
                r0 = 0
                r4 = 0
                vendor.google.wireless_charger.IWirelessCharger$Stub$Proxy r11 = (vendor.google.wireless_charger.IWirelessCharger.Stub.Proxy) r11     // Catch:{ Exception -> 0x0058 }
                vendor.google.wireless_charger.WpcAuthChallengeResponse r11 = r11.getWpcAuthChallengeResponse(r1, r2)     // Catch:{ Exception -> 0x0058 }
                byte r1 = r11.maxProtocolVersion     // Catch:{ Exception -> 0x0058 }
                byte r2 = r11.slotPopulatedMask     // Catch:{ Exception -> 0x0054 }
                byte r5 = r11.certificateChainHashLsb     // Catch:{ Exception -> 0x0051 }
                byte[] r6 = r11.signatureR     // Catch:{ Exception -> 0x004f }
                java.util.ArrayList r6 = com.google.android.systemui.dreamliner.WirelessChargerImpl.convertPrimitiveArrayToArrayList(r6)     // Catch:{ Exception -> 0x004f }
                byte[] r11 = r11.signatureS     // Catch:{ Exception -> 0x004b }
                java.util.ArrayList r11 = com.google.android.systemui.dreamliner.WirelessChargerImpl.convertPrimitiveArrayToArrayList(r11)     // Catch:{ Exception -> 0x004b }
                r7 = r0
                goto L_0x0078
            L_0x004b:
                r11 = move-exception
                goto L_0x005c
            L_0x004d:
                r6 = r4
                goto L_0x005c
            L_0x004f:
                r11 = move-exception
                goto L_0x004d
            L_0x0051:
                r11 = move-exception
                r5 = r0
                goto L_0x004d
            L_0x0054:
                r11 = move-exception
                r2 = r0
            L_0x0056:
                r5 = r2
                goto L_0x004d
            L_0x0058:
                r11 = move-exception
                r1 = r0
                r2 = r1
                goto L_0x0056
            L_0x005c:
                int r7 = com.google.android.systemui.dreamliner.WirelessChargerImpl.mapError(r11)
                java.lang.StringBuilder r8 = new java.lang.StringBuilder
                java.lang.String r9 = "get wpc challenge response fail: "
                r8.<init>(r9)
                java.lang.String r11 = r11.getMessage()
                r8.append(r11)
                java.lang.String r11 = r8.toString()
                java.lang.String r8 = "Dreamliner-WLC_HAL"
                android.util.Log.i(r8, r11)
                r11 = r4
            L_0x0078:
                java.lang.String r8 = "GWACR() result: "
                java.lang.String r9 = "DLObserver"
                androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0.m(r8, r7, r9)
                android.os.ResultReceiver r8 = r3.mResultReceiver
                if (r7 != 0) goto L_0x00d6
                java.lang.String r4 = "GWACR() response: mpv="
                java.lang.String r7 = ", pm="
                java.lang.String r10 = ", chl="
                java.lang.StringBuilder r4 = androidx.compose.foundation.text.ValidatingOffsetMapping$$ExternalSyntheticOutline0.m(r4, r1, r7, r2, r10)
                r4.append(r5)
                java.lang.String r7 = ", rv="
                r4.append(r7)
                r4.append(r6)
                java.lang.String r7 = ", sv="
                r4.append(r7)
                r4.append(r11)
                java.lang.String r4 = r4.toString()
                android.util.Log.d(r9, r4)
                com.google.android.systemui.dreamliner.DockObserver r3 = com.google.android.systemui.dreamliner.DockObserver.this
                r3.getClass()
                android.os.Bundle r3 = new android.os.Bundle
                r3.<init>()
                java.lang.String r4 = "max_protocol_ver"
                r3.putByte(r4, r1)
                java.lang.String r1 = "slot_populated_mask"
                r3.putByte(r1, r2)
                java.lang.String r1 = "cert_lsb"
                r3.putByte(r1, r5)
                java.lang.String r1 = "signature_r"
                byte[] r2 = com.google.android.systemui.dreamliner.DockObserver.convertArrayListToPrimitiveArray(r6)
                r3.putByteArray(r1, r2)
                java.lang.String r1 = "signature_s"
                byte[] r11 = com.google.android.systemui.dreamliner.DockObserver.convertArrayListToPrimitiveArray(r11)
                r3.putByteArray(r1, r11)
                r8.send(r0, r3)
                goto L_0x00da
            L_0x00d6:
                r11 = 1
                r8.send(r11, r4)
            L_0x00da:
                return
            L_0x00db:
                com.google.android.systemui.dreamliner.DockObserver r0 = r11.this$0
                java.util.Optional r0 = r0.mWirelessCharger
                boolean r0 = r0.isPresent()
                if (r0 == 0) goto L_0x00ff
                com.google.android.systemui.dreamliner.DockObserver r0 = r11.this$0
                java.util.Optional r0 = r0.mWirelessCharger
                java.lang.Object r0 = r0.get()
                com.google.android.systemui.dreamliner.WirelessCharger r0 = (com.google.android.systemui.dreamliner.WirelessCharger) r0
                byte r1 = r11.dockId
                byte[] r2 = r11.challengeData
                com.google.android.systemui.dreamliner.DockObserver$ChallengeCallback r3 = new com.google.android.systemui.dreamliner.DockObserver$ChallengeCallback
                com.google.android.systemui.dreamliner.DockObserver r4 = r11.this$0
                android.os.ResultReceiver r11 = r11.resultReceiver
                r3.<init>(r11)
                r0.challenge(r1, r2, r3)
            L_0x00ff:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.dreamliner.DockObserver.ChallengeWithDock.run():void");
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class DockObserverBroadcastReceiver extends BroadcastReceiver {
        public DockObserverBroadcastReceiver() {
        }

        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void onReceive(android.content.Context r20, android.content.Intent r21) {
            /*
                r19 = this;
                r0 = r19
                r1 = r20
                r2 = r21
                if (r2 != 0) goto L_0x0009
                return
            L_0x0009:
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                java.lang.String r4 = "onReceive(); "
                r3.<init>(r4)
                java.lang.String r4 = r21.getAction()
                r3.append(r4)
                java.lang.String r3 = r3.toString()
                java.lang.String r4 = "DLObserver"
                android.util.Log.d(r4, r3)
                java.lang.String r3 = r21.getAction()
                int r5 = r3.hashCode()
                r6 = 5
                r7 = 0
                r8 = 3
                r9 = 4
                r10 = 2
                r11 = 1
                switch(r5) {
                    case -1886648615: goto L_0x0064;
                    case -1863595884: goto L_0x005a;
                    case -1538406691: goto L_0x0050;
                    case 798292259: goto L_0x0046;
                    case 882378784: goto L_0x003c;
                    case 1318602046: goto L_0x0032;
                    default: goto L_0x0031;
                }
            L_0x0031:
                goto L_0x006e
            L_0x0032:
                java.lang.String r5 = "com.google.android.systemui.dreamliner.ACTION_REBIND_DOCK_SERVICE"
                boolean r3 = r3.equals(r5)
                if (r3 == 0) goto L_0x006e
                r3 = r10
                goto L_0x006f
            L_0x003c:
                java.lang.String r5 = "com.google.android.systemui.dreamliner.ACTION_GET_FEATURES"
                boolean r3 = r3.equals(r5)
                if (r3 == 0) goto L_0x006e
                r3 = r9
                goto L_0x006f
            L_0x0046:
                java.lang.String r5 = "android.intent.action.BOOT_COMPLETED"
                boolean r3 = r3.equals(r5)
                if (r3 == 0) goto L_0x006e
                r3 = r8
                goto L_0x006f
            L_0x0050:
                java.lang.String r5 = "android.intent.action.BATTERY_CHANGED"
                boolean r3 = r3.equals(r5)
                if (r3 == 0) goto L_0x006e
                r3 = r7
                goto L_0x006f
            L_0x005a:
                java.lang.String r5 = "com.google.android.systemui.dreamliner.ACTION_SET_FEATURES"
                boolean r3 = r3.equals(r5)
                if (r3 == 0) goto L_0x006e
                r3 = r6
                goto L_0x006f
            L_0x0064:
                java.lang.String r5 = "android.intent.action.ACTION_POWER_DISCONNECTED"
                boolean r3 = r3.equals(r5)
                if (r3 == 0) goto L_0x006e
                r3 = r11
                goto L_0x006f
            L_0x006e:
                r3 = -1
            L_0x006f:
                if (r3 == 0) goto L_0x0111
                if (r3 == r11) goto L_0x0109
                if (r3 == r10) goto L_0x0103
                if (r3 == r8) goto L_0x0103
                r1 = 0
                java.lang.String r5 = "android.intent.extra.RESULT_RECEIVER"
                r7 = -1
                java.lang.String r10 = "charger_id"
                if (r3 == r9) goto L_0x00d0
                if (r3 == r6) goto L_0x0084
                goto L_0x0136
            L_0x0084:
                com.google.android.systemui.dreamliner.DockObserver r13 = com.google.android.systemui.dreamliner.DockObserver.this
                r13.getClass()
                long r9 = r2.getLongExtra(r10, r7)
                java.lang.String r0 = "charger_feature"
                long r14 = r2.getLongExtra(r0, r7)
                android.os.Parcelable r0 = r2.getParcelableExtra(r5)
                android.os.ResultReceiver r0 = (android.os.ResultReceiver) r0
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                java.lang.String r3 = "sF, id="
                r2.<init>(r3)
                r2.append(r9)
                java.lang.String r3 = ", feature="
                r2.append(r3)
                r2.append(r14)
                java.lang.String r2 = r2.toString()
                android.util.Log.d(r4, r2)
                if (r0 == 0) goto L_0x0136
                int r2 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
                if (r2 == 0) goto L_0x00cc
                int r2 = (r14 > r7 ? 1 : (r14 == r7 ? 0 : -1))
                if (r2 != 0) goto L_0x00bd
                goto L_0x00cc
            L_0x00bd:
                com.google.android.systemui.dreamliner.DockObserver$SetFeatures r1 = new com.google.android.systemui.dreamliner.DockObserver$SetFeatures
                r12 = r1
                r2 = r14
                r14 = r0
                r15 = r9
                r17 = r2
                r12.<init>(r14, r15, r17)
                com.google.android.systemui.dreamliner.DockObserver.runOnBackgroundThread(r1)
                goto L_0x0136
            L_0x00cc:
                r0.send(r11, r1)
                goto L_0x0136
            L_0x00d0:
                com.google.android.systemui.dreamliner.DockObserver r0 = com.google.android.systemui.dreamliner.DockObserver.this
                r0.getClass()
                long r9 = r2.getLongExtra(r10, r7)
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                java.lang.String r6 = "gF, id="
                r3.<init>(r6)
                r3.append(r9)
                java.lang.String r3 = r3.toString()
                android.util.Log.d(r4, r3)
                android.os.Parcelable r2 = r2.getParcelableExtra(r5)
                android.os.ResultReceiver r2 = (android.os.ResultReceiver) r2
                if (r2 == 0) goto L_0x0136
                int r3 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
                if (r3 != 0) goto L_0x00fa
                r2.send(r11, r1)
                goto L_0x0136
            L_0x00fa:
                com.google.android.systemui.dreamliner.DockObserver$GetFeatures r1 = new com.google.android.systemui.dreamliner.DockObserver$GetFeatures
                r1.<init>(r2, r9)
                com.google.android.systemui.dreamliner.DockObserver.runOnBackgroundThread(r1)
                goto L_0x0136
            L_0x0103:
                com.google.android.systemui.dreamliner.DockObserver r0 = com.google.android.systemui.dreamliner.DockObserver.this
                r0.updateCurrentDockingStatus(r1)
                goto L_0x0136
            L_0x0109:
                com.google.android.systemui.dreamliner.DockObserver r0 = com.google.android.systemui.dreamliner.DockObserver.this
                com.google.android.systemui.dreamliner.DockObserver.m1709$$Nest$mstopDreamlinerService(r0, r1)
                com.google.android.systemui.dreamliner.DockObserver.sIsDockingUiShowing = r7
                goto L_0x0136
            L_0x0111:
                com.google.android.systemui.dreamliner.DockObserver r3 = com.google.android.systemui.dreamliner.DockObserver.this
                boolean r5 = r3.mIsWirelessCharging
                boolean r2 = com.google.android.systemui.dreamliner.DockObserver.isWirelessCharging(r20, r21)
                r3.mIsWirelessCharging = r2
                com.google.android.systemui.dreamliner.DockObserver r2 = com.google.android.systemui.dreamliner.DockObserver.this
                boolean r2 = r2.mIsWirelessCharging
                if (r5 == r2) goto L_0x0136
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                java.lang.String r3 = "mWirelessCharging:"
                r2.<init>(r3)
                com.google.android.systemui.dreamliner.DockObserver r3 = com.google.android.systemui.dreamliner.DockObserver.this
                boolean r3 = r3.mIsWirelessCharging
                com.android.settingslib.bluetooth.CachedBluetoothDevice$$ExternalSyntheticOutline0.m(r2, r3, r4)
                com.google.android.systemui.dreamliner.DockObserver r0 = com.google.android.systemui.dreamliner.DockObserver.this
                boolean r2 = r0.mIsWirelessCharging
                r0.tryToStartDreamlinerServiceIfNeeded(r1, r2)
            L_0x0136:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.dreamliner.DockObserver.DockObserverBroadcastReceiver.onReceive(android.content.Context, android.content.Intent):void");
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    class DreamlinerBroadcastReceiver extends BroadcastReceiver {
        public boolean mListening;

        public DreamlinerBroadcastReceiver() {
        }

        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void onReceive(android.content.Context r22, android.content.Intent r23) {
            /*
                r21 = this;
                r0 = r21
                r1 = r22
                r2 = r23
                if (r2 != 0) goto L_0x0009
                return
            L_0x0009:
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                java.lang.String r4 = "Dock Receiver.onReceive(): "
                r3.<init>(r4)
                java.lang.String r4 = r23.getAction()
                r3.append(r4)
                java.lang.String r3 = r3.toString()
                java.lang.String r4 = "DLObserver"
                android.util.Log.d(r4, r3)
                java.lang.String r3 = r23.getAction()
                int r5 = r3.hashCode()
                r6 = 4
                r7 = 2
                r8 = -1
                r9 = 0
                r10 = 1
                switch(r5) {
                    case -2133451883: goto L_0x0106;
                    case -1627881412: goto L_0x00fb;
                    case -1616532553: goto L_0x00f1;
                    case -1598391011: goto L_0x00e7;
                    case -1584152500: goto L_0x00dc;
                    case -1579804275: goto L_0x00d2;
                    case -1458969207: goto L_0x00c8;
                    case -1185055092: goto L_0x00bd;
                    case -686255721: goto L_0x00b2;
                    case -545730616: goto L_0x00a7;
                    case -484477188: goto L_0x009c;
                    case -390730981: goto L_0x0090;
                    case 664552276: goto L_0x0085;
                    case 675144007: goto L_0x0079;
                    case 675346819: goto L_0x006d;
                    case 717413661: goto L_0x0061;
                    case 1954561023: goto L_0x0055;
                    case 1996802687: goto L_0x004a;
                    case 2009307741: goto L_0x003e;
                    case 2121889077: goto L_0x0032;
                    default: goto L_0x0030;
                }
            L_0x0030:
                goto L_0x0111
            L_0x0032:
                java.lang.String r5 = "com.google.android.systemui.dreamliner.ACTION_GET_WPC_CHALLENGE_RESPONSE"
                boolean r3 = r3.equals(r5)
                if (r3 == 0) goto L_0x0111
                r3 = 19
                goto L_0x0112
            L_0x003e:
                java.lang.String r5 = "com.google.android.systemui.dreamliner.ACTION_GET_FAN_INFO"
                boolean r3 = r3.equals(r5)
                if (r3 == 0) goto L_0x0111
                r3 = 14
                goto L_0x0112
            L_0x004a:
                java.lang.String r5 = "com.google.android.systemui.dreamliner.ACTION_DOCK_UI_ACTIVE"
                boolean r3 = r3.equals(r5)
                if (r3 == 0) goto L_0x0111
                r3 = r7
                goto L_0x0112
            L_0x0055:
                java.lang.String r5 = "com.google.android.systemui.dreamliner.ACTION_GET_WPC_CERTIFICATE"
                boolean r3 = r3.equals(r5)
                if (r3 == 0) goto L_0x0111
                r3 = 18
                goto L_0x0112
            L_0x0061:
                java.lang.String r5 = "com.google.android.systemui.dreamliner.assistant_poodle"
                boolean r3 = r3.equals(r5)
                if (r3 == 0) goto L_0x0111
                r3 = 10
                goto L_0x0112
            L_0x006d:
                java.lang.String r5 = "com.google.android.systemui.dreamliner.photo"
                boolean r3 = r3.equals(r5)
                if (r3 == 0) goto L_0x0111
                r3 = 11
                goto L_0x0112
            L_0x0079:
                java.lang.String r5 = "com.google.android.systemui.dreamliner.pause"
                boolean r3 = r3.equals(r5)
                if (r3 == 0) goto L_0x0111
                r3 = 8
                goto L_0x0112
            L_0x0085:
                java.lang.String r5 = "com.google.android.systemui.dreamliner.dream"
                boolean r3 = r3.equals(r5)
                if (r3 == 0) goto L_0x0111
                r3 = 5
                goto L_0x0112
            L_0x0090:
                java.lang.String r5 = "com.google.android.systemui.dreamliner.undock"
                boolean r3 = r3.equals(r5)
                if (r3 == 0) goto L_0x0111
                r3 = 9
                goto L_0x0112
            L_0x009c:
                java.lang.String r5 = "com.google.android.systemui.dreamliner.resume"
                boolean r3 = r3.equals(r5)
                if (r3 == 0) goto L_0x0111
                r3 = 7
                goto L_0x0112
            L_0x00a7:
                java.lang.String r5 = "com.google.android.systemui.dreamliner.paired"
                boolean r3 = r3.equals(r5)
                if (r3 == 0) goto L_0x0111
                r3 = 6
                goto L_0x0112
            L_0x00b2:
                java.lang.String r5 = "com.google.android.systemui.dreamliner.ACTION_GET_WPC_DIGESTS"
                boolean r3 = r3.equals(r5)
                if (r3 == 0) goto L_0x0111
                r3 = 17
                goto L_0x0112
            L_0x00bd:
                java.lang.String r5 = "com.google.android.systemui.dreamliner.ACTION_GET_FAN_SIMPLE_INFO"
                boolean r3 = r3.equals(r5)
                if (r3 == 0) goto L_0x0111
                r3 = 13
                goto L_0x0112
            L_0x00c8:
                java.lang.String r5 = "com.google.android.systemui.dreamliner.ACTION_CHALLENGE"
                boolean r3 = r3.equals(r5)
                if (r3 == 0) goto L_0x0111
                r3 = r6
                goto L_0x0112
            L_0x00d2:
                java.lang.String r5 = "com.google.android.systemui.dreamliner.ACTION_DOCK_UI_IDLE"
                boolean r3 = r3.equals(r5)
                if (r3 == 0) goto L_0x0111
                r3 = r10
                goto L_0x0112
            L_0x00dc:
                java.lang.String r5 = "com.google.android.systemui.dreamliner.photo_error"
                boolean r3 = r3.equals(r5)
                if (r3 == 0) goto L_0x0111
                r3 = 12
                goto L_0x0112
            L_0x00e7:
                java.lang.String r5 = "com.google.android.systemui.dreamliner.ACTION_KEY_EXCHANGE"
                boolean r3 = r3.equals(r5)
                if (r3 == 0) goto L_0x0111
                r3 = 3
                goto L_0x0112
            L_0x00f1:
                java.lang.String r5 = "com.google.android.systemui.dreamliner.ACTION_GET_DOCK_INFO"
                boolean r3 = r3.equals(r5)
                if (r3 == 0) goto L_0x0111
                r3 = r9
                goto L_0x0112
            L_0x00fb:
                java.lang.String r5 = "com.google.android.systemui.dreamliner.ACTION_SET_FAN"
                boolean r3 = r3.equals(r5)
                if (r3 == 0) goto L_0x0111
                r3 = 15
                goto L_0x0112
            L_0x0106:
                java.lang.String r5 = "com.google.android.systemui.dreamliner.ACTION_GET_FAN_LEVEL"
                boolean r3 = r3.equals(r5)
                if (r3 == 0) goto L_0x0111
                r3 = 16
                goto L_0x0112
            L_0x0111:
                r3 = r8
            L_0x0112:
                r5 = 1073741824(0x40000000, float:2.0)
                java.lang.String r11 = "slot_number"
                java.lang.String r12 = "fan_id"
                r13 = 0
                java.lang.String r14 = "android.intent.extra.RESULT_RECEIVER"
                switch(r3) {
                    case 0: goto L_0x044f;
                    case 1: goto L_0x0420;
                    case 2: goto L_0x03f1;
                    case 3: goto L_0x03c5;
                    case 4: goto L_0x038c;
                    case 5: goto L_0x0370;
                    case 6: goto L_0x0323;
                    case 7: goto L_0x033d;
                    case 8: goto L_0x0309;
                    case 9: goto L_0x02ef;
                    case 10: goto L_0x02dc;
                    case 11: goto L_0x02ac;
                    case 12: goto L_0x0299;
                    case 13: goto L_0x026c;
                    case 14: goto L_0x023f;
                    case 15: goto L_0x01eb;
                    case 16: goto L_0x01df;
                    case 17: goto L_0x01ad;
                    case 18: goto L_0x0165;
                    case 19: goto L_0x0120;
                    default: goto L_0x011e;
                }
            L_0x011e:
                goto L_0x0461
            L_0x0120:
                byte r1 = r2.getByteExtra(r11, r8)
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                java.lang.String r5 = "gWACR, num="
                r3.<init>(r5)
                r3.append(r1)
                java.lang.String r3 = r3.toString()
                android.util.Log.d(r4, r3)
                android.os.Parcelable r3 = r2.getParcelableExtra(r14)
                android.os.ResultReceiver r3 = (android.os.ResultReceiver) r3
                if (r3 == 0) goto L_0x0461
                java.lang.String r4 = "wpc_nonce"
                byte[] r2 = r2.getByteArrayExtra(r4)
                if (r2 == 0) goto L_0x0160
                int r4 = r2.length
                if (r4 > 0) goto L_0x0149
                goto L_0x0160
            L_0x0149:
                com.google.android.systemui.dreamliner.DockObserver$ChallengeWithDock r4 = new com.google.android.systemui.dreamliner.DockObserver$ChallengeWithDock
                com.google.android.systemui.dreamliner.DockObserver r0 = com.google.android.systemui.dreamliner.DockObserver.this
                r20 = 1
                r15 = r4
                r16 = r0
                r17 = r3
                r18 = r1
                r19 = r2
                r15.<init>(r16, r17, r18, r19, r20)
                com.google.android.systemui.dreamliner.DockObserver.runOnBackgroundThread(r4)
                goto L_0x0461
            L_0x0160:
                r3.send(r10, r13)
                goto L_0x0461
            L_0x0165:
                byte r1 = r2.getByteExtra(r11, r8)
                java.lang.String r3 = "cert_offset"
                short r9 = r2.getShortExtra(r3, r8)
                java.lang.String r3 = "cert_length"
                short r3 = r2.getShortExtra(r3, r8)
                java.lang.String r5 = "gWAC, num="
                java.lang.String r6 = ", offset="
                java.lang.String r7 = ", length="
                java.lang.StringBuilder r5 = androidx.compose.foundation.text.ValidatingOffsetMapping$$ExternalSyntheticOutline0.m(r5, r1, r6, r9, r7)
                r5.append(r3)
                java.lang.String r5 = r5.toString()
                android.util.Log.d(r4, r5)
                android.os.Parcelable r2 = r2.getParcelableExtra(r14)
                r7 = r2
                android.os.ResultReceiver r7 = (android.os.ResultReceiver) r7
                if (r7 == 0) goto L_0x0461
                if (r1 == r8) goto L_0x01a8
                if (r9 == r8) goto L_0x01a8
                if (r3 != r8) goto L_0x0199
                goto L_0x01a8
            L_0x0199:
                com.google.android.systemui.dreamliner.DockObserver$GetWpcAuthCertificate r2 = new com.google.android.systemui.dreamliner.DockObserver$GetWpcAuthCertificate
                com.google.android.systemui.dreamliner.DockObserver r6 = com.google.android.systemui.dreamliner.DockObserver.this
                r5 = r2
                r8 = r1
                r10 = r3
                r5.<init>(r7, r8, r9, r10)
                com.google.android.systemui.dreamliner.DockObserver.runOnBackgroundThread(r2)
                goto L_0x0461
            L_0x01a8:
                r7.send(r10, r13)
                goto L_0x0461
            L_0x01ad:
                java.lang.String r1 = "slot_mask"
                byte r1 = r2.getByteExtra(r1, r8)
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                java.lang.String r5 = "gWAD, mask="
                r3.<init>(r5)
                r3.append(r1)
                java.lang.String r3 = r3.toString()
                android.util.Log.d(r4, r3)
                android.os.Parcelable r2 = r2.getParcelableExtra(r14)
                android.os.ResultReceiver r2 = (android.os.ResultReceiver) r2
                if (r2 == 0) goto L_0x0461
                if (r1 != r8) goto L_0x01d3
                r2.send(r10, r13)
                goto L_0x0461
            L_0x01d3:
                com.google.android.systemui.dreamliner.DockObserver$GetFanInformation r3 = new com.google.android.systemui.dreamliner.DockObserver$GetFanInformation
                com.google.android.systemui.dreamliner.DockObserver r0 = com.google.android.systemui.dreamliner.DockObserver.this
                r3.<init>(r0, r2, r1)
                com.google.android.systemui.dreamliner.DockObserver.runOnBackgroundThread(r3)
                goto L_0x0461
            L_0x01df:
                com.google.android.systemui.dreamliner.DockObserver r1 = com.google.android.systemui.dreamliner.DockObserver.this
                com.google.android.systemui.dreamliner.DockObserver$$ExternalSyntheticLambda3 r2 = new com.google.android.systemui.dreamliner.DockObserver$$ExternalSyntheticLambda3
                r2.<init>(r10, r0)
                r1.refreshFanLevel(r2)
                goto L_0x0461
            L_0x01eb:
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                java.lang.String r3 = "command=1, i="
                r1.<init>(r3)
                byte r3 = r2.getByteExtra(r12, r8)
                r1.append(r3)
                java.lang.String r3 = ", m="
                r1.append(r3)
                java.lang.String r3 = "fan_mode"
                byte r5 = r2.getByteExtra(r3, r8)
                r1.append(r5)
                java.lang.String r5 = ", r="
                r1.append(r5)
                java.lang.String r5 = "fan_rpm"
                int r6 = r2.getIntExtra(r5, r8)
                r1.append(r6)
                java.lang.String r1 = r1.toString()
                android.util.Log.d(r4, r1)
                byte r1 = r2.getByteExtra(r12, r9)
                byte r3 = r2.getByteExtra(r3, r9)
                int r2 = r2.getIntExtra(r5, r8)
                if (r3 != r10) goto L_0x0233
                if (r2 != r8) goto L_0x0233
                java.lang.String r0 = "Failed to get r."
                android.util.Log.e(r4, r0)
                goto L_0x0461
            L_0x0233:
                com.google.android.systemui.dreamliner.DockObserver$SetFan r4 = new com.google.android.systemui.dreamliner.DockObserver$SetFan
                com.google.android.systemui.dreamliner.DockObserver r0 = com.google.android.systemui.dreamliner.DockObserver.this
                r4.<init>(r1, r3, r2)
                com.google.android.systemui.dreamliner.DockObserver.runOnBackgroundThread(r4)
                goto L_0x0461
            L_0x023f:
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                java.lang.String r3 = "command=0, i="
                r1.<init>(r3)
                byte r3 = r2.getByteExtra(r12, r8)
                r1.append(r3)
                java.lang.String r1 = r1.toString()
                android.util.Log.d(r4, r1)
                android.os.Parcelable r1 = r2.getParcelableExtra(r14)
                android.os.ResultReceiver r1 = (android.os.ResultReceiver) r1
                if (r1 == 0) goto L_0x0461
                com.google.android.systemui.dreamliner.DockObserver$GetFanInformation r3 = new com.google.android.systemui.dreamliner.DockObserver$GetFanInformation
                com.google.android.systemui.dreamliner.DockObserver r0 = com.google.android.systemui.dreamliner.DockObserver.this
                byte r2 = r2.getByteExtra(r12, r9)
                r3.<init>(r0, r2, r1, r9)
                com.google.android.systemui.dreamliner.DockObserver.runOnBackgroundThread(r3)
                goto L_0x0461
            L_0x026c:
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                java.lang.String r3 = "command=3, i="
                r1.<init>(r3)
                byte r3 = r2.getByteExtra(r12, r8)
                r1.append(r3)
                java.lang.String r1 = r1.toString()
                android.util.Log.d(r4, r1)
                android.os.Parcelable r1 = r2.getParcelableExtra(r14)
                android.os.ResultReceiver r1 = (android.os.ResultReceiver) r1
                if (r1 == 0) goto L_0x0461
                com.google.android.systemui.dreamliner.DockObserver$GetFanInformation r3 = new com.google.android.systemui.dreamliner.DockObserver$GetFanInformation
                com.google.android.systemui.dreamliner.DockObserver r0 = com.google.android.systemui.dreamliner.DockObserver.this
                byte r2 = r2.getByteExtra(r12, r9)
                r3.<init>(r0, r2, r1, r10)
                com.google.android.systemui.dreamliner.DockObserver.runOnBackgroundThread(r3)
                goto L_0x0461
            L_0x0299:
                com.google.android.systemui.dreamliner.DockObserver r0 = com.google.android.systemui.dreamliner.DockObserver.this
                r0.getClass()
                java.lang.String r1 = "Fail to launch photo"
                android.util.Log.w(r4, r1)
                com.google.android.systemui.dreamliner.DockGestureController r0 = r0.mDockGestureController
                if (r0 == 0) goto L_0x0461
                r0.hidePhotoPreview(r9)
                goto L_0x0461
            L_0x02ac:
                com.google.android.systemui.dreamliner.DockObserver r1 = com.google.android.systemui.dreamliner.DockObserver.this
                r1.getClass()
                android.os.Parcelable r3 = r2.getParcelableExtra(r14)
                android.os.ResultReceiver r3 = (android.os.ResultReceiver) r3
                java.lang.String r5 = "enabled"
                boolean r2 = r2.getBooleanExtra(r5, r9)
                java.lang.String r5 = "configPhotoAction, enabled="
                com.android.settingslib.mobile.MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0.m(r5, r2, r4)
                com.google.android.systemui.dreamliner.DockGestureController r4 = r1.mDockGestureController
                if (r4 == 0) goto L_0x02c8
                r4.mPhotoEnabled = r2
            L_0x02c8:
                if (r3 == 0) goto L_0x02d3
                com.google.android.systemui.dreamliner.DockIndicationController r2 = r1.mIndicationController
                if (r2 == 0) goto L_0x02d3
                com.google.android.systemui.dreamliner.DockObserver$$ExternalSyntheticLambda0 r13 = new com.google.android.systemui.dreamliner.DockObserver$$ExternalSyntheticLambda0
                r13.<init>(r9, r1, r3)
            L_0x02d3:
                r1.mPhotoAction = r13
                com.google.android.systemui.dreamliner.DockObserver r0 = com.google.android.systemui.dreamliner.DockObserver.this
                r0.runPhotoAction()
                goto L_0x0461
            L_0x02dc:
                com.google.android.systemui.dreamliner.DockObserver r0 = com.google.android.systemui.dreamliner.DockObserver.this
                com.google.android.systemui.dreamliner.DockIndicationController r0 = r0.mIndicationController
                if (r0 == 0) goto L_0x0461
                java.lang.String r1 = "showing"
                boolean r1 = r2.getBooleanExtra(r1, r9)
                r0.mTopIconShowing = r1
                r0.updateVisibility$8()
                goto L_0x0461
            L_0x02ef:
                com.google.android.systemui.dreamliner.DockObserver r1 = com.google.android.systemui.dreamliner.DockObserver.this
                r1.onDockStateChanged(r9)
                com.google.android.systemui.dreamliner.DockObserver r1 = com.google.android.systemui.dreamliner.DockObserver.this
                com.google.android.systemui.dreamliner.DockGestureController r1 = r1.mDockGestureController
                java.lang.Class<com.google.android.systemui.dreamliner.DockGestureController> r2 = com.google.android.systemui.dreamliner.DockGestureController.class
                boolean r1 = com.google.android.systemui.dreamliner.DockObserver.assertNotNull(r1)
                if (r1 == 0) goto L_0x0461
                com.google.android.systemui.dreamliner.DockObserver r0 = com.google.android.systemui.dreamliner.DockObserver.this
                com.google.android.systemui.dreamliner.DockGestureController r0 = r0.mDockGestureController
                r0.stopMonitoring()
                goto L_0x0461
            L_0x0309:
                com.google.android.systemui.dreamliner.DockObserver r1 = com.google.android.systemui.dreamliner.DockObserver.this
                r1.onDockStateChanged(r7)
                com.google.android.systemui.dreamliner.DockObserver r1 = com.google.android.systemui.dreamliner.DockObserver.this
                com.google.android.systemui.dreamliner.DockGestureController r1 = r1.mDockGestureController
                java.lang.Class<com.google.android.systemui.dreamliner.DockGestureController> r2 = com.google.android.systemui.dreamliner.DockGestureController.class
                boolean r1 = com.google.android.systemui.dreamliner.DockObserver.assertNotNull(r1)
                if (r1 == 0) goto L_0x0461
                com.google.android.systemui.dreamliner.DockObserver r0 = com.google.android.systemui.dreamliner.DockObserver.this
                com.google.android.systemui.dreamliner.DockGestureController r0 = r0.mDockGestureController
                r0.stopMonitoring()
                goto L_0x0461
            L_0x0323:
                com.google.android.systemui.dreamliner.DockObserver r1 = com.google.android.systemui.dreamliner.DockObserver.this
                com.google.android.systemui.dreamliner.DockGestureController r1 = r1.mDockGestureController
                java.lang.Class<com.google.android.systemui.dreamliner.DockGestureController> r3 = com.google.android.systemui.dreamliner.DockGestureController.class
                boolean r1 = com.google.android.systemui.dreamliner.DockObserver.assertNotNull(r1)
                if (r1 == 0) goto L_0x033d
                com.google.android.systemui.dreamliner.DockObserver r1 = com.google.android.systemui.dreamliner.DockObserver.this
                com.google.android.systemui.dreamliner.DockGestureController r1 = r1.mDockGestureController
                java.lang.String r3 = "single_tap_action"
                android.os.Parcelable r2 = r2.getParcelableExtra(r3)
                android.app.PendingIntent r2 = (android.app.PendingIntent) r2
                r1.mTapAction = r2
            L_0x033d:
                com.google.android.systemui.dreamliner.DockObserver r1 = com.google.android.systemui.dreamliner.DockObserver.this
                r1.onDockStateChanged(r10)
                com.google.android.systemui.dreamliner.DockObserver r1 = com.google.android.systemui.dreamliner.DockObserver.this
                com.google.android.systemui.dreamliner.DockGestureController r1 = r1.mDockGestureController
                java.lang.Class<com.google.android.systemui.dreamliner.DockGestureController> r2 = com.google.android.systemui.dreamliner.DockGestureController.class
                boolean r1 = com.google.android.systemui.dreamliner.DockObserver.assertNotNull(r1)
                if (r1 == 0) goto L_0x0461
                com.google.android.systemui.dreamliner.DockObserver r0 = com.google.android.systemui.dreamliner.DockObserver.this
                com.google.android.systemui.dreamliner.DockGestureController r0 = r0.mDockGestureController
                android.widget.ImageView r1 = r0.mSettingsGear
                r1.setVisibility(r6)
                com.android.systemui.plugins.statusbar.StatusBarStateController r1 = r0.mStatusBarStateController
                boolean r1 = r1.isDozing()
                r0.onDozingChanged(r1)
                com.android.systemui.plugins.statusbar.StatusBarStateController r1 = r0.mStatusBarStateController
                r1.addCallback(r0)
                com.android.systemui.statusbar.policy.KeyguardStateController r1 = r0.mKeyguardStateController
                com.google.android.systemui.dreamliner.DockGestureController$1 r0 = r0.mKeyguardMonitorCallback
                com.android.systemui.statusbar.policy.KeyguardStateControllerImpl r1 = (com.android.systemui.statusbar.policy.KeyguardStateControllerImpl) r1
                r1.addCallback(r0)
                goto L_0x0461
            L_0x0370:
                com.google.android.systemui.dreamliner.DockObserver r0 = com.google.android.systemui.dreamliner.DockObserver.this
                r0.getClass()
                java.lang.Class<android.os.PowerManager> r0 = android.os.PowerManager.class
                java.lang.Object r0 = r1.getSystemService(r0)
                android.os.PowerManager r0 = (android.os.PowerManager) r0
                boolean r1 = r0.isScreenOn()
                if (r1 == 0) goto L_0x0461
                long r1 = android.os.SystemClock.uptimeMillis()
                r0.goToSleep(r1)
                goto L_0x0461
            L_0x038c:
                com.google.android.systemui.dreamliner.DockObserver r0 = com.google.android.systemui.dreamliner.DockObserver.this
                r0.getClass()
                java.lang.String r1 = "triggerChallengeWithDock"
                android.util.Log.d(r4, r1)
                android.os.Parcelable r1 = r2.getParcelableExtra(r14)
                r5 = r1
                android.os.ResultReceiver r5 = (android.os.ResultReceiver) r5
                if (r5 == 0) goto L_0x0461
                java.lang.String r1 = "challenge_dock_id"
                byte r6 = r2.getByteExtra(r1, r8)
                java.lang.String r1 = "challenge_data"
                byte[] r7 = r2.getByteArrayExtra(r1)
                if (r7 == 0) goto L_0x03c0
                int r1 = r7.length
                if (r1 <= 0) goto L_0x03c0
                if (r6 >= 0) goto L_0x03b3
                goto L_0x03c0
            L_0x03b3:
                com.google.android.systemui.dreamliner.DockObserver$ChallengeWithDock r1 = new com.google.android.systemui.dreamliner.DockObserver$ChallengeWithDock
                r8 = 0
                r3 = r1
                r4 = r0
                r3.<init>(r4, r5, r6, r7, r8)
                com.google.android.systemui.dreamliner.DockObserver.runOnBackgroundThread(r1)
                goto L_0x0461
            L_0x03c0:
                r5.send(r10, r13)
                goto L_0x0461
            L_0x03c5:
                com.google.android.systemui.dreamliner.DockObserver r0 = com.google.android.systemui.dreamliner.DockObserver.this
                r0.getClass()
                java.lang.String r1 = "triggerKeyExchangeWithDock"
                android.util.Log.d(r4, r1)
                android.os.Parcelable r1 = r2.getParcelableExtra(r14)
                android.os.ResultReceiver r1 = (android.os.ResultReceiver) r1
                if (r1 == 0) goto L_0x0461
                java.lang.String r3 = "public_key"
                byte[] r2 = r2.getByteArrayExtra(r3)
                if (r2 == 0) goto L_0x03ed
                int r3 = r2.length
                if (r3 > 0) goto L_0x03e3
                goto L_0x03ed
            L_0x03e3:
                com.google.android.systemui.dreamliner.DockObserver$GetDockInfo r3 = new com.google.android.systemui.dreamliner.DockObserver$GetDockInfo
                r3.<init>(r0, r1, r2, r10)
                com.google.android.systemui.dreamliner.DockObserver.runOnBackgroundThread(r3)
                goto L_0x0461
            L_0x03ed:
                r1.send(r10, r13)
                goto L_0x0461
            L_0x03f1:
                com.google.android.systemui.dreamliner.DockObserver r0 = com.google.android.systemui.dreamliner.DockObserver.this
                r0.getClass()
                java.lang.String r1 = "sendDockActiveIntent()"
                android.util.Log.d(r4, r1)
                com.google.android.systemui.dreamliner.DockObserver$ProtectedBroadcastSender r0 = r0.mProtectedBroadcastSender
                android.content.Intent r1 = new android.content.Intent
                java.lang.String r2 = "android.intent.action.DOCK_ACTIVE"
                r1.<init>(r2)
                android.content.Intent r1 = r1.addFlags(r5)
                android.content.Context r2 = r0.mContext
                android.os.UserHandle r2 = r2.getUser()
                boolean r2 = r2.isSystem()
                if (r2 != 0) goto L_0x0415
                goto L_0x041d
            L_0x0415:
                com.google.android.systemui.dreamliner.DockObserver$$ExternalSyntheticLambda0 r2 = new com.google.android.systemui.dreamliner.DockObserver$$ExternalSyntheticLambda0
                r2.<init>(r6, r0, r1)
                com.google.android.systemui.dreamliner.DockObserver.runOnBackgroundThread(r2)
            L_0x041d:
                com.google.android.systemui.dreamliner.DockObserver.sIsDockingUiShowing = r9
                goto L_0x0461
            L_0x0420:
                com.google.android.systemui.dreamliner.DockObserver r0 = com.google.android.systemui.dreamliner.DockObserver.this
                r0.getClass()
                java.lang.String r1 = "sendDockIdleIntent()"
                android.util.Log.d(r4, r1)
                com.google.android.systemui.dreamliner.DockObserver$ProtectedBroadcastSender r0 = r0.mProtectedBroadcastSender
                android.content.Intent r1 = new android.content.Intent
                java.lang.String r2 = "android.intent.action.DOCK_IDLE"
                r1.<init>(r2)
                android.content.Intent r1 = r1.addFlags(r5)
                android.content.Context r2 = r0.mContext
                android.os.UserHandle r2 = r2.getUser()
                boolean r2 = r2.isSystem()
                if (r2 != 0) goto L_0x0444
                goto L_0x044c
            L_0x0444:
                com.google.android.systemui.dreamliner.DockObserver$$ExternalSyntheticLambda0 r2 = new com.google.android.systemui.dreamliner.DockObserver$$ExternalSyntheticLambda0
                r2.<init>(r6, r0, r1)
                com.google.android.systemui.dreamliner.DockObserver.runOnBackgroundThread(r2)
            L_0x044c:
                com.google.android.systemui.dreamliner.DockObserver.sIsDockingUiShowing = r10
                goto L_0x0461
            L_0x044f:
                android.os.Parcelable r2 = r2.getParcelableExtra(r14)
                android.os.ResultReceiver r2 = (android.os.ResultReceiver) r2
                if (r2 == 0) goto L_0x0461
                com.google.android.systemui.dreamliner.DockObserver$GetDockInfo r3 = new com.google.android.systemui.dreamliner.DockObserver$GetDockInfo
                com.google.android.systemui.dreamliner.DockObserver r0 = com.google.android.systemui.dreamliner.DockObserver.this
                r3.<init>(r0, r2, r1, r9)
                com.google.android.systemui.dreamliner.DockObserver.runOnBackgroundThread(r3)
            L_0x0461:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.dreamliner.DockObserver.DreamlinerBroadcastReceiver.onReceive(android.content.Context, android.content.Intent):void");
        }

        public final void registerReceiver(Context context) {
            if (!this.mListening) {
                UserHandle userHandle = UserHandle.ALL;
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(DockObserver.ACTION_GET_DOCK_INFO);
                intentFilter.addAction(DockObserver.ACTION_DOCK_UI_IDLE);
                intentFilter.addAction(DockObserver.ACTION_DOCK_UI_ACTIVE);
                intentFilter.addAction(DockObserver.ACTION_KEY_EXCHANGE);
                intentFilter.addAction(DockObserver.ACTION_CHALLENGE);
                intentFilter.addAction("com.google.android.systemui.dreamliner.dream");
                intentFilter.addAction("com.google.android.systemui.dreamliner.paired");
                intentFilter.addAction("com.google.android.systemui.dreamliner.pause");
                intentFilter.addAction("com.google.android.systemui.dreamliner.resume");
                intentFilter.addAction("com.google.android.systemui.dreamliner.undock");
                intentFilter.addAction("com.google.android.systemui.dreamliner.assistant_poodle");
                intentFilter.addAction("com.google.android.systemui.dreamliner.photo");
                intentFilter.addAction("com.google.android.systemui.dreamliner.photo_error");
                intentFilter.addAction("com.google.android.systemui.dreamliner.ACTION_GET_FAN_INFO");
                intentFilter.addAction("com.google.android.systemui.dreamliner.ACTION_GET_FAN_SIMPLE_INFO");
                intentFilter.addAction("com.google.android.systemui.dreamliner.ACTION_SET_FAN");
                intentFilter.addAction("com.google.android.systemui.dreamliner.ACTION_GET_FAN_LEVEL");
                intentFilter.addAction("com.google.android.systemui.dreamliner.ACTION_GET_WPC_DIGESTS");
                intentFilter.addAction("com.google.android.systemui.dreamliner.ACTION_GET_WPC_CERTIFICATE");
                intentFilter.addAction("com.google.android.systemui.dreamliner.ACTION_GET_WPC_CHALLENGE_RESPONSE");
                context.registerReceiverAsUser(this, userHandle, intentFilter, DockObserver.PERMISSION_WIRELESS_CHARGER_STATUS, (Handler) null, 2);
                this.mListening = true;
            }
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class GetDockInfo implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public final Object context;
        public final ResultReceiver resultReceiver;
        public final /* synthetic */ DockObserver this$0;

        public /* synthetic */ GetDockInfo(DockObserver dockObserver, ResultReceiver resultReceiver2, Object obj, int i) {
            this.$r8$classId = i;
            this.this$0 = dockObserver;
            this.resultReceiver = resultReceiver2;
            this.context = obj;
        }

        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    if (this.this$0.mWirelessCharger.isPresent()) {
                        ((WirelessCharger) this.this$0.mWirelessCharger.get()).getInformation(new GetInformationCallback(this.resultReceiver));
                        return;
                    }
                    return;
                default:
                    if (this.this$0.mWirelessCharger.isPresent()) {
                        ((WirelessCharger) this.this$0.mWirelessCharger.get()).keyExchange((byte[]) this.context, new KeyExchangeCallback(this.resultReceiver));
                        return;
                    }
                    return;
            }
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class GetFanInformation implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public final byte mFanId;
        public final ResultReceiver mResultReceiver;
        public final /* synthetic */ DockObserver this$0;

        public /* synthetic */ GetFanInformation(DockObserver dockObserver, byte b, ResultReceiver resultReceiver, int i) {
            this.$r8$classId = i;
            this.this$0 = dockObserver;
            this.mFanId = b;
            this.mResultReceiver = resultReceiver;
        }

        /* JADX WARNING: Removed duplicated region for block: B:22:0x0076  */
        /* JADX WARNING: Removed duplicated region for block: B:26:0x00b6  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void run() {
            /*
                r9 = this;
                int r0 = r9.$r8$classId
                switch(r0) {
                    case 0: goto L_0x00dc;
                    case 1: goto L_0x00bb;
                    default: goto L_0x0005;
                }
            L_0x0005:
                com.google.android.systemui.dreamliner.DockObserver r0 = r9.this$0
                java.util.Optional r0 = r0.mWirelessCharger
                boolean r0 = r0.isPresent()
                if (r0 == 0) goto L_0x00ba
                com.google.android.systemui.dreamliner.DockObserver r0 = r9.this$0
                java.util.Optional r0 = r0.mWirelessCharger
                java.lang.Object r0 = r0.get()
                com.google.android.systemui.dreamliner.WirelessCharger r0 = (com.google.android.systemui.dreamliner.WirelessCharger) r0
                byte r1 = r9.mFanId
                com.google.android.systemui.dreamliner.DockObserver$GetWpcAuthDigestsCallback r2 = new com.google.android.systemui.dreamliner.DockObserver$GetWpcAuthDigestsCallback
                com.google.android.systemui.dreamliner.DockObserver r3 = r9.this$0
                android.os.ResultReceiver r9 = r9.mResultReceiver
                r2.<init>(r9)
                com.google.android.systemui.dreamliner.WirelessChargerImpl r0 = (com.google.android.systemui.dreamliner.WirelessChargerImpl) r0
                r0.initHALInterface()
                vendor.google.wireless_charger.IWirelessCharger r9 = r0.mWirelessCharger
                if (r9 == 0) goto L_0x00ba
                r0 = 0
                r3 = 0
                vendor.google.wireless_charger.IWirelessCharger$Stub$Proxy r9 = (vendor.google.wireless_charger.IWirelessCharger.Stub.Proxy) r9     // Catch:{ Exception -> 0x004c }
                vendor.google.wireless_charger.WpcAuthDigests r9 = r9.getWpcAuthDigests(r1)     // Catch:{ Exception -> 0x004c }
                byte r1 = r9.slotPopulatedMask     // Catch:{ Exception -> 0x004c }
                byte r4 = r9.slotReturnedMask     // Catch:{ Exception -> 0x0049 }
                java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ Exception -> 0x0047 }
                byte[][] r9 = r9.digests     // Catch:{ Exception -> 0x0047 }
                java.util.List r9 = java.util.Arrays.asList(r9)     // Catch:{ Exception -> 0x0047 }
                r5.<init>(r9)     // Catch:{ Exception -> 0x0047 }
                r9 = r5
                r5 = r0
                goto L_0x006b
            L_0x0047:
                r9 = move-exception
                goto L_0x004f
            L_0x0049:
                r9 = move-exception
                r4 = r0
                goto L_0x004f
            L_0x004c:
                r9 = move-exception
                r1 = r0
                r4 = r1
            L_0x004f:
                int r5 = com.google.android.systemui.dreamliner.WirelessChargerImpl.mapError(r9)
                java.lang.StringBuilder r6 = new java.lang.StringBuilder
                java.lang.String r7 = "get wpc digests fail: "
                r6.<init>(r7)
                java.lang.String r9 = r9.getMessage()
                r6.append(r9)
                java.lang.String r9 = r6.toString()
                java.lang.String r6 = "Dreamliner-WLC_HAL"
                android.util.Log.i(r6, r9)
                r9 = r3
            L_0x006b:
                java.lang.String r6 = "GWAD() result: "
                java.lang.String r7 = "DLObserver"
                androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0.m(r6, r5, r7)
                android.os.ResultReceiver r6 = r2.mResultReceiver
                if (r5 != 0) goto L_0x00b6
                java.lang.String r3 = "GWAD() response: pm="
                java.lang.String r5 = ", rm="
                java.lang.String r8 = ", d="
                java.lang.StringBuilder r3 = androidx.compose.foundation.text.ValidatingOffsetMapping$$ExternalSyntheticOutline0.m(r3, r1, r5, r4, r8)
                r3.append(r9)
                java.lang.String r3 = r3.toString()
                android.util.Log.d(r7, r3)
                com.google.android.systemui.dreamliner.DockObserver r2 = com.google.android.systemui.dreamliner.DockObserver.this
                r2.getClass()
                android.os.Bundle r2 = new android.os.Bundle
                r2.<init>()
                java.lang.String r3 = "slot_populated_mask"
                r2.putByte(r3, r1)
                java.lang.String r1 = "slot_returned_mask"
                r2.putByte(r1, r4)
                java.util.ArrayList r1 = new java.util.ArrayList
                r1.<init>()
                if (r9 == 0) goto L_0x00ad
                com.google.android.systemui.dreamliner.DockObserver$$ExternalSyntheticLambda1 r3 = new com.google.android.systemui.dreamliner.DockObserver$$ExternalSyntheticLambda1
                r3.<init>(r1)
                r9.forEach(r3)
            L_0x00ad:
                java.lang.String r9 = "wpc_digests"
                r2.putParcelableArrayList(r9, r1)
                r6.send(r0, r2)
                goto L_0x00ba
            L_0x00b6:
                r9 = 1
                r6.send(r9, r3)
            L_0x00ba:
                return
            L_0x00bb:
                com.google.android.systemui.dreamliner.DockObserver r0 = r9.this$0
                java.util.Optional r0 = r0.mWirelessCharger
                boolean r0 = r0.isPresent()
                if (r0 == 0) goto L_0x00db
                com.google.android.systemui.dreamliner.DockObserver r0 = r9.this$0
                java.util.Optional r0 = r0.mWirelessCharger
                java.lang.Object r0 = r0.get()
                com.google.android.systemui.dreamliner.WirelessCharger r0 = (com.google.android.systemui.dreamliner.WirelessCharger) r0
                byte r1 = r9.mFanId
                com.google.android.systemui.dreamliner.DockObserver$GetFanSimpleInformationCallback r2 = new com.google.android.systemui.dreamliner.DockObserver$GetFanSimpleInformationCallback
                android.os.ResultReceiver r9 = r9.mResultReceiver
                r2.<init>(r1, r9)
                r0.getFanSimpleInformation(r1, r2)
            L_0x00db:
                return
            L_0x00dc:
                com.google.android.systemui.dreamliner.DockObserver r0 = r9.this$0
                java.util.Optional r0 = r0.mWirelessCharger
                boolean r0 = r0.isPresent()
                if (r0 == 0) goto L_0x00fc
                com.google.android.systemui.dreamliner.DockObserver r0 = r9.this$0
                java.util.Optional r0 = r0.mWirelessCharger
                java.lang.Object r0 = r0.get()
                com.google.android.systemui.dreamliner.WirelessCharger r0 = (com.google.android.systemui.dreamliner.WirelessCharger) r0
                byte r1 = r9.mFanId
                com.google.android.systemui.dreamliner.DockObserver$GetFanInformationCallback r2 = new com.google.android.systemui.dreamliner.DockObserver$GetFanInformationCallback
                android.os.ResultReceiver r9 = r9.mResultReceiver
                r2.<init>(r1, r9)
                r0.getFanInformation(r1, r2)
            L_0x00fc:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.dreamliner.DockObserver.GetFanInformation.run():void");
        }

        public GetFanInformation(DockObserver dockObserver, ResultReceiver resultReceiver, byte b) {
            this.$r8$classId = 2;
            this.this$0 = dockObserver;
            this.mResultReceiver = resultReceiver;
            this.mFanId = b;
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    final class GetFanInformationCallback implements WirelessCharger.GetFanInformationCallback {
        public final byte mFanId;
        public final ResultReceiver mResultReceiver;

        public GetFanInformationCallback(byte b, ResultReceiver resultReceiver) {
            this.mFanId = b;
            this.mResultReceiver = resultReceiver;
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    final class GetFanSimpleInformationCallback implements WirelessCharger.GetFanSimpleInformationCallback {
        public final byte mFanId;
        public final ResultReceiver mResultReceiver;

        public GetFanSimpleInformationCallback(byte b, ResultReceiver resultReceiver) {
            this.mFanId = b;
            this.mResultReceiver = resultReceiver;
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class GetFeatures implements Runnable {
        public final long mChargerId;
        public final ResultReceiver mResultReceiver;

        public GetFeatures(ResultReceiver resultReceiver, long j) {
            this.mResultReceiver = resultReceiver;
            this.mChargerId = j;
        }

        public final void run() {
            long j;
            int i;
            if (DockObserver.this.mWirelessCharger.isPresent()) {
                long j2 = this.mChargerId;
                GetFeaturesCallback getFeaturesCallback = new GetFeaturesCallback(this.mResultReceiver);
                WirelessChargerImpl wirelessChargerImpl = (WirelessChargerImpl) ((WirelessCharger) DockObserver.this.mWirelessCharger.get());
                wirelessChargerImpl.initHALInterface();
                IWirelessCharger iWirelessCharger = wirelessChargerImpl.mWirelessCharger;
                if (iWirelessCharger != null) {
                    try {
                        j = ((IWirelessCharger.Stub.Proxy) iWirelessCharger).getFeatures(j2);
                        i = 0;
                    } catch (Exception e) {
                        i = WirelessChargerImpl.mapError(e);
                        Log.i("Dreamliner-WLC_HAL", "get features fail: " + e.getMessage());
                        j = 0;
                    }
                    ExifInterface$$ExternalSyntheticOutline0.m("GF() result: ", i, "DLObserver");
                    ResultReceiver resultReceiver = getFeaturesCallback.mResultReceiver;
                    if (i == 0) {
                        Log.d("DLObserver", "GF() response: f=" + j);
                        DockObserver.this.getClass();
                        Bundle bundle = new Bundle();
                        bundle.putLong("charger_feature", j);
                        resultReceiver.send(0, bundle);
                        return;
                    }
                    resultReceiver.send(1, (Bundle) null);
                }
            }
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    final class GetFeaturesCallback {
        public final ResultReceiver mResultReceiver;

        public GetFeaturesCallback(ResultReceiver resultReceiver) {
            this.mResultReceiver = resultReceiver;
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    final class GetInformationCallback implements WirelessCharger.GetInformationCallback {
        public final ResultReceiver mResultReceiver;

        public GetInformationCallback(ResultReceiver resultReceiver) {
            this.mResultReceiver = resultReceiver;
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class GetWpcAuthCertificate implements Runnable {
        public final short mLength;
        public final short mOffset;
        public final ResultReceiver mResultReceiver;
        public final byte mSlotNum;

        public GetWpcAuthCertificate(ResultReceiver resultReceiver, byte b, short s, short s2) {
            this.mResultReceiver = resultReceiver;
            this.mSlotNum = b;
            this.mOffset = s;
            this.mLength = s2;
        }

        public final void run() {
            ArrayList arrayList;
            int i;
            if (DockObserver.this.mWirelessCharger.isPresent()) {
                byte b = this.mSlotNum;
                short s = this.mOffset;
                short s2 = this.mLength;
                GetWpcAuthCertificateCallback getWpcAuthCertificateCallback = new GetWpcAuthCertificateCallback(this.mResultReceiver);
                WirelessChargerImpl wirelessChargerImpl = (WirelessChargerImpl) ((WirelessCharger) DockObserver.this.mWirelessCharger.get());
                wirelessChargerImpl.initHALInterface();
                IWirelessCharger iWirelessCharger = wirelessChargerImpl.mWirelessCharger;
                if (iWirelessCharger != null) {
                    Bundle bundle = null;
                    try {
                        arrayList = WirelessChargerImpl.convertPrimitiveArrayToArrayList(((IWirelessCharger.Stub.Proxy) iWirelessCharger).getWpcAuthCertificate(b, (char) s, (char) s2));
                        i = 0;
                    } catch (Exception e) {
                        i = WirelessChargerImpl.mapError(e);
                        Log.i("Dreamliner-WLC_HAL", "get wpc cert fail: " + e.getMessage());
                        arrayList = null;
                    }
                    ExifInterface$$ExternalSyntheticOutline0.m("GWAC() result: ", i, "DLObserver");
                    ResultReceiver resultReceiver = getWpcAuthCertificateCallback.mResultReceiver;
                    if (i == 0) {
                        Log.d("DLObserver", "GWAC() response: c=" + arrayList);
                        DockObserver.this.getClass();
                        if (arrayList != null && !arrayList.isEmpty()) {
                            byte[] convertArrayListToPrimitiveArray = DockObserver.convertArrayListToPrimitiveArray(arrayList);
                            bundle = new Bundle();
                            bundle.putByteArray("wpc_cert", convertArrayListToPrimitiveArray);
                        }
                        resultReceiver.send(0, bundle);
                        return;
                    }
                    resultReceiver.send(1, (Bundle) null);
                }
            }
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    final class GetWpcAuthCertificateCallback {
        public final ResultReceiver mResultReceiver;

        public GetWpcAuthCertificateCallback(ResultReceiver resultReceiver) {
            this.mResultReceiver = resultReceiver;
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    final class GetWpcAuthChallengeResponseCallback {
        public final ResultReceiver mResultReceiver;

        public GetWpcAuthChallengeResponseCallback(ResultReceiver resultReceiver) {
            this.mResultReceiver = resultReceiver;
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    final class GetWpcAuthDigestsCallback {
        public final ResultReceiver mResultReceiver;

        public GetWpcAuthDigestsCallback(ResultReceiver resultReceiver) {
            this.mResultReceiver = resultReceiver;
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class IsDockPresent implements Runnable {
        public final Context context;

        public IsDockPresent(Context context2) {
            this.context = context2;
        }

        public final void run() {
            if (DockObserver.this.mWirelessCharger.isPresent()) {
                ((WirelessCharger) DockObserver.this.mWirelessCharger.get()).asyncIsDockPresent(new IsDockPresentCallback(this.context));
            }
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    final class IsDockPresentCallback implements WirelessCharger.IsDockPresentCallback {
        public final Context mContext;

        public IsDockPresentCallback(Context context) {
            this.mContext = context;
        }

        public final void onCallback(boolean z, byte b, byte b2, boolean z2, int i) {
            Log.i("DLObserver", "IDP() response: d=" + z + ", i=" + i + ", t=" + b + ", o=" + b2 + ", sgi=" + z2);
            if (z) {
                DockObserver.runOnBackgroundThread(new DockObserver$IsDockPresentCallback$$ExternalSyntheticLambda0(this, b, b2, i));
            }
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    final class KeyExchangeCallback implements WirelessCharger.KeyExchangeCallback {
        public final ResultReceiver mResultReceiver;

        public KeyExchangeCallback(ResultReceiver resultReceiver) {
            this.mResultReceiver = resultReceiver;
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class ProtectedBroadcastSender {
        public final Context mContext;

        public ProtectedBroadcastSender(Context context) {
            this.mContext = context.getApplicationContext();
        }

        public final void sendBroadcastAsUser(Intent intent, UserHandle userHandle) {
            if (this.mContext.getUser().isSystem()) {
                DockObserver.runOnBackgroundThread(new DockObserver$ProtectedBroadcastSender$$ExternalSyntheticLambda0(this, intent, userHandle));
            }
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class SetFan implements Runnable {
        public final byte mFanId;
        public final byte mFanMode;
        public final int mFanRpm;

        public SetFan(byte b, byte b2, int i) {
            this.mFanId = b;
            this.mFanMode = b2;
            this.mFanRpm = i;
        }

        public final void run() {
            if (DockObserver.this.mWirelessCharger.isPresent()) {
                ((WirelessCharger) DockObserver.this.mWirelessCharger.get()).setFan(this.mFanId, this.mFanMode, this.mFanRpm, new SetFanCallback());
            }
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    final class SetFanCallback implements WirelessCharger.SetFanCallback {
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class SetFeatures implements Runnable {
        public final long mChargerId;
        public final long mFeature;
        public final ResultReceiver mResultReceiver;

        public SetFeatures(ResultReceiver resultReceiver, long j, long j2) {
            this.mResultReceiver = resultReceiver;
            this.mChargerId = j;
            this.mFeature = j2;
        }

        public final void run() {
            int i;
            if (DockObserver.this.mWirelessCharger.isPresent()) {
                long j = this.mChargerId;
                long j2 = this.mFeature;
                WirelessChargerImpl wirelessChargerImpl = (WirelessChargerImpl) ((WirelessCharger) DockObserver.this.mWirelessCharger.get());
                wirelessChargerImpl.initHALInterface();
                IWirelessCharger iWirelessCharger = wirelessChargerImpl.mWirelessCharger;
                if (iWirelessCharger != null) {
                    try {
                        ((IWirelessCharger.Stub.Proxy) iWirelessCharger).setFeatures(j, j2);
                        i = 0;
                    } catch (Exception e) {
                        int mapError = WirelessChargerImpl.mapError(e);
                        Log.i("Dreamliner-WLC_HAL", "set features fail: " + e.getMessage());
                        i = mapError;
                    }
                    this.mResultReceiver.send(i, (Bundle) null);
                }
            }
        }
    }

    /* renamed from: -$$Nest$mstopDreamlinerService  reason: not valid java name */
    public static void m1709$$Nest$mstopDreamlinerService(DockObserver dockObserver, Context context) {
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
    public DockObserver(Context context, Optional optional, StatusBarStateController statusBarStateController, Lazy lazy, ConfigurationController configurationController, DelayableExecutor delayableExecutor, KeyguardStateController keyguardStateController, DockAlignmentController dockAlignmentController, UserTracker userTracker) {
        DockObserverBroadcastReceiver dockObserverBroadcastReceiver = new DockObserverBroadcastReceiver();
        this.mDockObserverBroadcastReceiver = dockObserverBroadcastReceiver;
        this.mDockState = 0;
        this.mLastAlignState = -1;
        this.mFanLevel = -1;
        this.mUserChangedCallback = new UserTracker.Callback() {
            public final void onUserChanged(int i, Context context) {
                DockObserver dockObserver = DockObserver.this;
                DockObserver.m1709$$Nest$mstopDreamlinerService(dockObserver, dockObserver.mContext);
                dockObserver.updateCurrentDockingStatus(dockObserver.mContext);
            }
        };
        this.mInterruptSuppressor = new Object();
        Set.of(VisualInterruptionType.PEEK, VisualInterruptionType.PULSE, VisualInterruptionType.BUBBLE);
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
        this.mStatusBarStateController = statusBarStateController;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.BATTERY_CHANGED");
        intentFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        intentFilter.addAction("android.intent.action.BOOT_COMPLETED");
        intentFilter.addAction(ACTION_REBIND_DOCK_SERVICE);
        intentFilter.addAction("com.google.android.systemui.dreamliner.ACTION_GET_FEATURES");
        intentFilter.addAction("com.google.android.systemui.dreamliner.ACTION_SET_FEATURES");
        intentFilter.setPriority(1000);
        context.registerReceiver(dockObserverBroadcastReceiver, intentFilter, PERMISSION_WIRELESS_CHARGER_STATUS, (Handler) null, 2);
        this.mDockAlignmentController = dockAlignmentController;
        this.mConfigurationController = configurationController;
        refreshFanLevel((DockObserver$$ExternalSyntheticLambda3) null);
    }

    public static boolean assertNotNull(DockGestureController dockGestureController) {
        if (dockGestureController != null) {
            return true;
        }
        Log.w("DLObserver", "DockGestureController is null");
        return false;
    }

    public static byte[] convertArrayListToPrimitiveArray(ArrayList arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            return null;
        }
        int size = arrayList.size();
        byte[] bArr = new byte[size];
        for (int i = 0; i < size; i++) {
            bArr[i] = ((Byte) arrayList.get(i)).byteValue();
        }
        return bArr;
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
        ExifInterface$$ExternalSyntheticOutline0.m("plugged=", intExtra, "DLObserver");
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

    public static void runOnBackgroundThread(Runnable runnable) {
        if (sSingleThreadExecutor == null) {
            sSingleThreadExecutor = Executors.newSingleThreadExecutor();
        }
        sSingleThreadExecutor.execute(runnable);
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
        ((ExecutorImpl) this.mMainExecutor).execute(new DockObserver$$ExternalSyntheticLambda0(3, this, dockEventListener));
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

    public final void notifyDreamlinerLatestFanLevel() {
        Log.d("DLObserver", "notify l=" + this.mFanLevel + ", isDocked=" + isDocked());
        if (isDocked()) {
            this.mProtectedBroadcastSender.sendBroadcastAsUser(new Intent("com.google.android.systemui.dreamliner.ACTION_UPDATE_FAN_LEVEL").putExtra("fan_level", this.mFanLevel).addFlags(1073741824), UserHandle.ALL);
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
                notifyDreamlinerLatestFanLevel();
            }
        }
    }

    public final void refreshFanLevel(DockObserver$$ExternalSyntheticLambda3 dockObserver$$ExternalSyntheticLambda3) {
        Log.d("DLObserver", "command=2");
        runOnBackgroundThread(new DockObserver$$ExternalSyntheticLambda0(1, this, dockObserver$$ExternalSyntheticLambda3));
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
            this.mMainExecutor.executeDelayed(this.mPhotoAction, Duration.ofSeconds(3).toMillis());
        }
    }

    public final void tryToStartDreamlinerServiceIfNeeded(Context context, boolean z) {
        if (this.mWirelessCharger.isEmpty()) {
            Log.e("DLObserver", "No WirelessCharger HAL");
            return;
        }
        UserManager userManager = (UserManager) context.getSystemService(UserManager.class);
        if (userManager.isManagedProfile() && !userManager.isSystemUser()) {
            Log.i("DLObserver", "do not bind Dreamliner service for work profile");
        } else if (z) {
            runOnBackgroundThread(new IsDockPresent(context));
        }
    }

    public final void updateCurrentDockingStatus(Context context) {
        notifyForceEnabledAmbientDisplay(false);
        tryToStartDreamlinerServiceIfNeeded(context, isWirelessCharging(context, (Intent) null));
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    final class DreamlinerServiceConn implements ServiceConnection {
        public final Context mContext;

        public DreamlinerServiceConn(Context context) {
            this.mContext = context;
        }

        public final void onBindingDied(ComponentName componentName) {
            DockObserver.m1709$$Nest$mstopDreamlinerService(DockObserver.this, this.mContext);
            DockObserver.sIsDockingUiShowing = false;
            boolean isWirelessCharging = DockObserver.isWirelessCharging(this.mContext, (Intent) null);
            MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0.m("onBindingDied, isWirelessCharging:", isWirelessCharging, "DLObserver");
            if (isWirelessCharging) {
                DockObserver.this.updateCurrentDockingStatus(this.mContext);
            }
        }

        public final void onServiceDisconnected(ComponentName componentName) {
            DockObserver dockObserver = DockObserver.this;
            dockObserver.getClass();
            Log.d("DLObserver", "sendDockActiveIntent()");
            ProtectedBroadcastSender protectedBroadcastSender = dockObserver.mProtectedBroadcastSender;
            Intent addFlags = new Intent("android.intent.action.DOCK_ACTIVE").addFlags(1073741824);
            if (protectedBroadcastSender.mContext.getUser().isSystem()) {
                DockObserver.runOnBackgroundThread(new DockObserver$$ExternalSyntheticLambda0(4, protectedBroadcastSender, addFlags));
            }
        }

        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        }
    }
}
