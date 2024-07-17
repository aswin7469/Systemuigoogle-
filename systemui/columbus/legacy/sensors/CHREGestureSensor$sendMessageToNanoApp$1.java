package com.google.android.systemui.columbus.legacy.sensors;

import android.hardware.location.ContextHubClient;
import android.hardware.location.NanoAppMessage;
import android.util.Log;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class CHREGestureSensor$sendMessageToNanoApp$1 implements Runnable {
    public final /* synthetic */ byte[] $bytes;
    public final /* synthetic */ int $messageType;
    public final /* synthetic */ Function0 $onFail;
    public final /* synthetic */ Function0 $onSuccess;
    public final /* synthetic */ CHREGestureSensor this$0;

    public CHREGestureSensor$sendMessageToNanoApp$1(CHREGestureSensor cHREGestureSensor, int i, byte[] bArr, Function0 function0, Function0 function02) {
        this.this$0 = cHREGestureSensor;
        this.$messageType = i;
        this.$bytes = bArr;
        this.$onFail = function0;
        this.$onSuccess = function02;
    }

    public final void run() {
        Integer num;
        if (this.this$0.contextHubClient == null) {
            Log.w("Columbus/GestureSensor", "ContextHubClient null");
            return;
        }
        NanoAppMessage createMessageToNanoApp = NanoAppMessage.createMessageToNanoApp(5147455389092024345L, this.$messageType, this.$bytes);
        ContextHubClient contextHubClient = this.this$0.contextHubClient;
        if (contextHubClient != null) {
            num = Integer.valueOf(contextHubClient.sendMessageToNanoApp(createMessageToNanoApp));
        } else {
            num = null;
        }
        if (num != null && num.intValue() == 0) {
            Function0 function0 = this.$onSuccess;
            if (function0 != null) {
                function0.invoke();
                return;
            }
            return;
        }
        int i = this.$messageType;
        Log.e("Columbus/GestureSensor", "Unable to send message " + i + " to nanoapp, error code " + num);
        Function0 function02 = this.$onFail;
        if (function02 != null) {
            function02.invoke();
        }
    }
}
