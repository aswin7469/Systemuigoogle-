package com.google.android.material.circularreveal;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.google.android.material.circularreveal.CircularRevealWidget;
import com.google.android.material.math.MathUtils;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class CircularRevealHelper {
    public final Delegate delegate;
    public Drawable overlayDrawable;
    public CircularRevealWidget.RevealInfo revealInfo;
    public final Paint scrimPaint;
    public final View view;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public interface Delegate {
        void actualDraw(Canvas canvas);

        boolean actualIsOpaque();
    }

    public CircularRevealHelper(Delegate delegate2) {
        this.delegate = delegate2;
        View view2 = (View) delegate2;
        this.view = view2;
        view2.setWillNotDraw(false);
        new Path();
        new Paint(7);
        Paint paint = new Paint(1);
        this.scrimPaint = paint;
        paint.setColor(0);
    }

    public final void draw(Canvas canvas) {
        boolean z;
        CircularRevealWidget.RevealInfo revealInfo2 = this.revealInfo;
        if (revealInfo2 == null || revealInfo2.radius == Float.MAX_VALUE) {
            z = true;
        } else {
            z = false;
        }
        boolean z2 = !z;
        Paint paint = this.scrimPaint;
        Delegate delegate2 = this.delegate;
        View view2 = this.view;
        if (z2) {
            delegate2.actualDraw(canvas);
            if (Color.alpha(paint.getColor()) != 0) {
                canvas.drawRect(0.0f, 0.0f, (float) view2.getWidth(), (float) view2.getHeight(), paint);
            }
        } else {
            delegate2.actualDraw(canvas);
            if (Color.alpha(paint.getColor()) != 0) {
                canvas.drawRect(0.0f, 0.0f, (float) view2.getWidth(), (float) view2.getHeight(), paint);
            }
        }
        Drawable drawable = this.overlayDrawable;
        if (drawable != null && this.revealInfo != null) {
            Rect bounds = drawable.getBounds();
            float width = this.revealInfo.centerX - (((float) bounds.width()) / 2.0f);
            float height = this.revealInfo.centerY - (((float) bounds.height()) / 2.0f);
            canvas.translate(width, height);
            this.overlayDrawable.draw(canvas);
            canvas.translate(-width, -height);
        }
    }

    public final CircularRevealWidget.RevealInfo getRevealInfo() {
        CircularRevealWidget.RevealInfo revealInfo2 = this.revealInfo;
        if (revealInfo2 == null) {
            return null;
        }
        CircularRevealWidget.RevealInfo revealInfo3 = new CircularRevealWidget.RevealInfo(revealInfo2);
        if (revealInfo3.radius == Float.MAX_VALUE) {
            float f = revealInfo3.centerX;
            float f2 = revealInfo3.centerY;
            View view2 = this.view;
            revealInfo3.radius = MathUtils.distanceToFurthestCorner(f, f2, (float) view2.getWidth(), (float) view2.getHeight());
        }
        return revealInfo3;
    }

    public final boolean isOpaque() {
        boolean z;
        if (!this.delegate.actualIsOpaque()) {
            return false;
        }
        CircularRevealWidget.RevealInfo revealInfo2 = this.revealInfo;
        if (revealInfo2 == null || revealInfo2.radius == Float.MAX_VALUE) {
            z = true;
        } else {
            z = false;
        }
        if (!(!z)) {
            return true;
        }
        return false;
    }

    public final void setCircularRevealOverlayDrawable(Drawable drawable) {
        this.overlayDrawable = drawable;
        this.view.invalidate();
    }

    public final void setCircularRevealScrimColor(int i) {
        this.scrimPaint.setColor(i);
        this.view.invalidate();
    }

    public final void setRevealInfo(CircularRevealWidget.RevealInfo revealInfo2) {
        View view2 = this.view;
        if (revealInfo2 == null) {
            this.revealInfo = null;
        } else {
            CircularRevealWidget.RevealInfo revealInfo3 = this.revealInfo;
            if (revealInfo3 == null) {
                this.revealInfo = new CircularRevealWidget.RevealInfo(revealInfo2);
            } else {
                float f = revealInfo2.centerX;
                float f2 = revealInfo2.centerY;
                float f3 = revealInfo2.radius;
                revealInfo3.centerX = f;
                revealInfo3.centerY = f2;
                revealInfo3.radius = f3;
            }
            if (revealInfo2.radius + 1.0E-4f >= MathUtils.distanceToFurthestCorner(revealInfo2.centerX, revealInfo2.centerY, (float) view2.getWidth(), (float) view2.getHeight())) {
                this.revealInfo.radius = Float.MAX_VALUE;
            }
        }
        view2.invalidate();
    }
}
