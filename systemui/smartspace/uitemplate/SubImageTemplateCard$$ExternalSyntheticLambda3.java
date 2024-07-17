package com.google.android.systemui.smartspace.uitemplate;

import android.graphics.ImageDecoder;
import android.util.Size;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class SubImageTemplateCard$$ExternalSyntheticLambda3 implements ImageDecoder.OnHeaderDecodedListener {
    public final /* synthetic */ int f$0;

    public /* synthetic */ SubImageTemplateCard$$ExternalSyntheticLambda3(int i) {
        this.f$0 = i;
    }

    public final void onHeaderDecoded(ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source) {
        float f;
        int i = this.f$0;
        int i2 = SubImageTemplateCard.$r8$clinit;
        imageDecoder.setAllocator(3);
        Size size = imageInfo.getSize();
        if (size.getHeight() != 0) {
            f = ((float) size.getWidth()) / ((float) size.getHeight());
        } else {
            f = 0.0f;
        }
        imageDecoder.setTargetSize((int) (((float) i) * f), i);
    }
}
