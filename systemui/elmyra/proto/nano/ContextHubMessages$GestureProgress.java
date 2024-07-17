package com.google.android.systemui.elmyra.proto.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
