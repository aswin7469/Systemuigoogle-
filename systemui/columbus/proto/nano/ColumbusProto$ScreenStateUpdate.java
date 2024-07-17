package com.google.android.systemui.columbus.proto.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ColumbusProto$ScreenStateUpdate extends MessageNano {
    public int screenState = 0;

    public ColumbusProto$ScreenStateUpdate() {
        this.cachedSize = -1;
    }

    public final int computeSerializedSize() {
        int i = this.screenState;
        if (i != 0) {
            return CodedOutputByteBufferNano.computeInt32Size(1, i);
        }
        return 0;
    }

    public final MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) {
        while (true) {
            int readTag = codedInputByteBufferNano.readTag();
            if (readTag == 0) {
                break;
            } else if (readTag == 8) {
                int readRawVarint32 = codedInputByteBufferNano.readRawVarint32();
                if (readRawVarint32 == 0 || readRawVarint32 == 1 || readRawVarint32 == 2) {
                    this.screenState = readRawVarint32;
                }
            } else if (!codedInputByteBufferNano.skipField(readTag)) {
                break;
            }
        }
        return this;
    }

    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        int i = this.screenState;
        if (i != 0) {
            codedOutputByteBufferNano.writeInt32(1, i);
        }
    }
}
