package com.google.android.material.chip;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.core.graphics.drawable.WrappedDrawable;
import androidx.core.graphics.drawable.WrappedDrawableApi14;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.widget.ExploreByTouchHelper;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.internal.TextDrawableHelper;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import java.util.Arrays;
import java.util.List;
import java.util.WeakHashMap;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public class Chip extends AppCompatCheckBox implements ChipDrawable.Delegate, Shapeable, Checkable {
    public static final int[] CHECKABLE_STATE_SET = {16842911};
    public static final Rect EMPTY_BOUNDS = new Rect();
    public static final int[] SELECTED_STATE = {16842913};
    public CharSequence accessibilityClassName;
    public final ChipDrawable chipDrawable;
    public boolean closeIconFocused;
    public boolean closeIconHovered;
    public boolean closeIconPressed;
    public boolean deferredCheckedValue;
    public final boolean ensureMinTouchTargetSize;
    public final AnonymousClass1 fontCallback;
    public InsetDrawable insetBackgroundDrawable;
    public int lastLayoutDirection;
    public int minTouchTargetSize;
    public CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
    public final Rect rect;
    public final RectF rectF;
    public RippleDrawable ripple;

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class ChipTouchHelper extends ExploreByTouchHelper {
        public ChipTouchHelper(Chip chip) {
            super(chip);
        }

        public final int getVirtualViewAt(float f, float f2) {
            Rect rect = Chip.EMPTY_BOUNDS;
            Chip chip = Chip.this;
            if (!chip.hasCloseIcon() || !chip.getCloseIconTouchBounds().contains(f, f2)) {
                return 0;
            }
            return 1;
        }

        public final void getVisibleVirtualViews(List list) {
            list.add(0);
            Rect rect = Chip.EMPTY_BOUNDS;
            Chip.this.hasCloseIcon();
        }

        public final boolean onPerformActionForVirtualView(int i, int i2) {
            if (i2 == 16) {
                Chip chip = Chip.this;
                if (i == 0) {
                    return chip.performClick();
                }
                if (i == 1) {
                    chip.playSoundEffect(0);
                }
            }
            return false;
        }

        public final void onPopulateNodeForHost(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            Chip chip = Chip.this;
            accessibilityNodeInfoCompat.mInfo.setCheckable(chip.isCheckable());
            accessibilityNodeInfoCompat.setClickable(chip.isClickable());
            accessibilityNodeInfoCompat.setClassName(chip.getAccessibilityClassName());
            accessibilityNodeInfoCompat.setText(chip.getText());
        }

        public final void onPopulateNodeForVirtualView(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            CharSequence charSequence = "";
            if (i == 1) {
                Chip chip = Chip.this;
                ChipDrawable chipDrawable = chip.chipDrawable;
                CharSequence text = chip.getText();
                Context context = chip.getContext();
                if (!TextUtils.isEmpty(text)) {
                    charSequence = text;
                }
                accessibilityNodeInfoCompat.setContentDescription(context.getString(2131953291, new Object[]{charSequence}).trim());
                RectF closeIconTouchBounds = chip.getCloseIconTouchBounds();
                chip.rect.set((int) closeIconTouchBounds.left, (int) closeIconTouchBounds.top, (int) closeIconTouchBounds.right, (int) closeIconTouchBounds.bottom);
                accessibilityNodeInfoCompat.setBoundsInParent(chip.rect);
                accessibilityNodeInfoCompat.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
                accessibilityNodeInfoCompat.mInfo.setEnabled(chip.isEnabled());
                return;
            }
            accessibilityNodeInfoCompat.setContentDescription(charSequence);
            accessibilityNodeInfoCompat.setBoundsInParent(Chip.EMPTY_BOUNDS);
        }

        public final void onVirtualViewKeyboardFocusChanged(int i, boolean z) {
            if (i == 1) {
                Chip chip = Chip.this;
                chip.closeIconFocused = z;
                chip.refreshDrawableState();
            }
        }
    }

    public Chip(Context context) {
        this(context, (AttributeSet) null);
    }

    public final boolean dispatchHoverEvent(MotionEvent motionEvent) {
        return super.dispatchHoverEvent(motionEvent);
    }

    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    public final void drawableStateChanged() {
        super.drawableStateChanged();
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null && ChipDrawable.isStateful(chipDrawable2.closeIcon)) {
            ChipDrawable chipDrawable3 = this.chipDrawable;
            int isEnabled = isEnabled();
            if (this.closeIconFocused) {
                isEnabled++;
            }
            if (this.closeIconHovered) {
                isEnabled++;
            }
            if (this.closeIconPressed) {
                isEnabled++;
            }
            if (isChecked()) {
                isEnabled++;
            }
            int[] iArr = new int[isEnabled];
            int i = 0;
            if (isEnabled()) {
                iArr[0] = 16842910;
                i = 1;
            }
            if (this.closeIconFocused) {
                iArr[i] = 16842908;
                i++;
            }
            if (this.closeIconHovered) {
                iArr[i] = 16843623;
                i++;
            }
            if (this.closeIconPressed) {
                iArr[i] = 16842919;
                i++;
            }
            if (isChecked()) {
                iArr[i] = 16842913;
            }
            if (!Arrays.equals(chipDrawable3.closeIconStateSet, iArr)) {
                chipDrawable3.closeIconStateSet = iArr;
                if (chipDrawable3.showsCloseIcon() && chipDrawable3.onStateChange(chipDrawable3.getState(), iArr)) {
                    invalidate();
                }
            }
        }
    }

    public final void ensureAccessibleTouchTarget(int i) {
        int i2;
        this.minTouchTargetSize = i;
        float f = 0.0f;
        int i3 = 0;
        if (!this.ensureMinTouchTargetSize) {
            InsetDrawable insetDrawable = this.insetBackgroundDrawable;
            if (insetDrawable == null) {
                updateBackgroundDrawable();
            } else if (insetDrawable != null) {
                this.insetBackgroundDrawable = null;
                setMinWidth(0);
                ChipDrawable chipDrawable2 = this.chipDrawable;
                if (chipDrawable2 != null) {
                    f = chipDrawable2.chipMinHeight;
                }
                setMinHeight((int) f);
                updateBackgroundDrawable();
            }
        } else {
            int max = Math.max(0, i - ((int) this.chipDrawable.chipMinHeight));
            int max2 = Math.max(0, i - this.chipDrawable.getIntrinsicWidth());
            if (max2 > 0 || max > 0) {
                if (max2 > 0) {
                    i2 = max2 / 2;
                } else {
                    i2 = 0;
                }
                if (max > 0) {
                    i3 = max / 2;
                }
                int i4 = i3;
                if (this.insetBackgroundDrawable != null) {
                    Rect rect2 = new Rect();
                    this.insetBackgroundDrawable.getPadding(rect2);
                    if (rect2.top == i4 && rect2.bottom == i4 && rect2.left == i2 && rect2.right == i2) {
                        updateBackgroundDrawable();
                        return;
                    }
                }
                if (getMinHeight() != i) {
                    setMinHeight(i);
                }
                if (getMinWidth() != i) {
                    setMinWidth(i);
                }
                this.insetBackgroundDrawable = new InsetDrawable(this.chipDrawable, i2, i4, i2, i4);
                updateBackgroundDrawable();
                return;
            }
            InsetDrawable insetDrawable2 = this.insetBackgroundDrawable;
            if (insetDrawable2 == null) {
                updateBackgroundDrawable();
            } else if (insetDrawable2 != null) {
                this.insetBackgroundDrawable = null;
                setMinWidth(0);
                ChipDrawable chipDrawable3 = this.chipDrawable;
                if (chipDrawable3 != null) {
                    f = chipDrawable3.chipMinHeight;
                }
                setMinHeight((int) f);
                updateBackgroundDrawable();
            }
        }
    }

    public final CharSequence getAccessibilityClassName() {
        if (!TextUtils.isEmpty(this.accessibilityClassName)) {
            return this.accessibilityClassName;
        }
        if (isCheckable()) {
            getParent();
            return "android.widget.Button";
        } else if (isClickable()) {
            return "android.widget.Button";
        } else {
            return "android.view.View";
        }
    }

    public final RectF getCloseIconTouchBounds() {
        this.rectF.setEmpty();
        hasCloseIcon();
        return this.rectF;
    }

    public final TextUtils.TruncateAt getEllipsize() {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            return chipDrawable2.truncateAt;
        }
        return null;
    }

    public final void getFocusedRect(Rect rect2) {
        super.getFocusedRect(rect2);
    }

    public final boolean hasCloseIcon() {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            Drawable drawable = chipDrawable2.closeIcon;
            if (drawable == null) {
                drawable = null;
            } else if (drawable instanceof WrappedDrawable) {
                drawable = ((WrappedDrawableApi14) ((WrappedDrawable) drawable)).mDrawable;
            }
            if (drawable != null) {
                return true;
            }
        }
        return false;
    }

    public final boolean isCheckable() {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 == null || !chipDrawable2.checkable) {
            return false;
        }
        return true;
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.setParentAbsoluteElevation(this, this.chipDrawable);
    }

    public final int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 2);
        if (isChecked()) {
            CheckBox.mergeDrawableStates(onCreateDrawableState, SELECTED_STATE);
        }
        if (isCheckable()) {
            CheckBox.mergeDrawableStates(onCreateDrawableState, CHECKABLE_STATE_SET);
        }
        return onCreateDrawableState;
    }

    public final void onFocusChanged(boolean z, int i, Rect rect2) {
        super.onFocusChanged(z, i, rect2);
    }

    public final boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 7) {
            boolean contains = getCloseIconTouchBounds().contains(motionEvent.getX(), motionEvent.getY());
            if (this.closeIconHovered != contains) {
                this.closeIconHovered = contains;
                refreshDrawableState();
            }
        } else if (actionMasked == 10 && this.closeIconHovered) {
            this.closeIconHovered = false;
            refreshDrawableState();
        }
        return super.onHoverEvent(motionEvent);
    }

    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(getAccessibilityClassName());
        accessibilityNodeInfo.setCheckable(isCheckable());
        accessibilityNodeInfo.setClickable(isClickable());
        getParent();
    }

    public final PointerIcon onResolvePointerIcon(MotionEvent motionEvent, int i) {
        if (!getCloseIconTouchBounds().contains(motionEvent.getX(), motionEvent.getY()) || !isEnabled()) {
            return null;
        }
        return PointerIcon.getSystemIcon(getContext(), 1002);
    }

    public final void onRtlPropertiesChanged(int i) {
        super.onRtlPropertiesChanged(i);
        if (this.lastLayoutDirection != i) {
            this.lastLayoutDirection = i;
            updatePaddingInternal();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001e, code lost:
        if (r0 != 3) goto L_0x0051;
     */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean onTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            int r0 = r6.getActionMasked()
            android.graphics.RectF r1 = r5.getCloseIconTouchBounds()
            float r2 = r6.getX()
            float r3 = r6.getY()
            boolean r1 = r1.contains(r2, r3)
            r2 = 1
            r3 = 0
            if (r0 == 0) goto L_0x0045
            if (r0 == r2) goto L_0x002f
            r4 = 2
            if (r0 == r4) goto L_0x0021
            r1 = 3
            if (r0 == r1) goto L_0x0038
            goto L_0x0051
        L_0x0021:
            boolean r0 = r5.closeIconPressed
            if (r0 == 0) goto L_0x0051
            if (r1 != 0) goto L_0x0059
            if (r0 == 0) goto L_0x0059
            r5.closeIconPressed = r3
            r5.refreshDrawableState()
            goto L_0x0059
        L_0x002f:
            boolean r0 = r5.closeIconPressed
            if (r0 == 0) goto L_0x0038
            r5.playSoundEffect(r3)
            r0 = r2
            goto L_0x0039
        L_0x0038:
            r0 = r3
        L_0x0039:
            boolean r1 = r5.closeIconPressed
            if (r1 == 0) goto L_0x0042
            r5.closeIconPressed = r3
            r5.refreshDrawableState()
        L_0x0042:
            if (r0 != 0) goto L_0x0059
            goto L_0x0051
        L_0x0045:
            if (r1 == 0) goto L_0x0051
            boolean r6 = r5.closeIconPressed
            if (r6 == r2) goto L_0x0059
            r5.closeIconPressed = r2
            r5.refreshDrawableState()
            goto L_0x0059
        L_0x0051:
            boolean r5 = super.onTouchEvent(r6)
            if (r5 == 0) goto L_0x0058
            goto L_0x0059
        L_0x0058:
            r2 = r3
        L_0x0059:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.chip.Chip.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public final void setBackground(Drawable drawable) {
        Drawable drawable2 = this.insetBackgroundDrawable;
        if (drawable2 == null) {
            drawable2 = this.chipDrawable;
        }
        if (drawable == drawable2 || drawable == this.ripple) {
            super.setBackground(drawable);
        } else {
            Log.w("Chip", "Do not set the background; Chip manages its own background drawable.");
        }
    }

    public final void setBackgroundColor(int i) {
        Log.w("Chip", "Do not set the background color; Chip manages its own background drawable.");
    }

    public final void setBackgroundDrawable(Drawable drawable) {
        Drawable drawable2 = this.insetBackgroundDrawable;
        if (drawable2 == null) {
            drawable2 = this.chipDrawable;
        }
        if (drawable == drawable2 || drawable == this.ripple) {
            super.setBackgroundDrawable(drawable);
        } else {
            Log.w("Chip", "Do not set the background drawable; Chip manages its own background drawable.");
        }
    }

    public final void setBackgroundResource(int i) {
        Log.w("Chip", "Do not set the background resource; Chip manages its own background drawable.");
    }

    public final void setBackgroundTintList(ColorStateList colorStateList) {
        Log.w("Chip", "Do not set the background tint list; Chip manages its own background drawable.");
    }

    public final void setBackgroundTintMode(PorterDuff.Mode mode) {
        Log.w("Chip", "Do not set the background tint mode; Chip manages its own background drawable.");
    }

    public final void setChecked(boolean z) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 == null) {
            this.deferredCheckedValue = z;
        } else if (chipDrawable2.checkable) {
            super.setChecked(z);
        }
    }

    public final void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        } else if (drawable3 == null) {
            super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        } else {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
    }

    public final void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        } else if (drawable3 == null) {
            super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        } else {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
    }

    public final void setCompoundDrawablesRelativeWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        if (i != 0) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        } else if (i3 == 0) {
            super.setCompoundDrawablesRelativeWithIntrinsicBounds(i, i2, i3, i4);
        } else {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
    }

    public final void setCompoundDrawablesWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        if (i != 0) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        } else if (i3 == 0) {
            super.setCompoundDrawablesWithIntrinsicBounds(i, i2, i3, i4);
        } else {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
    }

    public final void setElevation(float f) {
        super.setElevation(f);
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setElevation(f);
        }
    }

    public final void setEllipsize(TextUtils.TruncateAt truncateAt) {
        if (this.chipDrawable != null) {
            if (truncateAt != TextUtils.TruncateAt.MARQUEE) {
                super.setEllipsize(truncateAt);
                ChipDrawable chipDrawable2 = this.chipDrawable;
                if (chipDrawable2 != null) {
                    chipDrawable2.truncateAt = truncateAt;
                    return;
                }
                return;
            }
            throw new UnsupportedOperationException("Text within a chip are not allowed to scroll.");
        }
    }

    public final void setGravity(int i) {
        if (i != 8388627) {
            Log.w("Chip", "Chip text must be vertically center and start aligned");
        } else {
            super.setGravity(i);
        }
    }

    public final void setLayoutDirection(int i) {
        if (this.chipDrawable != null) {
            super.setLayoutDirection(i);
        }
    }

    public final void setLines(int i) {
        if (i <= 1) {
            super.setLines(i);
            return;
        }
        throw new UnsupportedOperationException("Chip does not support multi-line text");
    }

    public final void setMaxLines(int i) {
        if (i <= 1) {
            super.setMaxLines(i);
            return;
        }
        throw new UnsupportedOperationException("Chip does not support multi-line text");
    }

    public final void setMaxWidth(int i) {
        super.setMaxWidth(i);
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.maxWidth = i;
        }
    }

    public final void setMinLines(int i) {
        if (i <= 1) {
            super.setMinLines(i);
            return;
        }
        throw new UnsupportedOperationException("Chip does not support multi-line text");
    }

    public final void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener2) {
        this.onCheckedChangeListener = onCheckedChangeListener2;
    }

    public final void setShapeAppearanceModel(ShapeAppearanceModel shapeAppearanceModel) {
        this.chipDrawable.setShapeAppearanceModel(shapeAppearanceModel);
    }

    public final void setSingleLine(boolean z) {
        if (z) {
            super.setSingleLine(z);
            return;
        }
        throw new UnsupportedOperationException("Chip does not support multi-line text");
    }

    public final void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        CharSequence charSequence2;
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            if (charSequence == null) {
                charSequence = "";
            }
            if (chipDrawable2.shouldDrawText) {
                charSequence2 = null;
            } else {
                charSequence2 = charSequence;
            }
            super.setText(charSequence2, bufferType);
            ChipDrawable chipDrawable3 = this.chipDrawable;
            if (chipDrawable3 != null && !TextUtils.equals(chipDrawable3.text, charSequence)) {
                chipDrawable3.text = charSequence;
                chipDrawable3.textDrawableHelper.textWidthDirty = true;
                chipDrawable3.invalidateSelf();
                chipDrawable3.onSizeChange();
            }
        }
    }

    public final void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setTextAppearance(new TextAppearance(chipDrawable2.context, i));
        }
        updateTextPaintDrawState();
    }

    public final void setTextSize(int i, float f) {
        super.setTextSize(i, f);
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            float applyDimension = TypedValue.applyDimension(i, f, getResources().getDisplayMetrics());
            TextDrawableHelper textDrawableHelper = chipDrawable2.textDrawableHelper;
            TextAppearance textAppearance = textDrawableHelper.textAppearance;
            if (textAppearance != null) {
                textAppearance.textSize = applyDimension;
                textDrawableHelper.textPaint.setTextSize(applyDimension);
                chipDrawable2.onSizeChange();
                chipDrawable2.invalidateSelf();
            }
        }
        updateTextPaintDrawState();
    }

    public final void updateBackgroundDrawable() {
        ColorStateList colorStateList = this.chipDrawable.rippleColor;
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(0);
        }
        Drawable drawable = this.insetBackgroundDrawable;
        if (drawable == null) {
            drawable = this.chipDrawable;
        }
        this.ripple = new RippleDrawable(colorStateList, drawable, (Drawable) null);
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2.useCompatRipple) {
            chipDrawable2.useCompatRipple = false;
            chipDrawable2.compatRippleColor = null;
            chipDrawable2.onStateChange(chipDrawable2.getState());
        }
        RippleDrawable rippleDrawable = this.ripple;
        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
        setBackground(rippleDrawable);
        updatePaddingInternal();
    }

    public final void updatePaddingInternal() {
        ChipDrawable chipDrawable2;
        if (!TextUtils.isEmpty(getText()) && (chipDrawable2 = this.chipDrawable) != null) {
            int calculateCloseIconWidth = (int) (chipDrawable2.calculateCloseIconWidth() + chipDrawable2.chipEndPadding + chipDrawable2.textEndPadding);
            ChipDrawable chipDrawable3 = this.chipDrawable;
            int calculateChipIconWidth = (int) (chipDrawable3.calculateChipIconWidth() + chipDrawable3.chipStartPadding + chipDrawable3.textStartPadding);
            if (this.insetBackgroundDrawable != null) {
                Rect rect2 = new Rect();
                this.insetBackgroundDrawable.getPadding(rect2);
                calculateChipIconWidth += rect2.left;
                calculateCloseIconWidth += rect2.right;
            }
            int paddingTop = getPaddingTop();
            int paddingBottom = getPaddingBottom();
            WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
            setPaddingRelative(calculateChipIconWidth, paddingTop, calculateCloseIconWidth, paddingBottom);
        }
    }

    public final void updateTextPaintDrawState() {
        TextAppearance textAppearance;
        TextPaint paint = getPaint();
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            paint.drawableState = chipDrawable2.getState();
        }
        ChipDrawable chipDrawable3 = this.chipDrawable;
        if (chipDrawable3 != null) {
            textAppearance = chipDrawable3.textDrawableHelper.textAppearance;
        } else {
            textAppearance = null;
        }
        if (textAppearance != null) {
            textAppearance.updateDrawState(getContext(), paint, this.fontCallback);
        }
    }

    public Chip(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 2130968816);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Chip(android.content.Context r17, android.util.AttributeSet r18, int r19) {
        /*
            r16 = this;
            r0 = r16
            r7 = r18
            r8 = r19
            r1 = 2132018804(0x7f140674, float:1.9675925E38)
            r2 = r17
            android.content.Context r1 = com.google.android.material.theme.overlay.MaterialThemeOverlay.wrap(r2, r7, r8, r1)
            r0.<init>(r1, r7, r8)
            android.graphics.Rect r1 = new android.graphics.Rect
            r1.<init>()
            r0.rect = r1
            android.graphics.RectF r1 = new android.graphics.RectF
            r1.<init>()
            r0.rectF = r1
            com.google.android.material.chip.Chip$1 r1 = new com.google.android.material.chip.Chip$1
            r1.<init>()
            r0.fontCallback = r1
            android.content.Context r9 = r16.getContext()
            r10 = 1
            r11 = 8388627(0x800013, float:1.175497E-38)
            if (r7 != 0) goto L_0x0032
            goto L_0x0092
        L_0x0032:
            java.lang.String r1 = "background"
            java.lang.String r2 = "http://schemas.android.com/apk/res/android"
            java.lang.String r1 = r7.getAttributeValue(r2, r1)
            java.lang.String r3 = "Chip"
            if (r1 == 0) goto L_0x0043
            java.lang.String r1 = "Do not set the background; Chip manages its own background drawable."
            android.util.Log.w(r3, r1)
        L_0x0043:
            java.lang.String r1 = "drawableLeft"
            java.lang.String r1 = r7.getAttributeValue(r2, r1)
            if (r1 != 0) goto L_0x05f1
            java.lang.String r1 = "drawableStart"
            java.lang.String r1 = r7.getAttributeValue(r2, r1)
            if (r1 != 0) goto L_0x05e9
            java.lang.String r1 = "drawableEnd"
            java.lang.String r1 = r7.getAttributeValue(r2, r1)
            java.lang.String r4 = "Please set end drawable using R.attr#closeIcon."
            if (r1 != 0) goto L_0x05e3
            java.lang.String r1 = "drawableRight"
            java.lang.String r1 = r7.getAttributeValue(r2, r1)
            if (r1 != 0) goto L_0x05dd
            java.lang.String r1 = "singleLine"
            boolean r1 = r7.getAttributeBooleanValue(r2, r1, r10)
            if (r1 == 0) goto L_0x05d5
            java.lang.String r1 = "lines"
            int r1 = r7.getAttributeIntValue(r2, r1, r10)
            if (r1 != r10) goto L_0x05d5
            java.lang.String r1 = "minLines"
            int r1 = r7.getAttributeIntValue(r2, r1, r10)
            if (r1 != r10) goto L_0x05d5
            java.lang.String r1 = "maxLines"
            int r1 = r7.getAttributeIntValue(r2, r1, r10)
            if (r1 != r10) goto L_0x05d5
            java.lang.String r1 = "gravity"
            int r1 = r7.getAttributeIntValue(r2, r1, r11)
            if (r1 == r11) goto L_0x0092
            java.lang.String r1 = "Chip text must be vertically center and start aligned"
            android.util.Log.w(r3, r1)
        L_0x0092:
            com.google.android.material.chip.ChipDrawable r12 = new com.google.android.material.chip.ChipDrawable
            r12.<init>(r9, r7, r8)
            android.content.Context r1 = r12.context
            int[] r13 = com.google.android.material.R$styleable.Chip
            r14 = 0
            int[] r6 = new int[r14]
            r5 = 2132018804(0x7f140674, float:1.9675925E38)
            r2 = r18
            r3 = r13
            r4 = r19
            android.content.res.TypedArray r1 = com.google.android.material.internal.ThemeEnforcement.obtainStyledAttributes(r1, r2, r3, r4, r5, r6)
            r15 = 37
            boolean r2 = r1.hasValue(r15)
            r12.isShapeThemingEnabled = r2
            android.content.Context r2 = r12.context
            r3 = 24
            android.content.res.ColorStateList r2 = com.google.android.material.resources.MaterialResources.getColorStateList((android.content.Context) r2, (android.content.res.TypedArray) r1, (int) r3)
            android.content.res.ColorStateList r3 = r12.chipSurfaceColor
            if (r3 == r2) goto L_0x00c7
            r12.chipSurfaceColor = r2
            int[] r2 = r12.getState()
            r12.onStateChange(r2)
        L_0x00c7:
            android.content.Context r2 = r12.context
            r3 = 11
            android.content.res.ColorStateList r2 = com.google.android.material.resources.MaterialResources.getColorStateList((android.content.Context) r2, (android.content.res.TypedArray) r1, (int) r3)
            android.content.res.ColorStateList r3 = r12.chipBackgroundColor
            if (r3 == r2) goto L_0x00dc
            r12.chipBackgroundColor = r2
            int[] r2 = r12.getState()
            r12.onStateChange(r2)
        L_0x00dc:
            r2 = 19
            r3 = 0
            float r2 = r1.getDimension(r2, r3)
            float r4 = r12.chipMinHeight
            int r4 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x00f1
            r12.chipMinHeight = r2
            r12.invalidateSelf()
            r12.onSizeChange()
        L_0x00f1:
            r2 = 12
            boolean r4 = r1.hasValue(r2)
            if (r4 == 0) goto L_0x0130
            float r2 = r1.getDimension(r2, r3)
            float r4 = r12.chipCornerRadius
            int r4 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x0130
            r12.chipCornerRadius = r2
            com.google.android.material.shape.MaterialShapeDrawable$MaterialShapeDrawableState r4 = r12.drawableState
            com.google.android.material.shape.ShapeAppearanceModel r4 = r4.shapeAppearanceModel
            com.google.android.material.shape.ShapeAppearanceModel$Builder r4 = r4.toBuilder()
            com.google.android.material.shape.AbsoluteCornerSize r5 = new com.google.android.material.shape.AbsoluteCornerSize
            r5.<init>(r2)
            r4.topLeftCornerSize = r5
            com.google.android.material.shape.AbsoluteCornerSize r5 = new com.google.android.material.shape.AbsoluteCornerSize
            r5.<init>(r2)
            r4.topRightCornerSize = r5
            com.google.android.material.shape.AbsoluteCornerSize r5 = new com.google.android.material.shape.AbsoluteCornerSize
            r5.<init>(r2)
            r4.bottomRightCornerSize = r5
            com.google.android.material.shape.AbsoluteCornerSize r5 = new com.google.android.material.shape.AbsoluteCornerSize
            r5.<init>(r2)
            r4.bottomLeftCornerSize = r5
            com.google.android.material.shape.ShapeAppearanceModel r2 = r4.build()
            r12.setShapeAppearanceModel(r2)
        L_0x0130:
            android.content.Context r2 = r12.context
            r4 = 22
            android.content.res.ColorStateList r2 = com.google.android.material.resources.MaterialResources.getColorStateList((android.content.Context) r2, (android.content.res.TypedArray) r1, (int) r4)
            android.content.res.ColorStateList r4 = r12.chipStrokeColor
            if (r4 == r2) goto L_0x0158
            r12.chipStrokeColor = r2
            boolean r4 = r12.isShapeThemingEnabled
            if (r4 == 0) goto L_0x0151
            com.google.android.material.shape.MaterialShapeDrawable$MaterialShapeDrawableState r4 = r12.drawableState
            android.content.res.ColorStateList r5 = r4.strokeColor
            if (r5 == r2) goto L_0x0151
            r4.strokeColor = r2
            int[] r2 = r12.getState()
            r12.onStateChange(r2)
        L_0x0151:
            int[] r2 = r12.getState()
            r12.onStateChange(r2)
        L_0x0158:
            r2 = 23
            float r2 = r1.getDimension(r2, r3)
            float r4 = r12.chipStrokeWidth
            int r4 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x0179
            r12.chipStrokeWidth = r2
            android.graphics.Paint r4 = r12.chipPaint
            r4.setStrokeWidth(r2)
            boolean r4 = r12.isShapeThemingEnabled
            if (r4 == 0) goto L_0x0176
            com.google.android.material.shape.MaterialShapeDrawable$MaterialShapeDrawableState r4 = r12.drawableState
            r4.strokeWidth = r2
            r12.invalidateSelf()
        L_0x0176:
            r12.invalidateSelf()
        L_0x0179:
            android.content.Context r2 = r12.context
            r4 = 36
            android.content.res.ColorStateList r2 = com.google.android.material.resources.MaterialResources.getColorStateList((android.content.Context) r2, (android.content.res.TypedArray) r1, (int) r4)
            android.content.res.ColorStateList r4 = r12.rippleColor
            if (r4 == r2) goto L_0x019d
            r12.rippleColor = r2
            boolean r4 = r12.useCompatRipple
            if (r4 == 0) goto L_0x0193
            if (r2 == 0) goto L_0x018e
            goto L_0x0194
        L_0x018e:
            android.content.res.ColorStateList r2 = android.content.res.ColorStateList.valueOf(r14)
            goto L_0x0194
        L_0x0193:
            r2 = 0
        L_0x0194:
            r12.compatRippleColor = r2
            int[] r2 = r12.getState()
            r12.onStateChange(r2)
        L_0x019d:
            r2 = 5
            java.lang.CharSequence r2 = r1.getText(r2)
            if (r2 != 0) goto L_0x01a6
            java.lang.String r2 = ""
        L_0x01a6:
            java.lang.CharSequence r4 = r12.text
            boolean r4 = android.text.TextUtils.equals(r4, r2)
            if (r4 != 0) goto L_0x01ba
            r12.text = r2
            com.google.android.material.internal.TextDrawableHelper r2 = r12.textDrawableHelper
            r2.textWidthDirty = r10
            r12.invalidateSelf()
            r12.onSizeChange()
        L_0x01ba:
            android.content.Context r2 = r12.context
            boolean r4 = r1.hasValue(r14)
            if (r4 == 0) goto L_0x01ce
            int r4 = r1.getResourceId(r14, r14)
            if (r4 == 0) goto L_0x01ce
            com.google.android.material.resources.TextAppearance r5 = new com.google.android.material.resources.TextAppearance
            r5.<init>(r2, r4)
            goto L_0x01cf
        L_0x01ce:
            r5 = 0
        L_0x01cf:
            float r2 = r5.textSize
            float r2 = r1.getDimension(r10, r2)
            r5.textSize = r2
            r12.setTextAppearance(r5)
            r2 = 3
            int r4 = r1.getInt(r2, r14)
            if (r4 == r10) goto L_0x01f1
            r5 = 2
            if (r4 == r5) goto L_0x01ec
            if (r4 == r2) goto L_0x01e7
            goto L_0x01f5
        L_0x01e7:
            android.text.TextUtils$TruncateAt r2 = android.text.TextUtils.TruncateAt.END
            r12.truncateAt = r2
            goto L_0x01f5
        L_0x01ec:
            android.text.TextUtils$TruncateAt r2 = android.text.TextUtils.TruncateAt.MIDDLE
            r12.truncateAt = r2
            goto L_0x01f5
        L_0x01f1:
            android.text.TextUtils$TruncateAt r2 = android.text.TextUtils.TruncateAt.START
            r12.truncateAt = r2
        L_0x01f5:
            r2 = 18
            boolean r2 = r1.getBoolean(r2, r14)
            r12.setChipIconVisible(r2)
            java.lang.String r2 = "http://schemas.android.com/apk/res-auto"
            if (r7 == 0) goto L_0x021b
            java.lang.String r4 = "chipIconEnabled"
            java.lang.String r4 = r7.getAttributeValue(r2, r4)
            if (r4 == 0) goto L_0x021b
            java.lang.String r4 = "chipIconVisible"
            java.lang.String r4 = r7.getAttributeValue(r2, r4)
            if (r4 != 0) goto L_0x021b
            r4 = 15
            boolean r4 = r1.getBoolean(r4, r14)
            r12.setChipIconVisible(r4)
        L_0x021b:
            android.content.Context r4 = r12.context
            r5 = 14
            android.graphics.drawable.Drawable r4 = com.google.android.material.resources.MaterialResources.getDrawable(r4, r1, r5)
            android.graphics.drawable.Drawable r5 = r12.chipIcon
            if (r5 == 0) goto L_0x0232
            boolean r6 = r5 instanceof androidx.core.graphics.drawable.WrappedDrawable
            if (r6 == 0) goto L_0x0233
            androidx.core.graphics.drawable.WrappedDrawable r5 = (androidx.core.graphics.drawable.WrappedDrawable) r5
            androidx.core.graphics.drawable.WrappedDrawableApi14 r5 = (androidx.core.graphics.drawable.WrappedDrawableApi14) r5
            android.graphics.drawable.Drawable r5 = r5.mDrawable
            goto L_0x0233
        L_0x0232:
            r5 = 0
        L_0x0233:
            if (r5 == r4) goto L_0x025f
            float r6 = r12.calculateChipIconWidth()
            if (r4 == 0) goto L_0x0240
            android.graphics.drawable.Drawable r4 = r4.mutate()
            goto L_0x0241
        L_0x0240:
            r4 = 0
        L_0x0241:
            r12.chipIcon = r4
            float r4 = r12.calculateChipIconWidth()
            com.google.android.material.chip.ChipDrawable.unapplyChildDrawable(r5)
            boolean r5 = r12.showsChipIcon()
            if (r5 == 0) goto L_0x0255
            android.graphics.drawable.Drawable r5 = r12.chipIcon
            r12.applyChildDrawable(r5)
        L_0x0255:
            r12.invalidateSelf()
            int r4 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r4 == 0) goto L_0x025f
            r12.onSizeChange()
        L_0x025f:
            r4 = 17
            boolean r5 = r1.hasValue(r4)
            if (r5 == 0) goto L_0x0287
            android.content.Context r5 = r12.context
            android.content.res.ColorStateList r4 = com.google.android.material.resources.MaterialResources.getColorStateList((android.content.Context) r5, (android.content.res.TypedArray) r1, (int) r4)
            r12.hasChipIconTint = r10
            android.content.res.ColorStateList r5 = r12.chipIconTint
            if (r5 == r4) goto L_0x0287
            r12.chipIconTint = r4
            boolean r5 = r12.showsChipIcon()
            if (r5 == 0) goto L_0x0280
            android.graphics.drawable.Drawable r5 = r12.chipIcon
            r5.setTintList(r4)
        L_0x0280:
            int[] r4 = r12.getState()
            r12.onStateChange(r4)
        L_0x0287:
            r4 = 16
            r5 = -1082130432(0xffffffffbf800000, float:-1.0)
            float r4 = r1.getDimension(r4, r5)
            float r5 = r12.chipIconSize
            int r5 = (r5 > r4 ? 1 : (r5 == r4 ? 0 : -1))
            if (r5 == 0) goto L_0x02a9
            float r5 = r12.calculateChipIconWidth()
            r12.chipIconSize = r4
            float r4 = r12.calculateChipIconWidth()
            r12.invalidateSelf()
            int r4 = (r5 > r4 ? 1 : (r5 == r4 ? 0 : -1))
            if (r4 == 0) goto L_0x02a9
            r12.onSizeChange()
        L_0x02a9:
            r4 = 31
            boolean r4 = r1.getBoolean(r4, r14)
            r12.setCloseIconVisible(r4)
            if (r7 == 0) goto L_0x02cd
            java.lang.String r4 = "closeIconEnabled"
            java.lang.String r4 = r7.getAttributeValue(r2, r4)
            if (r4 == 0) goto L_0x02cd
            java.lang.String r4 = "closeIconVisible"
            java.lang.String r4 = r7.getAttributeValue(r2, r4)
            if (r4 != 0) goto L_0x02cd
            r4 = 26
            boolean r4 = r1.getBoolean(r4, r14)
            r12.setCloseIconVisible(r4)
        L_0x02cd:
            android.content.Context r4 = r12.context
            r5 = 25
            android.graphics.drawable.Drawable r4 = com.google.android.material.resources.MaterialResources.getDrawable(r4, r1, r5)
            android.graphics.drawable.Drawable r5 = r12.closeIcon
            if (r5 == 0) goto L_0x02e4
            boolean r6 = r5 instanceof androidx.core.graphics.drawable.WrappedDrawable
            if (r6 == 0) goto L_0x02e5
            androidx.core.graphics.drawable.WrappedDrawable r5 = (androidx.core.graphics.drawable.WrappedDrawable) r5
            androidx.core.graphics.drawable.WrappedDrawableApi14 r5 = (androidx.core.graphics.drawable.WrappedDrawableApi14) r5
            android.graphics.drawable.Drawable r5 = r5.mDrawable
            goto L_0x02e5
        L_0x02e4:
            r5 = 0
        L_0x02e5:
            if (r5 == r4) goto L_0x0325
            float r6 = r12.calculateCloseIconWidth()
            if (r4 == 0) goto L_0x02f2
            android.graphics.drawable.Drawable r4 = r4.mutate()
            goto L_0x02f3
        L_0x02f2:
            r4 = 0
        L_0x02f3:
            r12.closeIcon = r4
            android.graphics.drawable.RippleDrawable r4 = new android.graphics.drawable.RippleDrawable
            android.content.res.ColorStateList r11 = r12.rippleColor
            if (r11 == 0) goto L_0x02fc
            goto L_0x0300
        L_0x02fc:
            android.content.res.ColorStateList r11 = android.content.res.ColorStateList.valueOf(r14)
        L_0x0300:
            android.graphics.drawable.Drawable r10 = r12.closeIcon
            android.graphics.drawable.ShapeDrawable r15 = com.google.android.material.chip.ChipDrawable.closeIconRippleMask
            r4.<init>(r11, r10, r15)
            r12.closeIconRipple = r4
            float r4 = r12.calculateCloseIconWidth()
            com.google.android.material.chip.ChipDrawable.unapplyChildDrawable(r5)
            boolean r5 = r12.showsCloseIcon()
            if (r5 == 0) goto L_0x031b
            android.graphics.drawable.Drawable r5 = r12.closeIcon
            r12.applyChildDrawable(r5)
        L_0x031b:
            r12.invalidateSelf()
            int r4 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r4 == 0) goto L_0x0325
            r12.onSizeChange()
        L_0x0325:
            android.content.Context r4 = r12.context
            r5 = 30
            android.content.res.ColorStateList r4 = com.google.android.material.resources.MaterialResources.getColorStateList((android.content.Context) r4, (android.content.res.TypedArray) r1, (int) r5)
            android.content.res.ColorStateList r5 = r12.closeIconTint
            if (r5 == r4) goto L_0x0345
            r12.closeIconTint = r4
            boolean r5 = r12.showsCloseIcon()
            if (r5 == 0) goto L_0x033e
            android.graphics.drawable.Drawable r5 = r12.closeIcon
            r5.setTintList(r4)
        L_0x033e:
            int[] r4 = r12.getState()
            r12.onStateChange(r4)
        L_0x0345:
            r4 = 28
            float r4 = r1.getDimension(r4, r3)
            float r5 = r12.closeIconSize
            int r5 = (r5 > r4 ? 1 : (r5 == r4 ? 0 : -1))
            if (r5 == 0) goto L_0x035f
            r12.closeIconSize = r4
            r12.invalidateSelf()
            boolean r4 = r12.showsCloseIcon()
            if (r4 == 0) goto L_0x035f
            r12.onSizeChange()
        L_0x035f:
            r4 = 6
            boolean r4 = r1.getBoolean(r4, r14)
            boolean r5 = r12.checkable
            if (r5 == r4) goto L_0x0384
            r12.checkable = r4
            float r5 = r12.calculateChipIconWidth()
            if (r4 != 0) goto L_0x0376
            boolean r4 = r12.currentChecked
            if (r4 == 0) goto L_0x0376
            r12.currentChecked = r14
        L_0x0376:
            float r4 = r12.calculateChipIconWidth()
            r12.invalidateSelf()
            int r4 = (r5 > r4 ? 1 : (r5 == r4 ? 0 : -1))
            if (r4 == 0) goto L_0x0384
            r12.onSizeChange()
        L_0x0384:
            r4 = 10
            boolean r4 = r1.getBoolean(r4, r14)
            r12.setCheckedIconVisible(r4)
            if (r7 == 0) goto L_0x03a8
            java.lang.String r4 = "checkedIconEnabled"
            java.lang.String r4 = r7.getAttributeValue(r2, r4)
            if (r4 == 0) goto L_0x03a8
            java.lang.String r4 = "checkedIconVisible"
            java.lang.String r2 = r7.getAttributeValue(r2, r4)
            if (r2 != 0) goto L_0x03a8
            r2 = 8
            boolean r2 = r1.getBoolean(r2, r14)
            r12.setCheckedIconVisible(r2)
        L_0x03a8:
            android.content.Context r2 = r12.context
            r4 = 7
            android.graphics.drawable.Drawable r2 = com.google.android.material.resources.MaterialResources.getDrawable(r2, r1, r4)
            android.graphics.drawable.Drawable r4 = r12.checkedIcon
            if (r4 == r2) goto L_0x03d1
            float r4 = r12.calculateChipIconWidth()
            r12.checkedIcon = r2
            float r2 = r12.calculateChipIconWidth()
            android.graphics.drawable.Drawable r5 = r12.checkedIcon
            com.google.android.material.chip.ChipDrawable.unapplyChildDrawable(r5)
            android.graphics.drawable.Drawable r5 = r12.checkedIcon
            r12.applyChildDrawable(r5)
            r12.invalidateSelf()
            int r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r2 == 0) goto L_0x03d1
            r12.onSizeChange()
        L_0x03d1:
            r2 = 9
            boolean r4 = r1.hasValue(r2)
            if (r4 == 0) goto L_0x03fb
            android.content.Context r4 = r12.context
            android.content.res.ColorStateList r2 = com.google.android.material.resources.MaterialResources.getColorStateList((android.content.Context) r4, (android.content.res.TypedArray) r1, (int) r2)
            android.content.res.ColorStateList r4 = r12.checkedIconTint
            if (r4 == r2) goto L_0x03fb
            r12.checkedIconTint = r2
            boolean r4 = r12.checkedIconVisible
            if (r4 == 0) goto L_0x03f4
            android.graphics.drawable.Drawable r4 = r12.checkedIcon
            if (r4 == 0) goto L_0x03f4
            boolean r5 = r12.checkable
            if (r5 == 0) goto L_0x03f4
            r4.setTintList(r2)
        L_0x03f4:
            int[] r2 = r12.getState()
            r12.onStateChange(r2)
        L_0x03fb:
            android.content.Context r2 = r12.context
            r4 = 39
            boolean r5 = r1.hasValue(r4)
            if (r5 == 0) goto L_0x040e
            int r4 = r1.getResourceId(r4, r14)
            if (r4 == 0) goto L_0x040e
            com.google.android.material.animation.MotionSpec.createFromResource(r4, r2)
        L_0x040e:
            android.content.Context r2 = r12.context
            r4 = 33
            boolean r5 = r1.hasValue(r4)
            if (r5 == 0) goto L_0x0421
            int r4 = r1.getResourceId(r4, r14)
            if (r4 == 0) goto L_0x0421
            com.google.android.material.animation.MotionSpec.createFromResource(r4, r2)
        L_0x0421:
            r2 = 21
            float r2 = r1.getDimension(r2, r3)
            float r4 = r12.chipStartPadding
            int r4 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x0435
            r12.chipStartPadding = r2
            r12.invalidateSelf()
            r12.onSizeChange()
        L_0x0435:
            r2 = 35
            float r2 = r1.getDimension(r2, r3)
            float r4 = r12.iconStartPadding
            int r4 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x0455
            float r4 = r12.calculateChipIconWidth()
            r12.iconStartPadding = r2
            float r2 = r12.calculateChipIconWidth()
            r12.invalidateSelf()
            int r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r2 == 0) goto L_0x0455
            r12.onSizeChange()
        L_0x0455:
            r2 = 34
            float r2 = r1.getDimension(r2, r3)
            float r4 = r12.iconEndPadding
            int r4 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x0475
            float r4 = r12.calculateChipIconWidth()
            r12.iconEndPadding = r2
            float r2 = r12.calculateChipIconWidth()
            r12.invalidateSelf()
            int r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r2 == 0) goto L_0x0475
            r12.onSizeChange()
        L_0x0475:
            r2 = 41
            float r2 = r1.getDimension(r2, r3)
            float r4 = r12.textStartPadding
            int r4 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x0489
            r12.textStartPadding = r2
            r12.invalidateSelf()
            r12.onSizeChange()
        L_0x0489:
            r2 = 40
            float r2 = r1.getDimension(r2, r3)
            float r4 = r12.textEndPadding
            int r4 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x049d
            r12.textEndPadding = r2
            r12.invalidateSelf()
            r12.onSizeChange()
        L_0x049d:
            r2 = 29
            float r2 = r1.getDimension(r2, r3)
            float r4 = r12.closeIconStartPadding
            int r4 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x04b7
            r12.closeIconStartPadding = r2
            r12.invalidateSelf()
            boolean r2 = r12.showsCloseIcon()
            if (r2 == 0) goto L_0x04b7
            r12.onSizeChange()
        L_0x04b7:
            r2 = 27
            float r2 = r1.getDimension(r2, r3)
            float r4 = r12.closeIconEndPadding
            int r4 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x04d1
            r12.closeIconEndPadding = r2
            r12.invalidateSelf()
            boolean r2 = r12.showsCloseIcon()
            if (r2 == 0) goto L_0x04d1
            r12.onSizeChange()
        L_0x04d1:
            r2 = 13
            float r2 = r1.getDimension(r2, r3)
            float r3 = r12.chipEndPadding
            int r3 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1))
            if (r3 == 0) goto L_0x04e5
            r12.chipEndPadding = r2
            r12.invalidateSelf()
            r12.onSizeChange()
        L_0x04e5:
            r2 = 4
            r3 = 2147483647(0x7fffffff, float:NaN)
            int r2 = r1.getDimensionPixelSize(r2, r3)
            r12.maxWidth = r2
            r1.recycle()
            int[] r6 = new int[r14]
            r10 = 2132018804(0x7f140674, float:1.9675925E38)
            com.google.android.material.internal.ThemeEnforcement.checkCompatibleTheme(r9, r7, r8, r10)
            r1 = r9
            r2 = r18
            r3 = r13
            r4 = r19
            r5 = r10
            r11 = 0
            com.google.android.material.internal.ThemeEnforcement.checkTextAppearance(r1, r2, r3, r4, r5, r6)
            android.content.res.TypedArray r1 = r9.obtainStyledAttributes(r7, r13, r8, r10)
            r2 = 32
            boolean r2 = r1.getBoolean(r2, r14)
            r0.ensureMinTouchTargetSize = r2
            android.content.Context r2 = r16.getContext()
            r3 = 48
            float r2 = com.google.android.material.internal.ViewUtils.dpToPx(r3, r2)
            double r2 = (double) r2
            double r2 = java.lang.Math.ceil(r2)
            float r2 = (float) r2
            r3 = 20
            float r2 = r1.getDimension(r3, r2)
            double r2 = (double) r2
            double r2 = java.lang.Math.ceil(r2)
            int r2 = (int) r2
            r0.minTouchTargetSize = r2
            r1.recycle()
            com.google.android.material.chip.ChipDrawable r1 = r0.chipDrawable
            if (r1 == r12) goto L_0x054f
            if (r1 == 0) goto L_0x053f
            java.lang.ref.WeakReference r2 = new java.lang.ref.WeakReference
            r2.<init>(r11)
            r1.delegate = r2
        L_0x053f:
            r0.chipDrawable = r12
            r12.shouldDrawText = r14
            java.lang.ref.WeakReference r1 = new java.lang.ref.WeakReference
            r1.<init>(r0)
            r12.delegate = r1
            int r1 = r0.minTouchTargetSize
            r0.ensureAccessibleTouchTarget(r1)
        L_0x054f:
            java.util.WeakHashMap r1 = androidx.core.view.ViewCompat.sViewPropertyAnimatorMap
            float r1 = androidx.core.view.ViewCompat.Api21Impl.getElevation(r16)
            r12.setElevation(r1)
            int[] r6 = new int[r14]
            r10 = 2132018804(0x7f140674, float:1.9675925E38)
            com.google.android.material.internal.ThemeEnforcement.checkCompatibleTheme(r9, r7, r8, r10)
            r1 = r9
            r2 = r18
            r3 = r13
            r4 = r19
            r5 = r10
            com.google.android.material.internal.ThemeEnforcement.checkTextAppearance(r1, r2, r3, r4, r5, r6)
            android.content.res.TypedArray r1 = r9.obtainStyledAttributes(r7, r13, r8, r10)
            r2 = 37
            boolean r2 = r1.hasValue(r2)
            r1.recycle()
            com.google.android.material.chip.Chip$ChipTouchHelper r1 = new com.google.android.material.chip.Chip$ChipTouchHelper
            r1.<init>(r0)
            boolean r1 = r16.hasCloseIcon()
            if (r1 == 0) goto L_0x0588
            com.google.android.material.chip.ChipDrawable r1 = r0.chipDrawable
            if (r1 == 0) goto L_0x0588
            boolean r1 = r1.closeIconVisible
        L_0x0588:
            androidx.core.view.ViewCompat.setAccessibilityDelegate(r0, r11)
            if (r2 != 0) goto L_0x0595
            com.google.android.material.chip.Chip$2 r1 = new com.google.android.material.chip.Chip$2
            r1.<init>()
            r0.setOutlineProvider(r1)
        L_0x0595:
            boolean r1 = r0.deferredCheckedValue
            r0.setChecked(r1)
            java.lang.CharSequence r1 = r12.text
            r0.setText(r1)
            android.text.TextUtils$TruncateAt r1 = r12.truncateAt
            r0.setEllipsize(r1)
            r16.updateTextPaintDrawState()
            com.google.android.material.chip.ChipDrawable r1 = r0.chipDrawable
            boolean r1 = r1.shouldDrawText
            if (r1 != 0) goto L_0x05b4
            r1 = 1
            r0.setLines(r1)
            r0.setHorizontallyScrolling(r1)
        L_0x05b4:
            r1 = 8388627(0x800013, float:1.175497E-38)
            r0.setGravity(r1)
            r16.updatePaddingInternal()
            boolean r1 = r0.ensureMinTouchTargetSize
            if (r1 == 0) goto L_0x05c6
            int r1 = r0.minTouchTargetSize
            r0.setMinHeight(r1)
        L_0x05c6:
            int r1 = r16.getLayoutDirection()
            r0.lastLayoutDirection = r1
            com.google.android.material.chip.Chip$$ExternalSyntheticLambda0 r1 = new com.google.android.material.chip.Chip$$ExternalSyntheticLambda0
            r1.<init>(r0)
            super.setOnCheckedChangeListener(r1)
            return
        L_0x05d5:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            java.lang.String r1 = "Chip does not support multi-line text"
            r0.<init>(r1)
            throw r0
        L_0x05dd:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            r0.<init>(r4)
            throw r0
        L_0x05e3:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            r0.<init>(r4)
            throw r0
        L_0x05e9:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            java.lang.String r1 = "Please set start drawable using R.attr#chipIcon."
            r0.<init>(r1)
            throw r0
        L_0x05f1:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            java.lang.String r1 = "Please set left drawable using R.attr#chipIcon."
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.chip.Chip.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public final void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        } else if (drawable3 == null) {
            super.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        } else {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
    }

    public final void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set left drawable using R.attr#chipIcon.");
        } else if (drawable3 == null) {
            super.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        } else {
            throw new UnsupportedOperationException("Please set right drawable using R.attr#closeIcon.");
        }
    }

    public final void setTextAppearance(int i) {
        super.setTextAppearance(i);
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setTextAppearance(new TextAppearance(chipDrawable2.context, i));
        }
        updateTextPaintDrawState();
    }
}
