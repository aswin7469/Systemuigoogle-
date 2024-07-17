package com.google.android.material.progressindicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import com.google.android.material.color.MaterialColors;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class CircularDrawingDelegate extends DrawingDelegate {
    public float adjustedRadius;
    public int arcDirectionFactor = 1;
    public float displayedCornerRadius;
    public float displayedTrackThickness;

    public CircularDrawingDelegate(CircularProgressIndicatorSpec circularProgressIndicatorSpec) {
        super(circularProgressIndicatorSpec);
    }

    public final void adjustCanvas(Canvas canvas, Rect rect, float f) {
        int i;
        float width = ((float) rect.width()) / ((float) getSize());
        float height = ((float) rect.height()) / ((float) getSize());
        BaseProgressIndicatorSpec baseProgressIndicatorSpec = this.spec;
        float f2 = (((float) ((CircularProgressIndicatorSpec) baseProgressIndicatorSpec).indicatorSize) / 2.0f) + ((float) ((CircularProgressIndicatorSpec) baseProgressIndicatorSpec).indicatorInset);
        canvas.translate((f2 * width) + ((float) rect.left), (f2 * height) + ((float) rect.top));
        canvas.scale(width, height);
        canvas.rotate(-90.0f);
        float f3 = -f2;
        canvas.clipRect(f3, f3, f2, f2);
        if (((CircularProgressIndicatorSpec) baseProgressIndicatorSpec).indicatorDirection == 0) {
            i = 1;
        } else {
            i = -1;
        }
        this.arcDirectionFactor = i;
        this.displayedTrackThickness = ((float) ((CircularProgressIndicatorSpec) baseProgressIndicatorSpec).trackThickness) * f;
        this.displayedCornerRadius = ((float) ((CircularProgressIndicatorSpec) baseProgressIndicatorSpec).trackCornerRadius) * f;
        this.adjustedRadius = ((float) (((CircularProgressIndicatorSpec) baseProgressIndicatorSpec).indicatorSize - ((CircularProgressIndicatorSpec) baseProgressIndicatorSpec).trackThickness)) / 2.0f;
        if ((this.drawable.isShowing() && ((CircularProgressIndicatorSpec) baseProgressIndicatorSpec).showAnimationBehavior == 2) || (this.drawable.isHiding() && ((CircularProgressIndicatorSpec) baseProgressIndicatorSpec).hideAnimationBehavior == 1)) {
            this.adjustedRadius = (((1.0f - f) * ((float) ((CircularProgressIndicatorSpec) baseProgressIndicatorSpec).trackThickness)) / 2.0f) + this.adjustedRadius;
        } else if ((this.drawable.isShowing() && ((CircularProgressIndicatorSpec) baseProgressIndicatorSpec).showAnimationBehavior == 1) || (this.drawable.isHiding() && ((CircularProgressIndicatorSpec) baseProgressIndicatorSpec).hideAnimationBehavior == 2)) {
            this.adjustedRadius -= ((1.0f - f) * ((float) ((CircularProgressIndicatorSpec) baseProgressIndicatorSpec).trackThickness)) / 2.0f;
        }
    }

    public final void drawRoundedEnd(Canvas canvas, Paint paint, float f, float f2, float f3) {
        canvas.save();
        canvas.rotate(f3);
        float f4 = this.adjustedRadius;
        float f5 = f / 2.0f;
        canvas.drawRoundRect(new RectF(f4 - f5, f2, f4 + f5, -f2), f2, f2, paint);
        canvas.restore();
    }

    public final void fillIndicator(Canvas canvas, Paint paint, float f, float f2, int i) {
        float f3;
        Paint paint2 = paint;
        if (f != f2) {
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeCap(Paint.Cap.BUTT);
            paint.setAntiAlias(true);
            paint.setColor(i);
            paint.setStrokeWidth(this.displayedTrackThickness);
            float f4 = (float) this.arcDirectionFactor;
            float f5 = f * 360.0f * f4;
            if (f2 >= f) {
                f3 = f2 - f;
            } else {
                f3 = (1.0f + f2) - f;
            }
            float f6 = f3 * 360.0f * f4;
            float f7 = this.adjustedRadius;
            float f8 = -f7;
            canvas.drawArc(new RectF(f8, f8, f7, f7), f5, f6, false, paint);
            if (this.displayedCornerRadius > 0.0f && Math.abs(f6) < 360.0f) {
                paint.setStyle(Paint.Style.FILL);
                Canvas canvas2 = canvas;
                Paint paint3 = paint;
                drawRoundedEnd(canvas2, paint3, this.displayedTrackThickness, this.displayedCornerRadius, f5);
                drawRoundedEnd(canvas2, paint3, this.displayedTrackThickness, this.displayedCornerRadius, f5 + f6);
            }
        }
    }

    public final void fillTrack(Canvas canvas, Paint paint) {
        int compositeARGBWithAlpha = MaterialColors.compositeARGBWithAlpha(((CircularProgressIndicatorSpec) this.spec).trackColor, this.drawable.totalAlpha);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.BUTT);
        paint.setAntiAlias(true);
        paint.setColor(compositeARGBWithAlpha);
        paint.setStrokeWidth(this.displayedTrackThickness);
        float f = this.adjustedRadius;
        canvas.drawArc(new RectF(-f, -f, f, f), 0.0f, 360.0f, false, paint);
    }

    public final int getPreferredHeight() {
        return getSize();
    }

    public final int getPreferredWidth() {
        return getSize();
    }

    public final int getSize() {
        BaseProgressIndicatorSpec baseProgressIndicatorSpec = this.spec;
        return (((CircularProgressIndicatorSpec) baseProgressIndicatorSpec).indicatorInset * 2) + ((CircularProgressIndicatorSpec) baseProgressIndicatorSpec).indicatorSize;
    }
}
