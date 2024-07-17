package com.google.android.systemui.elmyra.proto.nano;

import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ElmyraFilters$LowpassFilter extends MessageNano {
    public final /* synthetic */ int $r8$classId;
    public float cutoff;
    public float rate;

    public ElmyraFilters$LowpassFilter(int i) {
        this.$r8$classId = i;
        if (i != 1) {
            this.cutoff = 0.0f;
            this.rate = 0.0f;
            this.cachedSize = -1;
            return;
        }
        this.cutoff = 0.0f;
        this.rate = 0.0f;
        this.cachedSize = -1;
    }

    public final int computeSerializedSize() {
        int i = this.$r8$classId;
        int computeFloatSize = CodedOutputByteBufferNano.computeFloatSize(2);
        int i2 = 0;
        int computeFloatSize2 = CodedOutputByteBufferNano.computeFloatSize(1);
        switch (i) {
            case 0:
                if (Float.floatToIntBits(this.cutoff) != Float.floatToIntBits(0.0f)) {
                    i2 = computeFloatSize2;
                }
                if (Float.floatToIntBits(this.rate) != Float.floatToIntBits(0.0f)) {
                    return i2 + computeFloatSize;
                }
                return i2;
            default:
                if (Float.floatToIntBits(this.cutoff) != Float.floatToIntBits(0.0f)) {
                    i2 = computeFloatSize2;
                }
                if (Float.floatToIntBits(this.rate) != Float.floatToIntBits(0.0f)) {
                    return i2 + computeFloatSize;
                }
                return i2;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0028, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0029, code lost:
        r0 = r4.readTag();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002d, code lost:
        if (r0 == 0) goto L_0x0048;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002f, code lost:
        if (r0 == 13) goto L_0x0041;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0031, code lost:
        if (r0 == 21) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0037, code lost:
        if (r4.skipField(r0) != false) goto L_0x0029;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003a, code lost:
        r3.rate = r4.readFloat();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0041, code lost:
        r3.cutoff = r4.readFloat();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0048, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0009, code lost:
        r0 = r4.readTag();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000d, code lost:
        if (r0 == 0) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000f, code lost:
        if (r0 == 13) goto L_0x0021;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0011, code lost:
        if (r0 == 21) goto L_0x001a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0017, code lost:
        if (r4.skipField(r0) != false) goto L_0x0009;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001a, code lost:
        r3.rate = r4.readFloat();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0021, code lost:
        r3.cutoff = r4.readFloat();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.protobuf.nano.MessageNano mergeFrom(com.google.protobuf.nano.CodedInputByteBufferNano r4) {
        /*
            r3 = this;
            int r0 = r3.$r8$classId
            r1 = 21
            r2 = 13
            switch(r0) {
                case 0: goto L_0x0029;
                default: goto L_0x0009;
            }
        L_0x0009:
            int r0 = r4.readTag()
            if (r0 == 0) goto L_0x0028
            if (r0 == r2) goto L_0x0021
            if (r0 == r1) goto L_0x001a
            boolean r0 = r4.skipField(r0)
            if (r0 != 0) goto L_0x0009
            goto L_0x0028
        L_0x001a:
            float r0 = r4.readFloat()
            r3.rate = r0
            goto L_0x0009
        L_0x0021:
            float r0 = r4.readFloat()
            r3.cutoff = r0
            goto L_0x0009
        L_0x0028:
            return r3
        L_0x0029:
            int r0 = r4.readTag()
            if (r0 == 0) goto L_0x0048
            if (r0 == r2) goto L_0x0041
            if (r0 == r1) goto L_0x003a
            boolean r0 = r4.skipField(r0)
            if (r0 != 0) goto L_0x0029
            goto L_0x0048
        L_0x003a:
            float r0 = r4.readFloat()
            r3.rate = r0
            goto L_0x0029
        L_0x0041:
            float r0 = r4.readFloat()
            r3.cutoff = r0
            goto L_0x0029
        L_0x0048:
            return r3
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
