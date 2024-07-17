package com.google.android.material.materialswitch;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import androidx.appcompat.widget.DrawableUtils;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.graphics.ColorUtils;
import com.google.android.material.R$styleable;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.util.Arrays;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class MaterialSwitch extends SwitchCompat {
    public static final int[] STATE_SET_WITH_ICON = {2130969970};
    public int[] currentStateChecked;
    public int[] currentStateUnchecked;
    public final Drawable thumbDrawable;
    public final Drawable thumbIconDrawable;
    public final ColorStateList thumbIconTintList;
    public final ColorStateList thumbTintList;
    public final Drawable trackDecorationDrawable;
    public final ColorStateList trackDecorationTintList;
    public final Drawable trackDrawable;
    public final ColorStateList trackTintList;

    public MaterialSwitch(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public static Drawable createTintableDrawableIfNeeded(Drawable drawable, ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (drawable == null) {
            return null;
        }
        if (colorStateList != null) {
            drawable = drawable.mutate();
            if (mode != null) {
                drawable.setTintMode(mode);
            }
        }
        return drawable;
    }

    public static void setInterpolatedDrawableTintIfPossible(Drawable drawable, ColorStateList colorStateList, int[] iArr, int[] iArr2, float f) {
        if (drawable != null && colorStateList != null) {
            drawable.setTint(ColorUtils.blendARGB(colorStateList.getColorForState(iArr, 0), f, colorStateList.getColorForState(iArr2, 0)));
        }
    }

    public final void invalidate() {
        updateDrawableTints();
        super.invalidate();
    }

    public final int[] onCreateDrawableState(int i) {
        int[] copyOf;
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (this.thumbIconDrawable != null) {
            CompoundButton.mergeDrawableStates(onCreateDrawableState, STATE_SET_WITH_ICON);
        }
        int[] iArr = new int[onCreateDrawableState.length];
        int i2 = 0;
        int i3 = 0;
        for (int i4 : onCreateDrawableState) {
            if (i4 != 16842912) {
                iArr[i3] = i4;
                i3++;
            }
        }
        this.currentStateUnchecked = iArr;
        while (true) {
            if (i2 >= onCreateDrawableState.length) {
                copyOf = Arrays.copyOf(onCreateDrawableState, onCreateDrawableState.length + 1);
                copyOf[onCreateDrawableState.length] = 16842912;
                break;
            }
            int i5 = onCreateDrawableState[i2];
            if (i5 == 16842912) {
                copyOf = onCreateDrawableState;
                break;
            } else if (i5 == 0) {
                copyOf = (int[]) onCreateDrawableState.clone();
                copyOf[i2] = 16842912;
                break;
            } else {
                i2++;
            }
        }
        this.currentStateChecked = copyOf;
        return onCreateDrawableState;
    }

    public final void updateDrawableTints() {
        ColorStateList colorStateList = this.thumbTintList;
        if (colorStateList != null || this.thumbIconTintList != null || this.trackTintList != null || this.trackDecorationTintList != null) {
            float f = this.mThumbPosition;
            if (colorStateList != null) {
                setInterpolatedDrawableTintIfPossible(this.thumbDrawable, colorStateList, this.currentStateUnchecked, this.currentStateChecked, f);
            }
            ColorStateList colorStateList2 = this.thumbIconTintList;
            if (colorStateList2 != null) {
                setInterpolatedDrawableTintIfPossible(this.thumbIconDrawable, colorStateList2, this.currentStateUnchecked, this.currentStateChecked, f);
            }
            ColorStateList colorStateList3 = this.trackTintList;
            if (colorStateList3 != null) {
                setInterpolatedDrawableTintIfPossible(this.trackDrawable, colorStateList3, this.currentStateUnchecked, this.currentStateChecked, f);
            }
            ColorStateList colorStateList4 = this.trackDecorationTintList;
            if (colorStateList4 != null) {
                setInterpolatedDrawableTintIfPossible(this.trackDecorationDrawable, colorStateList4, this.currentStateUnchecked, this.currentStateChecked, f);
            }
        }
    }

    public MaterialSwitch(Context context, AttributeSet attributeSet, int i) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, 2130969553, 2132018674), attributeSet, 2130969553);
        int i2;
        int i3;
        Context context2 = getContext();
        this.thumbDrawable = this.mThumbDrawable;
        ColorStateList colorStateList = this.mThumbTintList;
        this.thumbTintList = colorStateList;
        this.mThumbTintList = null;
        this.mHasThumbTint = true;
        applyThumbTint();
        this.trackDrawable = this.mTrackDrawable;
        ColorStateList colorStateList2 = this.mTrackTintList;
        this.trackTintList = colorStateList2;
        this.mTrackTintList = null;
        this.mHasTrackTint = true;
        applyTrackTint();
        TintTypedArray obtainTintedStyledAttributes = ThemeEnforcement.obtainTintedStyledAttributes(context2, attributeSet, R$styleable.MaterialSwitch, 2130969553, 2132018674, new int[0]);
        this.thumbIconDrawable = obtainTintedStyledAttributes.getDrawable(0);
        ColorStateList colorStateList3 = obtainTintedStyledAttributes.getColorStateList(1);
        this.thumbIconTintList = colorStateList3;
        TypedArray typedArray = obtainTintedStyledAttributes.mWrapped;
        int i4 = typedArray.getInt(2, -1);
        PorterDuff.Mode mode = PorterDuff.Mode.SRC_IN;
        PorterDuff.Mode parseTintMode = DrawableUtils.parseTintMode(i4, mode);
        this.trackDecorationDrawable = obtainTintedStyledAttributes.getDrawable(3);
        ColorStateList colorStateList4 = obtainTintedStyledAttributes.getColorStateList(4);
        this.trackDecorationTintList = colorStateList4;
        PorterDuff.Mode parseTintMode2 = DrawableUtils.parseTintMode(typedArray.getInt(5, -1), mode);
        obtainTintedStyledAttributes.recycle();
        this.mEnforceSwitchWidth = false;
        invalidate();
        this.thumbDrawable = createTintableDrawableIfNeeded(this.thumbDrawable, colorStateList, this.mThumbTintMode);
        this.thumbIconDrawable = createTintableDrawableIfNeeded(this.thumbIconDrawable, colorStateList3, parseTintMode);
        updateDrawableTints();
        Drawable drawable = this.thumbDrawable;
        Drawable drawable2 = this.thumbIconDrawable;
        if (drawable == null) {
            drawable = drawable2;
        } else if (drawable2 != null) {
            LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{drawable, drawable2});
            if (drawable2.getIntrinsicWidth() > drawable.getIntrinsicWidth() || drawable2.getIntrinsicHeight() > drawable.getIntrinsicHeight()) {
                float intrinsicWidth = ((float) drawable2.getIntrinsicWidth()) / ((float) drawable2.getIntrinsicHeight());
                if (intrinsicWidth >= ((float) drawable.getIntrinsicWidth()) / ((float) drawable.getIntrinsicHeight())) {
                    i3 = drawable.getIntrinsicWidth();
                    i2 = (int) (((float) i3) / intrinsicWidth);
                } else {
                    i2 = drawable.getIntrinsicHeight();
                    i3 = (int) (intrinsicWidth * ((float) i2));
                }
            } else {
                i3 = drawable2.getIntrinsicWidth();
                i2 = drawable2.getIntrinsicHeight();
            }
            layerDrawable.setLayerSize(1, i3, i2);
            layerDrawable.setLayerGravity(1, 17);
            drawable = layerDrawable;
        }
        Drawable drawable3 = this.mThumbDrawable;
        if (drawable3 != null) {
            drawable3.setCallback((Drawable.Callback) null);
        }
        this.mThumbDrawable = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        requestLayout();
        refreshDrawableState();
        this.trackDrawable = createTintableDrawableIfNeeded(this.trackDrawable, colorStateList2, this.mTrackTintMode);
        this.trackDecorationDrawable = createTintableDrawableIfNeeded(this.trackDecorationDrawable, colorStateList4, parseTintMode2);
        updateDrawableTints();
        Drawable drawable4 = this.trackDrawable;
        if (drawable4 != null && this.trackDecorationDrawable != null) {
            drawable4 = new LayerDrawable(new Drawable[]{this.trackDrawable, this.trackDecorationDrawable});
        } else if (drawable4 == null) {
            drawable4 = this.trackDecorationDrawable;
        }
        if (drawable4 != null) {
            this.mSwitchMinWidth = drawable4.getIntrinsicWidth();
            requestLayout();
        }
        Drawable drawable5 = this.mTrackDrawable;
        if (drawable5 != null) {
            drawable5.setCallback((Drawable.Callback) null);
        }
        this.mTrackDrawable = drawable4;
        if (drawable4 != null) {
            drawable4.setCallback(this);
        }
        requestLayout();
    }
}
