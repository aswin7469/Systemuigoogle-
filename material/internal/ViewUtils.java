package com.google.android.material.internal;

import android.content.Context;
import android.graphics.PorterDuff;
import android.util.TypedValue;
import android.view.View;
import androidx.core.view.ViewCompat;
import com.android.app.viewcapture.data.ViewNode;
import java.util.WeakHashMap;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class ViewUtils {

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class RelativePadding {
        public int bottom;
        public int end;
        public int start;
    }

    public static float dpToPx(int i, Context context) {
        return TypedValue.applyDimension(1, (float) i, context.getResources().getDisplayMetrics());
    }

    public static boolean isLayoutRtl(View view) {
        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
        if (view.getLayoutDirection() == 1) {
            return true;
        }
        return false;
    }

    public static PorterDuff.Mode parseTintMode(int i, PorterDuff.Mode mode) {
        if (i == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (i) {
            case ViewNode.SCALEY_FIELD_NUMBER:
                return PorterDuff.Mode.MULTIPLY;
            case 15:
                return PorterDuff.Mode.SCREEN;
            case ViewNode.WILLNOTDRAW_FIELD_NUMBER:
                return PorterDuff.Mode.ADD;
            default:
                return mode;
        }
    }

    /* renamed from: com.google.android.material.internal.ViewUtils$4  reason: invalid class name */
    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class AnonymousClass4 implements View.OnAttachStateChangeListener {
        public final void onViewAttachedToWindow(View view) {
            view.removeOnAttachStateChangeListener(this);
            WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
            ViewCompat.Api20Impl.requestApplyInsets(view);
        }

        public final void onViewDetachedFromWindow(View view) {
        }
    }
}
