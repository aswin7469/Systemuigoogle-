package com.google.android.systemui.columbus.legacy.sensors;

import android.app.ambientcontext.AmbientContextEventRequest;
import android.app.ambientcontext.AmbientContextManager;
import android.os.Handler;
import android.util.Log;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.util.time.SystemClock;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class AiAiCHREGestureSensor extends GestureSensor {
    public final AiAiCHREGestureSensor$ambientContextCallback$1 ambientContextCallback;
    public final AmbientContextManager ambientContextManager;
    public final Handler bgHandler;
    public boolean isListening;
    public final Executor mainExecutor;
    public final UiEventLogger uiEventLogger;

    public AiAiCHREGestureSensor(UiEventLogger uiEventLogger2, AmbientContextManager ambientContextManager2, Handler handler, Executor executor, SystemClock systemClock) {
        this.uiEventLogger = uiEventLogger2;
        this.ambientContextManager = ambientContextManager2;
        this.bgHandler = handler;
        this.mainExecutor = executor;
        this.ambientContextCallback = new AiAiCHREGestureSensor$ambientContextCallback$1(systemClock, this);
    }

    public final boolean isListening() {
        return this.isListening;
    }

    public final void startListening() {
        this.isListening = true;
        Log.i("Columbus/GestureSensor", "startListening with AmbientContextManager.registerObserver");
        AmbientContextEventRequest build = new AmbientContextEventRequest.Builder().addEventType(3).build();
        if (this.ambientContextManager == null) {
            Log.e("Columbus/GestureSensor", "AmbientContextManager not found.");
        } else {
            this.bgHandler.post(new AiAiCHREGestureSensor$startListening$1(this, build));
        }
    }

    public final void stopListening() {
        Log.i("Columbus/GestureSensor", "stopListening with AmbientContextManager.unregisterObserver");
        if (this.ambientContextManager == null) {
            Log.e("Columbus/GestureSensor", "AmbientContextManager not found.");
            return;
        }
        this.bgHandler.post(new AiAiCHREGestureSensor$stopListening$1(this));
        this.isListening = false;
    }
}
