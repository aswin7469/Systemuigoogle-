package com.google.android.material.textfield;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.text.method.KeyListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.ListPopupWindow;
import androidx.core.graphics.ColorUtils;
import androidx.core.view.ViewCompat;
import com.google.android.material.R$styleable;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.util.Locale;
import java.util.WeakHashMap;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class MaterialAutoCompleteTextView extends AppCompatAutoCompleteTextView {
    public final AccessibilityManager accessibilityManager;
    public final ListPopupWindow modalListPopup;
    public final float popupElevation;
    public final int simpleItemSelectedColor;
    public final ColorStateList simpleItemSelectedRippleColor;
    public final Rect tempRect = new Rect();

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class MaterialArrayAdapter extends ArrayAdapter {
        public final ColorStateList pressedRippleColor;
        public final ColorStateList selectedItemRippleOverlaidColor;

        public MaterialArrayAdapter(Context context, int i, String[] strArr) {
            super(context, i, strArr);
            boolean z;
            ColorStateList colorStateList;
            ColorStateList colorStateList2;
            ColorStateList colorStateList3 = MaterialAutoCompleteTextView.this.simpleItemSelectedRippleColor;
            if (colorStateList3 != null) {
                z = true;
            } else {
                z = false;
            }
            ColorStateList colorStateList4 = null;
            if (!z) {
                colorStateList = null;
            } else {
                int[] iArr = {16842919};
                colorStateList = new ColorStateList(new int[][]{iArr, new int[0]}, new int[]{colorStateList3.getColorForState(iArr, 0), 0});
            }
            this.pressedRippleColor = colorStateList;
            if (!(MaterialAutoCompleteTextView.this.simpleItemSelectedColor == 0 || (colorStateList2 = MaterialAutoCompleteTextView.this.simpleItemSelectedRippleColor) == null)) {
                int[] iArr2 = {16843623, -16842919};
                int[] iArr3 = {16842913, -16842919};
                colorStateList4 = new ColorStateList(new int[][]{iArr3, iArr2, new int[0]}, new int[]{ColorUtils.compositeColors(colorStateList2.getColorForState(iArr3, 0), MaterialAutoCompleteTextView.this.simpleItemSelectedColor), ColorUtils.compositeColors(MaterialAutoCompleteTextView.this.simpleItemSelectedRippleColor.getColorForState(iArr2, 0), MaterialAutoCompleteTextView.this.simpleItemSelectedColor), MaterialAutoCompleteTextView.this.simpleItemSelectedColor});
            }
            this.selectedItemRippleOverlaidColor = colorStateList4;
        }

        public final View getView(int i, View view, ViewGroup viewGroup) {
            View view2 = super.getView(i, view, viewGroup);
            if (view2 instanceof TextView) {
                TextView textView = (TextView) view2;
                RippleDrawable rippleDrawable = null;
                if (MaterialAutoCompleteTextView.this.getText().toString().contentEquals(textView.getText()) && MaterialAutoCompleteTextView.this.simpleItemSelectedColor != 0) {
                    ColorDrawable colorDrawable = new ColorDrawable(MaterialAutoCompleteTextView.this.simpleItemSelectedColor);
                    if (this.pressedRippleColor != null) {
                        colorDrawable.setTintList(this.selectedItemRippleOverlaidColor);
                        rippleDrawable = new RippleDrawable(this.pressedRippleColor, colorDrawable, (Drawable) null);
                    } else {
                        rippleDrawable = colorDrawable;
                    }
                }
                WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                textView.setBackground(rippleDrawable);
            }
            return view2;
        }
    }

    public MaterialAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, 2130968655, 0), attributeSet, 2130968655);
        Context context2 = getContext();
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context2, attributeSet, R$styleable.MaterialAutoCompleteTextView, 2130968655, 2132018454, new int[0]);
        if (obtainStyledAttributes.hasValue(0) && obtainStyledAttributes.getInt(0, 0) == 0) {
            setKeyListener((KeyListener) null);
        }
        int resourceId = obtainStyledAttributes.getResourceId(2, 2131558806);
        this.popupElevation = (float) obtainStyledAttributes.getDimensionPixelOffset(1, 2131166855);
        this.simpleItemSelectedColor = obtainStyledAttributes.getColor(3, 0);
        this.simpleItemSelectedRippleColor = MaterialResources.getColorStateList(context2, obtainStyledAttributes, 4);
        this.accessibilityManager = (AccessibilityManager) context2.getSystemService("accessibility");
        ListPopupWindow listPopupWindow = new ListPopupWindow(context2, (AttributeSet) null, 2130969475, 0);
        this.modalListPopup = listPopupWindow;
        listPopupWindow.mModal = true;
        listPopupWindow.mPopup.setFocusable(true);
        listPopupWindow.mDropDownAnchorView = this;
        listPopupWindow.mPopup.setInputMethodMode(2);
        listPopupWindow.setAdapter(getAdapter());
        listPopupWindow.mItemClickListener = new AdapterView.OnItemClickListener() {
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                Object obj;
                int selectedItemPosition;
                View view2 = null;
                MaterialAutoCompleteTextView materialAutoCompleteTextView = MaterialAutoCompleteTextView.this;
                if (i < 0) {
                    ListPopupWindow listPopupWindow = materialAutoCompleteTextView.modalListPopup;
                    if (!listPopupWindow.mPopup.isShowing()) {
                        obj = null;
                    } else {
                        obj = listPopupWindow.mDropDownList.getSelectedItem();
                    }
                } else {
                    obj = materialAutoCompleteTextView.getAdapter().getItem(i);
                }
                MaterialAutoCompleteTextView.access$100(MaterialAutoCompleteTextView.this, obj);
                AdapterView.OnItemClickListener onItemClickListener = MaterialAutoCompleteTextView.this.getOnItemClickListener();
                if (onItemClickListener != null) {
                    if (view == null || i < 0) {
                        ListPopupWindow listPopupWindow2 = MaterialAutoCompleteTextView.this.modalListPopup;
                        if (listPopupWindow2.mPopup.isShowing()) {
                            view2 = listPopupWindow2.mDropDownList.getSelectedView();
                        }
                        view = view2;
                        ListPopupWindow listPopupWindow3 = MaterialAutoCompleteTextView.this.modalListPopup;
                        if (!listPopupWindow3.mPopup.isShowing()) {
                            selectedItemPosition = -1;
                        } else {
                            selectedItemPosition = listPopupWindow3.mDropDownList.getSelectedItemPosition();
                        }
                        i = selectedItemPosition;
                        ListPopupWindow listPopupWindow4 = MaterialAutoCompleteTextView.this.modalListPopup;
                        if (!listPopupWindow4.mPopup.isShowing()) {
                            j = Long.MIN_VALUE;
                        } else {
                            j = listPopupWindow4.mDropDownList.getSelectedItemId();
                        }
                    }
                    onItemClickListener.onItemClick(MaterialAutoCompleteTextView.this.modalListPopup.mDropDownList, view, i, j);
                }
                MaterialAutoCompleteTextView.this.modalListPopup.dismiss();
            }
        };
        if (obtainStyledAttributes.hasValue(5)) {
            setAdapter(new MaterialArrayAdapter(getContext(), resourceId, getResources().getStringArray(obtainStyledAttributes.getResourceId(5, 0))));
        }
        obtainStyledAttributes.recycle();
    }

    public static void access$100(MaterialAutoCompleteTextView materialAutoCompleteTextView, Object obj) {
        materialAutoCompleteTextView.setText(materialAutoCompleteTextView.convertSelectionToString(obj), false);
    }

    public final TextInputLayout findTextInputLayoutAncestor() {
        for (ViewParent parent = getParent(); parent != null; parent = parent.getParent()) {
            if (parent instanceof TextInputLayout) {
                return (TextInputLayout) parent;
            }
        }
        return null;
    }

    public final CharSequence getHint() {
        TextInputLayout findTextInputLayoutAncestor = findTextInputLayoutAncestor();
        if (findTextInputLayoutAncestor == null || !findTextInputLayoutAncestor.isProvidingHint) {
            return super.getHint();
        }
        if (findTextInputLayoutAncestor.hintEnabled) {
            return findTextInputLayoutAncestor.hint;
        }
        return null;
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        TextInputLayout findTextInputLayoutAncestor = findTextInputLayoutAncestor();
        if (findTextInputLayoutAncestor != null && findTextInputLayoutAncestor.isProvidingHint && super.getHint() == null && Build.MANUFACTURER.toLowerCase(Locale.ENGLISH).equals("meizu")) {
            setHint("");
        }
    }

    public final void onMeasure(int i, int i2) {
        int i3;
        super.onMeasure(i, i2);
        if (View.MeasureSpec.getMode(i) == Integer.MIN_VALUE) {
            int measuredWidth = getMeasuredWidth();
            ListAdapter adapter = getAdapter();
            TextInputLayout findTextInputLayoutAncestor = findTextInputLayoutAncestor();
            int i4 = 0;
            if (!(adapter == null || findTextInputLayoutAncestor == null)) {
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
                int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
                ListPopupWindow listPopupWindow = this.modalListPopup;
                if (!listPopupWindow.mPopup.isShowing()) {
                    i3 = -1;
                } else {
                    i3 = listPopupWindow.mDropDownList.getSelectedItemPosition();
                }
                int min = Math.min(adapter.getCount(), Math.max(0, i3) + 15);
                View view = null;
                int i5 = 0;
                for (int max = Math.max(0, min - 15); max < min; max++) {
                    int itemViewType = adapter.getItemViewType(max);
                    if (itemViewType != i4) {
                        view = null;
                        i4 = itemViewType;
                    }
                    view = adapter.getView(max, view, findTextInputLayoutAncestor);
                    if (view.getLayoutParams() == null) {
                        view.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
                    }
                    view.measure(makeMeasureSpec, makeMeasureSpec2);
                    i5 = Math.max(i5, view.getMeasuredWidth());
                }
                Drawable background = this.modalListPopup.mPopup.getBackground();
                if (background != null) {
                    background.getPadding(this.tempRect);
                    Rect rect = this.tempRect;
                    i5 += rect.left + rect.right;
                }
                i4 = findTextInputLayoutAncestor.endLayout.endIconView.getMeasuredWidth() + i5;
            }
            setMeasuredDimension(Math.min(Math.max(measuredWidth, i4), View.MeasureSpec.getSize(i)), getMeasuredHeight());
        }
    }

    public final void setAdapter(ListAdapter listAdapter) {
        super.setAdapter(listAdapter);
        this.modalListPopup.setAdapter(getAdapter());
    }

    public final void setOnItemSelectedListener(AdapterView.OnItemSelectedListener onItemSelectedListener) {
        super.setOnItemSelectedListener(onItemSelectedListener);
        this.modalListPopup.mItemSelectedListener = getOnItemSelectedListener();
    }

    public final void setRawInputType(int i) {
        super.setRawInputType(i);
        TextInputLayout findTextInputLayoutAncestor = findTextInputLayoutAncestor();
        if (findTextInputLayoutAncestor != null) {
            findTextInputLayoutAncestor.updateEditTextBoxBackgroundIfNeeded();
        }
    }

    public final void showDropDown() {
        AccessibilityManager accessibilityManager2 = this.accessibilityManager;
        if (accessibilityManager2 == null || !accessibilityManager2.isTouchExplorationEnabled()) {
            super.showDropDown();
        } else {
            this.modalListPopup.show();
        }
    }
}
