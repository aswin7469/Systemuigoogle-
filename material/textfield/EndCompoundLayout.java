package com.google.android.material.textfield;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.frameworks.stats.VendorAtomValue$1$$ExternalSyntheticOutline0;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.appcompat.app.WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityManagerCompat$TouchExplorationStateChangeListenerWrapper;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.internal.TextWatcherAdapter;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.WeakHashMap;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class EndCompoundLayout extends LinearLayout {
    public final AccessibilityManager accessibilityManager;
    public EditText editText;
    public final AnonymousClass1 editTextWatcher = new TextWatcherAdapter() {
        public final void afterTextChanged(Editable editable) {
            EndCompoundLayout.this.getEndIconDelegate().afterEditTextChanged();
        }

        public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            EndCompoundLayout.this.getEndIconDelegate().beforeEditTextChanged();
        }
    };
    public final LinkedHashSet endIconChangedListeners = new LinkedHashSet();
    public final EndIconDelegates endIconDelegates;
    public final FrameLayout endIconFrame;
    public int endIconMode = 0;
    public View.OnLongClickListener endIconOnLongClickListener;
    public final ColorStateList endIconTintList;
    public final PorterDuff.Mode endIconTintMode;
    public final CheckableImageButton endIconView;
    public final ColorStateList errorIconTintList;
    public final PorterDuff.Mode errorIconTintMode;
    public final CheckableImageButton errorIconView;
    public boolean hintExpanded;
    public final CharSequence suffixText;
    public final AppCompatTextView suffixTextView;
    public final TextInputLayout textInputLayout;
    public DropdownMenuEndIconDelegate$$ExternalSyntheticLambda5 touchExplorationStateChangeListener;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class EndIconDelegates {
        public final int customEndIconDrawableId;
        public final SparseArray delegates = new SparseArray();
        public final EndCompoundLayout endLayout;
        public final int passwordIconDrawableId;

        public EndIconDelegates(EndCompoundLayout endCompoundLayout, TintTypedArray tintTypedArray) {
            this.endLayout = endCompoundLayout;
            TypedArray typedArray = tintTypedArray.mWrapped;
            this.customEndIconDrawableId = typedArray.getResourceId(26, 0);
            this.passwordIconDrawableId = typedArray.getResourceId(47, 0);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public EndCompoundLayout(TextInputLayout textInputLayout2, TintTypedArray tintTypedArray) {
        super(textInputLayout2.getContext());
        CharSequence text;
        TextInputLayout textInputLayout3 = textInputLayout2;
        TintTypedArray tintTypedArray2 = tintTypedArray;
        AnonymousClass2 r4 = new Object() {
            public final void onEditTextAttached(TextInputLayout textInputLayout) {
                EndCompoundLayout endCompoundLayout = EndCompoundLayout.this;
                EditText editText = endCompoundLayout.editText;
                if (editText != textInputLayout.editText) {
                    if (editText != null) {
                        editText.removeTextChangedListener(endCompoundLayout.editTextWatcher);
                        if (endCompoundLayout.editText.getOnFocusChangeListener() == endCompoundLayout.getEndIconDelegate().getOnEditTextFocusChangeListener()) {
                            endCompoundLayout.editText.setOnFocusChangeListener((View.OnFocusChangeListener) null);
                        }
                    }
                    EditText editText2 = textInputLayout.editText;
                    endCompoundLayout.editText = editText2;
                    if (editText2 != null) {
                        editText2.addTextChangedListener(endCompoundLayout.editTextWatcher);
                    }
                    endCompoundLayout.getEndIconDelegate().onEditTextAttached(endCompoundLayout.editText);
                    endCompoundLayout.setOnFocusChangeListenersIfNeeded(endCompoundLayout.getEndIconDelegate());
                }
            }
        };
        this.accessibilityManager = (AccessibilityManager) getContext().getSystemService("accessibility");
        this.textInputLayout = textInputLayout3;
        setVisibility(8);
        setOrientation(0);
        setLayoutParams(new FrameLayout.LayoutParams(-2, -1, 8388613));
        FrameLayout frameLayout = new FrameLayout(getContext());
        this.endIconFrame = frameLayout;
        frameLayout.setVisibility(8);
        frameLayout.setLayoutParams(new LinearLayout.LayoutParams(-2, -1));
        LayoutInflater from = LayoutInflater.from(getContext());
        CheckableImageButton createIconView = createIconView(2131363811, from, this);
        this.errorIconView = createIconView;
        CheckableImageButton createIconView2 = createIconView(2131363810, from, frameLayout);
        this.endIconView = createIconView2;
        this.endIconDelegates = new EndIconDelegates(this, tintTypedArray2);
        AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
        this.suffixTextView = appCompatTextView;
        TypedArray typedArray = tintTypedArray2.mWrapped;
        if (typedArray.hasValue(33)) {
            this.errorIconTintList = MaterialResources.getColorStateList(getContext(), tintTypedArray2, 33);
        }
        CharSequence charSequence = null;
        if (typedArray.hasValue(34)) {
            this.errorIconTintMode = ViewUtils.parseTintMode(typedArray.getInt(34, -1), (PorterDuff.Mode) null);
        }
        if (typedArray.hasValue(32)) {
            createIconView.setImageDrawable(tintTypedArray2.getDrawable(32));
            updateErrorIconVisibility();
            IconHelper.applyIconTint(textInputLayout3, createIconView, this.errorIconTintList, this.errorIconTintMode);
        }
        createIconView.setContentDescription(getResources().getText(2131952507));
        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
        ViewCompat.Api16Impl.setImportantForAccessibility(createIconView, 2);
        createIconView.setClickable(false);
        createIconView.pressable = false;
        createIconView.setFocusable(false);
        if (!typedArray.hasValue(48)) {
            if (typedArray.hasValue(28)) {
                this.endIconTintList = MaterialResources.getColorStateList(getContext(), tintTypedArray2, 28);
            }
            if (typedArray.hasValue(29)) {
                this.endIconTintMode = ViewUtils.parseTintMode(typedArray.getInt(29, -1), (PorterDuff.Mode) null);
            }
        }
        if (typedArray.hasValue(27)) {
            setEndIconMode(typedArray.getInt(27, 0));
            if (typedArray.hasValue(25) && createIconView2.getContentDescription() != (text = typedArray.getText(25))) {
                createIconView2.setContentDescription(text);
            }
            boolean z = typedArray.getBoolean(24, true);
            if (createIconView2.checkable != z) {
                createIconView2.checkable = z;
                createIconView2.sendAccessibilityEvent(0);
            }
        } else if (typedArray.hasValue(48)) {
            if (typedArray.hasValue(49)) {
                this.endIconTintList = MaterialResources.getColorStateList(getContext(), tintTypedArray2, 49);
            }
            if (typedArray.hasValue(50)) {
                this.endIconTintMode = ViewUtils.parseTintMode(typedArray.getInt(50, -1), (PorterDuff.Mode) null);
            }
            setEndIconMode(typedArray.getBoolean(48, false) ? 1 : 0);
            CharSequence text2 = typedArray.getText(46);
            if (createIconView2.getContentDescription() != text2) {
                createIconView2.setContentDescription(text2);
            }
        }
        appCompatTextView.setVisibility(8);
        appCompatTextView.setId(2131363823);
        appCompatTextView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2, 80.0f));
        ViewCompat.Api19Impl.setAccessibilityLiveRegion(appCompatTextView, 1);
        appCompatTextView.setTextAppearance(typedArray.getResourceId(65, 0));
        if (typedArray.hasValue(66)) {
            appCompatTextView.setTextColor(tintTypedArray2.getColorStateList(66));
        }
        CharSequence text3 = typedArray.getText(64);
        this.suffixText = !TextUtils.isEmpty(text3) ? text3 : charSequence;
        appCompatTextView.setText(text3);
        updateSuffixTextVisibility();
        frameLayout.addView(createIconView2);
        addView(appCompatTextView);
        addView(frameLayout);
        addView(createIconView);
        textInputLayout3.editTextAttachedListeners.add(r4);
        if (textInputLayout3.editText != null) {
            r4.onEditTextAttached(textInputLayout3);
        }
        addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            public final void onViewAttachedToWindow(View view) {
                EndCompoundLayout.this.addTouchExplorationStateChangeListenerIfNeeded();
            }

            public final void onViewDetachedFromWindow(View view) {
                AccessibilityManager accessibilityManager;
                EndCompoundLayout endCompoundLayout = EndCompoundLayout.this;
                DropdownMenuEndIconDelegate$$ExternalSyntheticLambda5 dropdownMenuEndIconDelegate$$ExternalSyntheticLambda5 = endCompoundLayout.touchExplorationStateChangeListener;
                if (dropdownMenuEndIconDelegate$$ExternalSyntheticLambda5 != null && (accessibilityManager = endCompoundLayout.accessibilityManager) != null) {
                    accessibilityManager.removeTouchExplorationStateChangeListener(new AccessibilityManagerCompat$TouchExplorationStateChangeListenerWrapper(dropdownMenuEndIconDelegate$$ExternalSyntheticLambda5));
                }
            }
        });
    }

    public final void addTouchExplorationStateChangeListenerIfNeeded() {
        if (this.touchExplorationStateChangeListener != null && this.accessibilityManager != null) {
            WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
            if (ViewCompat.Api19Impl.isAttachedToWindow(this)) {
                this.accessibilityManager.addTouchExplorationStateChangeListener(new AccessibilityManagerCompat$TouchExplorationStateChangeListenerWrapper(this.touchExplorationStateChangeListener));
            }
        }
    }

    public final CheckableImageButton createIconView(int i, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        CheckableImageButton checkableImageButton = (CheckableImageButton) layoutInflater.inflate(2131558569, viewGroup, false);
        checkableImageButton.setId(i);
        if (MaterialResources.isFontScaleAtLeast1_3(getContext())) {
            ((ViewGroup.MarginLayoutParams) checkableImageButton.getLayoutParams()).setMarginStart(0);
        }
        return checkableImageButton;
    }

    public final EndIconDelegate getEndIconDelegate() {
        EndIconDelegate endIconDelegate;
        EndIconDelegates endIconDelegates2 = this.endIconDelegates;
        int i = this.endIconMode;
        SparseArray sparseArray = endIconDelegates2.delegates;
        EndIconDelegate endIconDelegate2 = (EndIconDelegate) sparseArray.get(i);
        if (endIconDelegate2 == null) {
            EndCompoundLayout endCompoundLayout = endIconDelegates2.endLayout;
            if (i == -1) {
                endIconDelegate = new EndIconDelegate(endCompoundLayout);
            } else if (i == 0) {
                endIconDelegate = new EndIconDelegate(endCompoundLayout);
            } else if (i == 1) {
                endIconDelegate2 = new PasswordToggleEndIconDelegate(endCompoundLayout, endIconDelegates2.passwordIconDrawableId);
                sparseArray.append(i, endIconDelegate2);
            } else if (i == 2) {
                endIconDelegate = new ClearTextEndIconDelegate(endCompoundLayout);
            } else if (i == 3) {
                endIconDelegate = new DropdownMenuEndIconDelegate(endCompoundLayout);
            } else {
                throw new IllegalArgumentException(VendorAtomValue$1$$ExternalSyntheticOutline0.m("Invalid end icon mode: ", i));
            }
            endIconDelegate2 = endIconDelegate;
            sparseArray.append(i, endIconDelegate2);
        }
        return endIconDelegate2;
    }

    public final boolean isEndIconVisible() {
        if (this.endIconFrame.getVisibility() == 0 && this.endIconView.getVisibility() == 0) {
            return true;
        }
        return false;
    }

    public final boolean isErrorIconVisible() {
        if (this.errorIconView.getVisibility() == 0) {
            return true;
        }
        return false;
    }

    public final void refreshIconState(boolean z) {
        boolean z2;
        boolean isActivated;
        boolean z3;
        EndIconDelegate endIconDelegate = getEndIconDelegate();
        boolean z4 = true;
        if (!endIconDelegate.isIconCheckable() || (z3 = this.endIconView.checked) == endIconDelegate.isIconChecked()) {
            z2 = false;
        } else {
            this.endIconView.setChecked(!z3);
            z2 = true;
        }
        if (!(endIconDelegate instanceof DropdownMenuEndIconDelegate) || (isActivated = this.endIconView.isActivated()) == endIconDelegate.isIconActivated()) {
            z4 = z2;
        } else {
            this.endIconView.setActivated(!isActivated);
        }
        if (z || z4) {
            IconHelper.refreshIconDrawableState(this.textInputLayout, this.endIconView, this.endIconTintList);
        }
    }

    public final void setEndIconMode(int i) {
        boolean z;
        Drawable drawable;
        AccessibilityManager accessibilityManager2;
        if (this.endIconMode != i) {
            EndIconDelegate endIconDelegate = getEndIconDelegate();
            DropdownMenuEndIconDelegate$$ExternalSyntheticLambda5 dropdownMenuEndIconDelegate$$ExternalSyntheticLambda5 = this.touchExplorationStateChangeListener;
            if (!(dropdownMenuEndIconDelegate$$ExternalSyntheticLambda5 == null || (accessibilityManager2 = this.accessibilityManager) == null)) {
                accessibilityManager2.removeTouchExplorationStateChangeListener(new AccessibilityManagerCompat$TouchExplorationStateChangeListenerWrapper(dropdownMenuEndIconDelegate$$ExternalSyntheticLambda5));
            }
            CharSequence charSequence = null;
            this.touchExplorationStateChangeListener = null;
            endIconDelegate.tearDown();
            this.endIconMode = i;
            Iterator it = this.endIconChangedListeners.iterator();
            if (!it.hasNext()) {
                if (i != 0) {
                    z = true;
                } else {
                    z = false;
                }
                setEndIconVisible(z);
                EndIconDelegate endIconDelegate2 = getEndIconDelegate();
                int i2 = this.endIconDelegates.customEndIconDrawableId;
                if (i2 == 0) {
                    i2 = endIconDelegate2.getIconDrawableResId();
                }
                if (i2 != 0) {
                    drawable = AppCompatResources.getDrawable(i2, getContext());
                } else {
                    drawable = null;
                }
                this.endIconView.setImageDrawable(drawable);
                if (drawable != null) {
                    IconHelper.applyIconTint(this.textInputLayout, this.endIconView, this.endIconTintList, this.endIconTintMode);
                    IconHelper.refreshIconDrawableState(this.textInputLayout, this.endIconView, this.endIconTintList);
                }
                int iconContentDescriptionResId = endIconDelegate2.getIconContentDescriptionResId();
                if (iconContentDescriptionResId != 0) {
                    charSequence = getResources().getText(iconContentDescriptionResId);
                }
                if (this.endIconView.getContentDescription() != charSequence) {
                    this.endIconView.setContentDescription(charSequence);
                }
                boolean isIconCheckable = endIconDelegate2.isIconCheckable();
                CheckableImageButton checkableImageButton = this.endIconView;
                if (checkableImageButton.checkable != isIconCheckable) {
                    checkableImageButton.checkable = isIconCheckable;
                    checkableImageButton.sendAccessibilityEvent(0);
                }
                if (endIconDelegate2.isBoxBackgroundModeSupported(this.textInputLayout.boxBackgroundMode)) {
                    endIconDelegate2.setUp();
                    this.touchExplorationStateChangeListener = endIconDelegate2.getTouchExplorationStateChangeListener();
                    addTouchExplorationStateChangeListenerIfNeeded();
                    View.OnClickListener onIconClickListener = endIconDelegate2.getOnIconClickListener();
                    CheckableImageButton checkableImageButton2 = this.endIconView;
                    View.OnLongClickListener onLongClickListener = this.endIconOnLongClickListener;
                    checkableImageButton2.setOnClickListener(onIconClickListener);
                    IconHelper.setIconClickable(checkableImageButton2, onLongClickListener);
                    EditText editText2 = this.editText;
                    if (editText2 != null) {
                        endIconDelegate2.onEditTextAttached(editText2);
                        setOnFocusChangeListenersIfNeeded(endIconDelegate2);
                    }
                    IconHelper.applyIconTint(this.textInputLayout, this.endIconView, this.endIconTintList, this.endIconTintMode);
                    refreshIconState(true);
                    return;
                }
                throw new IllegalStateException("The current box background mode " + this.textInputLayout.boxBackgroundMode + " is not supported by the end icon mode " + i);
            }
            WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(it.next());
            throw null;
        }
    }

    public final void setEndIconVisible(boolean z) {
        int i;
        if (isEndIconVisible() != z) {
            CheckableImageButton checkableImageButton = this.endIconView;
            if (z) {
                i = 0;
            } else {
                i = 8;
            }
            checkableImageButton.setVisibility(i);
            updateEndLayoutVisibility();
            updateSuffixTextViewPadding();
            this.textInputLayout.updateDummyDrawables();
        }
    }

    public final void setOnFocusChangeListenersIfNeeded(EndIconDelegate endIconDelegate) {
        if (this.editText != null) {
            if (endIconDelegate.getOnEditTextFocusChangeListener() != null) {
                this.editText.setOnFocusChangeListener(endIconDelegate.getOnEditTextFocusChangeListener());
            }
            if (endIconDelegate.getOnIconViewFocusChangeListener() != null) {
                this.endIconView.setOnFocusChangeListener(endIconDelegate.getOnIconViewFocusChangeListener());
            }
        }
    }

    public final void updateEndLayoutVisibility() {
        int i;
        boolean z;
        FrameLayout frameLayout = this.endIconFrame;
        int i2 = 8;
        if (this.endIconView.getVisibility() != 0 || isErrorIconVisible()) {
            i = 8;
        } else {
            i = 0;
        }
        frameLayout.setVisibility(i);
        if (this.suffixText == null || this.hintExpanded) {
            z = true;
        } else {
            z = false;
        }
        if (isEndIconVisible() || isErrorIconVisible() || !z) {
            i2 = 0;
        }
        setVisibility(i2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x001f  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x002f  */
    /* JADX WARNING: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void updateErrorIconVisibility() {
        /*
            r3 = this;
            com.google.android.material.internal.CheckableImageButton r0 = r3.errorIconView
            android.graphics.drawable.Drawable r0 = r0.getDrawable()
            r1 = 0
            if (r0 == 0) goto L_0x0019
            com.google.android.material.textfield.TextInputLayout r0 = r3.textInputLayout
            com.google.android.material.textfield.IndicatorViewController r2 = r0.indicatorViewController
            boolean r2 = r2.errorEnabled
            if (r2 == 0) goto L_0x0019
            boolean r0 = r0.shouldShowError()
            if (r0 == 0) goto L_0x0019
            r0 = 1
            goto L_0x001a
        L_0x0019:
            r0 = r1
        L_0x001a:
            com.google.android.material.internal.CheckableImageButton r2 = r3.errorIconView
            if (r0 == 0) goto L_0x001f
            goto L_0x0021
        L_0x001f:
            r1 = 8
        L_0x0021:
            r2.setVisibility(r1)
            r3.updateEndLayoutVisibility()
            r3.updateSuffixTextViewPadding()
            int r0 = r3.endIconMode
            if (r0 == 0) goto L_0x002f
            goto L_0x0034
        L_0x002f:
            com.google.android.material.textfield.TextInputLayout r3 = r3.textInputLayout
            r3.updateDummyDrawables()
        L_0x0034:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.EndCompoundLayout.updateErrorIconVisibility():void");
    }

    public final void updateSuffixTextViewPadding() {
        int i;
        if (this.textInputLayout.editText != null) {
            if (isEndIconVisible() || isErrorIconVisible()) {
                i = 0;
            } else {
                EditText editText2 = this.textInputLayout.editText;
                WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                i = ViewCompat.Api17Impl.getPaddingEnd(editText2);
            }
            AppCompatTextView appCompatTextView = this.suffixTextView;
            int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(2131166643);
            int paddingTop = this.textInputLayout.editText.getPaddingTop();
            int paddingBottom = this.textInputLayout.editText.getPaddingBottom();
            WeakHashMap weakHashMap2 = ViewCompat.sViewPropertyAnimatorMap;
            ViewCompat.Api17Impl.setPaddingRelative(appCompatTextView, dimensionPixelSize, paddingTop, i, paddingBottom);
        }
    }

    public final void updateSuffixTextVisibility() {
        int i;
        int visibility = this.suffixTextView.getVisibility();
        boolean z = false;
        if (this.suffixText == null || this.hintExpanded) {
            i = 8;
        } else {
            i = 0;
        }
        if (visibility != i) {
            EndIconDelegate endIconDelegate = getEndIconDelegate();
            if (i == 0) {
                z = true;
            }
            endIconDelegate.onSuffixVisibilityChanged(z);
        }
        updateEndLayoutVisibility();
        this.suffixTextView.setVisibility(i);
        this.textInputLayout.updateDummyDrawables();
    }
}
