package com.google.android.systemui.elmyra.proto.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
