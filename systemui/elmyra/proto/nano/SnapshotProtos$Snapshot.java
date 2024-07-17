package com.google.android.systemui.elmyra.proto.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class SnapshotProtos$Snapshot extends MessageNano {
    public static volatile SnapshotProtos$Snapshot[] _emptyArray;
    public SnapshotProtos$Event[] events;
    public SnapshotProtos$SnapshotHeader header = null;
    public float sensitivitySetting;

    public SnapshotProtos$Snapshot() {
        if (SnapshotProtos$Event._emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                try {
                    if (SnapshotProtos$Event._emptyArray == null) {
                        SnapshotProtos$Event._emptyArray = new SnapshotProtos$Event[0];
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        this.events = SnapshotProtos$Event._emptyArray;
        this.sensitivitySetting = 0.0f;
        this.cachedSize = -1;
    }

    public final int computeSerializedSize() {
        int i;
        SnapshotProtos$SnapshotHeader snapshotProtos$SnapshotHeader = this.header;
        int i2 = 0;
        if (snapshotProtos$SnapshotHeader != null) {
            i = CodedOutputByteBufferNano.computeMessageSize(1, snapshotProtos$SnapshotHeader);
        } else {
            i = 0;
        }
        SnapshotProtos$Event[] snapshotProtos$EventArr = this.events;
        if (snapshotProtos$EventArr != null && snapshotProtos$EventArr.length > 0) {
            while (true) {
                SnapshotProtos$Event[] snapshotProtos$EventArr2 = this.events;
                if (i2 >= snapshotProtos$EventArr2.length) {
                    break;
                }
                SnapshotProtos$Event snapshotProtos$Event = snapshotProtos$EventArr2[i2];
                if (snapshotProtos$Event != null) {
                    i = CodedOutputByteBufferNano.computeMessageSize(2, snapshotProtos$Event) + i;
                }
                i2++;
            }
        }
        if (Float.floatToIntBits(this.sensitivitySetting) != Float.floatToIntBits(0.0f)) {
            return i + CodedOutputByteBufferNano.computeFloatSize(3);
        }
        return i;
    }

    public final MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) {
        int i;
        while (true) {
            int readTag = codedInputByteBufferNano.readTag();
            if (readTag == 0) {
                break;
            } else if (readTag == 10) {
                if (this.header == null) {
                    this.header = new SnapshotProtos$SnapshotHeader();
                }
                codedInputByteBufferNano.readMessage(this.header);
            } else if (readTag == 18) {
                int repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 18);
                SnapshotProtos$Event[] snapshotProtos$EventArr = this.events;
                if (snapshotProtos$EventArr == null) {
                    i = 0;
                } else {
                    i = snapshotProtos$EventArr.length;
                }
                int i2 = repeatedFieldArrayLength + i;
                SnapshotProtos$Event[] snapshotProtos$EventArr2 = new SnapshotProtos$Event[i2];
                if (i != 0) {
                    System.arraycopy(snapshotProtos$EventArr, 0, snapshotProtos$EventArr2, 0, i);
                }
                while (i < i2 - 1) {
                    SnapshotProtos$Event snapshotProtos$Event = new SnapshotProtos$Event();
                    snapshotProtos$EventArr2[i] = snapshotProtos$Event;
                    codedInputByteBufferNano.readMessage(snapshotProtos$Event);
                    codedInputByteBufferNano.readTag();
                    i++;
                }
                SnapshotProtos$Event snapshotProtos$Event2 = new SnapshotProtos$Event();
                snapshotProtos$EventArr2[i] = snapshotProtos$Event2;
                codedInputByteBufferNano.readMessage(snapshotProtos$Event2);
                this.events = snapshotProtos$EventArr2;
            } else if (readTag == 29) {
                this.sensitivitySetting = codedInputByteBufferNano.readFloat();
            } else if (!codedInputByteBufferNano.skipField(readTag)) {
                break;
            }
        }
        return this;
    }

    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        SnapshotProtos$SnapshotHeader snapshotProtos$SnapshotHeader = this.header;
        if (snapshotProtos$SnapshotHeader != null) {
            codedOutputByteBufferNano.writeMessage(1, snapshotProtos$SnapshotHeader);
        }
        SnapshotProtos$Event[] snapshotProtos$EventArr = this.events;
        if (snapshotProtos$EventArr != null && snapshotProtos$EventArr.length > 0) {
            int i = 0;
            while (true) {
                SnapshotProtos$Event[] snapshotProtos$EventArr2 = this.events;
                if (i >= snapshotProtos$EventArr2.length) {
                    break;
                }
                SnapshotProtos$Event snapshotProtos$Event = snapshotProtos$EventArr2[i];
                if (snapshotProtos$Event != null) {
                    codedOutputByteBufferNano.writeMessage(2, snapshotProtos$Event);
                }
                i++;
            }
        }
        if (Float.floatToIntBits(this.sensitivitySetting) != Float.floatToIntBits(0.0f)) {
            codedOutputByteBufferNano.writeFloat(3, this.sensitivitySetting);
        }
    }
}
