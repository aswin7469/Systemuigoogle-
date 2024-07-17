package com.google.android.systemui.reversecharging;

import android.frameworks.stats.VendorAtom;
import android.frameworks.stats.VendorAtomValue;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;
import androidx.compose.foundation.text.input.internal.RecordingInputConnection$$ExternalSyntheticOutline0;
import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0;
import com.android.settingslib.bluetooth.CachedBluetoothDevice$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class ReverseChargingController$$ExternalSyntheticLambda8 implements Runnable {
    public final /* synthetic */ ReverseChargingController f$0;
    public final /* synthetic */ Bundle f$1;

    public /* synthetic */ ReverseChargingController$$ExternalSyntheticLambda8(ReverseChargingController reverseChargingController, Bundle bundle) {
        this.f$0 = reverseChargingController;
        this.f$1 = bundle;
    }

    public final void run() {
        long j;
        String str;
        String str2;
        int i;
        int i2;
        ReverseChargingController reverseChargingController = this.f$0;
        Bundle bundle = this.f$1;
        reverseChargingController.getClass();
        boolean z = ReverseChargingController.DEBUG;
        int i3 = 0;
        if (z) {
            StringBuilder sb = new StringBuilder("onReverseStateChangedOnBackgroundThread(): rtx=");
            if (bundle.getInt("key_rtx_mode") == 1) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            sb.append(i2);
            sb.append(" bundle=");
            sb.append(bundle.toString());
            sb.append(" this=");
            sb.append(reverseChargingController);
            Log.d("ReverseChargingControl", sb.toString());
        }
        int i4 = bundle.getInt("key_rtx_mode");
        int i5 = bundle.getInt("key_reason_type");
        boolean z2 = bundle.getBoolean("key_rtx_connection");
        int i6 = bundle.getInt("key_accessory_type");
        int i7 = bundle.getInt("key_rtx_level");
        if (!reverseChargingController.mReverse && reverseChargingController.mWirelessCharging && i4 == 0 && i7 > 0) {
            reverseChargingController.mRtxLevel = i7;
            if (TextUtils.isEmpty(reverseChargingController.mName)) {
                reverseChargingController.mName = reverseChargingController.mContext.getString(2131953699);
            }
            reverseChargingController.fireReverseChanged$1();
        } else if (!reverseChargingController.isReverseSupported()) {
            reverseChargingController.mReverse = false;
            reverseChargingController.mRtxLevel = -1;
            reverseChargingController.mName = null;
            reverseChargingController.fireReverseChanged$1();
        } else {
            int i8 = reverseChargingController.mCurrentRtxMode;
            if (i8 == 1 && i4 != 1 && reverseChargingController.mReverse) {
                if (i5 != 0) {
                    if (i5 == 1) {
                        reverseChargingController.logReverseStopEvent(4);
                    } else if (i5 == 2) {
                        reverseChargingController.logReverseStopEvent(3);
                    } else if (i5 == 3) {
                        reverseChargingController.logReverseStopEvent(102);
                    } else if (i5 == 4) {
                        reverseChargingController.logReverseStopEvent(110);
                    } else if (i5 == 15) {
                        reverseChargingController.logReverseStopEvent(8);
                    }
                } else if (i4 != 2 || reverseChargingController.mCurrentRtxReceiverType == 0) {
                    reverseChargingController.logReverseStopEvent(1);
                } else {
                    reverseChargingController.logReverseStopEvent(8);
                }
                RecordingInputConnection$$ExternalSyntheticOutline0.m("Reverse charging error happened : ", "ReverseChargingControl", i5);
            } else if (i8 != 1 && i4 == 1 && !reverseChargingController.mReverse) {
                reverseChargingController.logReverseStartEvent(1);
            }
            if (reverseChargingController.mCurrentRtxMode != 1 && i4 == 1 && !reverseChargingController.mReverse && reverseChargingController.mDoesNfcConflictWithWlc && !reverseChargingController.mRestoreWlcNfcPollingMode) {
                reverseChargingController.enableNfcPollingMode(false);
                reverseChargingController.mRestoreWlcNfcPollingMode = true;
            }
            reverseChargingController.mCurrentRtxMode = i4;
            reverseChargingController.mReverse = false;
            reverseChargingController.mRtxLevel = -1;
            reverseChargingController.mName = null;
            if (i4 == 1) {
                boolean z3 = reverseChargingController.mProvidingBattery;
                if (z3 || !z2) {
                    if (z3 && !z2) {
                        if (z) {
                            StringBuilder sb2 = new StringBuilder("playSoundIfNecessary() play end charging sound: ");
                            sb2.append(z2);
                            sb2.append(", accType : ");
                            sb2.append(i6);
                            sb2.append(", mStartReConnected : ");
                            CachedBluetoothDevice$$ExternalSyntheticOutline0.m(sb2, reverseChargingController.mStartReconnected, "ReverseChargingControl");
                        }
                        if (!reverseChargingController.mStartReconnected && (i6 == 16 || i6 == 90 || i6 == 114)) {
                            reverseChargingController.mStartReconnected = true;
                            if (z) {
                                Log.w("ReverseChargingControl", "playSoundIfNecessary() start reconnected");
                            }
                        }
                    }
                    str2 = null;
                } else {
                    if (z) {
                        StringBuilder sb3 = new StringBuilder("playSoundIfNecessary() play start charging sound: ");
                        sb3.append(z2);
                        sb3.append(", accType : ");
                        sb3.append(i6);
                        sb3.append(", mStartReconnected : ");
                        CachedBluetoothDevice$$ExternalSyntheticOutline0.m(sb3, reverseChargingController.mStartReconnected, "ReverseChargingControl");
                    }
                    if (!reverseChargingController.mStartReconnected || !(i6 == 16 || i6 == 90 || i6 == 114)) {
                        str2 = reverseChargingController.mContext.getString(2131953702);
                    } else {
                        str2 = null;
                    }
                    reverseChargingController.mStartReconnected = false;
                }
                if (!TextUtils.isEmpty(str2)) {
                    reverseChargingController.playSound(RingtoneManager.getRingtone(reverseChargingController.mContext, new Uri.Builder().scheme("file").appendPath(str2).build()));
                }
                reverseChargingController.mProvidingBattery = z2;
                reverseChargingController.mReverse = true;
                if (!z2) {
                    if (z) {
                        Log.d("ReverseChargingControl", "receiver is not available");
                    }
                    reverseChargingController.mRtxLevel = -1;
                    reverseChargingController.mCurrentRtxReceiverType = 0;
                } else {
                    reverseChargingController.mStopReverseAtAcUnplug = false;
                    reverseChargingController.mRtxLevel = i7;
                    reverseChargingController.mUseRxRemovalTimeOut = true;
                    if (reverseChargingController.mCurrentRtxReceiverType != i6) {
                        if (z) {
                            Log.d("ReverseChargingControl", "receiver type updated: " + reverseChargingController.mCurrentRtxReceiverType + " " + i6);
                        }
                        if (z) {
                            ExifInterface$$ExternalSyntheticOutline0.m("logReverseAccessoryType: ", "ReverseChargingControl", i6);
                        }
                        if (i6 != 0) {
                            boolean z4 = ReverseChargingMetrics.DEBUG;
                            VendorAtom vendorAtom = new VendorAtom();
                            vendorAtom.reverseDomainName = "";
                            VendorAtomValue[] vendorAtomValueArr = new VendorAtomValue[1];
                            vendorAtom.values = vendorAtomValueArr;
                            vendorAtom.atomId = 100040;
                            if (i6 == 16 || i6 == 114) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            vendorAtomValueArr[0] = new VendorAtomValue(0, Integer.valueOf(i));
                            ReverseChargingMetrics.reportVendorAtom(vendorAtom);
                        }
                        reverseChargingController.mCurrentRtxReceiverType = i6;
                    }
                }
            } else {
                reverseChargingController.mStopReverseAtAcUnplug = false;
                reverseChargingController.mProvidingBattery = false;
                reverseChargingController.mUseRxRemovalTimeOut = false;
                reverseChargingController.mStartReconnected = false;
                if (reverseChargingController.mDoesNfcConflictWithWlc && reverseChargingController.mRestoreWlcNfcPollingMode) {
                    reverseChargingController.mRestoreWlcNfcPollingMode = false;
                    reverseChargingController.enableNfcPollingMode(!reverseChargingController.mRestoreUsbNfcPollingMode);
                }
            }
            reverseChargingController.fireReverseChanged$1();
            reverseChargingController.cancelRtxTimer(0);
            reverseChargingController.cancelRtxTimer(1);
            reverseChargingController.cancelRtxTimer(4);
            if (!reverseChargingController.mStartReconnected) {
                reverseChargingController.cancelRtxTimer(3);
            }
            boolean z5 = reverseChargingController.mReverse;
            if (z5 && reverseChargingController.mRtxLevel == -1) {
                if (reverseChargingController.mStartReconnected) {
                    if (i6 == 16) {
                        j = ReverseChargingController.DURATION_TO_ADVANCED_ACCESSORY_DEVICE_RECONNECTED_TIME_OUT;
                    } else if (i6 == 114) {
                        j = ReverseChargingController.DURATION_TO_ADVANCED_PHONE_RECONNECTED_TIME_OUT;
                    } else {
                        j = ReverseChargingController.DURATION_TO_ADVANCED_PLUS_ACCESSORY_DEVICE_RECONNECTED_TIME_OUT;
                    }
                } else if (reverseChargingController.mStopReverseAtAcUnplug) {
                    j = ReverseChargingController.DURATION_TO_REVERSE_AC_TIME_OUT;
                } else if (reverseChargingController.mUseRxRemovalTimeOut) {
                    j = ReverseChargingController.DURATION_TO_REVERSE_RX_REMOVAL_TIME_OUT;
                } else {
                    j = ReverseChargingController.DURATION_TO_REVERSE_TIME_OUT;
                }
                if (reverseChargingController.mStopReverseAtAcUnplug) {
                    str = "rtx.ac.timeout";
                } else {
                    str = "rtx.timeout";
                }
                String str3 = SystemProperties.get(str);
                if (!TextUtils.isEmpty(str3)) {
                    try {
                        j = Long.parseLong(str3);
                    } catch (NumberFormatException e) {
                        Log.w("ReverseChargingControl", "getRtxTimeOut(): invalid timeout, " + e);
                    }
                }
                if (ReverseChargingController.DEBUG) {
                    Log.d("ReverseChargingControl", "onReverseStateChangedOnBackgroundThread(): time out, setRtxTimer, duration=" + j);
                }
                if (reverseChargingController.mStartReconnected) {
                    i3 = 3;
                } else if (reverseChargingController.mUseRxRemovalTimeOut && !reverseChargingController.mStopReverseAtAcUnplug) {
                    i3 = 4;
                }
                reverseChargingController.setRtxTimer(j, i3);
            } else if (z5 && reverseChargingController.mRtxLevel >= 100) {
                if (z) {
                    Log.d("ReverseChargingControl", "onReverseStateChangedOnBackgroundThread(): rtx=" + (reverseChargingController.mReverse ? 1 : 0) + ", Rx fully charged, setRtxTimer, REVERSE_FINISH_RX_FULL");
                }
                reverseChargingController.setRtxTimer(0, 1);
            }
        }
    }
}
