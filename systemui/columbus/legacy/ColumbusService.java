package com.google.android.systemui.columbus.legacy;

import android.os.PowerManager;
import android.util.Log;
import com.android.systemui.Dumpable;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import com.google.android.systemui.columbus.legacy.PowerManagerWrapper;
import com.google.android.systemui.columbus.legacy.actions.Action;
import com.google.android.systemui.columbus.legacy.feedback.FeedbackEffect;
import com.google.android.systemui.columbus.legacy.gates.Gate;
import com.google.android.systemui.columbus.legacy.sensors.GestureController;
import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ColumbusService implements Dumpable {
    public final ColumbusService$actionListener$1 actionListener;
    public final List actions;
    public final DelayableExecutor delayableExecutor;
    public final Set effects;
    public final ColumbusService$gateListener$1 gateListener;
    public final Set gates;
    public final GestureController gestureController;
    public final ColumbusService$gestureListener$1 gestureListener;
    public Action lastActiveAction;
    public long lastStateChangeTimestamp;
    public ExecutorImpl.ExecutionToken removeStartListeningRunnable;
    public ExecutorImpl.ExecutionToken removeStopListeningRunnable;
    public final ColumbusService$stopListeningRunnable$1 startListeningRunnable;
    public final ColumbusService$stopListeningRunnable$1 stopListeningRunnable;
    public final SystemClock systemClock;
    public final PowerManagerWrapper.WakeLockWrapper wakeLock;

    public ColumbusService(List list, Set set, Set set2, GestureController gestureController2, PowerManagerWrapper powerManagerWrapper, DelayableExecutor delayableExecutor2, SystemClock systemClock2) {
        PowerManager.WakeLock wakeLock2;
        this.actions = list;
        this.effects = set;
        this.gates = set2;
        this.gestureController = gestureController2;
        this.delayableExecutor = delayableExecutor2;
        this.systemClock = systemClock2;
        PowerManager powerManager = powerManagerWrapper.powerManager;
        if (powerManager != null) {
            wakeLock2 = powerManager.newWakeLock(1, "Columbus/Service");
        } else {
            wakeLock2 = null;
        }
        this.wakeLock = new PowerManagerWrapper.WakeLockWrapper(wakeLock2);
        this.actionListener = new ColumbusService$actionListener$1(this);
        this.gateListener = new ColumbusService$gateListener$1(this);
        this.gestureListener = new ColumbusService$gestureListener$1(this);
        this.startListeningRunnable = new ColumbusService$stopListeningRunnable$1(this, 1);
        this.stopListeningRunnable = new ColumbusService$stopListeningRunnable$1(this, 0);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((Action) it.next()).listeners.add(this.actionListener);
        }
        this.gestureController.gestureListener = this.gestureListener;
        updateSensorListener();
    }

    public final void dump(PrintWriter printWriter, String[] strArr) {
        String str;
        String str2;
        printWriter.println("ColumbusService state:");
        printWriter.println("  Gates:");
        Iterator it = this.gates.iterator();
        while (true) {
            str = "O ";
            if (!it.hasNext()) {
                break;
            }
            Gate gate = (Gate) it.next();
            printWriter.print("    ");
            if (gate.isActive()) {
                if (gate.isBlocking()) {
                    str = "X ";
                }
                printWriter.print(str);
            } else {
                printWriter.print("- ");
            }
            printWriter.println(gate.toString());
        }
        printWriter.println("  Actions:");
        for (Action action : this.actions) {
            printWriter.print("    ");
            if (action.isAvailable) {
                str2 = str;
            } else {
                str2 = "X ";
            }
            printWriter.print(str2);
            printWriter.println(action.toString());
        }
        Action action2 = this.lastActiveAction;
        printWriter.println("  Active: " + action2);
        printWriter.println("  Feedback Effects:");
        for (FeedbackEffect obj : this.effects) {
            printWriter.print("    ");
            printWriter.println(obj.toString());
        }
        this.gestureController.dump(printWriter, strArr);
    }

    public final void stopListening$1() {
        ExecutorImpl.ExecutionToken executionToken = this.removeStartListeningRunnable;
        if (executionToken != null) {
            executionToken.run();
        }
        this.removeStartListeningRunnable = null;
        ExecutorImpl.ExecutionToken executionToken2 = this.removeStopListeningRunnable;
        if (executionToken2 != null) {
            executionToken2.run();
        }
        ((SystemClockImpl) this.systemClock).getClass();
        this.removeStopListeningRunnable = this.delayableExecutor.executeDelayed(Math.max(0, 1000 - (android.os.SystemClock.elapsedRealtime() - this.lastStateChangeTimestamp)), this.stopListeningRunnable);
    }

    public final Action updateActiveAction() {
        Object obj;
        Iterator it = this.actions.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (((Action) obj).isAvailable) {
                break;
            }
        }
        Action action = (Action) obj;
        Action action2 = this.lastActiveAction;
        if (!(action2 == null || action == action2)) {
            Log.i("Columbus/Service", "Switching action from " + action2 + " to " + action);
            action2.onGestureDetected(0, (GestureSensor.DetectionProperties) null);
        }
        this.lastActiveAction = action;
        return action;
    }

    public final void updateSensorListener() {
        Object obj;
        Action updateActiveAction = updateActiveAction();
        ColumbusService$gateListener$1 columbusService$gateListener$1 = this.gateListener;
        Set<Gate> set = this.gates;
        if (updateActiveAction == null) {
            Log.i("Columbus/Service", "No available actions");
            for (Gate unregisterListener : set) {
                unregisterListener.unregisterListener(columbusService$gateListener$1);
            }
            stopListening$1();
            return;
        }
        Iterable<Gate> iterable = set;
        for (Gate registerListener : iterable) {
            registerListener.registerListener(columbusService$gateListener$1);
        }
        Iterator it = iterable.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (((Gate) obj).isBlocking()) {
                break;
            }
        }
        Gate gate = (Gate) obj;
        if (gate != null) {
            Log.i("Columbus/Service", "Gated by " + gate);
            stopListening$1();
            return;
        }
        Log.i("Columbus/Service", "Unblocked; current action: " + updateActiveAction);
        ExecutorImpl.ExecutionToken executionToken = this.removeStopListeningRunnable;
        if (executionToken != null) {
            executionToken.run();
        }
        this.removeStopListeningRunnable = null;
        ExecutorImpl.ExecutionToken executionToken2 = this.removeStartListeningRunnable;
        if (executionToken2 != null) {
            executionToken2.run();
        }
        ((SystemClockImpl) this.systemClock).getClass();
        this.removeStartListeningRunnable = this.delayableExecutor.executeDelayed(Math.max(0, 1000 - (android.os.SystemClock.elapsedRealtime() - this.lastStateChangeTimestamp)), this.startListeningRunnable);
    }
}
