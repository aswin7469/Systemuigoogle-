package com.google.android.systemui.columbus.legacy.sensors;

import android.util.SparseLongArray;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.Dumpable;
import com.android.systemui.statusbar.commandline.Command;
import com.android.systemui.statusbar.commandline.CommandRegistry;
import com.google.android.systemui.columbus.legacy.ColumbusService$gestureListener$1;
import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class GestureController implements Dumpable {
    public ColumbusService$gestureListener$1 gestureListener;
    public final GestureSensor gestureSensor;
    public final GestureController$gestureSensorListener$1 gestureSensorListener;
    public final SparseLongArray lastTimestampMap = new SparseLongArray();
    public long softGateBlockCount;
    public final GestureController$softGateListener$1 softGateListener;
    public final Set softGates;
    public final UiEventLogger uiEventLogger;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class ColumbusCommand implements Command {
        public ColumbusCommand() {
        }

        public final void execute(PrintWriter printWriter, List list) {
            if (list.isEmpty()) {
                printWriter.println("usage: quick-tap <command>");
                printWriter.println("Available commands:");
                printWriter.println("  trigger");
            } else if (Intrinsics.areEqual((String) list.get(0), "trigger")) {
                GestureController.this.gestureSensorListener.onGestureDetected(1, (GestureSensor.DetectionProperties) null);
            } else {
                printWriter.println("usage: quick-tap <command>");
                printWriter.println("Available commands:");
                printWriter.println("  trigger");
            }
        }
    }

    /* JADX WARNING: type inference failed for: r4v1, types: [java.lang.Object, com.google.android.systemui.columbus.legacy.sensors.GestureController$softGateListener$1] */
    public GestureController(GestureSensor gestureSensor2, Set set, CommandRegistry commandRegistry, UiEventLogger uiEventLogger2) {
        this.gestureSensor = gestureSensor2;
        this.softGates = set;
        this.uiEventLogger = uiEventLogger2;
        GestureController$gestureSensorListener$1 gestureController$gestureSensorListener$1 = new GestureController$gestureSensorListener$1(this);
        this.gestureSensorListener = gestureController$gestureSensorListener$1;
        this.softGateListener = new Object();
        gestureSensor2.setGestureListener(gestureController$gestureSensorListener$1);
        commandRegistry.registerCommand("quick-tap", new Function0() {
            public final Object invoke() {
                return new ColumbusCommand();
            }
        });
    }

    public final void dump(PrintWriter printWriter, String[] strArr) {
        long j = this.softGateBlockCount;
        printWriter.println("  Soft Blocks: " + j);
        StringBuilder sb = new StringBuilder("  Gesture Sensor: ");
        GestureSensor gestureSensor2 = this.gestureSensor;
        sb.append(gestureSensor2);
        printWriter.println(sb.toString());
        if (gestureSensor2 instanceof Dumpable) {
            ((Dumpable) gestureSensor2).dump(printWriter, strArr);
        }
    }
}
