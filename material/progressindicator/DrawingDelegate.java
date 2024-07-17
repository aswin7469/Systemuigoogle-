package com.google.android.material.progressindicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class DrawingDelegate {
    public DrawableWithAnimatedVisibilityChange drawable;
    public final BaseProgressIndicatorSpec spec;

    public DrawingDelegate(BaseProgressIndicatorSpec baseProgressIndicatorSpec) {
        this.spec = baseProgressIndicatorSpec;
    }

    public abstract void adjustCanvas(Canvas canvas, Rect rect, float f);

    public abstract void fillIndicator(Canvas canvas, Paint paint, float f, float f2, int i);

    public abstract void fillTrack(Canvas canvas, Paint paint);

    public abstract int getPreferredHeight();

    public abstract int getPreferredWidth();
}
