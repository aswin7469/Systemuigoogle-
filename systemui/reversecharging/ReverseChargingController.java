package com.google.android.systemui.reversecharging;

import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.frameworks.stats.VendorAtom;
import android.frameworks.stats.VendorAtomValue;
import android.hardware.usb.UsbDevice;
import android.media.Ringtone;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IThermalEventListener;
import android.os.IThermalService;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.Temperature;
import android.os.UserManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0;
import com.android.systemui.BootCompleteCache;
import com.android.systemui.BootCompleteCacheImpl;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.CallbackController;
import com.google.android.systemui.statusbar.policy.BatteryControllerImplGoogle;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import vendor.google.wireless_charger.IWirelessCharger;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ReverseChargingController extends BroadcastReceiver implements CallbackController {
    public static final boolean DEBUG = Log.isLoggable("ReverseChargingControl", 3);
    public static final long DURATION_TO_ADVANCED_ACCESSORY_DEVICE_RECONNECTED_TIME_OUT;
    public static final long DURATION_TO_ADVANCED_PHONE_RECONNECTED_TIME_OUT;
    public static final long DURATION_TO_ADVANCED_PLUS_ACCESSORY_DEVICE_RECONNECTED_TIME_OUT;
    public static final long DURATION_TO_REVERSE_AC_TIME_OUT;
    public static final long DURATION_TO_REVERSE_RX_REMOVAL_TIME_OUT;
    public static final long DURATION_TO_REVERSE_TIME_OUT;
    public static final long DURATION_WAIT_NFC_SERVICE;
    public final ReverseChargingController$$ExternalSyntheticLambda2 mAccessoryDeviceRemovedTimeoutAlarmAction = new ReverseChargingController$$ExternalSyntheticLambda2(this, 4);
    public final AlarmManager mAlarmManager;
    final BatteryController.BatteryStateChangeCallback mBatteryStateChangeCallback = new BatteryController.BatteryStateChangeCallback() {
        public final void onPowerSaveChanged(boolean z) {
            ReverseChargingController.this.mPowerSave = z;
        }

        public final void onWirelessChargingChanged(boolean z) {
            ReverseChargingController.this.mWirelessCharging = z;
        }
    };
    public final Executor mBgExecutor;
    public final BootCompleteCache mBootCompleteCache;
    public final ReverseChargingController$$ExternalSyntheticLambda1 mBootCompleteListener = new ReverseChargingController$$ExternalSyntheticLambda1(this);
    boolean mBootCompleted;
    public final BroadcastDispatcher mBroadcastDispatcher;
    public boolean mCacheIsReverseSupported;
    public final ArrayList mChangeCallbacks = new ArrayList();
    public final ReverseChargingController$$ExternalSyntheticLambda2 mCheckNfcConflictWithUsbAudioAlarmAction = new ReverseChargingController$$ExternalSyntheticLambda2(this, 2);
    public final Context mContext;
    int mCurrentRtxMode = 0;
    public int mCurrentRtxReceiverType = 0;
    final boolean mDoesNfcConflictWithUsbAudio;
    public final boolean mDoesNfcConflictWithWlc;
    public boolean mIsReverseSupported;
    boolean mIsUsbPlugIn = false;
    int mLevel;
    public final Executor mMainExecutor;
    public String mName;
    final int[] mNfcUsbProductIds;
    final int[] mNfcUsbVendorIds;
    public boolean mPluggedAc;
    public boolean mPowerSave;
    public boolean mProvidingBattery = false;
    public final ReverseChargingController$$ExternalSyntheticLambda2 mReconnectedTimeoutAlarmAction = new ReverseChargingController$$ExternalSyntheticLambda2(this, 3);
    boolean mRestoreUsbNfcPollingMode;
    public boolean mRestoreWlcNfcPollingMode;
    boolean mReverse;
    public long mReverseStartTime = 0;
    public final Optional mRtxChargerManagerOptional;
    public final ReverseChargingController$$ExternalSyntheticLambda2 mRtxFinishAlarmAction = new ReverseChargingController$$ExternalSyntheticLambda2(this, 0);
    public final ReverseChargingController$$ExternalSyntheticLambda2 mRtxFinishRxFullAlarmAction = new ReverseChargingController$$ExternalSyntheticLambda2(this, 1);
    public int mRtxLevel;
    IThermalEventListener mSkinThermalEventListener;
    public boolean mStartReconnected;
    public boolean mStopReverseAtAcUnplug;
    public final IThermalService mThermalService;
    public final Optional mUsbManagerOptional;
    public boolean mUseRxRemovalTimeOut;
    public boolean mWirelessCharging;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public interface ReverseChargingChangeCallback {
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    final class SkinThermalEventListener extends IThermalEventListener.Stub {
        public SkinThermalEventListener() {
        }

        public final void notifyThrottling(Temperature temperature) {
            int status = temperature.getStatus();
            Log.i("ReverseChargingControl", "notifyThrottling(): thermal status=" + status);
            ReverseChargingController reverseChargingController = ReverseChargingController.this;
            if (reverseChargingController.mReverse && status >= 4) {
                reverseChargingController.setReverseStateInternal(3, false);
            }
        }
    }

    static {
        TimeUnit timeUnit = TimeUnit.MINUTES;
        DURATION_TO_REVERSE_TIME_OUT = timeUnit.toMillis(1);
        DURATION_TO_REVERSE_AC_TIME_OUT = timeUnit.toMillis(1);
        TimeUnit timeUnit2 = TimeUnit.SECONDS;
        DURATION_TO_REVERSE_RX_REMOVAL_TIME_OUT = timeUnit2.toMillis(30);
        DURATION_TO_ADVANCED_ACCESSORY_DEVICE_RECONNECTED_TIME_OUT = timeUnit2.toMillis(120);
        DURATION_TO_ADVANCED_PHONE_RECONNECTED_TIME_OUT = timeUnit2.toMillis(120);
        DURATION_TO_ADVANCED_PLUS_ACCESSORY_DEVICE_RECONNECTED_TIME_OUT = timeUnit2.toMillis(120);
        DURATION_WAIT_NFC_SERVICE = timeUnit2.toMillis(10);
    }

    public ReverseChargingController(Context context, BroadcastDispatcher broadcastDispatcher, Optional optional, AlarmManager alarmManager, Optional optional2, Executor executor, Executor executor2, BootCompleteCache bootCompleteCache, IThermalService iThermalService) {
        new Binder();
        this.mContext = context;
        this.mBroadcastDispatcher = broadcastDispatcher;
        this.mRtxChargerManagerOptional = optional;
        this.mAlarmManager = alarmManager;
        this.mDoesNfcConflictWithWlc = context.getResources().getBoolean(2131034156);
        this.mUsbManagerOptional = optional2;
        this.mMainExecutor = executor;
        this.mBgExecutor = executor2;
        this.mBootCompleteCache = bootCompleteCache;
        this.mThermalService = iThermalService;
        int[] intArray = context.getResources().getIntArray(2130903110);
        this.mNfcUsbVendorIds = intArray;
        int[] intArray2 = context.getResources().getIntArray(2130903109);
        this.mNfcUsbProductIds = intArray2;
        if (intArray.length == intArray2.length) {
            this.mDoesNfcConflictWithUsbAudio = context.getResources().getBoolean(2131034155);
            return;
        }
        throw new IllegalStateException("VendorIds and ProductIds must be the same length");
    }

    public final void cancelRtxTimer(int i) {
        if (i == 0) {
            this.mAlarmManager.cancel(this.mRtxFinishAlarmAction);
        } else if (i == 1) {
            this.mAlarmManager.cancel(this.mRtxFinishRxFullAlarmAction);
        } else if (i == 3) {
            this.mAlarmManager.cancel(this.mReconnectedTimeoutAlarmAction);
        } else if (i == 4) {
            this.mAlarmManager.cancel(this.mAccessoryDeviceRemovedTimeoutAlarmAction);
        }
    }

    public final void checkAndChangeNfcPollingAgainstUsbAudioDevice(boolean z, UsbDevice usbDevice) {
        boolean z2 = false;
        for (int i = 0; i < this.mNfcUsbVendorIds.length; i++) {
            if (usbDevice.getVendorId() == this.mNfcUsbVendorIds[i] && usbDevice.getProductId() == this.mNfcUsbProductIds[i]) {
                this.mRestoreUsbNfcPollingMode = !z;
                if (!this.mRestoreWlcNfcPollingMode && z) {
                    z2 = true;
                }
                enableNfcPollingMode(z2);
                return;
            }
        }
    }

    public final void enableNfcPollingMode(boolean z) {
        int i;
        if (z) {
            i = 0;
        } else {
            i = 4096;
        }
        if (DEBUG) {
            ExifInterface$$ExternalSyntheticOutline0.m("Change NFC reader mode to flags: ", i, "ReverseChargingControl");
        }
        this.mBgExecutor.execute(new ReverseChargingController$$ExternalSyntheticLambda0(this, z, 1));
    }

    public final void fireReverseChanged$1() {
        synchronized (this.mChangeCallbacks) {
            this.mMainExecutor.execute(new ReverseChargingController$$ExternalSyntheticLambda5(this));
        }
    }

    public final void handleIntentForReverseCharging(Intent intent) {
        UsbDevice usbDevice;
        boolean z;
        boolean z2;
        boolean z3;
        if (isReverseSupported()) {
            String action = intent.getAction();
            boolean z4 = true;
            if (action.equals("android.intent.action.BATTERY_CHANGED")) {
                boolean z5 = this.mPluggedAc;
                this.mLevel = (int) ((((float) intent.getIntExtra("level", 0)) * 100.0f) / ((float) intent.getIntExtra("scale", 100)));
                int intExtra = intent.getIntExtra("plugged", 0);
                if (intExtra == 1) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                this.mPluggedAc = z3;
                Log.i("ReverseChargingControl", "handleIntentForReverseCharging(): rtx=" + (this.mReverse ? 1 : 0) + " wlc=" + (this.mWirelessCharging ? 1 : 0) + " plgac=" + (z5 ? 1 : 0) + " ac=" + (this.mPluggedAc ? 1 : 0) + " acrtx=" + (this.mStopReverseAtAcUnplug ? 1 : 0) + " extra=" + intExtra + " this=" + this);
                boolean z6 = this.mReverse;
                if (z6 && this.mWirelessCharging) {
                    if (DEBUG) {
                        Log.d("ReverseChargingControl", "handleIntentForReverseCharging(): wireless charging, stop");
                    }
                    setReverseStateInternal(102, false);
                } else if (z6 && z5 && !this.mPluggedAc && this.mStopReverseAtAcUnplug) {
                    if (DEBUG) {
                        Log.d("ReverseChargingControl", "handleIntentForReverseCharging(): wired charging, stop");
                    }
                    this.mStopReverseAtAcUnplug = false;
                    setReverseStateInternal(106, false);
                } else if (z6 || z5 || !this.mPluggedAc) {
                    if (z6 && isLowBattery()) {
                        if (DEBUG) {
                            Log.d("ReverseChargingControl", "handleIntentForReverseCharging(): lower then battery threshold, stop");
                        }
                        setReverseStateInternal(4, false);
                    }
                } else if (Settings.Global.getInt(this.mContext.getContentResolver(), "settings_key_reverse_charging_auto_turn_on", 0) != 1) {
                    Log.d("ReverseChargingControl", "auto turn on is disabled");
                } else if (!this.mBootCompleted) {
                    Log.i("ReverseChargingControl", "skip auto turn on");
                } else {
                    if (DEBUG) {
                        Log.d("ReverseChargingControl", "handleIntentForReverseCharging(): wired charging, start");
                    }
                    this.mStopReverseAtAcUnplug = true;
                    setReverseStateInternal(3, true);
                }
            } else if (action.equals("android.os.action.POWER_SAVE_MODE_CHANGED")) {
                if (this.mReverse && this.mPowerSave) {
                    Log.i("ReverseChargingControl", "handleIntentForReverseCharging(): power save, stop");
                    setReverseStateInternal(105, false);
                }
            } else if (TextUtils.equals(action, "android.hardware.usb.action.USB_DEVICE_ATTACHED")) {
                UsbDevice usbDevice2 = (UsbDevice) intent.getParcelableExtra("device");
                if (usbDevice2 == null) {
                    Log.w("ReverseChargingControl", "handleIntentForReverseCharging() UsbDevice is null!");
                    this.mIsUsbPlugIn = false;
                    return;
                }
                if (this.mDoesNfcConflictWithUsbAudio) {
                    checkAndChangeNfcPollingAgainstUsbAudioDevice(false, usbDevice2);
                }
                int i = 0;
                while (true) {
                    if (i >= usbDevice2.getInterfaceCount()) {
                        z = false;
                        break;
                    } else if (usbDevice2.getInterface(i).getInterfaceClass() == 1) {
                        z = true;
                        break;
                    } else {
                        i++;
                    }
                }
                int i2 = 0;
                while (true) {
                    if (i2 >= usbDevice2.getConfigurationCount()) {
                        z2 = false;
                        break;
                    } else if (usbDevice2.getConfiguration(i2).getMaxPower() < 100) {
                        z2 = true;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (z && z2) {
                    z4 = false;
                }
                this.mIsUsbPlugIn = z4;
                if (this.mReverse && z4) {
                    setReverseStateInternal(108, false);
                    Log.d("ReverseChargingControl", "handleIntentForReverseCharging(): stop reverse charging because USB-C plugin!");
                }
            } else if (TextUtils.equals(action, "android.hardware.usb.action.USB_DEVICE_DETACHED")) {
                if (this.mDoesNfcConflictWithUsbAudio && (usbDevice = (UsbDevice) intent.getParcelableExtra("device")) != null) {
                    checkAndChangeNfcPollingAgainstUsbAudioDevice(true, usbDevice);
                }
                this.mIsUsbPlugIn = false;
            }
        }
    }

    public final void init(BatteryController batteryController) {
        if (!((UserManager) this.mContext.getSystemService(UserManager.class)).isSystemUser()) {
            Log.i("ReverseChargingControl", "Skip initialization for non system user");
            this.mCacheIsReverseSupported = true;
            this.mIsReverseSupported = false;
            return;
        }
        batteryController.addCallback(this.mBatteryStateChangeCallback);
        this.mCacheIsReverseSupported = false;
        this.mReverse = false;
        this.mRtxLevel = -1;
        this.mName = null;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
        this.mBroadcastDispatcher.registerReceiver(this, intentFilter);
        ((BootCompleteCacheImpl) this.mBootCompleteCache).addListener(this.mBootCompleteListener);
        if (this.mRtxChargerManagerOptional.isPresent()) {
            if (this.mRtxChargerManagerOptional.isPresent()) {
                this.mBgExecutor.execute(new ReverseChargingController$$ExternalSyntheticLambda0(this, false, 0));
            } else {
                Log.i("ReverseChargingControl", "setRtxMode(): rtx not available");
            }
            ReverseWirelessCharger reverseWirelessCharger = (ReverseWirelessCharger) this.mRtxChargerManagerOptional.get();
            Object obj = new Object();
            synchronized (reverseWirelessCharger.mLock) {
                reverseWirelessCharger.mIsDockPresentCallbacks.add(obj);
            }
            ReverseWirelessCharger reverseWirelessCharger2 = (ReverseWirelessCharger) this.mRtxChargerManagerOptional.get();
            Object obj2 = new Object();
            synchronized (reverseWirelessCharger2.mLock) {
                reverseWirelessCharger2.mRtxInformationCallbacks.add(obj2);
            }
            ReverseWirelessCharger reverseWirelessCharger3 = (ReverseWirelessCharger) this.mRtxChargerManagerOptional.get();
            ReverseChargingController$$ExternalSyntheticLambda4 reverseChargingController$$ExternalSyntheticLambda4 = new ReverseChargingController$$ExternalSyntheticLambda4(this);
            synchronized (reverseWirelessCharger3.mLock) {
                reverseWirelessCharger3.mRtxStatusCallbacks.add(reverseChargingController$$ExternalSyntheticLambda4);
            }
            try {
                if (this.mSkinThermalEventListener == null) {
                    this.mSkinThermalEventListener = new SkinThermalEventListener();
                }
                this.mThermalService.registerThermalEventListenerWithType(this.mSkinThermalEventListener, 3);
            } catch (RemoteException e) {
                Log.e("ReverseChargingControl", "Could not register thermal event listener, exception: " + e);
            }
        }
    }

    public final boolean isLowBattery() {
        int i = Settings.Global.getInt(this.mContext.getContentResolver(), "advanced_battery_usage_amount", 2) * 5;
        if (this.mLevel > i) {
            return false;
        }
        Log.w("ReverseChargingControl", "The battery is lower than threshold turn off reverse charging ! level : " + this.mLevel + ", threshold : " + i);
        return true;
    }

    public final boolean isReverseSupported() {
        if (this.mCacheIsReverseSupported) {
            return this.mIsReverseSupported;
        }
        boolean z = false;
        if (this.mRtxChargerManagerOptional.isPresent()) {
            ReverseWirelessCharger reverseWirelessCharger = (ReverseWirelessCharger) this.mRtxChargerManagerOptional.get();
            reverseWirelessCharger.initHALInterface();
            IWirelessCharger iWirelessCharger = reverseWirelessCharger.mWirelessCharger;
            if (iWirelessCharger != null) {
                try {
                    z = ((IWirelessCharger.Stub.Proxy) iWirelessCharger).isRtxSupported();
                } catch (Exception e) {
                    Log.i("ReverseWirelessCharger", "isRtxSupported fail: ", e);
                }
            }
            this.mIsReverseSupported = z;
            this.mCacheIsReverseSupported = true;
            return z;
        }
        if (DEBUG) {
            Log.d("ReverseChargingControl", "isReverseSupported(): mRtxChargerManagerOptional is not present!");
        }
        return false;
    }

    public final void logReverseStartEvent(int i) {
        if (DEBUG) {
            ExifInterface$$ExternalSyntheticOutline0.m("logReverseStartEvent: ", i, "ReverseChargingControl");
        }
        this.mReverseStartTime = SystemClock.uptimeMillis();
        int i2 = this.mLevel;
        boolean z = ReverseChargingMetrics.DEBUG;
        VendorAtom vendorAtom = new VendorAtom();
        vendorAtom.reverseDomainName = "";
        VendorAtomValue[] vendorAtomValueArr = new VendorAtomValue[2];
        vendorAtom.values = vendorAtomValueArr;
        vendorAtom.atomId = 100037;
        vendorAtomValueArr[0] = new VendorAtomValue(0, Integer.valueOf(i));
        vendorAtom.values[1] = new VendorAtomValue(0, Integer.valueOf(i2));
        ReverseChargingMetrics.reportVendorAtom(vendorAtom);
    }

    public final void logReverseStopEvent(int i) {
        if (DEBUG) {
            ExifInterface$$ExternalSyntheticOutline0.m("logReverseStopEvent: ", i, "ReverseChargingControl");
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        int i2 = this.mLevel;
        boolean z = ReverseChargingMetrics.DEBUG;
        VendorAtom vendorAtom = new VendorAtom();
        vendorAtom.reverseDomainName = "";
        VendorAtomValue[] vendorAtomValueArr = new VendorAtomValue[3];
        vendorAtom.values = vendorAtomValueArr;
        vendorAtom.atomId = 100038;
        vendorAtomValueArr[0] = new VendorAtomValue(0, Integer.valueOf(i));
        vendorAtom.values[1] = new VendorAtomValue(0, Integer.valueOf(i2));
        vendorAtom.values[2] = new VendorAtomValue(1, Long.valueOf((uptimeMillis - this.mReverseStartTime) / 1000));
        ReverseChargingMetrics.reportVendorAtom(vendorAtom);
    }

    public final void onAlarmRtxFinish(int i) {
        Log.i("ReverseChargingControl", "onAlarmRtxFinish(): rtx=0, reason: " + i);
        setReverseStateInternal(i, false);
    }

    public final void onReceive(Context context, Intent intent) {
        handleIntentForReverseCharging(intent);
    }

    public void onReverseStateChanged(Bundle bundle) {
        StringBuilder sb = new StringBuilder("onReverseStateChanged(): rtx=");
        int i = 1;
        if (bundle.getInt("key_rtx_mode") != 1) {
            i = 0;
        }
        sb.append(i);
        sb.append(" bundle=");
        sb.append(bundle.toString());
        sb.append(" this=");
        sb.append(this);
        Log.i("ReverseChargingControl", sb.toString());
        this.mBgExecutor.execute(new ReverseChargingController$$ExternalSyntheticLambda3(this, bundle));
    }

    public void playSound(Ringtone ringtone) {
        if (ringtone != null) {
            ringtone.setStreamType(1);
            ringtone.play();
        }
    }

    public final void removeCallback(Object obj) {
        ReverseChargingChangeCallback reverseChargingChangeCallback = (ReverseChargingChangeCallback) obj;
        synchronized (this.mChangeCallbacks) {
            this.mChangeCallbacks.remove(reverseChargingChangeCallback);
        }
    }

    public final void setReverseStateInternal(int i, boolean z) {
        if (isReverseSupported()) {
            Log.i("ReverseChargingControl", "setReverseStateInternal(): rtx=" + (z ? 1 : 0) + ",reason=" + i);
            if (!z || this.mReverse) {
                logReverseStopEvent(i);
            } else {
                logReverseStartEvent(i);
                if (this.mPowerSave) {
                    logReverseStopEvent(104);
                    return;
                } else if (isLowBattery()) {
                    logReverseStopEvent(100);
                    return;
                } else if (this.mIsUsbPlugIn) {
                    logReverseStopEvent(107);
                    return;
                }
            }
            if (z != this.mReverse) {
                if (z && this.mDoesNfcConflictWithWlc && !this.mRestoreWlcNfcPollingMode) {
                    enableNfcPollingMode(false);
                    this.mRestoreWlcNfcPollingMode = true;
                }
                this.mReverse = z;
                if (z) {
                    setRtxTimer(DURATION_TO_REVERSE_TIME_OUT, 0);
                }
                if (this.mRtxChargerManagerOptional.isPresent()) {
                    this.mBgExecutor.execute(new ReverseChargingController$$ExternalSyntheticLambda0(this, z, 0));
                } else {
                    Log.i("ReverseChargingControl", "setRtxMode(): rtx not available");
                }
            }
        }
    }

    public final void setRtxTimer(long j, int i) {
        int i2 = i;
        if (i2 == 0) {
            AlarmManager alarmManager = this.mAlarmManager;
            ReverseChargingController$$ExternalSyntheticLambda2 reverseChargingController$$ExternalSyntheticLambda2 = this.mRtxFinishAlarmAction;
            alarmManager.setExact(2, SystemClock.elapsedRealtime() + j, "ReverseChargingControl", reverseChargingController$$ExternalSyntheticLambda2, (Handler) null);
        } else if (i2 == 1) {
            this.mAlarmManager.setExact(2, SystemClock.elapsedRealtime() + j, "ReverseChargingControl", this.mRtxFinishRxFullAlarmAction, (Handler) null);
        } else if (i2 == 2) {
            AlarmManager alarmManager2 = this.mAlarmManager;
            ReverseChargingController$$ExternalSyntheticLambda2 reverseChargingController$$ExternalSyntheticLambda22 = this.mCheckNfcConflictWithUsbAudioAlarmAction;
            alarmManager2.setExact(2, SystemClock.elapsedRealtime() + j, "ReverseChargingControl", reverseChargingController$$ExternalSyntheticLambda22, (Handler) null);
        } else if (i2 == 3) {
            this.mAlarmManager.setExact(2, SystemClock.elapsedRealtime() + j, "ReverseChargingControl", this.mReconnectedTimeoutAlarmAction, (Handler) null);
        } else if (i2 == 4) {
            this.mAlarmManager.setExact(2, SystemClock.elapsedRealtime() + j, "ReverseChargingControl", this.mAccessoryDeviceRemovedTimeoutAlarmAction, (Handler) null);
        }
    }

    public final void addCallback(ReverseChargingChangeCallback reverseChargingChangeCallback) {
        synchronized (this.mChangeCallbacks) {
            this.mChangeCallbacks.add(reverseChargingChangeCallback);
        }
        BatteryControllerImplGoogle batteryControllerImplGoogle = (BatteryControllerImplGoogle) reverseChargingChangeCallback;
        batteryControllerImplGoogle.onReverseChargingChanged(this.mRtxLevel, this.mName, this.mReverse);
    }
}
