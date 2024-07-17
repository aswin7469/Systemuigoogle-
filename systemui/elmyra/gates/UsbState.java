package com.google.android.systemui.elmyra.gates;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.android.systemui.util.concurrency.DelayableExecutor;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class UsbState extends TransientGate {
    public final Context mContext;
    public boolean mUsbConnected;
    public final AnonymousClass1 mUsbReceiver = new BroadcastReceiver() {
        public final void onReceive(Context context, Intent intent) {
            boolean booleanExtra = intent.getBooleanExtra("connected", false);
            UsbState usbState = UsbState.this;
            if (booleanExtra != usbState.mUsbConnected) {
                usbState.mUsbConnected = booleanExtra;
                usbState.block();
            }
        }
    };

    public UsbState(Context context, DelayableExecutor delayableExecutor, int i) {
        super(delayableExecutor, (long) i);
        this.mContext = context;
    }

    public final void onActivate() {
        IntentFilter intentFilter = new IntentFilter("android.hardware.usb.action.USB_STATE");
        Context context = this.mContext;
        Intent registerReceiver = context.registerReceiver((BroadcastReceiver) null, intentFilter);
        if (registerReceiver != null) {
            this.mUsbConnected = registerReceiver.getBooleanExtra("connected", false);
        }
        context.registerReceiver(this.mUsbReceiver, intentFilter);
    }

    public final void onDeactivate() {
        this.mContext.unregisterReceiver(this.mUsbReceiver);
    }
}
