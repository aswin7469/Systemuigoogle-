package com.google.android.systemui.smartspace;

import android.view.View;
import android.widget.TextView;
import com.google.android.systemui.smartspace.BcSmartspaceCardWeatherForecast;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class BcSmartspaceCardWeatherForecast$$ExternalSyntheticLambda2 implements BcSmartspaceCardWeatherForecast.ItemUpdateFunction {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ int f$0;

    public /* synthetic */ BcSmartspaceCardWeatherForecast$$ExternalSyntheticLambda2(int i, int i2) {
        this.$r8$classId = i2;
        this.f$0 = i;
    }

    public final void update(View view, int i) {
        switch (this.$r8$classId) {
            case 0:
                int i2 = BcSmartspaceCardWeatherForecast.$r8$clinit;
                ((TextView) view).setTextColor(this.f$0);
                return;
            default:
                int i3 = BcSmartspaceCardWeatherForecast.$r8$clinit;
                ((TextView) view).setTextColor(this.f$0);
                return;
        }
    }
}
