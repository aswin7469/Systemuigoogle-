package com.google.android.material.timepicker;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.core.view.ViewCompat;
import com.google.android.material.R$styleable;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
class ClockHandView extends View {
    public final float centerDotRadius;
    public boolean changedDuringTouch;
    public int circleRadius;
    public double degRad;
    public float downX;
    public float downY;
    public final List listeners;
    public float originalDeg;
    public final Paint paint;
    public final ValueAnimator rotationAnimator;
    public final int scaledTouchSlop;
    public final RectF selectorBox;
    public final int selectorRadius;
    public final int selectorStrokeWidth;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public interface OnRotateListener {
    }

    public ClockHandView(Context context) {
        this(context, (AttributeSet) null);
    }

    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight() / 2;
        int width = getWidth() / 2;
        float f = (float) width;
        float f2 = (float) height;
        this.paint.setStrokeWidth(0.0f);
        canvas.drawCircle((((float) this.circleRadius) * ((float) Math.cos(this.degRad))) + f, (((float) this.circleRadius) * ((float) Math.sin(this.degRad))) + f2, (float) this.selectorRadius, this.paint);
        double sin = Math.sin(this.degRad);
        double cos = Math.cos(this.degRad);
        double d = (double) ((float) (this.circleRadius - this.selectorRadius));
        float f3 = (float) (width + ((int) (cos * d)));
        float f4 = (float) (height + ((int) (d * sin)));
        this.paint.setStrokeWidth((float) this.selectorStrokeWidth);
        canvas.drawLine(f, f2, f3, f4, this.paint);
        canvas.drawCircle(f, f2, this.centerDotRadius, this.paint);
    }

    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        setHandRotation(this.originalDeg);
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z;
        boolean z2;
        boolean z3;
        int actionMasked = motionEvent.getActionMasked();
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        boolean z4 = false;
        if (actionMasked == 0) {
            this.changedDuringTouch = false;
            z = true;
            z2 = false;
        } else if (actionMasked == 1 || actionMasked == 2) {
            z2 = this.changedDuringTouch;
            z = false;
        } else {
            z2 = false;
            z = false;
        }
        boolean z5 = this.changedDuringTouch;
        int degrees = (int) Math.toDegrees(Math.atan2((double) (y - ((float) (getHeight() / 2))), (double) (x - ((float) (getWidth() / 2)))));
        int i = degrees + 90;
        if (i < 0) {
            i = degrees + 450;
        }
        float f = (float) i;
        if (this.originalDeg != f) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (!z || !z3) {
            if (z3 || z2) {
                setHandRotation(f);
            }
            this.changedDuringTouch = z5 | z4;
            return true;
        }
        z4 = true;
        this.changedDuringTouch = z5 | z4;
        return true;
    }

    public final void setHandRotation(float f) {
        ValueAnimator valueAnimator = this.rotationAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        setHandRotationInternal(f);
    }

    public final void setHandRotationInternal(float f) {
        float f2 = f % 360.0f;
        this.originalDeg = f2;
        this.degRad = Math.toRadians((double) (f2 - 90.0f));
        float cos = (((float) this.circleRadius) * ((float) Math.cos(this.degRad))) + ((float) (getWidth() / 2));
        float sin = (((float) this.circleRadius) * ((float) Math.sin(this.degRad))) + ((float) (getHeight() / 2));
        RectF rectF = this.selectorBox;
        float f3 = (float) this.selectorRadius;
        rectF.set(cos - f3, sin - f3, cos + f3, sin + f3);
        for (OnRotateListener onRotateListener : this.listeners) {
            ClockFaceView clockFaceView = (ClockFaceView) onRotateListener;
            if (Math.abs(clockFaceView.currentHandRotation - f2) > 0.001f) {
                clockFaceView.currentHandRotation = f2;
                clockFaceView.findIntersectingTextView();
            }
        }
        invalidate();
    }

    public ClockHandView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 2130969545);
    }

    public ClockHandView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.listeners = new ArrayList();
        Paint paint2 = new Paint();
        this.paint = paint2;
        this.selectorBox = new RectF();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ClockHandView, i, 2132018875);
        this.circleRadius = obtainStyledAttributes.getDimensionPixelSize(1, 0);
        this.selectorRadius = obtainStyledAttributes.getDimensionPixelSize(2, 0);
        Resources resources = getResources();
        this.selectorStrokeWidth = resources.getDimensionPixelSize(2131166619);
        this.centerDotRadius = (float) resources.getDimensionPixelSize(2131166617);
        int color = obtainStyledAttributes.getColor(0, 0);
        paint2.setAntiAlias(true);
        paint2.setColor(color);
        setHandRotation(0.0f);
        ViewConfiguration.get(context).getScaledTouchSlop();
        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
        ViewCompat.Api16Impl.setImportantForAccessibility(this, 2);
        obtainStyledAttributes.recycle();
    }
}
