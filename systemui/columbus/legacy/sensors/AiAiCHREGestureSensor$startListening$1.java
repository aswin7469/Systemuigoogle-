package com.google.android.systemui.columbus.legacy.sensors;

import android.app.ambientcontext.AmbientContextEventRequest;
import android.app.ambientcontext.AmbientContextManager;
import com.google.android.systemui.columbus.ColumbusEvent;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
