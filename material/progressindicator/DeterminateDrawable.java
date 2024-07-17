package com.google.android.material.progressindicator;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.provider.Settings;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.google.android.material.color.MaterialColors;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class DeterminateDrawable extends DrawableWithAnimatedVisibilityChange {
    public static final AnonymousClass1 INDICATOR_LENGTH_IN_LEVEL = new Object();
    public final DrawingDelegate drawingDelegate;
    public float indicatorFraction;
    public boolean skipAnimationOnLevelChange = false;
    public final SpringAnimation springAnimation;
    public final SpringForce springForce;

    /* renamed from: com.google.android.material.progressindicator.DeterminateDrawable$1  reason: invalid class name */
    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class AnonymousClass1 extends FloatPropertyCompat {
        public final float getValue(Object obj) {
            return ((DeterminateDrawable) obj).indicatorFraction * 10000.0f;
        }

        public final void setValue(Object obj, float f) {
            DeterminateDrawable determinateDrawable = (DeterminateDrawable) obj;
            determinateDrawable.indicatorFraction = f / 10000.0f;
            determinateDrawable.invalidateSelf();
        }
    }

    public DeterminateDrawable(Context context, BaseProgressIndicatorSpec baseProgressIndicatorSpec, DrawingDelegate drawingDelegate2) {
        super(context, baseProgressIndicatorSpec);
        this.drawingDelegate = drawingDelegate2;
        drawingDelegate2.drawable = this;
        SpringForce springForce2 = new SpringForce();
        this.springForce = springForce2;
        springForce2.setDampingRatio(1.0f);
        springForce2.setStiffness(50.0f);
        SpringAnimation springAnimation2 = new SpringAnimation(INDICATOR_LENGTH_IN_LEVEL, this);
        this.springAnimation = springAnimation2;
        springAnimation2.mSpring = springForce2;
        if (this.growFraction != 1.0f) {
            this.growFraction = 1.0f;
            invalidateSelf();
        }
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
            Canvas canvas2 = canvas;
            this.drawingDelegate.fillIndicator(canvas2, this.paint, 0.0f, this.indicatorFraction, MaterialColors.compositeARGBWithAlpha(this.baseSpec.indicatorColors[0], this.totalAlpha));
            canvas.restore();
        }
    }

    public final int getIntrinsicHeight() {
        return this.drawingDelegate.getPreferredHeight();
    }

    public final int getIntrinsicWidth() {
        return this.drawingDelegate.getPreferredWidth();
    }

    public final void jumpToCurrentState() {
        this.springAnimation.skipToEnd();
        this.indicatorFraction = ((float) getLevel()) / 10000.0f;
        invalidateSelf();
    }

    public final boolean onLevelChange(int i) {
        if (this.skipAnimationOnLevelChange) {
            this.springAnimation.skipToEnd();
            this.indicatorFraction = ((float) i) / 10000.0f;
            invalidateSelf();
        } else {
            SpringAnimation springAnimation2 = this.springAnimation;
            springAnimation2.mValue = this.indicatorFraction * 10000.0f;
            springAnimation2.mStartValueIsSet = true;
            springAnimation2.animateToFinalPosition((float) i);
        }
        return true;
    }

    public final boolean setVisibleInternal(boolean z, boolean z2, boolean z3) {
        boolean visibleInternal = super.setVisibleInternal(z, z2, z3);
        AnimatorDurationScaleProvider animatorDurationScaleProvider = this.animatorDurationScaleProvider;
        ContentResolver contentResolver = this.context.getContentResolver();
        animatorDurationScaleProvider.getClass();
        float f = Settings.Global.getFloat(contentResolver, "animator_duration_scale", 1.0f);
        if (f == 0.0f) {
            this.skipAnimationOnLevelChange = true;
        } else {
            this.skipAnimationOnLevelChange = false;
            this.springForce.setStiffness(50.0f / f);
        }
        return visibleInternal;
    }
}
