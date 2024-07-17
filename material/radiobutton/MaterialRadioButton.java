package com.google.android.material.radiobutton;

import android.content.res.ColorStateList;
import androidx.appcompat.widget.AppCompatRadioButton;
import com.google.android.material.color.MaterialColors;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class MaterialRadioButton extends AppCompatRadioButton {
    public static final int[][] ENABLED_CHECKED_STATES = {new int[]{16842910, 16842912}, new int[]{16842910, -16842912}, new int[]{-16842910, 16842912}, new int[]{-16842910, -16842912}};
    public ColorStateList materialThemeColorsTintList;
    public boolean useMaterialThemeColors;

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.useMaterialThemeColors && getButtonTintList() == null) {
            this.useMaterialThemeColors = true;
            if (this.materialThemeColorsTintList == null) {
                int color = MaterialColors.getColor(this, 2130968858);
                int color2 = MaterialColors.getColor(this, 2130968872);
                int color3 = MaterialColors.getColor(this, 2130968887);
                this.materialThemeColorsTintList = new ColorStateList(ENABLED_CHECKED_STATES, new int[]{MaterialColors.layer(color3, 1.0f, color), MaterialColors.layer(color3, 0.54f, color2), MaterialColors.layer(color3, 0.38f, color2), MaterialColors.layer(color3, 0.38f, color2)});
            }
            setButtonTintList(this.materialThemeColorsTintList);
        }
    }
}
