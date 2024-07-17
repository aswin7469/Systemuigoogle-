package com.google.android.material.chip;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.core.graphics.ColorUtils;
import com.google.android.material.internal.TextDrawableHelper;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearancePathProvider;
import java.lang.ref.WeakReference;
import java.util.Arrays;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ChipDrawable extends MaterialShapeDrawable implements Drawable.Callback, TextDrawableHelper.TextDrawableDelegate {
    public static final int[] DEFAULT_STATE = {16842910};
    public static final ShapeDrawable closeIconRippleMask = new ShapeDrawable(new OvalShape());
    public int alpha = 255;
    public boolean checkable;
    public Drawable checkedIcon;
    public ColorStateList checkedIconTint;
    public boolean checkedIconVisible;
    public ColorStateList chipBackgroundColor;
    public float chipCornerRadius = -1.0f;
    public float chipEndPadding;
    public Drawable chipIcon;
    public float chipIconSize;
    public ColorStateList chipIconTint;
    public boolean chipIconVisible;
    public float chipMinHeight;
    public final Paint chipPaint = new Paint(1);
    public float chipStartPadding;
    public ColorStateList chipStrokeColor;
    public float chipStrokeWidth;
    public ColorStateList chipSurfaceColor;
    public Drawable closeIcon;
    public float closeIconEndPadding;
    public Drawable closeIconRipple;
    public float closeIconSize;
    public float closeIconStartPadding;
    public int[] closeIconStateSet;
    public ColorStateList closeIconTint;
    public boolean closeIconVisible;
    public ColorFilter colorFilter;
    public ColorStateList compatRippleColor;
    public final Context context;
    public boolean currentChecked;
    public int currentChipBackgroundColor;
    public int currentChipStrokeColor;
    public int currentChipSurfaceColor;
    public int currentCompatRippleColor;
    public int currentCompositeSurfaceBackgroundColor;
    public int currentTextColor;
    public int currentTint;
    public WeakReference delegate = new WeakReference((Object) null);
    public final Paint.FontMetrics fontMetrics = new Paint.FontMetrics();
    public boolean hasChipIconTint;
    public float iconEndPadding;
    public float iconStartPadding;
    public boolean isShapeThemingEnabled;
    public int maxWidth;
    public final PointF pointF = new PointF();
    public final RectF rectF = new RectF();
    public ColorStateList rippleColor;
    public final Path shapePath = new Path();
    public boolean shouldDrawText;
    public CharSequence text;
    public final TextDrawableHelper textDrawableHelper;
    public float textEndPadding;
    public float textStartPadding;
    public ColorStateList tint;
    public PorterDuffColorFilter tintFilter;
    public PorterDuff.Mode tintMode = PorterDuff.Mode.SRC_IN;
    public TextUtils.TruncateAt truncateAt;
    public boolean useCompatRipple;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public interface Delegate {
    }

    public ChipDrawable(Context context2, AttributeSet attributeSet, int i) {
        super(context2, attributeSet, i, 2132018798);
        initializeElevationOverlay(context2);
        this.context = context2;
        TextDrawableHelper textDrawableHelper2 = new TextDrawableHelper(this);
        this.textDrawableHelper = textDrawableHelper2;
        this.text = "";
        textDrawableHelper2.textPaint.density = context2.getResources().getDisplayMetrics().density;
        int[] iArr = DEFAULT_STATE;
        setState(iArr);
        if (!Arrays.equals(this.closeIconStateSet, iArr)) {
            this.closeIconStateSet = iArr;
            if (showsCloseIcon()) {
                onStateChange(getState(), iArr);
            }
        }
        this.shouldDrawText = true;
        closeIconRippleMask.setTint(-1);
    }

    public static void unapplyChildDrawable(Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback((Drawable.Callback) null);
        }
    }

    public final void applyChildDrawable(Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback(this);
            drawable.setLayoutDirection(getLayoutDirection());
            drawable.setLevel(getLevel());
            drawable.setVisible(isVisible(), false);
            if (drawable == this.closeIcon) {
                if (drawable.isStateful()) {
                    drawable.setState(this.closeIconStateSet);
                }
                drawable.setTintList(this.closeIconTint);
                return;
            }
            Drawable drawable2 = this.chipIcon;
            if (drawable == drawable2 && this.hasChipIconTint) {
                drawable2.setTintList(this.chipIconTint);
            }
            if (drawable.isStateful()) {
                drawable.setState(getState());
            }
        }
    }

    public final void calculateChipIconBounds(Rect rect, RectF rectF2) {
        Drawable drawable;
        Drawable drawable2;
        rectF2.setEmpty();
        if (showsChipIcon() || showsCheckedIcon()) {
            float f = this.chipStartPadding + this.iconStartPadding;
            if (this.currentChecked) {
                drawable = this.checkedIcon;
            } else {
                drawable = this.chipIcon;
            }
            float f2 = this.chipIconSize;
            if (f2 <= 0.0f && drawable != null) {
                f2 = (float) drawable.getIntrinsicWidth();
            }
            if (getLayoutDirection() == 0) {
                float f3 = ((float) rect.left) + f;
                rectF2.left = f3;
                rectF2.right = f3 + f2;
            } else {
                float f4 = ((float) rect.right) - f;
                rectF2.right = f4;
                rectF2.left = f4 - f2;
            }
            if (this.currentChecked) {
                drawable2 = this.checkedIcon;
            } else {
                drawable2 = this.chipIcon;
            }
            float f5 = this.chipIconSize;
            if (f5 <= 0.0f && drawable2 != null) {
                f5 = (float) Math.ceil((double) ViewUtils.dpToPx(24, this.context));
                if (((float) drawable2.getIntrinsicHeight()) <= f5) {
                    f5 = (float) drawable2.getIntrinsicHeight();
                }
            }
            float exactCenterY = rect.exactCenterY() - (f5 / 2.0f);
            rectF2.top = exactCenterY;
            rectF2.bottom = exactCenterY + f5;
        }
    }

    public final float calculateChipIconWidth() {
        Drawable drawable;
        if (!showsChipIcon() && !showsCheckedIcon()) {
            return 0.0f;
        }
        float f = this.iconStartPadding;
        if (this.currentChecked) {
            drawable = this.checkedIcon;
        } else {
            drawable = this.chipIcon;
        }
        float f2 = this.chipIconSize;
        if (f2 <= 0.0f && drawable != null) {
            f2 = (float) drawable.getIntrinsicWidth();
        }
        return f2 + f + this.iconEndPadding;
    }

    public final float calculateCloseIconWidth() {
        if (showsCloseIcon()) {
            return this.closeIconStartPadding + this.closeIconSize + this.closeIconEndPadding;
        }
        return 0.0f;
    }

    public final void draw(Canvas canvas) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        boolean z;
        int i6;
        Canvas canvas2 = canvas;
        Rect bounds = getBounds();
        if (!bounds.isEmpty() && (i = this.alpha) != 0) {
            if (i < 255) {
                i2 = canvas.saveLayerAlpha((float) bounds.left, (float) bounds.top, (float) bounds.right, (float) bounds.bottom, i);
            } else {
                i2 = 0;
            }
            if (!this.isShapeThemingEnabled) {
                this.chipPaint.setColor(this.currentChipSurfaceColor);
                this.chipPaint.setStyle(Paint.Style.FILL);
                this.rectF.set(bounds);
                canvas2.drawRoundRect(this.rectF, getChipCornerRadius(), getChipCornerRadius(), this.chipPaint);
            }
            if (!this.isShapeThemingEnabled) {
                this.chipPaint.setColor(this.currentChipBackgroundColor);
                this.chipPaint.setStyle(Paint.Style.FILL);
                Paint paint = this.chipPaint;
                ColorFilter colorFilter2 = this.colorFilter;
                if (colorFilter2 == null) {
                    colorFilter2 = this.tintFilter;
                }
                paint.setColorFilter(colorFilter2);
                this.rectF.set(bounds);
                canvas2.drawRoundRect(this.rectF, getChipCornerRadius(), getChipCornerRadius(), this.chipPaint);
            }
            if (this.isShapeThemingEnabled) {
                super.draw(canvas);
            }
            if (this.chipStrokeWidth > 0.0f && !this.isShapeThemingEnabled) {
                this.chipPaint.setColor(this.currentChipStrokeColor);
                this.chipPaint.setStyle(Paint.Style.STROKE);
                if (!this.isShapeThemingEnabled) {
                    Paint paint2 = this.chipPaint;
                    ColorFilter colorFilter3 = this.colorFilter;
                    if (colorFilter3 == null) {
                        colorFilter3 = this.tintFilter;
                    }
                    paint2.setColorFilter(colorFilter3);
                }
                RectF rectF2 = this.rectF;
                float f = this.chipStrokeWidth / 2.0f;
                rectF2.set(((float) bounds.left) + f, ((float) bounds.top) + f, ((float) bounds.right) - f, ((float) bounds.bottom) - f);
                float f2 = this.chipCornerRadius - (this.chipStrokeWidth / 2.0f);
                canvas2.drawRoundRect(this.rectF, f2, f2, this.chipPaint);
            }
            this.chipPaint.setColor(this.currentCompatRippleColor);
            this.chipPaint.setStyle(Paint.Style.FILL);
            this.rectF.set(bounds);
            if (!this.isShapeThemingEnabled) {
                canvas2.drawRoundRect(this.rectF, getChipCornerRadius(), getChipCornerRadius(), this.chipPaint);
            } else {
                RectF rectF3 = new RectF(bounds);
                Path path = this.shapePath;
                ShapeAppearancePathProvider shapeAppearancePathProvider = this.pathProvider;
                MaterialShapeDrawable.MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
                shapeAppearancePathProvider.calculatePath(materialShapeDrawableState.shapeAppearanceModel, materialShapeDrawableState.interpolation, rectF3, this.pathShadowListener, path);
                drawShape(canvas, this.chipPaint, this.shapePath, this.drawableState.shapeAppearanceModel, getBoundsAsRectF$1());
            }
            if (showsChipIcon()) {
                calculateChipIconBounds(bounds, this.rectF);
                RectF rectF4 = this.rectF;
                float f3 = rectF4.left;
                float f4 = rectF4.top;
                canvas2.translate(f3, f4);
                this.chipIcon.setBounds(0, 0, (int) this.rectF.width(), (int) this.rectF.height());
                this.chipIcon.draw(canvas2);
                canvas2.translate(-f3, -f4);
            }
            if (showsCheckedIcon()) {
                calculateChipIconBounds(bounds, this.rectF);
                RectF rectF5 = this.rectF;
                float f5 = rectF5.left;
                float f6 = rectF5.top;
                canvas2.translate(f5, f6);
                this.checkedIcon.setBounds(0, 0, (int) this.rectF.width(), (int) this.rectF.height());
                this.checkedIcon.draw(canvas2);
                canvas2.translate(-f5, -f6);
            }
            if (!this.shouldDrawText || this.text == null) {
                i3 = i2;
                i4 = 0;
                i5 = 255;
            } else {
                PointF pointF2 = this.pointF;
                pointF2.set(0.0f, 0.0f);
                Paint.Align align = Paint.Align.LEFT;
                if (this.text != null) {
                    float calculateChipIconWidth = calculateChipIconWidth() + this.chipStartPadding + this.textStartPadding;
                    if (getLayoutDirection() == 0) {
                        pointF2.x = ((float) bounds.left) + calculateChipIconWidth;
                    } else {
                        pointF2.x = ((float) bounds.right) - calculateChipIconWidth;
                        align = Paint.Align.RIGHT;
                    }
                    this.textDrawableHelper.textPaint.getFontMetrics(this.fontMetrics);
                    Paint.FontMetrics fontMetrics2 = this.fontMetrics;
                    pointF2.y = ((float) bounds.centerY()) - ((fontMetrics2.descent + fontMetrics2.ascent) / 2.0f);
                }
                RectF rectF6 = this.rectF;
                rectF6.setEmpty();
                if (this.text != null) {
                    float calculateChipIconWidth2 = calculateChipIconWidth() + this.chipStartPadding + this.textStartPadding;
                    float calculateCloseIconWidth = calculateCloseIconWidth() + this.chipEndPadding + this.textEndPadding;
                    if (getLayoutDirection() == 0) {
                        rectF6.left = ((float) bounds.left) + calculateChipIconWidth2;
                        rectF6.right = ((float) bounds.right) - calculateCloseIconWidth;
                    } else {
                        rectF6.left = ((float) bounds.left) + calculateCloseIconWidth;
                        rectF6.right = ((float) bounds.right) - calculateChipIconWidth2;
                    }
                    rectF6.top = (float) bounds.top;
                    rectF6.bottom = (float) bounds.bottom;
                }
                TextDrawableHelper textDrawableHelper2 = this.textDrawableHelper;
                if (textDrawableHelper2.textAppearance != null) {
                    textDrawableHelper2.textPaint.drawableState = getState();
                    TextDrawableHelper textDrawableHelper3 = this.textDrawableHelper;
                    textDrawableHelper3.textAppearance.updateDrawState(this.context, textDrawableHelper3.textPaint, textDrawableHelper3.fontCallback);
                }
                this.textDrawableHelper.textPaint.setTextAlign(align);
                if (Math.round(this.textDrawableHelper.getTextWidth(this.text.toString())) > Math.round(this.rectF.width())) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    i6 = canvas.save();
                    canvas2.clipRect(this.rectF);
                } else {
                    i6 = 0;
                }
                CharSequence charSequence = this.text;
                if (z && this.truncateAt != null) {
                    charSequence = TextUtils.ellipsize(charSequence, this.textDrawableHelper.textPaint, this.rectF.width(), this.truncateAt);
                }
                CharSequence charSequence2 = charSequence;
                int length = charSequence2.length();
                PointF pointF3 = this.pointF;
                i3 = i2;
                float f7 = pointF3.x;
                i4 = 0;
                float f8 = pointF3.y;
                i5 = 255;
                canvas.drawText(charSequence2, 0, length, f7, f8, this.textDrawableHelper.textPaint);
                if (z) {
                    canvas2.restoreToCount(i6);
                }
            }
            if (showsCloseIcon()) {
                RectF rectF7 = this.rectF;
                rectF7.setEmpty();
                if (showsCloseIcon()) {
                    float f9 = this.chipEndPadding + this.closeIconEndPadding;
                    if (getLayoutDirection() == 0) {
                        float f10 = ((float) bounds.right) - f9;
                        rectF7.right = f10;
                        rectF7.left = f10 - this.closeIconSize;
                    } else {
                        float f11 = ((float) bounds.left) + f9;
                        rectF7.left = f11;
                        rectF7.right = f11 + this.closeIconSize;
                    }
                    float exactCenterY = bounds.exactCenterY();
                    float f12 = this.closeIconSize;
                    float f13 = exactCenterY - (f12 / 2.0f);
                    rectF7.top = f13;
                    rectF7.bottom = f13 + f12;
                }
                RectF rectF8 = this.rectF;
                float f14 = rectF8.left;
                float f15 = rectF8.top;
                canvas2.translate(f14, f15);
                this.closeIcon.setBounds(i4, i4, (int) this.rectF.width(), (int) this.rectF.height());
                this.closeIconRipple.setBounds(this.closeIcon.getBounds());
                this.closeIconRipple.jumpToCurrentState();
                this.closeIconRipple.draw(canvas2);
                canvas2.translate(-f14, -f15);
            }
            if (this.alpha < i5) {
                canvas2.restoreToCount(i3);
            }
        }
    }

    public final int getAlpha() {
        return this.alpha;
    }

    public final float getChipCornerRadius() {
        if (this.isShapeThemingEnabled) {
            return getTopLeftCornerResolvedSize();
        }
        return this.chipCornerRadius;
    }

    public final ColorFilter getColorFilter() {
        return this.colorFilter;
    }

    public final int getIntrinsicHeight() {
        return (int) this.chipMinHeight;
    }

    public final int getIntrinsicWidth() {
        return Math.min(Math.round(calculateCloseIconWidth() + this.textDrawableHelper.getTextWidth(this.text.toString()) + calculateChipIconWidth() + this.chipStartPadding + this.textStartPadding + this.textEndPadding + this.chipEndPadding), this.maxWidth);
    }

    public final int getOpacity() {
        return -3;
    }

    public final void getOutline(Outline outline) {
        if (this.isShapeThemingEnabled) {
            super.getOutline(outline);
            return;
        }
        Rect bounds = getBounds();
        if (!bounds.isEmpty()) {
            outline.setRoundRect(bounds, this.chipCornerRadius);
        } else {
            outline.setRoundRect(0, 0, getIntrinsicWidth(), (int) this.chipMinHeight, this.chipCornerRadius);
        }
        outline.setAlpha(((float) this.alpha) / 255.0f);
    }

    public final void invalidateDrawable(Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002a, code lost:
        r0 = (r0 = r1.textDrawableHelper.textAppearance).textColor;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean isStateful() {
        /*
            r1 = this;
            android.content.res.ColorStateList r0 = r1.chipSurfaceColor
            boolean r0 = isStateful((android.content.res.ColorStateList) r0)
            if (r0 != 0) goto L_0x005d
            android.content.res.ColorStateList r0 = r1.chipBackgroundColor
            boolean r0 = isStateful((android.content.res.ColorStateList) r0)
            if (r0 != 0) goto L_0x005d
            android.content.res.ColorStateList r0 = r1.chipStrokeColor
            boolean r0 = isStateful((android.content.res.ColorStateList) r0)
            if (r0 != 0) goto L_0x005d
            boolean r0 = r1.useCompatRipple
            if (r0 == 0) goto L_0x0024
            android.content.res.ColorStateList r0 = r1.compatRippleColor
            boolean r0 = isStateful((android.content.res.ColorStateList) r0)
            if (r0 != 0) goto L_0x005d
        L_0x0024:
            com.google.android.material.internal.TextDrawableHelper r0 = r1.textDrawableHelper
            com.google.android.material.resources.TextAppearance r0 = r0.textAppearance
            if (r0 == 0) goto L_0x0035
            android.content.res.ColorStateList r0 = r0.textColor
            if (r0 == 0) goto L_0x0035
            boolean r0 = r0.isStateful()
            if (r0 == 0) goto L_0x0035
            goto L_0x005d
        L_0x0035:
            boolean r0 = r1.checkedIconVisible
            if (r0 == 0) goto L_0x0042
            android.graphics.drawable.Drawable r0 = r1.checkedIcon
            if (r0 == 0) goto L_0x0042
            boolean r0 = r1.checkable
            if (r0 == 0) goto L_0x0042
            goto L_0x005d
        L_0x0042:
            android.graphics.drawable.Drawable r0 = r1.chipIcon
            boolean r0 = isStateful((android.graphics.drawable.Drawable) r0)
            if (r0 != 0) goto L_0x005d
            android.graphics.drawable.Drawable r0 = r1.checkedIcon
            boolean r0 = isStateful((android.graphics.drawable.Drawable) r0)
            if (r0 != 0) goto L_0x005d
            android.content.res.ColorStateList r1 = r1.tint
            boolean r1 = isStateful((android.content.res.ColorStateList) r1)
            if (r1 == 0) goto L_0x005b
            goto L_0x005d
        L_0x005b:
            r1 = 0
            goto L_0x005e
        L_0x005d:
            r1 = 1
        L_0x005e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.chip.ChipDrawable.isStateful():boolean");
    }

    public final boolean onLayoutDirectionChanged(int i) {
        boolean onLayoutDirectionChanged = super.onLayoutDirectionChanged(i);
        if (showsChipIcon()) {
            onLayoutDirectionChanged |= this.chipIcon.setLayoutDirection(i);
        }
        if (showsCheckedIcon()) {
            onLayoutDirectionChanged |= this.checkedIcon.setLayoutDirection(i);
        }
        if (showsCloseIcon()) {
            onLayoutDirectionChanged |= this.closeIcon.setLayoutDirection(i);
        }
        if (!onLayoutDirectionChanged) {
            return true;
        }
        invalidateSelf();
        return true;
    }

    public final boolean onLevelChange(int i) {
        boolean onLevelChange = super.onLevelChange(i);
        if (showsChipIcon()) {
            onLevelChange |= this.chipIcon.setLevel(i);
        }
        if (showsCheckedIcon()) {
            onLevelChange |= this.checkedIcon.setLevel(i);
        }
        if (showsCloseIcon()) {
            onLevelChange |= this.closeIcon.setLevel(i);
        }
        if (onLevelChange) {
            invalidateSelf();
        }
        return onLevelChange;
    }

    public final void onSizeChange() {
        Delegate delegate2 = (Delegate) this.delegate.get();
        if (delegate2 != null) {
            Chip chip = (Chip) delegate2;
            chip.ensureAccessibleTouchTarget(chip.minTouchTargetSize);
            chip.requestLayout();
            chip.invalidateOutline();
        }
    }

    public final boolean onStateChange(int[] iArr) {
        if (this.isShapeThemingEnabled) {
            super.onStateChange(iArr);
        }
        return onStateChange(iArr, this.closeIconStateSet);
    }

    public final void onTextSizeChange() {
        onSizeChange();
        invalidateSelf();
    }

    public final void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j);
        }
    }

    public final void setAlpha(int i) {
        if (this.alpha != i) {
            this.alpha = i;
            invalidateSelf();
        }
    }

    public final void setCheckedIconVisible(boolean z) {
        if (this.checkedIconVisible != z) {
            boolean showsCheckedIcon = showsCheckedIcon();
            this.checkedIconVisible = z;
            boolean showsCheckedIcon2 = showsCheckedIcon();
            if (showsCheckedIcon != showsCheckedIcon2) {
                if (showsCheckedIcon2) {
                    applyChildDrawable(this.checkedIcon);
                } else {
                    unapplyChildDrawable(this.checkedIcon);
                }
                invalidateSelf();
                onSizeChange();
            }
        }
    }

    public final void setChipIconVisible(boolean z) {
        if (this.chipIconVisible != z) {
            boolean showsChipIcon = showsChipIcon();
            this.chipIconVisible = z;
            boolean showsChipIcon2 = showsChipIcon();
            if (showsChipIcon != showsChipIcon2) {
                if (showsChipIcon2) {
                    applyChildDrawable(this.chipIcon);
                } else {
                    unapplyChildDrawable(this.chipIcon);
                }
                invalidateSelf();
                onSizeChange();
            }
        }
    }

    public final void setCloseIconVisible(boolean z) {
        if (this.closeIconVisible != z) {
            boolean showsCloseIcon = showsCloseIcon();
            this.closeIconVisible = z;
            boolean showsCloseIcon2 = showsCloseIcon();
            if (showsCloseIcon != showsCloseIcon2) {
                if (showsCloseIcon2) {
                    applyChildDrawable(this.closeIcon);
                } else {
                    unapplyChildDrawable(this.closeIcon);
                }
                invalidateSelf();
                onSizeChange();
            }
        }
    }

    public final void setColorFilter(ColorFilter colorFilter2) {
        if (this.colorFilter != colorFilter2) {
            this.colorFilter = colorFilter2;
            invalidateSelf();
        }
    }

    public final void setTintList(ColorStateList colorStateList) {
        if (this.tint != colorStateList) {
            this.tint = colorStateList;
            onStateChange(getState());
        }
    }

    public final void setTintMode(PorterDuff.Mode mode) {
        PorterDuffColorFilter porterDuffColorFilter;
        if (this.tintMode != mode) {
            this.tintMode = mode;
            ColorStateList colorStateList = this.tint;
            if (colorStateList == null || mode == null) {
                porterDuffColorFilter = null;
            } else {
                porterDuffColorFilter = new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
            }
            this.tintFilter = porterDuffColorFilter;
            invalidateSelf();
        }
    }

    public final boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (showsChipIcon()) {
            visible |= this.chipIcon.setVisible(z, z2);
        }
        if (showsCheckedIcon()) {
            visible |= this.checkedIcon.setVisible(z, z2);
        }
        if (showsCloseIcon()) {
            visible |= this.closeIcon.setVisible(z, z2);
        }
        if (visible) {
            invalidateSelf();
        }
        return visible;
    }

    public final boolean showsCheckedIcon() {
        if (!this.checkedIconVisible || this.checkedIcon == null || !this.currentChecked) {
            return false;
        }
        return true;
    }

    public final boolean showsChipIcon() {
        if (!this.chipIconVisible || this.chipIcon == null) {
            return false;
        }
        return true;
    }

    public final boolean showsCloseIcon() {
        if (!this.closeIconVisible || this.closeIcon == null) {
            return false;
        }
        return true;
    }

    public final void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, runnable);
        }
    }

    public final boolean onStateChange(int[] iArr, int[] iArr2) {
        boolean z;
        boolean z2;
        ColorStateList colorStateList;
        boolean onStateChange = super.onStateChange(iArr);
        ColorStateList colorStateList2 = this.chipSurfaceColor;
        int compositeElevationOverlayIfNeeded = compositeElevationOverlayIfNeeded(colorStateList2 != null ? colorStateList2.getColorForState(iArr, this.currentChipSurfaceColor) : 0);
        boolean z3 = true;
        if (this.currentChipSurfaceColor != compositeElevationOverlayIfNeeded) {
            this.currentChipSurfaceColor = compositeElevationOverlayIfNeeded;
            onStateChange = true;
        }
        ColorStateList colorStateList3 = this.chipBackgroundColor;
        int compositeElevationOverlayIfNeeded2 = compositeElevationOverlayIfNeeded(colorStateList3 != null ? colorStateList3.getColorForState(iArr, this.currentChipBackgroundColor) : 0);
        if (this.currentChipBackgroundColor != compositeElevationOverlayIfNeeded2) {
            this.currentChipBackgroundColor = compositeElevationOverlayIfNeeded2;
            onStateChange = true;
        }
        int compositeColors = ColorUtils.compositeColors(compositeElevationOverlayIfNeeded2, compositeElevationOverlayIfNeeded);
        if ((this.currentCompositeSurfaceBackgroundColor != compositeColors) || (this.drawableState.fillColor == null)) {
            this.currentCompositeSurfaceBackgroundColor = compositeColors;
            setFillColor(ColorStateList.valueOf(compositeColors));
            onStateChange = true;
        }
        ColorStateList colorStateList4 = this.chipStrokeColor;
        int colorForState = colorStateList4 != null ? colorStateList4.getColorForState(iArr, this.currentChipStrokeColor) : 0;
        if (this.currentChipStrokeColor != colorForState) {
            this.currentChipStrokeColor = colorForState;
            onStateChange = true;
        }
        int colorForState2 = (this.compatRippleColor == null || !RippleUtils.shouldDrawRippleCompat(iArr)) ? 0 : this.compatRippleColor.getColorForState(iArr, this.currentCompatRippleColor);
        if (this.currentCompatRippleColor != colorForState2) {
            this.currentCompatRippleColor = colorForState2;
            if (this.useCompatRipple) {
                onStateChange = true;
            }
        }
        TextAppearance textAppearance = this.textDrawableHelper.textAppearance;
        int colorForState3 = (textAppearance == null || (colorStateList = textAppearance.textColor) == null) ? 0 : colorStateList.getColorForState(iArr, this.currentTextColor);
        if (this.currentTextColor != colorForState3) {
            this.currentTextColor = colorForState3;
            onStateChange = true;
        }
        int[] state = getState();
        if (state != null) {
            int length = state.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                } else if (state[i] != 16842912) {
                    i++;
                } else if (this.checkable) {
                    z = true;
                }
            }
        }
        z = false;
        if (this.currentChecked == z || this.checkedIcon == null) {
            z2 = false;
        } else {
            float calculateChipIconWidth = calculateChipIconWidth();
            this.currentChecked = z;
            if (calculateChipIconWidth != calculateChipIconWidth()) {
                onStateChange = true;
                z2 = true;
            } else {
                z2 = false;
                onStateChange = true;
            }
        }
        ColorStateList colorStateList5 = this.tint;
        int colorForState4 = colorStateList5 != null ? colorStateList5.getColorForState(iArr, this.currentTint) : 0;
        if (this.currentTint != colorForState4) {
            this.currentTint = colorForState4;
            ColorStateList colorStateList6 = this.tint;
            PorterDuff.Mode mode = this.tintMode;
            this.tintFilter = (colorStateList6 == null || mode == null) ? null : new PorterDuffColorFilter(colorStateList6.getColorForState(getState(), 0), mode);
        } else {
            z3 = onStateChange;
        }
        if (isStateful(this.chipIcon)) {
            z3 |= this.chipIcon.setState(iArr);
        }
        if (isStateful(this.checkedIcon)) {
            z3 |= this.checkedIcon.setState(iArr);
        }
        if (isStateful(this.closeIcon)) {
            int[] iArr3 = new int[(iArr.length + iArr2.length)];
            System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
            System.arraycopy(iArr2, 0, iArr3, iArr.length, iArr2.length);
            z3 |= this.closeIcon.setState(iArr3);
        }
        if (isStateful(this.closeIconRipple)) {
            z3 |= this.closeIconRipple.setState(iArr2);
        }
        if (z3) {
            invalidateSelf();
        }
        if (z2) {
            onSizeChange();
        }
        return z3;
    }

    public static boolean isStateful(ColorStateList colorStateList) {
        return colorStateList != null && colorStateList.isStateful();
    }

    public static boolean isStateful(Drawable drawable) {
        return drawable != null && drawable.isStateful();
    }
}
