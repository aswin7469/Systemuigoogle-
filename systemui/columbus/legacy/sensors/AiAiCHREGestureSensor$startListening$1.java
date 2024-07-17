package com.google.android.systemui.columbus.legacy.sensors;

import android.app.ambientcontext.AmbientContextEventRequest;
import android.app.ambientcontext.AmbientContextManager;
import com.google.android.systemui.columbus.ColumbusEvent;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class AiAiCHREGestureSensor$startListening$1 implements Runnable {
    public final /* synthetic */ AmbientContextEventRequest $request;
    public final /* synthetic */ AiAiCHREGestureSensor this$0;

    public AiAiCHREGestureSensor$startListening$1(AiAiCHREGestureSensor aiAiCHREGestureSensor, AmbientContextEventRequest ambientContextEventRequest) {
        this.this$0 = aiAiCHREGestureSensor;
        this.$request = ambientContextEventRequest;
    }

    public final void run() {
        AiAiCHREGestureSensor aiAiCHREGestureSensor = this.this$0;
        AmbientContextManager ambientContextManager = aiAiCHREGestureSensor.ambientContextManager;
        if (ambientContextManager != null) {
            ambientContextManager.registerObserver(this.$request, aiAiCHREGestureSensor.mainExecutor, aiAiCHREGestureSensor.ambientContextCallback);
        }
        this.this$0.uiEventLogger.log(ColumbusEvent.COLUMBUS_MODE_LOW_POWER_ACTIVE);
    }
}
