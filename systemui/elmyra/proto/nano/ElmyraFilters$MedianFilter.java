package com.google.android.systemui.elmyra.proto.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ElmyraFilters$MedianFilter extends MessageNano {
    public int windowSize = 0;

    public ElmyraFilters$MedianFilter() {
        this.cachedSize = -1;
    }

    public final int computeSerializedSize() {
        int i = this.windowSize;
        if (i == 0) {
            return 0;
        }
        return CodedOutputByteBufferNano.computeRawVarint32Size(i) + CodedOutputByteBufferNano.computeTagSize(1);
    }

    public final MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) {
        while (true) {
            int readTag = codedInputByteBufferNano.readTag();
            if (readTag == 0) {
                break;
            } else if (readTag == 8) {
                this.windowSize = codedInputByteBufferNano.readRawVarint32();
            } else if (!codedInputByteBufferNano.skipField(readTag)) {
                break;
            }
        }
        return this;
    }

    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        int i = this.windowSize;
        if (i != 0) {
            codedOutputByteBufferNano.writeTag(1, 0);
            codedOutputByteBufferNano.writeRawVarint32(i);
        }
    }
}
