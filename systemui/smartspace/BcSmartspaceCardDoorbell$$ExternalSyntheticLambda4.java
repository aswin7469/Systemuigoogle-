package com.google.android.systemui.smartspace;

import android.graphics.ImageDecoder;
import android.util.Size;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class BcSmartspaceCardDoorbell$$ExternalSyntheticLambda4 implements ImageDecoder.OnHeaderDecodedListener {
    public final /* synthetic */ int f$0;

    public /* synthetic */ BcSmartspaceCardDoorbell$$ExternalSyntheticLambda4(int i) {
        this.f$0 = i;
    }

    public final void onHeaderDecoded(ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source) {
        float f;
        int i = this.f$0;
        int i2 = BcSmartspaceCardDoorbell.$r8$clinit;
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
