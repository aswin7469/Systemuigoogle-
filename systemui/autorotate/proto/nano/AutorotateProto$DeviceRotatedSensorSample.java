package com.google.android.systemui.autorotate.proto.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class AutorotateProto$DeviceRotatedSensorSample extends MessageNano {
    public static volatile AutorotateProto$DeviceRotatedSensorSample[] _emptyArray;
    public int sensorType = 0;
    public int timestampOffset = 0;
    public float xValue = 0.0f;
    public float yValue = 0.0f;
    public float zValue = 0.0f;

    public AutorotateProto$DeviceRotatedSensorSample() {
        this.cachedSize = -1;
    }

    public final int computeSerializedSize() {
        int i;
        int i2 = this.timestampOffset;
        if (i2 != 0) {
            i = CodedOutputByteBufferNano.computeInt32Size(1, i2);
        } else {
            i = 0;
        }
        int i3 = this.sensorType;
        if (i3 != 0) {
            i += CodedOutputByteBufferNano.computeInt32Size(2, i3);
        }
        if (Float.floatToIntBits(this.xValue) != Float.floatToIntBits(0.0f)) {
            i += CodedOutputByteBufferNano.computeFloatSize(3);
        }
        if (Float.floatToIntBits(this.yValue) != Float.floatToIntBits(0.0f)) {
            i += CodedOutputByteBufferNano.computeFloatSize(4);
        }
        if (Float.floatToIntBits(this.zValue) != Float.floatToIntBits(0.0f)) {
            return i + CodedOutputByteBufferNano.computeFloatSize(5);
        }
        return i;
    }

    public final MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) {
        while (true) {
            int readTag = codedInputByteBufferNano.readTag();
            if (readTag == 0) {
                break;
            } else if (readTag == 8) {
                this.timestampOffset = codedInputByteBufferNano.readRawVarint32();
            } else if (readTag == 16) {
                int readRawVarint32 = codedInputByteBufferNano.readRawVarint32();
                if (readRawVarint32 == 0 || readRawVarint32 == 1 || readRawVarint32 == 2) {
                    this.sensorType = readRawVarint32;
                }
            } else if (readTag == 29) {
                this.xValue = codedInputByteBufferNano.readFloat();
            } else if (readTag == 37) {
                this.yValue = codedInputByteBufferNano.readFloat();
            } else if (readTag == 45) {
                this.zValue = codedInputByteBufferNano.readFloat();
            } else if (!codedInputByteBufferNano.skipField(readTag)) {
                break;
            }
        }
        return this;
    }

    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        int i = this.timestampOffset;
        if (i != 0) {
            codedOutputByteBufferNano.writeInt32(1, i);
        }
        int i2 = this.sensorType;
        if (i2 != 0) {
            codedOutputByteBufferNano.writeInt32(2, i2);
        }
        if (Float.floatToIntBits(this.xValue) != Float.floatToIntBits(0.0f)) {
            codedOutputByteBufferNano.writeFloat(3, this.xValue);
        }
        if (Float.floatToIntBits(this.yValue) != Float.floatToIntBits(0.0f)) {
            codedOutputByteBufferNano.writeFloat(4, this.yValue);
        }
        if (Float.floatToIntBits(this.zValue) != Float.floatToIntBits(0.0f)) {
            codedOutputByteBufferNano.writeFloat(5, this.zValue);
        }
    }
}
