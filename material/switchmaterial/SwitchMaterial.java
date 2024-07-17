package com.google.android.material.switchmaterial;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.R$styleable;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.elevation.ElevationOverlayProvider;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.util.WeakHashMap;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class SwitchMaterial extends SwitchCompat {
    public static final int[][] ENABLED_CHECKED_STATES = {new int[]{16842910, 16842912}, new int[]{16842910, -16842912}, new int[]{-16842910, 16842912}, new int[]{-16842910, -16842912}};
    public final ElevationOverlayProvider elevationOverlayProvider;
    public ColorStateList materialThemeColorsThumbTintList;
    public ColorStateList materialThemeColorsTrackTintList;
    public final boolean useMaterialThemeColors;

    public SwitchMaterial(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        boolean z = this.useMaterialThemeColors;
        int[][] iArr = ENABLED_CHECKED_STATES;
        if (z && this.mThumbTintList == null) {
            if (this.materialThemeColorsThumbTintList == null) {
                int color = MaterialColors.getColor(this, 2130968887);
                int color2 = MaterialColors.getColor(this, 2130968858);
                float dimension = getResources().getDimension(2131166895);
                if (this.elevationOverlayProvider.elevationOverlayEnabled) {
                    float f = 0.0f;
                    for (ViewParent parent = getParent(); parent instanceof View; parent = parent.getParent()) {
                        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                        f += ViewCompat.Api21Impl.getElevation((View) parent);
                    }
                    dimension += f;
                }
                int compositeOverlayIfNeeded = this.elevationOverlayProvider.compositeOverlayIfNeeded(color, dimension);
                this.materialThemeColorsThumbTintList = new ColorStateList(iArr, new int[]{MaterialColors.layer(color, 1.0f, color2), compositeOverlayIfNeeded, MaterialColors.layer(color, 0.38f, color2), compositeOverlayIfNeeded});
            }
            this.mThumbTintList = this.materialThemeColorsThumbTintList;
            this.mHasThumbTint = true;
            applyThumbTint();
        }
        if (this.useMaterialThemeColors && this.mTrackTintList == null) {
            if (this.materialThemeColorsTrackTintList == null) {
                int color3 = MaterialColors.getColor(this, 2130968887);
                int color4 = MaterialColors.getColor(this, 2130968858);
                int color5 = MaterialColors.getColor(this, 2130968872);
                this.materialThemeColorsTrackTintList = new ColorStateList(iArr, new int[]{MaterialColors.layer(color3, 0.54f, color4), MaterialColors.layer(color3, 0.32f, color5), MaterialColors.layer(color3, 0.12f, color4), MaterialColors.layer(color3, 0.12f, color5)});
            }
            this.mTrackTintList = this.materialThemeColorsTrackTintList;
            this.mHasTrackTint = true;
            applyTrackTint();
        }
    }

    public SwitchMaterial(Context context, AttributeSet attributeSet, int i) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, 2130970130, 2132018810), attributeSet, 2130970130);
        Context context2 = getContext();
        this.elevationOverlayProvider = new ElevationOverlayProvider(context2);
        int[] iArr = R$styleable.SwitchMaterial;
        ThemeEnforcement.checkCompatibleTheme(context2, attributeSet, 2130970130, 2132018810);
        ThemeEnforcement.checkTextAppearance(context2, attributeSet, iArr, 2130970130, 2132018810, new int[0]);
        TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, iArr, 2130970130, 2132018810);
        this.useMaterialThemeColors = obtainStyledAttributes.getBoolean(0, false);
        obtainStyledAttributes.recycle();
    }
}
