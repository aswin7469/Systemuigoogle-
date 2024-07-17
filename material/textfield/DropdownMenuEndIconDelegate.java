package com.google.android.material.textfield;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.animation.AnimationUtils;
import java.util.WeakHashMap;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class DropdownMenuEndIconDelegate extends EndIconDelegate {
    public AccessibilityManager accessibilityManager;
    public AutoCompleteTextView autoCompleteTextView;
    public long dropdownPopupActivatedAt = Long.MAX_VALUE;
    public boolean dropdownPopupDirty;
    public boolean editTextHasFocus;
    public ValueAnimator fadeInAnim;
    public ValueAnimator fadeOutAnim;
    public boolean isEndIconChecked;
    public final DropdownMenuEndIconDelegate$$ExternalSyntheticLambda4 onEditTextFocusChangeListener = new DropdownMenuEndIconDelegate$$ExternalSyntheticLambda4(this);
    public final DropdownMenuEndIconDelegate$$ExternalSyntheticLambda3 onIconClickListener = new DropdownMenuEndIconDelegate$$ExternalSyntheticLambda3(this);
    public final DropdownMenuEndIconDelegate$$ExternalSyntheticLambda5 touchExplorationStateChangeListener = new DropdownMenuEndIconDelegate$$ExternalSyntheticLambda5(this);

    public DropdownMenuEndIconDelegate(EndCompoundLayout endCompoundLayout) {
        super(endCompoundLayout);
    }

    public final void afterEditTextChanged() {
        if (this.accessibilityManager.isTouchExplorationEnabled() && EditTextUtils.isEditable(this.autoCompleteTextView) && !this.endIconView.hasFocus()) {
            this.autoCompleteTextView.dismissDropDown();
        }
        this.autoCompleteTextView.post(new DropdownMenuEndIconDelegate$$ExternalSyntheticLambda6(this));
    }

    public final int getIconContentDescriptionResId() {
        return 2131952511;
    }

    public final int getIconDrawableResId() {
        return 2131233296;
    }

    public final View.OnFocusChangeListener getOnEditTextFocusChangeListener() {
        return this.onEditTextFocusChangeListener;
    }

    public final View.OnClickListener getOnIconClickListener() {
        return this.onIconClickListener;
    }

    public final DropdownMenuEndIconDelegate$$ExternalSyntheticLambda5 getTouchExplorationStateChangeListener() {
        return this.touchExplorationStateChangeListener;
    }

    public final boolean isBoxBackgroundModeSupported(int i) {
        if (i != 0) {
            return true;
        }
        return false;
    }

    public final boolean isIconActivated() {
        return this.editTextHasFocus;
    }

    public final boolean isIconChecked() {
        return this.isEndIconChecked;
    }

    public final void onEditTextAttached(EditText editText) {
        if (editText instanceof AutoCompleteTextView) {
            AutoCompleteTextView autoCompleteTextView2 = (AutoCompleteTextView) editText;
            this.autoCompleteTextView = autoCompleteTextView2;
            autoCompleteTextView2.setOnTouchListener(new DropdownMenuEndIconDelegate$$ExternalSyntheticLambda1(this));
            this.autoCompleteTextView.setOnDismissListener(new DropdownMenuEndIconDelegate$$ExternalSyntheticLambda2(this));
            this.autoCompleteTextView.setThreshold(0);
            TextInputLayout textInputLayout = this.textInputLayout;
            EndCompoundLayout endCompoundLayout = textInputLayout.endLayout;
            endCompoundLayout.errorIconView.setImageDrawable((Drawable) null);
            endCompoundLayout.updateErrorIconVisibility();
            IconHelper.applyIconTint(endCompoundLayout.textInputLayout, endCompoundLayout.errorIconView, endCompoundLayout.errorIconTintList, endCompoundLayout.errorIconTintMode);
            if (!EditTextUtils.isEditable(editText) && this.accessibilityManager.isTouchExplorationEnabled()) {
                WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                ViewCompat.Api16Impl.setImportantForAccessibility(this.endIconView, 2);
            }
            textInputLayout.endLayout.setEndIconVisible(true);
            return;
        }
        throw new RuntimeException("EditText needs to be an AutoCompleteTextView if an Exposed Dropdown Menu is being used.");
    }

    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (!EditTextUtils.isEditable(this.autoCompleteTextView)) {
            accessibilityNodeInfoCompat.setClassName(Spinner.class.getName());
        }
        AccessibilityNodeInfo accessibilityNodeInfo = accessibilityNodeInfoCompat.mInfo;
        if (accessibilityNodeInfo.isShowingHintText()) {
            accessibilityNodeInfo.setHintText((CharSequence) null);
        }
    }

    public final void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent.getEventType() == 1 && this.accessibilityManager.isEnabled() && !EditTextUtils.isEditable(this.autoCompleteTextView)) {
            showHideDropdown();
            this.dropdownPopupDirty = true;
            this.dropdownPopupActivatedAt = System.currentTimeMillis();
        }
    }

    public final void setEndIconChecked(boolean z) {
        if (this.isEndIconChecked != z) {
            this.isEndIconChecked = z;
            this.fadeInAnim.cancel();
            this.fadeOutAnim.start();
        }
    }

    public final void setUp() {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        TimeInterpolator timeInterpolator = AnimationUtils.LINEAR_INTERPOLATOR;
        ofFloat.setInterpolator(timeInterpolator);
        ofFloat.setDuration((long) 67);
        ofFloat.addUpdateListener(new DropdownMenuEndIconDelegate$$ExternalSyntheticLambda0(this));
        this.fadeInAnim = ofFloat;
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});
        ofFloat2.setInterpolator(timeInterpolator);
        ofFloat2.setDuration((long) 50);
        ofFloat2.addUpdateListener(new DropdownMenuEndIconDelegate$$ExternalSyntheticLambda0(this));
        this.fadeOutAnim = ofFloat2;
        ofFloat2.addListener(new AnimatorListenerAdapter() {
            public final void onAnimationEnd(Animator animator) {
                DropdownMenuEndIconDelegate.this.refreshIconState();
                DropdownMenuEndIconDelegate.this.fadeInAnim.start();
            }
        });
        this.accessibilityManager = (AccessibilityManager) this.context.getSystemService("accessibility");
    }

    public final void showHideDropdown() {
        boolean z;
        if (this.autoCompleteTextView != null) {
            long currentTimeMillis = System.currentTimeMillis() - this.dropdownPopupActivatedAt;
            if (currentTimeMillis < 0 || currentTimeMillis > 300) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                this.dropdownPopupDirty = false;
            }
            if (!this.dropdownPopupDirty) {
                setEndIconChecked(!this.isEndIconChecked);
                if (this.isEndIconChecked) {
                    this.autoCompleteTextView.requestFocus();
                    this.autoCompleteTextView.showDropDown();
                    return;
                }
                this.autoCompleteTextView.dismissDropDown();
                return;
            }
            this.dropdownPopupDirty = false;
        }
    }

    public final void tearDown() {
        AutoCompleteTextView autoCompleteTextView2 = this.autoCompleteTextView;
        if (autoCompleteTextView2 != null) {
            autoCompleteTextView2.setOnTouchListener((View.OnTouchListener) null);
            this.autoCompleteTextView.setOnDismissListener((AutoCompleteTextView.OnDismissListener) null);
        }
    }
}
