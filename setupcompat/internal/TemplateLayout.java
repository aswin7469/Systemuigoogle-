package com.google.android.setupcompat.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import com.google.android.setupcompat.R$styleable;
import com.google.android.setupcompat.template.Mixin;
import java.util.HashMap;
import java.util.Map;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class TemplateLayout extends FrameLayout {
    public ViewGroup container;
    public final Map mixins = new HashMap();
    public AnonymousClass1 preDrawListener;
    public float xFraction;

    public TemplateLayout(Context context, int i, int i2) {
        super(context);
        init(i, i2, (AttributeSet) null, 2130970022);
    }

    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        this.container.addView(view, i, layoutParams);
    }

    public ViewGroup findContainer(int i) {
        return (ViewGroup) findViewById(i);
    }

    public View findManagedViewById(int i) {
        return findViewById(i);
    }

    public final Mixin getMixin(Class cls) {
        return (Mixin) this.mixins.get(cls);
    }

    public float getXFraction() {
        return this.xFraction;
    }

    public final View inflateTemplate(LayoutInflater layoutInflater, int i, int i2) {
        if (i2 != 0) {
            if (i != 0) {
                layoutInflater = LayoutInflater.from(new ContextThemeWrapper(layoutInflater.getContext(), i));
            }
            return layoutInflater.inflate(i2, this, false);
        }
        throw new IllegalArgumentException("android:layout not specified for TemplateLayout");
    }

    public final void init(int i, int i2, AttributeSet attributeSet, int i3) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.SucTemplateLayout, i3, 0);
        if (i == 0) {
            i = obtainStyledAttributes.getResourceId(0, 0);
        }
        if (i2 == 0) {
            i2 = obtainStyledAttributes.getResourceId(1, 0);
        }
        onBeforeTemplateInflated(attributeSet, i3);
        super.addView(onInflateTemplate(LayoutInflater.from(getContext()), i), -1, generateDefaultLayoutParams());
        ViewGroup findContainer = findContainer(i2);
        this.container = findContainer;
        if (findContainer != null) {
            onTemplateInflated();
            obtainStyledAttributes.recycle();
            return;
        }
        throw new IllegalArgumentException("Container cannot be null in TemplateLayout");
    }

    public View onInflateTemplate(LayoutInflater layoutInflater, int i) {
        return inflateTemplate(layoutInflater, 0, i);
    }

    public final void registerMixin(Class cls, Mixin mixin) {
        this.mixins.put(cls, mixin);
    }

    public void setXFraction(float f) {
        this.xFraction = f;
        int width = getWidth();
        if (width != 0) {
            setTranslationX(((float) width) * f);
        } else if (this.preDrawListener == null) {
            this.preDrawListener = new ViewTreeObserver.OnPreDrawListener() {
                public final boolean onPreDraw() {
                    TemplateLayout.this.getViewTreeObserver().removeOnPreDrawListener(TemplateLayout.this.preDrawListener);
                    TemplateLayout templateLayout = TemplateLayout.this;
                    templateLayout.setXFraction(templateLayout.xFraction);
                    return true;
                }
            };
            getViewTreeObserver().addOnPreDrawListener(this.preDrawListener);
        }
    }

    public TemplateLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(0, 0, attributeSet, 2130970022);
    }

    public TemplateLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(0, 0, attributeSet, i);
    }

    public void onTemplateInflated() {
    }

    public void onBeforeTemplateInflated(AttributeSet attributeSet, int i) {
    }
}
