package com.google.android.systemui.reversecharging;

import android.app.AlarmManager;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.util.Log;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class ReverseChargingController$$ExternalSyntheticLambda2 implements AlarmManager.OnAlarmListener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ReverseChargingController f$0;

    public /* synthetic */ ReverseChargingController$$ExternalSyntheticLambda2(ReverseChargingController reverseChargingController, int i) {
        this.$r8$classId = i;
        this.f$0 = reverseChargingController;
    }

    public final void onAlarm() {
        int i = this.$r8$classId;
        ReverseChargingController reverseChargingController = this.f$0;
        switch (i) {
            case 0:
                reverseChargingController.onAlarmRtxFinish(5);
                return;
            case 1:
                reverseChargingController.onAlarmRtxFinish(103);
                return;
            case 2:
                if (reverseChargingController.mUsbManagerOptional.isPresent()) {
                    for (UsbDevice checkAndChangeNfcPollingAgainstUsbAudioDevice : ((UsbManager) reverseChargingController.mUsbManagerOptional.get()).getDeviceList().values()) {
                        reverseChargingController.checkAndChangeNfcPollingAgainstUsbAudioDevice(false, checkAndChangeNfcPollingAgainstUsbAudioDevice);
                    }
                    return;
                }
                return;
            case 3:
                if (ReverseChargingController.DEBUG) {
                    reverseChargingController.getClass();
                    Log.w("ReverseChargingControl", "mReConnectedTimeoutAlarmAction() timeout");
                }
                reverseChargingController.mStartReconnected = false;
                reverseChargingController.onAlarmRtxFinish(6);
                return;
            default:
                if (ReverseChargingController.DEBUG) {
                    reverseChargingController.getClass();
                    Log.w("ReverseChargingControl", "mAccessoryDeviceRemovedTimeoutAlarmAction() timeout");
                }
                reverseChargingController.onAlarmRtxFinish(6);
                return;
        }
    }
}
