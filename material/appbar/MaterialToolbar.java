package com.google.android.material.appbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import com.google.android.material.R$styleable;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ToolbarUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.util.Collections;
import java.util.List;
import java.util.WeakHashMap;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class MaterialToolbar extends Toolbar {
    public static final ImageView.ScaleType[] LOGO_SCALE_TYPE_ARRAY = {ImageView.ScaleType.MATRIX, ImageView.ScaleType.FIT_XY, ImageView.ScaleType.FIT_START, ImageView.ScaleType.FIT_CENTER, ImageView.ScaleType.FIT_END, ImageView.ScaleType.CENTER, ImageView.ScaleType.CENTER_CROP, ImageView.ScaleType.CENTER_INSIDE};
    public final Boolean logoAdjustViewBounds;
    public final ImageView.ScaleType logoScaleType;
    public final Integer navigationIconTint;
    public final boolean subtitleCentered;
    public final boolean titleCentered;

    public MaterialToolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public final void layoutTitleCenteredHorizontally(View view, Pair pair) {
        int measuredWidth = getMeasuredWidth();
        int measuredWidth2 = view.getMeasuredWidth();
        int i = (measuredWidth / 2) - (measuredWidth2 / 2);
        int i2 = measuredWidth2 + i;
        int max = Math.max(Math.max(((Integer) pair.first).intValue() - i, 0), Math.max(i2 - ((Integer) pair.second).intValue(), 0));
        if (max > 0) {
            i += max;
            i2 -= max;
            view.measure(View.MeasureSpec.makeMeasureSpec(i2 - i, 1073741824), view.getMeasuredHeightAndState());
        }
        view.layout(i, view.getTop(), i2, view.getBottom());
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.setParentAbsoluteElevation(this);
    }

    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        Drawable drawable;
        ImageView imageView;
        Drawable drawable2;
        TextView textView;
        TextView textView2;
        super.onLayout(z, i, i2, i3, i4);
        int i5 = 0;
        ImageView imageView2 = null;
        if (this.titleCentered || this.subtitleCentered) {
            ToolbarUtils.AnonymousClass1 r7 = ToolbarUtils.VIEW_TOP_COMPARATOR;
            List textViewsWithText = ToolbarUtils.getTextViewsWithText(this, this.mTitleText);
            if (textViewsWithText.isEmpty()) {
                textView = null;
            } else {
                textView = (TextView) Collections.min(textViewsWithText, ToolbarUtils.VIEW_TOP_COMPARATOR);
            }
            List textViewsWithText2 = ToolbarUtils.getTextViewsWithText(this, this.mSubtitleText);
            if (textViewsWithText2.isEmpty()) {
                textView2 = null;
            } else {
                textView2 = (TextView) Collections.max(textViewsWithText2, ToolbarUtils.VIEW_TOP_COMPARATOR);
            }
            if (!(textView == null && textView2 == null)) {
                int measuredWidth = getMeasuredWidth();
                int i6 = measuredWidth / 2;
                int paddingLeft = getPaddingLeft();
                int paddingRight = measuredWidth - getPaddingRight();
                for (int i7 = 0; i7 < getChildCount(); i7++) {
                    View childAt = getChildAt(i7);
                    if (!(childAt.getVisibility() == 8 || childAt == textView || childAt == textView2)) {
                        if (childAt.getRight() < i6 && childAt.getRight() > paddingLeft) {
                            paddingLeft = childAt.getRight();
                        }
                        if (childAt.getLeft() > i6 && childAt.getLeft() < paddingRight) {
                            paddingRight = childAt.getLeft();
                        }
                    }
                }
                Pair pair = new Pair(Integer.valueOf(paddingLeft), Integer.valueOf(paddingRight));
                if (this.titleCentered && textView != null) {
                    layoutTitleCenteredHorizontally(textView, pair);
                }
                if (this.subtitleCentered && textView2 != null) {
                    layoutTitleCenteredHorizontally(textView2, pair);
                }
            }
        }
        ToolbarUtils.AnonymousClass1 r72 = ToolbarUtils.VIEW_TOP_COMPARATOR;
        AppCompatImageView appCompatImageView = this.mLogoView;
        if (appCompatImageView != null) {
            drawable = appCompatImageView.getDrawable();
        } else {
            drawable = null;
        }
        if (drawable != null) {
            while (true) {
                if (i5 >= getChildCount()) {
                    break;
                }
                View childAt2 = getChildAt(i5);
                if ((childAt2 instanceof ImageView) && (drawable2 = imageView.getDrawable()) != null && drawable2.getConstantState() != null && drawable2.getConstantState().equals(drawable.getConstantState())) {
                    imageView2 = (ImageView) childAt2;
                    break;
                }
                i5++;
            }
        }
        if (imageView2 != null) {
            Boolean bool = this.logoAdjustViewBounds;
            if (bool != null) {
                imageView2.setAdjustViewBounds(bool.booleanValue());
            }
            ImageView.ScaleType scaleType = this.logoScaleType;
            if (scaleType != null) {
                imageView2.setScaleType(scaleType);
            }
        }
    }

    public final void setElevation(float f) {
        super.setElevation(f);
        MaterialShapeUtils.setElevation(this, f);
    }

    public final void setNavigationIcon(Drawable drawable) {
        if (!(drawable == null || this.navigationIconTint == null)) {
            drawable = drawable.mutate();
            drawable.setTint(this.navigationIconTint.intValue());
        }
        super.setNavigationIcon(drawable);
    }

    public MaterialToolbar(Context context, AttributeSet attributeSet, int i) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, 2130970291, 2132018883), attributeSet, 2130970291);
        Context context2 = getContext();
        int i2 = 0;
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context2, attributeSet, R$styleable.MaterialToolbar, 2130970291, 2132018883, new int[0]);
        if (obtainStyledAttributes.hasValue(2)) {
            this.navigationIconTint = Integer.valueOf(obtainStyledAttributes.getColor(2, -1));
            AppCompatImageButton appCompatImageButton = this.mNavButtonView;
            Drawable drawable = appCompatImageButton != null ? appCompatImageButton.getDrawable() : null;
            if (drawable != null) {
                setNavigationIcon(drawable);
            }
        }
        this.titleCentered = obtainStyledAttributes.getBoolean(4, false);
        this.subtitleCentered = obtainStyledAttributes.getBoolean(3, false);
        int i3 = obtainStyledAttributes.getInt(1, -1);
        if (i3 >= 0) {
            ImageView.ScaleType[] scaleTypeArr = LOGO_SCALE_TYPE_ARRAY;
            if (i3 < scaleTypeArr.length) {
                this.logoScaleType = scaleTypeArr[i3];
            }
        }
        if (obtainStyledAttributes.hasValue(0)) {
            this.logoAdjustViewBounds = Boolean.valueOf(obtainStyledAttributes.getBoolean(0, false));
        }
        obtainStyledAttributes.recycle();
        Drawable background = getBackground();
        if (background == null || (background instanceof ColorDrawable)) {
            MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable();
            materialShapeDrawable.setFillColor(ColorStateList.valueOf(background != null ? ((ColorDrawable) background).getColor() : i2));
            materialShapeDrawable.initializeElevationOverlay(context2);
            WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
            materialShapeDrawable.setElevation(ViewCompat.Api21Impl.getElevation(this));
            ViewCompat.Api16Impl.setBackground(this, materialShapeDrawable);
        }
    }
}
