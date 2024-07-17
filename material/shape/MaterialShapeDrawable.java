package com.google.android.material.shape;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import com.google.android.material.elevation.ElevationOverlayProvider;
import com.google.android.material.shadow.ShadowRenderer;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.ShapeAppearancePathProvider;
import com.google.android.material.shape.ShapePath;
import java.util.BitSet;
import java.util.Objects;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class MaterialShapeDrawable extends Drawable implements Shapeable {
    public static final Paint clearPaint;
    public final BitSet containsIncompatibleShadowOp;
    public final ShapePath.ShadowCompatOperation[] cornerShadowOperation;
    public MaterialShapeDrawableState drawableState;
    public final ShapePath.ShadowCompatOperation[] edgeShadowOperation;
    public final Paint fillPaint;
    public final RectF insetRectF;
    public final Matrix matrix;
    public final Path path;
    public final RectF pathBounds;
    public boolean pathDirty;
    public final Path pathInsetByStroke;
    public final ShapeAppearancePathProvider pathProvider;
    public final AnonymousClass1 pathShadowListener;
    public final RectF rectF;
    public int resolvedTintColor;
    public final Region scratchRegion;
    public boolean shadowBitmapDrawingEnable;
    public final ShadowRenderer shadowRenderer;
    public final Paint strokePaint;
    public ShapeAppearanceModel strokeShapeAppearance;
    public PorterDuffColorFilter strokeTintFilter;
    public PorterDuffColorFilter tintFilter;
    public final Region transparentRegion;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class MaterialShapeDrawableState extends Drawable.ConstantState {
        public int alpha;
        public float elevation;
        public ElevationOverlayProvider elevationOverlayProvider;
        public ColorStateList fillColor;
        public float interpolation;
        public Rect padding;
        public Paint.Style paintStyle;
        public float parentAbsoluteElevation;
        public float scale;
        public int shadowCompatMode;
        public int shadowCompatOffset;
        public int shadowCompatRadius;
        public int shadowCompatRotation;
        public ShapeAppearanceModel shapeAppearanceModel;
        public ColorStateList strokeColor;
        public ColorStateList strokeTintList;
        public float strokeWidth;
        public ColorStateList tintList;
        public PorterDuff.Mode tintMode;
        public float translationZ;
        public boolean useTintColorForShadow;

        public final int getChangingConfigurations() {
            return 0;
        }

        public final Drawable newDrawable() {
            MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(this);
            materialShapeDrawable.pathDirty = true;
            return materialShapeDrawable;
        }
    }

    static {
        Class<MaterialShapeDrawable> cls = MaterialShapeDrawable.class;
        Paint paint = new Paint(1);
        clearPaint = paint;
        paint.setColor(-1);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    }

    public MaterialShapeDrawable() {
        this(new ShapeAppearanceModel());
    }

    public final void calculatePath(RectF rectF2, Path path2) {
        ShapeAppearancePathProvider shapeAppearancePathProvider = this.pathProvider;
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        ShapeAppearanceModel shapeAppearanceModel = materialShapeDrawableState.shapeAppearanceModel;
        float f = materialShapeDrawableState.interpolation;
        shapeAppearancePathProvider.calculatePath(shapeAppearanceModel, f, rectF2, this.pathShadowListener, path2);
        if (this.drawableState.scale != 1.0f) {
            this.matrix.reset();
            Matrix matrix2 = this.matrix;
            float f2 = this.drawableState.scale;
            matrix2.setScale(f2, f2, rectF2.width() / 2.0f, rectF2.height() / 2.0f);
            path2.transform(this.matrix);
        }
        path2.computeBounds(this.pathBounds, true);
    }

    public final PorterDuffColorFilter calculateTintFilter(ColorStateList colorStateList, PorterDuff.Mode mode, Paint paint, boolean z) {
        if (colorStateList == null || mode == null) {
            if (z) {
                int color = paint.getColor();
                int compositeElevationOverlayIfNeeded = compositeElevationOverlayIfNeeded(color);
                this.resolvedTintColor = compositeElevationOverlayIfNeeded;
                if (compositeElevationOverlayIfNeeded != color) {
                    return new PorterDuffColorFilter(compositeElevationOverlayIfNeeded, PorterDuff.Mode.SRC_IN);
                }
            }
            return null;
        }
        int colorForState = colorStateList.getColorForState(getState(), 0);
        if (z) {
            colorForState = compositeElevationOverlayIfNeeded(colorForState);
        }
        this.resolvedTintColor = colorForState;
        return new PorterDuffColorFilter(colorForState, mode);
    }

    public final int compositeElevationOverlayIfNeeded(int i) {
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        float f = materialShapeDrawableState.elevation + materialShapeDrawableState.translationZ + materialShapeDrawableState.parentAbsoluteElevation;
        ElevationOverlayProvider elevationOverlayProvider = materialShapeDrawableState.elevationOverlayProvider;
        if (elevationOverlayProvider != null) {
            return elevationOverlayProvider.compositeOverlayIfNeeded(i, f);
        }
        return i;
    }

    public void draw(Canvas canvas) {
        float f;
        this.fillPaint.setColorFilter(this.tintFilter);
        int alpha = this.fillPaint.getAlpha();
        Paint paint = this.fillPaint;
        int i = this.drawableState.alpha;
        paint.setAlpha(((i + (i >>> 7)) * alpha) >>> 8);
        this.strokePaint.setColorFilter(this.strokeTintFilter);
        this.strokePaint.setStrokeWidth(this.drawableState.strokeWidth);
        int alpha2 = this.strokePaint.getAlpha();
        Paint paint2 = this.strokePaint;
        int i2 = this.drawableState.alpha;
        paint2.setAlpha(((i2 + (i2 >>> 7)) * alpha2) >>> 8);
        if (this.pathDirty) {
            float f2 = 0.0f;
            if (hasStroke()) {
                f = this.strokePaint.getStrokeWidth() / 2.0f;
            } else {
                f = 0.0f;
            }
            float f3 = -f;
            ShapeAppearanceModel shapeAppearanceModel = this.drawableState.shapeAppearanceModel;
            ShapeAppearanceModel.Builder builder = shapeAppearanceModel.toBuilder();
            CornerSize cornerSize = shapeAppearanceModel.topLeftCornerSize;
            if (!(cornerSize instanceof RelativeCornerSize)) {
                cornerSize = new AdjustedCornerSize(f3, cornerSize);
            }
            builder.topLeftCornerSize = cornerSize;
            CornerSize cornerSize2 = shapeAppearanceModel.topRightCornerSize;
            if (!(cornerSize2 instanceof RelativeCornerSize)) {
                cornerSize2 = new AdjustedCornerSize(f3, cornerSize2);
            }
            builder.topRightCornerSize = cornerSize2;
            CornerSize cornerSize3 = shapeAppearanceModel.bottomLeftCornerSize;
            if (!(cornerSize3 instanceof RelativeCornerSize)) {
                cornerSize3 = new AdjustedCornerSize(f3, cornerSize3);
            }
            builder.bottomLeftCornerSize = cornerSize3;
            CornerSize cornerSize4 = shapeAppearanceModel.bottomRightCornerSize;
            if (!(cornerSize4 instanceof RelativeCornerSize)) {
                cornerSize4 = new AdjustedCornerSize(f3, cornerSize4);
            }
            builder.bottomRightCornerSize = cornerSize4;
            ShapeAppearanceModel build = builder.build();
            this.strokeShapeAppearance = build;
            ShapeAppearancePathProvider shapeAppearancePathProvider = this.pathProvider;
            float f4 = this.drawableState.interpolation;
            this.insetRectF.set(getBoundsAsRectF$1());
            if (hasStroke()) {
                f2 = this.strokePaint.getStrokeWidth() / 2.0f;
            }
            this.insetRectF.inset(f2, f2);
            shapeAppearancePathProvider.calculatePath(build, f4, this.insetRectF, (AnonymousClass1) null, this.pathInsetByStroke);
            calculatePath(getBoundsAsRectF$1(), this.path);
            this.pathDirty = false;
        }
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        int i3 = materialShapeDrawableState.shadowCompatMode;
        if (i3 != 1 && materialShapeDrawableState.shadowCompatRadius > 0) {
            if (i3 == 2) {
                canvas.save();
                MaterialShapeDrawableState materialShapeDrawableState2 = this.drawableState;
                canvas.translate((float) ((int) (Math.sin(Math.toRadians((double) materialShapeDrawableState2.shadowCompatRotation)) * ((double) materialShapeDrawableState2.shadowCompatOffset))), (float) getShadowOffsetY());
                if (!this.shadowBitmapDrawingEnable) {
                    drawCompatShadow(canvas);
                    canvas.restore();
                } else {
                    int width = (int) (this.pathBounds.width() - ((float) getBounds().width()));
                    int height = (int) (this.pathBounds.height() - ((float) getBounds().height()));
                    if (width < 0 || height < 0) {
                        throw new IllegalStateException("Invalid shadow bounds. Check that the treatments result in a valid path.");
                    }
                    Bitmap createBitmap = Bitmap.createBitmap((this.drawableState.shadowCompatRadius * 2) + ((int) this.pathBounds.width()) + width, (this.drawableState.shadowCompatRadius * 2) + ((int) this.pathBounds.height()) + height, Bitmap.Config.ARGB_8888);
                    Canvas canvas2 = new Canvas(createBitmap);
                    float f5 = (float) ((getBounds().left - this.drawableState.shadowCompatRadius) - width);
                    float f6 = (float) ((getBounds().top - this.drawableState.shadowCompatRadius) - height);
                    canvas2.translate(-f5, -f6);
                    drawCompatShadow(canvas2);
                    canvas.drawBitmap(createBitmap, f5, f6, (Paint) null);
                    createBitmap.recycle();
                    canvas.restore();
                }
            } else if (!isRoundRect()) {
                this.path.isConvex();
            }
        }
        MaterialShapeDrawableState materialShapeDrawableState3 = this.drawableState;
        Paint.Style style = materialShapeDrawableState3.paintStyle;
        if (style == Paint.Style.FILL_AND_STROKE || style == Paint.Style.FILL) {
            drawShape(canvas, this.fillPaint, this.path, materialShapeDrawableState3.shapeAppearanceModel, getBoundsAsRectF$1());
        }
        if (hasStroke()) {
            drawStrokeShape(canvas);
        }
        this.fillPaint.setAlpha(alpha);
        this.strokePaint.setAlpha(alpha2);
    }

    public final void drawCompatShadow(Canvas canvas) {
        if (this.containsIncompatibleShadowOp.cardinality() > 0) {
            Log.w("MaterialShapeDrawable", "Compatibility shadow requested but can't be drawn for all operations in this shape.");
        }
        if (this.drawableState.shadowCompatOffset != 0) {
            canvas.drawPath(this.path, this.shadowRenderer.shadowPaint);
        }
        for (int i = 0; i < 4; i++) {
            ShapePath.ShadowCompatOperation shadowCompatOperation = this.cornerShadowOperation[i];
            ShadowRenderer shadowRenderer2 = this.shadowRenderer;
            int i2 = this.drawableState.shadowCompatRadius;
            Matrix matrix2 = ShapePath.ShadowCompatOperation.IDENTITY_MATRIX;
            shadowCompatOperation.draw(matrix2, shadowRenderer2, i2, canvas);
            this.edgeShadowOperation[i].draw(matrix2, this.shadowRenderer, this.drawableState.shadowCompatRadius, canvas);
        }
        if (this.shadowBitmapDrawingEnable) {
            MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
            int sin = (int) (Math.sin(Math.toRadians((double) materialShapeDrawableState.shadowCompatRotation)) * ((double) materialShapeDrawableState.shadowCompatOffset));
            int shadowOffsetY = getShadowOffsetY();
            canvas.translate((float) (-sin), (float) (-shadowOffsetY));
            canvas.drawPath(this.path, clearPaint);
            canvas.translate((float) sin, (float) shadowOffsetY);
        }
    }

    public final void drawShape(Canvas canvas, Paint paint, Path path2, ShapeAppearanceModel shapeAppearanceModel, RectF rectF2) {
        if (shapeAppearanceModel.isRoundRect(rectF2)) {
            float cornerSize = shapeAppearanceModel.topRightCornerSize.getCornerSize(rectF2) * this.drawableState.interpolation;
            canvas.drawRoundRect(rectF2, cornerSize, cornerSize, paint);
            return;
        }
        canvas.drawPath(path2, paint);
    }

    public void drawStrokeShape(Canvas canvas) {
        float f;
        Paint paint = this.strokePaint;
        Path path2 = this.pathInsetByStroke;
        ShapeAppearanceModel shapeAppearanceModel = this.strokeShapeAppearance;
        this.insetRectF.set(getBoundsAsRectF$1());
        if (hasStroke()) {
            f = this.strokePaint.getStrokeWidth() / 2.0f;
        } else {
            f = 0.0f;
        }
        this.insetRectF.inset(f, f);
        drawShape(canvas, paint, path2, shapeAppearanceModel, this.insetRectF);
    }

    public int getAlpha() {
        return this.drawableState.alpha;
    }

    public final RectF getBoundsAsRectF$1() {
        this.rectF.set(getBounds());
        return this.rectF;
    }

    public final Drawable.ConstantState getConstantState() {
        return this.drawableState;
    }

    public int getOpacity() {
        return -3;
    }

    public void getOutline(Outline outline) {
        if (this.drawableState.shadowCompatMode != 2) {
            if (isRoundRect()) {
                outline.setRoundRect(getBounds(), getTopLeftCornerResolvedSize() * this.drawableState.interpolation);
                return;
            }
            calculatePath(getBoundsAsRectF$1(), this.path);
            this.path.isConvex();
            try {
                outline.setConvexPath(this.path);
            } catch (IllegalArgumentException unused) {
            }
        }
    }

    public final boolean getPadding(Rect rect) {
        Rect rect2 = this.drawableState.padding;
        if (rect2 == null) {
            return super.getPadding(rect);
        }
        rect.set(rect2);
        return true;
    }

    public final int getShadowOffsetY() {
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        return (int) (Math.cos(Math.toRadians((double) materialShapeDrawableState.shadowCompatRotation)) * ((double) materialShapeDrawableState.shadowCompatOffset));
    }

    public final float getTopLeftCornerResolvedSize() {
        return this.drawableState.shapeAppearanceModel.topLeftCornerSize.getCornerSize(getBoundsAsRectF$1());
    }

    public final Region getTransparentRegion() {
        this.transparentRegion.set(getBounds());
        calculatePath(getBoundsAsRectF$1(), this.path);
        this.scratchRegion.setPath(this.path, this.transparentRegion);
        this.transparentRegion.op(this.scratchRegion, Region.Op.DIFFERENCE);
        return this.transparentRegion;
    }

    public final boolean hasStroke() {
        Paint.Style style = this.drawableState.paintStyle;
        if ((style == Paint.Style.FILL_AND_STROKE || style == Paint.Style.STROKE) && this.strokePaint.getStrokeWidth() > 0.0f) {
            return true;
        }
        return false;
    }

    public final void initializeElevationOverlay(Context context) {
        this.drawableState.elevationOverlayProvider = new ElevationOverlayProvider(context);
        updateZ();
    }

    public final void invalidateSelf() {
        this.pathDirty = true;
        super.invalidateSelf();
    }

    public final boolean isRoundRect() {
        return this.drawableState.shapeAppearanceModel.isRoundRect(getBoundsAsRectF$1());
    }

    public boolean isStateful() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        ColorStateList colorStateList3;
        ColorStateList colorStateList4;
        if (super.isStateful() || (((colorStateList = this.drawableState.tintList) != null && colorStateList.isStateful()) || (((colorStateList2 = this.drawableState.strokeTintList) != null && colorStateList2.isStateful()) || (((colorStateList3 = this.drawableState.strokeColor) != null && colorStateList3.isStateful()) || ((colorStateList4 = this.drawableState.fillColor) != null && colorStateList4.isStateful()))))) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [android.graphics.drawable.Drawable$ConstantState, com.google.android.material.shape.MaterialShapeDrawable$MaterialShapeDrawableState] */
    public final Drawable mutate() {
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        ? constantState = new Drawable.ConstantState();
        constantState.fillColor = null;
        constantState.strokeColor = null;
        constantState.strokeTintList = null;
        constantState.tintList = null;
        constantState.tintMode = PorterDuff.Mode.SRC_IN;
        constantState.padding = null;
        constantState.scale = 1.0f;
        constantState.interpolation = 1.0f;
        constantState.alpha = 255;
        constantState.parentAbsoluteElevation = 0.0f;
        constantState.elevation = 0.0f;
        constantState.translationZ = 0.0f;
        constantState.shadowCompatMode = 0;
        constantState.shadowCompatRadius = 0;
        constantState.shadowCompatOffset = 0;
        constantState.shadowCompatRotation = 0;
        constantState.useTintColorForShadow = false;
        constantState.paintStyle = Paint.Style.FILL_AND_STROKE;
        constantState.shapeAppearanceModel = materialShapeDrawableState.shapeAppearanceModel;
        constantState.elevationOverlayProvider = materialShapeDrawableState.elevationOverlayProvider;
        constantState.strokeWidth = materialShapeDrawableState.strokeWidth;
        constantState.fillColor = materialShapeDrawableState.fillColor;
        constantState.strokeColor = materialShapeDrawableState.strokeColor;
        constantState.tintMode = materialShapeDrawableState.tintMode;
        constantState.tintList = materialShapeDrawableState.tintList;
        constantState.alpha = materialShapeDrawableState.alpha;
        constantState.scale = materialShapeDrawableState.scale;
        constantState.shadowCompatOffset = materialShapeDrawableState.shadowCompatOffset;
        constantState.shadowCompatMode = materialShapeDrawableState.shadowCompatMode;
        constantState.useTintColorForShadow = materialShapeDrawableState.useTintColorForShadow;
        constantState.interpolation = materialShapeDrawableState.interpolation;
        constantState.parentAbsoluteElevation = materialShapeDrawableState.parentAbsoluteElevation;
        constantState.elevation = materialShapeDrawableState.elevation;
        constantState.translationZ = materialShapeDrawableState.translationZ;
        constantState.shadowCompatRadius = materialShapeDrawableState.shadowCompatRadius;
        constantState.shadowCompatRotation = materialShapeDrawableState.shadowCompatRotation;
        constantState.strokeTintList = materialShapeDrawableState.strokeTintList;
        constantState.paintStyle = materialShapeDrawableState.paintStyle;
        if (materialShapeDrawableState.padding != null) {
            constantState.padding = new Rect(materialShapeDrawableState.padding);
        }
        this.drawableState = constantState;
        return this;
    }

    public void onBoundsChange(Rect rect) {
        this.pathDirty = true;
        super.onBoundsChange(rect);
    }

    public boolean onStateChange(int[] iArr) {
        boolean z;
        boolean updateColorsForState = updateColorsForState(iArr);
        boolean updateTintFilter = updateTintFilter();
        if (updateColorsForState || updateTintFilter) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            invalidateSelf();
        }
        return z;
    }

    public void onTextSizeChange() {
        invalidateSelf();
    }

    public void setAlpha(int i) {
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        if (materialShapeDrawableState.alpha != i) {
            materialShapeDrawableState.alpha = i;
            super.invalidateSelf();
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.drawableState.getClass();
        super.invalidateSelf();
    }

    public final void setElevation(float f) {
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        if (materialShapeDrawableState.elevation != f) {
            materialShapeDrawableState.elevation = f;
            updateZ();
        }
    }

    public final void setFillColor(ColorStateList colorStateList) {
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        if (materialShapeDrawableState.fillColor != colorStateList) {
            materialShapeDrawableState.fillColor = colorStateList;
            onStateChange(getState());
        }
    }

    public final void setInterpolation(float f) {
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        if (materialShapeDrawableState.interpolation != f) {
            materialShapeDrawableState.interpolation = f;
            this.pathDirty = true;
            invalidateSelf();
        }
    }

    public final void setPaintStyle(Paint.Style style) {
        this.drawableState.paintStyle = style;
        super.invalidateSelf();
    }

    public final void setShadowColor() {
        this.shadowRenderer.setShadowColor(-12303292);
        this.drawableState.useTintColorForShadow = false;
        super.invalidateSelf();
    }

    public final void setShadowCompatibilityMode() {
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        if (materialShapeDrawableState.shadowCompatMode != 2) {
            materialShapeDrawableState.shadowCompatMode = 2;
            super.invalidateSelf();
        }
    }

    public final void setShapeAppearanceModel(ShapeAppearanceModel shapeAppearanceModel) {
        this.drawableState.shapeAppearanceModel = shapeAppearanceModel;
        invalidateSelf();
    }

    public final void setStrokeColor(ColorStateList colorStateList) {
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        if (materialShapeDrawableState.strokeColor != colorStateList) {
            materialShapeDrawableState.strokeColor = colorStateList;
            onStateChange(getState());
        }
    }

    public final void setTint(int i) {
        setTintList(ColorStateList.valueOf(i));
    }

    public void setTintList(ColorStateList colorStateList) {
        this.drawableState.tintList = colorStateList;
        updateTintFilter();
        super.invalidateSelf();
    }

    public void setTintMode(PorterDuff.Mode mode) {
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        if (materialShapeDrawableState.tintMode != mode) {
            materialShapeDrawableState.tintMode = mode;
            updateTintFilter();
            super.invalidateSelf();
        }
    }

    public final boolean updateColorsForState(int[] iArr) {
        boolean z;
        int color;
        int colorForState;
        int color2;
        int colorForState2;
        if (this.drawableState.fillColor == null || (color2 = this.fillPaint.getColor()) == (colorForState2 = this.drawableState.fillColor.getColorForState(iArr, color2))) {
            z = false;
        } else {
            this.fillPaint.setColor(colorForState2);
            z = true;
        }
        if (this.drawableState.strokeColor == null || (color = this.strokePaint.getColor()) == (colorForState = this.drawableState.strokeColor.getColorForState(iArr, color))) {
            return z;
        }
        this.strokePaint.setColor(colorForState);
        return true;
    }

    public final boolean updateTintFilter() {
        PorterDuffColorFilter porterDuffColorFilter = this.tintFilter;
        PorterDuffColorFilter porterDuffColorFilter2 = this.strokeTintFilter;
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        this.tintFilter = calculateTintFilter(materialShapeDrawableState.tintList, materialShapeDrawableState.tintMode, this.fillPaint, true);
        MaterialShapeDrawableState materialShapeDrawableState2 = this.drawableState;
        this.strokeTintFilter = calculateTintFilter(materialShapeDrawableState2.strokeTintList, materialShapeDrawableState2.tintMode, this.strokePaint, false);
        MaterialShapeDrawableState materialShapeDrawableState3 = this.drawableState;
        if (materialShapeDrawableState3.useTintColorForShadow) {
            this.shadowRenderer.setShadowColor(materialShapeDrawableState3.tintList.getColorForState(getState(), 0));
        }
        if (!Objects.equals(porterDuffColorFilter, this.tintFilter) || !Objects.equals(porterDuffColorFilter2, this.strokeTintFilter)) {
            return true;
        }
        return false;
    }

    public final void updateZ() {
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        float f = materialShapeDrawableState.elevation + materialShapeDrawableState.translationZ;
        materialShapeDrawableState.shadowCompatRadius = (int) Math.ceil((double) (0.75f * f));
        this.drawableState.shadowCompatOffset = (int) Math.ceil((double) (f * 0.25f));
        updateTintFilter();
        super.invalidateSelf();
    }

    public MaterialShapeDrawable(Context context, AttributeSet attributeSet, int i, int i2) {
        this(ShapeAppearanceModel.builder(context, attributeSet, i, i2).build());
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [android.graphics.drawable.Drawable$ConstantState, com.google.android.material.shape.MaterialShapeDrawable$MaterialShapeDrawableState] */
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MaterialShapeDrawable(com.google.android.material.shape.ShapeAppearanceModel r4) {
        /*
            r3 = this;
            com.google.android.material.shape.MaterialShapeDrawable$MaterialShapeDrawableState r0 = new com.google.android.material.shape.MaterialShapeDrawable$MaterialShapeDrawableState
            r0.<init>()
            r1 = 0
            r0.fillColor = r1
            r0.strokeColor = r1
            r0.strokeTintList = r1
            r0.tintList = r1
            android.graphics.PorterDuff$Mode r2 = android.graphics.PorterDuff.Mode.SRC_IN
            r0.tintMode = r2
            r0.padding = r1
            r2 = 1065353216(0x3f800000, float:1.0)
            r0.scale = r2
            r0.interpolation = r2
            r2 = 255(0xff, float:3.57E-43)
            r0.alpha = r2
            r2 = 0
            r0.parentAbsoluteElevation = r2
            r0.elevation = r2
            r0.translationZ = r2
            r2 = 0
            r0.shadowCompatMode = r2
            r0.shadowCompatRadius = r2
            r0.shadowCompatOffset = r2
            r0.shadowCompatRotation = r2
            r0.useTintColorForShadow = r2
            android.graphics.Paint$Style r2 = android.graphics.Paint.Style.FILL_AND_STROKE
            r0.paintStyle = r2
            r0.shapeAppearanceModel = r4
            r0.elevationOverlayProvider = r1
            r3.<init>((com.google.android.material.shape.MaterialShapeDrawable.MaterialShapeDrawableState) r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.shape.MaterialShapeDrawable.<init>(com.google.android.material.shape.ShapeAppearanceModel):void");
    }

    public MaterialShapeDrawable(MaterialShapeDrawableState materialShapeDrawableState) {
        ShapeAppearancePathProvider shapeAppearancePathProvider;
        this.cornerShadowOperation = new ShapePath.ShadowCompatOperation[4];
        this.edgeShadowOperation = new ShapePath.ShadowCompatOperation[4];
        this.containsIncompatibleShadowOp = new BitSet(8);
        this.matrix = new Matrix();
        this.path = new Path();
        this.pathInsetByStroke = new Path();
        this.rectF = new RectF();
        this.insetRectF = new RectF();
        this.transparentRegion = new Region();
        this.scratchRegion = new Region();
        Paint paint = new Paint(1);
        this.fillPaint = paint;
        Paint paint2 = new Paint(1);
        this.strokePaint = paint2;
        this.shadowRenderer = new ShadowRenderer();
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            shapeAppearancePathProvider = ShapeAppearancePathProvider.Lazy.INSTANCE;
        } else {
            shapeAppearancePathProvider = new ShapeAppearancePathProvider();
        }
        this.pathProvider = shapeAppearancePathProvider;
        this.pathBounds = new RectF();
        this.shadowBitmapDrawingEnable = true;
        this.drawableState = materialShapeDrawableState;
        paint2.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.FILL);
        updateTintFilter();
        updateColorsForState(getState());
        this.pathShadowListener = new Object() {
        };
    }
}
