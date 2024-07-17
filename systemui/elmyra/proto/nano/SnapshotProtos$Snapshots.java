package com.google.android.systemui.elmyra.proto.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class SnapshotProtos$Snapshots extends MessageNano {
    public SnapshotProtos$Snapshot[] snapshots;

    public SnapshotProtos$Snapshots() {
        if (SnapshotProtos$Snapshot._emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                try {
                    if (SnapshotProtos$Snapshot._emptyArray == null) {
                        SnapshotProtos$Snapshot._emptyArray = new SnapshotProtos$Snapshot[0];
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        this.snapshots = SnapshotProtos$Snapshot._emptyArray;
        this.cachedSize = -1;
    }

    public final int computeSerializedSize() {
        SnapshotProtos$Snapshot[] snapshotProtos$SnapshotArr = this.snapshots;
        int i = 0;
        if (snapshotProtos$SnapshotArr == null || snapshotProtos$SnapshotArr.length <= 0) {
            return 0;
        }
        int i2 = 0;
        while (true) {
            SnapshotProtos$Snapshot[] snapshotProtos$SnapshotArr2 = this.snapshots;
            if (i >= snapshotProtos$SnapshotArr2.length) {
                return i2;
            }
            SnapshotProtos$Snapshot snapshotProtos$Snapshot = snapshotProtos$SnapshotArr2[i];
            if (snapshotProtos$Snapshot != null) {
                i2 = CodedOutputByteBufferNano.computeMessageSize(1, snapshotProtos$Snapshot) + i2;
            }
            i++;
        }
    }

    public final MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) {
        int i;
        while (true) {
            int readTag = codedInputByteBufferNano.readTag();
            if (readTag == 0) {
                break;
            } else if (readTag == 10) {
                int repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 10);
                SnapshotProtos$Snapshot[] snapshotProtos$SnapshotArr = this.snapshots;
                if (snapshotProtos$SnapshotArr == null) {
                    i = 0;
                } else {
                    i = snapshotProtos$SnapshotArr.length;
                }
                int i2 = repeatedFieldArrayLength + i;
                SnapshotProtos$Snapshot[] snapshotProtos$SnapshotArr2 = new SnapshotProtos$Snapshot[i2];
                if (i != 0) {
                    System.arraycopy(snapshotProtos$SnapshotArr, 0, snapshotProtos$SnapshotArr2, 0, i);
                }
                while (i < i2 - 1) {
                    SnapshotProtos$Snapshot snapshotProtos$Snapshot = new SnapshotProtos$Snapshot();
                    snapshotProtos$SnapshotArr2[i] = snapshotProtos$Snapshot;
                    codedInputByteBufferNano.readMessage(snapshotProtos$Snapshot);
                    codedInputByteBufferNano.readTag();
                    i++;
                }
                SnapshotProtos$Snapshot snapshotProtos$Snapshot2 = new SnapshotProtos$Snapshot();
                snapshotProtos$SnapshotArr2[i] = snapshotProtos$Snapshot2;
                codedInputByteBufferNano.readMessage(snapshotProtos$Snapshot2);
                this.snapshots = snapshotProtos$SnapshotArr2;
            } else if (!codedInputByteBufferNano.skipField(readTag)) {
                break;
            }
        }
        return this;
    }

    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        SnapshotProtos$Snapshot[] snapshotProtos$SnapshotArr = this.snapshots;
        if (snapshotProtos$SnapshotArr != null && snapshotProtos$SnapshotArr.length > 0) {
            int i = 0;
            while (true) {
                SnapshotProtos$Snapshot[] snapshotProtos$SnapshotArr2 = this.snapshots;
                if (i < snapshotProtos$SnapshotArr2.length) {
                    SnapshotProtos$Snapshot snapshotProtos$Snapshot = snapshotProtos$SnapshotArr2[i];
                    if (snapshotProtos$Snapshot != null) {
                        codedOutputByteBufferNano.writeMessage(1, snapshotProtos$Snapshot);
                    }
                    i++;
                } else {
                    return;
                }
            }
        }
    }
}
