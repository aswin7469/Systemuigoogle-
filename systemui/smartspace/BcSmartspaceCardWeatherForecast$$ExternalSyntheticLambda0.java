package com.google.android.systemui.smartspace;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.systemui.smartspace.BcSmartspaceCardWeatherForecast;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class BcSmartspaceCardWeatherForecast$$ExternalSyntheticLambda0 implements BcSmartspaceCardWeatherForecast.ItemUpdateFunction {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object[] f$0;

    public /* synthetic */ BcSmartspaceCardWeatherForecast$$ExternalSyntheticLambda0(int i, Object[] objArr) {
        this.$r8$classId = i;
        this.f$0 = objArr;
    }

    public final void update(View view, int i) {
        switch (this.$r8$classId) {
            case 0:
                int i2 = BcSmartspaceCardWeatherForecast.$r8$clinit;
                ((TextView) view).setText(((String[]) this.f$0)[i]);
                return;
            case 1:
                int i3 = BcSmartspaceCardWeatherForecast.$r8$clinit;
                ((TextView) view).setText(((String[]) this.f$0)[i]);
                return;
            default:
                int i4 = BcSmartspaceCardWeatherForecast.$r8$clinit;
                ((ImageView) view).setImageBitmap(((Bitmap[]) this.f$0)[i]);
                return;
        }
    }
}
