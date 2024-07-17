package com.google.android.systemui.columbus.legacy.sensors;

import android.app.ambientcontext.AmbientContextManager;
import android.os.Handler;
import android.util.Log;
import com.android.systemui.Dumpable;
import com.google.android.systemui.columbus.legacy.sensors.CHREGestureSensorDelegator$startListening$1;
import dagger.Lazy;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class CHREGestureSensorDelegator extends GestureSensor implements Dumpable {
    public final Lazy aiAiCHREGestureSensor;
    public final AmbientContextManager ambientContextManager;
    public final Executor bgExecutor;
    public final Handler bgHandler;
    public final CHREGestureSensor chreGestureSensor;
    public GestureSensor gestureSensor;

    public CHREGestureSensorDelegator(AmbientContextManager ambientContextManager2, CHREGestureSensor cHREGestureSensor, Lazy lazy, Executor executor, Handler handler) {
        this.ambientContextManager = ambientContextManager2;
        this.chreGestureSensor = cHREGestureSensor;
        this.aiAiCHREGestureSensor = lazy;
        this.bgExecutor = executor;
        this.bgHandler = handler;
        this.gestureSensor = cHREGestureSensor;
    }

    public static final void access$switchSensor(CHREGestureSensorDelegator cHREGestureSensorDelegator, boolean z) {
        GestureSensor gestureSensor2;
        cHREGestureSensorDelegator.getClass();
        Log.i("Columbus/GestureSensor", "CHREGestureSensorDelegator switchSensor, AiAi = " + z);
        boolean isListening = cHREGestureSensorDelegator.gestureSensor.isListening();
        if (isListening) {
            cHREGestureSensorDelegator.gestureSensor.stopListening();
        }
        cHREGestureSensorDelegator.gestureSensor.setGestureListener((GestureController$gestureSensorListener$1) null);
        cHREGestureSensorDelegator.gestureSensor.close();
        if (z) {
            Object obj = cHREGestureSensorDelegator.aiAiCHREGestureSensor.get();
            Intrinsics.checkNotNull(obj);
            gestureSensor2 = (GestureSensor) obj;
        } else {
            gestureSensor2 = cHREGestureSensorDelegator.chreGestureSensor;
        }
        cHREGestureSensorDelegator.gestureSensor = gestureSensor2;
        gestureSensor2.setGestureListener(cHREGestureSensorDelegator.listener);
        if (isListening) {
            cHREGestureSensorDelegator.gestureSensor.startListening();
        }
    }

    public final void dump(PrintWriter printWriter, String[] strArr) {
        Dumpable dumpable;
        GestureSensor gestureSensor2 = this.gestureSensor;
        if (gestureSensor2 instanceof Dumpable) {
            dumpable = (Dumpable) gestureSensor2;
        } else {
            dumpable = null;
        }
        if (dumpable != null) {
            dumpable.dump(printWriter, strArr);
        }
    }

    public final boolean isListening() {
        return this.gestureSensor.isListening();
    }

    public final void setGestureListener(GestureController$gestureSensorListener$1 gestureController$gestureSensorListener$1) {
        GestureSensor gestureSensor2 = this.gestureSensor;
        Log.i("Columbus/GestureSensor", "CHREGestureSensorDelegator setGestureListener to " + gestureSensor2);
        this.listener = gestureController$gestureSensorListener$1;
        this.bgHandler.post(new CHREGestureSensorDelegator$startListening$1.AnonymousClass1(this, gestureController$gestureSensorListener$1));
    }

    public final void startListening() {
        AmbientContextManager ambientContextManager2 = this.ambientContextManager;
        if (ambientContextManager2 != null) {
            ambientContextManager2.queryAmbientContextServiceStatus(Collections.singleton(3), this.bgExecutor, new CHREGestureSensorDelegator$startListening$1(this));
        }
        GestureSensor gestureSensor2 = this.gestureSensor;
        Log.i("Columbus/GestureSensor", "CHREGestureSensorDelegator startListening, gestureSensor = " + gestureSensor2);
        this.bgHandler.post(new CHREGestureSensorDelegator$stopListening$1(this, 1));
    }

    public final void stopListening() {
        GestureSensor gestureSensor2 = this.gestureSensor;
        Log.i("Columbus/GestureSensor", "CHREGestureSensorDelegator stopListening, gestureSensor = " + gestureSensor2);
        this.bgHandler.post(new CHREGestureSensorDelegator$stopListening$1(this, 0));
    }
}
