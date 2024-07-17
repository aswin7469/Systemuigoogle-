package com.google.android.material.internal;

import android.content.Context;
import android.graphics.PorterDuff;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.android.app.viewcapture.data.ViewNode;
import java.util.WeakHashMap;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class ViewUtils {

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public interface OnApplyWindowInsetsListener {
        WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat, RelativePadding relativePadding);
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class RelativePadding {
        public int bottom;
        public int end;
        public int start;
        public int top;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.android.material.internal.ViewUtils$RelativePadding, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r6v2, types: [java.lang.Object, android.view.View$OnAttachStateChangeListener] */
    public static void doOnApplyWindowInsets(View view, final OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
        int paddingStart = ViewCompat.Api17Impl.getPaddingStart(view);
        int paddingTop = view.getPaddingTop();
        int paddingEnd = ViewCompat.Api17Impl.getPaddingEnd(view);
        int paddingBottom = view.getPaddingBottom();
        final ? obj = new Object();
        obj.start = paddingStart;
        obj.top = paddingTop;
        obj.end = paddingEnd;
        obj.bottom = paddingBottom;
        ViewCompat.Api21Impl.setOnApplyWindowInsetsListener(view, new androidx.core.view.OnApplyWindowInsetsListener() {
            /* JADX WARNING: type inference failed for: r0v0, types: [com.google.android.material.internal.ViewUtils$RelativePadding, java.lang.Object] */
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                ? obj = new Object();
                RelativePadding relativePadding = obj;
                obj.start = relativePadding.start;
                obj.top = relativePadding.top;
                obj.end = relativePadding.end;
                obj.bottom = relativePadding.bottom;
                return OnApplyWindowInsetsListener.this.onApplyWindowInsets(view, windowInsetsCompat, obj);
            }
        });
        if (ViewCompat.Api19Impl.isAttachedToWindow(view)) {
            ViewCompat.Api20Impl.requestApplyInsets(view);
        } else {
            view.addOnAttachStateChangeListener(new Object());
        }
    }

    public static float dpToPx(int i, Context context) {
        return TypedValue.applyDimension(1, (float) i, context.getResources().getDisplayMetrics());
    }

    public static ViewGroup getContentView(View view) {
        if (view == null) {
            return null;
        }
        View rootView = view.getRootView();
        ViewGroup viewGroup = (ViewGroup) rootView.findViewById(16908290);
        if (viewGroup != null) {
            return viewGroup;
        }
        if (rootView == view || !(rootView instanceof ViewGroup)) {
            return null;
        }
        return (ViewGroup) rootView;
    }

    public static ViewOverlayApi18 getContentViewOverlay(View view) {
        ViewGroup contentView = getContentView(view);
        if (contentView == null) {
            return null;
        }
        return new ViewOverlayApi18(contentView);
    }

    public static boolean isLayoutRtl(View view) {
        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
        if (ViewCompat.Api17Impl.getLayoutDirection(view) == 1) {
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
    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
