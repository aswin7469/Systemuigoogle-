package com.google.android.systemui.columbus.legacy.gates;

import android.content.Context;
import android.content.IntentFilter;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class UsbState extends TransientGate {
    public final Context context;
    public final long gateDuration = 500;
    public boolean usbConnected;
    public final UsbState$usbReceiver$1 usbReceiver = new UsbState$usbReceiver$1(this);

    public UsbState(Context context2) {
        this.context = context2;
    }

    public final void onActivate() {
        BuildersKt.launch$default(this.coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new UsbState$onActivate$1(this, this.context.registerReceiver(this.usbReceiver, new IntentFilter("android.hardware.usb.action.USB_STATE")), (Continuation) null), 3);
    }

    public final void onDeactivate() {
        this.context.unregisterReceiver(this.usbReceiver);
    }
}
