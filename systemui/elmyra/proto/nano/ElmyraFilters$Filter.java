package com.google.android.systemui.elmyra.proto.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ElmyraFilters$Filter extends MessageNano {
    public static volatile ElmyraFilters$Filter[] _emptyArray;
    public int parametersCase_ = 0;
    public MessageNano parameters_ = null;

    public ElmyraFilters$Filter() {
        this.cachedSize = -1;
    }

    public static ElmyraFilters$Filter[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                try {
                    if (_emptyArray == null) {
                        _emptyArray = new ElmyraFilters$Filter[0];
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return _emptyArray;
    }

    public final int computeSerializedSize() {
        int i;
        if (this.parametersCase_ == 1) {
            i = CodedOutputByteBufferNano.computeMessageSize(1, this.parameters_);
        } else {
            i = 0;
        }
        if (this.parametersCase_ == 2) {
            i += CodedOutputByteBufferNano.computeMessageSize(2, this.parameters_);
        }
        if (this.parametersCase_ == 3) {
            i += CodedOutputByteBufferNano.computeMessageSize(3, this.parameters_);
        }
        if (this.parametersCase_ == 4) {
            return i + CodedOutputByteBufferNano.computeMessageSize(4, this.parameters_);
        }
        return i;
    }

    public final MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) {
        while (true) {
            int readTag = codedInputByteBufferNano.readTag();
            if (readTag == 0) {
                break;
            } else if (readTag == 10) {
                if (this.parametersCase_ != 1) {
                    this.parameters_ = new ElmyraFilters$FIRFilter();
                }
                codedInputByteBufferNano.readMessage(this.parameters_);
                this.parametersCase_ = 1;
            } else if (readTag == 18) {
                if (this.parametersCase_ != 2) {
                    this.parameters_ = new ElmyraFilters$LowpassFilter(1);
                }
                codedInputByteBufferNano.readMessage(this.parameters_);
                this.parametersCase_ = 2;
            } else if (readTag == 26) {
                if (this.parametersCase_ != 3) {
                    this.parameters_ = new ElmyraFilters$LowpassFilter(0);
                }
                codedInputByteBufferNano.readMessage(this.parameters_);
                this.parametersCase_ = 3;
            } else if (readTag == 34) {
                if (this.parametersCase_ != 4) {
                    this.parameters_ = new ElmyraFilters$MedianFilter();
                }
                codedInputByteBufferNano.readMessage(this.parameters_);
                this.parametersCase_ = 4;
            } else if (!codedInputByteBufferNano.skipField(readTag)) {
                break;
            }
        }
        return this;
    }

    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        if (this.parametersCase_ == 1) {
            codedOutputByteBufferNano.writeMessage(1, this.parameters_);
        }
        if (this.parametersCase_ == 2) {
            codedOutputByteBufferNano.writeMessage(2, this.parameters_);
        }
        if (this.parametersCase_ == 3) {
            codedOutputByteBufferNano.writeMessage(3, this.parameters_);
        }
        if (this.parametersCase_ == 4) {
            codedOutputByteBufferNano.writeMessage(4, this.parameters_);
        }
    }
}
