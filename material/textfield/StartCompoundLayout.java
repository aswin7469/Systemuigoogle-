package com.google.android.material.textfield;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.view.ViewCompat;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;
import java.util.WeakHashMap;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class StartCompoundLayout extends LinearLayout {
    public boolean hintExpanded;
    public final CharSequence prefixText;
    public final AppCompatTextView prefixTextView;
    public final View.OnLongClickListener startIconOnLongClickListener;
    public final ColorStateList startIconTintList;
    public final PorterDuff.Mode startIconTintMode;
    public final CheckableImageButton startIconView;
    public final TextInputLayout textInputLayout;

    public StartCompoundLayout(TextInputLayout textInputLayout2, TintTypedArray tintTypedArray) {
        super(textInputLayout2.getContext());
        CharSequence text;
        this.textInputLayout = textInputLayout2;
        setVisibility(8);
        setOrientation(0);
        setLayoutParams(new FrameLayout.LayoutParams(-2, -1, 8388611));
        CheckableImageButton checkableImageButton = (CheckableImageButton) LayoutInflater.from(getContext()).inflate(2131558576, this, false);
        this.startIconView = checkableImageButton;
        AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
        this.prefixTextView = appCompatTextView;
        if (MaterialResources.isFontScaleAtLeast1_3(getContext())) {
            ((ViewGroup.MarginLayoutParams) checkableImageButton.getLayoutParams()).setMarginEnd(0);
        }
        View.OnLongClickListener onLongClickListener = this.startIconOnLongClickListener;
        CharSequence charSequence = null;
        checkableImageButton.setOnClickListener((View.OnClickListener) null);
        IconHelper.setIconClickable(checkableImageButton, onLongClickListener);
        this.startIconOnLongClickListener = null;
        checkableImageButton.setOnLongClickListener((View.OnLongClickListener) null);
        IconHelper.setIconClickable(checkableImageButton, (View.OnLongClickListener) null);
        TypedArray typedArray = tintTypedArray.mWrapped;
        if (typedArray.hasValue(62)) {
            this.startIconTintList = MaterialResources.getColorStateList(getContext(), tintTypedArray, 62);
        }
        if (typedArray.hasValue(63)) {
            this.startIconTintMode = ViewUtils.parseTintMode(typedArray.getInt(63, -1), (PorterDuff.Mode) null);
        }
        if (typedArray.hasValue(61)) {
            Drawable drawable = tintTypedArray.getDrawable(61);
            checkableImageButton.setImageDrawable(drawable);
            if (drawable != null) {
                IconHelper.applyIconTint(textInputLayout2, checkableImageButton, this.startIconTintList, this.startIconTintMode);
                if (checkableImageButton.getVisibility() != 0) {
                    checkableImageButton.setVisibility(0);
                    updatePrefixTextViewPadding();
                    updateVisibility();
                }
                IconHelper.refreshIconDrawableState(textInputLayout2, checkableImageButton, this.startIconTintList);
            } else {
                if (checkableImageButton.getVisibility() == 0) {
                    checkableImageButton.setVisibility(8);
                    updatePrefixTextViewPadding();
                    updateVisibility();
                }
                View.OnLongClickListener onLongClickListener2 = this.startIconOnLongClickListener;
                checkableImageButton.setOnClickListener((View.OnClickListener) null);
                IconHelper.setIconClickable(checkableImageButton, onLongClickListener2);
                this.startIconOnLongClickListener = null;
                checkableImageButton.setOnLongClickListener((View.OnLongClickListener) null);
                IconHelper.setIconClickable(checkableImageButton, (View.OnLongClickListener) null);
                if (checkableImageButton.getContentDescription() != null) {
                    checkableImageButton.setContentDescription((CharSequence) null);
                }
            }
            if (typedArray.hasValue(60) && checkableImageButton.getContentDescription() != (text = typedArray.getText(60))) {
                checkableImageButton.setContentDescription(text);
            }
            boolean z = typedArray.getBoolean(59, true);
            if (checkableImageButton.checkable != z) {
                checkableImageButton.checkable = z;
                checkableImageButton.sendAccessibilityEvent(0);
            }
        }
        appCompatTextView.setVisibility(8);
        appCompatTextView.setId(2131363856);
        appCompatTextView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
        appCompatTextView.setAccessibilityLiveRegion(1);
        appCompatTextView.setTextAppearance(typedArray.getResourceId(55, 0));
        if (typedArray.hasValue(56)) {
            appCompatTextView.setTextColor(tintTypedArray.getColorStateList(56));
        }
        CharSequence text2 = typedArray.getText(54);
        this.prefixText = !TextUtils.isEmpty(text2) ? text2 : charSequence;
        appCompatTextView.setText(text2);
        updateVisibility();
        addView(checkableImageButton);
        addView(appCompatTextView);
    }

    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        updatePrefixTextViewPadding();
    }

    public final void updatePrefixTextViewPadding() {
        int i;
        EditText editText = this.textInputLayout.editText;
        if (editText != null) {
            if (this.startIconView.getVisibility() == 0) {
                i = 0;
            } else {
                WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                i = editText.getPaddingStart();
            }
            AppCompatTextView appCompatTextView = this.prefixTextView;
            int compoundPaddingTop = editText.getCompoundPaddingTop();
            int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(2131166685);
            int compoundPaddingBottom = editText.getCompoundPaddingBottom();
            WeakHashMap weakHashMap2 = ViewCompat.sViewPropertyAnimatorMap;
            appCompatTextView.setPaddingRelative(i, compoundPaddingTop, dimensionPixelSize, compoundPaddingBottom);
        }
    }

    public final void updateVisibility() {
        int i;
        int i2 = 8;
        if (this.prefixText == null || this.hintExpanded) {
            i = 8;
        } else {
            i = 0;
        }
        if (this.startIconView.getVisibility() == 0 || i == 0) {
            i2 = 0;
        }
        setVisibility(i2);
        this.prefixTextView.setVisibility(i);
        this.textInputLayout.updateDummyDrawables();
    }
}
