package com.google.android.systemui.elmyra.proto.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class SnapshotProtos$SnapshotHeader extends MessageNano {
    public int feedback = 0;
    public int gestureType = 0;
    public long identifier = 0;

    public SnapshotProtos$SnapshotHeader() {
        this.cachedSize = -1;
    }

    public final int computeSerializedSize() {
        int i;
        long j = this.identifier;
        if (j != 0) {
            i = CodedOutputByteBufferNano.computeInt64Size(j, 1);
        } else {
            i = 0;
        }
        int i2 = this.gestureType;
        if (i2 != 0) {
            i += CodedOutputByteBufferNano.computeInt32Size(2, i2);
        }
        int i3 = this.feedback;
        if (i3 != 0) {
            return i + CodedOutputByteBufferNano.computeInt32Size(3, i3);
        }
        return i;
    }

    public final MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) {
        while (true) {
            int readTag = codedInputByteBufferNano.readTag();
            if (readTag == 0) {
                break;
            } else if (readTag == 8) {
                this.identifier = codedInputByteBufferNano.readRawVarint64();
            } else if (readTag == 16) {
                int readRawVarint32 = codedInputByteBufferNano.readRawVarint32();
                if (readRawVarint32 == 0 || readRawVarint32 == 1 || readRawVarint32 == 2 || readRawVarint32 == 3 || readRawVarint32 == 4) {
                    this.gestureType = readRawVarint32;
                }
            } else if (readTag == 24) {
                int readRawVarint322 = codedInputByteBufferNano.readRawVarint32();
                if (readRawVarint322 == 0 || readRawVarint322 == 1 || readRawVarint322 == 2) {
                    this.feedback = readRawVarint322;
                }
            } else if (!codedInputByteBufferNano.skipField(readTag)) {
                break;
            }
        }
        return this;
    }

    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        long j = this.identifier;
        if (j != 0) {
            codedOutputByteBufferNano.writeInt64(j, 1);
        }
        int i = this.gestureType;
        if (i != 0) {
            codedOutputByteBufferNano.writeInt32(2, i);
        }
        int i2 = this.feedback;
        if (i2 != 0) {
            codedOutputByteBufferNano.writeInt32(3, i2);
        }
    }
}
