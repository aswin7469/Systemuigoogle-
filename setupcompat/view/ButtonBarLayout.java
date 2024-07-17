package com.google.android.setupcompat.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.google.android.setupcompat.partnerconfig.PartnerConfigHelper;
import com.google.android.setupcompat.template.FooterActionButton;
import com.google.android.setupcompat.util.Logger;
import java.util.ArrayList;
import java.util.Collections;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public class ButtonBarLayout extends LinearLayout {
    public static final Logger LOG = new Logger("ButtonBarLayout");
    public int originalPaddingLeft;
    public int originalPaddingRight;
    public boolean stacked = false;

    static {
        Class<ButtonBarLayout> cls = ButtonBarLayout.class;
    }

    public ButtonBarLayout(Context context) {
        super(context);
    }

    public final void onMeasure(int i, int i2) {
        boolean z;
        int i3;
        boolean z2;
        int size = View.MeasureSpec.getSize(i);
        setStacked(false);
        if (View.MeasureSpec.getMode(i) == 1073741824) {
            i3 = View.MeasureSpec.makeMeasureSpec(0, 0);
            z = true;
        } else {
            i3 = i;
            z = false;
        }
        super.onMeasure(i3, i2);
        if (size <= 0 || getMeasuredWidth() <= size) {
            z2 = false;
        } else {
            z2 = true;
        }
        Context context = getContext();
        int childCount = getChildCount();
        int i4 = 0;
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if ((childAt instanceof FooterActionButton) && ((FooterActionButton) childAt).isPrimaryButtonStyle) {
                i4++;
            }
        }
        if ((i4 != 2 || context.getResources().getConfiguration().smallestScreenWidthDp < 600 || !PartnerConfigHelper.shouldApplyExtendedPartnerConfig(context)) && z2) {
            setStacked(true);
        } else if (!z) {
            return;
        }
        super.onMeasure(i, i2);
    }

    public final void setStacked(boolean z) {
        boolean z2;
        if (this.stacked != z) {
            this.stacked = z;
            int childCount = getChildCount();
            boolean z3 = false;
            int i = 0;
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = getChildAt(i2);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt.getLayoutParams();
                if (z) {
                    childAt.setTag(2131363742, Float.valueOf(layoutParams.weight));
                    layoutParams.weight = 0.0f;
                    layoutParams.leftMargin = 0;
                } else {
                    Float f = (Float) childAt.getTag(2131363742);
                    if (f != null) {
                        layoutParams.weight = f.floatValue();
                    } else {
                        z3 = true;
                    }
                    if ((childAt instanceof FooterActionButton) && ((FooterActionButton) childAt).isPrimaryButtonStyle) {
                        i++;
                    }
                }
                childAt.setLayoutParams(layoutParams);
            }
            setOrientation(z ? 1 : 0);
            if (z3) {
                LOG.w("Reorder the FooterActionButtons in the container");
                if (i <= 1) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                ArrayList arrayList = new ArrayList();
                if (z2) {
                    arrayList.addAll(Collections.nCopies(3, (Object) null));
                }
                for (int i3 = 0; i3 < childCount; i3++) {
                    View childAt2 = getChildAt(i3);
                    if (z2) {
                        boolean z4 = childAt2 instanceof FooterActionButton;
                        if (z4 && ((FooterActionButton) childAt2).isPrimaryButtonStyle) {
                            arrayList.set(2, childAt2);
                        } else if (!z4) {
                            arrayList.set(1, childAt2);
                        } else {
                            arrayList.set(0, childAt2);
                        }
                    } else if (!(childAt2 instanceof FooterActionButton)) {
                        arrayList.add(1, childAt2);
                    } else {
                        arrayList.add(getChildAt(i3));
                    }
                }
                for (int i4 = 0; i4 < childCount; i4++) {
                    View view = (View) arrayList.get(i4);
                    if (view != null) {
                        bringChildToFront(view);
                    }
                }
            } else {
                for (int i5 = childCount - 1; i5 >= 0; i5--) {
                    bringChildToFront(getChildAt(i5));
                }
            }
            if (z) {
                setHorizontalGravity(17);
                this.originalPaddingLeft = getPaddingLeft();
                int paddingRight = getPaddingRight();
                this.originalPaddingRight = paddingRight;
                int max = Math.max(this.originalPaddingLeft, paddingRight);
                setPadding(max, getPaddingTop(), max, getPaddingBottom());
                return;
            }
            setPadding(this.originalPaddingLeft, getPaddingTop(), this.originalPaddingRight, getPaddingBottom());
        }
    }

    public ButtonBarLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
