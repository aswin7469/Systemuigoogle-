package com.google.android.systemui.elmyra.proto.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ContextHubMessages$GestureProgress extends MessageNano {
    public float progress;

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.protobuf.nano.MessageNano, com.google.android.systemui.elmyra.proto.nano.ContextHubMessages$GestureProgress] */
    public static ContextHubMessages$GestureProgress parseFrom(byte[] bArr) {
        ? messageNano = new MessageNano();
        messageNano.progress = 0.0f;
        messageNano.cachedSize = -1;
        return (ContextHubMessages$GestureProgress) MessageNano.mergeFrom(messageNano, bArr);
    }

    public final int computeSerializedSize() {
        if (Float.floatToIntBits(this.progress) != Float.floatToIntBits(0.0f)) {
            return CodedOutputByteBufferNano.computeFloatSize(1);
        }
        return 0;
    }

    public final MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) {
        while (true) {
            int readTag = codedInputByteBufferNano.readTag();
            if (readTag == 0) {
                break;
            } else if (readTag == 13) {
                this.progress = codedInputByteBufferNano.readFloat();
            } else if (!codedInputByteBufferNano.skipField(readTag)) {
                break;
            }
        }
        return this;
    }

    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        if (Float.floatToIntBits(this.progress) != Float.floatToIntBits(0.0f)) {
            codedOutputByteBufferNano.writeFloat(1, this.progress);
        }
    }
}
