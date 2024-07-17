package com.google.android.systemui.columbus.legacy.gates;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
