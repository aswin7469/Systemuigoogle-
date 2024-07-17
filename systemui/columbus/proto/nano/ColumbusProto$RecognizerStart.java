package com.google.android.systemui.columbus.proto.nano;

import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ColumbusProto$RecognizerStart extends MessageNano {
    public final /* synthetic */ int $r8$classId;
    public float sensitivity;

    public ColumbusProto$RecognizerStart(int i) {
        this.$r8$classId = i;
        switch (i) {
            case 1:
                this.sensitivity = 0.0f;
                this.cachedSize = -1;
                return;
            default:
                this.sensitivity = 0.0f;
                this.cachedSize = -1;
                return;
        }
    }

    public final int computeSerializedSize() {
        switch (this.$r8$classId) {
            case 0:
                if (Float.floatToIntBits(this.sensitivity) != Float.floatToIntBits(0.0f)) {
                    return CodedOutputByteBufferNano.computeFloatSize(1);
                }
                return 0;
            default:
                if (Float.floatToIntBits(this.sensitivity) != Float.floatToIntBits(0.0f)) {
                    return CodedOutputByteBufferNano.computeFloatSize(1);
                }
                return 0;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001e, code lost:
        r0 = r3.readTag();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0022, code lost:
        if (r0 == 0) goto L_0x0036;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0026, code lost:
        if (r0 == 13) goto L_0x002f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002c, code lost:
        if (r3.skipField(r0) != false) goto L_0x001e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002f, code lost:
        r2.sensitivity = r3.readFloat();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0036, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0005, code lost:
        r0 = r3.readTag();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0009, code lost:
        if (r0 == 0) goto L_0x001d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000d, code lost:
        if (r0 == 13) goto L_0x0016;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0013, code lost:
        if (r3.skipField(r0) != false) goto L_0x0005;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0016, code lost:
        r2.sensitivity = r3.readFloat();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001d, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.protobuf.nano.MessageNano mergeFrom(com.google.protobuf.nano.CodedInputByteBufferNano r3) {
        /*
            r2 = this;
            int r0 = r2.$r8$classId
            switch(r0) {
                case 0: goto L_0x001e;
                default: goto L_0x0005;
            }
        L_0x0005:
            int r0 = r3.readTag()
            if (r0 == 0) goto L_0x001d
            r1 = 13
            if (r0 == r1) goto L_0x0016
            boolean r0 = r3.skipField(r0)
            if (r0 != 0) goto L_0x0005
            goto L_0x001d
        L_0x0016:
            float r0 = r3.readFloat()
            r2.sensitivity = r0
            goto L_0x0005
        L_0x001d:
            return r2
        L_0x001e:
            int r0 = r3.readTag()
            if (r0 == 0) goto L_0x0036
            r1 = 13
            if (r0 == r1) goto L_0x002f
            boolean r0 = r3.skipField(r0)
            if (r0 != 0) goto L_0x001e
            goto L_0x0036
        L_0x002f:
            float r0 = r3.readFloat()
            r2.sensitivity = r0
            goto L_0x001e
        L_0x0036:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.columbus.proto.nano.ColumbusProto$RecognizerStart.mergeFrom(com.google.protobuf.nano.CodedInputByteBufferNano):com.google.protobuf.nano.MessageNano");
    }

    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        switch (this.$r8$classId) {
            case 0:
                if (Float.floatToIntBits(this.sensitivity) != Float.floatToIntBits(0.0f)) {
                    codedOutputByteBufferNano.writeFloat(1, this.sensitivity);
                    return;
                }
                return;
            default:
                if (Float.floatToIntBits(this.sensitivity) != Float.floatToIntBits(0.0f)) {
                    codedOutputByteBufferNano.writeFloat(1, this.sensitivity);
                    return;
                }
                return;
        }
    }
}
