package com.google.android.systemui.columbus.legacy.sensors;

import android.app.ambientcontext.AmbientContextManager;
import com.google.android.systemui.columbus.ColumbusEvent;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
