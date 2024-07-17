package com.google.android.systemui.elmyra.proto.nano;

import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ElmyraFilters$LowpassFilter extends MessageNano {
    public final /* synthetic */ int $r8$classId;
    public float cutoff;
    public float rate;

    public ElmyraFilters$LowpassFilter(int i) {
        this.$r8$classId = i;
        switch (i) {
            case 1:
                this.cutoff = 0.0f;
                this.rate = 0.0f;
                this.cachedSize = -1;
                return;
            default:
                this.cutoff = 0.0f;
                this.rate = 0.0f;
                this.cachedSize = -1;
                return;
        }
    }

    public final int computeSerializedSize() {
        int i;
        int i2;
        switch (this.$r8$classId) {
            case 0:
                if (Float.floatToIntBits(this.cutoff) != Float.floatToIntBits(0.0f)) {
                    i = CodedOutputByteBufferNano.computeFloatSize(1);
                } else {
                    i = 0;
                }
                if (Float.floatToIntBits(this.rate) != Float.floatToIntBits(0.0f)) {
                    return i + CodedOutputByteBufferNano.computeFloatSize(2);
                }
                return i;
            default:
                if (Float.floatToIntBits(this.cutoff) != Float.floatToIntBits(0.0f)) {
                    i2 = CodedOutputByteBufferNano.computeFloatSize(1);
                } else {
                    i2 = 0;
                }
                if (Float.floatToIntBits(this.rate) != Float.floatToIntBits(0.0f)) {
                    return i2 + CodedOutputByteBufferNano.computeFloatSize(2);
                }
                return i2;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001a, code lost:
        r2.rate = r3.readFloat();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0021, code lost:
        r2.cutoff = r3.readFloat();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0028, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0029, code lost:
        r0 = r3.readTag();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002d, code lost:
        if (r0 == 0) goto L_0x004c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0031, code lost:
        if (r0 == 13) goto L_0x0045;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0035, code lost:
        if (r0 == 21) goto L_0x003e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003b, code lost:
        if (r3.skipField(r0) != false) goto L_0x0029;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x003e, code lost:
        r2.rate = r3.readFloat();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0045, code lost:
        r2.cutoff = r3.readFloat();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004c, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0005, code lost:
        r0 = r3.readTag();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0009, code lost:
        if (r0 == 0) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000d, code lost:
        if (r0 == 13) goto L_0x0021;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0011, code lost:
        if (r0 == 21) goto L_0x001a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0017, code lost:
        if (r3.skipField(r0) != false) goto L_0x0005;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.protobuf.nano.MessageNano mergeFrom(com.google.protobuf.nano.CodedInputByteBufferNano r3) {
        /*
            r2 = this;
            int r0 = r2.$r8$classId
            switch(r0) {
                case 0: goto L_0x0029;
                default: goto L_0x0005;
            }
        L_0x0005:
            int r0 = r3.readTag()
            if (r0 == 0) goto L_0x0028
            r1 = 13
            if (r0 == r1) goto L_0x0021
            r1 = 21
            if (r0 == r1) goto L_0x001a
            boolean r0 = r3.skipField(r0)
            if (r0 != 0) goto L_0x0005
            goto L_0x0028
        L_0x001a:
            float r0 = r3.readFloat()
            r2.rate = r0
            goto L_0x0005
        L_0x0021:
            float r0 = r3.readFloat()
            r2.cutoff = r0
            goto L_0x0005
        L_0x0028:
            return r2
        L_0x0029:
            int r0 = r3.readTag()
            if (r0 == 0) goto L_0x004c
            r1 = 13
            if (r0 == r1) goto L_0x0045
            r1 = 21
            if (r0 == r1) goto L_0x003e
            boolean r0 = r3.skipField(r0)
            if (r0 != 0) goto L_0x0029
            goto L_0x004c
        L_0x003e:
            float r0 = r3.readFloat()
            r2.rate = r0
            goto L_0x0029
        L_0x0045:
            float r0 = r3.readFloat()
            r2.cutoff = r0
            goto L_0x0029
        L_0x004c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.elmyra.proto.nano.ElmyraFilters$LowpassFilter.mergeFrom(com.google.protobuf.nano.CodedInputByteBufferNano):com.google.protobuf.nano.MessageNano");
    }

    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        switch (this.$r8$classId) {
            case 0:
                if (Float.floatToIntBits(this.cutoff) != Float.floatToIntBits(0.0f)) {
                    codedOutputByteBufferNano.writeFloat(1, this.cutoff);
                }
                if (Float.floatToIntBits(this.rate) != Float.floatToIntBits(0.0f)) {
                    codedOutputByteBufferNano.writeFloat(2, this.rate);
                    return;
                }
                return;
            default:
                if (Float.floatToIntBits(this.cutoff) != Float.floatToIntBits(0.0f)) {
                    codedOutputByteBufferNano.writeFloat(1, this.cutoff);
                }
                if (Float.floatToIntBits(this.rate) != Float.floatToIntBits(0.0f)) {
                    codedOutputByteBufferNano.writeFloat(2, this.rate);
                    return;
                }
                return;
        }
    }
}
