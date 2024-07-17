package com.google.android.material.chip;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R$styleable;
import com.google.android.material.internal.CheckableGroup;
import com.google.android.material.internal.FlowLayout;
import com.google.android.material.internal.MaterialCheckable;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.util.WeakHashMap;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class ChipGroup extends FlowLayout {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final CheckableGroup checkableGroup;
    public final int chipSpacingHorizontal;
    public final int chipSpacingVertical;
    public final int defaultCheckedId;
    public final PassThroughHierarchyChangeListener passThroughListener = new PassThroughHierarchyChangeListener();

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public class LayoutParams extends ViewGroup.MarginLayoutParams {
        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class PassThroughHierarchyChangeListener implements ViewGroup.OnHierarchyChangeListener {
        public ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener;

        public PassThroughHierarchyChangeListener() {
        }

        public final void onChildViewAdded(View view, View view2) {
            if (view == ChipGroup.this && (view2 instanceof Chip)) {
                if (view2.getId() == -1) {
                    WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                    view2.setId(ViewCompat.Api17Impl.generateViewId());
                }
                CheckableGroup checkableGroup = ChipGroup.this.checkableGroup;
                Chip chip = (Chip) view2;
                checkableGroup.checkables.put(Integer.valueOf(chip.getId()), chip);
                if (chip.isChecked()) {
                    checkableGroup.checkInternal(chip);
                }
                chip.onCheckedChangeListenerInternal = new Object() {
                };
            }
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener2 = this.onHierarchyChangeListener;
            if (onHierarchyChangeListener2 != null) {
                onHierarchyChangeListener2.onChildViewAdded(view, view2);
            }
        }

        public final void onChildViewRemoved(View view, View view2) {
            ChipGroup chipGroup = ChipGroup.this;
            if (view == chipGroup && (view2 instanceof Chip)) {
                CheckableGroup checkableGroup = chipGroup.checkableGroup;
                Chip chip = (Chip) view2;
                checkableGroup.getClass();
                chip.onCheckedChangeListenerInternal = null;
                checkableGroup.checkables.remove(Integer.valueOf(chip.getId()));
                checkableGroup.checkedIds.remove(Integer.valueOf(chip.getId()));
            }
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener2 = this.onHierarchyChangeListener;
            if (onHierarchyChangeListener2 != null) {
                onHierarchyChangeListener2.onChildViewRemoved(view, view2);
            }
        }
    }

    public ChipGroup(Context context, AttributeSet attributeSet) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, 2130968801, 2132018802), attributeSet, 2130968801);
        CheckableGroup checkableGroup2 = new CheckableGroup();
        this.checkableGroup = checkableGroup2;
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(getContext(), attributeSet, R$styleable.ChipGroup, 2130968801, 2132018802, new int[0]);
        int dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelOffset(1, 0);
        int dimensionPixelOffset2 = obtainStyledAttributes.getDimensionPixelOffset(2, dimensionPixelOffset);
        if (this.chipSpacingHorizontal != dimensionPixelOffset2) {
            this.chipSpacingHorizontal = dimensionPixelOffset2;
            this.itemSpacing = dimensionPixelOffset2;
            requestLayout();
        }
        int dimensionPixelOffset3 = obtainStyledAttributes.getDimensionPixelOffset(3, dimensionPixelOffset);
        if (this.chipSpacingVertical != dimensionPixelOffset3) {
            this.chipSpacingVertical = dimensionPixelOffset3;
            this.lineSpacing = dimensionPixelOffset3;
            requestLayout();
        }
        this.singleLine = obtainStyledAttributes.getBoolean(5, false);
        boolean z = obtainStyledAttributes.getBoolean(6, false);
        if (checkableGroup2.singleSelection != z) {
            checkableGroup2.singleSelection = z;
            boolean z2 = !checkableGroup2.checkedIds.isEmpty();
            for (MaterialCheckable uncheckInternal : checkableGroup2.checkables.values()) {
                checkableGroup2.uncheckInternal(uncheckInternal, false);
            }
            if (z2) {
                checkableGroup2.onCheckedStateChanged();
            }
        }
        this.checkableGroup.selectionRequired = obtainStyledAttributes.getBoolean(4, false);
        this.defaultCheckedId = obtainStyledAttributes.getResourceId(0, -1);
        obtainStyledAttributes.recycle();
        this.checkableGroup.onCheckedStateChangeListener = new Object() {
        };
        super.setOnHierarchyChangeListener(this.passThroughListener);
        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
        ViewCompat.Api16Impl.setImportantForAccessibility(this, 1);
    }

    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (!super.checkLayoutParams(layoutParams) || !(layoutParams instanceof LayoutParams)) {
            return false;
        }
        return true;
    }

    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-2, -2);
    }

    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public final boolean isSingleLine() {
        return this.singleLine;
    }

    public final void onFinishInflate() {
        super.onFinishInflate();
        int i = this.defaultCheckedId;
        if (i != -1) {
            CheckableGroup checkableGroup2 = this.checkableGroup;
            MaterialCheckable materialCheckable = (MaterialCheckable) checkableGroup2.checkables.get(Integer.valueOf(i));
            if (materialCheckable != null && checkableGroup2.checkInternal(materialCheckable)) {
                checkableGroup2.onCheckedStateChanged();
            }
        }
    }

    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        int i;
        int i2;
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        if (this.singleLine) {
            i = 0;
            for (int i3 = 0; i3 < getChildCount(); i3++) {
                if ((getChildAt(i3) instanceof Chip) && getChildAt(i3).getVisibility() == 0) {
                    i++;
                }
            }
        } else {
            i = -1;
        }
        int i4 = this.rowCount;
        if (this.checkableGroup.singleSelection) {
            i2 = 1;
        } else {
            i2 = 2;
        }
        accessibilityNodeInfo.setCollectionInfo((AccessibilityNodeInfo.CollectionInfo) AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(i4, i, i2).mInfo);
    }

    public final void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        this.passThroughListener.onHierarchyChangeListener = onHierarchyChangeListener;
    }

    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new ViewGroup.MarginLayoutParams(layoutParams);
    }
}
