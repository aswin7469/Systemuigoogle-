package com.google.android.material.checkbox;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CheckBox;
import androidx.appcompat.widget.AppCompatCheckBox;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.ViewUtils;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class MaterialCheckBox extends AppCompatCheckBox {
    public static final int[][] CHECKBOX_STATES = {new int[]{16842910, 2130969964}, new int[]{16842910, 16842912}, new int[]{16842910, -16842912}, new int[]{-16842910, 16842912}, new int[]{-16842910, -16842912}};
    public static final int[] ERROR_STATE_SET = {2130969964};
    public boolean centerIfNoTextEnabled;
    public CharSequence errorAccessibilityLabel;
    public boolean errorShown;
    public ColorStateList materialThemeColorsTintList;
    public boolean useMaterialThemeColors;

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
