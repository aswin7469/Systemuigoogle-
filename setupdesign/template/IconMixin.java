package com.google.android.setupdesign.template;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.google.android.setupcompat.internal.TemplateLayout;
import com.google.android.setupcompat.template.Mixin;
import com.google.android.setupdesign.R$styleable;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class IconMixin implements Mixin {
    public final int originalHeight;
    public final ImageView.ScaleType originalScaleType;
    public final TemplateLayout templateLayout;

    public IconMixin(TemplateLayout templateLayout2, AttributeSet attributeSet, int i) {
        ImageView imageView;
        ImageView.ScaleType scaleType;
        ImageView imageView2;
        int i2;
        this.templateLayout = templateLayout2;
        Context context = templateLayout2.getContext();
        ImageView imageView3 = (ImageView) templateLayout2.findManagedViewById(2131363739);
        if (imageView3 != null) {
            this.originalHeight = imageView3.getLayoutParams().height;
            this.originalScaleType = imageView3.getScaleType();
        } else {
            this.originalHeight = 0;
            this.originalScaleType = null;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SudIconMixin, i, 0);
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        if (!(resourceId == 0 || (imageView2 = (ImageView) templateLayout2.findManagedViewById(2131363739)) == null)) {
            imageView2.setImageResource(resourceId);
            if (resourceId != 0) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            imageView2.setVisibility(i2);
            int visibility = imageView2.getVisibility();
            if (((FrameLayout) templateLayout2.findManagedViewById(2131363740)) != null) {
                ((FrameLayout) templateLayout2.findManagedViewById(2131363740)).setVisibility(visibility);
            }
        }
        boolean z = obtainStyledAttributes.getBoolean(2, false);
        ImageView imageView4 = (ImageView) templateLayout2.findManagedViewById(2131363739);
        if (imageView4 != null) {
            ViewGroup.LayoutParams layoutParams = imageView4.getLayoutParams();
            layoutParams.height = !z ? this.originalHeight : imageView4.getMaxHeight();
            imageView4.setLayoutParams(layoutParams);
            if (z) {
                scaleType = ImageView.ScaleType.FIT_CENTER;
            } else {
                scaleType = this.originalScaleType;
            }
            imageView4.setScaleType(scaleType);
        }
        int color = obtainStyledAttributes.getColor(1, 0);
        if (!(color == 0 || (imageView = (ImageView) templateLayout2.findManagedViewById(2131363739)) == null)) {
            imageView.setColorFilter(color);
        }
        obtainStyledAttributes.recycle();
    }
}
