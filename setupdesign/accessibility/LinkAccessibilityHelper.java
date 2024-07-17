package com.google.android.setupdesign.accessibility;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeProviderCompat;
import androidx.customview.widget.ExploreByTouchHelper;
import java.util.List;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class LinkAccessibilityHelper extends AccessibilityDelegateCompat {
    public final AccessibilityDelegateCompat delegate;

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    class PreOLinkAccessibilityHelper extends ExploreByTouchHelper {
        public final int getVirtualViewAt(float f, float f2) {
            throw null;
        }

        public final void getVisibleVirtualViews(List list) {
            throw null;
        }

        public final boolean onPerformActionForVirtualView(int i, int i2) {
            if (i2 != 16) {
                return false;
            }
            throw null;
        }

        public final void onPopulateEventForVirtualView(int i, AccessibilityEvent accessibilityEvent) {
            throw null;
        }

        public final void onPopulateNodeForVirtualView(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            throw null;
        }
    }

    public LinkAccessibilityHelper(AccessibilityDelegateCompat accessibilityDelegateCompat) {
        this.delegate = accessibilityDelegateCompat;
    }

    public final boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        return this.delegate.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public final AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View view) {
        return this.delegate.getAccessibilityNodeProvider(view);
    }

    public final void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        this.delegate.onInitializeAccessibilityEvent(view, accessibilityEvent);
    }

    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        this.delegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
    }

    public final void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        this.delegate.onPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public final boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return this.delegate.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    public final boolean performAccessibilityAction(View view, int i, Bundle bundle) {
        return this.delegate.performAccessibilityAction(view, i, bundle);
    }

    public final void sendAccessibilityEvent(View view, int i) {
        this.delegate.sendAccessibilityEvent(view, i);
    }

    public final void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
        this.delegate.sendAccessibilityEventUnchecked(view, accessibilityEvent);
    }
}
