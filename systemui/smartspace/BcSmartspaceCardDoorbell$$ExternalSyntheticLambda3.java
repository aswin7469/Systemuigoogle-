package com.google.android.systemui.smartspace;

import android.app.smartspace.SmartspaceAction;
import android.net.Uri;
import java.util.function.Function;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class BcSmartspaceCardDoorbell$$ExternalSyntheticLambda3 implements Function {
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ BcSmartspaceCardDoorbell$$ExternalSyntheticLambda3(int i) {
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
