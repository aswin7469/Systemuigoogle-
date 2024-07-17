package com.google.android.systemui.columbus.legacy.gates;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class UsbState$usbReceiver$1 extends BroadcastReceiver {
    public final /* synthetic */ UsbState this$0;

    public UsbState$usbReceiver$1(UsbState usbState) {
        this.this$0 = usbState;
    }

    public final void onReceive(Context context, Intent intent) {
        if (intent != null) {
            UsbState usbState = this.this$0;
            BuildersKt.launch$default(usbState.coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new UsbState$usbReceiver$1$onReceive$1$1(intent.getBooleanExtra("connected", false), usbState, (Continuation) null), 3);
        }
    }
}
