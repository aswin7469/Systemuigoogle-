package com.google.android.material.button;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Layout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.CompoundButton;
import androidx.appcompat.app.WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatBackgroundHelper;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.view.ViewCompat;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.WeakHashMap;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class MaterialButton extends AppCompatButton implements Checkable, Shapeable {
    public static final int[] CHECKABLE_STATE_SET = {16842911};
    public static final int[] CHECKED_STATE_SET = {16842912};
    public boolean broadcasting;
    public boolean checked;
    public Drawable icon;
    public final int iconGravity;
    public int iconLeft;
    public final int iconPadding;
    public final int iconSize;
    public final ColorStateList iconTint;
    public final PorterDuff.Mode iconTintMode;
    public int iconTop;
    public final MaterialButtonHelper materialButtonHelper;
    public final LinkedHashSet onCheckedChangeListeners;
    public MaterialButtonToggleGroup.PressedStateTracker onPressedChangeListenerInternal;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class SavedState extends AbsSavedState {
        public static final Parcelable.Creator CREATOR = new Object();
        public boolean checked;

        /* renamed from: com.google.android.material.button.MaterialButton$SavedState$1  reason: invalid class name */
        /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
        public final class AnonymousClass1 implements Parcelable.ClassLoaderCreator {
            public final Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            public final Object[] newArray(int i) {
                return new SavedState[i];
            }

            public final Object createFromParcel(Parcel parcel) {
                return new SavedState(parcel, (ClassLoader) null);
            }
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            if (classLoader == null) {
                SavedState.class.getClassLoader();
            }
            this.checked = parcel.readInt() != 1 ? false : true;
        }

        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.checked ? 1 : 0);
        }
    }

    public MaterialButton(Context context) {
        this(context, (AttributeSet) null);
    }

    public final ColorStateList getBackgroundTintList() {
        if (isUsingOriginalBackground()) {
            return this.materialButtonHelper.backgroundTint;
        }
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            return appCompatBackgroundHelper.getSupportBackgroundTintList();
        }
        return null;
    }

    public final PorterDuff.Mode getBackgroundTintMode() {
        if (isUsingOriginalBackground()) {
            return this.materialButtonHelper.backgroundTintMode;
        }
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            return appCompatBackgroundHelper.getSupportBackgroundTintMode();
        }
        return null;
    }

    public final boolean isCheckable() {
        MaterialButtonHelper materialButtonHelper2 = this.materialButtonHelper;
        if (materialButtonHelper2 == null || !materialButtonHelper2.checkable) {
            return false;
        }
        return true;
    }

    public final boolean isChecked() {
        return this.checked;
    }

    public final boolean isUsingOriginalBackground() {
        MaterialButtonHelper materialButtonHelper2 = this.materialButtonHelper;
        if (materialButtonHelper2 == null || materialButtonHelper2.backgroundOverwritten) {
            return false;
        }
        return true;
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isUsingOriginalBackground()) {
            MaterialShapeUtils.setParentAbsoluteElevation(this, this.materialButtonHelper.getMaterialShapeDrawable(false));
        }
    }

    public final int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 2);
        if (isCheckable()) {
            Button.mergeDrawableStates(onCreateDrawableState, CHECKABLE_STATE_SET);
        }
        if (this.checked) {
            Button.mergeDrawableStates(onCreateDrawableState, CHECKED_STATE_SET);
        }
        return onCreateDrawableState;
    }

    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        Class cls;
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (isCheckable()) {
            cls = CompoundButton.class;
        } else {
            cls = Button.class;
        }
        accessibilityEvent.setClassName(cls.getName());
        accessibilityEvent.setChecked(this.checked);
    }

    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        Class cls;
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        if (isCheckable()) {
            cls = CompoundButton.class;
        } else {
            cls = Button.class;
        }
        accessibilityNodeInfo.setClassName(cls.getName());
        accessibilityNodeInfo.setCheckable(isCheckable());
        accessibilityNodeInfo.setChecked(this.checked);
        accessibilityNodeInfo.setClickable(isClickable());
    }

    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        updateIconPosition(getMeasuredWidth(), getMeasuredHeight());
    }

    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.mSuperState);
        setChecked(savedState.checked);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.os.Parcelable, androidx.customview.view.AbsSavedState, com.google.android.material.button.MaterialButton$SavedState] */
    public final Parcelable onSaveInstanceState() {
        ? absSavedState = new AbsSavedState(super.onSaveInstanceState());
        absSavedState.checked = this.checked;
        return absSavedState;
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        updateIconPosition(getMeasuredWidth(), getMeasuredHeight());
    }

    public final boolean performClick() {
        if (this.materialButtonHelper.toggleCheckedStateOnClick) {
            toggle();
        }
        return super.performClick();
    }

    public final void refreshDrawableState() {
        super.refreshDrawableState();
        if (this.icon != null) {
            if (this.icon.setState(getDrawableState())) {
                invalidate();
            }
        }
    }

    public final void resetIconDrawable() {
        int i = this.iconGravity;
        boolean z = true;
        if (!(i == 1 || i == 2)) {
            z = false;
        }
        if (z) {
            setCompoundDrawablesRelative(this.icon, (Drawable) null, (Drawable) null, (Drawable) null);
        } else if (i == 3 || i == 4) {
            setCompoundDrawablesRelative((Drawable) null, (Drawable) null, this.icon, (Drawable) null);
        } else if (i == 16 || i == 32) {
            setCompoundDrawablesRelative((Drawable) null, this.icon, (Drawable) null, (Drawable) null);
        }
    }

    public final void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    public final void setBackgroundColor(int i) {
        if (isUsingOriginalBackground()) {
            MaterialButtonHelper materialButtonHelper2 = this.materialButtonHelper;
            if (materialButtonHelper2.getMaterialShapeDrawable(false) != null) {
                materialButtonHelper2.getMaterialShapeDrawable(false).setTint(i);
                return;
            }
            return;
        }
        super.setBackgroundColor(i);
    }

    public final void setBackgroundDrawable(Drawable drawable) {
        if (!isUsingOriginalBackground()) {
            super.setBackgroundDrawable(drawable);
        } else if (drawable != getBackground()) {
            Log.w("MaterialButton", "MaterialButton manages its own background to control elevation, shape, color and states. Consider using backgroundTint, shapeAppearance and other attributes where available. A custom background will ignore these attributes and you should consider handling interaction states such as pressed, focused and disabled");
            MaterialButtonHelper materialButtonHelper2 = this.materialButtonHelper;
            materialButtonHelper2.backgroundOverwritten = true;
            ColorStateList colorStateList = materialButtonHelper2.backgroundTint;
            MaterialButton materialButton = materialButtonHelper2.materialButton;
            materialButton.setSupportBackgroundTintList(colorStateList);
            materialButton.setSupportBackgroundTintMode(materialButtonHelper2.backgroundTintMode);
            super.setBackgroundDrawable(drawable);
        } else {
            getBackground().setState(drawable.getState());
        }
    }

    public final void setBackgroundResource(int i) {
        Drawable drawable;
        if (i != 0) {
            drawable = AppCompatResources.getDrawable(i, getContext());
        } else {
            drawable = null;
        }
        setBackgroundDrawable(drawable);
    }

    public final void setBackgroundTintList(ColorStateList colorStateList) {
        setSupportBackgroundTintList(colorStateList);
    }

    public final void setBackgroundTintMode(PorterDuff.Mode mode) {
        setSupportBackgroundTintMode(mode);
    }

    public final void setChecked(boolean z) {
        if (isCheckable() && isEnabled() && this.checked != z) {
            this.checked = z;
            refreshDrawableState();
            if (getParent() instanceof MaterialButtonToggleGroup) {
                MaterialButtonToggleGroup materialButtonToggleGroup = (MaterialButtonToggleGroup) getParent();
                boolean z2 = this.checked;
                if (!materialButtonToggleGroup.skipCheckedStateTracker) {
                    materialButtonToggleGroup.checkInternal(getId(), z2);
                }
            }
            if (!this.broadcasting) {
                this.broadcasting = true;
                Iterator it = this.onCheckedChangeListeners.iterator();
                if (!it.hasNext()) {
                    this.broadcasting = false;
                } else {
                    WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(it.next());
                    throw null;
                }
            }
        }
    }

    public final void setElevation(float f) {
        super.setElevation(f);
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.getMaterialShapeDrawable(false).setElevation(f);
        }
    }

    public final void setInternalBackground(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
    }

    public final void setPressed(boolean z) {
        MaterialButtonToggleGroup.PressedStateTracker pressedStateTracker = this.onPressedChangeListenerInternal;
        if (pressedStateTracker != null) {
            MaterialButtonToggleGroup.this.invalidate();
        }
        super.setPressed(z);
    }

    public final void setShapeAppearanceModel(ShapeAppearanceModel shapeAppearanceModel) {
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setShapeAppearanceModel(shapeAppearanceModel);
            return;
        }
        throw new IllegalStateException("Attempted to set ShapeAppearanceModel on a MaterialButton which has an overwritten background.");
    }

    public final void setSupportBackgroundTintList(ColorStateList colorStateList) {
        if (isUsingOriginalBackground()) {
            MaterialButtonHelper materialButtonHelper2 = this.materialButtonHelper;
            if (materialButtonHelper2.backgroundTint != colorStateList) {
                materialButtonHelper2.backgroundTint = colorStateList;
                if (materialButtonHelper2.getMaterialShapeDrawable(false) != null) {
                    materialButtonHelper2.getMaterialShapeDrawable(false).setTintList(materialButtonHelper2.backgroundTint);
                    return;
                }
                return;
            }
            return;
        }
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.setSupportBackgroundTintList(colorStateList);
        }
    }

    public final void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        if (isUsingOriginalBackground()) {
            MaterialButtonHelper materialButtonHelper2 = this.materialButtonHelper;
            if (materialButtonHelper2.backgroundTintMode != mode) {
                materialButtonHelper2.backgroundTintMode = mode;
                if (materialButtonHelper2.getMaterialShapeDrawable(false) != null && materialButtonHelper2.backgroundTintMode != null) {
                    materialButtonHelper2.getMaterialShapeDrawable(false).setTintMode(materialButtonHelper2.backgroundTintMode);
                    return;
                }
                return;
            }
            return;
        }
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.setSupportBackgroundTintMode(mode);
        }
    }

    public final void setTextAlignment(int i) {
        super.setTextAlignment(i);
        updateIconPosition(getMeasuredWidth(), getMeasuredHeight());
    }

    public final void toggle() {
        setChecked(!this.checked);
    }

    public final void updateIcon(boolean z) {
        Drawable drawable = this.icon;
        if (drawable != null) {
            Drawable mutate = drawable.mutate();
            this.icon = mutate;
            mutate.setTintList(this.iconTint);
            PorterDuff.Mode mode = this.iconTintMode;
            if (mode != null) {
                this.icon.setTintMode(mode);
            }
            int i = this.iconSize;
            if (i == 0) {
                i = this.icon.getIntrinsicWidth();
            }
            int i2 = this.iconSize;
            if (i2 == 0) {
                i2 = this.icon.getIntrinsicHeight();
            }
            Drawable drawable2 = this.icon;
            int i3 = this.iconLeft;
            int i4 = this.iconTop;
            drawable2.setBounds(i3, i4, i + i3, i2 + i4);
            this.icon.setVisible(true, z);
        }
        if (z) {
            resetIconDrawable();
            return;
        }
        Drawable[] compoundDrawablesRelative = getCompoundDrawablesRelative();
        Drawable drawable3 = compoundDrawablesRelative[0];
        Drawable drawable4 = compoundDrawablesRelative[1];
        Drawable drawable5 = compoundDrawablesRelative[2];
        int i5 = this.iconGravity;
        if (((i5 == 1 || i5 == 2) && drawable3 != this.icon) || (((i5 == 3 || i5 == 4) && drawable5 != this.icon) || ((i5 == 16 || i5 == 32) && drawable4 != this.icon))) {
            resetIconDrawable();
        }
    }

    public final void updateIconPosition(int i, int i2) {
        boolean z;
        Layout.Alignment alignment;
        boolean z2;
        int i3;
        if (this.icon != null && getLayout() != null) {
            int i4 = this.iconGravity;
            boolean z3 = true;
            if (i4 == 1 || i4 == 2) {
                z = true;
            } else {
                z = false;
            }
            if (z || i4 == 3 || i4 == 4) {
                this.iconTop = 0;
                int textAlignment = getTextAlignment();
                if (textAlignment == 1) {
                    int gravity = getGravity() & 8388615;
                    if (gravity == 1) {
                        alignment = Layout.Alignment.ALIGN_CENTER;
                    } else if (gravity == 5 || gravity == 8388613) {
                        alignment = Layout.Alignment.ALIGN_OPPOSITE;
                    } else {
                        alignment = Layout.Alignment.ALIGN_NORMAL;
                    }
                } else if (textAlignment == 6 || textAlignment == 3) {
                    alignment = Layout.Alignment.ALIGN_OPPOSITE;
                } else if (textAlignment != 4) {
                    alignment = Layout.Alignment.ALIGN_NORMAL;
                } else {
                    alignment = Layout.Alignment.ALIGN_CENTER;
                }
                int i5 = this.iconGravity;
                if (i5 == 1 || i5 == 3 || ((i5 == 2 && alignment == Layout.Alignment.ALIGN_NORMAL) || (i5 == 4 && alignment == Layout.Alignment.ALIGN_OPPOSITE))) {
                    this.iconLeft = 0;
                    updateIcon(false);
                    return;
                }
                int i6 = this.iconSize;
                if (i6 == 0) {
                    i6 = this.icon.getIntrinsicWidth();
                }
                int lineCount = getLineCount();
                int i7 = 0;
                for (int i8 = 0; i8 < lineCount; i8++) {
                    CharSequence subSequence = getText().subSequence(getLayout().getLineStart(i8), getLayout().getLineEnd(i8));
                    TextPaint paint = getPaint();
                    String charSequence = subSequence.toString();
                    if (getTransformationMethod() != null) {
                        charSequence = getTransformationMethod().getTransformation(charSequence, this).toString();
                    }
                    i7 = Math.max(i7, Math.min((int) paint.measureText(charSequence), getLayout().getEllipsizedWidth()));
                }
                WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                int paddingEnd = ((((i - i7) - ViewCompat.Api17Impl.getPaddingEnd(this)) - i6) - this.iconPadding) - ViewCompat.Api17Impl.getPaddingStart(this);
                if (alignment == Layout.Alignment.ALIGN_CENTER) {
                    paddingEnd /= 2;
                }
                if (ViewCompat.Api17Impl.getLayoutDirection(this) == 1) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (this.iconGravity != 4) {
                    z3 = false;
                }
                if (z2 != z3) {
                    paddingEnd = -paddingEnd;
                }
                if (this.iconLeft != paddingEnd) {
                    this.iconLeft = paddingEnd;
                    updateIcon(false);
                }
            } else if (i4 == 16 || i4 == 32) {
                this.iconLeft = 0;
                if (i4 == 16) {
                    this.iconTop = 0;
                    updateIcon(false);
                    return;
                }
                int i9 = this.iconSize;
                if (i9 == 0) {
                    i9 = this.icon.getIntrinsicHeight();
                }
                if (getLineCount() > 1) {
                    i3 = getLayout().getHeight();
                } else {
                    TextPaint paint2 = getPaint();
                    String charSequence2 = getText().toString();
                    if (getTransformationMethod() != null) {
                        charSequence2 = getTransformationMethod().getTransformation(charSequence2, this).toString();
                    }
                    Rect rect = new Rect();
                    paint2.getTextBounds(charSequence2, 0, charSequence2.length(), rect);
                    i3 = Math.min(rect.height(), getLayout().getHeight());
                }
                int max = Math.max(0, (((((i2 - i3) - getPaddingTop()) - i9) - this.iconPadding) - getPaddingBottom()) / 2);
                if (this.iconTop != max) {
                    this.iconTop = max;
                    updateIcon(false);
                }
            }
        }
    }

    public MaterialButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 2130969523);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MaterialButton(android.content.Context r21, android.util.AttributeSet r22, int r23) {
        /*
            r20 = this;
            r0 = r20
            r7 = r22
            r8 = r23
            r9 = 2
            r10 = 2132018784(0x7f140660, float:1.9675884E38)
            r1 = r21
            android.content.Context r1 = com.google.android.material.theme.overlay.MaterialThemeOverlay.wrap(r1, r7, r8, r10)
            r0.<init>(r1, r7, r8)
            java.util.LinkedHashSet r1 = new java.util.LinkedHashSet
            r1.<init>()
            r0.onCheckedChangeListeners = r1
            r11 = 0
            r0.checked = r11
            r0.broadcasting = r11
            android.content.Context r12 = r20.getContext()
            int[] r3 = com.google.android.material.R$styleable.MaterialButton
            r5 = 2132018784(0x7f140660, float:1.9675884E38)
            int[] r6 = new int[r11]
            r1 = r12
            r2 = r22
            r4 = r23
            android.content.res.TypedArray r1 = com.google.android.material.internal.ThemeEnforcement.obtainStyledAttributes(r1, r2, r3, r4, r5, r6)
            r2 = 12
            int r2 = r1.getDimensionPixelSize(r2, r11)
            r0.iconPadding = r2
            r3 = 15
            r4 = -1
            int r3 = r1.getInt(r3, r4)
            android.graphics.PorterDuff$Mode r5 = android.graphics.PorterDuff.Mode.SRC_IN
            android.graphics.PorterDuff$Mode r3 = com.google.android.material.internal.ViewUtils.parseTintMode(r3, r5)
            r0.iconTintMode = r3
            android.content.Context r3 = r20.getContext()
            r6 = 14
            android.content.res.ColorStateList r3 = com.google.android.material.resources.MaterialResources.getColorStateList((android.content.Context) r3, (android.content.res.TypedArray) r1, (int) r6)
            r0.iconTint = r3
            android.content.Context r3 = r20.getContext()
            r6 = 10
            android.graphics.drawable.Drawable r3 = com.google.android.material.resources.MaterialResources.getDrawable(r3, r1, r6)
            r0.icon = r3
            r3 = 11
            r6 = 1
            int r3 = r1.getInteger(r3, r6)
            r0.iconGravity = r3
            r3 = 13
            int r3 = r1.getDimensionPixelSize(r3, r11)
            r0.iconSize = r3
            com.google.android.material.shape.ShapeAppearanceModel$Builder r3 = com.google.android.material.shape.ShapeAppearanceModel.builder((android.content.Context) r12, (android.util.AttributeSet) r7, (int) r8, (int) r10)
            com.google.android.material.shape.ShapeAppearanceModel r3 = r3.build()
            com.google.android.material.button.MaterialButtonHelper r7 = new com.google.android.material.button.MaterialButtonHelper
            r7.<init>(r0, r3)
            r0.materialButtonHelper = r7
            int r3 = r1.getDimensionPixelOffset(r6, r11)
            r7.insetLeft = r3
            int r3 = r1.getDimensionPixelOffset(r9, r11)
            r7.insetRight = r3
            r3 = 3
            int r3 = r1.getDimensionPixelOffset(r3, r11)
            r7.insetTop = r3
            r3 = 4
            int r3 = r1.getDimensionPixelOffset(r3, r11)
            r7.insetBottom = r3
            r3 = 8
            boolean r8 = r1.hasValue(r3)
            if (r8 == 0) goto L_0x00b9
            int r3 = r1.getDimensionPixelSize(r3, r4)
            com.google.android.material.shape.ShapeAppearanceModel r8 = r7.shapeAppearanceModel
            float r3 = (float) r3
            com.google.android.material.shape.ShapeAppearanceModel$Builder r8 = r8.toBuilder()
            r8.setAllCornerSizes(r3)
            com.google.android.material.shape.ShapeAppearanceModel r3 = r8.build()
            r7.setShapeAppearanceModel(r3)
        L_0x00b9:
            r3 = 20
            int r3 = r1.getDimensionPixelSize(r3, r11)
            r7.strokeWidth = r3
            r3 = 7
            int r3 = r1.getInt(r3, r4)
            android.graphics.PorterDuff$Mode r3 = com.google.android.material.internal.ViewUtils.parseTintMode(r3, r5)
            r7.backgroundTintMode = r3
            android.content.Context r3 = r20.getContext()
            r5 = 6
            android.content.res.ColorStateList r3 = com.google.android.material.resources.MaterialResources.getColorStateList((android.content.Context) r3, (android.content.res.TypedArray) r1, (int) r5)
            r7.backgroundTint = r3
            android.content.Context r3 = r20.getContext()
            r5 = 19
            android.content.res.ColorStateList r3 = com.google.android.material.resources.MaterialResources.getColorStateList((android.content.Context) r3, (android.content.res.TypedArray) r1, (int) r5)
            r7.strokeColor = r3
            android.content.Context r3 = r20.getContext()
            r5 = 16
            android.content.res.ColorStateList r3 = com.google.android.material.resources.MaterialResources.getColorStateList((android.content.Context) r3, (android.content.res.TypedArray) r1, (int) r5)
            r7.rippleColor = r3
            r3 = 5
            boolean r3 = r1.getBoolean(r3, r11)
            r7.checkable = r3
            r3 = 9
            int r3 = r1.getDimensionPixelSize(r3, r11)
            r7.elevation = r3
            r3 = 21
            boolean r3 = r1.getBoolean(r3, r6)
            r7.toggleCheckedStateOnClick = r3
            java.util.WeakHashMap r3 = androidx.core.view.ViewCompat.sViewPropertyAnimatorMap
            int r3 = androidx.core.view.ViewCompat.Api17Impl.getPaddingStart(r20)
            int r5 = r20.getPaddingTop()
            int r8 = androidx.core.view.ViewCompat.Api17Impl.getPaddingEnd(r20)
            int r10 = r20.getPaddingBottom()
            boolean r12 = r1.hasValue(r11)
            if (r12 == 0) goto L_0x012d
            r7.backgroundOverwritten = r6
            android.content.res.ColorStateList r4 = r7.backgroundTint
            r0.setSupportBackgroundTintList(r4)
            android.graphics.PorterDuff$Mode r4 = r7.backgroundTintMode
            r0.setSupportBackgroundTintMode(r4)
            r4 = r11
            goto L_0x01d2
        L_0x012d:
            com.google.android.material.shape.MaterialShapeDrawable r12 = new com.google.android.material.shape.MaterialShapeDrawable
            com.google.android.material.shape.ShapeAppearanceModel r13 = r7.shapeAppearanceModel
            r12.<init>((com.google.android.material.shape.ShapeAppearanceModel) r13)
            android.content.Context r13 = r20.getContext()
            r12.initializeElevationOverlay(r13)
            android.content.res.ColorStateList r13 = r7.backgroundTint
            r12.setTintList(r13)
            android.graphics.PorterDuff$Mode r13 = r7.backgroundTintMode
            if (r13 == 0) goto L_0x0147
            r12.setTintMode(r13)
        L_0x0147:
            int r13 = r7.strokeWidth
            float r13 = (float) r13
            android.content.res.ColorStateList r14 = r7.strokeColor
            com.google.android.material.shape.MaterialShapeDrawable$MaterialShapeDrawableState r15 = r12.drawableState
            r15.strokeWidth = r13
            r12.invalidateSelf()
            r12.setStrokeColor(r14)
            com.google.android.material.shape.MaterialShapeDrawable r13 = new com.google.android.material.shape.MaterialShapeDrawable
            com.google.android.material.shape.ShapeAppearanceModel r14 = r7.shapeAppearanceModel
            r13.<init>((com.google.android.material.shape.ShapeAppearanceModel) r14)
            r13.setTint(r11)
            int r14 = r7.strokeWidth
            float r14 = (float) r14
            boolean r15 = r7.shouldDrawSurfaceColorStroke
            if (r15 == 0) goto L_0x016f
            r15 = 2130968887(0x7f040137, float:1.754644E38)
            int r15 = com.google.android.material.color.MaterialColors.getColor(r0, r15)
            goto L_0x0170
        L_0x016f:
            r15 = r11
        L_0x0170:
            com.google.android.material.shape.MaterialShapeDrawable$MaterialShapeDrawableState r6 = r13.drawableState
            r6.strokeWidth = r14
            r13.invalidateSelf()
            android.content.res.ColorStateList r6 = android.content.res.ColorStateList.valueOf(r15)
            r13.setStrokeColor(r6)
            com.google.android.material.shape.MaterialShapeDrawable r6 = new com.google.android.material.shape.MaterialShapeDrawable
            com.google.android.material.shape.ShapeAppearanceModel r14 = r7.shapeAppearanceModel
            r6.<init>((com.google.android.material.shape.ShapeAppearanceModel) r14)
            r7.maskDrawable = r6
            r6.setTint(r4)
            android.graphics.drawable.RippleDrawable r4 = new android.graphics.drawable.RippleDrawable
            android.content.res.ColorStateList r6 = r7.rippleColor
            android.content.res.ColorStateList r6 = com.google.android.material.ripple.RippleUtils.sanitizeRippleDrawableColor(r6)
            android.graphics.drawable.LayerDrawable r15 = new android.graphics.drawable.LayerDrawable
            android.graphics.drawable.Drawable[] r9 = new android.graphics.drawable.Drawable[r9]
            r9[r11] = r13
            r13 = 1
            r9[r13] = r12
            r15.<init>(r9)
            android.graphics.drawable.InsetDrawable r9 = new android.graphics.drawable.InsetDrawable
            int r12 = r7.insetLeft
            int r14 = r7.insetTop
            int r13 = r7.insetRight
            int r11 = r7.insetBottom
            r17 = r14
            r14 = r9
            r16 = r12
            r18 = r13
            r19 = r11
            r14.<init>(r15, r16, r17, r18, r19)
            com.google.android.material.shape.MaterialShapeDrawable r11 = r7.maskDrawable
            r4.<init>(r6, r9, r11)
            r7.rippleDrawable = r4
            r0.setInternalBackground(r4)
            r4 = 0
            com.google.android.material.shape.MaterialShapeDrawable r6 = r7.getMaterialShapeDrawable(r4)
            if (r6 == 0) goto L_0x01d2
            int r9 = r7.elevation
            float r9 = (float) r9
            r6.setElevation(r9)
            int[] r9 = r20.getDrawableState()
            r6.setState(r9)
        L_0x01d2:
            int r6 = r7.insetLeft
            int r3 = r3 + r6
            int r6 = r7.insetTop
            int r5 = r5 + r6
            int r6 = r7.insetRight
            int r8 = r8 + r6
            int r6 = r7.insetBottom
            int r10 = r10 + r6
            androidx.core.view.ViewCompat.Api17Impl.setPaddingRelative(r0, r3, r5, r8, r10)
            r1.recycle()
            r0.setCompoundDrawablePadding(r2)
            android.graphics.drawable.Drawable r1 = r0.icon
            if (r1 == 0) goto L_0x01ed
            r11 = 1
            goto L_0x01ee
        L_0x01ed:
            r11 = r4
        L_0x01ee:
            r0.updateIcon(r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.button.MaterialButton.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }
}
