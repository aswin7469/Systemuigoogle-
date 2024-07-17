package com.google.android.systemui.columbus.proto.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ColumbusProto$NanoappEvents extends MessageNano {
    public ColumbusProto$NanoappEvent[] batchedEvents;

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.protobuf.nano.MessageNano, com.google.android.systemui.columbus.proto.nano.ColumbusProto$NanoappEvents] */
    public static ColumbusProto$NanoappEvents parseFrom(byte[] bArr) {
        ? messageNano = new MessageNano();
        if (ColumbusProto$NanoappEvent._emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                try {
                    if (ColumbusProto$NanoappEvent._emptyArray == null) {
                        ColumbusProto$NanoappEvent._emptyArray = new ColumbusProto$NanoappEvent[0];
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        messageNano.batchedEvents = ColumbusProto$NanoappEvent._emptyArray;
        messageNano.cachedSize = -1;
        return (ColumbusProto$NanoappEvents) MessageNano.mergeFrom(messageNano, bArr);
    }

    public final int computeSerializedSize() {
        ColumbusProto$NanoappEvent[] columbusProto$NanoappEventArr = this.batchedEvents;
        int i = 0;
        if (columbusProto$NanoappEventArr == null || columbusProto$NanoappEventArr.length <= 0) {
            return 0;
        }
        int i2 = 0;
        while (true) {
            ColumbusProto$NanoappEvent[] columbusProto$NanoappEventArr2 = this.batchedEvents;
            if (i >= columbusProto$NanoappEventArr2.length) {
                return i2;
            }
            ColumbusProto$NanoappEvent columbusProto$NanoappEvent = columbusProto$NanoappEventArr2[i];
            if (columbusProto$NanoappEvent != null) {
                i2 = CodedOutputByteBufferNano.computeMessageSize(1, columbusProto$NanoappEvent) + i2;
            }
            i++;
        }
    }

    public final MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) {
        int i;
        while (true) {
            int readTag = codedInputByteBufferNano.readTag();
            if (readTag == 0) {
                break;
            } else if (readTag == 10) {
                int repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 10);
                ColumbusProto$NanoappEvent[] columbusProto$NanoappEventArr = this.batchedEvents;
                if (columbusProto$NanoappEventArr == null) {
                    i = 0;
                } else {
                    i = columbusProto$NanoappEventArr.length;
                }
                int i2 = repeatedFieldArrayLength + i;
                ColumbusProto$NanoappEvent[] columbusProto$NanoappEventArr2 = new ColumbusProto$NanoappEvent[i2];
                if (i != 0) {
                    System.arraycopy(columbusProto$NanoappEventArr, 0, columbusProto$NanoappEventArr2, 0, i);
                }
                while (i < i2 - 1) {
                    ColumbusProto$NanoappEvent columbusProto$NanoappEvent = new ColumbusProto$NanoappEvent();
                    columbusProto$NanoappEventArr2[i] = columbusProto$NanoappEvent;
                    codedInputByteBufferNano.readMessage(columbusProto$NanoappEvent);
                    codedInputByteBufferNano.readTag();
                    i++;
                }
                ColumbusProto$NanoappEvent columbusProto$NanoappEvent2 = new ColumbusProto$NanoappEvent();
                columbusProto$NanoappEventArr2[i] = columbusProto$NanoappEvent2;
                codedInputByteBufferNano.readMessage(columbusProto$NanoappEvent2);
                this.batchedEvents = columbusProto$NanoappEventArr2;
            } else if (!codedInputByteBufferNano.skipField(readTag)) {
                break;
            }
        }
        return this;
    }

    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        ColumbusProto$NanoappEvent[] columbusProto$NanoappEventArr = this.batchedEvents;
        if (columbusProto$NanoappEventArr != null && columbusProto$NanoappEventArr.length > 0) {
            int i = 0;
            while (true) {
                ColumbusProto$NanoappEvent[] columbusProto$NanoappEventArr2 = this.batchedEvents;
                if (i < columbusProto$NanoappEventArr2.length) {
                    ColumbusProto$NanoappEvent columbusProto$NanoappEvent = columbusProto$NanoappEventArr2[i];
                    if (columbusProto$NanoappEvent != null) {
                        codedOutputByteBufferNano.writeMessage(1, columbusProto$NanoappEvent);
                    }
                    i++;
                } else {
                    return;
                }
            }
        }
    }
}
