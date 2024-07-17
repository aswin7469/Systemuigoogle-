package com.google.android.systemui.smartspace;

import android.app.smartspace.SmartspaceAction;
import android.net.Uri;
import java.util.function.Function;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class BcSmartspaceCardDoorbell$$ExternalSyntheticLambda4 implements Function {
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ BcSmartspaceCardDoorbell$$ExternalSyntheticLambda4(int i) {
        this.$r8$classId = i;
    }

    public final Object apply(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                int i = BcSmartspaceCardDoorbell.$r8$clinit;
                return ((SmartspaceAction) obj).getExtras().getString("imageUri");
            default:
                return Uri.parse((String) obj);
        }
    }
}
