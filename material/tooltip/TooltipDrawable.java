package com.google.android.material.tooltip;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.material.internal.TextDrawableHelper;
import com.google.android.material.shape.MarkerEdgeTreatment;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.OffsetEdgeTreatment;
import com.google.android.material.shape.ShapeAppearanceModel;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class TooltipDrawable extends MaterialShapeDrawable implements TextDrawableHelper.TextDrawableDelegate {
    public int arrowSize;
    public final AnonymousClass1 attachedViewLayoutChangeListener;
    public final Context context;
    public final Rect displayFrame;
    public final Paint.FontMetrics fontMetrics = new Paint.FontMetrics();
    public float labelOpacity;
    public int layoutMargin;
    public int locationOnScreenX;
    public int minHeight;
    public int minWidth;
    public int padding;
    public CharSequence text;
    public final TextDrawableHelper textDrawableHelper;
    public float tooltipPivotY;
    public float tooltipScaleX;
    public float tooltipScaleY;

    public TooltipDrawable(Context context2, int i) {
        super(context2, (AttributeSet) null, 0, i);
        TextDrawableHelper textDrawableHelper2 = new TextDrawableHelper(this);
        this.textDrawableHelper = textDrawableHelper2;
        this.attachedViewLayoutChangeListener = new View.OnLayoutChangeListener() {
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                TooltipDrawable tooltipDrawable = TooltipDrawable.this;
                tooltipDrawable.getClass();
                int[] iArr = new int[2];
                view.getLocationOnScreen(iArr);
                tooltipDrawable.locationOnScreenX = iArr[0];
                view.getWindowVisibleDisplayFrame(tooltipDrawable.displayFrame);
            }
        };
        this.displayFrame = new Rect();
        this.tooltipScaleX = 1.0f;
        this.tooltipScaleY = 1.0f;
        this.tooltipPivotY = 0.5f;
        this.labelOpacity = 1.0f;
        this.context = context2;
        TextPaint textPaint = textDrawableHelper2.textPaint;
        textPaint.density = context2.getResources().getDisplayMetrics().density;
        textPaint.setTextAlign(Paint.Align.CENTER);
    }

    public final float calculatePointerOffset() {
        int i;
        if (((this.displayFrame.right - getBounds().right) - this.locationOnScreenX) - this.layoutMargin < 0) {
            i = ((this.displayFrame.right - getBounds().right) - this.locationOnScreenX) - this.layoutMargin;
        } else if (((this.displayFrame.left - getBounds().left) - this.locationOnScreenX) + this.layoutMargin <= 0) {
            return 0.0f;
        } else {
            i = ((this.displayFrame.left - getBounds().left) - this.locationOnScreenX) + this.layoutMargin;
        }
        return (float) i;
    }

    public final OffsetEdgeTreatment createMarkerEdge() {
        float width = ((float) (((double) getBounds().width()) - (Math.sqrt(2.0d) * ((double) this.arrowSize)))) / 2.0f;
        return new OffsetEdgeTreatment(new MarkerEdgeTreatment((float) this.arrowSize), Math.min(Math.max(-calculatePointerOffset(), -width), width));
    }

    public final void draw(Canvas canvas) {
        canvas.save();
        float calculatePointerOffset = calculatePointerOffset();
        double sqrt = Math.sqrt(2.0d);
        canvas.scale(this.tooltipScaleX, this.tooltipScaleY, (((float) getBounds().width()) * 0.5f) + ((float) getBounds().left), (((float) getBounds().height()) * this.tooltipPivotY) + ((float) getBounds().top));
        canvas.translate(calculatePointerOffset, (float) (-((sqrt * ((double) this.arrowSize)) - ((double) this.arrowSize))));
        super.draw(canvas);
        if (this.text != null) {
            Rect bounds = getBounds();
            this.textDrawableHelper.textPaint.getFontMetrics(this.fontMetrics);
            Paint.FontMetrics fontMetrics2 = this.fontMetrics;
            int centerY = (int) (((float) bounds.centerY()) - ((fontMetrics2.descent + fontMetrics2.ascent) / 2.0f));
            TextDrawableHelper textDrawableHelper2 = this.textDrawableHelper;
            if (textDrawableHelper2.textAppearance != null) {
                textDrawableHelper2.textPaint.drawableState = getState();
                TextDrawableHelper textDrawableHelper3 = this.textDrawableHelper;
                textDrawableHelper3.textAppearance.updateDrawState(this.context, textDrawableHelper3.textPaint, textDrawableHelper3.fontCallback);
                this.textDrawableHelper.textPaint.setAlpha((int) (this.labelOpacity * 255.0f));
            }
            CharSequence charSequence = this.text;
            canvas.drawText(charSequence, 0, charSequence.length(), (float) bounds.centerX(), (float) centerY, this.textDrawableHelper.textPaint);
        }
        canvas.restore();
    }

    public final int getIntrinsicHeight() {
        return (int) Math.max(this.textDrawableHelper.textPaint.getTextSize(), (float) this.minHeight);
    }

    public final int getIntrinsicWidth() {
        float f;
        float f2 = (float) (this.padding * 2);
        CharSequence charSequence = this.text;
        if (charSequence == null) {
            f = 0.0f;
        } else {
            f = this.textDrawableHelper.getTextWidth(charSequence.toString());
        }
        return (int) Math.max(f2 + f, (float) this.minWidth);
    }

    public final void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        ShapeAppearanceModel.Builder builder = this.drawableState.shapeAppearanceModel.toBuilder();
        builder.bottomEdge = createMarkerEdge();
        setShapeAppearanceModel(builder.build());
    }

    public final boolean onStateChange(int[] iArr) {
        return super.onStateChange(iArr);
    }
}
