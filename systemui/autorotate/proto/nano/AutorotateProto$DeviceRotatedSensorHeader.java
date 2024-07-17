package com.google.android.systemui.autorotate.proto.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class AutorotateProto$DeviceRotatedSensorHeader extends MessageNano {
    public long timestampBase = 0;

    public AutorotateProto$DeviceRotatedSensorHeader() {
        this.cachedSize = -1;
    }

    public final int computeSerializedSize() {
        long j = this.timestampBase;
        if (j != 0) {
            return CodedOutputByteBufferNano.computeInt64Size(j, 1);
        }
        return 0;
    }

    public final MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) {
        while (true) {
            int readTag = codedInputByteBufferNano.readTag();
            if (readTag == 0) {
                break;
            } else if (readTag == 8) {
                this.timestampBase = codedInputByteBufferNano.readRawVarint64();
            } else if (!codedInputByteBufferNano.skipField(readTag)) {
                break;
            }
        }
        return this;
    }

    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        long j = this.timestampBase;
        if (j != 0) {
            codedOutputByteBufferNano.writeInt64(j, 1);
        }
    }
}
