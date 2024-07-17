package com.google.android.systemui.elmyra.proto.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ChassisProtos$Chassis extends MessageNano {
    public ElmyraFilters$Filter[] defaultFilters;
    public ChassisProtos$Sensor[] sensors;

    public ChassisProtos$Chassis() {
        if (ChassisProtos$Sensor._emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                try {
                    if (ChassisProtos$Sensor._emptyArray == null) {
                        ChassisProtos$Sensor._emptyArray = new ChassisProtos$Sensor[0];
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        this.sensors = ChassisProtos$Sensor._emptyArray;
        this.defaultFilters = ElmyraFilters$Filter.emptyArray();
        this.cachedSize = -1;
    }

    public final int computeSerializedSize() {
        int i;
        ChassisProtos$Sensor[] chassisProtos$SensorArr = this.sensors;
        int i2 = 0;
        if (chassisProtos$SensorArr != null && chassisProtos$SensorArr.length > 0) {
            int i3 = 0;
            i = 0;
            while (true) {
                ChassisProtos$Sensor[] chassisProtos$SensorArr2 = this.sensors;
                if (i3 >= chassisProtos$SensorArr2.length) {
                    break;
                }
                ChassisProtos$Sensor chassisProtos$Sensor = chassisProtos$SensorArr2[i3];
                if (chassisProtos$Sensor != null) {
                    i += CodedOutputByteBufferNano.computeMessageSize(1, chassisProtos$Sensor);
                }
                i3++;
            }
        } else {
            i = 0;
        }
        ElmyraFilters$Filter[] elmyraFilters$FilterArr = this.defaultFilters;
        if (elmyraFilters$FilterArr != null && elmyraFilters$FilterArr.length > 0) {
            while (true) {
                ElmyraFilters$Filter[] elmyraFilters$FilterArr2 = this.defaultFilters;
                if (i2 >= elmyraFilters$FilterArr2.length) {
                    break;
                }
                ElmyraFilters$Filter elmyraFilters$Filter = elmyraFilters$FilterArr2[i2];
                if (elmyraFilters$Filter != null) {
                    i = CodedOutputByteBufferNano.computeMessageSize(2, elmyraFilters$Filter) + i;
                }
                i2++;
            }
        }
        return i;
    }

    public final MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) {
        int i;
        int i2;
        while (true) {
            int readTag = codedInputByteBufferNano.readTag();
            if (readTag == 0) {
                break;
            } else if (readTag == 10) {
                int repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 10);
                ChassisProtos$Sensor[] chassisProtos$SensorArr = this.sensors;
                if (chassisProtos$SensorArr == null) {
                    i = 0;
                } else {
                    i = chassisProtos$SensorArr.length;
                }
                int i3 = repeatedFieldArrayLength + i;
                ChassisProtos$Sensor[] chassisProtos$SensorArr2 = new ChassisProtos$Sensor[i3];
                if (i != 0) {
                    System.arraycopy(chassisProtos$SensorArr, 0, chassisProtos$SensorArr2, 0, i);
                }
                while (i < i3 - 1) {
                    ChassisProtos$Sensor chassisProtos$Sensor = new ChassisProtos$Sensor();
                    chassisProtos$SensorArr2[i] = chassisProtos$Sensor;
                    codedInputByteBufferNano.readMessage(chassisProtos$Sensor);
                    codedInputByteBufferNano.readTag();
                    i++;
                }
                ChassisProtos$Sensor chassisProtos$Sensor2 = new ChassisProtos$Sensor();
                chassisProtos$SensorArr2[i] = chassisProtos$Sensor2;
                codedInputByteBufferNano.readMessage(chassisProtos$Sensor2);
                this.sensors = chassisProtos$SensorArr2;
            } else if (readTag == 18) {
                int repeatedFieldArrayLength2 = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 18);
                ElmyraFilters$Filter[] elmyraFilters$FilterArr = this.defaultFilters;
                if (elmyraFilters$FilterArr == null) {
                    i2 = 0;
                } else {
                    i2 = elmyraFilters$FilterArr.length;
                }
                int i4 = repeatedFieldArrayLength2 + i2;
                ElmyraFilters$Filter[] elmyraFilters$FilterArr2 = new ElmyraFilters$Filter[i4];
                if (i2 != 0) {
                    System.arraycopy(elmyraFilters$FilterArr, 0, elmyraFilters$FilterArr2, 0, i2);
                }
                while (i2 < i4 - 1) {
                    ElmyraFilters$Filter elmyraFilters$Filter = new ElmyraFilters$Filter();
                    elmyraFilters$FilterArr2[i2] = elmyraFilters$Filter;
                    codedInputByteBufferNano.readMessage(elmyraFilters$Filter);
                    codedInputByteBufferNano.readTag();
                    i2++;
                }
                ElmyraFilters$Filter elmyraFilters$Filter2 = new ElmyraFilters$Filter();
                elmyraFilters$FilterArr2[i2] = elmyraFilters$Filter2;
                codedInputByteBufferNano.readMessage(elmyraFilters$Filter2);
                this.defaultFilters = elmyraFilters$FilterArr2;
            } else if (!codedInputByteBufferNano.skipField(readTag)) {
                break;
            }
        }
        return this;
    }

    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        ChassisProtos$Sensor[] chassisProtos$SensorArr = this.sensors;
        int i = 0;
        if (chassisProtos$SensorArr != null && chassisProtos$SensorArr.length > 0) {
            int i2 = 0;
            while (true) {
                ChassisProtos$Sensor[] chassisProtos$SensorArr2 = this.sensors;
                if (i2 >= chassisProtos$SensorArr2.length) {
                    break;
                }
                ChassisProtos$Sensor chassisProtos$Sensor = chassisProtos$SensorArr2[i2];
                if (chassisProtos$Sensor != null) {
                    codedOutputByteBufferNano.writeMessage(1, chassisProtos$Sensor);
                }
                i2++;
            }
        }
        ElmyraFilters$Filter[] elmyraFilters$FilterArr = this.defaultFilters;
        if (elmyraFilters$FilterArr != null && elmyraFilters$FilterArr.length > 0) {
            while (true) {
                ElmyraFilters$Filter[] elmyraFilters$FilterArr2 = this.defaultFilters;
                if (i < elmyraFilters$FilterArr2.length) {
                    ElmyraFilters$Filter elmyraFilters$Filter = elmyraFilters$FilterArr2[i];
                    if (elmyraFilters$Filter != null) {
                        codedOutputByteBufferNano.writeMessage(2, elmyraFilters$Filter);
                    }
                    i++;
                } else {
                    return;
                }
            }
        }
    }
}
