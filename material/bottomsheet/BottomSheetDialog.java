package com.google.android.material.bottomsheet;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatDialog;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.SoftwareKeyboardControllerCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.shape.MaterialShapeDrawable;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class BottomSheetDialog extends AppCompatDialog {
    public BottomSheetBehavior behavior;
    public FrameLayout bottomSheet;
    public AnonymousClass5 bottomSheetCallback;
    public boolean cancelable;
    public boolean canceledOnTouchOutside;
    public boolean canceledOnTouchOutsideSet;
    public FrameLayout container;
    public CoordinatorLayout coordinator;
    public EdgeToEdgeCallback edgeToEdgeCallback;
    public boolean edgeToEdgeEnabled;

    /* renamed from: com.google.android.material.bottomsheet.BottomSheetDialog$4  reason: invalid class name */
    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class AnonymousClass4 implements View.OnTouchListener {
        public final boolean onTouch(View view, MotionEvent motionEvent) {
            return true;
        }
    }

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class EdgeToEdgeCallback extends BottomSheetBehavior.BottomSheetCallback {
        public final WindowInsetsCompat insetsCompat;
        public final Boolean lightBottomSheet;
        public boolean lightStatusBar;
        public Window window;

        public EdgeToEdgeCallback(View view, WindowInsetsCompat windowInsetsCompat) {
            ColorStateList colorStateList;
            this.insetsCompat = windowInsetsCompat;
            MaterialShapeDrawable materialShapeDrawable = BottomSheetBehavior.from(view).materialShapeDrawable;
            if (materialShapeDrawable != null) {
                colorStateList = materialShapeDrawable.drawableState.fillColor;
            } else {
                WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                colorStateList = ViewCompat.Api21Impl.getBackgroundTintList(view);
            }
            if (colorStateList != null) {
                this.lightBottomSheet = Boolean.valueOf(MaterialColors.isColorLight(colorStateList.getDefaultColor()));
            } else if (view.getBackground() instanceof ColorDrawable) {
                this.lightBottomSheet = Boolean.valueOf(MaterialColors.isColorLight(((ColorDrawable) view.getBackground()).getColor()));
            } else {
                this.lightBottomSheet = null;
            }
        }

        public final void onLayout(View view) {
            setPaddingForPosition(view);
        }

        public final void onSlide(View view) {
            setPaddingForPosition(view);
        }

        public final void onStateChanged(View view, int i) {
            setPaddingForPosition(view);
        }

        public final void setPaddingForPosition(View view) {
            boolean z;
            int top = view.getTop();
            WindowInsetsCompat windowInsetsCompat = this.insetsCompat;
            if (top < windowInsetsCompat.getSystemWindowInsetTop()) {
                Window window2 = this.window;
                if (window2 != null) {
                    Boolean bool = this.lightBottomSheet;
                    if (bool == null) {
                        z = this.lightStatusBar;
                    } else {
                        z = bool.booleanValue();
                    }
                    new WindowInsetsControllerCompat(window2, window2.getDecorView()).setAppearanceLightStatusBars(z);
                }
                view.setPadding(view.getPaddingLeft(), windowInsetsCompat.getSystemWindowInsetTop() - view.getTop(), view.getPaddingRight(), view.getPaddingBottom());
            } else if (view.getTop() != 0) {
                Window window3 = this.window;
                if (window3 != null) {
                    new WindowInsetsControllerCompat(window3, window3.getDecorView()).setAppearanceLightStatusBars(this.lightStatusBar);
                }
                view.setPadding(view.getPaddingLeft(), 0, view.getPaddingRight(), view.getPaddingBottom());
            }
        }

        public final void setWindow(Window window2) {
            boolean z;
            if (this.window != window2) {
                this.window = window2;
                if (window2 != null) {
                    new SoftwareKeyboardControllerCompat.Impl30(window2.getDecorView());
                    if ((new WindowInsetsControllerCompat.Impl30(window2).mInsetsController.getSystemBarsAppearance() & 8) != 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    this.lightStatusBar = z;
                }
            }
        }
    }

    public final void cancel() {
        if (this.behavior == null) {
            ensureContainerAndBehavior();
        }
        super.cancel();
    }

    public final void ensureContainerAndBehavior() {
        if (this.container == null) {
            FrameLayout frameLayout = (FrameLayout) View.inflate(getContext(), 2131558563, (ViewGroup) null);
            this.container = frameLayout;
            this.coordinator = (CoordinatorLayout) frameLayout.findViewById(2131362358);
            FrameLayout frameLayout2 = (FrameLayout) this.container.findViewById(2131362401);
            this.bottomSheet = frameLayout2;
            BottomSheetBehavior from = BottomSheetBehavior.from(frameLayout2);
            this.behavior = from;
            AnonymousClass5 r1 = this.bottomSheetCallback;
            ArrayList arrayList = from.callbacks;
            if (!arrayList.contains(r1)) {
                arrayList.add(r1);
            }
            this.behavior.setHideable(this.cancelable);
        }
    }

    public final void onAttachedToWindow() {
        boolean z;
        super.onAttachedToWindow();
        Window window = getWindow();
        if (window != null) {
            if (!this.edgeToEdgeEnabled || Color.alpha(window.getNavigationBarColor()) >= 255) {
                z = false;
            } else {
                z = true;
            }
            FrameLayout frameLayout = this.container;
            if (frameLayout != null) {
                frameLayout.setFitsSystemWindows(!z);
            }
            CoordinatorLayout coordinatorLayout = this.coordinator;
            if (coordinatorLayout != null) {
                coordinatorLayout.setFitsSystemWindows(!z);
            }
            window.setDecorFitsSystemWindows(!z);
            EdgeToEdgeCallback edgeToEdgeCallback2 = this.edgeToEdgeCallback;
            if (edgeToEdgeCallback2 != null) {
                edgeToEdgeCallback2.setWindow(window);
            }
        }
    }

    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Window window = getWindow();
        if (window != null) {
            window.setStatusBarColor(0);
            window.addFlags(Integer.MIN_VALUE);
            window.setLayout(-1, -1);
        }
    }

    public final void onDetachedFromWindow() {
        EdgeToEdgeCallback edgeToEdgeCallback2 = this.edgeToEdgeCallback;
        if (edgeToEdgeCallback2 != null) {
            edgeToEdgeCallback2.setWindow((Window) null);
        }
    }

    public final void onStart() {
        super.onStart();
        BottomSheetBehavior bottomSheetBehavior = this.behavior;
        if (bottomSheetBehavior != null && bottomSheetBehavior.state == 5) {
            bottomSheetBehavior.setState(4);
        }
    }

    public final void setCancelable(boolean z) {
        super.setCancelable(z);
        if (this.cancelable != z) {
            this.cancelable = z;
            BottomSheetBehavior bottomSheetBehavior = this.behavior;
            if (bottomSheetBehavior != null) {
                bottomSheetBehavior.setHideable(z);
            }
        }
    }

    public final void setCanceledOnTouchOutside(boolean z) {
        super.setCanceledOnTouchOutside(z);
        if (z && !this.cancelable) {
            this.cancelable = true;
        }
        this.canceledOnTouchOutside = z;
        this.canceledOnTouchOutsideSet = true;
    }

    public final void setContentView(int i) {
        super.setContentView(wrapInBottomSheet((View) null, i, (ViewGroup.LayoutParams) null));
    }

    /* JADX WARNING: type inference failed for: r5v5, types: [java.lang.Object, android.view.View$OnTouchListener] */
    public final View wrapInBottomSheet(View view, int i, ViewGroup.LayoutParams layoutParams) {
        ensureContainerAndBehavior();
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) this.container.findViewById(2131362358);
        if (i != 0 && view == null) {
            view = getLayoutInflater().inflate(i, coordinatorLayout, false);
        }
        if (this.edgeToEdgeEnabled) {
            FrameLayout frameLayout = this.bottomSheet;
            AnonymousClass1 r1 = new OnApplyWindowInsetsListener() {
                public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                    BottomSheetDialog bottomSheetDialog = BottomSheetDialog.this;
                    EdgeToEdgeCallback edgeToEdgeCallback = bottomSheetDialog.edgeToEdgeCallback;
                    if (edgeToEdgeCallback != null) {
                        bottomSheetDialog.behavior.callbacks.remove(edgeToEdgeCallback);
                    }
                    EdgeToEdgeCallback edgeToEdgeCallback2 = new EdgeToEdgeCallback(bottomSheetDialog.bottomSheet, windowInsetsCompat);
                    bottomSheetDialog.edgeToEdgeCallback = edgeToEdgeCallback2;
                    edgeToEdgeCallback2.setWindow(bottomSheetDialog.getWindow());
                    BottomSheetBehavior bottomSheetBehavior = bottomSheetDialog.behavior;
                    EdgeToEdgeCallback edgeToEdgeCallback3 = bottomSheetDialog.edgeToEdgeCallback;
                    ArrayList arrayList = bottomSheetBehavior.callbacks;
                    if (!arrayList.contains(edgeToEdgeCallback3)) {
                        arrayList.add(edgeToEdgeCallback3);
                    }
                    return windowInsetsCompat;
                }
            };
            WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
            ViewCompat.Api21Impl.setOnApplyWindowInsetsListener(frameLayout, r1);
        }
        this.bottomSheet.removeAllViews();
        if (layoutParams == null) {
            this.bottomSheet.addView(view);
        } else {
            this.bottomSheet.addView(view, layoutParams);
        }
        coordinatorLayout.findViewById(2131363898).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                BottomSheetDialog bottomSheetDialog = BottomSheetDialog.this;
                if (bottomSheetDialog.cancelable && bottomSheetDialog.isShowing()) {
                    BottomSheetDialog bottomSheetDialog2 = BottomSheetDialog.this;
                    if (!bottomSheetDialog2.canceledOnTouchOutsideSet) {
                        TypedArray obtainStyledAttributes = bottomSheetDialog2.getContext().obtainStyledAttributes(new int[]{16843611});
                        bottomSheetDialog2.canceledOnTouchOutside = obtainStyledAttributes.getBoolean(0, true);
                        obtainStyledAttributes.recycle();
                        bottomSheetDialog2.canceledOnTouchOutsideSet = true;
                    }
                    if (bottomSheetDialog2.canceledOnTouchOutside) {
                        BottomSheetDialog.this.cancel();
                    }
                }
            }
        });
        ViewCompat.setAccessibilityDelegate(this.bottomSheet, new AccessibilityDelegateCompat() {
            public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                View.AccessibilityDelegate accessibilityDelegate = this.mOriginalDelegate;
                AccessibilityNodeInfo accessibilityNodeInfo = accessibilityNodeInfoCompat.mInfo;
                accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                if (BottomSheetDialog.this.cancelable) {
                    accessibilityNodeInfoCompat.addAction(1048576);
                    accessibilityNodeInfo.setDismissable(true);
                    return;
                }
                accessibilityNodeInfo.setDismissable(false);
            }

            public final boolean performAccessibilityAction(View view, int i, Bundle bundle) {
                if (i == 1048576) {
                    BottomSheetDialog bottomSheetDialog = BottomSheetDialog.this;
                    if (bottomSheetDialog.cancelable) {
                        bottomSheetDialog.cancel();
                        return true;
                    }
                }
                return super.performAccessibilityAction(view, i, bundle);
            }
        });
        this.bottomSheet.setOnTouchListener(new Object());
        return this.container;
    }

    public final void setContentView(View view) {
        super.setContentView(wrapInBottomSheet(view, 0, (ViewGroup.LayoutParams) null));
    }

    public final void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        super.setContentView(wrapInBottomSheet(view, 0, layoutParams));
    }
}
