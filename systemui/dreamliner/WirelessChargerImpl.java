package com.google.android.systemui.dreamliner;

import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.os.Process;
import android.os.ResultReceiver;
import android.os.ServiceManager;
import android.os.ServiceSpecificException;
import android.util.Log;
import androidx.compose.foundation.text.input.internal.RecordingInputConnection$$ExternalSyntheticOutline0;
import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0;
import com.android.keyguard.ClockEventController$zenModeCallback$1$$ExternalSyntheticOutline0;
import com.android.settingslib.bluetooth.LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0;
import com.android.systemui.statusbar.KeyguardIndicationController;
import com.android.systemui.statusbar.KeyguardIndicationController$$ExternalSyntheticLambda12;
import com.android.systemui.statusbar.KeyguardIndicationController$$ExternalSyntheticLambda13;
import com.google.android.systemui.dreamliner.WirelessCharger;
import com.google.android.systemui.dreamliner.WirelessChargerCommander$doChallenge$1;
import com.google.android.systemui.dreamliner.WirelessChargerCommander$getFanInfo$1;
import com.google.android.systemui.dreamliner.WirelessChargerCommander$setFan$1;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.collections.CollectionsKt;
import vendor.google.wireless_charger.AlignInfo;
import vendor.google.wireless_charger.DockInfo;
import vendor.google.wireless_charger.FanInfo;
import vendor.google.wireless_charger.IWirelessCharger;
import vendor.google.wireless_charger.IWirelessChargerFanLevelChangedCallback;
import vendor.google.wireless_charger.IWirelessChargerInfoCallback;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public class WirelessChargerImpl extends WirelessCharger implements IBinder.DeathRecipient {
    public static final /* synthetic */ int $r8$clinit = 0;
    public static final long MAX_POLLING_TIMEOUT_NS = TimeUnit.SECONDS.toNanos(5);
    public WirelessCharger.IsDockPresentCallback mCallback = null;
    public final Context mContext;
    public final CopyOnWriteArraySet mFanLevelEventListeners = new CopyOnWriteArraySet();
    public final Handler mHandler = new Handler(Looper.getMainLooper());
    public final AnonymousClass1 mIWirelessChargerFanLevelChangedCallback = new IWirelessChargerFanLevelChangedCallback() {
        public int mPreviousFanLevel = -1;

        {
            markVintfStability();
            attachInterface(this, IWirelessChargerFanLevelChangedCallback.DESCRIPTOR);
        }

        public final void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            printWriter.printf("fan level callback in [%d]%s\n", new Object[]{Integer.valueOf(Process.myPid()), WirelessChargerImpl.this.mContext.getPackageName()});
        }

        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            String str = IWirelessChargerFanLevelChangedCallback.DESCRIPTOR;
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(str);
            }
            if (i == 1598968902) {
                parcel2.writeString(str);
                return true;
            } else if (i == 16777215) {
                parcel2.writeNoException();
                parcel2.writeInt(1);
                return true;
            } else if (i == 16777214) {
                parcel2.writeNoException();
                parcel2.writeString("2d5029583960b40ada539137df45bfa46a5b1333");
                return true;
            } else if (i != 1) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                int readInt = parcel.readInt();
                parcel.enforceNoDataAvail();
                Log.i("Dreamliner-WLC_HAL", "fan level changed: " + this.mPreviousFanLevel + " > " + readInt);
                this.mPreviousFanLevel = readInt;
                WirelessChargerImpl.this.mFanLevelEventListeners.forEach(new WirelessChargerImpl$1$$ExternalSyntheticLambda0(readInt));
                return true;
            }
        }

        public final IBinder asBinder() {
            return this;
        }
    };
    public final AtomicBoolean mIsFanLevelCallbackRegistered = new AtomicBoolean(false);
    public long mPollingStartedTimeNs;
    public final WirelessChargerImpl$$ExternalSyntheticLambda0 mRunnable = new WirelessChargerImpl$$ExternalSyntheticLambda0(this);
    public IWirelessCharger mWirelessCharger;

    public WirelessChargerImpl(Context context) {
        this.mContext = context;
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
        ClockEventController$zenModeCallback$1$$ExternalSyntheticOutline0.m("Got a ServiceSpecificExepction but failed to map erroCode: ", "Dreamliner-WLC_HAL", i);
        return 2;
    }

    public void asyncIsDockPresent(WirelessCharger.IsDockPresentCallback isDockPresentCallback) {
        if (initHALInterface()) {
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
        if (initHALInterface()) {
            Bundle bundle = null;
            try {
                arrayList = convertPrimitiveArrayToArrayList(((IWirelessCharger.Stub.Proxy) this.mWirelessCharger).challenge(b, bArr));
                i = 0;
            } catch (Exception e) {
                i = mapError(e);
                Log.i("Dreamliner-WLC_HAL", "challenge fail: " + e.getMessage());
                arrayList = null;
            }
            WirelessChargerCommander$doChallenge$1.AnonymousClass1 r7 = (WirelessChargerCommander$doChallenge$1.AnonymousClass1) challengeCallback;
            r7.getClass();
            Log.d("WirelessChargerCommander", "C() result: " + i);
            ResultReceiver resultReceiver = resultReceiver;
            if (i != 0 || arrayList == null) {
                resultReceiver.send(i, (Bundle) null);
                return;
            }
            Log.d("WirelessChargerCommander", "C() response: " + arrayList);
            wirelessChargerCommander.getClass();
            if (!arrayList.isEmpty()) {
                bundle = new Bundle();
                bundle.putByteArray("challenge_response", CollectionsKt.toByteArray(arrayList));
            }
            resultReceiver.send(0, bundle);
        }
    }

    public void getFanInformation(byte b, WirelessCharger.GetFanInformationCallback getFanInformationCallback) {
        int i;
        Bundle bundle;
        byte b2 = b;
        if (initHALInterface()) {
            Log.d("Dreamliner-WLC_HAL", "command=0");
            try {
                FanInfo fanInfo = ((IWirelessCharger.Stub.Proxy) this.mWirelessCharger).getFanInfo(b2);
                Log.d("Dreamliner-WLC_HAL", "command=0, i=" + b2 + ", m=" + fanInfo.fanMode + ", cr=" + fanInfo.currentRpm + ", mir=" + fanInfo.minimumRpm + ", mxr=" + fanInfo.maximumRpm + ", t=" + fanInfo.type + ", c=" + fanInfo.count);
                bundle = convertFanDetailedInfo(b2, fanInfo);
                i = 0;
            } catch (Exception e) {
                i = mapError(e);
                Log.i("Dreamliner-WLC_HAL", "command=0 fail: " + e.getMessage());
                bundle = null;
            }
            WirelessChargerCommander$getFanInfo$1.AnonymousClass1 r7 = (WirelessChargerCommander$getFanInfo$1.AnonymousClass1) getFanInformationCallback;
            r7.getClass();
            StringBuilder sb = new StringBuilder("GFI(), result=");
            sb.append(i);
            sb.append(", i=");
            LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0.m(sb, b, "WirelessChargerCommander");
            ResultReceiver resultReceiver = resultReceiver;
            if (i != 0 || bundle == null) {
                resultReceiver.send(i, (Bundle) null);
                return;
            }
            Byte b3 = bundle.getByte("fan_id", (byte) -1);
            Byte b4 = bundle.getByte("fan_mode", (byte) -1);
            int i2 = bundle.getInt("fan_current_rpm", -1);
            int i3 = bundle.getInt("fan_min_rpm", -1);
            int i4 = bundle.getInt("fan_max_rpm", -1);
            Byte b5 = bundle.getByte("fan_type", (byte) -1);
            Byte b6 = bundle.getByte("fan_count", (byte) -1);
            Log.d("WirelessChargerCommander", "GFI() response: i=" + b3 + ", m=" + b4 + ", cr=" + i2 + ", mnr=" + i3 + ", mxr=" + i4 + ", t=" + b5 + ", c=" + b6);
            resultReceiver.send(0, bundle);
        }
    }

    public void getFanSimpleInformation(byte b, WirelessCharger.GetFanSimpleInformationCallback getFanSimpleInformationCallback) {
        if (initHALInterface()) {
            Log.d("Dreamliner-WLC_HAL", "command=3");
            try {
                FanInfo fanInfo = ((IWirelessCharger.Stub.Proxy) this.mWirelessCharger).getFanInfo(b);
                Bundle bundle = new Bundle();
                bundle.putByte("fan_id", b);
                bundle.putByte("fan_mode", fanInfo.fanMode);
                bundle.putInt("fan_current_rpm", fanInfo.currentRpm);
            } catch (Exception e) {
                mapError(e);
                Log.i("Dreamliner-WLC_HAL", "command=3 fail: " + e.getMessage());
            }
            getFanSimpleInformationCallback.onCallback();
        }
    }

    /* JADX WARNING: type inference failed for: r2v8, types: [java.lang.Object, com.google.android.systemui.dreamliner.DockInfo] */
    public void getInformation(WirelessCharger.GetInformationCallback getInformationCallback) {
        DockInfo dockInfo;
        int i;
        if (initHALInterface()) {
            Bundle bundle = null;
            try {
                DockInfo information = ((IWirelessCharger.Stub.Proxy) this.mWirelessCharger).getInformation();
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
            WirelessChargerCommander$setFan$1.AnonymousClass1 r7 = (WirelessChargerCommander$setFan$1.AnonymousClass1) getInformationCallback;
            r7.getClass();
            Log.d("WirelessChargerCommander", "GI() result: " + i);
            ResultReceiver resultReceiver = (ResultReceiver) resultReceiver;
            if (i == 0) {
                Log.d("WirelessChargerCommander", "GI() response: di=" + dockInfo);
                if (dockInfo != null) {
                    bundle = new Bundle();
                    bundle.putString("manufacturer", dockInfo.manufacturer);
                    bundle.putString("model", dockInfo.model);
                    bundle.putString("serialNumber", dockInfo.serialNumber);
                    bundle.putInt("accessoryType", dockInfo.accessoryType);
                }
                resultReceiver.send(0, bundle);
            } else if (i != 1) {
                resultReceiver.send(i, (Bundle) null);
            }
        }
    }

    /* JADX WARNING: type inference failed for: r4v5, types: [java.lang.Object, vendor.google.wireless_charger.IWirelessCharger$Stub$Proxy] */
    public final boolean initHALInterface() {
        IWirelessCharger iWirelessCharger;
        if (this.mWirelessCharger != null) {
            return true;
        }
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
            Log.i("Dreamliner-WLC_HAL", "WirelessCharger HAL not found: " + e.getMessage());
            this.mWirelessCharger = null;
        }
        if (this.mWirelessCharger != null) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0057 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void keyExchange(byte[] r7, com.google.android.systemui.dreamliner.WirelessCharger.KeyExchangeCallback r8) {
        /*
            r6 = this;
            boolean r0 = r6.initHALInterface()
            if (r0 != 0) goto L_0x0007
            return
        L_0x0007:
            r0 = 0
            r1 = 0
            vendor.google.wireless_charger.IWirelessCharger r6 = r6.mWirelessCharger     // Catch:{ Exception -> 0x001d }
            vendor.google.wireless_charger.IWirelessCharger$Stub$Proxy r6 = (vendor.google.wireless_charger.IWirelessCharger.Stub.Proxy) r6     // Catch:{ Exception -> 0x001d }
            vendor.google.wireless_charger.KeyExchangeResponse r6 = r6.keyExchange(r7)     // Catch:{ Exception -> 0x001d }
            byte[] r7 = r6.dockPublicKey     // Catch:{ Exception -> 0x001d }
            java.util.ArrayList r7 = convertPrimitiveArrayToArrayList(r7)     // Catch:{ Exception -> 0x001d }
            byte r6 = r6.dockIdentifier     // Catch:{ Exception -> 0x001b }
            r2 = r1
            goto L_0x003b
        L_0x001b:
            r6 = move-exception
            goto L_0x001f
        L_0x001d:
            r6 = move-exception
            r7 = r0
        L_0x001f:
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
        L_0x003b:
            com.google.android.systemui.dreamliner.WirelessChargerCommander$doChallenge$1$1 r8 = (com.google.android.systemui.dreamliner.WirelessChargerCommander$doChallenge$1.AnonymousClass1) r8
            r8.getClass()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "KE() result: "
            r3.<init>(r4)
            r3.append(r2)
            java.lang.String r3 = r3.toString()
            java.lang.String r4 = "WirelessChargerCommander"
            android.util.Log.d(r4, r3)
            android.os.ResultReceiver r3 = r3
            if (r2 != 0) goto L_0x008e
            if (r7 != 0) goto L_0x005a
            goto L_0x008e
        L_0x005a:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r5 = "KE() response: pk="
            r2.<init>(r5)
            r2.append(r7)
            java.lang.String r2 = r2.toString()
            android.util.Log.d(r4, r2)
            com.google.android.systemui.dreamliner.WirelessChargerCommander r8 = r4
            r8.getClass()
            boolean r8 = r7.isEmpty()
            if (r8 == 0) goto L_0x0077
            goto L_0x008a
        L_0x0077:
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
            java.lang.String r8 = "dock_id"
            r0.putByte(r8, r6)
            byte[] r6 = kotlin.collections.CollectionsKt.toByteArray(r7)
            java.lang.String r7 = "dock_public_key"
            r0.putByteArray(r7, r6)
        L_0x008a:
            r3.send(r1, r0)
            goto L_0x0091
        L_0x008e:
            r3.send(r2, r0)
        L_0x0091:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.dreamliner.WirelessChargerImpl.keyExchange(byte[], com.google.android.systemui.dreamliner.WirelessCharger$KeyExchangeCallback):void");
    }

    public void registerAlignInfo(WirelessCharger.AlignInfoListener alignInfoListener) {
        if (initHALInterface()) {
            try {
                ((IWirelessCharger.Stub.Proxy) this.mWirelessCharger).registerCallback(new WirelessChargerInfoCallback(alignInfoListener));
            } catch (Exception e) {
                Log.i("Dreamliner-WLC_HAL", "register alignInfo callback fail: " + e.getMessage());
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x009b A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00d0  */
    /* JADX WARNING: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setFan(byte r16, byte r17, int r18, com.google.android.systemui.dreamliner.WirelessCharger.SetFanCallback r19) {
        /*
            r15 = this;
            r0 = r16
            r1 = r17
            r2 = r18
            java.lang.String r3 = "fan_current_rpm"
            java.lang.String r4 = "fan_mode"
            java.lang.String r5 = "fan_id"
            java.lang.String r6 = "command=1 spending time: "
            boolean r7 = r15.initHALInterface()
            if (r7 != 0) goto L_0x0015
            return
        L_0x0015:
            java.lang.String r7 = "command=1, i="
            java.lang.String r8 = ", m="
            java.lang.String r9 = ", r="
            java.lang.StringBuilder r7 = androidx.compose.foundation.text.ValidatingOffsetMappingKt$$ExternalSyntheticOutline0.m(r7, r0, r8, r1, r9)
            java.lang.String r9 = "Dreamliner-WLC_HAL"
            com.android.settingslib.bluetooth.LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0.m(r7, r2, r9)
            r7 = 0
            r10 = 0
            long r11 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x0060 }
            r13 = r15
            vendor.google.wireless_charger.IWirelessCharger r13 = r13.mWirelessCharger     // Catch:{ Exception -> 0x0060 }
            char r2 = (char) r2     // Catch:{ Exception -> 0x0060 }
            vendor.google.wireless_charger.IWirelessCharger$Stub$Proxy r13 = (vendor.google.wireless_charger.IWirelessCharger.Stub.Proxy) r13     // Catch:{ Exception -> 0x0060 }
            vendor.google.wireless_charger.FanInfo r1 = r13.setFan(r0, r1, r2)     // Catch:{ Exception -> 0x0060 }
            android.os.Bundle r2 = new android.os.Bundle     // Catch:{ Exception -> 0x0060 }
            r2.<init>()     // Catch:{ Exception -> 0x0060 }
            r2.putByte(r5, r0)     // Catch:{ Exception -> 0x0060 }
            byte r0 = r1.fanMode     // Catch:{ Exception -> 0x0060 }
            r2.putByte(r4, r0)     // Catch:{ Exception -> 0x0060 }
            char r0 = r1.currentRpm     // Catch:{ Exception -> 0x0060 }
            r2.putInt(r3, r0)     // Catch:{ Exception -> 0x0060 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x005c }
            r0.<init>(r6)     // Catch:{ Exception -> 0x005c }
            long r13 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x005c }
            long r13 = r13 - r11
            r0.append(r13)     // Catch:{ Exception -> 0x005c }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x005c }
            android.util.Log.d(r9, r0)     // Catch:{ Exception -> 0x005c }
            r1 = r10
            goto L_0x007b
        L_0x005c:
            r0 = move-exception
            goto L_0x0062
        L_0x005e:
            r2 = r7
            goto L_0x0062
        L_0x0060:
            r0 = move-exception
            goto L_0x005e
        L_0x0062:
            int r1 = mapError(r0)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r11 = "command=1 fail: "
            r6.<init>(r11)
            java.lang.String r0 = r0.getMessage()
            r6.append(r0)
            java.lang.String r0 = r6.toString()
            android.util.Log.i(r9, r0)
        L_0x007b:
            r0 = r19
            com.google.android.systemui.dreamliner.WirelessChargerCommander$setFan$1$1 r0 = (com.google.android.systemui.dreamliner.WirelessChargerCommander$setFan$1.AnonymousClass1) r0
            r0.getClass()
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r9 = "SF() result="
            r6.<init>(r9)
            r6.append(r1)
            java.lang.String r6 = r6.toString()
            java.lang.String r9 = "WirelessChargerCommander"
            android.util.Log.w(r9, r6)
            java.lang.Object r0 = r4
            android.os.ResultReceiver r0 = (android.os.ResultReceiver) r0
            if (r1 != 0) goto L_0x00ce
            if (r2 != 0) goto L_0x009e
            goto L_0x00ce
        L_0x009e:
            r1 = -1
            java.lang.Byte r5 = r2.getByte(r5, r1)
            java.lang.Byte r4 = r2.getByte(r4, r1)
            int r1 = r2.getInt(r3, r1)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "SF() response: i="
            r2.<init>(r3)
            r2.append(r5)
            r2.append(r8)
            r2.append(r4)
            java.lang.String r3 = ", cr="
            r2.append(r3)
            com.android.settingslib.bluetooth.LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0.m(r2, r1, r9)
            if (r0 == 0) goto L_0x00d3
            android.os.Bundle r1 = new android.os.Bundle
            r1.<init>()
            r0.send(r10, r1)
            goto L_0x00d3
        L_0x00ce:
            if (r0 == 0) goto L_0x00d3
            r0.send(r1, r7)
        L_0x00d3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.dreamliner.WirelessChargerImpl.setFan(byte, byte, int, com.google.android.systemui.dreamliner.WirelessCharger$SetFanCallback):void");
    }

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class WirelessChargerInfoCallback extends Binder implements IWirelessChargerInfoCallback {
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
                            RecordingInputConnection$$ExternalSyntheticOutline0.m("Unexpected state: ", "DockAlignmentController", intValue);
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
                    DockObserver dockObserver = ((DockObserver$$ExternalSyntheticLambda3) it.next()).f$0;
                    dockObserver.getClass();
                    Log.d("DLObserver", "onAlignStateChanged alignState = " + i);
                    dockObserver.mLastAlignState = i;
                    for (KeyguardIndicationController$$ExternalSyntheticLambda12 keyguardIndicationController$$ExternalSyntheticLambda12 : dockObserver.mAlignmentStateListeners) {
                        KeyguardIndicationController keyguardIndicationController = keyguardIndicationController$$ExternalSyntheticLambda12.f$0;
                        keyguardIndicationController.mHandler.post(new KeyguardIndicationController$$ExternalSyntheticLambda13(keyguardIndicationController, i));
                    }
                    dockObserver.runPhotoAction();
                    dockObserver.notifyDreamlinerAlignStateChanged(i);
                }
                if (z) {
                    LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0.m(new StringBuilder("onAlignStateChanged, state: "), dockAlignmentController.mAlignmentState, "DockAlignmentController");
                }
            }
        }

        public final void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            printWriter.printf("alignment callback in [%d]%s\n", new Object[]{Integer.valueOf(Process.myPid()), WirelessChargerImpl.this.mContext.getPackageName()});
        }

        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            String str = IWirelessChargerInfoCallback.DESCRIPTOR;
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(str);
            }
            if (i == 1598968902) {
                parcel2.writeString(str);
                return true;
            } else if (i == 16777215) {
                parcel2.writeNoException();
                parcel2.writeInt(1);
                return true;
            } else if (i == 16777214) {
                parcel2.writeNoException();
                parcel2.writeString("2d5029583960b40ada539137df45bfa46a5b1333");
                return true;
            } else if (i != 1) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel.enforceNoDataAvail();
                alignInfoChanged((AlignInfo) parcel.readTypedObject(AlignInfo.CREATOR));
                return true;
            }
        }

        public final IBinder asBinder() {
            return this;
        }
    }
}
