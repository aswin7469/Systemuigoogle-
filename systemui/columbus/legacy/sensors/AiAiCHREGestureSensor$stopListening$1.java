package com.google.android.systemui.columbus.legacy.sensors;

import android.app.ambientcontext.AmbientContextManager;
import com.google.android.systemui.columbus.ColumbusEvent;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class AiAiCHREGestureSensor$stopListening$1 implements Runnable {
    public final /* synthetic */ AiAiCHREGestureSensor this$0;

    public AiAiCHREGestureSensor$stopListening$1(AiAiCHREGestureSensor aiAiCHREGestureSensor) {
        this.this$0 = aiAiCHREGestureSensor;
    }

    public final void run() {
        AmbientContextManager ambientContextManager = this.this$0.ambientContextManager;
        if (ambientContextManager != null) {
            ambientContextManager.unregisterObserver();
        }
        this.this$0.uiEventLogger.log(ColumbusEvent.COLUMBUS_MODE_INACTIVE);
    }
}
