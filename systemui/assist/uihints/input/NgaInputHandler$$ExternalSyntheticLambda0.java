package com.google.android.systemui.assist.uihints.input;

import android.graphics.Region;
import java.util.function.Consumer;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class NgaInputHandler$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Region f$0;

    public /* synthetic */ NgaInputHandler$$ExternalSyntheticLambda0(int i, Region region) {
        this.$r8$classId = i;
        this.f$0 = region;
    }

    public final void accept(Object obj) {
        int i = this.$r8$classId;
        Region region = this.f$0;
        Region region2 = (Region) obj;
        switch (i) {
            case 0:
                region.op(region2, Region.Op.UNION);
                return;
            default:
                region.op(region2, Region.Op.DIFFERENCE);
                return;
        }
    }
}
