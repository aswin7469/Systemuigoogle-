package com.google.android.systemui.columbus.proto.nano;

import com.android.app.viewcapture.data.ViewNode;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ColumbusProto$NanoappEvent extends MessageNano {
    public static volatile ColumbusProto$NanoappEvent[] _emptyArray;
    public long timestamp = 0;
    public int type = 0;

    public ColumbusProto$NanoappEvent() {
        this.cachedSize = -1;
    }

    public final int computeSerializedSize() {
        int i;
        long j = this.timestamp;
        if (j != 0) {
            i = CodedOutputByteBufferNano.computeRawVarint64Size(j) + CodedOutputByteBufferNano.computeTagSize(1);
        } else {
            i = 0;
        }
        int i2 = this.type;
        if (i2 != 0) {
            return i + CodedOutputByteBufferNano.computeInt32Size(2, i2);
        }
        return i;
    }

    public final MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) {
        while (true) {
            int readTag = codedInputByteBufferNano.readTag();
            if (readTag == 0) {
                break;
            } else if (readTag != 8) {
                if (readTag == 16) {
                    int readRawVarint32 = codedInputByteBufferNano.readRawVarint32();
                    switch (readRawVarint32) {
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case ViewNode.WIDTH_FIELD_NUMBER:
                        case 8:
                            this.type = readRawVarint32;
                            break;
                    }
                } else if (!codedInputByteBufferNano.skipField(readTag)) {
                    break;
                }
            } else {
                this.timestamp = codedInputByteBufferNano.readRawVarint64();
            }
        }
        return this;
    }

    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        long j = this.timestamp;
        if (j != 0) {
            codedOutputByteBufferNano.writeTag(1, 0);
            codedOutputByteBufferNano.writeRawVarint64(j);
        }
        int i = this.type;
        if (i != 0) {
            codedOutputByteBufferNano.writeInt32(2, i);
        }
    }
}
