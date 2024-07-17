package com.google.android.systemui.elmyra.proto.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
