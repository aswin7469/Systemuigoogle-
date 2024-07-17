package com.google.android.systemui.elmyra.proto.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ElmyraAtoms$ElmyraSnapshot extends MessageNano {
    public ChassisProtos$Chassis chassis = null;
    public SnapshotProtos$Snapshot snapshot = null;

    public ElmyraAtoms$ElmyraSnapshot() {
        this.cachedSize = -1;
    }

    public final int computeSerializedSize() {
        int i;
        SnapshotProtos$Snapshot snapshotProtos$Snapshot = this.snapshot;
        if (snapshotProtos$Snapshot != null) {
            i = CodedOutputByteBufferNano.computeMessageSize(1, snapshotProtos$Snapshot);
        } else {
            i = 0;
        }
        ChassisProtos$Chassis chassisProtos$Chassis = this.chassis;
        if (chassisProtos$Chassis != null) {
            return i + CodedOutputByteBufferNano.computeMessageSize(2, chassisProtos$Chassis);
        }
        return i;
    }

    public final MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) {
        while (true) {
            int readTag = codedInputByteBufferNano.readTag();
            if (readTag == 0) {
                break;
            } else if (readTag == 10) {
                if (this.snapshot == null) {
                    this.snapshot = new SnapshotProtos$Snapshot();
                }
                codedInputByteBufferNano.readMessage(this.snapshot);
            } else if (readTag == 18) {
                if (this.chassis == null) {
                    this.chassis = new ChassisProtos$Chassis();
                }
                codedInputByteBufferNano.readMessage(this.chassis);
            } else if (!codedInputByteBufferNano.skipField(readTag)) {
                break;
            }
        }
        return this;
    }

    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        SnapshotProtos$Snapshot snapshotProtos$Snapshot = this.snapshot;
        if (snapshotProtos$Snapshot != null) {
            codedOutputByteBufferNano.writeMessage(1, snapshotProtos$Snapshot);
        }
        ChassisProtos$Chassis chassisProtos$Chassis = this.chassis;
        if (chassisProtos$Chassis != null) {
            codedOutputByteBufferNano.writeMessage(2, chassisProtos$Chassis);
        }
    }
}
