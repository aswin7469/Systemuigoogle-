package com.google.android.systemui.elmyra;

import android.os.Binder;
import com.android.systemui.Dumpable;
import com.google.android.systemui.elmyra.proto.nano.ChassisProtos$SensorEvent;
import com.google.android.systemui.elmyra.proto.nano.SnapshotProtos$Event;
import com.google.android.systemui.elmyra.proto.nano.SnapshotProtos$Snapshot;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class SnapshotLogger implements Dumpable {
    public final int mSnapshotCapacity;
    public final List mSnapshots;

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class Snapshot {
        public final SnapshotProtos$Snapshot mSnapshot;
        public final long mTimestamp;

        public Snapshot(SnapshotProtos$Snapshot snapshotProtos$Snapshot, long j) {
            this.mSnapshot = snapshotProtos$Snapshot;
            this.mTimestamp = j;
        }
    }

    public SnapshotLogger(int i) {
        this.mSnapshotCapacity = i;
        this.mSnapshots = new ArrayList(i);
    }

    public final void dump(PrintWriter printWriter, String[] strArr) {
        long clearCallingIdentity = Binder.clearCallingIdentity();
        try {
            dumpInternal(printWriter);
        } finally {
            Binder.restoreCallingIdentity(clearCallingIdentity);
        }
    }

    public final void dumpInternal(PrintWriter printWriter) {
        ChassisProtos$SensorEvent chassisProtos$SensorEvent;
        int i;
        printWriter.println("Dumping Elmyra Snapshots");
        int i2 = 0;
        while (true) {
            List list = this.mSnapshots;
            if (i2 < ((ArrayList) list).size()) {
                SnapshotProtos$Snapshot snapshotProtos$Snapshot = ((Snapshot) ((ArrayList) list).get(i2)).mSnapshot;
                printWriter.println("SystemTime: " + ((Snapshot) ((ArrayList) list).get(i2)).mTimestamp);
                printWriter.println("Snapshot: " + i2);
                printWriter.print("header {");
                printWriter.print("  identifier: " + snapshotProtos$Snapshot.header.identifier);
                printWriter.print("  gesture_type: " + snapshotProtos$Snapshot.header.gestureType);
                printWriter.print("  feedback: " + snapshotProtos$Snapshot.header.feedback);
                printWriter.print("}");
                for (int i3 = 0; i3 < snapshotProtos$Snapshot.events.length; i3++) {
                    printWriter.print("events {");
                    SnapshotProtos$Event snapshotProtos$Event = snapshotProtos$Snapshot.events[i3];
                    int i4 = snapshotProtos$Event.typesCase_;
                    if (i4 == 2) {
                        StringBuilder sb = new StringBuilder("  gesture_stage: ");
                        SnapshotProtos$Event snapshotProtos$Event2 = snapshotProtos$Snapshot.events[i3];
                        if (snapshotProtos$Event2.typesCase_ == 2) {
                            i = ((Integer) snapshotProtos$Event2.types_).intValue();
                        } else {
                            i = 0;
                        }
                        sb.append(i);
                        printWriter.print(sb.toString());
                    } else if (i4 == 1) {
                        if (i4 == 1) {
                            chassisProtos$SensorEvent = (ChassisProtos$SensorEvent) snapshotProtos$Event.types_;
                        } else {
                            chassisProtos$SensorEvent = null;
                        }
                        printWriter.print("  sensor_event {");
                        printWriter.print("    timestamp: " + chassisProtos$SensorEvent.timestamp);
                        for (int i5 = 0; i5 < chassisProtos$SensorEvent.values.length; i5++) {
                            printWriter.print("    values: " + chassisProtos$SensorEvent.values[i5]);
                        }
                        printWriter.print("  }");
                    }
                    printWriter.print("}");
                }
                printWriter.println("sensitivity_setting: " + snapshotProtos$Snapshot.sensitivitySetting);
                printWriter.println();
                i2++;
            } else {
                list.clear();
                printWriter.println("Finished Dumping Elmyra Snapshots");
                return;
            }
        }
    }
}
