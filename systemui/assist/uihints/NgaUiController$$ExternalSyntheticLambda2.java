package com.google.android.systemui.assist.uihints;

import android.graphics.Rect;
import android.graphics.Region;
import java.util.function.Consumer;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class NgaUiController$$ExternalSyntheticLambda2 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Region f$0;

    public /* synthetic */ NgaUiController$$ExternalSyntheticLambda2(int i, Region region) {
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
            case 1:
                region.op(region2, Region.Op.UNION);
                return;
            case 2:
                region.op(region2, Region.Op.UNION);
                return;
            default:
                if (region.isEmpty()) {
                    region.op(region2, Region.Op.UNION);
                    return;
                } else if (region.quickReject(region2)) {
                    Rect bounds = region.getBounds();
                    bounds.top = region2.getBounds().top;
                    region.set(bounds);
                    return;
                } else {
                    region.op(region2, Region.Op.UNION);
                    return;
                }
        }
    }
}
