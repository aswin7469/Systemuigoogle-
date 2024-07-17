package com.google.android.systemui.elmyra.proto.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ContextHubMessages$GestureDetected extends MessageNano {
    public boolean hapticConsumed;
    public boolean hostSuspended;

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.protobuf.nano.MessageNano, com.google.android.systemui.elmyra.proto.nano.ContextHubMessages$GestureDetected] */
    public static ContextHubMessages$GestureDetected parseFrom(byte[] bArr) {
        ? messageNano = new MessageNano();
        messageNano.hostSuspended = false;
        messageNano.hapticConsumed = false;
        messageNano.cachedSize = -1;
        return (ContextHubMessages$GestureDetected) MessageNano.mergeFrom(messageNano, bArr);
    }

    public final int computeSerializedSize() {
        int i;
        if (this.hostSuspended) {
            i = CodedOutputByteBufferNano.computeTagSize(1) + 1;
        } else {
            i = 0;
        }
        if (this.hapticConsumed) {
            return i + CodedOutputByteBufferNano.computeTagSize(2) + 1;
        }
        return i;
    }

    public final MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) {
        while (true) {
            int readTag = codedInputByteBufferNano.readTag();
            if (readTag == 0) {
                break;
            }
            boolean z = false;
            if (readTag == 8) {
                if (codedInputByteBufferNano.readRawVarint32() != 0) {
                    z = true;
                }
                this.hostSuspended = z;
            } else if (readTag == 16) {
                if (codedInputByteBufferNano.readRawVarint32() != 0) {
                    z = true;
                }
                this.hapticConsumed = z;
            } else if (!codedInputByteBufferNano.skipField(readTag)) {
                break;
            }
        }
        return this;
    }

    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        boolean z = this.hostSuspended;
        if (z) {
            codedOutputByteBufferNano.writeTag(1, 0);
            codedOutputByteBufferNano.writeRawByte(z ? 1 : 0);
        }
        boolean z2 = this.hapticConsumed;
        if (z2) {
            codedOutputByteBufferNano.writeTag(2, 0);
            codedOutputByteBufferNano.writeRawByte(z2 ? 1 : 0);
        }
    }
}
