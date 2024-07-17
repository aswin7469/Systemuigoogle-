package com.google.android.systemui.smartspace;

import android.app.smartspace.SmartspaceAction;
import com.google.android.systemui.smartspace.BcSmartspaceCardDoorbell;
import java.util.Objects;
import java.util.function.Predicate;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class BcSmartspaceCardDoorbell$$ExternalSyntheticLambda2 implements Predicate {
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ BcSmartspaceCardDoorbell$$ExternalSyntheticLambda2(int i) {
        this.$r8$classId = i;
    }

    public final boolean test(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                return Objects.nonNull((BcSmartspaceCardDoorbell.DrawableWithUri) obj);
            default:
                int i = BcSmartspaceCardDoorbell.$r8$clinit;
                return ((SmartspaceAction) obj).getExtras().containsKey("imageUri");
        }
    }
}
