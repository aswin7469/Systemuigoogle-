package com.google.android.systemui.elmyra.proto.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class SnapshotProtos$Event extends MessageNano {
    public static volatile SnapshotProtos$Event[] _emptyArray;
    public int typesCase_ = 0;
    public Object types_ = null;

    public SnapshotProtos$Event() {
        this.cachedSize = -1;
    }

    public final int computeSerializedSize() {
        int i;
        if (this.typesCase_ == 1) {
            i = CodedOutputByteBufferNano.computeMessageSize(1, (MessageNano) this.types_);
        } else {
            i = 0;
        }
        if (this.typesCase_ != 2) {
            return i;
        }
        int intValue = ((Integer) this.types_).intValue();
        return i + CodedOutputByteBufferNano.computeRawVarint32Size(intValue) + CodedOutputByteBufferNano.computeTagSize(2);
    }

    public final MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) {
        while (true) {
            int readTag = codedInputByteBufferNano.readTag();
            if (readTag == 0) {
                break;
            } else if (readTag == 10) {
                if (this.typesCase_ != 1) {
                    this.types_ = new ChassisProtos$SensorEvent();
                }
                codedInputByteBufferNano.readMessage((MessageNano) this.types_);
                this.typesCase_ = 1;
            } else if (readTag == 16) {
                this.types_ = Integer.valueOf(codedInputByteBufferNano.readRawVarint32());
                this.typesCase_ = 2;
            } else if (!codedInputByteBufferNano.skipField(readTag)) {
                break;
            }
        }
        return this;
    }

    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        if (this.typesCase_ == 1) {
            codedOutputByteBufferNano.writeMessage(1, (MessageNano) this.types_);
        }
        if (this.typesCase_ == 2) {
            int intValue = ((Integer) this.types_).intValue();
            codedOutputByteBufferNano.writeTag(2, 0);
            codedOutputByteBufferNano.writeRawVarint32(intValue);
        }
    }
}
