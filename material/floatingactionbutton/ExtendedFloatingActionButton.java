package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import com.google.android.material.R$styleable;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.internal.DescendantOffsetUtils;
import java.util.List;
import java.util.WeakHashMap;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class ExtendedFloatingActionButton extends MaterialButton implements CoordinatorLayout.AttachedBehavior {
    public static final AnonymousClass4 HEIGHT = new Property("height", 1) {
        public final /* bridge */ /* synthetic */ Object get(Object obj) {
            switch (3) {
                case 0:
                    return get((View) obj);
                case 1:
                    return get((View) obj);
                case 2:
                    return get((View) obj);
                default:
                    return get((View) obj);
            }
        }

        public final /* bridge */ /* synthetic */ void set(Object obj, Object obj2) {
            switch (3) {
                case 0:
                    set((View) obj, (Float) obj2);
                    return;
                case 1:
                    set((View) obj, (Float) obj2);
                    return;
                case 2:
                    set((View) obj, (Float) obj2);
                    return;
                default:
                    set((View) obj, (Float) obj2);
                    return;
            }
        }

        public final Float get(View view) {
            switch (3) {
                case 0:
                    return Float.valueOf((float) view.getLayoutParams().width);
                case 1:
                    return Float.valueOf((float) view.getLayoutParams().height);
                case 2:
                    WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                    return Float.valueOf((float) ViewCompat.Api17Impl.getPaddingStart(view));
                default:
                    WeakHashMap weakHashMap2 = ViewCompat.sViewPropertyAnimatorMap;
                    return Float.valueOf((float) ViewCompat.Api17Impl.getPaddingEnd(view));
            }
        }

        public final void set(View view, Float f) {
            switch (3) {
                case 0:
                    view.getLayoutParams().width = f.intValue();
                    view.requestLayout();
                    return;
                case 1:
                    view.getLayoutParams().height = f.intValue();
                    view.requestLayout();
                    return;
                case 2:
                    int intValue = f.intValue();
                    int paddingTop = view.getPaddingTop();
                    WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                    ViewCompat.Api17Impl.setPaddingRelative(view, intValue, paddingTop, ViewCompat.Api17Impl.getPaddingEnd(view), view.getPaddingBottom());
                    return;
                default:
                    WeakHashMap weakHashMap2 = ViewCompat.sViewPropertyAnimatorMap;
                    ViewCompat.Api17Impl.setPaddingRelative(view, ViewCompat.Api17Impl.getPaddingStart(view), view.getPaddingTop(), f.intValue(), view.getPaddingBottom());
                    return;
            }
        }
    };
    public static final AnonymousClass4 PADDING_END = new Property("paddingEnd", 3) {
        public final /* bridge */ /* synthetic */ Object get(Object obj) {
            switch (3) {
                case 0:
                    return get((View) obj);
                case 1:
                    return get((View) obj);
                case 2:
                    return get((View) obj);
                default:
                    return get((View) obj);
            }
        }

        public final /* bridge */ /* synthetic */ void set(Object obj, Object obj2) {
            switch (3) {
                case 0:
                    set((View) obj, (Float) obj2);
                    return;
                case 1:
                    set((View) obj, (Float) obj2);
                    return;
                case 2:
                    set((View) obj, (Float) obj2);
                    return;
                default:
                    set((View) obj, (Float) obj2);
                    return;
            }
        }

        public final Float get(View view) {
            switch (3) {
                case 0:
                    return Float.valueOf((float) view.getLayoutParams().width);
                case 1:
                    return Float.valueOf((float) view.getLayoutParams().height);
                case 2:
                    WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                    return Float.valueOf((float) ViewCompat.Api17Impl.getPaddingStart(view));
                default:
                    WeakHashMap weakHashMap2 = ViewCompat.sViewPropertyAnimatorMap;
                    return Float.valueOf((float) ViewCompat.Api17Impl.getPaddingEnd(view));
            }
        }

        public final void set(View view, Float f) {
            switch (3) {
                case 0:
                    view.getLayoutParams().width = f.intValue();
                    view.requestLayout();
                    return;
                case 1:
                    view.getLayoutParams().height = f.intValue();
                    view.requestLayout();
                    return;
                case 2:
                    int intValue = f.intValue();
                    int paddingTop = view.getPaddingTop();
                    WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                    ViewCompat.Api17Impl.setPaddingRelative(view, intValue, paddingTop, ViewCompat.Api17Impl.getPaddingEnd(view), view.getPaddingBottom());
                    return;
                default:
                    WeakHashMap weakHashMap2 = ViewCompat.sViewPropertyAnimatorMap;
                    ViewCompat.Api17Impl.setPaddingRelative(view, ViewCompat.Api17Impl.getPaddingStart(view), view.getPaddingTop(), f.intValue(), view.getPaddingBottom());
                    return;
            }
        }
    };
    public static final AnonymousClass4 PADDING_START = new Property("paddingStart", 2) {
        public final /* bridge */ /* synthetic */ Object get(Object obj) {
            switch (3) {
                case 0:
                    return get((View) obj);
                case 1:
                    return get((View) obj);
                case 2:
                    return get((View) obj);
                default:
                    return get((View) obj);
            }
        }

        public final /* bridge */ /* synthetic */ void set(Object obj, Object obj2) {
            switch (3) {
                case 0:
                    set((View) obj, (Float) obj2);
                    return;
                case 1:
                    set((View) obj, (Float) obj2);
                    return;
                case 2:
                    set((View) obj, (Float) obj2);
                    return;
                default:
                    set((View) obj, (Float) obj2);
                    return;
            }
        }

        public final Float get(View view) {
            switch (3) {
                case 0:
                    return Float.valueOf((float) view.getLayoutParams().width);
                case 1:
                    return Float.valueOf((float) view.getLayoutParams().height);
                case 2:
                    WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                    return Float.valueOf((float) ViewCompat.Api17Impl.getPaddingStart(view));
                default:
                    WeakHashMap weakHashMap2 = ViewCompat.sViewPropertyAnimatorMap;
                    return Float.valueOf((float) ViewCompat.Api17Impl.getPaddingEnd(view));
            }
        }

        public final void set(View view, Float f) {
            switch (3) {
                case 0:
                    view.getLayoutParams().width = f.intValue();
                    view.requestLayout();
                    return;
                case 1:
                    view.getLayoutParams().height = f.intValue();
                    view.requestLayout();
                    return;
                case 2:
                    int intValue = f.intValue();
                    int paddingTop = view.getPaddingTop();
                    WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                    ViewCompat.Api17Impl.setPaddingRelative(view, intValue, paddingTop, ViewCompat.Api17Impl.getPaddingEnd(view), view.getPaddingBottom());
                    return;
                default:
                    WeakHashMap weakHashMap2 = ViewCompat.sViewPropertyAnimatorMap;
                    ViewCompat.Api17Impl.setPaddingRelative(view, ViewCompat.Api17Impl.getPaddingStart(view), view.getPaddingTop(), f.intValue(), view.getPaddingBottom());
                    return;
            }
        }
    };
    public static final AnonymousClass4 WIDTH = new Property("width", 0) {
        public final /* bridge */ /* synthetic */ Object get(Object obj) {
            switch (3) {
                case 0:
                    return get((View) obj);
                case 1:
                    return get((View) obj);
                case 2:
                    return get((View) obj);
                default:
                    return get((View) obj);
            }
        }

        public final /* bridge */ /* synthetic */ void set(Object obj, Object obj2) {
            switch (3) {
                case 0:
                    set((View) obj, (Float) obj2);
                    return;
                case 1:
                    set((View) obj, (Float) obj2);
                    return;
                case 2:
                    set((View) obj, (Float) obj2);
                    return;
                default:
                    set((View) obj, (Float) obj2);
                    return;
            }
        }

        public final Float get(View view) {
            switch (3) {
                case 0:
                    return Float.valueOf((float) view.getLayoutParams().width);
                case 1:
                    return Float.valueOf((float) view.getLayoutParams().height);
                case 2:
                    WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                    return Float.valueOf((float) ViewCompat.Api17Impl.getPaddingStart(view));
                default:
                    WeakHashMap weakHashMap2 = ViewCompat.sViewPropertyAnimatorMap;
                    return Float.valueOf((float) ViewCompat.Api17Impl.getPaddingEnd(view));
            }
        }

        public final void set(View view, Float f) {
            switch (3) {
                case 0:
                    view.getLayoutParams().width = f.intValue();
                    view.requestLayout();
                    return;
                case 1:
                    view.getLayoutParams().height = f.intValue();
                    view.requestLayout();
                    return;
                case 2:
                    int intValue = f.intValue();
                    int paddingTop = view.getPaddingTop();
                    WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                    ViewCompat.Api17Impl.setPaddingRelative(view, intValue, paddingTop, ViewCompat.Api17Impl.getPaddingEnd(view), view.getPaddingBottom());
                    return;
                default:
                    WeakHashMap weakHashMap2 = ViewCompat.sViewPropertyAnimatorMap;
                    ViewCompat.Api17Impl.setPaddingRelative(view, ViewCompat.Api17Impl.getPaddingStart(view), view.getPaddingTop(), f.intValue(), view.getPaddingBottom());
                    return;
            }
        }
    };
    public int animState;
    public final ExtendedFloatingActionButtonBehavior behavior;
    public final int collapsedSize;
    public final ChangeSizeStrategy extendStrategy;
    public int extendedPaddingEnd;
    public int extendedPaddingStart;
    public final HideStrategy hideStrategy;
    public boolean isExtended;
    public boolean isTransforming;
    public ColorStateList originalTextCsl;
    public final ShowStrategy showStrategy;
    public final ChangeSizeStrategy shrinkStrategy;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class ChangeSizeStrategy extends BaseMotionStrategy {
        public final boolean extending;
        public final Size size;

        public ChangeSizeStrategy(AnimatorTracker animatorTracker, AnonymousClass1 r3, boolean z) {
            super(ExtendedFloatingActionButton.this, animatorTracker);
            this.size = r3;
            this.extending = z;
        }

        public final AnimatorSet createAnimator() {
            float f;
            int i;
            int i2;
            int i3;
            int i4;
            MotionSpec motionSpec = this.motionSpec;
            if (motionSpec == null) {
                if (this.defaultMotionSpec == null) {
                    this.defaultMotionSpec = MotionSpec.createFromResource(getDefaultMotionSpecResource(), this.context);
                }
                motionSpec = this.defaultMotionSpec;
                motionSpec.getClass();
            }
            boolean hasPropertyValues = motionSpec.hasPropertyValues("width");
            Size size2 = this.size;
            ExtendedFloatingActionButton extendedFloatingActionButton = ExtendedFloatingActionButton.this;
            if (hasPropertyValues) {
                PropertyValuesHolder[] propertyValues = motionSpec.getPropertyValues("width");
                PropertyValuesHolder propertyValuesHolder = propertyValues[0];
                float width = (float) extendedFloatingActionButton.getWidth();
                AnonymousClass1 r10 = (AnonymousClass1) size2;
                int i5 = 1;
                ExtendedFloatingActionButton extendedFloatingActionButton2 = r10.this$0;
                switch (i5) {
                    case 0:
                        i4 = extendedFloatingActionButton2.extendedPaddingEnd + (extendedFloatingActionButton2.getMeasuredWidth() - (((extendedFloatingActionButton2.getCollapsedSize() - extendedFloatingActionButton2.iconSize) / 2) * 2)) + extendedFloatingActionButton2.extendedPaddingStart;
                        break;
                    default:
                        i4 = extendedFloatingActionButton2.getCollapsedSize();
                        break;
                }
                propertyValuesHolder.setFloatValues(new float[]{width, (float) i4});
                motionSpec.setPropertyValues("width", propertyValues);
            }
            if (motionSpec.hasPropertyValues("height")) {
                PropertyValuesHolder[] propertyValues2 = motionSpec.getPropertyValues("height");
                PropertyValuesHolder propertyValuesHolder2 = propertyValues2[0];
                float height = (float) extendedFloatingActionButton.getHeight();
                AnonymousClass1 r102 = (AnonymousClass1) size2;
                int i6 = 1;
                ExtendedFloatingActionButton extendedFloatingActionButton3 = r102.this$0;
                switch (i6) {
                    case 0:
                        i3 = extendedFloatingActionButton3.getMeasuredHeight();
                        break;
                    default:
                        i3 = extendedFloatingActionButton3.getCollapsedSize();
                        break;
                }
                propertyValuesHolder2.setFloatValues(new float[]{height, (float) i3});
                motionSpec.setPropertyValues("height", propertyValues2);
            }
            if (motionSpec.hasPropertyValues("paddingStart")) {
                PropertyValuesHolder[] propertyValues3 = motionSpec.getPropertyValues("paddingStart");
                PropertyValuesHolder propertyValuesHolder3 = propertyValues3[0];
                WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                float paddingStart = (float) ViewCompat.Api17Impl.getPaddingStart(extendedFloatingActionButton);
                AnonymousClass1 r103 = (AnonymousClass1) size2;
                int i7 = 1;
                ExtendedFloatingActionButton extendedFloatingActionButton4 = r103.this$0;
                switch (i7) {
                    case 0:
                        i2 = extendedFloatingActionButton4.extendedPaddingStart;
                        break;
                    default:
                        i2 = (extendedFloatingActionButton4.getCollapsedSize() - extendedFloatingActionButton4.iconSize) / 2;
                        break;
                }
                propertyValuesHolder3.setFloatValues(new float[]{paddingStart, (float) i2});
                motionSpec.setPropertyValues("paddingStart", propertyValues3);
            }
            if (motionSpec.hasPropertyValues("paddingEnd")) {
                PropertyValuesHolder[] propertyValues4 = motionSpec.getPropertyValues("paddingEnd");
                PropertyValuesHolder propertyValuesHolder4 = propertyValues4[0];
                WeakHashMap weakHashMap2 = ViewCompat.sViewPropertyAnimatorMap;
                float paddingEnd = (float) ViewCompat.Api17Impl.getPaddingEnd(extendedFloatingActionButton);
                AnonymousClass1 r6 = (AnonymousClass1) size2;
                int i8 = 1;
                ExtendedFloatingActionButton extendedFloatingActionButton5 = r6.this$0;
                switch (i8) {
                    case 0:
                        i = extendedFloatingActionButton5.extendedPaddingEnd;
                        break;
                    default:
                        i = (extendedFloatingActionButton5.getCollapsedSize() - extendedFloatingActionButton5.iconSize) / 2;
                        break;
                }
                propertyValuesHolder4.setFloatValues(new float[]{paddingEnd, (float) i});
                motionSpec.setPropertyValues("paddingEnd", propertyValues4);
            }
            if (motionSpec.hasPropertyValues("labelOpacity")) {
                PropertyValuesHolder[] propertyValues5 = motionSpec.getPropertyValues("labelOpacity");
                float f2 = 1.0f;
                boolean z = this.extending;
                if (z) {
                    f = 0.0f;
                } else {
                    f = 1.0f;
                }
                if (!z) {
                    f2 = 0.0f;
                }
                propertyValues5[0].setFloatValues(new float[]{f, f2});
                motionSpec.setPropertyValues("labelOpacity", propertyValues5);
            }
            return createAnimator(motionSpec);
        }

        public final int getDefaultMotionSpecResource() {
            if (this.extending) {
                return 2130837548;
            }
            return 2130837547;
        }

        public final void onAnimationEnd() {
            this.tracker.currentAnimator = null;
            ExtendedFloatingActionButton extendedFloatingActionButton = ExtendedFloatingActionButton.this;
            extendedFloatingActionButton.isTransforming = false;
            extendedFloatingActionButton.setHorizontallyScrolling(false);
            ViewGroup.LayoutParams layoutParams = extendedFloatingActionButton.getLayoutParams();
            if (layoutParams != null) {
                AnonymousClass1 r2 = (AnonymousClass1) this.size;
                layoutParams.width = r2.getLayoutParams().width;
                layoutParams.height = r2.getLayoutParams().height;
            }
        }

        public final void onAnimationStart(Animator animator) {
            AnimatorTracker animatorTracker = this.tracker;
            Animator animator2 = animatorTracker.currentAnimator;
            if (animator2 != null) {
                animator2.cancel();
            }
            animatorTracker.currentAnimator = animator;
            boolean z = this.extending;
            ExtendedFloatingActionButton extendedFloatingActionButton = ExtendedFloatingActionButton.this;
            extendedFloatingActionButton.isExtended = z;
            extendedFloatingActionButton.isTransforming = true;
            extendedFloatingActionButton.setHorizontallyScrolling(true);
        }

        public final void performNow() {
            int i;
            int i2;
            boolean z = this.extending;
            ExtendedFloatingActionButton extendedFloatingActionButton = ExtendedFloatingActionButton.this;
            extendedFloatingActionButton.isExtended = z;
            ViewGroup.LayoutParams layoutParams = extendedFloatingActionButton.getLayoutParams();
            if (layoutParams != null) {
                AnonymousClass1 r5 = (AnonymousClass1) this.size;
                layoutParams.width = r5.getLayoutParams().width;
                layoutParams.height = r5.getLayoutParams().height;
                int i3 = 1;
                ExtendedFloatingActionButton extendedFloatingActionButton2 = r5.this$0;
                switch (i3) {
                    case 0:
                        i = extendedFloatingActionButton2.extendedPaddingStart;
                        break;
                    default:
                        i = (extendedFloatingActionButton2.getCollapsedSize() - extendedFloatingActionButton2.iconSize) / 2;
                        break;
                }
                int paddingTop = extendedFloatingActionButton.getPaddingTop();
                int i4 = 1;
                ExtendedFloatingActionButton extendedFloatingActionButton3 = r5.this$0;
                switch (i4) {
                    case 0:
                        i2 = extendedFloatingActionButton3.extendedPaddingEnd;
                        break;
                    default:
                        i2 = (extendedFloatingActionButton3.getCollapsedSize() - extendedFloatingActionButton3.iconSize) / 2;
                        break;
                }
                int paddingBottom = extendedFloatingActionButton.getPaddingBottom();
                WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                ViewCompat.Api17Impl.setPaddingRelative(extendedFloatingActionButton, i, paddingTop, i2, paddingBottom);
                extendedFloatingActionButton.requestLayout();
            }
        }

        public final boolean shouldCancel() {
            ExtendedFloatingActionButton extendedFloatingActionButton = ExtendedFloatingActionButton.this;
            if (this.extending == extendedFloatingActionButton.isExtended || extendedFloatingActionButton.icon == null || TextUtils.isEmpty(extendedFloatingActionButton.getText())) {
                return true;
            }
            return false;
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class HideStrategy extends BaseMotionStrategy {
        public boolean isCancelled;

        public HideStrategy(AnimatorTracker animatorTracker) {
            super(ExtendedFloatingActionButton.this, animatorTracker);
        }

        public final int getDefaultMotionSpecResource() {
            return 2130837549;
        }

        public final void onAnimationCancel() {
            super.onAnimationCancel();
            this.isCancelled = true;
        }

        public final void onAnimationEnd() {
            this.tracker.currentAnimator = null;
            ExtendedFloatingActionButton extendedFloatingActionButton = ExtendedFloatingActionButton.this;
            extendedFloatingActionButton.animState = 0;
            if (!this.isCancelled) {
                extendedFloatingActionButton.setVisibility(8);
            }
        }

        public final void onAnimationStart(Animator animator) {
            AnimatorTracker animatorTracker = this.tracker;
            Animator animator2 = animatorTracker.currentAnimator;
            if (animator2 != null) {
                animator2.cancel();
            }
            animatorTracker.currentAnimator = animator;
            this.isCancelled = false;
            ExtendedFloatingActionButton extendedFloatingActionButton = ExtendedFloatingActionButton.this;
            extendedFloatingActionButton.setVisibility(0);
            extendedFloatingActionButton.animState = 1;
        }

        public final void performNow() {
            ExtendedFloatingActionButton.this.setVisibility(8);
        }

        public final boolean shouldCancel() {
            AnonymousClass4 r0 = ExtendedFloatingActionButton.WIDTH;
            ExtendedFloatingActionButton extendedFloatingActionButton = ExtendedFloatingActionButton.this;
            if (extendedFloatingActionButton.getVisibility() == 0) {
                if (extendedFloatingActionButton.animState != 1) {
                    return false;
                }
            } else if (extendedFloatingActionButton.animState == 2) {
                return false;
            }
            return true;
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public abstract class OnChangedCallback {
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class ShowStrategy extends BaseMotionStrategy {
        public ShowStrategy(AnimatorTracker animatorTracker) {
            super(ExtendedFloatingActionButton.this, animatorTracker);
        }

        public final int getDefaultMotionSpecResource() {
            return 2130837550;
        }

        public final void onAnimationEnd() {
            this.tracker.currentAnimator = null;
            ExtendedFloatingActionButton.this.animState = 0;
        }

        public final void onAnimationStart(Animator animator) {
            AnimatorTracker animatorTracker = this.tracker;
            Animator animator2 = animatorTracker.currentAnimator;
            if (animator2 != null) {
                animator2.cancel();
            }
            animatorTracker.currentAnimator = animator;
            ExtendedFloatingActionButton extendedFloatingActionButton = ExtendedFloatingActionButton.this;
            extendedFloatingActionButton.setVisibility(0);
            extendedFloatingActionButton.animState = 2;
        }

        public final void performNow() {
            ExtendedFloatingActionButton extendedFloatingActionButton = ExtendedFloatingActionButton.this;
            extendedFloatingActionButton.setVisibility(0);
            extendedFloatingActionButton.setAlpha(1.0f);
            extendedFloatingActionButton.setScaleY(1.0f);
            extendedFloatingActionButton.setScaleX(1.0f);
        }

        public final boolean shouldCancel() {
            AnonymousClass4 r0 = ExtendedFloatingActionButton.WIDTH;
            ExtendedFloatingActionButton extendedFloatingActionButton = ExtendedFloatingActionButton.this;
            if (extendedFloatingActionButton.getVisibility() != 0) {
                if (extendedFloatingActionButton.animState != 2) {
                    return false;
                }
            } else if (extendedFloatingActionButton.animState == 1) {
                return false;
            }
            return true;
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public interface Size {
    }

    public ExtendedFloatingActionButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public static void access$400(ExtendedFloatingActionButton extendedFloatingActionButton, final BaseMotionStrategy baseMotionStrategy) {
        if (!baseMotionStrategy.shouldCancel()) {
            WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
            if (!ViewCompat.Api19Impl.isLaidOut(extendedFloatingActionButton)) {
                extendedFloatingActionButton.getVisibility();
            } else if (!extendedFloatingActionButton.isInEditMode()) {
                extendedFloatingActionButton.measure(0, 0);
                AnimatorSet createAnimator = baseMotionStrategy.createAnimator();
                createAnimator.addListener(new AnimatorListenerAdapter() {
                    public boolean cancelled;

                    public final void onAnimationCancel(Animator animator) {
                        this.cancelled = true;
                        BaseMotionStrategy.this.onAnimationCancel();
                    }

                    public final void onAnimationEnd(Animator animator) {
                        BaseMotionStrategy.this.onAnimationEnd();
                        if (!this.cancelled) {
                            BaseMotionStrategy.this.getClass();
                        }
                    }

                    public final void onAnimationStart(Animator animator) {
                        BaseMotionStrategy.this.onAnimationStart(animator);
                        this.cancelled = false;
                    }
                });
                for (Animator.AnimatorListener addListener : baseMotionStrategy.listeners) {
                    createAnimator.addListener(addListener);
                }
                createAnimator.start();
                return;
            }
            baseMotionStrategy.performNow();
        }
    }

    public final CoordinatorLayout.Behavior getBehavior() {
        return this.behavior;
    }

    public int getCollapsedSize() {
        int i = this.collapsedSize;
        if (i >= 0) {
            return i;
        }
        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
        return (Math.min(ViewCompat.Api17Impl.getPaddingStart(this), ViewCompat.Api17Impl.getPaddingEnd(this)) * 2) + this.iconSize;
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.isExtended && TextUtils.isEmpty(getText()) && this.icon != null) {
            this.isExtended = false;
            this.shrinkStrategy.performNow();
        }
    }

    public final void setPadding(int i, int i2, int i3, int i4) {
        super.setPadding(i, i2, i3, i4);
        if (this.isExtended && !this.isTransforming) {
            WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
            this.extendedPaddingStart = ViewCompat.Api17Impl.getPaddingStart(this);
            this.extendedPaddingEnd = ViewCompat.Api17Impl.getPaddingEnd(this);
        }
    }

    public final void setPaddingRelative(int i, int i2, int i3, int i4) {
        super.setPaddingRelative(i, i2, i3, i4);
        if (this.isExtended && !this.isTransforming) {
            this.extendedPaddingStart = i;
            this.extendedPaddingEnd = i3;
        }
    }

    public final void setTextColor(int i) {
        super.setTextColor(i);
        this.originalTextCsl = getTextColors();
    }

    public final void silentlyUpdateTextColor(ColorStateList colorStateList) {
        super.setTextColor(colorStateList);
    }

    /* JADX WARNING: type inference failed for: r1v2, types: [java.lang.Object, com.google.android.material.floatingactionbutton.AnimatorTracker] */
    /* JADX WARNING: type inference failed for: r6v6, types: [java.lang.Object, com.google.android.material.floatingactionbutton.AnimatorTracker] */
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ExtendedFloatingActionButton(android.content.Context r17, android.util.AttributeSet r18, int r19) {
        /*
            r16 = this;
            r0 = r16
            r7 = r18
            r8 = 2130969092(0x7f040204, float:1.7546856E38)
            r9 = 2132018812(0x7f14067c, float:1.9675941E38)
            r1 = r17
            android.content.Context r1 = com.google.android.material.theme.overlay.MaterialThemeOverlay.wrap(r1, r7, r8, r9)
            r0.<init>(r1, r7, r8)
            r10 = 0
            r0.animState = r10
            com.google.android.material.floatingactionbutton.AnimatorTracker r1 = new com.google.android.material.floatingactionbutton.AnimatorTracker
            r1.<init>()
            com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$ShowStrategy r11 = new com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$ShowStrategy
            r11.<init>(r1)
            r0.showStrategy = r11
            com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$HideStrategy r12 = new com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$HideStrategy
            r12.<init>(r1)
            r0.hideStrategy = r12
            r13 = 1
            r0.isExtended = r13
            r0.isTransforming = r10
            android.content.Context r14 = r16.getContext()
            com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$ExtendedFloatingActionButtonBehavior r1 = new com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$ExtendedFloatingActionButtonBehavior
            r1.<init>(r14, r7)
            r0.behavior = r1
            int[] r3 = com.google.android.material.R$styleable.ExtendedFloatingActionButton
            r5 = 2132018812(0x7f14067c, float:1.9675941E38)
            int[] r6 = new int[r10]
            r1 = r14
            r2 = r18
            r4 = r8
            android.content.res.TypedArray r1 = com.google.android.material.internal.ThemeEnforcement.obtainStyledAttributes(r1, r2, r3, r4, r5, r6)
            r2 = 4
            com.google.android.material.animation.MotionSpec r2 = com.google.android.material.animation.MotionSpec.createFromAttribute(r14, r1, r2)
            r3 = 3
            com.google.android.material.animation.MotionSpec r3 = com.google.android.material.animation.MotionSpec.createFromAttribute(r14, r1, r3)
            r4 = 2
            com.google.android.material.animation.MotionSpec r4 = com.google.android.material.animation.MotionSpec.createFromAttribute(r14, r1, r4)
            r5 = 5
            com.google.android.material.animation.MotionSpec r5 = com.google.android.material.animation.MotionSpec.createFromAttribute(r14, r1, r5)
            r6 = -1
            int r6 = r1.getDimensionPixelSize(r10, r6)
            r0.collapsedSize = r6
            java.util.WeakHashMap r6 = androidx.core.view.ViewCompat.sViewPropertyAnimatorMap
            int r6 = androidx.core.view.ViewCompat.Api17Impl.getPaddingStart(r16)
            r0.extendedPaddingStart = r6
            int r6 = androidx.core.view.ViewCompat.Api17Impl.getPaddingEnd(r16)
            r0.extendedPaddingEnd = r6
            com.google.android.material.floatingactionbutton.AnimatorTracker r6 = new com.google.android.material.floatingactionbutton.AnimatorTracker
            r6.<init>()
            com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$ChangeSizeStrategy r15 = new com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$ChangeSizeStrategy
            com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$1 r8 = new com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$1
            r8.<init>(r0, r10)
            r15.<init>(r6, r8, r13)
            r0.extendStrategy = r15
            com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$ChangeSizeStrategy r8 = new com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$ChangeSizeStrategy
            com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$1 r9 = new com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$1
            r9.<init>(r0, r13)
            r8.<init>(r6, r9, r10)
            r0.shrinkStrategy = r8
            r11.motionSpec = r2
            r12.motionSpec = r3
            r15.motionSpec = r4
            r8.motionSpec = r5
            r1.recycle()
            com.google.android.material.shape.RelativeCornerSize r1 = com.google.android.material.shape.ShapeAppearanceModel.PILL
            r2 = 2130969092(0x7f040204, float:1.7546856E38)
            r3 = 2132018812(0x7f14067c, float:1.9675941E38)
            com.google.android.material.shape.ShapeAppearanceModel$Builder r1 = com.google.android.material.shape.ShapeAppearanceModel.builder(r14, r7, r2, r3, r1)
            com.google.android.material.shape.ShapeAppearanceModel r1 = r1.build()
            r0.setShapeAppearanceModel(r1)
            android.content.res.ColorStateList r1 = r16.getTextColors()
            r0.originalTextCsl = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public final void setTextColor(ColorStateList colorStateList) {
        super.setTextColor(colorStateList);
        this.originalTextCsl = getTextColors();
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public class ExtendedFloatingActionButtonBehavior extends CoordinatorLayout.Behavior {
        public final boolean autoHideEnabled;
        public final boolean autoShrinkEnabled;
        public Rect tmpRect;

        public ExtendedFloatingActionButtonBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ExtendedFloatingActionButton_Behavior_Layout);
            this.autoHideEnabled = obtainStyledAttributes.getBoolean(0, false);
            this.autoShrinkEnabled = obtainStyledAttributes.getBoolean(1, true);
            obtainStyledAttributes.recycle();
        }

        public final /* bridge */ /* synthetic */ boolean getInsetDodgeRect(View view, Rect rect) {
            ExtendedFloatingActionButton extendedFloatingActionButton = (ExtendedFloatingActionButton) view;
            return false;
        }

        public final void onAttachedToLayoutParams(CoordinatorLayout.LayoutParams layoutParams) {
            if (layoutParams.dodgeInsetEdges == 0) {
                layoutParams.dodgeInsetEdges = 80;
            }
        }

        public final boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, View view, View view2) {
            ExtendedFloatingActionButton extendedFloatingActionButton = (ExtendedFloatingActionButton) view;
            if (view2 instanceof AppBarLayout) {
                updateFabVisibilityForAppBarLayout(coordinatorLayout, (AppBarLayout) view2, extendedFloatingActionButton);
                return false;
            }
            ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
            if (!(layoutParams instanceof CoordinatorLayout.LayoutParams) || !(((CoordinatorLayout.LayoutParams) layoutParams).mBehavior instanceof BottomSheetBehavior)) {
                return false;
            }
            updateFabVisibilityForBottomSheet(view2, extendedFloatingActionButton);
            return false;
        }

        public final boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i) {
            ExtendedFloatingActionButton extendedFloatingActionButton = (ExtendedFloatingActionButton) view;
            List dependencies = coordinatorLayout.getDependencies(extendedFloatingActionButton);
            int size = dependencies.size();
            for (int i2 = 0; i2 < size; i2++) {
                View view2 = (View) dependencies.get(i2);
                if (!(view2 instanceof AppBarLayout)) {
                    ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
                    if ((layoutParams instanceof CoordinatorLayout.LayoutParams) && (((CoordinatorLayout.LayoutParams) layoutParams).mBehavior instanceof BottomSheetBehavior) && updateFabVisibilityForBottomSheet(view2, extendedFloatingActionButton)) {
                        break;
                    }
                } else if (updateFabVisibilityForAppBarLayout(coordinatorLayout, (AppBarLayout) view2, extendedFloatingActionButton)) {
                    break;
                }
            }
            coordinatorLayout.onLayoutChild(extendedFloatingActionButton, i);
            return true;
        }

        public final boolean updateFabVisibilityForAppBarLayout(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, ExtendedFloatingActionButton extendedFloatingActionButton) {
            BaseMotionStrategy baseMotionStrategy;
            BaseMotionStrategy baseMotionStrategy2;
            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) extendedFloatingActionButton.getLayoutParams();
            if ((!this.autoHideEnabled && !this.autoShrinkEnabled) || layoutParams.mAnchorId != appBarLayout.getId()) {
                return false;
            }
            if (this.tmpRect == null) {
                this.tmpRect = new Rect();
            }
            Rect rect = this.tmpRect;
            DescendantOffsetUtils.getDescendantRect(coordinatorLayout, appBarLayout, rect);
            if (rect.bottom <= appBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
                if (this.autoShrinkEnabled) {
                    baseMotionStrategy2 = extendedFloatingActionButton.shrinkStrategy;
                } else {
                    baseMotionStrategy2 = extendedFloatingActionButton.hideStrategy;
                }
                ExtendedFloatingActionButton.access$400(extendedFloatingActionButton, baseMotionStrategy2);
                return true;
            }
            if (this.autoShrinkEnabled) {
                baseMotionStrategy = extendedFloatingActionButton.extendStrategy;
            } else {
                baseMotionStrategy = extendedFloatingActionButton.showStrategy;
            }
            ExtendedFloatingActionButton.access$400(extendedFloatingActionButton, baseMotionStrategy);
            return true;
        }

        public final boolean updateFabVisibilityForBottomSheet(View view, ExtendedFloatingActionButton extendedFloatingActionButton) {
            BaseMotionStrategy baseMotionStrategy;
            BaseMotionStrategy baseMotionStrategy2;
            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) extendedFloatingActionButton.getLayoutParams();
            if ((!this.autoHideEnabled && !this.autoShrinkEnabled) || layoutParams.mAnchorId != view.getId()) {
                return false;
            }
            if (view.getTop() < (extendedFloatingActionButton.getHeight() / 2) + ((CoordinatorLayout.LayoutParams) extendedFloatingActionButton.getLayoutParams()).topMargin) {
                if (this.autoShrinkEnabled) {
                    baseMotionStrategy2 = extendedFloatingActionButton.shrinkStrategy;
                } else {
                    baseMotionStrategy2 = extendedFloatingActionButton.hideStrategy;
                }
                ExtendedFloatingActionButton.access$400(extendedFloatingActionButton, baseMotionStrategy2);
                return true;
            }
            if (this.autoShrinkEnabled) {
                baseMotionStrategy = extendedFloatingActionButton.extendStrategy;
            } else {
                baseMotionStrategy = extendedFloatingActionButton.showStrategy;
            }
            ExtendedFloatingActionButton.access$400(extendedFloatingActionButton, baseMotionStrategy);
            return true;
        }

        public ExtendedFloatingActionButtonBehavior() {
            this.autoHideEnabled = false;
            this.autoShrinkEnabled = true;
        }

        public void setInternalAutoHideCallback(OnChangedCallback onChangedCallback) {
        }

        public void setInternalAutoShrinkCallback(OnChangedCallback onChangedCallback) {
        }
    }
}
