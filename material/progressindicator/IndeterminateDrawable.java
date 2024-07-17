package com.google.android.material.progressindicator;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.provider.Settings;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class IndeterminateDrawable extends DrawableWithAnimatedVisibilityChange {
    public final IndeterminateAnimatorDelegate animatorDelegate;
    public final DrawingDelegate drawingDelegate;

    public IndeterminateDrawable(Context context, BaseProgressIndicatorSpec baseProgressIndicatorSpec, DrawingDelegate drawingDelegate2, IndeterminateAnimatorDelegate indeterminateAnimatorDelegate) {
        super(context, baseProgressIndicatorSpec);
        this.drawingDelegate = drawingDelegate2;
        drawingDelegate2.drawable = this;
        this.animatorDelegate = indeterminateAnimatorDelegate;
        indeterminateAnimatorDelegate.drawable = this;
    }

    public final void draw(Canvas canvas) {
        Rect rect = new Rect();
        if (!getBounds().isEmpty() && isVisible() && canvas.getClipBounds(rect)) {
            canvas.save();
            DrawingDelegate drawingDelegate2 = this.drawingDelegate;
            Rect bounds = getBounds();
            float growFraction = getGrowFraction();
            drawingDelegate2.spec.validateSpec();
            drawingDelegate2.adjustCanvas(canvas, bounds, growFraction);
            this.drawingDelegate.fillTrack(canvas, this.paint);
            int i = 0;
            while (true) {
                IndeterminateAnimatorDelegate indeterminateAnimatorDelegate = this.animatorDelegate;
                int[] iArr = indeterminateAnimatorDelegate.segmentColors;
                if (i < iArr.length) {
                    DrawingDelegate drawingDelegate3 = this.drawingDelegate;
                    Paint paint = this.paint;
                    int i2 = i * 2;
                    float[] fArr = indeterminateAnimatorDelegate.segmentPositions;
                    drawingDelegate3.fillIndicator(canvas, paint, fArr[i2], fArr[i2 + 1], iArr[i]);
                    i++;
                } else {
                    canvas.restore();
                    return;
                }
            }
        }
    }

    public final int getIntrinsicHeight() {
        return this.drawingDelegate.getPreferredHeight();
    }

    public final int getIntrinsicWidth() {
        return this.drawingDelegate.getPreferredWidth();
    }

    public final boolean setVisibleInternal(boolean z, boolean z2, boolean z3) {
        boolean visibleInternal = super.setVisibleInternal(z, z2, z3);
        if (!isRunning()) {
            this.animatorDelegate.cancelAnimatorImmediately();
        }
        AnimatorDurationScaleProvider animatorDurationScaleProvider = this.animatorDurationScaleProvider;
        ContentResolver contentResolver = this.context.getContentResolver();
        animatorDurationScaleProvider.getClass();
        Settings.Global.getFloat(contentResolver, "animator_duration_scale", 1.0f);
        if (z && z3) {
            this.animatorDelegate.startAnimator();
        }
        return visibleInternal;
    }
}
