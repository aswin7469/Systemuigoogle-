package com.google.android.material.slider;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.SeekBar;
import androidx.appcompat.app.WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.compose.animation.AndroidFlingSpline$$ExternalSyntheticOutline0;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.math.MathUtils;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.widget.ExploreByTouchHelper;
import com.google.android.material.R$styleable;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.internal.DescendantOffsetUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewOverlayApi18;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.shape.AbsoluteCornerSize;
import com.google.android.material.shape.CornerTreatment;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import com.google.android.material.tooltip.TooltipDrawable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
abstract class BaseSlider extends View {
    public static final /* synthetic */ int $r8$clinit = 0;
    public AccessibilityEventSender accessibilityEventSender;
    public final AccessibilityHelper accessibilityHelper;
    public final AccessibilityManager accessibilityManager;
    public int activeThumbIdx;
    public final Paint activeTicksPaint;
    public final Paint activeTrackPaint;
    public final List changeListeners;
    public final List customThumbDrawablesForValues;
    public final MaterialShapeDrawable defaultThumbDrawable;
    public final int defaultThumbRadius;
    public final int defaultTrackHeight;
    public boolean dirtyConfig;
    public int focusedThumbIdx;
    public boolean forceDrawCompatHalo;
    public ColorStateList haloColor;
    public final Paint haloPaint;
    public int haloRadius;
    public final Paint inactiveTicksPaint;
    public final Paint inactiveTrackPaint;
    public boolean isLongPress;
    public int labelBehavior;
    public final AnonymousClass1 labelMaker;
    public final int labelPadding;
    public final List labels;
    public boolean labelsAreAnimatedIn;
    public ValueAnimator labelsInAnimator;
    public ValueAnimator labelsOutAnimator;
    public MotionEvent lastEvent;
    public final int minTrackSidePadding;
    public final int minWidgetHeight;
    public final int scaledTouchSlop;
    public int separationUnit;
    public float stepSize;
    public boolean thumbIsPressed;
    public final Paint thumbPaint;
    public int thumbRadius;
    public ColorStateList tickColorActive;
    public ColorStateList tickColorInactive;
    public final boolean tickVisible;
    public float[] ticksCoordinates;
    public float touchDownX;
    public final List touchListeners;
    public float touchPosition;
    public ColorStateList trackColorActive;
    public ColorStateList trackColorInactive;
    public int trackHeight;
    public int trackSidePadding;
    public int trackWidth;
    public float valueFrom;
    public float valueTo;
    public ArrayList values;
    public int widgetHeight;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class AccessibilityEventSender implements Runnable {
        public int virtualViewId = -1;

        public AccessibilityEventSender() {
        }

        public final void run() {
            BaseSlider.this.accessibilityHelper.sendEventForVirtualView(this.virtualViewId, 4);
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class AccessibilityHelper extends ExploreByTouchHelper {
        public final BaseSlider slider;
        public final Rect virtualViewBounds = new Rect();

        public AccessibilityHelper(BaseSlider baseSlider) {
            super(baseSlider);
            this.slider = baseSlider;
        }

        public final int getVirtualViewAt(float f, float f2) {
            int i = 0;
            while (true) {
                BaseSlider baseSlider = this.slider;
                if (i >= ((ArrayList) baseSlider.getValues()).size()) {
                    return -1;
                }
                Rect rect = this.virtualViewBounds;
                baseSlider.updateBoundsForVirtualViewId(i, rect);
                if (rect.contains((int) f, (int) f2)) {
                    return i;
                }
                i++;
            }
        }

        public final void getVisibleVirtualViews(List list) {
            for (int i = 0; i < ((ArrayList) this.slider.getValues()).size(); i++) {
                list.add(Integer.valueOf(i));
            }
        }

        public final boolean onPerformActionForVirtualView(int i, int i2, Bundle bundle) {
            BaseSlider baseSlider = this.slider;
            if (!baseSlider.isEnabled()) {
                return false;
            }
            if (i2 == 4096 || i2 == 8192) {
                int i3 = BaseSlider.$r8$clinit;
                float f = baseSlider.stepSize;
                if (f == 0.0f) {
                    f = 1.0f;
                }
                float f2 = (baseSlider.valueTo - baseSlider.valueFrom) / f;
                float f3 = (float) 20;
                if (f2 > f3) {
                    f *= (float) Math.round(f2 / f3);
                }
                if (i2 == 8192) {
                    f = -f;
                }
                if (baseSlider.isRtl()) {
                    f = -f;
                }
                if (!baseSlider.snapThumbToValue(i, MathUtils.clamp(((Float) ((ArrayList) baseSlider.getValues()).get(i)).floatValue() + f, baseSlider.getValueFrom(), baseSlider.getValueTo()))) {
                    return false;
                }
                baseSlider.updateHaloHotspot();
                baseSlider.postInvalidate();
                invalidateVirtualView(i);
                return true;
            }
            if (i2 == 16908349 && bundle != null && bundle.containsKey("android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE")) {
                float f4 = bundle.getFloat("android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE");
                int i4 = BaseSlider.$r8$clinit;
                if (baseSlider.snapThumbToValue(i, f4)) {
                    baseSlider.updateHaloHotspot();
                    baseSlider.postInvalidate();
                    invalidateVirtualView(i);
                    return true;
                }
            }
            return false;
        }

        public final void onPopulateNodeForVirtualView(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            String str;
            accessibilityNodeInfoCompat.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SET_PROGRESS);
            BaseSlider baseSlider = this.slider;
            ArrayList arrayList = (ArrayList) baseSlider.getValues();
            float floatValue = ((Float) arrayList.get(i)).floatValue();
            float valueFrom = baseSlider.getValueFrom();
            float valueTo = baseSlider.getValueTo();
            if (baseSlider.isEnabled()) {
                if (floatValue > valueFrom) {
                    accessibilityNodeInfoCompat.addAction(8192);
                }
                if (floatValue < valueTo) {
                    accessibilityNodeInfoCompat.addAction(4096);
                }
            }
            AccessibilityNodeInfo.RangeInfo obtain = AccessibilityNodeInfo.RangeInfo.obtain(1, valueFrom, valueTo, floatValue);
            AccessibilityNodeInfo.RangeInfo rangeInfo = obtain;
            accessibilityNodeInfoCompat.mInfo.setRangeInfo(obtain);
            accessibilityNodeInfoCompat.setClassName(SeekBar.class.getName());
            StringBuilder sb = new StringBuilder();
            if (baseSlider.getContentDescription() != null) {
                sb.append(baseSlider.getContentDescription());
                sb.append(",");
            }
            if (arrayList.size() > 1) {
                if (i == ((ArrayList) baseSlider.getValues()).size() - 1) {
                    str = baseSlider.getContext().getString(2131953086);
                } else if (i == 0) {
                    str = baseSlider.getContext().getString(2131953087);
                } else {
                    str = "";
                }
                sb.append(str);
                sb.append(baseSlider.formatValue(floatValue));
            }
            accessibilityNodeInfoCompat.setContentDescription(sb.toString());
            Rect rect = this.virtualViewBounds;
            baseSlider.updateBoundsForVirtualViewId(i, rect);
            accessibilityNodeInfoCompat.setBoundsInParent(rect);
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class SliderState extends View.BaseSavedState {
        public static final Parcelable.Creator CREATOR = new Object();
        public boolean hasFocus;
        public float stepSize;
        public float valueFrom;
        public float valueTo;
        public ArrayList values;

        /* renamed from: com.google.android.material.slider.BaseSlider$SliderState$1  reason: invalid class name */
        /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
        public final class AnonymousClass1 implements Parcelable.Creator {
            /* JADX WARNING: type inference failed for: r2v1, types: [android.view.View$BaseSavedState, com.google.android.material.slider.BaseSlider$SliderState, java.lang.Object] */
            public final Object createFromParcel(Parcel parcel) {
                ? baseSavedState = new View.BaseSavedState(parcel);
                baseSavedState.valueFrom = parcel.readFloat();
                baseSavedState.valueTo = parcel.readFloat();
                ArrayList arrayList = new ArrayList();
                baseSavedState.values = arrayList;
                parcel.readList(arrayList, Float.class.getClassLoader());
                baseSavedState.stepSize = parcel.readFloat();
                baseSavedState.hasFocus = parcel.createBooleanArray()[0];
                return baseSavedState;
            }

            public final Object[] newArray(int i) {
                return new SliderState[i];
            }
        }

        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeFloat(this.valueFrom);
            parcel.writeFloat(this.valueTo);
            parcel.writeList(this.values);
            parcel.writeFloat(this.stepSize);
            parcel.writeBooleanArray(new boolean[]{this.hasFocus});
        }
    }

    static {
        Class<BaseSlider> cls = BaseSlider.class;
    }

    public BaseSlider(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public final int calculateTrackCenter() {
        int i = this.widgetHeight / 2;
        int i2 = this.labelBehavior;
        int i3 = 0;
        if (i2 == 1 || i2 == 3) {
            i3 = ((TooltipDrawable) ((ArrayList) this.labels).get(0)).getIntrinsicHeight();
        }
        return i + i3;
    }

    public final ValueAnimator createLabelAnimator(boolean z) {
        float f;
        ValueAnimator valueAnimator;
        long j;
        TimeInterpolator timeInterpolator;
        float f2 = 1.0f;
        if (z) {
            f = 0.0f;
        } else {
            f = 1.0f;
        }
        if (z) {
            valueAnimator = this.labelsOutAnimator;
        } else {
            valueAnimator = this.labelsInAnimator;
        }
        if (valueAnimator != null && valueAnimator.isRunning()) {
            f = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            valueAnimator.cancel();
        }
        if (!z) {
            f2 = 0.0f;
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{f, f2});
        if (z) {
            j = 83;
        } else {
            j = 117;
        }
        ofFloat.setDuration(j);
        if (z) {
            timeInterpolator = AnimationUtils.DECELERATE_INTERPOLATOR;
        } else {
            timeInterpolator = AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR;
        }
        ofFloat.setInterpolator(timeInterpolator);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                for (TooltipDrawable tooltipDrawable : BaseSlider.this.labels) {
                    tooltipDrawable.tooltipPivotY = 1.2f;
                    tooltipDrawable.tooltipScaleX = floatValue;
                    tooltipDrawable.tooltipScaleY = floatValue;
                    tooltipDrawable.labelOpacity = AnimationUtils.lerp(0.0f, 1.0f, 0.19f, 1.0f, floatValue);
                    tooltipDrawable.invalidateSelf();
                }
                BaseSlider baseSlider = BaseSlider.this;
                WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                ViewCompat.Api16Impl.postInvalidateOnAnimation(baseSlider);
            }
        });
        return ofFloat;
    }

    public boolean dispatchHoverEvent(MotionEvent motionEvent) {
        if (this.accessibilityHelper.dispatchHoverEvent(motionEvent) || super.dispatchHoverEvent(motionEvent)) {
            return true;
        }
        return false;
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    public final void drawThumbDrawable(Canvas canvas, int i, int i2, float f, Drawable drawable) {
        canvas.save();
        canvas.translate(((float) (this.trackSidePadding + ((int) (normalizeValue(f) * ((float) i))))) - (((float) drawable.getBounds().width()) / 2.0f), ((float) i2) - (((float) drawable.getBounds().height()) / 2.0f));
        drawable.draw(canvas);
        canvas.restore();
    }

    public final void drawableStateChanged() {
        super.drawableStateChanged();
        this.inactiveTrackPaint.setColor(getColorForState(this.trackColorInactive));
        this.activeTrackPaint.setColor(getColorForState(this.trackColorActive));
        this.inactiveTicksPaint.setColor(getColorForState(this.tickColorInactive));
        this.activeTicksPaint.setColor(getColorForState(this.tickColorActive));
        for (TooltipDrawable tooltipDrawable : this.labels) {
            if (tooltipDrawable.isStateful()) {
                tooltipDrawable.setState(getDrawableState());
            }
        }
        if (this.defaultThumbDrawable.isStateful()) {
            this.defaultThumbDrawable.setState(getDrawableState());
        }
        this.haloPaint.setColor(getColorForState(this.haloColor));
        this.haloPaint.setAlpha(63);
    }

    public final void ensureLabelsRemoved() {
        if (this.labelsAreAnimatedIn) {
            this.labelsAreAnimatedIn = false;
            ValueAnimator createLabelAnimator = createLabelAnimator(false);
            this.labelsOutAnimator = createLabelAnimator;
            this.labelsInAnimator = null;
            createLabelAnimator.addListener(new AnimatorListenerAdapter() {
                public final void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    ViewOverlayApi18 contentViewOverlay = ViewUtils.getContentViewOverlay(BaseSlider.this);
                    for (TooltipDrawable remove : BaseSlider.this.labels) {
                        contentViewOverlay.viewOverlay.remove(remove);
                    }
                }
            });
            this.labelsOutAnimator.start();
        }
    }

    public void forceDrawCompatHalo(boolean z) {
        this.forceDrawCompatHalo = z;
    }

    public final String formatValue(float f) {
        String str;
        if (((float) ((int) f)) == f) {
            str = "%.0f";
        } else {
            str = "%.2f";
        }
        return String.format(str, new Object[]{Float.valueOf(f)});
    }

    public CharSequence getAccessibilityClassName() {
        return SeekBar.class.getName();
    }

    public final int getAccessibilityFocusedVirtualViewId() {
        return this.accessibilityHelper.mAccessibilityFocusedVirtualViewId;
    }

    public final float[] getActiveRange() {
        float floatValue = ((Float) Collections.max(getValues())).floatValue();
        float floatValue2 = ((Float) Collections.min(getValues())).floatValue();
        if (this.values.size() == 1) {
            floatValue2 = this.valueFrom;
        }
        float normalizeValue = normalizeValue(floatValue2);
        float normalizeValue2 = normalizeValue(floatValue);
        if (isRtl()) {
            return new float[]{normalizeValue2, normalizeValue};
        }
        return new float[]{normalizeValue, normalizeValue2};
    }

    public final int getColorForState(ColorStateList colorStateList) {
        return colorStateList.getColorForState(getDrawableState(), colorStateList.getDefaultColor());
    }

    public float getMinSeparation() {
        return 0.0f;
    }

    public float getValueFrom() {
        return this.valueFrom;
    }

    public float getValueTo() {
        return this.valueTo;
    }

    public List getValues() {
        return new ArrayList(this.values);
    }

    public final boolean isInVerticalScrollingContainer() {
        for (ViewParent parent = getParent(); parent instanceof ViewGroup; parent = parent.getParent()) {
            ViewGroup viewGroup = (ViewGroup) parent;
            if ((viewGroup.canScrollVertically(1) || viewGroup.canScrollVertically(-1)) && viewGroup.shouldDelayChildPressedState()) {
                return true;
            }
        }
        return false;
    }

    public final boolean isMultipleOfStepSize(float f) {
        double doubleValue = new BigDecimal(Float.toString(f)).divide(new BigDecimal(Float.toString(this.stepSize)), MathContext.DECIMAL64).doubleValue();
        if (Math.abs(((double) Math.round(doubleValue)) - doubleValue) < 1.0E-4d) {
            return true;
        }
        return false;
    }

    public final boolean isRtl() {
        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
        if (ViewCompat.Api17Impl.getLayoutDirection(this) == 1) {
            return true;
        }
        return false;
    }

    public final void maybeCalculateTicksCoordinates() {
        if (this.stepSize > 0.0f) {
            validateConfigurationIfDirty();
            int min = Math.min((int) (((this.valueTo - this.valueFrom) / this.stepSize) + 1.0f), (this.trackWidth / (this.trackHeight * 2)) + 1);
            float[] fArr = this.ticksCoordinates;
            if (fArr == null || fArr.length != min * 2) {
                this.ticksCoordinates = new float[(min * 2)];
            }
            float f = ((float) this.trackWidth) / ((float) (min - 1));
            for (int i = 0; i < min * 2; i += 2) {
                float[] fArr2 = this.ticksCoordinates;
                fArr2[i] = (((float) (i / 2)) * f) + ((float) this.trackSidePadding);
                fArr2[i + 1] = (float) calculateTrackCenter();
            }
        }
    }

    public final boolean moveFocus(int i) {
        int i2 = this.focusedThumbIdx;
        long j = ((long) i2) + ((long) i);
        long size = (long) (this.values.size() - 1);
        if (j < 0) {
            j = 0;
        } else if (j > size) {
            j = size;
        }
        int i3 = (int) j;
        this.focusedThumbIdx = i3;
        if (i3 == i2) {
            return false;
        }
        if (this.activeThumbIdx != -1) {
            this.activeThumbIdx = i3;
        }
        updateHaloHotspot();
        postInvalidate();
        return true;
    }

    public final void moveFocusInAbsoluteDirection(int i) {
        if (isRtl()) {
            if (i == Integer.MIN_VALUE) {
                i = Integer.MAX_VALUE;
            } else {
                i = -i;
            }
        }
        moveFocus(i);
    }

    public final float normalizeValue(float f) {
        float f2 = this.valueFrom;
        float f3 = (f - f2) / (this.valueTo - f2);
        if (isRtl()) {
            return 1.0f - f3;
        }
        return f3;
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        for (TooltipDrawable tooltipDrawable : this.labels) {
            ViewGroup contentView = ViewUtils.getContentView(this);
            if (contentView == null) {
                tooltipDrawable.getClass();
            } else {
                tooltipDrawable.getClass();
                int[] iArr = new int[2];
                contentView.getLocationOnScreen(iArr);
                tooltipDrawable.locationOnScreenX = iArr[0];
                contentView.getWindowVisibleDisplayFrame(tooltipDrawable.displayFrame);
                contentView.addOnLayoutChangeListener(tooltipDrawable.attachedViewLayoutChangeListener);
            }
        }
    }

    public final void onDetachedFromWindow() {
        AccessibilityEventSender accessibilityEventSender2 = this.accessibilityEventSender;
        if (accessibilityEventSender2 != null) {
            removeCallbacks(accessibilityEventSender2);
        }
        this.labelsAreAnimatedIn = false;
        for (TooltipDrawable tooltipDrawable : this.labels) {
            ViewOverlayApi18 contentViewOverlay = ViewUtils.getContentViewOverlay(this);
            if (contentViewOverlay != null) {
                contentViewOverlay.viewOverlay.remove(tooltipDrawable);
                ViewGroup contentView = ViewUtils.getContentView(this);
                if (contentView == null) {
                    tooltipDrawable.getClass();
                } else {
                    contentView.removeOnLayoutChangeListener(tooltipDrawable.attachedViewLayoutChangeListener);
                }
            }
        }
        super.onDetachedFromWindow();
    }

    public final void onDraw(Canvas canvas) {
        if (this.dirtyConfig) {
            validateConfigurationIfDirty();
            maybeCalculateTicksCoordinates();
        }
        super.onDraw(canvas);
        int calculateTrackCenter = calculateTrackCenter();
        int i = this.trackWidth;
        float[] activeRange = getActiveRange();
        int i2 = this.trackSidePadding;
        float f = (float) i;
        float f2 = (activeRange[1] * f) + ((float) i2);
        float f3 = (float) (i2 + i);
        if (f2 < f3) {
            float f4 = (float) calculateTrackCenter;
            canvas.drawLine(f2, f4, f3, f4, this.inactiveTrackPaint);
        }
        float f5 = (float) this.trackSidePadding;
        float f6 = (activeRange[0] * f) + f5;
        if (f6 > f5) {
            float f7 = (float) calculateTrackCenter;
            canvas.drawLine(f5, f7, f6, f7, this.inactiveTrackPaint);
        }
        if (((Float) Collections.max(getValues())).floatValue() > this.valueFrom) {
            int i3 = this.trackWidth;
            float[] activeRange2 = getActiveRange();
            float f8 = (float) this.trackSidePadding;
            float f9 = (float) i3;
            float f10 = (float) calculateTrackCenter;
            canvas.drawLine((activeRange2[0] * f9) + f8, f10, (activeRange2[1] * f9) + f8, f10, this.activeTrackPaint);
        }
        if (this.tickVisible && this.stepSize > 0.0f) {
            float[] activeRange3 = getActiveRange();
            int round = Math.round(activeRange3[0] * ((float) ((this.ticksCoordinates.length / 2) - 1)));
            int round2 = Math.round(activeRange3[1] * ((float) ((this.ticksCoordinates.length / 2) - 1)));
            int i4 = round * 2;
            canvas.drawPoints(this.ticksCoordinates, 0, i4, this.inactiveTicksPaint);
            int i5 = round2 * 2;
            canvas.drawPoints(this.ticksCoordinates, i4, i5 - i4, this.activeTicksPaint);
            float[] fArr = this.ticksCoordinates;
            canvas.drawPoints(fArr, i5, fArr.length - i5, this.inactiveTicksPaint);
        }
        if ((this.thumbIsPressed || isFocused() || this.labelBehavior == 3) && isEnabled()) {
            int i6 = this.trackWidth;
            if (shouldDrawCompatHalo()) {
                canvas.drawCircle((float) ((int) ((normalizeValue(((Float) this.values.get(this.focusedThumbIdx)).floatValue()) * ((float) i6)) + ((float) this.trackSidePadding))), (float) calculateTrackCenter, (float) this.haloRadius, this.haloPaint);
            }
            if (this.activeThumbIdx == -1 && this.labelBehavior != 3) {
                ensureLabelsRemoved();
            } else if (this.labelBehavior != 2) {
                if (!this.labelsAreAnimatedIn) {
                    this.labelsAreAnimatedIn = true;
                    ValueAnimator createLabelAnimator = createLabelAnimator(true);
                    this.labelsInAnimator = createLabelAnimator;
                    this.labelsOutAnimator = null;
                    createLabelAnimator.start();
                }
                Iterator it = this.labels.iterator();
                for (int i7 = 0; i7 < this.values.size() && it.hasNext(); i7++) {
                    if (i7 != this.focusedThumbIdx) {
                        setValueForLabel((TooltipDrawable) it.next(), ((Float) this.values.get(i7)).floatValue());
                    }
                }
                if (it.hasNext()) {
                    setValueForLabel((TooltipDrawable) it.next(), ((Float) this.values.get(this.focusedThumbIdx)).floatValue());
                } else {
                    throw new IllegalStateException(String.format("Not enough labels(%d) to display all the values(%d)", new Object[]{Integer.valueOf(((ArrayList) this.labels).size()), Integer.valueOf(this.values.size())}));
                }
            }
        } else {
            ensureLabelsRemoved();
        }
        int i8 = this.trackWidth;
        for (int i9 = 0; i9 < this.values.size(); i9++) {
            float floatValue = ((Float) this.values.get(i9)).floatValue();
            if (i9 < this.customThumbDrawablesForValues.size()) {
                drawThumbDrawable(canvas, i8, calculateTrackCenter, floatValue, (Drawable) this.customThumbDrawablesForValues.get(i9));
            } else {
                if (!isEnabled()) {
                    canvas.drawCircle((normalizeValue(floatValue) * ((float) i8)) + ((float) this.trackSidePadding), (float) calculateTrackCenter, (float) this.thumbRadius, this.thumbPaint);
                }
                drawThumbDrawable(canvas, i8, calculateTrackCenter, floatValue, this.defaultThumbDrawable);
            }
        }
    }

    public final void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
        if (!z) {
            this.activeThumbIdx = -1;
            this.accessibilityHelper.clearKeyboardFocusForVirtualView(this.focusedThumbIdx);
            return;
        }
        if (i == 1) {
            moveFocus(Integer.MAX_VALUE);
        } else if (i == 2) {
            moveFocus(Integer.MIN_VALUE);
        } else if (i == 17) {
            moveFocusInAbsoluteDirection(Integer.MAX_VALUE);
        } else if (i == 66) {
            moveFocusInAbsoluteDirection(Integer.MIN_VALUE);
        }
        this.accessibilityHelper.requestKeyboardFocusForVirtualView(this.focusedThumbIdx);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: java.lang.Float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: java.lang.Boolean} */
    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r3v15 */
    /* JADX WARNING: type inference failed for: r3v16 */
    /* JADX WARNING: type inference failed for: r3v17 */
    /* JADX WARNING: type inference failed for: r3v18 */
    /* JADX WARNING: type inference failed for: r3v19 */
    /* JADX WARNING: type inference failed for: r3v20 */
    /* JADX WARNING: type inference failed for: r3v21 */
    /* JADX WARNING: type inference failed for: r3v22 */
    /* JADX WARNING: type inference failed for: r3v23 */
    /* JADX WARNING: type inference failed for: r3v24 */
    /* JADX WARNING: type inference failed for: r3v25 */
    /* JADX WARNING: type inference failed for: r3v26 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onKeyDown(int r14, android.view.KeyEvent r15) {
        /*
            r13 = this;
            boolean r0 = r13.isEnabled()
            if (r0 != 0) goto L_0x000b
            boolean r13 = super.onKeyDown(r14, r15)
            return r13
        L_0x000b:
            java.util.ArrayList r0 = r13.values
            int r0 = r0.size()
            r1 = 0
            r2 = 1
            if (r0 != r2) goto L_0x0017
            r13.activeThumbIdx = r1
        L_0x0017:
            int r0 = r13.activeThumbIdx
            r3 = 0
            r4 = 70
            r5 = 69
            r6 = 81
            r7 = 66
            r8 = 61
            r9 = -1
            if (r0 != r9) goto L_0x0083
            if (r14 == r8) goto L_0x0057
            if (r14 == r7) goto L_0x004d
            if (r14 == r6) goto L_0x0047
            if (r14 == r5) goto L_0x0041
            if (r14 == r4) goto L_0x0047
            switch(r14) {
                case 21: goto L_0x003b;
                case 22: goto L_0x0035;
                case 23: goto L_0x004d;
                default: goto L_0x0034;
            }
        L_0x0034:
            goto L_0x0077
        L_0x0035:
            r13.moveFocusInAbsoluteDirection(r2)
            java.lang.Boolean r3 = java.lang.Boolean.TRUE
            goto L_0x0077
        L_0x003b:
            r13.moveFocusInAbsoluteDirection(r9)
            java.lang.Boolean r3 = java.lang.Boolean.TRUE
            goto L_0x0077
        L_0x0041:
            r13.moveFocus(r9)
            java.lang.Boolean r3 = java.lang.Boolean.TRUE
            goto L_0x0077
        L_0x0047:
            r13.moveFocus(r2)
            java.lang.Boolean r3 = java.lang.Boolean.TRUE
            goto L_0x0077
        L_0x004d:
            int r0 = r13.focusedThumbIdx
            r13.activeThumbIdx = r0
            r13.postInvalidate()
            java.lang.Boolean r3 = java.lang.Boolean.TRUE
            goto L_0x0077
        L_0x0057:
            boolean r0 = r15.hasNoModifiers()
            if (r0 == 0) goto L_0x0066
            boolean r0 = r13.moveFocus(r2)
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r0)
            goto L_0x0077
        L_0x0066:
            boolean r0 = r15.isShiftPressed()
            if (r0 == 0) goto L_0x0075
            boolean r0 = r13.moveFocus(r9)
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r0)
            goto L_0x0077
        L_0x0075:
            java.lang.Boolean r3 = java.lang.Boolean.FALSE
        L_0x0077:
            if (r3 == 0) goto L_0x007e
            boolean r13 = r3.booleanValue()
            goto L_0x0082
        L_0x007e:
            boolean r13 = super.onKeyDown(r14, r15)
        L_0x0082:
            return r13
        L_0x0083:
            boolean r0 = r13.isLongPress
            boolean r10 = r15.isLongPress()
            r0 = r0 | r10
            r13.isLongPress = r0
            r10 = 1065353216(0x3f800000, float:1.0)
            r11 = 0
            if (r0 == 0) goto L_0x00af
            float r0 = r13.stepSize
            int r11 = (r0 > r11 ? 1 : (r0 == r11 ? 0 : -1))
            if (r11 != 0) goto L_0x0098
            goto L_0x0099
        L_0x0098:
            r10 = r0
        L_0x0099:
            float r0 = r13.valueTo
            float r11 = r13.valueFrom
            float r0 = r0 - r11
            float r0 = r0 / r10
            r11 = 20
            float r11 = (float) r11
            int r12 = (r0 > r11 ? 1 : (r0 == r11 ? 0 : -1))
            if (r12 > 0) goto L_0x00a7
            goto L_0x00b7
        L_0x00a7:
            float r0 = r0 / r11
            int r0 = java.lang.Math.round(r0)
            float r0 = (float) r0
            float r10 = r10 * r0
            goto L_0x00b7
        L_0x00af:
            float r0 = r13.stepSize
            int r11 = (r0 > r11 ? 1 : (r0 == r11 ? 0 : -1))
            if (r11 != 0) goto L_0x00b6
            goto L_0x00b7
        L_0x00b6:
            r10 = r0
        L_0x00b7:
            r0 = 21
            if (r14 == r0) goto L_0x00dd
            r0 = 22
            if (r14 == r0) goto L_0x00d1
            if (r14 == r5) goto L_0x00cb
            if (r14 == r4) goto L_0x00c6
            if (r14 == r6) goto L_0x00c6
            goto L_0x00e9
        L_0x00c6:
            java.lang.Float r3 = java.lang.Float.valueOf(r10)
            goto L_0x00e9
        L_0x00cb:
            float r0 = -r10
            java.lang.Float r3 = java.lang.Float.valueOf(r0)
            goto L_0x00e9
        L_0x00d1:
            boolean r0 = r13.isRtl()
            if (r0 == 0) goto L_0x00d8
            float r10 = -r10
        L_0x00d8:
            java.lang.Float r3 = java.lang.Float.valueOf(r10)
            goto L_0x00e9
        L_0x00dd:
            boolean r0 = r13.isRtl()
            if (r0 == 0) goto L_0x00e4
            goto L_0x00e5
        L_0x00e4:
            float r10 = -r10
        L_0x00e5:
            java.lang.Float r3 = java.lang.Float.valueOf(r10)
        L_0x00e9:
            if (r3 == 0) goto L_0x010d
            java.util.ArrayList r14 = r13.values
            int r15 = r13.activeThumbIdx
            java.lang.Object r14 = r14.get(r15)
            java.lang.Float r14 = (java.lang.Float) r14
            float r14 = r14.floatValue()
            float r15 = r3.floatValue()
            float r15 = r15 + r14
            int r14 = r13.activeThumbIdx
            boolean r14 = r13.snapThumbToValue(r14, r15)
            if (r14 == 0) goto L_0x010c
            r13.updateHaloHotspot()
            r13.postInvalidate()
        L_0x010c:
            return r2
        L_0x010d:
            r0 = 23
            if (r14 == r0) goto L_0x0131
            if (r14 == r8) goto L_0x011a
            if (r14 == r7) goto L_0x0131
            boolean r13 = super.onKeyDown(r14, r15)
            return r13
        L_0x011a:
            boolean r14 = r15.hasNoModifiers()
            if (r14 == 0) goto L_0x0125
            boolean r13 = r13.moveFocus(r2)
            return r13
        L_0x0125:
            boolean r14 = r15.isShiftPressed()
            if (r14 == 0) goto L_0x0130
            boolean r13 = r13.moveFocus(r9)
            return r13
        L_0x0130:
            return r1
        L_0x0131:
            r13.activeThumbIdx = r9
            r13.postInvalidate()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.slider.BaseSlider.onKeyDown(int, android.view.KeyEvent):boolean");
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        this.isLongPress = false;
        return super.onKeyUp(i, keyEvent);
    }

    public final void onMeasure(int i, int i2) {
        int i3 = this.widgetHeight;
        int i4 = this.labelBehavior;
        int i5 = 0;
        if (i4 == 1 || i4 == 3) {
            i5 = ((TooltipDrawable) ((ArrayList) this.labels).get(0)).getIntrinsicHeight();
        }
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(i3 + i5, 1073741824));
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SliderState sliderState = (SliderState) parcelable;
        super.onRestoreInstanceState(sliderState.getSuperState());
        this.valueFrom = sliderState.valueFrom;
        this.valueTo = sliderState.valueTo;
        setValuesInternal(sliderState.values);
        this.stepSize = sliderState.stepSize;
        if (sliderState.hasFocus) {
            requestFocus();
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.view.View$BaseSavedState, android.os.Parcelable, com.google.android.material.slider.BaseSlider$SliderState] */
    public Parcelable onSaveInstanceState() {
        ? baseSavedState = new View.BaseSavedState(super.onSaveInstanceState());
        baseSavedState.valueFrom = this.valueFrom;
        baseSavedState.valueTo = this.valueTo;
        baseSavedState.values = new ArrayList(this.values);
        baseSavedState.stepSize = this.stepSize;
        baseSavedState.hasFocus = hasFocus();
        return baseSavedState;
    }

    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        this.trackWidth = Math.max(i - (this.trackSidePadding * 2), 0);
        maybeCalculateTicksCoordinates();
        updateHaloHotspot();
    }

    public final void onStartTrackingTouch() {
        Iterator it = this.touchListeners.iterator();
        if (it.hasNext()) {
            WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(it.next());
            throw null;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled()) {
            return false;
        }
        float x = motionEvent.getX();
        float f = (x - ((float) this.trackSidePadding)) / ((float) this.trackWidth);
        this.touchPosition = f;
        float max = Math.max(0.0f, f);
        this.touchPosition = max;
        this.touchPosition = Math.min(1.0f, max);
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.touchDownX = x;
            if (!isInVerticalScrollingContainer()) {
                getParent().requestDisallowInterceptTouchEvent(true);
                if (pickActiveThumb()) {
                    requestFocus();
                    this.thumbIsPressed = true;
                    snapTouchPosition();
                    updateHaloHotspot();
                    invalidate();
                    onStartTrackingTouch();
                }
            }
        } else if (actionMasked == 1) {
            this.thumbIsPressed = false;
            MotionEvent motionEvent2 = this.lastEvent;
            if (motionEvent2 != null && motionEvent2.getActionMasked() == 0 && Math.abs(this.lastEvent.getX() - motionEvent.getX()) <= ((float) this.scaledTouchSlop) && Math.abs(this.lastEvent.getY() - motionEvent.getY()) <= ((float) this.scaledTouchSlop) && pickActiveThumb()) {
                onStartTrackingTouch();
            }
            if (this.activeThumbIdx != -1) {
                snapTouchPosition();
                this.activeThumbIdx = -1;
                Iterator it = this.touchListeners.iterator();
                if (it.hasNext()) {
                    WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(it.next());
                    throw null;
                }
            }
            invalidate();
        } else if (actionMasked == 2) {
            if (!this.thumbIsPressed) {
                if (isInVerticalScrollingContainer() && Math.abs(x - this.touchDownX) < ((float) this.scaledTouchSlop)) {
                    return false;
                }
                getParent().requestDisallowInterceptTouchEvent(true);
                onStartTrackingTouch();
            }
            if (pickActiveThumb()) {
                this.thumbIsPressed = true;
                snapTouchPosition();
                updateHaloHotspot();
                invalidate();
            }
        }
        setPressed(this.thumbIsPressed);
        this.lastEvent = MotionEvent.obtain(motionEvent);
        return true;
    }

    public final void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (i != 0) {
            ViewOverlayApi18 contentViewOverlay = ViewUtils.getContentViewOverlay(this);
            for (TooltipDrawable remove : this.labels) {
                contentViewOverlay.viewOverlay.remove(remove);
            }
        }
    }

    public boolean pickActiveThumb() {
        boolean z;
        if (this.activeThumbIdx != -1) {
            return true;
        }
        float f = this.touchPosition;
        if (isRtl()) {
            f = 1.0f - f;
        }
        float f2 = this.valueTo;
        float f3 = this.valueFrom;
        float m = AndroidFlingSpline$$ExternalSyntheticOutline0.m(f2, f3, f, f3);
        float normalizeValue = (normalizeValue(m) * ((float) this.trackWidth)) + ((float) this.trackSidePadding);
        this.activeThumbIdx = 0;
        float abs = Math.abs(((Float) this.values.get(0)).floatValue() - m);
        for (int i = 1; i < this.values.size(); i++) {
            float abs2 = Math.abs(((Float) this.values.get(i)).floatValue() - m);
            float normalizeValue2 = (normalizeValue(((Float) this.values.get(i)).floatValue()) * ((float) this.trackWidth)) + ((float) this.trackSidePadding);
            if (Float.compare(abs2, abs) > 1) {
                break;
            }
            if (!isRtl() ? normalizeValue2 - normalizeValue >= 0.0f : normalizeValue2 - normalizeValue <= 0.0f) {
                z = false;
            } else {
                z = true;
            }
            if (Float.compare(abs2, abs) < 0) {
                this.activeThumbIdx = i;
            } else {
                if (Float.compare(abs2, abs) != 0) {
                    continue;
                } else if (Math.abs(normalizeValue2 - normalizeValue) < ((float) this.scaledTouchSlop)) {
                    this.activeThumbIdx = -1;
                    return false;
                } else if (z) {
                    this.activeThumbIdx = i;
                }
            }
            abs = abs2;
        }
        if (this.activeThumbIdx != -1) {
            return true;
        }
        return false;
    }

    public void setEnabled(boolean z) {
        int i;
        super.setEnabled(z);
        if (z) {
            i = 0;
        } else {
            i = 2;
        }
        setLayerType(i, (Paint) null);
    }

    public void setHaloRadius(int i) {
        if (i != this.haloRadius) {
            this.haloRadius = i;
            Drawable background = getBackground();
            if (shouldDrawCompatHalo() || !(background instanceof RippleDrawable)) {
                postInvalidate();
            } else {
                ((RippleDrawable) background).setRadius(this.haloRadius);
            }
        }
    }

    public void setHaloTintList(ColorStateList colorStateList) {
        if (!colorStateList.equals(this.haloColor)) {
            this.haloColor = colorStateList;
            Drawable background = getBackground();
            if (shouldDrawCompatHalo() || !(background instanceof RippleDrawable)) {
                this.haloPaint.setColor(getColorForState(colorStateList));
                this.haloPaint.setAlpha(63);
                invalidate();
                return;
            }
            ((RippleDrawable) background).setColor(colorStateList);
        }
    }

    public void setLabelBehavior(int i) {
        if (this.labelBehavior != i) {
            this.labelBehavior = i;
            requestLayout();
        }
    }

    public void setThumbElevation(float f) {
        this.defaultThumbDrawable.setElevation(f);
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [com.google.android.material.shape.EdgeTreatment, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r1v0, types: [com.google.android.material.shape.EdgeTreatment, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.android.material.shape.EdgeTreatment, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r3v0, types: [com.google.android.material.shape.EdgeTreatment, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r4v2, types: [java.lang.Object, com.google.android.material.shape.ShapeAppearanceModel] */
    public void setThumbRadius(int i) {
        if (i != this.thumbRadius) {
            this.thumbRadius = i;
            MaterialShapeDrawable materialShapeDrawable = this.defaultThumbDrawable;
            ? obj = new Object();
            ? obj2 = new Object();
            ? obj3 = new Object();
            ? obj4 = new Object();
            float f = (float) this.thumbRadius;
            CornerTreatment createCornerTreatment = MaterialShapeUtils.createCornerTreatment(0);
            ShapeAppearanceModel.Builder.compatCornerTreatmentSize(createCornerTreatment);
            ShapeAppearanceModel.Builder.compatCornerTreatmentSize(createCornerTreatment);
            ShapeAppearanceModel.Builder.compatCornerTreatmentSize(createCornerTreatment);
            ShapeAppearanceModel.Builder.compatCornerTreatmentSize(createCornerTreatment);
            AbsoluteCornerSize absoluteCornerSize = new AbsoluteCornerSize(f);
            AbsoluteCornerSize absoluteCornerSize2 = new AbsoluteCornerSize(f);
            AbsoluteCornerSize absoluteCornerSize3 = new AbsoluteCornerSize(f);
            AbsoluteCornerSize absoluteCornerSize4 = new AbsoluteCornerSize(f);
            ? obj5 = new Object();
            obj5.topLeftCorner = createCornerTreatment;
            obj5.topRightCorner = createCornerTreatment;
            obj5.bottomRightCorner = createCornerTreatment;
            obj5.bottomLeftCorner = createCornerTreatment;
            obj5.topLeftCornerSize = absoluteCornerSize;
            obj5.topRightCornerSize = absoluteCornerSize2;
            obj5.bottomRightCornerSize = absoluteCornerSize3;
            obj5.bottomLeftCornerSize = absoluteCornerSize4;
            obj5.topEdge = obj;
            obj5.rightEdge = obj2;
            obj5.bottomEdge = obj3;
            obj5.leftEdge = obj4;
            materialShapeDrawable.setShapeAppearanceModel(obj5);
            MaterialShapeDrawable materialShapeDrawable2 = this.defaultThumbDrawable;
            int i2 = this.thumbRadius * 2;
            materialShapeDrawable2.setBounds(0, 0, i2, i2);
            for (Drawable drawable : this.customThumbDrawablesForValues) {
                int i3 = this.thumbRadius * 2;
                int intrinsicWidth = drawable.getIntrinsicWidth();
                int intrinsicHeight = drawable.getIntrinsicHeight();
                if (intrinsicWidth == -1 && intrinsicHeight == -1) {
                    drawable.setBounds(0, 0, i3, i3);
                } else {
                    float max = ((float) i3) / ((float) Math.max(intrinsicWidth, intrinsicHeight));
                    drawable.setBounds(0, 0, (int) (((float) intrinsicWidth) * max), (int) (((float) intrinsicHeight) * max));
                }
            }
            updateWidgetLayout();
        }
    }

    public void setThumbStrokeColor(ColorStateList colorStateList) {
        this.defaultThumbDrawable.setStrokeColor(colorStateList);
        postInvalidate();
    }

    public void setThumbStrokeWidth(float f) {
        MaterialShapeDrawable materialShapeDrawable = this.defaultThumbDrawable;
        materialShapeDrawable.drawableState.strokeWidth = f;
        materialShapeDrawable.invalidateSelf();
        postInvalidate();
    }

    public void setTickActiveTintList(ColorStateList colorStateList) {
        if (!colorStateList.equals(this.tickColorActive)) {
            this.tickColorActive = colorStateList;
            this.activeTicksPaint.setColor(getColorForState(colorStateList));
            invalidate();
        }
    }

    public void setTickInactiveTintList(ColorStateList colorStateList) {
        if (!colorStateList.equals(this.tickColorInactive)) {
            this.tickColorInactive = colorStateList;
            this.inactiveTicksPaint.setColor(getColorForState(colorStateList));
            invalidate();
        }
    }

    public void setTrackActiveTintList(ColorStateList colorStateList) {
        if (!colorStateList.equals(this.trackColorActive)) {
            this.trackColorActive = colorStateList;
            this.activeTrackPaint.setColor(getColorForState(colorStateList));
            invalidate();
        }
    }

    public void setTrackHeight(int i) {
        if (this.trackHeight != i) {
            this.trackHeight = i;
            this.inactiveTrackPaint.setStrokeWidth((float) i);
            this.activeTrackPaint.setStrokeWidth((float) this.trackHeight);
            this.inactiveTicksPaint.setStrokeWidth(((float) this.trackHeight) / 2.0f);
            this.activeTicksPaint.setStrokeWidth(((float) this.trackHeight) / 2.0f);
            updateWidgetLayout();
        }
    }

    public void setTrackInactiveTintList(ColorStateList colorStateList) {
        if (!colorStateList.equals(this.trackColorInactive)) {
            this.trackColorInactive = colorStateList;
            this.inactiveTrackPaint.setColor(getColorForState(colorStateList));
            invalidate();
        }
    }

    public final void setValueForLabel(TooltipDrawable tooltipDrawable, float f) {
        String formatValue = formatValue(f);
        if (!TextUtils.equals(tooltipDrawable.text, formatValue)) {
            tooltipDrawable.text = formatValue;
            tooltipDrawable.textDrawableHelper.textWidthDirty = true;
            tooltipDrawable.invalidateSelf();
        }
        int normalizeValue = (this.trackSidePadding + ((int) (normalizeValue(f) * ((float) this.trackWidth)))) - (tooltipDrawable.getIntrinsicWidth() / 2);
        int calculateTrackCenter = calculateTrackCenter() - (this.labelPadding + this.thumbRadius);
        tooltipDrawable.setBounds(normalizeValue, calculateTrackCenter - tooltipDrawable.getIntrinsicHeight(), tooltipDrawable.getIntrinsicWidth() + normalizeValue, calculateTrackCenter);
        Rect rect = new Rect(tooltipDrawable.getBounds());
        DescendantOffsetUtils.offsetDescendantRect(ViewUtils.getContentView(this), this, rect);
        tooltipDrawable.setBounds(rect);
        ViewUtils.getContentViewOverlay(this).viewOverlay.add(tooltipDrawable);
    }

    public void setValues(Float... fArr) {
        ArrayList arrayList = new ArrayList();
        Collections.addAll(arrayList, fArr);
        setValuesInternal(arrayList);
    }

    public final void setValuesInternal(ArrayList arrayList) {
        int i;
        int i2;
        int i3;
        ViewGroup contentView;
        int resourceId;
        ViewOverlayApi18 contentViewOverlay;
        if (!arrayList.isEmpty()) {
            Collections.sort(arrayList);
            if (this.values.size() != arrayList.size() || !this.values.equals(arrayList)) {
                this.values = arrayList;
                int i4 = 1;
                this.dirtyConfig = true;
                this.focusedThumbIdx = 0;
                updateHaloHotspot();
                if (((ArrayList) this.labels).size() > this.values.size()) {
                    List<TooltipDrawable> subList = this.labels.subList(this.values.size(), ((ArrayList) this.labels).size());
                    for (TooltipDrawable tooltipDrawable : subList) {
                        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                        if (ViewCompat.Api19Impl.isAttachedToWindow(this) && (contentViewOverlay = ViewUtils.getContentViewOverlay(this)) != null) {
                            contentViewOverlay.viewOverlay.remove(tooltipDrawable);
                            ViewGroup contentView2 = ViewUtils.getContentView(this);
                            if (contentView2 == null) {
                                tooltipDrawable.getClass();
                            } else {
                                contentView2.removeOnLayoutChangeListener(tooltipDrawable.attachedViewLayoutChangeListener);
                            }
                        }
                    }
                    subList.clear();
                }
                while (true) {
                    TextAppearance textAppearance = null;
                    if (((ArrayList) this.labels).size() >= this.values.size()) {
                        break;
                    }
                    AnonymousClass1 r1 = this.labelMaker;
                    BaseSlider baseSlider = BaseSlider.this;
                    TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(baseSlider.getContext(), r14, R$styleable.Slider, r1.val$defStyleAttr, 2132018853, new int[0]);
                    Context context = baseSlider.getContext();
                    int resourceId2 = obtainStyledAttributes.getResourceId(8, 2132018887);
                    TooltipDrawable tooltipDrawable2 = new TooltipDrawable(context, resourceId2);
                    TypedArray obtainStyledAttributes2 = ThemeEnforcement.obtainStyledAttributes(tooltipDrawable2.context, (AttributeSet) null, R$styleable.Tooltip, 0, resourceId2, new int[0]);
                    tooltipDrawable2.arrowSize = tooltipDrawable2.context.getResources().getDimensionPixelSize(2131166909);
                    ShapeAppearanceModel.Builder builder = tooltipDrawable2.drawableState.shapeAppearanceModel.toBuilder();
                    builder.bottomEdge = tooltipDrawable2.createMarkerEdge();
                    tooltipDrawable2.setShapeAppearanceModel(builder.build());
                    CharSequence text = obtainStyledAttributes2.getText(6);
                    if (!TextUtils.equals(tooltipDrawable2.text, text)) {
                        tooltipDrawable2.text = text;
                        tooltipDrawable2.textDrawableHelper.textWidthDirty = true;
                        tooltipDrawable2.invalidateSelf();
                    }
                    Context context2 = tooltipDrawable2.context;
                    if (obtainStyledAttributes2.hasValue(0) && (resourceId = obtainStyledAttributes2.getResourceId(0, 0)) != 0) {
                        textAppearance = new TextAppearance(context2, resourceId);
                    }
                    if (textAppearance != null && obtainStyledAttributes2.hasValue(1)) {
                        textAppearance.textColor = MaterialResources.getColorStateList(tooltipDrawable2.context, obtainStyledAttributes2, 1);
                    }
                    tooltipDrawable2.textDrawableHelper.setTextAppearance(textAppearance, tooltipDrawable2.context);
                    Context context3 = tooltipDrawable2.context;
                    TypedValue resolveTypedValueOrThrow = MaterialAttributes.resolveTypedValueOrThrow(context3, TooltipDrawable.class.getCanonicalName(), 2130968863);
                    int i5 = resolveTypedValueOrThrow.resourceId;
                    if (i5 != 0) {
                        Object obj = ContextCompat.sLock;
                        i = context3.getColor(i5);
                    } else {
                        i = resolveTypedValueOrThrow.data;
                    }
                    Context context4 = tooltipDrawable2.context;
                    TypedValue resolveTypedValueOrThrow2 = MaterialAttributes.resolveTypedValueOrThrow(context4, TooltipDrawable.class.getCanonicalName(), 16842801);
                    int i6 = resolveTypedValueOrThrow2.resourceId;
                    if (i6 != 0) {
                        Object obj2 = ContextCompat.sLock;
                        i2 = context4.getColor(i6);
                    } else {
                        i2 = resolveTypedValueOrThrow2.data;
                    }
                    tooltipDrawable2.setFillColor(ColorStateList.valueOf(obtainStyledAttributes2.getColor(7, ColorUtils.compositeColors(ColorUtils.setAlphaComponent(i, 153), ColorUtils.setAlphaComponent(i2, 229)))));
                    Context context5 = tooltipDrawable2.context;
                    TypedValue resolveTypedValueOrThrow3 = MaterialAttributes.resolveTypedValueOrThrow(context5, TooltipDrawable.class.getCanonicalName(), 2130968887);
                    int i7 = resolveTypedValueOrThrow3.resourceId;
                    if (i7 != 0) {
                        Object obj3 = ContextCompat.sLock;
                        i3 = context5.getColor(i7);
                    } else {
                        i3 = resolveTypedValueOrThrow3.data;
                    }
                    tooltipDrawable2.setStrokeColor(ColorStateList.valueOf(i3));
                    tooltipDrawable2.padding = obtainStyledAttributes2.getDimensionPixelSize(2, 0);
                    tooltipDrawable2.minWidth = obtainStyledAttributes2.getDimensionPixelSize(4, 0);
                    tooltipDrawable2.minHeight = obtainStyledAttributes2.getDimensionPixelSize(5, 0);
                    tooltipDrawable2.layoutMargin = obtainStyledAttributes2.getDimensionPixelSize(3, 0);
                    obtainStyledAttributes2.recycle();
                    obtainStyledAttributes.recycle();
                    this.labels.add(tooltipDrawable2);
                    WeakHashMap weakHashMap2 = ViewCompat.sViewPropertyAnimatorMap;
                    if (ViewCompat.Api19Impl.isAttachedToWindow(this) && (contentView = ViewUtils.getContentView(this)) != null) {
                        int[] iArr = new int[2];
                        contentView.getLocationOnScreen(iArr);
                        tooltipDrawable2.locationOnScreenX = iArr[0];
                        contentView.getWindowVisibleDisplayFrame(tooltipDrawable2.displayFrame);
                        contentView.addOnLayoutChangeListener(tooltipDrawable2.attachedViewLayoutChangeListener);
                    }
                }
                if (((ArrayList) this.labels).size() == 1) {
                    i4 = 0;
                }
                for (TooltipDrawable tooltipDrawable3 : this.labels) {
                    tooltipDrawable3.drawableState.strokeWidth = (float) i4;
                    tooltipDrawable3.invalidateSelf();
                }
                for (Object m : this.changeListeners) {
                    WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(m);
                    Iterator it = this.values.iterator();
                    if (it.hasNext()) {
                        ((Float) it.next()).getClass();
                        throw null;
                    }
                }
                postInvalidate();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("At least one value must be set");
    }

    public final boolean shouldDrawCompatHalo() {
        if (this.forceDrawCompatHalo || !(getBackground() instanceof RippleDrawable)) {
            return true;
        }
        return false;
    }

    public final boolean snapThumbToValue(int i, float f) {
        float f2;
        float f3;
        this.focusedThumbIdx = i;
        if (((double) Math.abs(f - ((Float) this.values.get(i)).floatValue())) < 1.0E-4d) {
            return false;
        }
        float minSeparation = getMinSeparation();
        if (this.separationUnit == 0) {
            if (minSeparation == 0.0f) {
                minSeparation = 0.0f;
            } else {
                float f4 = (minSeparation - ((float) this.trackSidePadding)) / ((float) this.trackWidth);
                float f5 = this.valueFrom;
                minSeparation = AndroidFlingSpline$$ExternalSyntheticOutline0.m(f5, this.valueTo, f4, f5);
            }
        }
        if (isRtl()) {
            minSeparation = -minSeparation;
        }
        int i2 = i + 1;
        if (i2 >= this.values.size()) {
            f2 = this.valueTo;
        } else {
            f2 = ((Float) this.values.get(i2)).floatValue() - minSeparation;
        }
        int i3 = i - 1;
        if (i3 < 0) {
            f3 = this.valueFrom;
        } else {
            f3 = minSeparation + ((Float) this.values.get(i3)).floatValue();
        }
        this.values.set(i, Float.valueOf(MathUtils.clamp(f, f3, f2)));
        Iterator it = this.changeListeners.iterator();
        if (!it.hasNext()) {
            AccessibilityManager accessibilityManager2 = this.accessibilityManager;
            if (accessibilityManager2 == null || !accessibilityManager2.isEnabled()) {
                return true;
            }
            AccessibilityEventSender accessibilityEventSender2 = this.accessibilityEventSender;
            if (accessibilityEventSender2 == null) {
                this.accessibilityEventSender = new AccessibilityEventSender();
            } else {
                removeCallbacks(accessibilityEventSender2);
            }
            AccessibilityEventSender accessibilityEventSender3 = this.accessibilityEventSender;
            accessibilityEventSender3.virtualViewId = i;
            postDelayed(accessibilityEventSender3, 200);
            return true;
        }
        WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(it.next());
        ((Float) this.values.get(i)).getClass();
        throw null;
    }

    public final void snapTouchPosition() {
        double d;
        float f = this.touchPosition;
        float f2 = this.stepSize;
        if (f2 > 0.0f) {
            int i = (int) ((this.valueTo - this.valueFrom) / f2);
            d = ((double) Math.round(f * ((float) i))) / ((double) i);
        } else {
            d = (double) f;
        }
        if (isRtl()) {
            d = 1.0d - d;
        }
        float f3 = this.valueTo;
        float f4 = this.valueFrom;
        snapThumbToValue(this.activeThumbIdx, (float) ((d * ((double) (f3 - f4))) + ((double) f4)));
    }

    public final void updateBoundsForVirtualViewId(int i, Rect rect) {
        int normalizeValue = this.trackSidePadding + ((int) (normalizeValue(((Float) ((ArrayList) getValues()).get(i)).floatValue()) * ((float) this.trackWidth)));
        int calculateTrackCenter = calculateTrackCenter();
        int i2 = this.thumbRadius;
        rect.set(normalizeValue - i2, calculateTrackCenter - i2, normalizeValue + i2, calculateTrackCenter + i2);
    }

    public final void updateHaloHotspot() {
        if (!shouldDrawCompatHalo() && getMeasuredWidth() > 0) {
            Drawable background = getBackground();
            if (background instanceof RippleDrawable) {
                int normalizeValue = (int) ((normalizeValue(((Float) this.values.get(this.focusedThumbIdx)).floatValue()) * ((float) this.trackWidth)) + ((float) this.trackSidePadding));
                int calculateTrackCenter = calculateTrackCenter();
                int i = this.haloRadius;
                background.setHotspotBounds(normalizeValue - i, calculateTrackCenter - i, normalizeValue + i, calculateTrackCenter + i);
            }
        }
    }

    public final void updateWidgetLayout() {
        boolean z;
        int max = Math.max(this.minWidgetHeight, Math.max(this.trackHeight + getPaddingBottom() + getPaddingTop(), getPaddingBottom() + getPaddingTop() + (this.thumbRadius * 2)));
        boolean z2 = false;
        if (max == this.widgetHeight) {
            z = false;
        } else {
            this.widgetHeight = max;
            z = true;
        }
        int max2 = Math.max(Math.max(this.thumbRadius - this.defaultThumbRadius, 0), Math.max((this.trackHeight - this.defaultTrackHeight) / 2, 0)) + this.minTrackSidePadding;
        if (this.trackSidePadding != max2) {
            this.trackSidePadding = max2;
            WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
            if (ViewCompat.Api19Impl.isLaidOut(this)) {
                this.trackWidth = Math.max(getWidth() - (this.trackSidePadding * 2), 0);
                maybeCalculateTicksCoordinates();
            }
            z2 = true;
        }
        if (z) {
            requestLayout();
        } else if (z2) {
            postInvalidate();
        }
    }

    public final void validateConfigurationIfDirty() {
        if (this.dirtyConfig) {
            float f = this.valueFrom;
            float f2 = this.valueTo;
            if (f >= f2) {
                float f3 = this.valueFrom;
                float f4 = this.valueTo;
                throw new IllegalStateException("valueFrom(" + f3 + ") must be smaller than valueTo(" + f4 + ")");
            } else if (f2 <= f) {
                float f5 = this.valueTo;
                float f6 = this.valueFrom;
                throw new IllegalStateException("valueTo(" + f5 + ") must be greater than valueFrom(" + f6 + ")");
            } else if (this.stepSize <= 0.0f || isMultipleOfStepSize(f2 - f)) {
                Iterator it = this.values.iterator();
                while (it.hasNext()) {
                    Float f7 = (Float) it.next();
                    if (f7.floatValue() < this.valueFrom || f7.floatValue() > this.valueTo) {
                        float f8 = this.valueFrom;
                        float f9 = this.valueTo;
                        throw new IllegalStateException("Slider value(" + f7 + ") must be greater or equal to valueFrom(" + f8 + "), and lower or equal to valueTo(" + f9 + ")");
                    } else if (this.stepSize > 0.0f && !isMultipleOfStepSize(f7.floatValue() - this.valueFrom)) {
                        float f10 = this.valueFrom;
                        float f11 = this.stepSize;
                        throw new IllegalStateException("Value(" + f7 + ") must be equal to valueFrom(" + f10 + ") plus a multiple of stepSize(" + f11 + ") when using stepSize(" + f11 + ")");
                    }
                }
                float minSeparation = getMinSeparation();
                if (minSeparation >= 0.0f) {
                    float f12 = this.stepSize;
                    if (f12 > 0.0f && minSeparation > 0.0f) {
                        if (this.separationUnit != 1) {
                            float f13 = this.stepSize;
                            throw new IllegalStateException("minSeparation(" + minSeparation + ") cannot be set as a dimension when using stepSize(" + f13 + ")");
                        } else if (minSeparation < f12 || !isMultipleOfStepSize(minSeparation)) {
                            float f14 = this.stepSize;
                            throw new IllegalStateException("minSeparation(" + minSeparation + ") must be greater or equal and a multiple of stepSize(" + f14 + ") when using stepSize(" + f14 + ")");
                        }
                    }
                    float f15 = this.stepSize;
                    if (f15 != 0.0f) {
                        if (((float) ((int) f15)) != f15) {
                            Log.w("BaseSlider", "Floating point value used for stepSize(" + f15 + "). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the value correctly.");
                        }
                        float f16 = this.valueFrom;
                        if (((float) ((int) f16)) != f16) {
                            Log.w("BaseSlider", "Floating point value used for valueFrom(" + f16 + "). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the value correctly.");
                        }
                        float f17 = this.valueTo;
                        if (((float) ((int) f17)) != f17) {
                            Log.w("BaseSlider", "Floating point value used for valueTo(" + f17 + "). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the value correctly.");
                        }
                    }
                    this.dirtyConfig = false;
                    return;
                }
                throw new IllegalStateException("minSeparation(" + minSeparation + ") must be greater or equal to 0");
            } else {
                float f18 = this.stepSize;
                float f19 = this.valueFrom;
                float f20 = this.valueTo;
                throw new IllegalStateException("The stepSize(" + f18 + ") must be 0, or a factor of the valueFrom(" + f19 + ")-valueTo(" + f20 + ") range");
            }
        }
    }

    public BaseSlider(Context context, final AttributeSet attributeSet, int i) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, 2130969926, 2132018853), attributeSet, 2130969926);
        this.labels = new ArrayList();
        this.changeListeners = new ArrayList();
        this.touchListeners = new ArrayList();
        this.labelsAreAnimatedIn = false;
        this.thumbIsPressed = false;
        this.values = new ArrayList();
        this.activeThumbIdx = -1;
        this.focusedThumbIdx = -1;
        this.stepSize = 0.0f;
        this.tickVisible = true;
        this.isLongPress = false;
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable();
        this.defaultThumbDrawable = materialShapeDrawable;
        this.customThumbDrawablesForValues = Collections.emptyList();
        this.separationUnit = 0;
        Context context2 = getContext();
        Paint paint = new Paint();
        this.inactiveTrackPaint = paint;
        Paint.Style style = Paint.Style.STROKE;
        paint.setStyle(style);
        Paint.Cap cap = Paint.Cap.ROUND;
        paint.setStrokeCap(cap);
        Paint paint2 = new Paint();
        this.activeTrackPaint = paint2;
        paint2.setStyle(style);
        paint2.setStrokeCap(cap);
        Paint paint3 = new Paint(1);
        this.thumbPaint = paint3;
        Paint.Style style2 = Paint.Style.FILL;
        paint3.setStyle(style2);
        paint3.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        Paint paint4 = new Paint(1);
        this.haloPaint = paint4;
        paint4.setStyle(style2);
        Paint paint5 = new Paint();
        this.inactiveTicksPaint = paint5;
        paint5.setStyle(style);
        paint5.setStrokeCap(cap);
        Paint paint6 = new Paint();
        this.activeTicksPaint = paint6;
        paint6.setStyle(style);
        paint6.setStrokeCap(cap);
        Resources resources = context2.getResources();
        this.minWidgetHeight = resources.getDimensionPixelSize(2131166887);
        int dimensionPixelOffset = resources.getDimensionPixelOffset(2131166886);
        this.minTrackSidePadding = dimensionPixelOffset;
        this.trackSidePadding = dimensionPixelOffset;
        this.defaultThumbRadius = resources.getDimensionPixelSize(2131166884);
        this.defaultTrackHeight = resources.getDimensionPixelSize(2131166885);
        this.labelPadding = resources.getDimensionPixelSize(2131166880);
        this.labelMaker = new Object() {
            public final /* synthetic */ int val$defStyleAttr = 2130969926;
        };
        int[] iArr = R$styleable.Slider;
        ThemeEnforcement.checkCompatibleTheme(context2, attributeSet, 2130969926, 2132018853);
        ThemeEnforcement.checkTextAppearance(context2, attributeSet, iArr, 2130969926, 2132018853, new int[0]);
        TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, iArr, 2130969926, 2132018853);
        this.valueFrom = obtainStyledAttributes.getFloat(3, 0.0f);
        this.valueTo = obtainStyledAttributes.getFloat(4, 1.0f);
        setValues(Float.valueOf(this.valueFrom));
        this.stepSize = obtainStyledAttributes.getFloat(2, 0.0f);
        int i2 = 18;
        boolean hasValue = obtainStyledAttributes.hasValue(18);
        int i3 = hasValue ? 18 : 20;
        i2 = !hasValue ? 19 : i2;
        ColorStateList colorStateList = MaterialResources.getColorStateList(context2, obtainStyledAttributes, i3);
        setTrackInactiveTintList(colorStateList == null ? ContextCompat.getColorStateList(2131100407, context2) : colorStateList);
        ColorStateList colorStateList2 = MaterialResources.getColorStateList(context2, obtainStyledAttributes, i2);
        setTrackActiveTintList(colorStateList2 == null ? ContextCompat.getColorStateList(2131100404, context2) : colorStateList2);
        materialShapeDrawable.setFillColor(MaterialResources.getColorStateList(context2, obtainStyledAttributes, 9));
        if (obtainStyledAttributes.hasValue(12)) {
            setThumbStrokeColor(MaterialResources.getColorStateList(context2, obtainStyledAttributes, 12));
        }
        setThumbStrokeWidth(obtainStyledAttributes.getDimension(13, 0.0f));
        ColorStateList colorStateList3 = MaterialResources.getColorStateList(context2, obtainStyledAttributes, 5);
        setHaloTintList(colorStateList3 == null ? ContextCompat.getColorStateList(2131100405, context2) : colorStateList3);
        this.tickVisible = obtainStyledAttributes.getBoolean(17, true);
        int i4 = 14;
        boolean hasValue2 = obtainStyledAttributes.hasValue(14);
        int i5 = hasValue2 ? 14 : 16;
        i4 = !hasValue2 ? 15 : i4;
        ColorStateList colorStateList4 = MaterialResources.getColorStateList(context2, obtainStyledAttributes, i5);
        setTickInactiveTintList(colorStateList4 == null ? ContextCompat.getColorStateList(2131100406, context2) : colorStateList4);
        ColorStateList colorStateList5 = MaterialResources.getColorStateList(context2, obtainStyledAttributes, i4);
        setTickActiveTintList(colorStateList5 == null ? ContextCompat.getColorStateList(2131100403, context2) : colorStateList5);
        setThumbRadius(obtainStyledAttributes.getDimensionPixelSize(11, 0));
        setHaloRadius(obtainStyledAttributes.getDimensionPixelSize(6, 0));
        setThumbElevation(obtainStyledAttributes.getDimension(10, 0.0f));
        setTrackHeight(obtainStyledAttributes.getDimensionPixelSize(21, 0));
        setLabelBehavior(obtainStyledAttributes.getInt(7, 0));
        if (!obtainStyledAttributes.getBoolean(0, true)) {
            setEnabled(false);
        }
        obtainStyledAttributes.recycle();
        setFocusable(true);
        setClickable(true);
        materialShapeDrawable.setShadowCompatibilityMode();
        this.scaledTouchSlop = ViewConfiguration.get(context2).getScaledTouchSlop();
        AccessibilityHelper accessibilityHelper2 = new AccessibilityHelper(this);
        this.accessibilityHelper = accessibilityHelper2;
        ViewCompat.setAccessibilityDelegate(this, accessibilityHelper2);
        this.accessibilityManager = (AccessibilityManager) getContext().getSystemService("accessibility");
    }
}
