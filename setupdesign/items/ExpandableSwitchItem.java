package com.google.android.setupdesign.items;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.setupdesign.R$styleable;
import com.google.android.setupdesign.util.LayoutStyler;
import com.google.android.setupdesign.view.CheckableLinearLayout;
import java.util.WeakHashMap;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class ExpandableSwitchItem extends SwitchItem implements View.OnClickListener {
    public final AnonymousClass1 accessibilityDelegate = new AccessibilityDelegateCompat() {
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
                expandableSwitchItem.notifyItemRangeChanged(0, 1);
            }
            return true;
        }
    };
    public final CharSequence collapsedSummary;
    public final CharSequence expandedSummary;
    public boolean isExpanded = false;

    public ExpandableSwitchItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SudExpandableSwitchItem);
        this.collapsedSummary = obtainStyledAttributes.getText(0);
        this.expandedSummary = obtainStyledAttributes.getText(1);
        this.iconGravity = obtainStyledAttributes.getInt(7, 48);
        obtainStyledAttributes.recycle();
    }

    public final int getDefaultLayoutResource() {
        return 2131559106;
    }

    public final CharSequence getSummary() {
        if (this.isExpanded) {
            return this.expandedSummary;
        }
        return this.collapsedSummary;
    }

    public final void onBindView(View view) {
        super.onBindView(view);
        View findViewById = view.findViewById(2131363725);
        findViewById.setOnClickListener(this);
        if (findViewById instanceof CheckableLinearLayout) {
            CheckableLinearLayout checkableLinearLayout = (CheckableLinearLayout) findViewById;
            checkableLinearLayout.setChecked(this.isExpanded);
            boolean z = this.isExpanded;
            WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
            ViewCompat.Api19Impl.setAccessibilityLiveRegion(checkableLinearLayout, z ? 1 : 0);
            ViewCompat.setAccessibilityDelegate(checkableLinearLayout, this.accessibilityDelegate);
        }
        TypedArray obtainStyledAttributes = view.getContext().obtainStyledAttributes(new int[]{16842806});
        ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(0);
        obtainStyledAttributes.recycle();
        if (colorStateList != null) {
            TextView textView = (TextView) view.findViewById(2131363731);
            for (Drawable drawable : textView.getCompoundDrawables()) {
                if (drawable != null) {
                    drawable.setColorFilter(colorStateList.getDefaultColor(), PorterDuff.Mode.SRC_IN);
                }
            }
            for (Drawable drawable2 : textView.getCompoundDrawablesRelative()) {
                if (drawable2 != null) {
                    drawable2.setColorFilter(colorStateList.getDefaultColor(), PorterDuff.Mode.SRC_IN);
                }
            }
        }
        view.setFocusable(false);
        LayoutStyler.applyPartnerCustomizationLayoutPaddingStyle(findViewById);
    }

    public final void onClick(View view) {
        boolean z = this.isExpanded;
        boolean z2 = !z;
        if (z != z2) {
            this.isExpanded = z2;
            notifyItemRangeChanged(0, 1);
        }
    }
}
