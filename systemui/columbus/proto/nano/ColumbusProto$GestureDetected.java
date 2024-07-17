package com.google.android.systemui.columbus.proto.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ColumbusProto$GestureDetected extends MessageNano {
    public float[] featureVector;
    public int gestureType;

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.protobuf.nano.MessageNano, com.google.android.systemui.columbus.proto.nano.ColumbusProto$GestureDetected] */
    public static ColumbusProto$GestureDetected parseFrom(byte[] bArr) {
        ? messageNano = new MessageNano();
        messageNano.gestureType = 0;
        messageNano.featureVector = WireFormatNano.EMPTY_FLOAT_ARRAY;
        messageNano.cachedSize = -1;
        return (ColumbusProto$GestureDetected) MessageNano.mergeFrom(messageNano, bArr);
    }

    public final int computeSerializedSize() {
        int i;
        int i2 = this.gestureType;
        if (i2 != 0) {
            i = CodedOutputByteBufferNano.computeInt32Size(1, i2);
        } else {
            i = 0;
        }
        float[] fArr = this.featureVector;
        if (fArr == null || fArr.length <= 0) {
            return i;
        }
        return (fArr.length * 4) + i + fArr.length;
    }

    public final MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) {
        int i;
        int i2;
        while (true) {
            int readTag = codedInputByteBufferNano.readTag();
            if (readTag == 0) {
                break;
            } else if (readTag == 8) {
                int readRawVarint32 = codedInputByteBufferNano.readRawVarint32();
                if (readRawVarint32 == 0 || readRawVarint32 == 1 || readRawVarint32 == 2) {
                    this.gestureType = readRawVarint32;
                }
            } else if (readTag == 18) {
                int readRawVarint322 = codedInputByteBufferNano.readRawVarint32();
                int pushLimit = codedInputByteBufferNano.pushLimit(readRawVarint322);
                int i3 = readRawVarint322 / 4;
                float[] fArr = this.featureVector;
                if (fArr == null) {
                    i = 0;
                } else {
                    i = fArr.length;
                }
                int i4 = i3 + i;
                float[] fArr2 = new float[i4];
                if (i != 0) {
                    System.arraycopy(fArr, 0, fArr2, 0, i);
                }
                while (i < i4) {
                    fArr2[i] = codedInputByteBufferNano.readFloat();
                    i++;
                }
                this.featureVector = fArr2;
                codedInputByteBufferNano.currentLimit = pushLimit;
                int i5 = codedInputByteBufferNano.bufferSize + codedInputByteBufferNano.bufferSizeAfterLimit;
                codedInputByteBufferNano.bufferSize = i5;
                if (i5 > pushLimit) {
                    int i6 = i5 - pushLimit;
                    codedInputByteBufferNano.bufferSizeAfterLimit = i6;
                    codedInputByteBufferNano.bufferSize = i5 - i6;
                } else {
                    codedInputByteBufferNano.bufferSizeAfterLimit = 0;
                }
            } else if (readTag == 21) {
                int repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 21);
                float[] fArr3 = this.featureVector;
                if (fArr3 == null) {
                    i2 = 0;
                } else {
                    i2 = fArr3.length;
                }
                int i7 = repeatedFieldArrayLength + i2;
                float[] fArr4 = new float[i7];
                if (i2 != 0) {
                    System.arraycopy(fArr3, 0, fArr4, 0, i2);
                }
                while (i2 < i7 - 1) {
                    fArr4[i2] = codedInputByteBufferNano.readFloat();
                    codedInputByteBufferNano.readTag();
                    i2++;
                }
                fArr4[i2] = codedInputByteBufferNano.readFloat();
                this.featureVector = fArr4;
            } else if (!codedInputByteBufferNano.skipField(readTag)) {
                break;
            }
        }
        return this;
    }

    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        int i = this.gestureType;
        if (i != 0) {
            codedOutputByteBufferNano.writeInt32(1, i);
        }
        float[] fArr = this.featureVector;
        if (fArr != null && fArr.length > 0) {
            int i2 = 0;
            while (true) {
                float[] fArr2 = this.featureVector;
                if (i2 < fArr2.length) {
                    codedOutputByteBufferNano.writeFloat(2, fArr2[i2]);
                    i2++;
                } else {
                    return;
                }
            }
        }
    }
}
