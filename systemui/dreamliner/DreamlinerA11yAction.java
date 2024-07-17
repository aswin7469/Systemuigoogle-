package com.google.android.systemui.dreamliner;

import android.view.View;
import com.google.common.base.Preconditions;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class DreamlinerA11yAction {
    public int mActionId = -1;
    public final String mActionLabel;
    public final AnonymousClass1 mActionWrapper;
    public final Runnable mCustomAction;
    public final View mViewForAction;

    /* renamed from: com.google.android.systemui.dreamliner.DreamlinerA11yAction$1  reason: invalid class name */
    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class AnonymousClass1 {
    }

    /* JADX WARNING: type inference failed for: r3v1, types: [com.google.android.systemui.dreamliner.DreamlinerA11yAction$1, java.lang.Object] */
    public DreamlinerA11yAction(String str, View view, DockGestureController$$ExternalSyntheticLambda0 dockGestureController$$ExternalSyntheticLambda0) {
        String str2;
        if (str == null || str.isEmpty()) {
            str2 = null;
        } else {
            str2 = str;
        }
        Preconditions.checkNotNull(str2, "action label cannot be null or empty");
        Preconditions.checkNotNull(view, "view cannot be null");
        this.mActionLabel = str;
        this.mViewForAction = view;
        this.mCustomAction = dockGestureController$$ExternalSyntheticLambda0;
        this.mActionWrapper = new Object();
    }
}
