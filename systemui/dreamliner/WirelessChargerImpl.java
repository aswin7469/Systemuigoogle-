package com.google.android.systemui.dreamliner;

import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.ResultReceiver;
import android.os.ServiceManager;
import android.os.ServiceSpecificException;
import android.util.Log;
import androidx.compose.ui.text.input.RecordingInputConnection$$ExternalSyntheticOutline0;
import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0;
import com.android.keyguard.ClockEventController$zenModeCallback$1$$ExternalSyntheticOutline0;
import com.android.settingslib.bluetooth.LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0;
import com.android.systemui.statusbar.KeyguardIndicationController;
import com.android.systemui.statusbar.KeyguardIndicationController$$ExternalSyntheticLambda7;
import com.android.systemui.statusbar.KeyguardIndicationController$$ExternalSyntheticLambda8;
import com.google.android.systemui.dreamliner.DockObserver;
import com.google.android.systemui.dreamliner.WirelessCharger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import vendor.google.wireless_charger.AlignInfo;
import vendor.google.wireless_charger.DockInfo;
import vendor.google.wireless_charger.DockPresent;
import vendor.google.wireless_charger.FanInfo;
import vendor.google.wireless_charger.IWirelessCharger;
import vendor.google.wireless_charger.IWirelessChargerInfoCallback;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class WirelessChargerImpl extends WirelessCharger implements IBinder.DeathRecipient {
    public static final /* synthetic */ int $r8$clinit = 0;
    public static final long MAX_POLLING_TIMEOUT_NS = TimeUnit.SECONDS.toNanos(5);
    public WirelessCharger.IsDockPresentCallback mCallback = null;
    public final Handler mHandler = new Handler(Looper.getMainLooper());
    public long mPollingStartedTimeNs;
    public final AnonymousClass1 mRunnable = new Runnable() {
        public final void run() {
            WirelessChargerImpl wirelessChargerImpl = WirelessChargerImpl.this;
            int i = WirelessChargerImpl.$r8$clinit;
            wirelessChargerImpl.initHALInterface();
            IWirelessCharger iWirelessCharger = wirelessChargerImpl.mWirelessCharger;
            if (iWirelessCharger != null) {
                try {
                    DockPresent isDockPresent = ((IWirelessCharger.Stub.Proxy) iWirelessCharger).isDockPresent();
                    if (System.nanoTime() >= wirelessChargerImpl.mPollingStartedTimeNs + WirelessChargerImpl.MAX_POLLING_TIMEOUT_NS || isDockPresent.id != 0) {
                        WirelessCharger.IsDockPresentCallback isDockPresentCallback = wirelessChargerImpl.mCallback;
                        if (isDockPresentCallback != null) {
                            ((DockObserver.IsDockPresentCallback) isDockPresentCallback).onCallback(isDockPresent.docked, isDockPresent.type, isDockPresent.orientation, isDockPresent.isGetInfoSupported, isDockPresent.id);
                            wirelessChargerImpl.mCallback = null;
                            return;
                        }
                        return;
                    }
                    wirelessChargerImpl.mHandler.postDelayed(wirelessChargerImpl.mRunnable, 100);
                } catch (Exception e) {
                    Log.i("Dreamliner-WLC_HAL", "isDockPresent fail: " + e.getMessage());
                }
            }
        }
    };
    public IWirelessCharger mWirelessCharger;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class WirelessChargerInfoCallback extends IWirelessChargerInfoCallback.Stub {
        public final WirelessCharger.AlignInfoListener mListener;

        public WirelessChargerInfoCallback(WirelessCharger.AlignInfoListener alignInfoListener) {
            markVintfStability();
            attachInterface(this, IWirelessChargerInfoCallback.DESCRIPTOR);
            this.mListener = alignInfoListener;
        }

        public final void alignInfoChanged(AlignInfo alignInfo) {
            int i;
            WirelessCharger.AlignInfoListener alignInfoListener = this.mListener;
            int intValue = Byte.valueOf(alignInfo.alignState).intValue();
            int intValue2 = Byte.valueOf(alignInfo.alignPct).intValue();
            DockAlignmentController dockAlignmentController = ((DockAlignmentController$$ExternalSyntheticLambda0) alignInfoListener).f$0;
            int i2 = dockAlignmentController.mAlignmentState;
            boolean z = DockAlignmentController.DEBUG;
            if (z) {
                ExifInterface$$ExternalSyntheticOutline0.m("onAlignInfo, state: ", intValue, ", alignPct: ", intValue2, "DockAlignmentController");
            }
            if (intValue != 0) {
                i = 2;
                if (intValue != 1) {
                    if (intValue != 2) {
                        if (intValue != 3) {
                            RecordingInputConnection$$ExternalSyntheticOutline0.m("Unexpected state: ", intValue, "DockAlignmentController");
                        }
                    } else if (intValue2 >= 0) {
                        if (intValue2 < 100) {
                            i = 1;
                        } else {
                            i = 0;
                        }
                    }
                    i = -1;
                }
            } else {
                i = i2;
            }
            dockAlignmentController.mAlignmentState = i;
            if (i2 != i) {
                Iterator it = dockAlignmentController.mDockAlignmentStateChangeListeners.iterator();
                while (it.hasNext()) {
                    DockObserver dockObserver = ((DockObserver$$ExternalSyntheticLambda2) it.next()).f$0;
                    dockObserver.getClass();
                    Log.d("DLObserver", "onAlignStateChanged alignState = " + i);
                    dockObserver.mLastAlignState = i;
                    for (KeyguardIndicationController$$ExternalSyntheticLambda7 keyguardIndicationController$$ExternalSyntheticLambda7 : dockObserver.mAlignmentStateListeners) {
                        KeyguardIndicationController keyguardIndicationController = keyguardIndicationController$$ExternalSyntheticLambda7.f$0;
                        keyguardIndicationController.mHandler.post(new KeyguardIndicationController$$ExternalSyntheticLambda8(keyguardIndicationController, i));
                    }
                    dockObserver.runPhotoAction();
                    dockObserver.notifyDreamlinerAlignStateChanged(i);
                    dockObserver.refreshFanLevel(new DockObserver$$ExternalSyntheticLambda3(0, dockObserver));
                }
                if (z) {
                    LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0.m(new StringBuilder("onAlignStateChanged, state: "), dockAlignmentController.mAlignmentState, "DockAlignmentController");
                }
            }
        }
    }

    public static Bundle convertFanDetailedInfo(byte b, FanInfo fanInfo) {
        Bundle bundle = new Bundle();
        bundle.putByte("fan_id", b);
        bundle.putByte("fan_mode", fanInfo.fanMode);
        bundle.putInt("fan_current_rpm", fanInfo.currentRpm);
        bundle.putInt("fan_min_rpm", fanInfo.minimumRpm);
        bundle.putInt("fan_max_rpm", fanInfo.maximumRpm);
        bundle.putByte("fan_type", fanInfo.type);
        bundle.putByte("fan_count", fanInfo.count);
        return bundle;
    }

    public static ArrayList convertPrimitiveArrayToArrayList(byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (byte valueOf : bArr) {
            arrayList.add(Byte.valueOf(valueOf));
        }
        return arrayList;
    }

    public static int mapError(Exception exc) {
        if (!(exc instanceof ServiceSpecificException)) {
            return 2;
        }
        int i = ((ServiceSpecificException) exc).errorCode;
        if (i >= 0) {
            return i;
        }
        ClockEventController$zenModeCallback$1$$ExternalSyntheticOutline0.m("Got a ServiceSpecificExepction but failed to map erroCode: ", i, "Dreamliner-WLC_HAL");
        return 2;
    }

    public void asyncIsDockPresent(WirelessCharger.IsDockPresentCallback isDockPresentCallback) {
        initHALInterface();
        if (this.mWirelessCharger != null) {
            this.mPollingStartedTimeNs = System.nanoTime();
            this.mCallback = isDockPresentCallback;
            this.mHandler.removeCallbacks(this.mRunnable);
            this.mHandler.postDelayed(this.mRunnable, 100);
        }
    }

    public final void binderDied() {
        Log.i("Dreamliner-WLC_HAL", "serviceDied");
        this.mWirelessCharger = null;
    }

    public void challenge(byte b, byte[] bArr, WirelessCharger.ChallengeCallback challengeCallback) {
        int i;
        ArrayList arrayList;
        initHALInterface();
        IWirelessCharger iWirelessCharger = this.mWirelessCharger;
        if (iWirelessCharger != null) {
            Bundle bundle = null;
            try {
                arrayList = convertPrimitiveArrayToArrayList(((IWirelessCharger.Stub.Proxy) iWirelessCharger).challenge(b, bArr));
                i = 0;
            } catch (Exception e) {
                i = mapError(e);
                Log.i("Dreamliner-WLC_HAL", "challenge fail: " + e.getMessage());
                arrayList = null;
            }
            DockObserver.ChallengeCallback challengeCallback2 = (DockObserver.ChallengeCallback) challengeCallback;
            challengeCallback2.getClass();
            Log.d("DLObserver", "C() Result: " + i);
            ResultReceiver resultReceiver = challengeCallback2.mResultReceiver;
            if (i == 0) {
                Log.d("DLObserver", "C() response: " + arrayList);
                DockObserver.this.getClass();
                if (arrayList != null && !arrayList.isEmpty()) {
                    byte[] convertArrayListToPrimitiveArray = DockObserver.convertArrayListToPrimitiveArray(arrayList);
                    bundle = new Bundle();
                    bundle.putByteArray("challenge_response", convertArrayListToPrimitiveArray);
                }
                resultReceiver.send(0, bundle);
                return;
            }
            resultReceiver.send(1, (Bundle) null);
        }
    }

    public void getFanInformation(byte b, WirelessCharger.GetFanInformationCallback getFanInformationCallback) {
        int i;
        Bundle bundle;
        initHALInterface();
        Log.d("Dreamliner-WLC_HAL", "command=0");
        IWirelessCharger iWirelessCharger = this.mWirelessCharger;
        if (iWirelessCharger != null) {
            try {
                FanInfo fanInfo = ((IWirelessCharger.Stub.Proxy) iWirelessCharger).getFanInfo(b);
                Log.d("Dreamliner-WLC_HAL", "command=0, i=" + b + ", m=" + fanInfo.fanMode + ", cr=" + fanInfo.currentRpm + ", mir=" + fanInfo.minimumRpm + ", mxr=" + fanInfo.maximumRpm + ", t=" + fanInfo.type + ", c=" + fanInfo.count);
                bundle = convertFanDetailedInfo(b, fanInfo);
                i = 0;
            } catch (Exception e) {
                i = mapError(e);
                Log.i("Dreamliner-WLC_HAL", "command=0 fail: " + e.getMessage());
                bundle = null;
            }
            DockObserver.GetFanInformationCallback getFanInformationCallback2 = (DockObserver.GetFanInformationCallback) getFanInformationCallback;
            getFanInformationCallback2.getClass();
            StringBuilder sb = new StringBuilder("Callback of command=0, result=");
            sb.append(i);
            sb.append(", i=");
            LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0.m(sb, getFanInformationCallback2.mFanId, "DLObserver");
            ResultReceiver resultReceiver = getFanInformationCallback2.mResultReceiver;
            if (i == 0) {
                Log.d("DLObserver", "Callback of command=0, i=" + bundle.getByte("fan_id", (byte) -1) + ", m=" + bundle.getByte("fan_mode", (byte) -1) + ", cr=" + bundle.getInt("fan_current_rpm", -1) + ", mir=" + bundle.getInt("fan_min_rpm", -1) + ", mxr=" + bundle.getInt("fan_max_rpm", -1) + ", t=" + bundle.getByte("fan_type", (byte) -1) + ", c=" + bundle.getByte("fan_count", (byte) -1));
                resultReceiver.send(0, bundle);
                return;
            }
            resultReceiver.send(1, (Bundle) null);
        }
    }

    public void getFanSimpleInformation(byte b, WirelessCharger.GetFanSimpleInformationCallback getFanSimpleInformationCallback) {
        int i;
        Bundle bundle;
        initHALInterface();
        Log.d("Dreamliner-WLC_HAL", "command=3");
        IWirelessCharger iWirelessCharger = this.mWirelessCharger;
        if (iWirelessCharger != null) {
            try {
                FanInfo fanInfo = ((IWirelessCharger.Stub.Proxy) iWirelessCharger).getFanInfo(b);
                bundle = new Bundle();
                bundle.putByte("fan_id", b);
                bundle.putByte("fan_mode", fanInfo.fanMode);
                bundle.putInt("fan_current_rpm", fanInfo.currentRpm);
                i = 0;
            } catch (Exception e) {
                i = mapError(e);
                Log.i("Dreamliner-WLC_HAL", "command=3 fail: " + e.getMessage());
                bundle = null;
            }
            DockObserver.GetFanSimpleInformationCallback getFanSimpleInformationCallback2 = (DockObserver.GetFanSimpleInformationCallback) getFanSimpleInformationCallback;
            getFanSimpleInformationCallback2.getClass();
            StringBuilder sb = new StringBuilder("Callback of command=3, result=");
            sb.append(i);
            sb.append(", i=");
            LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0.m(sb, getFanSimpleInformationCallback2.mFanId, "DLObserver");
            ResultReceiver resultReceiver = getFanSimpleInformationCallback2.mResultReceiver;
            if (i == 0) {
                Log.d("DLObserver", "Callback of command=3, i=" + bundle.getByte("fan_id", (byte) -1) + ", m=" + bundle.getByte("fan_mode", (byte) -1) + ", cr=" + bundle.getInt("fan_current_rpm", -1));
                resultReceiver.send(0, bundle);
                return;
            }
            resultReceiver.send(1, (Bundle) null);
        }
    }

    /* JADX WARNING: type inference failed for: r2v8, types: [java.lang.Object, com.google.android.systemui.dreamliner.DockInfo] */
    public void getInformation(WirelessCharger.GetInformationCallback getInformationCallback) {
        DockInfo dockInfo;
        int i;
        initHALInterface();
        IWirelessCharger iWirelessCharger = this.mWirelessCharger;
        if (iWirelessCharger != null) {
            try {
                DockInfo information = ((IWirelessCharger.Stub.Proxy) iWirelessCharger).getInformation();
                String str = information.manufacturer;
                String str2 = information.model;
                String str3 = information.serial;
                int intValue = Byte.valueOf(information.type).intValue();
                ? obj = new Object();
                obj.manufacturer = str;
                obj.model = str2;
                obj.serialNumber = str3;
                obj.accessoryType = intValue;
                dockInfo = obj;
                i = 0;
            } catch (Exception e) {
                i = mapError(e);
                Log.i("Dreamliner-WLC_HAL", "getInformation fail: " + e.getMessage());
                dockInfo = null;
            }
            DockObserver.GetInformationCallback getInformationCallback2 = (DockObserver.GetInformationCallback) getInformationCallback;
            getInformationCallback2.getClass();
            Log.d("DLObserver", "GI() Result: " + i);
            ResultReceiver resultReceiver = getInformationCallback2.mResultReceiver;
            if (i == 0) {
                Log.d("DLObserver", "GI() response: di=" + dockInfo.toString());
                Bundle bundle = new Bundle();
                bundle.putString("manufacturer", dockInfo.manufacturer);
                bundle.putString("model", dockInfo.model);
                bundle.putString("serialNumber", dockInfo.serialNumber);
                bundle.putInt("accessoryType", dockInfo.accessoryType);
                resultReceiver.send(0, bundle);
            } else if (i != 1) {
                resultReceiver.send(1, (Bundle) null);
            }
        }
    }

    /* JADX WARNING: type inference failed for: r3v5, types: [java.lang.Object, vendor.google.wireless_charger.IWirelessCharger$Stub$Proxy] */
    public final boolean initHALInterface() {
        IWirelessCharger iWirelessCharger;
        if (this.mWirelessCharger == null) {
            try {
                IBinder service = ServiceManager.getService("vendor.google.wireless_charger.IWirelessCharger/default");
                if (service != null) {
                    int i = IWirelessCharger.Stub.$r8$clinit;
                    IInterface queryLocalInterface = service.queryLocalInterface(IWirelessCharger.DESCRIPTOR);
                    if (queryLocalInterface == null || !(queryLocalInterface instanceof IWirelessCharger)) {
                        ? obj = new Object();
                        obj.mRemote = service;
                        iWirelessCharger = obj;
                    } else {
                        iWirelessCharger = (IWirelessCharger) queryLocalInterface;
                    }
                    this.mWirelessCharger = iWirelessCharger;
                    service.linkToDeath(this, 0);
                    Log.i("Dreamliner-WLC_HAL", "mWirelessCharger service connected!!!!");
                }
            } catch (Exception e) {
                Log.i("Dreamliner-WLC_HAL", "no wireless charger hal found: " + e.getMessage());
                this.mWirelessCharger = null;
            }
        }
        if (this.mWirelessCharger != null) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0055  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x008b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void keyExchange(byte[] r7, com.google.android.systemui.dreamliner.WirelessCharger.KeyExchangeCallback r8) {
        /*
            r6 = this;
            r6.initHALInterface()
            vendor.google.wireless_charger.IWirelessCharger r6 = r6.mWirelessCharger
            if (r6 == 0) goto L_0x008f
            r0 = 0
            r1 = 0
            vendor.google.wireless_charger.IWirelessCharger$Stub$Proxy r6 = (vendor.google.wireless_charger.IWirelessCharger.Stub.Proxy) r6     // Catch:{ Exception -> 0x001b }
            vendor.google.wireless_charger.KeyExchangeResponse r6 = r6.keyExchange(r7)     // Catch:{ Exception -> 0x001b }
            byte[] r7 = r6.dockPublicKey     // Catch:{ Exception -> 0x001b }
            java.util.ArrayList r7 = convertPrimitiveArrayToArrayList(r7)     // Catch:{ Exception -> 0x001b }
            byte r6 = r6.dockIdentifier     // Catch:{ Exception -> 0x0019 }
            r2 = r0
            goto L_0x0039
        L_0x0019:
            r6 = move-exception
            goto L_0x001d
        L_0x001b:
            r6 = move-exception
            r7 = r1
        L_0x001d:
            int r2 = mapError(r6)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "keyExchange fail: "
            r3.<init>(r4)
            java.lang.String r6 = r6.getMessage()
            r3.append(r6)
            java.lang.String r6 = r3.toString()
            java.lang.String r3 = "Dreamliner-WLC_HAL"
            android.util.Log.i(r3, r6)
            r6 = -1
        L_0x0039:
            com.google.android.systemui.dreamliner.DockObserver$KeyExchangeCallback r8 = (com.google.android.systemui.dreamliner.DockObserver.KeyExchangeCallback) r8
            r8.getClass()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "KE() Result: "
            r3.<init>(r4)
            r3.append(r2)
            java.lang.String r3 = r3.toString()
            java.lang.String r4 = "DLObserver"
            android.util.Log.d(r4, r3)
            android.os.ResultReceiver r3 = r8.mResultReceiver
            if (r2 != 0) goto L_0x008b
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r5 = "KE() response: pk="
            r2.<init>(r5)
            r2.append(r7)
            java.lang.String r2 = r2.toString()
            android.util.Log.d(r4, r2)
            com.google.android.systemui.dreamliner.DockObserver r8 = com.google.android.systemui.dreamliner.DockObserver.this
            r8.getClass()
            if (r7 == 0) goto L_0x0087
            boolean r8 = r7.isEmpty()
            if (r8 == 0) goto L_0x0074
            goto L_0x0087
        L_0x0074:
            byte[] r7 = com.google.android.systemui.dreamliner.DockObserver.convertArrayListToPrimitiveArray(r7)
            android.os.Bundle r1 = new android.os.Bundle
            r1.<init>()
            java.lang.String r8 = "dock_id"
            r1.putByte(r8, r6)
            java.lang.String r6 = "dock_public_key"
            r1.putByteArray(r6, r7)
        L_0x0087:
            r3.send(r0, r1)
            goto L_0x008f
        L_0x008b:
            r6 = 1
            r3.send(r6, r1)
        L_0x008f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.dreamliner.WirelessChargerImpl.keyExchange(byte[], com.google.android.systemui.dreamliner.WirelessCharger$KeyExchangeCallback):void");
    }

    public void registerAlignInfo(WirelessCharger.AlignInfoListener alignInfoListener) {
        initHALInterface();
        IWirelessCharger iWirelessCharger = this.mWirelessCharger;
        if (iWirelessCharger != null) {
            try {
                ((IWirelessCharger.Stub.Proxy) iWirelessCharger).registerCallback(new WirelessChargerInfoCallback(alignInfoListener));
            } catch (Exception e) {
                Log.i("Dreamliner-WLC_HAL", "register alignInfo callback fail: " + e.getMessage());
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0096  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setFan(byte r10, byte r11, int r12, com.google.android.systemui.dreamliner.WirelessCharger.SetFanCallback r13) {
        /*
            r9 = this;
            java.lang.String r0 = "fan_current_rpm"
            java.lang.String r1 = "fan_mode"
            java.lang.String r2 = "fan_id"
            java.lang.String r3 = "command=1 spending time: "
            r9.initHALInterface()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "command=1, i="
            r4.<init>(r5)
            r4.append(r10)
            java.lang.String r5 = ", m="
            r4.append(r5)
            r4.append(r11)
            java.lang.String r6 = ", r="
            r4.append(r6)
            r4.append(r12)
            java.lang.String r4 = r4.toString()
            java.lang.String r6 = "Dreamliner-WLC_HAL"
            android.util.Log.d(r6, r4)
            vendor.google.wireless_charger.IWirelessCharger r4 = r9.mWirelessCharger
            if (r4 == 0) goto L_0x00c2
            r4 = 0
            long r7 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x006b }
            vendor.google.wireless_charger.IWirelessCharger r9 = r9.mWirelessCharger     // Catch:{ Exception -> 0x006b }
            char r12 = (char) r12     // Catch:{ Exception -> 0x006b }
            vendor.google.wireless_charger.IWirelessCharger$Stub$Proxy r9 = (vendor.google.wireless_charger.IWirelessCharger.Stub.Proxy) r9     // Catch:{ Exception -> 0x006b }
            vendor.google.wireless_charger.FanInfo r9 = r9.setFan(r10, r11, r12)     // Catch:{ Exception -> 0x006b }
            android.os.Bundle r11 = new android.os.Bundle     // Catch:{ Exception -> 0x006b }
            r11.<init>()     // Catch:{ Exception -> 0x006b }
            r11.putByte(r2, r10)     // Catch:{ Exception -> 0x006b }
            byte r10 = r9.fanMode     // Catch:{ Exception -> 0x006b }
            r11.putByte(r1, r10)     // Catch:{ Exception -> 0x006b }
            char r9 = r9.currentRpm     // Catch:{ Exception -> 0x006b }
            r11.putInt(r0, r9)     // Catch:{ Exception -> 0x006b }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0068 }
            r9.<init>(r3)     // Catch:{ Exception -> 0x0068 }
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x0068 }
            long r3 = r3 - r7
            r9.append(r3)     // Catch:{ Exception -> 0x0068 }
            java.lang.String r9 = r9.toString()     // Catch:{ Exception -> 0x0068 }
            android.util.Log.d(r6, r9)     // Catch:{ Exception -> 0x0068 }
            r9 = 0
            goto L_0x0087
        L_0x0068:
            r9 = move-exception
            r4 = r11
            goto L_0x006c
        L_0x006b:
            r9 = move-exception
        L_0x006c:
            int r10 = mapError(r9)
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            java.lang.String r12 = "command=1 fail: "
            r11.<init>(r12)
            java.lang.String r9 = r9.getMessage()
            r11.append(r9)
            java.lang.String r9 = r11.toString()
            android.util.Log.i(r6, r9)
            r9 = r10
            r11 = r4
        L_0x0087:
            com.google.android.systemui.dreamliner.DockObserver$SetFanCallback r13 = (com.google.android.systemui.dreamliner.DockObserver.SetFanCallback) r13
            r13.getClass()
            java.lang.String r10 = "DLObserver"
            if (r11 != 0) goto L_0x0096
            java.lang.String r11 = "null fanInfo for SetFanCallback. result="
            androidx.compose.ui.text.input.RecordingInputConnection$$ExternalSyntheticOutline0.m(r11, r9, r10)
            goto L_0x00c2
        L_0x0096:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r12 = "Callback of command=1, i="
            r9.<init>(r12)
            r12 = -1
            java.lang.Byte r13 = r11.getByte(r2, r12)
            r9.append(r13)
            r9.append(r5)
            java.lang.Byte r13 = r11.getByte(r1, r12)
            r9.append(r13)
            java.lang.String r13 = ", cr="
            r9.append(r13)
            int r11 = r11.getInt(r0, r12)
            r9.append(r11)
            java.lang.String r9 = r9.toString()
            android.util.Log.d(r10, r9)
        L_0x00c2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.dreamliner.WirelessChargerImpl.setFan(byte, byte, int, com.google.android.systemui.dreamliner.WirelessCharger$SetFanCallback):void");
    }
}
