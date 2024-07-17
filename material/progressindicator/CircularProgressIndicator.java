package com.google.android.material.progressindicator;

import android.content.Context;
import android.util.AttributeSet;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class CircularProgressIndicator extends BaseProgressIndicator {
    public static final /* synthetic */ int $r8$clinit = 0;

    public CircularProgressIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 2130968819, 2132018803);
        Context context2 = getContext();
        CircularProgressIndicatorSpec circularProgressIndicatorSpec = (CircularProgressIndicatorSpec) this.spec;
        setIndeterminateDrawable(new IndeterminateDrawable(context2, circularProgressIndicatorSpec, new CircularDrawingDelegate(circularProgressIndicatorSpec), new CircularIndeterminateAnimatorDelegate(circularProgressIndicatorSpec)));
        Context context3 = getContext();
        CircularProgressIndicatorSpec circularProgressIndicatorSpec2 = (CircularProgressIndicatorSpec) this.spec;
        setProgressDrawable(new DeterminateDrawable(context3, circularProgressIndicatorSpec2, new CircularDrawingDelegate(circularProgressIndicatorSpec2)));
    }

    public final BaseProgressIndicatorSpec createSpec(Context context, AttributeSet attributeSet) {
        return new CircularProgressIndicatorSpec(context, attributeSet);
    }
}
