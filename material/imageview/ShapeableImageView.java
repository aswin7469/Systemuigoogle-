package com.google.android.material.imageview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.appcompat.widget.AppCompatImageView;
import com.google.android.material.R$styleable;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.ShapeAppearancePathProvider;
import com.google.android.material.shape.Shapeable;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class ShapeableImageView extends AppCompatImageView implements Shapeable {
    public final Paint borderPaint;
    public final int bottomContentPadding;
    public final Paint clearPaint;
    public final RectF destination;
    public final int endContentPadding;
    public boolean hasAdjustedPaddingAfterLayoutDirectionResolved = false;
    public final int leftContentPadding;
    public final Path maskPath;
    public final RectF maskRect;
    public final Path path = new Path();
    public final ShapeAppearancePathProvider pathProvider = ShapeAppearancePathProvider.Lazy.INSTANCE;
    public final int rightContentPadding;
    public MaterialShapeDrawable shadowDrawable;
    public ShapeAppearanceModel shapeAppearanceModel;
    public final int startContentPadding;
    public final ColorStateList strokeColor;
    public final float strokeWidth;
    public final int topContentPadding;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class OutlineProvider extends ViewOutlineProvider {
        public final Rect rect = new Rect();

        public OutlineProvider() {
        }

        public final void getOutline(View view, Outline outline) {
            ShapeableImageView shapeableImageView = ShapeableImageView.this;
            if (shapeableImageView.shapeAppearanceModel != null) {
                if (shapeableImageView.shadowDrawable == null) {
                    shapeableImageView.shadowDrawable = new MaterialShapeDrawable(ShapeableImageView.this.shapeAppearanceModel);
                }
                ShapeableImageView.this.destination.round(this.rect);
                ShapeableImageView.this.shadowDrawable.setBounds(this.rect);
                ShapeableImageView.this.shadowDrawable.getOutline(outline);
            }
        }
    }

    public ShapeableImageView(Context context, AttributeSet attributeSet) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, 0, 2132018852), attributeSet, 0);
        Context context2 = getContext();
        Paint paint = new Paint();
        this.clearPaint = paint;
        paint.setAntiAlias(true);
        paint.setColor(-1);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        this.destination = new RectF();
        this.maskRect = new RectF();
        this.maskPath = new Path();
        TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, R$styleable.ShapeableImageView, 0, 2132018852);
        setLayerType(2, (Paint) null);
        this.strokeColor = MaterialResources.getColorStateList(context2, obtainStyledAttributes, 9);
        this.strokeWidth = (float) obtainStyledAttributes.getDimensionPixelSize(10, 0);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        this.leftContentPadding = dimensionPixelSize;
        this.topContentPadding = dimensionPixelSize;
        this.rightContentPadding = dimensionPixelSize;
        this.bottomContentPadding = dimensionPixelSize;
        this.leftContentPadding = obtainStyledAttributes.getDimensionPixelSize(3, dimensionPixelSize);
        this.topContentPadding = obtainStyledAttributes.getDimensionPixelSize(6, dimensionPixelSize);
        this.rightContentPadding = obtainStyledAttributes.getDimensionPixelSize(4, dimensionPixelSize);
        this.bottomContentPadding = obtainStyledAttributes.getDimensionPixelSize(1, dimensionPixelSize);
        this.startContentPadding = obtainStyledAttributes.getDimensionPixelSize(5, Integer.MIN_VALUE);
        this.endContentPadding = obtainStyledAttributes.getDimensionPixelSize(2, Integer.MIN_VALUE);
        obtainStyledAttributes.recycle();
        Paint paint2 = new Paint();
        this.borderPaint = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setAntiAlias(true);
        this.shapeAppearanceModel = ShapeAppearanceModel.builder(context2, attributeSet, 0, 2132018852).build();
        setOutlineProvider(new OutlineProvider());
    }

    public final int getContentPaddingLeft() {
        int i;
        int i2;
        if (!(this.startContentPadding == Integer.MIN_VALUE && this.endContentPadding == Integer.MIN_VALUE)) {
            if (isRtl$1() && (i2 = this.endContentPadding) != Integer.MIN_VALUE) {
                return i2;
            }
            if (!isRtl$1() && (i = this.startContentPadding) != Integer.MIN_VALUE) {
                return i;
            }
        }
        return this.leftContentPadding;
    }

    public final int getContentPaddingRight() {
        int i;
        int i2;
        if (!(this.startContentPadding == Integer.MIN_VALUE && this.endContentPadding == Integer.MIN_VALUE)) {
            if (isRtl$1() && (i2 = this.startContentPadding) != Integer.MIN_VALUE) {
                return i2;
            }
            if (!isRtl$1() && (i = this.endContentPadding) != Integer.MIN_VALUE) {
                return i;
            }
        }
        return this.rightContentPadding;
    }

    public final int getPaddingBottom() {
        return super.getPaddingBottom() - this.bottomContentPadding;
    }

    public final int getPaddingEnd() {
        int i;
        int paddingEnd = super.getPaddingEnd();
        int i2 = this.endContentPadding;
        if (i2 == Integer.MIN_VALUE) {
            if (isRtl$1()) {
                i = this.leftContentPadding;
            } else {
                i = this.rightContentPadding;
            }
            i2 = i;
        }
        return paddingEnd - i2;
    }

    public final int getPaddingLeft() {
        return super.getPaddingLeft() - getContentPaddingLeft();
    }

    public final int getPaddingRight() {
        return super.getPaddingRight() - getContentPaddingRight();
    }

    public final int getPaddingStart() {
        int i;
        int paddingStart = super.getPaddingStart();
        int i2 = this.startContentPadding;
        if (i2 == Integer.MIN_VALUE) {
            if (isRtl$1()) {
                i = this.rightContentPadding;
            } else {
                i = this.leftContentPadding;
            }
            i2 = i;
        }
        return paddingStart - i2;
    }

    public final int getPaddingTop() {
        return super.getPaddingTop() - this.topContentPadding;
    }

    public final boolean isRtl$1() {
        if (getLayoutDirection() == 1) {
            return true;
        }
        return false;
    }

    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(this.maskPath, this.clearPaint);
        if (this.strokeColor != null) {
            this.borderPaint.setStrokeWidth(this.strokeWidth);
            int colorForState = this.strokeColor.getColorForState(getDrawableState(), this.strokeColor.getDefaultColor());
            if (this.strokeWidth > 0.0f && colorForState != 0) {
                this.borderPaint.setColor(colorForState);
                canvas.drawPath(this.path, this.borderPaint);
            }
        }
    }

    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (!this.hasAdjustedPaddingAfterLayoutDirectionResolved && isLayoutDirectionResolved()) {
            this.hasAdjustedPaddingAfterLayoutDirectionResolved = true;
            if (!isPaddingRelative() && this.startContentPadding == Integer.MIN_VALUE && this.endContentPadding == Integer.MIN_VALUE) {
                setPadding(super.getPaddingLeft(), super.getPaddingTop(), super.getPaddingRight(), super.getPaddingBottom());
            } else {
                setPaddingRelative(super.getPaddingStart(), super.getPaddingTop(), super.getPaddingEnd(), super.getPaddingBottom());
            }
        }
    }

    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        updateShapeMask(i, i2);
    }

    public final void setPadding(int i, int i2, int i3, int i4) {
        super.setPadding(getContentPaddingLeft() + i, i2 + this.topContentPadding, getContentPaddingRight() + i3, i4 + this.bottomContentPadding);
    }

    public final void setPaddingRelative(int i, int i2, int i3, int i4) {
        int i5 = this.startContentPadding;
        if (i5 == Integer.MIN_VALUE) {
            if (isRtl$1()) {
                i5 = this.rightContentPadding;
            } else {
                i5 = this.leftContentPadding;
            }
        }
        int i6 = i5 + i;
        int i7 = i2 + this.topContentPadding;
        int i8 = this.endContentPadding;
        if (i8 == Integer.MIN_VALUE) {
            if (isRtl$1()) {
                i8 = this.leftContentPadding;
            } else {
                i8 = this.rightContentPadding;
            }
        }
        super.setPaddingRelative(i6, i7, i8 + i3, i4 + this.bottomContentPadding);
    }

    public final void setShapeAppearanceModel(ShapeAppearanceModel shapeAppearanceModel2) {
        this.shapeAppearanceModel = shapeAppearanceModel2;
        MaterialShapeDrawable materialShapeDrawable = this.shadowDrawable;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.setShapeAppearanceModel(shapeAppearanceModel2);
        }
        updateShapeMask(getWidth(), getHeight());
        invalidate();
        invalidateOutline();
    }

    public final void updateShapeMask(int i, int i2) {
        this.destination.set((float) getPaddingLeft(), (float) getPaddingTop(), (float) (i - getPaddingRight()), (float) (i2 - getPaddingBottom()));
        this.pathProvider.calculatePath(this.shapeAppearanceModel, 1.0f, this.destination, (MaterialShapeDrawable.AnonymousClass1) null, this.path);
        this.maskPath.rewind();
        this.maskPath.addPath(this.path);
        this.maskRect.set(0.0f, 0.0f, (float) i, (float) i2);
        this.maskPath.addRect(this.maskRect, Path.Direction.CCW);
    }
}
