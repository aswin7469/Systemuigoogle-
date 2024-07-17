package com.google.android.material.checkbox;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CheckBox;
import androidx.appcompat.widget.AppCompatCheckBox;
import com.google.android.material.R$styleable;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.util.LinkedHashSet;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class MaterialCheckBox extends AppCompatCheckBox {
    public static final int[][] CHECKBOX_STATES = {new int[]{16842910, 2130969964}, new int[]{16842910, 16842912}, new int[]{16842910, -16842912}, new int[]{-16842910, 16842912}, new int[]{-16842910, -16842912}};
    public static final int[] ERROR_STATE_SET = {2130969964};
    public final boolean centerIfNoTextEnabled;
    public final CharSequence errorAccessibilityLabel;
    public final boolean errorShown;
    public ColorStateList materialThemeColorsTintList;
    public boolean useMaterialThemeColors;

    public MaterialCheckBox(Context context, AttributeSet attributeSet) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, 2130968787, 2132018808), attributeSet, 2130968787);
        new LinkedHashSet();
        Context context2 = getContext();
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context2, attributeSet, R$styleable.MaterialCheckBox, 2130968787, 2132018808, new int[0]);
        if (obtainStyledAttributes.hasValue(0)) {
            setButtonTintList(MaterialResources.getColorStateList(context2, obtainStyledAttributes, 0));
        }
        this.useMaterialThemeColors = obtainStyledAttributes.getBoolean(4, false);
        this.centerIfNoTextEnabled = obtainStyledAttributes.getBoolean(1, true);
        this.errorShown = obtainStyledAttributes.getBoolean(3, false);
        this.errorAccessibilityLabel = obtainStyledAttributes.getText(2);
        obtainStyledAttributes.recycle();
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.useMaterialThemeColors && getButtonTintList() == null) {
            this.useMaterialThemeColors = true;
            if (this.materialThemeColorsTintList == null) {
                int color = MaterialColors.getColor(this, 2130968858);
                int color2 = MaterialColors.getColor(this, 2130968861);
                int color3 = MaterialColors.getColor(this, 2130968887);
                int color4 = MaterialColors.getColor(this, 2130968872);
                this.materialThemeColorsTintList = new ColorStateList(CHECKBOX_STATES, new int[]{MaterialColors.layer(color3, 1.0f, color2), MaterialColors.layer(color3, 1.0f, color), MaterialColors.layer(color3, 0.54f, color4), MaterialColors.layer(color3, 0.38f, color4), MaterialColors.layer(color3, 0.38f, color4)});
            }
            setButtonTintList(this.materialThemeColorsTintList);
        }
    }

    public final int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (this.errorShown) {
            CheckBox.mergeDrawableStates(onCreateDrawableState, ERROR_STATE_SET);
        }
        return onCreateDrawableState;
    }

    public final void onDraw(Canvas canvas) {
        Drawable buttonDrawable;
        int i;
        if (!this.centerIfNoTextEnabled || !TextUtils.isEmpty(getText()) || (buttonDrawable = getButtonDrawable()) == null) {
            super.onDraw(canvas);
            return;
        }
        if (ViewUtils.isLayoutRtl(this)) {
            i = -1;
        } else {
            i = 1;
        }
        int width = ((getWidth() - buttonDrawable.getIntrinsicWidth()) / 2) * i;
        int save = canvas.save();
        canvas.translate((float) width, 0.0f);
        super.onDraw(canvas);
        canvas.restoreToCount(save);
        if (getBackground() != null) {
            Rect bounds = buttonDrawable.getBounds();
            getBackground().setHotspotBounds(bounds.left + width, bounds.top, bounds.right + width, bounds.bottom);
        }
    }

    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        if (accessibilityNodeInfo != null && this.errorShown) {
            accessibilityNodeInfo.setText(accessibilityNodeInfo.getText() + ", " + this.errorAccessibilityLabel);
        }
    }
}
