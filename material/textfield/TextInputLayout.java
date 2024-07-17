package com.google.android.material.textfield;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatDrawableManager;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.DrawableUtils;
import androidx.compose.runtime.Anchor$$ExternalSyntheticOutline0;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.text.BidiFormatter;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.DifferentialMotionFlingController$$ExternalSyntheticLambda0;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.view.AbsSavedState;
import androidx.transition.Fade;
import androidx.transition.TransitionManager;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.CollapsingTextHelper;
import com.google.android.material.internal.DescendantOffsetUtils;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.AbsoluteCornerSize;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.textfield.EndCompoundLayout;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.WeakHashMap;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class TextInputLayout extends LinearLayout {
    public static final int[][] EDIT_TEXT_BACKGROUND_RIPPLE_STATE = {new int[]{16842919}, new int[0]};
    public ValueAnimator animator;
    public boolean areCornerRadiiRtl;
    public MaterialShapeDrawable boxBackground;
    public boolean boxBackgroundApplied;
    public int boxBackgroundColor;
    public final int boxBackgroundMode;
    public int boxCollapsedPaddingTopPx;
    public final int boxLabelCutoutPaddingPx;
    public int boxStrokeColor;
    public final int boxStrokeWidthDefaultPx;
    public final int boxStrokeWidthFocusedPx;
    public int boxStrokeWidthPx;
    public MaterialShapeDrawable boxUnderlineDefault;
    public MaterialShapeDrawable boxUnderlineFocused;
    public final CollapsingTextHelper collapsingTextHelper;
    public final boolean counterEnabled;
    public final int counterMaxLength;
    public final int counterOverflowTextAppearance;
    public final ColorStateList counterOverflowTextColor;
    public boolean counterOverflowed;
    public final int counterTextAppearance;
    public final ColorStateList counterTextColor;
    public final AppCompatTextView counterView;
    public final int defaultFilledBackgroundColor;
    public ColorStateList defaultHintTextColor;
    public final int defaultStrokeColor;
    public final int disabledColor;
    public final int disabledFilledBackgroundColor;
    public EditText editText;
    public final LinkedHashSet editTextAttachedListeners;
    public Drawable endDummyDrawable;
    public int endDummyDrawableWidth;
    public final EndCompoundLayout endLayout;
    public final boolean expandedHintEnabled;
    public StateListDrawable filledDropDownMenuBackground;
    public final int focusedFilledBackgroundColor;
    public final int focusedStrokeColor;
    public final ColorStateList focusedTextColor;
    public CharSequence hint;
    public final boolean hintAnimationEnabled;
    public final boolean hintEnabled;
    public boolean hintExpanded;
    public final int hoveredFilledBackgroundColor;
    public final int hoveredStrokeColor;
    public boolean inDrawableStateChanged;
    public final IndicatorViewController indicatorViewController;
    public final FrameLayout inputFrame;
    public boolean isProvidingHint;
    public final DifferentialMotionFlingController$$ExternalSyntheticLambda0 lengthCounter;
    public int maxEms;
    public int maxWidth;
    public int minEms;
    public int minWidth;
    public Drawable originalEditTextEndDrawable;
    public CharSequence originalHint;
    public MaterialShapeDrawable outlinedDropDownMenuBackground;
    public boolean placeholderEnabled;
    public final Fade placeholderFadeIn;
    public final Fade placeholderFadeOut;
    public final CharSequence placeholderText;
    public final int placeholderTextAppearance;
    public final ColorStateList placeholderTextColor;
    public AppCompatTextView placeholderTextView;
    public boolean restoringSavedState;
    public ShapeAppearanceModel shapeAppearanceModel;
    public Drawable startDummyDrawable;
    public int startDummyDrawableWidth;
    public final StartCompoundLayout startLayout;
    public final ColorStateList strokeErrorColor;
    public final Rect tmpBoundsRect;
    public final Rect tmpRect;
    public final RectF tmpRectF;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class AccessibilityDelegate extends AccessibilityDelegateCompat {
        public final TextInputLayout layout;

        public AccessibilityDelegate(TextInputLayout textInputLayout) {
            this.layout = textInputLayout;
        }

        public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            Editable editable;
            CharSequence charSequence;
            CharSequence charSequence2;
            CharSequence charSequence3;
            String str;
            AppCompatTextView appCompatTextView;
            View.AccessibilityDelegate accessibilityDelegate = this.mOriginalDelegate;
            AccessibilityNodeInfo accessibilityNodeInfo = accessibilityNodeInfoCompat.mInfo;
            accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
            TextInputLayout textInputLayout = this.layout;
            EditText editText = textInputLayout.editText;
            CharSequence charSequence4 = null;
            if (editText != null) {
                editable = editText.getText();
            } else {
                editable = null;
            }
            if (textInputLayout.hintEnabled) {
                charSequence = textInputLayout.hint;
            } else {
                charSequence = null;
            }
            IndicatorViewController indicatorViewController = textInputLayout.indicatorViewController;
            if (indicatorViewController.errorEnabled) {
                charSequence2 = indicatorViewController.errorText;
            } else {
                charSequence2 = null;
            }
            if (textInputLayout.placeholderEnabled) {
                charSequence3 = textInputLayout.placeholderText;
            } else {
                charSequence3 = null;
            }
            int i = textInputLayout.counterMaxLength;
            if (textInputLayout.counterEnabled && textInputLayout.counterOverflowed && (appCompatTextView = textInputLayout.counterView) != null) {
                charSequence4 = appCompatTextView.getContentDescription();
            }
            boolean isEmpty = TextUtils.isEmpty(editable);
            boolean z = !isEmpty;
            boolean z2 = true;
            boolean z3 = !TextUtils.isEmpty(charSequence);
            boolean z4 = !textInputLayout.hintExpanded;
            boolean z5 = !TextUtils.isEmpty(charSequence2);
            if (!z5 && TextUtils.isEmpty(charSequence4)) {
                z2 = false;
            }
            if (z3) {
                str = charSequence.toString();
            } else {
                str = "";
            }
            StartCompoundLayout startCompoundLayout = textInputLayout.startLayout;
            if (startCompoundLayout.prefixTextView.getVisibility() == 0) {
                accessibilityNodeInfo.setLabelFor(startCompoundLayout.prefixTextView);
                accessibilityNodeInfo.setTraversalAfter(startCompoundLayout.prefixTextView);
            } else {
                accessibilityNodeInfo.setTraversalAfter(startCompoundLayout.startIconView);
            }
            if (z) {
                accessibilityNodeInfoCompat.setText(editable);
            } else if (!TextUtils.isEmpty(str)) {
                accessibilityNodeInfoCompat.setText(str);
                if (z4 && charSequence3 != null) {
                    accessibilityNodeInfoCompat.setText(str + ", " + charSequence3);
                }
            } else if (charSequence3 != null) {
                accessibilityNodeInfoCompat.setText(charSequence3);
            }
            if (!TextUtils.isEmpty(str)) {
                accessibilityNodeInfo.setHintText(str);
                accessibilityNodeInfo.setShowingHintText(isEmpty);
            }
            if (editable == null || editable.length() != i) {
                i = -1;
            }
            accessibilityNodeInfo.setMaxTextLength(i);
            if (z2) {
                if (!z5) {
                    charSequence2 = charSequence4;
                }
                accessibilityNodeInfo.setError(charSequence2);
            }
            AppCompatTextView appCompatTextView2 = textInputLayout.indicatorViewController.helperTextView;
            if (appCompatTextView2 != null) {
                accessibilityNodeInfo.setLabelFor(appCompatTextView2);
            }
            textInputLayout.endLayout.getEndIconDelegate().onInitializeAccessibilityNodeInfo(accessibilityNodeInfoCompat);
        }

        public final void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            super.onPopulateAccessibilityEvent(view, accessibilityEvent);
            this.layout.endLayout.getEndIconDelegate().onPopulateAccessibilityEvent(accessibilityEvent);
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class SavedState extends AbsSavedState {
        public static final Parcelable.Creator CREATOR = new Object();
        public CharSequence error;
        public boolean isEndIconChecked;

        /* renamed from: com.google.android.material.textfield.TextInputLayout$SavedState$1  reason: invalid class name */
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
            this.error = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.isEndIconChecked = parcel.readInt() != 1 ? false : true;
        }

        public final String toString() {
            return "TextInputLayout.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " error=" + this.error + "}";
        }

        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            TextUtils.writeToParcel(this.error, parcel, i);
            parcel.writeInt(this.isEndIconChecked ? 1 : 0);
        }
    }

    public TextInputLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public static void recursiveSetEnabled(ViewGroup viewGroup, boolean z) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            childAt.setEnabled(z);
            if (childAt instanceof ViewGroup) {
                recursiveSetEnabled((ViewGroup) childAt, z);
            }
        }
    }

    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (view instanceof EditText) {
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(layoutParams);
            layoutParams2.gravity = (layoutParams2.gravity & -113) | 16;
            this.inputFrame.addView(view, layoutParams2);
            this.inputFrame.setLayoutParams(layoutParams);
            updateInputLayoutMargins();
            EditText editText2 = (EditText) view;
            if (this.editText == null) {
                if (this.endLayout.endIconMode != 3 && !(editText2 instanceof TextInputEditText)) {
                    Log.i("TextInputLayout", "EditText added is not a TextInputEditText. Please switch to using that class instead.");
                }
                this.editText = editText2;
                int i2 = this.minEms;
                if (i2 != -1) {
                    this.minEms = i2;
                    if (!(editText2 == null || i2 == -1)) {
                        editText2.setMinEms(i2);
                    }
                } else {
                    int i3 = this.minWidth;
                    this.minWidth = i3;
                    if (!(editText2 == null || i3 == -1)) {
                        editText2.setMinWidth(i3);
                    }
                }
                int i4 = this.maxEms;
                if (i4 != -1) {
                    this.maxEms = i4;
                    EditText editText3 = this.editText;
                    if (!(editText3 == null || i4 == -1)) {
                        editText3.setMaxEms(i4);
                    }
                } else {
                    int i5 = this.maxWidth;
                    this.maxWidth = i5;
                    EditText editText4 = this.editText;
                    if (!(editText4 == null || i5 == -1)) {
                        editText4.setMaxWidth(i5);
                    }
                }
                this.boxBackgroundApplied = false;
                onApplyBoxBackgroundMode();
                AccessibilityDelegate accessibilityDelegate = new AccessibilityDelegate(this);
                EditText editText5 = this.editText;
                if (editText5 != null) {
                    ViewCompat.setAccessibilityDelegate(editText5, accessibilityDelegate);
                }
                CollapsingTextHelper collapsingTextHelper2 = this.collapsingTextHelper;
                Typeface typeface = this.editText.getTypeface();
                boolean collapsedTypefaceInternal = collapsingTextHelper2.setCollapsedTypefaceInternal(typeface);
                boolean expandedTypefaceInternal = collapsingTextHelper2.setExpandedTypefaceInternal(typeface);
                if (collapsedTypefaceInternal || expandedTypefaceInternal) {
                    collapsingTextHelper2.recalculate(false);
                }
                CollapsingTextHelper collapsingTextHelper3 = this.collapsingTextHelper;
                float textSize = this.editText.getTextSize();
                if (collapsingTextHelper3.expandedTextSize != textSize) {
                    collapsingTextHelper3.expandedTextSize = textSize;
                    collapsingTextHelper3.recalculate(false);
                }
                CollapsingTextHelper collapsingTextHelper4 = this.collapsingTextHelper;
                float letterSpacing = this.editText.getLetterSpacing();
                if (collapsingTextHelper4.expandedLetterSpacing != letterSpacing) {
                    collapsingTextHelper4.expandedLetterSpacing = letterSpacing;
                    collapsingTextHelper4.recalculate(false);
                }
                int gravity = this.editText.getGravity();
                CollapsingTextHelper collapsingTextHelper5 = this.collapsingTextHelper;
                int i6 = (gravity & -113) | 48;
                if (collapsingTextHelper5.collapsedTextGravity != i6) {
                    collapsingTextHelper5.collapsedTextGravity = i6;
                    collapsingTextHelper5.recalculate(false);
                }
                CollapsingTextHelper collapsingTextHelper6 = this.collapsingTextHelper;
                if (collapsingTextHelper6.expandedTextGravity != gravity) {
                    collapsingTextHelper6.expandedTextGravity = gravity;
                    collapsingTextHelper6.recalculate(false);
                }
                this.editText.addTextChangedListener(new TextWatcher() {
                    public final void afterTextChanged(Editable editable) {
                        TextInputLayout textInputLayout = TextInputLayout.this;
                        textInputLayout.updateLabelState(!textInputLayout.restoringSavedState, false);
                        TextInputLayout textInputLayout2 = TextInputLayout.this;
                        if (textInputLayout2.counterEnabled) {
                            textInputLayout2.updateCounter(editable);
                        }
                        TextInputLayout textInputLayout3 = TextInputLayout.this;
                        if (textInputLayout3.placeholderEnabled) {
                            textInputLayout3.updatePlaceholderText(editable);
                        }
                    }

                    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    }

                    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    }
                });
                if (this.defaultHintTextColor == null) {
                    this.defaultHintTextColor = this.editText.getHintTextColors();
                }
                if (this.hintEnabled) {
                    if (TextUtils.isEmpty(this.hint)) {
                        CharSequence hint2 = this.editText.getHint();
                        this.originalHint = hint2;
                        setHint(hint2);
                        this.editText.setHint((CharSequence) null);
                    }
                    this.isProvidingHint = true;
                }
                if (this.counterView != null) {
                    updateCounter(this.editText.getText());
                }
                updateEditTextBackground();
                this.indicatorViewController.adjustIndicatorPadding();
                this.startLayout.bringToFront();
                this.endLayout.bringToFront();
                Iterator it = this.editTextAttachedListeners.iterator();
                while (it.hasNext()) {
                    ((EndCompoundLayout.AnonymousClass2) it.next()).onEditTextAttached(this);
                }
                this.endLayout.updateSuffixTextViewPadding();
                if (!isEnabled()) {
                    editText2.setEnabled(false);
                }
                updateLabelState(false, true);
                return;
            }
            throw new IllegalArgumentException("We already have an EditText, can only have one");
        }
        super.addView(view, i, layoutParams);
    }

    public void animateToExpansionFraction(float f) {
        if (this.collapsingTextHelper.expandedFraction != f) {
            if (this.animator == null) {
                ValueAnimator valueAnimator = new ValueAnimator();
                this.animator = valueAnimator;
                valueAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
                this.animator.setDuration(167);
                this.animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                        TextInputLayout.this.collapsingTextHelper.setExpansionFraction(((Float) valueAnimator.getAnimatedValue()).floatValue());
                    }
                });
            }
            this.animator.setFloatValues(new float[]{this.collapsingTextHelper.expandedFraction, f});
            this.animator.start();
        }
    }

    public final void applyBoxAttributes() {
        ColorStateList colorStateList;
        int i;
        int i2;
        MaterialShapeDrawable materialShapeDrawable = this.boxBackground;
        if (materialShapeDrawable != null) {
            ShapeAppearanceModel shapeAppearanceModel2 = materialShapeDrawable.drawableState.shapeAppearanceModel;
            ShapeAppearanceModel shapeAppearanceModel3 = this.shapeAppearanceModel;
            if (shapeAppearanceModel2 != shapeAppearanceModel3) {
                materialShapeDrawable.setShapeAppearanceModel(shapeAppearanceModel3);
            }
            if (this.boxBackgroundMode == 2 && (i = this.boxStrokeWidthPx) > -1 && (i2 = this.boxStrokeColor) != 0) {
                MaterialShapeDrawable materialShapeDrawable2 = this.boxBackground;
                materialShapeDrawable2.drawableState.strokeWidth = (float) i;
                materialShapeDrawable2.invalidateSelf();
                materialShapeDrawable2.setStrokeColor(ColorStateList.valueOf(i2));
            }
            int i3 = this.boxBackgroundColor;
            if (this.boxBackgroundMode == 1) {
                i3 = ColorUtils.compositeColors(this.boxBackgroundColor, MaterialColors.getColor(getContext(), 2130968887, 0));
            }
            this.boxBackgroundColor = i3;
            this.boxBackground.setFillColor(ColorStateList.valueOf(i3));
            MaterialShapeDrawable materialShapeDrawable3 = this.boxUnderlineDefault;
            if (!(materialShapeDrawable3 == null || this.boxUnderlineFocused == null)) {
                if (this.boxStrokeWidthPx > -1 && this.boxStrokeColor != 0) {
                    if (this.editText.isFocused()) {
                        colorStateList = ColorStateList.valueOf(this.defaultStrokeColor);
                    } else {
                        colorStateList = ColorStateList.valueOf(this.boxStrokeColor);
                    }
                    materialShapeDrawable3.setFillColor(colorStateList);
                    this.boxUnderlineFocused.setFillColor(ColorStateList.valueOf(this.boxStrokeColor));
                }
                invalidate();
            }
            updateEditTextBoxBackgroundIfNeeded();
        }
    }

    public final int calculateLabelMarginTop() {
        float collapsedTextHeight;
        if (!this.hintEnabled) {
            return 0;
        }
        int i = this.boxBackgroundMode;
        if (i == 0) {
            collapsedTextHeight = this.collapsingTextHelper.getCollapsedTextHeight();
        } else if (i != 2) {
            return 0;
        } else {
            collapsedTextHeight = this.collapsingTextHelper.getCollapsedTextHeight() / 2.0f;
        }
        return (int) collapsedTextHeight;
    }

    public final boolean cutoutEnabled() {
        if (!this.hintEnabled || TextUtils.isEmpty(this.hint) || !(this.boxBackground instanceof CutoutDrawable)) {
            return false;
        }
        return true;
    }

    public boolean cutoutIsOpen() {
        if (!cutoutEnabled() || !(!((CutoutDrawable) this.boxBackground).cutoutBounds.isEmpty())) {
            return false;
        }
        return true;
    }

    public final void dispatchProvideAutofillStructure(ViewStructure viewStructure, int i) {
        CharSequence charSequence;
        EditText editText2 = this.editText;
        if (editText2 == null) {
            super.dispatchProvideAutofillStructure(viewStructure, i);
            return;
        }
        if (this.originalHint != null) {
            boolean z = this.isProvidingHint;
            this.isProvidingHint = false;
            CharSequence hint2 = editText2.getHint();
            this.editText.setHint(this.originalHint);
            try {
                super.dispatchProvideAutofillStructure(viewStructure, i);
            } finally {
                this.editText.setHint(hint2);
                this.isProvidingHint = z;
            }
        } else {
            viewStructure.setAutofillId(getAutofillId());
            onProvideAutofillStructure(viewStructure, i);
            onProvideAutofillVirtualStructure(viewStructure, i);
            viewStructure.setChildCount(this.inputFrame.getChildCount());
            for (int i2 = 0; i2 < this.inputFrame.getChildCount(); i2++) {
                View childAt = this.inputFrame.getChildAt(i2);
                ViewStructure newChild = viewStructure.newChild(i2);
                childAt.dispatchProvideAutofillStructure(newChild, i);
                if (childAt == this.editText) {
                    if (this.hintEnabled) {
                        charSequence = this.hint;
                    } else {
                        charSequence = null;
                    }
                    newChild.setHint(charSequence);
                }
            }
        }
    }

    public final void dispatchRestoreInstanceState(SparseArray sparseArray) {
        this.restoringSavedState = true;
        super.dispatchRestoreInstanceState(sparseArray);
        this.restoringSavedState = false;
    }

    public final void draw(Canvas canvas) {
        MaterialShapeDrawable materialShapeDrawable;
        super.draw(canvas);
        if (this.hintEnabled) {
            this.collapsingTextHelper.draw(canvas);
        }
        if (this.boxUnderlineFocused != null && (materialShapeDrawable = this.boxUnderlineDefault) != null) {
            materialShapeDrawable.draw(canvas);
            if (this.editText.isFocused()) {
                Rect bounds = this.boxUnderlineFocused.getBounds();
                Rect bounds2 = this.boxUnderlineDefault.getBounds();
                float f = this.collapsingTextHelper.expandedFraction;
                int centerX = bounds2.centerX();
                bounds.left = AnimationUtils.lerp(centerX, f, bounds2.left);
                bounds.right = AnimationUtils.lerp(centerX, f, bounds2.right);
                this.boxUnderlineFocused.draw(canvas);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x004f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void drawableStateChanged() {
        /*
            r4 = this;
            boolean r0 = r4.inDrawableStateChanged
            if (r0 == 0) goto L_0x0005
            return
        L_0x0005:
            r0 = 1
            r4.inDrawableStateChanged = r0
            super.drawableStateChanged()
            int[] r1 = r4.getDrawableState()
            com.google.android.material.internal.CollapsingTextHelper r2 = r4.collapsingTextHelper
            r3 = 0
            if (r2 == 0) goto L_0x002f
            r2.state = r1
            android.content.res.ColorStateList r1 = r2.collapsedTextColor
            if (r1 == 0) goto L_0x0020
            boolean r1 = r1.isStateful()
            if (r1 != 0) goto L_0x002a
        L_0x0020:
            android.content.res.ColorStateList r1 = r2.expandedTextColor
            if (r1 == 0) goto L_0x002f
            boolean r1 = r1.isStateful()
            if (r1 == 0) goto L_0x002f
        L_0x002a:
            r2.recalculate(r3)
            r1 = r0
            goto L_0x0030
        L_0x002f:
            r1 = r3
        L_0x0030:
            android.widget.EditText r2 = r4.editText
            if (r2 == 0) goto L_0x0047
            java.util.WeakHashMap r2 = androidx.core.view.ViewCompat.sViewPropertyAnimatorMap
            boolean r2 = androidx.core.view.ViewCompat.Api19Impl.isLaidOut(r4)
            if (r2 == 0) goto L_0x0043
            boolean r2 = r4.isEnabled()
            if (r2 == 0) goto L_0x0043
            goto L_0x0044
        L_0x0043:
            r0 = r3
        L_0x0044:
            r4.updateLabelState(r0, r3)
        L_0x0047:
            r4.updateEditTextBackground()
            r4.updateTextInputBoxState()
            if (r1 == 0) goto L_0x0052
            r4.invalidate()
        L_0x0052:
            r4.inDrawableStateChanged = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.drawableStateChanged():void");
    }

    public final int getBaseline() {
        EditText editText2 = this.editText;
        if (editText2 == null) {
            return super.getBaseline();
        }
        int baseline = editText2.getBaseline();
        return calculateLabelMarginTop() + getPaddingTop() + baseline;
    }

    /* JADX WARNING: type inference failed for: r3v1, types: [java.lang.Object, com.google.android.material.shape.CornerTreatment] */
    /* JADX WARNING: type inference failed for: r4v0, types: [java.lang.Object, com.google.android.material.shape.CornerTreatment] */
    /* JADX WARNING: type inference failed for: r5v0, types: [java.lang.Object, com.google.android.material.shape.CornerTreatment] */
    /* JADX WARNING: type inference failed for: r6v0, types: [java.lang.Object, com.google.android.material.shape.CornerTreatment] */
    /* JADX WARNING: type inference failed for: r7v0, types: [com.google.android.material.shape.EdgeTreatment, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r8v0, types: [com.google.android.material.shape.EdgeTreatment, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r9v0, types: [com.google.android.material.shape.EdgeTreatment, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r10v0, types: [com.google.android.material.shape.EdgeTreatment, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r0v3, types: [java.lang.Object, com.google.android.material.shape.ShapeAppearanceModel] */
    public final MaterialShapeDrawable getDropDownMaterialShapeDrawable(boolean z) {
        float f;
        float f2;
        int i;
        float dimensionPixelOffset = (float) getResources().getDimensionPixelOffset(2131166878);
        if (z) {
            f = dimensionPixelOffset;
        } else {
            f = 0.0f;
        }
        EditText editText2 = this.editText;
        if (editText2 instanceof MaterialAutoCompleteTextView) {
            f2 = ((MaterialAutoCompleteTextView) editText2).popupElevation;
        } else {
            f2 = (float) getResources().getDimensionPixelOffset(2131166813);
        }
        int dimensionPixelOffset2 = getResources().getDimensionPixelOffset(2131166815);
        ? obj = new Object();
        ? obj2 = new Object();
        ? obj3 = new Object();
        ? obj4 = new Object();
        ? obj5 = new Object();
        ? obj6 = new Object();
        ? obj7 = new Object();
        ? obj8 = new Object();
        AbsoluteCornerSize absoluteCornerSize = new AbsoluteCornerSize(f);
        AbsoluteCornerSize absoluteCornerSize2 = new AbsoluteCornerSize(f);
        AbsoluteCornerSize absoluteCornerSize3 = new AbsoluteCornerSize(dimensionPixelOffset);
        AbsoluteCornerSize absoluteCornerSize4 = new AbsoluteCornerSize(dimensionPixelOffset);
        ? obj9 = new Object();
        obj9.topLeftCorner = obj;
        obj9.topRightCorner = obj2;
        obj9.bottomRightCorner = obj3;
        obj9.bottomLeftCorner = obj4;
        obj9.topLeftCornerSize = absoluteCornerSize;
        obj9.topRightCornerSize = absoluteCornerSize2;
        obj9.bottomRightCornerSize = absoluteCornerSize4;
        obj9.bottomLeftCornerSize = absoluteCornerSize3;
        obj9.topEdge = obj5;
        obj9.rightEdge = obj6;
        obj9.bottomEdge = obj7;
        obj9.leftEdge = obj8;
        Context context = getContext();
        Paint paint = MaterialShapeDrawable.clearPaint;
        Class<MaterialShapeDrawable> cls = MaterialShapeDrawable.class;
        TypedValue resolveTypedValueOrThrow = MaterialAttributes.resolveTypedValueOrThrow(context, "MaterialShapeDrawable", 2130968887);
        int i2 = resolveTypedValueOrThrow.resourceId;
        if (i2 != 0) {
            Object obj10 = ContextCompat.sLock;
            i = context.getColor(i2);
        } else {
            i = resolveTypedValueOrThrow.data;
        }
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable();
        materialShapeDrawable.initializeElevationOverlay(context);
        materialShapeDrawable.setFillColor(ColorStateList.valueOf(i));
        materialShapeDrawable.setElevation(f2);
        materialShapeDrawable.setShapeAppearanceModel(obj9);
        MaterialShapeDrawable.MaterialShapeDrawableState materialShapeDrawableState = materialShapeDrawable.drawableState;
        if (materialShapeDrawableState.padding == null) {
            materialShapeDrawableState.padding = new Rect();
        }
        materialShapeDrawable.drawableState.padding.set(0, dimensionPixelOffset2, 0, dimensionPixelOffset2);
        materialShapeDrawable.invalidateSelf();
        return materialShapeDrawable;
    }

    public final float getHintCollapsedTextHeight() {
        return this.collapsingTextHelper.getCollapsedTextHeight();
    }

    public final int getHintCurrentCollapsedTextColor() {
        CollapsingTextHelper collapsingTextHelper2 = this.collapsingTextHelper;
        return collapsingTextHelper2.getCurrentColor(collapsingTextHelper2.collapsedTextColor);
    }

    public final int getLabelLeftBoundAlightWithPrefix(int i, boolean z) {
        int compoundPaddingLeft = this.editText.getCompoundPaddingLeft() + i;
        StartCompoundLayout startCompoundLayout = this.startLayout;
        if (startCompoundLayout.prefixText == null || z) {
            return compoundPaddingLeft;
        }
        return (compoundPaddingLeft - startCompoundLayout.prefixTextView.getMeasuredWidth()) + this.startLayout.prefixTextView.getPaddingLeft();
    }

    public final int getLabelRightBoundAlignedWithSuffix(int i, boolean z) {
        int compoundPaddingRight = i - this.editText.getCompoundPaddingRight();
        StartCompoundLayout startCompoundLayout = this.startLayout;
        if (startCompoundLayout.prefixText == null || !z) {
            return compoundPaddingRight;
        }
        return compoundPaddingRight + (startCompoundLayout.prefixTextView.getMeasuredWidth() - this.startLayout.prefixTextView.getPaddingRight());
    }

    public final boolean isHelperTextDisplayed() {
        IndicatorViewController indicatorViewController2 = this.indicatorViewController;
        if (indicatorViewController2.captionDisplayed != 2 || indicatorViewController2.helperTextView == null || TextUtils.isEmpty(indicatorViewController2.helperText)) {
            return false;
        }
        return true;
    }

    public final void onApplyBoxBackgroundMode() {
        int i = this.boxBackgroundMode;
        if (i == 0) {
            this.boxBackground = null;
            this.boxUnderlineDefault = null;
            this.boxUnderlineFocused = null;
        } else if (i == 1) {
            this.boxBackground = new MaterialShapeDrawable(this.shapeAppearanceModel);
            this.boxUnderlineDefault = new MaterialShapeDrawable();
            this.boxUnderlineFocused = new MaterialShapeDrawable();
        } else if (i == 2) {
            if (!this.hintEnabled || (this.boxBackground instanceof CutoutDrawable)) {
                this.boxBackground = new MaterialShapeDrawable(this.shapeAppearanceModel);
            } else {
                this.boxBackground = new CutoutDrawable(this.shapeAppearanceModel);
            }
            this.boxUnderlineDefault = null;
            this.boxUnderlineFocused = null;
        } else {
            throw new IllegalArgumentException(Anchor$$ExternalSyntheticOutline0.m(new StringBuilder(), this.boxBackgroundMode, " is illegal; only @BoxBackgroundMode constants are supported."));
        }
        updateEditTextBoxBackgroundIfNeeded();
        updateTextInputBoxState();
        if (this.boxBackgroundMode == 1) {
            if (getContext().getResources().getConfiguration().fontScale >= 2.0f) {
                this.boxCollapsedPaddingTopPx = getResources().getDimensionPixelSize(2131166639);
            } else if (MaterialResources.isFontScaleAtLeast1_3(getContext())) {
                this.boxCollapsedPaddingTopPx = getResources().getDimensionPixelSize(2131166638);
            }
        }
        if (this.editText != null && this.boxBackgroundMode == 1) {
            if (getContext().getResources().getConfiguration().fontScale >= 2.0f) {
                EditText editText2 = this.editText;
                WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                ViewCompat.Api17Impl.setPaddingRelative(editText2, ViewCompat.Api17Impl.getPaddingStart(editText2), getResources().getDimensionPixelSize(2131166637), ViewCompat.Api17Impl.getPaddingEnd(this.editText), getResources().getDimensionPixelSize(2131166636));
            } else if (MaterialResources.isFontScaleAtLeast1_3(getContext())) {
                EditText editText3 = this.editText;
                WeakHashMap weakHashMap2 = ViewCompat.sViewPropertyAnimatorMap;
                ViewCompat.Api17Impl.setPaddingRelative(editText3, ViewCompat.Api17Impl.getPaddingStart(editText3), getResources().getDimensionPixelSize(2131166635), ViewCompat.Api17Impl.getPaddingEnd(this.editText), getResources().getDimensionPixelSize(2131166634));
            }
        }
        if (this.boxBackgroundMode != 0) {
            updateInputLayoutMargins();
        }
        EditText editText4 = this.editText;
        if (editText4 instanceof AutoCompleteTextView) {
            AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) editText4;
            if (autoCompleteTextView.getDropDownBackground() == null) {
                int i2 = this.boxBackgroundMode;
                if (i2 == 2) {
                    if (this.outlinedDropDownMenuBackground == null) {
                        this.outlinedDropDownMenuBackground = getDropDownMaterialShapeDrawable(true);
                    }
                    autoCompleteTextView.setDropDownBackgroundDrawable(this.outlinedDropDownMenuBackground);
                } else if (i2 == 1) {
                    if (this.filledDropDownMenuBackground == null) {
                        StateListDrawable stateListDrawable = new StateListDrawable();
                        this.filledDropDownMenuBackground = stateListDrawable;
                        int[] iArr = {16842922};
                        if (this.outlinedDropDownMenuBackground == null) {
                            this.outlinedDropDownMenuBackground = getDropDownMaterialShapeDrawable(true);
                        }
                        stateListDrawable.addState(iArr, this.outlinedDropDownMenuBackground);
                        this.filledDropDownMenuBackground.addState(new int[0], getDropDownMaterialShapeDrawable(false));
                    }
                    autoCompleteTextView.setDropDownBackgroundDrawable(this.filledDropDownMenuBackground);
                }
            }
        }
    }

    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.collapsingTextHelper.maybeUpdateFontWeightAdjustment(configuration);
    }

    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        super.onLayout(z, i, i2, i3, i4);
        EditText editText2 = this.editText;
        if (editText2 != null) {
            Rect rect = this.tmpRect;
            DescendantOffsetUtils.getDescendantRect(this, editText2, rect);
            MaterialShapeDrawable materialShapeDrawable = this.boxUnderlineDefault;
            if (materialShapeDrawable != null) {
                int i7 = rect.bottom;
                materialShapeDrawable.setBounds(rect.left, i7 - this.boxStrokeWidthDefaultPx, rect.right, i7);
            }
            MaterialShapeDrawable materialShapeDrawable2 = this.boxUnderlineFocused;
            if (materialShapeDrawable2 != null) {
                int i8 = rect.bottom;
                materialShapeDrawable2.setBounds(rect.left, i8 - this.boxStrokeWidthFocusedPx, rect.right, i8);
            }
            if (this.hintEnabled) {
                CollapsingTextHelper collapsingTextHelper2 = this.collapsingTextHelper;
                float textSize = this.editText.getTextSize();
                if (collapsingTextHelper2.expandedTextSize != textSize) {
                    collapsingTextHelper2.expandedTextSize = textSize;
                    collapsingTextHelper2.recalculate(false);
                }
                int gravity = this.editText.getGravity();
                CollapsingTextHelper collapsingTextHelper3 = this.collapsingTextHelper;
                int i9 = (gravity & -113) | 48;
                if (collapsingTextHelper3.collapsedTextGravity != i9) {
                    collapsingTextHelper3.collapsedTextGravity = i9;
                    collapsingTextHelper3.recalculate(false);
                }
                CollapsingTextHelper collapsingTextHelper4 = this.collapsingTextHelper;
                if (collapsingTextHelper4.expandedTextGravity != gravity) {
                    collapsingTextHelper4.expandedTextGravity = gravity;
                    collapsingTextHelper4.recalculate(false);
                }
                CollapsingTextHelper collapsingTextHelper5 = this.collapsingTextHelper;
                if (this.editText != null) {
                    Rect rect2 = this.tmpBoundsRect;
                    boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
                    rect2.bottom = rect.bottom;
                    int i10 = this.boxBackgroundMode;
                    if (i10 == 1) {
                        rect2.left = getLabelLeftBoundAlightWithPrefix(rect.left, isLayoutRtl);
                        rect2.top = rect.top + this.boxCollapsedPaddingTopPx;
                        rect2.right = getLabelRightBoundAlignedWithSuffix(rect.right, isLayoutRtl);
                    } else if (i10 != 2) {
                        rect2.left = getLabelLeftBoundAlightWithPrefix(rect.left, isLayoutRtl);
                        rect2.top = getPaddingTop();
                        rect2.right = getLabelRightBoundAlignedWithSuffix(rect.right, isLayoutRtl);
                    } else {
                        rect2.left = this.editText.getPaddingLeft() + rect.left;
                        rect2.top = rect.top - calculateLabelMarginTop();
                        rect2.right = rect.right - this.editText.getPaddingRight();
                    }
                    collapsingTextHelper5.getClass();
                    int i11 = rect2.left;
                    int i12 = rect2.top;
                    int i13 = rect2.right;
                    int i14 = rect2.bottom;
                    Rect rect3 = collapsingTextHelper5.collapsedBounds;
                    if (!(rect3.left == i11 && rect3.top == i12 && rect3.right == i13 && rect3.bottom == i14)) {
                        rect3.set(i11, i12, i13, i14);
                        collapsingTextHelper5.boundsChanged = true;
                    }
                    CollapsingTextHelper collapsingTextHelper6 = this.collapsingTextHelper;
                    if (this.editText != null) {
                        Rect rect4 = this.tmpBoundsRect;
                        TextPaint textPaint = collapsingTextHelper6.tmpPaint;
                        textPaint.setTextSize(collapsingTextHelper6.expandedTextSize);
                        textPaint.setTypeface(collapsingTextHelper6.expandedTypeface);
                        textPaint.setLetterSpacing(collapsingTextHelper6.expandedLetterSpacing);
                        float f = -textPaint.ascent();
                        rect4.left = this.editText.getCompoundPaddingLeft() + rect.left;
                        if (this.boxBackgroundMode != 1 || this.editText.getMinLines() > 1) {
                            i5 = rect.top + this.editText.getCompoundPaddingTop();
                        } else {
                            i5 = (int) (((float) rect.centerY()) - (f / 2.0f));
                        }
                        rect4.top = i5;
                        rect4.right = rect.right - this.editText.getCompoundPaddingRight();
                        if (this.boxBackgroundMode != 1 || this.editText.getMinLines() > 1) {
                            i6 = rect.bottom - this.editText.getCompoundPaddingBottom();
                        } else {
                            i6 = (int) (((float) rect4.top) + f);
                        }
                        rect4.bottom = i6;
                        int i15 = rect4.left;
                        int i16 = rect4.top;
                        int i17 = rect4.right;
                        Rect rect5 = collapsingTextHelper6.expandedBounds;
                        if (!(rect5.left == i15 && rect5.top == i16 && rect5.right == i17 && rect5.bottom == i6)) {
                            rect5.set(i15, i16, i17, i6);
                            collapsingTextHelper6.boundsChanged = true;
                        }
                        this.collapsingTextHelper.recalculate(false);
                        if (cutoutEnabled() && !this.hintExpanded) {
                            openCutout();
                            return;
                        }
                        return;
                    }
                    throw new IllegalStateException();
                }
                throw new IllegalStateException();
            }
        }
    }

    public final void onMeasure(int i, int i2) {
        EditText editText2;
        int max;
        super.onMeasure(i, i2);
        boolean z = false;
        if (this.editText != null && this.editText.getMeasuredHeight() < (max = Math.max(this.endLayout.getMeasuredHeight(), this.startLayout.getMeasuredHeight()))) {
            this.editText.setMinimumHeight(max);
            z = true;
        }
        boolean updateDummyDrawables = updateDummyDrawables();
        if (z || updateDummyDrawables) {
            this.editText.post(new Runnable(this, 1) {
                public final /* synthetic */ TextInputLayout this$0;

                {
                    this.this$0 = r1;
                }

                public final void run() {
                    switch (0) {
                        case 0:
                            EndCompoundLayout endCompoundLayout = this.this$0.endLayout;
                            endCompoundLayout.endIconView.performClick();
                            endCompoundLayout.endIconView.jumpDrawablesToCurrentState();
                            return;
                        default:
                            this.this$0.editText.requestLayout();
                            return;
                    }
                }
            });
        }
        if (!(this.placeholderTextView == null || (editText2 = this.editText) == null)) {
            this.placeholderTextView.setGravity(editText2.getGravity());
            this.placeholderTextView.setPadding(this.editText.getCompoundPaddingLeft(), this.editText.getCompoundPaddingTop(), this.editText.getCompoundPaddingRight(), this.editText.getCompoundPaddingBottom());
        }
        this.endLayout.updateSuffixTextViewPadding();
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x004f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onRestoreInstanceState(android.os.Parcelable r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.google.android.material.textfield.TextInputLayout.SavedState
            if (r0 != 0) goto L_0x0008
            super.onRestoreInstanceState(r6)
            return
        L_0x0008:
            com.google.android.material.textfield.TextInputLayout$SavedState r6 = (com.google.android.material.textfield.TextInputLayout.SavedState) r6
            android.os.Parcelable r0 = r6.mSuperState
            super.onRestoreInstanceState(r0)
            java.lang.CharSequence r0 = r6.error
            com.google.android.material.textfield.IndicatorViewController r1 = r5.indicatorViewController
            boolean r1 = r1.errorEnabled
            r2 = 1
            if (r1 != 0) goto L_0x0022
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L_0x001f
            goto L_0x004b
        L_0x001f:
            r5.setErrorEnabled(r2)
        L_0x0022:
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L_0x0046
            com.google.android.material.textfield.IndicatorViewController r1 = r5.indicatorViewController
            r1.cancelCaptionAnimator()
            r1.errorText = r0
            androidx.appcompat.widget.AppCompatTextView r3 = r1.errorView
            r3.setText(r0)
            int r3 = r1.captionDisplayed
            if (r3 == r2) goto L_0x003a
            r1.captionToShow = r2
        L_0x003a:
            int r2 = r1.captionToShow
            androidx.appcompat.widget.AppCompatTextView r4 = r1.errorView
            boolean r0 = r1.shouldAnimateCaptionView(r4, r0)
            r1.updateCaptionViewsVisibility(r3, r2, r0)
            goto L_0x004b
        L_0x0046:
            com.google.android.material.textfield.IndicatorViewController r0 = r5.indicatorViewController
            r0.hideError()
        L_0x004b:
            boolean r6 = r6.isEndIconChecked
            if (r6 == 0) goto L_0x0058
            com.google.android.material.textfield.TextInputLayout$2 r6 = new com.google.android.material.textfield.TextInputLayout$2
            r0 = 0
            r6.<init>(r5, r0)
            r5.post(r6)
        L_0x0058:
            r5.requestLayout()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.onRestoreInstanceState(android.os.Parcelable):void");
    }

    public final void onRtlPropertiesChanged(int i) {
        boolean z;
        float f;
        float f2;
        float f3;
        float f4;
        super.onRtlPropertiesChanged(i);
        boolean z2 = false;
        if (i == 1) {
            z = true;
        } else {
            z = false;
        }
        boolean z3 = this.areCornerRadiiRtl;
        if (z != z3) {
            if (z && !z3) {
                z2 = true;
            }
            float cornerSize = this.shapeAppearanceModel.topLeftCornerSize.getCornerSize(this.tmpRectF);
            float cornerSize2 = this.shapeAppearanceModel.topRightCornerSize.getCornerSize(this.tmpRectF);
            float cornerSize3 = this.shapeAppearanceModel.bottomLeftCornerSize.getCornerSize(this.tmpRectF);
            float cornerSize4 = this.shapeAppearanceModel.bottomRightCornerSize.getCornerSize(this.tmpRectF);
            if (z2) {
                f = cornerSize;
            } else {
                f = cornerSize2;
            }
            if (z2) {
                cornerSize = cornerSize2;
            }
            if (z2) {
                f2 = cornerSize3;
            } else {
                f2 = cornerSize4;
            }
            if (z2) {
                cornerSize3 = cornerSize4;
            }
            boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
            this.areCornerRadiiRtl = isLayoutRtl;
            if (isLayoutRtl) {
                f3 = cornerSize;
            } else {
                f3 = f;
            }
            if (!isLayoutRtl) {
                f = cornerSize;
            }
            if (isLayoutRtl) {
                f4 = cornerSize3;
            } else {
                f4 = f2;
            }
            if (!isLayoutRtl) {
                f2 = cornerSize3;
            }
            MaterialShapeDrawable materialShapeDrawable = this.boxBackground;
            if (materialShapeDrawable != null && materialShapeDrawable.getTopLeftCornerResolvedSize() == f3) {
                MaterialShapeDrawable materialShapeDrawable2 = this.boxBackground;
                if (materialShapeDrawable2.drawableState.shapeAppearanceModel.topRightCornerSize.getCornerSize(materialShapeDrawable2.getBoundsAsRectF$1()) == f) {
                    MaterialShapeDrawable materialShapeDrawable3 = this.boxBackground;
                    if (materialShapeDrawable3.drawableState.shapeAppearanceModel.bottomLeftCornerSize.getCornerSize(materialShapeDrawable3.getBoundsAsRectF$1()) == f4) {
                        MaterialShapeDrawable materialShapeDrawable4 = this.boxBackground;
                        if (materialShapeDrawable4.drawableState.shapeAppearanceModel.bottomRightCornerSize.getCornerSize(materialShapeDrawable4.getBoundsAsRectF$1()) == f2) {
                            return;
                        }
                    }
                }
            }
            ShapeAppearanceModel.Builder builder = this.shapeAppearanceModel.toBuilder();
            builder.topLeftCornerSize = new AbsoluteCornerSize(f3);
            builder.topRightCornerSize = new AbsoluteCornerSize(f);
            builder.bottomLeftCornerSize = new AbsoluteCornerSize(f4);
            builder.bottomRightCornerSize = new AbsoluteCornerSize(f2);
            this.shapeAppearanceModel = builder.build();
            applyBoxAttributes();
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.google.android.material.textfield.TextInputLayout$SavedState, android.os.Parcelable, androidx.customview.view.AbsSavedState] */
    public final Parcelable onSaveInstanceState() {
        boolean z;
        CharSequence charSequence;
        ? absSavedState = new AbsSavedState(super.onSaveInstanceState());
        if (shouldShowError()) {
            IndicatorViewController indicatorViewController2 = this.indicatorViewController;
            if (indicatorViewController2.errorEnabled) {
                charSequence = indicatorViewController2.errorText;
            } else {
                charSequence = null;
            }
            absSavedState.error = charSequence;
        }
        EndCompoundLayout endCompoundLayout = this.endLayout;
        if (endCompoundLayout.endIconMode == 0 || !endCompoundLayout.endIconView.checked) {
            z = false;
        } else {
            z = true;
        }
        absSavedState.isEndIconChecked = z;
        return absSavedState;
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:45:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void openCutout() {
        /*
            r12 = this;
            boolean r0 = r12.cutoutEnabled()
            if (r0 != 0) goto L_0x0007
            return
        L_0x0007:
            android.graphics.RectF r0 = r12.tmpRectF
            com.google.android.material.internal.CollapsingTextHelper r1 = r12.collapsingTextHelper
            android.widget.EditText r2 = r12.editText
            int r2 = r2.getWidth()
            android.widget.EditText r3 = r12.editText
            int r3 = r3.getGravity()
            java.lang.CharSequence r4 = r1.text
            boolean r4 = r1.calculateIsRtl(r4)
            r1.isRtl = r4
            r5 = 5
            r6 = 1073741824(0x40000000, float:2.0)
            r7 = 8388613(0x800005, float:1.175495E-38)
            r8 = 1
            r9 = 17
            android.graphics.Rect r10 = r1.collapsedBounds
            if (r3 == r9) goto L_0x0051
            r11 = r3 & 7
            if (r11 != r8) goto L_0x0031
            goto L_0x0051
        L_0x0031:
            r11 = r3 & r7
            if (r11 == r7) goto L_0x0046
            r11 = r3 & 5
            if (r11 != r5) goto L_0x003a
            goto L_0x0046
        L_0x003a:
            if (r4 == 0) goto L_0x0042
            int r4 = r10.right
            float r4 = (float) r4
            float r11 = r1.collapsedTextWidth
            goto L_0x0056
        L_0x0042:
            int r4 = r10.left
        L_0x0044:
            float r4 = (float) r4
            goto L_0x0057
        L_0x0046:
            if (r4 == 0) goto L_0x004b
            int r4 = r10.left
            goto L_0x0044
        L_0x004b:
            int r4 = r10.right
            float r4 = (float) r4
            float r11 = r1.collapsedTextWidth
            goto L_0x0056
        L_0x0051:
            float r4 = (float) r2
            float r4 = r4 / r6
            float r11 = r1.collapsedTextWidth
            float r11 = r11 / r6
        L_0x0056:
            float r4 = r4 - r11
        L_0x0057:
            int r11 = r10.left
            float r11 = (float) r11
            float r4 = java.lang.Math.max(r4, r11)
            r0.left = r4
            int r11 = r10.top
            float r11 = (float) r11
            r0.top = r11
            if (r3 == r9) goto L_0x008d
            r9 = r3 & 7
            if (r9 != r8) goto L_0x006c
            goto L_0x008d
        L_0x006c:
            r2 = r3 & r7
            if (r2 == r7) goto L_0x0081
            r2 = r3 & 5
            if (r2 != r5) goto L_0x0075
            goto L_0x0081
        L_0x0075:
            boolean r2 = r1.isRtl
            if (r2 == 0) goto L_0x007d
            int r2 = r10.right
        L_0x007b:
            float r2 = (float) r2
            goto L_0x0093
        L_0x007d:
            float r2 = r1.collapsedTextWidth
            float r2 = r2 + r4
            goto L_0x0093
        L_0x0081:
            boolean r2 = r1.isRtl
            if (r2 == 0) goto L_0x008a
            float r2 = r1.collapsedTextWidth
            float r4 = r4 + r2
            r2 = r4
            goto L_0x0093
        L_0x008a:
            int r2 = r10.right
            goto L_0x007b
        L_0x008d:
            float r2 = (float) r2
            float r2 = r2 / r6
            float r3 = r1.collapsedTextWidth
            float r3 = r3 / r6
            float r2 = r2 + r3
        L_0x0093:
            int r3 = r10.right
            float r3 = (float) r3
            float r2 = java.lang.Math.min(r2, r3)
            r0.right = r2
            int r2 = r10.top
            float r2 = (float) r2
            float r1 = r1.getCollapsedTextHeight()
            float r1 = r1 + r2
            r0.bottom = r1
            float r1 = r0.width()
            r2 = 0
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r1 <= 0) goto L_0x00f0
            float r1 = r0.height()
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r1 > 0) goto L_0x00b8
            goto L_0x00f0
        L_0x00b8:
            float r1 = r0.left
            int r2 = r12.boxLabelCutoutPaddingPx
            float r2 = (float) r2
            float r1 = r1 - r2
            r0.left = r1
            float r1 = r0.right
            float r1 = r1 + r2
            r0.right = r1
            int r1 = r12.getPaddingLeft()
            int r1 = -r1
            float r1 = (float) r1
            int r2 = r12.getPaddingTop()
            int r2 = -r2
            float r2 = (float) r2
            float r3 = r0.height()
            float r3 = r3 / r6
            float r2 = r2 - r3
            int r3 = r12.boxStrokeWidthPx
            float r3 = (float) r3
            float r2 = r2 + r3
            r0.offset(r1, r2)
            com.google.android.material.shape.MaterialShapeDrawable r12 = r12.boxBackground
            com.google.android.material.textfield.CutoutDrawable r12 = (com.google.android.material.textfield.CutoutDrawable) r12
            r12.getClass()
            float r1 = r0.left
            float r2 = r0.top
            float r3 = r0.right
            float r0 = r0.bottom
            r12.setCutout(r1, r2, r3, r0)
        L_0x00f0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.openCutout():void");
    }

    public final void setEnabled(boolean z) {
        recursiveSetEnabled(this, z);
        super.setEnabled(z);
    }

    public final void setErrorEnabled(boolean z) {
        IndicatorViewController indicatorViewController2 = this.indicatorViewController;
        if (indicatorViewController2.errorEnabled != z) {
            indicatorViewController2.cancelCaptionAnimator();
            TextInputLayout textInputLayout = indicatorViewController2.textInputView;
            if (z) {
                AppCompatTextView appCompatTextView = new AppCompatTextView(indicatorViewController2.context);
                indicatorViewController2.errorView = appCompatTextView;
                appCompatTextView.setId(2131363819);
                indicatorViewController2.errorView.setTextAlignment(5);
                int i = indicatorViewController2.errorTextAppearance;
                indicatorViewController2.errorTextAppearance = i;
                AppCompatTextView appCompatTextView2 = indicatorViewController2.errorView;
                if (appCompatTextView2 != null) {
                    textInputLayout.setTextAppearanceCompatWithErrorFallback(i, appCompatTextView2);
                }
                ColorStateList colorStateList = indicatorViewController2.errorViewTextColor;
                indicatorViewController2.errorViewTextColor = colorStateList;
                AppCompatTextView appCompatTextView3 = indicatorViewController2.errorView;
                if (!(appCompatTextView3 == null || colorStateList == null)) {
                    appCompatTextView3.setTextColor(colorStateList);
                }
                CharSequence charSequence = indicatorViewController2.errorViewContentDescription;
                indicatorViewController2.errorViewContentDescription = charSequence;
                AppCompatTextView appCompatTextView4 = indicatorViewController2.errorView;
                if (appCompatTextView4 != null) {
                    appCompatTextView4.setContentDescription(charSequence);
                }
                indicatorViewController2.errorView.setVisibility(4);
                AppCompatTextView appCompatTextView5 = indicatorViewController2.errorView;
                WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                ViewCompat.Api19Impl.setAccessibilityLiveRegion(appCompatTextView5, 1);
                indicatorViewController2.addIndicator(0, indicatorViewController2.errorView);
            } else {
                indicatorViewController2.hideError();
                indicatorViewController2.removeIndicator(0, indicatorViewController2.errorView);
                indicatorViewController2.errorView = null;
                textInputLayout.updateEditTextBackground();
                textInputLayout.updateTextInputBoxState();
            }
            indicatorViewController2.errorEnabled = z;
        }
    }

    public final void setHelperTextEnabled(boolean z) {
        IndicatorViewController indicatorViewController2 = this.indicatorViewController;
        if (indicatorViewController2.helperTextEnabled != z) {
            indicatorViewController2.cancelCaptionAnimator();
            if (z) {
                AppCompatTextView appCompatTextView = new AppCompatTextView(indicatorViewController2.context);
                indicatorViewController2.helperTextView = appCompatTextView;
                appCompatTextView.setId(2131363820);
                indicatorViewController2.helperTextView.setTextAlignment(5);
                indicatorViewController2.helperTextView.setVisibility(4);
                AppCompatTextView appCompatTextView2 = indicatorViewController2.helperTextView;
                WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                ViewCompat.Api19Impl.setAccessibilityLiveRegion(appCompatTextView2, 1);
                int i = indicatorViewController2.helperTextTextAppearance;
                indicatorViewController2.helperTextTextAppearance = i;
                AppCompatTextView appCompatTextView3 = indicatorViewController2.helperTextView;
                if (appCompatTextView3 != null) {
                    appCompatTextView3.setTextAppearance(i);
                }
                ColorStateList colorStateList = indicatorViewController2.helperTextViewTextColor;
                indicatorViewController2.helperTextViewTextColor = colorStateList;
                AppCompatTextView appCompatTextView4 = indicatorViewController2.helperTextView;
                if (!(appCompatTextView4 == null || colorStateList == null)) {
                    appCompatTextView4.setTextColor(colorStateList);
                }
                indicatorViewController2.addIndicator(1, indicatorViewController2.helperTextView);
                indicatorViewController2.helperTextView.setAccessibilityDelegate(new View.AccessibilityDelegate() {
                    public final void onInitializeAccessibilityNodeInfo(
/*
Method generation error in method: com.google.android.material.textfield.IndicatorViewController.2.onInitializeAccessibilityNodeInfo(android.view.View, android.view.accessibility.AccessibilityNodeInfo):void, dex: classes2.dex
                    jadx.core.utils.exceptions.JadxRuntimeException: Method args not loaded: com.google.android.material.textfield.IndicatorViewController.2.onInitializeAccessibilityNodeInfo(android.view.View, android.view.accessibility.AccessibilityNodeInfo):void, class status: UNLOADED
                    	at jadx.core.dex.nodes.MethodNode.getArgRegs(MethodNode.java:278)
                    	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:116)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:313)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                    	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.util.ArrayList.forEach(ArrayList.java:1259)
                    	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:483)
                    	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
                    	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                    	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                    	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                    	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:485)
                    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                    	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:676)
                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:607)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:728)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                    	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.util.ArrayList.forEach(ArrayList.java:1259)
                    	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:483)
                    	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
                    	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                    	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                    	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                    	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:485)
                    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                    	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                    	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
                    	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                    	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
                    	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
                    	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
                    	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
                    
*/
                });
            } else {
                indicatorViewController2.cancelCaptionAnimator();
                int i2 = indicatorViewController2.captionDisplayed;
                if (i2 == 2) {
                    indicatorViewController2.captionToShow = 0;
                }
                indicatorViewController2.updateCaptionViewsVisibility(i2, indicatorViewController2.captionToShow, indicatorViewController2.shouldAnimateCaptionView(indicatorViewController2.helperTextView, ""));
                indicatorViewController2.removeIndicator(1, indicatorViewController2.helperTextView);
                indicatorViewController2.helperTextView = null;
                TextInputLayout textInputLayout = indicatorViewController2.textInputView;
                textInputLayout.updateEditTextBackground();
                textInputLayout.updateTextInputBoxState();
            }
            indicatorViewController2.helperTextEnabled = z;
        }
    }

    public final void setHint(CharSequence charSequence) {
        if (this.hintEnabled) {
            if (!TextUtils.equals(charSequence, this.hint)) {
                this.hint = charSequence;
                CollapsingTextHelper collapsingTextHelper2 = this.collapsingTextHelper;
                if (charSequence == null || !TextUtils.equals(collapsingTextHelper2.text, charSequence)) {
                    collapsingTextHelper2.text = charSequence;
                    collapsingTextHelper2.textToDraw = null;
                    Bitmap bitmap = collapsingTextHelper2.expandedTitleTexture;
                    if (bitmap != null) {
                        bitmap.recycle();
                        collapsingTextHelper2.expandedTitleTexture = null;
                    }
                    collapsingTextHelper2.recalculate(false);
                }
                if (!this.hintExpanded) {
                    openCutout();
                }
            }
            sendAccessibilityEvent(2048);
        }
    }

    public final void setPlaceholderTextEnabled(boolean z) {
        if (this.placeholderEnabled != z) {
            if (z) {
                AppCompatTextView appCompatTextView = this.placeholderTextView;
                if (appCompatTextView != null) {
                    this.inputFrame.addView(appCompatTextView);
                    this.placeholderTextView.setVisibility(0);
                }
            } else {
                AppCompatTextView appCompatTextView2 = this.placeholderTextView;
                if (appCompatTextView2 != null) {
                    appCompatTextView2.setVisibility(8);
                }
                this.placeholderTextView = null;
            }
            this.placeholderEnabled = z;
        }
    }

    public final void setTextAppearanceCompatWithErrorFallback(int i, TextView textView) {
        try {
            textView.setTextAppearance(i);
            if (textView.getTextColors().getDefaultColor() != -65281) {
                return;
            }
        } catch (Exception unused) {
        }
        textView.setTextAppearance(2132017899);
        Context context = getContext();
        Object obj = ContextCompat.sLock;
        textView.setTextColor(context.getColor(2131099844));
    }

    public final boolean shouldShowError() {
        IndicatorViewController indicatorViewController2 = this.indicatorViewController;
        if (indicatorViewController2.captionToShow != 1 || indicatorViewController2.errorView == null || TextUtils.isEmpty(indicatorViewController2.errorText)) {
            return false;
        }
        return true;
    }

    public final void updateCounter(Editable editable) {
        int i;
        boolean z;
        int i2;
        this.lengthCounter.getClass();
        if (editable != null) {
            i = editable.length();
        } else {
            i = 0;
        }
        boolean z2 = this.counterOverflowed;
        int i3 = this.counterMaxLength;
        if (i3 == -1) {
            this.counterView.setText(String.valueOf(i));
            this.counterView.setContentDescription((CharSequence) null);
            this.counterOverflowed = false;
        } else {
            if (i > i3) {
                z = true;
            } else {
                z = false;
            }
            this.counterOverflowed = z;
            Context context = getContext();
            AppCompatTextView appCompatTextView = this.counterView;
            int i4 = this.counterMaxLength;
            if (this.counterOverflowed) {
                i2 = 2131952187;
            } else {
                i2 = 2131952186;
            }
            appCompatTextView.setContentDescription(context.getString(i2, new Object[]{Integer.valueOf(i), Integer.valueOf(i4)}));
            if (z2 != this.counterOverflowed) {
                updateCounterTextAppearanceAndColor();
            }
            this.counterView.setText(BidiFormatter.getInstance().unicodeWrap(getContext().getString(2131952188, new Object[]{Integer.valueOf(i), Integer.valueOf(this.counterMaxLength)})));
        }
        if (this.editText != null && z2 != this.counterOverflowed) {
            updateLabelState(false, false);
            updateTextInputBoxState();
            updateEditTextBackground();
        }
    }

    public final void updateCounterTextAppearanceAndColor() {
        int i;
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        AppCompatTextView appCompatTextView = this.counterView;
        if (appCompatTextView != null) {
            if (this.counterOverflowed) {
                i = this.counterOverflowTextAppearance;
            } else {
                i = this.counterTextAppearance;
            }
            setTextAppearanceCompatWithErrorFallback(i, appCompatTextView);
            if (!this.counterOverflowed && (colorStateList2 = this.counterTextColor) != null) {
                this.counterView.setTextColor(colorStateList2);
            }
            if (this.counterOverflowed && (colorStateList = this.counterOverflowTextColor) != null) {
                this.counterView.setTextColor(colorStateList);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x008f, code lost:
        if (r6.isEndIconVisible() != false) goto L_0x0097;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0095, code lost:
        if (r10.endLayout.suffixText != null) goto L_0x0097;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0020, code lost:
        if (r0.prefixTextView.getVisibility() == 0) goto L_0x0022;
     */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0085  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0122  */
    /* JADX WARNING: Removed duplicated region for block: B:64:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean updateDummyDrawables() {
        /*
            r10 = this;
            android.widget.EditText r0 = r10.editText
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            com.google.android.material.textfield.StartCompoundLayout r0 = r10.startLayout
            com.google.android.material.internal.CheckableImageButton r0 = r0.startIconView
            android.graphics.drawable.Drawable r0 = r0.getDrawable()
            r2 = 0
            r3 = 2
            r4 = 3
            r5 = 1
            if (r0 != 0) goto L_0x0022
            com.google.android.material.textfield.StartCompoundLayout r0 = r10.startLayout
            java.lang.CharSequence r6 = r0.prefixText
            if (r6 == 0) goto L_0x0063
            androidx.appcompat.widget.AppCompatTextView r0 = r0.prefixTextView
            int r0 = r0.getVisibility()
            if (r0 != 0) goto L_0x0063
        L_0x0022:
            com.google.android.material.textfield.StartCompoundLayout r0 = r10.startLayout
            int r0 = r0.getMeasuredWidth()
            if (r0 <= 0) goto L_0x0063
            com.google.android.material.textfield.StartCompoundLayout r0 = r10.startLayout
            int r0 = r0.getMeasuredWidth()
            android.widget.EditText r6 = r10.editText
            int r6 = r6.getPaddingLeft()
            int r0 = r0 - r6
            android.graphics.drawable.Drawable r6 = r10.startDummyDrawable
            if (r6 == 0) goto L_0x003f
            int r6 = r10.startDummyDrawableWidth
            if (r6 == r0) goto L_0x004b
        L_0x003f:
            android.graphics.drawable.ColorDrawable r6 = new android.graphics.drawable.ColorDrawable
            r6.<init>()
            r10.startDummyDrawable = r6
            r10.startDummyDrawableWidth = r0
            r6.setBounds(r1, r1, r0, r5)
        L_0x004b:
            android.widget.EditText r0 = r10.editText
            android.graphics.drawable.Drawable[] r0 = r0.getCompoundDrawablesRelative()
            r6 = r0[r1]
            android.graphics.drawable.Drawable r7 = r10.startDummyDrawable
            if (r6 == r7) goto L_0x007c
            android.widget.EditText r6 = r10.editText
            r8 = r0[r5]
            r9 = r0[r3]
            r0 = r0[r4]
            r6.setCompoundDrawablesRelative(r7, r8, r9, r0)
            goto L_0x007a
        L_0x0063:
            android.graphics.drawable.Drawable r0 = r10.startDummyDrawable
            if (r0 == 0) goto L_0x007c
            android.widget.EditText r0 = r10.editText
            android.graphics.drawable.Drawable[] r0 = r0.getCompoundDrawablesRelative()
            android.widget.EditText r6 = r10.editText
            r7 = r0[r5]
            r8 = r0[r3]
            r0 = r0[r4]
            r6.setCompoundDrawablesRelative(r2, r7, r8, r0)
            r10.startDummyDrawable = r2
        L_0x007a:
            r0 = r5
            goto L_0x007d
        L_0x007c:
            r0 = r1
        L_0x007d:
            com.google.android.material.textfield.EndCompoundLayout r6 = r10.endLayout
            boolean r6 = r6.isErrorIconVisible()
            if (r6 != 0) goto L_0x0097
            com.google.android.material.textfield.EndCompoundLayout r6 = r10.endLayout
            int r7 = r6.endIconMode
            if (r7 == 0) goto L_0x0091
            boolean r6 = r6.isEndIconVisible()
            if (r6 != 0) goto L_0x0097
        L_0x0091:
            com.google.android.material.textfield.EndCompoundLayout r6 = r10.endLayout
            java.lang.CharSequence r6 = r6.suffixText
            if (r6 == 0) goto L_0x011e
        L_0x0097:
            com.google.android.material.textfield.EndCompoundLayout r6 = r10.endLayout
            int r6 = r6.getMeasuredWidth()
            if (r6 <= 0) goto L_0x011e
            com.google.android.material.textfield.EndCompoundLayout r6 = r10.endLayout
            androidx.appcompat.widget.AppCompatTextView r6 = r6.suffixTextView
            int r6 = r6.getMeasuredWidth()
            android.widget.EditText r7 = r10.editText
            int r7 = r7.getPaddingRight()
            int r6 = r6 - r7
            com.google.android.material.textfield.EndCompoundLayout r7 = r10.endLayout
            boolean r8 = r7.isErrorIconVisible()
            if (r8 == 0) goto L_0x00b9
            com.google.android.material.internal.CheckableImageButton r2 = r7.errorIconView
            goto L_0x00c5
        L_0x00b9:
            int r8 = r7.endIconMode
            if (r8 == 0) goto L_0x00c5
            boolean r8 = r7.isEndIconVisible()
            if (r8 == 0) goto L_0x00c5
            com.google.android.material.internal.CheckableImageButton r2 = r7.endIconView
        L_0x00c5:
            if (r2 == 0) goto L_0x00d8
            int r7 = r2.getMeasuredWidth()
            int r7 = r7 + r6
            android.view.ViewGroup$LayoutParams r2 = r2.getLayoutParams()
            android.view.ViewGroup$MarginLayoutParams r2 = (android.view.ViewGroup.MarginLayoutParams) r2
            int r2 = r2.getMarginStart()
            int r6 = r2 + r7
        L_0x00d8:
            android.widget.EditText r2 = r10.editText
            android.graphics.drawable.Drawable[] r2 = r2.getCompoundDrawablesRelative()
            android.graphics.drawable.Drawable r7 = r10.endDummyDrawable
            if (r7 == 0) goto L_0x00f9
            int r8 = r10.endDummyDrawableWidth
            if (r8 == r6) goto L_0x00f9
            r10.endDummyDrawableWidth = r6
            r7.setBounds(r1, r1, r6, r5)
            android.widget.EditText r0 = r10.editText
            r1 = r2[r1]
            r3 = r2[r5]
            android.graphics.drawable.Drawable r10 = r10.endDummyDrawable
            r2 = r2[r4]
            r0.setCompoundDrawablesRelative(r1, r3, r10, r2)
            goto L_0x011c
        L_0x00f9:
            if (r7 != 0) goto L_0x0107
            android.graphics.drawable.ColorDrawable r7 = new android.graphics.drawable.ColorDrawable
            r7.<init>()
            r10.endDummyDrawable = r7
            r10.endDummyDrawableWidth = r6
            r7.setBounds(r1, r1, r6, r5)
        L_0x0107:
            r3 = r2[r3]
            android.graphics.drawable.Drawable r6 = r10.endDummyDrawable
            if (r3 == r6) goto L_0x011b
            r10.originalEditTextEndDrawable = r3
            android.widget.EditText r10 = r10.editText
            r0 = r2[r1]
            r1 = r2[r5]
            r2 = r2[r4]
            r10.setCompoundDrawablesRelative(r0, r1, r6, r2)
            goto L_0x011c
        L_0x011b:
            r5 = r0
        L_0x011c:
            r0 = r5
            goto L_0x0140
        L_0x011e:
            android.graphics.drawable.Drawable r6 = r10.endDummyDrawable
            if (r6 == 0) goto L_0x0140
            android.widget.EditText r6 = r10.editText
            android.graphics.drawable.Drawable[] r6 = r6.getCompoundDrawablesRelative()
            r3 = r6[r3]
            android.graphics.drawable.Drawable r7 = r10.endDummyDrawable
            if (r3 != r7) goto L_0x013c
            android.widget.EditText r0 = r10.editText
            r1 = r6[r1]
            r3 = r6[r5]
            android.graphics.drawable.Drawable r7 = r10.originalEditTextEndDrawable
            r4 = r6[r4]
            r0.setCompoundDrawablesRelative(r1, r3, r7, r4)
            goto L_0x013d
        L_0x013c:
            r5 = r0
        L_0x013d:
            r10.endDummyDrawable = r2
            goto L_0x011c
        L_0x0140:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.updateDummyDrawables():boolean");
    }

    public final void updateEditTextBackground() {
        Drawable background;
        AppCompatTextView appCompatTextView;
        int i;
        EditText editText2 = this.editText;
        if (editText2 != null && this.boxBackgroundMode == 0 && (background = editText2.getBackground()) != null) {
            Rect rect = DrawableUtils.INSETS_NONE;
            Drawable mutate = background.mutate();
            if (shouldShowError()) {
                AppCompatTextView appCompatTextView2 = this.indicatorViewController.errorView;
                if (appCompatTextView2 != null) {
                    i = appCompatTextView2.getCurrentTextColor();
                } else {
                    i = -1;
                }
                mutate.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(i, PorterDuff.Mode.SRC_IN));
            } else if (!this.counterOverflowed || (appCompatTextView = this.counterView) == null) {
                mutate.clearColorFilter();
                this.editText.refreshDrawableState();
            } else {
                mutate.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(appCompatTextView.getCurrentTextColor(), PorterDuff.Mode.SRC_IN));
            }
        }
    }

    public final void updateEditTextBoxBackgroundIfNeeded() {
        Drawable drawable;
        int i;
        EditText editText2 = this.editText;
        if (editText2 != null && this.boxBackground != null) {
            if ((this.boxBackgroundApplied || editText2.getBackground() == null) && this.boxBackgroundMode != 0) {
                EditText editText3 = this.editText;
                if (!(editText3 instanceof AutoCompleteTextView) || EditTextUtils.isEditable(editText3)) {
                    drawable = this.boxBackground;
                } else {
                    int color = MaterialColors.getColor(this.editText, 2130968859);
                    int i2 = this.boxBackgroundMode;
                    int[][] iArr = EDIT_TEXT_BACKGROUND_RIPPLE_STATE;
                    if (i2 == 2) {
                        Context context = getContext();
                        MaterialShapeDrawable materialShapeDrawable = this.boxBackground;
                        TypedValue resolveTypedValueOrThrow = MaterialAttributes.resolveTypedValueOrThrow(context, "TextInputLayout", 2130968887);
                        int i3 = resolveTypedValueOrThrow.resourceId;
                        if (i3 != 0) {
                            Object obj = ContextCompat.sLock;
                            i = context.getColor(i3);
                        } else {
                            i = resolveTypedValueOrThrow.data;
                        }
                        MaterialShapeDrawable materialShapeDrawable2 = new MaterialShapeDrawable(materialShapeDrawable.drawableState.shapeAppearanceModel);
                        int layer = MaterialColors.layer(color, 0.1f, i);
                        materialShapeDrawable2.setFillColor(new ColorStateList(iArr, new int[]{layer, 0}));
                        materialShapeDrawable2.setTint(i);
                        ColorStateList colorStateList = new ColorStateList(iArr, new int[]{layer, i});
                        MaterialShapeDrawable materialShapeDrawable3 = new MaterialShapeDrawable(materialShapeDrawable.drawableState.shapeAppearanceModel);
                        materialShapeDrawable3.setTint(-1);
                        drawable = new LayerDrawable(new Drawable[]{new RippleDrawable(colorStateList, materialShapeDrawable2, materialShapeDrawable3), materialShapeDrawable});
                    } else if (i2 == 1) {
                        MaterialShapeDrawable materialShapeDrawable4 = this.boxBackground;
                        int i4 = this.boxBackgroundColor;
                        drawable = new RippleDrawable(new ColorStateList(iArr, new int[]{MaterialColors.layer(color, 0.1f, i4), i4}), materialShapeDrawable4, materialShapeDrawable4);
                    } else {
                        drawable = null;
                    }
                }
                WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                ViewCompat.Api16Impl.setBackground(editText3, drawable);
                this.boxBackgroundApplied = true;
            }
        }
    }

    public final void updateInputLayoutMargins() {
        if (this.boxBackgroundMode != 1) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.inputFrame.getLayoutParams();
            int calculateLabelMarginTop = calculateLabelMarginTop();
            if (calculateLabelMarginTop != layoutParams.topMargin) {
                layoutParams.topMargin = calculateLabelMarginTop;
                this.inputFrame.requestLayout();
            }
        }
    }

    public final void updateLabelState(boolean z, boolean z2) {
        boolean z3;
        boolean z4;
        ColorStateList colorStateList;
        AppCompatTextView appCompatTextView;
        ColorStateList colorStateList2;
        int i;
        boolean isEnabled = isEnabled();
        EditText editText2 = this.editText;
        if (editText2 == null || TextUtils.isEmpty(editText2.getText())) {
            z3 = false;
        } else {
            z3 = true;
        }
        EditText editText3 = this.editText;
        if (editText3 == null || !editText3.hasFocus()) {
            z4 = false;
        } else {
            z4 = true;
        }
        ColorStateList colorStateList3 = this.defaultHintTextColor;
        if (colorStateList3 != null) {
            this.collapsingTextHelper.setCollapsedTextColor(colorStateList3);
            CollapsingTextHelper collapsingTextHelper2 = this.collapsingTextHelper;
            ColorStateList colorStateList4 = this.defaultHintTextColor;
            if (collapsingTextHelper2.expandedTextColor != colorStateList4) {
                collapsingTextHelper2.expandedTextColor = colorStateList4;
                collapsingTextHelper2.recalculate(false);
            }
        }
        Editable editable = null;
        if (!isEnabled) {
            ColorStateList colorStateList5 = this.defaultHintTextColor;
            if (colorStateList5 != null) {
                i = colorStateList5.getColorForState(new int[]{-16842910}, this.disabledColor);
            } else {
                i = this.disabledColor;
            }
            this.collapsingTextHelper.setCollapsedTextColor(ColorStateList.valueOf(i));
            CollapsingTextHelper collapsingTextHelper3 = this.collapsingTextHelper;
            ColorStateList valueOf = ColorStateList.valueOf(i);
            if (collapsingTextHelper3.expandedTextColor != valueOf) {
                collapsingTextHelper3.expandedTextColor = valueOf;
                collapsingTextHelper3.recalculate(false);
            }
        } else if (shouldShowError()) {
            CollapsingTextHelper collapsingTextHelper4 = this.collapsingTextHelper;
            AppCompatTextView appCompatTextView2 = this.indicatorViewController.errorView;
            if (appCompatTextView2 != null) {
                colorStateList2 = appCompatTextView2.getTextColors();
            } else {
                colorStateList2 = null;
            }
            collapsingTextHelper4.setCollapsedTextColor(colorStateList2);
        } else if (this.counterOverflowed && (appCompatTextView = this.counterView) != null) {
            this.collapsingTextHelper.setCollapsedTextColor(appCompatTextView.getTextColors());
        } else if (z4 && (colorStateList = this.focusedTextColor) != null) {
            this.collapsingTextHelper.setCollapsedTextColor(colorStateList);
        }
        if (z3 || !this.expandedHintEnabled || (isEnabled() && z4)) {
            if (z2 || this.hintExpanded) {
                ValueAnimator valueAnimator = this.animator;
                if (valueAnimator != null && valueAnimator.isRunning()) {
                    this.animator.cancel();
                }
                if (!z || !this.hintAnimationEnabled) {
                    this.collapsingTextHelper.setExpansionFraction(1.0f);
                } else {
                    animateToExpansionFraction(1.0f);
                }
                this.hintExpanded = false;
                if (cutoutEnabled()) {
                    openCutout();
                }
                EditText editText4 = this.editText;
                if (editText4 != null) {
                    editable = editText4.getText();
                }
                updatePlaceholderText(editable);
                StartCompoundLayout startCompoundLayout = this.startLayout;
                startCompoundLayout.hintExpanded = false;
                startCompoundLayout.updateVisibility();
                EndCompoundLayout endCompoundLayout = this.endLayout;
                endCompoundLayout.hintExpanded = false;
                endCompoundLayout.updateSuffixTextVisibility();
            }
        } else if (z2 || !this.hintExpanded) {
            ValueAnimator valueAnimator2 = this.animator;
            if (valueAnimator2 != null && valueAnimator2.isRunning()) {
                this.animator.cancel();
            }
            if (!z || !this.hintAnimationEnabled) {
                this.collapsingTextHelper.setExpansionFraction(0.0f);
            } else {
                animateToExpansionFraction(0.0f);
            }
            if (cutoutEnabled() && (!((CutoutDrawable) this.boxBackground).cutoutBounds.isEmpty()) && cutoutEnabled()) {
                ((CutoutDrawable) this.boxBackground).setCutout(0.0f, 0.0f, 0.0f, 0.0f);
            }
            this.hintExpanded = true;
            AppCompatTextView appCompatTextView3 = this.placeholderTextView;
            if (appCompatTextView3 != null && this.placeholderEnabled) {
                appCompatTextView3.setText((CharSequence) null);
                TransitionManager.beginDelayedTransition(this.inputFrame, this.placeholderFadeOut);
                this.placeholderTextView.setVisibility(4);
            }
            StartCompoundLayout startCompoundLayout2 = this.startLayout;
            startCompoundLayout2.hintExpanded = true;
            startCompoundLayout2.updateVisibility();
            EndCompoundLayout endCompoundLayout2 = this.endLayout;
            endCompoundLayout2.hintExpanded = true;
            endCompoundLayout2.updateSuffixTextVisibility();
        }
    }

    public final void updatePlaceholderText(Editable editable) {
        this.lengthCounter.getClass();
        if ((editable != null && editable.length() != 0) || this.hintExpanded) {
            AppCompatTextView appCompatTextView = this.placeholderTextView;
            if (appCompatTextView != null && this.placeholderEnabled) {
                appCompatTextView.setText((CharSequence) null);
                TransitionManager.beginDelayedTransition(this.inputFrame, this.placeholderFadeOut);
                this.placeholderTextView.setVisibility(4);
            }
        } else if (this.placeholderTextView != null && this.placeholderEnabled && !TextUtils.isEmpty(this.placeholderText)) {
            this.placeholderTextView.setText(this.placeholderText);
            TransitionManager.beginDelayedTransition(this.inputFrame, this.placeholderFadeIn);
            this.placeholderTextView.setVisibility(0);
            this.placeholderTextView.bringToFront();
            announceForAccessibility(this.placeholderText);
        }
    }

    public final void updateStrokeErrorColor(boolean z, boolean z2) {
        int defaultColor = this.strokeErrorColor.getDefaultColor();
        int colorForState = this.strokeErrorColor.getColorForState(new int[]{16843623, 16842910}, defaultColor);
        int colorForState2 = this.strokeErrorColor.getColorForState(new int[]{16843518, 16842910}, defaultColor);
        if (z) {
            this.boxStrokeColor = colorForState2;
        } else if (z2) {
            this.boxStrokeColor = colorForState;
        } else {
            this.boxStrokeColor = defaultColor;
        }
    }

    public final void updateTextInputBoxState() {
        boolean z;
        AppCompatTextView appCompatTextView;
        int i;
        EditText editText2;
        EditText editText3;
        if (this.boxBackground != null && this.boxBackgroundMode != 0) {
            boolean z2 = false;
            if (isFocused() || ((editText3 = this.editText) != null && editText3.hasFocus())) {
                z = true;
            } else {
                z = false;
            }
            if (isHovered() || ((editText2 = this.editText) != null && editText2.isHovered())) {
                z2 = true;
            }
            int i2 = -1;
            if (!isEnabled()) {
                this.boxStrokeColor = this.disabledColor;
            } else if (shouldShowError()) {
                if (this.strokeErrorColor != null) {
                    updateStrokeErrorColor(z, z2);
                } else {
                    AppCompatTextView appCompatTextView2 = this.indicatorViewController.errorView;
                    if (appCompatTextView2 != null) {
                        i = appCompatTextView2.getCurrentTextColor();
                    } else {
                        i = -1;
                    }
                    this.boxStrokeColor = i;
                }
            } else if (!this.counterOverflowed || (appCompatTextView = this.counterView) == null) {
                if (z) {
                    this.boxStrokeColor = this.focusedStrokeColor;
                } else if (z2) {
                    this.boxStrokeColor = this.hoveredStrokeColor;
                } else {
                    this.boxStrokeColor = this.defaultStrokeColor;
                }
            } else if (this.strokeErrorColor != null) {
                updateStrokeErrorColor(z, z2);
            } else {
                this.boxStrokeColor = appCompatTextView.getCurrentTextColor();
            }
            EndCompoundLayout endCompoundLayout = this.endLayout;
            endCompoundLayout.updateErrorIconVisibility();
            IconHelper.refreshIconDrawableState(endCompoundLayout.textInputLayout, endCompoundLayout.errorIconView, endCompoundLayout.errorIconTintList);
            IconHelper.refreshIconDrawableState(endCompoundLayout.textInputLayout, endCompoundLayout.endIconView, endCompoundLayout.endIconTintList);
            if (endCompoundLayout.getEndIconDelegate() instanceof DropdownMenuEndIconDelegate) {
                if (!endCompoundLayout.textInputLayout.shouldShowError() || endCompoundLayout.endIconView.getDrawable() == null) {
                    IconHelper.applyIconTint(endCompoundLayout.textInputLayout, endCompoundLayout.endIconView, endCompoundLayout.endIconTintList, endCompoundLayout.endIconTintMode);
                } else {
                    Drawable mutate = endCompoundLayout.endIconView.getDrawable().mutate();
                    AppCompatTextView appCompatTextView3 = endCompoundLayout.textInputLayout.indicatorViewController.errorView;
                    if (appCompatTextView3 != null) {
                        i2 = appCompatTextView3.getCurrentTextColor();
                    }
                    mutate.setTint(i2);
                    endCompoundLayout.endIconView.setImageDrawable(mutate);
                }
            }
            StartCompoundLayout startCompoundLayout = this.startLayout;
            IconHelper.refreshIconDrawableState(startCompoundLayout.textInputLayout, startCompoundLayout.startIconView, startCompoundLayout.startIconTintList);
            if (this.boxBackgroundMode == 2) {
                int i3 = this.boxStrokeWidthPx;
                if (!z || !isEnabled()) {
                    this.boxStrokeWidthPx = this.boxStrokeWidthDefaultPx;
                } else {
                    this.boxStrokeWidthPx = this.boxStrokeWidthFocusedPx;
                }
                if (this.boxStrokeWidthPx != i3 && cutoutEnabled() && !this.hintExpanded) {
                    if (cutoutEnabled()) {
                        ((CutoutDrawable) this.boxBackground).setCutout(0.0f, 0.0f, 0.0f, 0.0f);
                    }
                    openCutout();
                }
            }
            if (this.boxBackgroundMode == 1) {
                if (!isEnabled()) {
                    this.boxBackgroundColor = this.disabledFilledBackgroundColor;
                } else if (z2 && !z) {
                    this.boxBackgroundColor = this.hoveredFilledBackgroundColor;
                } else if (z) {
                    this.boxBackgroundColor = this.focusedFilledBackgroundColor;
                } else {
                    this.boxBackgroundColor = this.defaultFilledBackgroundColor;
                }
            }
            applyBoxAttributes();
        }
    }

    public TextInputLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 2130970226);
    }

    /* JADX WARNING: type inference failed for: r1v2, types: [androidx.core.view.DifferentialMotionFlingController$$ExternalSyntheticLambda0, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r3v87, types: [androidx.transition.Transition, androidx.transition.Fade, androidx.transition.Visibility] */
    /* JADX WARNING: type inference failed for: r3v88, types: [androidx.transition.Transition, androidx.transition.Fade, androidx.transition.Visibility] */
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public TextInputLayout(android.content.Context r21, android.util.AttributeSet r22, int r23) {
        /*
            r20 = this;
            r0 = r20
            r7 = r22
            r8 = r23
            r9 = 2132018531(0x7f140563, float:1.9675371E38)
            r1 = r21
            android.content.Context r1 = com.google.android.material.theme.overlay.MaterialThemeOverlay.wrap(r1, r7, r8, r9)
            r0.<init>(r1, r7, r8)
            r10 = -1
            r0.minEms = r10
            r0.maxEms = r10
            r0.minWidth = r10
            r0.maxWidth = r10
            com.google.android.material.textfield.IndicatorViewController r11 = new com.google.android.material.textfield.IndicatorViewController
            r11.<init>(r0)
            r0.indicatorViewController = r11
            androidx.core.view.DifferentialMotionFlingController$$ExternalSyntheticLambda0 r1 = new androidx.core.view.DifferentialMotionFlingController$$ExternalSyntheticLambda0
            r1.<init>()
            r0.lengthCounter = r1
            android.graphics.Rect r1 = new android.graphics.Rect
            r1.<init>()
            r0.tmpRect = r1
            android.graphics.Rect r1 = new android.graphics.Rect
            r1.<init>()
            r0.tmpBoundsRect = r1
            android.graphics.RectF r1 = new android.graphics.RectF
            r1.<init>()
            r0.tmpRectF = r1
            java.util.LinkedHashSet r1 = new java.util.LinkedHashSet
            r1.<init>()
            r0.editTextAttachedListeners = r1
            com.google.android.material.internal.CollapsingTextHelper r12 = new com.google.android.material.internal.CollapsingTextHelper
            r12.<init>(r0)
            r0.collapsingTextHelper = r12
            android.content.Context r13 = r20.getContext()
            r14 = 1
            r0.setOrientation(r14)
            r15 = 0
            r0.setWillNotDraw(r15)
            r0.setAddStatesFromChildren(r14)
            android.widget.FrameLayout r6 = new android.widget.FrameLayout
            r6.<init>(r13)
            r0.inputFrame = r6
            r6.setAddStatesFromChildren(r14)
            android.animation.TimeInterpolator r5 = com.google.android.material.animation.AnimationUtils.LINEAR_INTERPOLATOR
            r12.textSizeInterpolator = r5
            r12.recalculate(r15)
            r12.positionInterpolator = r5
            r12.recalculate(r15)
            int r1 = r12.collapsedTextGravity
            r2 = 8388659(0x800033, float:1.1755015E-38)
            if (r1 == r2) goto L_0x007d
            r12.collapsedTextGravity = r2
            r12.recalculate(r15)
        L_0x007d:
            int[] r3 = com.google.android.material.R$styleable.TextInputLayout
            r4 = 22
            r2 = 20
            r1 = 35
            r15 = 40
            r9 = 44
            int[] r16 = new int[]{r4, r2, r1, r15, r9}
            r17 = 2132018531(0x7f140563, float:1.9675371E38)
            r15 = r1
            r1 = r13
            r2 = r22
            r4 = r23
            r18 = r5
            r5 = r17
            r19 = r6
            r6 = r16
            androidx.appcompat.widget.TintTypedArray r1 = com.google.android.material.internal.ThemeEnforcement.obtainTintedStyledAttributes(r1, r2, r3, r4, r5, r6)
            com.google.android.material.textfield.StartCompoundLayout r2 = new com.google.android.material.textfield.StartCompoundLayout
            r2.<init>(r0, r1)
            r0.startLayout = r2
            r3 = 43
            android.content.res.TypedArray r4 = r1.mWrapped
            boolean r3 = r4.getBoolean(r3, r14)
            r0.hintEnabled = r3
            r3 = 4
            java.lang.CharSequence r3 = r4.getText(r3)
            r0.setHint(r3)
            r3 = 42
            boolean r3 = r4.getBoolean(r3, r14)
            r0.hintAnimationEnabled = r3
            r3 = 37
            boolean r3 = r4.getBoolean(r3, r14)
            r0.expandedHintEnabled = r3
            r3 = 6
            boolean r5 = r4.hasValue(r3)
            if (r5 == 0) goto L_0x00e2
            int r3 = r4.getInt(r3, r10)
            r0.minEms = r3
            android.widget.EditText r5 = r0.editText
            if (r5 == 0) goto L_0x00f8
            if (r3 == r10) goto L_0x00f8
            r5.setMinEms(r3)
            goto L_0x00f8
        L_0x00e2:
            r3 = 3
            boolean r5 = r4.hasValue(r3)
            if (r5 == 0) goto L_0x00f8
            int r3 = r4.getDimensionPixelSize(r3, r10)
            r0.minWidth = r3
            android.widget.EditText r5 = r0.editText
            if (r5 == 0) goto L_0x00f8
            if (r3 == r10) goto L_0x00f8
            r5.setMinWidth(r3)
        L_0x00f8:
            r3 = 5
            boolean r5 = r4.hasValue(r3)
            r6 = 2
            if (r5 == 0) goto L_0x0113
            int r3 = r4.getInt(r3, r10)
            r0.maxEms = r3
            android.widget.EditText r5 = r0.editText
            if (r5 == 0) goto L_0x010f
            if (r3 == r10) goto L_0x010f
            r5.setMaxEms(r3)
        L_0x010f:
            r3 = 2132018531(0x7f140563, float:1.9675371E38)
            goto L_0x0129
        L_0x0113:
            boolean r3 = r4.hasValue(r6)
            if (r3 == 0) goto L_0x010f
            int r3 = r4.getDimensionPixelSize(r6, r10)
            r0.maxWidth = r3
            android.widget.EditText r5 = r0.editText
            if (r5 == 0) goto L_0x010f
            if (r3 == r10) goto L_0x010f
            r5.setMaxWidth(r3)
            goto L_0x010f
        L_0x0129:
            com.google.android.material.shape.ShapeAppearanceModel$Builder r3 = com.google.android.material.shape.ShapeAppearanceModel.builder((android.content.Context) r13, (android.util.AttributeSet) r7, (int) r8, (int) r3)
            com.google.android.material.shape.ShapeAppearanceModel r3 = r3.build()
            r0.shapeAppearanceModel = r3
            android.content.res.Resources r3 = r13.getResources()
            r5 = 2131166901(0x7f0706b5, float:1.794806E38)
            int r3 = r3.getDimensionPixelOffset(r5)
            r0.boxLabelCutoutPaddingPx = r3
            r3 = 9
            r5 = 0
            int r3 = r4.getDimensionPixelOffset(r3, r5)
            r0.boxCollapsedPaddingTopPx = r3
            android.content.res.Resources r3 = r13.getResources()
            r5 = 2131166902(0x7f0706b6, float:1.7948062E38)
            int r3 = r3.getDimensionPixelSize(r5)
            r5 = 16
            int r3 = r4.getDimensionPixelSize(r5, r3)
            r0.boxStrokeWidthDefaultPx = r3
            android.content.res.Resources r5 = r13.getResources()
            r7 = 2131166903(0x7f0706b7, float:1.7948064E38)
            int r5 = r5.getDimensionPixelSize(r7)
            r7 = 17
            int r5 = r4.getDimensionPixelSize(r7, r5)
            r0.boxStrokeWidthFocusedPx = r5
            r0.boxStrokeWidthPx = r3
            r3 = 13
            r5 = -1082130432(0xffffffffbf800000, float:-1.0)
            float r3 = r4.getDimension(r3, r5)
            r7 = 12
            float r7 = r4.getDimension(r7, r5)
            r8 = 10
            float r8 = r4.getDimension(r8, r5)
            r6 = 11
            float r5 = r4.getDimension(r6, r5)
            com.google.android.material.shape.ShapeAppearanceModel r6 = r0.shapeAppearanceModel
            com.google.android.material.shape.ShapeAppearanceModel$Builder r6 = r6.toBuilder()
            r16 = 0
            int r17 = (r3 > r16 ? 1 : (r3 == r16 ? 0 : -1))
            if (r17 < 0) goto L_0x019e
            com.google.android.material.shape.AbsoluteCornerSize r15 = new com.google.android.material.shape.AbsoluteCornerSize
            r15.<init>(r3)
            r6.topLeftCornerSize = r15
        L_0x019e:
            int r3 = (r7 > r16 ? 1 : (r7 == r16 ? 0 : -1))
            if (r3 < 0) goto L_0x01a9
            com.google.android.material.shape.AbsoluteCornerSize r3 = new com.google.android.material.shape.AbsoluteCornerSize
            r3.<init>(r7)
            r6.topRightCornerSize = r3
        L_0x01a9:
            int r3 = (r8 > r16 ? 1 : (r8 == r16 ? 0 : -1))
            if (r3 < 0) goto L_0x01b4
            com.google.android.material.shape.AbsoluteCornerSize r3 = new com.google.android.material.shape.AbsoluteCornerSize
            r3.<init>(r8)
            r6.bottomRightCornerSize = r3
        L_0x01b4:
            int r3 = (r5 > r16 ? 1 : (r5 == r16 ? 0 : -1))
            if (r3 < 0) goto L_0x01bf
            com.google.android.material.shape.AbsoluteCornerSize r3 = new com.google.android.material.shape.AbsoluteCornerSize
            r3.<init>(r5)
            r6.bottomLeftCornerSize = r3
        L_0x01bf:
            com.google.android.material.shape.ShapeAppearanceModel r3 = r6.build()
            r0.shapeAppearanceModel = r3
            r3 = 7
            android.content.res.ColorStateList r3 = com.google.android.material.resources.MaterialResources.getColorStateList((android.content.Context) r13, (androidx.appcompat.widget.TintTypedArray) r1, (int) r3)
            r5 = 16842908(0x101009c, float:2.3693995E-38)
            r6 = 16842910(0x101009e, float:2.3694E-38)
            r7 = 16843623(0x1010367, float:2.3696E-38)
            r8 = -16842910(0xfffffffffefeff62, float:-1.6947497E38)
            if (r3 == 0) goto L_0x0223
            int r15 = r3.getDefaultColor()
            r0.defaultFilledBackgroundColor = r15
            r0.boxBackgroundColor = r15
            boolean r16 = r3.isStateful()
            if (r16 == 0) goto L_0x0205
            int[] r15 = new int[]{r8}
            int r15 = r3.getColorForState(r15, r10)
            r0.disabledFilledBackgroundColor = r15
            int[] r15 = new int[]{r5, r6}
            int r15 = r3.getColorForState(r15, r10)
            r0.focusedFilledBackgroundColor = r15
            int[] r15 = new int[]{r7, r6}
            int r3 = r3.getColorForState(r15, r10)
            r0.hoveredFilledBackgroundColor = r3
            goto L_0x022e
        L_0x0205:
            r0.focusedFilledBackgroundColor = r15
            r3 = 2131100472(0x7f060338, float:1.7813326E38)
            android.content.res.ColorStateList r3 = androidx.core.content.ContextCompat.getColorStateList(r3, r13)
            int[] r15 = new int[]{r8}
            int r15 = r3.getColorForState(r15, r10)
            r0.disabledFilledBackgroundColor = r15
            int[] r15 = new int[]{r7}
            int r3 = r3.getColorForState(r15, r10)
            r0.hoveredFilledBackgroundColor = r3
            goto L_0x022e
        L_0x0223:
            r3 = 0
            r0.boxBackgroundColor = r3
            r0.defaultFilledBackgroundColor = r3
            r0.disabledFilledBackgroundColor = r3
            r0.focusedFilledBackgroundColor = r3
            r0.hoveredFilledBackgroundColor = r3
        L_0x022e:
            boolean r3 = r4.hasValue(r14)
            if (r3 == 0) goto L_0x023c
            android.content.res.ColorStateList r3 = r1.getColorStateList(r14)
            r0.focusedTextColor = r3
            r0.defaultHintTextColor = r3
        L_0x023c:
            r3 = 14
            android.content.res.ColorStateList r15 = com.google.android.material.resources.MaterialResources.getColorStateList((android.content.Context) r13, (androidx.appcompat.widget.TintTypedArray) r1, (int) r3)
            r14 = 0
            int r3 = r4.getColor(r3, r14)
            r0.focusedStrokeColor = r3
            java.lang.Object r3 = androidx.core.content.ContextCompat.sLock
            r3 = 2131100499(0x7f060353, float:1.7813381E38)
            int r3 = r13.getColor(r3)
            r0.defaultStrokeColor = r3
            r3 = 2131100500(0x7f060354, float:1.7813383E38)
            int r3 = r13.getColor(r3)
            r0.disabledColor = r3
            r3 = 2131100503(0x7f060357, float:1.781339E38)
            int r3 = r13.getColor(r3)
            r0.hoveredStrokeColor = r3
            if (r15 == 0) goto L_0x02a4
            boolean r3 = r15.isStateful()
            if (r3 == 0) goto L_0x0293
            int r3 = r15.getDefaultColor()
            r0.defaultStrokeColor = r3
            int[] r3 = new int[]{r8}
            int r3 = r15.getColorForState(r3, r10)
            r0.disabledColor = r3
            int[] r3 = new int[]{r7, r6}
            int r3 = r15.getColorForState(r3, r10)
            r0.hoveredStrokeColor = r3
            int[] r3 = new int[]{r5, r6}
            int r3 = r15.getColorForState(r3, r10)
            r0.focusedStrokeColor = r3
            goto L_0x02a1
        L_0x0293:
            int r3 = r0.focusedStrokeColor
            int r5 = r15.getDefaultColor()
            if (r3 == r5) goto L_0x02a1
            int r3 = r15.getDefaultColor()
            r0.focusedStrokeColor = r3
        L_0x02a1:
            r20.updateTextInputBoxState()
        L_0x02a4:
            r3 = 15
            boolean r5 = r4.hasValue(r3)
            if (r5 == 0) goto L_0x02b9
            android.content.res.ColorStateList r3 = com.google.android.material.resources.MaterialResources.getColorStateList((android.content.Context) r13, (androidx.appcompat.widget.TintTypedArray) r1, (int) r3)
            android.content.res.ColorStateList r5 = r0.strokeErrorColor
            if (r5 == r3) goto L_0x02b9
            r0.strokeErrorColor = r3
            r20.updateTextInputBoxState()
        L_0x02b9:
            int r3 = r4.getResourceId(r9, r10)
            if (r3 == r10) goto L_0x02d8
            r3 = 0
            int r5 = r4.getResourceId(r9, r3)
            r12.setCollapsedTextAppearance(r5)
            android.content.res.ColorStateList r5 = r12.collapsedTextColor
            r0.focusedTextColor = r5
            android.widget.EditText r5 = r0.editText
            if (r5 == 0) goto L_0x02d5
            r0.updateLabelState(r3, r3)
            r20.updateInputLayoutMargins()
        L_0x02d5:
            r5 = 35
            goto L_0x02da
        L_0x02d8:
            r3 = 0
            goto L_0x02d5
        L_0x02da:
            int r5 = r4.getResourceId(r5, r3)
            r6 = 30
            java.lang.CharSequence r6 = r4.getText(r6)
            r7 = 31
            boolean r7 = r4.getBoolean(r7, r3)
            r8 = 40
            int r8 = r4.getResourceId(r8, r3)
            r9 = 39
            boolean r9 = r4.getBoolean(r9, r3)
            r13 = 38
            java.lang.CharSequence r13 = r4.getText(r13)
            r14 = 52
            int r14 = r4.getResourceId(r14, r3)
            r15 = 51
            java.lang.CharSequence r15 = r4.getText(r15)
            r10 = 18
            boolean r10 = r4.getBoolean(r10, r3)
            r3 = 19
            r22 = r13
            r13 = -1
            int r3 = r4.getInt(r3, r13)
            int r13 = r0.counterMaxLength
            r23 = r10
            if (r13 == r3) goto L_0x033a
            if (r3 <= 0) goto L_0x0322
            r0.counterMaxLength = r3
            goto L_0x0325
        L_0x0322:
            r3 = -1
            r0.counterMaxLength = r3
        L_0x0325:
            boolean r3 = r0.counterEnabled
            if (r3 == 0) goto L_0x033a
            androidx.appcompat.widget.AppCompatTextView r3 = r0.counterView
            if (r3 == 0) goto L_0x033a
            android.widget.EditText r3 = r0.editText
            if (r3 != 0) goto L_0x0333
            r3 = 0
            goto L_0x0337
        L_0x0333:
            android.text.Editable r3 = r3.getText()
        L_0x0337:
            r0.updateCounter(r3)
        L_0x033a:
            r3 = 0
            r13 = 22
            int r13 = r4.getResourceId(r13, r3)
            r0.counterTextAppearance = r13
            r13 = 20
            int r13 = r4.getResourceId(r13, r3)
            r0.counterOverflowTextAppearance = r13
            r13 = 8
            int r13 = r4.getInt(r13, r3)
            int r3 = r0.boxBackgroundMode
            if (r13 != r3) goto L_0x0356
            goto L_0x035f
        L_0x0356:
            r0.boxBackgroundMode = r13
            android.widget.EditText r3 = r0.editText
            if (r3 == 0) goto L_0x035f
            r20.onApplyBoxBackgroundMode()
        L_0x035f:
            r11.errorViewContentDescription = r6
            androidx.appcompat.widget.AppCompatTextView r3 = r11.errorView
            if (r3 == 0) goto L_0x0368
            r3.setContentDescription(r6)
        L_0x0368:
            r11.helperTextTextAppearance = r8
            androidx.appcompat.widget.AppCompatTextView r3 = r11.helperTextView
            if (r3 == 0) goto L_0x0371
            r3.setTextAppearance(r8)
        L_0x0371:
            r11.errorTextAppearance = r5
            androidx.appcompat.widget.AppCompatTextView r3 = r11.errorView
            if (r3 == 0) goto L_0x037c
            com.google.android.material.textfield.TextInputLayout r6 = r11.textInputView
            r6.setTextAppearanceCompatWithErrorFallback(r5, r3)
        L_0x037c:
            androidx.appcompat.widget.AppCompatTextView r3 = r0.placeholderTextView
            if (r3 != 0) goto L_0x03c4
            androidx.appcompat.widget.AppCompatTextView r3 = new androidx.appcompat.widget.AppCompatTextView
            android.content.Context r5 = r20.getContext()
            r3.<init>(r5)
            r0.placeholderTextView = r3
            r5 = 2131363821(0x7f0a07ed, float:1.8347462E38)
            r3.setId(r5)
            androidx.appcompat.widget.AppCompatTextView r3 = r0.placeholderTextView
            java.util.WeakHashMap r5 = androidx.core.view.ViewCompat.sViewPropertyAnimatorMap
            r5 = 2
            androidx.core.view.ViewCompat.Api16Impl.setImportantForAccessibility(r3, r5)
            androidx.transition.Fade r3 = new androidx.transition.Fade
            r3.<init>()
            r5 = 87
            r3.mDuration = r5
            r8 = r18
            r3.mInterpolator = r8
            r0.placeholderFadeIn = r3
            r13 = r11
            r10 = 67
            r3.mStartDelay = r10
            androidx.transition.Fade r3 = new androidx.transition.Fade
            r3.<init>()
            r3.mDuration = r5
            r3.mInterpolator = r8
            r0.placeholderFadeOut = r3
            int r3 = r0.placeholderTextAppearance
            r0.placeholderTextAppearance = r3
            androidx.appcompat.widget.AppCompatTextView r5 = r0.placeholderTextView
            if (r5 == 0) goto L_0x03c5
            r5.setTextAppearance(r3)
            goto L_0x03c5
        L_0x03c4:
            r13 = r11
        L_0x03c5:
            boolean r3 = android.text.TextUtils.isEmpty(r15)
            if (r3 == 0) goto L_0x03d0
            r3 = 0
            r0.setPlaceholderTextEnabled(r3)
            goto L_0x03da
        L_0x03d0:
            boolean r3 = r0.placeholderEnabled
            if (r3 != 0) goto L_0x03d8
            r3 = 1
            r0.setPlaceholderTextEnabled(r3)
        L_0x03d8:
            r0.placeholderText = r15
        L_0x03da:
            android.widget.EditText r3 = r0.editText
            if (r3 != 0) goto L_0x03e0
            r3 = 0
            goto L_0x03e4
        L_0x03e0:
            android.text.Editable r3 = r3.getText()
        L_0x03e4:
            r0.updatePlaceholderText(r3)
            r0.placeholderTextAppearance = r14
            androidx.appcompat.widget.AppCompatTextView r3 = r0.placeholderTextView
            if (r3 == 0) goto L_0x03f0
            r3.setTextAppearance(r14)
        L_0x03f0:
            r3 = 36
            boolean r5 = r4.hasValue(r3)
            if (r5 == 0) goto L_0x0409
            android.content.res.ColorStateList r3 = r1.getColorStateList(r3)
            r5 = r13
            r5.errorViewTextColor = r3
            androidx.appcompat.widget.AppCompatTextView r6 = r5.errorView
            if (r6 == 0) goto L_0x040a
            if (r3 == 0) goto L_0x040a
            r6.setTextColor(r3)
            goto L_0x040a
        L_0x0409:
            r5 = r13
        L_0x040a:
            r3 = 41
            boolean r6 = r4.hasValue(r3)
            if (r6 == 0) goto L_0x0421
            android.content.res.ColorStateList r3 = r1.getColorStateList(r3)
            r5.helperTextViewTextColor = r3
            androidx.appcompat.widget.AppCompatTextView r6 = r5.helperTextView
            if (r6 == 0) goto L_0x0421
            if (r3 == 0) goto L_0x0421
            r6.setTextColor(r3)
        L_0x0421:
            r3 = 45
            boolean r6 = r4.hasValue(r3)
            if (r6 == 0) goto L_0x0442
            android.content.res.ColorStateList r3 = r1.getColorStateList(r3)
            android.content.res.ColorStateList r6 = r0.focusedTextColor
            if (r6 == r3) goto L_0x0442
            android.content.res.ColorStateList r6 = r0.defaultHintTextColor
            if (r6 != 0) goto L_0x0438
            r12.setCollapsedTextColor(r3)
        L_0x0438:
            r0.focusedTextColor = r3
            android.widget.EditText r3 = r0.editText
            if (r3 == 0) goto L_0x0442
            r3 = 0
            r0.updateLabelState(r3, r3)
        L_0x0442:
            r3 = 23
            boolean r6 = r4.hasValue(r3)
            if (r6 == 0) goto L_0x0457
            android.content.res.ColorStateList r3 = r1.getColorStateList(r3)
            android.content.res.ColorStateList r6 = r0.counterTextColor
            if (r6 == r3) goto L_0x0457
            r0.counterTextColor = r3
            r20.updateCounterTextAppearanceAndColor()
        L_0x0457:
            r3 = 21
            boolean r6 = r4.hasValue(r3)
            if (r6 == 0) goto L_0x046c
            android.content.res.ColorStateList r3 = r1.getColorStateList(r3)
            android.content.res.ColorStateList r6 = r0.counterOverflowTextColor
            if (r6 == r3) goto L_0x046c
            r0.counterOverflowTextColor = r3
            r20.updateCounterTextAppearanceAndColor()
        L_0x046c:
            r3 = 53
            boolean r6 = r4.hasValue(r3)
            if (r6 == 0) goto L_0x0487
            android.content.res.ColorStateList r3 = r1.getColorStateList(r3)
            android.content.res.ColorStateList r6 = r0.placeholderTextColor
            if (r6 == r3) goto L_0x0487
            r0.placeholderTextColor = r3
            androidx.appcompat.widget.AppCompatTextView r6 = r0.placeholderTextView
            if (r6 == 0) goto L_0x0487
            if (r3 == 0) goto L_0x0487
            r6.setTextColor(r3)
        L_0x0487:
            com.google.android.material.textfield.EndCompoundLayout r3 = new com.google.android.material.textfield.EndCompoundLayout
            r3.<init>(r0, r1)
            r0.endLayout = r3
            r6 = 1
            r8 = 0
            boolean r4 = r4.getBoolean(r8, r6)
            r1.recycle()
            java.util.WeakHashMap r1 = androidx.core.view.ViewCompat.sViewPropertyAnimatorMap
            r1 = 2
            androidx.core.view.ViewCompat.Api16Impl.setImportantForAccessibility(r0, r1)
            androidx.core.view.ViewCompat.Api26Impl.setImportantForAutofill(r0, r6)
            r1 = r19
            r1.addView(r2)
            r1.addView(r3)
            r0.addView(r1)
            r0.setEnabled(r4)
            r0.setHelperTextEnabled(r9)
            r0.setErrorEnabled(r7)
            boolean r1 = r0.counterEnabled
            r2 = r23
            if (r1 == r2) goto L_0x050f
            if (r2 == 0) goto L_0x0504
            androidx.appcompat.widget.AppCompatTextView r1 = new androidx.appcompat.widget.AppCompatTextView
            android.content.Context r3 = r20.getContext()
            r1.<init>(r3)
            r0.counterView = r1
            r3 = 2131363818(0x7f0a07ea, float:1.8347456E38)
            r1.setId(r3)
            androidx.appcompat.widget.AppCompatTextView r1 = r0.counterView
            r3 = 1
            r1.setMaxLines(r3)
            androidx.appcompat.widget.AppCompatTextView r1 = r0.counterView
            r3 = 2
            r5.addIndicator(r3, r1)
            androidx.appcompat.widget.AppCompatTextView r1 = r0.counterView
            android.view.ViewGroup$LayoutParams r1 = r1.getLayoutParams()
            android.view.ViewGroup$MarginLayoutParams r1 = (android.view.ViewGroup.MarginLayoutParams) r1
            android.content.res.Resources r3 = r20.getResources()
            r4 = 2131166904(0x7f0706b8, float:1.7948067E38)
            int r3 = r3.getDimensionPixelOffset(r4)
            r1.setMarginStart(r3)
            r20.updateCounterTextAppearanceAndColor()
            androidx.appcompat.widget.AppCompatTextView r1 = r0.counterView
            if (r1 == 0) goto L_0x050d
            android.widget.EditText r1 = r0.editText
            if (r1 != 0) goto L_0x04fc
            r10 = 0
            goto L_0x0500
        L_0x04fc:
            android.text.Editable r10 = r1.getText()
        L_0x0500:
            r0.updateCounter(r10)
            goto L_0x050d
        L_0x0504:
            androidx.appcompat.widget.AppCompatTextView r1 = r0.counterView
            r3 = 2
            r5.removeIndicator(r3, r1)
            r1 = 0
            r0.counterView = r1
        L_0x050d:
            r0.counterEnabled = r2
        L_0x050f:
            boolean r1 = android.text.TextUtils.isEmpty(r22)
            if (r1 == 0) goto L_0x051e
            boolean r1 = r5.helperTextEnabled
            if (r1 == 0) goto L_0x0544
            r1 = 0
            r0.setHelperTextEnabled(r1)
            goto L_0x0544
        L_0x051e:
            boolean r1 = r5.helperTextEnabled
            if (r1 != 0) goto L_0x0526
            r1 = 1
            r0.setHelperTextEnabled(r1)
        L_0x0526:
            r5.cancelCaptionAnimator()
            r0 = r22
            r5.helperText = r0
            androidx.appcompat.widget.AppCompatTextView r1 = r5.helperTextView
            r1.setText(r0)
            int r1 = r5.captionDisplayed
            r2 = 2
            if (r1 == r2) goto L_0x0539
            r5.captionToShow = r2
        L_0x0539:
            int r2 = r5.captionToShow
            androidx.appcompat.widget.AppCompatTextView r3 = r5.helperTextView
            boolean r0 = r5.shouldAnimateCaptionView(r3, r0)
            r5.updateCaptionViewsVisibility(r1, r2, r0)
        L_0x0544:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }
}
