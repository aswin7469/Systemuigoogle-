package com.google.android.material.bottomsheet;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class BottomSheetDragHandleView extends AppCompatImageView implements AccessibilityManager.AccessibilityStateChangeListener {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final AccessibilityManager accessibilityManager = ((AccessibilityManager) getContext().getSystemService("accessibility"));
    public boolean accessibilityServiceEnabled;
    public BottomSheetBehavior bottomSheetBehavior;
    public final AnonymousClass1 bottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() {
        public final void onStateChanged(View view, int i) {
            String str;
            int i2 = BottomSheetDragHandleView.$r8$clinit;
            BottomSheetDragHandleView bottomSheetDragHandleView = BottomSheetDragHandleView.this;
            if (i == 4) {
                str = bottomSheetDragHandleView.clickToExpandActionLabel;
            } else {
                str = bottomSheetDragHandleView.clickToCollapseActionLabel;
            }
            ViewCompat.replaceAccessibilityAction(bottomSheetDragHandleView, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK, str, new BottomSheetDragHandleView$$ExternalSyntheticLambda0(bottomSheetDragHandleView));
        }

        public final void onSlide(View view) {
        }
    };
    public final String clickFeedback = getResources().getString(2131952111);
    public final String clickToCollapseActionLabel = getResources().getString(2131952108);
    public final String clickToExpandActionLabel = getResources().getString(2131952109);
    public boolean interactable;

    public BottomSheetDragHandleView(Context context, AttributeSet attributeSet) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, 2130968718, 2132018630), attributeSet, 2130968718);
        updateInteractableState();
        ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegateCompat() {
            public final void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
                super.onPopulateAccessibilityEvent(view, accessibilityEvent);
                if (accessibilityEvent.getEventType() == 1) {
                    int i = BottomSheetDragHandleView.$r8$clinit;
                    BottomSheetDragHandleView.this.toggleBottomSheetIfPossible();
                }
            }
        });
    }

    public final void onAccessibilityStateChanged(boolean z) {
        this.accessibilityServiceEnabled = z;
        updateInteractableState();
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onAttachedToWindow() {
        /*
            r3 = this;
            super.onAttachedToWindow()
            r0 = r3
        L_0x0004:
            android.view.ViewParent r0 = r0.getParent()
            boolean r1 = r0 instanceof android.view.View
            r2 = 0
            if (r1 == 0) goto L_0x0010
            android.view.View r0 = (android.view.View) r0
            goto L_0x0011
        L_0x0010:
            r0 = r2
        L_0x0011:
            if (r0 == 0) goto L_0x0026
            android.view.ViewGroup$LayoutParams r1 = r0.getLayoutParams()
            boolean r2 = r1 instanceof androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams
            if (r2 == 0) goto L_0x0004
            androidx.coordinatorlayout.widget.CoordinatorLayout$LayoutParams r1 = (androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams) r1
            androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior r1 = r1.mBehavior
            boolean r2 = r1 instanceof com.google.android.material.bottomsheet.BottomSheetBehavior
            if (r2 == 0) goto L_0x0004
            r2 = r1
            com.google.android.material.bottomsheet.BottomSheetBehavior r2 = (com.google.android.material.bottomsheet.BottomSheetBehavior) r2
        L_0x0026:
            r3.setBottomSheetBehavior(r2)
            android.view.accessibility.AccessibilityManager r0 = r3.accessibilityManager
            if (r0 == 0) goto L_0x0039
            r0.addAccessibilityStateChangeListener(r3)
            android.view.accessibility.AccessibilityManager r0 = r3.accessibilityManager
            boolean r0 = r0.isEnabled()
            r3.onAccessibilityStateChanged(r0)
        L_0x0039:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetDragHandleView.onAttachedToWindow():void");
    }

    public final void onDetachedFromWindow() {
        AccessibilityManager accessibilityManager2 = this.accessibilityManager;
        if (accessibilityManager2 != null) {
            accessibilityManager2.removeAccessibilityStateChangeListener(this);
        }
        setBottomSheetBehavior((BottomSheetBehavior) null);
        super.onDetachedFromWindow();
    }

    public final void setBottomSheetBehavior(BottomSheetBehavior bottomSheetBehavior2) {
        String str;
        BottomSheetBehavior bottomSheetBehavior3 = this.bottomSheetBehavior;
        if (bottomSheetBehavior3 != null) {
            bottomSheetBehavior3.callbacks.remove(this.bottomSheetCallback);
        }
        this.bottomSheetBehavior = bottomSheetBehavior2;
        if (bottomSheetBehavior2 != null) {
            if (bottomSheetBehavior2.state == 4) {
                str = this.clickToExpandActionLabel;
            } else {
                str = this.clickToCollapseActionLabel;
            }
            ViewCompat.replaceAccessibilityAction(this, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK, str, new BottomSheetDragHandleView$$ExternalSyntheticLambda0(this));
            BottomSheetBehavior bottomSheetBehavior4 = this.bottomSheetBehavior;
            AnonymousClass1 r0 = this.bottomSheetCallback;
            ArrayList arrayList = bottomSheetBehavior4.callbacks;
            if (!arrayList.contains(r0)) {
                arrayList.add(r0);
            }
        }
        updateInteractableState();
    }

    public final boolean toggleBottomSheetIfPossible() {
        boolean z = false;
        if (!this.interactable) {
            return false;
        }
        String str = this.clickFeedback;
        if (this.accessibilityManager != null) {
            AccessibilityEvent obtain = AccessibilityEvent.obtain(16384);
            obtain.getText().add(str);
            this.accessibilityManager.sendAccessibilityEvent(obtain);
        }
        BottomSheetBehavior bottomSheetBehavior2 = this.bottomSheetBehavior;
        int i = 4;
        if (bottomSheetBehavior2.state == 4) {
            z = true;
        }
        if (z) {
            i = 3;
        }
        bottomSheetBehavior2.setState(i);
        return true;
    }

    public final void updateInteractableState() {
        boolean z;
        int i = 1;
        if (!this.accessibilityServiceEnabled || this.bottomSheetBehavior == null) {
            z = false;
        } else {
            z = true;
        }
        this.interactable = z;
        if (this.bottomSheetBehavior == null) {
            i = 2;
        }
        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
        ViewCompat.Api16Impl.setImportantForAccessibility(this, i);
        setClickable(this.interactable);
    }
}
