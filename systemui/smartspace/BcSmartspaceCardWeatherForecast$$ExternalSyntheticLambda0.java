package com.google.android.systemui.smartspace;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.systemui.smartspace.BcSmartspaceCardWeatherForecast;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class BcSmartspaceCardWeatherForecast$$ExternalSyntheticLambda0 implements BcSmartspaceCardWeatherForecast.ItemUpdateFunction {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object[] f$0;

    public /* synthetic */ BcSmartspaceCardWeatherForecast$$ExternalSyntheticLambda0(int i, Object[] objArr) {
        this.$r8$classId = i;
        this.f$0 = objArr;
    }

    public final void update(View view, int i) {
        int i2 = this.$r8$classId;
        Object[] objArr = this.f$0;
        switch (i2) {
            case 0:
                int i3 = BcSmartspaceCardWeatherForecast.$r8$clinit;
                ((TextView) view).setText(((String[]) objArr)[i]);
                return;
            case 1:
                int i4 = BcSmartspaceCardWeatherForecast.$r8$clinit;
                ((TextView) view).setText(((String[]) objArr)[i]);
                return;
            default:
                int i5 = BcSmartspaceCardWeatherForecast.$r8$clinit;
                ((ImageView) view).setImageBitmap(((Bitmap[]) objArr)[i]);
                return;
        }
    }
}
