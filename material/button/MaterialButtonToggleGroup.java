package com.google.android.material.button;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.LinearLayout;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.android.keyguard.ClockEventController$zenModeCallback$1$$ExternalSyntheticOutline0;
import com.google.android.material.R$styleable;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.shape.AbsoluteCornerSize;
import com.google.android.material.shape.CornerSize;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import com.google.android.material.timepicker.TimePickerView$$ExternalSyntheticLambda0;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.WeakHashMap;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class MaterialButtonToggleGroup extends LinearLayout {
    public static final /* synthetic */ int $r8$clinit = 0;
    public Set checkedIds;
    public Integer[] childOrder;
    public final AnonymousClass1 childOrderComparator;
    public final int defaultCheckId;
    public final LinkedHashSet onButtonCheckedListeners;
    public final List originalCornerData;
    public final PressedStateTracker pressedStateTracker;
    public final boolean selectionRequired;
    public final boolean singleSelection;
    public boolean skipCheckedStateTracker;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class CornerData {
        public static final AbsoluteCornerSize noCorner = new AbsoluteCornerSize(0.0f);
        public final CornerSize bottomLeft;
        public final CornerSize bottomRight;
        public final CornerSize topLeft;
        public final CornerSize topRight;

        public CornerData(CornerSize cornerSize, CornerSize cornerSize2, CornerSize cornerSize3, CornerSize cornerSize4) {
            this.topLeft = cornerSize;
            this.topRight = cornerSize3;
            this.bottomRight = cornerSize4;
            this.bottomLeft = cornerSize2;
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class PressedStateTracker {
        public PressedStateTracker() {
        }
    }

    static {
        Class<MaterialButtonToggleGroup> cls = MaterialButtonToggleGroup.class;
    }

    public MaterialButtonToggleGroup(Context context) {
        this(context, (AttributeSet) null);
    }

    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (!(view instanceof MaterialButton)) {
            Log.e("MaterialButtonToggleGroup", "Child views must be of type MaterialButton.");
            return;
        }
        super.addView(view, i, layoutParams);
        MaterialButton materialButton = (MaterialButton) view;
        if (materialButton.getId() == -1) {
            WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
            materialButton.setId(ViewCompat.Api17Impl.generateViewId());
        }
        materialButton.setMaxLines(1);
        materialButton.setEllipsize(TextUtils.TruncateAt.END);
        if (materialButton.isUsingOriginalBackground()) {
            materialButton.materialButtonHelper.checkable = true;
        }
        materialButton.onPressedChangeListenerInternal = this.pressedStateTracker;
        if (materialButton.isUsingOriginalBackground()) {
            MaterialButtonHelper materialButtonHelper = materialButton.materialButtonHelper;
            materialButtonHelper.shouldDrawSurfaceColorStroke = true;
            int i2 = 0;
            MaterialShapeDrawable materialShapeDrawable = materialButtonHelper.getMaterialShapeDrawable(false);
            MaterialShapeDrawable materialShapeDrawable2 = materialButtonHelper.getMaterialShapeDrawable(true);
            if (materialShapeDrawable != null) {
                ColorStateList colorStateList = materialButtonHelper.strokeColor;
                materialShapeDrawable.drawableState.strokeWidth = (float) materialButtonHelper.strokeWidth;
                materialShapeDrawable.invalidateSelf();
                materialShapeDrawable.setStrokeColor(colorStateList);
                if (materialShapeDrawable2 != null) {
                    float f = (float) materialButtonHelper.strokeWidth;
                    if (materialButtonHelper.shouldDrawSurfaceColorStroke) {
                        i2 = MaterialColors.getColor(materialButtonHelper.materialButton, 2130968887);
                    }
                    materialShapeDrawable2.drawableState.strokeWidth = f;
                    materialShapeDrawable2.invalidateSelf();
                    materialShapeDrawable2.setStrokeColor(ColorStateList.valueOf(i2));
                }
            }
        }
        checkInternal(materialButton.getId(), materialButton.checked);
        if (materialButton.isUsingOriginalBackground()) {
            ShapeAppearanceModel shapeAppearanceModel = materialButton.materialButtonHelper.shapeAppearanceModel;
            this.originalCornerData.add(new CornerData(shapeAppearanceModel.topLeftCornerSize, shapeAppearanceModel.bottomLeftCornerSize, shapeAppearanceModel.topRightCornerSize, shapeAppearanceModel.bottomRightCornerSize));
            ViewCompat.setAccessibilityDelegate(materialButton, new AccessibilityDelegateCompat() {
                public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                    int i;
                    this.mOriginalDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat.mInfo);
                    int i2 = MaterialButtonToggleGroup.$r8$clinit;
                    MaterialButtonToggleGroup materialButtonToggleGroup = MaterialButtonToggleGroup.this;
                    materialButtonToggleGroup.getClass();
                    if (view instanceof MaterialButton) {
                        int i3 = 0;
                        int i4 = 0;
                        while (true) {
                            if (i3 >= materialButtonToggleGroup.getChildCount()) {
                                break;
                            } else if (materialButtonToggleGroup.getChildAt(i3) == view) {
                                i = i4;
                                break;
                            } else {
                                if ((materialButtonToggleGroup.getChildAt(i3) instanceof MaterialButton) && materialButtonToggleGroup.isChildVisible(i3)) {
                                    i4++;
                                }
                                i3++;
                            }
                        }
                    }
                    i = -1;
                    accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(0, 1, i, 1, false, ((MaterialButton) view).checked));
                }
            });
            return;
        }
        throw new IllegalStateException("Attempted to get ShapeAppearanceModel from a MaterialButton which has an overwritten background.");
    }

    public final void adjustChildMarginsAndUpdateLayout() {
        int i;
        int i2;
        LinearLayout.LayoutParams layoutParams;
        int childCount = getChildCount();
        int i3 = 0;
        while (true) {
            if (i3 >= childCount) {
                i3 = -1;
                break;
            } else if (isChildVisible(i3)) {
                break;
            } else {
                i3++;
            }
        }
        if (i3 != -1) {
            for (int i4 = i3 + 1; i4 < getChildCount(); i4++) {
                MaterialButton materialButton = (MaterialButton) getChildAt(i4);
                MaterialButton materialButton2 = (MaterialButton) getChildAt(i4 - 1);
                if (materialButton.isUsingOriginalBackground()) {
                    i = materialButton.materialButtonHelper.strokeWidth;
                } else {
                    i = 0;
                }
                if (materialButton2.isUsingOriginalBackground()) {
                    i2 = materialButton2.materialButtonHelper.strokeWidth;
                } else {
                    i2 = 0;
                }
                int min = Math.min(i, i2);
                ViewGroup.LayoutParams layoutParams2 = materialButton.getLayoutParams();
                if (layoutParams2 instanceof LinearLayout.LayoutParams) {
                    layoutParams = (LinearLayout.LayoutParams) layoutParams2;
                } else {
                    layoutParams = new LinearLayout.LayoutParams(layoutParams2.width, layoutParams2.height);
                }
                if (getOrientation() == 0) {
                    layoutParams.setMarginEnd(0);
                    layoutParams.setMarginStart(-min);
                    layoutParams.topMargin = 0;
                } else {
                    layoutParams.bottomMargin = 0;
                    layoutParams.topMargin = -min;
                    layoutParams.setMarginStart(0);
                }
                materialButton.setLayoutParams(layoutParams);
            }
            if (getChildCount() != 0 && i3 != -1) {
                LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) ((MaterialButton) getChildAt(i3)).getLayoutParams();
                if (getOrientation() == 1) {
                    layoutParams3.topMargin = 0;
                    layoutParams3.bottomMargin = 0;
                    return;
                }
                layoutParams3.setMarginEnd(0);
                layoutParams3.setMarginStart(0);
                layoutParams3.leftMargin = 0;
                layoutParams3.rightMargin = 0;
            }
        }
    }

    public final void checkInternal(int i, boolean z) {
        if (i == -1) {
            ClockEventController$zenModeCallback$1$$ExternalSyntheticOutline0.m("Button ID is not valid: ", i, "MaterialButtonToggleGroup");
            return;
        }
        HashSet hashSet = new HashSet(this.checkedIds);
        if (z && !hashSet.contains(Integer.valueOf(i))) {
            if (this.singleSelection && !hashSet.isEmpty()) {
                hashSet.clear();
            }
            hashSet.add(Integer.valueOf(i));
        } else if (!z && hashSet.contains(Integer.valueOf(i))) {
            if (!this.selectionRequired || hashSet.size() > 1) {
                hashSet.remove(Integer.valueOf(i));
            }
        } else {
            return;
        }
        updateCheckedIds(hashSet);
    }

    public final void dispatchDraw(Canvas canvas) {
        TreeMap treeMap = new TreeMap(this.childOrderComparator);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            treeMap.put((MaterialButton) getChildAt(i), Integer.valueOf(i));
        }
        this.childOrder = (Integer[]) treeMap.values().toArray(new Integer[0]);
        super.dispatchDraw(canvas);
    }

    public final int getChildDrawingOrder(int i, int i2) {
        Integer[] numArr = this.childOrder;
        if (numArr != null && i2 < numArr.length) {
            return numArr[i2].intValue();
        }
        Log.w("MaterialButtonToggleGroup", "Child order wasn't updated");
        return i2;
    }

    public final boolean isChildVisible(int i) {
        if (getChildAt(i).getVisibility() != 8) {
            return true;
        }
        return false;
    }

    public final void onFinishInflate() {
        super.onFinishInflate();
        int i = this.defaultCheckId;
        if (i != -1) {
            updateCheckedIds(Collections.singleton(Integer.valueOf(i)));
        }
    }

    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        int i;
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        int i2 = 0;
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            if ((getChildAt(i3) instanceof MaterialButton) && isChildVisible(i3)) {
                i2++;
            }
        }
        if (this.singleSelection) {
            i = 1;
        } else {
            i = 2;
        }
        accessibilityNodeInfo.setCollectionInfo((AccessibilityNodeInfo.CollectionInfo) AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(1, i2, i).mInfo);
    }

    public final void onMeasure(int i, int i2) {
        updateChildShapes();
        adjustChildMarginsAndUpdateLayout();
        super.onMeasure(i, i2);
    }

    public final void onViewRemoved(View view) {
        super.onViewRemoved(view);
        if (view instanceof MaterialButton) {
            ((MaterialButton) view).onPressedChangeListenerInternal = null;
        }
        int indexOfChild = indexOfChild(view);
        if (indexOfChild >= 0) {
            this.originalCornerData.remove(indexOfChild);
        }
        updateChildShapes();
        adjustChildMarginsAndUpdateLayout();
    }

    public final void updateCheckedIds(Set set) {
        Set set2 = this.checkedIds;
        this.checkedIds = new HashSet(set);
        for (int i = 0; i < getChildCount(); i++) {
            int id = ((MaterialButton) getChildAt(i)).getId();
            boolean contains = set.contains(Integer.valueOf(id));
            View findViewById = findViewById(id);
            if (findViewById instanceof MaterialButton) {
                this.skipCheckedStateTracker = true;
                ((MaterialButton) findViewById).setChecked(contains);
                this.skipCheckedStateTracker = false;
            }
            if (set2.contains(Integer.valueOf(id)) != set.contains(Integer.valueOf(id))) {
                set.contains(Integer.valueOf(id));
                Iterator it = this.onButtonCheckedListeners.iterator();
                while (it.hasNext()) {
                    ((TimePickerView$$ExternalSyntheticLambda0) it.next()).onButtonChecked();
                }
            }
        }
        invalidate();
    }

    public void updateChildShapes() {
        int i;
        boolean z;
        CornerData cornerData;
        int childCount = getChildCount();
        int childCount2 = getChildCount();
        int i2 = 0;
        while (true) {
            i = -1;
            if (i2 >= childCount2) {
                i2 = -1;
                break;
            } else if (isChildVisible(i2)) {
                break;
            } else {
                i2++;
            }
        }
        int childCount3 = getChildCount() - 1;
        while (true) {
            if (childCount3 < 0) {
                break;
            } else if (isChildVisible(childCount3)) {
                i = childCount3;
                break;
            } else {
                childCount3--;
            }
        }
        for (int i3 = 0; i3 < childCount; i3++) {
            MaterialButton materialButton = (MaterialButton) getChildAt(i3);
            if (materialButton.getVisibility() != 8) {
                if (materialButton.isUsingOriginalBackground()) {
                    ShapeAppearanceModel.Builder builder = materialButton.materialButtonHelper.shapeAppearanceModel.toBuilder();
                    CornerData cornerData2 = (CornerData) ((ArrayList) this.originalCornerData).get(i3);
                    if (i2 != i) {
                        if (getOrientation() == 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        AbsoluteCornerSize absoluteCornerSize = CornerData.noCorner;
                        if (i3 == i2) {
                            if (!z) {
                                cornerData = new CornerData(cornerData2.topLeft, absoluteCornerSize, cornerData2.topRight, absoluteCornerSize);
                            } else if (ViewUtils.isLayoutRtl(this)) {
                                cornerData = new CornerData(absoluteCornerSize, absoluteCornerSize, cornerData2.topRight, cornerData2.bottomRight);
                            } else {
                                cornerData = new CornerData(cornerData2.topLeft, cornerData2.bottomLeft, absoluteCornerSize, absoluteCornerSize);
                            }
                        } else if (i3 != i) {
                            cornerData2 = null;
                        } else if (!z) {
                            cornerData = new CornerData(absoluteCornerSize, cornerData2.bottomLeft, absoluteCornerSize, cornerData2.bottomRight);
                        } else if (ViewUtils.isLayoutRtl(this)) {
                            cornerData = new CornerData(cornerData2.topLeft, cornerData2.bottomLeft, absoluteCornerSize, absoluteCornerSize);
                        } else {
                            cornerData = new CornerData(absoluteCornerSize, absoluteCornerSize, cornerData2.topRight, cornerData2.bottomRight);
                        }
                        cornerData2 = cornerData;
                    }
                    if (cornerData2 == null) {
                        builder.setAllCornerSizes(0.0f);
                    } else {
                        builder.topLeftCornerSize = cornerData2.topLeft;
                        builder.bottomLeftCornerSize = cornerData2.bottomLeft;
                        builder.topRightCornerSize = cornerData2.topRight;
                        builder.bottomRightCornerSize = cornerData2.bottomRight;
                    }
                    materialButton.setShapeAppearanceModel(builder.build());
                } else {
                    throw new IllegalStateException("Attempted to get ShapeAppearanceModel from a MaterialButton which has an overwritten background.");
                }
            }
        }
    }

    public MaterialButtonToggleGroup(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 2130969524);
    }

    public MaterialButtonToggleGroup(Context context, AttributeSet attributeSet, int i) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, i, 2132018816), attributeSet, i);
        this.originalCornerData = new ArrayList();
        this.pressedStateTracker = new PressedStateTracker();
        this.onButtonCheckedListeners = new LinkedHashSet();
        this.childOrderComparator = new Comparator() {
            public final int compare(Object obj, Object obj2) {
                MaterialButton materialButton = (MaterialButton) obj;
                MaterialButton materialButton2 = (MaterialButton) obj2;
                int compareTo = Boolean.valueOf(materialButton.checked).compareTo(Boolean.valueOf(materialButton2.checked));
                if (compareTo != 0) {
                    return compareTo;
                }
                int compareTo2 = Boolean.valueOf(materialButton.isPressed()).compareTo(Boolean.valueOf(materialButton2.isPressed()));
                if (compareTo2 != 0) {
                    return compareTo2;
                }
                return Integer.valueOf(MaterialButtonToggleGroup.this.indexOfChild(materialButton)).compareTo(Integer.valueOf(MaterialButtonToggleGroup.this.indexOfChild(materialButton2)));
            }
        };
        this.skipCheckedStateTracker = false;
        this.checkedIds = new HashSet();
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(getContext(), attributeSet, R$styleable.MaterialButtonToggleGroup, i, 2132018816, new int[0]);
        boolean z = obtainStyledAttributes.getBoolean(2, false);
        if (this.singleSelection != z) {
            this.singleSelection = z;
            updateCheckedIds(new HashSet());
        }
        this.defaultCheckId = obtainStyledAttributes.getResourceId(0, -1);
        this.selectionRequired = obtainStyledAttributes.getBoolean(1, false);
        setChildrenDrawingOrderEnabled(true);
        obtainStyledAttributes.recycle();
        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
        ViewCompat.Api16Impl.setImportantForAccessibility(this, 1);
    }
}
