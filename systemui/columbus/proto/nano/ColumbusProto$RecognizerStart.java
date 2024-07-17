package com.google.android.systemui.columbus.proto.nano;

import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ColumbusProto$RecognizerStart extends MessageNano {
    public final /* synthetic */ int $r8$classId;
    public float sensitivity;

    public ColumbusProto$RecognizerStart(int i) {
        this.$r8$classId = i;
        if (i != 1) {
            this.sensitivity = 0.0f;
            this.cachedSize = -1;
            return;
        }
        this.sensitivity = 0.0f;
        this.cachedSize = -1;
    }

    public final int computeSerializedSize() {
        int i = this.$r8$classId;
        int computeFloatSize = CodedOutputByteBufferNano.computeFloatSize(1);
        switch (i) {
            case 0:
                if (Float.floatToIntBits(this.sensitivity) != Float.floatToIntBits(0.0f)) {
                    return computeFloatSize;
                }
                return 0;
            default:
                if (Float.floatToIntBits(this.sensitivity) != Float.floatToIntBits(0.0f)) {
                    return computeFloatSize;
                }
                return 0;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0022, code lost:
        if (r0 == 0) goto L_0x0034;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0024, code lost:
        if (r0 == 13) goto L_0x002d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002a, code lost:
        if (r3.skipField(r0) != false) goto L_0x001e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002d, code lost:
        r2.sensitivity = r3.readFloat();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0034, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0007, code lost:
        r0 = r3.readTag();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000b, code lost:
        if (r0 == 0) goto L_0x001d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000d, code lost:
        if (r0 == 13) goto L_0x0016;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0013, code lost:
        if (r3.skipField(r0) != false) goto L_0x0007;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0016, code lost:
        r2.sensitivity = r3.readFloat();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001d, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001e, code lost:
        r0 = r3.readTag();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.protobuf.nano.MessageNano mergeFrom(com.google.protobuf.nano.CodedInputByteBufferNano r3) {
        /*
            r2 = this;
            int r0 = r2.$r8$classId
            r1 = 13
            switch(r0) {
                case 0: goto L_0x001e;
                default: goto L_0x0007;
            }
        L_0x0007:
            int r0 = r3.readTag()
            if (r0 == 0) goto L_0x001d
            if (r0 == r1) goto L_0x0016
            boolean r0 = r3.skipField(r0)
            if (r0 != 0) goto L_0x0007
            goto L_0x001d
        L_0x0016:
            float r0 = r3.readFloat()
            r2.sensitivity = r0
            goto L_0x0007
        L_0x001d:
            return r2
        L_0x001e:
            int r0 = r3.readTag()
            if (r0 == 0) goto L_0x0034
            if (r0 == r1) goto L_0x002d
            boolean r0 = r3.skipField(r0)
            if (r0 != 0) goto L_0x001e
            goto L_0x0034
        L_0x002d:
            float r0 = r3.readFloat()
            r2.sensitivity = r0
            goto L_0x001e
        L_0x0034:
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
