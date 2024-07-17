package com.google.android.systemui.elmyra.proto.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ContextHubMessages$RecognizerStart extends MessageNano {
    public float progressReportThreshold = 0.0f;
    public float sensitivity = 0.0f;

    public ContextHubMessages$RecognizerStart() {
        this.cachedSize = -1;
    }

    public final int computeSerializedSize() {
        int i;
        if (Float.floatToIntBits(this.progressReportThreshold) != Float.floatToIntBits(0.0f)) {
            i = CodedOutputByteBufferNano.computeFloatSize(1);
        } else {
            i = 0;
        }
        if (Float.floatToIntBits(this.sensitivity) != Float.floatToIntBits(0.0f)) {
            return i + CodedOutputByteBufferNano.computeFloatSize(2);
        }
        return i;
    }

    public final MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) {
        while (true) {
            int readTag = codedInputByteBufferNano.readTag();
            if (readTag == 0) {
                break;
            } else if (readTag == 13) {
                this.progressReportThreshold = codedInputByteBufferNano.readFloat();
            } else if (readTag == 21) {
                this.sensitivity = codedInputByteBufferNano.readFloat();
            } else if (!codedInputByteBufferNano.skipField(readTag)) {
                break;
            }
        }
        return this;
    }

    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        if (Float.floatToIntBits(this.progressReportThreshold) != Float.floatToIntBits(0.0f)) {
            codedOutputByteBufferNano.writeFloat(1, this.progressReportThreshold);
        }
        if (Float.floatToIntBits(this.sensitivity) != Float.floatToIntBits(0.0f)) {
            codedOutputByteBufferNano.writeFloat(2, this.sensitivity);
        }
    }
}
