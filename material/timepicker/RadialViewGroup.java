package com.google.android.material.timepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.view.ViewCompat;
import com.google.android.material.R$styleable;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.RelativeCornerSize;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.util.WeakHashMap;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class RadialViewGroup extends ConstraintLayout {
    public final MaterialShapeDrawable background;
    public int radius;
    public final RadialViewGroup$$ExternalSyntheticLambda0 updateLayoutParametersRunnable = new RadialViewGroup$$ExternalSyntheticLambda0(this);

    public RadialViewGroup(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        LayoutInflater.from(context).inflate(2131558757, this);
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable();
        this.background = materialShapeDrawable;
        RelativeCornerSize relativeCornerSize = new RelativeCornerSize(0.5f);
        ShapeAppearanceModel.Builder builder = materialShapeDrawable.drawableState.shapeAppearanceModel.toBuilder();
        builder.topLeftCornerSize = relativeCornerSize;
        builder.topRightCornerSize = relativeCornerSize;
        builder.bottomRightCornerSize = relativeCornerSize;
        builder.bottomLeftCornerSize = relativeCornerSize;
        materialShapeDrawable.setShapeAppearanceModel(builder.build());
        this.background.setFillColor(ColorStateList.valueOf(-1));
        MaterialShapeDrawable materialShapeDrawable2 = this.background;
        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
        setBackground(materialShapeDrawable2);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.RadialViewGroup, i, 0);
        this.radius = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        obtainStyledAttributes.recycle();
    }

    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        if (view.getId() == -1) {
            WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
            view.setId(View.generateViewId());
        }
        Handler handler = getHandler();
        if (handler != null) {
            handler.removeCallbacks(this.updateLayoutParametersRunnable);
            handler.post(this.updateLayoutParametersRunnable);
        }
    }

    public final void onFinishInflate() {
        super.onFinishInflate();
        updateLayoutParams$1();
    }

    public final void onViewRemoved(View view) {
        super.onViewRemoved(view);
        Handler handler = getHandler();
        if (handler != null) {
            handler.removeCallbacks(this.updateLayoutParametersRunnable);
            handler.post(this.updateLayoutParametersRunnable);
        }
    }

    public final void setBackgroundColor(int i) {
        this.background.setFillColor(ColorStateList.valueOf(i));
    }

    public final void updateLayoutParams$1() {
        int childCount = getChildCount();
        int i = 1;
        for (int i2 = 0; i2 < childCount; i2++) {
            if ("skip".equals(getChildAt(i2).getTag())) {
                i++;
            }
        }
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone((ConstraintLayout) this);
        float f = 0.0f;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getId() != 2131362268 && !"skip".equals(childAt.getTag())) {
                int id = childAt.getId();
                int i4 = this.radius;
                ConstraintSet.Layout layout = constraintSet.get(id).layout;
                layout.circleConstraint = 2131362268;
                layout.circleRadius = i4;
                layout.circleAngle = f;
                f = (360.0f / ((float) (childCount - i))) + f;
            }
        }
        constraintSet.applyTo(this);
    }
}
