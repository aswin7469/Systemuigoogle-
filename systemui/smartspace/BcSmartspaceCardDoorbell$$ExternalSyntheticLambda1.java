package com.google.android.systemui.smartspace;

import android.content.ContentResolver;
import android.net.Uri;
import com.google.android.systemui.smartspace.BcSmartspaceCardDoorbell;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.function.Function;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class BcSmartspaceCardDoorbell$$ExternalSyntheticLambda1 implements Function {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ BcSmartspaceCardDoorbell f$0;
    public final /* synthetic */ ContentResolver f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ float f$3;
    public final /* synthetic */ WeakReference f$4;
    public final /* synthetic */ WeakReference f$5;

    public /* synthetic */ BcSmartspaceCardDoorbell$$ExternalSyntheticLambda1(BcSmartspaceCardDoorbell bcSmartspaceCardDoorbell, ContentResolver contentResolver, int i, float f, WeakReference weakReference, WeakReference weakReference2, int i2) {
        this.$r8$classId = i2;
        this.f$0 = bcSmartspaceCardDoorbell;
        this.f$1 = contentResolver;
        this.f$2 = i;
        this.f$3 = f;
        this.f$4 = weakReference;
        this.f$5 = weakReference2;
    }

    public final Object apply(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                BcSmartspaceCardDoorbell bcSmartspaceCardDoorbell = this.f$0;
                ContentResolver contentResolver = this.f$1;
                int i = this.f$2;
                float f = this.f$3;
                WeakReference weakReference = this.f$4;
                WeakReference weakReference2 = this.f$5;
                return (BcSmartspaceCardDoorbell.DrawableWithUri) ((HashMap) bcSmartspaceCardDoorbell.mUriToDrawable).computeIfAbsent((Uri) obj, new BcSmartspaceCardDoorbell$$ExternalSyntheticLambda1(bcSmartspaceCardDoorbell, contentResolver, i, f, weakReference, weakReference2, 1));
            default:
                BcSmartspaceCardDoorbell bcSmartspaceCardDoorbell2 = this.f$0;
                ContentResolver contentResolver2 = this.f$1;
                int i2 = this.f$2;
                float f2 = this.f$3;
                WeakReference weakReference3 = this.f$4;
                WeakReference weakReference4 = this.f$5;
                int i3 = BcSmartspaceCardDoorbell.$r8$clinit;
                bcSmartspaceCardDoorbell2.getClass();
                BcSmartspaceCardDoorbell.DrawableWithUri drawableWithUri = new BcSmartspaceCardDoorbell.DrawableWithUri(f2, i2, contentResolver2, (Uri) obj, weakReference3, weakReference4);
                new BcSmartspaceCardDoorbell.LoadUriTask(bcSmartspaceCardDoorbell2.mLatencyInstrumentContext).execute(new BcSmartspaceCardDoorbell.DrawableWithUri[]{drawableWithUri});
                return drawableWithUri;
        }
    }
}
