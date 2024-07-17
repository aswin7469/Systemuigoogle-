package com.google.android.material.progressindicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import com.google.android.material.color.MaterialColors;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class LinearDrawingDelegate extends DrawingDelegate {
    public float displayedCornerRadius;
    public float displayedTrackThickness;
    public float trackLength = 300.0f;

    public LinearDrawingDelegate(LinearProgressIndicatorSpec linearProgressIndicatorSpec) {
        super(linearProgressIndicatorSpec);
    }

    public final void adjustCanvas(Canvas canvas, Rect rect, float f) {
        this.trackLength = (float) rect.width();
        BaseProgressIndicatorSpec baseProgressIndicatorSpec = this.spec;
        float f2 = (float) ((LinearProgressIndicatorSpec) baseProgressIndicatorSpec).trackThickness;
        canvas.translate((((float) rect.width()) / 2.0f) + ((float) rect.left), Math.max(0.0f, ((float) (rect.height() - ((LinearProgressIndicatorSpec) baseProgressIndicatorSpec).trackThickness)) / 2.0f) + (((float) rect.height()) / 2.0f) + ((float) rect.top));
        if (((LinearProgressIndicatorSpec) baseProgressIndicatorSpec).drawHorizontallyInverse) {
            canvas.scale(-1.0f, 1.0f);
        }
        if ((this.drawable.isShowing() && ((LinearProgressIndicatorSpec) baseProgressIndicatorSpec).showAnimationBehavior == 1) || (this.drawable.isHiding() && ((LinearProgressIndicatorSpec) baseProgressIndicatorSpec).hideAnimationBehavior == 2)) {
            canvas.scale(1.0f, -1.0f);
        }
        if (this.drawable.isShowing() || this.drawable.isHiding()) {
            canvas.translate(0.0f, ((f - 1.0f) * ((float) ((LinearProgressIndicatorSpec) baseProgressIndicatorSpec).trackThickness)) / 2.0f);
        }
        float f3 = this.trackLength;
        canvas.clipRect((-f3) / 2.0f, (-f2) / 2.0f, f3 / 2.0f, f2 / 2.0f);
        this.displayedTrackThickness = ((float) ((LinearProgressIndicatorSpec) baseProgressIndicatorSpec).trackThickness) * f;
        this.displayedCornerRadius = ((float) ((LinearProgressIndicatorSpec) baseProgressIndicatorSpec).trackCornerRadius) * f;
    }

    public final void fillIndicator(Canvas canvas, Paint paint, float f, float f2, int i) {
        if (f != f2) {
            float f3 = this.trackLength;
            float f4 = (-f3) / 2.0f;
            float f5 = this.displayedCornerRadius * 2.0f;
            float f6 = f3 - f5;
            paint.setStyle(Paint.Style.FILL);
            paint.setAntiAlias(true);
            paint.setColor(i);
            float f7 = this.displayedTrackThickness;
            RectF rectF = new RectF((f * f6) + f4, (-f7) / 2.0f, (f6 * f2) + f4 + f5, f7 / 2.0f);
            float f8 = this.displayedCornerRadius;
            canvas.drawRoundRect(rectF, f8, f8, paint);
        }
    }

    public final void fillTrack(Canvas canvas, Paint paint) {
        int compositeARGBWithAlpha = MaterialColors.compositeARGBWithAlpha(((LinearProgressIndicatorSpec) this.spec).trackColor, this.drawable.totalAlpha);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(compositeARGBWithAlpha);
        float f = this.trackLength;
        float f2 = this.displayedTrackThickness;
        RectF rectF = new RectF((-f) / 2.0f, (-f2) / 2.0f, f / 2.0f, f2 / 2.0f);
        float f3 = this.displayedCornerRadius;
        canvas.drawRoundRect(rectF, f3, f3, paint);
    }

    public final int getPreferredHeight() {
        return ((LinearProgressIndicatorSpec) this.spec).trackThickness;
    }

    public final int getPreferredWidth() {
        return -1;
    }
}
