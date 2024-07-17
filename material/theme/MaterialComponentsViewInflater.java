package com.google.android.material.theme;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import androidx.appcompat.app.AppCompatViewInflater;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.TextViewCompat;
import com.google.android.material.R$styleable;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textview.MaterialTextView;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.util.LinkedHashSet;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public class MaterialComponentsViewInflater extends AppCompatViewInflater {
    public final AppCompatAutoCompleteTextView createAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        return new MaterialAutoCompleteTextView(context, attributeSet);
    }

    public final AppCompatButton createButton(Context context, AttributeSet attributeSet) {
        return new MaterialButton(context, attributeSet);
    }

    /* JADX WARNING: type inference failed for: r7v1, types: [android.widget.CheckBox, android.widget.CompoundButton, com.google.android.material.checkbox.MaterialCheckBox, androidx.appcompat.widget.AppCompatCheckBox] */
    public final AppCompatCheckBox createCheckBox(Context context, AttributeSet attributeSet) {
        ? appCompatCheckBox = new AppCompatCheckBox(MaterialThemeOverlay.wrap(context, attributeSet, 2130968787, 2132018814), attributeSet, 2130968787);
        new LinkedHashSet();
        Context context2 = appCompatCheckBox.getContext();
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context2, attributeSet, R$styleable.MaterialCheckBox, 2130968787, 2132018814, new int[0]);
        if (obtainStyledAttributes.hasValue(0)) {
            appCompatCheckBox.setButtonTintList(MaterialResources.getColorStateList(context2, obtainStyledAttributes, 0));
        }
        appCompatCheckBox.useMaterialThemeColors = obtainStyledAttributes.getBoolean(4, false);
        appCompatCheckBox.centerIfNoTextEnabled = obtainStyledAttributes.getBoolean(1, true);
        appCompatCheckBox.errorShown = obtainStyledAttributes.getBoolean(3, false);
        appCompatCheckBox.errorAccessibilityLabel = obtainStyledAttributes.getText(2);
        obtainStyledAttributes.recycle();
        return appCompatCheckBox;
    }

    /* JADX WARNING: type inference failed for: r7v1, types: [androidx.appcompat.widget.AppCompatRadioButton, com.google.android.material.radiobutton.MaterialRadioButton, android.widget.CompoundButton, android.widget.RadioButton] */
    public final AppCompatRadioButton createRadioButton(Context context, AttributeSet attributeSet) {
        ? appCompatRadioButton = new AppCompatRadioButton(MaterialThemeOverlay.wrap(context, attributeSet, 2130969799, 2132018815), attributeSet);
        Context context2 = appCompatRadioButton.getContext();
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context2, attributeSet, R$styleable.MaterialRadioButton, 2130969799, 2132018815, new int[0]);
        if (obtainStyledAttributes.hasValue(0)) {
            appCompatRadioButton.setButtonTintList(MaterialResources.getColorStateList(context2, obtainStyledAttributes, 0));
        }
        appCompatRadioButton.useMaterialThemeColors = obtainStyledAttributes.getBoolean(1, false);
        obtainStyledAttributes.recycle();
        return appCompatRadioButton;
    }

    public final AppCompatTextView createTextView(Context context, AttributeSet attributeSet) {
        AppCompatTextView appCompatTextView = new AppCompatTextView(MaterialThemeOverlay.wrap(context, attributeSet, 16842884, 0), attributeSet, 16842884);
        Context context2 = appCompatTextView.getContext();
        TypedValue resolve = MaterialAttributes.resolve(2130970195, context2);
        if (!(resolve != null && resolve.type == 18 && resolve.data == 0)) {
            Resources.Theme theme = context2.getTheme();
            int[] iArr = R$styleable.MaterialTextView;
            TypedArray obtainStyledAttributes = theme.obtainStyledAttributes(attributeSet, iArr, 16842884, 0);
            int readFirstAvailableDimension = MaterialTextView.readFirstAvailableDimension(context2, obtainStyledAttributes, 1, 2);
            obtainStyledAttributes.recycle();
            if (readFirstAvailableDimension == -1) {
                TypedArray obtainStyledAttributes2 = theme.obtainStyledAttributes(attributeSet, iArr, 16842884, 0);
                int resourceId = obtainStyledAttributes2.getResourceId(0, -1);
                obtainStyledAttributes2.recycle();
                if (resourceId != -1) {
                    TypedArray obtainStyledAttributes3 = theme.obtainStyledAttributes(resourceId, R$styleable.MaterialTextAppearance);
                    int readFirstAvailableDimension2 = MaterialTextView.readFirstAvailableDimension(appCompatTextView.getContext(), obtainStyledAttributes3, 1, 2);
                    obtainStyledAttributes3.recycle();
                    if (readFirstAvailableDimension2 >= 0) {
                        TextViewCompat.setLineHeight(appCompatTextView, readFirstAvailableDimension2);
                    }
                }
            }
        }
        return appCompatTextView;
    }
}
