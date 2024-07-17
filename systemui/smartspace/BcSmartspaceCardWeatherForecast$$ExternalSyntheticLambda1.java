package com.google.android.systemui.smartspace;

import android.view.View;
import android.widget.TextView;
import com.google.android.systemui.smartspace.BcSmartspaceCardWeatherForecast;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class BcSmartspaceCardWeatherForecast$$ExternalSyntheticLambda1 implements BcSmartspaceCardWeatherForecast.ItemUpdateFunction {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ int f$0;

    public /* synthetic */ BcSmartspaceCardWeatherForecast$$ExternalSyntheticLambda1(int i, int i2) {
        this.$r8$classId = i2;
        this.f$0 = i;
    }

    public final void update(View view, int i) {
        int i2 = this.$r8$classId;
        int i3 = this.f$0;
        switch (i2) {
            case 0:
                int i4 = BcSmartspaceCardWeatherForecast.$r8$clinit;
                ((TextView) view).setTextColor(i3);
                return;
            default:
                int i5 = BcSmartspaceCardWeatherForecast.$r8$clinit;
                ((TextView) view).setTextColor(i3);
                return;
        }
    }
}
