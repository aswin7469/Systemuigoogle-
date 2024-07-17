package com.google.android.setupdesign.items;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.setupdesign.R$styleable;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public class ExpandableSwitchItem extends SwitchItem implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    public boolean isExpanded = false;

    public ExpandableSwitchItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        new AccessibilityDelegateCompat() {
            public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat;
                this.mOriginalDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat.mInfo);
                if (ExpandableSwitchItem.this.isExpanded) {
                    accessibilityActionCompat = AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_COLLAPSE;
                } else {
                    accessibilityActionCompat = AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_EXPAND;
                }
                accessibilityNodeInfoCompat.addAction(accessibilityActionCompat);
            }

            public final boolean performAccessibilityAction(View view, int i, Bundle bundle) {
                if (i != 262144 && i != 524288) {
                    return super.performAccessibilityAction(view, i, bundle);
                }
                ExpandableSwitchItem expandableSwitchItem = ExpandableSwitchItem.this;
                boolean z = expandableSwitchItem.isExpanded;
                boolean z2 = !z;
                if (z != z2) {
                    expandableSwitchItem.isExpanded = z2;
                    expandableSwitchItem.notifyItemRangeChanged(0);
                }
                return true;
            }
        };
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SudExpandableSwitchItem);
        obtainStyledAttributes.getText(0);
        obtainStyledAttributes.getText(1);
        obtainStyledAttributes.getInt(7, 48);
        obtainStyledAttributes.recycle();
    }

    public final int getDefaultLayoutResource() {
        return 2131559124;
    }

    public final void onClick(View view) {
        boolean z = this.isExpanded;
        boolean z2 = !z;
        if (z != z2) {
            this.isExpanded = z2;
            notifyItemRangeChanged(0);
        }
    }
}
